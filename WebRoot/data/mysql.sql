 /**
 * 删除表
 */
drop table if exists sys_dict_detail;
drop table if exists sys_dict;
drop table if exists sys_user;
drop table if exists tb_column;

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
  username    varchar(32) not null,
  password    varchar(32) not null,
  realname    varchar(32),
  state       varchar(32),
  endtime     varchar(32),
  tel         varchar(32),
  address     varchar(32),
  create_id   int(11) default 0,
  create_time varchar(32),
  PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 栏目表
 */
CREATE TABLE tb_column
(
  id int(11) NOT NULL AUTO_INCREMENT,
  parent_id integer DEFAULT 0,
  title text,
  content text, 
  type integer,
  level integer, 
  sort integer,
  image_url varchar(256),
  publish_time varchar(64),
  publish_user varchar(64),
  update_time varchar(64),
  start_time varchar(64),
  end_time varchar(64),
  create_time  varchar(64),
  create_id  int(11),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 联系人表
 */ 
-- Table: tb_contact

-- DROP TABLE tb_contact;
drop table if exists tb_contact;

CREATE TABLE tb_contact
(
  id  int(11) NOT NULL AUTO_INCREMENT,  -- 主键
  name varchar(256) NOT NULL, -- 姓名
  phone varchar(32), -- 手机号
  email varchar(32), -- Email
  addr varchar(256), -- 地址
  birthday varchar(32), -- 生日
  remark varchar(256), -- 说明
  create_time varchar(32),
  create_id      integer,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;