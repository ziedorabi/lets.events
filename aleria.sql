-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Nov 04, 2021 at 12:05 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aleria`
--

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
CREATE TABLE IF NOT EXISTS `event` (
  `idEvent` int(8) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) COLLATE utf8_bin NOT NULL,
  `description` varchar(100) COLLATE utf8_bin NOT NULL,
  `date` datetime NOT NULL,
  `address` varchar(50) COLLATE utf8_bin NOT NULL,
  `type` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idEvent`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`idEvent`, `title`, `description`, `date`, `address`, `type`) VALUES
(2, 'My Wedding ', 'Please come thank you ', '2021-10-01 06:22:00', 'Somewhere in Tunis', 'Sortie'),
(13, 'My graduation', 'Come and celebrate with me ! We will have fun.', '2021-11-25 17:28:00', 'Somewhere in tunisia', 'Sortie');

-- --------------------------------------------------------

--
-- Table structure for table `friendrequest`
--

DROP TABLE IF EXISTS `friendrequest`;
CREATE TABLE IF NOT EXISTS `friendrequest` (
  `idRequest` int(10) NOT NULL AUTO_INCREMENT,
  `idUserOne` int(5) NOT NULL,
  `idUserTwo` int(5) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0= not friends ; 1=Friends ; 2 = Declined ; 3: Pending',
  `seen` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idRequest`),
  KEY `idUserOne` (`idUserOne`),
  KEY `idUserTwo` (`idUserTwo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `friendrequest`
--

INSERT INTO `friendrequest` (`idRequest`, `idUserOne`, `idUserTwo`, `status`, `seen`) VALUES
(4, 1, 5, 1, 1),
(5, 1, 3, 1, 1),
(6, 1, 4, 1, 1),
(7, 1, 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `invitation`
--

DROP TABLE IF EXISTS `invitation`;
CREATE TABLE IF NOT EXISTS `invitation` (
  `idInvite` int(8) NOT NULL AUTO_INCREMENT,
  `idEvent` int(5) NOT NULL,
  `idHost` int(5) NOT NULL,
  `idAttendee` int(5) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '3',
  `seen` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idInvite`),
  KEY `hostId` (`idHost`),
  KEY `idAttendee` (`idAttendee`),
  KEY `idEvent` (`idEvent`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `invitation`
--

INSERT INTO `invitation` (`idInvite`, `idEvent`, `idHost`, `idAttendee`, `status`, `seen`) VALUES
(47, 13, 1, 2, 1, 0),
(48, 13, 1, 3, 1, 1),
(49, 13, 1, 4, 1, 1),
(50, 13, 1, 5, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `firstname` varchar(30) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(30) COLLATE utf8_bin NOT NULL,
  `email` varchar(30) COLLATE utf8_bin NOT NULL,
  `phone` varchar(8) COLLATE utf8_bin NOT NULL,
  `address` varchar(50) COLLATE utf8_bin NOT NULL,
  `avatar` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `username`, `password`, `firstname`, `lastname`, `email`, `phone`, `address`, `avatar`) VALUES
(1, 'ziedorabi', 'Motdepasse!1', 'Zied', 'Orabi', 'zied@gmail.com', '24556610', 'Somewhere in tunisia', 'img/man.webp'),
(2, 'ahmedhamdi', 'Motdepasse!1', 'Ahmed', 'Hamdi', 'hamdi@gmail.com', '22334455', 'Somewhere in Sfax', 'img/man.webp'),
(3, 'foulenfouleni', 'Motdepasse!1', 'Fouleni', 'Fouleni', 'foulen@gmail.com', '22334455', 'Somewhere in bizerte', 'img/man.webp'),
(4, 'foulenafouleni', 'Motdepasse!1', 'Foulena', 'Fouleni', 'foulena@gmail.com', '22111444', 'Somewhere in Sfax', 'img/woman.webp'),
(5, 'bobalice', 'Motdepasse!1', 'Bob', 'Alice', 'bob@gmail.com', '98123123', 'Somewhere in Nabeul', 'img/man.webp');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `friendrequest`
--
ALTER TABLE `friendrequest`
  ADD CONSTRAINT `idUserOne` FOREIGN KEY (`idUserOne`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idUserTwo` FOREIGN KEY (`idUserTwo`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `invitation`
--
ALTER TABLE `invitation`
  ADD CONSTRAINT `hostId` FOREIGN KEY (`idHost`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idAttendee` FOREIGN KEY (`idAttendee`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idEvent` FOREIGN KEY (`idEvent`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
