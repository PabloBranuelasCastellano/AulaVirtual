-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: aula_virtual
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnos` (
  `AlumnoId` int NOT NULL AUTO_INCREMENT,
  `CentroEducativoId` int NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `PrimerApellido` varchar(100) NOT NULL,
  `SegundoApellido` varchar(100) NOT NULL,
  `NIF_NIE` varchar(10) NOT NULL,
  `FechaAlta` date NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Usuario` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `EsActivo` tinyint(1) NOT NULL,
  PRIMARY KEY (`AlumnoId`),
  KEY `CentroEducativoId` (`CentroEducativoId`),
  CONSTRAINT `alumnos_ibfk_1` FOREIGN KEY (`CentroEducativoId`) REFERENCES `centroseducativos` (`CentroEducativoId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='En esta tabla se registran los datos de los alumnos y\r\n	 al centro educativo que pertenecen';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (1,1,'Paco','Peluca','Peluca','135664W','1940-12-20','Paco.Peluca@gamil.com','Paco Peluca Peluca','6d30a43d109544eaec2283e4d5aedf44',1);
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `centroseducativos`
--

DROP TABLE IF EXISTS `centroseducativos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `centroseducativos` (
  `CentroEducativoId` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Administrador` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  PRIMARY KEY (`CentroEducativoId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='En esta tabla de indican los centros Educativos,el Id del centro,\r\n	 el nombre del Administrador y la clave de acceso';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `centroseducativos`
--

LOCK TABLES `centroseducativos` WRITE;
/*!40000 ALTER TABLE `centroseducativos` DISABLE KEYS */;
INSERT INTO `centroseducativos` VALUES (1,'IES GRAN CAPITAN','Pepe','12585');
/*!40000 ALTER TABLE `centroseducativos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionarios`
--

DROP TABLE IF EXISTS `cuestionarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuestionarios` (
  `CuestionarioId` int NOT NULL AUTO_INCREMENT,
  `ProfesorId` int NOT NULL,
  `Titulo` varchar(100) NOT NULL,
  `Instrucciones` text NOT NULL,
  `Resumen` text NOT NULL,
  `NumeroPreguntasPorTest` int NOT NULL,
  `PuntosAcierto` int NOT NULL,
  `PuntosError` int NOT NULL,
  `FechaCreacion` date NOT NULL,
  `EsActivo` tinyint(1) NOT NULL,
  PRIMARY KEY (`CuestionarioId`),
  KEY `ProfesorId` (`ProfesorId`),
  CONSTRAINT `cuestionarios_ibfk_1` FOREIGN KEY (`ProfesorId`) REFERENCES `profesores` (`ProfesorId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene las intrucciones ,el numero de preguntas,\r\n	el numero de aciertos y fallos y cuando se creo';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionarios`
--

LOCK TABLES `cuestionarios` WRITE;
/*!40000 ALTER TABLE `cuestionarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuestionarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuestionariosgrupos`
--

DROP TABLE IF EXISTS `cuestionariosgrupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuestionariosgrupos` (
  `CuestionarioGrupoId` int NOT NULL AUTO_INCREMENT,
  `CuestionarioId` int NOT NULL,
  `GrupoId` int NOT NULL,
  `FechaInicio` date NOT NULL,
  `FechaFin` date NOT NULL,
  PRIMARY KEY (`CuestionarioGrupoId`),
  KEY `CuestionarioId` (`CuestionarioId`),
  KEY `GrupoId` (`GrupoId`),
  CONSTRAINT `cuestionariosgrupos_ibfk_1` FOREIGN KEY (`CuestionarioId`) REFERENCES `cuestionarios` (`CuestionarioId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cuestionariosgrupos_ibfk_2` FOREIGN KEY (`GrupoId`) REFERENCES `grupos` (`GrupoId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene el identificador del cuestioanario,\r\n	que grupo lo ha realizado ,cuando se inició y cuando se termino';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuestionariosgrupos`
--

LOCK TABLES `cuestionariosgrupos` WRITE;
/*!40000 ALTER TABLE `cuestionariosgrupos` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuestionariosgrupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursosacademicos`
--

DROP TABLE IF EXISTS `cursosacademicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursosacademicos` (
  `CursoAcademicoId` int NOT NULL AUTO_INCREMENT,
  `CentroEducativoId` int NOT NULL,
  `Denominacion` varchar(100) NOT NULL,
  `EsActivo` tinyint(1) NOT NULL,
  `FechaInicio` date NOT NULL,
  `FechaFin` date NOT NULL,
  PRIMARY KEY (`CursoAcademicoId`),
  KEY `CentroEducativoId` (`CentroEducativoId`),
  CONSTRAINT `cursosacademicos_ibfk_1` FOREIGN KEY (`CentroEducativoId`) REFERENCES `centroseducativos` (`CentroEducativoId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='En esta tabla se registran los distintos cursos academicos\r\n	 que tiene cada centro con fecha de inicio y fecha de fin';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursosacademicos`
--

LOCK TABLES `cursosacademicos` WRITE;
/*!40000 ALTER TABLE `cursosacademicos` DISABLE KEYS */;
INSERT INTO `cursosacademicos` VALUES (1,1,'2019/2020',1,'2019-09-15','2020-06-15'),(2,1,'2015/2016',0,'2015-09-15','2016-06-15');
/*!40000 ALTER TABLE `cursosacademicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `GrupoId` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `CursoAcademicoId` int NOT NULL,
  `MateriaId` int NOT NULL,
  `NivelId` int NOT NULL,
  `ProfesorId` int NOT NULL,
  PRIMARY KEY (`GrupoId`),
  KEY `CursoAcademicoId` (`CursoAcademicoId`),
  KEY `MateriaId` (`MateriaId`),
  KEY `NivelId` (`NivelId`),
  KEY `ProfesorId` (`ProfesorId`),
  CONSTRAINT `grupos_ibfk_1` FOREIGN KEY (`CursoAcademicoId`) REFERENCES `cursosacademicos` (`CursoAcademicoId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `grupos_ibfk_2` FOREIGN KEY (`MateriaId`) REFERENCES `materias` (`MateriaId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `grupos_ibfk_3` FOREIGN KEY (`NivelId`) REFERENCES `niveles` (`NivelId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `grupos_ibfk_4` FOREIGN KEY (`ProfesorId`) REFERENCES `profesores` (`ProfesorId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene el nombre del grupo ,el curso academico,\r\n	la materia,el nivel y el profesor que la imparte';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES (1,'1ºH',1,1,1,1),(2,'1ºA',2,1,1,1),(3,'1ºC',2,2,1,1);
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gruposalumnos`
--

DROP TABLE IF EXISTS `gruposalumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gruposalumnos` (
  `GrupoAlumnoId` int NOT NULL AUTO_INCREMENT,
  `GrupoId` int NOT NULL,
  `AlumnoId` int NOT NULL,
  PRIMARY KEY (`GrupoAlumnoId`),
  KEY `GrupoId` (`GrupoId`),
  KEY `AlumnoId` (`AlumnoId`),
  CONSTRAINT `gruposalumnos_ibfk_1` FOREIGN KEY (`GrupoId`) REFERENCES `grupos` (`GrupoId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `gruposalumnos_ibfk_2` FOREIGN KEY (`AlumnoId`) REFERENCES `alumnos` (`AlumnoId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene los codigos de los alumnos y del grupo';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gruposalumnos`
--

LOCK TABLES `gruposalumnos` WRITE;
/*!40000 ALTER TABLE `gruposalumnos` DISABLE KEYS */;
INSERT INTO `gruposalumnos` VALUES (1,1,1),(2,3,1);
/*!40000 ALTER TABLE `gruposalumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intentos`
--

DROP TABLE IF EXISTS `intentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `intentos` (
  `IntentoId` int NOT NULL AUTO_INCREMENT,
  `AlumnoId` int NOT NULL,
  `CuestionarioId` int NOT NULL,
  `FechaComienzo` date NOT NULL,
  `FechaFin` date NOT NULL,
  `TiempoTotal` int NOT NULL,
  `Puntuacion` int NOT NULL,
  `Finalizado` tinyint(1) NOT NULL,
  PRIMARY KEY (`IntentoId`),
  KEY `AlumnoId` (`AlumnoId`),
  KEY `CuestionarioId` (`CuestionarioId`),
  CONSTRAINT `intentos_ibfk_1` FOREIGN KEY (`AlumnoId`) REFERENCES `alumnos` (`AlumnoId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `intentos_ibfk_2` FOREIGN KEY (`CuestionarioId`) REFERENCES `cuestionarios` (`CuestionarioId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene el numero de veces que los alumnos han intentado los \r\n	cuestionarios,cuando han empezado ,cuando lo han terminado ,los puntos obtenidos \r\n	y el tiempo que han tardado';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intentos`
--

LOCK TABLES `intentos` WRITE;
/*!40000 ALTER TABLE `intentos` DISABLE KEYS */;
/*!40000 ALTER TABLE `intentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materias`
--

DROP TABLE IF EXISTS `materias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materias` (
  `MateriaId` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `CentroEducativoId` int NOT NULL,
  `EsActiva` tinyint(1) NOT NULL,
  PRIMARY KEY (`MateriaId`),
  KEY `CentroEducativoId` (`CentroEducativoId`),
  CONSTRAINT `materias_ibfk_1` FOREIGN KEY (`CentroEducativoId`) REFERENCES `centroseducativos` (`CentroEducativoId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Esta tabla contendrá las materias que impartirá cada centro';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materias`
--

LOCK TABLES `materias` WRITE;
/*!40000 ALTER TABLE `materias` DISABLE KEYS */;
INSERT INTO `materias` VALUES (1,'Historia del Mundo Contemporaneo',1,1),(2,'Historia del Arte',1,1),(3,'Economia de la Empresa',1,1);
/*!40000 ALTER TABLE `materias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveles`
--

DROP TABLE IF EXISTS `niveles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `niveles` (
  `NivelId` int NOT NULL AUTO_INCREMENT,
  `CentroEducativoId` int NOT NULL,
  `Denominacion` varchar(100) NOT NULL,
  `EsActivo` tinyint(1) NOT NULL,
  PRIMARY KEY (`NivelId`),
  KEY `CentroEducativoId` (`CentroEducativoId`),
  CONSTRAINT `niveles_ibfk_1` FOREIGN KEY (`CentroEducativoId`) REFERENCES `centroseducativos` (`CentroEducativoId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene los diferentes niveles de estudios';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveles`
--

LOCK TABLES `niveles` WRITE;
/*!40000 ALTER TABLE `niveles` DISABLE KEYS */;
INSERT INTO `niveles` VALUES (1,1,'1ºESO',1),(2,1,'4ºESO',1);
/*!40000 ALTER TABLE `niveles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opciones`
--

DROP TABLE IF EXISTS `opciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opciones` (
  `OpcionId` int NOT NULL AUTO_INCREMENT,
  `PreguntaId` int NOT NULL,
  `Texto` text NOT NULL,
  `EsCorrecta` tinyint(1) NOT NULL,
  `FechaCreacion` date NOT NULL,
  `Orden` int NOT NULL,
  `EsActivo` tinyint(1) NOT NULL,
  PRIMARY KEY (`OpcionId`),
  KEY `PreguntaId` (`PreguntaId`),
  CONSTRAINT `opciones_ibfk_1` FOREIGN KEY (`PreguntaId`) REFERENCES `preguntas` (`PreguntaId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene las opciones de las distintas preguntas ,\r\n	cuando se crearon ,en que orden se muestran y\r\n	si es correcta o no';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opciones`
--

LOCK TABLES `opciones` WRITE;
/*!40000 ALTER TABLE `opciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `opciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opcionespuntos`
--

DROP TABLE IF EXISTS `opcionespuntos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opcionespuntos` (
  `OpcionPuntoId` int NOT NULL AUTO_INCREMENT,
  `OpcionId` int NOT NULL,
  `PuntoId` int NOT NULL,
  PRIMARY KEY (`OpcionPuntoId`),
  KEY `OpcionId` (`OpcionId`),
  KEY `PuntoId` (`PuntoId`),
  CONSTRAINT `opcionespuntos_ibfk_1` FOREIGN KEY (`OpcionId`) REFERENCES `opciones` (`OpcionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `opcionespuntos_ibfk_2` FOREIGN KEY (`PuntoId`) REFERENCES `puntos` (`PuntoId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Esta tabla guarda la respuesta elegida\r\n	 y a que punto corresponde';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opcionespuntos`
--

LOCK TABLES `opcionespuntos` WRITE;
/*!40000 ALTER TABLE `opcionespuntos` DISABLE KEYS */;
/*!40000 ALTER TABLE `opcionespuntos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preguntas`
--

DROP TABLE IF EXISTS `preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preguntas` (
  `PreguntaId` int NOT NULL AUTO_INCREMENT,
  `CuestionarioId` int NOT NULL,
  `Texto` text NOT NULL,
  `FechaCreacion` date NOT NULL,
  `Orden` int NOT NULL,
  `EsActivo` tinyint(1) NOT NULL,
  PRIMARY KEY (`PreguntaId`),
  KEY `CuestionarioId` (`CuestionarioId`),
  CONSTRAINT `preguntas_ibfk_1` FOREIGN KEY (`CuestionarioId`) REFERENCES `cuestionarios` (`CuestionarioId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene las preguntas,a  que formulario pertenecen,\r\n	el orden en que se muestran y cuando se crearon';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preguntas`
--

LOCK TABLES `preguntas` WRITE;
/*!40000 ALTER TABLE `preguntas` DISABLE KEYS */;
/*!40000 ALTER TABLE `preguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesores`
--

DROP TABLE IF EXISTS `profesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesores` (
  `ProfesorId` int NOT NULL AUTO_INCREMENT,
  `CentroEducativoId` int NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `PrimerApellido` varchar(100) NOT NULL,
  `SegundoApellido` varchar(100) NOT NULL,
  `NIF_NIE` varchar(10) NOT NULL,
  `FechaAlta` date NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Usuario` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `EsActivo` tinyint(1) NOT NULL,
  PRIMARY KEY (`ProfesorId`),
  KEY `CentroEducativoId` (`CentroEducativoId`),
  CONSTRAINT `profesores_ibfk_1` FOREIGN KEY (`CentroEducativoId`) REFERENCES `centroseducativos` (`CentroEducativoId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='En esta tabla se registran los datos de los profesores y\r\n	 a que centro pertenecen';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesores`
--

LOCK TABLES `profesores` WRITE;
/*!40000 ALTER TABLE `profesores` DISABLE KEYS */;
INSERT INTO `profesores` VALUES (1,1,'Miguel','Herguedas','Heredias','15428562S','1960-02-15','MiguelHistoria@gmail.com','Miguel Herguedas Heredias','fe2c270b68bc91b8c6d4abd5cb3c0755',1),(2,1,'Javier','Ortega ','Cano','453531Q','2008-03-30','JavierOrtega@gmail.com','Javier Ortega Cano','1c8d5439478c6d9124f0516c4c9c5c29',1);
/*!40000 ALTER TABLE `profesores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puntos`
--

DROP TABLE IF EXISTS `puntos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puntos` (
  `PuntoId` int NOT NULL AUTO_INCREMENT,
  `TemaId` int NOT NULL,
  `Titulo` varchar(100) NOT NULL,
  `Resumen` text NOT NULL,
  `Texto` text NOT NULL,
  `Orden` int NOT NULL,
  `Numero` int NOT NULL,
  `EsActivo` tinyint(1) NOT NULL,
  PRIMARY KEY (`PuntoId`),
  KEY `TemaId` (`TemaId`),
  CONSTRAINT `puntos_ibfk_1` FOREIGN KEY (`TemaId`) REFERENCES `temas` (`TemaId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene los apartados que tiene cada tema ,\r\n	numero y orden en que se va a presentar';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puntos`
--

LOCK TABLES `puntos` WRITE;
/*!40000 ALTER TABLE `puntos` DISABLE KEYS */;
INSERT INTO `puntos` VALUES (1,2,'Causas de la Revolución Francesa','Principales Causas que provocaron la caida del Antiguo Régimen y del fin del Absolutismo.','Las principales causas de la Revolución francesa fueron las siguientes:\r\n\r\n1)Las arbitrariedades de un absolutismo monárquico que oprimía a la mayoría de sus súbditos.\r\n\r\n2)Una gran desigualdad social debido a las fuertes cargas (impuestos, tributos y diezmo) que recaían sobre los campesinos franceses, quienes con su trabajo debían mantenerse a sí mismos y a los grupos privilegiados: la nobleza y el clero.\r\n\r\n3)El descontento de sectores intelectuales por la falta de derechos y libertades. Estos intelectuales estaban muy influidos por las ideas de la Ilustración.\r\n\r\n4)La crisis económica y financiera en la que se encontraba Francia. Los excesos de gastos de la Corona y los gastos provenientes de la participación en la guerra por la Independencia de Estados Unidos habían provocado un déficit presupuestario.\r\n\r\n5)Una serie de malas cosechas que provocaron aumentos desmedidos del precio del pan, que era el principal alimento de los sectores populares.\r\n\r\n6)Las aspiraciones de una burguesía en ascenso que deseaba que su posición económica se correspondiera con su situación social y sus derechos políticos.',1,1,1);
/*!40000 ALTER TABLE `puntos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuestasalumno`
--

DROP TABLE IF EXISTS `respuestasalumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respuestasalumno` (
  `RespuestaAlumnoId` int NOT NULL AUTO_INCREMENT,
  `IntentoId` int NOT NULL,
  `PreguntaId` int NOT NULL,
  `OpcionId` int NOT NULL,
  `Tiempo` int NOT NULL,
  PRIMARY KEY (`RespuestaAlumnoId`),
  KEY `IntentoId` (`IntentoId`),
  KEY `PreguntaId` (`PreguntaId`),
  KEY `OpcionId` (`OpcionId`),
  CONSTRAINT `respuestasalumno_ibfk_1` FOREIGN KEY (`IntentoId`) REFERENCES `intentos` (`IntentoId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `respuestasalumno_ibfk_2` FOREIGN KEY (`PreguntaId`) REFERENCES `preguntas` (`PreguntaId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `respuestasalumno_ibfk_3` FOREIGN KEY (`OpcionId`) REFERENCES `opciones` (`OpcionId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Esta tabla guarda los intentos ,las preguntas ,las respuestas\r\n	y el tiempo que ha tardado en hacerlas el alumno ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestasalumno`
--

LOCK TABLES `respuestasalumno` WRITE;
/*!40000 ALTER TABLE `respuestasalumno` DISABLE KEYS */;
/*!40000 ALTER TABLE `respuestasalumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temas`
--

DROP TABLE IF EXISTS `temas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temas` (
  `TemaId` int NOT NULL AUTO_INCREMENT,
  `Titulo` varchar(100) NOT NULL,
  `Resumen` text NOT NULL,
  `ProfesorId` int NOT NULL,
  `MateriaId` int NOT NULL,
  `NivelId` int NOT NULL,
  `FechaCreacion` date NOT NULL,
  `EsActivo` tinyint(1) NOT NULL,
  `Numero` int NOT NULL,
  `Orden` int NOT NULL,
  PRIMARY KEY (`TemaId`),
  KEY `ProfesorId` (`ProfesorId`),
  KEY `MateriaId` (`MateriaId`),
  KEY `NivelId` (`NivelId`),
  CONSTRAINT `temas_ibfk_1` FOREIGN KEY (`ProfesorId`) REFERENCES `profesores` (`ProfesorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `temas_ibfk_2` FOREIGN KEY (`MateriaId`) REFERENCES `materias` (`MateriaId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `temas_ibfk_3` FOREIGN KEY (`NivelId`) REFERENCES `niveles` (`NivelId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Esta tabla contiene el nombre,resumen numero de tema ,\r\n	la materia a la que corresponde,el profesor que la imparte ,\r\n	el nivel,cuando se creo y el orden el que se tiene que mostrar\r\n	cada tema';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temas`
--

LOCK TABLES `temas` WRITE;
/*!40000 ALTER TABLE `temas` DISABLE KEYS */;
INSERT INTO `temas` VALUES (1,'La Revolución Industrial','Los cambios que transformaron la forma de vivir y producir en Inglaterra a mediados del siglo XIX',1,1,1,'2020-02-18',1,2,3),(2,'La Revolucion Francesa','La Francia de 1799 era totalmente distinta a la de 1789. En apenas una década, la Revolución había creado un estado completamente nuevo. De una monarquía absolutista se había pasado a una república. Ya no había súbditos, sino ciudadanos. La sociedad, antes capitaneada por aristocracia y clero, tenía ahora en la burguesía su motor principal. Tan irreconocible estaba la nación y tan ori­ginal era el modo en que se había organizado que hubo de remontarse a la Roma clásica para dar nombre a sus nuevas instituciones: Senado, Consulado, Tribunado, Prefectura...\r\n\r\nLas leyes y la economía, el arte y la ciencia, la educación, el ejército, el papel de la Iglesia, la administración territorial... todos los aspectos del estado habían cambiado respecto del Antiguo Régimen. E, inevitablemente, el modelo de esta renovación integral se tomó como ejemplo en aquellas otras latitudes en que también se perseguía la soberanía del pueblo en los asuntos colectivos, la libertad política y la igualdad ante la ley. Francia estaba de estreno tras el vendaval revolucionario y el mundo la miraba fascinado.',1,1,1,'2020-05-29',1,1,1);
/*!40000 ALTER TABLE `temas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-01 15:53:10
