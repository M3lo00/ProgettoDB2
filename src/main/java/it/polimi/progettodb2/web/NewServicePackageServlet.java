package it.polimi.progettodb2.web;
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

@WebServlet("/employee")
public class NewServicePackageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private EmployeeService employeeService;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if ((req.getParameter("quantityserv")!="" || Integer.parseInt(req.getParameter("quantityserv")) == 0)  && req.getParameter("packagename")!="" &&
                (req.getParameter("sms")!="" || Integer.parseInt(req.getParameter("sms")) == 0 )
                ||  (req.getParameter("minute")!="" || Integer.parseInt(req.getParameter("minute")) == 0 )
                || (req.getParameter("gigamobile")!="" || Integer.parseInt(req.getParameter("gigamobile")) == 0 )
                || (req.getParameter("gigafixed")!="" || Integer.parseInt(req.getParameter("gigafixed")) == 0 )
                || req.getParameter("fixedphone")!=null
        ){
            String choseName=req.getParameter("packagename");
            Integer chosenPrice= Integer.parseInt(req.getParameter("quantityserv"));
            Integer chosenSms= Integer.parseInt(req.getParameter("sms"));
            Integer chosenMinute= Integer.parseInt(req.getParameter("minute"));
            Integer chosenGigaMobile= Integer.parseInt(req.getParameter("gigamobile"));
            Integer chosenGigaFixed= Integer.parseInt(req.getParameter("gigafixed"));
            Integer chosenFixedPhone= Integer.parseInt(req.getParameter("fixedphone"));

        }



    }


}
