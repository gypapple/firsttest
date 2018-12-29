package cn.unowen.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hutool.db.Page;
import cn.unowen.pojo.User;
import cn.unowen.vo.PageBean;
import cn.unowen.vo.PasswordForm;
import cn.unowen.vo.ResultBean;
import cn.unowen.vo.SearchBookDate;

public interface UserService {
	public ResultBean login(User user, HttpSession session, HttpServletResponse response);

	public PageBean getLogByDate(SearchBookDate searchBookDate);
	
	public ResultBean updatePwd(HttpSession session, PasswordForm pwdForm);
	
	
}
