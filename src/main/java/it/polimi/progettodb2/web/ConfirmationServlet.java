package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.OptserviceEntity;
import it.polimi.progettodb2.entities.PackageEntity;
import it.polimi.progettodb2.entities.UserEntity;
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

        List<OptserviceEntity> chosenOpt = (List<OptserviceEntity>) session.getAttribute("optionals");
        String[] optIndex = req.getParameterValues("chosenOpt");

        if (optIndex!=null){
            chosenOpt.removeIf(opt -> Arrays.stream(optIndex).noneMatch(str -> opt.getIdOptService() == Integer.parseInt(str))); //ruove dalla List chosenOpt
        }else if (chosenOpt!=null){
            try{
                chosenOpt.removeAll(chosenOpt);
            }catch (NullPointerException e ){
                e.printStackTrace();
            }
        }

        session.setAttribute("chosenOpt", chosenOpt);

        RequestDispatcher dispatcher = req.getRequestDispatcher("Confirmation.jsp");
        dispatcher.forward(req, res);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session= req.getSession();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        float total=0;

        Date start = null;
        try {
            start=(formatter.parse((String) session.getAttribute("startDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<OptserviceEntity> chosenOpt = (List<OptserviceEntity>) session.getAttribute("chosenOpt");

        userService.newOrder((UserEntity) session.getAttribute("customer"), (PackageEntity) session.getAttribute("chosenPackObj"), date,
                start, Integer.parseInt((String) session.getAttribute("chosenMonths")), total, chosenOpt);

    }
}
