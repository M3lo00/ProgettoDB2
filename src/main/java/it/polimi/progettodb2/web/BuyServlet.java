package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.FeeperiodEntity;
import it.polimi.progettodb2.entities.OptserviceEntity;
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

@WebServlet("/buy")
public class BuyServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;


    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session =req.getSession();

        List<PackageEntity> packages = userService.findAllPackages();
        session.setAttribute("packages", packages);

        List<FeeperiodEntity> fees = userService.findAllFees();
        session.setAttribute("fees", fees);



        RequestDispatcher dispatcher = req.getRequestDispatcher("BuyPage.jsp");
        dispatcher.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession();

        if (req.getParameter("chosenPack")!=null){

            int refPack= Integer.parseInt(req.getParameter("chosenPackid"));

            List<OptserviceEntity> optionals=userService.choosableOptServices(refPack);
            session.setAttribute("optionals", optionals);
        }

    }

}
