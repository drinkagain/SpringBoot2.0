/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : springboot-report

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-11-04 20:47:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `down_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for report_sql
-- ----------------------------
DROP TABLE IF EXISTS `report_sql`;
CREATE TABLE `report_sql` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `original_sql` varchar(1000) DEFAULT NULL,
  `result_sql` varchar(1500) DEFAULT NULL,
  `count_sql` varchar(1000) DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for report_sql_column
-- ----------------------------
DROP TABLE IF EXISTS `report_sql_column`;
CREATE TABLE `report_sql_column` (
  `id` varchar(36) NOT NULL,
  `report_sql_id` varchar(36) DEFAULT NULL,
  `reportColumn` varchar(20) DEFAULT NULL,
  `show_name` varchar(50) DEFAULT NULL,
  `isCondition` tinyint(1) DEFAULT NULL,
  `data_type` varchar(10) DEFAULT NULL,
  `query_type` varchar(10) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for report_sql_column_show
-- ----------------------------
DROP TABLE IF EXISTS `report_sql_column_show`;
CREATE TABLE `report_sql_column_show` (
  `id` varchar(36) NOT NULL,
  `report_sql_column_id` varchar(36) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : springboot-report

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2018-11-17 20:31:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tm_plant
-- ----------------------------
DROP TABLE IF EXISTS `tm_plant`;
CREATE TABLE `tm_plant` (
  `ID` bigint(36) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `NO` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '工厂编号',
  `NAME_CN` varchar(150) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '中文名称',
  `NAME_EN` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '英文名称',
  `ENABLED` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '启用',
  `CREATE_USER` bigint(10) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATE_USER` bigint(10) DEFAULT NULL,
  `UPDATE_TIME` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `OPT_COUNTER` bigint(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `U_TM_PLANT_NO` (`NO`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 COMMENT='工厂表';

-- ----------------------------
-- Records of tm_plant
-- ----------------------------
INSERT INTO `tm_plant` VALUES ('1', '1', 'a', null, 'ON', null, null, '6', '2017-06-01 14:09:53', '0');
INSERT INTO `tm_plant` VALUES ('70', '12', 'a', null, 'ON', null, null, '6', '2017-06-01 14:09:53', '0');
INSERT INTO `tm_plant` VALUES ('75', '1001', '上海自行车公司', 'SHZXC', 'ON', null, null, '1', '2017-06-02 12:53:12', '0');
INSERT INTO `tm_plant` VALUES ('76', 'ZY', '上海制衣厂', '', 'ON', null, null, '1', '2017-06-02 12:53:04', '0');
INSERT INTO `tm_plant` VALUES ('88', 'T-00451', '上海皮革厂', 'UGVVT', 'ON', null, null, '1', '2017-06-02 12:53:24', '0');
INSERT INTO `tm_plant` VALUES ('93', 'F-174', '昆山', null, 'ON', null, null, '6', '2017-06-01 11:18:22', '0');
INSERT INTO `tm_plant` VALUES ('105', '1-1-1', '测试', null, 'ON', '6', '2017-06-01 11:17:58', '6', '2017-06-01 11:18:22', '0');
INSERT INTO `tm_plant` VALUES ('129', 'F-177', '昆山', null, 'ON', '6', '2017-06-05 11:53:36', null, null, '0');
INSERT INTO `tm_plant` VALUES ('130', '1-1-3', '测试', null, 'ON', '6', '2017-06-05 11:57:19', null, null, '0');
INSERT INTO `tm_plant` VALUES ('132', 'R-0612', '发动机总厂', '', 'ON', null, null, '17', '2017-06-12 14:39:27', '0');
INSERT INTO `tm_plant` VALUES ('134', '65786', '汽车制造厂', null, 'ON', '17', '2017-06-12 10:26:51', '17', '2017-06-12 10:30:46', '0');
INSERT INTO `tm_plant` VALUES ('139', '11', '1', '', 'ON', '6', '2017-06-12 10:55:14', null, null, '0');
INSERT INTO `tm_plant` VALUES ('140', 'A-01', '测试工厂', '', 'ON', '17', '2017-06-14 16:06:51', null, null, '0');
INSERT INTO `tm_plant` VALUES ('143', 'qqqq111', 'sa', '', 'ON', '1', '2017-06-21 16:07:00', null, null, '0');
INSERT INTO `tm_plant` VALUES ('148', 'DAE001', '汽车发动机制造', 'dae/cn', 'ON', null, null, '26', '2017-06-23 16:09:10', '0');

-- ----------------------------
-- Table structure for tm_workshop
-- ----------------------------
DROP TABLE IF EXISTS `tm_workshop`;
CREATE TABLE `tm_workshop` (
  `ID` bigint(36) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TM_PLANT_ID` bigint(36) NOT NULL COMMENT '工厂id',
  `NO` varchar(100) NOT NULL DEFAULT '' COMMENT '车间编号',
  `NAME_CN` varchar(150) DEFAULT NULL COMMENT '车间中文名称',
  `NAME_EN` varchar(100) DEFAULT NULL COMMENT '车间英文名称',
  `ENABLED` varchar(10) DEFAULT NULL COMMENT '启用',
  `CREATE_USER` bigint(10) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `UPDATE_USER` bigint(10) DEFAULT NULL,
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `OPT_COUNTER` bigint(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `U_TM_WORKSHOP_PLANTID_AND_NO` (`TM_PLANT_ID`,`NO`) USING BTREE,
  KEY `I_TM_WORKSHOP_NO` (`NO`),
  KEY `I_TM_WORKSHOP_PLANTID` (`TM_PLANT_ID`) USING BTREE,
  CONSTRAINT `tm_workshop_ibfk_1` FOREIGN KEY (`TM_PLANT_ID`) REFERENCES `tm_plant` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=50358 DEFAULT CHARSET=utf8 COMMENT='车间表';

-- ----------------------------
-- Records of tm_workshop
-- ----------------------------
INSERT INTO `tm_workshop` VALUES ('50318', '75', '0001', '总装车间', '1', 'ON', null, '2017-04-21 14:59:26', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50319', '76', 'BZ', '包装车间', '', 'ON', null, '2017-05-19 15:05:28', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50327', '88', 'CJ-001', '洗涤车间', 'WASH  WORKSHOP', 'ON', '1', '2017-05-09 10:57:05', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50328', '88', 'CJ-002', '染色车间', 'R', 'ON', null, '2017-05-09 11:13:44', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50330', '88', '03', 'fff', '', 'ON', '1', '2017-05-09 11:33:40', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50331', '93', 'Y1-01', '衣服仓库', 'YF', 'ON', null, '2017-05-18 10:58:47', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50336', '105', '1-1-1', '测试', null, 'ON', '6', '2017-06-01 11:18:38', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50343', '132', 'F-001', '油漆车间', null, 'ON', null, '2017-06-12 10:39:55', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50345', '134', 'Q-001', '轮胎车间', null, 'ON', '17', '2017-06-12 10:41:35', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50346', '132', 'F-002', '缆线车间', null, 'ON', '17', '2017-06-12 10:44:18', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50353', '139', '1', null, null, 'ON', null, '2017-06-18 15:52:29', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50354', '139', '2', null, null, 'ON', '6', '2017-06-12 14:37:55', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50355', '140', 'B-01', '测试车间一', null, 'ON', '17', '2017-06-14 16:07:20', '1', '2017-06-16 16:30:19', '0');
INSERT INTO `tm_workshop` VALUES ('50357', '148', '6MT', '6MT线', '6MT', 'ON', '26', '2017-06-23 11:15:05', null, '2017-06-23 11:15:05', '0');
