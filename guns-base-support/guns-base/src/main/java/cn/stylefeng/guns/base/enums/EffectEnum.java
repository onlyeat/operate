package cn.stylefeng.guns.base.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/19/21:34
 * @Description:
 */
public enum EffectEnum {

    EFFECTIVE(1, "有效"),
    INVALID(0, "无效");

    private Integer code;
    private String desc;

    EffectEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer getCode() {
        return this.code;
    }


    public String getDesc() {
        return this.desc;
    }

}
