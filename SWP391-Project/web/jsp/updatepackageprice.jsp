<%-- 
    Document   : updatepackageprice
    Created on : Jul 15, 2022, 11:47:26 PM
    Author     : Tran Thi Thanh Thuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update price package</title>
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
                        <li >
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
                        <li>
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
                            <a href="queslist">
                                <i class="fa fa-dashboard"></i>
                                <span>Question List</span>
                            </a>
                        </li>
                        <li style="background-color: #425164;">
                            <a href="registrationlist">
                                <i class="fa fa-dashboard"></i>
                                <span>Registration List</span>
                            </a>
                        </li>
                        <li>
                            <a href="sliderlist">
                                <i class="fa fa-dashboard"></i>
                                <span>Sliders List</span>
                            </a>
                        </li>
                        <li >
                            <a href="userlist">
                                <i class="fa fa-dashboard"></i>
                                <span>User List</span>
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
            <div id="right" class="col-lg-10 col-md-8 col-12" style="padding-bottom: 130px;">
                <jsp:include page="header_admin.jsp"/>
                <div class="registration-container" style="margin-top: 35px;">
                    <div class="title-add" style="margin-left: 2%; margin-bottom: 2%;">
                        <h4><a href="subjectdetails?cid=${cid}">Subject detail </a><b>></b> Update price package</h4>
                    </div>
                    <div class="ms">
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
                    </div>
                    <div class="content-add d-flex justify-content-center">
                        <form action="updatepricepackage?cid=${cid}" method="post">
                            <table>
                                <tr>
                                    <td>Package ID</td>
                                    <td><input type="text" name="id" value="${id}" style="width: 100%;" readonly=""></td>
                                </tr>
                                <tr>
                                    <td>Package name</td>
                                    <td><input type="text" name="name" value="${name}" style="width: 100%;"></td>
                                </tr>
                                <tr>
                                    <td>Duration</td>
                                    <td><input type="number" name="duration" value="${duration}" style="width: 100%;"></td>
                                </tr>
                                <tr>
                                    <td>Status</td>
                                    <td>
                                        <select name="status" style="width: 100%;">
                                            <option value="-1" ${status == "-1" ? "selected":""}>Choose status</option>
                                            <option value="0" ${status == "0" ? "selected":""}>Inactive</option>
                                            <option value="1" ${status == "1" ? "selected":""}>Active</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Multiple</td>
                                    <td><input type="text" name="mul" value="${mul}" style="width: 100%;"></td>
                                </tr>
                                <tr>
                                    <td>Description</td>
                                    <td>
                                        <!--ckeditor-->
                                        <textarea id="des" name="des" style="width: 100%;">
                                            ${des}
                                        </textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align: center;">
                                        <button type="button" class="btn btn-primary btn-sm" id="button" onclick="this.form.submit()">Update</button>
                                    </td>
                                </tr>
                            </table>
                        </form>
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
        var des = CKEDITOR.replace('des');
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
