package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import act.Book;
import util.DbUtil;

/**
 * 增加图书信息、删除图书信息、更新图书信息、查询图书信息、查询借阅信息和归还图书
 */
public class BookDao {


	/**
	 * 增加图书信息
	 */
	public void addBook(Book book){
		String sql="insert into books"
				// 书名、图书作者、借书人姓名、借书人电话
				+ "(book_name,book_author, borrower_name,borrower_phone)"
				// 借书日期，已借天数   ?作为占位符
				+ "values(?,?,?,?)";
		Connection conn = null;				//和数据库取得连接
		PreparedStatement pstmt = null;
		try{
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getAuthor());

			if (book.getBorrowerName() == null || book.getBorrowerName() == "") {
				pstmt.setString(3, null);
			}
			else {
				pstmt.setString(3, book.getBorrowerName());
			}

			if (book.getBorrowerPhone() == null || book.getBorrowerPhone() == "") {
				pstmt.setString(4, null);
			}
			else {
				pstmt.setString(4, book.getBorrowerPhone());
			}

			pstmt.executeUpdate();			//执行
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			DbUtil.close((com.mysql.jdbc.PreparedStatement) pstmt);
			DbUtil.close(conn);		//必须关闭
		}
	}
	
  
    /**
     * 删除图书信息
     */
	public void delBook(int ID){
		String sql="" +
				"DELETE FROM books "+
				//?作为占位符
				"WHERE ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,ID);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DbUtil.close((com.mysql.jdbc.PreparedStatement) pstmt);
			DbUtil.close(conn);		//必须关闭
		}
	}
	    

	/**
	 * 更新图书信息
	 */
	public void modifyBook(Book book){
		String sql="update books "
				+ "set book_name = ?, book_author = ?, "
				+ "borrower_name = ?, borrower_phone = ? "
				+ "where ID = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);

			// 先对应SQL语句，给SQL语句传递参数
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getAuthor());

			if (book.getBorrowerName().equals("")) {
				pstmt.setString(3, null);
			}
			else {
				pstmt.setString(3, book.getBorrowerName());
			}

			if (book.getBorrowerPhone().equals("")) {
				pstmt.setString(4, null);
			}
			else {
				pstmt.setString(4, book.getBorrowerPhone());
			}

			pstmt.setInt(5, book.getID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DbUtil.close((com.mysql.jdbc.PreparedStatement) pstmt);
			DbUtil.close(conn);		//必须关闭
		}
	}
	        

	/**
	 * 查询书籍信息
	 */
	public List<Book> findBookList(){
		String sql = "select "
				// 书名、作者
				+ "ID, book_name, book_author, "
				// 借书人姓名、借书人电话
				+ "borrower_name, borrower_phone "
				+ "from books";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		//创建一个集合对象用来存放查询到的数据
		List<Book> bookList = new ArrayList<>();
		Book book;
		try {
			conn = DbUtil.getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = (ResultSet) pstmt.executeQuery();
			while (rs.next()){
				book = new Book();
				book.setID(rs.getInt("ID"));
				book.setBookName(rs.getString("book_name"));
				book.setAuthor(rs.getString("book_author"));
				book.setBorrowerName(rs.getString("borrower_name"));
				book.setBorrowerPhone(rs.getString("borrower_phone"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DbUtil.close((com.mysql.jdbc.PreparedStatement) pstmt);
			DbUtil.close(conn);		//必须关闭
		}
		return bookList;
	}


	/**
	 * 查询借阅信息
	 * 
	 * @return
	 * 		bookList
	 */
	public List<Book> findBorrowers(){
		String sql = ""
				// ID、书名、借书人姓名、借书人电话
				+ "SELECT ID, book_name, borrower_name, borrower_phone "
				+ "FROM books "
				+ "WHERE borrower_name IS NOT NULL";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		//创建一个集合对象用来存放查询到的数据
		List<Book> bookList = new ArrayList<>();
		Book book;
		try {
			conn = DbUtil.getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			rs = (ResultSet) pstmt.executeQuery();

			while (rs.next()){
				book = new Book();
				book.setID(rs.getInt("ID"));
				book.setBookName(rs.getString("book_name"));
				book.setBorrowerName(rs.getString("borrower_name"));
				book.setBorrowerPhone(rs.getString("borrower_phone"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DbUtil.close((com.mysql.jdbc.PreparedStatement) pstmt);
			DbUtil.close(conn);		//必须关闭
		}
		return bookList;
	}


	/**
	 * 更新图书信息，归还图书
	 */
	public void returnBook(Book book){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="UPDATE books "
				// 图书名称、作者
				+ "SET "
				// 借书人姓名、借书人电话
				+ "borrower_name = ?, borrower_phone = ? "
				// 参数用?表示，相当于占位符
				+ "WHERE ID = ?";
		try {
			conn= DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBorrowerName());
			pstmt.setString(2, book.getBorrowerPhone());
			pstmt.setInt(3, book.getID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DbUtil.close((com.mysql.jdbc.PreparedStatement) pstmt);
			DbUtil.close(conn);		//必须关闭
		}
	}

}
