-- 对账文件主表
drop table file_result;
CREATE TABLE `file_result` (
  `id` bigint(8) NOT NULL AUTO_INCREMENT COMMENT 'id',
   file_date  datetime DEFAULT CURRENT_TIMESTAMP  COMMENT '对账文件日期',
	 channel varchar(10) DEFAULT NULL COMMENT '文件渠道',
  `trade_number` varchar(20) DEFAULT NULL COMMENT '交易笔数',
  trade_amount DECIMAL(10,2) DEFAULT 0 COMMENT '交易金额',
  clear_amount DECIMAL(10,2) DEFAULT 0 COMMENT '清算金额',
  `diff_number` varchar(20) DEFAULT NULL COMMENT '差错交易笔数',
  diff_amount DECIMAL(10,2) DEFAULT 0 COMMENT '差错交易金额',
  diff_clear_amount DECIMAL(10,2) DEFAULT 0 COMMENT '差错清算金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 银商明细表
 create table bank_detail(
 merchant_no                 varchar(15)    comment   '商户编号',
 terminal                    varchar(8)     comment   '终端编号',
 clear_date                  varchar(8)     comment   '清算日期yyyyMMdd',
 trade_date                  varchar(10)    comment   '交易日期时间MMddhhmmss',
 trade_card_no               varchar(19)    comment   '交易卡号',
 index_no                    varchar(12)    comment   '检索参考号',
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
 bak7                        varchar(200)   comment   '预留字段7'
 );


--富基表 索引未设置
create table bussiness_detail(
merchant_no	   varchar(15)         comment                  '商户编号',
terminal	   varchar(8)          comment                  '终端编号',
clear_date	   varchar(8)          comment                  '清算日期yyyyMMdd',
trade_date	   varchar(8)          comment                  '交易日期时间MMddhhmmss',
trade_card_no	 varchar(19)       comment                  '交易卡号',
index_no	     varchar(12)       comment                  '检索参考号',
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
bak8   varchar(200)                comment                  '预留字段8'
);