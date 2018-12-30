package cn.unowen.bookReg;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.unowen.Service.RegService;
import cn.unowen.pojo.Book;
import cn.unowen.vo.ResultBean;

@RestController
@RequestMapping("/api/v1/book")
public class BookRegController {
	@Autowired
	RegService regService;

	@RequestMapping("/reg")
	public ResultBean registBook(@Valid Book book) {
		return regService.registBook(book);

	}

}
