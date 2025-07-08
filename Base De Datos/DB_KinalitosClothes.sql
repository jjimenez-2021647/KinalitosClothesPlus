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

-- Metodo Pagos
Create table MetodoPagos(
	codigoMetodoPago int auto_increment,
    tipoMetodoPago enum('Tarjeta', 'Efectivo') not null,
    entidadFinanciaera varchar(200) not null,
    moneda varchar(150) not null,
	porcentajeComision double(5,2) not null,
    primary key PK_codigoMetodoPago (codigoMetodoPago)
);

-- --------------------------- Procedimientos almacenados ---------------------------


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




