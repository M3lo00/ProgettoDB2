package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.*;
import it.polimi.progettodb2.services.EmployeeService;
import it.polimi.progettodb2.services.UserService;
import jakarta.ejb.EJB;
import jakarta.persistence.criteria.Order;
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

    private TotpurchaseperpackandvalidityEntity totPurchaseXPackage;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
/*
        //first query
        String select2 = req.getParameter("select2");
        if(select2!=null) totPurchaseXPackage = employeeService.purchasePackage();


 */
        /*
        totPurchaseXPackage = employeeService.purchasePackage();


         */


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

        //first query

        List<TotpurchaseperpackandvalidityEntity> findAllTot = employeeService.findAllTot();
        req.setAttribute("findAllTot", findAllTot);

        //second query
        List<AvgproductperserviceEntity> findAllAvg = employeeService.findAllAvg();
        req.setAttribute("findAllAvg", findAllAvg);

        //Third query
        List<PackagePerSalesEntity> findAllSales = employeeService.findAllSales();
        req.setAttribute("findAllSales", findAllSales);

        List<InsolventUserEntity> findAllInsolvent = employeeService.findAllInsolvent();
        req.setAttribute("findAllInsolvent", findAllInsolvent);

        List<UserEntity> findAllUser = employeeService.findAllUser();
        req.setAttribute("findAllUser", findAllUser);

        List<OrderEntity> findAllOrder = employeeService.findAllOrder();
        req.setAttribute("findAllOrder", findAllOrder);

        List<SuspendedOrderEntity> findAllSuspendedOrder = employeeService.findAllSuspendedOrder();
        req.setAttribute("findAllSuspendedOrder", findAllSuspendedOrder);


        //if

        /*if (session.getAttribute("val")!=null && Integer.parseInt(session.getAttribute("val").toString())==5){
            List<UserEntity> findAllUser = employeeService.findAllUser();
            req.setAttribute("findAllUser", findAllUser);
        }*/



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
