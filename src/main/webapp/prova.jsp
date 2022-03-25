<%--
  Created by IntelliJ IDEA.
  User: Giuseppe
  Date: 10/03/2022
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="it.polimi.progettodb2.entities.PackageEntity" %>
<%@ page import="java.util.List" %>
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
    <title>Home-Telco</title>
    <script>
        history.forward();
    </script>
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
                    <!-------null------>
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
                    <div class="info">
                        <div class="row px-3 mb-2">
                            <h4 class="dark-text mr-4"><%=pack.getName()%></h4>

                        </div>

                        <div class="row px-3">
                            <% if (pack.getMinute() != null) { %>
                            <div class="col-md-2"><%=pack.getMinute()%> Minuti</div>
                            <% } %>
                            <div class="col-md-2">
                                <% if (pack.getSms() != null) { %>
                                <div class="row"><%=pack.getSms()%> Sms</div>
                                <% } %>
                                <% if (pack.getExtraSms() != null) { %>
                                <div class="row"><%=pack.getExtraSms()%> ExtraSms</div>
                                <% } %>
                            </div>

                            <div class="col-md-2">
                                <% if (pack.getmGiga() != null) { %>
                                <div class="row"><%=pack.getmGiga()%> Giga</div>
                                <% } %>
                                <% if (pack.getExtraMGiga() != null) { %>
                                <div class="row"><%=pack.getExtraMGiga()%> ExtraGiga</div>
                                <% } %>
                            </div>

                            <div class="col-md-1">
                                <!-------null------>
                            </div>


                            <div class="col-md-2">
                                <% if (pack.getfGiga() != null) { %>
                                <div class="row"><%=pack.getfGiga()%> Fisso Giga</div>
                                <% } %>
                                <% if (pack.getExtraFGiga() != null) { %>
                                <div class="row"><%=pack.getExtraFGiga()%> ExtraGiga Fisso </div>
                                <% } %>
                            </div>


                        </div>



                    </div>
                    <div class="v-line ml-auto"></div>
                    <div class="price">
                        <div class="row px-3">

                        </div>
                        <div class="row px-3">
                            <h4 class="blue-text mr-2">$ 380,00</h4>
                            <p class="mt-1 price-fall mr-5"><del>$ 760,00</del></p>
                        </div>
                        <button class="btn btn-orange mt-4" type="submit" name="chosenPack" value="<%=pack.getIdPackage()%>">Get started</button>
                    </div>
                </div>
                <%
                    }
                %>


    </form>

</body>
</html>