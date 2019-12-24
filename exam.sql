/*
Navicat MySQL Data Transfer

Source Server         : root1
Source Server Version : 50645
Source Host           : 123.57.92.151:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2019-12-24 13:03:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `dutycycle` varchar(255) DEFAULT NULL,
  `pagecount` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `high` varchar(255) DEFAULT NULL,
  `allowed` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'yin', 'd9b1d7db4cd6e70935368a1efb10e377', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('2', 'admin', '665f644e43731ff9db3d341da5c827e1', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('3', '3', '38026ed22fc1a91d92b5d2ef93540f20', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('4', '4', '4', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('5', '5', '5', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('9', '9', '4c0d13d3ad6cc317017872e51d01b238', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('12', '12', 'c8b2f17833a4c73bb20f88876219ddcd', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('666', '666', 'de8d6c50fef22cab3abae03d0826b6a1', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('667', '667', '28c8edde3d61a0411511d3b1866f0636', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('888', '888', '28c8edde3d61a0411511d3b1866f0636', '1', '6', '1', '1', 'true');
INSERT INTO `admin` VALUES ('1444', '1', '866c406c3a6afce6ce5b848e1586fd01', '1', '6', '1', '1', 'true');

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam` (
  `eid` int(10) NOT NULL AUTO_INCREMENT,
  `ename` varchar(30) DEFAULT NULL,
  `starttime` datetime(6) DEFAULT NULL,
  `autostart` tinyint(1) DEFAULT NULL,
  `exampaper` varchar(30) DEFAULT NULL,
  `started` tinyint(1) DEFAULT NULL,
  `finished` tinyint(1) DEFAULT NULL,
  `archived` tinyint(1) DEFAULT NULL,
  `cleaned` tinyint(1) DEFAULT NULL,
  `tname` varchar(30) DEFAULT NULL,
  `runing` tinyint(1) DEFAULT NULL,
  `tid` int(10) DEFAULT NULL,
  `dateout` int(1) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam` VALUES ('26', '9', '2019-11-12 14:55:00.000000', '1', '考中管理.txt', '0', '0', '0', '0', 'admin', '1', '2', '0');
INSERT INTO `exam` VALUES ('27', '数学', '2019-12-04 08:25:00.000000', '1', 'demo项目（新员工练习）.txt', '0', '1', '1', '0', 'yin', '0', '1', '0');
INSERT INTO `exam` VALUES ('32', '123', '2019-11-12 17:08:00.000000', '0', 'student1.xlsx', '0', '0', '0', '0', 'yin', '0', '1', '0');
INSERT INTO `exam` VALUES ('37', '12345', '2019-12-03 20:50:00.000000', '0', '面试计划.txt', '0', '1', '0', '0', 'yin', '0', '1', '0');
INSERT INTO `exam` VALUES ('40', '语文', '2019-11-19 16:35:00.000000', '0', 'student1.xlsx', '0', '0', '0', '0', 'admin', '0', '2', '0');
INSERT INTO `exam` VALUES ('41', '告诉你', '2019-11-25 16:56:02.000000', '1', '考中管理.txt', '0', '0', '0', '0', 'yin', '1', '1', '0');
INSERT INTO `exam` VALUES ('66', '最后一场考试', '2019-11-26 14:59:21.000000', '0', '面试计划.txt', '0', '0', '0', '0', 'yin', '1', '1', '0');
INSERT INTO `exam` VALUES ('72', '121212', '2019-11-05 05:25:00.000000', '0', '截图.doc', '0', '0', '0', '0', '666', '0', '666', '1');
INSERT INTO `exam` VALUES ('82', '4636', '2019-12-25 05:25:00.000000', '0', null, '0', '0', '0', '0', '3', '0', '3', '0');

-- ----------------------------
-- Table structure for oa_id_exam_student
-- ----------------------------
DROP TABLE IF EXISTS `oa_id_exam_student`;
CREATE TABLE `oa_id_exam_student` (
  `student_id` varchar(255) NOT NULL,
  `exam_id` int(11) NOT NULL,
  PRIMARY KEY (`student_id`,`exam_id`),
  KEY `FK89j8aoyu0itrm28nql4dymqdw` (`exam_id`),
  CONSTRAINT `FK89j8aoyu0itrm28nql4dymqdw` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`eid`),
  CONSTRAINT `FKr123531o7dpuoamr6cp7vfwsp` FOREIGN KEY (`student_id`) REFERENCES `student` (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_id_exam_student
-- ----------------------------
INSERT INTO `oa_id_exam_student` VALUES ('d', '26');
INSERT INTO `oa_id_exam_student` VALUES ('11', '27');
INSERT INTO `oa_id_exam_student` VALUES ('10', '37');
INSERT INTO `oa_id_exam_student` VALUES ('13', '66');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stu_id` varchar(20) NOT NULL,
  `stu_name` varchar(20) DEFAULT NULL,
  `stu_class` varchar(20) DEFAULT NULL,
  `stu_submit` varchar(21746) DEFAULT 'null',
  `stu_ip` varchar(15) DEFAULT 'null',
  `stu_exam` int(10) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('10', '10', '10', '1', '0:0:0:0:0:0:0:2', null);
INSERT INTO `student` VALUES ('11', '11', '11', null, '0:0:0:0:0:0:0:0', '27');
INSERT INTO `student` VALUES ('13', '13', '13', null, null, '66');
INSERT INTO `student` VALUES ('d', 'd', 'd', null, null, '26');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `examid` int(11) DEFAULT NULL,
  `fullstudentid` int(11) DEFAULT NULL,
  `admin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1445 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', 'yin', 'd9b1d7db4cd6e70935368a1efb10e377', 'yin', null, null, 'true');
INSERT INTO `teacher` VALUES ('2', 'admin', '665f644e43731ff9db3d341da5c827e1', null, null, null, 'true');
INSERT INTO `teacher` VALUES ('3', '3', '38026ed22fc1a91d92b5d2ef93540f20', null, null, null, 'true');
INSERT INTO `teacher` VALUES ('4', '4', '4', null, null, null, null);
INSERT INTO `teacher` VALUES ('5', '5', '5', null, null, null, null);
INSERT INTO `teacher` VALUES ('7', '7', '7', null, null, null, null);
INSERT INTO `teacher` VALUES ('9', '9', 'd9b1d7db4cd6e70935368a1efb10e377', null, null, null, 'true');
INSERT INTO `teacher` VALUES ('12', '12', 'c8b2f17833a4c73bb20f88876219ddcd', null, null, null, 'true');
INSERT INTO `teacher` VALUES ('666', '666', 'de8d6c50fef22cab3abae03d0826b6a1', null, null, null, 'true');
INSERT INTO `teacher` VALUES ('1246', '000', 'b900afac6b173100b1e55432e9d58c88', null, null, null, 'true');
INSERT INTO `teacher` VALUES ('1444', '1', '866c406c3a6afce6ce5b848e1586fd01', null, null, null, 'true');
