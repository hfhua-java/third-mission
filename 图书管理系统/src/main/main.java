package main;

import act.Book;
import dao.BookDao;

/**
 * 本来想再学习搞管理员和用户的不同权限，但是之前考试加军训，几乎是19号才开始写的，真的做不来，尽力了，手下留情（感谢西二一次又一次拉高我熬夜的极限）。
 * 然后package login是面向百度编程+自己理解修改的
 * package act是半途而废不舍得删
 * 基本的功能应该算是完成了吧
 * 然后sql表是经过部分测试后得到的，不是原来的
 */
public class main {
    public static void main(String[] args) {
        /**
         * 增加一本书，搞了好久才成功嘿嘿
         */
        Book book = new Book("《战国策》","刘向",null,null);
        BookDao a = new BookDao();
        a.addBook(book);

        /**
         * 删掉一本书
         */
        a.delBook(1);

        /**
         * 更新图书信息
         */
        Book book1 = new Book("《钢铁是怎样炼成的》","【苏】奥斯特洛夫斯基","hfhua","123456",2);
        a.modifyBook(book1);

        /**
         *查询图书信息,试一下,就不写循环了(能看到出借状态）
         */
        System.out.println(a.findBookList().get(2).toString());
    }
}
