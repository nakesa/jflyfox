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
  dict_id int(11) NOT NULL AUTO_INCREMENT, 
  dict_name   VARCHAR(256) not null,
  dict_type  VARCHAR(64) not null,
  dict_remark VARCHAR(256),
  PRIMARY KEY (dict_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
alter table sys_dict add unique UK_SYS_DICT_TYPE (dict_type);

/**
 * 数据字典明细表
 */
create table sys_dict_detail
(
  detail_id      int(11) NOT NULL AUTO_INCREMENT,
  dict_type      varchar(64) NOT NULL,
  detail_name    varchar(256),
  detail_code    varchar(32),
  detail_sort    varchar(32),
  detail_type    varchar(32),
  detail_state   varchar(32),
  detail_content varchar(256),
  detail_remark  varchar(256),
  create_time    varchar(32),
  create_id      int(11),
  PRIMARY KEY (detail_id)
  -- ,foreign key (dict_type) references sys_dict (dict_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
/**
 * 用户表
 */
create table sys_user
(
  userid      int(11) NOT NULL AUTO_INCREMENT,
  username    varchar(32) not null COMMENT '用户名',
  password    varchar(32) not null COMMENT '密码',
  realname    varchar(32) COMMENT '真实姓名',
  usertype    varchar(32) default '2' comment '类型：1 管理员 2 普通用户 3 前台用户 4 第三方用户',
  state       varchar(32) default '1' comment '状态',
  thirdid	  varchar(200) COMMENT '第三方ID',
  endtime     varchar(32) COMMENT '结束时间',
  email       varchar(64) COMMENT 'email',
  tel         varchar(32) COMMENT '手机号',
  address     varchar(32) COMMENT '地址',
  title_url   varchar(200) COMMENT '头像地址',
  remark      varchar(1000) COMMENT '说明',
  create_id   int(11) default 0,
  create_time varchar(32),
  PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 目录表
 */
-- Table: tb_folder

-- DROP TABLE tb_folder;
drop table if exists tb_folder;

CREATE TABLE tb_folder (
  id int(11) not null auto_increment comment '目录id',
  name varchar(100) not null default '' comment '中文名',
  path varchar(200) not null default '' comment '路径',
  content text comment '描述',
  sort  int(11) default '1' comment '排序',
  status varchar(20) DEFAULT '1' comment '状态：2 隐藏 1 显示',
  type  int(11) DEFAULT '1' comment '类型 1 普通目录 2 a标签 3 a标签_blank 4 直接加载url信息',
  jump_url varchar(200) DEFAULT NULL comment '跳转地址',
  update_time varchar(64) DEFAULT NULL COMMENT '更新时间',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录';

/**
 * 文章表
 */
-- Table: tb_article

-- DROP TABLE tb_article;
drop table if exists tb_article;
CREATE TABLE tb_article
(
  id int(11) not null auto_increment comment 'id',
  folder_id integer default 1 comment '目录id',
  title varchar(200) default '' comment '文章名称',
  content text comment '文件内容', 
  count_view int(11) default '0' comment '浏览数',
  count_comment int(11) default '0' comment '评论数',
  type  int(11) default '1' comment '类型：1 正常 2 预览展示概述 3 程序调用处理',
  status varchar(20) default '1' comment '状态：2 隐藏 1 显示',
  is_comment varchar(20) default '1' comment '是否评论：2 否 1 是',
  sort  int(11) default '1' comment '排序',
  image_url varchar(256) default null comment '图片路径',
  publish_time varchar(64) DEFAULT NULL COMMENT '发布时间',
  publish_user varchar(64) DEFAULT '1' COMMENT '发布者',
  start_time varchar(64) DEFAULT NULL COMMENT '开始时间',
  end_time varchar(64) DEFAULT NULL COMMENT '结束时间',
  update_time varchar(64) DEFAULT NULL COMMENT '更新时间',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';
-- 主键从200开始
alter table tb_article AUTO_INCREMENT=200;

/**
 * 联系人表
 */ 
-- Table: tb_contact

-- DROP TABLE tb_contact;
drop table if exists tb_contact;

CREATE TABLE tb_contact
(
  id  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  name varchar(256) NOT NULL COMMENT '姓名',
  phone varchar(32) COMMENT '手机号',
  email varchar(32) COMMENT 'Email',
  addr varchar(256) COMMENT '地址', 
  birthday varchar(32) COMMENT '生日',
  remark varchar(256) COMMENT '说明',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联系人';


/**
 * 评论表
 */ 
-- Table: tb_comment

-- DROP TABLE tb_comment;
drop table if exists tb_comment;

CREATE TABLE tb_comment (
  id  int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  fatherId int(11) DEFAULT NULL COMMENT '父评论ID',
  article_id int(11) DEFAULT NULL COMMENT '文章ID',
  content text NOT NULL COMMENT '内容',
  status varchar(32) DEFAULT NULL COMMENT '状态',
  create_time  varchar(64) DEFAULT NULL COMMENT '创建时间',
  create_id  int(11) DEFAULT 0 COMMENT '创建者 评论者',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论';