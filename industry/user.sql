/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : es

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-11-01 13:48:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户唯一id',
  `name` varchar(32) NOT NULL COMMENT '用户名',
  `email` varchar(32) DEFAULT NULL COMMENT '电子邮箱',
  `phone_number` varchar(15) NOT NULL COMMENT '电话号码',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `status` int(2) unsigned NOT NULL DEFAULT '0' COMMENT '用户状态 0-正常 1-封禁',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户账号创建时间',
  `last_login_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次更新记录时间',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_on_phone` (`phone_number`) USING BTREE COMMENT '用户手机号',
  UNIQUE KEY `index_on_username` (`name`) USING BTREE COMMENT '用户名索引',
  UNIQUE KEY `index_on_email` (`email`) USING BTREE COMMENT '电子邮箱索引'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本信息表';

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `name` varchar(32) NOT NULL COMMENT '用户角色名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_and_name` (`user_id`,`name`) USING BTREE,
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

