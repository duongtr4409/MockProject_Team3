/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.User;
import services.CommonServices;

/**
 *
 * @author tuanc
 */
public class Main {

    public static void main(String[] args) {
        CommonServices.getConnection();
        List<User> dataUser = CommonServices.getAllUser();

//        for (int i = 0; i < dataUser.size(); i++) {
//            System.out.println(dataUser.get(i).getUserName() + " " + dataUser.get(i).getPassword());
//
//        }
//
//        List<Book> dataBook = BookServices.getAllBook();
//
//        for (int i = 0; i < dataBook.size(); i++) {
//            System.out.println(
//                    dataBook.get(i).getId() + " "
//                    + dataBook.get(i).getBookTitle() + " "
//                    + dataBook.get(i).getAuthor() + " "
//                    + dataBook.get(i).getBrief() + " "
//                    + dataBook.get(i).getPublisher() + " "
//                    + dataBook.get(i).getCategory() + " "
//            );
//
//        }
//        System.out.println(BookServices.readABook(1));
    }
}
