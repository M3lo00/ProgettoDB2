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
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class LandingPageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession();

        String message = "Invalid email/password";
        if (req.getParameter("loginFailed") != null) req.setAttribute("messageLogin", message);

        List<PackageEntity> packages = userService.findAllPackagesOptional();
        session.setAttribute("packages", packages);

        session.removeAttribute("chosenPack");
        session.removeAttribute("chosenPackObj");
        session.removeAttribute("chosenMonths");
        session.removeAttribute("startDate");
        session.removeAttribute("optionals");
        session.removeAttribute("chosenOptObj");

        if(session.getAttribute("customer")!=null) {
            res.sendRedirect("./customer");

        }else if(session.getAttribute("employee")!=null){
            res.sendRedirect("./employee");
        }
        else{
            RequestDispatcher dispatcher= req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req, res);
        }

    }

}