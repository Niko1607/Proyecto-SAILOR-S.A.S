CREATE DATABASE IF NOT EXISTS base_de_datos;
USE base_de_datos;

-- Tabla de empleados (corregido nombre de columna)
CREATE TABLE empleados(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    identificacion VARCHAR(50) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    horas_trabajo INT NOT NULL  -- Nombre consistente con DAO
);

-- Tabla de productos
CREATE TABLE productos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    Talla VARCHAR(50) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    cantidad INT NOT NULL
);

-- Tabla de proveedores
CREATE TABLE proveedores(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    empresa VARCHAR(50) NOT NULL

);

-- Relación muchos-a-muchos entre proveedores y productos
CREATE TABLE proveedor_producto(
    proveedor_id INT NOT NULL,
    producto_id INT NOT NULL,
    PRIMARY KEY (proveedor_id, producto_id),
    FOREIGN KEY (proveedor_id) REFERENCES proveedores(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE CASCADE
);

-- Tabla de inventario
CREATE TABLE inventario(
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    proveedor_id INT NOT NULL,
    cantidad INT NOT NULL,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (producto_id) REFERENCES productos(id) ON DELETE CASCADE,
    FOREIGN KEY (proveedor_id) REFERENCES proveedores(id) ON DELETE CASCADE 
);

-- Vista útil para consultar el inventario
CREATE VIEW vista_inventario AS
SELECT
    i.id,
    p.nombre AS producto,
    pr.nombre AS proveedor,
    i.cantidad,
    i.fecha_actualizacion
FROM inventario i
JOIN productos p ON i.producto_id = p.id
JOIN proveedores pr ON i.proveedor_id = pr.id;

DROP TABLE IF EXISTS empleados, productos, proveedores, proveedor_producto, inventario; 

DESCRIBE empleados;