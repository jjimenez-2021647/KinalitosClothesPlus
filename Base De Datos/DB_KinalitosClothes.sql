-- Por Josué Gilberto Jiménez Ajtún, Sebastian alejandro Molina Herrera, Rhandy Estuardo Cana Subuyj, Joshua Alejandro Santa Cruz Chicas, 
-- Alejandro Jose Arocha Virula, Jefry Andre Cruz Yuman, Roberto Andre Rodriguez Gonzalez, Jeremy Jhoel Mendez Palencia
-- 2021647, 2021528, 2021639, 2024254, 2024203, 2021644, 2023342, 2021550
-- IN5BM
-- 07/07/2025

-- Creación de la base de datos
Drop Database if exists DB_KinalitosClothes; 
Create Database DB_KinalitosClothes;
Use DB_KinalitosClothes;

-- ---------- Entidades ----------
-- Proveedores 
Create table Proveedores(
	codigoProveedor int auto_increment,
    nombreProveedor varchar(100) not null,
    telefonoProveedor varchar(20) not null,
    correoProveedor varchar(200) not null,
    paisProveedor varchar(150),
    primary key PK_codigoProveedor (codigoProveedor)
);

-- Categorías
Create table Categorias(
	codigoCategoria int auto_increment,
    nombreCategoria varchar(100) not null,
    descripcionCategoria varchar(200) not null,
    genero enum('Hombre', 'Mujer', 'Unisex') not null,
    rangoEdad enum('Infantil', 'Juvenil', 'Adultos') not null,
    primary key PK_codigoCategoria (codigoCategoria)
);

-- Metodo Pagos
Create table MetodoPagos(
	codigoMetodoPago int auto_increment,
    tipoMetodoPago enum('Tarjeta', 'Efectivo') not null,
    entidadFinanciaera varchar(200) not null,
    moneda varchar(150) not null,
	porcentajeComision double(5,2) not null,
    primary key PK_codigoMetodoPago (codigoMetodoPago)
);

-- Usuarios
Create table Usuarios(
	codigoUsuario int auto_increment,
	nombreUsuario varchar(100) not null,
    contraseñaUsuario varchar(100) not null,
    tipoUsuario enum('Admin', 'Usuario') not null,
    fechaRegistro date not null,
    primary key PK_codigoUsuario (codigoUsuario) 
);

-- --------------------------- Procedimientos almacenados ---------------------------

-- --------------------------- Entidad Proveedor --------------------------- 
-- Agregar Proveedor
Delimiter //
	Create procedure sp_AgregarProveedor(
    in nombreProveedor varchar(100), 
    in telefonoProveedor varchar(20), 
    in correoProveedor varchar(200), 
    in paisProveedor varchar(150))
		Begin
			Insert into Proveedores(nombreProveedor, telefonoProveedor, correoProveedor, paisProveedor)
				Values(nombreProveedor, telefonoProveedor, correoProveedor, paisProveedor);
        End //
Delimiter ;
call sp_AgregarProveedor('Textiles Centro', '+502 2456-7890', 'textiles.centro@gmail.com', 'Guatemala');
call sp_AgregarProveedor('Distribuidora El Faro', '+502 3345-1234', 'elfaro.proveedores@gmail.com', 'Guatemala');
call sp_AgregarProveedor('Moda Global', '+502 5566-7788', 'modaglobal@gmail.com', 'México');
call sp_AgregarProveedor('Colores y Telas', '+502 4789-0012', 'coloresytelas@gmail.com', 'El Salvador');
call sp_AgregarProveedor('Fashion Import', '+502 5123-4567', 'fashionimport@gmail.com', 'Estados Unidos');
call sp_AgregarProveedor('Ropa Latina', '+502 6034-5678', 'ropalatina@gmail.com', 'Honduras');
call sp_AgregarProveedor('Tejidos Maya', '+502 7234-1122', 'tejidosmaya@gmail.com', 'Guatemala');
call sp_AgregarProveedor('Estilo Urbano', '+502 3109-8765', 'estilourbano@gmail.com', 'Costa Rica');
call sp_AgregarProveedor('Boutique Express', '+502 4490-2233', 'boutique.express@gmail.com', 'Guatemala');
call sp_AgregarProveedor('Importadora del Sur', '+502 5678-3344', 'import.sur@gmail.com', 'Panamá');
call sp_AgregarProveedor('Distribuciones Vera', '+502 6012-4455', 'vera.distribuciones@gmail.com', 'Nicaragua');
call sp_AgregarProveedor('Telas del Norte', '+502 4343-5566', 'telasnorte@gmail.com', 'México');
call sp_AgregarProveedor('Universal Ropa', '+502 7201-6677', 'universalropa@gmail.com', 'Colombia');
call sp_AgregarProveedor('Ropa Moderna', '+502 8182-7788', 'ropamoderna@gmail.com', 'Guatemala');
call sp_AgregarProveedor('Estampados GT', '+502 9093-8899', 'estampados.gt@gmail.com', 'Guatemala');
call sp_AgregarProveedor('Tendencias del Istmo', '+502 4567-9900', 'tendenciasistmo@gmail.com', 'El Salvador');
call sp_AgregarProveedor('Comercial La Moda', '+502 2121-3344', 'comercialmoda@gmail.com', 'Honduras');
call sp_AgregarProveedor('Distribuciones América', '+502 3678-4455', 'america.dist@gmail.com', 'Costa Rica');
call sp_AgregarProveedor('Tiendas FashionMix', '+502 4532-6677', 'fashionmix.proveedor@gmail.com', 'Guatemala');
call sp_AgregarProveedor('Estilo Joven', '+502 2345-7788', 'estilojoven.gt@gmail.com', 'México');


-- Listar Proveedor
Delimiter //
	Create procedure sp_ListarProveedor()
		Begin
			Select codigoProveedor, nombreProveedor, telefonoProveedor, correoProveedor, paisProveedor from Proveedores;
        End //
Delimiter ;
call sp_ListarProveedor();

-- Eliminar Proveedor
Delimiter //
	Create procedure sp_EliminarProveedor(
    in _codigoProveedor int)
		Begin
			set foreign_key_checks = 0;
				Delete from Proveedores
					where codigoProveedor = _codigoProveedor;
				Select row_count() as filasEliminadas;
			set foreign_key_checks = 1;
        End//
Delimiter ;
call sp_EliminarProveedor(15);

-- Buscar Proveedor
Delimiter //
	Create procedure sp_BuscarProveedor(
    in _codigoProveedor int)
		Begin
			Select codigoProveedor, nombreProveedor, telefonoProveedor, correoProveedor, paisProveedor from Proveedores
				where codigoProveedor = _codigoProveedor;
        End //
Delimiter ;
call sp_BuscarProveedor(1);

-- Editar Proveedor
Delimiter //
	Create procedure sp_EditarProveedor(
    in _codigoProveedor int,
    in _nombreProveedor varchar(100), 
    in _telefonoProveedor varchar(20), 
    in _correoProveedor varchar(200), 
    in _paisProveedor varchar(150))
		Begin
			Update Proveedores
				set nombreProveedor = _nombreProveedor,
					telefonoProveedor = _telefonoProveedor,
                    correoProveedor = _correoProveedor,
                    paisProveedor = _paisProveedor
					where codigoProveedor = _codigoProveedor;
        End //
Delimiter ;
call sp_EditarProveedor(16, 'Tendencias del Sur', '+502 4567-8899', 'tendenciassur@gmail.com', 'Costa Rica');
call sp_EditarProveedor(17, 'La Moda Express', '+502 2122-4455', 'lamodaexpress@gmail.com', 'Honduras');
call sp_EditarProveedor(18, 'Distribuciones Continental', '+502 3678-5566', 'dist.continental@gmail.com', 'Colombia');
call sp_EditarProveedor(19, 'FashionMix Proveedores', '+502 4533-6788', 'proveedor.fashionmix@gmail.com', 'Guatemala');
call sp_EditarProveedor(20, 'Estilo Joven Plus', '+502 2346-7899', 'estilojovenplus@gmail.com', 'México');

-- --------------------------- Entidad Categoria --------------------------- 
-- Agregar Categoria
Delimiter //
	Create procedure sp_AgregarCategoria(
    in nombreCategoria varchar(100), 
    in descripcionCategoria varchar(200), 
    in genero enum('Hombre','Mujer','Unisex'), 
    in rangoEdad enum('Infantil','Juvenil','Adultos'))
		Begin
			Insert into Categorias(nombreCategoria, descripcionCategoria, genero, rangoEdad)
				Values(nombreCategoria, descripcionCategoria, genero, rangoEdad);
        End //
Delimiter ;
call sp_AgregarCategoria('Camisas Casual', 'Camisas de uso diario y relajado', 'Hombre', 'Adultos');
call sp_AgregarCategoria('Blusas Elegantes', 'Blusas para eventos y trabajo', 'Mujer', 'Adultos');
call sp_AgregarCategoria('Ropa Deportiva', 'Prendas para hacer ejercicio', 'Unisex', 'Juvenil');
call sp_AgregarCategoria('Pijamas', 'Conjunto de dormir cómodo', 'Unisex', 'Infantil');
call sp_AgregarCategoria('Vestidos de Fiesta', 'Vestidos elegantes para eventos', 'Mujer', 'Juvenil');
call sp_AgregarCategoria('Camisas Formales', 'Camisas para oficina o reuniones', 'Hombre', 'Adultos');
call sp_AgregarCategoria('Pantalones Cargo', 'Pantalones con bolsillos utilitarios', 'Hombre', 'Juvenil');
call sp_AgregarCategoria('Faldas Casual', 'Faldas para el día a día', 'Mujer', 'Adultos');
call sp_AgregarCategoria('Shorts', 'Shorts para clima cálido', 'Unisex', 'Juvenil');
call sp_AgregarCategoria('Suéteres', 'Prendas abrigadoras para frío', 'Unisex', 'Adultos');
call sp_AgregarCategoria('Ropa Escolar', 'Uniformes y prendas escolares', 'Unisex', 'Infantil');
call sp_AgregarCategoria('Chaquetas', 'Prendas para el frío y estilo', 'Unisex', 'Juvenil');
call sp_AgregarCategoria('Ropa de Playa', 'Ropa ligera para el verano', 'Unisex', 'Adultos');
call sp_AgregarCategoria('Leggins', 'Ropa ajustada y cómoda', 'Mujer', 'Juvenil');
call sp_AgregarCategoria('Jeans', 'Pantalones de mezclilla', 'Unisex', 'Juvenil');
call sp_AgregarCategoria('Trajes', 'Ropa formal para eventos', 'Hombre', 'Adultos');
call sp_AgregarCategoria('Vestidos Casual', 'Vestidos para uso diario', 'Mujer', 'Adultos');
call sp_AgregarCategoria('Sudaderas', 'Ropa cómoda para clima fresco', 'Unisex', 'Juvenil');
call sp_AgregarCategoria('Conjuntos Infantiles', 'Ropa para niños pequeños', 'Unisex', 'Infantil');
call sp_AgregarCategoria('Camisas Polo', 'Camisas semiformales de algodón', 'Hombre', 'Juvenil');

-- Listar Categoria
Delimiter //
	Create procedure sp_ListarCategoria()
		Begin
			Select codigoCategoria, nombreCategoria, descripcionCategoria, genero, rangoEdad from Categorias;
        End //
Delimiter ;
call sp_ListarCategoria();

-- Eliminar Categoria
Delimiter //
	Create procedure sp_EliminarCategoria(
    in _codigoCategoria int)
		Begin
			set foreign_key_checks = 0;
				Delete from Categorias
					where codigoCategoria = _codigoCategoria;
				Select row_count() as filasEliminadas;
			set foreign_key_checks = 1;
        End//
Delimiter ;
call sp_EliminarCategoria(15);

-- Buscar Categoria
Delimiter //
	Create procedure sp_BuscarCategoria(
    in _codigoCategoria int)
		Begin
			Select codigoCategoria, nombreCategoria, descripcionCategoria, genero, rangoEdad from Categorias
				where codigoCategoria = _codigoCategoria;
        End //
Delimiter ;
call sp_BuscarCategoria(1);

-- Editar Categoria
Delimiter //
	Create procedure sp_EditarCategoria(
    in _codigoCategoria int,
    in _nombreCategoria varchar(100), 
    in _descripcionCategoria varchar(200), 
    in _genero enum('Hombre','Mujer','Unisex'), 
    in _rangoEdad enum('Infantil','Juvenil','Adultos'))
		Begin
			Update Categorias
				set nombreCategoria = _nombreCategoria,
                    descripcionCategoria = _descripcionCategoria,
                    genero = _genero,
                    rangoEdad = _rangoEdad
					where codigoCategoria = _codigoCategoria;
        End //
Delimiter ;
call sp_EditarCategoria(16, 'Trajes Ejecutivos', 'Ropa formal para oficina y eventos', 'Hombre', 'Adultos');
call sp_EditarCategoria(17, 'Vestidos de Verano', 'Vestidos frescos para clima cálido', 'Mujer', 'Adultos');
call sp_EditarCategoria(18, 'Sudaderas Estampadas', 'Prendas juveniles con diseño gráfico', 'Unisex', 'Juvenil');
call sp_EditarCategoria(19, 'Ropa Escolar Moderna', 'Conjuntos escolares actualizados', 'Unisex', 'Infantil');
call sp_EditarCategoria(20, 'Camisas Polo Juveniles', 'Polo juveniles para el día a día', 'Hombre', 'Juvenil');

-- --------------------------- Entidad Metodo Pago --------------------------- 
-- Agregar MetodoPago
Delimiter //
	Create procedure sp_AgregarMetodoPago(
    in tipoMetodoPago enum('Tarjeta','Efectivo'), 
    in entidadFinanciaera varchar(200), 
    in moneda varchar(150), 
    in porcentajeComision double(5,2))
		Begin
			Insert into MetodoPagos(tipoMetodoPago, entidadFinanciaera, moneda, porcentajeComision)
				Values(tipoMetodoPago, entidadFinanciaera, moneda, porcentajeComision);
        End //
Delimiter ;
call sp_AgregarMetodoPago('Tarjeta', 'Banco Industrial', 'Quetzal', 2.50);
call sp_AgregarMetodoPago('Efectivo', 'Ninguna', 'Quetzal', 0.00);
call sp_AgregarMetodoPago('Tarjeta', 'Banrural', 'Quetzal', 2.75);
call sp_AgregarMetodoPago('Tarjeta', 'BAC Credomatic', 'USD', 3.00);
call sp_AgregarMetodoPago('Tarjeta', 'Banco G&T Continental', 'Quetzal', 2.20);
call sp_AgregarMetodoPago('Efectivo', 'Ninguna', 'USD', 0.00);
call sp_AgregarMetodoPago('Tarjeta', 'Visa', 'Quetzal', 2.60);
call sp_AgregarMetodoPago('Tarjeta', 'Mastercard', 'USD', 3.25);
call sp_AgregarMetodoPago('Tarjeta', 'Banco Promérica', 'USD', 2.90);
call sp_AgregarMetodoPago('Tarjeta', 'Davivienda', 'Quetzal', 2.40);
call sp_AgregarMetodoPago('Efectivo', 'Ninguna', 'Euro', 0.00);
call sp_AgregarMetodoPago('Tarjeta', 'InterBanco', 'USD', 2.85);
call sp_AgregarMetodoPago('Tarjeta', 'American Express', 'USD', 3.50);
call sp_AgregarMetodoPago('Tarjeta', 'Banco Azteca', 'Quetzal', 2.00);
call sp_AgregarMetodoPago('Efectivo', 'Ninguna', 'Quetzal', 0.00);
call sp_AgregarMetodoPago('Tarjeta', 'Paypal', 'USD', 4.00);
call sp_AgregarMetodoPago('Tarjeta', 'Zelle', 'USD', 1.50);
call sp_AgregarMetodoPago('Tarjeta', 'Western Union', 'USD', 3.10);
call sp_AgregarMetodoPago('Efectivo', 'Ninguna', 'USD', 0.00);
call sp_AgregarMetodoPago('Tarjeta', 'Stripe', 'USD', 3.60);

-- Listar MetodoPago
Delimiter //
	Create procedure sp_ListarMetodoPago()
		Begin
			Select codigoMetodoPago, tipoMetodoPago, entidadFinanciaera, moneda, porcentajeComision from MetodoPagos;
        End //
Delimiter ;
call sp_ListarMetodoPago();

-- Eliminar MetodoPago
Delimiter //
	Create procedure sp_EliminarMetodoPago(
    in _codigoMetodoPago int)
		Begin
			set foreign_key_checks = 0;
				Delete from MetodoPagos
					where codigoMetodoPago= _codigoMetodoPago;
				Select row_count() as filasEliminadas;
			set foreign_key_checks = 1;
        End//
Delimiter ;
call sp_EliminarMetodoPago(15);

-- Buscar MetodoPago
Delimiter //
	Create procedure sp_BuscarMetodoPago(
    in _codigoMetodoPago int)
		Begin
			Select codigoMetodoPago, tipoMetodoPago, entidadFinanciaera, moneda, porcentajeComision from MetodoPagos
				where codigoMetodoPago= _codigoMetodoPago;
        End //
Delimiter ;
call sp_BuscarMetodoPago(1);

-- Editar MetodoPago
Delimiter //
	Create procedure sp_EditarMetodoPago(
    in _codigoMetodoPago int,
	in _tipoMetodoPago enum('Tarjeta','Efectivo'), 
    in _entidadFinanciaera varchar(200), 
    in _moneda varchar(150), 
    in _porcentajeComision double(5,2))
		Begin
			Update MetodoPagos
				set tipoMetodoPago = _tipoMetodoPago,
					entidadFinanciaera = _entidadFinanciaera,
                    moneda = _moneda,
                    porcentajeComision = _porcentajeComision
					where codigoMetodoPago= _codigoMetodoPago;
        End //
Delimiter ;
call sp_EditarMetodoPago(16, 'Tarjeta', 'TransferWise', 'USD', 2.80);
call sp_EditarMetodoPago(17, 'Tarjeta', 'Zelle Corp', 'USD', 1.25);
call sp_EditarMetodoPago(18, 'Tarjeta', 'WU Servicios', 'USD', 2.90);
call sp_EditarMetodoPago(19, 'Efectivo', 'Caja Local', 'Quetzal', 0.00);
call sp_EditarMetodoPago(20, 'Tarjeta', 'Stripe Global', 'USD', 3.75);




