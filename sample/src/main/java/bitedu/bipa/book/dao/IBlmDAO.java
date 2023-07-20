package bitedu.bipa.book.dao;

import java.util.ArrayList;

import bitedu.bipa.book.utils.PageInfo;
import bitedu.bipa.book.vo.BookCopy;

public interface IBlmDAO {
	
	public boolean insertBook(BookCopy copy);	
	public boolean deleteBook(int parseInt);
	public BookCopy selectBook(int parseInt);
	public boolean updateBook(BookCopy copy);
	ArrayList<BookCopy> selectBookAll();
	ArrayList<BookCopy> searchPagingBook(int start, int end);
}
