/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL_LOCAL
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : study_buddy

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 14/09/2023 19:04:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `question_id` int NULL DEFAULT NULL,
  `content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

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
  `is_voice_call` tinyint NULL DEFAULT NULL,
  `is_chat_message` tinyint NULL DEFAULT NULL,
  `is_video_call` tinyint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (21, 6, 3, 'Question 1', 0, 'Question input 1', 'z3882406469634_c1dc5a8c5a2579fabe19a7d0f44f4a5b.jpg', '2023-09-14 14:07:41', '2023-09-14 14:07:41', 0, 0, 0);
INSERT INTO `question` VALUES (23, 6, 7, 'Question 3', 0, 'Question 3 input', '', '2023-09-14 17:20:53', '2023-09-14 17:20:53', 0, 0, 0);

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES (1, 1, 'Math', '2023-09-11 15:42:21');
INSERT INTO `subject` VALUES (2, 1, 'Science', '2023-09-10 15:42:28');
INSERT INTO `subject` VALUES (3, 1, 'Social studies', '2023-09-02 15:42:35');
INSERT INTO `subject` VALUES (6, 1, 'Subject A', '2023-09-11 16:22:22');

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'nxtung95@gmail.com', 'Tung', 'Nghiem', '$2a$10$3eX0s2fxZBBhffpP98dlRua9lBM0BXw5WsyLGoCQL7n4ze0aC01em', 'student');
INSERT INTO `user` VALUES (2, 'nxtung30195@gmail.com', 'Nghiem1', 'Tung1', '$2a$10$nf42f70gVWukHLR3UzWJrOj2U5RSYZX4E5RwPxdA.TFmySJAGQsG6', 'tutor');
INSERT INTO `user` VALUES (3, 'nxtung3000195@gmail.com', 'Nghiem2', 'Tung2', '$2a$10$7ck.yphLKOOI5eJnqtzmv.LKPv5U2kJwkELcRZESgBxKbbgYOfzt.', 'tutor');
INSERT INTO `user` VALUES (4, 'nxtung30010195@gmail.com', 'Nghiem', 'Tung', '$2a$10$Vg4jsgL0v9nONb86q4OmzuJCHfS1FOba8jxHWYi4zfYAZYzT5I9XG', 'student');
INSERT INTO `user` VALUES (5, 'nxtung123123195@gmail.com', 'Nghiem', 'Tung', '$2a$10$hZpzIrAg7GCYaEvFvBEmnuG6MrEkNdc26z.m3RsfyI.d4MX63gRzC', 'student');
INSERT INTO `user` VALUES (6, 'nxtung12321395@gmail.com', 'Nghiem', 'Tung', '$2a$10$MzsZFy7cqBD7Yhnip9y81usH.jxswlSzDq8p1GyFMADSOBmIFQR4e', 'student');
INSERT INTO `user` VALUES (7, 'nxtung12121295@gmail.com', 'Nghiem3', 'Tung3', '$2a$10$ew9mgP0uBwDQgHSCbuoLJu0VL1rmKFmGIbbJ0nBgOTjgwmiqpEwEW', 'tutor');

SET FOREIGN_KEY_CHECKS = 1;
