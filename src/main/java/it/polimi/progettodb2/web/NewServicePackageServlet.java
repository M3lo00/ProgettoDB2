package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.EmployeeEntity;
import it.polimi.progettodb2.entities.OptserviceEntity;
import it.polimi.progettodb2.services.EmployeeService;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/newpack")
public class NewServicePackageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private EmployeeService employeeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("employee");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        boolean valid= true;
        float price = Float.parseFloat(req.getParameter("quantitypack"));
        int sms = 0;
        int minute = 0;
        int gigaMobile = 0;
        int gigaFixed = 0;
        byte fixedPhone = Byte.parseByte(req.getParameter("fixedphone"));
        float extraFeeMinute = 0;
        float extraFeeGigaMobile = 0;
        float extraFeeSms = 0;
        float extraFeeGigaFixed= 0;
        List<OptserviceEntity> optionals= new ArrayList<>();

        if(!req.getParameter("minute").equals("") && Integer.parseInt(req.getParameter("minute"))!=0){
            minute=Integer.parseInt(req.getParameter("minute"));
            req.setAttribute("minute",minute);
            if(!req.getParameter("extraminute").equals("") && Float.parseFloat(req.getParameter("extraminute"))!=0){
                extraFeeMinute=Float.parseFloat(req.getParameter("extraminute"));
            }else{
                req.setAttribute("minuteV", "is-invalid");
                valid=false;
            }
        }

        if(!req.getParameter("sms").equals("") && Integer.parseInt(req.getParameter("sms"))!=0){
            sms=Integer.parseInt(req.getParameter("sms"));
            req.setAttribute("sms",sms);
            if(!req.getParameter("extrasms").equals("") && Float.parseFloat(req.getParameter("extrasms"))!=0){
                extraFeeSms=Float.parseFloat(req.getParameter("extrasms"));
            }else{
                req.setAttribute("smsV", "is-invalid");
                valid=false;
            }
        }
        if(!req.getParameter("gigamobile").equals("") && Integer.parseInt(req.getParameter("gigamobile"))!=0){
            gigaMobile=Integer.parseInt(req.getParameter("gigamobile"));
            req.setAttribute("gigamobile",gigaMobile);
            if(!req.getParameter("extragigamobile").equals("") && Float.parseFloat(req.getParameter("extragigamobile"))!=0){
                extraFeeGigaMobile=Float.parseFloat(req.getParameter("extragigamobile"));
            }else{
                req.setAttribute("gigamobileV", "is-invalid");
                valid=false;
            }
        }

        if(!req.getParameter("gigafixed").equals("") && Integer.parseInt(req.getParameter("gigafixed"))!=0){
            gigaFixed=Integer.parseInt(req.getParameter("gigafixed"));
            req.setAttribute("gigaFixed",gigaFixed);
            if(!req.getParameter("extragigafixed").equals("") && Float.parseFloat(req.getParameter("extragigafixed"))!=0){
                extraFeeGigaFixed=Float.parseFloat(req.getParameter("extragigafixed"));
            }else{
                req.setAttribute("gigaFixedV", "is-invalid");
                valid=false;
            }
        }

        if(req.getParameterValues("chosenOpt")!=null){
            optionals.addAll((List<OptserviceEntity>) session.getAttribute("findAllOptionalProduct"));

            String[] optIndex = req.getParameterValues("chosenOpt");

            if (optIndex != null) {
                optionals.removeIf(opt -> Arrays.stream(optIndex).noneMatch(str -> opt.getIdOptService() == Integer.parseInt(str)));
            } else {
                try {
                    optionals.removeAll(optionals);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }

        if(!(minute!=0||sms!=0||gigaMobile!=0||gigaFixed!=0||fixedPhone!=0)){
            valid=false;
            req.setAttribute("noSelection", "true");
        }

        if(valid){
            try {
                employeeService.newPack((EmployeeEntity) session.getAttribute("employee"), req.getParameter("packagename"), sms, minute, gigaMobile, extraFeeMinute, extraFeeGigaMobile, extraFeeSms, fixedPhone, gigaFixed, extraFeeGigaFixed, price, optionals);
            } catch (Exception e) {
                e.printStackTrace();
            }
            res.sendRedirect("employee");
        }else{
            req.setAttribute("quantitypack", price);
            req.setAttribute("packagename", req.getParameter("packagename"));
            RequestDispatcher dispatcher = req.getRequestDispatcher("employee.jsp");
            dispatcher.forward(req, res);
        }
    }
}
