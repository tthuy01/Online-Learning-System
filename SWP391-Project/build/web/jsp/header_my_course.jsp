<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style type="text/css">
            <%@include file="../css/header.css"%>
            <c:if test="${requestScope.path == null}">
                <%@include file="../css/account_login.css"%>
            </c:if>
            <c:if test="${requestScope.path eq '/login'}">
                <%@include file="../css/account_login.css"%>
            </c:if>
            <c:if test="${requestScope.path eq '/register'}">
                <%@include file="../css/account_register.css"%>
            </c:if>
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-white py-3 fixed-top" w3-include-html="header.jsp">
            <a class="navbar-brand" href="home">COURSERE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <form class="form-inline my-2 my-lg-0 ml-5" method="get" action="searchmycourse">
                    <input class="form-control mr-sm-2" type="search" name="search" value="${search}" placeholder="Enter your course" aria-label="Search">
                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                            class="fa-solid fa-magnifying-glass"></i> </button>
                </form>
                <ul class="navbar-nav ml-auto">
                    <c:forEach items="${listBlog}" var="lb">
                        <li class="nav-item mr-2 ">
                            <a class="nav-link" href="bloglist?bid=${lb.bid}">${lb.bname}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${sessionScope.user == null}">
                        <li class="nav-item ml-5 mr-4">
                            <a class="nav-link" id="account" style="border: 1px solid black;" href="login">ACCOUNT</a>
                        </li>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">   
                        <li class="nav-item dropdown">
                            <a
                                class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                aria-expanded="false"
                                >
                                <c:if test="${sessionScope.user.uimg != null}">
                                    <img src="images/avatar/${sessionScope.user.uimg}" style="background-color: #3BAFDA; width: 37px; height: 37px; border-radius: 50%;">
                                </c:if>
                                <c:if test="${sessionScope.user.uimg == null}">
                                    <img id="img1" src="images/avatar/default.jpg" alt="avatar user" style="height: 37px; width: 37px; border-radius: 50%;">
                                </c:if>
                                <span class="pl-1">${sessionScope.user.ufullname}</span>
                            </a>
                            <div class="dropdown-menu mt-0" aria-labelledby="navbarDropdown">
                                <c:if test="${sessionScope.user.rid == 6}">
                                    <a class="dropdown-item" href="dashboard" title="My Dashboard">Dashboard</a>
                                    <a class="dropdown-item" href="settinglist" title="Settings">Settings</a>
                                </c:if>
                                <c:if test="${sessionScope.user.rid == 5}">
                                    <a class="dropdown-item" href="subjectlist" title="My Subject List">Subject List</a>
                                    <a class="dropdown-item" href="quizlist" title="My Quiz List">Quiz List</a>
                                    <a class="dropdown-item" href="queslist" title="My Question List">Question List</a>
                                </c:if>
                                <c:if test="${sessionScope.user.rid == 4}">
                                    <a class="dropdown-item" href="postlist" title="My Post List">Post List</a>
                                </c:if>
                                <c:if test="${sessionScope.user.rid == 3}">
                                    <a class="dropdown-item" href="registrationlist" title="My Registration List">Registration List</a>
                                </c:if>
                                <c:if test="${sessionScope.user.rid == 2}">
                                    <a class="dropdown-item" href="mycourse" title="My Course">My Course</a>
                                    <a class="dropdown-item" href="myregistration" title="My Registration">My Registration</a>
                                </c:if>
                                <div class="dropdown-divider"></div> 
                                <a class="dropdown-item" href="changepassword" title="Change Password">Change Password</a>
                                <a class="dropdown-item" href="logout" title="Logout">Logout</a>
                            </div>
                        </li>
                    </c:if> 
                </ul>
                <!-- <li class="nav-item dropdown ml-5 mr-4">
                    <a class="nav-link dropdown-toggle" style="border: 1px solid black;" href="#" id="navbarDropdown"
                        role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        ACCOUNT
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">DASHBOARD</a>
                        <a class="dropdown-item" href="#">PROFILE</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">SOMETHING</a>
                    </div>
                </li> -->
                </ul>
            </div>
        </nav>

        <!--View Profile-->            
        <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="row">
                        <div class="col-md-4 gradient-custom text-center text-white"
                             style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                            <c:if test="${sessionScope.user.uimg == null}">
                                <img src="https://i.pinimg.com/564x/ec/31/11/ec3111d183cceb285b3431c931c11e96.jpg"
                                     alt="Avatar" class="img-fluid my-5" style="width: 80px;border-radius: 95%" />
                            </c:if>
                            <c:if test="${sessionScope.user.uimg != null}">
                                <img src="images/avatar/${sessionScope.user.uimg}"
                                     alt="Avatar" class="img-fluid my-5" style="width: 80px;border-radius: 95%" />
                            </c:if>
                            <h5 class="text-uppercase">${sessionScope.user.ufullname}</h5>
                            <div>
                                <button style="height: 30px; background: none; border: none" id="myModalProfileBt" data-toggle="modal" data-target=".profile-setting-button">
                                    <i class="far fa-edit"></i>
                                </button>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <button id="closeView" type="button" class="close mr-3 mt-2" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <div class="card-body p-4">
                                <h6>Information</h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6>Email</h6>
                                        <p class="text-muted">${sessionScope.user.uemail}</p>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6>Phone</h6>
                                        <c:if test="${sessionScope.user.uphone == null || sessionScope.user.uphone == ''}">
                                            <p class="text-muted">None</p>
                                        </c:if>
                                        <c:if test="${sessionScope.user.uphone != null && sessionScope.user.uphone != ''}">
                                            <p class="text-muted">${sessionScope.user.uphone}</p>
                                        </c:if>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6>Gender</h6>
                                        <c:if test="${sessionScope.user.gid == 1}">
                                            <p class="text-muted">Male</p>
                                        </c:if>
                                        <c:if test="${sessionScope.user.gid == 2}">
                                            <p class="text-muted">Female</p>
                                        </c:if>
                                        <c:if test="${sessionScope.user.gid == 3}">
                                            <p class="text-muted">Other</p>
                                        </c:if>
                                        <c:if test="${sessionScope.user.gid == 4}">
                                            <p class="text-muted">I'd rather not say</p>
                                        </c:if>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6>Address</h6>
                                        <c:if test="${sessionScope.user.uaddress == null || sessionScope.user.uaddress == ''}">
                                            <p class="text-muted">None</p>
                                        </c:if>
                                        <c:if test="${sessionScope.user.uaddress != null && sessionScope.user.uaddress != ''}">
                                            <p class="text-muted">${sessionScope.user.uaddress}</p>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>                    

        <!-- Profile Settings -->
        <div class="modal fade profile-setting-button" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content" style="width: 900px; margin-left: -20px;">
                    <form action="userprofile" enctype="multipart/form-data" method="post" id="formProfile">

                        <div class="container rounded bg-white">
                            <div class="row">
                                <div class="col-md-4 border-right">
                                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                                        <c:if test="${sessionScope.user.uimg == null}">
                                            <img id="userimg" class="rounded-circle mt-5" width="150px"
                                                 src="https://i.pinimg.com/564x/ec/31/11/ec3111d183cceb285b3431c931c11e96.jpg">
                                        </c:if>
                                        <c:if test="${sessionScope.user.uimg != null}">
                                            <img id="userimg" class="rounded-circle mt-5" width="150px"
                                                 src="images/avatar/${sessionScope.user.uimg}">
                                        </c:if>    
                                        <label for="uimg"><i class="fas fa-camera-retro"></i></label>
                                        <input id="uimg" onchange="preview_image_uimg(event)" type="file" name="uimg" accept="image/jpeg, image/png, image/jpg" style="width: 0">
                                        <span class="font-weight-bold">${sessionScope.user.ufullname}</span>
                                        <span class="text-black-50">${sessionScope.user.uemail}</span><span> </span></div>

                                </div>
                                <div class="col-md-8">
                                    <button type="button" class="close mr-2 mt-2" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    <div class="p-3 py-5">
                                        <div class="d-flex justify-content-between align-items-center mb-2">
                                            <h4 class="text-right">Profile Settings</h4>
                                        </div>
                                        <c:if test="${alertUserProfile != null}">
                                            <p class="mb-3" style="color: green"><strong>*${alertUserProfile}</strong></p>
                                        </c:if>
                                        <c:if test="${error != null}">
                                            <p class="mb-3" style="color: red"><strong>*${errorProfile}</strong></p>
                                        </c:if>
                                        <div class="row mt-3">
                                            <div class="col-md-12"><label class="labels">Full Name</label>
                                                <input type="text" name="uname" class="form-control" value="${sessionScope.user.ufullname}">
                                            </div>
                                            <div class="col-md-12"><label class="labels">Mobile Number</label>
                                                <c:if test="${sessionScope.user.uphone == null || sessionScope.user.uphone == ''}">
                                                    <input type="text" name="uphone" class="form-control" placeholder="Enter...">
                                                </c:if>
                                                <c:if test="${sessionScope.user.uphone != null && sessionScope.user.uphone != ''}">
                                                    <input type="text" name="uphone" class="form-control" value="${sessionScope.user.uphone}">
                                                </c:if>
                                            </div>
                                            <div class="col-md-12"><label class="labels">Email<span style="color: red; font-size: 11px"><em> (Note: Your Email cannot edit) </em></span></label>
                                                <input type="text" name="uemail" class="form-control" readonly value="${sessionScope.user.uemail}">
                                            </div>
                                            <div class="col-md-12"><label class="labels">Date Of Birth</label>
                                                <c:if test="${sessionScope.user.udob != null}">
                                                    <input class="form-control" name="udob" type="date" value="${sessionScope.user.udob}">
                                                </c:if>
                                                <c:if test="${sessionScope.user.udob == null}">
                                                    <input class="form-control" name="udob" type="date"  placeholder="dd-mm-yyyy" pattern="(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])-\d{4}"> 
                                                </c:if>
                                            </div>
                                            <div class="col-md-12"><label class="labels">Address</label>
                                                <c:if test="${sessionScope.user.uaddress == null || sessionScope.user.uaddress == ''}">
                                                    <input type="text" class="form-control" name="uaddress" placeholder="Enter...">
                                                </c:if>
                                                <c:if test="${sessionScope.user.uaddress != null && sessionScope.user.uaddress != ''}">
                                                    <input type="text" class="form-control" name="uaddress" value="${sessionScope.user.uaddress}">
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="row mt-3">
                                            <div class="col-md-12"><label class="labels">Gender</label></div>
                                            <div class="col-md-2">
                                                <input  name="genId" <c:if test="${sessionScope.user.gid == 1}">checked</c:if> value="1" type="radio">
                                                    <label class="labels">Male</label>
                                                </div>
                                                <div class="col-md-3">
                                                    <input  name="genId" <c:if test="${sessionScope.user.gid == 2}">checked</c:if>  value="2" type="radio">
                                                    <label class="labels">Female</label>
                                                </div>
                                                <div class="col-md-3">
                                                    <input  name="genId" <c:if test="${sessionScope.user.gid == 3}">checked</c:if> value="3" type="radio">
                                                    <label class="labels">Other</label>
                                                </div>
                                                <div class="col-md-4">
                                                    <input  name="genId" <c:if test="${sessionScope.user.gid == 4}">checked</c:if> value="4"type="radio">
                                                    <label class="labels">I'd rather not say</label>
                                                </div>
                                            </div>        
                                            <div class="row mt-3">        
                                                <div class="col-md-12"><label class="labels">Wallet</label>
                                                <c:if test="${sessionScope.user.uwallet == null || sessionScope.user.uwallet == ''}">
                                                    <input type="text" name="uwallet" class="form-control" readonly placeholder="0$" value="0">
                                                </c:if>
                                                <c:if test="${sessionScope.user.uwallet != null && sessionScope.user.uwallet != ''}">
                                                    <input type="text" name="uwallet" class="form-control" readonly value="${sessionScope.user.uwallet == 0.0 ? 0 : sessionScope.user.uwallet}$">
                                                </c:if>    
                                            </div>
                                        </div>
                                        <div class="mt-4 text-center d-flex justify-content-center">

                                            <input hidden type="text" id="url" name="url" value="">
                                            <button class="btn btn-primary profile-button" onclick="submit()" type="button">Save Profile</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>                                           
                                                           
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

    <c:if test="${requestScope.path == null}">
        <script type="text/javascript">
            <%@include file="../js/account_login.js"%>
        </script>
    </c:if>
    <c:if test="${requestScope.path eq '/login'}">
        <script type="text/javascript">
            <%@include file="../js/account_login.js"%>
        </script>
    </c:if>
    <c:if test="${requestScope.path eq '/register'}">
        <script type="text/javascript">
            <%@include file="../js/account_register.js"%>
        </script>
    </c:if>
</html>

