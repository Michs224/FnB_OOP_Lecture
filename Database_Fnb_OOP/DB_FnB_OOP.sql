-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2023 at 04:02 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_fnb_oop`
--

-- --------------------------------------------------------

--
-- Table structure for table `beverages`
--

CREATE TABLE `beverages` (
  `beverage_id` int(11) NOT NULL,
  `beverage_name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `type` varchar(50) NOT NULL,
  `is_carbonated` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `beverages`
--

INSERT INTO `beverages` (`beverage_id`, `beverage_name`, `price`, `type`, `is_carbonated`) VALUES
(1, 'Coca Cola', 10000, 'SOFT_DRINK', 1),
(2, 'Apple Juice', 12000, 'JUICE', 0),
(3, 'Espresso', 15000, 'COFFEE', 0),
(4, 'Green Tea', 13000, 'TEA', 0),
(5, 'Lemonade', 11000, 'SOFT_DRINK', 1),
(6, 'Orange Juice', 12000, 'JUICE', 0),
(7, 'Latte', 17000, 'COFFEE', 0),
(8, 'Black Tea', 14000, 'TEA', 0),
(9, 'Sprite', 10000, 'SOFT_DRINK', 1),
(10, 'Mango Smoothie', 18000, 'JUICE', 0);

-- --------------------------------------------------------

--
-- Table structure for table `beverage_order_details`
--

CREATE TABLE `beverage_order_details` (
  `order_id` int(11) NOT NULL,
  `beverage_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `beverage_order_details`
--

INSERT INTO `beverage_order_details` (`order_id`, `beverage_id`, `quantity`) VALUES
(9, 3, 1),
(10, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `cashiers`
--

CREATE TABLE `cashiers` (
  `cashier_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `cashier_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cashiers`
--

INSERT INTO `cashiers` (`cashier_id`, `username`, `password`, `cashier_name`) VALUES
(17, 'Mich224', 'qwaszx12@#', 'Michael Geraldin Wijaya'),
(18, 'Mich24', 'qwaszx12@#', 'Michael Gerl'),
(19, 'Zoro11', 'enma123!', 'Roronoa Zoro');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `customer_name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `balance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `phone`, `customer_name`, `address`, `balance`) VALUES
(1, '081234567890', 'Michael Geraldin WW', 'Kos Bu Joko Kamar A', 886000),
(2, '080987654321', 'Luffy', 'East Blue', 125000);

-- --------------------------------------------------------

--
-- Table structure for table `foods`
--

CREATE TABLE `foods` (
  `food_id` int(11) NOT NULL,
  `food_name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `category` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `foods`
--

INSERT INTO `foods` (`food_id`, `food_name`, `price`, `category`) VALUES
(1, 'Spaghetti Carbonara', 30000, 'MAIN_COURSE'),
(2, 'French Fries', 15000, 'APPETIZER'),
(3, 'Chicken Wings', 20000, 'APPETIZER'),
(4, 'Chocolate Cake', 35000, 'DESSERT'),
(5, 'Pancake', 25000, 'DESSERT'),
(6, 'Caesar Salad', 22000, 'APPETIZER'),
(7, 'Beef Burger', 28000, 'MAIN_COURSE'),
(8, 'Ice Cream Sundae', 20000, 'DESSERT'),
(9, 'Tomato Soup', 18000, 'APPETIZER');

-- --------------------------------------------------------

--
-- Table structure for table `food_order_details`
--

CREATE TABLE `food_order_details` (
  `order_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `food_order_details`
--

INSERT INTO `food_order_details` (`order_id`, `food_id`, `quantity`) VALUES
(9, 1, 1),
(10, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `cashier_id` int(11) DEFAULT NULL,
  `order_date` timestamp NULL DEFAULT current_timestamp(),
  `total_amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `customer_id`, `cashier_id`, `order_date`, `total_amount`) VALUES
(9, 1, 18, '2023-12-21 21:33:43', 45000),
(10, 1, 18, '2023-12-21 21:35:53', 114000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `beverages`
--
ALTER TABLE `beverages`
  ADD PRIMARY KEY (`beverage_id`);

--
-- Indexes for table `beverage_order_details`
--
ALTER TABLE `beverage_order_details`
  ADD PRIMARY KEY (`order_id`,`beverage_id`),
  ADD KEY `beverage_id` (`beverage_id`);

--
-- Indexes for table `cashiers`
--
ALTER TABLE `cashiers`
  ADD PRIMARY KEY (`cashier_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Indexes for table `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`food_id`);

--
-- Indexes for table `food_order_details`
--
ALTER TABLE `food_order_details`
  ADD PRIMARY KEY (`order_id`,`food_id`),
  ADD KEY `food_id` (`food_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FK_customers_orders` (`customer_id`),
  ADD KEY `FK_cashiers_orders` (`cashier_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `beverages`
--
ALTER TABLE `beverages`
  MODIFY `beverage_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `cashiers`
--
ALTER TABLE `cashiers`
  MODIFY `cashier_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `foods`
--
ALTER TABLE `foods`
  MODIFY `food_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `beverage_order_details`
--
ALTER TABLE `beverage_order_details`
  ADD CONSTRAINT `beverage_order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `beverage_order_details_ibfk_2` FOREIGN KEY (`beverage_id`) REFERENCES `beverages` (`beverage_id`);

--
-- Constraints for table `food_order_details`
--
ALTER TABLE `food_order_details`
  ADD CONSTRAINT `food_order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `food_order_details_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `foods` (`food_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_cashiers_orders` FOREIGN KEY (`cashier_id`) REFERENCES `cashiers` (`cashier_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_customers_orders` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
