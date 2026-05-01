# 🚀 SAILOR S.A.S - Backend API

Backend REST API para el sistema de gestión de inventario y ventas SAILOR S.A.S.

---

## 📋 Descripción

API Java/Spring Boot que proporciona endpoints para:
- ✅ Autenticación y gestión de usuarios
- ✅ CRUD de productos e inventario
- ✅ Gestión de ventas y detalles de venta
- ✅ Gestión de proveedores
- ✅ Paginación de datos

**Tecnologías:**
- Java 17
- Spring Boot 3.5.13
- MySQL 8
- JWT (JJWT 0.12.3)
- Maven

---

## 🔧 Requisitos Previos

- **Java 17+** instalado
- **Maven 3.8+** instalado
- **MySQL 8** ejecutándose en `localhost:3306`
- **Git** (opcional)

### Verificar instalación:

```bash
java -version          # Debe mostrar Java 17+
mvn -version           # Debe mostrar Maven 3.8+
mysql --version        # Debe mostrar MySQL 8+
```

---

## 📥 Instalación

### 1. Clonar o descargar el proyecto

```bash
cd "c:\Users\nicol\Downloads\Proyecto-SAILOR S.A.S"
```

### 2. Crear base de datos

```bash
mysql -u root -p
```

```sql
CREATE DATABASE sailor_db;
USE sailor_db;
```

### 3. Instalar dependencias

```bash
cd backend
mvn clean install
```

### 4. Configurar application.properties

Edita `backend/src/main/resources/application.properties`:

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/sailor_db
spring.datasource.username=root
spring.datasource.password=1617
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# Servidor
server.port=8081

# JWT
jwt.secret=my_super_secret_key_for_sailor_2024_must_be_long_enough_for_hs512_algorithm
jwt.expiration=86400000

# Logging
logging.level.root=INFO
logging.level.com.sailor=DEBUG
```

> ⚠️ **IMPORTANTE:** En producción, usa variables de entorno para credenciales.

---

## ▶️ Ejecución

### Iniciar servidor

```bash
cd backend
mvn spring-boot:run
```

O compilar e ejecutar JAR:

```bash
mvn clean package
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

**Output esperado:**
```
Started BackendApplication in XX seconds
Server running on http://localhost:8081
```

---

## 📚 API Endpoints

### 🔐 Autenticación

#### Login
```bash
POST /api/usuarios/login
Content-Type: application/json

{
  "correo": "admin@sailor.com",
  "password": "password123"
}

Response: 200 OK
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGc...",
  "usuario": {
    "id": 1,
    "nombre": "Admin",
    "apellido": "Sailor",
    "correo": "admin@sailor.com",
    "rol": "ADMIN"
  }
}
```

#### Registrar Usuario
```bash
POST /api/usuarios/registrar
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Pérez",
  "identificacion": "12345678",
  "correo": "juan@sailor.com",
  "password": "password123",
  "rol": "EMPLEADO",
  "direccion": "Cra 1 #2-3"
}

Response: 201 Created
```

### 👥 Usuarios

```bash
# Obtener todos (paginado)
GET /api/usuarios/paginado?page=0&size=10
Authorization: Bearer {token}

# Obtener por ID
GET /api/usuarios/{id}
Authorization: Bearer {token}

# Actualizar
PUT /api/usuarios/{id}
Authorization: Bearer {token}
Content-Type: application/json

# Eliminar
DELETE /api/usuarios/{id}
Authorization: Bearer {token}
```

### 📦 Productos

```bash
# Obtener todos
GET /api/productos
Authorization: Bearer {token}

# Obtener paginado
GET /api/productos/paginado?page=0&size=10
Authorization: Bearer {token}

# Crear producto
POST /api/productos
Authorization: Bearer {token}
Content-Type: application/json

{
  "nombreProducto": "Tenis Nike",
  "descripcion": "Tenis deportivos",
  "precioProducto": 150.00,
  "stock": 50
}

# Actualizar
PUT /api/productos/{id}
Authorization: Bearer {token}

# Eliminar
DELETE /api/productos/{id}
Authorization: Bearer {token}
```

### 💰 Ventas

```bash
# Obtener todas
GET /api/ventas

# Crear venta
POST /api/ventas

# Obtener por ID
GET /api/ventas/{id}
```

---

## 🔑 Autenticación JWT

**Cómo funciona:**

1. Usuario hace login con credenciales
2. Backend valida y genera JWT con 24h de expiración
3. Frontend almacena token en `localStorage.authToken`
4. Todas las requests incluyen: `Authorization: Bearer {token}`
5. Backend valida token antes de procesar request

**Token contiene:**
- `usuarioId` - ID del usuario
- `correo` - Email del usuario
- `rol` - ADMIN, EMPLEADO, CLIENTE
- `exp` - Timestamp de expiración (24h)

**Ejemplo JavaScript:**
```javascript
const token = localStorage.getItem('authToken');
const headers = {
  'Content-Type': 'application/json',
  'Authorization': `Bearer ${token}`
};

fetch('http://localhost:8081/api/productos', { headers })
```

---

## ✅ Validación de Datos

Todos los DTOs incluyen validaciones:

```java
@NotBlank        // No puede estar vacío
@Email           // Debe ser email válido
@Size(min=8)     // Mínimo 8 caracteres
@Positive        // Debe ser número positivo
@NotNull         // No puede ser null
```

**Respuesta de error:**
```json
{
  "timestamp": "2026-05-01T12:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": {
    "correo": "must be a valid email",
    "password": "size must be between 8 and 2147483647"
  }
}
```

---

## 📊 Paginación

**Parámetros:**
- `page` - Número de página (0-based)
- `size` - Elementos por página (default: 10)

**Ejemplo:**
```bash
GET /api/productos/paginado?page=0&size=20
```

**Respuesta:**
```json
{
  "content": [...],
  "totalPages": 5,
  "totalElements": 100,
  "number": 0,
  "size": 20,
  "first": true,
  "last": false
}
```

---

## 🗄️ Estructura de Base de Datos

### Tablas principales:

```
usuarios
├── id (PK)
├── nombre
├── apellido
├── identificacion
├── correo (UNIQUE)
├── password_hash
├── rol (ENUM: ADMIN, EMPLEADO, CLIENTE)
└── direccion

productos
├── id (PK)
├── nombre_producto
├── descripcion
├── precio_producto
└── stock

ventas
├── id (PK)
├── fecha_venta
├── usuario_id (FK)
├── total_venta
└── estado

detalles_venta
├── id (PK)
├── venta_id (FK)
├── producto_id (FK)
├── cantidad
└── precio_unitario

proveedores
├── id (PK)
├── nombre
├── contacto
└── telefono
```

---

## 🧪 Testing

### Ejecutar tests

```bash
mvn test
```

### Compilar sin tests

```bash
mvn clean install -DskipTests
```

---

## 📝 Logging

**Niveles de log:**
- `ERROR` - Errores críticos
- `WARN` - Advertencias
- `INFO` - Información general
- `DEBUG` - Información detallada

**Ver logs:**
```bash
# En tiempo real
tail -f target/backend.log

# Último 100 líneas
tail -100 target/backend.log
```

---

## 🚨 Troubleshooting

### Error: "Connection refused" (Base de datos)

```bash
# Verificar que MySQL está ejecutándose
mysql -u root -p -e "SELECT VERSION();"

# Si no está corriendo:
# Windows: Ejecutar MySQL Service desde Services.msc
# Linux: sudo systemctl start mysql
```

### Error: "Dependency not found"

```bash
# Limpiar cache Maven
mvn clean install -U
```

### Puerto 8081 en uso

```bash
# Cambiar puerto en application.properties
server.port=8082

# O: Encontrar y matar proceso
lsof -i :8081  # macOS/Linux
netstat -ano | findstr :8081  # Windows
```

---

## 📦 Dependencias Principales

| Dependencia | Versión | Propósito |
|------------|---------|----------|
| Spring Boot | 3.5.13 | Framework web |
| Spring Data JPA | - | ORM/Persistencia |
| MySQL Connector | - | Driver BD |
| JJWT | 0.12.3 | JWT tokens |
| Spring Security Crypto | - | BCrypt hashing |
| Lombok | - | Reduce boilerplate |
| Validation | - | @Valid annotations |

---

## 🔐 Seguridad

### ✅ Implementado:
- Contraseñas hasheadas con BCrypt
- JWT tokens con expiración 24h
- CORS configurado para frontend
- Validación de entrada en todos DTOs
- Endpoints protegidos requieren JWT
- Excepciones centralizadas

### ⚠️ Para Producción:
- [ ] Usar variables de entorno (JWT_SECRET, DB_PASSWORD)
- [ ] Implementar refresh tokens
- [ ] Agregar rate limiting en login
- [ ] Habilitar HTTPS
- [ ] Agregar logging de auditoría
- [ ] Implementar 2FA

---

## 📖 Documentación Adicional

- [API Endpoints Detallados](../../Docs/API_ENDPOINTS.md)
- [Guía de Desarrollo](../../Docs/DESARROLLO.md)
- [Estructura del Proyecto](../../Docs/ESTRUCTURA.md)

---

## 👥 Contribuidores

- Equipo SAILOR S.A.S
- Fecha: Mayo 2026

---

## 📄 Licencia

Propiedad de SAILOR S.A.S - 2026

---

## 📞 Soporte

Para reportar bugs o sugerencias:
1. Revisar [issue tracker](../../Docs/ISSUES.md)
2. Contactar al equipo de desarrollo
3. Consultar documentación en `/Docs`

