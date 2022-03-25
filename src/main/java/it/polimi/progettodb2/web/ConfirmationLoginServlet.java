package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.PackageEntity;
import it.polimi.progettodb2.entities.UserEntity;
import it.polimi.progettodb2.entities.EmployeeEntity;
import it.polimi.progettodb2.services.UserService;
import it.polimi.progettodb2.exceptions.CredentialsException;
import it.polimi.progettodb2.services.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/confLogin")
public class ConfirmationLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    PackageEntity pack;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String destServlet;

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserEntity user = null;
        try {
            user = userService.checkCredentials(username, password);
        } catch (CredentialsException e) {
            e.printStackTrace();
        }
        if(user!=null){

            session.setAttribute("customer", user);
        }
        else session.setAttribute("invalid", "is-invalid");
        destServlet="confirm";

        resp.sendRedirect(destServlet);
    }




}
