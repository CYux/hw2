package com.doublefakefrog.hw2.Service;

import com.doublefakefrog.hw2.Models.BookModel;
import com.doublefakefrog.hw2.Models.TopicModel;
import com.doublefakefrog.hw2.Models.UserModel;

import java.awt.print.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLdb {
    String url = "jdbc:mysql://localhost:3306/library_catalog";
    String user = "root";
    String password = "root";
    Connection conn = null;
    static MySQLdb instance = null;

    public static synchronized MySQLdb getInstance() {
        if (instance == null) {
            instance = new MySQLdb();
        }
        return instance;
    }

    public MySQLdb() {
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserModel doLogin(String username, String password) {
        UserModel user = null;
        try {
            String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                user = new UserModel(rs.getInt("user_id"),rs.getString("fname") ,
                        rs.getString("lname"), rs.getString("username"), rs.getString("password"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean userExists(String username) {
        boolean response = false;
        try {
        String sql = "SELECT count(*) from users WHERE username = '" + username + "'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            if(rs.getInt(1) > 0) {
                response = true;
            }        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    public UserModel doRegister(String fname, String lname, String username, String password) {
        UserModel user = null;
        if (!userExists(username)) {
            try {
                String sql = "INSERT INTO users (fname, lname, username, password) VALUES ('" + fname + "', '" + lname + "', '" + username + "', '" + password + "')";
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate(sql);
                if (rs > 0) {
                    user = doLogin(username, password);
                }
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;

        }
        return null;
    }

    public List<BookModel> fetchBook() {
        List<BookModel> books = new ArrayList<>();
        try {
            String sql = "SELECT book_id, book_name ,author_name,b.topic_id,topic_name,is_available FROM books as b LEFT JOIN authors as a on a.author_id=b.author_id LEFT JOIN topics as t on b.topic_id=t.topic_id ORDER BY book_id";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                books.add(new BookModel
                        (rs.getInt("book_id"), rs.getString("book_name"),rs.getInt("topic_id"), rs.getString("topic_name"),rs.getString("author_name"), rs.getBoolean("is_available")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<TopicModel> fetchTopic(){
        List<TopicModel> topics = new ArrayList<>();
        try {
            String sql = "SELECT * FROM topics";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                topics.add(new TopicModel
                (rs.getInt("topic_id"),rs.getString("topic_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topics;
    }

    public List<BookModel> fetchReservation(int user_id){
        List<BookModel> books = new ArrayList<>();
        try {
            String sql = "SELECT book_id, book_name ,author_name,b.topic_id,topic_name,is_available FROM books as b LEFT JOIN authors as a on a.author_id=b.author_id LEFT JOIN topics as t on b.topic_id=t.topic_id WHERE book_id IN (SELECT book_id FROM reservations WHERE user_id = " + user_id + ")";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                books.add(new BookModel
                        (rs.getInt("book_id"), rs.getString("book_name"),rs.getInt("topic_id"), rs.getString("topic_name"),rs.getString("author_name"), rs.getBoolean("is_available")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public BookModel findBook(int book_id) {
        BookModel book = null;
        try {
            String sql = "SELECT book_id, book_name ,author_name,b.topic_id,topic_name,is_available FROM books as b LEFT JOIN authors as a on a.author_id=b.author_id LEFT JOIN topics as t on b.topic_id=t.topic_id WHERE book_id = " + book_id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                book = new BookModel
                        (rs.getInt("book_id"), rs.getString("book_name"),rs.getInt("topic_id"), rs.getString("topic_name"),rs.getString("author_name"), rs.getBoolean("is_available"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public boolean changeAvability(int book_id){
        boolean response = false;
        try {
            String sql = "UPDATE books SET is_available = 0 WHERE book_id = " + book_id;
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);
            if (rs > 0) {
                response = true;
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public void doReservation(int user_id,int book_id) {
        BookModel book = null;
        book = findBook(book_id);
        if (book!=null && book.isAvailable()) {
            try {
                changeAvability(book_id);
                String sql = "INSERT INTO reservations (user_id, book_id) VALUES (" + user_id + "," + book_id + ")";
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate(sql);
                if (rs > 0) {
                    System.out.println("Reservation successful!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
