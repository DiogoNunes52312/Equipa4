CREATE DATABASE  IF NOT EXISTS `gestordeeventosupt` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestordeeventosupt`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gestordeeventosupt
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacao` (
  `id_avaliacao` int NOT NULL AUTO_INCREMENT,
  `comentario` text,
  `data_avaliacao` datetime(6) NOT NULL,
  `pontuacao` int NOT NULL,
  `id_evento` int NOT NULL,
  `id_participante` int NOT NULL,
  PRIMARY KEY (`id_avaliacao`),
  KEY `FK457mrh3tdfsiw6anbttvs09g9` (`id_evento`),
  KEY `FK2jd7igxl6umgpe8cm9r9e3p1o` (`id_participante`),
  CONSTRAINT `FK2jd7igxl6umgpe8cm9r9e3p1o` FOREIGN KEY (`id_participante`) REFERENCES `utilizador` (`id_utilizador`),
  CONSTRAINT `FK457mrh3tdfsiw6anbttvs09g9` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacao`
--

LOCK TABLES `avaliacao` WRITE;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
INSERT INTO `avaliacao` VALUES (1,'Evento muito atrativo','2025-10-28 20:13:36.570084',5,1,1),(2,'Não gostei muito da ultima parte do evento','2025-10-28 20:14:17.901752',2,1,2);
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `id_evento` int NOT NULL AUTO_INCREMENT,
  `capacidade_evento` int DEFAULT NULL,
  `categoria_evento` varchar(50) DEFAULT NULL,
  `data_fim` datetime(6) DEFAULT NULL,
  `data_inicio` datetime(6) DEFAULT NULL,
  `descricao_evento` text,
  `estado_evento` enum('ATIVO','CANCELADO','CONCLUIDO','FECHADO','RASCUNHO') DEFAULT NULL,
  `local_evento` varchar(100) DEFAULT NULL,
  `titulo_evento` varchar(100) NOT NULL,
  `id_organizador` int DEFAULT NULL,
  PRIMARY KEY (`id_evento`),
  KEY `FKj3pypdbr8xmoskv7j7vdcfrft` (`id_organizador`),
  CONSTRAINT `FKj3pypdbr8xmoskv7j7vdcfrft` FOREIGN KEY (`id_organizador`) REFERENCES `utilizador` (`id_utilizador`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,200,'nao sei','2025-11-01 22:00:00.000000','2025-11-01 18:00:00.000000','nao sei','ATIVO','Porto','feira da ladra',NULL),(2,300,'teste3','2025-12-12 12:40:00.000000','2025-12-12 12:12:00.000000','teste1','RASCUNHO','teste5','teste2',NULL);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscricao`
--

DROP TABLE IF EXISTS `inscricao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inscricao` (
  `id_inscricao` int NOT NULL AUTO_INCREMENT,
  `data_inscricao` datetime(6) NOT NULL,
  `estado_inscricao` enum('CANCELADA','CONFIRMADA','PENDENTE') NOT NULL,
  `id_evento` int NOT NULL,
  `id_participante` int NOT NULL,
  PRIMARY KEY (`id_inscricao`),
  KEY `FK59safqf92bkqk0ph66jl09rmj` (`id_evento`),
  KEY `FK8h87iacerw6ulnj4hhpah9kuu` (`id_participante`),
  CONSTRAINT `FK59safqf92bkqk0ph66jl09rmj` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`),
  CONSTRAINT `FK8h87iacerw6ulnj4hhpah9kuu` FOREIGN KEY (`id_participante`) REFERENCES `utilizador` (`id_utilizador`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscricao`
--

LOCK TABLES `inscricao` WRITE;
/*!40000 ALTER TABLE `inscricao` DISABLE KEYS */;
INSERT INTO `inscricao` VALUES (1,'2025-10-28 20:01:43.879668','PENDENTE',2,1),(3,'2025-10-28 20:03:27.204203','CONFIRMADA',1,2);
/*!40000 ALTER TABLE `inscricao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notificacao`
--

DROP TABLE IF EXISTS `notificacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notificacao` (
  `id_notificacao` int NOT NULL AUTO_INCREMENT,
  `data_envio` datetime(6) NOT NULL,
  `estado_notificacao` enum('LIDA','NAO_LIDA') NOT NULL,
  `mensagem` text NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `id_participante` int NOT NULL,
  PRIMARY KEY (`id_notificacao`),
  KEY `FKjhythovprqeqs1n5v6mtryhmm` (`id_participante`),
  CONSTRAINT `FKjhythovprqeqs1n5v6mtryhmm` FOREIGN KEY (`id_participante`) REFERENCES `utilizador` (`id_utilizador`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacao`
--

LOCK TABLES `notificacao` WRITE;
/*!40000 ALTER TABLE `notificacao` DISABLE KEYS */;
INSERT INTO `notificacao` VALUES (1,'2025-10-28 20:23:32.518535','LIDA','ola','Importante',3);
/*!40000 ALTER TABLE `notificacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilizador`
--

DROP TABLE IF EXISTS `utilizador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizador` (
  `id_utilizador` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `estado_utilizador` enum('ATIVO','INATIVO') NOT NULL,
  `nome_utilizador` varchar(100) NOT NULL,
  `senha_utilizador` varchar(255) NOT NULL,
  `tipo_utilizador` enum('ADMIN','ORGANIZADOR','PARTICIPANTE') NOT NULL,
  PRIMARY KEY (`id_utilizador`),
  UNIQUE KEY `UK_eougu510uft70icifeafv6cll` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizador`
--

LOCK TABLES `utilizador` WRITE;
/*!40000 ALTER TABLE `utilizador` DISABLE KEYS */;
INSERT INTO `utilizador` VALUES (1,'joao@upt.pt','INATIVO','joao','joao1234','PARTICIPANTE'),(2,'paulo@upt.pt','ATIVO','paulo','paulo123','PARTICIPANTE'),(3,'admin@upt.pt','ATIVO','admin','admin123','ADMIN'),(4,'organizador@upt.pt','ATIVO','organizador','organizador123','ORGANIZADOR'),(5,'diogo@upt.pt','ATIVO','Diogo','diogo123','PARTICIPANTE');
/*!40000 ALTER TABLE `utilizador` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-28 21:27:53
