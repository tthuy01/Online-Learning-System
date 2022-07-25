<%-- 
    Document   : lesson_view
    Created on : Jun 10, 2022, 4:19:41 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lesson view</title>
        <meta charset="UTF-8">
        <style type="text/css">
            <%@include file="../css/lessson_view.css"%>
        </style>

        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body style="">
        <c:set var="l" value="${requestScope.lesson}" />
        <c:set var="lessonCount" value="${requestScope.lessonCount}" />
        <c:set var="sectionCount" value="${requestScope.sectionCount}" />
        <c:set var="quiz" value="${requestScope.quiz}" />
        <c:set var="checknext" value="${requestScope.checknext}" />
        <c:set var="checknext2" value="${requestScope.checknext2}" />
        <c:set var="cid" value="${requestScope.cid}" />

        <nav>
            <div class="navbar w-100" >
                <div class="navbar_head w-100 p-2 " style="background-color: white; ">
                    <i class='bx bx-menu'></i>
                    <div class="lesson_name">
                        <h7>Section ${sectionCount} - Lesson ${lessonCount}:</h7>
                        <h7>${l.lname}</h7>
                        <br>
                        <h8>Lesson List</h8>
                    </div>
                    <div class="go_course ml-auto mb-auto align-items-center ">
                        <a href="coursedetail?cid=${cid}"><h7 style="color: black;">Go to Course</h7></a>
                    </div>
                </div>


                <div class="navbar_content" style="overflow: scroll;">
                    <div class="logo">
                        <span class="logo-name">Course content</span>
                        <i class='bx bx-x'></i>
                    </div>
                    <hr style="margin: 0;">

                    <ul class="content">
                        <c:set var="countSection" value="0"></c:set>
                        <c:forEach items="${requestScope.listSection}" var="ls">
                            <c:set var="countSection" value="${countSection+1}"></c:set>
                                <li>

                                    <div class="sub_lesson">
                                        <span>
                                            <h7>Selection ${countSection}: </h7>
                                        <h7 style="text-transform: uppercase;">${ls.sname}</h7>
                                    </span>
                                    <i class='bx bxs-chevron-down htmlcss_arrow arrow  '></i>
                                </div>

                                <ul class="sub_menu">
                                    <c:set var="count" value="0"></c:set>
                                    <c:forEach items="${requestScope.listLesson}" var="ll">                                       
                                        <c:forEach items="${requestScope.listLessonResult}" var="llr">                                       
                                            <c:if test="${ls.sid==ll.sid && ll.lid == llr.lid}">
                                                <c:set var="count" value="${count+1}"></c:set>
                                                    <li>
                                                        <a href="section?lid=${ll.lid}&cid=${cid}&section=${countSection}&lesson=${count}">
                                                        <c:if test="${llr.lstatus == true}">
                                                            <i class='bx bxs-checkbox-checked'></i>
                                                        </c:if>
                                                        <c:if test="${llr.lstatus == false}">
                                                            <i class='bx bx-checkbox'></i>
                                                        </c:if>                                                    
                                                        <h7>${count}: </h7>
                                                        <h8>${ll.lname}</h8>
<!--                                                        <div class="time"> ${ll.lvideo.length()}</div>-->
                                                    </a>
                                                </li>

                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>

                                    <c:set var="count" value="0"></c:set>
                                    <c:forEach items="${requestScope.listQuiz}" var="lq">
                                        <c:set var="endquiz" value="${endquiz+1}"></c:set>
                                        <c:if test="${ls.sid==lq.sid}">
                                            <c:set var="count" value="${count+1}"></c:set>                                         
                                                <li>

                                                <c:set var="noncheck" value="0"></c:set>
                                                <c:forEach items="${requestScope.listQuizResult}" var="lqr" >
                                                    <c:if test="${lqr.qid == lq.qid}">
                                                        <button onclick="myFunction2(${lqr.qid})"> 
                                                            <c:if test="${lqr.qstatus == true}">
                                                                <i class='bx bxs-checkbox-checked' ></i>
                                                            </c:if>
                                                            <c:if test="${lqr.qstatus == false}">
                                                                <i class='bx bx-checkbox'></i>
                                                            </c:if>
                                                            <c:set var="noncheck" value="1"></c:set>                                                        
                                                            <h7>Quiz ${count}: </h7>
                                                            <h8>${lq.qname}</h8>
                                                        </button> 
                                                    </c:if>                                                       
                                                </c:forEach>

                                                <c:if test="${noncheck == 0}">
                                                    <button onclick="myFunction(${lq.qid})">                                                    
                                                        <i class='bx bx-checkbox'></i>
                                                        <h7>Quiz ${count}: </h7>
                                                        <h8>${lq.qname}</h8>
                                                    </button> 
                                                </c:if>                                                       

                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>

                            </li>
                        </c:forEach>
                    </ul>
                </div>

            </div>
        </nav>


        <!--video-->
        <div class="video">
            <iframe id="videoplayer" src="${l.lvideo}?enablejsapi=1" width="100%" height="630px;" frameborder="0" 
                    allowfullscreen >
            </iframe>
        </div>

        <!--Lesson content-->
        <div class="lesson_desc mt-3 ml-5 mb-4">
            <h4>What do you learn in this lesson?</h4>
            ${l.ldesc}
        </div>



        <!--Previous  next-->
        <div class="next_lesson">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <li class="page-item ${sectionCount==1&&lessonCount==1?"disabled":""}"><a class="page-link" href="section?lid=${l.lid}&cid=${cid}&section=${sectionCount}&lesson=${lessonCount - 1}&next=-1">Previous</a></li>
                        <c:if test="${checknext!=null}">
                        <li class="page-item"><a class="page-link" href="section?lid=${l.lid}&cid=${cid}&section=${sectionCount}&lesson=${lessonCount + 1}&next=1">Next Lesson</a></li> 
                        </c:if>
                    <!--het lesson trong section-->
                    <c:if test="${checknext==null && checknext2!=null && quizresult == null && quiz != null}">
                        <li onclick="myFunction3(${quiz.qid})" class="page-item"><button class="page-link" >Next Lesson</button></li> 
                        </c:if>
                        <c:if test="${checknext==null && checknext2!=null && quiz == null}">
                        <li class="page-item"><a class="page-link" href="section?lid=${l.lid}&cid=${cid}&section=${sectionCount+1}&lesson=${1}&next=2">Next Lesson</a></li> 
                        </c:if>
                        <c:if test="${checknext==null && checknext2!=null && quizresult !=null && quiz != null}">
                        <li onclick="myFunction5(${quizresult.qid})" class="page-item"><button class="page-link" >Next Lesson</button></li> 
                        </c:if>
                    <!--het lesson trong section-->
                    <c:if test="${checknext==null && checknext2==null && quizresult == null && quiz != null}"> 
                        <li onclick="myFunction4(${quiz.qid})" class="page-item"><button class="page-link" >Next Lesson</button>></li> 
                        </c:if>
                        <c:if test="${checknext==null && checknext2==null && quizresult != null && quiz != null}"> 
                        <li onclick="myFunction6(${quizresult.qid})" class="page-item"><button class="page-link" >Next Lesson</button>></li> 
                        </c:if>
                </ul>
            </nav>
        </div>

        <!--function khi goi quiz-->                
        <script>
            function myFunction(id) {
                let text = "Do you want to take the quiz now?\n\(you will be redirected to the quiz page)";
                if (confirm(text)) {
                    window.location = "quizdetail?qid=" + id;
                }
            }
            function myFunction2(id) {
                let text = "Do you want to review the quiz now?\n\(you will be redirected to the quiz page)";
                if (confirm(text)) {
                    window.location = "quizresult?qid=" + id;
                }
            }
            function myFunction3(id) {
                let text = "You have learned all the lessons in this section\n\Do you want to take the quiz now?\n\(you will be redirected to the quiz page)";
                let text2 = "Do you want to continue studying the next section?";
                if (confirm(text)) {
                    window.location = "quizdetail?qid=" + id;
                } else {
                    if (confirm(text2)) {
                        window.location = "section?lid=${l.lid}&cid=${cid}&section=${sectionCount+1}&lesson=${1}&next=2";
                    }
                }
            }
            function myFunction4(id) {
                let text = "You have learned all the lessons in this section\n\Do you want to take the quiz now?\n\(you will be redirected to the quiz page)";
                let text2 = "You have learned all the lessons in this section";
                if (confirm(text)) {
                    window.location = "quizdetail?qid=" + id;
                } else {
                    window.alert(text2);
                }
            }
            function myFunction5(id) {
                let text = "Do you want to review the quiz now?\n\(you will be redirected to the quiz page)";
                let text2 = "Do you want to continue studying the next section?";
                if (confirm(text)) {
                    window.location = "quizdetail?qid=" + id;
                } else {
                    if (confirm(text2)) {
                        window.location = "section?lid=${l.lid}&cid=${cid}&section=${sectionCount+1}&lesson=${1}&next=2";
                    }
                }
            }
            function myFunction6(id) {
                let text = "Do you want to review the quiz now?\n\(you will be redirected to the quiz page)";
                let text2 = "You have learned all the lessons in this section";
                if (confirm(text)) {
                    window.location = "quizdetail?qid=" + id;
                } else {
                    window.alert(text2);
                }
            }
        </script>


        <!--function xet video finish-->
        <script type="text/javascript">
            var tag = document.createElement('script');
            tag.src = "https://www.youtube.com/iframe_api";
            var firstScript = document.getElementsByTagName('script')[0];
            firstScript.parentNode.insertBefore(tag, firstScript);
            function onYouTubeIframeAPIReady() {
                new YT.Player('videoplayer', {
                    events: {
                        'onStateChange': function (evt) {
                            if (evt.data === YT.PlayerState.ENDED) {
                                window.location.href = "section?lid=${l.lid}&cid=${cid}&section=${sectionCount}&lesson=${lessonCount}&end=1";
                            }
                        }
                    }
                });
            }
        </script>   

        <script type="text/javascript">
            <%@include file="../js/lesson_view.js"%>
        </script>



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
</html>
