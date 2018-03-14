CREATE TABLE `sys_role_data_control` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `data_type` int(2) DEFAULT NULL COMMENT '数据类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='角色数据管控表';

CREATE TABLE `sys_data_control` (
  `role_id` int(11) NOT NULL,
  `org_id` int(11) NOT NULL COMMENT '组织ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据管控表';

CREATE TABLE `busi_ticket_order` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `single_num` int(12) NOT NULL COMMENT '单票数',
  `single_num_privilege` int(12) NOT NULL COMMENT '单优票数',
  `double_num` int(12) DEFAULT NULL COMMENT '双票数',
  `double_num_privilege` int(12) DEFAULT NULL COMMENT '双优票数',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态（0：已出；1：未出）',
  `ticket_num` varchar(32) DEFAULT NULL COMMENT '出票号',
  `ticket_person` varchar(32) NOT NULL COMMENT '取票人姓名',
  `ticket_person_identity` varchar(32) NOT NULL COMMENT '取票人身份证',
  `ticket_person_phone` varchar(16) DEFAULT NULL COMMENT '取票人电话',
  `create_userid` int(12) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_userid` int(12) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1382 DEFAULT CHARSET=utf8 COMMENT='订单表';

CREATE TABLE `busi_pay_flow` (
  `seq` int(11) NOT NULL COMMENT '流水号',
  `status` int(11) DEFAULT '1' COMMENT '（支付状态【0：支付成功；1：支付流程创建；2：支付请求发生；3:收到支付响应】）',
  `order_id` int(11) DEFAULT NULL COMMENT '订单号',
  `excuted_class` varchar(255) DEFAULT NULL COMMENT '执行类',
  `processId` int(11) DEFAULT NULL COMMENT '流程定义ID',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付流程跟踪';