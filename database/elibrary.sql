-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2018 at 05:58 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `elibrary`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminmaster`
--

CREATE TABLE `adminmaster` (
  `id` int(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adminmaster`
--

INSERT INTO `adminmaster` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `bookmaster`
--

CREATE TABLE `bookmaster` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `author` varchar(50) NOT NULL,
  `qty` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookmaster`
--

INSERT INTO `bookmaster` (`id`, `name`, `author`, `qty`) VALUES
(100, 'J2EE', 'Niraj Kumar', 250),
(101, 'DS', 'Saurabh', 100);

-- --------------------------------------------------------

--
-- Table structure for table `libmaster`
--

CREATE TABLE `libmaster` (
  `id` int(10) NOT NULL,
  `studid` int(10) NOT NULL,
  `studname` varchar(20) NOT NULL,
  `bookid` int(10) NOT NULL,
  `bookname` varchar(20) NOT NULL,
  `author` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `duedate` date NOT NULL,
  `status` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `libmaster`
--

INSERT INTO `libmaster` (`id`, `studid`, `studname`, `bookid`, `bookname`, `author`, `date`, `duedate`, `status`) VALUES
(1, 100, 'Arun', 100, 'J2EE', 'Niraj Kumar', '2018-11-19', '2018-11-30', 'R'),
(2, 101, 'Rohit Soni', 101, 'DS', 'SAurabh', '2018-11-19', '2018-11-30', 'I'),
(3, 102, 'Gaurav', 312, 'java and networking', 'Dada', '2018-11-19', '2018-11-30', 'I');

-- --------------------------------------------------------

--
-- Table structure for table `studentmaster`
--

CREATE TABLE `studentmaster` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `branch` varchar(20) NOT NULL,
  `sem` varchar(10) NOT NULL,
  `mob` bigint(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `studentmaster`
--

INSERT INTO `studentmaster` (`id`, `name`, `branch`, `sem`, `mob`, `email`, `address`) VALUES
(100, 'Avinash Singh', 'MCA', 'Sem-VI', 9651156251, 'joinavi.net@gmail.com', 'Ghazipur            \r\n                    \r\n                   \r\n                    '),
(101, 'Rohit Soni', 'MCA', 'Sem-V', 1234, 'rohitsoni912266@gmail.com', 'Patana\r\n '),
(102, 'Gaurav', 'MCA', 'Sem-VI', 9005507033, 'gauravpaatni@gmail.com', ' Lucknow');

-- --------------------------------------------------------

--
-- Table structure for table `usermaster`
--

CREATE TABLE `usermaster` (
  `id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `adhar` bigint(12) NOT NULL,
  `mob` bigint(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usermaster`
--

INSERT INTO `usermaster` (`id`, `name`, `adhar`, `mob`, `email`, `password`, `address`) VALUES
(1, 'Avinash Kumar Singh', 220460343477, 9651156251, 'joinavi.net@gmail.com', '1234', 'Patana\r\n                        '),
(2, 'Hemant', 140251555, 9528555425, 'gauravpaatni@gmail.com', '1234', 'lucknow');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `adminmaster`
--
ALTER TABLE `adminmaster`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bookmaster`
--
ALTER TABLE `bookmaster`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `libmaster`
--
ALTER TABLE `libmaster`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `studentmaster`
--
ALTER TABLE `studentmaster`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usermaster`
--
ALTER TABLE `usermaster`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `adminmaster`
--
ALTER TABLE `adminmaster`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bookmaster`
--
ALTER TABLE `bookmaster`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=313;

--
-- AUTO_INCREMENT for table `libmaster`
--
ALTER TABLE `libmaster`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `studentmaster`
--
ALTER TABLE `studentmaster`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15141;

--
-- AUTO_INCREMENT for table `usermaster`
--
ALTER TABLE `usermaster`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
