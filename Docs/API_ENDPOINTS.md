# 📡 API Endpoints - SAILOR S.A.S

Documentación completa de todos los endpoints REST del Backend.

**Base URL:** `http://localhost:8081/api`

---

## 🔐 Autenticación

### 1. Login

**Endpoint:** `POST /usuarios/login`

**Descripción:** Autentica usuario y retorna JWT token

**Request:**
```json
{
  "correo": "admin@sailor.com",
  "password": "password123"
}
```

**Response (200 OK):**
```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9...",
  "usuario": {
    "id": 1,
    "nombre": "Admin",
    "apellido": "Sailor",
    "identificacion": "123456789",
    "correo": "admin@sailor.com",
    "rol": "ADMIN",
    "direccion": "Cra 1 #2-3"
  }
}
```

**Error (400 Bad Request):**
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

**Curl:**
```bash
curl -X POST http://localhost:8081/api/usuarios/login \
  -H "Content-Type: application/json" \
  -d '{
    "correo": "admin@sailor.com",
    "password": "password123"
  }'
```

---

### 2. Registrar Usuario

**Endpoint:** `POST /usuarios/registrar`

**Descripción:** Crea nuevo usuario

**Request:**
```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "identificacion": "987654321",
  "correo": "juan@sailor.com",
  "password": "password123",
  "rol": "EMPLEADO",
  "direccion": "Cra 2 #3-4"
}
```

**Validaciones:**
- `nombre`: @NotBlank
- `apellido`: @NotBlank
- `identificacion`: @NotBlank, unique
- `correo`: @Email, @NotBlank, unique
- `password`: @NotBlank, @Size(min=8)
- `rol`: @NotBlank (ADMIN, EMPLEADO, CLIENTE)
- `direccion`: @NotBlank

**Response (201 Created):**
```json
{
  "id": 2,
  "nombre": "Juan",
  "apellido": "Pérez",
  "identificacion": "987654321",
  "correo": "juan@sailor.com",
  "rol": "EMPLEADO",
  "direccion": "Cra 2 #3-4"
}
```

---

## 👥 Usuarios

### 3. Obtener Todos (Paginado)

**Endpoint:** `GET /usuarios/paginado?page=0&size=10`

**Headers:**
```
Authorization: Bearer {token}
```

**Query Parameters:**
- `page` - Número de página (0-based, default: 0)
- `size` - Elementos por página (default: 10, max: 100)

**Response (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "nombre": "Admin",
      "apellido": "Sailor",
      "identificacion": "123456789",
      "correo": "admin@sailor.com",
      "rol": "ADMIN",
      "direccion": "Cra 1 #2-3"
    }
  ],
  "totalPages": 1,
  "totalElements": 1,
  "number": 0,
  "size": 10,
  "first": true,
  "last": true
}
```

**Curl:**
```bash
curl -X GET "http://localhost:8081/api/usuarios/paginado?page=0&size=10" \
  -H "Authorization: Bearer {token}"
```

---

### 4. Obtener Usuario por ID

**Endpoint:** `GET /usuarios/{id}`

**Headers:**
```
Authorization: Bearer {token}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nombre": "Admin",
  "apellido": "Sailor",
  "identificacion": "123456789",
  "correo": "admin@sailor.com",
  "rol": "ADMIN",
  "direccion": "Cra 1 #2-3"
}
```

**Error (404 Not Found):**
```json
{
  "timestamp": "2026-05-01T12:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Usuario no encontrado"
}
```

---

### 5. Actualizar Usuario

**Endpoint:** `PUT /usuarios/{id}`

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request:**
```json
{
  "nombre": "Juan",
  "apellido": "Pérez",
  "identificacion": "987654321",
  "correo": "juan.nuevo@sailor.com",
  "password": "newpassword123",
  "rol": "EMPLEADO",
  "direccion": "Cra 2 #3-4"
}
```

**Response (200 OK):** Usuario actualizado

---

### 6. Eliminar Usuario

**Endpoint:** `DELETE /usuarios/{id}`

**Headers:**
```
Authorization: Bearer {token}
```

**Response (204 No Content):** -

---

## 📦 Productos

### 7. Obtener Todos los Productos

**Endpoint:** `GET /productos`

**Headers:**
```
Authorization: Bearer {token}
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "nombreProducto": "Tenis Nike",
    "descripcion": "Tenis deportivos de alta calidad",
    "precioProducto": 150.00,
    "stock": 50
  }
]
```

---

### 8. Obtener Productos (Paginado)

**Endpoint:** `GET /productos/paginado?page=0&size=10`

**Headers:**
```
Authorization: Bearer {token}
```

**Query Parameters:**
- `page` - Número de página (0-based)
- `size` - Elementos por página

**Response (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "nombreProducto": "Tenis Nike",
      "descripcion": "Tenis deportivos",
      "precioProducto": 150.00,
      "stock": 50
    }
  ],
  "totalPages": 5,
  "totalElements": 50,
  "number": 0,
  "size": 10,
  "first": true,
  "last": false
}
```

**Curl:**
```bash
curl -X GET "http://localhost:8081/api/productos/paginado?page=0&size=20" \
  -H "Authorization: Bearer {token}"
```

---

### 9. Obtener Producto por ID

**Endpoint:** `GET /productos/{id}`

**Headers:**
```
Authorization: Bearer {token}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "nombreProducto": "Tenis Nike",
  "descripcion": "Tenis deportivos de alta calidad",
  "precioProducto": 150.00,
  "stock": 50
}
```

---

### 10. Crear Producto

**Endpoint:** `POST /productos`

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request:**
```json
{
  "nombreProducto": "Tenis Adidas",
  "descripcion": "Tenis deportivos Adidas",
  "precioProducto": 120.00,
  "stock": 30
}
```

**Validaciones:**
- `nombreProducto`: @NotBlank
- `descripcion`: @NotBlank
- `precioProducto`: @Positive, @NotNull
- `stock`: @PositiveOrZero, @NotNull

**Response (201 Created):**
```json
{
  "id": 2,
  "nombreProducto": "Tenis Adidas",
  "descripcion": "Tenis deportivos Adidas",
  "precioProducto": 120.00,
  "stock": 30
}
```

**Curl:**
```bash
curl -X POST http://localhost:8081/api/productos \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "nombreProducto": "Tenis Adidas",
    "descripcion": "Tenis deportivos Adidas",
    "precioProducto": 120.00,
    "stock": 30
  }'
```

---

### 11. Actualizar Producto

**Endpoint:** `PUT /productos/{id}`

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request:** (mismo formato que crear)
```json
{
  "nombreProducto": "Tenis Adidas Actualizado",
  "descripcion": "Tenis deportivos Adidas 2.0",
  "precioProducto": 130.00,
  "stock": 25
}
```

**Response (200 OK):** Producto actualizado

---

### 12. Eliminar Producto

**Endpoint:** `DELETE /productos/{id}`

**Headers:**
```
Authorization: Bearer {token}
```

**Response (204 No Content):** -

---

## 💰 Ventas

### 13. Obtener Todas las Ventas

**Endpoint:** `GET /ventas`

**Headers:**
```
Authorization: Bearer {token}
```

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "usuarioId": 1,
    "fechaVenta": "2026-05-01T12:30:00",
    "totalVenta": 450.00,
    "estado": "COMPLETADA"
  }
]
```

---

### 14. Obtener Venta por ID

**Endpoint:** `GET /ventas/{id}`

**Headers:**
```
Authorization: Bearer {token}
```

---

### 15. Crear Venta

**Endpoint:** `POST /ventas`

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request:**
```json
{
  "usuarioId": 1,
  "estado": "COMPLETADA",
  "detalles": [
    {
      "productoId": 1,
      "cantidad": 2,
      "precioUnitario": 150.00
    }
  ]
}
```

---

## 🏪 Proveedores

### 16. Obtener Proveedores

**Endpoint:** `GET /proveedores`

**Headers:**
```
Authorization: Bearer {token}
```

---

### 17. Crear Proveedor

**Endpoint:** `POST /proveedores`

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request:**
```json
{
  "nombre": "Nike Colombia",
  "contacto": "contacto@nike.co",
  "telefono": "+57 1 1234567"
}
```

---

## 📊 Detalles de Venta

### 18. Obtener Detalles de Venta

**Endpoint:** `GET /detalles-venta/{ventaId}`

**Headers:**
```
Authorization: Bearer {token}
```

**Response:**
```json
[
  {
    "id": 1,
    "ventaId": 1,
    "productoId": 1,
    "cantidad": 2,
    "precioUnitario": 150.00,
    "subtotal": 300.00
  }
]
```

---

## 🔍 Inventario

### 19. Obtener Inventario

**Endpoint:** `GET /inventario`

**Headers:**
```
Authorization: Bearer {token}
```

---

### 20. Actualizar Stock

**Endpoint:** `PUT /inventario/{productoId}`

**Headers:**
```
Authorization: Bearer {token}
Content-Type: application/json
```

**Request:**
```json
{
  "stock": 100
}
```

---

## 🆘 Códigos HTTP

| Código | Significado | Ejemplo |
|--------|------------|---------|
| 200 | OK | GET exitoso |
| 201 | Created | POST exitoso |
| 204 | No Content | DELETE exitoso |
| 400 | Bad Request | Validación fallida |
| 401 | Unauthorized | Token inválido |
| 403 | Forbidden | Sin permisos |
| 404 | Not Found | Recurso no existe |
| 500 | Server Error | Error interno servidor |

---

## 🛡️ Seguridad

### Headers Requeridos

Todos los endpoints protegidos requieren:

```
Authorization: Bearer {jwt_token}
```

### Token JWT

```
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.
SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

**Token contiene:**
- `usuarioId` - ID del usuario
- `correo` - Email
- `rol` - ADMIN, EMPLEADO, CLIENTE
- `exp` - Tiempo de expiración (24h)

### CORS

Frontend autorizado: `http://localhost:5173`

---

## 📝 Validaciones

### DTOs

#### LoginRequestDTO
```java
@Email
@NotBlank
private String correo;

@NotBlank
@Size(min=8)
private String password;
```

#### UsuarioRegistroDTO
```java
@NotBlank
private String nombre;

@NotBlank
private String apellido;

@NotBlank
private String identificacion;

@Email
@NotBlank
private String correo;

@NotBlank
@Size(min=8)
private String password;

@NotBlank
private String rol;

@NotBlank
private String direccion;
```

#### ProductoDTO
```java
@NotBlank
private String nombreProducto;

@NotBlank
private String descripcion;

@Positive
@NotNull
private BigDecimal precioProducto;

@PositiveOrZero
@NotNull
private Integer stock;
```

---

## 📚 Ejemplos Prácticos

### Ejemplo 1: Login y guardar token

```javascript
const response = await fetch('http://localhost:8081/api/usuarios/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    correo: 'admin@sailor.com',
    password: 'password123'
  })
});

const { token, usuario } = await response.json();
localStorage.setItem('authToken', token);
console.log('Bienvenido', usuario.nombre);
```

### Ejemplo 2: Obtener productos paginados

```javascript
const token = localStorage.getItem('authToken');
const response = await fetch(
  'http://localhost:8081/api/productos/paginado?page=0&size=10',
  {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  }
);

const { content, totalPages } = await response.json();
console.log(`Página 1 de ${totalPages}:`, content);
```

### Ejemplo 3: Crear producto

```javascript
const token = localStorage.getItem('authToken');
const response = await fetch('http://localhost:8081/api/productos', {
  method: 'POST',
  headers: {
    'Authorization': `Bearer ${token}`,
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    nombreProducto: 'Tenis Nike',
    descripcion: 'Tenis deportivos',
    precioProducto: 150.00,
    stock: 50
  })
});

const producto = await response.json();
console.log('Producto creado:', producto.id);
```

---

## 🔗 Recursos Relacionados

- [Backend README](../backend/README.md)
- [Frontend README](../Frontend/README.md)
- [Arquitectura](ARQUITECTURA.md)
- [Instalación](INSTALACION.md)

