package cn.unowen.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.unowen.Service.UserService;
import cn.unowen.pojo.User;
import cn.unowen.vo.PageBean;
import cn.unowen.vo.PasswordForm;
import cn.unowen.vo.ResultBean;
import cn.unowen.vo.SearchBookDate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/user")
@Api(tags = "用户类信息查询")
@CrossOrigin(allowCredentials = "true")
public class UserController {
	@Autowired
	UserService uservice;

	// 2.1后台管理员登录页面
	// 页面输入：登录名、密码
	// 操作按钮：登录；
	// 要求：
	// 1）每次登录记录日志，无论是否登录成功或者失败；
	// 2）登录失败次数，超过2次，就要出验证码验证登录；
	// 3）总计登录失败超过5次，则账户只能2个小时后才能继续登录；
	// 4）登录限制的参数信息，要求写成配置文件。可动态修改登录限制；
	@PostMapping("/login")
	@ApiOperation(value = "使用用户名密码登录", responseContainer = "ResultBean.class")
	@ApiImplicitParams({ @ApiImplicitParam(name = "name", value = "用户名", required = true),
			@ApiImplicitParam(name = "password", value = "密码", required = true), })
	public ResultBean login(User user, HttpSession session, HttpServletResponse response)
			throws UnsupportedEncodingException {
		return uservice.login(user, session, response);
	}

	// 2.5查看登录日志
	// 查询条件：操作开始时间、操作结束时间；
	// 查询结果：数据结构定义的数据，全部展示；
	// 查询结果：数据结构定义的数据，全部展示；
	@ApiOperation(value = "根据注册开始时间、注册结束时间查看用户的登录日志信息", notes = "通过开始日期和结束日期,返回一个带有分页信息的列表，pageCount代表总数量", responseContainer = "PageBean.class")
	@ApiImplicitParams({ @ApiImplicitParam(name = "startDate", value = "开始时间", required = true, paramType = "query"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", required = true),
			@ApiImplicitParam(name = "pageNum", value = "需要查询的页码", required = false),
			@ApiImplicitParam(name = "pageSize", value = "每页展示的数量", required = false) })
	@PostMapping("/searchUserLog/byDate")
	public PageBean userLogByDateWithPage(@Valid SearchBookDate searchBookDate) {

		return uservice.getLogByDate(searchBookDate);
	}

	// 2.6修改密码
	// 简述：修改管理员账户密码；
	// 查询结果：数据结构定义的数据，全部展示；
	@ApiOperation(value = "根据旧密码修改新密码", notes = "根据用户名修改，用户名从session获取，无需前端传递", responseContainer = "ResultBean.class")
	@ApiImplicitParams({ @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, paramType = "query"),
			@ApiImplicitParam(name = "newePassword", value = "新密码", required = true), })
	@PostMapping("/updatePassword")
	public ResultBean updatePWD(@Valid PasswordForm pwdForm, HttpSession session) {
		return uservice.updatePwd(session, pwdForm);

	}
}
