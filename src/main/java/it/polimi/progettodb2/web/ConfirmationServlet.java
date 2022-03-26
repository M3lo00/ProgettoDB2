package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.OptserviceEntity;
import it.polimi.progettodb2.entities.OrderEntity;
import it.polimi.progettodb2.entities.PackageEntity;
import it.polimi.progettodb2.entities.UserEntity;
import it.polimi.progettodb2.exceptions.CredentialsException;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet("/confirm")
public class ConfirmationServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("chosenPackObj")==null){
            res.sendRedirect("buy");
        }else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("Confirmation.jsp");
            dispatcher.forward(req, res);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session.getAttribute("chosenOptObj")==null) {
            List<OptserviceEntity> optionals = (List<OptserviceEntity>) session.getAttribute("optionals");
            String[] optIndex = req.getParameterValues("chosenOpt");

            if (optIndex != null) {
                optionals.removeIf(opt -> Arrays.stream(optIndex).noneMatch(str -> opt.getIdOptService() == Integer.parseInt(str))); //ruove dalla List optionals
            } else if (optionals != null) {
                try {
                    optionals.removeAll(optionals);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            session.setAttribute("chosenOptObj", optionals);


            PackageEntity pack= (PackageEntity) session.getAttribute("chosenPackObj");
            int periodo= Integer.parseInt((String)  session.getAttribute("chosenMonths"));
            double totale = pack.getPrice12M();

            if (periodo==24){
                session.setAttribute("savings", totale*0.1);
                totale=totale*0.9;
            }else if(periodo==36){
                session.setAttribute("savings", totale*0.2);
                totale=totale*0.8;
            }

            for (OptserviceEntity opt:optionals){
                totale+=opt.getMonthly();
            }

            session.setAttribute("total", totale);

            RequestDispatcher dispatcher = req.getRequestDispatcher("Confirmation.jsp");
            dispatcher.forward(req, res);
        }else {

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            float total = 0;

            Date start = null;
            try {
                start = (formatter.parse((String) session.getAttribute("startDate")));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int chosenMonths=Integer.parseInt((String) session.getAttribute("chosenMonths"));

            PackageEntity pack= (PackageEntity) session.getAttribute("chosenPackObj");

            if(chosenMonths==24){
                total+=pack.getPrice12M()*0.9;
            }else if (chosenMonths==36){
                total+=pack.getPrice12M()*0.8;
            }else{
                total+=pack.getPrice12M();
            }

            List<OptserviceEntity> chosenOpt = (List<OptserviceEntity>) session.getAttribute("chosenOptObj");

            if (!chosenOpt.isEmpty()){
                for (OptserviceEntity opt:chosenOpt){
                    total+=opt.getMonthly();
                }
            }

            userService.newOrder((UserEntity) session.getAttribute("customer"), pack, date,
                    start, chosenMonths, total, chosenOpt);

            res.sendRedirect("customer");
        }
    }
}
