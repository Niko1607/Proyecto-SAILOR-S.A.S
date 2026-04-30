# 🧵 Sistema de Gestión de Inventarios – SAILOR S.A.S

Aplicación web desarrollada para la empresa SAILOR S.A.S con el objetivo de optimizar la gestión de inventarios, pedidos y usuarios, reemplazando procesos manuales por una solución digital eficiente.

---

## 📌 Descripción

El sistema permite gestionar productos textiles, inventario y pedidos en tiempo real mediante una plataforma web moderna.

Está diseñado para:
- Reducir errores humanos
- Mejorar la trazabilidad
- Centralizar la información
- Optimizar la toma de decisiones

---

## 🛠️ Tecnologías Utilizadas

### 🔹 Frontend
- React.js
- JavaScript / TypeScript
- CSS
- Vite

### 🔹 Backend
- Java
- Spring Boot
- Maven

### 🔹 Base de Datos
- MySQL

### 🔹 Control de Versiones
- Git & GitHub

---

## 🧩 Arquitectura

El sistema sigue una arquitectura cliente-servidor:

Frontend (React) ↔ Backend (Spring Boot API REST) ↔ Base de datos (MySQL)

---

## 📁 Estructura del Proyecto

```bash
Backend/     → API REST (Spring Boot)
Frontend/    → Interfaz de usuario (React)
Docs/        → Documentación (manual técnico y usuario)
.vscode/     → Configuración del entorno

##👥 Roles del Sistema##
##🔹 Administrador##
Gestión de usuarios
Gestión de productos
Visualización de reportes

##🔹 Empleado##
Registro de inventario
Consulta de productos
Gestión de pedidos

##⚙️ Instalación del Proyecto##
##📌 Requisitos##
Node.js
JDK 17+
MySQL
Maven

##🔧 Configuración Base de Datos##
Crear base de datos en MySQL
Configurar credenciales en:
Backend/src/main/resources/application.properties

##▶️ Ejecutar Backend##
cd Backend
mvn spring-boot:run

##Servidor en:##
http://localhost:8081

##▶️ Ejecutar Frontend##
cd Frontend
npm install
npm run dev

##🔗 Endpoints Principales##
##Usuarios##
POST /api/usuarios/login
POST /api/usuarios/registrar
GET /api/usuarios

##Productos##
GET /api/productos
POST /api/productos

##Ventas##
POST /api/ventas
GET /api/ventas

##📊 Funcionalidades##
Autenticación de usuarios
Control de inventario en tiempo real
Gestión de productos textiles
Registro de pedidos
Control de acceso por roles
Reportes básicos

##⚠️ Problemas Comunes##
| Problema          | Solución               |
| ----------------- | ---------------------- |
| Error DB          | Verificar credenciales |
| Backend no inicia | `mvn clean install`    |
| Frontend no carga | `npm install`          |
| Error CORS        | Configurar backend     |

##📌 Estado del Proyecto##

✅ Proyecto finalizado

##👨‍💻 Autor ##

##Nicolás Camilo Moreno Arias ##
## Tecnologia e construccion de software – Universidad Antonio Nariño ##

##📄 Licencia##

##Destinado a producción comercial.##

