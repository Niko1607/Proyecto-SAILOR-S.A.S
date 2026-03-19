USE db_sailor_s.a.s;

CREATE TABLE Usuarios(
	idUsuario INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	correo VARCHAR(50) NOT NULL,
    identificacion INT NOT NULL,
	password VARCHAR(50) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    direccion VARCHAR(50) NOT NULL,
	PRIMARY KEY(idUsuario)
);

CREATE TABLE Venta( 
    idVenta INT NOT NULL AUTO_INCREMENT,
    idUsuario INT NOT NULL,
    fecha DATE NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    estado BOOLEAN NOT NULL,
    PRIMARY KEY(idVenta),
    FOREIGN KEY(idUsuario) REFERENCES Usuarios(idUsuario)
);

CREATE TABLE Productos (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,
    nombreProducto VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    precioProducto DOUBLE NOT NULL,
    cantidad INT NOT NULL,
    stockMinimo INT,
    stockMaximo INT,
    fechaRegistro DATE,
    activo BOOLEAN
);

CREATE TABLE Categorias(
    idCategoria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY(idCategoria)
);

CREATE TABLE Proveedores(
    idProveedor INT AUTO_INCREMENT,
    nombreProveedorEmpresa VARCHAR(50) NOT NULL,
    identificacion VARCHAR(50) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    direccion VARCHAR(500) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    tipoProveedor VARCHAR(50) NOT NULL,
    activo BOOLEAN NOT NULL,
    ciudad VARCHAR(50) NOT NULL,
    fechaRegistro DATE NOT NULL,
    PRIMARY KEY(idProveedor)
);

CREATE TABLE detalleventa (

    idDetalleVenta INT AUTO_INCREMENT PRIMARY KEY,
    idVenta INT,
    idProducto INT,
    cantidad INT,
    precioUnitario DOUBLE,
    subtotal DOUBLE,
    FOREIGN KEY (idVenta) REFERENCES venta(idVenta),
    FOREIGN KEY (idProducto) REFERENCES productos(idProducto)
);

CREATE TABLE Inventario(
    idInventario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idProducto INT NOT NULL,
    idUsuario INT NOT NULL,
    cantidad INT NOT NULL,
    fechaMovimiento DATE NOT NULL,
    FOREIGN KEY(idProducto) REFERENCES Productos(idProducto),
    FOREIGN KEY(idUsuario) REFERENCES Usuarios(idUsuario)
);


