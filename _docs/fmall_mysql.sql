CREATE TABLE `merchant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '渠道商名称',
  `code` varchar(50) DEFAULT NULL COMMENT '渠道商编码',
  `status` int(4) NOT NULL COMMENT '有效状态(0:无效,1:有效)',
  `create_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `update_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  KEY `idx_code` (`code`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='渠道商信息表';


/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : fmall

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-19 23:13:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `hlcar_cd_dispatcher`
-- ----------------------------
DROP TABLE IF EXISTS `hlcar_cd_dispatcher`;
CREATE TABLE `hlcar_cd_dispatcher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT '' COMMENT '名称',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `login_name` varchar(20) DEFAULT NULL COMMENT '登录名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `merchant_id` bigint(20) DEFAULT NULL COMMENT '渠道商id',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `id_card_expire` timestamp NULL DEFAULT NULL COMMENT '身份证有效期',
  `depot_code` varchar(20) DEFAULT NULL COMMENT '机场三字码',
  `depot_name` varchar(10) DEFAULT NULL COMMENT '机场名称',
  `depot_floor` varchar(20) DEFAULT NULL COMMENT '机场航站楼',
  `depot_door` varchar(20) DEFAULT NULL COMMENT '航站楼门号',
  `status` int(4) NOT NULL COMMENT '有效状态(0:无效,1:有效)',
  `access_token` varchar(200) DEFAULT NULL COMMENT 'auth认证码',
  `expire_time` bigint(20) DEFAULT NULL COMMENT '过期时间',
  `create_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `update_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `sign_time` timestamp NULL DEFAULT NULL COMMENT '签到时间',
  `sign_status` int(4) DEFAULT NULL COMMENT '有效状态(1:有效,)',
  `unionid` varchar(64) DEFAULT NULL COMMENT '微信unionid',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_phone` (`phone`) USING BTREE,
  UNIQUE KEY `uidx_login_name` (`login_name`) USING BTREE,
  KEY `idx_depot_code` (`depot_code`),
  KEY `idx_depot_floor` (`depot_floor`),
  KEY `idx_merchant_id` (`merchant_id`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='调度用户信息表';

-- ----------------------------
-- Records of hlcar_cd_dispatcher
-- ----------------------------
INSERT INTO `hlcar_cd_dispatcher` VALUES ('1', null, '', 'admin', '$1$9e9195b2$VFMz3N1DfAkkWN9Jas5.D.', '1', null, null, null, null, null, null, '1', null, null, '0', '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for `hlcar_cd_merchant`
-- ----------------------------
DROP TABLE IF EXISTS `hlcar_cd_merchant`;
CREATE TABLE `hlcar_cd_merchant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '渠道名称',
  `city_id` int(8) DEFAULT NULL,
  `merchant_type` int(8) DEFAULT NULL,
  `depot_code` varchar(20) DEFAULT NULL COMMENT '机场三字码',
  `depot_name` varchar(100) DEFAULT NULL COMMENT '机场名称',
  `status` int(4) NOT NULL COMMENT '有效状态(0:无效,1:有效)',
  `create_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `update_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_depot_code` (`depot_code`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='渠道信息表';

-- ----------------------------
-- Records of hlcar_cd_merchant
-- ----------------------------
INSERT INTO `hlcar_cd_merchant` VALUES ('1', '运营部', '1', null, null, null, '1', '0', '0', null, null);

-- ----------------------------
-- Table structure for `hlcar_cd_operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `hlcar_cd_operation_log`;
CREATE TABLE `hlcar_cd_operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation_code` varchar(50) NOT NULL COMMENT '操作编码',
  `creater` varchar(20) DEFAULT NULL COMMENT '操作用户名称',
  `create_date` datetime NOT NULL COMMENT '日志生成时间',
  `type` int(11) DEFAULT NULL COMMENT '日志类型: 1：安全日志 2：表示操作日志',
  `os` varchar(20) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(20) DEFAULT NULL COMMENT '浏览器类型',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `execute_time` int(11) DEFAULT NULL COMMENT '执行时间',
  `description` varchar(4000) DEFAULT NULL COMMENT '详细描述',
  `request_param` text COMMENT '请求参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7354 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of hlcar_cd_operation_log
-- ----------------------------
INSERT INTO `hlcar_cd_operation_log` VALUES ('7335', '/login', '', '2019-12-11 21:20:44', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '12', '{\"view\":null,\"model\":{},\"empty\":false,\"reference\":true,\"viewName\":\"login\",\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7336', '/login', '', '2019-12-11 21:26:10', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '2', '{\"view\":null,\"model\":{},\"empty\":false,\"reference\":true,\"viewName\":\"login\",\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7337', '/login', '', '2019-12-11 21:26:46', null, 'Unknown', 'Unknown', '127.0.0.1', '0', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7338', '/login', '', '2019-12-11 21:26:45', null, 'Unknown', 'Unknown', '127.0.0.1', '1', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7339', '/login', '', '2019-12-11 21:26:46', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '7', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7340', '/login', '', '2019-12-11 22:02:20', null, 'Windows 7', 'Chrome 45', '127.0.0.1', '1', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7341', '/login', '', '2019-12-19 22:02:04', null, 'Unknown', 'Unknown', '127.0.0.1', '10', '{\"view\":null,\"model\":{},\"empty\":false,\"modelMap\":{},\"viewName\":\"login\",\"reference\":true}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7342', '/login', '', '2019-12-19 22:02:04', null, 'Unknown', 'Unknown', '127.0.0.1', '13', '{\"view\":null,\"model\":{},\"empty\":false,\"modelMap\":{},\"viewName\":\"login\",\"reference\":true}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7343', '/login', '', '2019-12-19 22:02:05', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '4', '{\"view\":null,\"model\":{},\"empty\":false,\"modelMap\":{},\"viewName\":\"login\",\"reference\":true}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7344', '/login', '', '2019-12-19 22:05:15', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '0', '{\"view\":null,\"model\":{},\"empty\":false,\"modelMap\":{},\"viewName\":\"login\",\"reference\":true}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7345', '/login', '', '2019-12-19 22:07:59', null, 'Unknown', 'Unknown', '127.0.0.1', '7', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7346', '/login', '', '2019-12-19 22:08:00', null, 'Unknown', 'Unknown', '127.0.0.1', '1', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7347', '/login', '', '2019-12-19 22:08:00', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '1', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7348', '/login', '', '2019-12-19 22:12:11', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '1', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7349', '/login', '', '2019-12-19 22:23:53', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '2', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7350', '/login', '', '2019-12-19 22:25:52', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '2', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7351', '/login', '', '2019-12-19 22:34:08', null, 'Windows 7', 'Chrome 45', '0:0:0:0:0:0:0:1', '2', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7352', '/login', '', '2019-12-19 22:35:14', null, 'Windows 7', 'Chrome 43', '0:0:0:0:0:0:0:1', '1', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');
INSERT INTO `hlcar_cd_operation_log` VALUES ('7353', '/login', '', '2019-12-19 22:52:48', null, 'Windows 7', 'Chrome 43', '0:0:0:0:0:0:0:1', '2', '{\"view\":null,\"model\":{},\"empty\":false,\"viewName\":\"login\",\"reference\":true,\"modelMap\":{}}', '{}');

-- ----------------------------
-- Table structure for `hlcar_cd_permission`
-- ----------------------------
DROP TABLE IF EXISTS `hlcar_cd_permission`;
CREATE TABLE `hlcar_cd_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '节点id',
  `pid` bigint(20) DEFAULT NULL COMMENT '父节点id',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `permission_level` int(11) DEFAULT NULL COMMENT '权限级别，1:菜单，2:页面按钮（增删改查）',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `perm_code` varchar(50) DEFAULT NULL COMMENT '权限编码',
  `icon` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '-1:禁用，1:正常',
  `description` varchar(1000) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8 COMMENT='供应商权限表';

-- ----------------------------
-- Records of hlcar_cd_permission
-- ----------------------------
INSERT INTO `hlcar_cd_permission` VALUES ('1', null, '父级菜单1', '1', '1', 'baidu.com', '1', '11', '1', '父级菜单1', '2019-12-11 21:26:03', '2019-12-19 22:50:29');
INSERT INTO `hlcar_cd_permission` VALUES ('2', '1', '子菜单1', '1', '2', '1212', '1', '22', '1', null, '2019-12-19 22:34:02', '2019-12-19 22:52:30');

-- ----------------------------
-- Table structure for `hlcar_cd_role`
-- ----------------------------
DROP TABLE IF EXISTS `hlcar_cd_role`;
CREATE TABLE `hlcar_cd_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `code` varchar(20) NOT NULL COMMENT '角色code',
  `status` int(11) DEFAULT NULL COMMENT '-1:禁用，1:正常',
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='供应商角色表';

-- ----------------------------
-- Records of hlcar_cd_role
-- ----------------------------
INSERT INTO `hlcar_cd_role` VALUES ('1', '管理员', '1', '1', null, '2019-12-19 22:00:38', '2019-12-19 22:00:38');

-- ----------------------------
-- Table structure for `hlcar_cd_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `hlcar_cd_role_permission`;
CREATE TABLE `hlcar_cd_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(20) DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_permission_id` (`permission_id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5234 DEFAULT CHARSET=utf8 COMMENT='供应商角色权限关联表';

-- ----------------------------
-- Records of hlcar_cd_role_permission
-- ----------------------------
INSERT INTO `hlcar_cd_role_permission` VALUES ('1', '1', '1', '2019-12-19 22:01:04', '2019-12-19 22:01:04');
INSERT INTO `hlcar_cd_role_permission` VALUES ('2', '1', '2', '2019-12-19 22:45:04', '2019-12-19 22:45:04');

-- ----------------------------
-- Table structure for `hlcar_cd_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `hlcar_cd_user_role`;
CREATE TABLE `hlcar_cd_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8 COMMENT='供应商用户角色关联表';

-- ----------------------------
-- Records of hlcar_cd_user_role
-- ----------------------------
INSERT INTO `hlcar_cd_user_role` VALUES ('1', '1', '1', '2019-12-19 22:23:08', '2019-12-19 22:23:08');

-- ----------------------------
-- Table structure for `operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operation_code` varchar(50) NOT NULL COMMENT '操作编码',
  `creater` varchar(20) DEFAULT NULL COMMENT '操作用户名称',
  `create_date` datetime NOT NULL COMMENT '日志生成时间',
  `type` int(11) DEFAULT NULL COMMENT '日志类型: 1：安全日志 2：表示操作日志',
  `os` varchar(20) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(20) DEFAULT NULL COMMENT '浏览器类型',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `execute_time` int(11) DEFAULT NULL COMMENT '执行时间',
  `description` varchar(4000) DEFAULT NULL COMMENT '详细描述',
  `request_param` varchar(2000) DEFAULT NULL COMMENT '请求参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9522 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of operation_log
-- ----------------------------

-- ----------------------------
-- Table structure for `test_case_sum`
-- ----------------------------
DROP TABLE IF EXISTS `test_case_sum`;
CREATE TABLE `test_case_sum` (
  `id` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_case_sum
-- ----------------------------
INSERT INTO `test_case_sum` VALUES ('1', '1');
INSERT INTO `test_case_sum` VALUES ('1', '1');
INSERT INTO `test_case_sum` VALUES ('2', '2');
INSERT INTO `test_case_sum` VALUES ('2', '3');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `passWord` varchar(32) DEFAULT NULL COMMENT '密码',
  `user_sex` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  `score` int(11) DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=158 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('28', '张三', '123', 'MAN', '三三', null);
INSERT INTO `users` VALUES ('29', '李四', '456', 'MAN', '四四', null);
INSERT INTO `users` VALUES ('30', '菜菜', '123', 'WOMAN', '菜', null);
INSERT INTO `users` VALUES ('31', '张三', '123', 'WOMAN', '三三', null);
INSERT INTO `users` VALUES ('32', '刘谦益1', null, null, '', null);
INSERT INTO `users` VALUES ('33', '刘谦益0', null, null, null, null);
INSERT INTO `users` VALUES ('34', '刘谦益1', null, null, '', null);
INSERT INTO `users` VALUES ('35', '刘谦益2', null, null, null, null);
INSERT INTO `users` VALUES ('36', '刘谦益3', null, null, null, null);
INSERT INTO `users` VALUES ('37', '刘谦益4', null, null, null, null);
INSERT INTO `users` VALUES ('38', '刘谦益5', null, null, null, null);
INSERT INTO `users` VALUES ('39', '刘谦益6', null, null, null, null);
INSERT INTO `users` VALUES ('40', '刘谦益7', null, null, null, null);
INSERT INTO `users` VALUES ('41', '刘谦益8', null, null, null, null);
INSERT INTO `users` VALUES ('42', '刘谦益9', null, null, null, null);
INSERT INTO `users` VALUES ('43', '刘谦益10', null, null, null, null);
INSERT INTO `users` VALUES ('44', '刘谦益11', null, null, null, null);
INSERT INTO `users` VALUES ('45', '刘谦益12', null, null, null, null);
INSERT INTO `users` VALUES ('46', '刘谦益13', null, null, null, null);
INSERT INTO `users` VALUES ('47', '刘谦益14', null, null, null, null);
INSERT INTO `users` VALUES ('48', '刘谦益15', null, null, null, null);
INSERT INTO `users` VALUES ('49', '刘谦益16', null, null, null, null);
INSERT INTO `users` VALUES ('50', '刘谦益17', null, null, null, null);
INSERT INTO `users` VALUES ('51', '刘谦益18', null, null, null, null);
INSERT INTO `users` VALUES ('52', '刘谦益19', null, null, null, null);
INSERT INTO `users` VALUES ('53', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('54', '刘谦益0', null, null, null, null);
INSERT INTO `users` VALUES ('55', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('56', '刘谦益2', null, null, null, null);
INSERT INTO `users` VALUES ('57', '刘谦益3', null, null, null, null);
INSERT INTO `users` VALUES ('58', '刘谦益4', null, null, null, null);
INSERT INTO `users` VALUES ('59', '刘谦益5', null, null, null, null);
INSERT INTO `users` VALUES ('60', '刘谦益6', null, null, null, null);
INSERT INTO `users` VALUES ('61', '刘谦益7', null, null, null, null);
INSERT INTO `users` VALUES ('62', '刘谦益8', null, null, null, null);
INSERT INTO `users` VALUES ('63', '刘谦益9', null, null, null, null);
INSERT INTO `users` VALUES ('64', '刘谦益10', null, null, null, null);
INSERT INTO `users` VALUES ('65', '刘谦益11', null, null, null, null);
INSERT INTO `users` VALUES ('66', '刘谦益12', null, null, null, null);
INSERT INTO `users` VALUES ('67', '刘谦益13', null, null, null, null);
INSERT INTO `users` VALUES ('68', '刘谦益14', null, null, null, null);
INSERT INTO `users` VALUES ('69', '刘谦益15', null, null, null, null);
INSERT INTO `users` VALUES ('70', '刘谦益16', null, null, null, null);
INSERT INTO `users` VALUES ('71', '刘谦益17', null, null, null, null);
INSERT INTO `users` VALUES ('72', '刘谦益18', null, null, null, null);
INSERT INTO `users` VALUES ('73', '刘谦益19', null, null, null, null);
INSERT INTO `users` VALUES ('74', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('75', '刘谦益0', null, null, null, null);
INSERT INTO `users` VALUES ('76', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('77', '刘谦益2', null, null, null, null);
INSERT INTO `users` VALUES ('78', '刘谦益3', null, null, null, null);
INSERT INTO `users` VALUES ('79', '刘谦益4', null, null, null, null);
INSERT INTO `users` VALUES ('80', '刘谦益5', null, null, null, null);
INSERT INTO `users` VALUES ('81', '刘谦益6', null, null, null, null);
INSERT INTO `users` VALUES ('82', '刘谦益7', null, null, null, null);
INSERT INTO `users` VALUES ('83', '刘谦益8', null, null, null, null);
INSERT INTO `users` VALUES ('84', '刘谦益9', null, null, null, null);
INSERT INTO `users` VALUES ('85', '刘谦益10', null, null, null, null);
INSERT INTO `users` VALUES ('86', '刘谦益11', null, null, null, null);
INSERT INTO `users` VALUES ('87', '刘谦益12', null, null, null, null);
INSERT INTO `users` VALUES ('88', '刘谦益13', null, null, null, null);
INSERT INTO `users` VALUES ('89', '刘谦益14', null, null, null, null);
INSERT INTO `users` VALUES ('90', '刘谦益15', null, null, null, null);
INSERT INTO `users` VALUES ('91', '刘谦益16', null, null, null, null);
INSERT INTO `users` VALUES ('92', '刘谦益17', null, null, null, null);
INSERT INTO `users` VALUES ('93', '刘谦益18', null, null, null, null);
INSERT INTO `users` VALUES ('94', '刘谦益19', null, null, null, null);
INSERT INTO `users` VALUES ('95', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('96', '刘谦益0', null, null, null, null);
INSERT INTO `users` VALUES ('97', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('98', '刘谦益2', null, null, null, null);
INSERT INTO `users` VALUES ('99', '刘谦益3', null, null, null, null);
INSERT INTO `users` VALUES ('100', '刘谦益4', null, null, null, null);
INSERT INTO `users` VALUES ('101', '刘谦益5', null, null, null, null);
INSERT INTO `users` VALUES ('102', '刘谦益6', null, null, null, null);
INSERT INTO `users` VALUES ('103', '刘谦益7', null, null, null, null);
INSERT INTO `users` VALUES ('104', '刘谦益8', null, null, null, null);
INSERT INTO `users` VALUES ('105', '刘谦益9', null, null, null, null);
INSERT INTO `users` VALUES ('106', '刘谦益10', null, null, null, null);
INSERT INTO `users` VALUES ('107', '刘谦益11', null, null, null, null);
INSERT INTO `users` VALUES ('108', '刘谦益12', null, null, null, null);
INSERT INTO `users` VALUES ('109', '刘谦益13', null, null, null, null);
INSERT INTO `users` VALUES ('110', '刘谦益14', null, null, null, null);
INSERT INTO `users` VALUES ('111', '刘谦益15', null, null, null, null);
INSERT INTO `users` VALUES ('112', '刘谦益16', null, null, null, null);
INSERT INTO `users` VALUES ('113', '刘谦益17', null, null, null, null);
INSERT INTO `users` VALUES ('114', '刘谦益18', null, null, null, null);
INSERT INTO `users` VALUES ('115', '刘谦益19', null, null, null, null);
INSERT INTO `users` VALUES ('116', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('117', '刘谦益0', null, null, null, null);
INSERT INTO `users` VALUES ('118', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('119', '刘谦益2', null, null, null, null);
INSERT INTO `users` VALUES ('120', '刘谦益3', null, null, null, null);
INSERT INTO `users` VALUES ('121', '刘谦益4', null, null, null, null);
INSERT INTO `users` VALUES ('122', '刘谦益5', null, null, null, null);
INSERT INTO `users` VALUES ('123', '刘谦益6', null, null, null, null);
INSERT INTO `users` VALUES ('124', '刘谦益7', null, null, null, null);
INSERT INTO `users` VALUES ('125', '刘谦益8', null, null, null, null);
INSERT INTO `users` VALUES ('126', '刘谦益9', null, null, null, null);
INSERT INTO `users` VALUES ('127', '刘谦益10', null, null, null, null);
INSERT INTO `users` VALUES ('128', '刘谦益11', null, null, null, null);
INSERT INTO `users` VALUES ('129', '刘谦益12', null, null, null, null);
INSERT INTO `users` VALUES ('130', '刘谦益13', null, null, null, null);
INSERT INTO `users` VALUES ('131', '刘谦益14', null, null, null, null);
INSERT INTO `users` VALUES ('132', '刘谦益15', null, null, null, null);
INSERT INTO `users` VALUES ('133', '刘谦益16', null, null, null, null);
INSERT INTO `users` VALUES ('134', '刘谦益17', null, null, null, null);
INSERT INTO `users` VALUES ('135', '刘谦益18', null, null, null, null);
INSERT INTO `users` VALUES ('136', '刘谦益19', null, null, null, null);
INSERT INTO `users` VALUES ('137', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('138', '刘谦益0', null, null, null, null);
INSERT INTO `users` VALUES ('139', '刘谦益1', null, null, null, null);
INSERT INTO `users` VALUES ('140', '刘谦益2', null, null, null, null);
INSERT INTO `users` VALUES ('141', '刘谦益3', null, null, null, null);
INSERT INTO `users` VALUES ('142', '刘谦益4', null, null, null, null);
INSERT INTO `users` VALUES ('143', '刘谦益5', null, null, null, null);
INSERT INTO `users` VALUES ('144', '刘谦益6', null, null, null, null);
INSERT INTO `users` VALUES ('145', '刘谦益7', null, null, null, null);
INSERT INTO `users` VALUES ('146', '刘谦益8', null, null, null, null);
INSERT INTO `users` VALUES ('147', '刘谦益9', null, null, null, null);
INSERT INTO `users` VALUES ('148', '刘谦益10', null, null, null, null);
INSERT INTO `users` VALUES ('149', '刘谦益11', null, null, null, null);
INSERT INTO `users` VALUES ('150', '刘谦益12', null, null, null, null);
INSERT INTO `users` VALUES ('151', '刘谦益13', null, null, null, null);
INSERT INTO `users` VALUES ('152', '刘谦益14', null, null, null, null);
INSERT INTO `users` VALUES ('153', '刘谦益15', null, null, null, null);
INSERT INTO `users` VALUES ('154', '刘谦益16', null, null, null, null);
INSERT INTO `users` VALUES ('155', '刘谦益17', null, null, null, null);
INSERT INTO `users` VALUES ('156', '刘谦益18', null, null, null, null);
INSERT INTO `users` VALUES ('157', '刘谦益19', null, null, null, null);
