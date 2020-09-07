/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class AdminServices {

    

    public static void createBook(String bookTitle, String author, String brief, String publisher, String content, String category) throws SQLException {
        try (Connection connection = CommonServices.getConnection()) {
            String sql = null;
            sql = "INSERT INTO dbo.tbl_Book(bookTitle , author, brief, publisher, content, category) "
                    + "VALUES(N'" + bookTitle + "', N'" + author + "', N'" + brief + "', N'" + publisher + "', N'" + content + "', N'" + category + "')";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
        }
    }

    

    public static void deleteBook(int book_id) throws SQLException {
        if (!CommonServices.checkBook(book_id)) {
            JOptionPane.showMessageDialog(null, "This book is not exist!");
        } else {
            Connection connection = CommonServices.getConnection();
            String sql = null;
            sql = "DELETE FROM dbo.tbl_Book WHERE book_id = " + book_id;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
        }
    }

    public static void updateContentBook(int book_id, String content) throws SQLException {
        if (!CommonServices.checkBook(book_id)) {
            JOptionPane.showMessageDialog(null, "This book is not exist!");
        } else {
            Connection connection = CommonServices.getConnection();
            String sql = null;
            sql = "UPDATE dbo.tbl_Book SET content = N'" + content + "' WHERE book_id = " + book_id;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
        }
    }
}
