<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Subject Details</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <style>
            <%@include file="../css/dashboard.css"%>
            <%@include file="../css/header.css"%>
            #right{
                background: white;
                min-height: 1000px;
            }
        </style>
        <script src="/js/dashboard.js"></script>
        <script src="ckeditor/ckeditor.js"></script>
    </head>

    <body>

        <c:if test="${sessionScope.user.rid == 6}">
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
                <div id="right" class="col-lg-10 col-md-8 col-12">
                    <jsp:include page="header_admin.jsp"/>

                    <!--Subject Details-->
                    <div class="mr-5 ml-5 mt-3 mb-5">

                        <div class="d-flex align-items-center" style="margin:30px 0 20px 0">
                            <a href="subjectlist" title="subjectlist"><h4><strong>Subject List</strong></h4></a>
                            <h4>&nbsp;>&nbsp;<strong>Subject Details</strong></h4>
                        </div>
                        <c:if test="${alertUpdate != null}">
                            <h5 style="color: green" class="mb-4"><b><i>*Saved Changes Successfully!</i></b></h5>
                                    </c:if>
                                    <c:if test="${error != null}">
                                        <c:if test="${error == \"Not enough Lesson for this Course!\"}">
                                <h5 style="color: red" class="mb-4"><b><i>*${error}</i></b></h5>
                                        </c:if>
                                        <c:if test="${error != \"Not enough Lesson for this Course!\"}">
                                <h5 style="color: red" class="mb-4"><b><i>*${error}</i></b></h5>
                                        </c:if>
                                    </c:if>
                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="overview-tab" data-toggle="tab" href="#overview" role="tab"
                                   aria-controls="overview" aria-selected="true">Edit Subject</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="dimension-tab" data-toggle="tab" href="#dimension" role="tab"
                                   aria-controls="dimension" aria-selected="false">Dimension</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pricepackage-tab" data-toggle="tab" href="#pricepackage" role="tab"
                                   aria-controls="pricepackage" aria-selected="false" onclick="">Price Package</a>
                            </li>
                        </ul>
                        <div class="tab-content border-left border-right border-bottom" id="myTabContent">
                            <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview-tab">
                                <form action="subjectdetails" enctype="multipart/form-data" method="POST" onsubmit="return submitForm1(this);">　　　　　　　　　　
                                    <div class="row mr-1 ml-1">
                                        <div class="col-md-8 mb-3">
                                            <input id="cid" name="cid" type="text" value="${course.cid}" hidden>

                                            <div class="input-group mb-4 d-flex flex-column">
                                                <label for="cname">Subject Name</label>
                                                <input id="cname" name="cname" type="text" required class="form-control w-100" value="${course.cname}">
                                            </div>
                                            <div class="input-group mb-4 d-flex flex-column">
                                                <label for="ctitle">Subject Title</label>
                                                <input id="ctitle" name="ctitle" type="text" required class="form-control w-100" value="${course.ctitle}">
                                            </div>
                                            <div class="input-group mb-4 d-flex flex-column">
                                                <label for="brow">Subject Category</label>
                                                <c:forEach items="${listSubject}" var="listS">
                                                    <c:if test="${listS.sid == course.sid}">
                                                        <input list="brow" name="subname" placeholder="Choose..." class="form-control w-100" required value="${listS.sname}">
                                                    </c:if>
                                                </c:forEach>
                                                <datalist id="brow">
                                                    <c:forEach items="${listSubject}" var="listS">
                                                        <option value="${listS.sname}"></option>
                                                    </c:forEach>
                                                </datalist>
                                            </div>
                                            <div class="input-group mb-4 row">
                                                <div class="d-flex flex-column col-md-6">
                                                    <label for="clecturer">Lecturer</label>
                                                    <c:if test="${course.lecid != 0}">
                                                        <c:forEach items="${listLecturer}" var="listLec">
                                                            <c:if test="${listLec.lid == course.lecid}">
                                                                <input list="listLecturer" id="clecturer" name="lecname" type="text" class="form-control w-100" required value="${listLec.lname == null ? "" : listLec.lname}">
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${course.lecid == 0}">
                                                        <input list="listLecturer" id="clecturer" name="lecname"  class="form-control w-100" required type="text">
                                                    </c:if>
                                                    <datalist id="listLecturer">
                                                        <c:forEach items="${listLecturer}" var="listLec">
                                                            <option value="${listLec.lname}"></option>
                                                        </c:forEach>
                                                    </datalist>
                                                </div>
                                                <div class="col-md-6">
                                                    <label for="clevel">Subject Level</label>
                                                    <select class="custom-select" name="clevel">
                                                        <c:if test="${course.levid == 0}">
                                                            <option hidden selected>Choose...</option>
                                                        </c:if>

                                                        <c:forEach items="${listLevel}" var="listLev">
                                                            <option ${course.levid == listLev.lid ? "selected" : ""} value="${listLev.lid}">${listLev.lname}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4 pt-3">
                                            <div class="pt-3 pb-3">
                                                <button class="btn btn-primary" type="button" onclick="window.location.href = 'subjectlesson?cid=${course.cid}'">Edit Lessons for ${course.cname}</button>       
                                            </div>
                                            <div class="d-flex flex-column justify-content-center">
                                                <label for="cimg">Subject Image</label>
                                                <!--Dung anh tuyet doi-->
                                                <c:if test="${cImage != null}">
                                                    <img id="cimg" src="${course.cimg}" alt="CourseImg" class="img-thumbnail" style="width: 400px; height: 230px;">
                                                </c:if>
                                                <!--Dung anh tuong doi-->
                                                <c:if test="${cImage == null}">
                                                    <img id="cimg" src="images/course/${course.cimg}" alt="CourseImg" class="img-thumbnail" style="width: 400px; height: 230px;">
                                                </c:if>
                                                <div>
                                                    <label for="cfile" class="btn btn-primary">Change Image</label>
                                                    <input id="cfile" type="file" onchange="preview_image_cimg(event)" name="cimg" accept="image/jpeg, image/png, image/jpg" style="width: 0px;">

                                                </div>
                                            </div>
                                        </div>

                                        <!-- tagline|brief info|description -->
                                        <div class="row mr-1 ml-1">
                                            <div class="col-md-12 input-group mb-3 d-flex flex-column">
                                                <label for="tagline">Tag Line</label>
                                                <input id="tagline" name="tagline" type="text" class="form-control w-100" value="${tagline}">
                                            </div>
                                            <div class="col-md-12 input-group mb-4 d-flex flex-column">
                                                <label for="brief">Brief Information</label>
                                                <textarea id="brief" name="brief" type="text" class="form-control w-100">${brief}</textarea>
                                            </div>
                                            <div class="col-md-12 input-group mb-3 d-flex flex-column">
                                                <div class="mb-5">
                                                    <label for="cdes">Subject Description</label>
                                                    <textarea hidden id="cdes" type="text" class="form-control w-100"
                                                              style="height: 300px;">${description}</textarea>
                                                    <textarea id="contentCourse" name="cdes"></textarea>
                                                </div>
                                            </div>
                                        </div>  
                                    </div>
                                    <div class="row mr-1 ml-1">
                                        <div class="input-group mb-3 d-flex flex-column col-md-6">
                                            <label for="cprice">Subject Price($)</label>
                                            <input id="cprice" name="cprice" required type="text" class="form-control w-100" value="${course.cprice}">
                                        </div>
                                        <div class="input-group d-flex flex-column col-md-6">
                                            <p>Status</p>
                                            <div class="form-check">
                                                <input class="form-check-input" id="Unpublished" ${course.cstatus ? "" : "checked"}  type="radio" data-toggle="modal" data-target="#alertStatusUnPush" name="cstatus" value="0">
                                                <label class="form-check-label" for="Unpublished">
                                                    Unpublished
                                                </label>
                                            </div>
                                            <div class="form-check">
                                                <input id="Published" class="form-check-input" ${course.cstatus ? "checked" : ""} type="radio" data-toggle="modal" data-target="#alertStatusPush" name="cstatus" value="1">
                                                <label class="form-check-label" for="Published">
                                                    Published
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="d-flex ml-3 mb-3">
                                        <input type="submit" class="btn btn-primary mr-5" value="Save Changes">
                                        <button type="button" class="btn btn-secondary" onclick="window.location.href = 'subjectlist'">Back</button>
                                    </div>
                                </form>
                            </div>
                            <!--Dimension-->
                            <div class="tab-pane fade" id="dimension" role="tabpanel" aria-labelledby="dimension-tab">
                                <div class="d-flex justify-content-end pr-3 pt-3 pb-1">
                                    <button class="btn btn-primary" data-toggle="modal" data-target="#Dimension">Add new</button>
                                </div>
                                <div class="ml-3">
                                    <c:if test="${deleteSubCat == 0}">
                                        <h5 style="color: red"><b>Cannot delete this Dimension</b><i style="color: gray"> (This relates to other relationships like subject,...)</i></h5>
                                    </c:if>
                                    <c:if test="${deleteSubCat == 1}">
                                        <h5 style="color: green"><b><i>Delete successful!</i></b></h5>
                                                </c:if>
                                </div>
                                <div class="table table-hover pr-3 pl-3">
                                    <div class="table-responsive-md">
                                        <table class="w-100">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col">#</th> 
                                                    <th scope="col">Dimension</th> 
                                                    <th scope="col">Action</th> 
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listSubjectCategory}" var="listSC">
                                                    <tr>
                                                        <!--Id-->
                                                        <td scope="row">${listSC.subjectCateId}</td>
                                                        <!--Name SubCategory-->
                                                        <td scope="row">${listSC.subjectCateName}</td>
                                                        <!--Action-->
                                                        <td>
                                                            <a href="subjectdetails?cid=${course.cid}&scidEdit=${listSC.subjectCateId}"><i class="bi bi-pencil-square"></i></a>&nbsp;&nbsp;&nbsp;
                                                            <a href="adddimension?cid=${course.cid}&scid=${listSC.subjectCateId}&action=delete"><i class="bi bi-trash"></i></a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <!--Price Package-->
                            <div class="tab-pane fade" id="pricepackage" role="tabpanel" aria-labelledby="pricepackage-tab">
                                <div class="add-button" style="float: right; margin-right: 2%; margin-top: 2%;">
                                    <c:if test="${courseprice > 0}"><button class="btn btn-primary btn-sm" onclick="window.location = 'addnewpricepackage?cid=${course.cid}'">Add new</button></c:if>
                                    </div>
                                    <div style="clear: both;"></div>
                                    <div style="margin: 2%;">
                                        <table class="table table-striped border" style="text-align: center;">
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Duration(Month)</th>
                                                <th>List Price</th>
                                                <th>Sale Price</th>
                                                <th>Status</th>
                                                <th>Description</th>
                                                <c:if test="${sessionScope.user.rid == 6 && courseprice > 0}">
                                                <th>Action</th>
                                                </c:if>
                                        </tr>
                                        <c:set value="${listPricePackage}" var="lpp"></c:set>
                                        <c:if test="${lpp.size() > 0}">
                                            <c:if test="${courseprice > 0}">
                                                <c:forEach begin="1" end="${lpp.size()-1}" var="i">
                                                    <tr>
                                                        <td>${lpp.get(i).packid}</td>
                                                        <td>${lpp.get(i).packname}</td>
                                                        <td>${lpp.get(i).duration}</td>
                                                        <td>
                                                            <fmt:formatNumber value="${course.cprice * lpp.get(i).multiple/0.8}" pattern="##.#"></fmt:formatNumber>$
                                                            </td>
                                                            <td>
                                                            <fmt:formatNumber value="${course.cprice * lpp.get(i).multiple}" pattern="##.#"></fmt:formatNumber>$
                                                            </td>
                                                            <td>
                                                            <c:if test="${lpp.get(i).packstatus == false}">
                                                                Inactive
                                                            </c:if>
                                                            <c:if test="${lpp.get(i).packstatus == true}">
                                                                Active
                                                            </c:if>
                                                        </td>
                                                        <td>${lpp.get(i).description}</td>
                                                        <c:if test="${sessionScope.user.rid == 6}">
                                                            <c:if test="${courseprice > 0}"><td><a href="updatepricepackage?id=${lpp.get(i).packid}&cid=${course.cid}">Edit</a></td></c:if>
                                                        </c:if>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${courseprice == 0}">
                                                <c:forEach begin="0" end="0" var="i">
                                                    <tr>
                                                        <td>${lpp.get(i).packid}</td>
                                                        <td>${lpp.get(i).packname}</td>
                                                        <td>${lpp.get(i).duration}</td>
                                                        <td>
                                                            0$
                                                        </td>
                                                        <td>
                                                            0$
                                                        </td>
                                                        <td>
                                                            <c:if test="${lpp.get(i).packstatus == false}">
                                                                Inactive
                                                            </c:if>
                                                            <c:if test="${lpp.get(i).packstatus == true}">
                                                                Active
                                                            </c:if>
                                                        </td>
                                                        <td>${lpp.get(i).description}</td>
                                                        <c:if test="${sessionScope.user.rid == 6}">
                                                            <c:if test="${courseprice > 0}"><td><a href="updatepricepackage?id=${lpp.get(i).packid}">Edit</a></td></c:if>
                                                        </c:if>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                        </c:if>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </c:if>

        <c:if test="${sessionScope.user.rid == 5}">
            <jsp:include page="header.jsp"/>

            <div class="mr-5 ml-5 mt-3 mb-5">
                <div class="d-flex align-items-center" style="margin:110px 0 20px 0">
                    <a href="subjectlist" title="subjectlist"><h4><strong>Subject List</strong></h4></a>
                    <h4>&nbsp;>&nbsp;<strong>Subject Details</strong></h4>
                </div>
                <c:if test="${alertUpdate != null}">
                    <h5 style="color: green" class="mb-4"><b><i>*Saved Changes Successfully!</i></b></h5>
                            </c:if>
                            <c:if test="${error != null}">
                                <c:if test="${error == 'Not enough Lesson for this Course!'}">
                        <h5 style="color: red" class="mb-4"><b><i>*${error}</i></b></h5>
                                </c:if>
                                <c:if test="${error != 'Not enough Lesson for this Course!'}">
                        <h5 style="color: red" class="mb-4"><b><i>*${error}</i></b></h5>
                                </c:if>
                            </c:if>
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="overview-tab" data-toggle="tab" href="#overview" role="tab"
                           aria-controls="overview" aria-selected="true">Edit Subject</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="dimension-tab" data-toggle="tab" href="#dimension" role="tab"
                           aria-controls="dimension" aria-selected="false">Dimension</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="pricepackage-tab" data-toggle="tab" href="#pricepackage" role="tab"
                           aria-controls="pricepackage" aria-selected="false" onclick="">Price Package</a>
                    </li>
                </ul>
                <div class="tab-content border-left border-right border-bottom" id="myTabContent">
                    <div class="tab-pane fade show active" id="overview" role="tabpanel" aria-labelledby="overview-tab">
                        <form action="subjectdetails" enctype="multipart/form-data" method="POST" onsubmit="return submitForm1(this);">　　　　　　　　　　
                            <div class="row mr-1 ml-1">
                                <div class="col-md-8 mb-3">
                                    <input id="cid" name="cid" type="text" value="${course.cid}" hidden>

                                    <div class="input-group mb-4 d-flex flex-column">
                                        <label for="cname">Subject Name</label>
                                        <input id="cname" name="cname" type="text" required class="form-control w-100" value="${course.cname}">
                                    </div>
                                    <div class="input-group mb-4 d-flex flex-column">
                                        <label for="ctitle">Subject Title</label>
                                        <input id="ctitle" name="ctitle" type="text" required class="form-control w-100" value="${course.ctitle}">
                                    </div>
                                    <div class="input-group mb-4 d-flex flex-column">
                                        <label for="brow">Subject Category</label>
                                        <c:forEach items="${listSubject}" var="listS">
                                            <c:if test="${listS.sid == course.sid}">
                                                <input list="brow" name="subname" placeholder="Choose..." class="form-control w-100" required value="${listS.sname}">
                                            </c:if>
                                        </c:forEach>
                                        <datalist id="brow">
                                            <c:forEach items="${listSubject}" var="listS">
                                                <option value="${listS.sname}"></option>
                                            </c:forEach>
                                        </datalist>
                                    </div>
                                    <div class="input-group mb-4 row">
                                        <div class="d-flex flex-column col-md-6">
                                            <label for="clecturer">Lecturer</label>
                                            <c:if test="${course.lecid != 0}">
                                                <c:forEach items="${listLecturer}" var="listLec">
                                                    <c:if test="${listLec.lid == course.lecid}">
                                                        <input list="listLecturer" id="clecturer" name="lecname" type="text" class="form-control w-100" required value="${listLec.lname == null ? "" : listLec.lname}">
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${course.lecid == 0}">
                                                <input list="listLecturer" id="clecturer" name="lecname"  class="form-control w-100" required type="text">
                                            </c:if>
                                            <datalist id="listLecturer">
                                                <c:forEach items="${listLecturer}" var="listLec">
                                                    <option value="${listLec.lname}"></option>
                                                </c:forEach>
                                            </datalist>
                                        </div>
                                        <div class="col-md-6">
                                            <label for="clevel">Subject Level</label>
                                            <select class="custom-select" name="clevel">
                                                <c:if test="${course.levid == 0}">
                                                    <option hidden selected>Choose...</option>
                                                </c:if>

                                                <c:forEach items="${listLevel}" var="listLev">
                                                    <option ${course.levid == listLev.lid ? "selected" : ""} value="${listLev.lid}">${listLev.lname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4 pt-3">
                                    <div class="pt-3 pb-3">
                                        <button class="btn btn-primary" type="button" onclick="window.location.href = 'subjectlesson?cid=${course.cid}'">Edit Lessons for ${course.cname}</button>       
                                    </div>
                                    <div class="d-flex flex-column justify-content-center">
                                        <label for="cimg">Subject Image</label>
                                        <!--Dung anh tuyet doi-->
                                        <c:if test="${cImage != null}">
                                            <img id="cimg" src="${course.cimg}" alt="CourseImg" class="img-thumbnail" style="width: 400px; height: 230px;">
                                        </c:if>
                                        <!--Dung anh tuong doi-->
                                        <c:if test="${cImage == null}">
                                            <img id="cimg" src="images/course/${course.cimg}" alt="CourseImg" class="img-thumbnail" style="width: 400px; height: 230px;">
                                        </c:if>
                                        <div>
                                            <label for="cfile" class="btn btn-primary">Change Image</label>
                                            <input id="cfile" type="file" onchange="preview_image_cimg(event)" name="cimg" accept="image/jpeg, image/png, image/jpg" style="width: 0px;">

                                        </div>
                                    </div>
                                </div>

                                <!-- tagline|brief info|description -->
                                <div class="row mr-1 ml-1">
                                    <div class="col-md-12 input-group mb-3 d-flex flex-column">
                                        <label for="tagline">Tag Line</label>
                                        <input id="tagline" name="tagline" type="text" class="form-control w-100" value="${tagline}">
                                    </div>
                                    <div class="col-md-12 input-group mb-4 d-flex flex-column">
                                        <label for="brief">Brief Information</label>
                                        <textarea id="brief" name="brief" type="text" class="form-control w-100">${brief}</textarea>
                                    </div>
                                    <div class="col-md-12 input-group mb-3 d-flex flex-column">
                                        <div class="mb-5">
                                            <label for="cdes">Subject Description</label>
                                            <textarea hidden id="cdes" type="text" class="form-control w-100"
                                                      style="height: 300px;">${description}</textarea>
                                            <textarea id="contentCourse" name="cdes"></textarea>
                                        </div>
                                    </div>
                                </div>  
                            </div>
                            <div class="d-flex ml-3 mb-3">
                                <input type="submit" class="btn btn-primary mr-5" value="Save Changes">
                                <button type="button" class="btn btn-secondary" onclick="window.location.href = 'subjectlist'">Back</button>
                            </div>
                        </form>
                    </div>
                    <!--Dimension-->
                    <div class="tab-pane fade" id="dimension" role="tabpanel" aria-labelledby="dimension-tab">
                        <div class="d-flex justify-content-end pr-3 pt-3 pb-1">
                            <button class="btn btn-primary" data-toggle="modal" data-target="#Dimension">Add new</button>
                        </div>
                        <div class="ml-3">
                            <c:if test="${deleteSubCat == 0}">
                                <h5 style="color: red"><b>Cannot delete this Dimension</b><i style="color: gray"> (This relates to other relationships like subject,...)</i></h5>
                            </c:if>
                            <c:if test="${deleteSubCat == 1}">
                                <h5 style="color: green"><b><i>Delete successful!</i></b></h5>
                                        </c:if>
                        </div>
                        <div class="table table-hover pr-3 pl-3">
                            <div class="table-responsive-md">
                                <table class="w-100">
                                    <thead class="thead-light">
                                        <tr>
                                            <th scope="col">#</th> 
                                            <th scope="col">Dimension</th> 
                                            <th scope="col">Action</th> 
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listSubjectCategory}" var="listSC">
                                            <tr>
                                                <!--Id-->
                                                <td scope="row">${listSC.subjectCateId}</td>
                                                <!--Name SubCategory-->
                                                <td scope="row">${listSC.subjectCateName}</td>
                                                <!--Action-->
                                                <td>
                                                    <a href="subjectdetails?cid=${course.cid}&scidEdit=${listSC.subjectCateId}"><i class="bi bi-pencil-square"></i></a>&nbsp;&nbsp;&nbsp;
                                                    <a href="adddimension?cid=${course.cid}&scid=${listSC.subjectCateId}&action=delete"><i class="bi bi-trash"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!--Price Package-->
                    <div class="tab-pane fade" id="pricepackage" role="tabpanel" aria-labelledby="pricepackage-tab">
                        <div class="mr-4 ml-4 mb-4 pt-4">
                            <table class="table table-striped border" style="text-align: center;">
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Duration(Month)</th>
                                    <th>List Price</th>
                                    <th>Sale Price</th>
                                    <th>Status</th>
                                    <th>Description</th>
                                </tr>
                                <c:set value="${listPricePackage}" var="lpp"></c:set>
                                <c:if test="${lpp.size() > 0}">
                                    <c:if test="${courseprice > 0}">
                                        <c:forEach begin="1" end="${lpp.size()-1}" var="i">
                                            <tr>
                                                <td>${lpp.get(i).packid}</td>
                                                <td>${lpp.get(i).packname}</td>
                                                <td>${lpp.get(i).duration}</td>
                                                <td>
                                                    <fmt:formatNumber value="${course.cprice * lpp.get(i).multiple/0.8}" pattern="##.#"></fmt:formatNumber>$
                                                    </td>
                                                    <td>
                                                    <fmt:formatNumber value="${course.cprice * lpp.get(i).multiple}" pattern="##.#"></fmt:formatNumber>$
                                                    </td>
                                                    <td>
                                                    <c:if test="${lpp.get(i).packstatus == false}">
                                                        Inactive
                                                    </c:if>
                                                    <c:if test="${lpp.get(i).packstatus == true}">
                                                        Active
                                                    </c:if>
                                                </td>
                                                <td>${lpp.get(i).description}</td>
                                                <c:if test="${sessionScope.user.rid == 6}">
                                                    <c:if test="${courseprice > 0}"><td><a href="updatepricepackage?id=${lpp.get(i).packid}">Edit</a></td></c:if>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${courseprice == 0}">
                                        <c:forEach begin="0" end="0" var="i">
                                            <tr>
                                                <td>${lpp.get(i).packid}</td>
                                                <td>${lpp.get(i).packname}</td>
                                                <td>${lpp.get(i).duration}</td>
                                                <td>
                                                    0$
                                                </td>
                                                <td>
                                                    0$
                                                </td>
                                                <td>
                                                    <c:if test="${lpp.get(i).packstatus == false}">
                                                        Inactive
                                                    </c:if>
                                                    <c:if test="${lpp.get(i).packstatus == true}">
                                                        Active
                                                    </c:if>
                                                </td>
                                                <td>${lpp.get(i).description}</td>
                                                <c:if test="${sessionScope.user.rid == 6}">
                                                    <c:if test="${courseprice > 0}"><td><a href="updatepricepackage?id=${lpp.get(i).packid}">Edit</a></td></c:if>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </c:if>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>                                        

        <!-- ------------------- Dimension---------------------- -->      
        <!-- Add new-->
        <button id="btAddSubCat" data-toggle="modal" data-target="#Dimension" hidden></button>
        <div class="modal fade" id="Dimension" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="d-flex flex-column">
                            <h5 class="modal-title" id="exampleModalLongTitle">Add New Dimension</h5>

                            <c:if test="${addSubCat != null}">
                                <p style="color: green"><b>*Add successful</b></p>
                            </c:if>
                        </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="adddimension">
                        <div class="modal-body">
                            <div class="d-flex flex-column">
                                <label for="scname">Dimension</label>
                                <input id="scname" required type="text" name="scname" class="form-control">
                            </div>
                            <input hidden name="cid" value="${course.cid}">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary">Add</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Edit -->
        <button id="btEditDimension" data-toggle="modal" data-target="#EditDimension" hidden></button>
        <div class="modal fade" id="EditDimension" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="d-flex flex-column">
                            <h5 class="modal-title" id="exampleModalLongTitle">Edit Dimension</h5>

                            <c:if test="${editSubCat != null}">
                                <p style="color: green"><b>*Save changed</b></p>
                            </c:if>
                        </div>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="adddimension">
                        <div class="modal-body">
                            <div class="d-flex flex-column">
                                <label for="scname">Dimension</label>
                                <c:forEach items="${listSubjectCategory}" var="listSC">
                                    <c:if test="${scidEdit == listSC.subjectCateId}">
                                        <input id="scname" required type="text" name="scname" value="${listSC.subjectCateName}" class="form-control">
                                    </c:if>
                                </c:forEach>
                            </div>

                            <input hidden name="scid" value="${scidEdit}">
                            <input hidden name="action" value="edit">
                            <input hidden name="cid" value="${course.cid}">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- -------------------Edit Subject---------------------- -->                                    
        <!--Alert Change Status-->
        <div id="alertStatusPush" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Notification</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <p><span style="color: blue"><b>${course.cname}</b></span> course has changed to <span style="color: green"><b>Published</b></span> status.
                            This course is <span style="color: blue"><b>READY</b></span> to be displayed on the system's public screens.</p>
                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-primary">Continue</button>
                    </div>
                </div>
            </div>
        </div>  
        <div id="alertStatusUnPush" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Notification</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <p><span style="color: blue"><b>${course.cname}</b></span> course has been changed to <span style="color: red"><b>Un-Published</b></span> status.
                            This course will <span style="color: red"><b>NOT BE DISPLAYED</b></span> on the system's public screens.</p>
                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-primary">Continue</button>
                    </div>
                </div>
            </div>
        </div>  
        <!--Error None Lesson-->                     
        <button hidden id="NoneLesson" data-toggle="modal" data-target="#errorNoneLesson"></button>                    
        <div id="errorNoneLesson" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Notification</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <p><span style="color: blue"><b>${course.cname}</b></span> course <span style="color: red"><b>does not have enough lessons</b></span> .
                            The status <span style="color: red"><b>cannot be changed Published</b></span> for this course.</p>
                    </div>
                    <div class="modal-footer">
                        <button data-dismiss="modal" class="btn btn-secondary">Cancel</button>
                        <button onclick="window.location.href = 'subjectlesson?cid=${course.cid}'" class="btn btn-primary">Add more Lesson</button>
                    </div>
                </div>
            </div>
        </div>  

    </body>

    <script type="text/javascript">

        <c:if test="${error == 'Not enough Lesson for this Course!'}">
        window.onload = function () {
            document.getElementById('NoneLesson').click();
        }
        </c:if>

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

        function preview_image_cimg(event) {
            var reader = new FileReader();
            reader.onload = function () {
                var output = document.getElementById('cimg');
                output.src = reader.result;
            }
            reader.readAsDataURL(event.target.files[0]);
        }

        function submitForm1() {
            return confirm('Do you really want to Save Changes this Subject?');
        }

        <c:if test="${scidEdit != null}">
        document.getElementById('btEditDimension').click();
        document.getElementById('dimension-tab').click();
        </c:if>
        <c:if test="${deleteSubCat != null}">
        document.getElementById('dimension-tab').click();
        </c:if>

        <c:if test="${addSubCat != null}">
        document.getElementById('dimension-tab').click();
        document.getElementById('btAddSubCat').click();
        </c:if>

        <c:if test="${mspp != null}">
        window.onload = function () {
            document.getElementById('pricepackage-tab').click();
        }
        </c:if>

        var editor = '';
        var cdes = document.getElementById('cdes');
        $(document).ready(function () {
            CKEDITOR.replace('contentCourse');
            CKEDITOR.instances['contentCourse'].setData(cdes.value);
        });
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