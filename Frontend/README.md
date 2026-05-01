# 🎨 SAILOR S.A.S - Frontend

Interfaz web moderna para el sistema de gestión de inventario y ventas SAILOR S.A.S.

---

## 📋 Descripción

Aplicación SPA (Single Page Application) desarrollada en **React + TypeScript + Vite** con:
- ✅ Sistema de autenticación JWT
- ✅ Gestión de productos e inventario
- ✅ Carrito de compras
- ✅ Panel administrativo
- ✅ Dashboard empleados
- ✅ UI moderna con shadcn/ui
- ✅ Responsive design

**Tecnologías:**
- React 18+
- TypeScript
- Vite
- TailwindCSS
- shadcn/ui
- React Router v6
- Framer Motion
- Vitest + Playwright

---

## 🔧 Requisitos Previos

- **Node.js 18+** instalado
- **npm 9+** o **Bun** instalado
- **Backend corriendo** en `localhost:8081`
- **Git** (opcional)

### Verificar instalación:

```bash
node --version        # Debe mostrar v18+
npm --version         # Debe mostrar 9+
bun --version         # (Opcional)
```

---

## 📥 Instalación

### 1. Navegar al directorio Frontend

```bash
cd "c:\Users\nicol\Downloads\Proyecto-SAILOR S.A.S\Frontend"
```

### 2. Instalar dependencias

**Con npm:**
```bash
npm install
```

**Con Bun:**
```bash
bun install
```

### 3. Verificar variables de entorno

Crea `.env.local` (opcional, si necesitas configuración personalizada):

```env
VITE_API_URL=http://localhost:8081/api
VITE_JWT_TOKEN_KEY=authToken
```

> Nota: Los valores por defecto ya apuntan a localhost:8081

---

## ▶️ Ejecución

### Desarrollo (con hot reload)

```bash
npm run dev
```

**Output esperado:**
```
VITE v5.0.0 ready in XX ms

➜  Local:   http://localhost:5173/
➜  Press h to show help
```

Abre navegador en `http://localhost:5173`

### Build para producción

```bash
npm run build
```

Genera carpeta `dist/` lista para deploy

### Preview de build

```bash
npm run preview
```

---

## 🌍 Rutas de la Aplicación

### 🛍️ Tienda (Público)

| Ruta | Componente | Descripción |
|------|-----------|-------------|
| `/` | Index | Página inicio |
| `/catalogo` | Catalogo | Listado de productos |
| `/colecciones` | Colecciones | Colecciones destacadas |
| `/producto/:id` | ProductoDetalle | Detalle de producto |
| `/ofertas` | Ofertas | Productos en oferta |
| `/nosotros` | Nosotros | Información empresa |
| `/contacto` | Contacto | Formulario contacto |

### 🔐 Autenticación

| Ruta | Componente | Descripción |
|------|-----------|-------------|
| `/login` | Login | Formulario login |
| `/registrar` | Registrar | Formulario registro |
| `/cuenta` | Cuenta | Mi cuenta (protegido) |

### 👨‍💼 Empleado (Protegido)

| Ruta | Componente | Descripción |
|------|-----------|-------------|
| `/empleado` | EmpleadoLayout | Panel empleado |
| `/empleado/ventas` | EmpleadoVentas | Gestionar ventas |
| `/empleado/pedidos` | EmpleadoPedidos | Ver pedidos |

### 👨‍💻 Administrador (Protegido)

| Ruta | Componente | Descripción |
|------|-----------|-------------|
| `/admin` | AdminLayout | Panel admin |
| `/admin/productos` | AdminInventario | Gestionar productos |
| `/admin/usuarios` | AdminClientes | Gestionar usuarios |
| `/admin/reportes` | AdminReportes | Reportes (futuro) |

### ⚠️ Otras

| Ruta | Componente | Descripción |
|------|-----------|-------------|
| `*` | NotFound | Página no encontrada |

---

## 🔑 Autenticación

### Login

```typescript
// userService.ts
const { token, usuario } = await loginUsuario('correo@example.com', 'password123');

// Token se almacena automáticamente en:
localStorage.getItem('authToken')

// Usuario se almacena en:
localStorage.getItem('usuario')
```

### Usar token en requests

```typescript
// Todos los servicios incluyen automáticamente JWT:
const headers = getAuthHeaders();
// {
//   'Content-Type': 'application/json',
//   'Authorization': 'Bearer eyJ0eXAi...'
// }

fetch('http://localhost:8081/api/productos', { headers })
```

### Verificar autenticación

```typescript
import { getAuthToken } from '@/lib/utils';

const token = getAuthToken();
if (!token) {
  // Usuario no autenticado
  navigate('/login');
}
```

---

## 🛒 Carrito de Compras

### Usar CartContext

```typescript
import { useCart } from '@/context/CartContext';

function MiComponente() {
  const { cart, agregarProducto, removerProducto, vaciarCarrito } = useCart();
  
  // cart es array de productos
  const total = cart.reduce((sum, item) => sum + (item.precio * item.cantidad), 0);
}
```

### Estructura del carrito

```typescript
interface CartItem {
  id: number;
  nombreProducto: string;
  precio: number;
  cantidad: number;
  imagen?: string;
}
```

---

## 🎨 Componentes UI

### Componentes disponibles (shadcn/ui)

```typescript
import { Button } from '@/components/ui/button';
import { Card } from '@/components/ui/card';
import { Dialog } from '@/components/ui/dialog';
import { Input } from '@/components/ui/input';
import { Select } from '@/components/ui/select';
// ... y muchos más en /components/ui
```

### Ejemplo de uso

```typescript
<Button 
  onClick={handleClick}
  variant="default"
  size="lg"
>
  Comprar Ahora
</Button>

<Card>
  <Card.Header>
    <Card.Title>Mi Producto</Card.Title>
  </Card.Header>
  <Card.Content>
    Contenido del producto
  </Card.Content>
</Card>
```

---

## 📊 Paginación

### Hook usePagination

```typescript
import { usePagination } from '@/hooks/usePagination';

function AdminProductos() {
  const { page, size, totalPages, setPage, setSize } = usePagination();
  
  // Obtener productos paginados
  useEffect(() => {
    getProductosPaginados(page, size).then(data => {
      // data.content, data.totalPages, data.totalElements
    });
  }, [page, size]);
}
```

### Componente PaginationControls

```typescript
import { PaginationControls } from '@/components/PaginationControls';

<PaginationControls
  currentPage={page}
  totalPages={totalPages}
  totalElements={totalElements}
  pageSize={size}
  onPageChange={setPage}
  onPageSizeChange={setSize}
/>
```

---

## 🧪 Testing

### Ejecutar tests unitarios

```bash
npm run test
```

### Modo watch (desarrollo)

```bash
npm run test:watch
```

### Tests E2E con Playwright

```bash
npx playwright test
```

### Archivo de configuración

`playwright.config.ts` - Configuración de tests

---

## 📁 Estructura del Proyecto

```
Frontend/
├── src/
│   ├── components/          # Componentes reutilizables
│   │   ├── ui/              # Componentes shadcn/ui
│   │   ├── admin/           # Componentes admin
│   │   ├── empleado/        # Componentes empleado
│   │   ├── store/           # Componentes tienda
│   │   └── NavLink.tsx
│   ├── context/             # React Context (CartContext)
│   ├── hooks/               # Custom hooks (usePagination, useToast)
│   ├── lib/                 # Utilidades (getAuthHeaders, etc)
│   ├── pages/               # Páginas principales
│   │   ├── admin/           # Páginas admin
│   │   ├── empleado/        # Páginas empleado
│   │   └── *.tsx
│   ├── services/            # API calls (userService, productService)
│   ├── data/                # Datos estáticos
│   ├── test/                # Tests
│   ├── App.tsx              # Componente root
│   ├── main.tsx             # Entry point
│   └── index.css            # Estilos globales
├── public/                  # Archivos estáticos
├── package.json
├── vite.config.ts           # Configuración Vite
├── tsconfig.json            # Configuración TypeScript
├── tailwind.config.ts       # Configuración TailwindCSS
├── vitest.config.ts         # Configuración tests
└── playwright.config.ts     # Configuración E2E tests
```

---

## 🎯 Scripts principales

```bash
npm run dev              # Iniciar servidor desarrollo
npm run build            # Build producción
npm run build:dev        # Build modo desarrollo
npm run preview          # Preview del build
npm run lint             # Ejecutar ESLint
npm run test             # Ejecutar tests
npm run test:watch       # Tests en modo watch
```

---

## 🌐 Llamadas API

### userService.ts

```typescript
import { loginUsuario, getUsuariosPaginados, getAuthToken } from '@/services/userService';

// Login
const { token, usuario } = await loginUsuario('correo@example.com', 'pass123');

// Obtener usuarios paginados
const page = await getUsuariosPaginados(0, 10);
```

### productService.ts

```typescript
import { 
  getProductos, 
  getProductosPaginados, 
  crearProducto,
  actualizarProducto,
  eliminarProducto 
} from '@/services/productService';

// Obtener todos
const todos = await getProductos();

// Paginado
const page = await getProductosPaginados(0, 10);

// CRUD
await crearProducto(productoData);
await actualizarProducto(id, productoData);
await eliminarProducto(id);
```

---

## ⚙️ Configuración Avanzada

### Vite Config (vite.config.ts)

```typescript
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/api': 'http://localhost:8081'
    }
  }
});
```

### TailwindCSS (tailwind.config.ts)

Personaliza colores, fuentes, breakpoints...

### ESLint (eslint.config.js)

Configuración de reglas de linting

---

## 🚨 Troubleshooting

### Error: "Cannot GET /api/..."

**Problema:** Backend no está corriendo
```bash
# Verificar que backend está en localhost:8081
npm run dev
# El proxy debe estar configurado en vite.config.ts
```

### Error: "Module not found"

```bash
# Limpiar e reinstalar
rm -rf node_modules package-lock.json
npm install
npm run dev
```

### Puerto 5173 en uso

```bash
# Cambiar puerto en vite.config.ts
export default defineConfig({
  server: {
    port: 5174  // Nuevo puerto
  }
})
```

### Token expirado

El usuario será redirigido a `/login` automáticamente cuando:
- El token expire (24 horas)
- El token sea inválido
- Acceda a ruta protegida sin autenticación

---

## 📦 Dependencias Principales

| Dependencia | Versión | Propósito |
|------------|---------|----------|
| React | 18+ | UI library |
| TypeScript | 5+ | Tipado estático |
| Vite | 5+ | Build tool |
| React Router | 6+ | Routing |
| TailwindCSS | 3+ | Styling |
| shadcn/ui | Latest | UI components |
| Framer Motion | Latest | Animaciones |
| Vitest | Latest | Testing |
| Playwright | Latest | E2E tests |

---

## 🔐 Seguridad

### ✅ Implementado:
- Tokens JWT en Authorization header
- Token almacenado en localStorage
- Rutas protegidas requieren autenticación
- CORS habilitado para backend

### ⚠️ Mejoras futuras:
- [ ] Implementar refresh tokens
- [ ] Usar sessionStorage en lugar de localStorage
- [ ] Agregar rate limiting en login
- [ ] Implementar 2FA
- [ ] Content Security Policy headers

---

## 📖 Documentación Adicional

- [API Endpoints Detallados](../../Docs/API_ENDPOINTS.md)
- [Guía de Componentes](../../Docs/COMPONENTES.md)
- [Guía de Desarrollo](../../Docs/DESARROLLO.md)

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
