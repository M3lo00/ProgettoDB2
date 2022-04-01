package it.polimi.progettodb2.web;

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
        HttpSession session = req.getSession();

        try{
            List<PackageEntity> packages = userService.findAllPackagesOptional();
            session.setAttribute("packages", packages);
        }catch (Exception e){
            e.printStackTrace();
        }

        session.removeAttribute("chosenPack");
        session.removeAttribute("chosenPackObj");
        session.removeAttribute("chosenMonths");
        session.removeAttribute("startDate");
        session.removeAttribute("optionals");
        session.removeAttribute("chosenOptObj");
        session.removeAttribute("savings");
        session.removeAttribute("total");
        session.removeAttribute("ownOptionals");


        RequestDispatcher dispatcher = req.getRequestDispatcher("BuyPage.jsp");
        dispatcher.forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        HttpSession session = req.getSession();

        if(session.getAttribute("packages")==null){
            try {
                List<PackageEntity> packages = userService.findAllPackagesOptional();
                session.setAttribute("packages", packages);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (req.getParameter("chosenPack")!=null){

            Integer chosenPack_Id= Integer.parseInt(req.getParameter("chosenPack"));

            PackageEntity chosen;
            List<PackageEntity> packageEntityList = (List<PackageEntity>) session.getAttribute("packages");

            chosen = packageEntityList.stream()
                    .filter(pack -> chosenPack_Id.equals(pack.getIdPackage()))
                    .findFirst()
                    .orElse(null);

            session.setAttribute("chosenPackObj", chosen);
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if(session.getAttribute("startDate")==null) req.setAttribute("startDate", formatter.format(date));

        if (req.getParameter("chosenPack")!=null){
            req.setAttribute("monthChoice", "false");
        }else{
            req.setAttribute("monthChoice", "true");
        }


        if (session.getAttribute("chosenPackObj")!=null && req.getParameter("chosenMonths")!=null){

            session.setAttribute("chosenMonths", req.getParameter("chosenMonths"));
            session.setAttribute("startDate", req.getParameter("startDate"));

            PackageEntity pack= (PackageEntity) session.getAttribute("chosenPackObj");

            List<OptserviceEntity> optionals=userService.getAllBuyableOpt(pack.getIdPackage());
            session.setAttribute("optionals", optionals);
            if (optionals.size()==0){
                res.sendRedirect("./confirmation");
            }
        }

        if(req.getParameter("reset")!=null){
            if (req.getParameter("reset").equals("reset")){
                session.removeAttribute("chosenPack");
                session.removeAttribute("chosenPackObj");
                session.removeAttribute("chosenMonths");
                session.removeAttribute("startDate");
                session.removeAttribute("optionals");
                session.removeAttribute("chosenOptObj");
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("BuyPage.jsp");
        dispatcher.forward(req, res);

    }


}
