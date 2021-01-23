package act;

import dao.BookDao;

import javax.swing.*;

public class Borrowers implements borrowersAct{
    BookDao borrowerDao;

    @Override
    public void ReturnBook(JTable table)  {
        borrowerDao=new BookDao();
        Book book=new Book();

        int selRow = table.getSelectedRow();
        int ID = Integer.parseInt(table.getValueAt(selRow, 0).toString());

        book.setID(ID);
        book.setBorrowerName(null);
        book.setBorrowerPhone(null);

        // 归还图书
        borrowerDao.returnBook(book);
    }
}
