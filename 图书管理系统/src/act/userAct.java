package act;

import javax.swing.*;

public interface userAct {
    /**
     * 增加图书
     */
    public void addBookInformation(JTextField textFieldName
            , JTextField textFieldAuthor
            , JTextField textFieldBorrowName
            , JTextField textFieldBorrowPhone);

    /**
     * 删除图书
     *
     * @param table
     */
    public void delBookInformation(JTable table);

    /**
     * 修改图书信息
     */
    public void changeBookInformation(JTextField textFieldName
            , JTextField textFieldAuthor
            , JTextField textFieldBorrowerName
            , JTextField textFieldBorrowerPhone, JTable table);

    /**
     * 修改用户名和密码
     * @param username
     * @param password
     */
    public void edit(JTextField username, JTextField password);
}