package bitedu.bipa.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import bitedu.bipa.book.utils.ConnectionManager;
import bitedu.bipa.book.vo.BookCopy;

@Repository("blmDAO")
public class BlmDAO {
	private ConnectionManager manager;
	
	public BlmDAO() {
		manager = ConnectionManager.getInstance();
	}
	public boolean insertBook(BookCopy copy){
		boolean flag = false;
		String sql1 = "insert into book_info values (?,?,?,?)";
		String sql2 = "insert into book_copy(book_isbn) values (?)";
		Connection con = manager.getConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, copy.getIsbn());
			pstmt.setString(2, copy.getTitle());
			pstmt.setString(3, copy.getAuthor());
			pstmt.setTimestamp(4, copy.getPublishDate());
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, copy.getIsbn());
				affectedCount = pstmt.executeUpdate();
				if(affectedCount>0) {
					flag = true;
					con.commit();
					System.out.println("commit");
				}
			} else {
				con.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
				System.out.println("rollback");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				con.setAutoCommit(true);
				manager.closeConnection(null, null, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return flag;
	}
	
	public ArrayList<BookCopy> selectBookAll(){
		ArrayList<BookCopy> list = null;
		list = new ArrayList<BookCopy>();
		BookCopy copy = null;
		StringBuilder sb = new StringBuilder("select a.*, b.* from book_info a ");
		sb.append("inner join book_copy b on a.book_isbn=b.book_isbn");
		String sql = sb.toString();
		Connection con = manager.getConnection();
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				copy = new BookCopy();
				copy.setIsbn(rs.getString(1));
				copy.setTitle(rs.getString(2));
				copy.setAuthor(rs.getString(3));
				copy.setPublishDate(rs.getTimestamp(4));
				copy.setBookSeq(rs.getInt(5));
				copy.setBookPosition(rs.getString(6));
				copy.setBookStaus(rs.getString(7));
				list.add(copy);
			}
			manager.closeConnection(rs, pstmt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	public boolean deleteBook(int parseInt) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "delete from book_copy where book_seq = ?";
		Connection con = manager.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, parseInt);
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}
			manager.closeConnection(null, pstmt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	public BookCopy selectBook(int parseInt) {
		// TODO Auto-generated method stub
		BookCopy copy = null;
		StringBuilder sb = new StringBuilder("select a.*, b.* from book_info a ");
		sb.append("inner join book_copy b on a.book_isbn=b.book_isbn ");
		sb.append("where b.book_seq = ?");
		String sql = sb.toString();
		Connection con = manager.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, parseInt);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				copy = new BookCopy();
				copy.setIsbn(rs.getString(1));
				copy.setTitle(rs.getString(2));
				copy.setAuthor(rs.getString(3));
				copy.setPublishDate(rs.getTimestamp(4));
				copy.setBookSeq(rs.getInt(5));
				copy.setBookPosition(rs.getString(6));
				copy.setBookStaus(rs.getString(7));
			}
			manager.closeConnection(rs, pstmt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return copy;
	}
	public boolean updateBook(BookCopy copy) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "update book_info set book_title = ?, book_author=?, book_published_date = ? where book_isbn = ?";
		Connection con = manager.getConnection();
		PreparedStatement pstmt = null;
		System.out.println(copy);
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, copy.getTitle());
			pstmt.setString(2, copy.getAuthor());
			pstmt.setTimestamp(3, copy.getPublishDate());
			//pstmt.setInt(4, copy.getBookSeq());
			pstmt.setString(4, copy.getIsbn());
			int affectedCount = pstmt.executeUpdate();
			if(affectedCount>0) {
				flag = true;
			}
			manager.closeConnection(null, pstmt, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
}







