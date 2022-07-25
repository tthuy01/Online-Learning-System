<%-- 
    Document   : silder_list
    Created on : Jul 15, 2022, 9:42:20 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Slider List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style type="text/css">
            <%@include file="../css/dashboard.css"%>
            <%@include file="../css/slider_list.css"%>
        </style>
    </head>
    <body>
        <section id="wrapper-dashboard" class="row container-fluid mx-auto px-0">
            <div id="left" class="col-lg-2 col-md-4 col-12 px-0">
                <c:forEach items="${listRole}" var="listRole">
                    <c:if test="${sessionScope.user.rid==listRole.id}">
                        <a href="" id="logo">${listRole.name}</a>
                    </c:if>
                </c:forEach>
                <div class="sidebar">
                    <div class="user-panel d-flex justify-content-start mt-3 ml-4">
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
                        <li >
                            <a href="postlist">
                                <i class="fa fa-dashboard"></i>
                                <span>Post List</span>
                            </a>
                        </li>
                        <c:if test="${sessionScope.user.rid==6}">

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
                            <li>
                                <a href="registrationlist">
                                    <i class="fa fa-dashboard"></i>
                                    <span>Registration List</span>
                                </a>
                            </li>
                        </c:if>
                        <li style="background-color: #425164;">
                            <a href="sliderlist" >
                                <i class="fa fa-dashboard"></i>
                                <span>Sliders List</span>
                            </a>
                        </li>
                        <c:if test="${sessionScope.user.rid==6}">

                            <li >
                                <a href="userlist">
                                    <i class="fa fa-dashboard"></i>
                                    <span>User List</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>

            </div>
            <div id="right" class="col-lg-10 col-md-8 col-12">
                <!--header navbar-->
                <jsp:include page="../jsp/header_admin.jsp" />
                <div class="container-fluid mt-5 mb-3">
                    <h4>Sliders List</h4>
                </div>
                <div class="row container-fluid">
                    <div class="col-3">
                        <form id="forms" class="form-inline my-2 my-lg-0" method="get" action="sliderlist">
                            <input id="search" style="width: 200px" class="form-control mr-sm-2" type="search" name="search" value="${search}" placeholder="Search by title or backlink" aria-label="Search">
                            <p class="btn btn-outline-primary my-2 my-sm-0" onclick="MyChange()"><i
                                    class="fa-solid fa-magnifying-glass"></i> </p><br>
                            <div>
                                <p style="font-size: 25px"><i class="fa-solid fa-filter mt-4"></i> Filter By:</p>
                                <h5 style="color: blue">Status</h5>
                                <input id="show" type="checkbox" onclick="MyChange1()" name="show" ${show=="on"?"checked":""} value="${show}"/> Show<br>
                                <input id="hide" type="checkbox" onclick="MyChange2()" name="hide" ${hide=="on"?"checked":""} value="${hide}"/> Hide
                                <input hidden name="side" id="side" value="${side}"/>
                            </div>
                        </form>
                    </div>
                    <div class="col-9">
                        <c:if test="${listSlide.size()==0}">
                            <h2 style="text-align: center;color: red">Not Found</h2>
                        </c:if>
                        <c:if test="${listSlide.size()!=0}">
                            <table class="table">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">Id</th>
                                        <th scope="col">Title</th>
                                        <th scope="col">Image</th>
                                        <th scope="col">Backlink</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listSlide}" var="listSlide">
                                        <tr>
                                            <th onclick="window.location.href='sliderdetails?id=${listSlide.id}'" scope="row">${listSlide.id}</th>
                                            <td onclick="window.location.href='sliderdetails?id=${listSlide.id}'">${listSlide.title}</td>
                                            <td onclick="window.location.href='sliderdetails?id=${listSlide.id}'"><img style="width: 160px" src="./images/slider/${listSlide.image}"/></td>
                                            <td onclick="window.location.href='sliderdetails?id=${listSlide.id}'">${listSlide.link}</td>
                                            <td><label class="switch">
                                                    <input id="${listSlide.id}" onclick="MySwitch(this)" data-toggle="modal" data-target="#exampleModal" value="${listSlide.status}" type="checkbox" ${listSlide.status?"checked":""}>
                                                    <span class="slider round"></span>
                                                </label></td>
                                            <td><a href="editslider?id=${listSlide.id}" style="font-size: 25px"><i class="fa-solid fa-pen-to-square"></i></a></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <nav class="d-flex justify-content-center" aria-label="...">
                                <ul class="pagination">
                                    <!--                                <li class="page-item disabled">
                                                                        <a class="page-link" href="#" tabindex="-1">Previous</a>
                                                                    </li>-->
                                    <c:forEach var="i" begin="1" end="${numPage}">
                                        <li class="page-item ${index==i?'active':''}">
                                            <a class="page-link" href="sliderlist?search=${search}&show=${show}&hide=${hide}&index=${i}&side=${side}">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <!--                                <li class="page-item">
                                                                        <a class="page-link" href="#">Next</a>
                                                                    </li>-->
                                </ul>
                            </nav>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="exampleModal" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Slider Status</h5>
                            <button type="button" onclick="MyClose()" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Do you want to change this status?
                        </div>
                        <div class="modal-footer">
                            <button type="button" onclick="MyClose()" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" onclick="MyEdit()">Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
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
    <script type="text/javascript">
        <c:if test="${side == 'hide'}">
                                document.getElementById('left').style.display = "none";
                                right.classList.replace("col-lg-10", "col-lg-12");
        </c:if>
                                var cur;
                                function  MySwitch(a) {
                                    cur = a.id;
                                }

                                function MyClose() {
                                    var curSwitch = document.getElementById(cur);
                                    if (curSwitch.value === "true") {
                                        curSwitch.checked = true;
                                    } else {
                                        curSwitch.checked = false;
                                    }
                                }
                                function MySide() {
                                    var right = document.getElementById('right');
                                    if (document.getElementById('left').style.display != "none") {
                                        document.getElementById('left').style.display = "none";
                                        right.classList.replace("col-lg-10", "col-lg-12");
                                        document.getElementById('side').value = 'hide';
                                    } else {
                                        document.getElementById('left').style.display = "block";
                                        right.classList.replace("col-lg-12", "col-lg-10");
                                        document.getElementById('side').value = 'show';
                                    }
                                }
                                function MyEdit() {
                                    var search = document.getElementById('search').value;
                                    var s = document.getElementById('show').value;
                                    var h = document.getElementById('hide').value;
                                    var side = document.getElementById('side').value;
                                    window.location.href = "sliderlist?search=" + search + "&show=" + s + "&hide=" + h + "&index=" + ${index} + "&side=" + side + "&id=" + cur;
                                }
                                function MyChange() {
                                    var search = document.getElementById('search').value;
                                    var s = document.getElementById('show').value;
                                    var h = document.getElementById('hide').value;
                                    var side = document.getElementById('side').value;
                                    window.location.href = "sliderlist?search=" + search + "&show=" + s + "&hide=" + h + "&side=" + side;
                                }
                                function MyChange1() {
                                    var show = document.getElementById('show').value;
                                    if (show === "on") {
                                        document.getElementById('show').value = "off";
                                    } else {
                                        document.getElementById('show').value = "on";
                                    }
                                    MyChange();
                                }

                                function MyChange2() {
                                    var hide = document.getElementById('hide').value;
                                    if (hide === "on") {
                                        document.getElementById('hide').value = "off";
                                    } else {
                                        document.getElementById('hide').value = "on";
                                    }
                                    MyChange();
                                }

    </script>
</html>
