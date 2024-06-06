# Backend de la aplicación web BreadCredit
## Configuración para el desarrollo
- Usar `docker-compose build` para compilar.
- Usar `docker-compose up` para correr el proyecto y `Ctrl + c` para detener.
- Opcional para correr en segundo plano, usar `docker-compose up -d` para correr el proyecto y `docker-compose down` para detenerlo. 

## Pruebas del API
- Importar colecciones de postman en `.\postman\`

## Algunas consideraciones acerca del proyecto
- IntelliJ IDEA 2024.1 (Ultimate Edition)
- Spring Boot 3.2.5 - Java 17
- Proyecto basado en Maven
- Plugins usados: JPA Buddy
- Dependencias: Lombok, PostgreSQL Driver, Spring Boot DevTools, Spring Web, and Spring Data JPA
- Dependencias Externas: [ModelMapper](https://modelmapper.org/)