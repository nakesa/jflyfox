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
drop table if exists tb_column;

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
 * 栏目表
 */
CREATE TABLE tb_column
(
  id serial NOT NULL, --主键
  parent_id integer DEFAULT 0, --父ID
  title text, --题目
  content text, --内容
  type integer, --类型
  level integer, --级别
  sort integer, --顺序
  image_url character varying(256), --图片URL
  publish_time character varying(64), --发布时间（展示）
  publish_user character varying(64), --发布人（展示）
  update_time character varying(64), --更新时间
  start_time character varying(64),	 --开始时间
  end_time character varying(64),	 --结束时间
  create_time  character varying(64),	 --创建时间
  create_id  integer	 --创建人
);
-- add comments to the columns 
comment on column tb_column.id
  is '主键';
comment on column tb_column.parent_id
  is '父ID';
comment on column tb_column.title
  is '题目';
comment on column tb_column.content
  is '内容';
comment on column tb_column.type
  is '类型';
comment on column tb_column.level
  is '级别';
comment on column tb_column.sort
  is '顺序';
comment on column tb_column.image_url
  is '图片URL';
comment on column tb_column.publish_time
  is '发布时间';
comment on column tb_column.publish_user
  is '发布人';
comment on column tb_column.update_time
  is '更新时间';
comment on column tb_column.start_time
  is '开始时间';
comment on column tb_column.end_time
  is '结束时间';
comment on column tb_column.create_time
  is '创建时间';
comment on column tb_column.create_id
  is '创建人';
-- create/recreate primary, unique and foreign key constraints 
alter table tb_column
  add constraint pk_tb_column_id primary key (id);
  
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
  OWNER TO website;
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
  OWNER TO website;
COMMENT ON TABLE tb_money
  IS '金钱表';
COMMENT ON COLUMN tb_money.id IS '主键';
COMMENT ON COLUMN tb_money.project_id IS '项目ID';
COMMENT ON COLUMN tb_money.name IS '简单说明';
COMMENT ON COLUMN tb_money.amount IS '金额';
COMMENT ON COLUMN tb_money.pay_time IS '费用产生时间';
COMMENT ON COLUMN tb_money.type IS '类型';
COMMENT ON COLUMN tb_money.remark IS '说明';





