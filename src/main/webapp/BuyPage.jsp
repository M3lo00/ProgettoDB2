<%@ page import="it.polimi.progettodb2.entities.PackageEntity" %>
<%@ page import="java.util.List" %>
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
    <link rel="stylesheet" href="style.css">
    <title>Hello, world!</title>


</head>


<body>
<%
    List<PackageEntity> packageEntityList = (List<PackageEntity>) request.getSession().getAttribute("packages");

    List<OptserviceEntity> optServices=null;
    if (request.getSession().getAttribute("optionals")!=null) optServices = (List<OptserviceEntity>) request.getSession().getAttribute("optionals");
%>


<div class="container d-flex flex-wrap">

    <a class="navbar-brand" href="/dbproj/">TELCO COMPANY</a>

    <ul class="nav me-auto">
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2"></a></li>
    </ul>

    <%  if (request.getSession().getAttribute("customer")!=null){%>

    <ul class="nav">
        <li class="nav-item"><a class="nav-link link-dark px-2">${customer.getUsername()}</a></li>
        <li class="nav-item"><a class="nav-link font-weight-bold px-2" href="/dbproj/logout">Logout</a></li>
    </ul>

    <%}else
    {%>

    <ul class="nav">
        <form class="p-1 d-flex" method="post" action="buy">

            <input type="text" class="form-control ${invalid}" name="username" placeholder="username">
            <input type="password" class="form-control ${invalid}" name="password" placeholder="password">
            <button type="submit" class="btn btn-sm btn-outline-primary">Login</button>

        </form>
    </ul>
    <%}%>
</div>

<div class="d-flex justify-content-around my-3">
    <h2 >Chose the package that fits you the best</h2>
    <div></div>
</div>

<form class="container-fluid" action="buy" method="post">
    <%
        PackageEntity chosen =null;
        if (request.getSession().getAttribute("chosenPack")!=null){ //lasciare in pack solo un pacchetto

            Integer chosenPack_Id= Integer.parseInt(request.getSession().getAttribute("chosenPack").toString());

            chosen = packageEntityList.stream()
                    .filter(pack -> chosenPack_Id.equals(pack.getIdPackage()))
                    .findFirst()
                    .orElse(null);
            request.getSession().setAttribute("chosenPackObj", chosen);
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
                <h4 class="blue-text mr-2">$ <%=String.format("%.2f",pack.getPrice12M()*0.8)%></h4>
                <p class="mt-1 price-fall mr-5"><del>$ <%=String.format("%.2f",pack.getPrice12M())%></del></p>
            </div>
            <%if (request.getSession().getAttribute("chosenPack")!=null){%>
            <button type="submit" class="btn btn-danger mt-4" name="reset" value="reset" > Change Package </button>
            <%}else{%>
            <button class="btn btn-orange mt-4" type="submit" name="chosenPack" value="<%=pack.getIdPackage()%>">Get started</button>
            <%}%>
        </div>
    </div>
    <%
            }
        }
    %>
<%--</form>--%>
    <%
        if (request.getSession().getAttribute("chosenPack")!=null){
            PackageEntity packagec= (PackageEntity) request.getSession().getAttribute("chosenPackObj");
            double prova =  packagec.getPrice12M();

    %>
        <%--<form class="container" action="buy" method="post">--%>

        <div class="row d-flex justify-content-start card-strip">
            <div class="col">
                <h5 class="card-title">Offer Duration</h5>
            </div>
            <div class="col">
                <div class="card h-80">
                    <div class="card-body">
                        <h5 class="card-title">Silver</h5>
                        <p class="card-text"><%= String.format("%.2f",prova) %>€/month</p>
                        <input class="form-check-input stretched-link" type="radio" name="chosenMonths" value="12">
                        <label class="form-check-label">12 Months</label>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-80">
                    <div class="card-body">
                        <h5 class="card-title">Gold</h5>
                        <p class="card-text"><%= String.format("%.2f",prova*0.9)%>€/month</p>
                        <input class="form-check-input stretched-link" type="radio" name="chosenMonths" value="24">
                        <label class="form-check-label">24 Months</label>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-80">
                    <div class="card-body">
                        <h5 class="card-title">Platinum</h5>
                        <p class="card-text"><%= String.format("%.2f",prova*0.8) %>€/month</p>
                        <input class="form-check-input stretched-link" type="radio" name="chosenMonths" value="36">
                        <label class="form-check-label">36 Months</label>
                    </div>
                </div>
            </div>
        </div>
        <div><p> </p></div>
        <div class="row d-flex justify-content-start card-strip">
            <div class="col">
                <h5 class="card-title">Starting date</h5>
            </div>
            <div class="col">
            </div>
            <div class="col">
                <div class="card-body">
                    <input type="date" name="startDate" value="${startDate}" min="${startDate}">
                </div>
            </div>
            <div class="col">
                <div class="card-body">
                    <button type="submit" class="btn btn-lg btn-primary">Continue</button>
                </div>
            </div>
        </div>
<%
    }
%>
</form>
<form class="container-fluid" action="confirm" method="get">

<%
    if (optServices !=null){
%>
    <div class="row d-flex justify-content-start card-strip">
        <div class="card-title">
            <h5>Choose optional services</h5>
        </div>
        <%--<div class="form-check form-check-inline" >
            <input class="form-check-input" type="checkbox" name="chosenOpt" id="opt<%=opt.getIdOptService()%>" value="0" checked>
        </div>--%>
        <div class="col-9">
            <%for (OptserviceEntity opt: optServices) {%>
            <div class="form-check form-check-inline" >
                <input class="form-check-input" type="checkbox" name="chosenOpt" id="opt<%=opt.getIdOptService()%>" value="<%=opt.getIdOptService()%>">
                <label class="form-check-label" for="opt<%=opt.getIdOptService()%>">
                    <%=opt.getName()%>
                </label>
            </div>
            <% } %>
        </div>
        <div class="col-3 ">
            <div class="card-body position-relative bottom-0 end-0">
                <button type="submit" class="btn btn-lg btn-primary">Confirm</button>
            </div>
        </div>
    </div>
<%
    }
%>

</form>


</body>
</html>
