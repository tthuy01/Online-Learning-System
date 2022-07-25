<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                min-height: 1000px;
            }
        </style>
        <script src="/js/dashboard.js"></script>
    </head>
    <body>
        <c:if test="${sessionScope.user.rid == 6}">
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
                    <!--Subject List-->
                    <div class="mt-3 mb-5">
                        <div class="d-flex justify-content-start mb-1 ml-3">
                            <h4><strong>Subject List</strong></h4>
                        </div>
                        <form class="d-flex" action="subjectlist">

                            <div class="ml-3" style="width: 20%">
                                <!--Search button-->
                                <div class="w-75 d-flex mt-1 mb-1">
                                    <input class="form-control mr-sm-2" list="searchcname" type="search" name="search" value="${search}" placeholder="Subject name..." aria-label="Search">
                                    <datalist id="searchcname" >
                                        <c:forEach items="${listSearchCname}" var="listSearch">
                                            <option value="${listSearch.cname}"></option>
                                        </c:forEach>
                                    </datalist>
                                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">
                                        <i class="fa-solid fa-magnifying-glass"></i> 
                                    </button>
                                </div>

                                <!--Checkbox-->
                                <h5><strong>Filter</strong><img style="height: 30px; width: 30px" src="https://png.pngtree.com/png-vector/20191001/ourlarge/pngtree-filter-icon-isolated-on-abstract-background-png-image_1769008.jpg"></h5>
                                <div class="mt-1">
                                    <h5 style="color: blue">Subject Category:</h5>
                                    <c:forEach begin="0" end="${listSubject.size()-1}" var="i">
                                        <input type="checkbox" name="sid" value="${listSubject.get(i).getSid()}"
                                               ${sid[i]?"checked" : ""} onclick="this.form.submit()">${listSubject.get(i).getSname()}<br/> 
                                    </c:forEach>
                                </div>
                                <div class="mt-1">
                                    <h5 style="color: blue">Status:</h5>
                                    <input type="checkbox" name="cstatus" value="0"
                                           ${cstatus[0]?"checked" : ""} onclick="this.form.submit()">Unpublished<br/> 
                                    <input type="checkbox" name="cstatus" value="1"
                                           ${cstatus[1]?"checked" : ""} onclick="this.form.submit()">Published<br/> 
                                </div>
                            </div>

                            <c:if test="${totalCourse != 0}">
                                <div class="table table-hover ml-auto mr-4" style="width: 80%; margin-bottom: 100px">

                                    <div class="d-flex justify-content-end mb-1">
                                        <!--<h5><strong>Course List</strong><span style="font-size: 17px; color: lightgray;"><em> (${listCourse.size()} of ${totalCourse} courses)</em></span></h5>-->
                                        <a class="btn btn-primary" href="newsubject">New Subject</a>
                                    </div>
                                    <div class="table-responsive-md mb-5">
                                        <table class="w-100">
                                            <thead class="thead-light">
                                                <tr>
                                                    <th scope="col">Id</th> 
                                                    <th scope="col">Name</th> 
                                                    <th scope="col">Category</th> 
                                                    <th scope="col">Number of lessons</th> 
                                                    <th scope="col">Owner</th> 
                                                    <th scope="col">Status</th> 
                                                    <th scope="col">Action</th> 
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listCourse}" var="listC">
                                                    <tr>
                                                        <!--Id-->
                                                        <td scope="row">${listC.cid}</td>
                                                        <!--Name-->
                                                        <td>${listC.cname}</td>
                                                        <!--Category-->
                                                        <c:forEach items="${listSubject}" var="listS">
                                                            <c:if test="${listS.sid==listC.sid}">
                                                                <td>${listS.sname}</td>
                                                            </c:if> 
                                                        </c:forEach>
                                                        <!--Number of lessons-->
                                                        <c:forEach items="${listTotalLesson}" var="listTL">
                                                            <c:if test="${listTL.cid==listC.cid}">                  
                                                                <c:if test="${listTL.total != null}">
                                                                    <td>${listTL.total}</td>  
                                                                </c:if>
                                                                <c:if test="${listTL.total == null}">
                                                                    <td>0</td> 
                                                                </c:if>
                                                            </c:if>
                                                        </c:forEach>   
                                                        <!--Owner-->
                                                        <c:forEach items="${listUser}" var="listU">
                                                            <c:forEach items="${listManageCourse}" var="listMC">
                                                                <c:if test="${listMC.cid == listC.cid && listMC.uid == listU.uid}">
                                                                    <c:if test="${listU.rid == 5}">
                                                                        <td>${listU.ufullname}<em> (Expert) </em></td>
                                                                    </c:if>
                                                                    <c:if test="${listU.rid == 6}">
                                                                        <td>${listU.ufullname}<em> (Admin) </em></td>
                                                                    </c:if>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:forEach>
                                                        <!--Status-->
                                                        <c:if test="${listC.cstatus == false}">
                                                            <td style="color: red; font-weight: bold">Unpublished</td>
                                                        </c:if>
                                                        <c:if test="${listC.cstatus == true}">
                                                            <td style="color: blue; font-weight: bold">Published</td>
                                                        </c:if>
                                                        <!--Action-->
                                                        <td><a href="subjectdetails?cid=${listC.cid}"><i class="bi bi-pencil-square"></i></a></td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <c:if test="${totalCourse > 5}">
                                        <div class="d-flex justify-content-center">
                                            <nav aria-label="Page navigation example">
                                                <ul class="pagination">
                                                    <c:if test="${!(tag == 1)}">
                                                        <li class="page-item"><button type="submit" class="page-link" name="page" value="${tag-1}">Previous</button></li>
                                                        </c:if>
                                                        <c:forEach begin="1" end="${endPage}" var="i">
                                                        <li class="page-item ${tag == i ? "active" : ""}"><button type="submit" class="page-link" name="page" value="${i}">${i}</button></li>
                                                        </c:forEach>
                                                        <c:if test="${!(tag == endPage || endPage == 0)}">
                                                        <li class="page-item"><button type="submit" class="page-link" name="page" value="${tag+1}">Next</button></li>
                                                        </c:if>

                                                </ul>
                                            </nav>
                                        </div>
                                    </c:if>    
                                </div>
                            </c:if>
                            <c:if test="${totalCourse == 0}">
                                <div style="margin: 200px 0 200px 350px">
                                    <p style="color: red; font-size: 30px"><strong>Empty!</strong></p>
                                </div>
                            </c:if>
                            <input hidden id="side" name="side" type="text" value="${side}"/>
                        </form>
                    </div>
                </div>
            </section>        
        </c:if>


        <c:if test="${sessionScope.user.rid == 5}">       
            <jsp:include page="header.jsp"/>
            <!--Subject List-->
            <div style="margin-top: 100px">
                <div class="d-flex justify-content-start mb-1 ml-3">
                    <h4><strong>Subject List</strong></h4>
                </div>
                <form class="d-flex" action="subjectlist">
                    <div class="ml-3" style="width: 20%">
                        <!--Search button-->
                        <div class="w-75 d-flex mt-1 mb-1">
                            <input class="form-control mr-sm-2" type="search" name="search" value="${search}" placeholder="Subject name..." aria-label="Search">
                            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">
                                <i class="fa-solid fa-magnifying-glass"></i> 
                            </button>
                        </div>

                        <!--Checkbox-->
                        <h5><strong>Filter</strong><img style="height: 35px; width: 35px" src="https://png.pngtree.com/png-vector/20191001/ourlarge/pngtree-filter-icon-isolated-on-abstract-background-png-image_1769008.jpg"></h5>
                        <div class="mt-1">
                            <h5 style="color: blue">Subject Category:</h5>
                            <c:forEach begin="0" end="${listSubject.size()-1}" var="i">
                                <input type="checkbox" name="sid" value="${listSubject.get(i).getSid()}"
                                       ${sid[i]?"checked" : ""} onclick="this.form.submit()">${listSubject.get(i).getSname()}<br/> 
                            </c:forEach>
                        </div>
                    </div>

                    <c:if test="${totalCourse != 0}">
                        <div class="table table-hover ml-auto mr-4" style="width: 80%">
                            <div class="table-responsive-md mb-5">
                                <table class="w-100">
                                    <thead class="thead-light">
                                        <tr>
                                            <th scope="col">Id</th> 
                                            <th scope="col">Name</th> 
                                            <th scope="col">Category</th> 
                                            <th scope="col">Number of lessons</th> 
                                            <th scope="col">Action</th> 
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${listCourse}" var="listC">
                                            <tr>
                                                <!--Id-->
                                                <td scope="row">${listC.cid}</td>
                                                <!--Name-->
                                                <td>${listC.cname}</td>
                                                <!--Category-->
                                                <c:forEach items="${listSubject}" var="listS">
                                                    <c:if test="${listS.sid==listC.sid}">
                                                        <td>${listS.sname}</td>
                                                    </c:if> 
                                                </c:forEach>
                                                <!--Number of lessons-->
                                                <c:forEach items="${listTotalLesson}" var="listTL">
                                                    <c:if test="${listTL.cid==listC.cid}">                  
                                                        <c:if test="${listTL.total != null}">
                                                            <td>${listTL.total}</td>  
                                                        </c:if>
                                                        <c:if test="${listTL.total == null}">
                                                            <td>0</td> 
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                                <td><a href="subjectdetails?cid=${listC.cid}"><i class="bi bi-pencil-square"></i></a></td>
                                            </tr>            
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <c:if test="${totalCourse > 5}">
                                <div class="d-flex justify-content-center">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination">
                                            <c:if test="${!(tag == 1)}">
                                                <li class="page-item"><button type="submit" class="page-link" name="page" value="${tag-1}">Previous</button></li>
                                                </c:if>
                                                <c:forEach begin="1" end="${endPage}" var="i">
                                                <li class="page-item ${tag == i ? "active" : ""}"><button type="submit" class="page-link" name="page" value="${i}">${i}</button></li>
                                                </c:forEach>
                                                <c:if test="${!(tag == endPage || endPage == 0)}">
                                                <li class="page-item"><button type="submit" class="page-link" name="page" value="${tag+1}">Next</button></li>
                                                </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </c:if>     
                        </div>

                    </c:if>

                    <c:if test="${totalCourse == 0}">
                        <div style="margin: 200px 0 200px 350px">
                            <p style="color: red; font-size: 30px"><strong>Empty!</strong></p>
                        </div>
                    </c:if>       

                </form>
            </div>                
        </c:if>
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
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
