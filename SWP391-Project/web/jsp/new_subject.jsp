<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>New Subject</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <meta name="viewport" content="width=device-w`idth, initial-scale=1.0" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
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
                        <li style="background-color: #425164;">
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
                        <li >
                            <a href="userlist">
                                <i class="fa fa-dashboard"></i>
                                <span>User List</span>
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
            <div id="right" class="col-lg-10 col-md-8 col-12" >
                <jsp:include page="header_admin.jsp"/>
                <!--New Subject-->
                <div class="mb-5">
                    <div class="d-flex align-items-center" style="margin:30px 0 20px 15px">
                        <a href="subjectlist" title="subjectlist"><h4><strong>Subject List</strong></h4></a><h4><strong>&nbsp;>&nbsp;New Subject</strong></h4>
                    </div>

                    <form action="newsubject" enctype="multipart/form-data" method="post" onsubmit="return submitForm1(this);">
                        <div style="margin: 0 15px">
                            <div class="row">
                                <div class="input-group col-md-6">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Subject Id</span>
                                    </div>
                                    <input required name="cid" type="text" placeholder="Eg:English(ENG)" class="form-control" aria-describedby="basic-addon1">
                                </div>
                                <div class="input-group col-md-6">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Subject Name</span>
                                    </div>
                                    <input required name="cname" type="text" placeholder="Enter..." class="form-control" aria-describedby="basic-addon1">
                                </div>

                            </div>
                            <div class="row mt-3">
                                <div class="col-md-6">
                                    <div class="input-group-prepend">
                                        <label class="input-group-text justify-content-center" style="width: 100%;" for="cimg">Subject Image</label>
                                    </div>
                                    <div class="d-flex flex-column">
                                        <img class="img-thumbnail" id="cimg" src="https://i.pinimg.com/564x/49/0e/1e/490e1e1721ea8c8eba038bc7807ee8ba.jpg" alt="Course Thumbnail"
                                             style="width: 100%;height: 300px">
                                        <label for="cfile"><span style="border: 1px solid black;"><b>Upload</b></span></label>
                                        <input id="cfile" type="file" onchange="preview_image_cimg(event)" name="cimg" accept="image/jpeg, image/png, image/jpg" style="height: 30px; width: 0px;">
                                    </div>

                                </div>
                                <div class="col-md-6 d-flex">
                                    <div class="col-md-12" style="padding: 0">
                                        <div class="input-group mb-4">
                                            <div class="input-group-prepend">
                                                <label class="input-group-text" for="subject">Subject Category</label>
                                            </div>

                                            <input id="subject" name="sname" list="listSubject" placeholder="Choose...">
                                            <datalist id="listSubject">
                                                <c:forEach items="${listSubject}" var="ls">
                                                    <option value="${ls.sname}"></option>
                                                </c:forEach>
                                            </datalist>

                                        </div>
                                        <em style="color: gray; font-size: 13px">(<span style="color: red">Note: </span><span style="color: blue">Published Status</span> must be change in the <span style="color: blue">Subject Detail</span> screen.)</em>
                                        <div class="input-group mb-5">
                                            <div class="input-group-prepend">
                                                <label class="input-group-text" for="inputGroupSelect01">Status</label>
                                            </div>
                                            <input id="inputGroupSelect01" type="text" placeholder="Unpublished" disabled>
                                        </div>
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <label class="input-group-text" for="inputGroupSelect01">Owner</label>
                                            </div>
                                            <select name="uidManageCourse" class="custom-select" id="inputGroupSelect01">
                                                <option value="-1" hidden selected>Choose expert...</option>
                                                <c:forEach items="${listUserByExpert}" var="lube">
                                                    <option value="${lube.uid}">${lube.ufullname}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div>
                                        <label class="input-group-text justify-content-center" for="contentCourse">Subject Description</label>
                                        <textarea id="contentCourse" name="cdes" row="100"></textarea>
                                    </div>
                                </div>
                                <div class="m-3">
                                    <button type="submit" class="btn btn-primary">Add Subject</button>
                                </div>
                            </div>
                        </div>                            
                    </form>
                </div>
            </div>
        </section>
        <!--Alert-->
        <c:if test="${alertNewSub != null}">
            <button hidden id="myModalBt" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"></button>
            <div id="myModal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Notification</h5>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <p>Add <strong style="color: blue">New Course</strong> Successful!!!</p>
                        </div>
                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-primary">Continue</button>
                        </div>
                    </div>
                </div>
            </div>                                                 
        </c:if>
        <!--Error-->
        <c:if test="${error != null}">
            <button hidden id="alertErrorBt" type="button" class="btn btn-primary" data-toggle="modal" data-target="#alertError"></button>
            <div id="alertError" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Notification</h5>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <p><strong style="color: red">${error}!!!</strong></p>
                        </div>
                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-primary">Continue</button>
                        </div>
                    </div>
                </div>
            </div>  
        </c:if> 

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

        function preview_image_cimg(event) {
            var reader = new FileReader();
            reader.onload = function () {
                var output = document.getElementById('cimg');
                output.src = reader.result;
            }
            reader.readAsDataURL(event.target.files[0]);
        }

        function submitForm1() {
            return confirm('Do you really want to add this New Subject?');
        }
    </script>  

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script>
        <c:if test="${alertNewSub != null}">
        window.onload = function () {
            document.getElementById('myModalBt').click();
        }
        </c:if>
        <c:if test="${error != null}">
        window.onload = function () {
            document.getElementById('alertErrorBt').click();
        }
        </c:if>
        var editor = '';
        $(document).ready(function () {
            CKEDITOR.replace('contentCourse');
        });
    </script>
</html>
