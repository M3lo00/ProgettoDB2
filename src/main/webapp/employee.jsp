<%@ page import="it.polimi.progettodb2.entities.EmployeeEntity" %>
<%@ page import="it.polimi.progettodb2.entities.OptserviceEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: giuseppegiovia
  Date: 13/03/22
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Home-employee</title>
</head>
<body>

<%
    request.getAttribute("noSelection");
    EmployeeEntity employee = (EmployeeEntity) session.getAttribute("employee");
    List<OptserviceEntity> findAllOptionalProduct = (List<OptserviceEntity>) session.getAttribute("findAllOptionalProduct");
%>


<div class="container d-flex flex-wrap">
    <a class="navbar-brand" href="#">TELCO COMPANY</a>
    <ul class="nav me-auto">
        <li class="nav-item"><a href="${pageContext.request.contextPath}/employee" class="nav-link link-dark px-2">Home</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/report" class="nav-link link-dark px-2">Sales Report</a></li>
    </ul>
    <ul class="nav">
        <% if (employee!=null) { %>
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2"><%=employee.getUsername()%></a></li>
        <li class="nav-item"><a class="nav-link font-weight-bold px-2" href="${pageContext.request.contextPath}/logout">Logout</a></li>
        <% } %>
    </ul>
</div>

<div class="alert alert-warning alert-dismissible fade show" role="alert" <%=(request.getAttribute("noSelection")=="true")? " ": "hidden"%>>
    <strong>Error!</strong> At least a service must be selected
    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
</div>

<div class="container-fluid px-1 px-sm-4 py-5 col-md-6">
        <h4 class="dark-text mr-4">Create a new Service Package</h4>
    <form method="post" action="newpack">
        <div class="form-row mb-3">
            <div class="form-group ">
                <label for="packageName">Package Name</label>
                <input type="text" class="form-control" name="packagename" id="packageName" value="${packagename}" placeholder="Es. RED Pro" required>
            </div>
        </div>

        <label for="packageName">Minute</label>
        <div class="input-group mb-3">
            <input type="number" id="quantity" name="minute" placeholder="Insert value" value=${minute} min="0" class="form-control" >
        </div>

        <label for="packageName">SMS</label>
        <div class="input-group mb-3">
            <input type="number" id="quantity1" name="sms" placeholder="Insert value" value=${sms} min="0" class="form-control" >
        </div>

        <label for="packageName">Giga Mobile</label>
        <div class="input-group mb-3">

            <input type="number" id="quantity2" name="gigamobile" placeholder="Insert value" value=${gigamobile} min="0" class="form-control">
        </div>

        <label for="quantity8">Extra Minute</label>
        <div class="input-group mb-3">
            <span class="input-group-text">0.00</span>
            <input type="number" step="0.01" id="quantity8" name="extraminute" placeholder="Insert value"  min="0" class="form-control ${minuteV}" >
        </div>

        <label for="packageName">Extra SMS</label>
        <div class="input-group mb-3">
            <span class="input-group-text">0.00</span>
            <input type="number" step="0.01" id="quantity4" name="extrasms" placeholder="Insert value"  min="0" class="form-control ${smsV}">
        </div>

        <label for="packageName">Extra Giga Mobile</label>
        <div class="input-group mb-3">
            <span class="input-group-text">0.00</span>
            <input type="number" step="0.01" id="quantity3" name="extragigamobile" placeholder="Insert value"  min="0" class="form-control ${gigamobileV}" >
        </div>

        <label for="packageName">Giga Fixed</label>
        <div class="input-group mb-3">
            <input type="number" id="quantity6" name="gigafixed" placeholder="Insert value" value=${gigaFixed} min="0" class="form-control">
        </div>

        <label for="packageName">Extra Giga Fixed</label>
        <div class="input-group mb-3">
            <span class="input-group-text">0.00</span>
            <input type="number" step="0.01" id="quantity7" name="extragigafixed" placeholder="Insert value" min="0" class="form-control ${gigaFixedV}">
        </div>

        <label for="packageName">Fixed Phone</label>
        <div class="input-group mb-3">
            <select class="form-select form-select " name="fixedphone">
                <option hidden value="0">Is Fixed-Phone Service included? (def. NO)</option>
                <option value="1">YES</option>
                <option value="0">NO</option>
            </select>
        </div>

        <%for (OptserviceEntity opt: findAllOptionalProduct) {%>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" name="chosenOpt" id="opt<%=opt.getIdOptService()%>" value="<%=opt.getIdOptService()%>">
                <label class="form-check-label" for="opt<%=opt.getIdOptService()%>">
                    <%=opt.getName()+"("+opt.getMonthly()+"€)"%>
                </label>
            </div>
        <% } %>
        <br/>
        <br/>
        <label for="price">Price/Month</label>
        <div class="input-group mb-3">
            <span class="input-group-text">$</span>
            <span class="input-group-text">0.00</span>
            <input type="number" step="0.01" name="quantitypack" id="price" min="1" class="form-control" value="${quantitypack}" aria-label="Dollar amount (with dot and two decimal places)" required>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>


<div class="container-fluid px-1 px-sm-4 py-5 col-md-6">
    <h4 class="dark-text mr-4">Create a new Optional Product</h4>
    <form action="newOpt" method="post">
        <div class="form-row mb-3">
            <div class="form-group ">
                <label for="packageName1">Product Name</label>
                <input type="text" class="form-control" id="packageName1" name="optName" placeholder="Es. Spotify" required>
            </div>
        </div>
        <label for="priceOpt">Price/Month</label>
        <div class="input-group mb-3">
            <span class="input-group-text">$</span>
            <span class="input-group-text">0.00</span>
            <input type="number" step="0.01" id="priceOpt" name="quantityopt" min="1" class="form-control" aria-label="Dollar amount (with dot and two decimal places)" required>
        </div>

        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</body>
</html>
