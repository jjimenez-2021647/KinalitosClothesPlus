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




