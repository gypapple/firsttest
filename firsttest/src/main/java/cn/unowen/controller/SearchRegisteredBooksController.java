package cn.unowen.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.unowen.Service.SearchRegisteredBooksService;
import vo.PageBean;
import vo.ResultBean;
import vo.SearchBookDate;

@RestController
@RequestMapping("/api/v1/searchRegBook")
public class SearchRegisteredBooksController {
	@Autowired
	SearchRegisteredBooksService searchRegisteredBooksService;

	@GetMapping("/byNum")
	public ResultBean regedBookByNum(String bookNumber) {
		return searchRegisteredBooksService.selectByBookNumber(bookNumber);
	}

	@PostMapping("/byDate")
	public PageBean regedBookByDate(@Valid SearchBookDate searchBookDate) {

		return searchRegisteredBooksService.selectByDate(searchBookDate);
	}

}
