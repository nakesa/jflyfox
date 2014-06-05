
delete from tb_money;
delete from tb_project;
delete from tb_column;
delete from sys_user;
delete from sys_dict_detail;
delete from sys_dict;
commit;

INSERT INTO sys_dict VALUES (9, '费用类型', 'moneyType', NULL);


INSERT INTO sys_dict_detail VALUES (24, 'moneyType', '预算', '3', '3', NULL, NULL, NULL, NULL, '2014-03-13 11:18:20', 1);
INSERT INTO sys_dict_detail VALUES (22, 'moneyType', '收入', '2', '2', NULL, NULL, NULL, NULL, '2014-03-13 11:17:40', 1);
INSERT INTO sys_dict_detail VALUES (23, 'moneyType', '支出', '1', '1', NULL, NULL, NULL, NULL, '2014-03-13 11:17:50', 1);


INSERT INTO sys_user VALUES (4, 'test', '123456', 'test', NULL, NULL, NULL, NULL, 1, '2014-02-27 16:26:46');
INSERT INTO sys_user VALUES (1, 'admin', 'admin123', NULL, NULL, NULL, NULL, NULL, 1, '2014-02-27 16:26:46');

INSERT INTO tb_project VALUES (1, '结婚', '1', '2014年4月4日XXX大酒楼结婚', '2014-03-30 16:47:21', 1);
INSERT INTO tb_project VALUES (2, '装修', '1', '三居装修', '2014-03-30 16:47:42', 1);

INSERT INTO tb_money VALUES (11, 1, '结婚预算', 100000.00, '24', NULL, '2014-03-01 17:42:41', 1, '2014-03-01');
INSERT INTO tb_money VALUES (12, 2, '装修预算', 180000.00, '24', NULL, '2014-03-01 17:42:41', 1, '2014-03-01');

INSERT INTO tb_money VALUES (144, 1, '礼花、鞭炮', 2370.00, '23', NULL, '2014-04-27 17:42:41', 1, '2014-04-26');
INSERT INTO tb_money VALUES (146, 1, '新娘美甲', 750.00, '23', NULL, '2014-04-27 17:44:51', 1, '2014-04-27');
INSERT INTO tb_money VALUES (13, 2, '装修费', 53000.00, '23', NULL, '2014-04-03 22:00:39', 1, '2014-03-10');
INSERT INTO tb_money VALUES (17, 1, '结婚照', 4200.00, '23', NULL, '2014-04-03 22:04:50', 1, '2013-04-01');
INSERT INTO tb_money VALUES (19, 2, '木地板', 5000.00, '23', NULL, '2014-04-03 22:16:28', 1, '2014-03-22');
INSERT INTO tb_money VALUES (22, 1, '父亲服装', 1220.00, '23', NULL, '2014-04-03 22:20:04', 1, '2014-04-03');
INSERT INTO tb_money VALUES (24, 1, '丈母娘服装', 1120.00, '23', NULL, '2014-04-03 22:25:40', 1, '2014-03-30');
INSERT INTO tb_money VALUES (14, 1, '新娘服装', 2550.00, '23', NULL, '2014-04-03 21:57:11', 1, '2014-03-30');
INSERT INTO tb_money VALUES (30, 1, '新郎服装', 2040.00, '23', NULL, '2014-04-05 20:20:25', 1, '2014-04-05');
INSERT INTO tb_money VALUES (61, 1, '酒席（试吃）', 4220.00, '23', NULL, '2014-04-12 22:50:55', 1, '2014-04-12');
INSERT INTO tb_money VALUES (62, 1, '糖、烟酒', 1350.00, '23', NULL, '2014-04-12 22:51:39', 1, '2014-04-12');
INSERT INTO tb_money VALUES (69, 1, '伴郎服装', 590.00, '23', NULL, '2014-04-20 21:35:12', 1, '2014-04-20');
INSERT INTO tb_money VALUES (70, 2, '窗户押金', 100.00, '23', NULL, '2014-04-20 21:36:15', 1, '2014-04-17');
INSERT INTO tb_money VALUES (71, 2, '护栏押金', 100.00, '23', NULL, '2014-04-20 21:37:42', 1, '2014-04-15');
INSERT INTO tb_money VALUES (102, 1, '结婚相关物品', 550.00, '23', '喜字、糖盒、桌牌、礼花筒。。。', '2014-04-21 15:00:53', 1, '2014-04-21');
INSERT INTO tb_money VALUES (136, 2, '燃气灶', 3200.00, '23', NULL, '2014-04-23 19:18:05', 1, '2014-04-22');
INSERT INTO tb_money VALUES (137, 2, '木门', 9000.00, '23', NULL, '2014-04-23 19:18:33', 1, '2014-04-22');
INSERT INTO tb_money VALUES (141, 2, '断桥铝窗户', 12900.00, '23', NULL, '2014-04-27 17:40:25', 1, '2014-04-25');
INSERT INTO tb_money VALUES (181, 1, '新郎母亲服装', 1558.00, '23', '袜子10元、秋衣48', '2014-04-27 17:52:02', 1, '2013-04-04');
INSERT INTO tb_money VALUES (183, 1, '饮料、烟、酒', 6710.00, '23', NULL, '2014-04-29 21:32:49', 1, '2014-04-30');
INSERT INTO tb_money VALUES (185, 1, '新娘婚庆', 13000.00, '23', '余款', '2014-05-02 13:45:51', 1, '2014-05-01');
INSERT INTO tb_money VALUES (186, 1, '新娘酒席', 49700.00, '23', NULL, '2014-05-02 13:46:19', 1, '2014-05-01');
INSERT INTO tb_money VALUES (187, 1, '份子钱_新郎', 103000.00, '22', NULL, '2014-05-02 13:48:16', 1, '2014-05-01');
INSERT INTO tb_money VALUES (188, 1, '份子钱_新娘', 68400.00, '22', '舅妈10000、山西（二姑、三姑）5800、大姨3000、山西（大姑、二婶、三婶）7800', '2014-05-02 13:49:12', 1, '2014-05-01');
INSERT INTO tb_money VALUES (192, 2, '防盗门', 1400.00, '23', NULL, '2014-05-13 18:12:21', 1, '2014-05-15');
INSERT INTO tb_money VALUES (142, 2, '护栏', 4000.00, '23', NULL, '2014-04-27 17:41:26', 1, '2014-04-26');
INSERT INTO tb_money VALUES (193, 2, '水电', 7000.00, '23', NULL, '2014-05-18 22:02:37', 1, '2014-05-16');
INSERT INTO tb_money VALUES (198, 2, '马桶', 1100.00, '23', NULL, '2014-05-21 18:04:05', 1, '2014-05-21');
INSERT INTO tb_money VALUES (199, 2, '地漏', 100.00, '23', NULL, '2014-05-21 18:04:27', 1, '2014-05-21');
INSERT INTO tb_money VALUES (204, 2, '家具（书房）', 7788.00, '23', '红苹果家具（书房、主卧衣柜）', '2014-05-25 12:34:42', 1, '2014-05-24');
INSERT INTO tb_money VALUES (205, 2, '家具（主卧）', 12788.00, '23', '意风家具', '2014-05-25 12:34:42', 1, '2014-05-24');
INSERT INTO tb_money VALUES (191, 2, '瓷砖', 6107.00, '23', '第一次6500，补砖450、退砖843', '2014-05-13 18:11:59', 1, '2014-05-13');
INSERT INTO tb_money VALUES (203, 2, '家具（次卧）', 6998.00, '23', '欧瑞家具', '2014-05-25 12:33:26', 1, '2014-05-24');
INSERT INTO tb_money VALUES (208, 2, '家具（客厅）', 17000.00, '23', '意风家具', '2014-05-25 12:34:42', 1, '2014-05-24');
INSERT INTO tb_money VALUES (206, 2, '橱柜', 7300.00, '23', NULL, '2014-05-27 10:51:34', 1, '2014-05-26');
INSERT INTO tb_money VALUES (207, 2, '大理石', 1000.00, '23', NULL, '2014-05-28 20:16:07', 1, '2014-05-28');

commit;