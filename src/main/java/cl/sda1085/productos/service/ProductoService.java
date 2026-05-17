package cl.sda1085.productos.service;

import cl.sda1085.productos.dto.ProductoRequestDTO;
import cl.sda1085.productos.dto.ProductoResponseDTO;
import cl.sda1085.productos.model.Producto;
import cl.sda1085.productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoService {

    //Vínculo con 'repository'
    private final ProductoRepository productoRepository;

    //Método de apoyo para encapsulamiento de datos
    private ProductoResponseDTO mapToDTO(Producto producto){
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getAutor(),
                producto.getDescripcion(),
                producto.getPeriodo(),
                producto.getTecnica(),
                producto.getDimensiones(),
                producto.getPrecioBase(),
                producto.getEstadoAutenticidad(),
                producto.getUrlImagen(),
                producto.getIdCategoria(),
                producto.getIdVendedor()
        );
    }


    //------------------------------
    //CRUD estándar
    //------------------------------

    //Obtener todos los productos
    public List<ProductoResponseDTO> obtenerTodos(){
        log.info("Buscando la lista completa de productos en el catálogo");
        return productoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //Obtener producto por ID
    public ProductoResponseDTO obtenerPorId(Long id){
        log.info("Buscando producto por ID exacto: {}", id);
        return productoRepository.findById(id).map(this::mapToDTO)
                .orElseThrow(() -> {
                    log.warn("Búsqueda fallida: No existe ningún producto con el ID: {}", id);
                    return new RuntimeException("Producto no encontrado con el ID: " + id);
                });
    }

    //Crear (guardar) nuevo producto
    @Transactional
    public ProductoResponseDTO guardar(ProductoRequestDTO dto){
        log.info("Iniciando el proceso de guardado para el producto: '{}' del vendedor ID: {}", dto.getNombre(), dto.getIdVendedor());

        if (productoRepository.existsByNombreAndIdVendedor(dto.getNombre(), dto.getIdVendedor())){
            log.warn("Intento de registro duplicado rechazado: El producto '{}' ya existe para el vendedor {}", dto.getNombre(), dto.getIdVendedor());
            throw new RuntimeException("Ya tienes un producto registrado con este nombre.");
        }

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setAutor(dto.getAutor());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPeriodo(dto.getPeriodo());
        producto.setTecnica(dto.getTecnica());
        producto.setDimensiones(dto.getDimensiones());
        producto.setPrecioBase(dto.getPrecioBase());
        producto.setEstadoAutenticidad(dto.getEstadoAutenticidad());
        producto.setUrlImagen(dto.getUrlImagen());
        producto.setIdCategoria(dto.getIdCategoria());
        producto.setIdVendedor(dto.getIdVendedor());

        //Guardar en la base de datos
        Producto productoGuardado = productoRepository.save(producto);
        log.info("Producto guardado exitosamente en la base de datos con el ID asignado: {}", productoGuardado.getId());

        //Devolver la respuesta como DTO
        return mapToDTO(productoGuardado);
    }

    //Actualizar producto existente
    @Transactional
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto){
        return productoRepository.findById(id).map(productoDisponible -> {
            productoDisponible.setNombre(dto.getNombre());
            productoDisponible.setAutor(dto.getAutor());
            productoDisponible.setDescripcion(dto.getDescripcion());
            productoDisponible.setPeriodo(dto.getPeriodo());
            productoDisponible.setTecnica(dto.getTecnica());
            productoDisponible.setDimensiones(dto.getDimensiones());
            productoDisponible.setPrecioBase(dto.getPrecioBase());
            productoDisponible.setEstadoAutenticidad(dto.getEstadoAutenticidad());
            productoDisponible.setUrlImagen(dto.getUrlImagen());
            productoDisponible.setIdCategoria(dto.getIdCategoria());

            Producto actualizado = productoRepository.save(productoDisponible);
            log.info("Producto con ID: {} actualizado correctamente en el catálogo", id);
            return mapToDTO(actualizado);
        }).orElseThrow(() -> {
            log.warn("Actualización fallida: Imposible modificar. El producto con ID: {} no existe", id);
            return new RuntimeException("No se puede actualizar. Producto no encontrado con el ID: " + id);
        });
    }

    //Eliminar producto
    @Transactional
    public void eliminar(Long id){
        log.info("Solicitud recibida para eliminar el producto con ID: {}", id);

        if (!productoRepository.existsById(id)) {
            log.warn("Eliminación fallida: El producto con ID: {} no existe en el sistema", id);
            throw new RuntimeException("No se puede eliminar. Producto no encontrado con el ID: " + id);
        }

        productoRepository.deleteById(id);
        log.info("Producto con ID: {} eliminado exitosamente del catálogo", id);
    }


    //------------------------------
    //CRUD personalizado
    //------------------------------

    //Buscar producto por nombre
    public List<ProductoResponseDTO> buscarPorNombre(String nombre){
        log.info("Ejecutando buscador parcial de productos por nombre: '{}'", nombre);

        List<ProductoResponseDTO> resultados = productoRepository
                .findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (resultados.isEmpty()) {
            log.warn("Búsqueda parcial vacía: No se encontraron coincidencias para '{}'", nombre);
            throw new RuntimeException("No se encontraron productos que coincidan con el nombre: '" + nombre + "'");
        }
        return resultados;
    }

    //Buscar producto por ID de categoría
    public List<ProductoResponseDTO> buscarPorIdCategoria(Long idCategoria){
        log.info("Filtrando catálogo de productos por ID de Categoría: {}", idCategoria);

        List<ProductoResponseDTO> resultados = productoRepository.findByIdCategoria(idCategoria).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        if (resultados.isEmpty()) {
            log.warn("Filtro vacío: No existen productos registrados bajo la categoría ID: {}", idCategoria);
            throw new RuntimeException("No se encontraron productos en el sistema bajo la categoría ID: " + idCategoria);
        }

        return resultados;
    }

    //Buscar el producto más caro del catálogo
    public ProductoResponseDTO obtenerProductoMasCaro(){
        log.info("Buscando el artículo con el precio base más alto de las subastas activas");
        return productoRepository.findTopByOrderByPrecioBaseDesc()
                .map(this::mapToDTO)
                .orElseThrow(() -> {
                    log.warn("Consulta vacía: El catálogo no posee productos registrados actualmente");
                    return new RuntimeException("No se pudo determinar el producto más caro porque el catálogo está vacío.");
                });
    }

    //Verificar duplicados de producto para un mismo vendedor
    public boolean existeProductoDuplicado(String nombre, Long idVendedor){
        log.info("Iniciando validación de duplicado para el producto '{}' del vendedor ID: {}", nombre, idVendedor);

        boolean existe = productoRepository.existsByNombreAndIdVendedor(nombre, idVendedor);

        if (existe) {
            log.warn("Validación rechazada: El producto '{}' ya existe para el vendedor ID: {}", nombre, idVendedor);
            throw new RuntimeException("Error: El vendedor con ID " + idVendedor + " ya tiene registrado un producto con el nombre '" + nombre + "'");
        }
        return existe;
    }

    //Contar cuántos productos tiene un vendedor específico
    public long contarProductosDeVendedor(Long idVendedor){
        log.info("Contando el volumen total de productos publicados por el vendedor ID: {}", idVendedor);
        return productoRepository.countByIdVendedor(idVendedor);
    }
}
