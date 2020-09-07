/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Contain;

/**
 *
 * @author Administrator
 */
public class ContainDAOImpl extends UtilsDAO<Contain> {
    
    private static ContainDAOImpl containDAOImpl;
    
    public static ContainDAOImpl getInstance(){
        if(containDAOImpl == null){
            containDAOImpl = new ContainDAOImpl();
        }
        return  containDAOImpl;
    }
    
    /*
	 *	Thêm Contain mới vào trong Database
	 *  phục vụ chức năng 'Edit BookCase' -> 'Add a new book'
     */
    public Integer insert(Contain contain) {
        StringBuilder sql = new StringBuilder("INSERT INTO dbo.Contain VALUES(0, 0,GETDATE())");
        return insert(sql.toString(), contain.getBookCaseID(), contain.getBookID());
    }

    /*
	 * Xóa danh sách Contain dựa trên BookCaseID
	 * phục vụ chức năng 'Edit BookCase' -> 'Clear BookCase'
     */
    public Integer removeByBookCaseID(int bookCaseID) {
        StringBuilder sql = new StringBuilder("DELETE FROM dbo.Contain WHERE book_case_id = ?");
        return removeOrUpdate(sql.toString(), bookCaseID);
    }

    /*
	 * Xóa Contain dựa trên BookCaseID và BookID
	 * phục vụ chức năng 'Edit BookCase' -> 'Remove a book'
     */
    public Integer removeByBookCaeIDBookID(int bookCaseID, int bookID) {
        StringBuilder sql = new StringBuilder("DELETE FROM dbo.Contain WHERE book_case_id = ? AND book_id = ?");
        return removeOrUpdate(sql.toString(), bookCaseID, bookID);
    }
}
