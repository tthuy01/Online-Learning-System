<%-- 
    Document   : registration_detail
    Created on : Jun 28, 2022, 1:14:29 PM
    Author     : Tran Thi Thanh Thuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Detail</title>
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
            <div id="left" class="col-lg-2 col-md-4 col-12 px-0" style="padding-bottom: 230px;">
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
            <div id="right" class="col-lg-10 col-md-8 col-12" style="padding-bottom: 230px;">
                <jsp:include page="header_admin.jsp"/>
                <div class="registration-detail-content container" style="margin-top: 35px;">
                    <div class="title-registration row">
                        <div class="col-md-6">
                            <h4><a href="registrationlist">List of registration</a> <b>></b> Registration detail</h4>
                        </div>
                        <div class="addButton col-md-6">
                            <button style="float: right; margin-left: 2%;" type="button" class="btn btn-primary btn-sm" onclick="window.location = 'editregistration?rid=${registration.rid}'">Edit</button>
                            <button style="float: right;" type="button" class="btn btn-primary btn-sm" onclick="window.location = 'registrationlist'">Back</button>
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                    <div class="content-registration" style="margin-bottom: 2%; margin-top: 2%;">
                        <c:set var="r" value="${registration}"></c:set>
                        <c:set var="s" value="${subject}"></c:set>
                        <c:set var="cs" value="${costSale}"></c:set>
                        <c:set var="op" value="${orPrice}"></c:set>
                        <c:set var="u" value="${user}"></c:set>
                        <c:set var="vt" value="${validTo}"></c:set>
                        <c:set var="c" value="${course}"></c:set>
                        <c:set var="g" value="${gender}"></c:set>
                        <c:set var="rs" value="${registrationstatus}"></c:set>
                            <div class="user-information row">
                                <div class="user-profile col-md-6">
                                    <h4 style="text-align: center;">User's Information</h4>
                                    <table class="table">
                                        <tr>
                                            <td>Registration ID</td>
                                            <td>${r.rid}</td>
                                    </tr>
                                    <tr>
                                        <td>User name</td>
                                        <td>${u.ufullname}</td>
                                    </tr>
                                    <tr>
                                        <td>Gender</td>
                                        <td>${g.gname}</td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td>${u.uemail}</td>
                                    </tr>
                                    <tr>
                                        <td>Phone</td>
                                        <td>${u.uphone}</td>
                                    </tr>
                                    <tr>
                                        <td>Address</td>
                                        <td>${u.uaddress}</td>
                                    </tr>
                                </table>
        <!--                            <div>Registration ID: ${r.rid}</div>
                                <div>User name: ${u.ufullname}</div>
                                <div>Gender: ${g.gname}</div>
                                <div>Email: ${u.uemail}</div>
                                <div>Phone: ${u.uphone}</div>
                                <div>Address: ${u.uaddress}</div>-->
                            </div>
                            <div class="user-profile col-md-6">
                                <h4 style="text-align: center;">Course's Information</h4>
                                <table class="table">
                                    <tr>
                                        <td>Course</td>
                                        <td>${course.cname}</td>
                                    </tr>
                                    <tr>
                                        <td>Subject</td>
                                        <td>${subject.sname}</td>
                                    </tr>
                                    <tr>
                                        <td>Registration Time</td>
                                        <td>${r.rtime}</td>
                                    </tr>
                                    <tr>
                                        <td>Registration status</td>
                                        <td>${rs.rstatusName}</td>
                                    </tr>
                                    <tr>
                                        <td>Cost sale</td>
                                        <td>${costSale}</td>
                                    </tr>
                                    <tr>
                                        <td>Original price</td>
                                        <td>${orPrice}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <!--                <div>
                                            <div>
                                                <h4>Registration detail</h4>
                                                <span>Registration ID: ${r.rid}</span><br/>
                                                <span>User name: ${u.ufullname}</span><br/>
                                                <span>Gender: ${g.gname}</span><br/>
                                                <span>Email: ${u.uemail}</span><br/>
                                                <span>Phone: ${u.uphone}</span><br/>
                                                <span>Address: ${u.uaddress}</span><br/>
                                                <span>Course: ${course.cname}</span><br/>
                                                <span>Subject: ${subject.sname}</span><br/>
                                                <span>Registration Time: ${r.rtime}</span><br/>
                                                <span>Registration status: ${registrationstatus.rstatusName}</span><br/>
                                                <span>Cost sale: ${costSale}</span><br/>
                                                <span>Original price: ${orPrice}</span><br/>
                                                <span>Valid from: ${r.rtime}</span><br/>
                                                <span>Valid to: ${vt}</span><br/>
                        <c:if test="${r.rnote == null}"><span>Note: none</span><br/></c:if>
                        <c:if test="${r.rnote != null}"><span>Note: ${r.rnote}</span><br/></c:if>
                    </div>
                </div>
                <div class="img-content">
                    <img src="${c.cimg}" alt="${c.cname}">
                </div>-->
                        <div class="clear"></div>
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
