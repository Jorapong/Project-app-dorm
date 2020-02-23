-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 04, 2019 at 01:35 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dormdb1`
--

-- --------------------------------------------------------

--
-- Table structure for table `dorm`
--

CREATE TABLE `dorm` (
  `dorm_id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  `dorm_name` varchar(50) DEFAULT NULL,
  `dorm_amoutRoom` int(3) DEFAULT NULL,
  `dorm_img` varchar(1000) NOT NULL,
  `rate_price` int(5) DEFAULT NULL,
  `map` varchar(1000) DEFAULT NULL,
  `zone` varchar(20) DEFAULT NULL,
  `tel` varchar(10) DEFAULT NULL,
  `page` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dorm`
--

INSERT INTO `dorm` (`dorm_id`, `user_id`, `dorm_name`, `dorm_amoutRoom`, `dorm_img`, `rate_price`, `map`, `zone`, `tel`, `page`) VALUES
(2001, 1001, 'กังสดารอพาร์ทเม้นท์', 20, 'https://sv1.picz.in.th/images/2019/11/04/giDYHQ.jpg', 3500, 'https://sv1.picz.in.th/images/2019/11/04/giD4SS.png', 'Kungsadan', '0954862156', 'KKU'),
(2002, 1002, 'ริเวนเดล รีเจ้นท์', 15, 'https://sv1.picz.in.th/images/2019/11/04/giDNgn.jpg', 4000, 'https://sv1.picz.in.th/images/2019/11/04/giDBCg.png', 'Kungsadan', '0940139961', 'KKu'),
(2003, 1001, 'ลิฟวิ่ง เพลส', 30, 'https://sv1.picz.in.th/images/2019/11/04/giDfIW.jpg', 6500, 'https://sv1.picz.in.th/images/2019/11/04/giDkt2.png', 'Kungsadan', '0987878925', 'KKu'),
(2004, 1001, 'อังกาบ', 20, 'https://sv1.picz.in.th/images/2019/11/04/giDZSb.jpg', 4000, 'https://sv1.picz.in.th/images/2019/11/04/giDcif.png', 'Kungsadan', '0911223344', 'KKU'),
(2005, 1001, 'เฟอร์รี่พาร์ค อิ้น', 15, 'https://sv1.picz.in.th/images/2019/11/04/giDgCa.jpg', 3500, 'https://sv1.picz.in.th/images/2019/11/04/giDiIq.png', 'LungMor', '0984545235', 'KKU'),
(2006, 1002, 'ฟอร์จูน แมนชั่น', 15, 'https://i.imgur.com/qb3e80C.jpg', 3500, 'https://sv1.picz.in.th/images/2019/11/04/giDQo8.png', 'LungMor', '0874568925', 'KKU'),
(2007, 1001, 'โกลเด้น วิว', 25, 'https://sv1.picz.in.th/images/2019/11/04/giDOVZ.jpg', 4500, 'https://sv1.picz.in.th/images/2019/11/04/giDjSI.png', 'LungMor', '0984563256', 'www'),
(2008, 1002, 'วังบัว', 25, 'https://sv1.picz.in.th/images/2019/11/04/giDout.jpg', 2800, 'https://sv1.picz.in.th/images/2019/11/04/giDDIe.png', 'Columbo', '0854625985', 'KKU'),
(2009, 1001, 'สุทธิลักษณ์ เรสซิเด้นท์', 30, 'https://sv1.picz.in.th/images/2019/11/04/giDPwl.jpg', 3500, 'https://sv1.picz.in.th/images/2019/11/04/giDsDk.png', 'Columbo', '0985462189', 'KKU'),
(2010, 1002, 'ชัยวัฒนา', 25, 'https://sv1.picz.in.th/images/2019/11/04/giD2Jv.jpg', 3500, 'https://sv1.picz.in.th/images/2019/11/04/giDuhE.png', 'Columbo', '085462598', 'KKU');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `room_id` int(4) NOT NULL,
  `dorm_id` int(4) NOT NULL,
  `room_name` varchar(50) NOT NULL,
  `price` int(5) DEFAULT NULL,
  `avariable` int(5) DEFAULT NULL,
  `amout_room` int(3) DEFAULT NULL,
  `room_img` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_id`, `dorm_id`, `room_name`, `price`, `avariable`, `amout_room`, `room_img`) VALUES
(3001, 2001, 'ห้องแอร์พิเศษ', 3500, 12, 15, 'https://sv1.picz.in.th/images/2019/11/04/giDVun.jpg'),
(3002, 2002, 'ห้องพัดลม', 3500, 25, 25, 'https://sv1.picz.in.th/images/2019/11/04/giDXMg.jpg'),
(3003, 2003, 'ห้องแอร์', 6500, 25, 25, 'https://sv1.picz.in.th/images/2019/11/04/giDvD2.jpg'),
(3004, 2004, 'ห้องแอร์', 4000, 15, 15, 'https://sv1.picz.in.th/images/2019/11/04/giDGL1.jpg'),
(3005, 2005, 'ห้องแอร์', 3500, 20, 20, 'https://sv1.picz.in.th/images/2019/11/04/giDLhy.jpg'),
(3006, 2006, 'ห้องพัดลม', 3500, 25, 25, 'https://sv1.picz.in.th/images/2019/11/04/giDeED.jpg'),
(3007, 2007, 'ห้องแอร์พิเศษ', 4500, 20, 20, 'https://sv1.picz.in.th/images/2019/11/04/giDFYJ.jpg'),
(3008, 3008, 'ห้องพัดลม', 2800, 25, 25, 'https://sv1.picz.in.th/images/2019/11/04/giDIRb.jpg'),
(3009, 2009, 'ห้องแอร์', 3500, 25, 25, 'https://sv1.picz.in.th/images/2019/11/04/giAWMa.jpg'),
(3010, 2010, 'ห้องแอร์', 3500, 20, 20, 'https://sv1.picz.in.th/images/2019/11/04/giA01q.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `room_detail`
--

CREATE TABLE `room_detail` (
  `room_id` int(4) NOT NULL,
  `bed` varchar(10) DEFAULT NULL,
  `air` varchar(4) DEFAULT NULL,
  `fan` varchar(4) DEFAULT NULL,
  `water_heater` varchar(4) DEFAULT NULL,
  `bill` varchar(4) DEFAULT NULL,
  `insure` varchar(4) DEFAULT NULL,
  `internet` varchar(4) DEFAULT NULL,
  `animal` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `room_detail`
--

INSERT INTO `room_detail` (`room_id`, `bed`, `air`, `fan`, `water_heater`, `bill`, `insure`, `internet`, `animal`) VALUES
(3001, 'two bed', 'no', 'yes', 'yes', '20/4', '6000', 'yes', 'yes'),
(3002, 'two bed', '-', 'yes', 'yes', '20/6', '2500', 'yes', '-'),
(3003, 'single', 'yes', 'yes', 'yes', '23/7', '3000', 'yes', '-'),
(3004, 'single', 'yes', 'yes', 'yes', '25/9', '3500', 'yes', 'yes'),
(3005, 'single', 'yes', 'yes', 'yes', '29/6', '3000', 'yes', 'yes'),
(3006, 'two bed', '-', 'yes', 'yes', '19/5', '2500', 'yes', '-'),
(3007, 'single', 'yes', 'yes', 'yes', '27/4', '2500', 'yes', 'yes'),
(3008, 'single', '-', 'yes', 'yes', '30/5', '3000', 'yes', '-'),
(3009, 'single', 'yes', 'yes', 'yes', '34/5', '3000', 'yes', 'yes'),
(3010, 'two bed', 'yes', 'yes', 'yes', '15/3', '3000', 'yes', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(4) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `type` int(2) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `type`, `email`, `name`) VALUES
(1000, 'joey', '123456', 0, 'joey@gmail.com', 'jo'),
(1001, 'somdad', '1234', 1, 'somdad@gmail.com', 'somdad rakmanee'),
(1002, 'test', 'test', 1, 'joeiboy.200@gmail.com', 'somdad rakmanee');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dorm`
--
ALTER TABLE `dorm`
  ADD PRIMARY KEY (`dorm_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`room_id`);

--
-- Indexes for table `room_detail`
--
ALTER TABLE `room_detail`
  ADD PRIMARY KEY (`room_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dorm`
--
ALTER TABLE `dorm`
  MODIFY `dorm_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2011;

--
-- AUTO_INCREMENT for table `room`
--
ALTER TABLE `room`
  MODIFY `room_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3012;

--
-- AUTO_INCREMENT for table `room_detail`
--
ALTER TABLE `room_detail`
  MODIFY `room_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3012;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1004;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
