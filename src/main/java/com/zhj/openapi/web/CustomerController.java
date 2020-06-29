package com.zhj.openapi.web;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Customer;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.pojo.TableData;
import com.zhj.openapi.service.CustomerService;
import com.zhj.openapi.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sys/customer")
// 客户处理器
public class CustomerController {
	@Autowired
	CustomerService customerService;    // 客户Service层接口

	@PostMapping("/add")
	// 添加客户
	public R insertCustomer(Customer customer) {
		Integer num = customerService.insertCustomer(customer); // 向数据库添加客户,返回影响行数

		return 0 < num ? new R(true, "添加客户成功", null) : new R(false, "添加客户失败", null);    // 根据影响行数判断添加数据是否成功
	}

	@PostMapping("/del")
	// 删除客户
	public R deleteCustomer(String[] ids) {
		Integer[] integerArray = CommonUtil.StringArrayTransformIntegerArray(ids);   // 通过自定义工具类,把字符串类型数组转换成整数类型数组

		Integer num = customerService.deleteCustomer(integerArray);  // 得到删除数据库数据影响行数

		return 0 < num ? new R(true, "删除客户成功", null) : new R(false, "删除客户失败", null);    // 返回操作是否成功
	}

	@PostMapping("/update")
	// 修改客户
	public R updateCustomer(Customer customer) {
		Integer num = customerService.updateCustomer(customer);   // 得到修改数据库数据影响行数

		return 0 < num ? new R(true, "修改客户成功", null) : new R(false, "修改客户失败", null);    // 返回操作是否成功
	}

	@GetMapping("/table")
	// 查询全部客户 或 根据条件查询客户
	public TableData<Customer> selectCustomer(String page, String limit, String username, String state) {
		if (null == state || "".equals(state)) {  // 如果state没有传值,把state赋值为-1,以便Mapper映射文件中进行判断是否拼接查询条件: 0 == state or 1 == state
			state = "-1";
		}

		PageInfo<Customer> customerPageInfo = customerService.selectCustomer(Integer.parseInt(page), Integer.parseInt(limit), username, Integer.parseInt(state));   // 得到全部客户信息的PageInfo对象 或 根据条件查询到的客户信息的PageInfo对象

		return new TableData<Customer>(0, customerPageInfo.getTotal(), "全部客户数据", customerPageInfo.getList());   // 返回表格需要格式JSON数据,状态码,数据总量,提示信息,数据
	}

	@GetMapping("/tree")
	// 查询客户列表
	public List<Customer> selectAllCustomerId() {
		List<Customer> CustomerIds = customerService.selectAllCustomer();   // 得到数据库查询数据

		return CustomerIds; // 返回客户ID列表
	}

}
