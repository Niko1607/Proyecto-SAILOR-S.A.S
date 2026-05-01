# 👨‍💻 Guía de Desarrollo - SAILOR S.A.S

Guía completa para desarrolladores que trabajan en el proyecto SAILOR S.A.S.

---

## 🎯 Objetivos

Esta guía ayuda a desarrolladores a:
- ✅ Entender la estructura del proyecto
- ✅ Configurar entorno de desarrollo
- ✅ Seguir estándares de código
- ✅ Ejecutar y debugguear aplicación
- ✅ Crear nuevas features
- ✅ Realizar tests

---

## 📋 Requisitos

### Software
- Java 17+
- Maven 3.8+
- Node.js 18+
- npm 9+
- MySQL 8+
- Git
- IDE: IntelliJ IDEA, VS Code

### Conocimientos previos
- Java y Spring Boot
- React y TypeScript
- REST APIs
- SQL / Databases
- Git

---

## 🚀 Setup de Entorno

### 1. Clonar repositorio

```bash
git clone <url-repositorio>
cd "Proyecto-SAILOR S.A.S"
```

### 2. Backend setup

```bash
cd backend

# Instalar dependencias
mvn clean install

# Verificar
mvn compile
```

### 3. Frontend setup

```bash
cd ../Frontend

# Instalar dependencias
npm install

# Verificar
npm run lint
```

### 4. Database setup

```bash
mysql -u root -p1617

CREATE DATABASE sailor_db;
EXIT;
```

---

## 🏃 Ejecutar Aplicación

### Terminal 1: Backend

```bash
cd backend
mvn spring-boot:run
```

### Terminal 2: Frontend

```bash
cd Frontend
npm run dev
```

### Terminal 3: MySQL (si no auto-start)

```bash
# Windows
net start MySQL80

# macOS
brew services start mysql-server

# Linux
sudo systemctl start mysql
```

**URLs:**
- Frontend: http://localhost:5173
- Backend: http://localhost:8081
- MySQL: localhost:3306

---

## 📁 Estructura de Carpetas

### Backend

```
backend/src/main/java/com/sailor/backend/
├── controller/           # REST Controllers
│   ├── UsuarioController.java
│   ├── ProductoController.java
│   └── VentaController.java
│
├── service/              # Business Logic
│   ├── UsuarioService.java
│   ├── ProductoService.java
│   └── VentaService.java
│
├── repository/           # Data Access
│   ├── UsuarioRepository.java
│   ├── ProductoRepository.java
│   └── VentaRepository.java
│
├── entity/               # JPA Entities
│   ├── Usuario.java
│   ├── Producto.java
│   └── Venta.java
│
├── dto/                  # Data Transfer Objects
│   ├── LoginRequestDTO.java
│   ├── UsuarioResponseDTO.java
│   └── ProductoDTO.java
│
├── security/             # JWT & Security
│   └── JwtTokenProvider.java
│
├── exception/            # Exception Handling
│   └── GlobalExceptionHandler.java
│
└── config/               # Configuration
    └── CorsConfig.java
```

### Frontend

```
Frontend/src/
├── components/
│   ├── ui/               # shadcn/ui components
│   ├── admin/            # Admin page components
│   ├── empleado/         # Employee page components
│   ├── store/            # Store page components
│   └── NavLink.tsx
│
├── pages/                # Page components
│   ├── admin/
│   ├── empleado/
│   └── [other pages]
│
├── services/             # API calls
│   ├── userService.ts
│   ├── productService.ts
│   └── [other services]
│
├── hooks/                # Custom hooks
│   ├── usePagination.ts
│   └── use-toast.ts
│
├── context/              # React Context
│   └── CartContext.tsx
│
├── lib/                  # Utilities
│   └── utils.ts
│
├── data/                 # Static data
│   └── products.ts
│
└── test/                 # Tests
    ├── example.test.ts
    └── setup.ts
```

---

## 🔨 Tareas Comunes

### Agregar nuevo endpoint

#### Backend

1. Crear DTO (si es necesario)

```java
// src/main/java/com/sailor/backend/dto/MiDTO.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiDTO {
    @NotBlank
    private String nombre;
    
    @Positive
    private BigDecimal precio;
}
```

2. Agregar método en Repository

```java
// src/main/java/com/sailor/backend/repository/MiRepository.java
public interface MiRepository extends JpaRepository<Mi, Long> {
    // Query custom si es necesario
    List<Mi> findByNombre(String nombre);
}
```

3. Agregar lógica en Service

```java
// src/main/java/com/sailor/backend/service/MiService.java
@Service
public class MiService {
    private final MiRepository miRepository;
    
    @Autowired
    public MiService(MiRepository repo) {
        this.miRepository = repo;
    }
    
    public Mi crear(MiDTO dto) {
        Mi mi = new Mi();
        mi.setNombre(dto.getNombre());
        return miRepository.save(mi);
    }
}
```

4. Agregar endpoint en Controller

```java
// src/main/java/com/sailor/backend/controller/MiController.java
@RestController
@RequestMapping("/api/mi")
@CrossOrigin(origins = "http://localhost:5173")
public class MiController {
    private final MiService miService;
    
    @Autowired
    public MiController(MiService service) {
        this.miService = service;
    }
    
    @PostMapping
    public ResponseEntity<Mi> crear(@Valid @RequestBody MiDTO dto) {
        Mi mi = miService.crear(dto);
        return ResponseEntity.status(201).body(mi);
    }
}
```

#### Frontend

1. Crear service

```typescript
// src/services/miService.ts
const API_URL = 'http://localhost:8081/api/mi';

export const crearMi = async (data: MiDTO) => {
  const headers = getAuthHeaders();
  const response = await fetch(`${API_URL}`, {
    method: 'POST',
    headers,
    body: JSON.stringify(data)
  });
  return response.json();
};
```

2. Crear componente

```typescript
// src/components/MiComponente.tsx
import { crearMi } from '@/services/miService';

export function MiComponente() {
  const [nombre, setNombre] = useState('');
  
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const result = await crearMi({ nombre });
      console.log('Creado:', result);
    } catch (error) {
      console.error('Error:', error);
    }
  };
  
  return (
    <form onSubmit={handleSubmit}>
      <input 
        value={nombre} 
        onChange={(e) => setNombre(e.target.value)} 
      />
      <button type="submit">Crear</button>
    </form>
  );
}
```

3. Usar en página

```typescript
// src/pages/MiPagina.tsx
import { MiComponente } from '@/components/MiComponente';

export function MiPagina() {
  return (
    <div>
      <h1>Mi Página</h1>
      <MiComponente />
    </div>
  );
}
```

---

## 🧪 Testing

### Backend Tests

```bash
cd backend

# Ejecutar todos los tests
mvn test

# Ejecutar test específico
mvn test -Dtest=UsuarioControllerTest

# Con coverage
mvn test jacoco:report
```

### Frontend Tests

```bash
cd Frontend

# Ejecutar tests
npm run test

# Modo watch
npm run test:watch

# Con coverage
npm run test -- --coverage
```

### Example: Unit Test (Backend)

```java
@SpringBootTest
class UsuarioServiceTest {
    @MockBean
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Test
    void testCrearUsuario() {
        // Arrange
        UsuarioRegistroDTO dto = new UsuarioRegistroDTO();
        dto.setNombre("Juan");
        
        // Act
        Usuario resultado = usuarioService.registrar(dto);
        
        // Assert
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
    }
}
```

---

## 🐛 Debugging

### Backend

#### IntelliJ IDEA

1. Set breakpoint (click en línea)
2. Click derecho en archivo → Debug
3. Use step into (F7), step over (F8), continue (F9)
4. Ver variables en panel "Variables"

#### VS Code

```java
// Agregar logs
logger.debug("Debug info: {}", variable);
logger.error("Error: {}", error);
```

```bash
# Ver logs en terminal
mvn spring-boot:run | grep -E "DEBUG|ERROR"
```

### Frontend

#### Chrome DevTools

1. Abrir DevTools (F12)
2. Console tab para logs
3. Sources tab para breakpoints
4. Network tab para API calls
5. Application tab para localStorage

#### VS Code Debugger

`launch.json`:
```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "chrome",
      "request": "launch",
      "name": "Launch Chrome",
      "url": "http://localhost:5173",
      "webRoot": "${workspaceFolder}/Frontend/src"
    }
  ]
}
```

---

## 📝 Estándares de Código

### Java

**Naming:**
```java
// ✅ Good
class UsuarioService { }
public List<Usuario> obtenerUsuarios() { }
private String correoUsuario;

// ❌ Bad
class user_Service { }
public List<Usuario> getUsers() { }
private String email;
```

**Formatting:**
```bash
# Maven formatter
mvn clean format:format
```

### TypeScript

**Naming:**
```typescript
// ✅ Good
function obtenerProductos() { }
const usuarioActual = user;
interface UsuarioDTO { }

// ❌ Bad
function Get_Products() { }
const UserActual = user;
type usuarioDTO = { }
```

**Formatting:**
```bash
# ESLint + Prettier
npm run lint
npm run lint -- --fix
```

---

## 🔄 Git Workflow

### Branches

```bash
# Crear feature branch
git checkout -b feature/agregar-paginacion

# Commits
git commit -m "feat: agregar paginacion en productos"

# Push
git push origin feature/agregar-paginacion

# Crear PR en GitHub
```

### Commit Messages

```
feat:       Nueva feature
fix:        Arregliar bug
docs:       Cambios en documentación
style:      Cambios de estilo (formato, etc)
refactor:   Refactorización sin cambio de funcionalidad
test:       Agregar o actualizar tests
chore:      Cambios en build, dependencies, etc

Ejemplo:
feat: agregar validacion de email en registro
fix: corregir bug en calculo de total venta
```

---

## 📊 Performance

### Backend

```bash
# Medir tiempo de respuesta
curl -w "Time: %{time_total}s\n" http://localhost:8081/api/productos

# Usar Spring Boot Actuator
http://localhost:8081/actuator/metrics
```

### Frontend

```typescript
// Medir render time
console.time('render');
// ... código
console.timeEnd('render');

// React Profiler
import { Profiler } from 'react';
```

---

## 🔒 Security Checklist

- [ ] Nunca exponer secrets en código
- [ ] Usar variables de entorno para credenciales
- [ ] Validar toda entrada de usuario
- [ ] Hash contraseñas con BCrypt
- [ ] Usar HTTPS en producción
- [ ] Implementar rate limiting
- [ ] Validar JWT en todos endpoints
- [ ] No guardar datos sensibles en localStorage (solo token)
- [ ] Implementar CORS restringido
- [ ] Usar parameterized queries (evitar SQL injection)

---

## 📚 Recursos Útiles

### Documentación
- [Backend README](../backend/README.md)
- [Frontend README](../Frontend/README.md)
- [API Endpoints](API_ENDPOINTS.md)
- [Arquitectura](ARQUITECTURA.md)

### Herramientas
- **Postman** - Testing de API
- **DBeaver** - GUI para MySQL
- **Git Extensions** - GUI para Git
- **Chrome DevTools** - Browser debugging

### References
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [React Docs](https://react.dev)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)

---

## 🤝 Contribuir

1. Fork del repositorio
2. Crear feature branch: `git checkout -b feature/mi-feature`
3. Commit cambios: `git commit -m "feat: descripcion"`
4. Push: `git push origin feature/mi-feature`
5. Crear Pull Request

---

## 📞 Soporte

- Documentación: Ver `/Docs`
- Issues: Reportar en GitHub
- Preguntas: Contactar a team lead

