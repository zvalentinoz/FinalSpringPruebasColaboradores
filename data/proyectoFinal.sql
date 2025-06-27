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

--
-- Estructura de tabla para la tabla `colegios`
--

CREATE TABLE `colegios` (
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
(4, 'Saco Oliveros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` int PRIMARY KEY auto_increment NOT NULL,
  `nombre_producto`  varchar(100) NOT NULL,
  `img_producto` varchar(255) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` ( `nombre_producto` ,`img_producto` ) VALUES
( 'Polo' ,'polo_negro.jpg'  ),
( 'Casaca' , 'casaca_negro.jpg' ),
( 'Pantalón' ,'pantalon_negro.jpg'  ),
('Camisa' ,'camisa_negra.jpg'  );

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tallas`
--

CREATE TABLE `tallas` (
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

-- tipos_usuarios
CREATE TABLE `tipos_usuarios`(
`id_tipo` INT PRIMARY KEY ,
`descripcion` varchar(20)
);

INSERT INTO tipos_usuarios(id_tipo , descripcion) VALUES(1,'Administrador') , (2,'Cliente');

-- Estructura de tabla para la tabla `tbusuario`
--

CREATE TABLE `tbusuario` (
  `codigo` int  PRIMARY KEY auto_increment,
  `nombre` varchar(50) DEFAULT NULL UNIQUE,
  `clave` varchar(100) DEFAULT NULL,
  `id_tipo` INT DEFAULT 2, 
  `estado` BIT DEFAULT 1 CHECK(estado IN (0,1)),
  FOREIGN KEY (`id_tipo`) references `tipos_usuarios`(`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tbusuario`
--

INSERT INTO `tbusuario` ( `nombre`, `clave`,`id_tipo`,`estado`) VALUES
( 'ADMINISTRADOR', 'adminPass' , 1 , 1),
( 'CLIENTE', 1234 , 2 , 1);
INSERT INTO `tbusuario` ( `nombre`, `clave`,`id_tipo`,`estado`) VALUES
( 'Tino', 2345 , 2 , 1);
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `transacciones` (
  `id_transaccion` int PRIMARY KEY auto_increment,     -- mostrar
  `codigo_cliente` int NOT NULL,      -- mostrar
  `nombre_cliente` varchar(100) NOT NULL,
  `apellido_cliente` varchar(100) NOT NULL,
  `fecha_Registro` Date,					-- mostrar 
  `id_producto` int NOT NULL,			-- mostrar
  `id_talla` int NOT NULL, 				-- mostrar
  `id_colegio` int NOT NULL, 			-- mostrar
  `cantidad` int NOT NULL,				-- mostrar
  `precio_unitario` decimal(10,2) NOT NULL,  -- mostrar
  `precio_total` decimal(10,2) GENERATED ALWAYS AS (`cantidad` * `precio_unitario`) STORED, -- mostrar
  `estado` BIT DEFAULT 1 CHECK(`estado` IN(0 , 1) ) ,	  -- mostrar
   FOREIGN KEY (`id_producto`)  REFERENCES `productos` (`id_producto`) ,
   FOREIGN KEY (`id_talla`) REFERENCES `tallas`(`id_talla`) ,
   FOREIGN KEY ( `id_colegio`) REFERENCES `colegios` (`id_colegio`)
    
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



--
-- Volcado de datos para la tabla `transacciones`
--

INSERT INTO `transacciones` ( `codigo_cliente`, `nombre_cliente`,
 `apellido_cliente` , `fecha_Registro` , `id_producto`  , `id_talla`, `id_colegio`, `cantidad`, `precio_unitario`, `estado`) VALUES
( 1, 'Angelo', 'Rey',curdate() , 1, 1, 1, 2, 90.00,  0),
( 2, 'Miguel', 'Rey',curdate(), 1, 1, 1, 7, 30.00, 1);

--

-- Índices para tablas volcadas
-


-- ver tablas
SELECT * FROM colegios;
SELECT * FROM productos;
SELECT * FROM tallas;
SELECT * FROM tbUsuario;
SELECT * FROM transacciones;
SELECT * FROM tipos_usuarios;
SELECT user, host FROM mysql.user;
SELECT user, host, plugin FROM mysql.user;
