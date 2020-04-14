-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 14, 2020 at 10:54 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `math_solutions`
--
DROP DATABASE IF EXISTS `math_solutions`;
CREATE DATABASE IF NOT EXISTS `math_solutions` DEFAULT CHARACTER SET utf8 COLLATE utf8_swedish_ci;
USE `math_solutions`;

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `bookID` varchar(20) NOT NULL,
  `bookName` varchar(80) NOT NULL,
  `courseID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `chapter`
--

CREATE TABLE `chapter` (
  `chapterID` varchar(20) NOT NULL,
  `bookID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `courseID` varchar(20) NOT NULL,
  `courseName` varchar(80) NOT NULL,
  `courseArea` enum('Math','Physics') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`courseID`, `courseName`, `courseArea`) VALUES
('Analys', 'Analys', 'Math'),
('Diskret', 'Diskret', 'Math'),
('Fler dim', 'Fler dim', 'Math'),
('Fysik 2 Ellära', 'Fysik 2 Ellära', 'Physics'),
('Fysik 3 våglära', 'Fysik 3 våglära', 'Physics'),
('Fysik grundkurs', 'Fysik grundkurs', 'Physics'),
('linear Algebra', 'linear Algebra', 'Math'),
('Matte grundkurs', 'Matte grundkurs', 'Math'),
('Statestik', 'Statestik', 'Math');

-- --------------------------------------------------------

--
-- Table structure for table `solution`
--

CREATE TABLE `solution` (
  `solutionID` varchar(20) NOT NULL,
  `taskID` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `upvote` int(11) NOT NULL,
  `downvote` int(11) NOT NULL,
  `username` varchar(80) NOT NULL,
  `tags` varchar(20) NOT NULL,
  `comment` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `taskID` varchar(20) NOT NULL,
  `chapterID` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(80) NOT NULL,
  `firstName` varchar(60) NOT NULL,
  `lastName` varchar(60) NOT NULL,
  `email` varchar(80) NOT NULL,
  `education` varchar(80) NOT NULL,
  `password` varchar(30) NOT NULL,
  `userType` enum('Student','Teacher','Admin') CHARACTER SET utf8 COLLATE utf8_swedish_ci NOT NULL DEFAULT 'Student'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `firstName`, `lastName`, `email`, `education`, `password`, `userType`) VALUES
('123', 'e', 'e', 'Test@dds.se', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('ad', 'r', 'r', 'z@x.md', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('ADa', 'r', 'r', 'rawd@x.mjgf', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('Eda', 'r', 'r', 'z@x.m', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('Edaq', 'e', 'e', 'adaw@x.m', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('Emil123', 'Emil', 'Einerskog', 'emil@einerskog.se', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('MalinKronvall', 'Malin', 'Kronvall', 'Malin.k98@gmail.com', 'industriell ekonomi', 'MalinKronvall1', 'Admin'),
('T', 'e', 'e', 'wd@xx.m', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('Tesdt', 'e', 'e', 'dawdad@ddda.m', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('Tess', 'e', 'e', 'e@dd.mj', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('Test1', 'e', 'e', 'Test@hotmail.se', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('tr', 'e', 'e', 'e@z.m', 'indstriell ekonomi', 'Abcd1234', 'Student'),
('Ttt', 'e', 'e', 'Ts1@asd.se', 'indstriell ekonomi', 'Abcd1234', 'Student');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bookID`),
  ADD KEY `Fk2` (`courseID`);

--
-- Indexes for table `chapter`
--
ALTER TABLE `chapter`
  ADD PRIMARY KEY (`chapterID`),
  ADD KEY `Fk3` (`bookID`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`courseID`);

--
-- Indexes for table `solution`
--
ALTER TABLE `solution`
  ADD PRIMARY KEY (`solutionID`),
  ADD KEY `FK` (`username`),
  ADD KEY `FK5` (`taskID`);

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`taskID`),
  ADD KEY `fk4` (`chapterID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `Fk2` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`);

--
-- Constraints for table `chapter`
--
ALTER TABLE `chapter`
  ADD CONSTRAINT `Fk3` FOREIGN KEY (`bookID`) REFERENCES `book` (`bookID`);

--
-- Constraints for table `solution`
--
ALTER TABLE `solution`
  ADD CONSTRAINT `FK` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  ADD CONSTRAINT `FK5` FOREIGN KEY (`taskID`) REFERENCES `task` (`taskID`);

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `fk4` FOREIGN KEY (`chapterID`) REFERENCES `chapter` (`chapterID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
