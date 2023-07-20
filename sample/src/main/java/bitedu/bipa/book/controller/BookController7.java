package bitedu.bipa.book.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.book.service.BlmService3;
import bitedu.bipa.book.vo.BookCopy;

@RestController("restController")
@RequestMapping("/restful")
public class BookController7 {
	
	@Autowired
	private BlmService3 blmService;

	@RequestMapping(value=" ", method=RequestMethod.GET)
	public ArrayList<BookCopy> list() {
		ArrayList<BookCopy> list = blmService.searchBookAll();
		return list;
	}
	
	
	@RequestMapping(value="/{num}", method=RequestMethod.GET)
	public BookCopy viewDetail(@PathVariable("num") String bookSeq) {
		BookCopy copy = blmService.findBook(bookSeq);
		return copy;
	}
	
	@RequestMapping(value="/{num}", method=RequestMethod.DELETE)
	public boolean remove(@PathVariable("num") String bookSeq) {

		boolean flag = blmService.removeBook(bookSeq);
		
		return flag;
	}
	
	@RequestMapping(value=" ", method=RequestMethod.PUT)
	public BookCopy update(@RequestBody BookCopy copy) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now = df.parse(df.format(copy.getPublishDate()));
			copy.setPublishDate(new Timestamp(now.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = blmService.modifyBook(copy);

		return copy;
	}
	
	@RequestMapping(value=" ", method=RequestMethod.POST)
	public BookCopy regist(@RequestBody BookCopy copy) {
		System.out.println(copy);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now = df.parse(df.format(copy.getPublishDate()));
			copy.setPublishDate(new Timestamp(now.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		blmService.registBook(copy);
		System.out.println("regist");

		return copy;
	}
	
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exceptionHandler(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("e",e);
		mav.setViewName("./error/exception");
		return mav;
	}
	
}
