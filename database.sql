-- MySQL dump 10.13  Distrib 5.1.73, for unknown-linux-gnu (x86_64)
--
-- Host: localhost    Database: findjob
-- ------------------------------------------------------
-- Server version	5.1.73-log

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
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` bigint(20) DEFAULT NULL,
  `contact` varchar(32) DEFAULT NULL,
  `text` varchar(256) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,1540634788688,'132123123123','fdassssssss',NULL),(2,1540634790361,'132123123123','fdassssssss',NULL),(3,1540634891945,'fsdsdf','fdssdfsdf',NULL),(4,1540634957646,'1231321','fasdasfd',NULL),(5,1540638389364,'fasdddd','rewadasdf','1'),(6,1540658733909,'13222222222','这是事实上的','1');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operate_log`
--

DROP TABLE IF EXISTS `operate_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operate_log` (
  `op_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(32) DEFAULT NULL,
  `l_value` varchar(64) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `operator` varchar(32) DEFAULT NULL,
  `operator_type` char(1) DEFAULT NULL,
  PRIMARY KEY (`op_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operate_log`
--

LOCK TABLES `operate_log` WRITE;
/*!40000 ALTER TABLE `operate_log` DISABLE KEYS */;
INSERT INTO `operate_log` VALUES (16,'1',NULL,1540645182413,'oFKkr5G2IMynVzbOnftxu2ES0tvs','1'),(17,'1',NULL,1540729683051,'oDA_c4joQrfXIdrfwtecmyRuQzUk','1');
/*!40000 ALTER TABLE `operate_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `p_order`
--

DROP TABLE IF EXISTS `p_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` char(32) NOT NULL,
  `position_id` int(11) NOT NULL,
  `vender_id` varchar(32) DEFAULT NULL,
  `work_time` bigint(20) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `end_time` bigint(20) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_Reference_3` (`position_id`),
  KEY `FK_Reference_4` (`vender_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`position_id`) REFERENCES `position` (`position_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`vender_id`) REFERENCES `vender` (`vender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `p_order`
--

LOCK TABLES `p_order` WRITE;
/*!40000 ALTER TABLE `p_order` DISABLE KEYS */;
INSERT INTO `p_order` VALUES (55,'oDA_c4joQrfXIdrfwtecmyRuQzUk',144,'oFKkr5LVQKNVVZoisHmEHyiupcBk',1540684800000,1540729659969,NULL,'4'),(56,'oDA_c4joQrfXIdrfwtecmyRuQzUk',145,'oFKkr5G2IMynVzbOnftxu2ES0tvs',1540684800000,1540729725427,NULL,'6'),(57,'oDA_c4j85y4rYy3I3O-XnRad5Jec',145,'oFKkr5G2IMynVzbOnftxu2ES0tvs',1540684800000,1540729764785,NULL,'6'),(58,'oDA_c4joQrfXIdrfwtecmyRuQzUk',148,'oFKkr5G2IMynVzbOnftxu2ES0tvs',1540684800000,1540730331589,NULL,'6'),(59,'oDA_c4j85y4rYy3I3O-XnRad5Jec',158,'oFKkr5PKjgy17MZ75X1cZ0KKKABY',1540684800000,1540733517934,NULL,'5'),(60,'oDA_c4j85y4rYy3I3O-XnRad5Jec',256,'oFKkr5PKjgy17MZ75X1cZ0KKKABY',1540771200000,1540815442529,NULL,'5'),(61,'oDA_c4j85y4rYy3I3O-XnRad5Jec',256,'oFKkr5PKjgy17MZ75X1cZ0KKKABY',1540771200000,1540815739171,NULL,'5'),(62,'oDA_c4j85y4rYy3I3O-XnRad5Jec',259,'oFKkr5LVQKNVVZoisHmEHyiupcBk',1540771200000,1540815745357,NULL,'5'),(63,'oDA_c4j85y4rYy3I3O-XnRad5Jec',259,'oFKkr5LVQKNVVZoisHmEHyiupcBk',1540771200000,1540815752010,NULL,'5'),(64,'oDA_c4j85y4rYy3I3O-XnRad5Jec',262,'oFKkr5LVQKNVVZoisHmEHyiupcBk',1540771200000,1540815790000,NULL,'5'),(65,'oDA_c4j85y4rYy3I3O-XnRad5Jec',260,'oFKkr5LVQKNVVZoisHmEHyiupcBk',1540771200000,1540815801160,NULL,'5'),(66,'oDA_c4j85y4rYy3I3O-XnRad5Jec',263,'oFKkr5LVQKNVVZoisHmEHyiupcBk',1540771200000,1540815808128,NULL,'5'),(67,'oDA_c4j85y4rYy3I3O-XnRad5Jec',264,'oFKkr5LVQKNVVZoisHmEHyiupcBk',1540771200000,1540815816766,NULL,'5'),(68,'oDA_c4rdcUMAyxMp3CEYl-9VpuOU',271,'oFKkr5G2IMynVzbOnftxu2ES0tvs',1540771200000,1540816558621,NULL,'6'),(69,'oDA_c4rdcUMAyxMp3CEYl-9VpuOU',271,'oFKkr5G2IMynVzbOnftxu2ES0tvs',1540771200000,1540817309843,NULL,'6'),(70,'oDA_c4joQrfXIdrfwtecmyRuQzUk',274,'oFKkr5LVQKNVVZoisHmEHyiupcBk',1540857600000,1540876343706,NULL,'1'),(71,'oDA_c4j85y4rYy3I3O-XnRad5Jec',275,'oFKkr5G2IMynVzbOnftxu2ES0tvs',1540857600000,1540889545303,NULL,'3');
/*!40000 ALTER TABLE `p_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `position_id` int(11) NOT NULL AUTO_INCREMENT,
  `vender_id` varchar(32) NOT NULL,
  `type` varchar(32) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `salary_type` varchar(10) DEFAULT NULL,
  `salary` char(32) DEFAULT NULL,
  `position_desc` text,
  `video_src` varchar(256) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `h_count` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `company` varchar(32) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`position_id`),
  KEY `FK_r_vender_position` (`vender_id`),
  CONSTRAINT `FK_r_vender_position` FOREIGN KEY (`vender_id`) REFERENCES `vender` (`vender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (144,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540729473226,'古今公','2'),(145,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540684800000,'元/12小时','120','宜家家居','',2,2,1540729567863,'吞吞吐吐','2'),(146,'oFKkr5LVQKNVVZoisHmEHyiupcBk','三发送个',1540771200000,'元/1小时','-1','看看咯','',2,0,1540729708545,'古今公','2'),(147,'oFKkr5LVQKNVVZoisHmEHyiupcBk','三发送个',1540684800000,'元/2小时','-1','看看咯看看咯ll','',36,0,1540729774198,'古今公','2'),(148,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540684800000,'元/12小时','120','宜家家居','',22,1,1540730116636,'吞吞吐吐','2'),(149,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','加扣扣家','',1,0,1540730954556,'古今公','2'),(150,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540684800000,'元/12小时','-1','宜家家居','',3,0,1540731025534,'吞吞吐吐','2'),(151,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540684800000,'元/12小时','100','宜家家居','',3,0,1540731160502,'吞吞吐吐','2'),(152,'oFKkr5G2IMynVzbOnftxu2ES0tvs','三发送个',1540684800000,'元/12小时','100','宜家家居','',3,0,1540731220606,'吞吞吐吐','2'),(153,'oFKkr5LVQKNVVZoisHmEHyiupcBk','三个发生地',1540684800000,'元/1小时','2','FDSAFDSA','',1,0,1540731599640,'古今公','2'),(154,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','21','324','',21,0,1540732654974,'古今公','2'),(155,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540732725398,'古今公','2'),(156,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','4','',1,0,1540733045206,'古今公','2'),(157,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','4rqe','',1,0,1540733265759,'古今公','2'),(158,'oFKkr5PKjgy17MZ75X1cZ0KKKABY','两个',1540684800000,'元/12小时','247','发发发姑姑姑父方法','',12,1,1540733363667,'染发方法','2'),(159,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','fdsa','',1,0,1540733373740,'古今公','2'),(160,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','1','fdsa','',2,0,1540733401476,'古今公','2'),(161,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','1','1','',1,0,1540733422684,'古今公','2'),(162,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','fdas','',1,0,1540733457992,'古今公','2'),(163,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','fas','',1,0,1540733523388,'古今公','2'),(164,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','32','',1,0,1540733883467,'古今公','2'),(165,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540734340199,'古今公','2'),(166,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540734434343,'古今公','2'),(167,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540734449373,'古今公','2'),(168,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','1','1','',1,0,1540734486782,'古今公','2'),(169,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540734547243,'古今公','2'),(170,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540736465351,'古今公','2'),(171,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540737233975,'古今公','2'),(172,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540737241224,'古今公','2'),(173,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','2','',1,0,1540737338128,'古今公','2'),(174,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','3','',1,0,1540739856449,'古今公','2'),(175,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540684800000,'元/1小时','2','2','',1,0,1540740038134,'古今公','2'),(176,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540743338026,'古今公','2'),(177,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540743360799,'古今公','2'),(178,'oFKkr5G2IMynVzbOnftxu2ES0tvs','三发送个',1540771200000,'元/1小时','140','刚刚哈哈哈','',2,0,1540763842314,'吞吞吐吐','2'),(179,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540787558071,'古今公','2'),(180,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540788711899,'古今公','2'),(181,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540788730591,'古今公','2'),(182,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540788799261,'古今公','2'),(183,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',4,0,1540788918501,'古今公','2'),(184,'oFKkr5LVQKNVVZoisHmEHyiupcBk','三个',1540771200000,'元/1小时','3','4','',2,0,1540788993550,'古今公','2'),(185,'oFKkr5LVQKNVVZoisHmEHyiupcBk','三个',1540771200000,'元/1小时','33','4','',2,0,1540789011503,'古今公','2'),(186,'oFKkr5LVQKNVVZoisHmEHyiupcBk','三个',1540771200000,'元/1小时','33','4','',2,0,1540789038765,'古今公','2'),(187,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540791017606,'古今公','2'),(188,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540791375954,'古今公','2'),(189,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540791769931,'古今公','2'),(190,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540791868583,'古今公','2'),(191,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540791880714,'古今公','2'),(192,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540791892220,'古今公','2'),(193,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540792034438,'古今公','2'),(194,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540792265102,'古今公','2'),(195,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540792403253,'古今公','2'),(196,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540792611058,'古今公','2'),(197,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540792669964,'古今公','2'),(198,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540792874861,'古今公','2'),(199,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540793118663,'古今公','2'),(200,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540793216869,'古今公','2'),(201,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540793403307,'古今公','2'),(202,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540793433043,'古今公','2'),(203,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','1','',1,0,1540793990389,'古今公','2'),(204,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','1','',1,0,1540794005047,'古今公','2'),(205,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','1','',1,0,1540794012105,'古今公','2'),(206,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540794447283,'古今公','2'),(207,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540796568963,'古今公','2'),(208,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540796685535,'古今公','2'),(209,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540796693952,'古今公','2'),(210,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','3','2','',4,0,1540796842071,'古今公','2'),(211,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','1','3','',2,0,1540797144001,'古今公','2'),(212,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797557022,'古今公','2'),(213,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797667763,'古今公','2'),(214,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797684425,'古今公','2'),(215,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797766864,'古今公','2'),(216,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797790397,'古今公','2'),(217,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797798571,'古今公','2'),(218,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','1','',1,0,1540797837897,'古今公','2'),(219,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797852019,'古今公','2'),(220,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797910590,'古今公','2'),(221,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797914569,'古今公','2'),(222,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540797980250,'古今公','2'),(223,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','3','4','',2,0,1540798050482,'古今公','2'),(224,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','3','4','',2,0,1540798055811,'古今公','2'),(225,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798195130,'古今公','2'),(226,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798225134,'古今公','2'),(227,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','1','',3,0,1540798329902,'古今公','2'),(228,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798365885,'古今公','2'),(229,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798394096,'古今公','2'),(230,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798503244,'古今公','2'),(231,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798529785,'古今公','2'),(232,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798537573,'古今公','2'),(233,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798556053,'古今公','2'),(234,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798597168,'古今公','2'),(235,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798623559,'古今公','2'),(236,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798651200,'古今公','2'),(237,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798791718,'古今公','2'),(238,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540798859362,'古今公','2'),(239,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540799003928,'古今公','2'),(240,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540799032626,'古今公','2'),(241,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540799078526,'古今公','2'),(242,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540799184033,'古今公','2'),(243,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540799215267,'古今公','2'),(244,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540799251706,'古今公','2'),(245,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540799507273,'古今公','2'),(246,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540799515732,'古今公','2'),(247,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','1','2','',1,0,1540799742500,'古今公','2'),(248,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','1','2','',1,0,1540799773056,'古今公','2'),(249,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/12小时','100','烦烦烦烦烦烦个','',2,0,1540799853482,'吞吞吐吐','2'),(250,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','1','2','',1,0,1540799945993,'古今公','2'),(251,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540799977470,'古今公','2'),(252,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540800045284,'古今公','2'),(253,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540800048895,'古今公','2'),(254,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540800098858,'古今公','2'),(255,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','1','2','',1,0,1540800125749,'古今公','2'),(256,'oFKkr5PKjgy17MZ75X1cZ0KKKABY','两个',1540771200000,'元/1小时','200','吞吞吐吐','',2,2,1540800303480,'染发方法','2'),(257,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540800315390,'古今公','2'),(258,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/1小时','-1','急急急急急急','',1,0,1540800512744,'吞吞吐吐','2'),(259,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','3','4','',2,2,1540811214804,'古今公','2'),(260,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,1,1540813449123,'古今公','2'),(261,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,0,1540813477749,'古今公','2'),(262,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,1,1540813510970,'古今公','2'),(263,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','4','',1,1,1540813622392,'古今公','2'),(264,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540771200000,'元/1小时','2','3','',1,1,1540815330298,'古今公','2'),(265,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/1小时','-1','贩夫贩妇','',2,0,1540815378235,'吞吞吐吐','2'),(266,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/1小时','-1','贩夫贩妇','',2,0,1540815405306,'吞吞吐吐','2'),(267,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/1小时','2','uuuu','',2,0,1540815839962,'吞吞吐吐','2'),(268,'oFKkr5PKjgy17MZ75X1cZ0KKKABY','三发送个',1540771200000,'元/1小时','-1','古古怪怪','',1,0,1540815899609,'染发方法','2'),(269,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/1小时','2','嘎嘎嘎嘎','',2,0,1540816029198,'吞吞吐吐','2'),(270,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/1小时','-1','嘎嘎嘎嘎','',2,0,1540816090767,'吞吞吐吐','2'),(271,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/1小时','-1','嘎嘎嘎嘎','',2,2,1540816199460,'吞吞吐吐','2'),(272,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/1小时','-1','嘎嘎嘎嘎','',2,0,1540824027857,'吞吞吐吐','2'),(273,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540771200000,'元/1小时','-1','嘎嘎嘎嘎','',2,0,1540824042599,'吞吞吐吐','2'),(274,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1540857600000,'元/1小时','2','3','',1,1,1540875805520,'古今公','2'),(275,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540857600000,'元/1小时','8','嘎嘎嘎嘎','',2,1,1540888959875,'吞吞吐吐','2'),(276,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540857600000,'元/1小时','3','1111111111','',1,0,1540907657759,'吞吞吐吐','1'),(277,'oFKkr5PKjgy17MZ75X1cZ0KKKABY','三发送个',1540857600000,'元/1小时','7','111111','',4,0,1540907693279,'染发方法','1'),(278,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1540857600000,'元/1小时','-1','呜呜呜呜呜呜呜呜','',12,0,1540913782624,'吞吞吐吐','1'),(279,'oFKkr5G2IMynVzbOnftxu2ES0tvs','两个',1541116800000,'元/1小时','1','去去去去我问问','',1,0,1541109030182,'吞吞吐吐','1'),(280,'oFKkr5LVQKNVVZoisHmEHyiupcBk','两个',1550188800000,'元/1小时','188','旅途','',1,0,1550201933863,'古今公','1');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system`
--

DROP TABLE IF EXISTS `system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system` (
  `system_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_key` varchar(12) DEFAULT NULL,
  `s_value` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`system_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system`
--

LOCK TABLES `system` WRITE;
/*!40000 ALTER TABLE `system` DISABLE KEYS */;
INSERT INTO `system` VALUES (1,'WT','两个'),(2,'WT','三个子'),(3,'WT','三个'),(4,'WT','三个发生地'),(5,'WT','三发送个'),(6,'KF_PHONE','17652525454'),(7,'KF_NAME','测试名字'),(9,'U_NOTICE','用户公告测试！'),(10,'V_NOTICE','工厂通告测试,欢迎使用工厂端');
/*!40000 ALTER TABLE `system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` char(32) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `name` char(12) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `idcard_src` varchar(320) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `valid` varchar(1) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('oDA_c4tG-uXcw43keAeYC8cQNqg4',1540719011633,NULL,NULL,NULL,'1','1',NULL),('oDA_c4joQrfXIdrfwtecmyRuQzUk',1540722404995,'黄家伟','17680504808',NULL,'1','1','两个|三个|三个发生地'),('oDA_c4rdcUMAyxMp3CEYl-9VpuOU',1540729104178,'太热','11111111111',NULL,'1','1','三个子'),('oDA_c4gQZffMK-KAOTO8z5pn9eRk',1540729264364,NULL,NULL,NULL,'1','1',NULL),('oDA_c4j85y4rYy3I3O-XnRad5Jec',1540729538568,'宿舍','22222222222',NULL,'1','1','两个|三个子|三个|三个发生地|三发送个');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vender`
--

DROP TABLE IF EXISTS `vender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vender` (
  `vender_id` varchar(32) NOT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `contacts` varchar(12) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `address_desc` char(128) DEFAULT NULL,
  `latitude` float DEFAULT NULL,
  `longitude` float DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `valid` char(1) DEFAULT NULL,
  PRIMARY KEY (`vender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vender`
--

LOCK TABLES `vender` WRITE;
/*!40000 ALTER TABLE `vender` DISABLE KEYS */;
INSERT INTO `vender` VALUES ('oFKkr5DaJ0hbE-otOK-xtT4oGbLw',1540720612946,NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,'1'),('oFKkr5G2IMynVzbOnftxu2ES0tvs',1540633111895,'吞吞吐吐','易语言','11111111111','1','兔兔兔兔兔兔兔兔兔',23.5691,113.173,'银盏森林公园(清远市清城区178乡道)',NULL),('oFKkr5LVQKNVVZoisHmEHyiupcBk',1540625439329,'古今公','黄家伟','17652574558','1','特么嗯呢好的图',26.2055,111.606,'湖南科技学院',NULL),('oFKkr5PKjgy17MZ75X1cZ0KKKABY',1540733326471,'染发方法','呃呃呃','12222222222','1','拖拖拖共同语言',23.6192,113.16,'惠洲岭(清远市清城区)',NULL);
/*!40000 ALTER TABLE `vender` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-15 15:05:52
