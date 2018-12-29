package cn.unowen.Service.impl;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.unowen.Service.UserService;
import cn.unowen.constant.SystemCon;
import cn.unowen.mapper.UserMapper;
import cn.unowen.pojo.User;
import vo.ResultBean;
import vo.ResultBeanUtils;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper uMapper;

	@Override
	public ResultBean login(User user, HttpSession session, HttpServletResponse response) {
		User resultUser = uMapper.selectByNameAndPwd(user);
		if (null == resultUser) {
			// 账号密码不正确
			uMapper.errorCount(user.getName(), 1);
			int times = uMapper.selectByName(user.getName()).getTimes();
			if (times == SystemCon.errorTimes - 1) {
				uMapper.lockNow(user.getName());
			}
			if (times >= SystemCon.errorCount - 1) {
				return ResultBeanUtils.setError(SystemCon.ERROR5, "账号或者密码不正确", null);
			}
			return ResultBeanUtils.setError(SystemCon.RERROR3, "账号或者密码不正确", null);
		} else {
			if (resultUser.getLockflag() == -1) {
				// -1账号被锁定
				if (uMapper.lockedTime(user.getName()) >= (SystemCon.lockHour * 3600)) {
					// 解锁
					uMapper.lockOrNo(user.getName(), 1);
				}
				// 登录成功
				session.setAttribute("user", resultUser);
				return ResultBeanUtils.setOK("登录成功", "登录成功");
			} else {
				return ResultBeanUtils.setError(SystemCon.RERROR4, "账号被锁定", null);
			}
		}
	}

}
