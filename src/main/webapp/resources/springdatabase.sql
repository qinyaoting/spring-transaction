/*
Navicat MySQL Data Transfer

Source Server         : local -- 113
Source Server Version : 50710
Source Host           : 127.0.0.1:3306
Source Database       : springdatabase

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2016-05-12 19:08:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(22) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'AA', '90');
INSERT INTO `account` VALUES ('2', 'jack', '20');
INSERT INTO `account` VALUES ('3', 'ken', '33');
INSERT INTO `account` VALUES ('4', 'lucy', '15');

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(22) DEFAULT NULL,
  `price` int(22) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `isbn` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '1001', '10');
INSERT INTO `book` VALUES ('2', '1002', '15');
INSERT INTO `book` VALUES ('3', '1003', '20');

-- ----------------------------
-- Table structure for `book_stock`
-- ----------------------------
DROP TABLE IF EXISTS `book_stock`;
CREATE TABLE `book_stock` (
  `stock` int(11) DEFAULT NULL,
  `isbn` varchar(22) DEFAULT NULL,
  KEY `book_stock` (`isbn`),
  CONSTRAINT `book_stock` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_stock
-- ----------------------------
INSERT INTO `book_stock` VALUES ('0', '1001');
INSERT INTO `book_stock` VALUES ('3', '1002');
INSERT INTO `book_stock` VALUES ('2', '1003');
