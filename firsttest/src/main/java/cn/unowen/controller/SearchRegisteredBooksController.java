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
@RequestMapping("/api/v1")
public class SearchRegisteredBooksController {
	@Autowired
	SearchRegisteredBooksService searchRegisteredBooksService;

	// 2.2-1查询注册图书明细：图书编号
	// 查询条件：图书编号、注册开始时间、注册结束时间；
	// 查询结果：数据结构定义的数据，全部展示；
	@GetMapping("/searchRegBook/byNum")
	public ResultBean regedBookByNum(String bookNumber) {
		return searchRegisteredBooksService.selectByBookNumber(bookNumber);
	}
	// 2.2-1查询注册图书明细：注册开始时间、注册结束时间

	// 2.3统计注册图书数量
	// 统计条件：注册开始时间、注册结束时间；
	// 统计结果：图书数量
	// pagebean中有count
	@PostMapping("/searchRegBook/byDate")
	public PageBean regedBookByDate(@Valid SearchBookDate searchBookDate) {
		return searchRegisteredBooksService.selectByDate(searchBookDate);
	}

	// 2.4查询注册日志信息
	// 查询条件：注册操作开始时间、注册操作结束时间；
	// 查询结果：数据结构定义的数据，全部展示；
	@PostMapping("/searchRegBookLog/byDate")
	public PageBean regedBookLogByDate(@Valid SearchBookDate searchBookDate) {
		return searchRegisteredBooksService.selectLogByDate(searchBookDate);
	}

	
}
