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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    @EJB
    private EmployeeService employeeService;


    PackageEntity pack;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String destServlet;

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserEntity user = null;
        EmployeeEntity employee = null;

        try {
            employee = employeeService.checkCredentials(username, password);
        } catch (CredentialsException e) {
            e.printStackTrace();
        }

        if (employee != null) {
            session.setAttribute("employee", employee);
            destServlet = "employee";
        }
        else {
            try {
                user = userService.checkCredentials(username, password);
            } catch (CredentialsException e) {
                e.printStackTrace();
            }
            if(user!=null){
                /*if(servicePackage==null){
                    destServlet = "homePageCustomer";
                    if(user.getInsolvent()!=null && user.getInsolvent()){
                        session.setAttribute("rejectedOrders", userService.findRejectedOrdersByUser(user.getUser_id()));
                    }
                }
                else*/ destServlet = "customer";

                session.setAttribute("user", user);
            }
            else destServlet = "/dbproj/?loginFailed=true";
        }

        resp.sendRedirect(destServlet);
    }




}
