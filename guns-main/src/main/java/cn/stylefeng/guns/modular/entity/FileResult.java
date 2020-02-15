package cn.stylefeng.guns.modular.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/14/18:14
 * @Description:
 */
@Data
public class FileResult implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 对账文件日期       db_column: file_date
     */
    private java.sql.Timestamp fileDate;
    /**
     * 文件渠道       db_column: channel
     */
    private java.lang.String channel;
    /**
     * 交易笔数       db_column: trade_number
     */
    private java.lang.String tradeNumber;
    /**
     * 交易金额       db_column: trade_amount
     */
    private java.math.BigDecimal tradeAmount;
    /**
     * 清算金额       db_column: clear_amount
     */
    private java.math.BigDecimal clearAmount;
    /**
     * 差错交易笔数       db_column: diff_number
     */
    private java.lang.String diffNumber;
    /**
     * 差错交易金额       db_column: diff_amount
     */
    private java.math.BigDecimal diffAmount;
    /**
     * 差错清算金额       db_column: diff_clear_amount
     */
    private java.math.BigDecimal diffClearAmount;
    //columns END


    public FileResult() {
    }

    public FileResult(String tradeNumber, BigDecimal tradeAmount, BigDecimal clearAmount) {
        this.tradeNumber = tradeNumber;
        this.tradeAmount = tradeAmount;
        this.clearAmount = clearAmount;
    }
}
