package cn.unowen.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.unowen.Service.SearchRegisteredBooksService;
import cn.unowen.constant.SystemCon;
import cn.unowen.mapper.BookMapper;
import cn.unowen.pojo.Book;
import vo.PageBean;
import vo.ResultBean;
import vo.ResultBeanUtils;
import vo.SearchBookDate;

@Service
public class SearchRegisteredBooksServiceImpl implements SearchRegisteredBooksService {
	@Autowired
	BookMapper bkmapper;

	@Override
	public ResultBean selectByBookNumber(String bookNumber) {
		return ResultBeanUtils.setOK("查询成功", bkmapper.selectBybookNumber(bookNumber));
	}

	@Override
	public PageBean selectByDate(SearchBookDate searchBookDate) {
		int pageNum = SystemCon.pageNum;
		if (searchBookDate.getPageNum() > 0) {
			pageNum = searchBookDate.getPageNum();
		}

		int pageSize = SystemCon.pageSize;
		if (searchBookDate.getPageSize() > 0) {
			pageSize = searchBookDate.getPageSize();
		}

		int offset = (pageNum - 1) * pageSize;

		List<Book> booklist = bkmapper.selectByDate(searchBookDate.getStartDate().toLocaleString(),
				searchBookDate.getEndDate().toLocaleString(), offset, pageSize);
		int count = bkmapper.selectCount();
		List<Object> objects = (List) booklist;
		return ResultBeanUtils.setPageOK(pageNum, pageSize, count, objects);

	}
}
