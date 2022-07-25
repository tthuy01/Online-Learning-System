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
                    
                    <div class="">

                    <nav class="body_content  mx-auto" style="width: 95%;">
                        
                        <h4 class="mt-3 mb-3 "><a href="postlist" >Post List</a><i class="fa-solid fa-angle-right mx-2"></i>Post Details</h4>
                        
                        <div class="mb-3 mx-auto justify-content-center" style="width: max-content;">
                            <span class="" style="color: red">${requestScope.successfull}</span>               
                        </div>
                        <div class="mb-3 mx-auto justify-content-center" style="width: max-content;">
                            <c:if test="${successfull eq 'Update post detail successfully!'}">
                                <input class="btn btn-primary" type="button" value="Go to Post List" onclick="Back()">
                            </c:if>
                        </div>
<!--                        <div class="title">
                            <h4>Post Detail</h4>
                        </div>-->
                        <form action="postdetail" method="post">
                            <div class="body_content_head row no-gutters mt-5">
                                <div class="body_head_left col-12 col-sm-6 col-md-8 mb-3">
                                    <span>Post Name</span><br>
                                    <!--                        <input class="mt-3 mb-4 w-75" name="name" type="text" value="${post.potitle}"><br>-->
                                    <textarea name="title" class="mt-3 mb-4 w-75">${post.potitle}</textarea><br>
                                    <span>Category</span><br>
                                    <select class="mt-3 mb-4 w-75" name="category">
                                        <c:forEach var="bl" items="${requestScope.listBlog}">
                                            <option ${bl.bid==post.bid?"selected":""} value="${bl.bid}">${bl.bname}</option>
                                        </c:forEach>
                                    </select><br>
                                    <span class="mr-4">Status</span>
                                    <select name="status" class="mb-4">
                                        <option value="1" ${post.postatus==true?"selected":""}>Show</option>
                                        <option value="0" ${post.postatus==false?"selected":""}>Hide</option>
                                    </select><br>
                                    <span class="mr-4">Last date update</span>
                                    <c:set var="year" value="${fn:substring(post.podate, 0, 4)}" />
                                    <c:set var="month" value="${fn:substring(post.podate, 5, 7)}" />
                                    <c:set var="day" value="${fn:substring(post.podate, 8, 10)}" />
                                    <input value="${day} / ${month} / ${year}" readonly="">
                                </div>
                                <diV class="body_head_right col-6 col-md-4 mb-3">
                                    <img id="output" class="w-75" src="${post.poimg}">
                                    <input name="imgvalue" id="imgdetail" value="${post.poimg}" style="display: none;">
                                    <div class="choose mt-3">
<!--                                        <div class="uploaad_img">
                                            <input type="file" accept="image/*" name="image" id="file"
                                                   onchange="loadFile(event)" style="display: none;">
                                            <label for="file"
                                                   style="cursor: pointer;border: 1px solid;padding: 5px;">Upload
                                                Image</label>
                                        </div>-->
                                        <div class="link_img mt-2">
                                            <span>Or image link in online library</span><br>
                                            <input class="mt-2" type="text" id="image">
                                            <!--                                <button onclick="MyFunction()">Choose image</button>-->
                                            <input type="button" value="Choose image" onclick="MyFunction()">
                                            <input name="pid" value="${post.poid}" style="display: none;">
                                        </div>
                                    </div>
                                </div>

                            </diV>
                            <div class="body_content_foot mt-4">
                                <div class="mb-2">Description</div>
                                <textarea name="desc" id="content" cols="1000" rows="10">${post.podesc}</textarea>
                            </div>
                            <div class="mt-4">
                                <input class="btn btn-primary" type="submit" value="Save Change">
                                <input class="ml-3 btn btn-primary" type="button" value="Back to post list" onclick="Back()">
                            </div>
                        </form>
                    </nav>



                    <script>
                        var loadFile = function (event) {
                            var image = document.getElementById('output');
                            var images = document.getElementById('img');
                            image.src = URL.createObjectURL(event.target.files[0]);
                            images.value = URL.createObjectURL(event.target.files[0]);
                        };
                        function MyFunction() {
                            var image = document.getElementById('output');
                            var images = document.getElementById('imgdetail');
                            var imag = document.getElementById('image').value;
                            if (imag !== "") {
                                image.src = imag;
                                images.value = imag;
                            }

                        }
                        ;
                        function Back() {
                            window.location = "postlist";
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
