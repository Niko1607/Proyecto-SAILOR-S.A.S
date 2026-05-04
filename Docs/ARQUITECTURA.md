# 🏗️ Arquitectura - SAILOR S.A.S

Descripción de la arquitectura general del sistema SAILOR S.A.S, incluyendo componentes, tecnologías y flujos.

---

## 📊 Arquitectura General

```
┌─────────────────────────────────────────────────────────────┐
│                    WEB BROWSER (Cliente)                     │
│  - Chrome, Firefox, Safari, Edge                             │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          │ HTTP/HTTPS
                          │
┌─────────────────────────▼───────────────────────────────────┐
│              FRONTEND (React + TypeScript)                   │
│  - localhost:5173 (Vite Dev Server)                          │
│  - Componentes UI con shadcn/ui + TailwindCSS               │
│  - State management con React Context                        │
│  - Autenticación JWT en localStorage                         │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          │ REST API (JSON)
                          │ Authorization: Bearer {JWT}
                          │
┌─────────────────────────▼───────────────────────────────────┐
│              BACKEND (Spring Boot 3.5.13)                    │
│  - localhost:8081 (Tomcat Server)                            │
│  - Controllers → Services → Repositories                     │
│  - JWT validation + Authorization                            │
│  - Input validation (DTO + @Valid)                           │
│  - Exception handling centralized                            │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          │ JDBC
                          │
┌─────────────────────────▼───────────────────────────────────┐
│              DATABASE (MySQL 8.0)                            │
│  - localhost:3306                                            │
│  - Hibernate ORM (Auto DDL generation)                       │
│  - Relational schema with FK constraints                     │
└─────────────────────────────────────────────────────────────┘
```

---

## 🔄 Flujo de Autenticación

### 1. Login

```
┌─────────────────────────────────────────────────────────────┐
│ Cliente ingresa credenciales (correo + password)             │
└─────────────────┬───────────────────────────────────────────┘
                  │
                  ▼
        POST /api/usuarios/login
        {correo, password}
                  │
                  ▼
        UsuarioController.login()
                  │
                  ▼
        UsuarioService.validarCredenciales()
        - Buscar usuario por correo
        - Validar password con BCrypt
                  │
                  ▼ Si válido:
        JwtTokenProvider.generarToken()
        - Crear JWT con usuarioId, correo, rol
        - Expiración: 24 horas
                  │
                  ▼
        Response: {token, usuario}
                  │
                  ▼
        localStorage.setItem('authToken', token)
        localStorage.setItem('usuario', usuario)
```

### 2. Requests Autenticados

```
┌─────────────────────────────────────────────────────────────┐
│ GET /api/productos?page=0&size=10                            │
│ Header: Authorization: Bearer {token}                        │
└─────────────────────────┬───────────────────────────────────┘
                          │
                          ▼
                ProductoController.listarPaginado()
                          │
                          ▼
              JwtTokenProvider.validarToken(token)
              - Verificar firma JWT
              - Validar no expirado
              - Extraer claims
                          │
            ┌─────────────┴─────────────┐
            │                           │
         Válido                    Inválido
            │                           │
            ▼                           ▼
        Continuar                  403 Forbidden
        procesar                   "Token inválido"
        request
```

---

## 🏛️ Capas de Aplicación

### Backend (4 Capas)

```
┌──────────────────────────────────────────────────────┐
│ 1. CONTROLLER LAYER                                  │
│ - UsuarioController.java                             │
│ - ProductoController.java                            │
│ - VentaController.java                               │
│ - Recibe HTTP requests                               │
│ - Mapea a DTOs                                       │
│ - Retorna ResponseEntity                             │
└────────────────┬─────────────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────────────┐
│ 2. SERVICE LAYER                                     │
│ - UsuarioService.java                                │
│ - ProductoService.java                               │
│ - VentaService.java                                  │
│ - Lógica de negocio                                  │
│ - Validaciones                                       │
│ - Transformaciones                                   │
└────────────────┬─────────────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────────────┐
│ 3. REPOSITORY LAYER                                  │
│ - UsuarioRepository (extends JpaRepository)          │
│ - ProductoRepository                                 │
│ - Acceso a datos con Spring Data JPA                 │
│ - Queries automáticas y custom                       │
└────────────────┬─────────────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────────────┐
│ 4. ENTITY / DOMAIN LAYER                             │
│ - Usuario.java (JPA Entity)                          │
│ - Producto.java                                      │
│ - Venta.java                                         │
│ - Mapeo a tablas DB                                  │
└──────────────────────────────────────────────────────┘
```

### Frontend (3 Capas)

```
┌──────────────────────────────────────────────────────┐
│ 1. PAGE / VIEW LAYER                                 │
│ - Index.tsx, Catalogo.tsx, etc                       │
│ - Componentes principales                           │
│ - Manejo de routing                                  │
│ - Orquestación de features                           │
└────────────────┬─────────────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────────────┐
│ 2. COMPONENT LAYER                                   │
│ - NavLink.tsx, Button, Card, etc                     │
│ - Componentes reutilizables                          │
│ - shadcn/ui components                               │
│ - Presentación y interacción                         │
└────────────────┬─────────────────────────────────────┘
                 │
┌────────────────▼─────────────────────────────────────┐
│ 3. SERVICE / API LAYER                               │
│ - userService.ts                                     │
│ - productService.ts                                  │
│ - HTTP calls con fetch                               │
│ - JWT handling                                       │
│ - Response parsing                                   │
└──────────────────────────────────────────────────────┘
```

---

## 🗄️ Modelo de Datos

### Entidades y Relaciones

```
Usuario
├── id (PK)
├── nombre
├── apellido
├── identificacion (UNIQUE)
├── correo (UNIQUE, @Email)
├── password_hash (BCrypt)
├── rol (ENUM: ADMIN, EMPLEADO, CLIENTE)
├── direccion
└── timestamps (createdAt, updatedAt)
    │
    ├─→ Venta (1:N)
    │   └─→ DetalleVenta (1:N)
    │       └─→ Producto (N:1)
    │
    └─→ Carrito (1:1)
        └─→ CartItem (1:N)
            └─→ Producto (N:1)

Producto
├── id (PK)
├── nombre_producto
├── descripcion
├── precio_producto (@Positive)
├── stock
├── categoria (FK)
├── imagenes (1:N)
└── timestamps

Proveedor
├── id (PK)
├── nombre
├── contacto
├── telefono
└── productos (N:M via ProductoProveedor)

Venta
├── id (PK)
├── usuario_id (FK)
├── fecha_venta
├── total_venta
├── estado
└── detalles_venta (1:N)

DetalleVenta
├── id (PK)
├── venta_id (FK)
├── producto_id (FK)
├── cantidad
├── precio_unitario
└── subtotal (cantidad * precio_unitario)
```

---

## 🔐 Seguridad

### Capas de Seguridad

```
1. AUTENTICACIÓN (JWT)
   ├── Login: Credenciales → Token
   ├── Token storage: localStorage
   └── Validation: Firma JWT + Expiración

2. AUTORIZACIÓN (Roles)
   ├── ADMIN: Acceso total
   ├── EMPLEADO: Lectura/escritura limitada
   └── CLIENTE: Solo datos personales

3. VALIDACIÓN
   ├── DTOs con @Valid annotations
   ├── GlobalExceptionHandler
   └── Respuestas formateadas

4. ENCRIPTACIÓN
   ├── Passwords: BCrypt (10 salt rounds)
   ├── JWT Secret: HS512 algorithm
   └── CORS: Origins específicos

5. API LAYER
   ├── HTTPS required (producción)
   ├── Rate limiting
   └── Input sanitization
```

### Flujo de Autorización

```
Request con JWT
        │
        ▼
JwtTokenProvider.validarToken()
        │
    ┌───┴────────────┐
    │                │
  Válido        Inválido
    │                │
    ▼                ▼
Extraer           Error
claims            401
    │
    ▼
Verificar rol
    │
    ┌──────────┬──────────┬──────────┐
    │          │          │          │
  ADMIN    EMPLEADO    CLIENTE   Otro
    │          │          │          │
    ✅         ✅         ❌        ❌
  Acceso    Acceso    Denegado  Denegado
  total    limitado   403       403
```

---

## 🔄 Patrones de Diseño

### 1. MVC (Model-View-Controller)

- **Model**: Entidades JPA + DTOs
- **View**: React Components
- **Controller**: RestController + Service

### 2. Repository Pattern

```java
// Spring Data JPA genera implementación automática
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
    Page<Usuario> findAll(Pageable pageable);
}
```

### 3. DTO (Data Transfer Object)

```java
// Entidad DB - No expone password
public class Usuario { ... }

// DTO para respuesta - Solo datos públicos
public class UsuarioResponseDTO {
    private Long id;
    private String nombre;
    // SIN password!
}
```

### 4. Dependency Injection

```java
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioService(UsuarioRepository repo) {
        this.usuarioRepository = repo;
    }
}
```

### 5. Paginación

```java
// Backend
Page<Producto> page = productoRepository.findAll(
    PageRequest.of(0, 10, Sort.by("nombre").ascending())
);

// Frontend
const { content, totalPages, totalElements } = 
    await getProductosPaginados(0, 10);
```

---

## 📡 Comunicación Frontend-Backend

### REST Conventions

| Operación | Método | Endpoint | Body | Response |
|-----------|--------|----------|------|----------|
| Listar | GET | /api/productos | - | 200 + Array |
| Paginar | GET | /api/productos/paginado?page=0&size=10 | - | 200 + Page |
| Obtener | GET | /api/productos/{id} | - | 200 + Object |
| Crear | POST | /api/productos | DTO | 201 + Object |
| Actualizar | PUT | /api/productos/{id} | DTO | 200 + Object |
| Eliminar | DELETE | /api/productos/{id} | - | 204 |

### Request/Response

```
REQUEST:
POST /api/usuarios/login
Content-Type: application/json
{
  "correo": "admin@sailor.com",
  "password": "password123"
}

RESPONSE:
HTTP/1.1 200 OK
Content-Type: application/json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGc...",
  "usuario": {
    "id": 1,
    "nombre": "Admin",
    "correo": "admin@sailor.com",
    "rol": "ADMIN"
  }
}

ERROR RESPONSE:
HTTP/1.1 400 Bad Request
Content-Type: application/json
{
  "timestamp": "2026-05-01T12:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": {
    "correo": "must be a valid email"
  }
}
```

---

## 🚀 Deployment

### Arquitectura de Deploy

```
┌─────────────────────────────────────┐
│     CLIENTE (Navegador)              │
│  - Frontend compilado (dist/)        │
│  - Servido por: Nginx o CDN          │
└────────────┬────────────────────────┘
             │
             │ HTTP/HTTPS
             │
┌────────────▼────────────────────────┐
│   SERVIDOR WEB (Nginx/Apache)        │
│  - Reverse proxy                     │
│  - SSL/TLS termination               │
│  - Compression (gzip)                │
└────────────┬────────────────────────┘
             │
             │ HTTP
             │
┌────────────▼────────────────────────┐
│   SERVIDOR APLICACIÓN                │
│  - Backend JAR (Spring Boot)         │
│  - Puerto 8081 (privado)             │
│  - Multiple instances (scaling)      │
└────────────┬────────────────────────┘
             │
             │ JDBC
             │
┌────────────▼────────────────────────┐
│   BASE DE DATOS                      │
│  - MySQL 8 (replicado)               │
│  - Backups automáticos               │
│  - Read-only replicas                │
└─────────────────────────────────────┘
```

### Opciones de Hosting

1. **On-Premise**
   - Servidor dedicado
   - Control total
   - Mayor responsabilidad

2. **Cloud (AWS)**
   - EC2 para apps
   - RDS para DB
   - S3 para frontend
   - CloudFront para CDN

3. **Platform as a Service (PaaS)**
   - Heroku (simple)
   - Render (moderno)
   - Railway (flexible)

4. **Contenedores (Docker)**
   - Frontend: Dockerfile
   - Backend: Dockerfile
   - Docker Compose para dev
   - Kubernetes para prod

---

## 📊 Performance

### Optimizaciones Implementadas

1. **Paginación**
   - Evita cargar todos los datos
   - Mejora tiempo de respuesta

2. **JWT Tokens**
   - Stateless auth
   - No requiere session store
   - Escalable

3. **CORS Configurado**
   - Reduce requests innecesarios
   - Pre-flight requests optimizados

4. **Lazy Loading**
   - Frontend: React.lazy()
   - Reducir bundle size

### Monitoreo

```
Frontend:
- Lighthouse (Performance, Accessibility)
- DevTools Network tab
- Console errors/warnings

Backend:
- Spring Boot Actuator (/actuator/metrics)
- Log levels
- Response times
- Database connection pool stats

Database:
- Query slow log
- Connection pool usage
- Index performance
```

---

## 📚 Dependencias Principales

| Layer | Dependencia | Versión | Propósito |
|-------|-------------|---------|----------|
| Backend | Spring Boot | 3.5.13 | Framework |
| Backend | Spring Data JPA | - | ORM |
| Backend | JJWT | 0.12.3 | JWT |
| Backend | Spring Security Crypto | - | BCrypt |
| Backend | Validation | - | @Valid |
| Frontend | React | 18+ | UI |
| Frontend | TypeScript | 5+ | Tipos |
| Frontend | Vite | 5+ | Build |
| Frontend | React Router | 6+ | SPA routing |
| Frontend | TailwindCSS | 3+ | Styling |
| Frontend | shadcn/ui | Latest | Components |

---

## 🔗 Referencias

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [JWT.io](https://jwt.io)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)

