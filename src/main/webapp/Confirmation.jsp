<%@ page import="it.polimi.progettodb2.entities.PackageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.progettodb2.entities.UserEntity" %>
<%@ page import="it.polimi.progettodb2.entities.OptserviceEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="./style.css">
    <title>Home-Telco</title>
</head>
<%
    PackageEntity pack= (PackageEntity) session.getAttribute("chosenPackObj");
%>

<body>
<div class="container d-flex flex-wrap">

    <a class="navbar-brand" href="/dbproj/">TELCO COMPANY</a>

    <ul class="nav me-auto">
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2"></a></li>
    </ul>

    <%  if (session.getAttribute("customer")!=null){
        UserEntity customer= (UserEntity) session.getAttribute("customer");
    %>

    <ul class="nav">
        <li class="nav-item"><a class="nav-link link-dark px-2"><%=customer.getUsername()%></a></li>
        <li class="nav-item"><a class="nav-link font-weight-bold px-2" href="logout">Logout</a></li>
    </ul>

    <%}else{%>

    <ul class="nav">
        <form class="p-1 d-flex" method="post" action="confLogin">

            <label>
                <input type="text" class="form-control ${invalid}" name="username" placeholder="username">
            </label>
            <label>
                <input type="password" class="form-control ${invalid}" name="password" placeholder="password">
            </label>
            <button type="submit" class="btn btn-sm btn-outline-primary">Login</button>

        </form>
    </ul>

    <%  }%>
</div>

<div class="container d-flex">
    <div class="row g-3">
        <div class="col-md-5">
            <%if (request.getSession().getAttribute("customer")!=null){%>
            <fieldset >
            <%}else{%>
            <fieldset disabled>
            <%}%>
            <form class="row g-3" method="post" action="confirm">
                <div class="col-md-9">
                    <label for="validationCustom01" class="form-label">Card Number</label>
                    <input type="text" class="form-control" id="validationCustom01" pattern="\d*" maxlength="16" minlength="16" required>
                </div>
                <div class="col-md-3">
                    <label for="validationCustom04" class="form-label">CVC</label>
                    <input type="number" class="form-control" id="validationCustom04" min="100" max="999" required>
                </div>
                <div class="col-md-6">
                    <label for="validationCustom02" class="form-label">Expiration Month</label>
                    <select class="form-select" id="validationCustom02" required>
                        <option selected disabled value="">Choose...</option>
                        <%for(int i=1;i<8;i++){
                        %>
                        <option><%=2022+i%></option>
                        <%}
                        %>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="validationCustom03" class="form-label">Expiration Year</label>
                    <select class="form-select" id="validationCustom03" required>
                        <option selected disabled value="">Choose...</option>
                        <%for(int i=1;i<=12;i++){
                        %>
                        <option><%=i%></option>
                        <%}
                        %>
                    </select>
                </div>
                <div class="col-12">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                        <label class="form-check-label" for="invalidCheck">
                            Agree to terms and conditions
                        </label>
                    </div>
                </div>
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">Submit form</button>
                </div>
            </form>
            </fieldset>
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-5">
            <div class="bg-light text-dark">
                <h5 class="p-2">Pacchetto base</h5>
                <div class="p-2 d-flex flex-row justify-content-between">
                    <p class="">Nome:</p>
                    <p><%=pack.getName()%></p>
                    <p><%=pack.getPrice12M()%>€/m</p>
                </div>
                <hr class="my-1">
                <div class="p-2 d-flex flex-row justify-content-between">
                    <p>Periodo:</p>
                    <p><%=session.getAttribute("chosenMonths")%> months</p>
                    <p>-<%=String.format("%.2f", ((session.getAttribute("savings")!=null)?Float.parseFloat(session.getAttribute("savings").toString()):0))%></p>
                </div>
                <hr class="my-1"/>

                <% if (session.getAttribute("chosenOptObj")!=null){
                    List<OptserviceEntity> chosenOpt = (List<OptserviceEntity>) session.getAttribute("chosenOptObj");
                    %>
                <h5 class="p-2">Pacchetti opzionali scelti</h5>
                <%
                    for (OptserviceEntity opt: chosenOpt){
                %>
                <div class="p-2 d-flex flex-row justify-content-between">
                    <p>Nome:</p>
                    <p><%=opt.getName()%></p>
                    <p><%=opt.getMonthly()%>€/m</p>
                </div>
                <%      }
                }
                %>
                <div class="p-2 d-flex flex-row justify-content-between">
                    <p>Totale:</p>
                    <p><%=String.format("%.2f", Float.parseFloat(session.getAttribute("total").toString()))%>€</p>
                </div>
            </div>
            <h5>Order Review</h5>
    </div>
    </div>
</div>
</body>
</html>
