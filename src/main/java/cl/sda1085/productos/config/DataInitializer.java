package cl.sda1085.productos.config;


import cl.sda1085.productos.model.Producto;
import cl.sda1085.productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    @Override
    public void run (String... args) {

        //Verificar si existen productos para no duplicarlos
        if (productoRepository.count() > 0){
            log.info("Base de datos de productos ya contiene datos. Omitiendo inicialización");
            return;
        }
        log.info("Iniciando la creación de productos de prueba para los 3 vendedores.");

        //Crear productos para la base de datos

        Producto p1 = new Producto(
                null,
                "Vasija Precolombina",
                "Ricardo Venegas",
                "Vasija ceremonial utilizada en rituales",
                "Siglo XV",
                "Cerámica",
                "30cm x 20cm",
                new BigDecimal("150000.00"),
                "PENDIENTE",
                "https://ejemplo.com/vasija.jpg",
                3L,
                1L);

        Producto p2 = new Producto(
                null,
                "Espada Medieval",
                "Sofía Mondaca",
                "Espada de combate con empuñadura de cuero",
                "Siglo XII",
                "Acero y cuero",
                "90cm",
                new BigDecimal("300000.00"),
                "VALIDADO",
                "https://ejemplo.com/espada.jpg",
                2L,
                2L);

        Producto p3 = new Producto(
                null,
                "Pintura Colonial",
                "Javier Espinoza",
                "Retrato de época colonial",
                "Siglo XVIII",
                "Óleo sobre tela",
                "60cm x 40cm",
                new BigDecimal("500000.00"),
                "PENDIENTE",
                "https://ejemplo.com/pintura.jpg",
                1L,
                3L);

        Producto p4 = new Producto(
                null,
                "Máscara Tribal Africana",
                "Elena Soto",
                "Máscara ceremonial utilizada en rituales espirituales",
                "Siglo XIX",
                "Madera tallada",
                "50cm x 25cm",
                new BigDecimal("220000.00"),
                "PENDIENTE",
                "https://ejemplo.com/mascara.jpg",
                2L,
                1L);

        //Categoría equivalente más cercana; ya que no existe, se puede hacer POST en 'categorías' y crearla.

        Producto p5 = new Producto(
                null,
                "Reloj de Bolsillo Antiguo",
                "Marcos Duarte",
                "Reloj mecánico de bolsillo en perfecto estado",
                "1900",
                "Oro y vidrio",
                "5cm diámetro",
                new BigDecimal("400000.00"),
                "VALIDADO",
                "https://ejemplo.com/reloj.jpg",
                5L,
                2L);

        Producto p6 = new Producto(
                null,
                "Escultura de Mármol",
                "Valeria Rojas",
                "Réplica detallada de escultura renacentista",
                "Siglo XVI",
                "Mármol",
                "70cm",
                new BigDecimal("350000.00"),
                "PENDIENTE",
                "https://ejemplo.com/escultura.jpg",
                7L,
                3L);

        Producto p7 = new Producto(
                null,
                "Libro Antiguo",
                "Andrés Ibarra",
                "Libro histórico con encuadernación original",
                "Siglo XVIII",
                "Papel y cuero",
                "25cm x 15cm",
                new BigDecimal("180000.00"),
                "PENDIENTE",
                "https://ejemplo.com/libro.jpg",
                6L,
                1L);

        //Categoría equivalente más cercana; ya que no existe, se puede hacer POST en 'categorías' y crearla.

        Producto p8 = new Producto(
                null,
                "Moneda Romana",
                "Lucía Navarro",
                "Moneda de bronce con inscripciones originales",
                "Siglo I",
                "Bronce",
                "3cm",
                new BigDecimal("270000.00"),
                "VALIDADO",
                "https://ejemplo.com/moneda.jpg",
                4L,
                2L);

        Producto p9 = new Producto(
                null,
                "Jarrón Chino Antiguo",
                "Fernando Tapia",
                "Jarrón decorativo con detalles pintados a mano",
                "Siglo XV",
                "Porcelana",
                "40cm",
                new BigDecimal("600000.00"),
                "PENDIENTE",
                "https://ejemplo.com/jarron.jpg",
                3L,
                3L);

        Producto p10 = new Producto(
                null,
                "Cámara Fotográfica Vintage",
                "Camila Osorio",
                "Cámara analógica en funcionamiento",
                "1950",
                "Metal y plástico",
                "15cm x 10cm",
                new BigDecimal("210000.00"),
                "VALIDADO",
                "https://ejemplo.com/camara.jpg",
                5L,
                2L);

        //Categoría equivalente más cercana; ya que no existe, se puede hacer POST en 'categorías' y crearla.

        Producto p11 = new Producto(
                null,
                "Tapiz Medieval",
                "Gabriel Vargas",
                "Tapiz decorativo con escenas de caza",
                "Siglo XIV",
                "Lana y lino",
                "200cm x 150cm",
                new BigDecimal("520000.00"),
                "PENDIENTE",
                "https://ejemplo.com/tapiz.jpg",
                1L,
                1L);

        Producto p12 = new Producto(
                null,
                "Anillo Victoriano",
                "Martina Silva",
                "Anillo antiguo con incrustaciones de piedras",
                "Siglo XIX",
                "Oro y amatista",
                "Talla 7",
                new BigDecimal("310000.00"),
                "VALIDADO",
                "https://ejemplo.com/anillo.jpg",
                1L,
                2L);

        //Categoría equivalente más cercana; ya que no existe, se puede hacer POST en 'categorías' y crearla.

        Producto p13 = new Producto(
                null,
                "Mapa Antiguo de América",
                "Hugo Morales",
                "Mapa detallado previo a divisiones modernas",
                "1700",
                "Papel pergamino",
                "60cm x 45cm",
                new BigDecimal("450000.00"),
                "PENDIENTE",
                "https://ejemplo.com/mapa.jpg",
                6L,
                3L);

        Producto p14 = new Producto(
                null,
                "Armadura Samurai",
                "Beatriz Contreras",
                "Armadura tradicional completa",
                "Siglo XVII",
                "Hierro y seda",
                "Tamaño adulto",
                new BigDecimal("800000.00"),
                "VALIDADO",
                "https://ejemplo.com/armadura.jpg",
                6L,
                2L);

        Producto p15 = new Producto(
                null,
                "Caja Musical Antigua",
                "Óscar Pizarro",
                "Caja musical funcional con melodía clásica",
                "Siglo XIX",
                "Madera y metal",
                "20cm x 10cm",
                new BigDecimal("260000.00"),
                "PENDIENTE",
                "https://ejemplo.com/cajamusical.jpg",
                8L,
                1L);

        Producto p16 = new Producto(
                null,
                "Lámpara de Aceite",
                "Natalia Fuentes",
                "Lámpara decorativa de uso doméstico",
                "Siglo XVIII",
                "Bronce",
                "25cm",
                new BigDecimal("190000.00"),
                "PENDIENTE",
                "https://ejemplo.com/lampara.jpg",
                8L,
                3L);

        Producto p17 = new Producto(
                null,
                "Busto Romano",
                "Sebastián Bravo",
                "Escultura representando figura romana",
                "Siglo II",
                "Mármol",
                "45cm",
                new BigDecimal("670000.00"),
                "VALIDADO",
                "https://ejemplo.com/busto.jpg",
                7L,
                2L);

        Producto p18 = new Producto(
                null,
                "Espejo Antiguo",
                "Daniela Méndez",
                "Espejo con marco ornamentado",
                "Siglo XVIII",
                "Madera y vidrio",
                "80cm x 50cm",
                new BigDecimal("340000.00"),
                "PENDIENTE",
                "https://ejemplo.com/espejo.jpg",
                8L,
                1L);

        Producto p19 = new Producto(
                null,
                "Pluma Estilográfica Vintage",
                "Roberto García",
                "Pluma de colección en excelente estado",
                "1950",
                "Resina y metal",
                "14cm",
                new BigDecimal("230000.00"),
                "VALIDADO",
                "https://ejemplo.com/pluma.jpg",
                8L,
                3L);

        //Categoría equivalente más cercana; ya que no existe, se puede hacer POST en 'categorías' y crearla.

        Producto p20 = new Producto(
                null,
                "Baúl Antiguo",
                "Claudia Herrera",
                "Baúl de viaje con herrajes originales",
                "Siglo XIX",
                "Madera y hierro",
                "90cm x 50cm",
                new BigDecimal("380000.00"),
                "PENDIENTE",
                "https://ejemplo.com/baul.jpg",
                8L,
                2L);


        //Lista de productos
        productoRepository.saveAll(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
                                            p11, p12, p13, p14, p15, p16, p17, p18, p19, p20));

        log.info("Productos creados correctamente");
    }
}
