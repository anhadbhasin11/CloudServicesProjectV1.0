CREATE DATABASE  IF NOT EXISTS `student_course_registration` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `student_course_registration`;
-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: student_course_registration
-- ------------------------------------------------------
-- Server version	5.6.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` bigint(20) NOT NULL,
  `apt_no` int(11) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `street_name` varchar(255) NOT NULL,
  `zip_code` int(11) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (100009,90,'San Jose','CA','65 Rio Robles E',95134),(100011,66,'Santa Clara','CA','7642 Scott Blvd',95054),(100013,167,'San Francisco','CA','70 S Market St',78534),(100015,48,'Fremont','CA','901 Stevenson Blvd',94536),(100017,10,'Santa Clara','CA','876 Lawrence St',95134),(100019,18,'Anchorage','Alaska','48455 Pierstorff Avenue',99522),(100026,86,'Sunnyvale','CA','201 W California Ave',94086),(100028,1028,'San Jose','CA','65 Rio Robles E',95134),(100030,456,'Fremont','CA','209 E Indio Ave',94536),(100032,15,'San Diego','CA','4356 Waterford Ave',45632),(100034,653,'San Jose','CA','101 San Fernando',95112),(100036,71,'Richmond','CA','0442 Melrose Street',23225),(100038,724,'Los Angels','CA','86 Sauthoff Junction',90055);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_details`
--

DROP TABLE IF EXISTS `course_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_details` (
  `course_id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `description` text,
  `name` varchar(255) NOT NULL,
  `no_of_credits` int(11) NOT NULL,
  `pre_requisite_course` varchar(255) DEFAULT NULL,
  `program` varchar(255) NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_details`
--

LOCK TABLES `course_details` WRITE;
/*!40000 ALTER TABLE `course_details` DISABLE KEYS */;
INSERT INTO `course_details` VALUES (100003,'CHEM-055','Chemistry','Introduction to theories and techniques of chemical analysis.','Quantitative Analysis',9,'CHEM 001A-General Chemistry','BS'),(100004,'CS-218','Computer Science','Topics in cloud computing, including distributed system models, virtual machines, virtualization, cloud platform architectures (IaaS, PaaS, SaaS), service-oriented architectures, cloud programming and software environments, peer-to-peer computing, ubiquitous cloud, cloud security and trust ',' Topics in Cloud Computing',9,'CS-149','MS'),(100005,'ENGR-202','General Engineering','Large scale system design and development. Integrated approach including mission statement, synthesis of design concepts, tradeoff studies, risk assessment and interactions encountered in the optimal design, development, manufacture and test of systems. ','Systems Engineering',9,'Graduate standing or instructor consent','MS-Engineering'),(100006,'CMPE-120','Computer Engineering','Introduction to computer organization and architecture, system buses, internal memory and external memory, input/output, central processing unit CPU, instruction sets, CPU structure and function, RISC, control unit','Computer Organization and Architecture',9,'CMPE-050 or CS-046B','BS'),(100007,'EE-262','Electrical Engineering','Study of various biomedical signals and their physiological origin. Study of analog instrumentation design to extract such signals with extensive biomedical signal analysis in the context of disease management, pathology and treatment with numerous case studies.','Acquisition and Analysis of Biosignals',9,'EE 210 or equivalent, or instructor consent','MS Electrical Engineering');
/*!40000 ALTER TABLE `course_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrolled_student`
--

DROP TABLE IF EXISTS `enrolled_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrolled_student` (
  `student_id` bigint(20) NOT NULL,
  `section_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK1xe3n21yit7uao16a3itd6mqg` (`student_id`,`section_id`),
  KEY `FKi2miiifta1ujt2nu406w4ihip` (`section_id`),
  CONSTRAINT `FK_student_section` FOREIGN KEY (`student_id`) REFERENCES `student_details` (`user_id`),
  CONSTRAINT `FKi2miiifta1ujt2nu406w4ihip` FOREIGN KEY (`section_id`) REFERENCES `section_info` (`section_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrolled_student`
--

LOCK TABLES `enrolled_student` WRITE;
/*!40000 ALTER TABLE `enrolled_student` DISABLE KEYS */;
INSERT INTO `enrolled_student` VALUES (100025,100020);
/*!40000 ALTER TABLE `enrolled_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor_details`
--

DROP TABLE IF EXISTS `professor_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor_details` (
  `faculty_type` varchar(255) NOT NULL,
  `office_hours_from_time` time NOT NULL,
  `office_hours_to_time` time NOT NULL,
  `years_of_experience` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKq356u7qxm5p8q9shcx3wwr3qu` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor_details`
--

LOCK TABLES `professor_details` WRITE;
/*!40000 ALTER TABLE `professor_details` DISABLE KEYS */;
INSERT INTO `professor_details` VALUES ('Permanent','10:00:00','12:30:00',12,100008),('Permanent','08:45:00','13:30:00',17,100010),('Permanent','10:00:00','15:00:00',25,100012),('Visiting','14:00:00','17:00:00',5,100014),('Permanent','11:30:00','14:30:00',9,100016),('Full-Time','11:15:00','15:30:00',20,100018);
/*!40000 ALTER TABLE `professor_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (100000,'Student'),(100001,'Professor'),(100002,'Administrator');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section_info`
--

DROP TABLE IF EXISTS `section_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section_info` (
  `section_id` bigint(20) NOT NULL,
  `class_end_time` time NOT NULL,
  `class_start_time` time NOT NULL,
  `day_of_week` varchar(255) NOT NULL,
  `class_end_date` date NOT NULL,
  `mode_of_instruction` varchar(255) NOT NULL,
  `room_number` varchar(255) NOT NULL,
  `semester` varchar(255) NOT NULL,
  `class_start_date` date NOT NULL,
  `total_capacity` int(11) NOT NULL,
  `wait_list_capacity` int(11) NOT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `professor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`section_id`),
  KEY `FK_section_course` (`course_id`),
  KEY `FK_section_professor` (`professor_id`),
  CONSTRAINT `FK_section_course` FOREIGN KEY (`course_id`) REFERENCES `course_details` (`course_id`),
  CONSTRAINT `FK_section_professor` FOREIGN KEY (`professor_id`) REFERENCES `professor_details` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_info`
--

LOCK TABLES `section_info` WRITE;
/*!40000 ALTER TABLE `section_info` DISABLE KEYS */;
INSERT INTO `section_info` VALUES (100020,'20:45:00','18:00:00','Thursday','2015-12-12','In Class','EM-501','Fall-2015','2015-08-24',40,20,100003,100008),(100021,'20:30:00','18:30:00','Friday','2016-05-05','In Class','COE-850','Spring-2016','2016-01-26',35,10,100004,100010),(100022,'20:45:00','18:00:00','Monday','2016-12-08','In Class','T-506','Fall-2016','2016-08-21',45,20,100004,100012),(100023,'20:45:00','09:00:00','Saturday','2015-12-12','In Class','C-15','Summer-2015','2015-08-24',42,15,100006,100014),(100024,'20:15:00','17:30:00','Wednesday','2016-05-26','In Class','MT-33','Spring-2016','2016-01-10',40,10,100006,100016);
/*!40000 ALTER TABLE `section_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequence`
--

DROP TABLE IF EXISTS `sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequence`
--

LOCK TABLES `sequence` WRITE;
/*!40000 ALTER TABLE `sequence` DISABLE KEYS */;
INSERT INTO `sequence` VALUES (100039),(100039),(100039),(100039),(100039);
/*!40000 ALTER TABLE `sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_details`
--

DROP TABLE IF EXISTS `student_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_details` (
  `admissionType` varchar(255) NOT NULL,
  `previous_degree` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FKt5phq64s517nt34bmyybwlnt4` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_details`
--

LOCK TABLES `student_details` WRITE;
/*!40000 ALTER TABLE `student_details` DISABLE KEYS */;
INSERT INTO `student_details` VALUES ('Accepted','Bsc Comp Science',100025),('Accepted - Conditionally','BE Comp Engineering',100027),('Accepted','Bachelor of commerce',100029),('Rejected','Bachelor of Arts',100031),('Waiting','BS in Computer Science',100033),('Classified-Graduate','BSc, Computer Engineering',100035),('Classified-Graduate','BSc, Mechanical Engineering',100037);
/*!40000 ALTER TABLE `student_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (100025,100000),(100027,100000),(100029,100000),(100031,100000),(100033,100000),(100035,100000),(100037,100000),(100008,100001),(100010,100001),(100012,100001),(100014,100001),(100016,100001),(100018,100001);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `college_id` bigint(20) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email_id` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_fnfxlrkjcuv9benmtevvmip21` (`college_id`),
  KEY `FK_user_address` (`address_id`),
  CONSTRAINT `FK_user_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (100008,123456779,'1970-08-07','alice@gmail.com','Alice',NULL,'Campbell','W.','+1-408-376-7860',100009),(100010,1234,'1962-08-07','larkin_mike@gmail.com','Mike',NULL,'Larkin','W.','+1-503-634-07630',100011),(100012,453,'1955-08-07','rakesh_ranjan@gmail.com','Rakesh',NULL,'Ranjan','C.','+1-408-774-7860',100013),(100014,676,'1983-08-04','erica.bing@gmail.com','Erica',NULL,'Bing','A.','+1-564-376-9160',100015),(100016,444,'1980-05-07','Davis_emily@gmail.com','Emily',NULL,'Davis','U.','+1-712-354-0062',100017),(100018,666,'1957-01-13','shill4@google.fr','Sandra',NULL,'Hill','AA','1-723-327-1856',100019),(100025,123456789,'1988-02-05','John@gmail.com','John',NULL,'Edward','R.','+1-408-386-7260',100026),(100027,123456788,'1986-08-07','alice@gmail.com','Alice',NULL,'Campbell','W.','+1-408-376-7860',100028),(100029,89078345345,'1987-07-28','sarah@gmail.com','Sarah',NULL,'Rossie','V.','+1-508-664-7260',100030),(100031,9459345,'1983-09-05','V.Alan@gmail.com','Alan',NULL,'Simon','V.','+1-407-654-8760',100032),(100033,64574,'1990-12-23','Alexander.olivia1986@gmail.com','Olivia',NULL,'Alexander','s.','+1-567-654-9812',100034),(100035,4534,'1993-08-10','esimpson8@youku.com','Edward',NULL,'Simpson','Cinacma','+1-187-063-6666',100036),(100037,5457,'1991-06-12','fross2@imgur.com','Fred',NULL,'Ross','H.','+1-830-195-5010',100038);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-29  3:34:29
