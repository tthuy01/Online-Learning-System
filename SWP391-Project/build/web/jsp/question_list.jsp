<%-- 
    Document   : quiz_list
    Created on : Jun 23, 2022, 9:50:48 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" contentType="text/html; charset=EUC-JP" >
        <meta property="og:title" content="Mazii - Japanese dictionary , Japanese English dictionary" class="site-title" />
        <meta property="og:image" content="assets/imgs/logo/mazii_feature_share.png" />
        <meta property="og:url" content="//mazii.net" id="site-url" />
        <meta property="og:site_name" content="Mazii - Japanese dictionary , Japanese English dictionary" class="site-name" />
        <meta property="og:description" content="Mazii is japanese dictionary free and the best Japanese to English dictionary. You can translate japanese to english free, japanese dictionary search with voice, handwriting and pictures, learn japanese kanji and practice JLPT test." class="site-desc" />
        <meta property="fb:app_id" content="1544160779141926" />
        <meta property="og:type" content="website" />
        <title>Question List</title>
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
        <style type="text/css">
            <%@include file="../css/header.css" %>
            <%@include file="../css/dashboard.css"%>
            /*fix*/
            <%@include file="../css/quiz_list.css" %>
        </style>
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
            <div id="right" class="col-lg-10 col-md-8 col-12">
                <!--header navbar-->
                <jsp:include page="header_admin.jsp"></jsp:include>
                <section class="ml-5 mt-1" style="margin-bottom: ${listQues.size() < 5? '100px':'100px'}">
                    <h4 class="mt-4">Question List</h4>
                    <form class="d-flex pt-4" action="queslist">
                        <input hidden id="side" name="side" value="${side}"/>
                        <div class="w-25">
                            <div class="d-flex w-75">
                                <input class="form-control" type="search" name="search" value="${searching}" placeholder="Type the question content" aria-label="Search">
                                <button class="btn btn-outline-primary" type="submit" title="Click to search"><i class="fa-solid fa-magnifying-glass"></i></button>
                            </div>
                            <h5 class="mt-4"><img class="img-fil" src="https://png.pngtree.com/png-vector/20191001/ourlarge/pngtree-filter-icon-isolated-on-abstract-background-png-image_1769008.jpg" >Filter by:</h5>
                            <div class="mt-2">
                                <h6 style="color: blue">Subject</h6>
                                <input hidden name="oldsub" value="${subid}" />
                                <c:forEach var="i" begin="0" end="${listCourse.size() - 1}">
                                    <c:if test="${listCourse.get(i).isCstatus() eq 'true'}">
                                        <input title="Click to filter by subject" type="radio" ${subid != null&&subid==listCourse.get(i).getCid()? "checked":""} onclick="this.form.submit()" name="subid" value="${listCourse.get(i).getCid()}" >&nbsp;&nbsp;${listCourse.get(i).getCname()}<br/>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="mt-2">
                                <h6 style="color: blue">Lesson</h6>
                                <c:if test="${listSec.size() <= 0}">Not found lessons!</c:if>
                                <c:if test="${listSec.size() > 0}">
                                    <c:forEach var="i" begin="0" end="${listSec.size() - 1}">
                                        <input title="Click to filter by lesson" type="checkbox" ${secid[i]? "checked":""} onclick="this.form.submit()" name="secid" value="${listSec.get(i).getSid()}" >&nbsp;&nbsp;${listSec.get(i).getSname()}<br/>
                                    </c:forEach>
                                </c:if>
                            </div>
                            <div class="mt-2"><h6 style="color: blue">Question Status</h6>
                                <input title="Click to filter by status" type="checkbox" name="status" value="0"
                                       ${qsta[0]?"checked" : ""} onclick="this.form.submit()">&nbsp;&nbsp;Not Yet<br/> 
                                <input title="Click to filter by status" type="checkbox" name="status" value="1"
                                       ${qsta[1]?"checked" : ""} onclick="this.form.submit()">&nbsp;&nbsp;Done
                            </div>
                            <c:if test="${Clear!=null}">
                                <button class="btn btn-primary mt-3" type="button" onclick="window.location.href = 'queslist'" title="Click to clear filter.">Clear Filter</button></c:if>
                            </div>
                            <div class="w-75 mr-5">
                            <c:if test="${listQues.size() <= 0}">
                                <div class="mb-2" style="text-align: right;">
                                    <button class="btn btn-primary" type="button" title="Click to import new questions." data-toggle="modal" data-target="#exampleModal">+ Import New</button>
                                </div>
                                <h5 class="notice-list" style="margin: 0 0 0 80px;">
                                    <span style="color: #e66465; font-weight: bold">Not found</span> the list questions of subject that you want.&nbsp;Please <span style="color: green; font-weight: bold">re-do.</span>
                                    <span class="list-em" style="margin: 25px 0 0 237px;"><i>The list is empty.</i></span>
                                </h5>
                            </c:if>
                            <c:if test="${listQues.size() > 0}">
                                <div class="d-flex">
                                    <div class="pt-3 w-50">
                                        <c:if test="${searching != null && searching.length() > 0}">
                                            <h5>Result of searching '${searching}' <span style="color: gray">(${listQues.size()} of total ${total} ${total > 1? "questions":"question"})</span></h5>
                                        </c:if>
                                    </div>
                                    <div class="mb-2 w-50" style="text-align: right;">
                                        <button class="btn btn-primary" type="button" title="Click to import new questions." data-toggle="modal" data-target="#exampleModal">+ Import New</button>
                                    </div>
                                </div>
                                <c:if test="${err != null}"> <h5 class="mb-1 py-2 pl-2" style="background-color: wheat; color: red">${err}</h5> </c:if>
                                    <table class="table table-hover">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col">Id</th>
                                                <th scope="col">Content</th>
                                                <th scope="col">Subject</th>
                                                <th scope="col">Lesson</th>
                                                <th scope="col">Quiz</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listQues}" var="list">
                                            <tr>
                                                <td>
                                                    ${list.queid%10==0?10:list.queid%10}
                                                </td>
                                                <td>
                                                    <c:set var="content" value="${fn:split(list.que_content,'|')}"/>
                                                    ${content[0]}<br/>
                                                    a.${content[1]};
                                                    b.${content[2]};
                                                    c.${content[3]};
                                                    d.${content[4]};
                                                </td>
                                                <td>${list.cname}</td>
                                                <td>${list.sname}</td>
                                                <td>${list.qname}</td>
                                                <c:if test="${list.que_status eq 'false'}">
                                                    <td style="color: red; font-weight: bold">Not Yet</td>   
                                                </c:if>
                                                <c:if test="${list.que_status eq 'true'}">
                                                    <td style="color: blue; font-weight: bold">Done</td>   
                                                </c:if>
                                                <td>
                                                    <a href="quesdetails?cid=${list.cid}&sid=${list.sid}&qid=${list.qid}&queid=${list.queid}&search=${searching}&page=${page}&side=${side}" title="Click to edit this question."><i class="fa-solid fa-pen-to-square"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            <c:if test="${total >= 1}">
                                <nav class="mt-5" aria-label="Page navigation example">
                                    <ul class="pagination justify-content-center">
                                        <c:if test="${page > 1}">
                                            <li class="page-item">
                                                <button class="page-link" type="submit" name="pageid" value="${page-1}">Previous</button>
                                            </li>
                                        </c:if>
                                        <c:forEach begin="1" end="${end}" var="z">
                                            <li class="page-item ${z==page? "active":""}"><button class="page-link" type="submit" name="pageid" value="${z}">${z}</button></li>      
                                            </c:forEach>
                                            <c:if test="${page < end}">
                                            <li class="page-item">
                                                <button class="page-link" type="submit" name="pageid" value="${page+1}">Next</button>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </c:if>
                        </div>
                    </form>
                </section>                            
            </div>
        </section>
        </c:if>
        <c:if test="${sessionScope.user.rid==5}">
            <jsp:include page="header.jsp"></jsp:include>
                <section class="ml-5" style="margin-bottom: ${listQues.size() < 5? '100px':'100px'}; margin-top: 110px">
                    <h4 class="mt-4">Question List</h4>
                    <form class="d-flex pt-4" action="queslist">
                        <input hidden id="side" name="side" value="${side}"/>
                        <div class="w-25">
                            <div class="d-flex w-75">
                                <input class="form-control" type="search" name="search" value="${searching}" placeholder="Type the question content" aria-label="Search">
                                <button class="btn btn-outline-primary" type="submit" title="Click to search"><i class="fa-solid fa-magnifying-glass"></i></button>
                            </div>
                            <h5 class="mt-4"><img class="img-fil" src="https://png.pngtree.com/png-vector/20191001/ourlarge/pngtree-filter-icon-isolated-on-abstract-background-png-image_1769008.jpg" >Filter by:</h5>
                            <div class="mt-2">
                                <h6 style="color: blue">Subject</h6>
                                <input hidden name="oldsub" value="${subid}" />
                                <c:forEach var="i" begin="0" end="${listCourse.size() - 1}">
                                    <c:if test="${listCourse.get(i).isCstatus() eq 'true'}">
                                        <input title="Click to filter by subject" type="radio" ${subid != null&&subid==listCourse.get(i).getCid()? "checked":""} onclick="this.form.submit()" name="subid" value="${listCourse.get(i).getCid()}" >&nbsp;&nbsp;${listCourse.get(i).getCname()}<br/>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <div class="mt-2">
                                <h6 style="color: blue">Lesson</h6>
                                <c:if test="${listSec.size() <= 0}">Not found lessons!</c:if>
                                <c:if test="${listSec.size() > 0}">
                                    <c:forEach var="i" begin="0" end="${listSec.size() - 1}">
                                        <input title="Click to filter by lesson" type="checkbox" ${secid[i]? "checked":""} onclick="this.form.submit()" name="secid" value="${listSec.get(i).getSid()}" >&nbsp;&nbsp;${listSec.get(i).getSname()}<br/>
                                    </c:forEach>
                                </c:if>
                            </div>
                            <div class="mt-2"><h6 style="color: blue">Question Status</h6>
                                <input title="Click to filter by status" type="checkbox" name="status" value="0"
                                       ${qsta[0]?"checked" : ""} onclick="this.form.submit()">&nbsp;&nbsp;Not Yet<br/> 
                                <input title="Click to filter by status" type="checkbox" name="status" value="1"
                                       ${qsta[1]?"checked" : ""} onclick="this.form.submit()">&nbsp;&nbsp;Done
                            </div>
                            <c:if test="${Clear!=null}">
                                <button class="btn btn-primary mt-3" type="button" onclick="window.location.href = 'queslist'" title="Click to clear filter.">Clear Filter</button></c:if>
                            </div>
                            <div class="w-75 mr-5">
                            <c:if test="${listQues.size() <= 0}">
                                <div class="mb-2" style="text-align: right;">
                                    <button class="btn btn-primary" type="button" title="Click to import new questions." data-toggle="modal" data-target="#exampleModal">+ Import New</button>
                                </div>
                                <h5 class="notice-list" style="margin: 0 0 0 80px;">
                                    <span style="color: #e66465; font-weight: bold">Not found</span> the list questions of subject that you want.&nbsp;Please <span style="color: green; font-weight: bold">re-do.</span>
                                    <span class="list-em" style="margin: 25px 0 0 237px;"><i>The list is empty.</i></span>
                                </h5>
                            </c:if>
                            <c:if test="${listQues.size() > 0}">
                                <div class="d-flex">
                                    <div class="pt-3 w-50">
                                        <c:if test="${searching != null && searching.length() > 0}">
                                            <h5>Result of searching '${searching}' <span style="color: gray">(${listQues.size()} of total ${total} ${total > 1? "questions":"question"})</span></h5>
                                        </c:if>
                                    </div>
                                    <div class="mb-2 w-50" style="text-align: right;">
                                        <button class="btn btn-primary" type="button" title="Click to import new questions." data-toggle="modal" data-target="#exampleModal">+ Import New</button>
                                    </div>
                                </div>
                                <c:if test="${err != null}"> <h5 class="mb-1 py-2 pl-2" style="background-color: wheat; color: red">${err}</h5> </c:if>
                                    <table class="table table-hover">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col">Id</th>
                                                <th scope="col">Content</th>
                                                <th scope="col">Subject</th>
                                                <th scope="col">Lesson</th>
                                                <th scope="col">Quiz</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${listQues}" var="list">
                                            <tr>
                                                <td>
                                                    ${list.queid%10==0?10:list.queid%10}
                                                </td>
                                                <td>
                                                    <c:set var="content" value="${fn:split(list.que_content,'|')}"/>
                                                    ${content[0]}<br/>
                                                    a.${content[1]};
                                                    b.${content[2]};
                                                    c.${content[3]};
                                                    d.${content[4]};
                                                </td>
                                                <td>${list.cname}</td>
                                                <td>${list.sname}</td>
                                                <td>${list.qname}</td>
                                                <c:if test="${list.que_status eq 'false'}">
                                                    <td style="color: red; font-weight: bold">Not Yet</td>   
                                                </c:if>
                                                <c:if test="${list.que_status eq 'true'}">
                                                    <td style="color: blue; font-weight: bold">Done</td>   
                                                </c:if>
                                                <td>
                                                    <a href="quesdetails?cid=${list.cid}&sid=${list.sid}&qid=${list.qid}&queid=${list.queid}&search=${searching}&page=${page}&side=${side}" title="Click to edit this question."><i class="fa-solid fa-pen-to-square"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            <c:if test="${total >= 1}">
                                <nav class="mt-5" aria-label="Page navigation example">
                                    <ul class="pagination justify-content-center">
                                        <c:if test="${page > 1}">
                                            <li class="page-item">
                                                <button class="page-link" type="submit" name="pageid" value="${page-1}">Previous</button>
                                            </li>
                                        </c:if>
                                        <c:forEach begin="1" end="${end}" var="z">
                                            <li class="page-item ${z==page? "active":""}"><button class="page-link" type="submit" name="pageid" value="${z}">${z}</button></li>      
                                            </c:forEach>
                                            <c:if test="${page < end}">
                                            <li class="page-item">
                                                <button class="page-link" type="submit" name="pageid" value="${page+1}">Next</button>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </c:if>
                        </div>
                    </form>
                </section>
        </c:if>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Question Import</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="document.getElementById('file').value = ''">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body py-5">
                        <p style="text-align: center;margin-bottom: 20px"><a href="./files/Template_Import_Question.xlsx" download> Click to Download Template File</a></p>
                        <form style="text-align: center;padding-left: 90px" id="fileform" method="POST" action="./import" enctype="multipart/form-data" >
                            <input hidden name="cid" value="${subid}" />
                            <div class="ml-5 mb-3" style="text-align: left">
                                <label for="inputAddress">Quiz</label>
                                <select name="qid" required style="width: 50%"> 
                                    <c:forEach items="${listQuiz}" var="lists">
                                        <option value="${lists.qid}">
                                            ${lists.qname}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="file" name="file" id="file"/>
                        </form>
                        <br>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="document.getElementById('file').value = ''">Close</button>
                        <button type="button" onclick="document.getElementById('fileform').submit()" class="btn btn-primary">Upload</button>
                    </div>
                </div>
            </div>
        </div>                    
    </body>
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
    <script src="https://kit.fontawesome.com/0d37c672ce.js" crossorigin="anonymous"></script>
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
</html>
