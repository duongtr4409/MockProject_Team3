/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;

/**
 *
 * @author tuanc
 */
public class UserServices {

    public static List<Book> searchByOption(String option, String input) {
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
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_Book WHERE " + option + " LIKE N'%" + input + "%'");
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
}
