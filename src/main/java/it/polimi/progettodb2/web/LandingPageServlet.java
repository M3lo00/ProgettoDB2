package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.PackageEntity;
import it.polimi.progettodb2.services.UserService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Request;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class LandingPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession();
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession();

        String message = "Invalid email/password";
        if (req.getParameter("loginFailed") != null) req.setAttribute("messageLogin", message);

        List<PackageEntity> packages = userService.findAllPackages();
        session.setAttribute("packages", packages);


        RequestDispatcher dispatcher = null;

        if(req.getSession().getAttribute("customer")!=null) {
            res.sendRedirect("./customer"); //ti sposta di servlet

        }else if(req.getSession().getAttribute("employee")!=null){
            res.sendRedirect("./employee"); //ti sposta di servlet
        }
        else{
            dispatcher= req.getRequestDispatcher("prova.jsp"); //compila il jsp con le req indicate nel get
            dispatcher.forward(req, res);
        }



    }

}