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
                        <li style="background-color: #425164;">
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
                        <li>
                            <a href="sliderlist">
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
            <div id="right" class="col-lg-10 col-md-8 col-12" style="${listCourse.size() < 5 ? "margin-bottom: 200px" : ""}">
                <jsp:include page="header_admin.jsp"/>

                <div class="Post_list mx-auto" style="width: 95%; ">
                    <!--                    <div class="mx-auto mt-3 mb-4" style="width: max-content;">
                                            <h4 class="">Post List</h4>
                                        </div>-->

                    <h4 class="mt-3 mb-3 ">Post List</h4>

                    <div class="addnewpost d-flex align-items-end" >

                        <form class="form-inline my-2 my-lg-0 " method="get" action="postlist">
                            <input class="form-control mr-sm-2" style="width: 250px" type="search" name="key" value="${key}" placeholder="Search Post" aria-label="Search">
                            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                                    class="fa-solid fa-magnifying-glass"></i> </button>
                        </form>


                        <div class="ml-auto" style="width: max-content;">
                            <input class="btn btn-primary " type="button" value="Add new Post" onclick="Addnew()" style="color: white;">
                        </div>

                    </div>
                    <c:if test="${listPost.size()==0}">
                        <h5 class="notice-list mt-5 text-center">
                            <span style="color: #e66465; font-weight: bold">Not found</span> the post list that you want.&nbsp;Please <span style="color: green; font-weight: bold">re-do.</span><br>
                            <div class="mt-4"></div>
                            <span class="list-em"><i>The list is empty!</i></span>
                        </h5>
                    </c:if>
                    <c:if test="${listPost.size()!=0}">
                        <table class="table table-hover mt-3 mx-auto border-bottom" >
                            <thead>
                                <tr class="text-center">
                                    <th scope="col">Image</th>
                                    <th scope="col" style="width: 55%;">Title</th>
                                    <th scope="col">Update Date</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="listPost" items="${requestScope.listPost}">
                                    <tr>
                                        <td style="width: 20%">
                                            <img src="${listPost.poimg}" style="width: 100%;" >
                                        </td>
                                        <td>${listPost.potitle}</td>
                                        <td class="text-center">${listPost.podate}</td>
                                        <td class="text-center">
                                            <a href="postdetail?pid=${listPost.poid}" style="margin-right: 20px;">Edit</a>
                                            <c:if test="${listPost.postatus == true}">
                                                <a href="postlist?bid=${bid}&key=${key}&page=${page}&pid=${listPost.poid}&hide=0">Hiden Post</a>
                                            </c:if>
                                            <c:if test="${listPost.postatus == false}">
                                                <a href="postlist?bid=${bid}&key=${key}&page=${page}&pid=${listPost.poid}&hide=1">Show Post</a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            <tbody>
                        </table>
                    </c:if>
                    <c:if test="${endPage>1}">
                        <nav aria-label="..." class="mt-5 mx-auto" style="width: max-content;">
                            <ul class="pagination ">
                                <li class="page-item ${page==1?"disabled":""}">
                                    <a class="page-link" href="postlist?bid=${bid}&key=${key}&page=${page-1}" tabindex="-1">Previous</a>
                                </li>
                                <c:forEach begin="${1}" end="${endPage}" var="i">
                                    <li class="page-item ${page==i?"active":""}">
                                        <a class="page-link" href="postlist?bid=${bid}&key=${key}&page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${page==endPage?"disabled":""}">
                                    <a class="page-link" href="postlist?bid=${bid}&key=${key}&page=${page+1}">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>


                    <script>
                        function Addnew() {
                            window.location = "addpost";
                        }
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
