# Backend de la aplicación web BreadCredit
## Configuración para el desarrollo
- Usar `docker-compose up --build` para correr el proyecto y `Ctrl + c` para detener
- Usar `docker image prune` para eliminar todas las imágenes de Docker que no tienen etiquetas asignadas (usar con precaución)

## Pruebas del API
- Importar colecciones de postman en `.\postman\`
- Swagger UI: http://localhost:8080/swagger-ui/index.html

## Algunas consideraciones acerca del proyecto
- IntelliJ IDEA 2024.1 (Ultimate Edition)
- Spring Boot 3.2.5 - Java 17
- Proyecto basado en Maven
- Plugins usados: JPA Buddy
- Dependencias: Lombok, PostgreSQL Driver, Spring Boot DevTools, Spring Web, and Spring Data JPA
- Dependencias Externas: [ModelMapper](https://modelmapper.org/)