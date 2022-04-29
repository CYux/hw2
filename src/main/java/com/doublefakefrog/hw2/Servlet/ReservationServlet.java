package com.doublefakefrog.hw2.Servlet;

import com.doublefakefrog.hw2.Models.BookModel;
import com.doublefakefrog.hw2.Models.UserModel;
import com.doublefakefrog.hw2.Service.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Reservation", value = "/Reservation")
public class ReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MySQLdb db = new MySQLdb();
        HttpSession session = request.getSession();
        UserModel user = (UserModel) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("error", "Please login first");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }else
        {
            if(request.getParameter("bookID") != null){
                int bookID = Integer.parseInt(request.getParameter("bookID"));
                db.doReservation(user.getId(), bookID);
            }
            List<BookModel> books = db.fetchReservation(user.getId());
            request.setAttribute("books", books);
            RequestDispatcher dispatcher = request.getRequestDispatcher("reservation.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
