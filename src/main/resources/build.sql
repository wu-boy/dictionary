-- 创建数据字典表
drop table if exists `sys_dictionary`;
create table `sys_dictionary`(
  `id` varchar(32) comment 'ID',
  `value` varchar(64) not null default '' comment '数据值',
  `label` varchar(64) not null default '' comment '标签名',
  `type` varchar(32) not null default '' comment '类型',
  `order_number` int not null default 0 comment '排序',
  `description` varchar(64) not null default '' comment '描述',
  `remark` varchar(64) not null default '' comment '备注',
  `state` varchar(2) not null default '1' comment '状态',
  `create_time` datetime not null default current_timestamp comment '创建时间',
  `update_time` datetime not null default current_timestamp comment '修改时间',
  primary key (`id`)
) engine=InnoDB comment '数据字典表';

-- 创建行政区划表
drop table if exists `sys_district`;
create table `sys_district`(
  `id` varchar(32) comment 'ID',
  `parent_id` varchar(32) not null default '' comment '父ID',
  `label` varchar(64) not null default '' comment '标签名',
  `type` varchar(32) not null default '' comment '类别',
  `lon` double(9,6) not null default 0 comment '经度',
  `lat` double(9,6) not null default 0 comment '纬度',
  `order_number` int not null default 0 comment '排序',
  `state` tinyint not null default 1 comment '状态',
  `create_time` datetime not null default current_timestamp comment '创建时间',
  `update_time` datetime not null default current_timestamp comment '修改时间',
  primary key (`id`)
) engine=InnoDB comment '行政区划表';
