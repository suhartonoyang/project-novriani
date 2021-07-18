-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 18, 2021 at 03:21 PM
-- Server version: 10.4.10-MariaDB
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
-- Database: `project-novriani`
--

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE `classroom` (
  `id` bigint(20) NOT NULL,
  `classroom_name` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `classroom`
--

INSERT INTO `classroom` (`id`, `classroom_name`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES
(80, '1e', 'suhartono', '2021-07-18 19:37:04', NULL, NULL),
(81, '2a', 'suhartono', '2021-07-18 19:37:04', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `enroll`
--

CREATE TABLE `enroll` (
  `id` bigint(20) NOT NULL,
  `student_classroom_id` bigint(20) NOT NULL,
  `lesson_id` bigint(20) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `enroll`
--

INSERT INTO `enroll` (`id`, `student_classroom_id`, `lesson_id`, `score`) VALUES
(1115, 193, 1, 100),
(1116, 193, 2, 90),
(1117, 193, 3, 70),
(1118, 193, 4, 95),
(1119, 193, 5, 70),
(1120, 193, 6, 90),
(1121, 194, 1, 70),
(1122, 194, 2, 82),
(1123, 194, 3, 77),
(1124, 194, 4, 78),
(1125, 194, 5, 89),
(1126, 194, 6, 100),
(1127, 195, 1, 10),
(1128, 195, 2, 20),
(1129, 195, 3, 40),
(1130, 195, 4, 50),
(1131, 195, 5, 10),
(1132, 195, 6, 100),
(1133, 196, 1, 12),
(1134, 196, 2, 70),
(1135, 196, 3, 80),
(1136, 196, 4, 90),
(1137, 196, 5, 100),
(1138, 196, 6, 100);

-- --------------------------------------------------------

--
-- Table structure for table `lesson`
--

CREATE TABLE `lesson` (
  `id` bigint(11) NOT NULL,
  `lesson_name` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `lesson`
--

INSERT INTO `lesson` (`id`, `lesson_name`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES
(1, 'Agama', 'suhartono', '2021-05-28 09:28:02', NULL, NULL),
(2, 'PKN', 'suhartono', '2021-05-28 09:28:02', NULL, NULL),
(3, 'B Indo', 'suhartono', '2021-05-28 09:28:02', NULL, '2021-05-28 09:28:02'),
(4, 'MTK', 'suhartono', '2021-05-28 09:28:02', NULL, '2021-05-28 09:28:02'),
(5, 'SBDP', 'suhartono', '2021-05-28 09:28:02', NULL, '2021-05-28 09:28:02'),
(6, 'PJS', 'suhartono', '2021-05-28 09:28:02', NULL, '2021-05-28 09:28:02');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` bigint(11) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `student_name`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES
(196, 'Carex Aloe', 'suhartono', '2021-07-18 19:37:04', NULL, NULL),
(197, 'Nature Rep', 'suhartono', '2021-07-18 19:37:04', NULL, NULL),
(198, 'Eliminator ', 'suhartono', '2021-07-18 19:37:04', NULL, NULL),
(199, 'Orico', 'suhartono', '2021-07-18 19:37:04', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student_classroom`
--

CREATE TABLE `student_classroom` (
  `id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `classroom_id` bigint(20) NOT NULL,
  `centroid_number` int(11) DEFAULT 0,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_classroom`
--

INSERT INTO `student_classroom` (`id`, `student_id`, `classroom_id`, `centroid_number`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES
(193, 196, 80, 1, 'suhartono', '2021-07-18 19:37:04', NULL, NULL),
(194, 197, 80, 0, 'suhartono', '2021-07-18 19:37:04', NULL, NULL),
(195, 198, 81, 2, 'suhartono', '2021-07-18 19:37:04', NULL, NULL),
(196, 199, 81, 0, 'suhartono', '2021-07-18 19:37:04', NULL, NULL);

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
  ADD PRIMARY KEY (`id`),
  ADD KEY `enroll_fk1` (`lesson_id`),
  ADD KEY `enroll_fk2` (`student_classroom_id`);

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
-- Indexes for table `student_classroom`
--
ALTER TABLE `student_classroom`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sc_fk1` (`student_id`),
  ADD KEY `sc_fk2` (`classroom_id`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT for table `enroll`
--
ALTER TABLE `enroll`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1139;

--
-- AUTO_INCREMENT for table `lesson`
--
ALTER TABLE `lesson`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=200;

--
-- AUTO_INCREMENT for table `student_classroom`
--
ALTER TABLE `student_classroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=197;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `enroll`
--
ALTER TABLE `enroll`
  ADD CONSTRAINT `enroll_fk1` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`),
  ADD CONSTRAINT `enroll_fk2` FOREIGN KEY (`student_classroom_id`) REFERENCES `student_classroom` (`id`);

--
-- Constraints for table `student_classroom`
--
ALTER TABLE `student_classroom`
  ADD CONSTRAINT `sc_fk1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `sc_fk2` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
