package cn.unowen.Service;

import vo.PageBean;
import vo.ResultBean;
import vo.SearchBookDate;

public interface SearchRegisteredBooksService {
	ResultBean selectByBookNumber(String bookNumber);

	PageBean selectByDate(SearchBookDate searchBookDate);
}
