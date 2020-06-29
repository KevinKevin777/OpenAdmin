package com.zhj.openapi.web;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Recharge;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.pojo.TableData;
import com.zhj.openapi.service.RechargeService;
import com.zhj.openapi.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/sys/recharge")
// 充值处理器
public class RechargeController {
	@Autowired
	RechargeService rechargeService;    // 充值Service层接口

	@PostMapping("/add")
	// 添加充值
	public R insertRecharge(Recharge recharge) {
		RandomStringGenerator generator = new RandomStringGenerator
				.Builder()  // 构建随机生成字符串对象
				.withinRange(new char[]{'0', '9'})  // 设置取值范围0~9
				.build();   // 创建随机生成字符串对象

		String code = generator.generate(15); // 生成随机订单号字符串长度为15

		recharge.setOrderId(new BigInteger(code));  // 设置订单号
		recharge.setCreateTime(new Date()); // 设置创建时间为现在

		Integer num = rechargeService.insertRecharge(recharge); // 向数据库添加数据,返回影响行数

		return 0 < num ? new R(true, "添加充值成功", null) : new R(false, "添加充值失败", null);    // 根据影响行数判断添加数据是否成功
	}

	@PostMapping("/del")
	// 删除充值
	public R deleteRecharge(String[] ids) {
		Integer[] integerArray = CommonUtil.StringArrayTransformIntegerArray(ids);   // 通过自定义工具类,把字符串类型数组转换成整数类型数组

		Integer num = rechargeService.deleteRecharge(integerArray);  // 得到删除数据库数据影响行数

		return 0 < num ? new R(true, "删除充值成功", null) : new R(false, "删除充值失败", null);    // 返回操作是否成功
	}

	@PostMapping("/update")
	// 修改充值
	public R updateRecharge(Recharge recharge) {
		recharge.setUpdateTime(new Date()); // 设置修改时间为现在

		Integer num = rechargeService.updateRecharge(recharge);   // 得到修改数据库数据影响行数

		return 0 < num ? new R(true, "修改充值成功", null) : new R(false, "修改充值失败", null);    // 返回操作是否成功
	}

	@GetMapping("/table")
	// 查询全部充值
	public TableData<Recharge> selectRecharge(String page, String limit) {
		PageInfo<Recharge> rechargePageInfo = rechargeService.selectRecharge(Integer.parseInt(page), Integer.parseInt(limit));   // 得到全部充值信息的PageInfo对象

		return new TableData<Recharge>(0, rechargePageInfo.getTotal(), "全部充值数据", rechargePageInfo.getList());   // 返回表格需要格式JSON数据,状态码,数据总量,提示信息,数据
	}
}
