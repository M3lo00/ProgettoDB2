package it.polimi.progettodb2.web;

import it.polimi.progettodb2.entities.OrderEntity;
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
import java.util.List;
import java.util.Optional;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    UserService userService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserEntity user;
        HttpSession session = req.getSession();




        if(session.getAttribute("customer")!=null){

            session.removeAttribute("chosenPack");
            session.removeAttribute("chosenPackObj");
            session.removeAttribute("chosenMonths");
            session.removeAttribute("startDate");
            session.removeAttribute("optionals");
            session.removeAttribute("chosenOptObj");
            session.removeAttribute("savings");
            session.removeAttribute("total");
            session.removeAttribute("ownOptionals");

            user = (UserEntity) session.getAttribute("customer");

            List<OrderEntity> rejectedOrders = null;
            List<OrderEntity> allOrderByUser = null;

            if(user.getUsername()!=null) rejectedOrders = userService.findRejectedOrdersByUser(user.getIdUser());

            if(user.getUsername()!=null) allOrderByUser = userService.findAllOrderByUser(user.getIdUser());

            session.setAttribute("rejectedOrders", rejectedOrders);
            session.setAttribute("allOrderByUser", allOrderByUser);

            if(session.getAttribute("success")!=null && !(Boolean) session.getAttribute("success")){
                    session.removeAttribute("success");
            }else{
                req.setAttribute("success","hidden");
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("customer.jsp");
            dispatcher.forward(req, res);
        }else{
            res.sendRedirect("./");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();

        if (req.getParameter("retry")!=null){
            Optional<OrderEntity> order = userService.findOrderByID(Integer.parseInt(req.getParameter("retry")));
            if (order.isPresent()){
                req.setAttribute("retry", order.get().getIdOrder());
                session.setAttribute("chosenPackObj", order.get().getRefPack());
                session.setAttribute("chosenMonths", order.get().getPeriodo());

                int periodo=order.get().getPeriodo();
                float totale= order.get().getTotalAmount();

                if (periodo==24){
                    session.setAttribute("savings", totale*0.1);
                    session.setAttribute("total", totale*0.9);
                }else if(periodo==36){
                    session.setAttribute("savings", totale*0.2);
                    session.setAttribute("total", totale*0.8);

                }
                session.setAttribute("chosenOptObj", order.get().getOptServices());
                session.setAttribute("ownOptionals", userService.getPackOptionals(order.get().getRefPack().getIdPackage()));

                RequestDispatcher dispatcher= req.getRequestDispatcher("Confirmation.jsp");
                dispatcher.forward(req, resp);
            }else {

                resp.sendRedirect("customer");
            }
        }else {
            resp.sendRedirect("customer");
        }
    }
}
