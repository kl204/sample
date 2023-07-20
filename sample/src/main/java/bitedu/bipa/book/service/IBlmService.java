package bitedu.bipa.book.service;

import java.util.ArrayList;

import bitedu.bipa.book.vo.BookCopy;

public interface IBlmService {
	
	public boolean registBook(BookCopy copy);
	
	public ArrayList<BookCopy> searchBookAll();
	public boolean removeBook(String bookSeq); 
	public BookCopy findBook(String bookSeq); 
	public boolean modifyBook(BookCopy copy);
	
}
