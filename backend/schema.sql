CREATE DATABASE IF NOT EXISTS campus_express DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE campus_express;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `account` VARCHAR(50) NOT NULL COMMENT '登录账号',
  `password` VARCHAR(100) NOT NULL COMMENT '登录密码',
  `role` VARCHAR(20) NOT NULL COMMENT '用户角色',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

INSERT INTO `user` (`id`, `username`, `account`, `password`, `role`) VALUES
(1, '普通用户1', 'user01', '123456', 'user'),
(2, '快递员1', 'courier01', '123456', 'courier'),
(3, '管理员1', 'admin01', '123456', 'admin');

DROP TABLE IF EXISTS `receive_record`;
CREATE TABLE `receive_record` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '收件记录编号',
  `tracking_number` VARCHAR(32) NOT NULL COMMENT '快递单号',
  `company` VARCHAR(50) NOT NULL COMMENT '快递公司',
  `receiver_id` BIGINT NOT NULL COMMENT '收件人编号',
  `location` VARCHAR(50) NOT NULL COMMENT '存放位置',
  `pickup_code` VARCHAR(20) NOT NULL COMMENT '取件码',
  `status` VARCHAR(20) NOT NULL COMMENT '当前状态',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tracking_number` (`tracking_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='收件记录表';

DROP TABLE IF EXISTS `send_request`;
CREATE TABLE `send_request` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '发件申请编号',
  `sender_id` BIGINT NOT NULL COMMENT '发件人编号',
  `receiver_name` VARCHAR(50) NOT NULL COMMENT '收件人姓名',
  `receiver_phone` VARCHAR(20) NOT NULL COMMENT '收件人电话',
  `receiver_address` VARCHAR(255) NOT NULL COMMENT '收件地址',
  `courier_id` BIGINT DEFAULT NULL COMMENT '分配的快递员编号',
  `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `status` VARCHAR(20) NOT NULL COMMENT '当前状态',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发件申请表';

DROP TABLE IF EXISTS `exception_record`;
CREATE TABLE `exception_record` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '异常编号',
  `tracking_number` VARCHAR(32) NOT NULL COMMENT '对应快递单号',
  `exception_type` VARCHAR(50) NOT NULL COMMENT '异常类型',
  `description` VARCHAR(255) NOT NULL COMMENT '异常说明',
  `result` VARCHAR(255) DEFAULT NULL COMMENT '处理结果',
  `status` VARCHAR(20) NOT NULL COMMENT '当前状态',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `handle_time` DATETIME DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='异常记录表';
