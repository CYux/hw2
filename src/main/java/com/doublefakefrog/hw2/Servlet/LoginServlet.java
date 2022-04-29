package com.doublefakefrog.hw2.Servlet;

import com.doublefakefrog.hw2.Models.UserModel;
import com.doublefakefrog.hw2.Service.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //fetches the user from the database
        MySQLdb db = new MySQLdb();
        UserModel user = db.doLogin(username,password);

        if (user!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);


            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            requestDispatcher.forward(request, response);
        }
        else {
            request.setAttribute("error", "Invalid username or password");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
