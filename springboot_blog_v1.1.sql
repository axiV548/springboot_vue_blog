/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : blog_test

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 27/03/2021 22:52:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `comment_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `comment_admin` bit(1) NOT NULL COMMENT '是否是管理员',
  `comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `comment_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `comment_nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `comment_create_time` datetime(0) NOT NULL COMMENT '评论时间',
  `blog_id` int(8) NOT NULL COMMENT '博客ID',
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `comment_blog_id`(`blog_id`) USING BTREE,
  CONSTRAINT `comment_blog_id` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `blog_id` int(8) NOT NULL COMMENT '博客ID',
  `tag_id` int(8) NOT NULL COMMENT '标签ID',
  INDEX `blog_id`(`blog_id`) USING BTREE,
  INDEX `tag_id`(`tag_id`) USING BTREE,
  CONSTRAINT `blog_id` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`blog_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tag_id` FOREIGN KEY (`tag_id`) REFERENCES `types` (`type_tag_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blogs
-- ----------------------------
DROP TABLE IF EXISTS `blogs`;
CREATE TABLE `blogs`  (
  `blog_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '博客ID',
  `blog_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `blog_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `blog_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `blog_update_time` date NOT NULL COMMENT '更新时间',
  `blog_views` bigint(20) NULL DEFAULT NULL COMMENT '查看次数',
  `blog_comment` bit(1) NOT NULL COMMENT '是否开启评论',
  `blog_public` bit(1) NOT NULL COMMENT '是否公开',
  `blog_release` bit(1) NOT NULL COMMENT '是否发布',
  `blog_recommend` bit(1) NOT NULL COMMENT '是否推荐',
  `blog_first_img` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '首图地址',
  `user_id` int(8) NOT NULL COMMENT '关联用户ID',
  PRIMARY KEY (`blog_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types`  (
  `type_tag_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '分类_标签ID',
  `type_tag_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类_标签名',
  `type_parent_id` int(8) NOT NULL COMMENT '关联的父分类ID',
  `type_tag_level` int(8) NOT NULL COMMENT '分类_标签级别',
  PRIMARY KEY (`type_tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `user_group` int(8) NOT NULL COMMENT '用户组',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
