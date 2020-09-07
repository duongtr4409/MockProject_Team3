/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Book;
import utils.mappers.BookMapper;

/**
 *
 * @author Administrator
 */
public class BookDAOImpl extends UtilsDAO<Book> {

    private static BookDAOImpl bookDAOImpl;

    /*
	 *	tránh việc phải khởi tạo đối tượng quá nhiều lần 
     */
    public static BookDAOImpl getInstance() {
        if (bookDAOImpl == null) {
            bookDAOImpl = new BookDAOImpl();
        }
        return bookDAOImpl;
    }

    /*
	 * lấy danh sách Book tương ứng với BookCase
	 * phục vụ chức năng 'View BookCase'
     */
    public List<Book> findBookOfBookCase(int bookCaseID) {
        StringBuilder sql = new StringBuilder("SELECT b.* ");
        sql.append("FROM dbo.Contain AS c JOIN dbo.tbl_Book AS b ON c.book_id = b.book_id ");
        sql.append("WHERE c.book_case_id = ?");
        List<Book> listBookOfBookCase = query(sql.toString(), new BookMapper(), bookCaseID);
        return listBookOfBookCase;
    }
    
    public Book findByID(int id) {
		StringBuilder sql = new StringBuilder("SELECT * FROM dbo.Book WHERE BookID = ?");
		List<Book> listBook = query(sql.toString(), new BookMapper(), id);
		if (listBook == null || listBook.isEmpty())
			return null;
		else
			return listBook.get(0);
	}
}
