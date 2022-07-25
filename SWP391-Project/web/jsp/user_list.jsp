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
                        <li>
                            <a href="sliderlist">
                                <i class="fa fa-dashboard"></i>
                                <span>Sliders List</span>
                            </a>
                        </li>
                        <li style="background-color: #425164;">
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

                <div class="userList mx-auto" style="width: 95%">
                    <form action="userlist" method="post" id="form1">
                        <!--                        <div class="mx-auto mt-3" style="width: max-content;">
                                                    <h4 class="">User List</h4>
                                                </div>-->

                        <h4 class="mt-3 mb-3 ">User List</h4>
                        <div class=" d-flex align-items-end" >
                            <div>
                                <h5>Sort By</h5>
                                <select name="sort" onchange="Submit()">
                                    <option value="1" ${sort==1?"selected":""}>ID</option>
                                    <option value="2" ${sort==2?"selected":""}>Fullname</option>
                                    <option value="3" ${sort==3?"selected":""}>Gender</option>
                                    <option value="4" ${sort==4?"selected":""}>Email</option>
                                    <option value="5" ${sort==5?"selected":""}>Phone</option>
                                    <option value="6" ${sort==6?"selected":""}>Role</option>
                                    <option value="7" ${sort==7?"selected":""}>Status</option>

                                </select>
                            </div>
                            <div class="addnewpost ml-auto" >
                                <div class="ml-auto" style="width: max-content;">
                                    <input class="btn btn-primary " type="button" value="Add new User" onclick="window.location = 'userdetail'" style="color: white;">
                                </div>
                            </div>
                        </div>
                        <table class="table table-hover mt-3 mx-auto border-bottom ">
                            <thead>
                                <tr >
                                    <th>UserID</th>
                                    <th>Full Name</th>
                                    <th>Gender</th>
                                    <th>Email</th>
                                    <th>Role</th>
                                    <th>Phone</th>                                  
                                    <th>Status</th>
                                    <th>Active</th>
                                </tr>
                                <tr>
                                    <th></th>
                                    <th class="d-flex">
                                        <!--Full Name--> 
                                        <input name="name" value="${name}" placeholder="Search by Name" style="width: 70%">
                                        <button class="btn btn-outline-primary my-2 my-sm-0" type="submit" style="font-size: 50%;"><i
                                                class="fa-solid fa-magnifying-glass" style="font-size: 200%;"></i> </button>
                                    </th>
                                    <th>
                                        <!--Gender--> 
                                        <select name="gender" onchange="Submit()">
                                            <option value="0" ${gender==0?"selected":""}>All gender</option>
                                            <option value="1" ${gender==1?"selected":""}>Male</option>
                                            <option value="2" ${gender==2?"selected":""}>Female</option>
                                            <option value="3" ${gender==3?"selected":""}>Orther</option>
                                            <option value="4" ${gender==4?"selected":""}>Undisclosed</option>
                                        </select>
                                    </th>

                                    <th class="d-flex">
                                        <!--Email--> 
                                        <input name="email" value="${email}" placeholder="Search by Email" style="width: 70%">
                                        <button class="btn btn-outline-primary my-2 my-sm-0" type="submit" style="font-size: 50%;"><i
                                                class="fa-solid fa-magnifying-glass" style="font-size: 200%;"></i> </button>
                                    </th>
                                    <th>
                                        <!--Role--> 
                                        <select name="role" onchange="Submit()">
                                            <option value="0" ${role==0?"selected":""}>All Role</option>
                                            <option value="2" ${role==2?"selected":""}>Customer</option>
                                            <option value="3" ${role==3?"selected":""}>Sale</option>
                                            <option value="4" ${role==4?"selected":""}>Marketing</option>
                                            <option value="5" ${role==5?"selected":""}>Expert</option>
                                            <option value="6" ${role==6?"selected":""}>Admin</option>
                                        </select>
                                    </th> 
                                    <th class="d-flex">
                                        <!--Phone--> 
                                        <input name="phone" value="${phone}" placeholder="Search by Phone" style="width: 70%">
                                        <button class="btn btn-outline-primary my-2 my-sm-0" type="submit" style="font-size: 50%;"><i
                                                class="fa-solid fa-magnifying-glass" style="font-size: 200%;"></i> </button>
                                    </th>

                                    <th>
                                        <!--Status--> 
                                        <select name="status" onchange="Submit()">
                                            <option value="2" ${status==2?"selected":""}>All status</option>
                                            <option value="1" ${status==1?"selected":""}>Activate</option>
                                            <option value="0" ${status==0?"selected":""}>Inactivate</option>
                                        </select>
                                    </th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="l" items="${requestScope.list}">
                                    <tr>
                                        <td>${l.uid}</td>
                                        <td>${l.ufullname}</td>
                                        <td>
                                            <c:if test="${l.gid == 1}">Male</c:if>
                                            <c:if test="${l.gid == 2}">Female</c:if>
                                            <c:if test="${l.gid == 3}">Orther</c:if>
                                            <c:if test="${l.gid == 4}">Undisclosed</c:if>
                                            </td>
                                            <td>${l.uemail}</td>
                                        <td>
                                            <c:if test="${l.rid == 2}">Customer</c:if>
                                            <c:if test="${l.rid == 3}">Sale</c:if>
                                            <c:if test="${l.rid == 4}">Marketing</c:if>
                                            <c:if test="${l.rid == 5}">Expert</c:if>
                                            <c:if test="${l.rid == 6}">Admin</c:if>                                  
                                            </td>
                                            <td>${l.uphone}</td>

                                        <td>
                                            <c:if test="${l.ustatus == true}">Activate</c:if>
                                            <c:if test="${l.ustatus == false}">Inactivate</c:if>

                                            </td>
                                            <td class="text-center"><a href="userdetail?id=${l.uid}">Edit</a></td>
                                    </tr>
                                </c:forEach> 
                            </tbody>
                        </table>
                        <c:if test="${list.size()==0}">
                            <h5 class="notice-list mt-5 text-center">
                                <span style="color: #e66465; font-weight: bold">Not found</span> the user list that you want.&nbsp;Please <span style="color: green; font-weight: bold">re-do.</span><br>
                                <div class="mt-4"></div>
                                <span class="list-em"><i>The list is empty!</i></span>
                            </h5>
                        </c:if>

                        <c:if test="${endPage > 1}">
                            <nav aria-label="..." class="mt-5 mx-auto" style="width: max-content;">
                                <ul class="pagination ">
                                    <li class="page-item ${page==1?"disabled":""}">
                                        <a class="page-link" href="userlist?name=${name}&gender=${gender}&email=${email}&role=${role}&phone=${phone}&status=${status}&sort=${sort}&page=${page-1}" tabindex="-1">Previous</a>
                                    </li>
                                    <c:forEach begin="${1}" end="${endPage}" var="i">
                                        <li class="page-item ${page==i?"active":""}">
                                            <a class="page-link" href="userlist?name=${name}&gender=${gender}&email=${email}&role=${role}&phone=${phone}&status=${status}&sort=${sort}&page=${i}">${i}</a>
                                        </li>
                                    </c:forEach>
                                    <li class="page-item ${page==endPage?"disabled":""}">
                                        <a class="page-link" href="userlist?name=${name}&gender=${gender}&email=${email}&role=${role}&phone=${phone}&status=${status}&sort=${sort}&page=${page+1}">Next</a>
                                    </li>
                                </ul>
                            </nav>
                        </c:if>
                    </form>

                    <script>
                        function Submit() {
                            document.getElementById("form1").submit();
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
