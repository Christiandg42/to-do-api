# To Do API - Proyecto Final DWES

API REST desarrollada con Spring Boot para gestionar tareas, usuarios, categorías y etiquetas.

## Tecnologías

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Spring Security
- Swagger / OpenAPI
- Lombok

## Funcionalidades

- CRUD de tareas
- CRUD de usuarios
- CRUD de categorías
- CRUD de etiquetas
- Promoción de usuario a gestor
- Degradación de gestor a usuario
- Asociación de tareas con usuario, categoría y etiquetas
- Asignación y eliminación de etiquetas en tareas creadas
- Búsqueda de tareas por estado, prioridad y etiqueta
- Seguridad con Basic Auth
- Roles ADMIN, GESTOR y USER

## Swagger

Una vez arrancado el proyecto:

http://localhost:8080/swagger-ui/index.html

## Usuarios de prueba

ADMIN:
- usuario: admin3
- contraseña: 1234

USER:
- usuario: user3
- contraseña: 1234

## Seguridad

La API usa Spring Security con Basic Auth.  
El registro de usuarios es público.  
El resto de endpoints requieren autenticación.
