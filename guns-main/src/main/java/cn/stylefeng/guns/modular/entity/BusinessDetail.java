package cn.stylefeng.guns.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/14/18:17
 * @Description:
 */
public class BusinessDetail implements Serializable {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	 * 商户编号       db_column: merchant_no
	 */
	private java.lang.String merchantNo;
	/**
	 * 终端编号       db_column: terminal
	 */
	private java.lang.String terminal;
	/**
	 * 清算日期yyyyMMdd       db_column: clear_date
	 */
	private java.lang.String clearDate;
	/**
	 * 交易日期时间MMddhhmmss       db_column: trade_date
	 */
	private java.lang.String tradeDate;
	/**
	 * 交易卡号       db_column: trade_card_no
	 */
	private java.lang.String tradeCardNo;
	/**
	 * 检索参考号       db_column: index_no
	 */
	private java.lang.String indexNo;
	/**
	 * 交易渠道WX微信 ZFB支付宝 YSF云闪付       db_column: trade_channel
	 */
	private java.lang.String tradeChannel;
	/**
	 * 交易金额       db_column: trade_amount
	 */
	private java.math.BigDecimal tradeAmount;
	/**
	 * 优惠金额       db_column: discount_amount
	 */
	private java.math.BigDecimal discountAmount;
	/**
	 * 实际金额       db_column: real_amount
	 */
	private java.math.BigDecimal realAmount;
	/**
	 * 交易类型支付分类ZBQB中百钱包 ZBST中百食堂 ZBPS中百配送 ZBSX中百生鲜       db_column: trade_type
	 */
	private java.lang.String tradeType;
	/**
	 * 商品大类信息       db_column: product_info
	 */
	private java.lang.String productInfo;
	/**
	 * 预留字段1       db_column: bak1
	 */
	private java.lang.String bak1;
	/**
	 * 预留字段2       db_column: bak2
	 */
	private java.lang.String bak2;
	/**
	 * 预留字段3       db_column: bak3
	 */
	private java.lang.String bak3;
	/**
	 * 预留字段4       db_column: bak4
	 */
	private java.lang.String bak4;
	/**
	 * 预留字段5       db_column: bak5
	 */
	private java.lang.String bak5;
	/**
	 * 预留字段6       db_column: bak6
	 */
	private java.lang.String bak6;
	/**
	 * 预留字段7       db_column: bak7
	 */
	private java.lang.String bak7;
	/**
	 * 预留字段8       db_column: bak8
	 */
	private java.lang.String bak8;
	//columns END


	public BusinessDetail() {
	}

	//898420154119049|            |20191105   |1105100940    |           |1911011000000467|ZFB       |200       |              |              |ZBQB       |||||||||
	//0-商户编号      1-终端编号     2-清算日期   3-交易日期时间    4-交易卡号   5-检索参考号      6-交易渠道   7-交易金额  8-活动优惠金额    9-实际交易金额  10-交易类型
	/**
	 * 富基明细构造
	 * @param detailRow
	 */
	public BusinessDetail(String[] detailRow) {
		this.merchantNo = detailRow[0];
		this.terminal = detailRow[1];
		this.clearDate = detailRow[2];
		this.tradeDate = detailRow[3];
		this.tradeCardNo = detailRow[4];
		this.indexNo = detailRow[5];
		this.tradeChannel = detailRow[6];
		this.tradeAmount = StringUtils.isNotBlank(detailRow[7]) ? new BigDecimal(detailRow[7]) : null;
		this.discountAmount = StringUtils.isNotBlank(detailRow[8]) ? new BigDecimal(detailRow[8]) : null;
		this.realAmount = StringUtils.isNotBlank(detailRow[9]) ? new BigDecimal(detailRow[9]) : null;
		this.tradeType = detailRow[10];
	}
}
