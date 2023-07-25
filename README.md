# SpringBootProject
# Spring Boot Microservices Project

Este proyecto es una aplicación de microservicios construida con Spring Boot. Cada microservicio es un proyecto Maven independiente.

## Estructura del Proyecto

El proyecto consta de varios módulos, cada uno representando un microservicio diferente:

- `api-gateway`: Este módulo actúa como un gateway de API, proporcionando un punto de entrada único para todas las solicitudes de los clientes.

- `auth-service`: Este módulo se encarga de la autenticación y la generación de tokens JWT.

- `eureka-server`: Este módulo actúa como un servidor de descubrimiento de servicios, permitiendo que los microservicios se descubran entre sí.

- `order-service`: Este módulo gestiona las operaciones relacionadas con los pedidos.

- `product-service`: Este módulo gestiona las operaciones relacionadas con los productos.

- `user-service`: Este módulo gestiona las operaciones relacionadas con los usuarios.

Cada uno de estos módulos tiene su propia estructura de directorios `src/main/java` y `src/test/java`.

## Cómo ejecutar el proyecto

Para ejecutar el proyecto, necesitarás tener instalado Java y Maven. Una vez que los tengas instalados, puedes ejecutar cada microservicio con el siguiente comando:

```bash
./mvnw spring-boot:run
Esto iniciará la aplicación en el puerto especificado en el archivo application.properties de cada microservicio.

Cómo ejecutar las pruebas
Para ejecutar las pruebas del proyecto, puedes usar el siguiente comando:

bash
Copy code
./mvnw test
Contribuir
Las contribuciones son bienvenidas. Por favor, abre un issue para discutir lo que te gustaría cambiar o enviar un pull request.

Por favor, ten en cuenta que esta es una documentación básica y puede que necesites añadir más detalles dependiendo de las características específicas de tu proyecto.
