<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Detail</title>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style type="text/css">
            <%@include file="../css/home.css"%>
            <%@include file="../css/header.css"%>
            #grad1 {
                background: linear-gradient(#e66465, #9198e5);               
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <!--COURSE DETAIL-->
            <section> 
                <div style="margin: 75px 0 0 0;" class="row container-fluid">

                    <div id="grad1" class="col-md-10">
                        <div style="padding: 20px 0 10px 0" class="row container-fluid">
                            <div class="col-md-8">                            
                                <div>
                                    <p style="color: #ffff33;font-weight: bold;">Course Detail:</p>
                                    <div style="padding: 0 0 0 15px" class="d-flex">
                                        <h1 style="font-size:35px;">${requestScope.course.ctitle.toUpperCase()}</h1>
                                    <p id="brief" style="color: #ffff33;margin: 35px 0 0 0"><em>"${requestScope.tagline}"</em></p>
                                </div>
                            </div>

                            <div style="margin: 20px 0 0 0">
                                <div>
                                    <p style="color: white;"><span style="color: #ffff33; font-weight: bold">Lecturer: </span>${requestScope.lecturer.lname}</p>
                                    <p><span style="font-weight: bold; color: #ffff33">Brief Information: </span><em style="color: white">${requestScope.brief}</em></p>
                                </div>
                            </div>

                            <div>
                                <div style="margin: 0 0 10px 0">
                                    <div class="d-flex" style="margin: 0 0 10px 0">
                                        <p style="color: #ffff33;font-weight: bold;margin: 0 10px 0 0">Package Price:</p>
                                        <div>
                                            <c:if test="${requestScope.course.cprice == 0}">
                                                <p class="badge badge-warning">Free</p>
                                            </c:if>
                                            <c:if test="${requestScope.course.cprice > 0}">
                                                <p class="badge badge-warning"><del>${requestScope.course.cprice}$</del></p>
                                                <p class="badge badge-warning">${requestScope.course.cprice * 80/100}$</p>
                                            </c:if>  
                                        </div>
                                    </div>
                                    <div>
                                        <c:if test="${requestScope.status == null}">
                                            <c:if test="${sessionScope.user == null}">
                                                <button onclick="window.location.href = 'login?ReturnUrl=' + window.location.href;" 
                                                        type="button" style="width: 150px; height: 70px; background: linear-gradient(#333333, #dd1818); border-radius: 15px">
                                                    <span style="color: white; font-weight: bold; font-size: 20px">Register</span>
                                                </button>         
                                            </c:if>
                                            <c:if test="${sessionScope.user != null}">
                                                <button type="button" data-toggle="modal" data-target=".bd-example-modal-xl" 
                                                        style="width: 150px; height: 70px; background: linear-gradient(#333333, #dd1818); border-radius: 15px">
                                                    <span style="color: white; font-weight: bold; font-size: 20px">Register</span>
                                                </button>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${requestScope.status == \"submitted\"}">
                                            <button type="button" data-toggle="modal" data-target="#popupStartCourse" 
                                                    style="width: 150px; height: 70px;  background: linear-gradient(#333333, #dd1818); border-radius: 15px">
                                                <span style="color: white; font-weight: bold; font-size: 20px">Start Course</span>
                                            </button>
                                        </c:if>
                                        <c:if test="${requestScope.status == \"registered\"}">
                                            <button onclick="window.location.href = 'section?cid=${course.cid}'" type="button" 
                                                    style="width: 150px; height: 70px;  background: linear-gradient(#333333, #dd1818); border-radius: 15px">
                                                <span style="color: white; font-weight: bold; font-size: 20px">Go To Course</span>
                                            </button>
                                        </c:if>    
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <img class="img-thumbnail" style="width: 100%" src="${requestScope.course.cimg}">
                        </div>
                    </div>
                </div>  
                <div class="col-md-2" style="position: fixed; right: 0; margin: 15px 0 0 0; background: white">
                    <h5 style="margin-top: 10px">Subject Category</h5>
                    <c:forEach items="${requestScope.listSubject}" var="lSub">
                        <a href="courselist?search=&subject=${lSub.sid}&level=0&lecturer=0&price=all&public=0">${lSub.sname}</a><br>
                    </c:forEach>
                </div>           
            </div>

            <div style="margin: 0 0 0 5px">
                <div class="row container-fluid">
                    <div class="col-md-10">
                        <div style="margin: 10px 0">
                            <p id="description" style="font-size: 20px"><a class="badge badge-warning" data-toggle="collapse" href="#coursedescription" aria-expanded="false" aria-controls="collapseExample">
                                    Course Description
                                </a></p>
                            <div class="collapse show">
                                <div class="card card-body">
                                    <p><em>${requestScope.description}</em></p>
                                </div>
                            </div>
                        </div>
                        <div>
                            <p style="font-size: 20px"><a class="badge badge-warning" data-toggle="collapse" href="#suggestcourse" aria-expanded="false" aria-controls="collapseExample">
                                    Suggest Courses In The Same Subject
                                </a></p>
                            <div class="collapse show" id="suggestcourse">
                                <div class="course-content row">
                                    <c:forEach items="${requestScope.suggestlist}" var="list">
                                        <div class="col-md-3 my-3">
                                            <div class="course-detail">
                                                <a style="text-decoration: none" href="coursedetail?cid=${list.cid}">
                                                    <img class="img-fluid"
                                                         src="${list.cimg}"
                                                         alt="image about course">
                                                    <div class="course-title">
                                                        <c:if test="${list.cprice == 0}">
                                                            <span class="my_span">Free</span>
                                                        </c:if>
                                                        <c:if test="${list.cprice != 0}">
                                                            <span class="my_span">${list.cprice} $</span>
                                                        </c:if>
                                                        <div class="div1">Course name: ${list.cname}</div>
                                                        <div class="div2">Course title: ${list.ctitle}</div>
                                                        <h6>
                                                            <c:forEach items="${listSubject}" var="s">
                                                                <c:if test="${s.sid==list.sid}">Subject: ${s.sname}</c:if>
                                                            </c:forEach>
                                                        </h6>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>    
                        </div>                        
                    </div>
                </div>
            </div>
        </section> 


        <!--Course Register-->
        <button hidden id="eidtRegistrationPopup" type="button" data-toggle="modal" data-target=".bd-example-modal-xl"></button>
        <form action="coursedetail" method="post">
            <div class="modal fade bd-example-modal-xl"  tabindex="-1" role="dialog" aria-labelledby="myExtraLargeModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-xl">
                    <div class="modal-content" style="width: 900px; margin-left: -180px; background: lightgray">
                        <div style="margin: 10px">
                            <div id="grad1" style="padding: 10px" class="d-flex justify-content-between">
                                <div class="col-md-8" style="margin: 5px">
                                    <p style="color: #ffff33;font-weight: bold;">Course Register:</p>
                                    <h3 class="text-uppercase">${requestScope.course.cname}</h3>
                                    <p id="price" style="color: white;"><span style="color: #ffff33; font-weight: bold">Lecturer: </span>${requestScope.lecturer.lname}</p>
                                    <p id="enroll"><span style="font-weight: bold; color: #ffff33">Brief Information: </span><em style="color: white">${requestScope.brief}</em></p>
                                </div>
                                <div class="col-md-4">
                                    <img class="img-thumbnail" style="width: 300px" src="${requestScope.course.cimg}">
                                </div>
                            </div>
                            <div style="margin: 10px 0 0 0;">
                                <div class="d-flex justify-content-around">
                                    <div class="d-flex flex-column">
                                        <p class="labels">Package Price:</p>
                                        <c:if test="${requestScope.course.cprice == 0}">
                                            <div class="badge badge-info" style="height: 110px; width: 200px">
                                                <p style="font-weight: bold; font-size: 30px">Free</p>
                                                <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                    <input class="btn-primary" onclick="return false" type="checkbox" checked>Unlimited Access
                                                </div>
                                                <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                    <input class="btn-primary" onclick="return false" type="checkbox" checked>Quiz Course Free
                                                </div>
                                                <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                    <input class="btn-primary" onclick="return false" type="checkbox" checked>Send FeedBack
                                                </div>
                                            </div>
                                            <input hidden class="form-check-input" type="radio" checked name="package_price" value="Free">
                                        </c:if>

                                        <c:if test="${requestScope.course.cprice != 0}">

                                            <div class="d-flex">
                                                <div class="badge" style="height: 110px; width: 220px; margin: 5px 0; background: #dfdfdf">
                                                    <p style="font-weight: bold; font-size: 30px">Sliver<span style="color: #605d5c; opacity: 80%; font-size: 15px"><em>(${requestScope.course.cprice * 0.8}$)</em></span></p>
                                                    <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                        <input class="btn-primary" onclick="return false" type="checkbox" checked>3 months Access
                                                    </div>
                                                    <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                        <input class="btn-primary" onclick="return false" type="checkbox" checked>Quiz Course Free
                                                    </div>
                                                    <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                        <input class="btn-primary" onclick="return false" type="checkbox" checked>Send FeedBack
                                                    </div>
                                                </div>
                                                <input ${myRegistration.pid == 2 ? "checked" : ""} checked style="margin:50px 0 0 -20px" class="form-check-input" type="radio" value="Silver" name="package_price">
                                            </div>


                                            <div class="d-flex">
                                                <div class="badge badge-warning" style="height: 110px; width: 220px; margin: 5px 0">
                                                    <p style="font-weight: bold; font-size: 30px">Gold<span style="color: #605d5c; opacity: 80%; font-size: 15px"><em>(${requestScope.course.cprice * 1.2}$)</em></span></p>
                                                    <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                        <input class="btn-primary" onclick="return false" type="checkbox" checked>6 months Access
                                                    </div>
                                                    <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                        <input class="btn-primary" onclick="return false" type="checkbox" checked>Quiz Course + Test Free
                                                    </div>
                                                    <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                        <input class="btn-primary" onclick="return false" type="checkbox" checked>Send FeedBack
                                                    </div>
                                                </div>
                                                <input ${myRegistration.pid == 3 ? "checked" : ""} style="margin:60px 0 0 -20px" class="form-check-input" type="radio" value="Gold" name="package_price">
                                            </div>

                                            <div class="d-flex">
                                                <div class="badge badge-info" style="height: 130px; width: 220px; margin: 5px 0; background: linear-gradient(#455d82, #a29da1); ">
                                                    <p style="font-weight: bold; font-size: 30px">Diamond<span style="color: #ffa82b; opacity: 80%; font-size: 15px"><em>(${requestScope.course.cprice * 1.6}$)</em></span></p>
                                                    <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                        <input class="btn-primary" onclick="return false" type="checkbox" checked>Unlimited Access
                                                    </div>
                                                    <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                        <input class="btn-primary" onclick="return false" type="checkbox" checked>Quiz Course + Test Free
                                                    </div>
                                                    <div class="d-flex align-items-center" style="height: 20px; margin: 0 0 0 30px">
                                                        <input class="btn-primary" onclick="return false" type="checkbox" checked>Send FeedBack
                                                    </div>
                                                </div>
                                                <input ${myRegistration.pid == 4 ? "checked" : ""} style="margin:65px 0 0 -20px" class="form-check-input" type="radio" value="Diamond" name="package_price">
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="d-flex justify-content-around">
                                        <div>
                                            <div class="d-flex flex-column" style="margin: 0 0 10px 0;">
                                                <label for="name" class="labels">Full name</label>
                                                <input class="form-control" id="name" style="width: 300px" value="${sessionScope.user.ufullname}" readonly type="text">
                                            </div>

                                            <div class="d-flex flex-column" style="margin: 0 0 10px 0;">
                                                <label for="email" class="labels">Email</label>
                                                <input class="form-control" id="email" style="width: 300px" value="${sessionScope.user.uemail}" readonly type="text">
                                            </div>

                                            <div class="d-flex flex-column" style="margin: 0 0 10px 0;">
                                                <label for="phone" class="labels">Phone</label>
                                                <input class="form-control" id="phone" style="width: 300px" value="${sessionScope.user.uphone}" readonly type="text">
                                            </div>

                                            <div style="margin: 0 0 10px 0;">
                                                <label class="labels">Gender</label>
                                                <div style="width: 350px">
                                                    <c:forEach  items="${requestScope.listGender}" var="listGen">
                                                        <c:if test="${sessionScope.user.gid == listGen.gid}">
                                                            <input checked onclick="return false" style="margin: 0 5px" name="ugender" type="radio">${listGen.gname}
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <input name="cid" value="${requestScope.course.cid}" hidden>

                                    <button type="button" style="background: linear-gradient(#333333, #dd1818); border-radius: 5px; color: white" data-toggle="modal" data-target="#popupcf">
                                        Register
                                    </button>
                                    <button class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  


            <!-- Confirm Register Course -->
            <div class="modal fade" id="popupcf" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content" style="margin-top: 200px">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Do you want to register for the course: <span class="text-uppercase" style="color: blue; font-weight: bold">${requestScope.course.cname}</span>?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            <input class="btn btn-primary" type="submit" value="Continue">
                        </div>
                    </div>
                </div>
            </div>       

            <!-- Confirm Start Course -->                
            <div class="modal fade" id="popupStartCourse" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content" style="margin-top: 100px">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p>Do you want to start for the course: <span class="text-uppercase" style="color: blue; font-weight: bold">${requestScope.course.cname}</span>, right now?<br>
                                <br>
                                If you are ready to study this course, COURSERE will automatically deduct money from this account if it is the <span style="color: red; font-weight: bold">Paid</span> course. 
                                Your registration for this course will also change to <span style="color: blue; font-weight: bold">Registered</span> status and you cannot edit or cancel this registration. <span style="color: green; font-weight: bold"><em>(My Registration section)</em></span><br>
                                <br>
                                Make sure you are ready to take this course.<br>
                                <br>
                                Click <span style="color: blue; font-weight: bold">Continue</span> to start this course now.<br>
                                Click <span style="color: red; font-weight: bold">Cancel</span> to cancel the request.<br>
                            </p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                            <button type="button" class="btn btn-primary" onclick="window.location.href = 'section?cid=${course.cid}&status=Registered'">Continue</button>
                        </div>
                    </div>
                </div>
            </div>      
        </form>

        <!-- Register successful -->                    
        <c:if test="${requestScope.successful != null}">
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Notification</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p><h6 style="color: green">You have successfully registered for the course: 
                                <span class="text-uppercase" style="color: blue; font-weight: bold">${requestScope.course.cname}</span></h6>
                            The course is in the <span style="color: red; font-weight: bold">Submitted</span> status. If you want to 
                            <span style="color: red; font-weight: bold">Cancel</span> your registration or change the 
                            <span style="color: blue; font-weight: bold">Package Price</span>, please access the 
                            <span style="color: blue; font-weight: bold">My Registration</span> section.<br/>
                            <br/>
                            <br/>
                            Thanks and Best regards,<br/> 
                            ----------------------------<br/> 
                            COURSERE
                            </p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary"  data-dismiss="modal">Continue</button>
                        </div>
                    </div>
                </div>
            </div>               
        </c:if>          

        <!-- Enough Money -->                    
        <c:if test="${requestScope.enoughMoney != null}">
            <div class="modal fade" id="enoughMoney" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Notification</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p style="color: red"><b>Your wallet does not have enough money!</b><br>
                                <span style="color: blue">Please add more money to your wallet.</span>
                            </p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary"  data-dismiss="modal">Continue</button>
                        </div>
                    </div>
                </div>
            </div>               
        </c:if>    

        <jsp:include page="footer.jsp"></jsp:include>
        </body>
        <script type="text/javascript">
        <c:if test="${requestScope.successful != null}">
            $(document).ready(function () {
                $("#exampleModalCenter").modal('show');
            });
        </c:if>
        <c:if test="${requestScope.enoughMoney != null}">
            $(document).ready(function () {
                $("#enoughMoney").modal('show');
            });
        </c:if>

        <c:if test="${eidtRegistration != null}"> // Yeu cau Edit
            window.onload = function () {
                document.getElementById('eidtRegistrationPopup').click();
            }
        </c:if>

    </script>  
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>    
</html>
