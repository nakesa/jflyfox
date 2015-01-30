/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : jflyfox

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2015-01-30 17:30:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_name` varchar(256) NOT NULL,
  `dict_type` varchar(64) NOT NULL,
  `dict_remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `UK_SYS_DICT_TYPE` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '目录类型', 'folderType', null);
INSERT INTO `sys_dict` VALUES ('3', '栏目类型', 'columnType', '');

-- ----------------------------
-- Table structure for `sys_dict_detail`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(64) NOT NULL,
  `detail_name` varchar(256) DEFAULT NULL,
  `detail_code` varchar(32) DEFAULT NULL,
  `detail_sort` varchar(32) DEFAULT NULL,
  `detail_type` varchar(32) DEFAULT NULL,
  `detail_state` varchar(32) DEFAULT NULL,
  `detail_content` varchar(256) DEFAULT NULL,
  `detail_remark` varchar(256) DEFAULT NULL,
  `create_time` varchar(32) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
INSERT INTO `sys_dict_detail` VALUES ('1', 'folderType', '目录', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('2', 'folderType', 'a标签', '2', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('3', 'folderType', 'a标签target', '3', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('4', 'folderType', 'html标签', '4', '4', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('11', 'columnType', '目录', '1', '1', null, null, null, null, '2013-11-15 14:11:24', '21');
INSERT INTO `sys_dict_detail` VALUES ('12', 'columnType', '新文章', '3', '3', null, null, null, null, '2013-11-15 14:11:24', '21');
INSERT INTO `sys_dict_detail` VALUES ('19', 'columnType', '文章', '2', '2', null, null, null, null, '2013-11-15 14:11:24', '21');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `realname` varchar(32) DEFAULT NULL,
  `state` varchar(32) DEFAULT NULL,
  `endtime` varchar(32) DEFAULT NULL,
  `tel` varchar(32) DEFAULT NULL,
  `address` varchar(32) DEFAULT NULL,
  `create_id` int(11) DEFAULT '0',
  `create_time` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin123', null, null, null, null, null, '1', '2014-02-27 16:26:46');
INSERT INTO `sys_user` VALUES ('4', 'test', '123456', 'test', null, null, null, null, '1', '2014-02-27 16:26:46');
INSERT INTO `sys_user` VALUES ('5', 'test1', '123456', 'test1', null, null, null, null, '1', '2015-01-30');
INSERT INTO `sys_user` VALUES ('6', 'test2', '123456', 'test2', null, null, null, null, '1', '2015-01-30');
INSERT INTO `sys_user` VALUES ('7', 'test3', '123', 'test3', null, null, null, null, '1', '2015-01-30');

-- ----------------------------
-- Table structure for `tb_article`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `folder_id` int(11) DEFAULT '1' COMMENT '目录id',
  `title` varchar(200) DEFAULT '' COMMENT '文章名称',
  `content` text COMMENT '文件内容',
  `count_view` int(11) DEFAULT '0' COMMENT '浏览数',
  `count_comment` int(11) DEFAULT '0' COMMENT '评论数',
  `type` int(11) DEFAULT '1' COMMENT '类型',
  `status` varchar(20) DEFAULT '1' COMMENT '状态：2 隐藏 1 显示',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `image_url` varchar(256) DEFAULT NULL COMMENT '图片路径',
  `publish_time` varchar(64) DEFAULT NULL COMMENT '发布时间',
  `publish_user` varchar(64) DEFAULT '1' COMMENT '发布者',
  `start_time` varchar(64) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(64) DEFAULT NULL COMMENT '结束时间',
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='文章';

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('1', '1', '个人介绍', '<p style=\"font-family:微软雅黑, Verdana, sans-serif, 宋体;\">\r\n		本人十分喜欢开源中国，开源中国 <a href=\"http://www.oschina.net\" target=\"_blank\">www.oschina.net</a>  是目前中国最大的开源技术社区。\r\n					</p>\r\n					<p style=\"font-family:微软雅黑, Verdana, sans-serif, 宋体;\">\r\n						通过“开源中国”可以学习新的技术，如Jfinal、fastjson、afinal等；\r\n						<br>可以了解最新的软件资讯，如 IT 业“国产自主开发”：扶不起的阿斗等；\r\n						<br>可以看到屌丝程序员们的无敌回复，让大家开怀一笑；\r\n						<br>并且还为广大程序员提供了能多好的资源，如Maven库、Git、在线工具等。\r\n					</p>', '0', '0', '1', '1', '1', '火影.JPG', '2014-03-05', '张三', '2015-01-29', '2015-01-23', '2015-01-28 17:29:55', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('2', '1', 'JFinal', '<p><strong>JFinal 极速开发</strong></p><p>JFinal 是基于 Java 语言的极速 WEB + ORM 框架，其核心设计目标是开发迅速、代码量少、学习简单、功能强大、轻量级、易扩展、Restful。 在拥有Java语言所有优势的同时再拥有ruby、python、php等动态语言的开发效率！为您节约更多时间，去陪恋人、家人和朋友 :)</p><p><br/></p>', '0', '0', '1', '1', '2', null, '2015-01-28', '张三', null, null, '2015-01-28 17:30:18', '2015-01-20', '1');
INSERT INTO `tb_article` VALUES ('3', '3', '金钱管理 jfinal money', '<p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><span style=\"line-height: 1.5; font-size: 10pt;\">这是一个java开发的金钱管理软件，本着帮助新人以及为学习新技术的态度。</span></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">JFinal-Money采用了简洁强大的JFinal作为web框架，加入了代码自动生成功能。</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">模板引擎用：Beetl（从JSP迁移到Beetl）</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">数据库：sqlite3，mysql和postgre</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">前端框架：bootstrap3，移动端Jquery Mobile</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><span style=\"line-height: 1.5; font-size: 10pt;\">运行效果：<a href=\"http://jmoney.sturgeon.mopaas.com/web\" target=\"_blank\" style=\"color: rgb(62, 98, 166); outline: 0px;\">http://jmoney.sturgeon.mopaas.com/web</a></span></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">源码地址：<a href=\"http://git.oschina.net/flyfox/jmoney\" target=\"_blank\" style=\"color: rgb(62, 98, 166); outline: 0px;\">http://git.oschina.net/flyfox/jmoney</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">感谢&nbsp;孤独的√3 的帮助，以及开源Jfinal，Beelt</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">首页效果图如下：</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><img src=\"http://static.oschina.net/uploads/space/2014/0605/021827_MMhD_166354.png\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"max-width: 700px;\"/></p><p><br/></p>', '0', '0', '1', '1', '2', null, '2014-06-05', '张三', null, null, '2015-01-28 17:30:23', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('5', '2', '基础单词', '<p><span style=\"white-space: nowrap;\">LOL</span></p><p><span style=\"white-space: nowrap;\"><br/></span></p><p><span style=\"white-space: nowrap;\">firstblood 第一滴血</span></p><p><span style=\"white-space: nowrap;\">An enemy has been slain 一个敌人被击杀</span></p><p><span style=\"white-space: nowrap;\">double kill 双杀</span></p><p><span style=\"white-space: nowrap;\">triple kill 三杀&nbsp;</span></p><p><span style=\"white-space: nowrap;\">quadra kill 四杀&nbsp;</span></p><p><span style=\"white-space: nowrap;\">penta kill 五杀&nbsp;</span></p><p><span style=\"white-space: nowrap;\">killing spree 大杀特杀</span></p><p><span style=\"white-space: nowrap;\">rampage 暴走</span></p><p><span style=\"white-space: nowrap;\">unstoppable 无人可挡</span></p><p><span style=\"white-space: nowrap;\">dominating 主宰比赛</span></p><p><span style=\"white-space: nowrap;\">godlike 神一般</span></p><p><span style=\"white-space: nowrap;\">legendary 超神</span></p><p><span style=\"white-space: nowrap;\"><br/></span></p><p><span style=\"white-space: nowrap;\">星期 &nbsp;&nbsp;</span></p><p><span style=\"white-space: nowrap;\"><br/></span></p><p><span style=\"white-space: nowrap;\">星期一： Mon.=Monday</span></p><p><span style=\"white-space: nowrap;\">星期二： Tues.=Tuesday</span></p><p><span style=\"white-space: nowrap;\">星期三：Wed.=Wednesday</span></p><p><span style=\"white-space: nowrap;\">星期四： Thur.=Thursday</span></p><p><span style=\"white-space: nowrap;\">星期五： Fri.=Friday</span></p><p><span style=\"white-space: nowrap;\">星期六： Sat.=Saturday&nbsp;</span></p><p><span style=\"white-space: nowrap;\">星期天： Sun.=Sunday</span></p><p><span style=\"white-space: nowrap;\"><br/></span></p><p><span style=\"white-space: nowrap;\">月份</span></p><p><span style=\"white-space: nowrap;\"><br/></span></p><p><span style=\"white-space: nowrap;\">一月份：Jan.=January</span></p><p><span style=\"white-space: nowrap;\">二月份：Feb.=February</span></p><p><span style=\"white-space: nowrap;\">三月份：Mar.=March&nbsp;</span></p><p><span style=\"white-space: nowrap;\">四月份：Apr.=April&nbsp;</span></p><p><span style=\"white-space: nowrap;\">五月份： May=May</span></p><p><span style=\"white-space: nowrap;\">六月份：Jun.=June</span></p><p><span style=\"white-space: nowrap;\">七月份：Jul.=July</span></p><p><span style=\"white-space: nowrap;\">八月份： Aug.=August&nbsp;</span></p><p><span style=\"white-space: nowrap;\">九月份： Sept.=September</span></p><p><span style=\"white-space: nowrap;\">十月份：Oct.=October</span></p><p><span style=\"white-space: nowrap;\">十一月份：Nov.=November</span></p><p><span style=\"white-space: nowrap;\">十二月份：Dec.=December</span></p><p><br/></p>', '0', '0', '1', '1', '1', null, '2015-01-28', '张三', null, null, '2015-01-28 17:48:26', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('6', '5', '信息', '<p>北京市</p>', '0', '0', '1', '1', '1', null, '2015-01-29', '张三', null, null, '2015-01-29 14:04:52', '2015-01-29', '1');
INSERT INTO `tb_article` VALUES ('7', '3', 'MP3音乐播放器FFPlayer', '<p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">FFPlayer 是一个通过&nbsp;<a target=\"_blank\" href=\"http://www.oschina.net/p/javafx\" style=\"color: rgb(62, 98, 166); outline: 0px;\">JavaFX</a>&nbsp;实现的MP3音乐播放器。</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">功能列表如下</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"></p><ol class=\" list-paddingleft-2\" style=\"margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 1.5em; list-style-position: inside; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><li><p style=\"margin-top: 0px; margin-bottom: 10px; display: inline;\"><span style=\"line-height: 1.5; font-size: 10pt;\">支持播放、暂停、上一首、下一首，时间轨，播放模式，音量调节；</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; display: inline;\"><span style=\"line-height: 1.5; font-size: 10pt;\">播放列表暂时只支持拖拽添加，右键可以删除列表音乐；</span></p></li><li><p style=\"margin-top: 0px; margin-bottom: 10px; display: inline;\"><span style=\"line-height: 1.5; font-size: 10pt;\">歌词展示通过网络获取，自动展示，但是由于许多MP3不是标准格式无法获取歌曲信息的会导致下载歌词失败。</span></p></li></ol><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">源码地址：<a href=\"http://git.oschina.net/flyfox/FFPlayer\" target=\"_blank\" style=\"color: rgb(62, 98, 166); outline: 0px;\">http://git.oschina.net/flyfox/FFPlayer</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">界面如下图：</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><img src=\"http://static.oschina.net/uploads/space/2014/1126/144612_G1zJ_166354.jpg\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"max-width: 700px;\"/></p><p><br/></p>', '0', '0', '1', '1', '10', null, '2014-11-24', '张三', null, null, '2015-01-30 17:15:46', '2015-01-30', '1');
INSERT INTO `tb_article` VALUES ('8', '3', '游戏贪吃蛇JavaFX Snake', '<p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">JavaFX Snake是一个通过JavaFX实现的贪吃蛇游戏，游戏比较简单就不用再介绍了。</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">源码地址：<a href=\"http://git.oschina.net/flyfox/GameSnake\" target=\"_blank\" style=\"color: rgb(62, 98, 166); outline: 0px;\">http://git.oschina.net/flyfox/GameSnake</a></p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\">界面比较简陋凑合看吧，如下图：</p><p style=\"margin-top: 0px; margin-bottom: 10px; color: rgb(0, 0, 0); font-family: 微软雅黑, Verdana, sans-serif, 宋体; font-size: 13px; line-height: 21.3333320617676px; white-space: normal;\"><img src=\"http://static.oschina.net/uploads/space/2014/1104/161350_1Vts_166354.jpg\" alt=\"\" data-bd-imgshare-binded=\"1\" style=\"max-width: 700px;\"/></p><p><br/></p>', '0', '0', '1', '1', '10', null, '2014-11-04', '张三', null, null, '2015-01-30 17:18:27', '2015-01-30', '1');

-- ----------------------------
-- Table structure for `tb_contact`
-- ----------------------------
DROP TABLE IF EXISTS `tb_contact`;
CREATE TABLE `tb_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `addr` varchar(256) DEFAULT NULL,
  `birthday` varchar(32) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `create_time` varchar(32) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_contact
-- ----------------------------
INSERT INTO `tb_contact` VALUES ('1', '测试', '测试', null, null, null, null, '2015-01-27', '1');

-- ----------------------------
-- Table structure for `tb_folder`
-- ----------------------------
DROP TABLE IF EXISTS `tb_folder`;
CREATE TABLE `tb_folder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '目录id',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '中文名',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '路径',
  `content` text COMMENT '描述',
  `sort` int(11) DEFAULT '1' COMMENT '排序',
  `status` varchar(20) DEFAULT '1' COMMENT '状态：2 隐藏 1 显示',
  `type` int(11) DEFAULT '1',
  `jump_url` varchar(200) DEFAULT NULL,
  `update_time` varchar(64) DEFAULT NULL COMMENT '更新时间',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT '0' COMMENT '创建者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='目录';

-- ----------------------------
-- Records of tb_folder
-- ----------------------------
INSERT INTO `tb_folder` VALUES ('1', '首页', '', 'FLY的狐狸', '1', '1', '1', null, '2015-01-28 16:54:03', '2015-01-28', '1');
INSERT INTO `tb_folder` VALUES ('2', '我的英语', '', null, '4', '1', '1', null, '2015-01-28 16:54:03', '2015-01-28', '1');
INSERT INTO `tb_folder` VALUES ('3', '我的项目', '', null, '3', '1', '1', null, '2015-01-28 16:56:45', '2015-01-28', '1');
INSERT INTO `tb_folder` VALUES ('5', '关于我们', '', null, '99', '1', '1', null, '2015-01-29 14:04:23', '2015-01-29', '1');
INSERT INTO `tb_folder` VALUES ('6', '后台管理', '', '跳转到后台页面', '90', '1', '3', 'admin', '2015-01-30 13:24:58', '2015-01-30', '1');
