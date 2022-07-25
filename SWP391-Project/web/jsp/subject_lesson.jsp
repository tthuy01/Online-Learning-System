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

                <div class="subject_lesson mx-auto" style="width: 95%">

                    <form action="subjectlesson" method="post" id="formSL">

                        <h4 class="mt-3 mb-3 "><a href="subjectlist" >Subject List</a><i class="fa-solid fa-angle-right mx-2"></i><a href="subjectdetails?cid=${course.cid}" >Subject Details</a><i class="fa-solid fa-angle-right mx-2"></i>Subject Lesson</h4>
                        <div>
                            <!--                            <h3 class="text-center mt-3 mb-3">Subject Lesssons</h3>-->
                            <span>Subject name: ${course.cname}</span>
                            <input type="text" name="cid" value="${course.cid}" style="display:none;">
                        </div>

                        <div class="d-flex mt-4">
                            <div class="w-75" >
                                <select class="w-50 mr-3" name="sectionselect" onchange="MyFunction()">
                                    <option value="0" ${sectionselect==0?"selected":""}>All section</option>
                                    <c:forEach var="sl" items="${requestScope.listSection1}">
                                        <option value="${sl.sid}" ${sectionselect==sl.sid?"selected":""}>${sl.sname}</option>
                                    </c:forEach>
                                </select>
                                <select class="w-25" name="statusselect" onchange="MyFunction()">
                                    <option value="2" ${statusselect==2?"selected":""}>All status</option>
                                    <option value="1" ${statusselect==1?"selected":""}>Activate</option>
                                    <option value="0" ${statusselect==0?"selected":""}>Inactivate</option>
                                </select>
                            </div>
                            <div class="ml-auto">
                                <input type="button" class="btn btn-primary" value="Add new" onclick="window.window.location = 'lessondetail?cid=${course.cid}'">
                            </div>
                        </div>

                        <c:if test="${listSection.size()==0}">
                            <h5 class="notice-list mt-5 text-center">
                                <span style="color: #e66465; font-weight: bold">Not found</span> the subject lesson that you want.&nbsp;Please <span style="color: green; font-weight: bold">re-do.</span><br>
                                <div class="mt-4"></div>
                                <span class="list-em"><i>The list is empty!</i></span>
                            </h5>
                        </c:if>
                        <c:if test="${listSection.size()!=0}">
                            <table class="table table-hover mt-3 mx-auto border-bottom">
                                <thead>
                                    <tr class="text-center">
                                        <th scope="col">#</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Type</th>
                                        <th scope="col">Status</th>
                                        <th scope="col" style="width: 15%;">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="count" value="0"/>
                                    <c:forEach var="sl2" items="${requestScope.listSection}">
                                        <c:set var="count" value="${count + 1}"/>
                                    <input type="text" name="type" value="3" style="display: none;">
                                    <tr>
                                        <td>${count}</td>
                                        <td>${sl2.sname}</td>
                                        <td class="text-center">Section</td>
                                        <td class="text-center">
                                            <c:if test="${sl2.sstatus == true}">
                                                Activate
                                            </c:if>
                                            <c:if test="${sl2.sstatus == false}">
                                                Inactivate
                                            </c:if>
                                        </td>

                                        <td class=" d-flex justify-content-between" >
                                            <a href="lessondetail?id=${sl2.sid}&type=3" class="ml-4" >Edit</a>
                                            <c:if test="${sl2.sstatus == true}">
                                                <a href="subjectlesson?cid=${course.cid}&sectionselect=${sectionselect}&statusselect=${statusselect}&id=${sl2.sid}&type=3&active=false">Inactivate</a>
                                                <!--<button onclick="MyFunction()" name="active" value="0" style="border: none;background-color: inherit; color: blue">Inactivate</button>-->
                                            </c:if>
                                            <c:if test="${sl2.sstatus == false}">
                                                <a href="subjectlesson?cid=${course.cid}&sectionselect=${sectionselect}&statusselect=${statusselect}&id=${sl2.sid}&type=3&active=true">Activate</a>
                                                <!--<button onclick="MyFunction()" name="active" value="1" style="border: none;background-color: inherit; color: blue">Activate</button>-->
                                            </c:if>
                                            <input type="text" name="id" value="${sl2.sid}" style="display: none;">
                                        </td>
                                    </tr>
                                    <c:forEach var="ll" items="${requestScope.listLesson}">
                                        <c:if test="${sl2.sid == ll.sid}">
                                            <c:set var="count" value="${count + 1}"/>
                                            <input type="text" name="type" value="1" style="display: none;">
                                            <tr>
                                                <td>${count}</td>
                                                <td>${ll.lname}</td>
                                                <td class="text-center font-weight-light">Lesson</td>
                                                <td class="text-center">
                                                    <c:if test="${ll.lstatus == true}">
                                                        Activate
                                                    </c:if>
                                                    <c:if test="${ll.lstatus == false}">
                                                        Inactivate
                                                    </c:if>
                                                </td>

                                                <td class=" d-flex justify-content-between" >
                                                    <a href="lessondetail?id=${ll.lid}&type=1" class="ml-4" >Edit</a>
                                                    <c:if test="${ll.lstatus == true}">
                                                        <a href="subjectlesson?cid=${course.cid}&sectionselect=${sectionselect}&statusselect=${statusselect}&id=${ll.lid}&type=1&active=false">Inactivate</a>
                                                        <!--<button onclick="MyFunction()" name="active" value="0" style="border: none;background-color: inherit; color: blue">Inactivate</button>-->
                                                    </c:if>
                                                    <c:if test="${ll.lstatus == false}">
                                                        <a href="subjectlesson?cid=${course.cid}&sectionselect=${sectionselect}&statusselect=${statusselect}&id=${ll.lid}&type=1&active=true">Activate</a>
                                                        <!--<button onclick="MyFunction()" name="active" value="1" style="border: none;background-color: inherit; color: blue">Activate</button>-->
                                                    </c:if>
                                                    <input type="text" name="id" value="${ll.lid}" style="display: none;">
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach var="ql" items="${requestScope.listQuiz}">
                                        <c:if test="${sl2.sid == ql.sid}">
                                            <c:set var="count" value="${count + 1}"/>
                                            <input type="text" name="type" value="2" style="display: none;">
                                            <tr>
                                                <td>${count}</td>
                                                <td>${ql.qname}</td>
                                                <td class="text-center font-weight-light">Quiz</td>
                                                <td class="text-center">
                                                    <c:if test="${ql.qstatus == true}">
                                                        Activate
                                                    </c:if>
                                                    <c:if test="${ql.qstatus == false}">
                                                        Inactivate
                                                    </c:if>
                                                </td>

                                                <td class=" d-flex justify-content-between" >
                                                    <a href="quizdetails?typeac=update&qid=${ql.qid}&search=&pass=&side=show" class="ml-4" >Edit</a>
                                                    <c:if test="${ql.qstatus == true}">
                                                        <a href="subjectlesson?cid=${course.cid}&sectionselect=${sectionselect}&statusselect=${statusselect}&id=${ql.qid}&type=2&active=false">Inactivate</a>
                                                        <!--<button onclick="MyFunction()" name="active" value="0" style="border: none;background-color: inherit; color: blue">Inactivate</button>-->
                                                    </c:if>
                                                    <c:if test="${ql.qstatus == false}">
                                                        <a href="subjectlesson?cid=${course.cid}&sectionselect=${sectionselect}&statusselect=${statusselect}&id=${ql.qid}&type=2&active=true">Activate</a>
                                                        <!--<button onclick="MyFunction()" name="active" value="1" style="border: none;background-color: inherit; color: blue">Activate</button>-->
                                                    </c:if>
                                                    <input type="text" name="id" value="${ql.qid}" style="display: none;">
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>

                    </form>
                    <script>
                        function MyFunction() {
                            document.getElementById("formSL").submit();
                        }
                        ;
                    </script>

                </div>



            </div>
        </section>        


    </body>
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
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
