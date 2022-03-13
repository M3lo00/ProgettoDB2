<%@ page import="it.polimi.progettodb2.entities.EmployeeEntity" %>
<%@ page import="it.polimi.progettodb2.entities.OptserviceEntity" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: giuseppegiovia
  Date: 13/03/22
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Home-employee</title>
</head>
<body>

<%


    EmployeeEntity employee = null;
    String employeeUsername = null;
    if(request.getSession().getAttribute("employee")!=null){
        employee = (EmployeeEntity) request.getSession().getAttribute("employee");
        employeeUsername = employee.getUsername();
    }
    List<OptserviceEntity> findAllOptionalProduct = (List<OptserviceEntity>) request.getAttribute("findAllOptionalProduct");
%>





<div class="container d-flex flex-wrap">
    <a class="navbar-brand" href="#">TELCO COMPANY</a>
    <ul class="nav me-auto">
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2">Buy Service</a></li>
    </ul>
    <ul class="nav">
        <% if (employee!=null) { %>
        <li class="nav-item"><a href="#" class="nav-link link-dark px-2" value="<%=employee.getIdEmployee()%>"><%=employee.getUsername()%></a></li>
        <li class="nav-item"><a class="nav-link font-weight-bold px-2" href="${pageContext.request.contextPath}/logout">Logout</a></li>
        <% } %>
    </ul>
</div>

<%-- Name, SMs, minute, mGiga, extraMGiga, extraSms, fixedPhone, fGiga, extraFgiga --%>
<div class="container-fluid px-1 px-sm-4 py-5 col-md-6">
        <h4 class="dark-text mr-4">Create a new Service Package</h4>
    <form>
        <div class="form-row mb-3">
            <div class="form-group ">
                <label for="packageName">Package Name</label>
                <input type="text" class="form-control" id="packageName" placeholder="Es. RED Pro" required>

            </div>
        </div>
        <label for="packageName">SMS</label>
        <div class="input-group mb-3">
            <div class="input-group-text">
                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
            </div>
            <input type="number" id="quantity" name="quantity" min="1" class="form-control" aria-label="Text input with checkbox" required>
        </div>
        <label for="packageName">Minute</label>
        <div class="input-group mb-3">
            <div class="input-group-text">
                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
            </div>
            <input type="number" id="quantity1" name="quantity" min="1" class="form-control" aria-label="Text input with checkbox" required>
        </div>
        <label for="packageName">Giga Mobile</label>
        <div class="input-group mb-3">
            <div class="input-group-text">
                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
            </div>
            <input type="number" id="quantity2" name="quantity" min="1" class="form-control" aria-label="Text input with checkbox"required>
        </div>
        <label for="packageName">Extra Giga Mobile</label>
        <div class="input-group mb-3">
            <div class="input-group-text">
                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
            </div>
            <input type="number" id="quantity3" name="quantity" min="1" class="form-control" aria-label="Text input with checkbox" required>
        </div>
        <label for="packageName">Extra SMS</label>
        <div class="input-group mb-3">
            <div class="input-group-text">
                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
            </div>
            <input type="number" id="quantity4" name="quantity" min="1" class="form-control" aria-label="Text input with checkbox" required>
        </div>
        <label for="packageName">Extra Giga Mobile</label>
        <div class="input-group mb-3">
            <div class="input-group-text">
                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
            </div>
            <input type="number" id="quantity5" name="quantity" min="1" class="form-control" aria-label="Text input with checkbox" required>
        </div>
        <label for="packageName">Giga Fixed</label>
        <div class="input-group mb-3">
            <div class="input-group-text">
                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
            </div>
            <input type="number" id="quantity6" name="quantity" min="1" class="form-control" aria-label="Text input with checkbox" required>
        </div>
        <label for="packageName">Extra Giga Fixed</label>
        <div class="input-group mb-3">
            <div class="input-group-text">
                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
            </div>
            <input type="number" id="quantity7" name="quantity" min="1" class="form-control" aria-label="Text input with checkbox" required>
        </div>
        <label for="packageName">Fixed Phone</label>
        <div class="input-group mb-3">
            <div class="input-group-text">
                <input class="form-check-input mt-0" type="checkbox" value="" aria-label="Checkbox for following text input">
            </div>
            <input id="quantity8"  class="form-control" aria-label="Text input with checkbox" disabled>
        </div>


        <%for (OptserviceEntity opt: findAllOptionalProduct) {%>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" id="opt<%=opt.getIdOptService()%>" value="<%=opt.getName()%>">
                <label class="form-check-label" for="opt<%=opt.getIdOptService()%>">
                    <%=opt.getName()%>
                </label>
            </div>
        <% } %>
        <br/>
        <br/>
        <label for="packageName">Price/Month</label>
        <div class="input-group mb-3">
            <span class="input-group-text">$</span>
            <span class="input-group-text">0.00</span>
            <input type="number" name="quantity" min="1" class="form-control" aria-label="Dollar amount (with dot and two decimal places)" required>
        </div>






        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>


<div class="container-fluid px-1 px-sm-4 py-5 col-md-6">
    <h4 class="dark-text mr-4">Create a new Optional Product</h4>
    <form>
        <div class="form-row mb-3">
            <div class="form-group ">
                <label for="packageName">Product Name</label>
                <input type="text" class="form-control" id="packageName1" placeholder="Es. Spotify" required>

            </div>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text">$</span>
            <span class="input-group-text">0.00</span>
            <input type="number" name="quantity" min="1" class="form-control" aria-label="Dollar amount (with dot and two decimal places)" required>
        </div>

        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>

</body>
</html>
