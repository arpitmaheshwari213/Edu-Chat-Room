-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2016 at 05:30 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `discussforumdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `session_master`
--

CREATE TABLE IF NOT EXISTS `session_master` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL,
  `duration` varchar(20) NOT NULL,
  `sess_date` date NOT NULL,
  `sess_time` varchar(20) NOT NULL,
  `status` int(10) NOT NULL,
  `isavail` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `session_master`
--

INSERT INTO `session_master` (`id`, `user_id`, `title`, `description`, `duration`, `sess_date`, `sess_time`, `status`, `isavail`) VALUES
(1, 2, 'Inner Classes', 'A Prominent Topic of JAVA', '2 hrs', '2016-05-24', '3:00 pm', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `session_transaction`
--

CREATE TABLE IF NOT EXISTS `session_transaction` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `sess_id` varchar(10) NOT NULL,
  `user_id` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `desc` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_master`
--

CREATE TABLE IF NOT EXISTS `user_master` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `contact` int(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `regOn` date NOT NULL,
  `regBy` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user_master`
--

INSERT INTO `user_master` (`id`, `loginId`, `password`, `firstName`, `lastName`, `Email`, `contact`, `type`, `regOn`, `regBy`) VALUES
(1, 'admin001', 'abc', 'Admin', 'Forum', 'firum.admin@mail.com', 123456, 'Admin', '2016-05-16', '-1'),
(2, 'Stud001', 'abc', 'First', 'Last', 'first@gmail.com', 123456789, 'Student', '2016-04-16', '-1');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
