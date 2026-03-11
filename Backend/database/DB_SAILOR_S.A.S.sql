USE db_sailor_s.a.s;

CREATE TABLE Usuarios(
	idUsuario INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
	apellido VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
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
    PRIMARY KEY(idVenta),
    FOREIGN KEY(idUsuario) REFERENCES Usuarios(idUsuario)
);

CREATE TABLE Productos (
    idProducto INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    descripcion TEXT NOT NULL,
    idCategoria INT NOT NULL,
    idProveedor INT NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (idProducto),
    FOREIGN KEY (idCategoria) REFERENCES Categorias(idCategoria) on delete cascade,
    FOREIGN KEY (idProveedor) REFERENCES Proveedores(idProveedor) on delete cascade
);

CREATE TABLE Categorias(
    idCategoria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY(idCategoria)
);

CREATE TABLE Proveedores(
    idProveedor INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    PRIMARY KEY(idProveedor)
);

CREATE TABLE DetalleVenta(
    idDetalleVenta INT NOT NULL AUTO_INCREMENT,
    idVenta INT NOT NULL,
    idProducto INT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    PRIMARY KEY(idDetalleVenta),
    FOREIGN KEY(idVenta) REFERENCES Venta(idVenta),
    FOREIGN KEY(idProducto) REFERENCES Productos(idProducto)
);

