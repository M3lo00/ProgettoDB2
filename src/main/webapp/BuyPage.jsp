<%@ page import="it.polimi.progettodb2.entities.PackageEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="it.polimi.progettodb2.entities.OptserviceEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <title>Hello, world!</title>

    <script>
        history.forward();
    </script>
</head>


<body>
<%
    List<PackageEntity> packageEntityList = (List<PackageEntity>) session.getAttribute("packages");
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

    <%}%>
</div>

<div class="d-flex justify-content-around my-3">
    <h2 >Chose the package that fits you the best</h2>
    <div></div>
</div>

<form class="container-fluid" action="buy" method="post">
    <%
        PackageEntity chosen= null;

        if (session.getAttribute("chosenPackObj")!=null) chosen= (PackageEntity) session.getAttribute("chosenPackObj");
        for (PackageEntity pack: packageEntityList) {
            if(chosen == null || pack == chosen){
    %>
    <div class="row d-flex justify-content-start card-strip">
        <div class="col-3">
            <div>
                <div class="row px-3 mb-2">
                    <h4 class="dark-text mr-4"><%=pack.getName()%></h4>
                </div>
                <div class="row p-4 "></div>
                <div class="row px-3">
                    <h4 class="blue-text mr-2">€ <%=String.format("%.2f",pack.getPrice12M()*0.8)%></h4>
                    <p class="mt-1 price-fall mr-5"><del>€ <%=String.format("%.2f",pack.getPrice12M())%></del></p>
                </div>
                <%if (chosen!=null){%>
                <button type="submit" class="btn btn-danger mt-4" name="reset" value="reset" > Change Package </button>
                <%}else{%>
                <button class="btn btn-orange mt-4" type="submit" name="chosenPack" value="<%=pack.getIdPackage()%>">Get started</button>
                <%}%>
            </div>
        </div>

        <div class="col-3">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">Mobile Service</h5>
                    <div class="row my-2">
                        <p class="bold card-title">Minutes</p>
                    </div>
                    <div class="row my-2">
                        <p class="card-text fw-bold"><%=(pack.getMinute()==null)? " ": pack.getMinute()%></p>
                    </div>
                    <div class="row my-2">
                        <p class="bold card-title">Sms </p>
                    </div>
                    <div class="row my-2">
                        <p class="card-text fw-bold"><%=(pack.getSms()==null)? " ": pack.getSms()%></p>
                    </div>
                    <div class="row my-2">
                        <p class="bold card-title">Giga</p>
                    </div>
                    <div class="row my-2">
                        <p class="card-text fw-bold"><%=(pack.getmGiga()==null)? " ": pack.getmGiga()%></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-3">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">Extra Fees</h5>
                    <div class="row my-2">
                        <p class="bold card-title">Extra minutes</p>
                    </div>
                    <div class="row my-2">
                        <p class="card-text fw-bold"><%=(pack.getExtraMinute()==null)? " ": pack.getExtraMinute()+"€/min"%></p>
                    </div>
                    <div class="row my-2">
                        <p class="card-title">Extra sms</p>
                    </div>
                    <div class="row my-2">
                        <p class="card-text fw-bold"><%=(pack.getExtraSms()==null)? " ": pack.getExtraSms()+"€/sms"%></p>
                    </div>
                    <div class="row my-2">
                        <p class="card-title">Extra GB</p>
                    </div>
                    <div class="row my-2">
                        <p class="card-text fw-bold"><%=(pack.getExtraMGiga()==null)? " ": pack.getExtraMGiga()+"€/GB"%></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-3">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">Fixed Service</h5>
                    <div class="row my-2">
                        <p class="bold card-title">Phone</p>
                    </div>
                    <div class="row my-2">
                        <p class="card-text fw-bold"><%=(pack.getFixedPhone()==null)? "Not Included": "Included"%></p>
                    </div>
                    <div class="row my-2">
                        <p class="card-title">Internet</p>
                    </div>
                    <div class="row my-2">
                        <p class="card-text fw-bold"><%=(pack.getfGiga()==null)? " ": pack.getfGiga()+"€/GB"%></p>
                    </div>
                    <div class="row my-2">
                        <p class="card-title">Extra Giga</p>
                    </div>
                    <div class="row my-2">
                        <p class="card-text fw-bold"><%=(pack.getExtraFGiga()==null)? " ": pack.getExtraFGiga()+"€/GB"%></p>
                    </div>
                </div>
            </div>
        </div>
        <%if (pack.getOptService().size()!=0){%>
        <div class="col-12 pt-2">
            <div class="card h-40">
                <p><strong>Bundled Optional Products</strong> <%=pack.getListOptName()%></p>
            </div>
        </div>
        <%}%>
    </div>

    <%
            }
        }
        if (chosen!=null && session.getAttribute("chosenMonths")==null){
            double cost =  chosen.getPrice12M();
    %>

        <div class="row d-flex justify-content-start card-strip">
            <div class="col">
                <h5 class="card-title">Offer Duration</h5>
            </div>
            <div class="col">
                <div class="card h-80">
                    <div class="card-body">
                        <h5 class="card-title">Silver</h5>
                        <p class="card-text"><%= String.format("%.2f",cost) %>€/month</p>
                        <input id="chosen1" class="form-check-input stretched-link" type="radio" name="chosenMonths" value="12" <%if(session.getAttribute("chosenMonths")=="12"){%>checked<%}%>>
                        <label for="chosen1" class="form-check-label">12 Months</label>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-80">
                    <div class="card-body">
                        <h5 class="card-title">Gold</h5>
                        <p class="card-text"><%= String.format("%.2f",cost*0.9)%>€/month</p>
                        <input id="chosen2" class="form-check-input stretched-link" type="radio" name="chosenMonths" value="24" <%if(session.getAttribute("chosenMonths")=="24"){%>checked<%}%>>
                        <label for="chosen2" class="form-check-label">24 Months</label>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-80">
                    <div class="card-body">
                        <h5 class="card-title">Platinum</h5>
                        <p class="card-text"><%= String.format("%.2f",cost*0.8) %>€/month</p>
                        <input id="chosen3" class="form-check-input stretched-link" type="radio" name="chosenMonths" value="36" <%if(session.getAttribute("chosenMonths")=="36"){%>checked<%}%>>
                        <label for="chosen3" class="form-check-label">36 Months</label>
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
                    <input id="sDate" type="date" name="startDate" value="${startDate}" min="${startDate}">
                    <label for="sDate">choose a Date</label>
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
<form class="container-fluid" action="confirm" method="post">

<%
    if (optServices !=null){
        if ((optServices.size()!=0)){
%>
    <div class="row d-flex justify-content-start card-strip">
        <div class="card-title">
            <h5>Choose optional services</h5>
        </div>
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
    } else {
%>
    <div class="row d-flex justify-content-start card-strip">
        <div class="card-title">
            <h5>Continue to payment</h5>
        </div>
        <div class="col-9">

        </div>
        <div class="col-3 ">
            <div class="card-body position-relative bottom-0 end-0">
                <button type="submit" class="btn btn-lg btn-primary">Confirm</button>
            </div>
        </div>
    </div>
<%
        }
    }
%>
</form>


</body>
</html>
