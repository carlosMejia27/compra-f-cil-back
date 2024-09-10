-- Crear la tabla 'cliente'
CREATE TABLE cliente (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(20),
    direccion VARCHAR(255),
    fecha_registro DATE NOT NULL
);

-- Crear la tabla 'producto'
CREATE TABLE producto (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL
);

-- Crear la tabla 'orden_compra'
CREATE TABLE orden_compra (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    fecha DATE NOT NULL,
    cantidad INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    cliente_id BIGINT,
    producto_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE,
    FOREIGN KEY (producto_id) REFERENCES producto(id) ON DELETE CASCADE
);

