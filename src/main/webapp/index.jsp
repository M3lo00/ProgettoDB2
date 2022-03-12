<%@ page import="it.polimi.progettodb2.entities.PackageEntity" %><%-- <%@ page import="it.polimi.progettodb2.entities.ServicePackageEntity" %> --%>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Quaccio Website</title>
</head>
<%--
<style><%@include file="css/style.css"%></style>
--%>
<%
    List<PackageEntity> packageEntityList = (List<PackageEntity>) request.getSession().getAttribute("packages");
%>

<body>


<div class='parent'>
    <h1>Quaccio Company</h1>
    <div class="login-page">
        <div class="form">
            <h2>LOGIN</h2>
            <form action="login" method="post">
                <input type="text" name="username" placeholder="username" required/>
                <input type="password" name="password" placeholder="password" required/>
                <br>${messageLogin}
                <button type="submit">login</button>
            </form>
        </div>
    </div>

    <div class="login-page">
        <div class="form">
            <h2>REGISTRATION</h2>
            <form action="signup" method="post">
                <input type="text" name="username" placeholder="username" required/>
                <input type="text" name="email" placeholder="email" required/>
                <input type="password" name="password" placeholder="password" required/>
                <label for="employee">I am an employee:</label>
                <input type="checkbox" class="check" id="employee" name="employee" value="employee">
                <br>${messageSignUp}
                <button type="submit">sign up</button>
            </form>
        </div>
    </div>
    <div>
        <form action="packageSelect" method="post">

            <%--@declare id="nameservpackage"--%>
            <input class="inputSrvPackage" name="nameServPackage" placeholder="Name Service Package" required/>
            <br><br>

            <fieldset>
                <legend>Choose one or more services</legend>
                <%
                    for (PackageEntity pack: packageEntityList) {
                %>
                <input type="checkbox" name="services"
                       value="<%=pack.getIdPackage()%>"><%=pack.getName()%><br>
                <%
                    }
                %>

            </fieldset>

            <br><br>
                <%--
            <fieldset>
                <legend>Choose one or more optional products</legend>
                <%

                    for (OptionalProductEntity optProd: optionalProducts) {
                %>
                <input type="checkbox" name="optionalProducts"
                       value="<%=optProd.getOptionalProduct_id() %>"><%=optProd.getName() %><br>
                <%
                    }
                %>
            </fieldset>

            <br><br>

            <fieldset>
                <legend>Choose one or more validity periods associated to this service package</legend>
                <%
                    for (ValidityPeriodEntity valPer: validityPeriods) {
                %>
                <input type="checkbox" name="validityPeriods"
                       value="<%=valPer.getValidityPeriod_id() %>"><%=valPer.toString() %><br>
                <%
                    }
                %>
            </fieldset>

            <br>${messageServicePackage}
            <br><br>
            --%>

            <button class="niceButton" type="submit">CREATE</button>
        </form>
    </div>

</div>

<%--
<%
    if(servicePackage==null){
%>

<div class="parent">
    <div class="login-page">
        <div class="form">
            <form action="homePageCustomer">
                <button type="submit">
                    skip login
                </button>
            </form>
        </div>
    </div>
</div>


<%
    }
%>

--%>
</body>
</html>