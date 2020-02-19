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
public enum FileStatusEnum {

	DOWN_SUCCESS(0, "对账文件下载成功"),
	IMPORT_SUCCESS(1, "入库成功"),
	CHECK_SUCCESS(2, "对账成功");

	private Integer code;

	private String desc;

	FileStatusEnum(Integer code, String desc) {
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
		public static final Map<Integer, FileStatusEnum> MAP = Maps.newHashMap();

		static {
			for(FileStatusEnum e: values()) {
				MAP.put(e.code, e);
			}
		}

		public static FileStatusEnum parse(Integer value) {
			return MAP.get(value);
		}
	}

}
