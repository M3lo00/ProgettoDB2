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
    <title>Hello, world!</title>
</head>


<%
    List<PackageEntity> packageEntityList = (List<PackageEntity>) request.getSession().getAttribute("packages");
%>





<body>

    <div class="container d-flex flex-wrap">
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        <a class="navbar-brand" href="#">TELCO COMPANY</a>
        <ul class="nav me-auto">
            <li class="nav-item"><a href="#" class="nav-link link-dark px-2">Buy Service</a></li>
        </ul>
        <ul class="nav">
            <li class="nav-item"><a href="#" class="nav-link link-dark px-2">Login</a></li>

        </ul>
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
                                <input type="text" name="password" class="form-control input-lg" placeholder="password" required>
                            </div>
                            <br>${messageSignUp}
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






    <div class="container-fluid px-1 px-sm-4 py-5 mx-auto">


                <%
                    for (PackageEntity pack: packageEntityList) {
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
                        <div class="btn btn-orange mt-4">Get started</div>
                    </div>
                </div>
<<<<<<< Updated upstream
                <div class="btn btn-orange mt-4">Get started</div>
            </div>
        </div>
=======
                <%
                    }
                %>


>>>>>>> Stashed changes
    </div>
<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
-->
</body>
</html>