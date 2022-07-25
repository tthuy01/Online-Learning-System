<%-- 
    Document   : quiz_details
    Created on : Jun 26, 2022, 11:53:02 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question Detail</title>
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
            integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
        <!-- JS for jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- CSS for searching -->
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <!-- JS for searching -->
        <script type="text/javascript">
            // .js-example-basic-single declare this class into your select box
            $(document).ready(function () {
                $('.js-example-basic-single').select2();
            });
        </script>
        <style type="text/css">
            <%@include file="../css/header.css" %>
            <%@include file="../css/dashboard.css"%>
        </style>
        <script src="ckeditor/ckeditor.js" ></script>
    </head>
    <body>
        <c:if test="${sessionScope.user.rid==6}">
            <section id="wrapper-dashboard" class="row container-fluid mx-auto px-0" style="height: 1000px">
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
                        <li >
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
                        <li style="background-color: #425164;">
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
            <div id="right" class="col-lg-10 col-md-8 col-12" style="background-color: white;">
                <!--header navbar-->
                <jsp:include page="header_admin.jsp"></jsp:include>
                <section class="ml-5 mt-1">
                    <h4 class="mt-4"><a href="queslist?search=${searching}&subid=${listsub != null? listsub:cid}&pageid=${pageid}&side=${side}">Question List<i class="fa-solid fa-angle-right mx-2"></i></a>Question Detail</h4>
                    <nav class="mt-4">
                        <div class="nav nav-tabs" id="nav-tab" role="tablist">
                            <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
                               aria-controls="nav-home" aria-selected="true">Editing Form</a>
                        </div>
                    </nav>

                    <div class="tab-content mr-4" id="nav-tabContent" style="border: 2px solid gray">
                        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                            <form class="mx-2 my-2" action="quesdetails" method="post">       
                                <c:if test="${Succ != null || Faii != null}"> 
                                    <h6 class="my-3 py-2 pl-2" style="background-color: wheat; ${Succ != null? 'color: green':'color: red'}">${Succ != null? Succ:Faii}</h6> 
                                </c:if>
                                <input hidden id="side" name="side" type="text" value="${side}"/>
                                <input type="hidden" name="typeac" value="update">
                                <input type="hidden" name="queid" value="${quesCur.queid}">
                                <input type="hidden" name="searching" value="${searching}">
                                <input type="hidden" name="cid" value="${listsub != null? listsub:cid}">
                                <input type="hidden" name="pageid" value="${pageid}">
                                <div class="form-group">
                                    <label for="inputAddress">Subject</label><br/>
                                    <select name="listsub" class="form-control js-example-basic-single" style="height: 40px" onchange="this.form.submit()" required>
                                        <option selected value="0">&nbsp;Select Subject</option>
                                        <c:forEach items="${listCourse}" var="lists">
                                            <option value="${lists.cid}" ${listsub != null? listsub == lists.cid ? "selected":"":quesCur.cid == lists.cid? "selected":""}>
                                                &nbsp;${lists.cname}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="inputAddress2">Lesson</label><br/>
                                    <select name="listles" class="form-control js-example-basic-single" style="height: 40px" onchange="this.form.submit()" required>
                                        <option selected value="0">&nbsp;Select Lesson</option>
                                        <c:forEach items="${listSec}" var="lists">
                                            <option value="${lists.sid}" ${listles != null? listles == lists.sid ? "selected":"":quesCur.sid == lists.sid? "selected":""}>
                                                &nbsp;${lists.sname}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="inputAddress3">Quiz</label><br/>
                                    <select name="listq" class="form-control js-example-basic-single" style="height: 40px" required>
                                        <option selected value="0">&nbsp;Select Quiz</option>
                                        <c:forEach items="${listQuiz}" var="lists">
                                            <option value="${lists.qid}" ${listq != null? listq == lists.qid ? "selected":"":quesCur.qid == lists.qid? "selected":""}>
                                                &nbsp;${lists.qname}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="inputAddress4">Content</label>
                                    <input type="text" class="form-control" rows="5" name="cont" value="${cont!=null? cont:content}" placeholder='Type the content' required />
                                </div>
                                <div class="form-group">
                                    <label for="inputAddress5">Answer Result</label>
                                    <input type="text" class="form-control" name="res" value="${res!=null? res:result}" placeholder='Type the result' required>
                                </div>
                                <div class="form-group">
                                    <label for="inputAddress6">Question Explanation</label>
                                    <textarea id="inputDescription2" class="form-control" name="exp" placeholder='Type the explanation' required>${exp!=null? exp:quesCur.que_note}</textarea>
                                </div>
                                <button type="submit" name="sub" value="update" class="btn btn-primary mr-5">Submit</button>
                                <!--<button class="btn btn-primary" type="button" onclick="window.location.href = 'queslist?search=${searching}&pageid=${pageid}&side=${side}'">Undo</button>-->
                                <button class="btn btn-primary" type="button" onclick="window.location.href = 'quesdetails?cid=${listsub != null? listsub:quesCur.cid}&sid=${quesCur.sid}&qid=${quesCur.qid}&queid=${quesCur.queid}&search=${searching}&page=${pageid}&side=${side}'">Undo Change</button>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </section>  
        </c:if>
        <c:if test="${sessionScope.user.rid==5}">
            <jsp:include page="header.jsp"></jsp:include>
            <section class="ml-5" style="margin-top: 110px">
                    <h4 class="mt-4"><a href="queslist?search=${searching}&subid=${listsub != null? listsub:cid}&pageid=${pageid}&side=${side}">Question List<i class="fa-solid fa-angle-right mx-2"></i></a>Question Detail</h4>
                    <nav class="mt-4">
                        <div class="nav nav-tabs" id="nav-tab" role="tablist">
                            <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
                               aria-controls="nav-home" aria-selected="true">Editing Form</a>
                        </div>
                    </nav>

                    <div class="tab-content mr-4" id="nav-tabContent" style="border: 2px solid gray">
                        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                            <form class="mx-2 my-2" action="quesdetails" method="post">       
                                <c:if test="${Succ != null || Faii != null}"> 
                                    <h6 class="my-3 py-2 pl-2" style="background-color: wheat; ${Succ != null? 'color: green':'color: red'}">${Succ != null? Succ:Faii}</h6> 
                                </c:if>
                                <input hidden id="side" name="side" type="text" value="${side}"/>
                                <input type="hidden" name="typeac" value="update">
                                <input type="hidden" name="queid" value="${quesCur.queid}">
                                <input type="hidden" name="searching" value="${searching}">
                                <input type="hidden" name="cid" value="${listsub != null? listsub:cid}">
                                <input type="hidden" name="pageid" value="${pageid}">
                                <div class="form-group">
                                    <label for="inputAddress">Subject</label><br/>
                                    <select name="listsub" class="form-control js-example-basic-single" style="height: 40px" onchange="this.form.submit()" required>
                                        <option selected value="0">&nbsp;Select Subject</option>
                                        <c:forEach items="${listCourse}" var="lists">
                                            <option value="${lists.cid}" ${listsub != null? listsub == lists.cid ? "selected":"":quesCur.cid == lists.cid? "selected":""}>
                                                &nbsp;${lists.cname}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="inputAddress2">Lesson</label><br/>
                                    <select name="listles" class="form-control js-example-basic-single" style="height: 40px" onchange="this.form.submit()" required>
                                        <option selected value="0">&nbsp;Select Lesson</option>
                                        <c:forEach items="${listSec}" var="lists">
                                            <option value="${lists.sid}" ${listles != null? listles == lists.sid ? "selected":"":quesCur.sid == lists.sid? "selected":""}>
                                                &nbsp;${lists.sname}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="inputAddress3">Quiz</label><br/>
                                    <select name="listq" class="form-control js-example-basic-single" style="height: 40px" required>
                                        <option selected value="0">&nbsp;Select Quiz</option>
                                        <c:forEach items="${listQuiz}" var="lists">
                                            <option value="${lists.qid}" ${listq != null? listq == lists.qid ? "selected":"":quesCur.qid == lists.qid? "selected":""}>
                                                &nbsp;${lists.qname}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="inputAddress4">Content</label>
                                    <input type="text" class="form-control" rows="5" name="cont" value="${cont!=null? cont:content}" placeholder='Type the content' required />
                                </div>
                                <div class="form-group">
                                    <label for="inputAddress5">Answer Result</label>
                                    <input type="text" class="form-control" name="res" value="${res!=null? res:result}" placeholder='Type the result' required>
                                </div>
                                <div class="form-group">
                                    <label for="inputAddress6">Question Explanation</label>
                                    <textarea id="inputDescription2" class="form-control" name="exp" placeholder='Type the explanation' required>${exp!=null? exp:quesCur.que_note}</textarea>
                                </div>
                                <button type="submit" name="sub" value="update" class="btn btn-primary mr-5">Submit</button>
                                <!--<button class="btn btn-primary" type="button" onclick="window.location.href = 'queslist?search=${searching}&pageid=${pageid}&side=${side}'">Undo</button>-->
                                <button class="btn btn-primary" type="button" onclick="window.location.href = 'quesdetails?cid=${listsub != null? listsub:quesCur.cid}&sid=${quesCur.sid}&qid=${quesCur.qid}&queid=${quesCur.queid}&search=${searching}&page=${pageid}&side=${side}'">Undo Change</button>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </section>  
        </c:if>               
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
    <script
        src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"
    ></script>
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"
    ></script>
     <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
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
        window.addEventListener('load', function () {
            document.querySelector('input[type="file"]').addEventListener('change', function () {
                if (this.files && this.files[0]) {
                    var img = document.querySelector('#img');
                    img.onload = () => {
                        URL.revokeObjectURL(img.src); // no longer needed, free memory
                    };
                    img.src = URL.createObjectURL(this.files[0]); // set src to blob url
                }
            });
        });
    </script>  
    <script type="text/javascript">
        $(document).ready(function () {
            CKEDITOR.replace('inputDescription1');
        });
        $(document).ready(function () {
            CKEDITOR.replace('inputDescription2');
        });
    </script>
</html>
