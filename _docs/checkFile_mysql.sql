-- 交易笔数		非差错交易笔数
-- 交易金额	负交易带负号，带小数位最后保留2位，最长16位	非差错交易金额
-- 交易清算金额	负交易带负号，带小数位最后保留2位，最长16位	非差错清算金额（交易金额-手续费）
-- 差错交易笔数		差错交易笔数
-- 差错交易金额	负交易带负号，带小数位最后保留2位，最长16位	差错交易金额
-- 差错交易清算金额	负交易带负号，带小数位最后保留2位，最长16位	差错交易清算金额（交易金额-手续费）
--
-- 对账文件主表
drop table if exists file_result;
CREATE TABLE `file_result` (
  `id` bigint(8) NOT NULL AUTO_INCREMENT COMMENT 'id',
 file_batch_id VARCHAR(20) not null COMMENT '批次号',
   file_date  datetime DEFAULT CURRENT_TIMESTAMP  COMMENT '对账文件日期',
	 channel varchar(10) DEFAULT NULL COMMENT '文件渠道',
  `trade_number` varchar(20) DEFAULT NULL COMMENT '交易笔数',
  trade_amount DECIMAL(10,2) DEFAULT 0 COMMENT '交易金额',
  clear_amount DECIMAL(10,2) DEFAULT 0 COMMENT '清算金额',
  `diff_number` varchar(20) DEFAULT NULL COMMENT '差错交易笔数',
  diff_amount DECIMAL(10,2) DEFAULT 0 COMMENT '差错交易金额',
  diff_clear_amount DECIMAL(10,2) DEFAULT 0 COMMENT '差错清算金额',
  PRIMARY KEY (`id`),
  UNIQUE key `uidx_file_batch_id` (`file_batch_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对账文件主表';

-- 1	商户编号	15位
-- 2	终端编号	8位
-- 3	清算日期	yyyyMMdd	与文件名日期一致
-- 4	交易日期时间	MMddhhmmss
-- 5	交易卡号	最长19位	条码交易为条码号（支付宝微信等均无卡号，只有条形码）
-- 6	检索参考号	12位
-- 7	系统跟踪号	6位
-- 8	交易金额	负交易带负号，带小数位最后保留2位，最长16位
-- 9	清算手续费	负交易带负号，带小数位最后保留2位，最长10位
-- 10	清算入账金额	负交易带负号，带小数位最后保留2位，最长16位
-- 11	原交易日期时间	MMDDhhmmss	撤销、冲正、差错类交易填写原交易日期时间，非撤销全0或空
-- 12	原系统跟踪号	6位	撤销、冲正、差错类填写原交易的流水号，非撤销全0或空
-- 13	交易类型	3位	同银联交易类型码，E开头为差错（退款）交易
-- 14	行业信息	最长200位	增值信息，中百暂无
-- 15	授权码	6位
-- 16	发卡行机构识别码	8位
-- 17	预留字段2	最长200位
-- 18	预留字段3	最长200位
-- 19	预留字段4	最长200位
-- 20	预留字段5	最长200位
-- 21	预留字段6	最长200位
-- 22	预留字段7	最长200位
-- 23	预留字段8	最长200位

-- 银商银联明细表
DROP  table if exists  bank_detail;
 create table bank_detail(
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 file_batch_id VARCHAR(20) not null COMMENT '批次号',
 trade_channel	             varchar(12)    comment   '交易渠道WX微信 ZFB支付宝 YSF云闪付',
 merchant_no                 varchar(15)    comment   '商户编号',
 terminal                    varchar(8)     comment   '终端编号',
 clear_date                  varchar(8)     comment   '清算日期yyyyMMdd',
 trade_date                  varchar(20)    comment   '交易日期时间MMddhhmmss',
 trade_card_no               varchar(19)    comment   '交易卡号',
 index_no                    varchar(30)    comment   '检索参考号',
 sys_trace_no                varchar(6)     comment   '系统跟踪号',
 trade_amount                decimal(16,2)  comment   '交易金额',
 clear_commission            decimal(10,2)  comment   '清算手续费',
 clear_income                decimal(16,2)  comment   '清算入账金额',
 ori_trade_date              varchar(10)    comment   '原交易日期时间MMDDhhmmss',
 ori_trace_no                varchar(6)     comment   '原系统跟踪号',
 trade_type                  varchar(3)     comment   '交易类型',
 industry_infor              varchar(200)   comment   '行业信息',
 auth_code                   varchar(6)     comment   '授权码',
 identity_no                 varchar(8)     comment   '发卡行机构识别码',
 bak1                        varchar(200)   comment   '预留字段1',
 bak2                        varchar(200)   comment   '预留字段2',
 bak3                        varchar(200)   comment   '预留字段3',
 bak4                        varchar(200)   comment   '预留字段4',
 bak5                        varchar(200)   comment   '预留字段5',
 bak6                        varchar(200)   comment   '预留字段6',
 bak7                        varchar(200)   comment   '预留字段7'	,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_index_no` (`clear_date`,`index_no`) USING BTREE,
  KEY `idx_merchant_no` (`merchant_no`),
  KEY `idx_clear_date` (`clear_date`),
  KEY `idx_index_no` (`index_no`),
  KEY `idx_trade_type` (`trade_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='银商银联明细表';

-- 1	商户编号	15位	线上交易可统一设定为相同值，即常量。
-- 2	终端编号	8位	如为线下主扫时填入
-- 线上交易不用填值，可为空
-- 云闪付交易有值
-- 3	清算日期	yyyyMMdd	与文件名日期一致
-- 4	交易日期时间	MMddhhmmss
-- 5	交易卡号	最长32位	条码交易为条码号（支付宝微信等均无卡号，只有条形码）如无值，可为空
-- 云闪付交易有值
-- 6	检索参考号	32位	商户订单号（不可为空）
-- 7	交易渠道	4位	WX微信
-- ZFB支付宝
-- YSF云闪付
-- 8	交易金额	负交易带负号，带小数位最后保留2位，最长16位
-- 9	活动优惠金额	负交易带负号，带小数位最后保留2位，最长10位	如无值，可为空
-- 10	实际交易金额	负交易带负号，带小数位最后保留2位，最长16位	如无值，可为空
-- 11	交易类型（支付分类）	4位	ZBQB中百钱包
-- ZBST中百食堂
-- ZBPS中百配送
-- ZBSX中百生鲜
-- 12	商品大类信息	最长200位	后期详商，为空时，去空格
-- 13	预留字段1	最长200位	为空时，去空格
-- 14	预留字段2	最长200位	为空时，去空格
-- 15	预留字段3	最长200位	为空时，去空格
-- 16	预留字段4	最长200位	为空时，去空格
-- 17	预留字段5	最长200位	为空时，去空格
-- 18	预留字段6	最长200位	为空时，去空格
-- 19	预留字段7	最长200位	为空时，去空格
-- 20	预留字段8	最长200位	为空时，去空格

-- 富基表 索引未设置
drop table if exists business_detail;
create table business_detail(
`id` bigint(20) NOT NULL AUTO_INCREMENT,
 file_batch_id VARCHAR(20) not null COMMENT '批次号',
merchant_no	   varchar(15)         comment                  '商户编号',
terminal	   varchar(8)          comment                  '终端编号',
clear_date	   varchar(8)          comment                  '清算日期yyyyMMdd',
trade_date	   varchar(20)          comment                  '交易日期时间MMddhhmmss',
trade_card_no	 varchar(19)       comment                  '交易卡号',
index_no	     varchar(30)       comment                  '检索参考号',
trade_channel	varchar(12)        comment                  '交易渠道WX微信 ZFB支付宝 YSF云闪付',
trade_amount	decimal(16,2)      comment                  '交易金额',
discount_amount	decimal(16,2)      comment                  '优惠金额',
real_amount  		decimal(16,2)  comment                  '实际金额',
trade_type	varchar(200)           comment                  '交易类型支付分类ZBQB中百钱包 ZBST中百食堂 ZBPS中百配送 ZBSX中百生鲜',
product_info varchar(200)          comment                  '商品大类信息',
bak1   varchar(200)                comment                  '预留字段1',
bak2   varchar(200)                comment                  '预留字段2',
bak3   varchar(200)                comment                  '预留字段3',
bak4   varchar(200)                comment                  '预留字段4',
bak5   varchar(200)                comment                  '预留字段5',
bak6   varchar(200)                comment                  '预留字段6',
bak7   varchar(200)                comment                  '预留字段7',
bak8   varchar(200)                comment                  '预留字段8',
 PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_index_no` (`clear_date`,`index_no`) USING BTREE,
  KEY `idx_merchant_no` (`merchant_no`),
  KEY `idx_clear_date` (`clear_date`),
  KEY `idx_index_no` (`index_no`),
  KEY `idx_trade_type` (`trade_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='富基业务明细表';


-- --对账结果表
drop table if exists check_detail;
create table check_detail(
`id` bigint(20) NOT NULL AUTO_INCREMENT,
 file_batch_id VARCHAR(20) not null COMMENT '批次号',
merchant_no	   varchar(15)         comment                  '商户编号',
terminal	   varchar(8)          comment                  '终端编号',
clear_date	   varchar(8)          comment                  '清算日期yyyyMMdd',
trade_date	   varchar(20)          comment                  '交易日期时间MMddhhmmss',
trade_card_no	 varchar(19)       comment                  '交易卡号',
index_no	     varchar(30)       comment                  '检索参考号',
trade_channel	varchar(12)        comment                  '交易渠道WX微信 ZFB支付宝 YSF云闪付',
trade_amount	decimal(16,2)      comment                  '交易金额',
discount_amount	decimal(16,2)      comment                  '优惠金额',
real_amount  		decimal(16,2)  comment                  '实际金额',
trade_type	varchar(200)           comment                  '交易类型支付分类ZBQB中百钱包 ZBST中百食堂 ZBPS中百配送 ZBSX中百生鲜',
 check_status	int(2)           comment                  '0-正常 1-差错',
PRIMARY KEY (`id`),
  UNIQUE KEY `uidx_index_no` (`clear_date`,`index_no`) USING BTREE,
  KEY `idx_merchant_no` (`merchant_no`),
  KEY `idx_clear_date` (`clear_date`),
  KEY `idx_index_no` (`index_no`),
  KEY `idx_trade_type` (`trade_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对账结果表';
