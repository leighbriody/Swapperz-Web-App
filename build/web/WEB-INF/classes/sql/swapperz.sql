-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2021 at 12:45 AM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `swapperz`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `allProductsOfCategoryChosen` (IN `category` VARCHAR(255))  SELECT * from products where productCategory = category$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `changeSoldStatus` (IN `productId` INT)  UPDATE products SET status = 'pending' WHERE id = productId$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllBrands` ()  SELECT * from brands$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllColours` ()  SELECT * from colour$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllProducts` ()  select * from products$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getBrand` (IN `id` INT)  SELECT * FROM brands WHERE id = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getColour` (IN `id` INT)  SELECT * FROM colour WHERE id = id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserAddressId` (IN `userId` INT)  select addressId from users where id = userId$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserById` (IN `userId` INT)  SELECT * FROM users WHERE id = userId$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserByUsername` (IN `username` VARCHAR(255))  SELECT * FROM users WHERE username = username$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserId` (IN `username` VARCHAR(255))  SELECT id FROM users WHERE username = username$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `successfulPurchase` (IN `purchaseId` INT, IN `productId` INT)  UPDATE products SET purchaseId = purchaseId WHERE id = productId$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` int(6) NOT NULL,
  `userId` int(6) NOT NULL,
  `line1` varchar(30) NOT NULL,
  `line2` varchar(30) NOT NULL,
  `town` varchar(30) NOT NULL,
  `county` varchar(30) NOT NULL,
  `country` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `userId`, `line1`, `line2`, `town`, `county`, `country`) VALUES
(1, 2, '13255 East End Ave', 'test ', 'Drogheda', 'Louth', 'Ireland'),
(2, 3, '123 lane', 'road', 'Drogheda', 'louth', 'Ireland'),
(3, 4, '123 hawntown ', 'ballymakenny road', 'Drogheda', 'Louth ', 'Irelad'),
(4, 6, '12 castlemannor', 'ballymakenny road', 'drogheda', 'Louth', 'Ireland'),
(5, 9, 'add', 'dd', 'droghed', 'Louth', 'Ireland'),
(6, 10, '67 castlemannor', 'ballymakenny road', 'Drogheda', 'Louth', 'Ireland'),
(7, 11, '123 high view', 'road', 'Drogheda', 'Louth', 'Ireland'),
(8, 3, '123 lane', 'road', 'Drogheda', 'louth', 'Ireland'),
(9, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(10, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(11, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(12, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(13, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(14, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(15, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(16, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(17, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(18, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland'),
(19, 3, '33 The Green', 'Inse Bay', 'Laytown', 'Meath', 'Ireland');

-- --------------------------------------------------------

--
-- Table structure for table `brands`
--

CREATE TABLE `brands` (
  `id` int(4) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brands`
--

INSERT INTO `brands` (`id`, `name`) VALUES
(1, 'Nike'),
(2, 'Adidas'),
(3, 'The North Face'),
(4, 'Puma'),
(5, 'Lacoste'),
(6, 'Regatta'),
(7, 'Gap'),
(8, 'Vans'),
(9, 'Asos'),
(10, 'Fred Perry'),
(11, 'Superdry'),
(12, 'Colombia'),
(13, 'Under Armour'),
(14, 'gucci'),
(15, 'calvin klein'),
(16, 'ray ban'),
(17, 'tommy hilfiger'),
(18, 'dsquared2'),
(20, 'Fendi');

-- --------------------------------------------------------

--
-- Table structure for table `clothingsize`
--

CREATE TABLE `clothingsize` (
  `id` int(2) NOT NULL,
  `size` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clothingsize`
--

INSERT INTO `clothingsize` (`id`, `size`) VALUES
(1, 'XXS'),
(2, 'XS'),
(3, 'S'),
(4, 'M'),
(5, 'L'),
(6, 'XL'),
(7, 'XXL'),
(8, '3'),
(9, '3.5'),
(10, '4'),
(11, '4.5'),
(12, '5'),
(13, '5.5'),
(14, '6'),
(15, '6.5'),
(16, '7'),
(17, '7.5'),
(18, '8'),
(19, '8.5'),
(20, '9'),
(21, '9.5'),
(22, '10'),
(23, '10.5'),
(24, '11'),
(25, '11.5'),
(26, '12');

-- --------------------------------------------------------

--
-- Table structure for table `colour`
--

CREATE TABLE `colour` (
  `id` int(3) NOT NULL,
  `colour` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `colour`
--

INSERT INTO `colour` (`id`, `colour`) VALUES
(1, 'red'),
(2, 'yellow'),
(3, 'pink'),
(4, 'purple'),
(5, 'green'),
(6, 'black'),
(7, 'white'),
(8, 'navy'),
(9, 'blue'),
(10, 'brown');

-- --------------------------------------------------------

--
-- Table structure for table `membership`
--

CREATE TABLE `membership` (
  `id` int(8) NOT NULL,
  `userId` int(6) NOT NULL,
  `expiry` date NOT NULL,
  `status` varchar(25) NOT NULL DEFAULT 'subscribed',
  `total` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `productimage`
--

CREATE TABLE `productimage` (
  `id` int(11) NOT NULL,
  `imageLocation` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productimage`
--

INSERT INTO `productimage` (`id`, `imageLocation`) VALUES
(1, 'images\\productImages\\blackTechHoodie.jpg'),
(2, 'images\\productImages\\womensVapormax.jpg'),
(3, 'images/loginGuy.jpg'),
(5, 'images/Kenzo.png'),
(10, 'images/contrastTech.webp'),
(11, 'images/hoodie2.png'),
(12, 'images/womensNikeRunners.jpg'),
(14, 'images/womensNikeRunners.jpg'),
(17, 'images/womensNikeRunners.jpg'),
(18, 'images/productImages/womensNikeRunners.jpg'),
(19, 'images/productImages/underArmour.jpg'),
(20, 'images/productImages/superdryJacket.jpg'),
(21, 'images/productImages/whitePerry.jpg'),
(22, 'images/productImages/lacosteHoodie.jpg'),
(23, 'images/productImages/womensGucciShades.jpg'),
(24, 'images/productImages/nikeLeggings.jpg'),
(25, 'images/productImages/addidasLeggings.jpg'),
(26, 'images/productImages/wmSuperdyJeans.jpg'),
(27, 'images/productImages/womensNorthFaceHoodie.jpg'),
(28, 'images/productImages/wmNikeRunners.jpg'),
(29, 'images/productImages/wmNikeRunners.jpg'),
(30, 'images/productImages/mensNikeHoodieBlk.jpg'),
(31, 'images/productImages/nikeTech.jpg'),
(32, 'images/productImages/airMax90.jpg'),
(33, 'images/productImages/mensCkJeans.jpg'),
(34, 'images/productImages/DsquaredJeans.jpg'),
(35, 'images/productImages/nikeJacketMn.jpg'),
(36, 'images/productImages/nikeJacketMn.jpg'),
(37, 'images/productImages/tommyShirt.jpg'),
(38, 'images/productImages/ckWallet.jpg'),
(39, 'images/productImages/womensGucciShades.jpg'),
(40, 'images/productImages/womensGucciShades.jpg'),
(41, 'images/productImages/'),
(42, 'images/productImages/womensGucciShades.jpg'),
(43, 'images/productImages/womensGucciShades.jpg'),
(44, 'images/productImages/retroShoe.png');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(6) NOT NULL,
  `userId` int(6) NOT NULL,
  `productName` varchar(35) NOT NULL,
  `description` varchar(512) NOT NULL,
  `colour` int(3) NOT NULL,
  `brandId` int(4) NOT NULL,
  `originalPrice` double(4,0) NOT NULL,
  `listedPrice` decimal(4,0) DEFAULT NULL,
  `status` enum('forSale','sold','withdrawn','removed','pending') NOT NULL DEFAULT 'forSale',
  `sizeId` int(3) NOT NULL,
  `productCondition` enum('worn','likenew','brandnew','') NOT NULL,
  `productImage` int(255) DEFAULT NULL,
  `productGender` enum('male','female','unisex','') DEFAULT NULL,
  `productCategory` enum('menshoodies','mens jeans','menstrainers','womens trainers','mens jackets','womens jackets','mensshirts','mensaccessories','womens hoodies','womens jeans','womens leggings','womens accessories','mens hoodies','mens trainers','mens shirts','mens accessories') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `userId`, `productName`, `description`, `colour`, `brandId`, `originalPrice`, `listedPrice`, `status`, `sizeId`, `productCondition`, `productImage`, `productGender`, `productCategory`) VALUES
(3, 2, 'Nike Tech Hoodie', 'old hoodie selling cheap', 1, 1, 60, '50', 'forSale', 4, 'brandnew', 1, '', 'mens hoodies'),
(4, 6, 'Vapour Max', 'vey comfy', 3, 1, 100, NULL, 'forSale', 12, 'worn', 2, 'female', ''),
(12, 3, 'Contrast Tech', 'Very Warm', 1, 13, 60, '64', 'forSale', 4, 'worn', 10, 'male', 'mens hoodies'),
(15, 3, 'Nike Running', 'Very Comfy', 3, 1, 50, NULL, 'forSale', 8, 'brandnew', 18, 'female', ''),
(16, 3, 'Under Runner', 'efwf', 6, 13, 50, NULL, 'forSale', 16, 'brandnew', 19, 'male', 'womens trainers'),
(17, 3, 'Superdry Jacket', 'Keep dry', 6, 11, 50, NULL, 'forSale', 4, 'brandnew', 20, 'female', 'womens jackets'),
(18, 3, 'Perry Polo', 'df', 7, 10, 50, NULL, 'forSale', 4, 'brandnew', 21, 'male', 'mens shirts'),
(19, 11, 'Lacoste Hoodie', 'Very Comfy', 6, 5, 76, NULL, 'sold', 4, 'brandnew', 22, 'male', 'mens hoodies'),
(20, 11, 'Womens Gucci Shades', 'Mint', 6, 14, 201, NULL, 'forSale', 3, 'brandnew', 23, 'female', 'womens accessories'),
(21, 11, 'Nike Leggings', 'Comfy and nice fit', 6, 1, 45, NULL, 'forSale', 3, 'brandnew', 24, 'female', 'womens leggings'),
(22, 11, 'Adidas Leggings', 'Really good for gym wear', 6, 2, 50, NULL, 'forSale', 3, 'brandnew', 25, 'female', 'womens leggings'),
(23, 9, 'Womens Superdry Jeans', 'Very nice material', 9, 11, 50, NULL, 'forSale', 3, 'brandnew', 26, 'female', 'womens jeans'),
(24, 9, 'Womens North Face Hoodie', 'Selling as i bought the wrong size', 3, 3, 50, NULL, 'forSale', 4, 'brandnew', 27, 'female', 'womens hoodies'),
(25, 9, 'Womens Nike Trainers', 'Selling as its an unwanted gift', 7, 1, 50, NULL, 'forSale', 13, 'brandnew', 29, 'female', 'womens trainers'),
(26, 9, 'Black Nike Hoodie', 'Selling unwanted', 6, 1, 40, NULL, 'removed', 4, 'brandnew', 30, 'unisex', 'womens hoodies'),
(27, 9, ' Tech Hoodie', 'Unwanted', 6, 1, 60, NULL, 'forSale', 4, 'brandnew', 31, 'male', 'mens hoodies'),
(28, 9, 'Air Max 90', 'Selling as wrong size', 7, 1, 60, NULL, 'forSale', 20, 'brandnew', 32, 'male', 'mens trainers'),
(29, 9, 'Calvin Klein Jeans', 'Very nice ', 9, 15, 100, NULL, 'forSale', 4, 'brandnew', 33, 'male', 'mens jeans'),
(30, 9, 'Dsquared Jeans', 'Selling as i got the wrong size', 9, 18, 150, NULL, 'forSale', 4, 'brandnew', 34, 'male', 'mens jeans'),
(31, 9, 'Nike Light Jacke', 'Selling as unwanted , only wore once great deal ! ', 7, 1, 45, NULL, 'forSale', 3, 'likenew', 36, 'male', 'mens jackets'),
(32, 9, 'Tommy Hilfiger Shirt', 'Selling as too small now , only wore twice perfect condition', 7, 17, 40, NULL, 'forSale', 4, 'likenew', 37, 'male', 'mens shirts'),
(33, 9, 'Calvin Klein Wallet', 'Selling as unwanted gift', 6, 15, 40, NULL, 'forSale', 3, 'brandnew', 38, 'male', 'mens accessories'),
(38, 3, 'Nike Air Max 95', 'Very Fresh', 1, 1, 180, NULL, 'forSale', 24, 'likenew', 44, 'male', 'mens trainers');

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE `purchases` (
  `id` int(8) NOT NULL,
  `userId` int(4) NOT NULL,
  `productId` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(8) NOT NULL,
  `userId` int(6) NOT NULL,
  `productId` int(6) NOT NULL,
  `rating` int(5) DEFAULT NULL,
  `review` varchar(160) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `securityanswers`
--

CREATE TABLE `securityanswers` (
  `id` int(8) NOT NULL,
  `userId` int(4) NOT NULL,
  `questionId` int(2) NOT NULL,
  `answer` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `securityanswers`
--

INSERT INTO `securityanswers` (`id`, `userId`, `questionId`, `answer`) VALUES
(2, 15, 1, '1000:9f78329ae146edc5bd0fc836af88bac0:f73112b58ecd8ad02c14dc39a6e08a3137e3f4740798a06fb28c69d6e71075b1e4bbaf93a591caddf406e07723a6965169a8b0e5499a2f95368c80028efc3ee0'),
(3, 15, 2, '1000:25161c1cfa4bc82f026eb92fc0c5f17d:69a92f99ae577b2ae6b67bf1b8bfec7521f5b3dec07aba9fc399d51aa1835d0194f00bce18b876bda4fc9c7f890c1f3b65c8dfb634bb2e25a4df5f13c5103977'),
(4, 15, 3, '1000:6c61150515297ecbe5a04d2138d268d7:3b3c4da29e1dd521181ea6708751954be819682324008ca92e8cda49736d83c9cd39600d6a1c141dba08bf60a972f3519229269b2df2605e79c0ee67a167a4aa');

-- --------------------------------------------------------

--
-- Table structure for table `securityquestions`
--

CREATE TABLE `securityquestions` (
  `id` int(2) NOT NULL,
  `question` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `securityquestions`
--

INSERT INTO `securityquestions` (`id`, `question`) VALUES
(1, 'What was the name of your childhood pet?'),
(2, 'Where did you go to primary school?'),
(3, 'What was the house number and street name you lived in as a child?'),
(4, 'What were the last four digits of your childhood telephone number?'),
(5, 'In what town or city was your first full time job?'),
(6, 'What is your grandmother\'s (on your mother\'s side) maiden name?'),
(7, 'What is your spouse or partner\'s mother\'s maiden name?'),
(8, 'When did you get your first car?');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(6) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(512) NOT NULL,
  `fName` varchar(25) NOT NULL,
  `lName` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `addressId` int(6) DEFAULT NULL,
  `phone` int(10) NOT NULL,
  `sellerRating` int(5) DEFAULT NULL,
  `gender` enum('male','female','other','') DEFAULT NULL,
  `profilePicture` varchar(255) DEFAULT NULL,
  `status` enum('standard','admin') DEFAULT 'standard'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `fName`, `lName`, `email`, `addressId`, `phone`, `sellerRating`, `gender`, `profilePicture`, `status`) VALUES
(1, 'leigh', 'daFf', 'leigh', 'leigh', 'leigh@gmail.com', NULL, 87828283, NULL, NULL, NULL, 'standard'),
(2, 'billy', '1000:1bec20d2d55980195ae9fcfb0b06fc4c:4ca36566b61a688650583bd8ce270e4aa8a68ee3f1c71d868eae344eb8369cd7d5c921cfd431142b2fde6047c83918580386779934cf2100820fb4b535dc35fa', 'billy', 'gibney', 'gibney@gmail.coom', 1, 934747373, NULL, NULL, 'images/profilePictures/billy.png', 'standard'),
(3, 'osama', '1000:fc218615ce2cf803b9dc239222f51066:4509c15cd7a5ddd9fd9add6e27c7b5a383fb5643ba7421a5729c730b3ae8bca6df831bbd9ff5f7517b391b90bccd40bc528a558061c1557e99fc1a7540344a46', 'Osama', 'Kheireddine', 'osamal@gmail.com', 2, 1234567890, NULL, NULL, 'images/profilePictures/osama.jpg', 'admin'),
(4, 'adam', '1000:c0a4f490239cd5661df8b35a0377d7a4:2f3af1036fe4e40a46aad05a53f13ee21880f514f13fbde05e7e73b34859e7f674f03a5049e2b34924143ab4b2e18b4d78c5b3f9bc4411938b61959f0fb38671', 'Adam', 'Lawless', 'adam@gmail.com', 3, 872626123, NULL, 'female', NULL, 'standard'),
(6, 'gillian', '1000:83a2c88d6d8ff760cbee3b5afed56f4f:0ad40437136e08a03f57d9e725d61d36201198dcb16fdf4614c42ef86ef5203ac9d3c51b536c34b03a95cefa034e9145a71b3f5f796e516189fa70b2d031e7fd', 'gillian', 'fitzpatrick', 'gillian@gmail.com', 4, 87272732, NULL, 'female', NULL, 'standard'),
(7, 'test', 'pass', 'fname', 'lname', 'email', NULL, 8373723, NULL, 'female', NULL, 'standard'),
(9, 'ryan', '1000:5434ea062f30098768266de63d776eb9:5abf8d91c41a173d280fa6295fbbe9c86dd3c0d8403c12b088bfa0042ced59f4722fb306a4fc68df4bdc7338038c39f6beecd5c3b81cabf3c0ecf3f299ef5ec0', 'Ryan', 'Chenigle', 'ryanc@gmail.com', 5, 87262512, NULL, 'male', 'images/profilePictures/ryan.jpg', 'standard'),
(10, 'leighbrio', '1000:59994c1092a4077dff221f80328204ab:dd27fab8fbf3d37d020111a6cfe6682509d590a193b9169b401b2721b9ac35058bcaf720389560756039686faa4adc1a3eb57d74919ad554f7a1d2fc9a5c268b', 'Leigh', 'Briody', 'lbriody10@gmail.com', 6, 987823, NULL, 'male', 'images/profilePictures/me.jpg', 'standard'),
(11, 'kyle', '1000:7ae19a02de7ac39f3209863873fb47d3:af70562a8a2b0cc73ba57caa6bfccf27b58a7876db7d90d5ee1dc99daf73ab0b3f1e58ed1d3c030f7b3a882e1428810a360a2b591d9f7257f8842e4994734219', 'Kyle', 'molloy', 'kmolloy@gmail.com', 7, 872625123, NULL, 'male', 'images/profilePictures/swapperzLogoLight.ico', 'standard');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_addressUserId` (`userId`);

--
-- Indexes for table `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `clothingsize`
--
ALTER TABLE `clothingsize`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `colour`
--
ALTER TABLE `colour`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `membership`
--
ALTER TABLE `membership`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_membershipUserId` (`userId`);

--
-- Indexes for table `productimage`
--
ALTER TABLE `productimage`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_productBrandId` (`brandId`),
  ADD KEY `fk_productSizeId` (`userId`),
  ADD KEY `fk_productColourId` (`colour`),
  ADD KEY `fk_productImage` (`productImage`);

--
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_userId` (`userId`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_productId` (`productId`),
  ADD KEY `fk_reviewsUserId` (`userId`);

--
-- Indexes for table `securityanswers`
--
ALTER TABLE `securityanswers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_userId_sAns` (`userId`),
  ADD KEY `fk_questionId` (`questionId`);

--
-- Indexes for table `securityquestions`
--
ALTER TABLE `securityquestions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `brands`
--
ALTER TABLE `brands`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `clothingsize`
--
ALTER TABLE `clothingsize`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `colour`
--
ALTER TABLE `colour`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `membership`
--
ALTER TABLE `membership`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `productimage`
--
ALTER TABLE `productimage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `securityanswers`
--
ALTER TABLE `securityanswers`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `securityquestions`
--
ALTER TABLE `securityquestions`
  MODIFY `id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `fk_addressUserId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);

--
-- Constraints for table `membership`
--
ALTER TABLE `membership`
  ADD CONSTRAINT `fk_membershipUserId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_productBrandId` FOREIGN KEY (`brandId`) REFERENCES `brands` (`id`),
  ADD CONSTRAINT `fk_productColourId` FOREIGN KEY (`colour`) REFERENCES `colour` (`id`),
  ADD CONSTRAINT `fk_productImage` FOREIGN KEY (`productImage`) REFERENCES `productimage` (`id`),
  ADD CONSTRAINT `fk_productSizeId` FOREIGN KEY (`userId`) REFERENCES `clothingsize` (`id`),
  ADD CONSTRAINT `fk_productUserId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);

--
-- Constraints for table `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `fk_userId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `fk_productId` FOREIGN KEY (`productId`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `fk_reviewsUserId` FOREIGN KEY (`userId`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
