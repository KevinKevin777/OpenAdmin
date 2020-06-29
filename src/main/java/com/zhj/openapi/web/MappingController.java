package com.zhj.openapi.web;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Mapping;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.pojo.TableData;
import com.zhj.openapi.service.MappingService;
import com.zhj.openapi.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/route")
// 路由处理器
public class MappingController {
	@Autowired
	MappingService mappingService;    // 路由Service层接口

	@PostMapping("/add")
	// 添加路由
	public R insertMapping(Mapping mapping) {
		Integer num = mappingService.insertMapping(mapping); // 向数据库添加数据,返回影响行数

		return 0 < num ? new R(true, "添加路由成功", null) : new R(false, "添加路由失败", null);    // 根据影响行数判断添加数据是否成功
	}

	@PostMapping("/del")
	// 删除路由
	public R deleteMapping(String[] ids) {
		Integer[] integerArray = CommonUtil.StringArrayTransformIntegerArray(ids);   // 通过自定义工具类,把字符串类型数组转换成整数类型数组

		Integer num = mappingService.deleteMapping(integerArray);  // 得到删除数据库数据影响行数

		return 0 < num ? new R(true, "删除路由成功", null) : new R(false, "删除路由失败", null);    // 返回操作是否成功
	}

	@PostMapping("/update")
	// 修改路由
	public R updateMapping(Mapping mapping) {
		Integer num = mappingService.updateMapping(mapping);   // 得到修改数据库数据影响行数

		return 0 < num ? new R(true, "修改路由成功", null) : new R(false, "修改路由失败", null);    // 返回操作是否成功
	}

	@GetMapping("/table")
	// 查询全部路由 或 根据条件查询路由
	public TableData<Mapping> selectMapping(String page, String limit, String gatewayApiName, String state) {
		if (null == state || "".equals(state)) {  // 如果state没有传值,把state赋值为-1,以便Mapper映射文件中进行判断是否拼接查询条件: 0 == state or 1 == state
			state = "-1";
		}

		PageInfo<Mapping> mappingPageInfo = mappingService.selectMapping(Integer.parseInt(page), Integer.parseInt(limit), gatewayApiName, Integer.parseInt(state));   // 得到全部路由信息的PageInfo对象 或 根据条件查询到的路由信息的PageInfo对象

		return new TableData<Mapping>(0, mappingPageInfo.getTotal(), "路由数据", mappingPageInfo.getList());   // 返回表格需要格式JSON数据,状态码,数据总量,提示信息,数据
	}
}
