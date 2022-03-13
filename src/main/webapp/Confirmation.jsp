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
</head>


<%
    List<PackageEntity> packageEntityList = (List<PackageEntity>) request.getSession().getAttribute("packages");
%>

<body>


<div class="container d-flex flex-wrap">
    <a class="navbar-brand" href="">TELCO COMPANY</a>
</div>

<div>
    Summary
</div>
<div class="container">
    <form class="row g-3 needs-validation" novalidate>
        <div class="col-md-6">
            <label for="validationCustom01" class="form-label">Card Number</label>
            <input type="text" class="form-control" id="validationCustom01" required>
        </div>
        <div class="col-md-2">
            <label for="validationCustom02" class="form-label">Expiration Month</label>
            <select class="form-select" id="validationCustom02" required>
                <option selected disabled value="">Choose...</option>
                <%for(int i=1;i<8;i++){
                %>
                <option><%=2021+i%></option>
                <%}
                %>
            </select>
            <div class="invalid-feedback">
                Please select a valid state.
            </div>
        </div>
        <div class="col-md-2">
            <label for="validationCustom03" class="form-label">Expiration Year</label>
            <select class="form-select" id="validationCustom03" required>
                <option selected disabled value="">Choose...</option>
                <%for(int i=1;i<=12;i++){
                %>
                <option><%=i%></option>
                <%}
                %>
            </select>
                <div class="invalid-feedback">
                Please select a valid state.
            </div>
        </div>
        <div class="col-md-2">
            <label for="validationCustom04" class="form-label">CVC</label>
            <input type="number" class="form-control" id="validationCustom04" min="100" max="999" required>
            <div class="invalid-feedback">
                Please provide a valid zip.
            </div>
        </div>
        <div class="col-12">
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                <label class="form-check-label" for="invalidCheck">
                    Agree to terms and conditions
                </label>
                <div class="invalid-feedback">
                    You must agree before submitting.
                </div>
            </div>
        </div>
        <div class="col-12">
            <button class="btn btn-primary" type="submit">Submit form</button>
        </div>
    </form>
</div>

</body>
</html>
