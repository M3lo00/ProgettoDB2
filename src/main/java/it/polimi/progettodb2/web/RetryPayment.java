package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.PackageEntity;
import it.polimi.progettodb2.entities.UserEntity;
import it.polimi.progettodb2.exceptions.CredentialsException;
import it.polimi.progettodb2.services.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/RetryPayment")

public class RetryPayment extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    PackageEntity pack;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        res.sendRedirect("customer");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        userService.retryPayment(Integer.parseInt(req.getParameter("retry")));
        resp.sendRedirect("customer");
    }
}
