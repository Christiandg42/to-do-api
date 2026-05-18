To Do API - Proyecto Final DWES

API REST desarrollada con Spring Boot para gestionar tareas, usuarios, categorías y etiquetas.

Tecnologías utilizadas
Java
Spring Boot
Spring Web
Spring Data JPA
Hibernate
MySQL
MySQL Workbench
Spring Security
Basic Auth
Swagger / OpenAPI
Lombok
Base de datos

El proyecto usa MySQL como sistema gestor de base de datos.

Nombre de la base de datos:

CREATE DATABASE tododb;

Configuración usada:

Base de datos: tododb
Usuario: root
Contraseña: sin contraseña

La aplicación está configurada para crear las tablas automáticamente al arrancar mediante JPA/Hibernate.

En application.properties se usa:

spring.jpa.hibernate.ddl-auto=create

Esto permite que Hibernate cree de nuevo las tablas al iniciar la aplicación y ejecute el archivo import.sql.

Datos iniciales

El proyecto incluye un archivo de carga inicial:

src/main/resources/import.sql

Este archivo inserta usuarios iniciales para poder probar la API desde el primer arranque.

Usuarios iniciales:

ADMIN
usuario: admin
contraseña: 1234
GESTOR
usuario: gestor
contraseña: 1234
USER
usuario: user
contraseña: 1234

Las contraseñas están guardadas cifradas con BCrypt.

Ejecución
Crear la base de datos tododb en MySQL Workbench:

CREATE DATABASE tododb;

Abrir el proyecto en IntelliJ IDEA.
Ejecutar la clase principal:

TodoapiApplication

Abrir Swagger:

http://localhost:8080/swagger-ui/index.html

Seguridad

La API usa Spring Security con Basic Auth.

El registro público está disponible en:

POST /auth/register

El resto de endpoints requieren autenticación según rol.

Roles
ADMIN: puede gestionar usuarios, roles y categorías.
GESTOR: puede gestionar categorías.
USER: puede gestionar tareas y etiquetas.
Funcionalidades implementadas
CRUD de tareas.
CRUD de usuarios.
CRUD de categorías.
CRUD de etiquetas.
Promoción de usuario a gestor.
Degradación de gestor a usuario.
Asociación de tareas con usuario, categoría y etiquetas.
Asignación de etiquetas a tareas creadas.
Eliminación de etiquetas de tareas creadas.
Búsqueda de tareas por etiqueta.
Búsqueda de tareas por estado.
Búsqueda de tareas por prioridad.
Búsqueda de tareas por deadline.
Registro público mediante POST /auth/register.
Seguridad con Basic Auth.
Roles ADMIN, GESTOR y USER.
Contraseñas cifradas con BCrypt.
Gestión de errores 404 mediante controlador global.
Entidades principales
User
Task
Category
Tag

La entidad Task incluye campos adicionales respecto al modelo inicial:

deadline
status
priority
Endpoints principales
Auth

POST /auth/register

Usuarios

GET /api/users
GET /api/users/{id}
POST /api/users
PUT /api/users/{id}
DELETE /api/users/{id}
PUT /api/users/{id}/promote
PUT /api/users/{id}/demote

Tareas

GET /api/tasks
GET /api/tasks/{id}
POST /api/tasks
PUT /api/tasks/{id}
DELETE /api/tasks/{id}
GET /api/tasks/status/{status}
GET /api/tasks/priority/{priority}
GET /api/tasks/deadline/{deadline}
GET /api/tasks/tag/{tagId}
PUT /api/tasks/{taskId}/tags/{tagId}
DELETE /api/tasks/{taskId}/tags/{tagId}

Categorías

GET /api/categories
GET /api/categories/{id}
POST /api/categories
PUT /api/categories/{id}
DELETE /api/categories/{id}

Etiquetas

GET /api/tags
GET /api/tags/{id}
POST /api/tags
PUT /api/tags/{id}
DELETE /api/tags/{id}

Documentación Swagger

Disponible en:

http://localhost:8080/swagger-ui/index.html

Notas

El proyecto está preparado para ejecutarse en local con MySQL.

Antes de arrancar la aplicación debe existir la base de datos tododb.

Al iniciar la aplicación, Hibernate crea las tablas y ejecuta import.sql, dejando disponibles los usuarios iniciales admin, gestor y user.
