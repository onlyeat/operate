package cn.stylefeng.guns.base.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/15/17:12
 * @Description:
 */
public enum TradeChannelEnum {

    //    WX微信
    //    ZFB支付宝
    //    YSF云闪付
    //    FJ富基
	WEI_XIN(1, "WX"),
	ZHI_FU_BAO(2, "ZFB"),
	YUN_SHAN_FU(3, "YSF"),
	FU_JI(4, "FJ");

    private Integer code;

    private String desc;

    TradeChannelEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class Holder {
        public static final Map<Integer, TradeChannelEnum> MAP = Maps.newHashMap();

        static {
            for(TradeChannelEnum e: values()) {
                MAP.put(e.code, e);
            }
        }

        public static TradeChannelEnum parse(Integer value) {
            return MAP.get(value);
        }
    }
}
