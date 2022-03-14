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
import java.text.SimpleDateFormat;
import java.util.Date;
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

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        req.setAttribute("startDate", formatter.format(date));

        if (req.getParameter("chosenPack")!=null){
            req.setAttribute("monthChoice", "false");
        }else{req.setAttribute("monthChoice", "true");}


        RequestDispatcher dispatcher = req.getRequestDispatcher("BuyPage.jsp");
        dispatcher.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession();


        if (session.getAttribute("chosenPack")==null) session.setAttribute("chosenPack", req.getParameter("chosenPack"));

        if (session.getAttribute("chosenPack")!=null && req.getParameter("chosenMonths")!=null){

            int refPack= Integer.parseInt((String) session.getAttribute("chosenPack"));

            List<OptserviceEntity> optionals=userService.choosableOptServices(refPack);
            session.setAttribute("optionals", optionals);

        }

        System.out.println(req.getParameter("reset"));
        if(req.getParameter("reset")!=null){
            if (req.getParameter("reset").equals("reset")){
                System.out.println("prova");
                session.setAttribute("chosenPack", null);
            }
        }

        res.sendRedirect("buy");
    }


}
