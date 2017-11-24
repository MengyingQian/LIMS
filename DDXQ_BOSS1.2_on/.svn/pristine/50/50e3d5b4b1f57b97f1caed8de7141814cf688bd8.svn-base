
-- 创建配置信息表
DROP TABLE IF EXISTS ddxq_configs;
CREATE TABLE ddxq_configs(
	key_label varchar(50) COMMENT '配置的键',
	value_label varchar(255) COMMENT '配置的值',
	created timestamp default now() comment '创建时间', 
	updated timestamp default now() comment '更新时间', 
	notes text,
    PRIMARY key(key_label)                                                                         
)engine = innodb
default character set = utf8;
INSERT INTO ddxq_configs(key_label, value_label) VALUES('cardno-current-num', '000000000001');

-- 创建全国区域代码表
DROP TABLE IF EXISTS ddxq_dict_areacodes;
CREATE TABLE IF NOT EXISTS ddxq_dict_areacodes (
  id int NOT NULL,
  code varchar(20) NOT NULL,
  name varchar(50) NOT NULL,
  levelId int ,
  parentId int ,
  pinyin varchar(50) DEFAULT NULL,
  primary key(id)
)engine = innodb
default character set = utf8;
CREATE UNIQUE INDEX idx_ddxq_dict_areacodes_code ON ddxq_dict_areacodes(code);

-- 手机归属地
DROP TABLE IF EXISTS ddxq_dict_mobiles;
CREATE TABLE IF NOT EXISTS ddxq_dict_mobiles (
  id varchar(32) NOT NULL,
  mobileNumber varchar(20) DEFAULT NULL,
  mobileArea varchar(50) DEFAULT NULL,
  mobileType varchar(50) DEFAULT NULL,
  areaCode varchar(10) DEFAULT NULL,
  postCode varchar(50) DEFAULT NULL,
  provinceCode varchar(10) DEFAULT NULL,
  cityCode varchar(10) DEFAULT NULL,
  category int DEFAULT NULL comment '分类',
  provinceName varchar(24) DEFAULT NULL,
  cityName varchar(48) DEFAULT NULL,
  primary key(id)
);
CREATE UNIQUE INDEX idx_ddxq_dict_mobiles_mobileNumber ON ddxq_dict_mobiles(mobileNumber);

--
-- 创建IP地址信息表
--
DROP TABLE IF EXISTS ddxq_dict_ipaddresses;
CREATE TABLE ddxq_dict_ipaddresses (
  id  bigint not null auto_increment,  
  ipa bigint not null,
  ipb bigint not null,
  address varchar(255),
  provider varchar(255),
  notes text,
  primary key(id)
);
CREATE INDEX idx_ddxq_dict_ipaddresses_ipa ON ddxq_dict_ipaddresses(ipa);
CREATE INDEX idx_ddxq_dict_ipaddresses_ipb ON ddxq_dict_ipaddresses(ipb);

--创建小区信息表
--
DROP SEQUENCE IF EXISTS ddxq_districts_id_seq CASCADE;
CREATE SEQUENCE ddxq_districts_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999999999999
  START 1 
  CACHE 1; --缓存为1，表示一次生成一个，在内存中不缓存
DROP TABLE IF EXISTS ddxq_districts;
CREATE TABLE ddxq_districts(
	id bigint default nextval('ddxq_districts_id_seq') primary key, --自增ID
	areacode bigint,                       	                        --区域ID  12位编号数字
	level integer,              	                	        --区划级别1：省(市)  2：市(地)  3：区(县)  4：街道(乡镇)  5：小区(村)
	name varchar(50),                          	                --小区名称（区划名称）
	alias varchar(50),                                              --小区别名（区划简称）
	qualitiy varchar(10),           	                        --小区性质（预留）
	category varchar(10),           	                        --小区类型 （城乡分类码） 111  210 220等。
	description text,                           	                --小区简介 
	address varchar(255),                       	                --小区地址
	postcode varchar(50),                                           --小区邮编
	phone  varchar(50),                          	                --小区大管家电话
	site varchar(50),                           	                --小区网址
	busroute varchar(255),                                          --乘车路线
	longitude varchar(255),                                         --经纬度坐标:经度
	latitude varchar(255),                                          --经纬度坐标:纬度
	sceneId varchar(255) unique,                          	        --【小区场景ID】 1-100000，不能重复；临时二维码，取值为1开头的10位整数
	ticket varchar(255),                                            -- sceneid对应的ticket
	qrcodeURL varchar(255),                                         --二维码存储的URL
	created timestamp default now(),            	                --创建时间
	updated timestamp default now(),            	                --更新时间
	tags text,                                                      --标签
	notes text                                   	                --备注信息
);                                                                                
CREATE INDEX idx_ddxq_districts_areacode ON ddxq_districts(areacode);
CREATE INDEX idx_ddxq_districts_level ON ddxq_districts(level);
CREATE INDEX idx_ddxq_districts_areacode ON ddxq_districts(areacode);
CREATE INDEX idx_ddxq_districts_sceneId ON ddxq_districts(sceneId);
--
-- 二维码管理表
--
DROP TABLE IF EXISTS ddxq_qrcode;
CREATE TABLE ddxq_qrcode(
	id bigint not null auto_increment comment '自增ID',                     
	districtId bigint unique comment '(小区场景ID) 1-100000，不能重复',
	ticket varchar(255) comment 'sceneid对应的ticket',
	qrCodeURL varchar(255) comment '二维码存储的url',
	created timestamp default now() comment '创建时间',
	updated timestamp default now() comment '更新时间', 
	notes text comment '备注信息',
    primary key (id)
)
engine = innodb
default character set = utf8;

--
-- 创建系统用户表
--
DROP TABLE IF EXISTS ddxq_sysman;
CREATE TABLE ddxq_sysman(
  id int auto_increment,
  account varchar(50) UNIQUE comment '账号', 
  pwd varchar(50) comment '密码(使用MD5加密)',
  actorid integer comment '角色ID（1：平台管理员 2：平台运营员、3：平台财务管理员: 4：平台客服  5：小区管理员）',
  privmod varchar(100) comment '模块权限(预留)',
  privcate varchar(100) comment '分类权限(预留)', 
  level int comment '级别(预留)',    
  name varchar(50) comment '系统用户姓名',
  tel varchar(30),
  mobile varchar(30),
  ims varchar(30),
  created TIMESTAMP default now(),
  updated TIMESTAMP default now(),
  expired TIMESTAMP default now(),
  lastlogindt TIMESTAMP default now(),
  lastloginip varchar(20),
  status char(1) DEFAULT '1',
  memo text,
  PRIMARY KEY(id)
)engine = innodb
default character set = utf8;
CREATE INDEX idx_ddxq_sysman_account ON ddxq_sysman(account);
CREATE INDEX idx_ddxq_sysman_level ON ddxq_sysman(level);
CREATE INDEX idx_ddxq_sysman_privmod ON ddxq_sysman(privmod);
CREATE INDEX idx_ddxq_sysman_privcate ON ddxq_sysman(privcate);
CREATE INDEX idx_ddxq_sysman_created ON ddxq_sysman(created);
CREATE INDEX idx_ddxq_sysman_updated ON ddxq_sysman(updated);
CREATE INDEX idx_ddxq_sysman_status ON ddxq_sysman(status);

INSERT INTO ddxq_sysman( account, pwd, actorid) VALUES( 'root', md5('123456'), 1);


--
--小区职员信息表(员工表，将合作商家都纳入进去，由小区管理员管理本小区职员)
--
DROP SEQUENCE IF EXISTS ddxq_employees_id_seq CASCADE;
CREATE SEQUENCE ddxq_employees_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 99999999999999999
  START 1 
  CACHE 1; --缓存为1，表示一次生成一个，在内存中不缓存
CREATE TABLE ddxq_employees(
	id bigint default nextval('ddxq_employees_id_seq') PRIMARY KEY,     --自增ID
	employeeId varchar(50)  unique,                                     --职员id（一般应为 手机号形式）
	passWD varchar(255),                                                --登录密码（MD5）
																		--1：物流  2：物业  3：商家  4：服务  5：居委会  6：业委会
	employeeActor integer,                                              --角色（5：小区 6：骑手 7：物业  8：商家  9：服务  10：居委会  11：业委会 110：片警 120：医生  150：中介  ）

	districtId bigint,                                                  --职员所属小区的ID编号(参见表ddxq_districts)

	employeeName varchar(50) NOT NULL DEFAULT ' ',                      --职员姓名
	employeeNickName varchar(50),                                       --昵称
	employeeIdCard varchar(50),                                         --证件号码
	employeeIdCardType char(1)  DEFAULT '1',                            --证件类型:  1:身份证
	employeeSex char(1)  DEFAULT '0',                                   --职员性别:  0: 保密； 1：男； 2：女
	employeeBirth date,                                                 --出生日期：1970-01-01，计算年龄
	employeeNation varchar(50),                                         --民族
	employeeMobile varchar(20),					    --职员手机号
	employeeTele varchar(50),                                           --电话号码
	employeeEmail varchar(50) unique,                                   --邮箱
	employeeCountry varchar(50) DEFAULT '中国',                         --国籍
	employeeProvince varchar(50),                                       --省份
	employeeCity varchar(50),                                           --城市
	employeeAddr varchar(100),                                          --联系地址
	employeePostcode  varchar(10),                                      --邮政编码

	employeeDepart  varchar(50),                                        --职员部门（预留）
	employeePosition  varchar(50),                                      --职员岗位（:sdy：速递员；wyy：物业员； bld：便利店员；pj：片警；）
	employeeSalary   bigint,                                            --职员工资待遇,单位是分
	employeeLevel    int,                                               --职员等级
	employeePoints   int,                                               --职员积分

	created timestamp default now(),                                    --创建时间
	updated timestamp default now(),                                    --上次更新时间
	status char(1) DEFAULT '1',                                         --状态标识： 0：冻结； 1：正常
	notes text                                                          --备注信息
);
CREATE INDEX idx_ddxq_employees_employeeId ON ddxq_employees(employeeId);
CREATE INDEX idx_ddxq_employees_employeeActor ON ddxq_employees(employeeActor);
CREATE INDEX idx_ddxq_employees_districtId ON ddxq_employees(districtId);

--
-- 居民信息表(粉丝表)
--
DROP TABLE IF EXISTS ddxq_residents;
CREATE TABLE ddxq_residents(
	id bigint auto_increment,                                                           -- 自增ID
	openId varchar(50)  unique,                                                         -- 居民微信号的openId，可空
	wxInfo text,                                                                        -- 通过openId拉取的微信用户信息，JSON格式(参加微信开放文档)，可空
	userId varchar(50)  unique,                                                         -- 居民id（字母+数字、手机号形式）用于非微信形式登陆，可空
	passWD varchar(255),                                                                -- 登录密码
	districtId bigint,                                                                  -- 居民所属小区的ID编号(默认小区ID, sceneId)
	ticket varchar(50),                                                                 -- districtId对应的ticket
	residentName varchar(50) NOT NULL DEFAULT ' ',                                      -- 居民姓名
	residentNickName varchar(50),                                                       -- 昵称
	residentIdCard varchar(50),                                                         -- 证件号码
	residentIdCardType char(1)  DEFAULT '1',                                            -- 证件类型:  1:身份证
	residentSex char(1)  DEFAULT '0',                                                   -- 居民性别:  0: 保密； 1：男； 2：女
	residentBirth date,                                                                 -- 出生日期：1970-01-01，计算年龄
	residentNation varchar(50),                                                         -- 民族
	residentMobile varchar(20),							                                -- 居民手机号
	residentTele varchar(50),                                                           -- 电话号码
	residentEmail varchar(50) unique,                                                   -- 邮箱
	residentCountry varchar(50) DEFAULT '中国',                                          -- 国籍
	residentProvince varchar(50),                                                       -- 省份
	residentCity varchar(50),                                                           -- 城市
	residentAddr varchar(100),                                                          -- 联系地址
	residentPostcode	varchar(10),                                                    -- 邮政编码
	residentType char(1)  DEFAULT '0',                                                  -- 居民类别：0：普通关注； 1：微信粉丝； 2：VIP会员
	residentLevel    int,                                                               -- 用户等级
	residentPoints   int,                                                               -- 用户积分
	residentBalance  bigint,                                                            -- 用户余额,单位是分
	focusedChannel varchar(100) DEFAULT '0',                                            -- 关注渠道，指从哪个sceneId关注而来，默认0：直接关注的。
	focusedDays int NOT NULL DEFAULT 0,                                                 -- 关注天数，缺省是0
	photoUrl varchar(255),                                                              -- 头像url
	created timestamp default now(),                                                    -- 创建时间
	updated timestamp default now(),                                                    -- 上次更新时间
	status char(1) DEFAULT '1',                                                         -- 状态标识： 0：冻结； 1：正常
	notes text,                                                                         -- 备注信息
	PRIMARY KEY(id)
)engine = innodb
default character set = utf8;
CREATE INDEX idx_ddxq_residents_districtId ON ddxq_residents(districtId);

--
-- 创建小区寻觅启事表
--
DROP TABLE IF EXISTS ddxq_seekings;
CREATE TABLE ddxq_seekings(
	id bigint auto_increment,                                                           -- 自增ID
	districtId varchar(50),                                                             -- 小区ID
	promulgator varchar(50),                                                            -- 发布者（可以为openId）
	title varchar(50),                                                                  -- 主标题
	subtitle varchar(50),                                                               -- 副标题
	content text,                                                                       -- 文本内容
	image_url varchar(255),                                                             -- 图片url
	category  char(1),                                                                  -- 启事类型  1:寻人  2：寻物   3：寻宠物
	created timestamp default now(),                                                    -- 创建时间
	updated timestamp default now(),                                                    -- 更新时间
	status char(1) DEFAULT '1',                                                         -- 状态标识：0：冻结（隐藏）； 1：正常
	notes text,                                                                         -- 备注信息
	primary key(id)
)engine = innodb
default character set = utf8;
CREATE INDEX idx_ddxq_seekings_districtId ON ddxq_seekings(districtId);
CREATE INDEX idx_ddxq_seekings_updated ON ddxq_seekings(updated);

--
-- 创建小区最新公告表
--
DROP TABLE IF EXISTS ddxq_notices;
CREATE TABLE ddxq_notices(
	id bigint auto_increment,                                                           -- 自增ID
	districtId varchar(50),                                                             -- 小区ID
	promulgator varchar(50),                                                            -- 发布者（可以为openId）
	title varchar(50),                                                                  -- 主标题
	subtitle varchar(50),                                                               -- 副标题
	content text,                                                                       -- 文本内容
	image_url varchar(255),                                                             -- 图片url
	category  char(1) default '0',                                                      -- 公告类型 默认为0
	created timestamp default now(),                                                    -- 创建时间
	updated timestamp default now(),                                                    -- 更新时间
	status char(1) DEFAULT '1',                                                         -- 状态标识：0：冻结（隐藏）； 1：正常
	notes text,                                                                         -- 备注信息
	primary key(id)
)engine = innodb
default character set = utf8;
CREATE INDEX idx_ddxq_notices_districtId ON ddxq_notices(districtId);
CREATE INDEX idx_ddxq_notices_updated ON ddxq_notices(updated);


--
-- 创建小区一呼百应表（小区电话黄页表）
--
DROP TABLE IF EXISTS ddxq_services;
CREATE TABLE ddxq_services(
	id bigint auto_increment,                                                           -- 自增ID
	promulgator varchar(50),                                                            -- 发布者（可以为openId）
	districtId bigint,                                                                  -- 小区ID

	cateId  varchar(5) default '0',                                                     -- 类型（0:公共 1:快递 2:餐饮 3:家政 4:出行 5:教育 6:娱乐 7:xx 8:xx 9:健康 10:xx 11:xx 12:xx 13:xx
	caption varchar(50),                                                                -- 标题
	phone varchar(50),                                                                  -- 电话号码
	comments varchar(255),                                                              -- 备注说明
	tags varchar(255),                                                                  -- 标签
	ordernum  int default 0,                                                            -- 排序号，默认为0，号小排在前面

	fee  bigint default 0,                                                              -- 收费标准,单位是 分/天，默认为0，免费
	timefrom timestamp default now(),                                                   -- 计费起始时间
	timeover timestamp default now(),                                                   -- 计费终止时间

	created timestamp default now(),                                                    -- 创建时间
	updated timestamp default now(),                                                    -- 上次更新时间
	status char(1) DEFAULT '1',                                                         -- 状态标识： 0：冻结； 1：正常
	primary key(id)
)engine = innodb
default character set = utf8;
CREATE INDEX idx_ddxq_services_districtId ON ddxq_services(districtId);
CREATE INDEX idx_ddxq_services_tags ON ddxq_services(tags);
CREATE INDEX idx_ddxq_services_updated ON ddxq_services(updated);

--
-- 创建小区超市商品表
--

DROP TABLE IF EXISTS ddxq_commodities;
CREATE TABLE ddxq_commodities(
	id bigint auto_increment,                                                           -- 自增ID
	seller varchar(50),                                                                 -- 卖方，对应到employees表中的bld岗位
	districtId bigint,                                                                  -- 小区ID

	category  varchar(10),                                                              -- 类型
	caption varchar(50),                                                                -- 商品描述标题
	price  bigint default 0,                                                            -- 商品报价, 单位为分
	unit  varchar(50),                                                                  -- 报价对应的商品单元，例如：个， 5瓶， 3箱等
	phone varchar(50),                                                                  -- 卖方电话号码
	comments varchar(255),                                                                      -- 备注说明
	tags varchar(255),                                                                          -- 标签
	ordernum  int default 0,                                                            -- 排序号，默认为0，号小排在前面

	created timestamp default now(),                                                    -- 创建时间
	updated timestamp default now(),                                                    -- 上次更新时间
	status char(1) DEFAULT '1',                                                          -- 状态标识： 0：冻结； 1：正常
  PRIMARY key(id)
)engine = innodb
default character set = utf8;
CREATE INDEX idx_ddxq_commodities_districtId ON ddxq_commodities(districtId);
CREATE INDEX idx_ddxq_commodities_category ON ddxq_commodities(category);
CREATE INDEX idx_ddxq_commodities_price ON ddxq_commodities(price);
CREATE INDEX idx_ddxq_commodities_tags ON ddxq_commodities(tags);
CREATE INDEX idx_ddxq_commodities_updated ON ddxq_commodities(updated);




--
-- 创建小区电商订单主表（超市快送）
--
DROP TABLE IF EXISTS ddxq_orders_main;
CREATE TABLE ddxq_orders_main(
	id bigint auto_increment,                  				-- 订单表的自增ID
	district_id int,                                                         -- 小区ID
	seller_id varchar(50),                                                   -- 卖方ID，对应到employees表中的商家角色
	buyer_id varchar(50),                                                    -- 买方ID，使用openid
	address varchar(255),                                                   -- 买方详细地址
	phone varchar(255),                                                     -- 买方电话

	amount_total  bigint default 0,                                         -- 订单总金额, 单位为分
	amount_goods  bigint default 0,                                         -- 商品总金额, 单位为分
	num_goods int default 0,						-- 商品总数量
	fee_parking bigint default 0,      					-- 包装费用,单位为分
	fee_delivery bigint default 0,						-- 快递费用,单位为分

	pay_status char(1) default '0',						-- 是否已支付
	pay_datetime timestamp,							-- 完成支付的时间

	deliveryman_id bigint,                                                  -- 送货员及联系方式，见employees表
	deliveryman_name varchar(255),                                          -- 送货员及联系方式，见employees表
	deliveryman_phone varchar(255),                                         -- 送货员及联系方式，见employees表

	remark varchar(255),     						-- 订单备注
	created timestamp default now(),                                        -- 下单时间
	updated timestamp default now(),                                        -- 上次更新时间
	status char(1) DEFAULT '1',                                              -- 状态标识： 0：待送； 1：完成； 2：延时；4：取消

  PRIMARY KEY(id)

)engine = innodb;

CREATE INDEX idx_ddxq_orders_main_district ON ddxq_orders_main(district_id);
CREATE INDEX idx_ddxq_orders_main_seller ON ddxq_orders_main(seller_id);
CREATE INDEX idx_ddxq_orders_main_buyer ON ddxq_orders_main(buyer_id);
CREATE INDEX idx_ddxq_orders_main_status ON ddxq_orders_main(status);



--
-- 创建小区电商订单商品详情表
--
DROP TABLE IF EXISTS ddxq_orders_items;
CREATE TABLE ddxq_orders_items(
	id bigint auto_increment,                                               -- item的自增ID
	order_id bigint,                                                        -- 主订单ID，对应orders_main中的id

	name varchar(255),                                              	-- 商品名称
	photo varchar(255),                                              	-- 商品照片URL
	quantity  int,                                                  	-- 商品数量
	price  bigint default 0,                                                -- 商品报价, 单位为分
	amount  bigint default 0,                                               -- 小计金额, 单位为分

	created timestamp default now(),                                        -- 创建时间
	updated timestamp default now(),                                        -- 上次更新时间
	status char(1) DEFAULT '1',                                              -- 状态标识： 0：冻结； 1：正常
 
 PRIMARY KEY(id)

)engine = innodb;
default character set = utf8;

CREATE INDEX idx_ddxq_orders_items_order ON ddxq_orders_items(order_id);
CREATE INDEX idx_ddxq_orders_items_status ON ddxq_orders_items(status);
