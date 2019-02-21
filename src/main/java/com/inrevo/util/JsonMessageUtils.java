package com.inrevo.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * JSON转换工具 by rocky 20170307
 */
public class JsonMessageUtils {

	public JsonMessageUtils() {
	}

	public static String getObjectJson(Object object) {
		if (object == null) {
			return setCharacter(getErrorJson("object is null"));
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			String objStr = mapper.writeValueAsString(object).toString();
//			return setCharacter((new StringBuilder()).append("{\"code\":0,\"message\":\"").append("\",\"result\":")
//					.append(objStr).append("}").toString());
			return (new StringBuilder()).append("{\"code\":0,\"message\":\"").append("\",\"result\":")
			.append(objStr).append("}").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getObjectJson(Object object, String objectname) {
		if (object == null) {
			return setCharacter(getErrorJson("object is null"));
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			String objStr = mapper.writeValueAsString(object).toString();
//			return setCharacter((new StringBuilder()).append("{\"code\":0,\"").append(objectname).append("\":")
//					.append(objStr).append("}").toString());
			return (new StringBuilder()).append("{\"code\":0,\"").append(objectname).append("\":")
			.append(objStr).append("}").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String getStringJson(String name, String value) {
//		return setCharacter((new StringBuilder()).append("{\"code\":0,\"").append(name).append("\":\"").append(value)
//				.append("\"}").toString());
		return (new StringBuilder()).append("{\"code\":0,\"").append(name).append("\":\"").append(value)
				.append("\"}").toString();
	}

	public static String getNumberJson(String name, Object value) {
//		return setCharacter((new StringBuilder()).append("{\"code\":0,\"").append(name).append("\":").append(value)
//				.append("}").toString());
		return (new StringBuilder()).append("{\"code\":0,\"").append(name).append("\":").append(value)
				.append("}").toString();
	}

	public static String getListJson(List<?> list) {
		if (list == null) {
			return getErrorJson("list is null");
		} else {
			try {
                String listStr = JSON.toJSONString(list);
                return setCharacter(
						(new StringBuilder()).append("{\"code\":0,\"result\":").append(listStr).append("}").toString());
			} catch (Exception e) {
				e.printStackTrace();
				return setCharacter(getErrorJson("服务器出错"));
			}
		}
	}

	public static String getErrorJson(String message) {
		return setCharacter(
				(new StringBuilder()).append("{\"code\":1,\"message\":\"").append(message).append("\"}").toString());
	}

	/**
	 * 返回错误Json信息
	 * 
	 * @param code
	 *            错误码，错误码请使用1以上的数字。
	 * @param message
	 *            错误信息
	 * @return
	 */
	public static String getErrorJson(Integer code, String message) {
		return setCharacter((new StringBuilder()).append("{\"code\":" + code + ",\"message\":\"").append(message)
				.append("\"}").toString());
	}

	public static String getSuccessJson(String message) {
		return setCharacter(
				(new StringBuilder()).append("{\"code\":0,\"message\":\"").append(message).append("\"}").toString());
	}

	public static String setCharacter(String result) {
		try {
			return new String(StringUtils.getUTF8BytesFromGBKString(result), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getErrorJson("转码出错");
	}


}
