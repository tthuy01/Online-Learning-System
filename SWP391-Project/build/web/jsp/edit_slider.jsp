<%-- 
    Document   : edit_slider
    Created on : Jul 16, 2022, 11:17:57 AM
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
        <script src="ckeditor/ckeditor.js" ></script>
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
                <jsp:include page="../jsp/header_admin.jsp"/>
                <h4 class="mt-3 mb-3 mx-5"><a href="sliderlist" >Sliders List</a><i class="fa-solid fa-angle-right mx-2"></i>
                    <a href="sliderdetails?id=${slider.id}">Slider Details</a><i class="fa-solid fa-angle-right mx-2"></i>Edit Slider <span style="color: red;margin-left: 300px">${mess}</span></h4>
                <div class="mx-5">
                    <form method="post" action="editslider" enctype="multipart/form-data">
                        <input hidden type="text" name="id" value="${slider.id}"/>
                        <div class="form-row">
                            <div class="form-row col-md-6">
                                <div class="form-group col-md-12">
                                    <label>Backlink</label>
                                    <input type="text" class="form-control" name="link" id="link" placeholder="Enter Backlink" value="${slider.link}">
                                </div>
                                <div class="form-group col-md-12">
                                    <label>Note</label>
                                    <input type="text" class="form-control" name="note" id="note" placeholder="Enter Note" value="${slider.note}">
                                </div>
                                <div class="form-group col-md-4">
                                    <label>Status</label>
                                    <select id="status" name="status" class="form-control">
                                        <option ${slider.status==true?"selected":""} value="1">Show</option>
                                        <option ${slider.status==false?"selected":""} value="0">Hide</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row col-md-6">
                                <div class="form-group col-md-12 d-flex flex-column">
                                    <label>Image</label>
                                    <img id="blah" class="img-fluid" style="width: 100%;height: 200px;border: 1px solid black" src="./images/slider/${slider.image}" alt="your image"/>
                                    <input style="margin-top: 10px" type='file' id="imgInp" name="image"/>
                                    <input hidden type="text" name="filename" value="${slider.image}"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputAddress">Title</label>
                            <textarea id="inputTitle" name="title">${slider.title}</textarea>
                        </div>
                        <span onclick="window.location = 'editslider?id=${slider.id}'" class="btn btn-primary">Undo Changes</span>
                        <button type="submit" class="btn btn-primary">Save Changes</button>
                    </form>
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
                            $(document).ready(function () {
                                CKEDITOR.replace('inputTitle');
                            });
                            imgInp.onchange = evt => {
                                const [file] = imgInp.files;
                                if (file) {
                                    blah.src = URL.createObjectURL(file);

                                }
                            };
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

    </script>
</html>
