/**
 * jae字符集修改
 * set names utf-8;
 */

delete from tb_money;
delete from tb_project;
delete from sys_user;
delete from sys_dict_detail;
delete from sys_dict;


INSERT INTO sys_user (userid,username,password,realname,state,endtime,tel,address,create_time,create_id)
VALUES (1, 'admin', 'admin123', NULL, NULL, NULL, NULL, NULL, '2014-02-27 16:26:46', 1);
INSERT INTO sys_user (userid,username,password,realname,state,endtime,tel,address,create_time,create_id)
VALUES (2, 'test', '123456', 'test', NULL, NULL, NULL, NULL, '2014-02-27 16:26:46', 1);

INSERT INTO sys_dict (dict_id,dict_name,dict_type,dict_remark) VALUES (9, '费用类型', 'moneyType', NULL);

INSERT INTO sys_dict_detail (detail_id,dict_type,detail_name,detail_code,detail_sort,
detail_type,detail_state,detail_content,detail_remark,create_time,create_id) 
VALUES (24, 'moneyType', '预算', '3', '3', NULL, NULL, NULL, NULL, '2014-03-13 11:18:20', 1);
INSERT INTO sys_dict_detail (detail_id,dict_type,detail_name,detail_code,detail_sort,
detail_type,detail_state,detail_content,detail_remark,create_time,create_id) 
VALUES (22, 'moneyType', '收入', '2', '2', NULL, NULL, NULL, NULL, '2014-03-13 11:17:40', 1);
INSERT INTO sys_dict_detail (detail_id,dict_type,detail_name,detail_code,detail_sort,
detail_type,detail_state,detail_content,detail_remark,create_time,create_id) 
VALUES (23, 'moneyType', '支出', '1', '1', NULL, NULL, NULL, NULL, '2014-03-13 11:17:50', 1);
  
INSERT INTO tb_project (id,name,type,remark,create_time,create_id)
VALUES (1, '结婚', '1', '', '2014-03-30 16:47:21', 1);
INSERT INTO tb_project (id,name,type,remark,create_time,create_id)
VALUES (2, '装修', '1', '装修', '2014-03-30 16:47:42', 1);  

INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id)
VALUES (1, '结婚预算', 100000.00, '24', NULL, '2014-03-01', '2014-03-01 17:42:41', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id)
VALUES (2, '装修预算', 150000.00, '24', NULL, '2014-03-01', '2014-03-01 17:42:41', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (1, '礼花、鞭炮', 2370.00, '23', NULL, '2014-04-26', '2014-04-27 17:42:41', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '装修费', 33000.00, '23', NULL, '2014-03-10', '2014-04-03 22:00:39', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (1, '结婚照', 4200.00, '23', NULL, '2013-04-01', '2014-04-03 22:04:50', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '木地板', 3000.00, '23', NULL, '2014-03-22', '2014-04-03 22:16:28', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (1, '新娘服装', 2550.00, '23', NULL, '2014-03-30', '2014-04-03 21:57:11', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (1, '新郎服装', 2040.00, '23', NULL, '2014-04-05', '2014-04-05 20:20:25', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (1, '结婚相关物品', 1550.00, '23', '', '2014-04-21', '2014-04-21 15:00:53', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '燃气灶', 3200.00, '23', NULL, '2014-04-22', '2014-04-23 19:18:05', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '木门', 5000.00, '23', NULL, '2014-04-22', '2014-04-23 19:18:33', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '断桥铝窗户', 5000.00, '23', NULL, '2014-04-25', '2014-04-27 17:40:25', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (1, '新郎母亲服装', 1558.00, '23', '', '2013-04-04', '2014-04-27 17:52:02', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (1, '饮料、烟、酒', 6710.00, '23', NULL, '2014-04-30', '2014-04-29 21:32:49', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (1, '新娘婚庆', 13000.00, '23', '余款', '2014-05-01', '2014-05-02 13:45:51', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id)  
VALUES (1, '新娘酒席', 49700.00, '23', NULL, '2014-05-01', '2014-05-02 13:46:19', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (1, '份子钱_新郎', 43000.00, '22', NULL, '2014-05-01', '2014-05-02 13:48:16', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id)  
VALUES (1, '份子钱_新娘', 38400.00, '22', NULL, '2014-05-01', '2014-05-02 13:49:12', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '防盗门', 1400.00, '23', NULL, '2014-05-15', '2014-05-13 18:12:21', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id)  
VALUES (2, '护栏', 4000.00, '23', NULL, '2014-04-26', '2014-04-27 17:41:26', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '水电', 7000.00, '23', NULL, '2014-05-16', '2014-05-18 22:02:37', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '马桶', 1100.00, '23', NULL, '2014-05-21', '2014-05-21 18:04:05', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '家具', 30000.00, '23', '', '2014-05-24', '2014-05-25 12:34:42', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '瓷砖', 6107.00, '23', '', '2014-05-13', '2014-05-13 18:11:59', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '橱柜', 7300.00, '23', NULL, '2014-05-26', '2014-05-27 10:51:34', 1);
INSERT INTO tb_money (project_id,name,amount,type,remark,pay_time,create_time,create_id) 
VALUES (2, '大理石', 1000.00, '23', NULL, '2014-05-28', '2014-05-28 20:16:07', 1);


