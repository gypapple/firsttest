package cn.unowen.Service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import cn.unowen.Service.UserService;
import cn.unowen.constant.SystemCon;
import cn.unowen.mapper.UserLogMapper;
import cn.unowen.mapper.UserMapper;
import cn.unowen.pojo.Book;
import cn.unowen.pojo.User;
import cn.unowen.pojo.UserLog;
import vo.PageBean;
import vo.PasswordForm;
import vo.ResultBean;
import vo.ResultBeanUtils;
import vo.SearchBookDate;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper uMapper;
	@Autowired
	UserLogMapper ulMapper;

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

	@Override
	public PageBean getLogByDate(SearchBookDate searchBookDate) {

		int pageNum = SystemCon.pageNum;
		if (searchBookDate.getPageNum() > 0) {
			pageNum = searchBookDate.getPageNum();
		}

		int pageSize = SystemCon.pageSize;
		if (searchBookDate.getPageSize() > 0) {
			pageSize = searchBookDate.getPageSize();
		}

		int offset = (pageNum - 1) * pageSize;

		List<UserLog> userLogs = ulMapper.selectByDate(searchBookDate.getStartDate().toLocaleString(),
				searchBookDate.getEndDate().toLocaleString(), offset, pageSize);
		int count = ulMapper.selectCount();
		List<Object> objects = (List) userLogs;
		return ResultBeanUtils.setPageOK(pageNum, pageSize, count, objects);

	}

	@Override
	public ResultBean updatePwd(HttpSession session, PasswordForm pwdForm) {
		User user = (User) session.getAttribute("user");
		if (user.getPassword().equals(pwdForm.getOldPassword())) {
			int flag = uMapper.updatePasswordByName(user.getName(), pwdForm.getNewePassword());
			if (flag > 0) {
				return ResultBeanUtils.setOK("修改成功", "修改成功");
			}
		}

		return ResultBeanUtils.setError(SystemCon.ERROR6, "修改失败", null);
	}

}
