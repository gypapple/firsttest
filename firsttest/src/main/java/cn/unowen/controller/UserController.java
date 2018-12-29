package cn.unowen.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.unowen.Service.UserService;
import cn.unowen.pojo.User;
import vo.PageBean;
import vo.PasswordForm;
import vo.ResultBean;
import vo.SearchBookDate;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserService uservice;

	//2.1后台管理员登录页面
	//页面输入：登录名、密码
	//操作按钮：登录；
	//要求：
	//1）每次登录记录日志，无论是否登录成功或者失败；
	//2）登录失败次数，超过2次，就要出验证码验证登录；
	//3）总计登录失败超过5次，则账户只能2个小时后才能继续登录；
	//4）登录限制的参数信息，要求写成配置文件。可动态修改登录限制；
	@PostMapping("/login")
	public ResultBean login(User user, HttpSession session, HttpServletResponse response) {
		return uservice.login(user, session, response);
	}

	// 2.5查看登录日志
	// 查询条件：操作开始时间、操作结束时间；
	// 查询结果：数据结构定义的数据，全部展示；
	@PostMapping("/searchUserLog/byDate")
	public PageBean userLogByDate(@Valid SearchBookDate searchBookDate) {

		return uservice.getLogByDate(searchBookDate);
	}

	// 2.6修改密码
	// 简述：修改管理员账户密码；
	public ResultBean updatePWD(@Valid PasswordForm pwdForm, HttpSession session) {
		return uservice.updatePwd(session, pwdForm);

	}
}
