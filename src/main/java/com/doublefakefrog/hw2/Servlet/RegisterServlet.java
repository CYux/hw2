package com.doublefakefrog.hw2.Servlet;

import com.doublefakefrog.hw2.Models.UserModel;
import com.doublefakefrog.hw2.Service.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Register", value = "/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        MySQLdb db = new MySQLdb();
        UserModel user = db.doRegister(firstName, lastName, username, password);

        if (user!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.setAttribute("msg", "Successfully registered!");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
            requestDispatcher.forward(request, response);
        }
        else {
            request.setAttribute("msg", "Error: Username already exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
