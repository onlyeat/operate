package cn.stylefeng.guns.base.enums;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Liu QianYi
 * @Date: 2020/02/19/21:34
 * @Description:
 */
public enum CheckStatusEnum {

	OK(0, "勾兑正常"),
	ZHONG_BAI_MORE(1, "中百多,银商少"),
	ZHONG_BAI_LESS(2, "中百少,银行多"),
	WRONG_AMOUNT(3, "交易金额不符");

	private Integer code;

	private String desc;

	CheckStatusEnum(Integer code, String desc) {
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
		public static final Map<Integer, CheckStatusEnum> MAP = Maps.newHashMap();

		static {
			for(CheckStatusEnum e: values()) {
				MAP.put(e.code, e);
			}
		}

		public static CheckStatusEnum parse(Integer value) {
			return MAP.get(value);
		}
	}

}
