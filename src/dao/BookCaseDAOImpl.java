/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.BookCase;
import model.User;
import utils.mappers.BookCaseMapper;

/**
 *
 * @author Administrator
 */
public class BookCaseDAOImpl extends UtilsDAO<BookCase> {

    private static BookCaseDAOImpl bookCaseDAOImpl;

    public static BookCaseDAOImpl getInstance() {
        if (bookCaseDAOImpl == null) {
            bookCaseDAOImpl = new BookCaseDAOImpl();
        }
        return bookCaseDAOImpl;
    }

    /* 
	 *	lấy BookCase tương ứng với User
     */
    public BookCase findOne(int id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM dbo.BookCase WHERE book_case_id = ?");
        List<BookCase> listBookCase = query(sql.toString(), new BookCaseMapper(), id);
        if (listBookCase == null || listBookCase.isEmpty()) {
            return null;
        } else {
            BookCase bookCase = listBookCase.get(0);
            // Lấy danh sách Book tương ứng trong BookCase
            bookCase.setListBook(new BookDAOImpl().findBookOfBookCase(id));
            return bookCase;
        }
    }

    /*
	 *	thêm BookCase mới vào Database
     */
    public Integer insert(User user) {
        String bookCaseName = "BookCase of " + user.getUserName();
        StringBuilder sql = new StringBuilder("INSERT INTO dbo.BookCase(book_case_id, book_case_name) VALUES(?, ?)");
        return insert(sql.toString(), user.getId(), bookCaseName);
    }
}
