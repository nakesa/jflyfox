/**
 * flyfox_sequence
 */
drop sequence if exists seq_flyfox;
create sequence seq_flyfox increment 1 minvalue 1 maxvalue 9223372036854775807  cache 1;
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
  dict_id serial not null, 
  dict_name   VARCHAR(256) not null,
  dict_type  VARCHAR(64) not null,
  dict_remark VARCHAR(256)
) ;

-- add comments to the table 
comment on table sys_dict
  is '数据字典表';
-- add comments to the columns 
comment on column sys_dict.dict_id
  is '主键';
comment on column sys_dict.dict_name
  is '字典名称';
comment on column sys_dict.dict_type
  is '字典类型';
comment on column sys_dict.dict_remark
  is '字典说明';
  
alter table sys_dict
  add constraint pk_dict_id primary key (dict_id);
  
alter table sys_dict add constraint UK_SYS_DICT_TYPE unique (DICT_TYPE);

/**
 * 数据字典明细表
 */
create table sys_dict_detail
(
  detail_id      serial not null,
  dict_type      varchar(64),
  detail_name    varchar(256),
  detail_code    varchar(32),
  detail_sort    varchar(32),
  detail_type    varchar(32),
  detail_state   varchar(32),
  detail_content varchar(256),
  detail_remark  varchar(256),
  create_time    varchar(32),
  create_id      integer
);
-- add comments to the table 
comment on table sys_dict_detail
  is '数据字典明细表';
-- add comments to the columns 
comment on column sys_dict_detail.detail_id
  is '主键';
comment on column sys_dict_detail.dict_type
  is '字典类型';
comment on column sys_dict_detail.detail_name
  is '名称';
comment on column sys_dict_detail.detail_code
  is '编号';
comment on column sys_dict_detail.detail_sort
  is '序号';
comment on column sys_dict_detail.detail_type
  is '类型';
comment on column sys_dict_detail.detail_state
  is '状态';
comment on column sys_dict_detail.detail_content
  is '说明';
comment on column sys_dict_detail.detail_remark
  is '备注';
comment on column sys_dict_detail.create_time
  is '创建时间';
comment on column sys_dict_detail.create_id
  is '创建人';
-- create/recreate primary, unique and foreign key constraints 
alter table sys_dict_detail
  add constraint pk_sys_dict_detail_id primary key (detail_id);
alter table sys_dict_detail
  add constraint fk_sys_dict_type foreign key (dict_type)
  references sys_dict (dict_type);
  
/**
 * 用户表
 */
create table sys_user
(
  userid      serial not null,
  username    varchar(32) not null,
  password    varchar(32) not null,
  realname    varchar(32),
  state       varchar(32),
  endtime     varchar(32),
  tel         varchar(32),
  address     varchar(32),
  create_id   integer default 0,
  create_time varchar(32)
);
-- add comments to the columns 
comment on column sys_user.userid
  is '主键';
comment on column sys_user.username
  is '用户名称';
comment on column sys_user.password
  is '用户密码';
comment on column sys_user.realname
  is '真实姓名';
comment on column sys_user.state
  is '状态';
comment on column sys_user.endtime
  is '失效日期';
comment on column sys_user.tel
  is '联系电话';
comment on column sys_user.address
  is '联系地址';
comment on column sys_user.create_id
  is '创建者';
comment on column sys_user.create_time
  is '创建时间';
-- create/recreate primary, unique and foreign key constraints 
alter table sys_user
  add constraint pk_sys_user_userid primary key (userid);

/**
 * 项目表
 */  
-- Table: tb_project

-- DROP TABLE tb_project;

CREATE TABLE tb_project
(
  id serial NOT NULL, -- 主键
  name character varying(256) NOT NULL, -- 字典名称
  type character varying(64) NOT NULL, -- 字典类型
  remark character varying(256), -- 字典说明
  create_time    varchar(32),
  create_id      integer,
  CONSTRAINT pk_project_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_project
  OWNER TO jmoney;
COMMENT ON TABLE tb_project
  IS '项目表';
COMMENT ON COLUMN tb_project.id IS '主键';
COMMENT ON COLUMN tb_project.name IS '项目名称';
COMMENT ON COLUMN tb_project.type IS '项目类型';
COMMENT ON COLUMN tb_project.remark IS '项目说明';


/**
 * 金钱表
 */ 
-- Table: tb_money

-- DROP TABLE tb_money;

CREATE TABLE tb_money
(
  id serial NOT NULL, -- 主键
  project_id int, --项目ID
  name character varying(256) NOT NULL, -- 简单说明
  amount numeric(8,2), -- 金额
  pay_time character varying(32), --费用产生时间
  type character varying(64) NOT NULL, -- 类型
  remark character varying(256), -- 说明
  create_time    varchar(32),
  create_id      integer,
  CONSTRAINT pk_money_id PRIMARY KEY (id),
  CONSTRAINT fk_tb_money_project_id FOREIGN KEY (project_id)
      REFERENCES tb_project (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_money
  OWNER TO jmoney;
COMMENT ON TABLE tb_money IS '金钱表';
COMMENT ON COLUMN tb_money.id IS '主键';
COMMENT ON COLUMN tb_money.project_id IS '项目ID';
COMMENT ON COLUMN tb_money.name IS '简单说明';
COMMENT ON COLUMN tb_money.amount IS '金额';
COMMENT ON COLUMN tb_money.pay_time IS '费用产生时间';
COMMENT ON COLUMN tb_money.type IS '类型';
COMMENT ON COLUMN tb_money.remark IS '说明';


/**
 * 联系人表
 */ 
-- Table: tb_contact

-- DROP TABLE tb_contact;
drop table if exists tb_contact;

CREATE TABLE tb_contact
(
  id  serial NOT NULL ,  -- 主键
  name character varying(256) NOT NULL, -- 姓名
  phone character varying(32), -- 手机号
  email character varying(32), -- Email
  addr character varying(256), -- 地址
  birthday character varying(32), -- 生日
  remark character varying(256), -- 说明
  create_time character varying(32),
  create_id      integer
) 
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_contact
  OWNER TO jmoney;
COMMENT ON TABLE tb_contact IS '联系人表';
COMMENT ON COLUMN tb_contact.id IS '主键';
COMMENT ON COLUMN tb_contact.name IS '姓名';
COMMENT ON COLUMN tb_contact.phone IS '手机号';
COMMENT ON COLUMN tb_contact.email IS 'Email';
COMMENT ON COLUMN tb_contact.addr IS '主键';
COMMENT ON COLUMN tb_contact.birthday IS '生日';
COMMENT ON COLUMN tb_contact.remark IS '说明';



