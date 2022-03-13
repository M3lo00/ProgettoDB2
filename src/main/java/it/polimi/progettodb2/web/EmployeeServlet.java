package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.EmployeeEntity;
import it.polimi.progettodb2.entities.OrderEntity;
import it.polimi.progettodb2.entities.UserEntity;
import it.polimi.progettodb2.services.EmployeeService;
import it.polimi.progettodb2.services.UserService;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmployeeEntity employee = null;
        HttpSession session = req.getSession();

        if(session.getAttribute("employee")!=null){
            employee = (EmployeeEntity) session.getAttribute("employee");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("employee.jsp");
        dispatcher.forward(req, resp);
    }

}
