<%--
  Created by IntelliJ IDEA.
  User: giuseppegiovia
  Date: 14/03/22
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="it.polimi.progettodb2.entities.EmployeeEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">

    <title>Sales-Report</title>
</head>
<body>
<%
    EmployeeEntity employee = null;
    String employeeUsername = null;
    if(request.getSession().getAttribute("employee")!=null){
        employee = (EmployeeEntity) request.getSession().getAttribute("employee");
        employeeUsername = employee.getUsername();
    }
%>

<div class="container d-flex flex-wrap">
    <a class="navbar-brand" href="/dbproj/">TELCO COMPANY</a>
    <ul class="nav me-auto">
        <li class="nav-item"><a href="${pageContext.request.contextPath}/employee" class="nav-link link-dark px-2">Home</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/report" class="nav-link link-dark px-2">Sales Report</a></li>
    </ul>
    <ul class="nav">
        <% if (employee!=null) { %>
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2" value="<%=employee.getIdEmployee()%>"><%=employee.getUsername()%></a></li>
        <li class="nav-item"><a class="nav-link font-weight-bold px-2" href="${pageContext.request.contextPath}/logout">Logout</a></li>
        <% } %>
    </ul>
</div>


<form class="container-fluid px-1 px-sm-4 py-5 mx-auto" action="buy" method="post">

   <%-- selettore --%>
    <div class="row d-flex justify-content-start card-strip">
        <div class="form-floating">
            <select class="form-select" id="floatingSelect" aria-label="Floating label select example">
                <option selected>Sales Report menu</option>
                <option value="1">Total Purchases per Package</option>
                <option value="2">Total Purchases per Package & Validity Period</option>
                <option value="3">Sales per Package With  the Optional Products</option>
                <option value="7">Sales per Package Without the Optional Products</option>
                <option value="4">Average number of optional products sold together with each service package</option>
                <option value="5">Insolvent Users</option>
                <option value="8">Suspended Orders</option>
                <option value="9">Alerts</option>
                <option value="6">Best seller optional product</option>
            </select>
            <label for="floatingSelect">Works with selects</label>
        </div>
    </div>





       <%-- tabella sales report --%>
    <div class="row d-flex justify-content-start card-strip">
        <ol class="list-group list-group-numbered">
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Subheading</div>
                    Content for list item
                </div>
                <span class="badge bg-primary rounded-pill">14</span>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Subheading</div>
                    Content for list item
                </div>
                <span class="badge bg-primary rounded-pill">14</span>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-start">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Subheading</div>
                    Content for list item
                </div>
                <span class="badge bg-primary rounded-pill">14</span>
            </li>
        </ol>
    </div>




</form>





</body>
</html>