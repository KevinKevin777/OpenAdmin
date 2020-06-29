package com.zhj.openapi.web;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.SystemParam;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.pojo.TableData;
import com.zhj.openapi.service.SystemParamService;
import com.zhj.openapi.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/systemparaters")
// 系统参数处理器
public class SystemParamController {
	@Autowired
	SystemParamService systemParamService;    // 系统参数Service层接口

	@PostMapping("/add")
	// 添加系统参数
	public R insertSystemParam(SystemParam systemParam) {
		Integer num = systemParamService.insertSystemParam(systemParam); // 向数据库添加数据,返回影响行数

		return 0 < num ? new R(true, "添加系统参数成功", null) : new R(false, "添加系统参数失败", null);    // 根据影响行数判断添加数据是否成功
	}

	@PostMapping("/del")
	// 删除系统参数
	public R deleteSystemParam(String[] ids) {
		Integer[] integerArray = CommonUtil.StringArrayTransformIntegerArray(ids);   // 通过自定义工具类,把字符串类型数组转换成整数类型数组

		Integer num = systemParamService.deleteSystemParam(integerArray);  // 得到删除数据库数据影响行数

		return 0 < num ? new R(true, "删除系统参数成功", null) : new R(false, "删除系统参数失败", null);    // 返回操作是否成功
	}

	@PostMapping("/update")
	// 修改系统参数
	public R updateSystemParam(SystemParam systemParam) {
		Integer num = systemParamService.updateSystemParam(systemParam);   // 得到修改数据库数据影响行数

		return 0 < num ? new R(true, "修改系统参数成功", null) : new R(false, "修改系统参数失败", null);    // 返回操作是否成功
	}

	@GetMapping("/table")
	// 查询全部系统参数 或 根据条件查询系统参数
	public TableData<SystemParam> selectSystemParam(String page, String limit, String name, String state) {
		if (null == state || "".equals(state)) {  // 如果state没有传值,把state赋值为-1,以便Mapper映射文件中进行判断是否拼接查询条件: 0 == state or 1 == state
			state = "-1";
		}

		PageInfo<SystemParam> systemParamPageInfo = systemParamService.selectSystemParam(Integer.parseInt(page), Integer.parseInt(limit), name, Integer.parseInt(state));   // 得到全部系统参数信息的PageInfo对象 或 根据条件查询到的系统参数信息的PageInfo对象

		return new TableData<SystemParam>(0, systemParamPageInfo.getTotal(), "系统参数数据", systemParamPageInfo.getList());   // 返回表格需要格式JSON数据,状态码,数据总量,提示信息,数据
	}
}
