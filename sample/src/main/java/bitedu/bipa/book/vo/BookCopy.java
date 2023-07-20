package bitedu.bipa.book.vo;

import java.sql.Timestamp;

public class BookCopy {
	private int bookSeq;
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private Timestamp publishDate;
	private String bookPosition;
	private String bookStaus;
	private String bookImage;
	
	public BookCopy(int bookSeq, String isbn, String title, String author, 
			Timestamp publishDate,String bookPosition, String bookStaus) {
		super();
		this.bookSeq = bookSeq;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.bookPosition = bookPosition;
		this.bookStaus = bookStaus;
	}
	public BookCopy() {}
	public int getBookSeq() {
		return bookSeq;
	}
	public void setBookSeq(int bookSeq) {
		this.bookSeq = bookSeq;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Timestamp getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}
	public String getBookPosition() {
		return bookPosition;
	}
	public void setBookPosition(String bookPosition) {
		this.bookPosition = bookPosition;
	}
	public String getBookStaus() {
		return bookStaus;
	}
	public void setBookStaus(String bookStaus) {
		this.bookStaus = bookStaus;
	}
	
	
	public String getBookImage() {
		return bookImage;
	}
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	
	@Override
	public String toString() {
		return "BookCopy [bookSeq=" + bookSeq + ", isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", publisher=" + publisher + ", publishDate=" + publishDate + ", bookPosition=" + bookPosition
				+ ", bookStaus=" + bookStaus + ", bookImage=" + bookImage + "]";
	}
	
	
	
}
