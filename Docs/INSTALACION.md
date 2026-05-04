# 📥 Guía de Instalación - SAILOR S.A.S

Instrucciones paso a paso para instalar y ejecutar el proyecto completo (Backend + Frontend + Base de Datos).

---

## 🔧 Requisitos Previos

### Hardware
- **RAM**: 4GB mínimo, 8GB recomendado
- **Disco**: 2GB libres
- **SO**: Windows 10+, macOS 10.15+, o Linux

### Software
- **Java 17+** - [Descargar](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.8+** - [Descargar](https://maven.apache.org/download.cgi)
- **MySQL 8+** - [Descargar](https://dev.mysql.com/downloads/mysql/)
- **Node.js 18+** - [Descargar](https://nodejs.org/)
- **npm 9+** - Se instala con Node.js
- **Git** (opcional) - [Descargar](https://git-scm.com/)

---

## ✅ Verificar Instalación

Abre terminal y ejecuta:

```bash
# Java
java -version
# Output esperado: openjdk version "17.0.1" 2021-10-19

# Maven
mvn -version
# Output esperado: Apache Maven 3.8.1

# MySQL
mysql --version
# Output esperado: mysql Ver 8.0.XX

# Node.js
node --version
# Output esperado: vXX.XX.XX

# npm
npm --version
# Output esperado: XX.XX.XX
```

Si falta alguno, instálalo antes de continuar.

---

## 1️⃣ Instalar Base de Datos (MySQL)

### Windows

```bash
# Abrir MySQL Command Line Client
# O ejecutar en PowerShell como Administrador:

mysql -u root -p
```

Ingresa contraseña (default: vacía o 1617)

```sql
-- Crear base de datos
CREATE DATABASE sailor_db;

-- Verificar
SHOW DATABASES;

-- Salir
EXIT;
```

### Linux / macOS

```bash
# Iniciar MySQL server
sudo systemctl start mysql

# O si usas Homebrew (macOS)
brew services start mysql-server

# Acceder
mysql -u root -p

# Dentro de MySQL:
CREATE DATABASE sailor_db;
EXIT;
```

---

## 2️⃣ Instalar Backend

### Paso 1: Navegar al directorio

```bash
cd "c:\Users\nicol\Downloads\Proyecto-SAILOR S.A.S\backend"
```

### Paso 2: Descargar dependencias Maven

```bash
mvn clean install
```

Espera a que termine (puede tardar 2-5 minutos la primera vez)

### Paso 3: Configurar application.properties

Edita `src/main/resources/application.properties`:

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/sailor_db
spring.datasource.username=root
spring.datasource.password=1617
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Servidor
server.port=8081

# JWT
jwt.secret=my_super_secret_key_for_sailor_2024_must_be_long_enough_for_hs512_algorithm
jwt.expiration=86400000
```

> ⚠️ **IMPORTANTE:** Cambia credenciales según tu instalación de MySQL

### Paso 4: Compilar (opcional, para verificar)

```bash
mvn clean compile
```

✅ Si ves `BUILD SUCCESS` está correcto.

### Paso 5: Iniciar Backend

```bash
mvn spring-boot:run
```

**Output esperado:**
```
Started BackendApplication in 12.345 seconds
Server running on http://localhost:8081
```

🎉 **Backend listo en `http://localhost:8081`**

---

## 3️⃣ Instalar Frontend

### Paso 1: Abrir nueva terminal

```bash
cd "c:\Users\nicol\Downloads\Proyecto-SAILOR S.A.S\Frontend"
```

### Paso 2: Instalar dependencias npm

```bash
npm install
```

Espera a que termine (puede tardar 1-3 minutos)

### Paso 3: Iniciar servidor de desarrollo

```bash
npm run dev
```

**Output esperado:**
```
VITE v5.0.0 ready in 567ms

➜  Local:   http://localhost:5173/
➜  press h to show help
```

### Paso 4: Abrir en navegador

Abre navegador en: `http://localhost:5173`

🎉 **Frontend listo en `http://localhost:5173`**

---

## 4️⃣ Verificar Instalación Completa

### Checklist

- [ ] MySQL corriendo en `localhost:3306`
- [ ] Base de datos `sailor_db` creada
- [ ] Backend corriendo en `http://localhost:8081`
- [ ] Frontend corriendo en `http://localhost:5173`
- [ ] Página inicial carga sin errores

### Prueba rápida

1. Ve a `http://localhost:5173/login`
2. Intenta hacer login:
   - Email: `admin@sailor.com`
   - Password: `password123`
3. Si ves error "Usuario no encontrado", es normal (no hay datos aún)

---

## 🌱 Crear Datos Iniciales (Seed)

### Opción 1: Via API (Postman/Insomnia)

1. Descarga [Postman](https://www.postman.com/downloads/)
2. Abre nueva request POST
3. URL: `http://localhost:8081/api/usuarios/registrar`
4. Body (JSON):
```json
{
  "nombre": "Admin",
  "apellido": "Sailor",
  "identificacion": "123456789",
  "correo": "admin@sailor.com",
  "password": "password123",
  "rol": "ADMIN",
  "direccion": "Cra 1 #2-3"
}
```
5. Click "Send"

### Opción 2: Via MySQL (SQL Script)

En MySQL:
```sql
USE sailor_db;

INSERT INTO usuarios (nombre, apellido, identificacion, correo, password_hash, rol, direccion)
VALUES 
('Admin', 'Sailor', '123456789', 'admin@sailor.com', '$2a$10$...', 'ADMIN', 'Cra 1 #2-3'),
('Juan', 'Empleado', '987654321', 'juan@sailor.com', '$2a$10$...', 'EMPLEADO', 'Cra 2 #3-4');
```

> ⚠️ Nota: Los passwords necesitan ser hasheados con BCrypt

---

## 🚀 Iniciar Sesión

Después de crear usuarios, puedes hacer login:

```bash
# Via curl
curl -X POST http://localhost:8081/api/usuarios/login \
  -H "Content-Type: application/json" \
  -d '{
    "correo": "admin@sailor.com",
    "password": "password123"
  }'

# Respuesta esperada:
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

---

## 📋 Estructura de Carpetas

```
Proyecto-SAILOR S.A.S/
├── backend/                    # API Java Spring Boot
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── target/                 # Archivos compilados (ignorar)
│   └── README.md               # Documentación Backend
│
├── Frontend/                   # App React + TypeScript
│   ├── package.json
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   │   ├── App.tsx
│   │   └── main.tsx
│   ├── vite.config.ts
│   ├── tsconfig.json
│   └── README.md               # Documentación Frontend
│
└── Docs/                       # Documentación general
    ├── INSTALACION.md          # Este archivo
    ├── API_ENDPOINTS.md
    ├── ARQUITECTURA.md
    └── ...
```

---

## 🛠️ Comandos Útiles

### Backend

```bash
cd backend

# Limpiar y compilar
mvn clean install

# Solo compilar
mvn clean compile

# Ejecutar aplicación
mvn spring-boot:run

# Ejecutar tests
mvn test

# Crear JAR
mvn package

# Ejecutar JAR compilado
java -jar target/backend-0.0.1-SNAPSHOT.jar

# Ver logs de Spring Boot
tail -f target/backend.log
```

### Frontend

```bash
cd Frontend

# Instalar dependencias
npm install

# Servidor desarrollo
npm run dev

# Build producción
npm run build

# Ver build compilado
npm run preview

# Tests
npm run test
npm run test:watch

# Linting
npm run lint

# Build optimizado para desarrollo
npm run build:dev
```

### MySQL

```bash
# Acceder a MySQL
mysql -u root -p

# Dentro de MySQL:
SHOW DATABASES;                    # Ver bases de datos
USE sailor_db;                     # Usar sailor_db
SHOW TABLES;                       # Ver tablas
SELECT * FROM usuarios;            # Ver usuarios
DESCRIBE usuarios;                 # Ver estructura tabla
```

---

## 🔍 Verificar Conectividad

### Backend ↔ Frontend

Abre consola del navegador (F12) y ejecuta:

```javascript
// Prueba conexión a Backend
fetch('http://localhost:8081/api/productos')
  .then(r => r.json())
  .then(d => console.log('✅ Backend conectado:', d))
  .catch(e => console.log('❌ Error:', e))
```

### Backend ↔ MySQL

En terminal Backend, verás logs como:
```
Hibernate: SELECT ... FROM usuarios
```

Si ves esto, está conectado a BD.

---

## 🚨 Troubleshooting

### Error: "Connection refused - localhost:3306"

```bash
# Verificar que MySQL está corriendo
mysql --version
mysql -u root -p -e "SELECT VERSION();"

# Windows: Iniciar servicio
# Services.msc → MySQL80 → Restart

# Linux:
sudo systemctl start mysql

# macOS (Homebrew):
brew services start mysql-server
```

### Error: "Address already in use" (Puerto 8081)

```bash
# Cambiar puerto en application.properties
server.port=8082

# O matar proceso usando puerto:
# Windows:
netstat -ano | findstr :8081
taskkill /PID XXXX /F

# Linux/macOS:
lsof -i :8081
kill -9 XXXXX
```

### Error: "Cannot find module '@radix-ui/...'" en Frontend

```bash
cd Frontend
npm install
rm -rf node_modules
npm install
npm run dev
```

### Error: "npm ERR! code EACCES"

```bash
# Problema de permisos
# Solución 1: Usar sudo (no recomendado)
sudo npm install

# Solución 2: Configurar npm
mkdir ~/.npm-global
npm config set prefix '~/.npm-global'
export PATH=~/.npm-global/bin:$PATH
npm install
```

### Backend no compila - "symbol not found"

```bash
# Limpiar todo y reinstalar
cd backend
mvn clean
mvn install -U
mvn spring-boot:run
```

---

## 📱 Acceso a Aplicación

### URLs

| Componente | URL |
|-----------|-----|
| Frontend (Tienda) | http://localhost:5173 |
| Frontend (Admin) | http://localhost:5173/admin |
| Backend API | http://localhost:8081/api |
| MySQL | localhost:3306 |

### Cuentas de Prueba

| Email | Password | Rol |
|-------|----------|-----|
| admin@sailor.com | password123 | ADMIN |
| empleado@sailor.com | password123 | EMPLEADO |
| cliente@sailor.com | password123 | CLIENTE |

> ⚠️ Estas cuentas deben ser creadas primero via API

---

## 📖 Próximos Pasos

1. Lee [API_ENDPOINTS.md](API_ENDPOINTS.md) para conocer endpoints
2. Lee [ARQUITECTURA.md](ARQUITECTURA.md) para entender estructura
3. Lee [backend/README.md](../backend/README.md) para más detalles backend
4. Lee [Frontend/README.md](../Frontend/README.md) para más detalles frontend

---

## 💡 Tips

- Mantén 3 terminales abiertas: una para MySQL, otra para Backend, otra para Frontend
- Usa Postman/Insomnia para probar API sin interfaz
- Usa DevTools del navegador (F12) para debug frontend
- Revisa logs en ambos lados para entender flujo

---

## 📞 Soporte

Si tienes problemas:
1. Verifica todos los requisitos previos
2. Lee troubleshooting arriba
3. Revisa logs (terminal y consola)
4. Contacta al equipo de desarrollo

