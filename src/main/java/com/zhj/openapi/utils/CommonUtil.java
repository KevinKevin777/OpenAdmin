package com.zhj.openapi.utils;

// 常用工具类
public class CommonUtil {
	// 字符串类型数组转换成整数类型数组
	public static Integer[] StringArrayTransformIntegerArray(String[] stringArray) {
		Integer[] integerArray = new Integer[stringArray.length];   // 创建一个,和字符串类型数组长度一样的整数类型数组

		for (int i = 0; i < stringArray.length; i++) {  // 遍历字符串类型数组
			integerArray[i] = Integer.parseInt(stringArray[i]); // 把字符串类型数组中元素,复制到整数类型数组中
		}

		return integerArray;    // 返回转换完成的整数类型数组
	}
}
