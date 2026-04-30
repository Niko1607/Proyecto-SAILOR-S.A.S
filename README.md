# 🧵 Sistema de Gestión de Inventarios — SAILOR S.A.S

Aplicación web para SAILOR S.A.S diseñada para optimizar la gestión de inventarios, pedidos y usuarios, reemplazando procesos manuales por una solución digital eficiente y trazable.

---

## 📌 Resumen

El sistema permite gestionar productos textiles, controlar inventario y procesar pedidos en tiempo real a través de una interfaz web. Está pensado para:
- Reducir errores humanos
- Mejorar la trazabilidad de movimientos de inventario
- Centralizar la información de productos y pedidos
- Facilitar la toma de decisiones mediante reportes básicos

---

## 🛠️ Tecnologías

- Frontend: React (Vite), JavaScript / TypeScript, CSS
- Backend: Java, Spring Boot, Maven
- Base de datos: MySQL
- Control de versiones: Git / GitHub

---

## 🧩 Arquitectura

Cliente (React) ↔ API REST (Spring Boot) ↔ MySQL

---

## 📁 Estructura del repositorio

- Backend/     → API REST (Spring Boot)
- Frontend/    → Interfaz de usuario (React + Vite)
- Docs/        → Documentación (manual técnico y de usuario)
- .vscode/     → Configuración del entorno

---

## 👥 Roles del sistema

- Administrador
  - Gestión de usuarios (crear, editar, eliminar, asignar roles)
  - Gestión de productos (CRUD)
  - Visualización de reportes y estadísticas

- Empleado
  - Registrar movimientos de inventario
  - Consultar productos
  - Gestionar pedidos (crear/actualizar)

---

## ⚙️ Requisitos

- Node.js (v16+ recomendado)
- JDK 17+
- MySQL (o compatible)
- Maven
- Git

---

## 🔧 Configuración de la base de datos (ejemplo)

1. Crear la base de datos en MySQL:
   - Nombre sugerido: sailor_db
2. Crear un usuario y otorgar permisos (ejemplo):
   - mysql> CREATE DATABASE sailor_db;
   - mysql> CREATE USER 'sailor'@'localhost' IDENTIFIED BY 'tu_password';
   - mysql> GRANT ALL PRIVILEGES ON sailor_db.* TO 'sailor'@'localhost';
   - mysql> FLUSH PRIVILEGES;

3. Configurar credenciales en:
   - Backend/src/main/resources/application.properties
   - O usar variables de entorno (recomendado):

Ejemplo de propiedades (application.properties):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sailor_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=sailor
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

También puedes usar variables de entorno:
- DB_URL (jdbc...)
- DB_USER
- DB_PASSWORD
- SERVER_PORT

---

## ▶️ Ejecutar Backend (desarrollo)

Desde la carpeta Backend:
```bash
cd Backend
mvn clean install
mvn spring-boot:run
```

API por defecto en:
http://localhost:8081

Si usas un IDE (IntelliJ/Eclipse), importa el proyecto Maven y ejecuta la clase principal de Spring Boot.

---

## ▶️ Ejecutar Frontend (desarrollo)

Desde la carpeta Frontend:
```bash
cd Frontend
npm install
# si usas yarn: yarn install
npm run dev
```

Si Vite necesita la URL de la API, crea un archivo .env.local en Frontend con:
```
VITE_API_URL=http://localhost:8081/api
```

---

## 🔗 Endpoints principales (ejemplos)

Usuarios
- POST /api/usuarios/login              — Autenticación (email/contraseña)
- POST /api/usuarios/registrar          — Crear usuario
- GET  /api/usuarios                    — Listar usuarios (rol: administrador)

Productos
- GET  /api/productos                   — Listar productos
- GET  /api/productos/{id}              — Detalle producto
- POST /api/productos                   — Crear producto (administrador)
- PUT  /api/productos/{id}              — Actualizar producto
- DELETE /api/productos/{id}            — Eliminar producto

Inventario / Movimientos
- GET  /api/inventario                  — Estado de inventario
- POST /api/inventario/movimientos      — Registrar entrada/salida

Ventas / Pedidos
- POST /api/ventas                      — Crear venta/pedido
- GET  /api/ventas                      — Listar ventas

Notas:
- Muchos endpoints estarán protegidos por roles y requieren token JWT en Authorization: Bearer <token>.
- Ajusta rutas según tu implementación real en el Backend.

---

## 📦 Scripts y comandos útiles

Backend
- mvn clean install
- mvn spring-boot:run
- mvn test

Frontend
- npm install
- npm run dev
- npm run build

---

## 🧪 Pruebas y utilidades

- Recomiendo usar Postman o Insomnia para probar la API.
- Si integras migraciones, agregar Flyway o Liquibase mejora despliegues.
- Documentación de API: considera añadir Swagger/OpenAPI en el backend:
  - Dependencia springfox o springdoc-openapi.

---

## ⚠️ Problemas comunes y soluciones

| Problema             | Solución recomendada                                    |
|----------------------|----------------------------------------------------------|
| Error DB             | Verificar URL, usuario y contraseña; revisar puerto MySQL|
| Backend no inicia    | Ejecutar `mvn clean install` y revisar logs de Spring    |
| Frontend no carga    | Ejecutar `npm install` y revisar `VITE_API_URL`         |
| Error CORS           | Configurar CORS en Spring (permitir origen del frontend) |

---

## ✅ Estado del proyecto

Proyecto finalizado (según lo declarado). Recomendaciones:
- Añadir pruebas automatizadas (unit/integration).
- Añadir CI (GitHub Actions) para construir y testear automáticamente.
- Añadir despliegue (Docker, Docker Compose / Kubernetes) para producción.

---

## 👨‍💻 Autor

Nicolás Camilo Moreno Arias  
Tecnología en Construcción de Software — Universidad Antonio Nariño

---

## 📄 Licencia

(Indica aquí la licencia que prefieras, por ejemplo MIT, Apache-2.0, o “Privado / Uso comercial permitido” si es necesario.)

Ejemplo MIT:
```text
MIT License
Copyright (c) 2026 Nicolás Camilo Moreno Arias
...
```

Si el proyecto es para uso comercial privado, describe las restricciones aquí.

---
