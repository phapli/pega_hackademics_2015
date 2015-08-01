/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : schooltasklist

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-08-01 23:02:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(300) DEFAULT NULL,
  `Description` text,
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `Admin` varchar(10) NOT NULL,
  `Active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`),
  KEY `Admin_FK` (`Admin`),
  CONSTRAINT `Admin_FK` FOREIGN KEY (`Admin`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', 'Physic 12A', 'Discussion Group about physic in class 12A', '2015-08-01 22:08:22', 'TE0000001', '');
INSERT INTO `group` VALUES ('2', 'Math 12A', 'Discussion Group about math in class 12A', '2015-08-01 22:11:39', 'TE0000002', '');
INSERT INTO `group` VALUES ('3', 'Assignment 1', 'Assignment 1 discussion group', '2015-08-01 22:08:22', 'ST0000001', '');
INSERT INTO `group` VALUES ('4', 'English Homework', 'English Homework discussion group', '2015-08-01 22:11:39', 'ST0000005', '');

-- ----------------------------
-- Table structure for groupuser
-- ----------------------------
DROP TABLE IF EXISTS `groupuser`;
CREATE TABLE `groupuser` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Group` bigint(20) NOT NULL,
  `User` varchar(10) NOT NULL,
  `Active` bit(1) DEFAULT b'1',
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `GroupUser_User_FK` (`User`),
  KEY `GroupUser_Group_FK` (`Group`),
  CONSTRAINT `GroupUser_Group_FK` FOREIGN KEY (`Group`) REFERENCES `group` (`ID`),
  CONSTRAINT `GroupUser_User_FK` FOREIGN KEY (`User`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groupuser
-- ----------------------------
INSERT INTO `groupuser` VALUES ('1', '2', 'ST0000003', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('2', '1', 'ST0000001', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('3', '1', 'ST0000002', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('4', '1', 'TE0000001', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('5', '2', 'ST0000004', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('6', '2', 'ST0000005', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('7', '2', 'TE0000002', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('8', '3', 'ST0000006', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('9', '4', 'ST0000008', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('10', '3', 'ST0000007', '', '2015-08-01 22:23:24');
INSERT INTO `groupuser` VALUES ('11', '4', 'ST0000009', '', '2015-08-01 22:23:24');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` int(11) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `Active` bit(1) DEFAULT b'1',
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'Student', '2015-08-01 21:56:50', '');
INSERT INTO `role` VALUES ('2', 'Teacher', '2015-08-01 21:56:58', '');
INSERT INTO `role` VALUES ('3', 'Parent', '2015-08-01 21:57:04', '');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Task` text,
  `Done` bit(1) DEFAULT b'1',
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `Deadline` datetime DEFAULT NULL,
  `Active` bit(1) DEFAULT b'1',
  `Group` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Group_FK` (`Group`),
  CONSTRAINT `Group_FK` FOREIGN KEY (`Group`) REFERENCES `group` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', 'Finish Question 6 in textbook', '', '2015-08-01 22:17:49', '2015-08-04 22:17:37', '', '1');
INSERT INTO `task` VALUES ('2', 'Finish Question 6 in textbook', '', '2015-08-01 22:17:49', '2015-08-04 22:17:37', '', '1');
INSERT INTO `task` VALUES ('3', 'Finish Question 10 in textbook', '', '2015-08-01 22:17:49', '2015-08-04 22:17:37', '', '2');
INSERT INTO `task` VALUES ('4', 'Write essay 2', '', '2015-08-01 22:17:49', '2015-08-04 22:17:37', '', '3');
INSERT INTO `task` VALUES ('5', 'Finish Question 7 in textbook', '', '2015-08-01 22:17:49', '2015-08-04 22:17:37', '', '4');
INSERT INTO `task` VALUES ('6', 'Finish Question 3 in textbook', '', '2015-08-01 22:17:49', '2015-08-04 22:17:37', '', '1');
INSERT INTO `task` VALUES ('7', 'Prepare presentation', '', '2015-08-01 22:17:49', '2015-08-04 22:17:37', '', '2');
INSERT INTO `task` VALUES ('8', 'Write essay 1', '', '2015-08-01 22:17:49', '2015-08-04 22:17:37', '', '3');
INSERT INTO `task` VALUES ('9', 'Translate letter 1', '', '2015-08-01 22:22:23', null, '', '4');

-- ----------------------------
-- Table structure for taskuser
-- ----------------------------
DROP TABLE IF EXISTS `taskuser`;
CREATE TABLE `taskuser` (
  `ID` bigint(20) NOT NULL,
  `User` varchar(10) NOT NULL,
  `Task` bigint(20) NOT NULL,
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `Active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`ID`),
  KEY `TaskUser_User` (`User`),
  KEY `TaskUser_Task` (`Task`),
  CONSTRAINT `TaskUser_Task` FOREIGN KEY (`Task`) REFERENCES `task` (`ID`),
  CONSTRAINT `TaskUser_User` FOREIGN KEY (`User`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taskuser
-- ----------------------------
INSERT INTO `taskuser` VALUES ('1', 'ST0000001', '1', '2015-08-01 22:28:47', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(10) NOT NULL,
  `LastName` varchar(100) DEFAULT NULL,
  `FirstName` varchar(100) DEFAULT NULL,
  `Class` varchar(10) DEFAULT NULL,
  `RoleID` int(11) NOT NULL,
  `ChildOf` varchar(10) DEFAULT NULL,
  `Active` bit(1) DEFAULT b'1',
  `CreateDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `Role_FK` (`RoleID`),
  KEY `Parent_FK` (`ChildOf`),
  CONSTRAINT `Parent_FK` FOREIGN KEY (`ChildOf`) REFERENCES `user` (`ID`),
  CONSTRAINT `Role_FK` FOREIGN KEY (`RoleID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('PA0000001', 'Khanh', 'Dang Tran', null, '3', null, '', '2015-08-01 22:03:57');
INSERT INTO `user` VALUES ('ST0000001', 'Tin', 'Tran Tien', '12A', '1', 'PA0000001', '', '2015-08-01 22:00:10');
INSERT INTO `user` VALUES ('ST0000002', 'Nhan', 'Tran Van', '12A', '1', null, '', '2015-08-01 22:00:25');
INSERT INTO `user` VALUES ('ST0000003', 'Phi', 'Mai Nguyen Dinh', '12B', '1', null, '', '2015-08-01 22:00:37');
INSERT INTO `user` VALUES ('ST0000004', 'Tinh', 'Pham Minh', '12B', '1', null, '', '2015-08-01 22:00:50');
INSERT INTO `user` VALUES ('ST0000005', 'Tung', 'Huynh Thanh', '12A', '1', null, '', '2015-08-01 22:01:06');
INSERT INTO `user` VALUES ('ST0000006', 'Son', 'Vu Hoang Hai', '12C', '1', null, '', '2015-08-01 22:01:21');
INSERT INTO `user` VALUES ('ST0000007', 'Cuong', 'Do Vu Huu', '12C', '1', null, '', '2015-08-01 22:01:37');
INSERT INTO `user` VALUES ('ST0000008', 'Vu', 'Duong Phuong', '8A', '1', null, '', '2015-08-01 22:01:56');
INSERT INTO `user` VALUES ('ST0000009', 'Thanh', 'Tran Van', '9C', '1', null, '', '2015-08-01 22:02:15');
INSERT INTO `user` VALUES ('TE0000001', 'Khanh', 'Pham Xuan', null, '2', null, '', '2015-08-01 22:02:34');
INSERT INTO `user` VALUES ('TE0000002', 'Vu', 'Pham Xuan', null, '2', null, '', '2015-08-01 22:03:02');
