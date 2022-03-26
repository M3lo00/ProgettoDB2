<%@ page import="it.polimi.progettodb2.entities.PackageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.progettodb2.entities.UserEntity" %>
<%@ page import="it.polimi.progettodb2.entities.OrderEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <title>Home-guest</title>
    <script>
        history.forward();
    </script>
</head>

<%
    List<PackageEntity> packageEntityList = (List<PackageEntity>) request.getSession().getAttribute("packages");
%>

<body>

<%


    UserEntity user = null;
    String userUsername = null;
    if(request.getSession().getAttribute("customer")!=null){
        user = (UserEntity) request.getSession().getAttribute("customer");
        userUsername = user.getUsername();
    }
    List<OrderEntity> rejectedOrders = (List<OrderEntity>) session.getAttribute("rejectedOrders");
    List<OrderEntity> allOrderByUser = (List<OrderEntity>) session.getAttribute("allOrderByUser");
%>


<div class="container d-flex flex-wrap">
    <a class="navbar-brand" href="">TELCO COMPANY</a>
    <ul class="nav me-auto">
        <li class="nav-item"><a href="buy" class="nav-link link-dark px-2">Buy Service</a></li>
    </ul>
    <ul class="nav">
        <% if (user!=null) { %>
            <li class="nav-item"><a class="nav-link link-dark px-2" ><%=user.getUsername()%></a></li>
            <li class="nav-item"><a class="nav-link font-weight-bold px-2" href="${pageContext.request.contextPath}/logout">Logout</a></li>
        <% } %>
    </ul>
</div>


<% if (user!=null && rejectedOrders!=null && rejectedOrders.size()>0) { %>
    <%for (OrderEntity order: rejectedOrders) {%>
    <div class="alert alert-danger alert-dismissible fade show">
        <form action="customer" method="post">
            <strong>Warning!</strong> The payment related to the order: <strong><%=order.getIdOrder()%></strong> is failed.
            <button type="submit"  class="btn btn-outline-danger mx-2" name="retry" value="<%=order.getIdOrder()%>">Retry Payment</button>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </form>
    </div>
    <% } %>
<% } %>




<div class="container-fluid px-1 px-sm-4 py-5 mx-auto">
    <div class="d-flex justify-content-around my-3">
        <h2 >My Orders</h2>
        <div></div>
    </div>
    <%for (OrderEntity order: allOrderByUser) {%>
    <div class="row d-flex justify-content-start card-strip">
        <div class="info">
            <div class="row px-3 mb-2">
                <h4 class="dark-text mr-4"><%=order.getIdOrder()%> Order</h4>
                <% if (order.getValid()) { %>
                    <h4 class="text-success mr-2">Completed</h4>
                <% } %>
                <% if (!order.getValid()) { %>
                <h4 class="text-danger mr-2">Failed</h4>
                <% } %>
            </div>
            <div class="row px-3">
                <div class="col-md-2">
                    <div class="row">Start</div>
                    <div class="row dark-text"><%=order.getStartDate()%> </div>
                </div>
                <div class="col-md-2">
                    <div class="row">Period</div>
                    <div class="row dark-text"><%=order.getPeriodo()%> Months</div>
                </div>
                <div class="col-md-2">
                    <div class="row">Price</div>
                    <div class="row dark-text"><%=order.getTotalAmount()%></div>
                </div>
                <% if (order.getnMobile() != null) { %>
                <div class="col-md-2">
                    <div class="row">Mobile Number</div>
                    <div class="row dark-text"><%=order.getnMobile()%> </div>
                </div>
                <% } %>
                <% if (order.getnFixed() != null) { %>
                <div class="col-md-2">
                    <div class="row">Fixed Number</div>
                    <div class="row dark-text"><%=order.getnFixed()%> </div>
                </div>
                <% } %>
            </div>

        </div>
        <% if (!order.getValid()) { %>
        <form action="customer" method="post">
            <button type="submit"  class="btn btn-orange mt-4" name="retry" value="<%=order.getIdOrder()%>">Retry Payment</button>
        </form>
        <% } %>
    </div>
    <% } %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>

</html>