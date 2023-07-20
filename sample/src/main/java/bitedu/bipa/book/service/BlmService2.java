package bitedu.bipa.book.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.book.dao.BlmDAO;
import bitedu.bipa.book.dao.BlmDAO2;
import bitedu.bipa.book.vo.BookCopy;

@Service("blmService2")
public class BlmService2 {
	
	@Autowired
	private BlmDAO2 dao;

	public boolean registBook(BookCopy copy) {
		boolean flag = false;
		flag = dao.insertBook(copy);
		return flag;
	}
	
	public ArrayList<BookCopy> searchBookAll(){
		ArrayList<BookCopy> list = null;
		list = dao.selectBookAll();
		return list;
	}
	public boolean removeBook(String bookSeq) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = dao.deleteBook(Integer.parseInt(bookSeq));
		return flag;
	}
	public BookCopy findBook(String bookSeq) {
		BookCopy copy = null;
		copy = dao.selectBook(Integer.parseInt(bookSeq));
		System.out.println(copy);
		return copy;
	}
	public boolean modifyBook(BookCopy copy) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = dao.updateBook(copy);
		return flag;
	}
}







