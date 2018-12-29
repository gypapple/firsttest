package cn.unowen.Service;

import cn.unowen.vo.PageBean;
import cn.unowen.vo.ResultBean;
import cn.unowen.vo.SearchBookDate;

public interface SearchRegisteredBooksService {
	ResultBean selectByBookNumber(String bookNumber);

	PageBean selectByDate(SearchBookDate searchBookDate);

	PageBean selectLogByDate(SearchBookDate searchBookDate);
}
