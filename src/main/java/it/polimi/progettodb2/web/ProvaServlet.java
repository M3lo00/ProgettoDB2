package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.PaymentEntity;
import it.polimi.progettodb2.entities.UserEntity;
import it.polimi.progettodb2.entities.EmployeeEntity;
import it.polimi.progettodb2.services.PaymentService;
import it.polimi.progettodb2.services.UserService;
import it.polimi.progettodb2.exceptions.CredentialsException;
import it.polimi.progettodb2.services.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/prova")
public class ProvaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("prova.jsp");
        dispatcher.forward(req, resp);
    }
}