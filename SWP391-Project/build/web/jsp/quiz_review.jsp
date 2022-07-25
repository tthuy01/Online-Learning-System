<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Quiz Review</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
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
            <%@include file="../css/quiz_review.css" %>
        </style>
        <style type="text/css">
            <%@include file="../css/header.css" %>
        </style>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-white py-3 fixed-top" w3-include-html="header.jsp">
            <a class="navbar-brand" href="home">&nbsp;&nbsp;&nbsp;COURSERE</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <c:forEach items="${listBlog}" var="lb">
                        <li class="nav-item mr-2 ">
                            <a style="margin: 3px 0 0 0" class="nav-link" href="bloglist?bid=${lb.bid}">${lb.bname}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${sessionScope.user == null}">
                        <li class="nav-item ml-5 mr-4">
                            <a class="nav-link" style="border: 1px solid black;" href="login" >ACCOUNT</a>
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
                                <a class="dropdown-item" href="mycourse" title="My Course">My Course</a>
                                <a class="dropdown-item" href="myregistration" title="My Registration">My Registration</a>
                                <a class="dropdown-item" data-toggle="modal" data-target=".bd-example-modal-lg" title="Profile">Profile</a>
                                <div class="dropdown-divider"></div> 
                                <a class="dropdown-item" href="changepassword" title="Change Password">Change Password</a>
                                <a class="dropdown-item" href="logout" title="Logout">Logout</a>
                            </div>
                        </li>
                    </c:if> 
                </ul>
            </div>
        </nav>
        <!-- <section>
          <header class="row1">
            <div class="col-12 pt-3 pb-3">
              <div class="card">
                <div class="card-body">
                  <div class="d-flex">
                    <div class="mr-auto">
                      <div class="page-context-header">
                        <div class="page-header-headings">
                          <h1>Course Name - Code</h1>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="d-flex flex-wrap">
                    <div>
                      <nav role="navigation" aria-label="Breadcrumb trail">
                        <ol class="breadcrumb">
                          <li class="breadcrumb-item">
                            <a href="https://cmshn.fpt.edu.vn/">Home</a>
                          </li>
                          <li class="breadcrumb-item">
                            <a href="https://cmshn.fpt.edu.vn/">My courses</a>
                          </li>
                          <li class="breadcrumb-item">
                            <a
                              href="https://cmshn.fpt.edu.vn/course/view.php?id=3928"
                              title="Discrete Mathematics - MAD101"
                              >Course Name</a
                            >
                          </li>
                          <li class="breadcrumb-item">
                            <a
                              href="https://cmshn.fpt.edu.vn/mod/quiz/view.php?id=130230"
                              aria-current="page"
                              title="Quiz"
                              >Quiz</a
                            >
                          </li>
                        </ol>
                      </nav>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </header>
        </section> -->
        <section id="quiz-title" class="container-fluid mb-3 row mx-auto" style="margin: 95px 0">
            <div class="quiz-title-detail col-12 py-3">
                <!--fix-->
                <h4 class="mb-4">Quiz Review</h4>
                <h5>${quimix.cname} - ${quimix.cid}</h5>
                <div class="quiz-link">
                    <a href="home">Home<i class="fa-solid fa-angle-right mx-2"></i></a>
                    <a href="mycourse">My courses<i class="fa-solid fa-angle-right mx-2"></i></a>
                    <a href="section?cid=${quimix.cid}">${quimix.cname}<i class="fa-solid fa-angle-right mx-2"></i></a>
                    <!--fix-->
                    <a>${quimix.qname}</a>
                    <!--<a href="quizresult?qrid=${quimix.qrid}">${quimix.qname}</a>-->
                </div>
            </div>
        </section>
        <section id="quiz-content" class="container-fluid row mx-auto">
            <div class="quiz-question col-lg-9 col-12">
                <table class="generaltable quizreviewsummary">
                    <tbody>
                        <tr>
                            <th class="cell" scope="row">Start on</th>
                            <td class="cell">${start}</td>
                        </tr>
                        <tr>
                            <th class="cell" scope="row">Status</th>
                            <!--fix-->
                                <c:if test="${quimix.qgrade >= 5}">
                                <td class="cell" style="color: green"><b>Passed</b></td>
                            </c:if>
                            <c:if test="${quimix.qgrade < 5}">
                                <td class="cell" style="color: red"><b>Not pass</b></td>
                            </c:if>
                        </tr>
                        <tr>
                            <th class="cell" scope="row">Finished at</th>
                            <td class="cell">${end}</td>
                        </tr>
                        <tr>
                            <th class="cell" scope="row">Time taken</th>
                            <td class="cell">${time}</td>
                        </tr>
                        <tr>
                            <th class="cell" scope="row">Marks</th>
                            <td class="cell">${mark}/${numques}</td>
                        </tr>
                        <tr>
                            <th class="cell" scope="row">Grade</th>
                            <td class="cell"><b>${grade}</b> out of 10.00 (<b>${percent}</b>%)</td>
                        </tr>
                    </tbody>
                </table>
                <div class="row quiz-question-detail">
                    <div
                        class="col-2 question-num my-4 pt-2 pl-5"
                        style="border-top: 2px solid black;"
                        >
                        <!--fix-->
                        <!--quesmix.quesid>10? 10+quesmix.quesid%10:-->
                        <h4 class="my-1">Question ${quesmix.quesid%10==0?10:quesmix.quesid%10}</h4>
                        <c:if test="${quesmix.quesstatus eq 'false'}">
                            <h6 class="my-3" style="color: red">Not answered</h6>
                        </c:if>
                        <c:if test="${quesmix.quesstatus eq 'true'}">
                            <h6 class="my-3" style="color: green">Completed</h6>
                        </c:if>
                        <c:if test="${quesmix.quesstatus eq 'false'}">
                            <h6 class="my-3" style="color: gray">Marked out of 1.00</h6>
                        </c:if>    
                        <c:if test="${quesmix.quesstatus eq 'true'}">
                            <c:if test="${quesmix.quesanswer == quesmix.quesresult}">
                                <h6 class="my-3" style="color: green">Mark 1.00 of 1.00</h6>
                            </c:if>
                            <c:if test="${quesmix.quesanswer != quesmix.quesresult}">
                                <h6 class="my-3" style="color: red">Mark 0.00 of 1.00</h6>
                            </c:if>
                        </c:if> 
                        <a style="cursor: no-drop;
                           <c:if test="${quesmix.quesflag eq 'false'}">
                               color: gray;
                           </c:if>
                           <c:if test="${quesmix.quesflag eq 'true'}">
                               color: red;
                           </c:if>" >
                            <c:if test="${quesmix.quesflag eq 'false'}">
                                <h6 title="This question is not flagged">
                                    <i class="fa-solid fa-flag"></i>
                                    Unflagged
                                    <p class="my-2">This question that you have passed or not been questionable and confusing.</p>
                                </h6>
                            </c:if>
                            <c:if test="${quesmix.quesflag eq 'true'}">
                                <h6 title="This question is flagged">
                                    <i class="fa-solid fa-flag"></i>
                                    Flagged
                                    <p class="my-2">This question that you haven't passed or been questionable and confusing.</p>
                                </h6>
                            </c:if>
                        </a>
                    </div>
                    <div class="col-10 question-wrapper">
                        <c:set var="content" value="${fn:split(quesmix.quescontext,'|')}"/>
                        <div class="question-content mt-4 pt-3 pb-4">
                            <h5>
                                ${content[0]}
                            </h5>
                        </div>
                        <div class="question-answer pt-4">
                            <h6>Select one:</h6>
                            <div style="${quesmix.quesanswer==quesmix.quesresult && quesmix.quesresult==1?"color:green; font-weight:bold;":quesmix.quesanswer==1? "font-weight:bold;":quesmix.quesresult==1? "color:green; font-weight:bold;":""}">
                                <input type="radio" ${quesmix.quesanswer==1? "checked":""} disabled 
                                       /> a.${content[1]} <br />
                            </div>
                            <div style="${quesmix.quesanswer==quesmix.quesresult && quesmix.quesresult==2?"color:green; font-weight:bold;":quesmix.quesanswer==2? "font-weight:bold;":quesmix.quesresult==2? "color:green; font-weight:bold;":""}">
                                <input type="radio" ${quesmix.quesanswer==2? "checked":""} disabled 
                                       /> b.${content[2]} <br />
                            </div>
                            <div style="${quesmix.quesanswer==quesmix.quesresult && quesmix.quesresult==3?"color:green; font-weight:bold;":quesmix.quesanswer==3? "font-weight:bold;":quesmix.quesresult==3? "color:green; font-weight:bold;":""}">
                                <input type="radio" ${quesmix.quesanswer==3? "checked":""} disabled 
                                       /> c.${content[3]} <br />
                            </div>
                            <div style="${quesmix.quesanswer==quesmix.quesresult && quesmix.quesresult==4?"color:green; font-weight:bold;":quesmix.quesanswer==4? "font-weight:bold;":quesmix.quesresult==4? "color:green; font-weight:bold;":""}">
                                <input type="radio" ${quesmix.quesanswer==4? "checked":""} disabled 
                                       /> d.${content[4]} <br />
                            </div>
                        </div>
                        <div
                            class="question-answer mt-4 pt-3 pb-1"
                            style="background-color: blanchedalmond"
                            >
                            <h6 class="px-2">
                                Explanation: ${quesmix.quesnote}
                            </h6>
                        </div>
                        <!--fix-->
                        <div class="quiz-next d-flex ${quesmix.quesid > 1 && quesmix.quesid%10!=0? "justify-content-between":quesmix.quesid<2? "justify-content-end":quesmix.quesid%10==0? "justify-content-start":""} my-5">
                            <c:if test="${quesmix.quesid > 1}">
                                <a href="review?qid=${quimix.qid}&qrid=${quimix.qrid}&quesid=${quesmix.quesid-1}">
                                    <!--<p>&prev=${quesmix.quesid}</p>-->
                                    <button class="btn" style="color: red">
                                        <i class="fa-solid fa-angle-left mx-2"></i>Previous page
                                    </button></a>
                                </c:if>
                                <c:if test="${quesmix.quesid%10!=0}">
                                <a href="review?qid=${quimix.qid}&qrid=${quimix.qrid}&quesid=${quesmix.quesid+1}">
                                    <button class="btn" style="color: red">
                                        Next page<i class="fa-solid fa-angle-right mx-2"></i>
                                    </button></a>
                                </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <div class="quiz-nav col-lg-3 col-12">
                <div
                    class="col-12 quiz-nav-detail py-2"
                    style="border-top: 2px solid blue">
                    <h4>Quiz navigation</h4>
                    <div class="quiz-button d-flex my-4">
                        <!--fix-->
                        <c:forEach items="${listques}" var="num">
                            <a <c:if test="${num.quesflag eq 'true'}">
                                    title="This question is flagged"
                                </c:if>
                                <c:if test="${num.quesflag eq 'false'}">
                                    title="This question is not flagged"
                                </c:if>
                                style="<c:if test="${num.quesflag eq 'true'}">
                                    background: url(https://cmshn.fpt.edu.vn/theme/image.php/trema/theme/1597744132/mod/quiz/flag-on) 25px 0px no-repeat;
                                </c:if>
                                <c:if test="${num.quesid == quesmix.quesid}">
                                    border: 2.5px solid blue;
                                </c:if>
                                /*num.quesid>10?10+num.quesid%10:*/
                                color: black; background-color: #acc6da;" href="review?qid=${quimix.qid}&qrid=${quimix.qrid}&quesid=${num.quesid}">${num.quesid%10==0?10:num.quesid%10}</a>
                        </c:forEach>
                    </div>
                    <a href="quizresult?qid=${quimix.qid}" style="color: red"
                       ><h6 class="my-4">Finish review</h6></a
                    >
                </div>
            </div>
        </section>
        <%@include file="/jsp/footer.jsp" %>
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
</html>