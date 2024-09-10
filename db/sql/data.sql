-- Insertar datos en la tabla 'cliente'
INSERT INTO cliente (nombre, apellido, email, telefono, direccion, fecha_registro)
VALUES
    ('Juan', 'Pérez', 'juan.perez@example.com', '555-1234', 'Calle Falsa 123', '2024-09-01'),
    ('María', 'López', 'maria.lopez@example.com', '555-5678', 'Av. Siempre Viva 456', '2024-09-02'),
    ('Carlos', 'Martínez', 'carlos.martinez@example.com', '555-8765', 'Plaza Mayor 789', '2024-09-03'),
    ('Ana', 'García', 'ana.garcia@example.com', '555-4321', 'Paseo Central 101', '2024-09-04'),
    ('Ana', 'Fernández', 'laura.fernandez@example.com', '555-3456', 'Calle del Sol 202', '2024-09-05');

-- Insertar datos en la tabla 'producto'
INSERT INTO producto (nombre, descripcion, precio)
VALUES
    ('Laptop', 'Laptop de alta gama', 1500.00),
    ('Teléfono', 'Teléfono inteligente', 150.00),
    ('Ratón', 'Ratón ergonómico', 25.50),
    ('Teclado', 'Teclado mecánico', 75.00),
    ('Monitor', 'Monitor LED 24 pulgadas', 200.00);

-- Insertar datos en la tabla 'orden_compra'
INSERT INTO orden_compra (fecha, cantidad, total, cliente_id, producto_id)
VALUES
    ('2024-09-02', 1, 1500.00, 1, 1),
    ('2024-09-03', 2, 300.00, 2, 2),
    ('2024-09-04', 3, 76.50, 1, 3),
    ('2024-09-05', 1, 75.00, 3, 4),
    ('2024-09-06', 2, 400.00, 4, 5);


