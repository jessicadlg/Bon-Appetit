use db;

INSERT INTO Categoria(nombreCategoria) values
                                           ('Bebidas'),
                                           ('Apta celiacos'),
                                           ('Comida vegana'),
                                           ('Comida vegetariana'),
                                           ('Desayunos'),
                                           ('Empanadas'),
                                           ('Hamburguesas'),
                                           ('Papas fritas'),
                                           ('Pastas'),
                                           ('Pescados'),
                                           ('Pizzas'),
                                           ('Sushi');

INSERT INTO Producto(nombre, precio,codigo,ID_CATEGORIA,activo,nombreImagen,cantidadMeGusta,descripcion) values
                                                                                                             ('Agua',50.0,"FF1",1,false,"agua.webp",5,"Agua Mineral sin Gas Villavicencio Botella de 500 ml"),
                                                                                                             ('Cerveza Stella Artois',150.0,"FF",1,false,"cerveza.webp",5,"Cerveza Stella Artois Lata de 473 ml"),
                                                                                                             ('Coca cola',250.0,"FF",1,false,"cocacola.webp",5,"Coca cola Sabor original Botella de 1.5 lts"),
                                                                                                             ('Chipa sin tacc',350.0,"FF",2,false,"chipa.webp",3,""),
                                                                                                             ('Pizza de Jamon',450.0,"FF21",11,false,"pizza.webp",2,""),
                                                                                                             ('Pizza de pepperoni',450.0,"FF22",11,false,"pizza2.webp",2,"La auténtica pizza Pepperoni con tomate, queso, pepperoni y orégano sobre una fina masa y hornada en el horno de leña."),
                                                                                                             ('Fugazzeta Rellena',450.0,"FF23",11,false,"pizza3.webp",2,"No hay una pizza más argentina que la fugazzeta rellena disfruta de esta delicia que incluye jamón cocido natural, doble mozzarella, cebolla blanca, provolone y aceite de oliva."),
                                                                                                             ('Pizza de Muzzarella',450.0,"FF24",11,false,"pizza4.webp",1,"La pizza de mozzarella es la más clásica entre todas las recetas de pizzas. Es la que nos gusta a la mayoría. Es una receta compuesta por una masa baja y crocante con una cubierta de salsa de tomate, mozzarella, aceitunas y orégano."),
                                                                                                             ('Pizza Vegana',650.0,"FE23",3,false,"pizza-vegana-tomate.webp",1,""),
                                                                                                             ('Panqueque de Espinaca',450.0,"FE24",3,false,"vegano-panqueque.webp",1,""),
                                                                                                             ('Pizza Tropical',450.0,"FE26",3,false,"pizza-tropical.webp",1,""),
                                                                                                             ('Wrap vegetariano',350.0,"FI26",4,false,"wrap-vegetariano.webp",3,""),
                                                                                                             ('Ensalada mediterranea',350.0,"FI27",4,false,"ensalada-mediterranea.webp",1,""),
                                                                                                             ('Hamburquesa Jamon y queso',550.0,"FE27",7,false,"hambur-jyq.webp",2,"Hamburguesa doble con jamón y queso con pan de semillas"),
                                                                                                             ('Hamburquesa con Huevo',550.0,"FE27",7,false,"hambur-huevo.webp",2,"Hamburguesa doble con jamón, huevo,lechuga,tomate,queso cheddar y pan con semillas"),
                                                                                                             ('Hamburquesa con Cheddar',650.0,"FE27",7,false,"hambur-cheddar.webp",2,"Nuestra carne de siempre con loncha de queso cheddar, salsa barbacoa, cheddar en crema, y panceta. La mejor combinación de sabores."),
                                                                                                             ('Papas Fritas',350.0,"FG20",8,false,"papas-fritas.webp",3,""),
                                                                                                             ('Papas con Huevo',450.0,"FG21",8,false,"papas-huevo.webp",2,""),
                                                                                                             ('Papas al Verdeo',550.0,"FG24",8,false,"papas-verdeo.webp",1,"");

INSERT INTO Reserva(nombre,celular,mesas,fecha,hora) values
    ('Sebastian','1566466616',1,'2021/10/01',"22:00");
    
    INSERT INTO Reserva(nombre,celular,mesas,fecha,hora) values
    ('Sebastian','1566466616',30,'2021/11/01',"22:00");