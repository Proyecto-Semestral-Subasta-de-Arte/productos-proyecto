# Microservicio de Gestión de Productos ('productos')

## Integrantes
* **Gonzalo Hormazábal**
* **Geraldinne González**


## Descripción
Módulo encargado del catálogo de obras de arte, piezas históricas y antigüedades disponibles. Gestiona las fichas técnicas, tasaciones originales y estados de conservación de los bienes.

* **Puerto:** `8082`
* **Base de Datos:** `productos_db` (MySQL)


## Funcionalidades Clave
* CRUD completo de artículos y lotes de arte.
* Control de inventario y estado del artículo (Disponible, Subastado, Retirado).
* Vinculación con categorías del sistema.


## Configuración ('application.properties')
* server.port=8082
* spring.datasource.url=jdbc:mysql://localhost:3306/productos_db
* spring.datasource.username=root
* spring.datasource.password=
* spring.jpa.hibernate.ddl-auto=update
* logging.level.cl.sda1085.productos=DEBUG


## Pasos para Ejecutar

### 1. Preparación de la Base de Datos
Antes de ejecutar el servicio, crear la conexión a la base de datos de MySQL (XAMPP) corriendo en el puerto 3306 y con el nombre 'productos_db'.

### 2. Verificación de Credenciales
Revisar que el archivo application.properties tenga por defecto, usuario root y contraseña vacía.

### 3. Lanzamiento del Microservicio
Ejecutar (run) la clase principal con la anotación @SpringBootApplication (ProductosApplication.java).

### 4. Reglas de Seguridad
Al consumir los endpoints en Postman, tener en cuenta el comportamiento de la cadena de filtros de seguridad:

* Ver Catálogo (GET /api/productos): Es de acceso público. Permite a cualquier visitante revisar las obras de arte sin autenticarse (No Auth).
