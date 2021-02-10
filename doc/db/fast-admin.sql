/*
Navicat MySQL Data Transfer

Source Server         : 10.2.95.149
Source Server Version : 50722
Source Host           : 10.2.95.149:3306
Source Database       : fast-admin

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-01-13 12:19:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_account`
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `introduction` varchar(255) DEFAULT NULL COMMENT '个人简介',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `dept_id` varchar(50) DEFAULT NULL COMMENT '部门id',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '昵称',
  `pass_strengh` varchar(30) DEFAULT NULL COMMENT '密码强度',
  `type` int(11) DEFAULT NULL COMMENT '账户类型',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '账户是否启用',
  `account_non_expired` tinyint(1) DEFAULT '1' COMMENT '账户没有超时',
  `account_non_locked` tinyint(1) DEFAULT '1' COMMENT '账户是否被锁定',
  `credentials_non_expired` tinyint(1) DEFAULT '1' COMMENT '凭证是否超时',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `tenant_id` int(11) DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户表';

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('108650257893560320', 'zhangsan', '$2a$10$nE17h.Qk/lV4wLbtgHE6Eep14pgUNJ3P3SW4d2.karCZdb7jdw0Eu', '15920568241', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908171046-37db21745d1142ca96fbc318d126454f.jpg?Expires=4719610000&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=WKX705%2BwdkD4xER2UT2EmY3VgIk%3D', null, null, '0', '264534534534530', '16865032@qq.com', null, null, null, '1', '1', '1', '1', '2019-02-14 02:25:59', '2020-01-13 10:33:50', 'admin', 'admin', '0', '1');
INSERT INTO `sys_account` VALUES ('108663193416503296', 'hahah', '$2a$10$/ZLAzcu1QBHXQkqHg4er4.loHjrIor4ZmSHflZO7riTFvJRyXnyZu', '15600012351', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2986383633,1787074034&fm=26&gp=0.jpg', null, null, '0', '109042886493868032', '23423423@qq.com', null, null, null, '0', '1', '1', '1', '2019-02-14 03:17:23', '2019-11-21 16:04:32', 'admin', 'admin', '0', '2');
INSERT INTO `sys_account` VALUES ('97510994686775329', 'admin', '$2a$10$Gpxlwao4pVAHJUFwLIr5ROLdhaMkP2ong/PCsE0JAlCRF3mlTMsFi', '15920584714', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2986383633,1787074034&fm=26&gp=0.jpg', '个人简介', '个人地址', '1', '264534534534530', '3242342@qq.com', '超管admin', null, '1', '1', '1', '1', '1', '2019-01-13 00:41:26', '2019-11-21 16:04:30', null, null, '0', '3');

-- ----------------------------
-- Table structure for `sys_account_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_roles`;
CREATE TABLE `sys_account_roles` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `aid` varchar(32) NOT NULL COMMENT '账户id',
  `rid` varchar(32) NOT NULL COMMENT '角色id',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账户角色表';

-- ----------------------------
-- Records of sys_account_roles
-- ----------------------------
INSERT INTO `sys_account_roles` VALUES ('106567837266481152', '97510994686775329', '105839387249479680', null, null, null, null, null);
INSERT INTO `sys_account_roles` VALUES ('108649836575723520', '108649835707502592', '106118882577616896', null, null, null, null, null);
INSERT INTO `sys_account_roles` VALUES ('208699780094758912', '108663193416503296', '107992614078255104', null, null, null, null, null);
INSERT INTO `sys_account_roles` VALUES ('211584340860080128', '211584340310626304', '107992614078255104', null, null, null, null, null);
INSERT INTO `sys_account_roles` VALUES ('211590597155229696', '211590596182151168', '107992614078255104', null, null, null, null, null);
INSERT INTO `sys_account_roles` VALUES ('228977014680326144', '108650257893560320', '106118882577616896', 'admin', null, '0', null, null);

-- ----------------------------
-- Table structure for `sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `leader` varchar(50) DEFAULT NULL COMMENT '部门主管id',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '上级部门id',
  `sort` int(11) DEFAULT NULL COMMENT '排序编号',
  `enabled` int(11) DEFAULT NULL COMMENT '是否启用',
  `is_parent` bit(1) DEFAULT b'0' COMMENT '是否为父级节点',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_del` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统部门表';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('107278820674899968', '技术1部', null, '264534534534530', '0', '1', '', '2019-01-31 02:06:21', '2019-01-31 11:56:26', null, null, '0');
INSERT INTO `sys_department` VALUES ('107278928686616576', '技术1-2部222', null, '107278820674899968', '0', '1', '', '2019-01-31 02:06:49', '2019-01-31 02:30:43', null, null, '0');
INSERT INTO `sys_department` VALUES ('107279323261571072', '技术1-1部', null, '107278820674899968', '0', '1', '', '2019-01-31 02:08:31', '2019-01-31 02:08:31', null, null, '0');
INSERT INTO `sys_department` VALUES ('109042886493868032', '上海分部', null, '0', '0', '1', '', '2019-02-14 16:33:58', '2019-02-14 16:40:51', null, null, '0');
INSERT INTO `sys_department` VALUES ('109044699188170752', '测试部', null, '109042886493868032', '0', '1', '', '2019-02-14 16:41:10', '2019-02-14 16:41:10', null, null, '0');
INSERT INTO `sys_department` VALUES ('214201379248410624', '市场部', null, '264534534534530', '0', '1', '', '2019-12-03 16:12:09', '2019-12-03 16:12:09', 'zhangsan', null, '0');
INSERT INTO `sys_department` VALUES ('264534534534530', '总部', '97510994686775329', '0', '1', '1', '', '2019-01-28 09:11:44', '2019-01-31 02:02:38', null, null, '0');
INSERT INTO `sys_department` VALUES ('264534534534531', ' 运营部', '97510994686892372', '264534534534530', '1', '1', '', '2019-01-28 09:12:20', '2019-01-31 02:03:16', null, null, '0');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `title` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` decimal(8,2) DEFAULT NULL COMMENT '排序',
  `type` varchar(200) DEFAULT NULL COMMENT '类型',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_del` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统字典分类表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('109134282798141440', '按钮', '0.00', 'btns', '按钮类型aaaaa', '2019-02-14 22:37:09', '2019-02-14 22:37:09', null, null, '0');
INSERT INTO `sys_dict` VALUES ('112019561045299200', '业务表', '0.00', 'business_table', '业务表名称', '2019-02-18 16:39:33', '2019-02-18 16:39:33', 'admin', 'admin', '0');
INSERT INTO `sys_dict` VALUES ('112020729888772096', '业务表单路由', '0.00', 'business_form_route', '业务表单路由', '2019-02-18 16:39:33', '2019-02-18 16:39:33', 'admin', 'admin', '0');
INSERT INTO `sys_dict` VALUES ('112300759982280704', '流程节点类型', '0.00', 'process_node_type', '流程节点类型', '2019-02-18 19:32:05', '2019-02-18 19:32:05', 'admin', 'admin', '0');
INSERT INTO `sys_dict` VALUES ('113500643234156544', '请假类型', '0.00', 'leave', '请假', '2019-02-19 20:53:08', '2019-02-19 20:53:08', 'admin', 'admin', '0');
INSERT INTO `sys_dict` VALUES ('114108805679157248', '优先级', '0.00', 'priority', '', '2019-02-20 00:38:17', '2019-02-20 00:38:17', 'admin', 'admin', '0');
INSERT INTO `sys_dict` VALUES ('ssss', '性别', '0.10', 'sex', '性别', '2019-02-14 18:52:38', '2019-02-14 18:53:04', null, null, '0');

-- ----------------------------
-- Table structure for `sys_dict_data`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `dict_id` varchar(50) DEFAULT NULL COMMENT '分类id',
  `type` varchar(50) DEFAULT NULL COMMENT '分类名称(字典类型)',
  `title` varchar(50) DEFAULT NULL COMMENT '键',
  `val` varchar(50) DEFAULT NULL COMMENT '值',
  `sort` decimal(6,2) DEFAULT NULL COMMENT '排序',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_del` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统分类数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES ('109381777952346112', '109134282798141440', 'btns', '添加操作（ADD）', 'add', '0.00', '添加', '0', '2019-02-15 01:18:24', '2019-02-15 01:18:24', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109382543282802688', 'ssss', 'sex', '男', '1', '0.00', 'male', '0', '2019-02-15 01:21:26', '2019-02-15 01:21:26', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109382659376943104', 'ssss', 'sex', '女', '0', '0.10', 'female', '0', '2019-02-15 01:21:54', '2019-02-15 01:21:54', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109382766780485632', 'ssss', 'sex', '未知', '3', '0.00', 'unknown', '0', '2019-02-15 01:22:19', '2019-02-15 01:22:19', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109382922359803904', '109134282798141440', 'btns', '修改操作（UPDATE）', 'update', '2.00', '修改', '0', '2019-02-15 01:22:56', '2019-02-15 01:22:56', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109383185778872320', '109134282798141440', 'btns', '删除操作（DELETE）', 'delete', '3.00', '', '0', '2019-02-15 01:22:56', '2019-02-15 01:22:56', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109383494014078976', '109134282798141440', 'btns', '启用操作（ENABLE）', 'enable', '4.00', '', '0', '2019-02-15 01:22:56', '2019-02-15 01:22:56', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109383551073390592', '109134282798141440', 'btns', '禁用操作（DISABLE）', 'disable', '5.00', '', '0', '2019-02-15 01:22:56', '2019-02-15 01:22:56', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109383677665873920', '109134282798141440', 'btns', '批量删除（BATCH-DELETE）', 'batch-del', '6.00', '', '0', '2019-02-15 01:22:56', '2019-02-15 01:22:56', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109384351816355840', '109134282798141440', 'btns', '清空操作（CLEAR）', 'clear', '0.00', '测试', '0', '2019-02-15 01:22:56', '2019-02-15 01:22:56', null, 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('109393044230377472', '109134282798141440', 'btns', '导入操作（IMPORT）', 'import', '7.00', '导入', '0', '2019-02-15 02:03:10', '2019-02-15 02:03:10', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109393137583001600', '109134282798141440', 'btns', '导出操作（EXPROT）', 'export', '8.00', '导出', '0', '2019-02-15 02:03:32', '2019-02-15 02:03:32', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109393296324825088', '109134282798141440', 'btns', '搜索操作（SEARCH）', 'search', '9.00', '提交搜索', '0', '2019-02-15 02:04:10', '2019-02-15 02:04:10', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('109393462083719168', '109134282798141440', 'btns', '设置默认操作（SET_DEFAULT）', 'set_default', '10.00', '设置默认', '0', '2019-02-15 02:04:50', '2019-02-15 02:04:50', null, null, '0');
INSERT INTO `sys_dict_data` VALUES ('112019913324892160', '112019561045299200', 'business_table', '请假申请表', 'leave', '0.00', '请假申请', '0', '2019-02-18 16:40:57', '2019-02-18 16:40:57', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('112020511671717888', '112019561045299200', 'business_table', '合同表', 'contract', '0.20', '合同表', '0', '2019-02-18 16:43:20', '2019-02-18 16:43:20', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('112020884268519424', '112020729888772096', 'business_form_route', '请假', 'leave', '0.00', '请假', '0', '2019-02-18 16:40:57', '2019-02-18 16:40:57', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('112300842987556864', '112300759982280704', 'process_node_type', '开始节点', '0', '0.00', '流程开始节点', '0', '2019-02-18 19:32:24', '2019-02-18 19:32:24', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('112300937887879168', '112300759982280704', 'process_node_type', '审批节点', '1', '0.00', '流程审批', '0', '2019-02-18 19:32:47', '2019-02-18 19:32:47', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('112301018418515968', '112300759982280704', 'process_node_type', '结束节点', '2', '0.00', '流程结束', '0', '2019-02-18 19:32:24', '2019-02-18 19:32:24', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('113500963620261888', '113500643234156544', 'leave', '事假', '1', '0.00', '事假', '0', '2019-02-19 20:54:24', '2019-02-19 20:54:24', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('113501005118705664', '113500643234156544', 'leave', '病假', '2', '0.00', '病假', '0', '2019-02-19 20:54:34', '2019-02-19 20:54:34', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('113501053130903552', '113500643234156544', 'leave', '产假', '3', '0.00', '产假', '0', '2019-02-19 20:54:46', '2019-02-19 20:54:46', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('113501087431921664', '113500643234156544', 'leave', '婚假', '4', '0.00', '婚假', '0', '2019-02-19 20:54:54', '2019-02-19 20:54:54', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('114108873165508608', '114108805679157248', 'priority', '普通', '1', '0.00', '普通', '0', '2019-02-20 00:38:33', '2019-02-20 00:38:33', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('114108904014614528', '114108805679157248', 'priority', '重要', '2', '0.00', '重要', '0', '2019-02-20 00:38:40', '2019-02-20 00:38:40', 'admin', 'admin', '0');
INSERT INTO `sys_dict_data` VALUES ('114108942518325248', '114108805679157248', 'priority', '紧急', '3', '0.00', '紧急', '0', '2019-02-20 00:38:50', '2019-02-20 00:38:50', 'admin', 'admin', '0');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `title` varchar(200) DEFAULT NULL COMMENT '请求标题',
  `type` int(11) DEFAULT NULL COMMENT '日志类型',
  `request_uri` varchar(200) DEFAULT NULL COMMENT '请求地址',
  `method` varchar(50) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(500) DEFAULT NULL COMMENT '请求参数',
  `user_agent` varchar(200) DEFAULT NULL COMMENT '客户端来源',
  `service_id` varchar(200) DEFAULT NULL COMMENT '服务id',
  `module_name` varchar(500) DEFAULT NULL,
  `exception` varchar(500) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(200) DEFAULT NULL COMMENT '请求ip',
  `ip_address` varchar(200) DEFAULT NULL COMMENT '归属地',
  `cost_time` int(11) DEFAULT NULL COMMENT '执行时间',
  `status` int(5) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_login_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_config`;
CREATE TABLE `sys_login_config` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `bg_video` varchar(500) DEFAULT '' COMMENT '背景视频',
  `bg_webm` varchar(500) DEFAULT '' COMMENT '背景webm',
  `bg_img` varchar(500) DEFAULT '' COMMENT '背景图片',
  `is_bg` int(1) DEFAULT '0' COMMENT '是否是背景',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录背景配置表';

-- ----------------------------
-- Records of sys_login_config
-- ----------------------------
INSERT INTO `sys_login_config` VALUES ('173983784293634048', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908141641-eeafdf1f3faf4902af1db21dd2277c9a.mp4?Expires=4719372081&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=zUT3uB1sXj8HqMUhHewzS2WWTR8%3D', '', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908141641-fdd7e20e12964736a079251b41bb1208.jpg?Expires=4719372082&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=013sMw7eV10dCjsTBZc%2FblBe1V0%3D', '1', '2019-08-14 16:41:25', '2019-09-12 10:20:28', 'admin', null, '0');
INSERT INTO `sys_login_config` VALUES ('173984106449735680', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908141642-7f66ec456a034db1850c4ed461b7c494.mp4?Expires=4719372158&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=2CtoWgJoVmRi%2Bu7jVUbPjrnKzGE%3D', '', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908141642-c4dbdb8315414b328531aa73bd64c0e1.jpg?Expires=4719372155&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=Tp3MRZENIqN6u9xQfHfsdgTY5LM%3D', '0', '2019-08-14 16:42:42', '2019-08-15 14:25:18', 'admin', null, '0');
INSERT INTO `sys_login_config` VALUES ('174311014840406016', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908151421-5154512a50f3421a85adb7f7abb0ffcf.mp4?Expires=4719450096&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=DZNMXWYzhbQqlhRROqnd1mirEWU%3D', '', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908151421-6a2b500b9e234ab0ab2bafb3cde606af.jpg?Expires=4719450101&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=7sWVONePH%2BNA2lcSenJI8mhrYUM%3D', '0', '2019-08-15 14:21:44', '2019-09-11 14:13:17', 'admin', null, '0');
INSERT INTO `sys_login_config` VALUES ('174314350352273408', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908151434-8a5d35bb8a6349d3b1983e4680b2fdb2.mp4?Expires=4719450896&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=zNJKMeFPOHiq%2BJyE%2FTaKea9W4rU%3D', '', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908151434-8455715ccef747c589111caf2fc6b126.jpg?Expires=4719450890&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=Xnx9rqXEVrwU4VrNpyz4MzFjyCg%3D', '0', '2019-08-15 14:34:59', '2019-09-12 10:20:28', 'admin', null, '0');
INSERT INTO `sys_login_config` VALUES ('174315119642152960', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908151437-00318108e4ad43eb8efb9a31a09f18e1.mp4?Expires=4719451072&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=6TLHUoM1g9nk0XGJ99IWqZf%2BBsQ%3D', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908151437-a76c28769687462f852e9b703dddbeeb.webm?Expires=4719451077&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=dKDEtUwfYKW6seJCnecNKAVzcI8%3D', 'https://hswe.oss-cn-beijing.aliyuncs.com/mycloud-admin/201908151437-fcb828e13a9f4c218a6070e0e6e29669.jpg?Expires=4719451068&OSSAccessKeyId=LTAIG3y86uEF0V8Q&Signature=2aM6aCyeXFYm10nYI%2Fn7UlrdRGI%3D', '0', '2019-08-15 14:38:03', '2019-08-15 14:38:03', 'admin', null, '0');

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单路径',
  `component` varchar(255) DEFAULT NULL COMMENT 'vue加载路径',
  `icon` varchar(255) DEFAULT NULL COMMENT '菜单图标',
  `title` varchar(255) DEFAULT NULL COMMENT '菜单标题（英文）',
  `hidden` tinyint(4) DEFAULT '0' COMMENT '是否隐藏',
  `redirect` varchar(255) DEFAULT NULL COMMENT '重定向地址',
  `always_show` tinyint(4) DEFAULT '1' COMMENT '是否展示',
  `enabled` int(11) DEFAULT '1' COMMENT '是否启用',
  `keepalived` tinyint(4) DEFAULT '0' COMMENT '是否缓存',
  `parent_id` varchar(255) DEFAULT NULL COMMENT '父节点id',
  `type` tinyint(1) DEFAULT '0' COMMENT '菜单类型（0：菜单，1按钮）',
  `btn_type` varchar(30) DEFAULT '0' COMMENT '按钮权限类型',
  `btn_code` varchar(50) DEFAULT NULL COMMENT '按钮代码',
  `sort` decimal(10,2) DEFAULT '0.00' COMMENT '排序值',
  `level` tinyint(1) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限菜单表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('106134573410160500', '系统管理', '/system', '/Layout', 'md-checkmark-circle', '系统配置', '0', null, '1', '1', '0', '0', '0', null, null, '0.00', '1', '2019-01-29 13:44:33', '2019-11-18 14:56:30', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('106134573410160501', '用户管理', '/user', '/system/user-manage/userManage', 'ios-people', '用户管理', '0', null, '1', '1', '0', '106134573410160500', '0', null, null, '0.00', '2', '2019-01-29 13:44:37', '2019-11-18 14:56:30', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('106134573410160502', '角色管理', 'role', '/system/role/role', 'md-contact', '角色管理', '0', null, '1', '1', '0', '106134573410160500', '0', null, null, '1.00', '2', '2019-01-29 13:44:42', '2019-12-03 16:06:11', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('106134573410160503', '菜单管理', 'menu', '/system/menu-manage/menuManage', 'md-menu', '菜单管理', '0', null, '1', '1', '0', '106134573410160500', '0', null, null, '3.00', '2', '2019-01-29 13:44:48', '2020-01-10 11:47:33', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('106134573410160504', '部门管理', 'dept', '/system/dept-manage/deptManage', 'md-nuclear', '部门管理', '0', '', '1', '1', '0', '106134573410160500', '0', null, null, '4.00', '2', '2019-01-29 13:44:52', '2019-11-18 14:56:31', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('106134573410160640', '字典管理', 'dict', '/system/dict-manage/dictManage', 'ios-apps', '字典管理', '0', null, '1', '1', '0', '106134573410160500', '0', null, null, '7.00', '2', '2019-01-29 22:27:01', '2019-11-18 15:16:01', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('106143842616479744', '添加字典', '/dict/add', '', '', '', '0', null, '1', '1', '0', '106134573410160640', '1', null, 'sys_dict_add', '0.00', '3', '2019-01-29 23:06:55', '2019-11-18 11:41:21', null, null, '0');
INSERT INTO `sys_permission` VALUES ('106145961096515584', '修改字典', '/api-user/dict/edit', '', '', '', '0', null, '1', '1', '0', '106134573410160640', '1', '1', 'sys_dict_edit', '1.22', '3', '2019-01-29 23:16:02', '2019-07-19 11:39:00', null, null, '0');
INSERT INTO `sys_permission` VALUES ('106612642122043392', '系统监控', '/sys/monitor', '/Layout', 'md-desktop', '系统监控', '0', null, '1', '1', '0', '0', '0', null, null, '2.00', '1', '2019-01-30 18:02:58', '2019-07-06 14:46:39', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('106616756197396480', 'SQL监控', 'http://localhost:9001/druid/index.html', '/system/monitor/monitor', 'md-analytics', 'SQL监控', '0', '', '1', '1', '0', '106612642122043392', '0', '0', null, '0.20', '2', '2019-01-30 18:20:41', '2019-11-18 14:36:36', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('107956490026881024', 'consul监控', 'http://10.1.70.95:8500', '/system/monitor/monitor', 'md-close', 'consul监控', '0', '', '1', '1', '0', '106612642122043392', '0', '0', null, '0.20', '2', '2019-01-31 09:26:59', '2019-12-19 10:49:35', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('107969141201178624', 'admin监控', 'http://10.1.70.71:8084/login', '/system/monitor/monitor', 'md-bonfire', 'admin监控', '0', '', '1', '1', '0', '106612642122043392', '0', '0', null, '0.30', '2', '2019-01-31 10:21:26', '2019-11-18 14:36:37', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('108413665375424512', '添加用户', '/account/add', '', '', '添加用户', '0', null, '1', '1', '0', '106134573410160501', '1', 'add', 'sys_account_add', '0.00', '3', '2019-02-13 23:56:54', '2019-11-18 14:56:30', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('108414764002054144', '编辑用户', '/account/edit', '', '', '', '0', null, '1', '1', '0', '106134573410160501', '1', '0', 'sys_account_edit', '0.00', '3', '2019-02-14 00:01:16', '2019-11-18 14:56:30', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108415040863866880', '禁用启用用户', '/account/disable', '', '', '', '0', null, '1', '1', '0', '106134573410160501', '1', '0', 'sys_account_disable', '0.20', '3', '2019-02-14 00:02:22', '2019-11-18 14:56:30', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108415255486402560', '删除用户', '/account/*', '', '', '删除用户', '0', null, '1', '1', '0', '106134573410160501', '1', 'delete', 'sys_account_del', '0.30', '3', '2019-02-14 00:03:13', '2019-11-26 11:16:28', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('108470067800313856', '添加角色', '/role/add', '', '', '', '0', null, '1', '1', '0', '106134573410160502', '1', '0', null, '0.20', '3', '2019-02-14 00:33:10', '2019-11-18 14:56:31', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108470158506332160', '编辑角色', '/role/edit', '', '', '编辑角色', '0', null, '1', '1', '0', '106134573410160502', '1', 'update', null, '0.30', '3', '2019-02-14 00:33:32', '2019-11-18 14:56:31', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('108470269089157120', '删除角色', '/role/del/**', '', '', '', '0', null, '1', '1', '0', '106134573410160502', '1', '0', null, '0.40', '3', '2019-02-14 00:33:58', '2019-11-18 14:56:31', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108470475503439872', '菜单权限', '/role/editPerm', '', '', '', '0', null, '1', '1', '0', '106134573410160502', '1', '0', null, '0.50', '3', '2019-02-14 00:34:47', '2019-11-18 14:56:31', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108470681737367552', '数据权限', '/role/editDept', '', '', '', '0', null, '1', '1', '0', '106134573410160502', '1', '0', null, '0.60', '3', '2019-02-14 00:35:37', '2019-11-18 14:56:31', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108470940857274368', '添加菜单', '/permission/add', '', '', '', '0', null, '1', '1', '0', '106134573410160503', '1', '0', null, '0.00', '3', '2019-02-14 00:36:38', '2020-01-10 11:47:15', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108471032284712960', '编辑菜单', '/permission/edit', '', '', '', '0', null, '1', '1', '0', '106134573410160503', '1', '0', null, '0.10', '3', '2019-02-14 00:37:00', '2020-01-10 11:47:17', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108471143521849344', '删除菜单', '/permission/del/**', '', '', '', '0', null, '1', '1', '0', '106134573410160503', '1', '0', null, '0.20', '3', '2019-02-14 00:37:27', '2020-01-10 11:47:20', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108471310111215616', '添加部门', '/dept/add', '', '', '', '0', null, '1', '1', '0', '106134573410160504', '1', '0', null, '0.00', '3', '2019-02-14 00:38:06', '2019-11-18 14:56:31', null, null, '0');
INSERT INTO `sys_permission` VALUES ('108471396610347008', '编辑部门', '/dept/eidt', '', '', '编辑部门', '0', null, '1', '1', '0', '106134573410160504', '1', 'update', null, '0.10', '3', '2019-02-14 00:38:27', '2019-11-18 14:56:31', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('108471485655420928', '删除部门', '/dept/del', '', '', '', '0', null, '1', '1', '0', '106134573410160504', '1', '0', null, '0.30', '3', '2019-02-14 00:38:48', '2019-11-18 14:56:31', null, null, '0');
INSERT INTO `sys_permission` VALUES ('109185669972103168', '添加字典数据', '/dictdata/add', '', '', '', '0', null, '1', '1', '0', '106134573410160640', '1', '0', null, '0.00', '3', '2019-02-14 23:44:06', '2019-11-18 11:41:47', null, null, '0');
INSERT INTO `sys_permission` VALUES ('109194757376315392', '修改字典数据', '/dictdata/update', '', '', '', '0', null, '1', '1', '0', '106134573410160640', '1', '0', null, '0.20', '3', '2019-02-15 00:20:12', '2019-11-18 11:41:49', null, null, '0');
INSERT INTO `sys_permission` VALUES ('109385739191455744', '删除字典数据', '/dictdata/del/*', '', '', '删除数据', '0', null, '1', '1', '0', '106134573410160640', '1', '3', null, '0.40', '3', '2019-02-15 01:34:08', '2019-11-18 11:41:52', null, null, '0');
INSERT INTO `sys_permission` VALUES ('109395715276410880', '工作流', '/activiti', '/Layout', 'md-pulse', '工作流', '0', null, '1', '1', '0', '0', '0', '0', null, '1.00', '1', '2019-02-15 02:13:47', '2019-07-06 14:46:45', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('109396310137769984', '我的申请', 'apply-manage', '/activiti/apply-manage/applyManage', 'md-alert', '我的申请', '0', null, '1', '1', '0', '109395715276410880', '0', '0', null, '0.10', '2', '2019-02-15 02:16:09', '2019-07-19 11:45:53', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('109396514354237440', '我的待办', 'todo-manage', '/activiti/todo-manage/todoManage', 'ios-american-football', '我的待办', '0', null, '1', '1', '0', '109395715276410880', '0', '0', null, '0.20', '2', '2019-02-15 02:16:57', '2019-07-19 11:46:34', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('109396651658973184', '我的已办', 'complate-manage', '/activiti/complate-manage/complateManage', 'ios-beer', '我的已办', '0', null, '1', '1', '0', '109395715276410880', '0', '0', null, '3.00', '2', '2019-02-15 02:17:30', '2019-07-19 11:46:51', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('109396805476683776', '运行中的流程', 'process-ins-manage', '/activiti/process-ins-manage/processInsManage', 'md-bookmark', '运行中的流程', '0', null, '1', '1', '0', '109395715276410880', '0', '0', null, '4.00', '2', '2019-02-15 02:18:07', '2019-07-19 11:47:01', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('109396987530448896', '流程管理', 'process-manage', '/activiti/process-manage/processManage', 'md-code-working', '流程管理', '0', null, '1', '1', '0', '109395715276410880', '0', '0', null, '5.00', '2', '2019-02-15 02:18:50', '2019-07-19 11:47:09', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('109397142623227904', '模型管理', 'model-manage', '/activiti/model-manage/modelManage', 'md-cube', '模型管理', '0', null, '1', '1', '0', '109395715276410880', '0', '0', null, '6.00', '2', '2019-02-15 02:19:27', '2019-07-19 11:47:17', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('109397297376268288', '流程分类管理', 'category-manage', '/activiti/category-manage/categoryManage', 'md-bowtie', '流程分类管理', '0', null, '1', '1', '0', '109395715276410880', '0', '0', null, '7.00', '2', '2019-02-15 02:20:04', '2019-07-19 11:47:35', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('110568738775896064', 'Kibana监控', 'http://10.1.70.84:5601', '/system/monitor/monitor', 'md-eye', 'Kibana监控', '0', null, '1', '1', '0', '106612642122043392', '0', '0', null, '0.50', '2', '2019-02-16 23:41:56', '2019-12-19 10:49:19', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('110569708826136576', 'es-head监控', 'http://10.2.95.29:9100/', '/system/monitor/monitor', 'logo-designernews', 'es-head监控', '0', null, '1', '1', '0', '106612642122043392', '0', '0', null, '0.70', '2', '2019-02-16 23:45:47', '2019-09-29 15:42:31', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('110570269952708608', 'Grafana监控', 'http://192.168.52.130:3000', '/system/monitor/monitor', 'ios-cog', 'Grafana监控', '0', null, '1', '1', '0', '106612642122043392', '0', '0', null, '0.90', '2', '2019-02-16 23:48:01', '2019-09-29 15:42:31', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('110658154085224448', '添加模型', '/model/add', '', '', '', '0', null, '1', '1', '0', '109397142623227904', '1', '0', null, '0.00', '3', '2019-02-17 05:37:14', '2019-12-27 11:08:53', null, null, '0');
INSERT INTO `sys_permission` VALUES ('110919528413663232', '部署发布模型', '/api-act/model/deploy', '', '', '', '0', null, '1', '1', '0', '109397142623227904', '1', '0', null, '0.20', '3', '2019-02-17 14:55:04', '2019-07-19 11:39:56', null, null, '0');
INSERT INTO `sys_permission` VALUES ('112033111574843392', '节点编辑', 'process_node_edit', '/activiti/process-manage/processNodeEdit', 'ios-aperture', '节点编辑', '1', null, '1', '1', '0', '109395715276410880', '0', '0', null, '0.50', '2', '2019-02-18 17:33:24', '2019-07-19 11:46:40', 'admin', 'admin', '0');
INSERT INTO `sys_permission` VALUES ('164523909301932032', 'RabbitMQ监控', 'http://10.1.70.71:15672', '/system/monitor/monitor', 'ios-analytics', 'RabbitMQ监控', '0', null, '1', '1', '0', '106612642122043392', '0', '0', null, '8.00', '2', '2019-07-19 14:10:59', '2019-09-29 15:42:31', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('166752626828906496', '登录背景管理', 'loginbg', '/system/loginbg-manage/loginbg-manage', 'ios-flower', '登录背景管理', '0', null, '1', '1', '0', '106134573410160500', '0', '0', null, '9.00', '2', '2019-07-25 17:47:06', '2019-11-18 15:16:56', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('170725600422531072', '添加背景', '/sysLoginConfig/add', '', '', '添加背景', '0', null, '1', '1', '0', '166752626828906496', '1', '0', null, '0.00', '3', '2019-08-02 08:45:27', '2019-08-03 10:40:45', null, 'admin', '0');
INSERT INTO `sys_permission` VALUES ('174294960210710528', '设置背景', '/sysLoginConfig/set/*', '', '', '添加背景', '0', null, '1', '1', '0', '166752626828906496', '1', '0', null, '2.00', '3', '2019-08-15 13:17:56', '2019-11-18 15:07:43', 'admin', 'admin', '0');
INSERT INTO `sys_permission` VALUES ('219919116859871232', 'SKY-walking监控', 'http://10.1.70.84:8080', '/system/monitor/monitor', 'ios-bug', 'SKY-walking监控', '0', null, '1', '1', '0', '106612642122043392', '0', '0', null, '0.00', '2', '2019-12-19 10:52:24', '2019-12-19 10:54:05', 'admin', 'admin', '0');
INSERT INTO `sys_permission` VALUES ('219919912364150785', 'Swagger文档', 'http://localhost:8443/swagger-ui.html', '/system/monitor/monitor', 'md-browsers', 'Swagger文档', '0', null, '1', '1', '0', '106612642122043392', '0', '0', null, '3.00', '2', '2019-12-19 10:55:34', '2019-12-19 10:55:34', 'admin', null, '0');
INSERT INTO `sys_permission` VALUES ('227960687266107392', '商铺管理', 'test', '/Layout', 'md-add-circle', '商铺管理', '0', null, '1', '1', '0', '0', '0', '0', null, '0.00', '1', '2020-01-10 15:26:44', '2020-01-10 15:26:44', 'admin', null, '0');
INSERT INTO `sys_permission` VALUES ('227960735903256576', '商铺添加', 'test', 'test', 'md-albums', '商铺添加', '0', null, '1', '1', '0', '227960687266107392', '0', '0', null, '0.00', '2', '2020-01-10 15:26:55', '2020-01-10 15:27:05', 'admin', 'admin', '0');

-- ----------------------------
-- Table structure for `sys_roles`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `code` varchar(255) NOT NULL COMMENT '角色代码',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `def` tinyint(1) DEFAULT '0' COMMENT '是否设为默认角色',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `data_type` tinyint(1) DEFAULT '0' COMMENT '数据权限类型',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of sys_roles
-- ----------------------------
INSERT INTO `sys_roles` VALUES ('105839387249479680', 'ROLE_ADMIN', '超级管理员', '1', '超管', '1', '2019-01-29 14:18:47', '2019-12-03 15:52:38', null, 'admin', '0');
INSERT INTO `sys_roles` VALUES ('106118882577616896', 'ROLE_NORMAL', '普通管理员', '0', '管理员', '1', '2019-01-29 21:19:28', '2019-12-03 15:39:30', 'admin', 'admin', '0');
INSERT INTO `sys_roles` VALUES ('107992614078255104', 'ROLE_GUEST', '游客', '0', '游客', '1', '2019-01-31 12:02:29', '2019-12-03 15:52:42', null, 'admin', '0');
INSERT INTO `sys_roles` VALUES ('214186886560747520', 'ROLE_XX', '测试', '0', '士大夫22', '0', '2019-12-03 15:14:34', '2019-12-03 15:41:48', 'admin', 'admin', '1');

-- ----------------------------
-- Table structure for `sys_roles_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_dept`;
CREATE TABLE `sys_roles_dept` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `rid` varchar(32) DEFAULT NULL COMMENT '角色id',
  `dept_id` varchar(32) DEFAULT NULL COMMENT '部门id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
  `is_del` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门数据权限表';

-- ----------------------------
-- Records of sys_roles_dept
-- ----------------------------
INSERT INTO `sys_roles_dept` VALUES ('106588771880800256', '105839387249479680', '264534534534530', '2019-01-30 16:20:12', '2019-01-30 16:20:12', null, null, '0');
INSERT INTO `sys_roles_dept` VALUES ('106588771880800257', '105839387249479680', '264534534534531', '2019-01-30 16:20:12', '2019-01-30 16:20:12', null, null, '0');
INSERT INTO `sys_roles_dept` VALUES ('106588771880800258', '105839387249479680', '264534534534531', '2019-01-30 16:20:12', '2019-01-30 16:20:12', null, null, '0');
INSERT INTO `sys_roles_dept` VALUES ('228970109211774976', '107992614078255104', '214201379248410624', '2020-01-13 10:17:49', '2020-01-13 10:17:49', 'admin', null, '0');
INSERT INTO `sys_roles_dept` VALUES ('228970109211774977', '107992614078255104', '264534534534531', '2020-01-13 10:17:49', '2020-01-13 10:17:49', 'admin', null, '0');
INSERT INTO `sys_roles_dept` VALUES ('228970142359359488', '106118882577616896', '107278928686616576', '2020-01-13 10:17:57', '2020-01-13 10:17:57', 'admin', null, '0');
INSERT INTO `sys_roles_dept` VALUES ('228970142359359489', '106118882577616896', '107279323261571072', '2020-01-13 10:17:57', '2020-01-13 10:17:57', 'admin', null, '0');

-- ----------------------------
-- Table structure for `sys_roles_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_permission`;
CREATE TABLE `sys_roles_permission` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `rid` varchar(32) NOT NULL COMMENT '角色id',
  `pid` varchar(32) NOT NULL COMMENT '权限id',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_roles_permission
-- ----------------------------
INSERT INTO `sys_roles_permission` VALUES ('190629363958419456', '106118882577616896', '106612642122043392', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('190629364008751104', '106118882577616896', '106616756197396480', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('190629364084248576', '106118882577616896', '107956490026881024', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('190629364134580224', '106118882577616896', '107969141201178624', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('190629364323323904', '106118882577616896', '107969309325660160', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('190629364394627072', '106118882577616896', '110568738775896064', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('190629364428181504', '106118882577616896', '110569708826136576', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('190629364457541632', '106118882577616896', '110570269952708608', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('190629364495290368', '106118882577616896', '164523909301932032', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214188044415143936', '214186886560747520', '106134573410160500', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214188044486447104', '214186886560747520', '106134573410160501', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214188044633247744', '214186886560747520', '108413665375424512', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214188044738105344', '214186886560747520', '108414764002054144', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214188044830380032', '214186886560747520', '108415040863866880', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214188044914266112', '214186886560747520', '108415255486402560', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214201279595941888', '107992614078255104', '106134573410160500', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214201279688216576', '107992614078255104', '106134573410160504', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214201279784685568', '107992614078255104', '108471310111215616', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214201279851794432', '107992614078255104', '108471396610347008', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214201279960846336', '107992614078255104', '108471485655420928', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214201280044732416', '107992614078255104', '106612642122043392', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214201280128618496', '107992614078255104', '106616756197396480', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214201280195727360', '107992614078255104', '110570269952708608', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('214201280271224832', '107992614078255104', '164523909301932032', null, null, null, null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149069795328', '105839387249479680', '106134573410160500', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149082378240', '105839387249479680', '106134573410160501', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149099155456', '105839387249479680', '108413665375424512', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149120126976', '105839387249479680', '108414764002054144', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149136904192', '105839387249479680', '108415040863866880', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149153681408', '105839387249479680', '108415255486402560', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149170458624', '105839387249479680', '106134573410160502', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149187235840', '105839387249479680', '108470067800313856', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149204013056', '105839387249479680', '108470158506332160', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149220790272', '105839387249479680', '108470269089157120', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149237567488', '105839387249479680', '108470475503439872', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149254344704', '105839387249479680', '108470681737367552', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149271121920', '105839387249479680', '106134573410160503', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149287899136', '105839387249479680', '108470940857274368', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149300482048', '105839387249479680', '108471032284712960', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149317259264', '105839387249479680', '108471143521849344', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149338230784', '105839387249479680', '106134573410160504', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149355008000', '105839387249479680', '108471310111215616', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149375979520', '105839387249479680', '108471396610347008', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149392756736', '105839387249479680', '108471485655420928', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149409533952', '105839387249479680', '106134573410160640', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149426311168', '105839387249479680', '106143842616479744', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149443088384', '105839387249479680', '106145961096515584', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149459865600', '105839387249479680', '109185669972103168', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149476642816', '105839387249479680', '109194757376315392', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149493420032', '105839387249479680', '109385739191455744', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149514391552', '105839387249479680', '166752626828906496', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149526974464', '105839387249479680', '170725600422531072', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149547945984', '105839387249479680', '174294960210710528', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149564723200', '105839387249479680', '106612642122043392', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149581500416', '105839387249479680', '106616756197396480', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149598277632', '105839387249479680', '107956490026881024', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149619249152', '105839387249479680', '107969141201178624', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149627637760', '105839387249479680', '110568738775896064', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149648609280', '105839387249479680', '110569708826136576', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149665386496', '105839387249479680', '110570269952708608', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149682163712', '105839387249479680', '164523909301932032', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149698940928', '105839387249479680', '219919116859871232', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149724106752', '105839387249479680', '219919912364150785', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149740883968', '105839387249479680', '109395715276410880', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149753466880', '105839387249479680', '109396310137769984', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149770244096', '105839387249479680', '109396514354237440', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149787021312', '105839387249479680', '109396651658973184', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149803798528', '105839387249479680', '109396805476683776', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149824770048', '105839387249479680', '109396987530448896', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149841547264', '105839387249479680', '109397142623227904', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149862518784', '105839387249479680', '110658154085224448', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149879296000', '105839387249479680', '110919528413663232', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149891878912', '105839387249479680', '109397297376268288', 'admin', null, '0', null, null);
INSERT INTO `sys_roles_permission` VALUES ('227960149908656128', '105839387249479680', '112033111574843392', 'admin', null, '0', null, null);

-- ----------------------------
-- Table structure for `sys_users`
-- ----------------------------
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_users
-- ----------------------------
