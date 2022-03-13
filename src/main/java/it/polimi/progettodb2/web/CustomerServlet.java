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

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    UserService userService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserEntity user = null;
        HttpSession session = req.getSession();


        List<OrderEntity> rejectedOrders = null;
        List<OrderEntity> allOrderByUser = null;


        if(session.getAttribute("customer")!=null){
            user = (UserEntity) session.getAttribute("customer");

            if(user.getUsername()!=null) rejectedOrders = userService.findRejectedOrdersByUser(user.getIdUser());

            if(user.getUsername()!=null) allOrderByUser = userService.findAllOrderByUser(user.getIdUser());

            req.getSession().setAttribute("rejectedOrders", rejectedOrders);
            req.getSession().setAttribute("allOrderByUser", allOrderByUser);
            RequestDispatcher dispatcher = req.getRequestDispatcher("customer.jsp");
            dispatcher.forward(req, res);
        }else{
            res.sendRedirect("./"); //ti sposta di servlet
        }

    }
}
