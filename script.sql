use db;

drop database db;
create database db;

INSERT INTO Categoria(nombreCategoria) values('arepas'),
('bebidas'),
('calzones'),
('carnes'),
('celiacos'),
('comida vegana'),
('comida vegetariana'),
('desayunos'),
('empanadas'),
('hamburguesas'),
('menu del dia'),
('papas fritas'),
('pastas'),
('pescados y mariscos'),
('pizzas'),
('sushi');

INSERT INTO Producto(nombre, precio,codigo, ID_CATEGORIA,activo,nombreImagen) values
-- ('Agua',50.0,"FF",1,false),
('Aquarius',50.0,"FF",1,false,"aquarius.png"),
('Cepita',50.0,"FF",1,false,"jugo-cepita-200ml.png"),
('Coca-cola',50.0,"FF",1,false,"coca-cola.png");


select * from Producto;