/*
 Navicat Premium Data Transfer

 Source Server         : 121.36.90.144
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : 121.36.90.144:23305
 Source Schema         : digitize_rural_education

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 14/12/2023 13:29:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `file_id` bigint NOT NULL COMMENT '文件ID',
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名称',
  `file_suffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件后缀（不含小数点）',
  `file_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件模块类型（share：共享资源，fourC：4C微课，customize：定制微课，avatar：用户头像）',
  `file_size` bigint NOT NULL COMMENT '文件大小',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件存储地址',
  `file_status` tinyint NOT NULL COMMENT '文件状态（0：启用，1：禁用）',
  `file_user_id` bigint NOT NULL COMMENT '文件所属用户的ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除（0表示未删除，1表示已删除）',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_file
-- ----------------------------
INSERT INTO `t_file` VALUES (1735137038505705474, 'avatar_admin.jpg', 'jpg', 'avatar', 2231, 'https://digitizeeducation-1306588126.cos.ap-beijing.myqcloud.com/avatar/jpg/2023/12/14/4ef18ee666ad486992a0408b999f6f1b1702523880831.jpg', 0, 1709543990343753729, '2023-12-14 11:18:02', '2023-12-14 11:18:02', 0);
INSERT INTO `t_file` VALUES (1735137219187933185, 'avatar_user.jpg', 'jpg', 'avatar', 6062, 'https://digitizeeducation-1306588126.cos.ap-beijing.myqcloud.com/avatar/jpg/2023/12/14/aa58d13003ec4ad48eba37fccdbf2a161702523923987.jpg', 0, 1733858980578934786, '2023-12-14 11:18:45', '2023-12-14 11:18:45', 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `user_account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `user_password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_role` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户角色（admin/user）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除（0表示未删除，1表示已删除）',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1709543990343753729, 'admin', '207acd61a3c1bd506d7e9a4535359f8a', NULL, NULL, 'admin', '2023-10-04 20:20:24', '2023-12-05 15:22:43', 0);
INSERT INTO `t_user` VALUES (1733858980578934786, 'antony', '207acd61a3c1bd506d7e9a4535359f8a', NULL, NULL, 'user', '2023-12-10 22:39:29', '2023-12-10 22:39:29', 0);

SET FOREIGN_KEY_CHECKS = 1;
