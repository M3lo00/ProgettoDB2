package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.EmployeeEntity;
import it.polimi.progettodb2.entities.FeeperiodEntity;
import it.polimi.progettodb2.entities.OptserviceEntity;
import it.polimi.progettodb2.entities.PackageEntity;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/report")
public class SalesReportServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @EJB
    private EmployeeService employeeService;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();


        if(req.getParameter("select1")!=null) {
            String val = req.getParameter("select1");
            //req.getParameter("select1").equals("select1");
            session = req.getSession(true);
            session.setAttribute("val", val);
            res.sendRedirect("report");
        }else{
            res.sendRedirect("report");
        }
    }


        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        EmployeeEntity employee = null;
        HttpSession session = req.getSession();

        if(session.getAttribute("employee")!=null){
            employee = (EmployeeEntity) session.getAttribute("employee");
            if (session.getAttribute("val")==null){
                session.setAttribute("val", "0");
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("report.jsp");
            dispatcher.forward(req, res);
        }else{
            res.sendRedirect("./"); //ti sposta di servlet
        }

    }
}
