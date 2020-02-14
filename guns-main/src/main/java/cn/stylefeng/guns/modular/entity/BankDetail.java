package cn.stylefeng.guns.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/14/18:16
 * @Description:
 */
@Data
public class BankDetail {

    //columns START
    /**
     * id       db_column: id
     */
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
     * 系统跟踪号       db_column: sys_trace_no
     */
    private java.lang.String sysTraceNo;
    /**
     * 交易金额       db_column: trade_amount
     */
    private java.math.BigDecimal tradeAmount;
    /**
     * 清算手续费       db_column: clear_commission
     */
    private java.math.BigDecimal clearCommission;
    /**
     * 清算入账金额       db_column: clear_income
     */
    private java.math.BigDecimal clearIncome;
    /**
     * 原交易日期时间MMDDhhmmss       db_column: ori_trade_date
     */
    private java.lang.String oriTradeDate;
    /**
     * 原系统跟踪号       db_column: ori_trace_no
     */
    private java.lang.String oriTraceNo;
    /**
     * 交易类型       db_column: trade_type
     */
    private java.lang.String tradeType;
    /**
     * 行业信息       db_column: industry_infor
     */
    private java.lang.String industryInfor;
    /**
     * 授权码       db_column: auth_code
     */
    private java.lang.String authCode;
    /**
     * 发卡行机构识别码       db_column: identity_no
     */
    private java.lang.String identityNo;
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
    //columns END
}
