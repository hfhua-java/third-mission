package act;

import dao.BookDao;
import dao.UserDao;

import javax.swing.*;

/**
 * 管理员权限
 */
public class User implements userAct{
	
	private int ID;

	private String userName;

	private String password;

	public int getID() {
		return ID;
	}

	public void setID(int iD) { ID = iD; }

	public String getUser() {
		return userName;
	}

	public void setUser(String userName) {
		this.userName = userName;
	}

	public String getPasswd() { return password; }

	public void setPasswd(String passwd) { this.password = passwd; }

	/**
	 * 增加图书
	 * @param textFieldName
	 * @param textFieldAuthor
	 * @param textFieldBorrowName
	 * @param textFieldBorrowPhone
	 */
	@Override
	public void addBookInformation(JTextField textFieldName
			, JTextField textFieldAuthor
			, JTextField textFieldBorrowName
			, JTextField textFieldBorrowPhone) {
		BookDao bookDao=new BookDao();
		Book book=new Book();


		book.setBookName(textFieldName.getText());

		book.setAuthor(textFieldAuthor.getText());

		if (textFieldBorrowName.getText() == null ||textFieldBorrowName.getText() == "" ) {
			book.setBorrowerName(null);
		}
		else {
			book.setBorrowerName(textFieldBorrowName.getText());
		}

		if (textFieldBorrowPhone.getText() == null || textFieldBorrowPhone.getText() == "") {
			book.setBorrowerPhone(null);
		}
		else {
			book.setBorrowerPhone(textFieldBorrowPhone.getText());
		}

		//添加图书
		bookDao.addBook(book);
	}

	/**
	 * 删除图书
	 * @param table
	 */
	@Override
	public void delBookInformation(JTable table) {
		int selRow = table.getSelectedRow();
		int ID = Integer.parseInt(table.getValueAt(selRow, 0).toString());

		BookDao bookDao=new BookDao();
		Book book=new Book();

		book.setID(ID);

		// 删除图书信息
		bookDao.delBook(ID);

	}

	/**
	 * 修改图书信息
	 * @param textFieldName
	 * @param textFieldAuthor
	 * @param textFieldBorrowerName
	 * @param textFieldBorrowerPhone
	 * @param table
	 */
	@Override
	public void changeBookInformation(JTextField textFieldName
			, JTextField textFieldAuthor
			, JTextField textFieldBorrowerName
			, JTextField textFieldBorrowerPhone, JTable table) {
		BookDao bookDao=new BookDao();
		Book book=new Book();

		int selRow = table.getSelectedRow();
		int ID = Integer.parseInt(table.getValueAt(selRow, 0).toString());

		book.setID(ID);
		book.setBookName(textFieldName.getText());
		book.setAuthor(textFieldAuthor.getText());
		book.setBorrowerName(textFieldBorrowerName.getText());
		book.setBorrowerPhone(textFieldBorrowerPhone.getText());

		//修改图书
		bookDao.modifyBook(book);
	}

	/**
	 * 修改用户名和密码
	 * @param username
	 * @param password
	 */
	@Override
	public void edit(JTextField username,JTextField password) {
		User user = new User();
		UserDao userdao=new UserDao();
		userdao.updateUser(user,username.getText(),password.getText());
	}


}
