drop table file_result;
CREATE TABLE `file_result` (
  `id` bigint(8) NOT NULL AUTO_INCREMENT COMMENT 'id',
	 channel varchar(10) DEFAULT NULL COMMENT '文件渠道',
  `trade_number` varchar(20) DEFAULT NULL COMMENT '交易笔数',
  trade_amount DECIMAL(10,2) DEFAULT 0 COMMENT '交易金额',
  clear_amount DECIMAL(10,2) DEFAULT 0 COMMENT '清算金额',
  `diff_number` varchar(20) DEFAULT NULL COMMENT '差错交易笔数',
  diff_amount DECIMAL(10,2) DEFAULT 0 COMMENT '差错交易金额',
  diff_clear_amount DECIMAL(10,2) DEFAULT 0 COMMENT '差错清算金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;