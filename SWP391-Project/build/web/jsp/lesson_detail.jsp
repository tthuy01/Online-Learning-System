<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Subject List</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
        <!-- JS for jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- CSS for searching -->
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <!-- JS for searching -->
        <script type="text/javascript">
            // .js-example-basic-single declare this class into your select box
            $(document).ready(function () {
                $('.js-example-basic-single').select2();
            });
        </script>
        <style type="text/css">
            <%@include file="../css/dashboard.css"%>
            <%@include file="../css/header.css" %>
            #right{
                background-color: white;
            }
        </style>
        <script src="/js/dashboard.js"></script>
        <script src="ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <!--Header-->
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
            <div id="right" class="col-lg-10 col-md-8 col-12" style="${listCourse.size() < 5 ? "margin-bottom: 200px" : ""}">
                <jsp:include page="header_admin.jsp"/>

                <div class="lesson_detail">
                    <nav class="body_content mx-auto  " style="width: 95%; ">
                        <c:if test="${id!=0}">
                            <h4 class="mt-3 mb-3 "><a href="subjectlist" >Subject List</a><i class="fa-solid fa-angle-right mx-2"></i><a href="subjectdetails?cid=${cid}" >Subject Details</a><i class="fa-solid fa-angle-right mx-2"></i><a href="subjectlesson?cid=${cid}" >Subject Lesson</a><i class="fa-solid fa-angle-right mx-2"></i>Lesson Detail</h4>
                                </c:if>
                                <c:if test="${id==0}">
                            <h4 class="mt-3 mb-3 "><a href="subjectlist" >Subject List</a><i class="fa-solid fa-angle-right mx-2"></i><a href="subjectdetails?cid=${cid}" >Subject Details</a><i class="fa-solid fa-angle-right mx-2"></i><a href="subjectlesson?cid=${cid}" >Subject Lesson</a><i class="fa-solid fa-angle-right mx-2"></i>New Lesson</h4>
                                </c:if>
                        <div class="mb-3 mx-auto justify-content-center" style="width: max-content;">
                            <span class="" style="color: red">${requestScope.successfull}</span>               
                        </div>
                        <div class="mb-3 mx-auto justify-content-center" style="width: max-content;">
                            <c:if test="${successfull != null}">
                                <input class="btn btn-primary" type="button" value="Back to Subject Lesson" onclick="Back()">
                            </c:if>
                        </div>

                        <!--                        <div class="content_name px-3">
                                                    <h4>Lesson Details</h4>
                                                </div>-->
                        <form action="lessondetail" method="post" id="form1">
                            <input type="text" name="cid" value="${cid}" style="display:none;" >
                            <c:if test="${lesson.tid == 1 && lesson.lid!=0}">
                                <div class="content_head_first row no-gutters mt-3">
                                    <div class="content_first_left col-12 col-sm-6 col-md-8 px-3">
                                        <input type="text" name="id" value="${lesson.lid}" style="display: none;">
                                        <span>Name</span><br>
                                        <input type="text" name="name" class="w-100 mt-2 mb-3" value="${lesson.lname}" required="">
                                    </div>
                                    <div class="content_first_right col-6 col-md-4 mb-3 px-3">
                                        <span>Type</span><br>
                                        <select class="w-100 mt-2" name="" disabled="">
                                            <c:forEach var="tl" items="${listType}">
                                                <option ${tl.tid==lesson.tid?"selected":""} value="${tl.tid}">${tl.tname}</option>
                                            </c:forEach>                               
                                        </select>
                                        <input name="type" value="${lesson.tid}" style="display: none;">
                                    </div>
                                </div>
                                <div class="content_head_second row no-gutters">
                                    <div class="content_first_left col-12 col-sm-6 col-md-8 px-3">
                                        <span>Section</span><br>
                                        <select class="w-100 mt-2 mb-3 " name="category">
                                            <c:forEach var="sl" items="${requestScope.listSection}">
                                                <option ${sl.sid==lesson.sid?"selected":""} value="${sl.sid}">${sl.sname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="content_first_right col-6 col-md-4 mb-3 px-3">
                                        <span>Course</span>
                                        <div class="mb-1"></div>
                                        <select class="w-100 js-example-basic-single" name="course" onchange="myFunction()">
                                            <c:forEach var="ss" items="${requestScope.listCourse}">
                                                <option ${ss.cid==lesson.cid?"selected":""} value="${ss.cid}" >${ss.cname}</option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>
                                <div class="content_body px-3">
                                    <span>Video link</span>
                                    <c:set var="link" value="${fn:substring(lesson.lvideo, 30, 41)}" />
                                    <input type="text" name="video" class="w-100 mt-2 mb-3" value="https://www.youtube.com/watch?v=${link}">
                                </div>
                                <div class="content_foot px-3">
                                    <div class="mb-2">HTML Content</div>
                                    <textarea name="desc" id="content" cols="1000" rows="10">${lesson.ldesc}</textarea>
                                </div>
                            </c:if>

                            <c:if test="${section.tid == 3 && section.sid != 0}">
                                <input type="text" name="cid" value="${cid}" style="display:none;">
                                <div class="content_head_first row no-gutters mt-3">
                                    <div class="content_first_left col-12 col-sm-6 col-md-8 px-3">
                                        <input type="text" name="id" value="${section.sid}" style="display: none;">
                                        <span>Name</span><br>
                                        <input type="text" name="name" class="w-100 mt-2 mb-3" value="${section.sname}">
                                    </div>
                                    <div class="content_first_right col-6 col-md-4 mb-3 px-3">
                                        <span>Type</span><br>
                                        <select class="w-100 mt-2" name="type" >
                                            <c:forEach var="tl" items="${listType}">
                                                <option ${tl.tid==section.tid?"selected":""} ${tl.tid!=section.tid?"disabled":""} value="${tl.tid}">${tl.tname}</option>
                                            </c:forEach>                               
                                        </select>
                                    </div>
                                </div>
                                <div class="content_head_second row no-gutters">
                                    <div class="content_first_left col-12 col-sm-6 col-md-8 px-3">
                                        <span>Course</span><br>
                                        <select class="w-100 mt-2 mb-3" name="" disabled="">
                                            <c:forEach var="ss" items="${requestScope.listCourse}">
                                                <option ${ss.cid==section.cid?"selected":""} value="${ss.cid}" >${ss.cname}</option>
                                            </c:forEach>
                                        </select>
                                        <input name="category" value="${section.cid}" style="display: none;">
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${id == 0 && lesson.tid==1}">
                                <div class="content_head_first row no-gutters mt-3">
                                    <div class="content_first_left col-12 col-sm-6 col-md-8 px-3">
                                        <input type="text" name="id" value="${id}" style="display: none;" >
                                        <span>Name</span><br>
                                        <input type="text" name="name" class="w-100 mt-2 mb-3" value="${lesson.lname}" required="">
                                    </div>
                                    <div class="content_first_right col-6 col-md-4 mb-3 px-3">
                                        <span>Type</span><br>
                                        <select class="w-100 mt-2" name="type" onchange="myFunction()">
                                            <option value="3" ${lesson.tid==3?"selected":""}>Section</option>
                                            <option value="1" ${lesson.tid==1?"selected":""}>Lesson</option>

                                        </select>
                                    </div>
                                </div>
                                <div class="content_head_second row no-gutters">
                                    <div class="content_first_left col-12 col-sm-6 col-md-8 px-3">
                                        <span>Section</span><br>
                                        <select class="w-100 mt-2 mb-3" name="category">
                                            <c:forEach var="sl" items="${requestScope.listSection}">
                                                <option ${sl.sid==lesson.sid?"selected":""} value="${sl.sid}">${sl.sname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="content_first_right col-6 col-md-4 mb-3 px-3">
                                        <span>Course</span>
                                        <div class="mb-1"></div>
                                        <select class="w-100 js-example-basic-single" name="course" onchange="myFunction()">
                                            <c:forEach var="ss" items="${requestScope.listCourse}">
                                                <option ${ss.cid==lesson.cid?"selected":""} value="${ss.cid}" >${ss.cname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="content_body px-3">
                                    <span>Video link</span>
                                    <c:set var="link" value="${fn:substring(lesson.lvideo, 30, 41)}" />
                                    <input type="text" name="video" class="w-100 mt-2 mb-3" value="${lesson.lvideo}" required="">
                                </div>
                                <div class="content_foot px-3">
                                    <div class="mb-2">HTML Content</div>
                                    <textarea name="desc" id="content" cols="1000" rows="10">${lesson.ldesc}</textarea>
                                </div>
                            </c:if>

                            <c:if test="${section.tid == 3 && id == 0}">
                                <div class="content_head_first row no-gutters mt-3">
                                    <div class="content_first_left col-12 col-sm-6 col-md-8 px-3">
                                        <input type="text" name="id" value="${id}" style="display: none;">
                                        <span>Name</span><br>
                                        <input type="text" name="name" class="w-100 mt-2 mb-3" value="${section.sname}">
                                    </div>
                                    <div class="content_first_right col-6 col-md-4 mb-3 px-3">
                                        <span>Type</span><br>
                                        <select class="w-100 mt-2" name="type" onchange="myFunction()">
                                            <option value="3" ${section.tid==3?"selected":""}>Section</option>
                                            <option value="1" ${section.tid==1?"selected":""}>Lesson</option>

                                        </select>
                                        <input name="checkchange" value="checkchange" style="display: none">
                                    </div>
                                </div>
                                <div class="content_head_second row no-gutters">
                                    <div class="content_first_left col-12 col-sm-6 col-md-8 px-3">
                                        <span>Course</span><br>
                                        <select class="w-100 mt-2 mb-3" name="category">
                                            <c:forEach var="ss" items="${requestScope.listCourse}">
                                                <option ${ss.cid==section.cid?"selected":""} value="${ss.cid}" >${ss.cname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </c:if>

                            <div class="mt-4 px-3">
                                <c:if test="${id!=0}">
                                    <input class="btn btn-primary" type="submit" value="Save Change" name="savechange">
                                    <input class="ml-3 btn btn-primary" type="button" value="Undo Changes" onclick="window.location = 'lessondetail?id=${id}&type=${type}'">
                                </c:if>
                                <c:if test="${id==0}">
                                    <input class="btn btn-primary" type="submit" value="Add new" name="savechange">
                                    <input class="ml-3 btn btn-primary" type="button" value="Undo Changes" onclick="window.location = 'lessondetail?cid=${cid}'">
                                </c:if>

                            </div>
                        </form>
                    </nav>





                    <script>
                        function Back() {
                            window.location = "subjectlesson?cid=${cid}";
                        }
                        ;

                        function myFunction() {
                            document.getElementById("form1").submit();
                        }
                    </script>
                </div>



            </div>
        </section>        


    </body>
    <!--    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>-->
    <script type="text/javascript">
        <c:if test="${side == 'hide'}">
        document.getElementById('left').style.display = "none";
        right.classList.replace("col-lg-10", "col-lg-12");
        </c:if>

        window.addEventListener('load', function () {
            document.querySelector('input[type="file"]').addEventListener('change', function () {
                if (this.files && this.files[0]) {
                    var img = document.querySelector('#img');
                    img.onload = () => {
                        URL.revokeObjectURL(img.src);  // no longer needed, free memory
                    };
                    img.src = URL.createObjectURL(this.files[0]); // set src to blob url
                }
            });
        });

        function MySide() {
            var right = document.getElementById('right');
            if (document.getElementById('left').style.display != "none") {
                document.getElementById('left').style.display = "none";
                right.classList.replace("col-lg-10", "col-lg-12");
                // right.classList.add("col-lg-12");
                document.getElementById('side').value = 'hide';
            } else {
                document.getElementById('left').style.display = "block";
                right.classList.replace("col-lg-12", "col-lg-10");
                document.getElementById('side').value = 'show';
            }
        }
    </script>  
    <script>
        var editor = 'desc';
        $(document).ready(function () {
            editor = CKEDITOR.replace('content');
        });
    </script>
    <!--    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
    <script
        src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <script type="text/javascript">

                            </html>
