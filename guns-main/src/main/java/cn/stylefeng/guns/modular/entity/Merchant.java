package cn.stylefeng.guns.modular.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Merchant implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 渠道商名称
     */
    private String name;
    /**
     * 渠道商编码
     */
    private String code;
    /**
     * 有效状态(0:无效,1:有效)
     */
    private Integer status;
    /**
     * 创建人ID
     */
    @TableField("create_by")
    private Long createBy;
    /**
     * 修改人ID
     */
    @TableField("update_by")
    private Long updateBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

}
