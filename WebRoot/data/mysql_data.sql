delete from sys_user;
delete from sys_dict_detail;
delete from sys_dict;
delete from tb_folder;
delete from tb_article;
delete from tb_contact;
commit;


INSERT INTO `sys_dict` VALUES ('100', '系统参数', 'systemParam', null);
INSERT INTO `sys_dict` VALUES ('1', '目录类型', 'folderType', null);
INSERT INTO `sys_dict` VALUES ('2', '目录类型', 'articleType', null);

INSERT INTO `sys_dict_detail` VALUES ('101', 'systemParam', 'FLY的狐狸', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('1', 'folderType', '目录', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('2', 'folderType', 'a标签', '2', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('3', 'folderType', 'a标签target', '3', '3', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('4', 'folderType', 'html标签', '4', '4', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('11', 'articleType', '正常', '1', '1', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('12', 'articleType', '预览', '2', '2', null, null, null, null, '2015-01-30', '1');
INSERT INTO `sys_dict_detail` VALUES ('13', 'articleType', '程序', '3', '3', null, null, null, null, '2015-01-30', '1');

INSERT INTO `sys_user` VALUES ('1', 'admin', 'LHmWhtwF/dGhJxqKJ8QXRg==', '系统管理员', '1','1', null, null, null,null, null, 'http://static.oschina.net/uploads/user/882/1765084_50.jpg?t=14031054400', null, '1', '2014-02-27 16:26:46');
INSERT INTO `sys_user` VALUES ('2', 'test', 'hZLBUnV3xZo=', '后台测试用户', '2','1', null, null, null,null, null, null, null, '1', '2014-02-27 16:26:46');
INSERT INTO `sys_user` VALUES ('3', 'webtest', 'hZLBUnV3xZo=', '前台测试用户', '3','1', null, null, null,null, null, null, null, '1', '2014-02-27 16:26:46');
INSERT INTO `sys_user` VALUES ('4', 'thridtest', 'hZLBUnV3xZo=', '第三方测试用户', '4','1', null, null, null,null, null, null, null, '1', '2014-02-27 16:26:46');

INSERT INTO `tb_folder` VALUES ('1', '首页', '', 'FLY的狐狸', '1', '1', '1', null, '2015-01-28 16:54:03', '2015-01-28', '1');
INSERT INTO `tb_folder` VALUES ('2', '我的英语', '', null, '2', '1', '1', null, '2015-01-28 16:54:03', '2015-01-28', '1');
INSERT INTO `tb_folder` VALUES ('3', '测试', '', null, '3', '2', '1', null, '2015-01-28 16:56:45', '2015-01-28', '1');
INSERT INTO `tb_folder` VALUES ('5', '关于我们', '', null, '99', '1', '1', null, '2015-01-29 14:04:23', '2015-01-29', '1');
INSERT INTO `tb_folder` VALUES ('6', '后台管理', '', null, '4', '1', '3', 'admin', '2015-01-30 13:24:58', '2015-01-30', '1');

INSERT INTO `tb_article` VALUES ('1', '1', '个人介绍', '<p style=\"font-family:微软雅黑, Verdana, sans-serif, 宋体;\">\r\n		本人十分喜欢开源中国，开源中国 <a href=\"http://www.oschina.net\" target=\"_blank\">www.oschina.net</a>  是目前中国最大的开源技术社区。\r\n					</p>\r\n					<p style=\"font-family:微软雅黑, Verdana, sans-serif, 宋体;\">\r\n						通过“开源中国”可以学习新的技术，如Jfinal、fastjson、afinal等；\r\n						<br>可以了解最新的软件资讯，如 IT 业“国产自主开发”：扶不起的阿斗等；\r\n						<br>可以看到屌丝程序员们的无敌回复，让大家开怀一笑；\r\n						<br>并且还为广大程序员提供了能多好的资源，如Maven库、Git、在线工具等。\r\n					</p>', '0', '0', '11', '1', '1', '1', '火影.JPG', '2014-03-05', '张三', '2015-01-29', '2015-01-23', '2015-01-28 17:29:55', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('100', '1', 'JFinal', '<p><strong>JFinal 极速开发</strong></p><p>JFinal 是基于 Java 语言的极速 WEB + ORM 框架，其核心设计目标是开发迅速、代码量少、学习简单、功能强大、轻量级、易扩展、Restful。 在拥有Java语言所有优势的同时再拥有ruby、python、php等动态语言的开发效率！为您节约更多时间，去陪恋人、家人和朋友 :)</p><p><br/></p>', '100', '2', '12', '1', '1', '2', null, '2015-01-28', '1', null, null, '2015-01-28 17:30:18', '2015-01-20', '1');
INSERT INTO `tb_article` VALUES ('101', '1', '金钱管理 jfinal money', '<p style=\"font-family:微软雅黑, Verdana, sans-serif, 宋体;\"><span style=\"line-height:1.5;\">这是一个java新人开发的金钱管理软件，内容比较简单，本着帮助新人以及为<span style=\"font-family:微软雅黑, Verdana, sans-serif, 宋体;background-color:#FFFFFF;\">学习</span>Jfinal的态度。</span></p><p style=\"font-family:微软雅黑, Verdana, sans-serif, 宋体;\">JFinal-Money采用了简洁强大的JFinal作为web框架，模板引擎用的是jsp，数据库用mysql，前端bootstrap框架。</p><p style=\"font-family:微软雅黑, Verdana, sans-serif, 宋体;\"><span style=\"line-height:1.5;font-size:10pt;\">运行效果：</span><a href=\"http://jmoney.jd-app.com/\" target=\"_blank\">http://jmoney.jd-app.com/</a></p><p style=\"font-family: 微软雅黑, Verdana, sans-serif, 宋体;\"><img width=\"730\" height=\"370\" src=\"http://static.oschina.net/uploads/space/2014/0605/021827_MMhD_166354.png\" alt=\"\"/></p>', '0', '0', '11', '1', '1', '2', null, '2015-01-28', '1', null, null, '2015-01-28 17:30:23', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('102', '2', '测试', null, '0', '0', '11', '1', '1', '1', null, '2015-01-28', '1', null, null, '2015-01-28 17:48:26', '2015-01-28', '1');
INSERT INTO `tb_article` VALUES ('103', '5', '信息', '<p>北京市</p>', '0', '0', '11', '1', '1', '1', null, '2015-01-29', null, null, null, '2015-01-29 14:04:52', '2015-01-29', '1');

INSERT INTO `tb_contact` VALUES ('1', '张三', '测试', null, null, null, null, '2015-01-27', '1');

commit;

--更新数据sql
update tb_comment set status = 11  where reply_userid = 0;
update tb_comment set reply_userid = 1  where reply_userid = 0;
update tb_comment set status = 21  where reply_userid != 0;

