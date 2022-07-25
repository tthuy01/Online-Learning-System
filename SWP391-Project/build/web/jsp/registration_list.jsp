<%-- 
    Document   : registration_list
    Created on : Jun 24, 2022, 3:38:03 PM
    Author     : Tran Thi Thanh Thuy
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration List</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style type="text/css">
            <%@include file="../css/registration_list.css"%>
            <%@include file="../css/dashboard.css"%>
            <%@include file="../css/header.css" %>
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.user.rid==6}">
            <section id="wrapper-dashboard" class="row container-fluid mx-auto px-0">
            <div id="left" class="col-lg-2 col-md-4 col-12 px-0" style="display: none;">
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
            <div id="right" class="col-lg-12 col-md-8 col-12">
                <!--header navbar-->
                <nav class="navbar navbar-expand-lg navbar-light bg-white py-2 row">
                    <a class="navbar-brand pl-3" href="#" onclick="MySide()"><i class="fa-solid fa-bars"></i></a>
                    <a class="navbar-brand" href="home">COURSERE</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <!--                        <form class="form-inline my-2 my-lg-0 ml-5" method="get" action="courselist">
                                                    <input class="form-control mr-sm-2" style="width: 250px" type="search" name="search" value="${search}" placeholder="Which course do you want" aria-label="Search">
                                                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                                                            class="fa-solid fa-magnifying-glass"></i> </button>
                                                </form>-->
                        <ul class="navbar-nav ml-auto">
                            <c:forEach items="${listBlog}" var="lb">
                                <li class="nav-item mr-2 ">
                                    <a style="margin: 3px 0 0 0" class="nav-link" href="bloglist?bid=${lb.bid}">${lb.bname}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${sessionScope.user == null}">
                                <li class="nav-item ml-5 mr-4">
                                    <a class="nav-link" style="border: 1px solid black;" href="login" >ACCOUNT</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.user != null}">   
                                <li class="nav-item dropdown">
                                    <a
                                        class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                        aria-expanded="false"
                                        >
                                        <img src="images/avatar/${sessionScope.user.uimg}" style="background-color: #3BAFDA; width: 37px; border-radius: 50%;">
                                        <span class="pl-1">${sessionScope.user.ufullname}</span>
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="mycourse" title="My Course">My Course</a>
                                        <a class="dropdown-item" href="myregistration" title="My Registration">My Registration</a>
                                        <a class="dropdown-item" data-toggle="modal" data-target=".bd-example-modal-lg" title="Profile">Profile</a>
                                        <a class="dropdown-item" href="#" title="Settings">Settings</a>
                                        <a class="dropdown-item" href="#" title="Help Center">Help Center</a>
                                        <div class="dropdown-divider"></div> 
                                        <a class="dropdown-item" href="changepassword" title="Change Password">Change Password</a>
                                        <a class="dropdown-item" href="logout" title="Logout">Logout</a>
                                    </div>
                                </li>
                            </c:if> 
                        </ul>
                    </div>
                </nav>
                <!--user_profile-->
                <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content" style="border-radius: 5%;">
                            <form action="userprofile" enctype="multipart/form-data" method="post">

                                <div class="d-flex justify-content-between align-items-start" style="margin: 15px; height: 100px">
                                    <h1>Profile</h1>
                                    <!--img user-->
                                    <div>
                                        <img id="img" src="images/avatar/${sessionScope.user.uimg}" alt="avatar user" style="height: 100px; width: 100px; border-radius: 50%;">
                                        <input type="file" name="uImg" accept="image/jpeg, image/png, image/jpg" style="height: 30px; width: 98px">
                                    </div>
                                    <div style="margin: 0 5px 0 0">
                                        <button class="btn btn-secondary" data-dismiss="modal">
                                            <span>Close</span>
                                        </button>
                                    </div>
                                </div>

                                <div class="d-flex justify-content-center">
                                    <div class="d-flex justify-content-center">

                                        <!--label input-->
                                        <div class="d-flex flex-column" style="margin: 0 30px 0 0;">
                                            <div class="d-flex justify-content-end" style="margin: 7px 0 0 0;">
                                                <p style="font-weight: bold;">Full Name</p>
                                            </div>
                                            <div class="d-flex justify-content-end" style="margin: 30px 0 0 0;">
                                                <p style="font-weight: bold;">Email</p>
                                            </div>
                                            <div class="d-flex justify-content-end" style="margin: 30px 0 0 0;">
                                                <p style="font-weight: bold;">Phone Number</p>
                                            </div>
                                            <div class="d-flex justify-content-end" style="margin: 30px 0 0 0;">
                                                <p style="font-weight: bold;">Birthday</p>
                                            </div>
                                            <div class="d-flex justify-content-end" style="margin: 30px 0 0 0;">
                                                <p style="font-weight: bold;">Gender</p>
                                            </div>
                                            <div class="d-flex justify-content-end" style="margin: 30px 0 0 0;">
                                                <p style="font-weight: bold;">Address</p>
                                            </div>
                                            <div class="d-flex justify-content-end" style="margin: 30px 0 0 0;">
                                                <p style="font-weight: bold;">Wallet</p>
                                            </div>              
                                        </div>
                                    </div>

                                    <div class="d-flex flex-column">
                                        <!--input Name-->
                                        <input name="uName" value="${requestScope.userEdit.ufullname != null ? requestScope.userEdit.ufullname : sessionScope.user.ufullname}" style="width: 400px;height: 40px;">  
                                        <!--input Email-->
                                        <div style="margin: 5px 0 0 0">
                                            <p style="font-style: italic; font-size: smaller;margin: 3px 0 0 0; color: #3333ff">You cannot edit your email.</p>
                                            <input name="uEmail" type="text" style="width: 400px;height: 40px;" readonly value="${sessionScope.user.uemail}">
                                        </div>

                                        <!--input Phone-->    
                                        <div style="margin: 30px 0 0 0;"> 
                                            <c:if test="${sessionScope.user.uphone != null}">
                                                <input name="uPhone" type="text" pattern="[0-9]{10,11}" title="Wrong format.Enter your phone." value="${requestScope.userEdit.uphone != null ? requestScope.userEdit.uphone : sessionScope.user.uphone}"  style="width: 400px;height: 40px;">
                                            </c:if>
                                            <c:if test="${sessionScope.user.uphone == null}">
                                                <input name="uPhone" type="text" placeholder="0123456789" pattern="[0-9]{10,11}" title="Wrong format.Enter your phone." value="${requestScope.userEdit.uphone != null ? requestScope.userEdit.uphone : ""}" style="width: 400px;height: 40px;">
                                            </c:if>
                                        </div>

                                        <!--input Dob-->
                                        <div style="margin: 30px 0 0 0;">
                                            <c:if test="${sessionScope.user.udob != null}">
                                                <input name="uDob" type="date" value="${requestScope.userEdit.udob != null ? requestScope.userEdit.udob : sessionScope.user.udob}" pattern="(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-\d{4}" style="width: 400px;height: 40px;">
                                            </c:if>
                                            <c:if test="${sessionScope.user.udob == null}">
                                                <input name="uDob" type="date" value="${requestScope.userEdit.udob != null ? requestScope.userEdit.udob : ""}"  placeholder="dd-mm-yyyy" id="uDob" pattern="(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-\d{4}" style="width: 400px;height: 40px;"> 
                                            </c:if>
                                        </div>

                                        <!--input Gender-->
                                        <div class="d-flex align-items-center" style="margin: 30px 0 0 0; width: 400px;height: 40px;">
                                            <input  name="genId" <c:if test="${(requestScope.userEdit.gid != null ? requestScope.userEdit.gid : sessionScope.user.gid) == 1}">checked</c:if> value="1" style="margin: 0 0.5vw 0 2vw;" type="radio">Male
                                            <input  name="genId" <c:if test="${(requestScope.userEdit.gid != null ? requestScope.userEdit.gid : sessionScope.user.gid) == 2}">checked</c:if>  value="2" style="margin: 0 0.5vw 0 2vw;;" type="radio">Female
                                            <input  name="genId" <c:if test="${(requestScope.userEdit.gid != null ? requestScope.userEdit.gid : sessionScope.user.gid) == 3}">checked</c:if> value="3" style="margin: 0 0.5vw 0 2vw;" type="radio">Other
                                            <input  name="genId" <c:if test="${(requestScope.userEdit.gid != null ? requestScope.userEdit.gid : sessionScope.user.gid) == 4}">checked</c:if> value="4" style="margin: 0 0.5vw 0 2vw;" type="radio">I'd rather not say                                                    
                                            </div>

                                            <!--input Address-->
                                            <div style="margin: 30px 0 0 0;">
                                            <c:if test="${sessionScope.user.uaddress != null}">
                                                <input name="uAddress" type="text" style="width: 400px;height: 40px;" value="${requestScope.userEdit.uaddress != null ? requestScope.userEdit.uaddress :sessionScope.user.uaddress}">
                                            </c:if>
                                            <c:if test="${sessionScope.user.uaddress == null}">
                                                <input name="uAddress" type="text" style="width: 400px;height: 40px;"  placeholder="Your Address" value="${requestScope.userEdit.uaddress != null ? requestScope.userEdit.uaddress :""}" >
                                            </c:if>
                                        </div>

                                        <!--input Wallet-->
                                        <div style="margin: 30px 0 0 0;">
                                            <c:if test="${sessionScope.user.uwallet != null}">
                                                <input name="uWallet" type="text" value="${requestScope.userEdit.uwallet != null ? requestScope.userEdit.uwallet : sessionScope.user.uwallet}" style="width: 400px;height: 40px;" >
                                            </c:if>
                                            <c:if test="${sessionScope.user.uwallet == null}">
                                                <input name="uWallet" type="text" placeholder="..." style="width: 400px;height: 40px;" value="${requestScope.userEdit.uwallet != null ? requestScope.userEdit.uwallet :""}">
                                            </c:if>    
                                        </div>

                                        <div style="margin:10px">
                                            <input class="btn btn-primary" style="width: 150px; height: 60px;margin: 1vw 3vw 0 3vw;" type="submit" value="Save change">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="registration-container" >
                    <div class="title-registration">
                        <!--<h4 style="width: 80%; float: left;"><a href="#">Dashboard</a> <b>></b> <a href="registrationlist">All List</a> <b>></b> List of registrations</h4>-->
                        <h3 style="width: 80%; float: left;">List of registrations</h3>
                        <div class="addButton" style="float: right;">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" onclick="window.location = 'addnewregistration'">Add new</button>
                        </div>                    
                        <div class="clear"></div>
                    </div>
                    <div class="row" style="margin-top: 2%;">
                        <!--Sider-->
                        <div class="sider col-md-2">
                            <form class="searchAndFilter" id="searchAndFilter" action="registrationlist" method="get">
                                <div class="search-block">
                                    <!--                            <div class="title">
                                                                    Search by Subject or Email
                                                                </div>-->
                                    <div class="search-box d-flex">
                                        <input class="form-control mr-sm-2" style="width: 80%;" type="search" name="search" value="${search}" placeholder="Enter Email, Subject or Course" aria-label="Search">
                                        <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                                                class="fa-solid fa-magnifying-glass"></i> </button>
                                    </div>
                                </div>

                                <div class="filter" style="margin-top: 5%;">
                                    <c:set var="lsc" value="${listSubjectCategory}"></c:set>
                                    <c:set var="ls" value="${listSubject}"></c:set>
                                    <c:set var="lpp" value="${listPricePackage}"></c:set>
                                    <c:set var="mp" value="${maxPrice}"></c:set>
                                    <c:set var="lrs" value="${listRegistrationStatus}"></c:set>

                                    <c:set var="cSubCate" value="${cSubCate}"></c:set>
                                    <c:set var="cSubject" value="${cSubject}"></c:set>
                                    <c:set var="cPricePackage" value="${cPricePackage}"></c:set>
                                    <c:set var="cRegistrationStatus" value="${cRegistrationStatus}"></c:set>
                                    <c:set var="cTotalCost" value="${cTotalCost}"></c:set>

                                    <c:set var="sortBy" value="${sortBy}"></c:set>

                                        <div class="sort-filter">
                                            <div class="title" style="float: left;">
                                                Sort <i class="fas fa-arrow-down"></i> by 
                                            </div>
                                            <div class="content-sort" style="float: left; margin-left: 6%;">
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <select name="sort" onchange="document.getElementById('searchAndFilter').submit();">
                                                                <option value="" ${sortBy eq "" ? "selected":""}>Default</option>
                                                            <option value="id" ${sortBy eq "id" ? "selected":""}>ID</option>
                                                            <option value="registrationTime" ${sortBy eq "registrationTime" ? "selected":""}>Registration Time</option>
                                                            <option value="course" ${sortBy eq "course" ? "selected":""}>Course</option>
                                                            <option value="subject" ${sortBy eq "subject" ? "selected":""}>Subject</option>
                                                            <option value="package" ${sortBy eq "package" ? "selected":""}>Package</option>
                                                            <option value="totalCost" ${sortBy eq 'totalCost' ? "selected":""}>Total cost</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="clear"></div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Subject Category
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:if test="${lsc.size() > 0}">
                                                    <c:forEach begin="0" end="${lsc.size()-1}" var="i">
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${lsc.get(i).subjectCateId}" name="subCate" id="subjectCaterogy"
                                                                       onclick="this.form.submit()" ${cSubCate[i]?"checked":""}>
                                                                <span>${lsc.get(i).subjectCateName}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Subject
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:if test="${ls.size() > 0}">
                                                    <c:forEach begin="0" end="${ls.size() - 1}" var="i">
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${ls.get(i).sid}" name="subject" id="subject"
                                                                       onclick="this.form.submit()" ${cSubject[i]?"checked":""}>
                                                                <span>${ls.get(i).sname}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Package
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:if test="${lpp.size() > 0}">
                                                    <c:forEach begin="0" end="${lpp.size()-1}" var="i">
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${lpp.get(i).packid}" name="package" id="package"
                                                                       onclick="this.form.submit()" ${cPricePackage[i]?"checked":""}>
                                                                <span>${lpp.get(i).packname}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Total cost
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:forEach begin="0" end="4" var="i">
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" value="${Math.ceil((mp/5)*i)} and ${Math.ceil((mp/5)*(i+1))}" name="totalCost" id="totalCost"
                                                                   onclick="this.form.submit()" ${cTotalCost[i]?"checked":""}>
                                                            <span>${Math.ceil((mp/5)*i)}$ - ${Math.ceil((mp/5)*(i+1))}$</span>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Status
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:if test="${lrs.size() > 0}">
                                                    <c:forEach begin="0" end="${lrs.size() - 1}" var="i">
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${lrs.get(i).rstatusId}" name="status" id="status"
                                                                       onclick="this.form.submit()" ${cRegistrationStatus[i]?"checked":""}>
                                                                <span>${lrs.get(i).rstatusName}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="search-time">
                                        <div class="title">
                                            Registration time
                                        </div>
                                        <div class="search-time">
                                            <c:set var="regTimeFrom" value="${regTimeFrom}"></c:set>
                                            <c:set var="regTimeTo" value="${regTimeTo}"></c:set>
                                                <table>
                                                    <tr>
                                                        <td>From</td>
                                                        <td><input type="date" name="regTimeFrom" id="regTimeFrom" value="${regTimeFrom}"></td>
                                                </tr>
                                                <tr>
                                                    <td>To</td>
                                                    <td><input type="date" name="regTimeTo" id="regTimeTo" value="${regTimeTo}"></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <div class="buttonSearch d-flex justify-content-center" style="width: 100%;">
                                                            <button type="button" class="btn btn-primary" onclick="searchFunction()">Search</button>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!--Content-->
                        <div class="content col-md-10" style="overflow: scroll;">
                            <c:set var="lr" value="${listRegistration}"></c:set>
                            <c:set var="lcn" value="${listCourseName}"></c:set>
                            <c:set var="lsn" value="${listSubjectName}"></c:set>
                            <c:set var="lpn" value="${listPackageName}"></c:set>
                            <c:set var="ltp" value="${listTotalPrice}"></c:set>
                            <c:set var="lvt" value="${listValidTo}"></c:set>
                            <c:set var="lu" value="${listUser}"></c:set>
                                <table class="table table-striped">
                                    <tr>
                                        <th>ID</th>
                                        <th>Email</th>
                                        <th>Course</th>
                                        <th>Subject</th>
                                        <th>Registration time</th>
                                        <th>Package</th>
                                        <th>Total cost</th>
                                        <th>Status</th>
                                        <th>Valid from</th>
                                        <th>Valid to</th>
                                        <th>Last updated by</th>
                                        <th>Action</th>
                                    </tr>
                                <c:if test="${lr.size() > 0}">
                                    <c:forEach begin="0" end="${lr.size()-1}" var="i">
                                        <tr>
                                            <td>${lr.get(i).rid}</td>
                                            <td>${lu.get(i).uemail}</td>
                                            <td>${lcn.get(i)}</td>
                                            <td>${lsn.get(i)}</td>
                                            <td>${lr.get(i).rtime}</td>
                                            <td>${lpn.get(i)}</td>
                                            <td>${ltp.get(i)}</td>
                                            <td>
                                                <c:if test="${lr.get(i).rstatusId == 0}">
                                                    Submitted
                                                </c:if>
                                                <c:if test="${lr.get(i).rstatusId == 1}">
                                                    Registered
                                                </c:if>
                                            </td>
                                            <td>${lr.get(i).rtime}</td>
                                            <td>${lvt.get(i)}</td>
                                            <td>${lr.get(i).lastUpBy}</td>
                                            <td><a href="registrationdetail?rid=${lr.get(i).rid}">View detail</a></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </table>

                            <c:if test="${requestScope.num == 0}">
                                <div style="text-align: left; font-size: 20px;">
                                    Not found the registration!
                                    <c:if test="${requestScope.ms != null}">
                                        <span style="color: red;">(${requestScope.ms})</span>
                                    </c:if>
                                </div>
                            </c:if> 

                            <!--Paging-->
                            <div class="paging-block d-flex justify-content-center" style="margin-top: 5%;">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination">
                                        <c:set var="page" value="${requestScope.page}"></c:set>
                                        <c:set var="subCates" value="${subCates}"></c:set>
                                        <c:set var="subjects" value="${subjects}"></c:set>
                                        <c:set var="packs" value="${packs}"></c:set>
                                        <c:set var="statuses" value="${statuses}"></c:set>
                                        <c:set var="costs" value="${costs}"></c:set>
                                        <c:if test="${requestScope.num != 0}">
                                            <c:if test="${search != null}">
                                                <li class="page-item ${page==1?"disabled":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${page-1}">Previous</a></li>
                                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${i}">${i}</a></li>
                                                    </c:forEach>
                                                <li class="page-item ${page==num?"disabled":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${page+1}">Next</a></li>
                                                </c:if>
                                                <c:if test="${search == null}">
                                                <li class="page-item ${page==1?"disabled":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${page-1}">Previous</a></li>
                                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${i}">${i}</a></li>
                                                    </c:forEach>
                                                <li class="page-item ${page==num?"disabled":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${page+1}">Next</a></li>
                                                </c:if>
                                            </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        </c:if>
        <c:if test="${sessionScope.user.rid==3}">
            <jsp:include page="header.jsp"></jsp:include>
            <div class="registration-container" style="margin-top: 110px" >
                    <div class="title-registration">
                        <!--<h4 style="width: 80%; float: left;"><a href="#">Dashboard</a> <b>></b> <a href="registrationlist">All List</a> <b>></b> List of registrations</h4>-->
                        <h3 style="width: 80%; float: left;">List of registrations</h3>
                        <div class="addButton" style="float: right;">
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" onclick="window.location = 'addnewregistration'">Add new</button>
                        </div>                    
                        <div class="clear"></div>
                    </div>
                    <div class="row" style="margin-top: 2%;">
                        <!--Sider-->
                        <div class="sider col-md-2">
                            <form class="searchAndFilter" id="searchAndFilter" action="registrationlist" method="get">
                                <div class="search-block">
                                    <!--                            <div class="title">
                                                                    Search by Subject or Email
                                                                </div>-->
                                    <div class="search-box d-flex">
                                        <input class="form-control mr-sm-2" style="width: 80%;" type="search" name="search" value="${search}" placeholder="Enter Email, Subject or Course" aria-label="Search">
                                        <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                                                class="fa-solid fa-magnifying-glass"></i> </button>
                                    </div>
                                </div>

                                <div class="filter" style="margin-top: 5%;">
                                    <c:set var="lsc" value="${listSubjectCategory}"></c:set>
                                    <c:set var="ls" value="${listSubject}"></c:set>
                                    <c:set var="lpp" value="${listPricePackage}"></c:set>
                                    <c:set var="mp" value="${maxPrice}"></c:set>
                                    <c:set var="lrs" value="${listRegistrationStatus}"></c:set>

                                    <c:set var="cSubCate" value="${cSubCate}"></c:set>
                                    <c:set var="cSubject" value="${cSubject}"></c:set>
                                    <c:set var="cPricePackage" value="${cPricePackage}"></c:set>
                                    <c:set var="cRegistrationStatus" value="${cRegistrationStatus}"></c:set>
                                    <c:set var="cTotalCost" value="${cTotalCost}"></c:set>

                                    <c:set var="sortBy" value="${sortBy}"></c:set>

                                        <div class="sort-filter">
                                            <div class="title" style="float: left;">
                                                Sort <i class="fas fa-arrow-down"></i> by 
                                            </div>
                                            <div class="content-sort" style="float: left; margin-left: 6%;">
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <select name="sort" onchange="document.getElementById('searchAndFilter').submit();">
                                                                <option value="" ${sortBy eq "" ? "selected":""}>Default</option>
                                                            <option value="id" ${sortBy eq "id" ? "selected":""}>ID</option>
                                                            <option value="registrationTime" ${sortBy eq "registrationTime" ? "selected":""}>Registration Time</option>
                                                            <option value="course" ${sortBy eq "course" ? "selected":""}>Course</option>
                                                            <option value="subject" ${sortBy eq "subject" ? "selected":""}>Subject</option>
                                                            <option value="package" ${sortBy eq "package" ? "selected":""}>Package</option>
                                                            <option value="totalCost" ${sortBy eq 'totalCost' ? "selected":""}>Total cost</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="clear"></div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Subject Category
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:if test="${lsc.size() > 0}">
                                                    <c:forEach begin="0" end="${lsc.size()-1}" var="i">
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${lsc.get(i).subjectCateId}" name="subCate" id="subjectCaterogy"
                                                                       onclick="this.form.submit()" ${cSubCate[i]?"checked":""}>
                                                                <span>${lsc.get(i).subjectCateName}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Subject
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:if test="${ls.size() > 0}">
                                                    <c:forEach begin="0" end="${ls.size() - 1}" var="i">
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${ls.get(i).sid}" name="subject" id="subject"
                                                                       onclick="this.form.submit()" ${cSubject[i]?"checked":""}>
                                                                <span>${ls.get(i).sname}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Package
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:if test="${lpp.size() > 0}">
                                                    <c:forEach begin="0" end="${lpp.size()-1}" var="i">
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${lpp.get(i).packid}" name="package" id="package"
                                                                       onclick="this.form.submit()" ${cPricePackage[i]?"checked":""}>
                                                                <span>${lpp.get(i).packname}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Total cost
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:forEach begin="0" end="4" var="i">
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" value="${Math.ceil((mp/5)*i)} and ${Math.ceil((mp/5)*(i+1))}" name="totalCost" id="totalCost"
                                                                   onclick="this.form.submit()" ${cTotalCost[i]?"checked":""}>
                                                            <span>${Math.ceil((mp/5)*i)}$ - ${Math.ceil((mp/5)*(i+1))}$</span>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="sub-filter">
                                        <div class="title">
                                            Status
                                        </div>
                                        <div class="content-filter">
                                            <table>
                                                <c:if test="${lrs.size() > 0}">
                                                    <c:forEach begin="0" end="${lrs.size() - 1}" var="i">
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" value="${lrs.get(i).rstatusId}" name="status" id="status"
                                                                       onclick="this.form.submit()" ${cRegistrationStatus[i]?"checked":""}>
                                                                <span>${lrs.get(i).rstatusName}</span>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                            </table>
                                        </div>
                                    </div>

                                    <div class="search-time">
                                        <div class="title">
                                            Registration time
                                        </div>
                                        <div class="search-time">
                                            <c:set var="regTimeFrom" value="${regTimeFrom}"></c:set>
                                            <c:set var="regTimeTo" value="${regTimeTo}"></c:set>
                                                <table>
                                                    <tr>
                                                        <td>From</td>
                                                        <td><input type="date" name="regTimeFrom" id="regTimeFrom" value="${regTimeFrom}"></td>
                                                </tr>
                                                <tr>
                                                    <td>To</td>
                                                    <td><input type="date" name="regTimeTo" id="regTimeTo" value="${regTimeTo}"></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2">
                                                        <div class="buttonSearch d-flex justify-content-center" style="width: 100%;">
                                                            <button type="button" class="btn btn-primary" onclick="searchFunction()">Search</button>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!--Content-->
                        <div class="content col-md-10" style="overflow: scroll;">
                            <c:set var="lr" value="${listRegistration}"></c:set>
                            <c:set var="lcn" value="${listCourseName}"></c:set>
                            <c:set var="lsn" value="${listSubjectName}"></c:set>
                            <c:set var="lpn" value="${listPackageName}"></c:set>
                            <c:set var="ltp" value="${listTotalPrice}"></c:set>
                            <c:set var="lvt" value="${listValidTo}"></c:set>
                            <c:set var="lu" value="${listUser}"></c:set>
                                <table class="table table-striped">
                                    <tr>
                                        <th>ID</th>
                                        <th>Email</th>
                                        <th>Course</th>
                                        <th>Subject</th>
                                        <th>Registration time</th>
                                        <th>Package</th>
                                        <th>Total cost</th>
                                        <th>Status</th>
                                        <th>Valid from</th>
                                        <th>Valid to</th>
                                        <th>Last updated by</th>
                                        <th>Action</th>
                                    </tr>
                                <c:if test="${lr.size() > 0}">
                                    <c:forEach begin="0" end="${lr.size()-1}" var="i">
                                        <tr>
                                            <td>${lr.get(i).rid}</td>
                                            <td>${lu.get(i).uemail}</td>
                                            <td>${lcn.get(i)}</td>
                                            <td>${lsn.get(i)}</td>
                                            <td>${lr.get(i).rtime}</td>
                                            <td>${lpn.get(i)}</td>
                                            <td>${ltp.get(i)}</td>
                                            <td>
                                                <c:if test="${lr.get(i).rstatusId == 0}">
                                                    Submitted
                                                </c:if>
                                                <c:if test="${lr.get(i).rstatusId == 1}">
                                                    Registered
                                                </c:if>
                                            </td>
                                            <td>${lr.get(i).rtime}</td>
                                            <td>${lvt.get(i)}</td>
                                            <td>${lr.get(i).lastUpBy}</td>
                                            <td><a href="registrationdetail?rid=${lr.get(i).rid}">View detail</a></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </table>

                            <c:if test="${requestScope.num == 0}">
                                <div style="text-align: left; font-size: 20px;">
                                    Not found the registration!
                                    <c:if test="${requestScope.ms != null}">
                                        <span style="color: red;">(${requestScope.ms})</span>
                                    </c:if>
                                </div>
                            </c:if> 

                            <!--Paging-->
                            <div class="paging-block d-flex justify-content-center" style="margin-top: 5%;">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination">
                                        <c:set var="page" value="${requestScope.page}"></c:set>
                                        <c:set var="subCates" value="${subCates}"></c:set>
                                        <c:set var="subjects" value="${subjects}"></c:set>
                                        <c:set var="packs" value="${packs}"></c:set>
                                        <c:set var="statuses" value="${statuses}"></c:set>
                                        <c:set var="costs" value="${costs}"></c:set>
                                        <c:if test="${requestScope.num != 0}">
                                            <c:if test="${search != null}">
                                                <li class="page-item ${page==1?"disabled":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${page-1}">Previous</a></li>
                                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${i}">${i}</a></li>
                                                    </c:forEach>
                                                <li class="page-item ${page==num?"disabled":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${page+1}">Next</a></li>
                                                </c:if>
                                                <c:if test="${search == null}">
                                                <li class="page-item ${page==1?"disabled":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${page-1}">Previous</a></li>
                                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${i}">${i}</a></li>
                                                    </c:forEach>
                                                <li class="page-item ${page==num?"disabled":""}"><a class="page-link" href="registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=${regTimeFrom}&regTimeTo=${regTimeTo}&page=${page+1}">Next</a></li>
                                                </c:if>
                                            </c:if>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
        </c:if>
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
    <script src="../js/registration_list.js"></script>
    <script type="text/javascript">
                                                                function searchFunction() {
                                                                    window.location = "registrationlist?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&regTimeFrom=" + document.getElementById('regTimeFrom').value + "&regTimeTo=" + document.getElementById('regTimeTo').value;
                                                                }
    </script>
    <script src="/js/dashboard.js"></script>

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
    </script>

    <script type="text/javascript">
        window.addEventListener('load', function () {
            document.querySelector('input[type="file"]').addEventListener('change', function () {
                if (this.files && this.files[0]) {
                    var img = document.querySelector('#img');
                    img.onload = () => {
                        URL.revokeObjectURL(img.src); // no longer needed, free memory
                    };
                    img.src = URL.createObjectURL(this.files[0]); // set src to blob url
                }
            });
        });
    </script>  
</html>