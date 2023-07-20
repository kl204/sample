package bitedu.bipa.book.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.book.service.BlmService3;
import bitedu.bipa.book.vo.BookCopy;

@RestController("restController2")
@RequestMapping("/response")
public class BookController6 {
	
	@Autowired
	private BlmService3 blmService;

	@RequestMapping(value=" ", method=RequestMethod.GET)
	public ResponseEntity<ArrayList<BookCopy>> list() {
		ArrayList<BookCopy> list = blmService.searchBookAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@RequestMapping(value="/{num}", method=RequestMethod.GET)
	public ResponseEntity<Object> viewDetail(@PathVariable("num") String bookSeq) {
		ResponseEntity<Object> entity = null;
		HttpHeaders headers = new HttpHeaders();
		BookCopy copy = blmService.findBook(bookSeq);
		if(copy==null) {
			headers.add("content-Type", "text/html; charset=UTF-8");
			entity = new ResponseEntity("해당 하는 도서가 없습니다.",headers, HttpStatus.NOT_FOUND);
		} else {
			entity = new ResponseEntity(copy, HttpStatus.OK);
		}
		return entity;
	}
	
	@RequestMapping(value="/{num}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("num") String bookSeq) {
		ResponseEntity<String> entity = null;
		boolean flag = blmService.removeBook(bookSeq);
		if(flag) {
			entity = new ResponseEntity("delete complete",null,HttpStatus.OK);
		} else {
			entity = new ResponseEntity("delete fails",HttpStatus.NOT_IMPLEMENTED);
		}
		return entity;
	}
	
	@RequestMapping(value=" ", method=RequestMethod.PUT)
	public ResponseEntity<String> update(@RequestBody BookCopy copy) {
		ResponseEntity<String> entity = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now = df.parse(df.format(copy.getPublishDate()));
			copy.setPublishDate(new Timestamp(now.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = blmService.modifyBook(copy);

		if(flag) {
			entity = new ResponseEntity("update complete",null,HttpStatus.OK);
		} else {
			entity = new ResponseEntity("update fails",HttpStatus.NOT_IMPLEMENTED);
		}
		return entity;
	}
	
	@RequestMapping(value=" ", method=RequestMethod.POST)
	public ResponseEntity<BookCopy> regist(@RequestBody BookCopy copy) {
		ResponseEntity<BookCopy> entity = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now = df.parse(df.format(copy.getPublishDate()));
			copy.setPublishDate(new Timestamp(now.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = blmService.registBook(copy);
		System.out.println("regist");
		if(flag) {
			entity = new ResponseEntity("insert complete",null,HttpStatus.OK);
		} else {
			entity = new ResponseEntity("insert fails",HttpStatus.NOT_IMPLEMENTED);
		}

		return entity;
	}
	
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exceptionHandler(Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("e",e);
		mav.setViewName("./error/exception");
		return mav;
	}
	
}
