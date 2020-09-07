/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;
import static services.CommonServices.getConnection;

/**
 *
 * @author tuanc
 */
public class BookServices {

    public static List<Book> getAllBook() {
        List<Book> dataBook = null;
        int id;
        String bookTitle;
        String author;
        String brief;
        String publisher;
        String category;
        Connection conn = CommonServices.getConnection();

        Statement st = null;

        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_Book");
            // create dataBook
            dataBook = new ArrayList<>();
            Book book = new Book();
            while (rs.next()) {
                id = rs.getInt("book_id");
                bookTitle = rs.getString("bookTitle");
                author = rs.getString("author");
                brief = rs.getString("brief");
                publisher = rs.getString("publisher");
                category = rs.getString("category");
                book = new Book(id, bookTitle, author, brief, publisher, category);
                dataBook.add(book);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommonServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataBook;

    }

    public static Book getABook(int book_id) throws SQLException {
        Book book = null;
        String bookTitle;
        String author;
        String brief;
        String publisher;
        String category;
        String content;
        Connection conn = getConnection();

        try {
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM tbl_Book WHERE book_id = " + book_id;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                book_id = rs.getInt("book_id");
                bookTitle = rs.getString("bookTitle");
                author = rs.getString("author");
                brief = rs.getString("brief");
                publisher = rs.getString("publisher");
                category = rs.getString("category");
                content = rs.getString("content");
                book = new Book(book_id, bookTitle, author, brief, publisher, content, category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommonServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return book;

    }

    public static String readABook(int id) {
        String content = "";
        Connection conn = CommonServices.getConnection();

        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT content FROM tbl_Book WHERE book_id=" + id + ";");
            while (rs.next()) {
                content = rs.getString("content");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommonServices.class.getName()).log(Level.SEVERE, null, ex);
            return content;
        }
        return content;
    }
}
