-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.3.16-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para banco
CREATE DATABASE IF NOT EXISTS `banco` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banco`;

-- Volcando estructura para tabla banco.plazo
CREATE TABLE IF NOT EXISTS `plazo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `semanas` int(11) NOT NULL,
  `tasa_nor` double NOT NULL DEFAULT 0,
  `tasa_pun` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.plazo: ~1 rows (aproximadamente)
DELETE FROM `plazo`;
/*!40000 ALTER TABLE `plazo` DISABLE KEYS */;
INSERT INTO `plazo` (`id`, `semanas`, `tasa_nor`, `tasa_pun`) VALUES
	(13, 12, 1.0366, 0.8963);
/*!40000 ALTER TABLE `plazo` ENABLE KEYS */;

-- Volcando estructura para tabla banco.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `SKU` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.producto: ~4 rows (aproximadamente)
DELETE FROM `producto`;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`id`, `SKU`, `nombre`, `descripcion`, `precio`) VALUES
	(1, '3', 'televisor', 'televisor descripcion', 5000),
	(2, '4', 'motocicleta', 'italika', 15000),
	(3, '5', 'example', 'des example', 2),
	(18, '', 'MS', 'TEST MS', 9000);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Volcando estructura para procedimiento banco.DeletePlazo
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeletePlazo`(
	IN `inPlazo` INT,
	OUT `outId` INT
)
BEGIN
	DELETE
	FROM banco.plazo
	WHERE semanas = inPlazo;
	
	SET outId = (SELECT COUNT(id) FROM banco.plazo WHERE semanas = inPlazo);
END//
DELIMITER ;

-- Volcando estructura para procedimiento banco.DeleteProducto
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `DeleteProducto`(
	IN `inSKU` INT,
	OUT `outId` INT
)
BEGIN
	DELETE
	FROM banco.producto
	WHERE SKU = inSKU;
	
	
	SET outId = (SELECT COUNT(id) FROM banco.producto WHERE SKU = inSKU);
END//
DELIMITER ;

-- Volcando estructura para procedimiento banco.GetPlazo
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetPlazo`(
	IN `inSemanas` INT
)
BEGIN
	SELECT
		pla.id "Id",
		pla.semanas "Plazo_Semanal",
		pla.tasa_nor "Tasa_Normal",
		pla.tasa_pun "Tasa_Puntual"
	FROM banco.plazo pla
	WHERE (pla.semanas = inSemanas AND inSemanas > 0)
		OR	(pla.semanas > 0 AND inSemanas = 0);
END//
DELIMITER ;

-- Volcando estructura para procedimiento banco.GetProducto
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetProducto`(
	IN `inSKU` VARCHAR(50),
	IN `inNombre` VARCHAR(50)
)
    COMMENT 'Obtener Producto por SKU o Nombre'
BEGIN

	SELECT
		pro.id,
		pro.SKU "SKU",
		pro.nombre "Nombre",
		pro.descripcion "Descripcion",
		pro.precio "Precio"
	FROM banco.producto pro
	WHERE
		pro.SKU = inSKU OR
		pro.nombre = inNombre;

END//
DELIMITER ;

-- Volcando estructura para procedimiento banco.SetPlazo
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `SetPlazo`(
	IN `inPlazo` INT,
	IN `inTasaNor` DOUBLE,
	IN `inTasaPun` DOUBLE,
	OUT `outId` INT
)
BEGIN
	INSERT INTO banco.plazo
	(
		semanas,
		tasa_nor,
		tasa_pun
	)
	VALUES
	(
		inPlazo,
		inTasaNor,
		inTasaPun
	);
	
	SET outId = LAST_INSERT_ID();
END//
DELIMITER ;

-- Volcando estructura para procedimiento banco.SetProducto
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `SetProducto`(
	IN `inSKU` VARCHAR(50),
	IN `inNombre` VARCHAR(50),
	IN `inDescripcion` VARCHAR(50),
	IN `inPrecio` INT,
	OUT `outId` INT
)
BEGIN
	INSERT INTO banco.producto 
	(
		SKU,
		nombre,
		descripcion,
		precio
	)
	VALUES
	(
		inSKU,
		inNombre,
		inDescripcion,
		inPrecio
	);
	
	SET outId = LAST_INSERT_ID();
END//
DELIMITER ;

-- Volcando estructura para procedimiento banco.UpdatePlazo
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdatePlazo`(
	IN `inPlazo` INT,
	IN `inTasaNor` DOUBLE,
	IN `inTasaPun` DOUBLE,
	OUT `outId` INT
)
BEGIN

	SET @Id := 0;
	UPDATE banco.plazo p
	SET
		semanas=inPlazo,
		tasa_nor=inTasaNor,
		tasa_pun=inTasaPun,
		id = (SELECT @Id := id)
	WHERE semanas = inPlazo;

	SET outId = @Id;
END//
DELIMITER ;

-- Volcando estructura para procedimiento banco.UpdateProducto
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `UpdateProducto`(
	IN `inSKU` VARCHAR(50),
	IN `inNombre` VARCHAR(50),
	IN `inDescripcion` VARCHAR(50),
	IN `inPrecio` INT,
	OUT `outId` INT
)
BEGIN
	SET @Id := 0;
	UPDATE banco.producto
	SET
		SKU=inSKU,
		nombre=inNombre,
		descripcion=inDescripcion,
		precio=inPrecio,
		id = (SELECT @Id := id)
	WHERE SKU = inSKU;
	
	SET outId = @Id;
	
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
