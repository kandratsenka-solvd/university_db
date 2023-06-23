-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: university
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
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant` (
  `applicant_id` int NOT NULL AUTO_INCREMENT,
  `person_id` int NOT NULL,
  `course_id` int DEFAULT NULL,
  PRIMARY KEY (`applicant_id`),
  KEY `fk_applicants_person1_idx` (`person_id`),
  KEY `fk_applicant_course1_idx` (`course_id`),
  CONSTRAINT `fk_applicant_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE ON UPDATE SET NULL,
  CONSTRAINT `fk_applicants_person1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant`
--

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `course_name` varchar(60) DEFAULT NULL,
  `department_id` int NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `fk_course_department1_idx` (`department_id`),
  CONSTRAINT `fk_course_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Structural Analysis and Design',1),(2,'Geotechnical Engineering',1),(3,'Thermodynamics and Heat Transfer',2),(4,'Manufacturing Processes and Automation',2),(5,'Power Systems Engineering',3),(6,'Control Systems and Robotics',3),(7,'Cell Biology',4),(8,'Genetics',4),(9,'Organic Chemistry',5),(10,'Analytical Chemistry',5),(11,'Quantum Mechanics',6),(12,'Electromagnetism',6),(13,'Data Structures and Algorithms',7),(14,'Database Systems',7),(15,'Information Security',8),(16,'Business Process Management',8),(17,'Software Development Life Cycle',9),(18,'Software Testing and Quality Assurance',9);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `degree`
--

DROP TABLE IF EXISTS `degree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `degree` (
  `degree_id` int NOT NULL AUTO_INCREMENT,
  `degree_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`degree_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degree`
--

LOCK TABLES `degree` WRITE;
/*!40000 ALTER TABLE `degree` DISABLE KEYS */;
INSERT INTO `degree` VALUES (1,'bachelor'),(2,'master'),(3,'doctor');
/*!40000 ALTER TABLE `degree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(60) DEFAULT NULL,
  `faculty_id` int DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  KEY `faculty_id` (`faculty_id`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`faculty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Civil Engineering',1),(2,'Mechanical Engineering',1),(3,'Electrical Engineering',1),(4,'Biology',2),(5,'Chemistry',2),(6,'Physics',2),(7,'Computer Science',3),(8,'Information Systems',3),(9,'Software Engineering',3);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edu_group`
--

DROP TABLE IF EXISTS `edu_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edu_group` (
  `edu_group_id` int NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) DEFAULT NULL,
  `degree_id` int NOT NULL,
  `department_id` int NOT NULL,
  PRIMARY KEY (`edu_group_id`),
  KEY `fk_edu_group_degree1_idx` (`degree_id`),
  KEY `fk_edu_group_department1_idx` (`department_id`),
  CONSTRAINT `fk_edu_group_degree1` FOREIGN KEY (`degree_id`) REFERENCES `degree` (`degree_id`),
  CONSTRAINT `fk_edu_group_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edu_group`
--

LOCK TABLES `edu_group` WRITE;
/*!40000 ALTER TABLE `edu_group` DISABLE KEYS */;
INSERT INTO `edu_group` VALUES (1,'CE7612',1,1),(2,'CE3058',2,1),(3,'ME6482',3,2),(4,'ME9706',1,2),(5,'EE2486',2,3),(6,'EE9431',3,3),(7,'BI9132',1,4),(8,'BI4267',2,4),(9,'CH6201',3,5),(10,'CH1438',1,5),(11,'PH3994',2,6),(12,'PH6027',3,6),(13,'CS5712',1,7),(14,'CS9263',2,7),(15,'IS6281',3,8),(16,'IS3045',1,8),(17,'SE6358',2,9),(18,'SE1492',3,9);
/*!40000 ALTER TABLE `edu_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `faculty_id` int NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`faculty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Engineering'),(2,'Natural Sciences'),(3,'Information Technology');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grades`
--

DROP TABLE IF EXISTS `grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grades` (
  `grade_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `grade` int DEFAULT NULL,
  PRIMARY KEY (`grade_id`),
  KEY `student_id` (`student_id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `grades_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grades`
--

LOCK TABLES `grades` WRITE;
/*!40000 ALTER TABLE `grades` DISABLE KEYS */;
/*!40000 ALTER TABLE `grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `graduate`
--

DROP TABLE IF EXISTS `graduate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `graduate` (
  `graduate_id` int NOT NULL AUTO_INCREMENT,
  `person_id` int NOT NULL,
  `degree_id` int NOT NULL,
  `qualification_id` int NOT NULL,
  PRIMARY KEY (`graduate_id`),
  KEY `fk_graduate_degree1_idx` (`degree_id`),
  KEY `fk_graduate_person1_idx` (`person_id`),
  KEY `fk_graduate_qualification1_idx` (`qualification_id`),
  CONSTRAINT `fk_graduate_degree1` FOREIGN KEY (`degree_id`) REFERENCES `degree` (`degree_id`),
  CONSTRAINT `fk_graduate_person1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`),
  CONSTRAINT `fk_graduate_qualification1` FOREIGN KEY (`qualification_id`) REFERENCES `qualification` (`qualification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `graduate`
--

LOCK TABLES `graduate` WRITE;
/*!40000 ALTER TABLE `graduate` DISABLE KEYS */;
INSERT INTO `graduate` VALUES (1,2,1,1),(2,6,1,1),(3,13,1,1),(4,24,1,1),(5,31,1,1),(6,33,1,1);
/*!40000 ALTER TABLE `graduate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer` (
  `lecturer_id` int NOT NULL AUTO_INCREMENT,
  `person_id` int NOT NULL,
  `degree_id` int NOT NULL,
  PRIMARY KEY (`lecturer_id`),
  UNIQUE KEY `degree_degree_id_UNIQUE` (`degree_id`),
  KEY `fk_lecturers_person1_idx` (`person_id`),
  KEY `fk_lecturer_degree1_idx` (`degree_id`),
  CONSTRAINT `fk_lecturer_degree1` FOREIGN KEY (`degree_id`) REFERENCES `degree` (`degree_id`),
  CONSTRAINT `fk_lecturers_person1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer`
--

LOCK TABLES `lecturer` WRITE;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer_subject`
--

DROP TABLE IF EXISTS `lecturer_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer_subject` (
  `lecturer_subject_id` int NOT NULL AUTO_INCREMENT,
  `lecturer_id` int NOT NULL,
  `subject_id` int NOT NULL,
  PRIMARY KEY (`lecturer_subject_id`),
  KEY `fk_lecturer_subject_subject1_idx` (`subject_id`),
  KEY `fk_lecturer_subject_lecturer1_idx` (`lecturer_id`),
  CONSTRAINT `fk_lecturer_subject_lecturer1` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`lecturer_id`),
  CONSTRAINT `fk_lecturer_subject_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer_subject`
--

LOCK TABLES `lecturer_subject` WRITE;
/*!40000 ALTER TABLE `lecturer_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturer_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `person_id` int NOT NULL AUTO_INCREMENT,
  `title_id` int NOT NULL,
  `full_name` varchar(45) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`person_id`),
  KEY `fk_person_status1_idx` (`title_id`),
  CONSTRAINT `fk_person_status1` FOREIGN KEY (`title_id`) REFERENCES `title` (`title_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,3,'Angelo Kessler','1980-01-26','Male','71482 Hyatt Light','treva.feil@gmail.com','925.869.3400'),(2,2,'Lee Lind','1967-05-05','Male','289 Wolf Tunnel','haley.lind@gmail.com','497-757-7158'),(3,1,'Gracie Kuphal','1964-02-10','Female','493 Myong Landing','jessi.morar@yahoo.com','(461) 260-4217'),(4,4,'Spencer Adams','1982-03-16','Female','6983 Krajcik Expressway','breanna.stehr@hotmail.com','1-136-012-9813'),(5,4,'Karima Collier','2000-12-22','Female','319 Williamson View','kasey.bosco@hotmail.com','668-162-8798'),(6,3,'Emerson Beahan','1986-02-07','Male','716 Clint Crescent','margart.spinka@yahoo.com','(994) 633-8576'),(7,1,'Trista White','1982-04-12','Male','5097 Ricky Locks','tyler.lubowitz@yahoo.com','1-667-273-4762'),(8,2,'Dale Hegmann','1984-04-24','Male','5456 Eddie Junctions','percy.wolf@hotmail.com','870.009.0823'),(9,4,'Beverly Hayes','1968-12-11','Male','888 Deon Rue','agnes.farrell@yahoo.com','1-231-589-1615'),(10,3,'Dorthy Welch','1985-04-11','Female','565 Sunny Roads','salvatore.schumm@gmail.com','1-367-876-5720'),(11,4,'Margarette Veum','1995-09-05','Female','576 Jamey Station','monica.nikolaus@yahoo.com','996.195.3197'),(12,4,'Kasha Kirlin','1972-09-24','Male','4016 Prohaska Oval','humberto.fahey@yahoo.com','769.050.8282'),(13,3,'Georgeann Jaskolski','1984-12-16','Male','477 Son Corners','tonya.yost@gmail.com','1-899-940-1828'),(14,3,'Jed Rohan','1984-06-28','Female','929 Jacques Cliffs','elease.cassin@hotmail.com','093.131.5267'),(15,3,'Vida Considine','1959-04-08','Male','139 Kizzie Orchard','sherman.ebert@hotmail.com','445.785.6051'),(16,2,'Nenita Pfannerstill','1974-04-16','Female','995 Rudolph Coves','lacy.orn@hotmail.com','154.829.1355'),(17,1,'Karlyn Rath','1965-08-07','Female','784 Bradley Prairie','humberto.olson@hotmail.com','(689) 955-6588'),(18,2,'Willette Konopelski','1966-03-06','Male','496 Kuphal Squares','larue.streich@hotmail.com','230-061-4687'),(19,4,'Danette Block','1995-01-09','Male','9151 Suzi Corner','mitzi.murazik@yahoo.com','1-278-772-2275'),(20,4,'Candyce Strosin','2003-06-13','Male','03243 Stacey Route','venita.feil@gmail.com','1-848-176-7177'),(21,2,'Ericka Abbott','1974-04-23','Male','3497 Joselyn Knolls','sterling.schmitt@yahoo.com','(728) 141-3243'),(22,3,'Patience Fahey','1958-08-18','Female','325 Lyda Rapid','horace.oconner@hotmail.com','114.738.1693'),(23,2,'Shana Kris','1990-11-15','Male','59017 Leuschke Camp','izetta.robel@hotmail.com','566.839.1813'),(24,3,'Tyron Larson','1986-01-02','Female','627 Fritz Stream','jo.lakin@hotmail.com','172-430-6228'),(25,3,'Cuc Torp','1981-01-08','Male','420 Stoltenberg Corners','cordell.bode@gmail.com','422.775.9122'),(26,1,'Arianne Hodkiewicz','1974-07-26','Female','1916 Ortiz Views','kimberely.auer@yahoo.com','384-188-0718'),(27,4,'Cleotilde Fritsch','1979-04-30','Female','98669 Bogan Common','sade.okon@yahoo.com','1-202-648-6184'),(28,1,'Michale Veum','1998-09-01','Female','715 Rodriguez Route','amos.smith@gmail.com','853-909-7258'),(29,2,'Illa Schuppe','1991-11-18','Female','437 Osinski Branch','shanita.hyatt@gmail.com','1-135-617-5812'),(30,4,'Samuel Heathcote','2003-08-21','Female','02974 Dian Square','dominick.kunde@yahoo.com','318-311-2939'),(31,4,'Lottie Skiles','1969-11-08','Female','90407 Wilderman Estate','dalton.rohan@gmail.com','1-312-697-2167'),(32,4,'Cary Kunze','1971-06-25','Female','417 Karl Glens','damien.bosco@hotmail.com','572-181-7888'),(33,3,'Clifford Tillman','1965-08-14','Male','45713 Ebert Hills','tommye.roob@yahoo.com','(872) 723-0581'),(34,4,'Elanor Kautzer','1965-02-17','Male','7346 Brett Parkways','junior.king@yahoo.com','(456) 650-2360'),(35,2,'Jacinda Morissette','1994-11-18','Male','60664 Predovic Inlet','brock.haag@yahoo.com','1-348-171-2518'),(36,2,'Albert Baumbach','1989-10-21','Female','23746 Farrell Bypass','leonel.brown@hotmail.com','180.875.6491'),(37,2,'Gordon Schiller','1988-05-31','Female','335 Stevie Stream','vivienne.kris@gmail.com','096.605.0163'),(38,4,'Johnny Yost','1972-10-02','Female','02106 Jenkins Tunnel','louie.tillman@hotmail.com','1-738-602-1870'),(39,3,'Levi Emard','1986-06-03','Male','12095 Gottlieb Fort','quinn.abbott@hotmail.com','896.172.4053'),(40,2,'Norberto Zboncak','1976-06-16','Female','4440 Ethyl Walks','genaro.simonis@gmail.com','(812) 992-2084'),(41,2,'Neal Wolf','1982-06-10','Female','4466 Devon Springs','enedina.macejkovic@yahoo.com','(273) 986-4544'),(42,3,'Mariela Hammes','1986-05-20','Male','48968 Shella Shoals','francisco.parisian@gmail.com','1-249-050-9000'),(43,2,'Aubrey Dickens','1989-06-15','Female','9856 Tuan Radial','jorge.thiel@gmail.com','1-862-047-9219'),(44,2,'Booker Swift','1959-09-23','Female','8142 Sherwood Plains','tyisha.braun@gmail.com','634-180-8173'),(45,3,'Chante Walter','1960-05-16','Male','09165 Chad Ways','thad.rice@yahoo.com','(843) 295-4379'),(46,3,'Delbert Schamberger','1966-02-24','Male','68644 Eleonore Mills','hillary.vonrueden@gmail.com','832-441-6648');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualification`
--

DROP TABLE IF EXISTS `qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualification` (
  `qualification_id` int NOT NULL AUTO_INCREMENT,
  `qualification_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`qualification_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification`
--

LOCK TABLES `qualification` WRITE;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
INSERT INTO `qualification` VALUES (1,'engineer'),(2,'chemist'),(3,'software engineer');
/*!40000 ALTER TABLE `qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `person_id` int NOT NULL,
  `edu_group_id` int NOT NULL,
  PRIMARY KEY (`student_id`),
  KEY `fk_student_person1_idx` (`person_id`),
  KEY `fk_student_edu_group1_idx` (`edu_group_id`),
  CONSTRAINT `fk_student_edu_group1` FOREIGN KEY (`edu_group_id`) REFERENCES `edu_group` (`edu_group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_student_person1` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,8,11),(3,10,2),(4,13,12),(5,14,16),(7,11,5),(8,16,8),(9,15,7),(10,17,13),(11,18,2),(12,19,15),(14,22,1),(15,21,5),(16,23,9),(17,24,11),(18,27,6),(19,29,8),(20,25,9),(21,26,11),(22,28,2),(23,30,9),(25,32,17),(26,33,4),(27,35,12),(28,36,15),(29,34,2),(30,38,15),(32,39,8),(34,44,2),(35,40,4),(36,42,8),(37,41,6),(38,46,16),(39,45,2);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Steel Structures'),(2,'Concrete Structures'),(3,'Foundation Engineering'),(4,'Soil Mechanics'),(5,'Fluid Mechanics'),(6,'Heat Transfer'),(7,'Manufacturing Systems'),(8,'CNC Machining'),(9,'Power Generation and Distribution'),(10,'Renewable Energy Systems'),(11,'Automatic Control Systems'),(12,'Robotics and Automation'),(13,'Molecular Biology'),(14,'Cell Physiology'),(15,'Human Genetics'),(16,'Molecular Genetics'),(17,'Organic Synthesis'),(18,'Spectroscopy'),(19,'Instrumental Analysis'),(20,'Chromatography Techniques'),(21,'Quantum Physics'),(22,'Quantum Field Theory'),(23,'Electrodynamics'),(24,'Optics and Photonics'),(25,'Algorithm Design and Analysis'),(26,'Data Structures and Algorithms in Java'),(27,'Database Design and Management'),(28,'SQL and Relational Databases'),(29,'Network Security'),(30,'Cryptography and Data Protection'),(31,'Business Process Modeling'),(32,'Process Optimization and Automation'),(33,'Software Requirements Engineering'),(34,'Software Design Patterns'),(35,'Software Testing Techniques'),(36,'Test Automation and Performance Testing');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_course`
--

DROP TABLE IF EXISTS `subject_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_course` (
  `subject_course_id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int NOT NULL,
  `course_id` int NOT NULL,
  PRIMARY KEY (`subject_course_id`),
  KEY `fk_subject_course_subject1_idx` (`subject_id`),
  KEY `fk_subject_course_course1_idx` (`course_id`),
  CONSTRAINT `fk_subject_course_course1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `fk_subject_course_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_course`
--

LOCK TABLES `subject_course` WRITE;
/*!40000 ALTER TABLE `subject_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title`
--

DROP TABLE IF EXISTS `title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `title` (
  `title_id` int NOT NULL AUTO_INCREMENT,
  `title_name` varchar(45) NOT NULL,
  PRIMARY KEY (`title_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title`
--

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
INSERT INTO `title` VALUES (1,'lecturer'),(2,'student'),(3,'applicant'),(4,'graduate');
/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-22 11:48:15
