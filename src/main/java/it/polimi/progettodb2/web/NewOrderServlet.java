package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.OrderEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/order")
public class NewOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

        HttpSession session= req.getSession();

        //OrderEntity order = new OrderEntity(user, pack, crD, (java.sql.Date) startD, period, valid,(int) total, nMobile, nFixed, opts);
        session.getAttribute("optionals");
        session.getAttribute("chosenPack");
        session.getAttribute("chosenMonths");
        session.getAttribute("startDate");
        session.getAttribute("chosenOpt");

        /*boolean checkLength = username.length() != 0 && email.length() != 0 && password.length() != 0;

        try {
            if(checkLength){
                user = userService.createUser(username, email, password);
                if (user != null) destServlet = "signup?signupDone=true";
                else destServlet = "signup?signupError=true";
            }
            else destServlet = "signup?signupError=true";

        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }*/
    }


}
