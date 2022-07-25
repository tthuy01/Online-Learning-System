<%-- 
    Document   : addnewregistration
    Created on : Jun 29, 2022, 4:14:59 PM
    Author     : Tran Thi Thanh Thuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new registration</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style type="text/css">
            <%@include file="../css/dashboard.css"%>
            <%@include file="../css/header.css"%>
            #right{
                background: white;
            }
        </style>
        <script src="/js/dashboard.js"></script>
        <script src="ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <section id="wrapper-dashboard" class="row container-fluid mx-auto px-0">
            <div id="left" class="col-lg-2 col-md-4 col-12 px-0" style="padding-bottom: 130px;">
                <c:forEach items="${listRole}" var="listRole">
                    <c:if test="${sessionScope.user.rid==listRole.id}">
                        <a href="" id="logo">${listRole.name}</a>
                    </c:if>
                </c:forEach>
                <div class="sidebar">
                    <div class="user-panel d-flex justify-content-start mt-3 ml-4"">
                        <div class="panel-left mr-3">
                            <img src="images/avatar/${sessionScope.user.uimg}" alt="" class="img-fluid">
                        </div>
                        <div class="panel-right">
                            <p>Hello, ${sessionScope.user.ufullname}</p>
                            <a href="" style="font-size: 12px">
                                <i class="fa fa-circle text-success"></i>
                                Online
                            </a>
                        </div>
                    </div>
                    <ul class="sidebar-menu mt-4">
                        <li>
                            <a href="dashboard">
                                <i class="fa fa-dashboard"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li>
                            <a href="postlist">
                                <i class="fa fa-dashboard"></i>
                                <span>Post List</span>
                            </a>
                        </li>
                        <li style="background-color: #425164;">
                            <a href="subjectlist">
                                <i class="fa fa-dashboard"></i>
                                <span>Subject List</span>
                            </a>
                        </li>
                        <li>
                            <a href="quizlist">
                                <i class="fa fa-dashboard"></i>
                                <span>Quiz List</span>
                            </a>
                        </li>
                        <li>
                            <a href="registrationlist">
                                <i class="fa fa-dashboard"></i>
                                <span>Registration List</span>
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
            <div id="right" class="col-lg-10 col-md-8 col-12" style="padding-bottom: 130px;">
                <jsp:include page="header_admin.jsp"/>
                <div class="registration-container" style="margin-top: 35px;">
                    <div class="title-registration">
                        <!--<h4 style="width: 80%; float: left;"><a href="#">Dashboard</a> <b>></b> <a href="registrationlist">All List</a> <b>></b> List of registrations</h4>-->
                        <h4 style="text-align: center;">Add new registration</h4>
                        <div class="addButton" style="float: right;">
                            <!-- Button trigger modal -->
                            <!--<button type="button" class="btn btn-primary" onclick="window.location = 'addnewregistration'">Add new</button>-->
                        </div>                    
                        <div class="clear"></div>
                    </div>
                    <div class="row" style="margin-top: 2%;">
                        <!--                        <div class="col-md-12" style="color: red;">
                                                    123456789123456789
                                                </div>-->
                        <div class="col-md-12">
                            <c:if test="${error != null}">
                                <div style="color: red; text-align: center;">
                                    <b>${error}</b>
                                </div>
                            </c:if>                         
                            <c:if test="${ms != null}">
                                <div style="color: green; text-align: center;">
                                    <b>${ms}</b>
                                </div>
                            </c:if>                         
                            <div style="margin-top: 2%;">
                                <form action="addnewregistration" method="post" class="d-flex justify-content-center">
                                    <table>
                                        <tr>
                                            <td>User email</td>
                                            <td><input type="text" name="email" value="${semail}" style="width: 100%;"></td>
                                        </tr>
                                        <tr>
                                            <td>Course</td>
                                            <td>
                                                <select name="course" style="width: 100%;">
                                                    <c:forEach items="${listCourse}" var="lc">
                                                        <option value="${lc.cid}" ${lc.cid == scid ? "selected":""}>${lc.cname}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Price package</td>
                                            <td>
                                                <select name="pack" style="width: 100%;">
                                                    <c:forEach items="${listPackagePrice}" var="lpp">
                                                        <option value="${lpp.packid}" ${spid == lpp.packid ? "selected":""}>${lpp.packname}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Note</td>
                                            <td>
                                                <!--ckeditor-->
                                                <textarea id="note" name="note">
                                                    ${snote}
                                                </textarea>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="text-align: center;">
                                                <button type="button" class="btn btn-primary btn-sm" id="button" onclick="this.form.submit()">Add new</button>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
    <script type="text/javascript">
        function MySide() {
            var right = document.getElementById('right');
            if (document.getElementById('left').style.display != "none") {
                document.getElementById('left').style.display = "none";
                right.classList.replace("col-lg-10", "col-lg-12");
                // right.classList.add("col-lg-12");
            } else {
                document.getElementById('left').style.display = "block";
                right.classList.replace("col-lg-12", "col-lg-10");
            }
        }

        <c:if test="${mspp != null}">
        window.onload = function () {
            document.getElementById('pricepackage-tab').click();
        }
        </c:if>
    </script>
    <script>
        var note = CKEDITOR.replace('note');
    </script>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>
</html>
