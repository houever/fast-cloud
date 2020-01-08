/*
Navicat MySQL Data Transfer

Source Server         : 10.1.70.78
Source Server Version : 50722
Source Host           : 10.1.70.78:3306
Source Database       : mycloud-admin

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-05-25 17:11:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_router`
-- ----------------------------
DROP TABLE IF EXISTS `sys_router`;
CREATE TABLE `sys_router` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `router_name` varchar(200) DEFAULT NULL COMMENT '路由名称',
  `predicates` varchar(500) DEFAULT NULL COMMENT '路由断言集合',
  `filters` varchar(500) DEFAULT NULL COMMENT '路由过滤器集合',
  `uri` varchar(200) DEFAULT NULL COMMENT '转发目标uri',
  `order` int(5) DEFAULT NULL COMMENT '路由排序',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(200) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_by` varchar(200) DEFAULT NULL COMMENT '修改人',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统路由配置';

-- ----------------------------
-- Records of sys_router
-- ----------------------------
INSERT INTO `sys_router` VALUES ('144635968593334272', '系统配置', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/api-usc/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\",\"_genkey_1\":\"\"}}]', 'lb://mycloud-provider-usc', '1', '2019-05-05 12:32:01', null, '2019-05-05 12:32:01', 'admin', '0');
