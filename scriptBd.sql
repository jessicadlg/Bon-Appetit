use db;

INSERT INTO Categoria(nombreCategoria) values
('Bebidas'),
('Apta celiacos'),
('Comida vegana'),
('Comida vegetariana'),
('Hamburguesas'),
('Papas fritas'),
('Pizzas'),
('Sushi');

INSERT INTO Bebida(ID_PRODUCTO,nombre, precio,codigo,ID_CATEGORIA,activo,nombreImagen,cantidadMeGusta,descripcion) values
(1,'Agua', 80.0,"F21",1,false,"agua.webp",3,"Agua Mineral sin Gas Villavicencio Botella de 500 ml");

INSERT INTO Comida(ID_PRODUCTO,nombre, precio,codigo,ID_CATEGORIA,activo,nombreImagen,cantidadMeGusta,descripcion,tiempoDeCoccion) values
(5,'Pizza de Jamon',450.0,"FF21",7,false,"pizza.webp",2,"La pizza de jamón con mozzarella huevo y aceitunas.No dejes de probarla ",'60.00'),
(6,'Pizza de pepperoni',450.0,"FF22",7,false,"pizza2.webp",2,"La auténtica pizza Pepperoni con tomate, queso, pepperoni y orégano sobre una fina masa y hornada en el horno de leña.",'60.00' ),
(7,'Fugazzeta Rellena',450.0,"FF23",7,false,"pizza3.webp",2,"No hay una pizza más argentina que la fugazzeta rellena disfruta de esta delicia que incluye jamón cocido natural, doble mozzarella, cebolla blanca, provolone y aceite de oliva.",'60.00' ),
(8,'Pizza de Muzzarella',450.0,"FF24",7,false,"pizza4.webp",1,"La pizza de mozzarella es la más clásica entre todas las recetas de pizzas. Es la que nos gusta a la mayoría. Es una receta compuesta por una masa baja y crocante con una cubierta de salsa de tomate, mozzarella, aceitunas y orégano.",'60.00' ),
(9,'Pizza Vegana',650.0,"FE23",3,false,"pizza-vegana-tomate.webp",1,"",'60.00'),
(10,'Panqueque de Espinaca',450.0,"FE24",3,false,"vegano-panqueque.webp",1,"",'60.00'),
(11,'Pizza Tropical',450.0,"FE26",3,false,"pizza-tropical.webp",1,"",'60.00'),
(12,'Wrap vegetariano',350.0,"FI26",4,false,"wrap-vegetariano.webp",3,"",'60.00'),
(13,'Ensalada mediterranea',350.0,"FI27",4,false,"ensalada-mediterranea.webp",1,"",'60.00'),
(14,'Hamburquesa Jamon y queso',550.0,"FE27",5,false,"hambur-jyq.webp",2,"Hamburguesa doble con jamón y queso con pan de semillas",'60.00'),
(15,'Hamburquesa con Huevo',550.0,"FE27",5,false,"hambur-huevo.webp",2,"Hamburguesa doble con jamón, huevo,lechuga,tomate,queso cheddar y pan con semillas",'60.00' ),
(16,'Hamburquesa con Cheddar',650.0,"FE27",5,false,"hambur-cheddar.webp",2,"Nuestra carne de siempre con loncha de queso cheddar, salsa barbacoa, cheddar en crema, y panceta. La mejor combinación de sabores.",'60.00' ),
(17,'Papas Fritas',350.0,"FG20",6,false,"papas-fritas.webp",3,"Porción de papas grandes",'60.00' ),
(18,'Papas con Huevo',450.0,"FG21",6,false,"papas-huevo.webp",2,"",'60.00' ),
(19,'Papas al Verdeo',550.0,"FG24",6,false,"papas-verdeo.webp",1,"",'60.00' ),
(20,'Pizza',400.0,"FF1",7,false,"pizza.webp",3,"Pizza",'60.00' );

INSERT INTO Producto(ID_PRODUCTO,nombre, precio,codigo,ID_CATEGORIA,activo,nombreImagen,cantidadMeGusta,descripcion) values
(2,'Cerveza Stella Artois',150.0,"FF",1,false,"cerveza.webp",5,"Cerveza Stella Artois Lata de 473 ml"),
(3,'Coca cola',250.0,"FF",1,false,"cocacola.webp",5,"Coca cola Sabor original Botella de 1.5 lts");


INSERT INTO Reserva(nombre,celular,mesas,fecha,hora) values
('Joel', '1522557899',1, '2021-10-30','22:00'),
('Rodrigo','1522557899',25, '2021-10-30', '22:00'),  
('Sebastian','1566466616',4,'2021/10/30',"22:00");

INSERT INTO Pedido(tiempoPreparacion,total) values
('0.0','0.0');