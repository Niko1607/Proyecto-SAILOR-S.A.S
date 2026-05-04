# 📚 Documentación - SAILOR S.A.S

Bienvenido a la documentación completa del proyecto SAILOR S.A.S. Aquí encontrarás guías, referencia de API y arquitectura del sistema.

---

## 📖 Índice de Documentación

### 🚀 Inicio Rápido
- **[INSTALACION.md](INSTALACION.md)** - Guía paso a paso para instalar y ejecutar el proyecto
  - Requisitos previos
  - Instalación de Backend
  - Instalación de Frontend
  - Configuración de Base de Datos
  - Troubleshooting

### 🏗️ Arquitectura
- **[ARQUITECTURA.md](ARQUITECTURA.md)** - Descripción general de la arquitectura del sistema
  - Diagrama de capas
  - Flujo de autenticación
  - Modelo de datos
  - Patrones de diseño
  - Deployment

### 📡 API
- **[API_ENDPOINTS.md](API_ENDPOINTS.md)** - Referencia completa de endpoints REST
  - Autenticación
  - Usuarios (CRUD)
  - Productos (CRUD)
  - Ventas
  - Detalles de Venta
  - Ejemplos de uso

### 📖 Documentación de Componentes
- **[BACKEND.md](../backend/README.md)** - Documentación detallada del Backend
  - Configuración
  - Ejecución
  - Seguridad
  - Testing
  - Troubleshooting

- **[FRONTEND.md](../Frontend/README.md)** - Documentación detallada del Frontend
  - Instalación
  - Rutas
  - Componentes
  - Autenticación
  - Testing

---

## 📊 Estructura del Proyecto

```
Proyecto-SAILOR S.A.S/
├── backend/                    # API Java Spring Boot
│   ├── README.md               # Documentación Backend
│   ├── pom.xml
│   └── src/
│       └── main/java/com/sailor/backend/
│           ├── controller/     # REST Controllers
│           ├── service/        # Lógica de negocio
│           ├── repository/     # Acceso a datos
│           ├── entity/         # Modelos JPA
│           ├── dto/            # Data Transfer Objects
│           ├── security/       # JWT Provider
│           ├── exception/      # Global Exception Handler
│           └── config/         # Configuración (CORS, etc)
│
├── Frontend/                   # App React + TypeScript
│   ├── README.md               # Documentación Frontend
│   ├── package.json
│   └── src/
│       ├── components/         # Componentes React
│       ├── pages/              # Páginas SPA
│       ├── services/           # API clients
│       ├── hooks/              # Custom hooks
│       ├── context/            # React Context
│       ├── lib/                # Utilidades
│       └── data/               # Datos estáticos
│
└── Docs/                       # Documentación general
    ├── README.md               # Este archivo
    ├── INSTALACION.md
    ├── ARQUITECTURA.md
    ├── API_ENDPOINTS.md
    ├── Manual Tecnico.docx
    └── Manual Usuario.docx
```

---

## 🎯 Guías por Caso de Uso

### Para Desarrolladores

1. **Primeros pasos:**
   - Lee [INSTALACION.md](INSTALACION.md)
   - Instala Backend y Frontend
   - Ejecuta `npm run dev` y `mvn spring-boot:run`

2. **Entender la arquitectura:**
   - Lee [ARQUITECTURA.md](ARQUITECTURA.md)
   - Familiarízate con capas y patrones

3. **Desarrollar features:**
   - Consulta [API_ENDPOINTS.md](API_ENDPOINTS.md)
   - Lee [backend/README.md](../backend/README.md) para backend
   - Lee [Frontend/README.md](../Frontend/README.md) para frontend

4. **Testing y debugging:**
   - Usa MVN test para backend
   - Usa `npm run test` para frontend
   - DevTools del navegador (F12)

### Para DevOps / Deployment

1. **Configuración de entorno:**
   - Usa variables de entorno para credenciales
   - Actualiza `application.properties` con BD en producción
   - Configura CORS para dominio de producción

2. **Docker (futuro):**
   - Crear Dockerfile para backend
   - Crear Dockerfile para frontend
   - Docker Compose para orquestación

3. **CI/CD (futuro):**
   - GitHub Actions para tests
   - Deploy automático a servidor

### Para QA / Testing

1. **Manual testing:**
   - Leer [API_ENDPOINTS.md](API_ENDPOINTS.md)
   - Usar Postman/Insomnia para API calls
   - Probar flujos de usuario en interfaz

2. **Casos de prueba:**
   - Login (válido/inválido)
   - CRUD de productos
   - Paginación
   - Validación de errores

### Para Usuarios Finales

1. **Instalación:**
   - Lee [INSTALACION.md](INSTALACION.md) - Sección "Instalar Base de Datos"

2. **Uso del sistema:**
   - Consulta Manual Usuario.docx (en Docs/)

---

## 🔧 Tecnologías

### Backend
- **Java 17** - Lenguaje de programación
- **Spring Boot 3.5.13** - Framework web
- **Spring Data JPA** - ORM
- **MySQL 8** - Base de datos
- **JWT (JJWT 0.12.3)** - Autenticación
- **BCrypt** - Encriptación de contraseñas
- **Maven** - Build tool

### Frontend
- **React 18+** - UI library
- **TypeScript** - Lenguaje tipado
- **Vite** - Build tool
- **TailwindCSS** - Estilos
- **shadcn/ui** - Componentes
- **React Router v6** - Routing
- **Vitest** - Testing

---

## 🔐 Seguridad

### Implementado
✅ JWT Tokens con expiración 24h
✅ BCrypt para contraseñas
✅ CORS configurado
✅ Validación de entrada
✅ Excepciones centralizadas
✅ Autenticación en todos endpoints

### Por implementar (futuro)
- [ ] Refresh tokens
- [ ] Rate limiting
- [ ] 2FA
- [ ] HTTPS obligatorio
- [ ] Logging de auditoría
- [ ] Encryption at rest

---

## 📱 Endpoints Principales

### Autenticación
```
POST   /api/usuarios/login              - Login
POST   /api/usuarios/registrar          - Registro
```

### Usuarios
```
GET    /api/usuarios/paginado           - Listar (paginado)
GET    /api/usuarios/{id}               - Obtener por ID
PUT    /api/usuarios/{id}               - Actualizar
DELETE /api/usuarios/{id}               - Eliminar
```

### Productos
```
GET    /api/productos                   - Listar todos
GET    /api/productos/paginado          - Listar (paginado)
GET    /api/productos/{id}              - Obtener por ID
POST   /api/productos                   - Crear
PUT    /api/productos/{id}              - Actualizar
DELETE /api/productos/{id}              - Eliminar
```

**Nota:** Consulta [API_ENDPOINTS.md](API_ENDPOINTS.md) para documentación completa

---

## 🚀 Inicio Rápido

### Backend

```bash
# Terminal 1: Navegar a backend
cd backend

# Instalar dependencias
mvn clean install

# Ejecutar
mvn spring-boot:run

# Backend listo en http://localhost:8081
```

### Frontend

```bash
# Terminal 2: Navegar a frontend
cd Frontend

# Instalar dependencias
npm install

# Ejecutar
npm run dev

# Frontend listo en http://localhost:5173
```

### MySQL

```bash
# Terminal 3: Verificar MySQL
mysql -u root -p
CREATE DATABASE sailor_db;
EXIT;
```

---

## 🆘 Troubleshooting Rápido

| Problema | Solución |
|----------|----------|
| Puerto 8081 en uso | Cambiar `server.port` en application.properties |
| Puerto 5173 en uso | Cambiar port en vite.config.ts |
| MySQL no conecta | Verificar: `mysql -u root -p` |
| Token inválido | Token expiró después de 24h, hacer login de nuevo |
| CORS error | Verificar CORS config en CorsConfig.java |

---

## 📞 Contacto y Soporte

- **Email:** support@sailor.com
- **Slack:** #sailor-dev
- **Jira:** SAILOR project

---

## 📋 Checklist de Setup

- [ ] Java 17+ instalado
- [ ] Maven 3.8+ instalado
- [ ] Node.js 18+ instalado
- [ ] MySQL 8+ instalado y corriendo
- [ ] Base de datos `sailor_db` creada
- [ ] Backend compilado sin errores
- [ ] Frontend dependencias instaladas
- [ ] Ambos servidores ejecutándose
- [ ] Acceso a http://localhost:5173
- [ ] Login funciona

---

## 📚 Referencias Externas

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)
- [MySQL Manual](https://dev.mysql.com/doc/)
- [JWT.io](https://jwt.io)
- [TailwindCSS](https://tailwindcss.com)
- [shadcn/ui](https://ui.shadcn.com)

---

## 📄 Historial de Cambios

### v1.0.0 (Mayo 2026)
- ✅ Autenticación JWT implementada
- ✅ Validación de datos con DTOs
- ✅ Paginación en endpoints
- ✅ CORS configurado
- ✅ Documentación completa

---

## 📄 Licencia

Propiedad de SAILOR S.A.S © 2026

---

**Última actualización:** 1 de Mayo de 2026
**Versión:** 1.0.0
