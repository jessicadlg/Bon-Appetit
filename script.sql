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

INSERT INTO Producto(nombre, precio,codigo, ID_CATEGORIA,activo,nombreImagen) values
                                                                                  ('Agua',50.0,"FF1",1,false,"agua.webp"),
                                                                                  ('Cerveza Stella Artois',50.0,"FF",1,false,"cerveza.webp"),
                                                                                  ('Cepita',50.0,"FF",1,false,"jugo-cepita-200ml.png"),
                                                                                  ('Coca cola',50.0,"FF",1,false,"cocacola.webp"),
                                                                                  ('Pizza de Jamon',450.0,"FF21",12,false,"pizza.webp"),
                                                                                  ('Pizza',450.0,"FF22",12,false,"pizza2.webp"),
                                                                                  ('Fugazzeta Rellena',450.0,"FF23",12,false,"pizza3.webp"),
                                                                                  ('Pizza de Muzzarella',450.0,"FF24",12,false,"pizza4.webp"),
                                                                                  ('Pizza Vegana',650.0,"FE23",3,false,"pizza-vegana-tomate.webp"),
                                                                                  ('Panqueque de Espinaca',450.0,"FE24",3,false,"vegano-panqueque.webp"),
                                                                                  ('Pizza de Tomate',450.0,"FE25",3,false,"pizza-vegana-tomate.webp"),
                                                                                  ('Pizza Tropical',450.0,"FE26",3,false,"pizza-tropical.webp"),
                                                                                  ('Hamburquesa Jamon y queso',550.0,"FE27",7,false,"hambur-jyq.webp"),
                                                                                  ('Hamburquesa con Huevo',550.0,"FE27",7,false,"hambur-huevo.webp"),
                                                                                  ('Hamburquesa con Cheddar',650.0,"FE27",7,false,"hambur-cheddar.webp"),
                                                                                  ('Papas Fritas',350.0,"FG20",8,false,"papas-fritas.webp"),
                                                                                  ('Papas con Huevo',450.0,"FG21",8,false,"papas-huevo.webp"),
                                                                                  ('Papas al Verdeo',550.0,"FG24",8,false,"papas-verdeo.webp");