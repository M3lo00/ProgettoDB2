package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.OptserviceEntity;
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
import java.util.List;


@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    EmployeeService employeeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("employee")!=null){

            List<OptserviceEntity> optionalProducts = employeeService.findAllOptionalProduct();
            session.setAttribute("findAllOptionalProduct", optionalProducts);

            session.removeAttribute("val");

            RequestDispatcher dispatcher = req.getRequestDispatcher("employee.jsp");
            dispatcher.forward(req, res);
        }else{
            res.sendRedirect("./");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        req.getParameter("packagename");
        req.getParameter("sms");
        req.getParameter("minute");
        req.getParameter("gigamobile");
        req.getParameter("extragigamobile");
        req.getParameter("extrasms");
        req.getParameter("gigafixed");
        req.getParameter("extragigafixed");
        req.getParameter("fixedphone");
        req.getParameter("quantityserv");

        RequestDispatcher dispatcher = req.getRequestDispatcher("employee.jsp");
        dispatcher.forward(req, res);
    }
}
