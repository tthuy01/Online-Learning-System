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
                        <li >
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

                <div class="userdetail mx-auto" style="width: 95%">


                    <form action="userdetail" enctype="multipart/form-data" method="post"> 
                        <c:if test="${id!=0}">
                            <h4 class="mt-3 mb-3 "><a href="userlist" >User List</a><i class="fa-solid fa-angle-right mx-2"></i>User Details</h4>
                        </c:if>
                        <c:if test="${id==0}">
                            <h4 class="mt-3 mb-3 "><a href="userlist" >User List</a><i class="fa-solid fa-angle-right mx-2"></i>New User </h4>
                        </c:if>

                        <div class="mt-4 mb-3 mx-auto justify-content-center" style="width: max-content;">
                            <span class="" style="color: red">${requestScope.successfull}</span>               
                        </div>
                        <div class="mb-3 mx-auto justify-content-center" style="width: max-content;">
                            <c:if test="${successfull != null}">
                                <input class="mt-2 btn btn-primary" type="button" value="Back to User List" onclick="Back()">
                            </c:if>
                        </div>
                        <!--                        <div class="mx-auto mt-3 mb-5" style="width: max-content;">
                                                    <h4 class="">User Detail</h4>
                                                </div>-->

                        <!--img user-->
                        <div class="text-center mb-5">
                            <input type="text" name="id" value="${id}" style="display: none;">
                            <c:if test="${user1.uimg != null}">
                                <img id="img1" src="images/avatar/${user1.uimg}" alt="avatar user" style="height: 100px; width: 100px; border-radius: 50%;">
                            </c:if>
                            <c:if test="${user1.uimg == null}">
                                <img id="img1" src="images/avatar/default.jpg" alt="avatar user" style="height: 100px; width: 100px; border-radius: 50%;">
                            </c:if>
                            <input id="imgload" type="file" name="img" accept="image/jpeg, image/png, image/jpg" style="height: 30px; width: 80px">
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
                                    <c:if test="${id == 0}">
                                        <div class="d-flex justify-content-end" style="margin: 30px 0 0 0;">
                                            <p style="font-weight: bold;">Password</p>
                                        </div>
                                    </c:if>
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
                                    <div class="d-flex justify-content-end" style="margin: 30px 0 0 0;">
                                        <p style="font-weight: bold;">Role</p>
                                    </div> 
                                    <div class="d-flex justify-content-end" style="margin: 30px 0 0 0;">
                                        <p style="font-weight: bold;">Status</p>
                                    </div> 
                                </div>
                            </div>

                            <div class="d-flex flex-column">
                                <!--input Name-->
                                <input name="name" value="${user1.ufullname}" style="width: 400px;height: 40px;" required>  
                                <!--input Email-->

                                <c:if test="${id!=0}">
                                    <div style="margin: 5px 0 0 0">
                                        <p style="font-style: italic; font-size: smaller;margin: 3px 0 0 0; color: #3333ff">You cannot edit email.</p>
                                        <input name="email" type="text" style="width: 400px;height: 40px;" readonly value="${user1.uemail}" required>
                                    </div>
                                </c:if>
                                <c:if test="${id==0}">
                                    <div style="margin: 30px 0 0 0;">
                                        <input name="email" type="text" style="width: 400px;height: 40px;" value="${user1.uemail}" required>
                                    </div>
                                </c:if>


                                <!--input Password--> 
                                <c:if test="${id == 0}">
                                    <div style="margin: 30px 0 0 0;"> 
                                        <input name="password" type="password" style="width: 400px;height: 40px;" required>
                                    </div>
                                </c:if>

                                <!--input Phone-->    
                                <div style="margin: 30px 0 0 0;"> 
                                    <c:if test="${user1.uphone != null}">
                                        <input name="phone" type="text" pattern="[0-9]{10,11}" title="Wrong format.Enter your phone." value="${user1.uphone}"  style="width: 400px;height: 40px;" required>
                                    </c:if>
                                    <c:if test="${user1.uphone == null}">
                                        <input name="phone" type="text" placeholder="0123456789" pattern="[0-9]{10,11}" title="Wrong format.Enter your phone." value="${user1.uphone}" style="width: 400px;height: 40px;" required>
                                    </c:if>
                                </div>

                                <!--input Dob-->
                                <div style="margin: 30px 0 0 0;">
                                    <c:if test="${user1.udob != null}">
                                        <input name="dob" type="date" value="${user1.udob}" pattern="(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-\d{4}" style="width: 400px;height: 40px;" required>
                                    </c:if>
                                    <c:if test="${user1.udob == null}">
                                        <input name="dob" type="date" value="${user1.udob}"  placeholder="dd-mm-yyyy" id="uDob" pattern="(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-\d{4}" style="width: 400px;height: 40px;" required> 
                                    </c:if>
                                </div>

                                <!--input Gender-->
                                <div class="d-flex align-items-center" style="margin: 30px 0 0 0; width: 400px;height: 40px;">
                                    <input  name="gender" <c:if test="${user1.gid == 1 || user1.gid == null}">checked</c:if> value="1" style="margin: 0 0.5vw 0 2vw;" type="radio">Male
                                    <input  name="gender" <c:if test="${user1.gid == 2}">checked</c:if> value="2" style="margin: 0 0.5vw 0 2vw;" type="radio">Female
                                    <input  name="gender" <c:if test="${user1.gid == 3}">checked</c:if> value="3" style="margin: 0 0.5vw 0 2vw;" type="radio">Other
                                    <input  name="gender" <c:if test="${user1.gid == 4}">checked</c:if> value="4" style="margin: 0 0.5vw 0 2vw;" type="radio">I'd rather not say                                                    
                                    </div>

                                    <!--input Address-->
                                    <div style="margin: 30px 0 0 0;">
                                    <c:if test="${user1.uaddress != null}">
                                        <input name="address" type="text" style="width: 400px;height: 40px;" value="${user1.uaddress}" required>
                                    </c:if>
                                    <c:if test="${user1.uaddress == null}">
                                        <input name="address" type="text" style="width: 400px;height: 40px;"  placeholder="Your Address" value="${user1.uaddress}" required>
                                    </c:if>
                                </div>

                                <!--input Wallet-->
                                <div style="margin: 30px 0 0 0;">
                                    <c:if test="${user1.uwallet != null}">
                                        <input name="wallet" type="text" value="${user1.uwallet}" style="width: 400px;height: 40px;" required>
                                    </c:if>
                                    <c:if test="${user1.uwallet == null}">
                                        <input name="wallet" type="text" placeholder="..." style="width: 400px;height: 40px;" value="${user1.uwallet}" required>
                                    </c:if>    
                                </div>

                                <!--input Role-->
                                <div class="d-flex align-items-center" style="margin: 30px 0 0 0; width: 400px;height: 40px;">
                                    <input  name="role" <c:if test="${user1.rid == 2 || user1.rid == null}">checked</c:if> value="2" style="margin: 0 0.5vw 0 2vw;" type="radio">Customer
                                    <input  name="role" <c:if test="${user1.rid == 3}">checked</c:if> value="3" style="margin: 0 0.5vw 0 2vw;" type="radio">Sale
                                    <input  name="role" <c:if test="${user1.rid == 4}">checked</c:if> value="4" style="margin: 0 0.5vw 0 2vw;" type="radio">Marketing
                                    <input  name="role" <c:if test="${user1.rid == 5}">checked</c:if> value="5" style="margin: 0 0.5vw 0 2vw;" type="radio">Expert                                                    
                                    <input  name="role" <c:if test="${user1.rid == 6}">checked</c:if> value="6" style="margin: 0 0.5vw 0 2vw;" type="radio">Admin                                                   
                                    </div>

                                    <!--input Status-->
                                    <div class="d-flex align-items-center" style="margin: 30px 0 0 0; width: 400px;height: 40px;">
                                        <input  name="status" <c:if test="${user1.ustatus == true || user1.ustatus== null}">checked</c:if> value="true" style="margin: 0 0.5vw 0 2vw;" type="radio">Activate
                                    <input  name="status" <c:if test="${user1.ustatus == false}">checked</c:if>  value="false" style="margin: 0 0.5vw 0 2vw;;" type="radio">Inactivate                                                   
                                    </div>

                                    <!--                                    <div style="margin:10px">
                                                                            <input class="btn btn-primary" style="width: 150px; height: 60px;margin: 1vw 3vw 0 3vw;" type="submit" value="Save change">
                                                                        </div>-->

                                    <div class="d-flex justify-content-between text-center mt-4">

                                    <c:if test="${id!=0}">
                                        <input class="btn btn-primary" type="submit" value="Save Change" name="savechange">
                                    </c:if>
                                    <c:if test="${id==0}">
                                        <input class="btn btn-primary" type="submit" value="Add new" name="savechange">
                                    </c:if>
                                    <input class=" btn btn-primary mr-5" type="button" value="Undo Changes" onclick="window.location = 'userdetail?id=${id}'">
                                </div>
                            </div>
                        </div>

                    </form>

                    <script>
                        function Back() {
                            window.location = "userlist";
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
    <script type="text/javascript">
        const input = document.getElementById("imgload");
        const image = document.getElementById("img1");

        input.addEventListener("change", (e) => {
            if (e.target.files.length) {
                const src = URL.createObjectURL(e.target.files[0]);
                image.src = src;
            }
        });
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
