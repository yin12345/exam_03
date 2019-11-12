/*
Navicat MySQL Data Transfer

Source Server         : root1
Source Server Version : 50645
Source Host           : 123.57.92.151:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2019-11-05 18:11:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `id` int(10) NOT NULL,
  `name` varchar(30) NOT NULL,
  `starttime` datetime(6) DEFAULT NULL,
  `autostart` tinyint(1) DEFAULT NULL,
  `exampaper` mediumblob,
  `started` tinyint(1) DEFAULT NULL,
  `finished` tinyint(1) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL,
  `cleaned` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------
