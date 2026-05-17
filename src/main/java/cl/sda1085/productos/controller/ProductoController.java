package cl.sda1085.productos.controller;

import cl.sda1085.productos.dto.ProductoRequestDTO;
import cl.sda1085.productos.dto.ProductoResponseDTO;
import cl.sda1085.productos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    //Conexión con 'service'
    private final ProductoService productoService;


    //------------------------------
    //CRUD estándar
    //------------------------------

    //Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    //Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }
    //Crear (guardar) nuevo producto
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> guardar(@Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.guardar(dto));
    }

    //Actualizar producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {

        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    //------------------------------
    //CRUD personalizado
    //------------------------------

    //Buscar producto por nombre
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorNombre(@PathVariable String nombre){
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    //Buscar producto por ID de categoría
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorIdCategoria(@PathVariable Long idCategoria){
        return ResponseEntity.ok(productoService.buscarPorIdCategoria(idCategoria));
    }

    //Buscar el producto más caro del catálogo
    @GetMapping("/destacado/mas-caro")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoMasCaro(){
        return ResponseEntity.ok(productoService.obtenerProductoMasCaro());
    }

    //Verificar duplicados de producto para un mismo vendedor
    @GetMapping("/verificar-duplicado")
    public ResponseEntity<Boolean> verificarDuplicado(
            @RequestParam String nombre,
            @RequestParam Long idVendedor){
        return ResponseEntity.ok(productoService.existeProductoDuplicado(nombre, idVendedor));
    }

    //Contar cuántos productos tiene un vendedor específico
    @GetMapping("/estadisticas/vendedor/{idVendedor}/conteo")
    public ResponseEntity<Long> contaerPorVendedor(@PathVariable Long idVendedor){
        return ResponseEntity.ok(productoService.contarProductosDeVendedor(idVendedor));
    }
}
