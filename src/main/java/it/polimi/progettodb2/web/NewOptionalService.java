package it.polimi.progettodb2.web;
import it.polimi.progettodb2.entities.EmployeeEntity;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/newOpt")
public class NewOptionalService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private EmployeeService employeeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("employee");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        float price = Float.parseFloat(req.getParameter("quantityopt"));

        try {
            employeeService.newOpt((EmployeeEntity) session.getAttribute("employee"), req.getParameter("optName"),  price);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.sendRedirect("employee");
    }
}
