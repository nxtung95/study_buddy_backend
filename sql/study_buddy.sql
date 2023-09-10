/*
 Navicat Premium Data Transfer

 Source Server         : LOCAL_HOME
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : study_buddy

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 10/09/2023 23:31:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int NULL DEFAULT NULL,
  `tutor_id` int NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `input_detail` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `image_detail_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'nxtung95@gmail.com', 'Tung', 'Nghiem', '$2a$10$3eX0s2fxZBBhffpP98dlRua9lBM0BXw5WsyLGoCQL7n4ze0aC01em', 'student');
INSERT INTO `user` VALUES (2, 'nxtung30195@gmail.com', 'Nghiem', 'Tung', '$2a$10$nf42f70gVWukHLR3UzWJrOj2U5RSYZX4E5RwPxdA.TFmySJAGQsG6', 'student');
INSERT INTO `user` VALUES (3, 'nxtung3000195@gmail.com', 'Nghiem', 'Tung', '$2a$10$7ck.yphLKOOI5eJnqtzmv.LKPv5U2kJwkELcRZESgBxKbbgYOfzt.', 'student');
INSERT INTO `user` VALUES (4, 'nxtung30010195@gmail.com', 'Nghiem', 'Tung', '$2a$10$Vg4jsgL0v9nONb86q4OmzuJCHfS1FOba8jxHWYi4zfYAZYzT5I9XG', 'student');
INSERT INTO `user` VALUES (5, 'nxtung123123195@gmail.com', 'Nghiem', 'Tung', '$2a$10$hZpzIrAg7GCYaEvFvBEmnuG6MrEkNdc26z.m3RsfyI.d4MX63gRzC', 'student');
INSERT INTO `user` VALUES (6, 'nxtung12321395@gmail.com', 'Nghiem', 'Tung', '$2a$10$MzsZFy7cqBD7Yhnip9y81usH.jxswlSzDq8p1GyFMADSOBmIFQR4e', 'student');
INSERT INTO `user` VALUES (7, 'nxtung12121295@gmail.com', 'Nghiem', 'Tung', '$2a$10$ew9mgP0uBwDQgHSCbuoLJu0VL1rmKFmGIbbJ0nBgOTjgwmiqpEwEW', 'student');

-- ----------------------------
-- Table structure for user_subject
-- ----------------------------
DROP TABLE IF EXISTS `user_subject`;
CREATE TABLE `user_subject`  (
  `user_id` int NOT NULL,
  `subject_id` int NOT NULL,
  PRIMARY KEY (`user_id`, `subject_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_subject
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
