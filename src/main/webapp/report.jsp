<%--
  Created by IntelliJ IDEA.
  User: giuseppegiovia
  Date: 14/03/22
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.progettodb2.entities.*" %>
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
    if(session.getAttribute("employee")!=null){
        employee = (EmployeeEntity) session.getAttribute("employee");
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


<form class="container-fluid px-1 px-sm-4 py-5 mx-auto" action="report" method="post">

   <%-- selettore --%>
    <div class="row d-flex justify-content-start card-strip">
        <form class="form-floating" action="report" method="post">
            <select name="select1"  class="form-select" id="floatingSelect" aria-label="Floating label select example">
                <option selected disabled>Sales Report menu</option>
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
            <input type="submit">
            <label for="floatingSelect">Works with selects</label>
        </form>


        <%  int result= Integer.parseInt((String) session.getAttribute("val"));

            if( result == 1) {
                List<PackageEntity> packageEntityList = (List<PackageEntity>) session.getAttribute("packages");
                System.out.println(result);%>
        <h4 class="dark-text mt-3">Total Purchase per Package</h4>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                            <div class="card-body">Total purchase: <%=pack.getTotpurch().get(0).getTotalPurchases() + pack.getTotpurch().get(1).getTotalPurchases() + pack.getTotpurch().get(2).getTotalPurchases()%></div>
                    </div>
                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>



        <%if( result == 2) {
            List<PackageEntity> packageEntityList = (List<PackageEntity>) session.getAttribute("packages");
            System.out.println(result);%>
        <h4 class="dark-text mt-3">Total Purchase per Package & Validity Period</h4>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                        <div class="card-body">Total purchase (12) months validity period: <%=pack.getTotpurch().get(0).getTotalPurchases()%></div>
                        <div class="card-body">Total purchase (24) months validity period: <%=pack.getTotpurch().get(1).getTotalPurchases()%></div>
                        <div class="card-body">Total purchase (36) months validity period: <%=pack.getTotpurch().get(2).getTotalPurchases()%></div>
                    </div>
                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>




        <%if( result == 4) {
            List<PackageEntity> packageEntityList = (List<PackageEntity>) session.getAttribute("packages");
            System.out.println(result);%>
        <h4 class="dark-text mt-3">Average number of optional products sold together with each service package</h4>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                        <div class="card-body">Average number of optional products sold: <%=pack.getAvgproduct().getAvgnumber()%></div>
                    </div>

                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>





        <%if( result == 3) {
            List<PackageEntity> packageEntityList = (List<PackageEntity>) session.getAttribute("packages");
            System.out.println(result);%>
        <p><h4 class="dark-text mr-4">Sales per Package With the Optional Products</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                        <div class="card-body">Sales with the optional products: <%=pack.getPackageSales().getWithOpt()%> €</div>
                    </div>

                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>




        <%if( result == 7) {
            List<PackageEntity> packageEntityList = (List<PackageEntity>) session.getAttribute("packages");
            System.out.println(result);%>
        <p><h4 class="dark-text mr-4">Sales per Package Without the Optional Products</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                        <div class="card-body">Sales without the optional products: <%=pack.getPackageSales().getJustPack()%> €</div>
                    </div>

                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>




        <%if( result == 5) {
            List<InsolventUserEntity> findAllInsolvent = (List<InsolventUserEntity>) request.getAttribute("findAllInsolvent");
            System.out.println(result);%>
        <p><h4 class="dark-text mr-4">Insolvent Users</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (InsolventUserEntity user: findAllInsolvent) {
                %>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="card-body"><%=user.getInsolventId().getUsername() %></div>
                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>


        <%if( result == 8) {
            List<OrderEntity> findAllSuspendedOrder = (List<OrderEntity>) request.getAttribute("findAllSuspendedOrder");
            System.out.println(result);%>
        <p><h4 class="dark-text mr-4">Suspended Orders</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (OrderEntity susp: findAllSuspendedOrder) {
                %>

                <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="card-body">#Order: <%=susp.getIdOrder()%>   (User: <%=susp.getRefUser().getUsername()%>)</div>
                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>


        <%if( result == 9) {
            List<AuditEntity> findAllAudit = (List<AuditEntity>) request.getAttribute("findAllAudit");
            System.out.println(result);%>
        <p><h4 class="dark-text mr-4">Alert</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (AuditEntity alert: findAllAudit) {
                %>

                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="card-body"><p>Id</p> <p><%=alert.getRefUser().getIdUser()%></p> </div>
                    <div class="card-body"><p>Username</p> <p><%=alert.getRefUser().getUsername()%></p> </div>
                    <div class="card-body"><p>Email</p> <p><%=alert.getRefUser().getEmail()%></p> </div>
                    <div class="card-body"><p>Date and Time Last Rejection:</p> <p><%=alert.getRefOrder().getCreationDate()%></p></div>
                    <div class="card-body"><p>Amount</p> <p><%=alert.getRefOrder().getTotalAmount()%></p> </div>
                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>


        <%if( result == 6) {
            List<BestOptionalEntity> findAllBestOptional = (List<BestOptionalEntity>) request.getAttribute("findAllBestOptional");
            System.out.println(result);%>
        <p><h4 class="dark-text mr-4">Best seller optional product</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (BestOptionalEntity best: findAllBestOptional) {
                %>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="card-body"><%=best.getOpt_id().getName()%>: <%=best.getTotalValue()%>€</div>
                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>




    </div>
</form>
</body>
</html>