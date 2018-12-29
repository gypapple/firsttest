package cn.unowen.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.unowen.pojo.User;
import vo.ResultBean;

public interface UserService {
	public ResultBean login(User user,HttpSession session,HttpServletResponse response);
}
