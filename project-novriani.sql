-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2021 at 12:03 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project-novriani`
--

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE `classroom` (
  `id` bigint(20) NOT NULL,
  `classroom_name` varchar(255) NOT NULL,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `enroll`
--

CREATE TABLE `enroll` (
  `id` bigint(20) NOT NULL,
  `studentclassroom_id` bigint(20) NOT NULL,
  `lesson_id` bigint(20) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `lesson`
--

CREATE TABLE `lesson` (
  `id` bigint(11) NOT NULL,
  `lesson_name` varchar(255) NOT NULL,
  `created_by` bigint(20) NOT NULL,
  `created_date` date NOT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` bigint(11) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `studentclassroom`
--

CREATE TABLE `studentclassroom` (
  `id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `classroom_id` bigint(20) NOT NULL,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` bigint(20) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `full_name`, `created_date`, `updated_date`) VALUES
(2, 'suhartono', '$2a$10$wibZ5KKK8YEzIXqW5xTqrOvZ.Uheh8xRL66tiv4pobQyODRJq7K.S', 'Suhartono', '2021-05-27 15:48:12', NULL),
(4, 'suhartono2', '$2a$10$htOZDIT2YGOK7xoop0XoBe9yFjA/1pzHYWxsCcNNqSczSuzPhDQta', 'Suhartono', '2021-05-27 16:02:26', NULL),
(6, 'suhartono3', '$2a$10$gum6fKxR0rfVaCwPUgFH4.MydO9vyBFSDVNETF.S1Vw236ECyWzYW', 'Suhartono', '2021-05-27 16:02:41', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `enroll`
--
ALTER TABLE `enroll`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `lesson`
--
ALTER TABLE `lesson`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studentclassroom`
--
ALTER TABLE `studentclassroom`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`updated_date`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classroom`
--
ALTER TABLE `classroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `enroll`
--
ALTER TABLE `enroll`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `lesson`
--
ALTER TABLE `lesson`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `studentclassroom`
--
ALTER TABLE `studentclassroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
