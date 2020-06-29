package com.zhj.openapi.web;

import com.zhj.openapi.domain.User;
import com.zhj.openapi.pojo.Constant;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
// 关于登录处理器
public class SystemController {
	@Autowired
	UserService userService;    // User的Service层接口

	@ResponseBody
	@PostMapping("/login")
	// 登录
	public R login(String email, String password, HttpServletRequest request) {
		User user = userService.login(email, password); // 向数据库中查询用户的结果

		if (null == user) { // 如果查询得到用户为空,表示账号或密码错误
			return new R(false, "账号或密码错误", null);
		}

		request.getSession().setAttribute(Constant.SESSION_USER, user); // 往Session中存储用户,表示现在处于用户已经登录状态

		return new R(true, "登录成功", user.getRealName());  // 返回登录成功响应信息
	}

	// 退出登录
	@GetMapping("/logout")
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();  // 使Session无效

		return "redirect:/login.html";
	}

	@ResponseBody
	@GetMapping("/loginInOrNot")
	// 判断是否登录
	public R loginInOrNot(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(Constant.SESSION_USER);    // 得到已经登录的用户

		if (null == user) { // 如果用户没有登录
			return new R(false, "未登录", null);
		}

		return new R(true, "已登录", null);
	}

}
