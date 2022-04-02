    <%@ page import="it.polimi.progettodb2.entities.PackageEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
    <title>Home-Telco</title>
</head>

<%
    List<PackageEntity> packageEntityList = (List<PackageEntity>) request.getSession().getAttribute("packages");
%>

<body>
    <div class="container d-flex flex-wrap">
        <a class="navbar-brand" href="">TELCO COMPANY</a>
    </div>

    <div class="container-fluid">
        <div class="container">

            <hr>
            <div class="row">
                <div class="col-md-5">
                    <form role="form" method="post" action="signup">
                        <fieldset>
                            <p class="text-uppercase pull-center">SIGN UP</p>
                            <div class="form-group">
                                <input type="text" name="username" class="form-control input-lg" placeholder="username" required>
                            </div>

                            <div class="form-group">
                                <input type="text" name="email" class="form-control input-lg" placeholder="email" required>
                            </div>
                            <div class="form-group">
                                <input type="password" name="password" class="form-control input-lg" placeholder="password" required>
                            </div>

                            <div class="row-md-2">
                                <br>${messageSignUp}
                            </div>
                            <button type="submit" class="btn btn-lg btn-primary">Sign Up</button>

                        </fieldset>
                    </form>
                </div>

                <div class="col-md-2">
                </div>

                <div class="col-md-5">
                    <form role="form" method="post" action="login">
                        <fieldset>
                            <p class="text-uppercase"> Login using your account: </p>

                            <div class="form-group">
                                <input type="text" name="username"  class="form-control input-lg" placeholder="username" required>
                            </div>
                            <div class="form-group">
                                <input type="password" name="password"  class="form-control input-lg" placeholder="password" required>
                            </div>
                            <br>${messageLogin}

                            <button type="submit" class="btn btn-lg btn-primary">Login</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>

    </div>


    <form class="container-fluid px-1 px-sm-4 py-5 mx-auto" action="buy" method="post">

        <%
            for (PackageEntity pack: packageEntityList) {
        %>
        <div class="row d-flex justify-content-start card-strip">
            <div class="col-3">
                <div>
                    <div class="row px-3 mb-2">
                        <h4 class="dark-text mr-4"><%=pack.getName()%></h4>
                    </div>


                    <div class="row p-4 "></div>
                    <div class="row px-3">
                        <h4 class="blue-text mr-2">$ <%=String.format("%.2f",pack.getPrice12M()*0.8)%></h4>
                        <p class="mt-1 price-fall mr-5"><del>$ <%=String.format("%.2f",pack.getPrice12M())%></del></p>
                    </div>
                    <button class="btn btn-orange mt-4" type="submit" name="chosenPack" value="<%=pack.getIdPackage()%>">Get started</button>
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
        %>

    </form>

</body>
</html>