<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
          integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style type="text/css">
        <%@include file="../css/account.css"%>
    </style>
</head>

<body>
    <div class="account-page mt-5">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="form-container">
                        <div>
                            <span style="width: 200px; font-weight: bold">Forgot password</span>
                            <p style="width: 400px; font-size: larger">Enter recovery email</p>
                        </div>
                        <form id="LoginForm" style="text-align: left;" action="forgotpass" method="post">
                            <div class="error" style="margin: 0 0 30px 0">
                                <c:if test="${requestScope.error != null}">
                                    <p style="color: black; text-align: center; margin-bottom: 5%; background: rgba(255, 0, 0, 0.2); 
                                       padding-top: 2%; padding-bottom: 2%;"><b>${requestScope.error}</b></p>
                                </c:if>
                            </div>
                            EMAIL
                            <input type="email" placeholder="name@example.com" name="email">
                            <input type="submit" value="Confirm">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
crossorigin="anonymous"></script>
<script src="../js/login.js"></script>
</html>