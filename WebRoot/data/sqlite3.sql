/**
 * 删除表
 */
drop table if exists sys_dict_detail;
drop table if exists sys_dict;
drop table if exists sys_user;

/**
 * 数据字典主表
 */
CREATE TABLE sys_dict
(
  dict_id integer PRIMARY KEY autoincrement, 
  dict_name   VARCHAR(256) not null,
  dict_type  VARCHAR(64) not null,
  dict_remark VARCHAR(256)
) ;

/**
 * 数据字典明细表
 */
create table sys_dict_detail
(
  detail_id      integer PRIMARY KEY autoincrement,
  dict_type      varchar(64) NOT NULL,
  detail_name    varchar(256),
  detail_code    varchar(32),
  detail_sort    varchar(32),
  detail_type    varchar(32),
  detail_state   varchar(32),
  detail_content varchar(256),
  detail_remark  varchar(256),
  create_time    varchar(32),
  create_id      integer
) ;
  
/**
 * 用户表
 */
create table sys_user
(
  userid      integer PRIMARY KEY autoincrement,
  username    varchar(32) not null,
  password    varchar(32) not null,
  realname    varchar(32),
  state       varchar(32),
  endtime     varchar(32),
  tel         varchar(32),
  address     varchar(32),
  create_time varchar(32),
  create_id   integer default 0
  
) ;

/**
 * 项目表
 */  
-- Table: tb_project

-- DROP TABLE tb_project;
drop table if exists tb_project;

CREATE TABLE tb_project
(
  id integer PRIMARY KEY autoincrement, -- 主键
  name varchar(256) NOT NULL, -- 字典名称
  type varchar(64) NOT NULL, -- 字典类型
  remark varchar(256), -- 字典说明
  create_time    varchar(32),
  create_id      integer
) ;

/**
 * 金钱表
 */ 
-- Table: tb_money

-- DROP TABLE tb_money;
drop table if exists tb_money;

CREATE TABLE tb_money
(
  id  integer PRIMARY KEY autoincrement,  -- 主键
  project_id  integer NOT NULL, -- 项目ID
  name varchar(256) NOT NULL, -- 简单说明
  amount numeric(8,2), -- 金额
  type varchar(64) NOT NULL, -- 类型
  remark varchar(256), -- 说明
  pay_time varchar(32), -- 费用产生时间
  create_time varchar(32),
  create_id      integer
) ;

/**
 * 联系人表
 */ 
-- Table: tb_contact

-- DROP TABLE tb_contact;
drop table if exists tb_contact;

CREATE TABLE tb_contact
(
  id  integer PRIMARY KEY autoincrement,  -- 主键
  name varchar(256) NOT NULL, -- 姓名
  phone varchar(32), -- 手机号
  email varchar(32), -- Email
  addr varchar(256), -- 地址
  birthday varchar(32), -- 生日
  remark varchar(256), -- 说明
  create_time varchar(32),
  create_id      integer
) ;