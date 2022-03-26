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
    //list of all service packages
    List<PackageEntity> packageEntityList = (List<PackageEntity>) request.getSession().getAttribute("packages");
    List<UserEntity> userEntityList = (List<UserEntity>) request.getAttribute("findAllUser");
    List<OrderEntity> orderEntityList =  (List<OrderEntity>) request.getAttribute("findAllorder");

    List<TotpurchaseperpackandvalidityEntity> findByPackage = (List<TotpurchaseperpackandvalidityEntity>) request.getAttribute("findByPackage");
    List<AvgproductperserviceEntity> findAllAvg = (List<AvgproductperserviceEntity>) request.getAttribute("findAllAvg");
    List<PackagePerSalesEntity> findAllSales = (List<PackagePerSalesEntity>) request.getAttribute("findAllSales");


    List<InsolventUserEntity> findAllInsolvent = (List<InsolventUserEntity>) request.getAttribute("findAllInsolvent");
    List<SuspendedOrderEntity> findAllSuspendedOrder = (List<SuspendedOrderEntity>) request.getAttribute("findAllSuspendedOrder");
%>








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


        <%  int result= Integer.parseInt((String) request.getSession().getAttribute("val"));

            if( result == 1) {
                System.out.println(result);%>



        <p><h4 class="dark-text mr-4">Total Purchase per Package</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>

                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                        <div class="fw-bold"><%=pack.getTotpurch().get(0).getTotalPurchases() + pack.getTotpurch().get(1).getTotalPurchases() + pack.getTotpurch().get(2).getTotalPurchases()%></div>
                    </div>
                    <span class="badge bg-primary rounded-pill">14</span>
                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>

        <%if( result == 2) {
        System.out.println(result);%>



        <p><h4 class="dark-text mr-4">Total Purchase per Package & Validity Period</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>

                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                        <a>12 months    </a><div class="badge bg-primary rounded-pill"><%=pack.getTotpurch().get(0).getTotalPurchases()%></div>
                        <br>
                        <a>24 months    </a><div class="badge bg-primary rounded-pill"><%=pack.getTotpurch().get(1).getTotalPurchases()%></div>
                        <br>
                        <a>36 months    </a><div class="badge bg-primary rounded-pill"><%=pack.getTotpurch().get(2).getTotalPurchases()%></div>
                    </div>

                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>


        <%if( result == 4) {
            System.out.println(result);%>



        <p><h4 class="dark-text mr-4">Average number of optional products sold together with each service package</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>

                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                        <a>Average number of optional products sold    </a><div class="badge bg-primary rounded-pill"><%=pack.getAvgproduct().get(0).getAvgnumber()%></div>
                    </div>

                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>


        <%if( result == 3) {
            System.out.println(result);%>



        <p><h4 class="dark-text mr-4">Sales per Package With  the Optional Products</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>

                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                        <a>Sales With  the Optional Products    </a><div class="badge bg-primary rounded-pill"><%=pack.getPackageSales().get(0).getWithOpt()%></div>
                    </div>

                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>

        <%if( result == 7) {
            System.out.println(result);%>



        <p><h4 class="dark-text mr-4">Sales per Package Without  the Optional Products</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>

                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=pack.getName() %></div>
                        <a>Sales Without  the Optional Products    </a><div class="badge bg-primary rounded-pill"><%=pack.getPackageSales().get(0).getJustPack()%></div>
                    </div>

                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>


        <%if( result == 5) {
            System.out.println(result);%>



        <p><h4 class="dark-text mr-4">Insolvent Users</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (InsolventUserEntity user: findAllInsolvent) {
                %>

                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=user.getInsolventId().getUsername() %></div>
                        <%--<a>Sales Without  the Optional Products    </a><div class="badge bg-primary rounded-pill"><%=pack.getPackageSales().get(0).getJustPack()%></div>--%>
                    </div>

                </li>
                <%
                    }
                %>
            </ol>
        </div>
        <% } %>


        <%if( result == 8) {
            System.out.println(result);%>



        <p><h4 class="dark-text mr-4">Suspended Orders</h4></p>
        <%-- tabella sales report --%>
        <div class="row d-flex justify-content-start card-strip">
            <ol class="list-group list-group-numbered">
                <%
                    for (SuspendedOrderEntity susp: findAllSuspendedOrder) {
                %>

                <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                        <div class="fw-bold"><%=susp.getOrder_id().getIdOrder()%> <%=susp.getOrder_id().getRefUser().getUsername()%></div>
                        <%--<a>Sales Without  the Optional Products    </a><div class="badge bg-primary rounded-pill"><%=pack.getPackageSales().get(0).getJustPack()%></div>--%>
                    </div>

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