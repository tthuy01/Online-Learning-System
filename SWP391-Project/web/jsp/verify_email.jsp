<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Verification Email</title>
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
                            <span style="width: 200px; font-weight: bold;">Verification email</span>
                        </div>
                        <form id="LoginForm" style="text-align: left;" action="confirmemail" method="post">
                            <div class="error" style="margin: 0 0 30px 0">
                                <c:if test="${error != null}">
                                    <p style="color: black; background: rgba(255, 0, 0, 0.2); text-align: center; padding: 2% 0; font-weight: bold">${error}</p>
                                </c:if>
                            </div>
                            VERIFICATION CODE
                            <input type="text" placeholder="Enter your verification code" name="code" required> 
                            <input type="submit" value="Check">
                            <input type="button" value="Back to Register" onclick="window.location.href = 'register'">
                        </form>
                        <button hidden id="myModel" type="button" data-toggle="modal" data-target="#myCf"></button>
                        <div id="myCf" class="modal fade">
                            <div class="modal-dialog">
                                <div class="modal-content" style="margin-top: 200px">
                                    <div class="modal-header">
                                        <h3 class="modal-title" style="color: green">Notification</h3>
                                        <a href="confirmemail" data-dismiss="modal" aria-hidden="true" class="close">×</a>
                                    </div>
                                    <div class="modal-body">
                                        <p style="text-align: left">Verified mail: <i style="color: #3C99DC80; font-weight: bold">${Suc}</i> successfully. Now, checking your mail to receive your password.
                                            <br/><br/>Please click 'Continue' to login!</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button data-dismiss="modal" aria-hidden="true" class="btn btn-primary" onclick="window.location.href = 'login'">Continue</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        <c:if test="${Suc != null}">
        window.onload = function () {
            document.getElementById('myModel').click();
        }
        </c:if>
    </script>
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