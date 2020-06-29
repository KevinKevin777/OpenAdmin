package com.zhj.openapi.web;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Application;
import com.zhj.openapi.domain.Customer;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.pojo.TableData;
import com.zhj.openapi.service.ApplicationService;
import com.zhj.openapi.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sys/app")
// 应用处理器
public class ApplicationController {
	@Autowired
	ApplicationService applicationService;    // 应用Service层接口

	@PostMapping("/add")
	// 添加应用
	public R insertApplication(Application application) {
		Integer num = applicationService.insertApplication(application); // 向数据库添加应用,返回影响行数

		return 0 < num ? new R(true, "添加应用成功", null) : new R(false, "添加应用失败", null);    // 根据影响行数判断添加数据是否成功
	}

	@PostMapping("/del")
	// 删除应用
	public R deleteApplication(String[] ids) {
		Integer[] integerArray = CommonUtil.StringArrayTransformIntegerArray(ids);   // 通过自定义工具类,把字符串类型数组转换成整数类型数组

		Integer num = applicationService.deleteApplication(integerArray);  // 得到删除数据库数据影响行数

		return 0 < num ? new R(true, "删除应用成功", null) : new R(false, "删除应用失败", null);    // 返回操作是否成功
	}

	@PostMapping("/update")
	// 修改应用
	public R updateApplication(Application application) {
		Integer num = applicationService.updateApplication(application);   // 得到修改数据库数据影响行数

		return 0 < num ? new R(true, "修改应用成功", null) : new R(false, "修改应用失败", null);    // 返回操作是否成功
	}

	@GetMapping("/table")
	// 查询全部应用 或 根据条件查询应用
	public TableData<Application> selectApplication(String page, String limit, String appName) {
		PageInfo<Application> applicationPageInfo = applicationService.selectApplication(Integer.parseInt(page), Integer.parseInt(limit), appName);   // 得到全部应用信息的PageInfo对象 或 根据条件查询到的应用信息的PageInfo对象

		return new TableData<Application>(0, applicationPageInfo.getTotal(), "应用数据", applicationPageInfo.getList());   // 返回表格需要格式JSON数据,状态码,数据总量,提示信息,数据
	}
}
