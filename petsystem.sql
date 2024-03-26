/*
 Navicat Premium Data Transfer

 Source Server         : PetSystem
 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Host           : localhost:3306
 Source Schema         : petsystem

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 26/03/2024 19:54:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adopt
-- ----------------------------
DROP TABLE IF EXISTS `adopt`;
CREATE TABLE `adopt`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `petType` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adopt
-- ----------------------------
INSERT INTO `adopt` VALUES (3, '三青', '20', '地球', '10000000001', '猫', '1', '2024-01-09 13:51:44');

-- ----------------------------
-- Table structure for adoption
-- ----------------------------
DROP TABLE IF EXISTS `adoption`;
CREATE TABLE `adoption`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pet_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pet_id`(`pet_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `adoption_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `adoption_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of adoption
-- ----------------------------
INSERT INTO `adoption` VALUES (1, 9, 2);
INSERT INTO `adoption` VALUES (2, 2, 1);

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `species` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` int(11) NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `available` tinyint(1) NOT NULL DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES (1, '二花', '猫', 3, '一只可爱顽皮的猫', 1, '2024-01-05 17:02:32');
INSERT INTO `pet` VALUES (2, '小基', '狗', 3, '一只友好忠诚的狗', 1, '2024-01-05 17:02:32');
INSERT INTO `pet` VALUES (3, '小白', '猫', 1, '一只毛茸茸的可爱的兔子', 1, '2024-01-05 17:02:32');
INSERT INTO `pet` VALUES (4, '小鸡毛', '狗', 2, '一只可爱顽皮的猫', 1, '2024-01-05 17:02:32');
INSERT INTO `pet` VALUES (8, '小白毛', '猫', 1, '一只毛茸茸的可爱的兔子', 1, '2024-01-05 17:02:32');
INSERT INTO `pet` VALUES (9, '小金毛', '狗', 2, '一只可爱顽皮的猫', 1, '2024-01-05 17:02:32');
INSERT INTO `pet` VALUES (11, 'a', 'a', 1, 'a', 2, '2024-01-14 17:43:01');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 'user1', 'password1', 'user1@example.com', '2024-01-05 17:02:32');
INSERT INTO `user` VALUES (2, '李四', 'user2', 'password2', 'user2@example.com', '2024-01-05 17:02:32');
INSERT INTO `user` VALUES (6, '去玩儿', 'user1', 'password1', '1395251710@qq.com', '2024-01-10 04:25:22');
INSERT INTO `user` VALUES (9, 'a', 'admina', 'admin', '1395251710@qq.com', '2024-01-10 21:16:43');

SET FOREIGN_KEY_CHECKS = 1;
