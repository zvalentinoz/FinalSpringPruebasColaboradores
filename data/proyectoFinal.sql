-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-12-2024 a las 12:18:26
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

-- Base de datos: `bdproyecto`
--
CREATE DATABASE  proyectoFinal;

-- borrar si existe 
DROP DATABASE IF EXISTS proyectoFinal;

-- usar bd
use  proyectoFinal;
-- --------------------------------------------------------

-- tipos_usuarios
CREATE TABLE `tipos_usuarios`(  -- añadido
`id_tipo` INT NOT NULL PRIMARY KEY ,
`descripcion` varchar(20)
);
INSERT INTO tipos_usuarios(id_tipo , descripcion) VALUES(1,'Administrador') , (2,'Cliente');

-- tabla usuarios
CREATE TABLE `tbusuario` (  -- añadido
  `codigo_usuario` int  PRIMARY KEY auto_increment,
  `nombre_usuario` varchar(50)  NOT NULL,
  `apellido_usuario` varchar(50) NOT NULL ,
  `user_usuario` varchar(50) NOT NULL UNIQUE,
  `contrasena_usuario` varchar(100) NOT NULL,
  `id_tipo` INT NOT NULL DEFAULT 2 CHECK(id_tipo IN (1,2) ) , 
  `estado` BIT NOT NULL DEFAULT 1 CHECK(estado IN (0,1) ) ,
  FOREIGN KEY (`id_tipo`) references `tipos_usuarios`(`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `tbusuario` (`nombre_usuario`, `apellido_usuario`, `user_usuario`,`contrasena_usuario` ,  `id_tipo` , `estado` ) 
VALUES ('Juan', 'Pérez', 'jperez', '123456', 1, 1),
 ('María', 'Gómez', 'mgomez', 'clave789', 2, 1),
 ('Carlos', 'Ruiz', 'cruiz', 'pass321', 1, 0);


--
-- Estructura de tabla para la tabla `colegios`
--

CREATE TABLE `colegios` ( -- añadido
  `id_colegio` int PRIMARY KEY NOT NULL,
  `nombre_colegio` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `colegios`
--

INSERT INTO `colegios` (`id_colegio`, `nombre_colegio`) VALUES
(1, 'Newton'),
(2, 'San Francisco'),
(3, 'Inka Gakuen'),
(4, 'Saco Oliveros'),
(5, 'Nazca');

-- table tallas

CREATE TABLE `tallas` ( -- añadido
  `id_talla`  int PRIMARY KEY NOT NULL,
  `talla` varchar(10) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tallas`
--

INSERT INTO `tallas` (`id_talla`, `talla`) VALUES
(1, 'S'),
(2, 'M'),
(3, 'L'),
(4, 'XL');

-- --------------------------------------------------------
-- table proveedor

CREATE TABLE  `proveedor`( -- añadido
 `id_proveedor` int PRIMARY KEY ,
 `nombre_proveedor` VARCHAR(50) NOT NULL,
 `telefono` VARCHAR(12) NOT NULL
);

INSERT INTO proveedor (`id_proveedor`,`nombre_proveedor`, `telefono`)
VALUES
(1,'Distribuidora Escolar Andina', '987654321'),
(2,'Papelería Educa S.A.C.', '945623789'),
(3,'Uniformes Escolares Perú', '956781234'),
(4,'Suministros Didácticos S.R.L.', '912345678'),
(5,'Tecnología Educativa Integral', '998877665'),
(6,'Librería Escolar San Marcos', '934567890');


-- Estructura de tabla para la tabla `productos`
--
CREATE TABLE `ropa` (
  `id_ropa` int auto_increment PRIMARY KEY  NOT NULL,
  `nombre_ropa`  varchar(100) NOT NULL,
  `img_ropa` varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--
INSERT INTO `ropa` (`id_ropa`, `nombre_ropa` , `img_ropa`)
VALUES
(1,'Polo escolar manga corta (varón)', 'polo_negro.jpg'),
(2,'Polo escolar manga corta (mujer)', 'polo_negro.jpg'),
(3,'Pantalón escolar negro', 'pantalon_negro.jpg'),
(4,'Falda escolar tabla encontrada', 'falda_negra.jpg'),
(5,'Buzo escolar completo (varón)', 'buzo_negro.jpg'),
(6,'Buzo escolar completo (mujer)', 'buzo_negro.jpg'),
(7,'Camisa escolar blanca manga larga', 'camisa_negra.jpg'),
(8,'Chompa escolar tejida azul', 'chompa_azul.jpg'),
(9,'chaqueta escolar ', 'chaqueta_escolar.jpg'),
(10,'Corbata escolar', 'corbata_escolar.jpg'),
(11,'Medias escolares blancas x2', 'media_escolar.jpg'),
(12,'Short deportivo escolar', 'short_escolar.jpg'),
(13,'Zapatos escolares negros', 'zapato_escolar.jpg');
select * from ropa;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `compra` (
  `id_compra` CHAR(6) PRIMARY KEY NOT NULL,     
  `fecha_Registro` Date,					
  `id_ropa` int NOT NULL, 
  `id_proveedor` INT NOT NULL,
  `id_talla` int NOT NULL, 			
  `id_colegio` int NOT NULL, 			
  `cantidad` TINYINT NOT NULL,				
  `precio_unitario` decimal(10,2) NOT NULL,  
  `precio_total` decimal(10,2) GENERATED ALWAYS AS (`cantidad` * `precio_unitario`) STORED, -- mostrar
  `estado` BIT DEFAULT 1 CHECK(`estado` IN(0 , 1) ) ,	  -- mostrar
   FOREIGN KEY (`id_ropa`)  REFERENCES `ropa` (`id_ropa`) ,
   FOREIGN KEY (`id_proveedor`)  REFERENCES `proveedor` (`id_proveedor`) ,
   FOREIGN KEY (`id_talla`) REFERENCES `tallas`(`id_talla`) ,
   FOREIGN KEY ( `id_colegio`) REFERENCES `colegios` (`id_colegio`)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Volcado de datos para la tabla `transacciones`
--

INSERT INTO `compra` (`id_compra`,`fecha_Registro`,
 `id_ropa`,`id_proveedor`,`id_talla`,`id_colegio`,`cantidad`,`precio_unitario`, `estado`) VALUES
('C00001' ,curdate(),1  , 1, 1, 1, 2, 90.00,  0),
('C00002' ,curdate(),3 , 3, 1, 1, 5, 30.00, 1),
('C00003', CURDATE(), 2, 2, 2, 3, 4, 28.00, 1),
('C00004', CURDATE(), 4, 3, 2, 2, 3, 35.00, 1),
('C00005', CURDATE(), 5, 1, 3, 4, 2, 65.00, 1),
('C00006', CURDATE(), 6, 1, 2, 5, 1, 63.50, 1),
('C00007', CURDATE(), 7, 4, 3, 1, 2, 30.00, 1),
('C00008', CURDATE(), 8, 5, 4, 2, 2, 47.50, 0),
('C00009', CURDATE(), 9, 6, 3, 3, 3, 70.00, 1),
('C00010', CURDATE(), 10, 2, 1, 1, 6, 10.00, 1),
('C00011', CURDATE(), 11, 3, 1, 5, 8, 7.50, 1),
('C00012', CURDATE(), 12, 4, 2, 4, 2, 24.00, 1),
('C00013', CURDATE(), 13, 5, 3, 3, 3, 85.00, 0),
('C00014', CURDATE(), 1, 6, 1, 1, 2, 26.00, 1),
('C00015', CURDATE(), 3, 1, 2, 2, 4, 33.00, 1);
SELECT * FROM compra;
--

-- Índices para tablas volcadas

create table boleta( 
numero_boleta int PRIMARY KEY auto_increment,
fecha_registro datetime NOT NULL default current_timestamp,
codigo_usuario int NOT NULL,
foreign key (codigo_usuario) REFERENCES tbusuario(codigo_usuario)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


create table detalle_boleta( 
numero_boleta int NOT NULL,
id_compra CHAR(6) NOT NULL,
cantidad TINYINT NOT NULL,
precio_unitario DECIMAL(8,2) NOT NULL,
PRIMARY KEY(numero_boleta , id_compra),
constraint fk_boleta_numero FOREIGN KEY (numero_boleta) REFERENCES boleta(numero_boleta),
constraint fk_compra_boleta FOREIGN KEY (id_compra) REFERENCES `compra`(id_compra)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

drop table detalle_boleta
use proyectoFinal;

-- ver tablas
SELECT * FROM colegios;
SELECT * FROM ropa;
SELECT * FROM tallas;
SELECT * FROM tbusuario;
SELECT * FROM compra;
SELECT * FROM tipos_usuarios;
SELECT user, host FROM mysql.user;
SELECT user, host, plugin FROM mysql.user;

select *  from tbUsuario  WHERE user_usuario = "jperez" AND clave_usuario = 123456

