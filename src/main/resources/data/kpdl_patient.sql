-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: kpdl
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `idPatient` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `polyuria` varchar(45) DEFAULT NULL,
  `polydipsia` varchar(45) DEFAULT NULL,
  `wLoss` varchar(45) DEFAULT NULL,
  `weakness` varchar(45) DEFAULT NULL,
  `polyphagia` varchar(45) DEFAULT NULL,
  `genital` varchar(45) DEFAULT NULL,
  `visualBlurring` varchar(45) DEFAULT NULL,
  `itching` varchar(45) DEFAULT NULL,
  `irritability` varchar(45) DEFAULT NULL,
  `delayedHealing` varchar(45) DEFAULT NULL,
  `partial` varchar(45) DEFAULT NULL,
  `muscleStiffness` varchar(45) DEFAULT NULL,
  `alopecia` varchar(45) DEFAULT NULL,
  `obesity` varchar(45) DEFAULT NULL,
  `class` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`idPatient`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'duyen',38,'Male','No','No','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-06'),(2,'duyen',38,'Male','No','No','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-06'),(3,'duyen',38,'Male','No','No','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-06'),(4,'duyen',38,'Male','No','No','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-06'),(5,'dung',38,'Male','No','No','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-06'),(6,'dung',38,'Male','No','No','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-08'),(7,'dung',38,'Male','No','Yes','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-08'),(8,'dung',38,'Male','Yes','Yes','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-08'),(9,'dung',38,'Male','Yes','Yes','Yes','Yes','No','No','Yes','Yes','Yes','No','No','Yes','No','Yes','Positive','2023-06-08'),(10,'dung',38,'Male','Yes','Yes','Yes','Yes','No','No','Yes','Yes','Yes','No','No','Yes','No','Yes','Positive','2023-06-08'),(11,'dung',38,'Male','No','No','No','No','No','No','No','No','Yes','No','No','Yes','No','No','Negative','2023-06-08'),(12,'giang',18,'Female','No','No','No','No','No','No','No','No','Yes','No','No','Yes','No','No','Negative','2023-06-09'),(13,'giang',18,'Female','No','No','No','No','No','No','No','No','Yes','No','No','Yes','No','No','Negative','2023-06-09'),(14,'minh',18,'Female','No','No','No','No','No','No','No','No','Yes','No','No','Yes','No','No','Negative','2023-06-09'),(15,'ninh',22,'Male','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Positive','2023-06-09'),(16,'ninh',22,'Male','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Positive','2023-06-09'),(17,'ninh',22,'Male','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Positive','2023-06-09'),(18,'ninh',22,'Male','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Positive','2023-06-09'),(19,'ninh',22,'Male','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Yes','Positive','2023-06-09'),(20,'lan anh',23,'Female','Yes','No','No','Yes','No','Yes','Yes','No','No','No','No','Yes','Yes','Yes','Negative','2023-06-09'),(21,'Trịnh Nhật Anh',23,'Male','No','No','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-09'),(22,'Trịnh Minh Anh',23,'Female','No','No','No','No','No','No','No','No','No','No','No','No','No','No','Negative','2023-06-09'),(23,'Vũ Trọng Trắng',28,'Male','No','Yes','No','No','No','No','No','No','Yes','No','No','No','Yes','No','Negative','2023-06-09'),(24,'Đinh Mai Anh',23,'Female','Yes','Yes','No','No','Yes','No','Yes','No','Yes','Yes','No','Yes','No','No','Negative','2023-06-09');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-09 17:31:06
