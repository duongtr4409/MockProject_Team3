/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import utils.Const;

/**
 *
 * @author tuanc
 */
public class CommonServices {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(Const.DB_URL, Const.USER_NAME, Const.PASSWORD);
            System.out.println("connect successfully!");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("connect failure!");
        }
        return conn;
    }

    public static List<User> getAllUser() {
        List<User> dataUser = null;
        Connection conn = getConnection();

        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_User");
            // create dataUser
            dataUser = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                boolean authority = rs.getBoolean("isAdmin");
                User user = new User(id, userName, password, authority);
                dataUser.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommonServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataUser;
    }

    public static int checkLogin(String userName, String password) {
        List<User> dataUser = null;
        try {
            dataUser = CommonServices.getAllUser();
            int len = dataUser.size();
            for (int i = 0; i < len; i++) {
                if (userName.equalsIgnoreCase(dataUser.get(i).getUserName())) {
                    if (password.equals(dataUser.get(i).getPassword())) {
                        if (dataUser.get(i).isAuthority()) {
                            return 1;
                        }
                        return 0;
                    }
                }
            }
        } catch (Exception e) {
        }
        return -1;
    }

    public static boolean checkBook(int book_id) throws SQLException {
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        String sql = "IF EXISTS(SELECT * FROM dbo.tbl_Book WHERE book_id = " + book_id + " ) BEGIN  SELECT 1 END";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            return true;
        } catch (SQLException e) {
            //System.out.println("Khong co sach nay");
            return false;
        }
    }
}
