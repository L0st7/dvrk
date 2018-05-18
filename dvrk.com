-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.19-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dvrk.com
--

CREATE DATABASE IF NOT EXISTS dvrk.com;
USE dvrk.com;

--
-- Definition of table `tblcategory`
--

DROP TABLE IF EXISTS `tblcategory`;
CREATE TABLE `tblcategory` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `category_image` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_notes` longtext COLLATE utf8_unicode_ci,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblcategory`
--

/*!40000 ALTER TABLE `tblcategory` DISABLE KEYS */;
INSERT INTO `tblcategory` (`category_id`,`category_name`,`category_image`,`category_notes`) VALUES 
 (1,'SALE',NULL,NULL),
 (2,'PANTS',NULL,NULL),
 (3,'JEAN',NULL,NULL),
 (4,'SHORTS',NULL,NULL),
 (5,'OUTERWEAR',NULL,NULL),
 (6,'T-SHIRT',NULL,NULL),
 (7,'TOPWEAR',NULL,NULL);
/*!40000 ALTER TABLE `tblcategory` ENABLE KEYS */;


--
-- Definition of table `tblcustomer`
--

DROP TABLE IF EXISTS `tblcustomer`;
CREATE TABLE `tblcustomer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_fullname` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_address` longtext COLLATE utf8_unicode_ci,
  `customer_email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_mobile` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_account` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `customer_password` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblcustomer`
--

/*!40000 ALTER TABLE `tblcustomer` DISABLE KEYS */;
INSERT INTO `tblcustomer` (`customer_id`,`customer_fullname`,`customer_address`,`customer_email`,`customer_mobile`,`customer_account`,`customer_password`) VALUES 
 (1,'Nguyễn Văn Hiệp','Hà Nội','nguyenhiep96vn@gmail.com','0964896938','Lost7','e10adc3949ba59abbe56e057f20f883e'),
 (2,'Nguyá»n Thá» HÃ  PhÆ°Æ¡ng','HÃ  Ná»i','haphuong@gmail.com','01684998223','dudu','e10adc3949ba59abbe56e057f20f883e');
/*!40000 ALTER TABLE `tblcustomer` ENABLE KEYS */;


--
-- Definition of table `tblorder`
--

DROP TABLE IF EXISTS `tblorder`;
CREATE TABLE `tblorder` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_title` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_address` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_date` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_note` longtext COLLATE utf8_unicode_ci,
  `order_customer_id` int(11) DEFAULT NULL,
  `order_price` int(10) unsigned DEFAULT NULL,
  `order_fullname_customer` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_phone` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_payments` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_status` tinyint(1) DEFAULT NULL,
  `order_email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `order_delivery_date` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_customer_id_idx` (`order_customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblorder`
--

/*!40000 ALTER TABLE `tblorder` DISABLE KEYS */;
INSERT INTO `tblorder` (`order_id`,`order_title`,`order_address`,`order_date`,`order_note`,`order_customer_id`,`order_price`,`order_fullname_customer`,`order_phone`,`order_payments`,`order_status`,`order_email`,`order_delivery_date`) VALUES 
 (4,NULL,'Hưng Hà - Thái Bình','Tue May 08 17:39:21 ICT 2018',NULL,0,830000,'Nguyễn Văn Hiệp','0964896938','Thanh toán khi nhận hàng',1,'nguyenhiep96vn@gmail.com','Tue May 08 17:39:36 ICT 2018');
/*!40000 ALTER TABLE `tblorder` ENABLE KEYS */;


--
-- Definition of table `tblorderdetail`
--

DROP TABLE IF EXISTS `tblorderdetail`;
CREATE TABLE `tblorderdetail` (
  `orderdetail_id` int(11) NOT NULL AUTO_INCREMENT,
  `orderdetail_product_id` int(11) DEFAULT NULL,
  `orderdetail_quantity` int(11) DEFAULT NULL,
  `orderdetail_price` int(11) DEFAULT NULL,
  `orderdetail_order_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderdetail_id`),
  KEY `orderdetail_order_id_idx` (`orderdetail_order_id`),
  KEY `orderdetail_product_id_idx` (`orderdetail_product_id`),
  CONSTRAINT `orderdetail_order_id` FOREIGN KEY (`orderdetail_order_id`) REFERENCES `tblorder` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderdetail_product_id` FOREIGN KEY (`orderdetail_product_id`) REFERENCES `tblproduct` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblorderdetail`
--

/*!40000 ALTER TABLE `tblorderdetail` DISABLE KEYS */;
INSERT INTO `tblorderdetail` (`orderdetail_id`,`orderdetail_product_id`,`orderdetail_quantity`,`orderdetail_price`,`orderdetail_order_id`) VALUES 
 (4,13,1,790000,4);
/*!40000 ALTER TABLE `tblorderdetail` ENABLE KEYS */;


--
-- Definition of table `tblproduct`
--

DROP TABLE IF EXISTS `tblproduct`;
CREATE TABLE `tblproduct` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_image` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_price` int(10) unsigned DEFAULT NULL,
  `product_visited` smallint(5) unsigned DEFAULT NULL,
  `product_total` smallint(5) unsigned DEFAULT NULL,
  `product_intro` longtext COLLATE utf8_unicode_ci,
  `product_size` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_category_id` int(11) DEFAULT NULL,
  `product_image2` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_image3` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_image4` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `product_image5` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `product_category_id_idx` (`product_category_id`),
  CONSTRAINT `product_category_id` FOREIGN KEY (`product_category_id`) REFERENCES `tblcategory` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tblproduct`
--

/*!40000 ALTER TABLE `tblproduct` DISABLE KEYS */;
INSERT INTO `tblproduct` (`product_id`,`product_name`,`product_image`,`product_price`,`product_visited`,`product_total`,`product_intro`,`product_size`,`product_category_id`,`product_image2`,`product_image3`,`product_image4`,`product_image5`) VALUES 
 (12,'POLICE LINE HOODIE','../images/upload/police hoodie 1.jpg',1390000,0,90,'','L',7,'../images/upload/police hoodie 2.jpg',NULL,NULL,NULL),
 (13,'RED ROSE TEE','../images/upload/red rose 1.jpg',790000,0,19,'','S',6,'../images/upload/red rose 2.jpg','../images/upload/red rose 3.jpg','../images/upload/red rose 4.jpg','../images/upload/red rose 5.jpg'),
 (14,'EMERGENCY ESSENTIAL ZIP UP HOODIE / WHITE','../images/upload/zip up hoodie 1.jpg',790000,0,19,'<p>COTTON FABRIC</p>\r\n','XL',5,'../images/upload/zip up hoodie 2.jpg','../images/upload/zip up hoodie 3.jpg','../images/upload/zip up hoodie 4.jpg','../images/upload/zip up hoodie 5.jpg'),
 (15,'FOGLOG SHORT','../images/upload/foglog short 1.jpg',550000,0,30,'','S',4,'../images/upload/foglog short 2.jpg','../images/upload/foglog short 3.jpg',NULL,NULL),
 (16,'WAX LEATHER JEANS','../images/upload/wax jean 1.jpg',990000,0,20,'','S',3,'../images/upload/wax jean 2.jpg','../images/upload/wax jean 3.jpg','../images/upload/wax jean 4.jpg',NULL),
 (17,'DVRK ESSENTIAL PACK - BLACK/RED','../images/upload/dvrk pack 1.jpg',1100000,0,0,'<p>Essential zip up hoodie</p>\r\n\r\n<p>100% cotton</p>\r\n\r\n<p>Essential stripe sweatpaint</p>\r\n\r\n<p>100% cotton</p>\r\n','S',2,'../images/upload/dvrk pack 2.jpg',NULL,NULL,NULL),
 (18,'covered hoodie - prink','../images/upload/covered hoodie 1.jpg',2000000,0,7,'<p>Oversized fit</p>\r\n\r\n<p>100% cotton</p>\r\n\r\n<p>Front: Printed</p>\r\n','M',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tblproduct` ENABLE KEYS */;


--
-- Definition of table `tbluser`
--

DROP TABLE IF EXISTS `tbluser`;
CREATE TABLE `tbluser` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_pass` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `user_fullname` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbluser`
--

/*!40000 ALTER TABLE `tbluser` DISABLE KEYS */;
INSERT INTO `tbluser` (`user_id`,`user_name`,`user_pass`,`user_fullname`,`user_email`) VALUES 
 (1,'Lost7','e10adc3949ba59abbe56e057f20f883e','Nguyễn Văn Hiệp','nguyenhiep96vn@gmail.com');
/*!40000 ALTER TABLE `tbluser` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
