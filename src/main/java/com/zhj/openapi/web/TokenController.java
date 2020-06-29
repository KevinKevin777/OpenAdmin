package com.zhj.openapi.web;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Token;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.pojo.TableData;
import com.zhj.openapi.service.TokenService;
import com.zhj.openapi.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/token")
// 标记处理器
public class TokenController {
	@Autowired
	TokenService tokenService;    // 标记Service层接口

	@PostMapping("/del")
	// 删除标记
	public R deleteToken(String[] ids) {
		Integer[] integerArray = CommonUtil.StringArrayTransformIntegerArray(ids);   // 通过自定义工具类,把字符串类型数组转换成整数类型数组

		Integer num = tokenService.deleteToken(integerArray);  // 得到删除数据库数据影响行数

		return 0 < num ? new R(true, "删除标记成功", null) : new R(false, "删除标记失败", null);    // 返回操作是否成功
	}

	@PostMapping("/update")
	// 修改标记
	public R updateToken(Token token) {
		Integer num = tokenService.updateToken(token);   // 得到修改数据库数据影响行数

		return 0 < num ? new R(true, "修改标记成功", null) : new R(false, "修改标记失败", null);    // 返回操作是否成功
	}

	@GetMapping("/table")
	// 查询全部标记 或 根据条件查询标记
	public TableData<Token> selectToken(String page, String limit, String accessToken) {
		PageInfo<Token> tokenPageInfo = tokenService.selectToken(Integer.parseInt(page), Integer.parseInt(limit), accessToken);   // 得到全部标记信息的PageInfo对象 或 根据条件查询到的标记信息的PageInfo对象

		return new TableData<Token>(0, tokenPageInfo.getTotal(), "标记数据", tokenPageInfo.getList());   // 返回表格需要格式JSON数据,状态码,数据总量,提示信息,数据
	}
}
