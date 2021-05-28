-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2021 at 06:58 PM
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
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `classroom`
--

INSERT INTO `classroom` (`id`, `classroom_name`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES
(1, '1a', 'suhartono', '2021-05-28 09:18:00', NULL, NULL);

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
(1, 1, 1, 85),
(2, 1, 2, 70),
(3, 1, 3, 70),
(4, 1, 4, 79),
(5, 1, 5, 70),
(6, 1, 6, 90),
(7, 2, 1, 97),
(8, 2, 2, 93),
(9, 2, 3, 88),
(10, 2, 4, 86),
(11, 2, 5, 93),
(12, 2, 6, 92),
(13, 3, 1, 80),
(14, 3, 2, 86),
(15, 3, 3, 78),
(16, 3, 4, 77),
(17, 3, 5, 84),
(18, 3, 6, 86),
(19, 4, 1, 88),
(20, 4, 2, 78),
(21, 4, 3, 75),
(22, 4, 4, 76),
(23, 4, 5, 78),
(24, 4, 6, 90),
(25, 5, 1, 87),
(26, 5, 2, 84),
(27, 5, 3, 73),
(28, 5, 4, 77),
(29, 5, 5, 84),
(30, 5, 6, 88),
(31, 6, 1, 91),
(32, 6, 2, 92),
(33, 6, 3, 76),
(34, 6, 4, 76),
(35, 6, 5, 92),
(36, 6, 6, 92),
(37, 7, 1, 86),
(38, 7, 2, 70),
(39, 7, 3, 70),
(40, 7, 4, 70),
(41, 7, 5, 70),
(42, 7, 6, 92),
(43, 8, 1, 84),
(44, 8, 2, 82),
(45, 8, 3, 77),
(46, 8, 4, 71),
(47, 8, 5, 82),
(48, 8, 6, 92),
(49, 9, 1, 84),
(50, 9, 2, 82),
(51, 9, 3, 73),
(52, 9, 4, 83),
(53, 9, 5, 82),
(54, 9, 6, 92),
(55, 10, 1, 93),
(56, 10, 2, 88),
(57, 10, 3, 83),
(58, 10, 4, 86),
(59, 10, 5, 88),
(60, 10, 6, 92),
(61, 11, 1, 87),
(62, 11, 2, 70),
(63, 11, 3, 69),
(64, 11, 4, 69),
(65, 11, 5, 70),
(66, 11, 6, 80),
(67, 12, 1, 80),
(68, 12, 2, 70),
(69, 12, 3, 69),
(70, 12, 4, 68),
(71, 12, 5, 70),
(72, 12, 6, 75),
(73, 13, 1, 87),
(74, 13, 2, 70),
(75, 13, 3, 70),
(76, 13, 4, 80),
(77, 13, 5, 70),
(78, 13, 6, 92),
(79, 14, 1, 96),
(80, 14, 2, 90),
(81, 14, 3, 85),
(82, 14, 4, 82),
(83, 14, 5, 90),
(84, 14, 6, 92),
(85, 15, 1, 87),
(86, 15, 2, 87),
(87, 15, 3, 85),
(88, 15, 4, 81),
(89, 15, 5, 87),
(90, 15, 6, 90),
(91, 16, 1, 90),
(92, 16, 2, 91),
(93, 16, 3, 84),
(94, 16, 4, 81),
(95, 16, 5, 91),
(96, 16, 6, 92),
(97, 17, 1, 87),
(98, 17, 2, 70),
(99, 17, 3, 80),
(100, 17, 4, 82),
(101, 17, 5, 70),
(102, 17, 6, 90),
(103, 18, 1, 85),
(104, 18, 2, 70),
(105, 18, 3, 76),
(106, 18, 4, 83),
(107, 18, 5, 70),
(108, 18, 6, 92),
(109, 19, 1, 80),
(110, 19, 2, 70),
(111, 19, 3, 69),
(112, 19, 4, 72),
(113, 19, 5, 70),
(114, 19, 6, 90),
(115, 20, 1, 89),
(116, 20, 2, 88),
(117, 20, 3, 85),
(118, 20, 4, 82),
(119, 20, 5, 88),
(120, 20, 6, 92),
(121, 21, 1, 91),
(122, 21, 2, 93),
(123, 21, 3, 80),
(124, 21, 4, 80),
(125, 21, 5, 93),
(126, 21, 6, 92),
(127, 22, 1, 89),
(128, 22, 2, 91),
(129, 22, 3, 80),
(130, 22, 4, 84),
(131, 22, 5, 91),
(132, 22, 6, 92),
(133, 23, 1, 90),
(134, 23, 2, 90),
(135, 23, 3, 85),
(136, 23, 4, 82),
(137, 23, 5, 90),
(138, 23, 6, 92),
(139, 24, 1, 91),
(140, 24, 2, 94),
(141, 24, 3, 83),
(142, 24, 4, 84),
(143, 24, 5, 94),
(144, 24, 6, 92),
(145, 25, 1, 80),
(146, 25, 2, 85),
(147, 25, 3, 73),
(148, 25, 4, 79),
(149, 25, 5, 85),
(150, 25, 6, 90),
(151, 26, 1, 88),
(152, 26, 2, 77),
(153, 26, 3, 80),
(154, 26, 4, 83),
(155, 26, 5, 77),
(156, 26, 6, 92),
(157, 27, 1, 85),
(158, 27, 2, 90),
(159, 27, 3, 78),
(160, 27, 4, 80),
(161, 27, 5, 90),
(162, 27, 6, 88),
(163, 28, 1, 80),
(164, 28, 2, 70),
(165, 28, 3, 72),
(166, 28, 4, 72),
(167, 28, 5, 70),
(168, 28, 6, 92),
(169, 29, 1, 89),
(170, 29, 2, 70),
(171, 29, 3, 78),
(172, 29, 4, 75),
(173, 29, 5, 72),
(174, 29, 6, 90),
(175, 30, 1, 82),
(176, 30, 2, 84),
(177, 30, 3, 88),
(178, 30, 4, 86),
(179, 30, 5, 84),
(180, 30, 6, 80),
(181, 31, 1, 89),
(182, 31, 2, 70),
(183, 31, 3, 85),
(184, 31, 4, 82),
(185, 31, 5, 70),
(186, 31, 6, 92),
(187, 32, 1, 79),
(188, 32, 2, 90),
(189, 32, 3, 73),
(190, 32, 4, 70),
(191, 32, 5, 90),
(192, 32, 6, 90),
(193, 33, 1, 85),
(194, 33, 2, 90),
(195, 33, 3, 83),
(196, 33, 4, 81),
(197, 33, 5, 90),
(198, 33, 6, 90),
(199, 34, 1, 86),
(200, 34, 2, 90),
(201, 34, 3, 75),
(202, 34, 4, 69),
(203, 34, 5, 82),
(204, 34, 6, 92),
(205, 35, 1, 86),
(206, 35, 2, 82),
(207, 35, 3, 80),
(208, 35, 4, 79),
(209, 35, 5, 78),
(210, 35, 6, 92),
(211, 36, 1, 90),
(212, 36, 2, 78),
(213, 36, 3, 91),
(214, 36, 4, 89),
(215, 36, 5, 91),
(216, 36, 6, 92),
(217, 37, 1, 80),
(218, 37, 2, 91),
(219, 37, 3, 80),
(220, 37, 4, 78),
(221, 37, 5, 90),
(222, 37, 6, 90),
(223, 38, 1, 97),
(224, 38, 2, 90),
(225, 38, 3, 87),
(226, 38, 4, 85),
(227, 38, 5, 90),
(228, 38, 6, 92),
(229, 39, 1, 93),
(230, 39, 2, 90),
(231, 39, 3, 89),
(232, 39, 4, 83),
(233, 39, 5, 90),
(234, 39, 6, 92),
(235, 40, 1, 86),
(236, 40, 2, 90),
(237, 40, 3, 70),
(238, 40, 4, 80),
(239, 40, 5, 70),
(240, 40, 6, 92),
(241, 41, 1, 90),
(242, 41, 2, 80),
(243, 41, 3, 80),
(244, 41, 4, 80),
(245, 41, 5, 89),
(246, 41, 6, 92),
(247, 42, 1, 93),
(248, 42, 2, 86),
(249, 42, 3, 80),
(250, 42, 4, 82),
(251, 42, 5, 86),
(252, 42, 6, 92);

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
(1, 'Abraham', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(2, 'Alexander', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(3, 'Ambrose', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(4, 'Arya', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(5, 'Caroline', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(6, 'Christabel', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(7, 'Cinta', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(8, 'Clarisa', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(9, 'Claudya', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(10, 'Della', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(11, 'Evelyn', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(12, 'Faylicia', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(13, 'Gicele', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(14, 'Giftan', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(15, 'Glory', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(16, 'Guruh', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(17, 'Hanna', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(18, 'Ima', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(19, 'Indra', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(20, 'Jane', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(21, 'Janwell', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(22, 'Jonathan', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(23, 'Kairos', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(24, 'Keireyna', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(25, 'Marchel', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(26, 'Maria', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(27, 'Maryon', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(28, 'Michael', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(29, 'Mikhaya', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(30, 'Nadine', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(31, 'Nathanael', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(32, 'Nazaretha', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(33, 'Pricillla', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(34, 'Reino', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(35, 'Rivan', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(36, 'Samuel', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(37, 'Sarah', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(38, 'Stefani', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(39, 'Steven', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(40, 'Tessalonika', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(41, 'Valthy', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(42, 'Vania', 'suhartono', '2021-05-28 09:20:46', NULL, NULL),
(43, 'Suyanto', 'suhartono', '2021-05-28 09:20:46', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `student_classroom`
--

CREATE TABLE `student_classroom` (
  `id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `classroom_id` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_classroom`
--

INSERT INTO `student_classroom` (`id`, `student_id`, `classroom_id`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES
(1, 1, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(2, 2, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(3, 3, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(4, 4, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(5, 5, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(6, 6, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(7, 7, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(8, 8, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(9, 9, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(10, 10, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(11, 11, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(12, 12, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(13, 13, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(14, 14, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(15, 15, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(16, 16, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(17, 17, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(18, 18, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(19, 19, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(20, 20, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(21, 21, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(22, 22, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(23, 23, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(24, 24, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(25, 25, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(26, 26, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(27, 27, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(28, 28, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(29, 29, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(30, 30, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(31, 31, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(32, 32, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(33, 33, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(34, 34, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(35, 35, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(36, 36, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(37, 37, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(38, 38, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(39, 39, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(40, 40, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(41, 41, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL),
(42, 42, 1, 'suhartono', '2021-05-28 09:29:52', NULL, NULL);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `enroll`
--
ALTER TABLE `enroll`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=253;

--
-- AUTO_INCREMENT for table `lesson`
--
ALTER TABLE `lesson`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `student_classroom`
--
ALTER TABLE `student_classroom`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

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
