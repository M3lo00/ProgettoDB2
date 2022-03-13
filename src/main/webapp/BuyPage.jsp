<%@ page import="it.polimi.progettodb2.entities.PackageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.progettodb2.entities.OptserviceEntity" %>
<%@ page import="jakarta.persistence.criteria.CriteriaBuilder" %>
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
    <title>Hello, world!</title>


</head>


<body>

<%
    int prova = 10;

%>
<%
    List<PackageEntity> packageEntityList = (List<PackageEntity>) request.getSession().getAttribute("packages");
    //List<OptserviceEntity> optSercices= (List<OptserviceEntity>) request.getSession().getAttribute("optionals");
%>


<div class="container d-flex flex-wrap">

    <a class="navbar-brand" href="/dbproj/">TELCO COMPANY</a>

    <ul class="nav me-auto">
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2"></a></li>
    </ul>

    <%  if (request.getSession().getAttribute("customer")!=null){%>

    <ul class="nav">
        <li class="nav-item"><a class="nav-link link-dark px-2">${customer.getUsername()}</a></li>
        <li class="nav-item"><a class="nav-link font-weight-bold px-2" href="${pageContext.request.contextPath}/logout">Logout</a></li>
    </ul>

    <%  }else{%>

    <ul class="nav">
        <form class="d-flex">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
    </ul>

    <%  }%>
</div>
<%--
div della landing page con cui scegliamo un pacchetto,
    questo deve essere disattivato quando scegliamo il pacchetto dalla LP

Div per la scelta del periodo.
--%>

<form class="container-fluid px-1 px-sm-4 py-5 mx-auto" action="buy" method="post">
    <%
        PackageEntity chosen =null;
        if (request.getSession().getAttribute("chosenPack")!=null){ //lasciare in pack solo un pacchetto

            Integer chosenPack_Id= Integer.parseInt(request.getSession().getAttribute("chosenPack").toString());

            chosen = packageEntityList.stream()
                    .filter(pack -> chosenPack_Id.equals(pack.getIdPackage()))
                    .findFirst()
                    .orElse(null);
            System.out.println(chosenPack_Id+"  "+chosen+" chosen pack= "+request.getSession().getAttribute("chosenPack"));
        }
        for (PackageEntity pack: packageEntityList) {
            if(request.getSession().getAttribute("chosenPack")!=null && pack==chosen || request.getSession().getAttribute("chosenPack")==null){
    %>
    <div class="row d-flex justify-content-start card-strip">
        <div class="info">
            <div class="row px-3 mb-2">
                <h4 class="dark-text mr-4" value="<%=pack.getIdPackage()%>"><%=pack.getName()%></h4>

            </div>
            <div class="row px-3">
                <p class="mb-1"><span class="fa fa-clock-o">Services</span></p>
            </div>
        </div>
        <div class="v-line ml-auto"></div>
        <div class="price">
            <div class="row px-3">
                <h4 class="blue-text mr-2">$ 380,00</h4>
                <p class="mt-1 price-fall mr-5"><del>$ 760,00</del></p>
            </div>
            <button class="btn btn-orange mt-4" type="submit" name="chosenPack" value="<%=pack.getIdPackage()%>">Get started</button>
        </div>
    </div>
    <%
            }
        }
    %>
</form>
<%
    if (request.getSession().getAttribute("chosenPack")!=null){
%>

    <form class="container" action="buy" method="post">

        <div class="row row-cols-1 row-cols-md-4 g-4">
            <div class="col">
                <p class="text-center">Inizio del servizio</p>
            </div>
            <div class="col">
                <div class="card h-80">
                    <div class="card-body">
                        <h5 class="card-title">Silver</h5>
                        <p class="card-text"><%= prova*1 %>€/month</p>
                        <input class="form-check-input stretched-link" type="radio" name="chosenMonths" value="12">
                        <label class="form-check-label">12</label>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-80">
                    <div class="card-body">
                        <h5 class="card-title">Gold</h5>
                        <p class="card-text"><%= prova*0.9%>€/month</p>
                        <input class="form-check-input stretched-link" type="radio" name="chosenMonths" value="24">
                        <label class="form-check-label">24</label>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-80">
                    <div class="card-body">
                        <h5 class="card-title">Platinum</h5>
                        <p class="card-text"><%= (prova*0.8) %>€/month</p>
                        <input class="form-check-input stretched-link" type="radio" name="chosenMonths" value="36">
                        <label class="form-check-label">36</label>
                    </div>
                </div>
            </div>
        </div>
        <div><p> </p></div>
        <div class="row row-cols-1 row-cols-md-4 g-4">
            <div class="col">
                <p class="text-center">Numero di mensilità</p>
            </div>
            <div class="col">
            </div>
            <div class="col">
                <div class="card h-100">
                    <div class="card-body">
                        <input type="date" name="startDate" value="${startDate}" min="${startDate}">
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100">
                    <div class="card-body">
                        <button type="submit" class="btn btn-lg btn-primary">Submit</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
<%
    }
%>
<%--
<container>
    <%
        for (OptserviceEntity opt: optSercices) {
    %>

    <%
        }
    %>
</container>
--%>
</body>
</html>
