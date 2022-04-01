package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.*;
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

@WebServlet("/report")
public class SalesReportServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @EJB
    private EmployeeService employeeService;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(req.getParameter("select1")!=null) {
            String val = req.getParameter("select1");
            session.setAttribute("val", val);
            res.sendRedirect("report");
        }else{
            res.sendRedirect("report");
        }
    }


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("employee")!=null){
            if (session.getAttribute("val")==null){
                session.setAttribute("val", "0");
            }else{
                if(Integer.parseInt((String) session.getAttribute("val"))==5){
                    List<UserEntity> findAllInsolvent = employeeService.findAllInsolvent();
                    req.setAttribute("findAllInsolvent", findAllInsolvent);
                }else if(Integer.parseInt((String) session.getAttribute("val"))==8){
                    List<OrderEntity> findAllSuspendedOrder = employeeService.findAllSuspendedOrder();
                    req.setAttribute("findAllSuspendedOrder", findAllSuspendedOrder);
                }else if(Integer.parseInt((String) session.getAttribute("val"))==9){
                    List<AuditEntity> findAllAudit = employeeService.findAllAudit();
                    req.setAttribute("findAllAudit", findAllAudit);
                }else if(Integer.parseInt((String) session.getAttribute("val"))==6){
                    List<OptserviceEntity> findAllBestOptional = employeeService.findAllBestOptional();
                    req.setAttribute("findAllBestOptional", findAllBestOptional);
                }
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("report.jsp");
            dispatcher.forward(req, res);
        }else{
            res.sendRedirect("./");
        }

    }
}
