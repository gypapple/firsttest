package cn.unowen.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.unowen.Service.UserService;
import cn.unowen.pojo.User;
import vo.ResultBean;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserService uservice;

	@PostMapping("/login")
	public ResultBean login(User user,HttpSession session,HttpServletResponse response) {
		return uservice.login(user,session,response);
	}
}
