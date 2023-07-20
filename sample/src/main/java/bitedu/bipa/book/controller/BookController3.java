package bitedu.bipa.book.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.book.service.BlmService;
import bitedu.bipa.book.service.QuizService;
import bitedu.bipa.book.vo.BookCopy;

@Controller("bookController3")
@RequestMapping("db")
public class BookController3 {
	
	@Autowired
	private BlmService blmService;

	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public ModelAndView list() {
		System.out.println("extends");
		ModelAndView mav = new ModelAndView();
		ArrayList<BookCopy> list = blmService.searchBookAll();
		mav.addObject("list",list);
		mav.setViewName("./manager/book_list");
		return mav;
	}
	
	@RequestMapping(value="/view_regist.do", method=RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();
		
		
		mav.setViewName("./manager/book_regist");
		return mav;
	}
	
	@RequestMapping(value="/view_detail.do", method=RequestMethod.GET)
	public ModelAndView viewDetail(@RequestParam("bookSeq") String bookSeq) {
		ModelAndView mav = new ModelAndView();
		BookCopy copy = blmService.findBook(bookSeq);
		mav.addObject("copy",copy);
		mav.setViewName("./manager/book_detail");
		return mav;
	}
	
	@RequestMapping(value="/view_update.do", method=RequestMethod.POST)
	public ModelAndView viewUpdate(@RequestParam("bookSeq") String bookSeq) {
		ModelAndView mav = new ModelAndView();
		BookCopy copy = blmService.findBook(bookSeq);
		mav.addObject("copy",copy);
		mav.setViewName("./manager/book_update");
		return mav;
	}
	
	@RequestMapping(value="/remove.do", method=RequestMethod.GET)
	public ModelAndView remove(@RequestParam("bookSeq") String bookSeq) {
		ModelAndView mav = new ModelAndView();
		boolean flag = blmService.removeBook(bookSeq);
		
		mav.setViewName("redirect:list.do");
		return mav;
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("book") BookCopy copy) {
		ModelAndView mav = new ModelAndView();
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			Date now = df.parse(date);
//			copy.setPublishDate(new Timestamp(now.getTime()));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		boolean flag = blmService.modifyBook(copy);
		System.out.println("regist");
		mav.setViewName("redirect:list.do");
		return mav;
	}
	
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute("book") BookCopy copy) {
		ModelAndView mav = new ModelAndView();
		System.out.println(copy);
		blmService.registBook(copy);
		System.out.println("regist");
		mav.setViewName("redirect:list.do");
		return mav;
	}
	
	
	
	
}
