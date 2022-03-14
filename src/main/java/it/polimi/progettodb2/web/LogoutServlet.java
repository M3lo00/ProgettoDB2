package it.polimi.progettodb2.web;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("customer");
            session.removeAttribute("employee");
            session.removeAttribute("rejectedOrders");
            session.removeAttribute("packages");
            session.removeAttribute("allOrderByUser");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("prova.jsp");
        dispatcher.forward(request, response);

    }
}
