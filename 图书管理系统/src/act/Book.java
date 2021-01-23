package act;

/**
 * 数据库图书表各对应的字段getter、setter方法
 */
public class Book {
	private int ID;

	private String bookName;

	private String author;

	private String borrowerName;

	private String borrowerPhone;

	public int getID() {
		return ID;
	}

	public void setID(int iD) { ID = iD; }

	public String getBookName() { return bookName; }

	public void setBookName(String bookName) { this.bookName = bookName; }

	public String getAuthor() { return author; }

	public void setAuthor(String author) { this.author = author; }

	public String getBorrowerName() { return borrowerName; }

	public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }

	public String getBorrowerPhone() { return borrowerPhone; }

	public void setBorrowerPhone(String borrowerPhone) { this.borrowerPhone = borrowerPhone; }

	public Book() {
	}

	public Book(int ID, String bookName, String author) {
		this.ID = ID;
		this.bookName = bookName;
		this.author = author;
	}

	public Book(String bookName, String author, String borrowerName, String borrowerPhone) {
		this.bookName = bookName;
		this.author = author;
		this.borrowerName = borrowerName;
		this.borrowerPhone = borrowerPhone;
	}

	public Book(String bookName, String author, String borrowerName, String borrowerPhone,int ID) {
		this.bookName = bookName;
		this.author = author;
		this.borrowerName = borrowerName;
		this.borrowerPhone = borrowerPhone;
		this.ID = ID;
	}

	@Override
	public String toString() {
		return "Book{" +
				"ID=" + ID +
				", bookName='" + bookName + '\'' +
				", author='" + author + '\'' +
				", borrowerName='" + borrowerName + '\'' +
				", borrowerPhone='" + borrowerPhone + '\'' +
				'}';
	}


}
