<%-- 
    Document   : quiz_result
    Created on : Jun 11, 2022, 5:09:01 PM
    Author     : May Tinh Ha Anh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Result</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="../css/quiz_result.css">
        <style>
            input[type=button]:hover{
                background-color: rgba(0,0,0,0.2);
                border: 2px solid black;
            }
            
            #navbarSupportedContent form input, #navbarSupportedContent form button{
                display: none;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="container" style="margin-top: 110px;">
                <div class="header">
                    <div class="coursename"><h2><a <%--href="section?cid=${course.cid}"--%>>${course.cname}</a></h2></div>
                <div class="path">
                    <a href="home">Home</a>
                    <i class="fa-solid fa-angle-right mx-2"></i>
                    <a href="mycourse">My courses</a>
                    <i class="fa-solid fa-angle-right mx-2"></i>
                    <a href="section?cid=${course.cid}">${course.cname}</a>
                    <i class="fa-solid fa-angle-right mx-2"></i>
                    <a>${quizname}</a>
                </div>
            </div>
            <hr style="height:1px;border-width:0;color:black;background-color:black;">
            <div class="content" style="margin-top: 2%">
                <div class="quizname"><h4>${quizname}</h4></div>
                <div class="timelimit d-flex justify-content-center" style="margin-top: 1%">Time limit: 30 mins</div>
                <div class="summary" style="margin-top: 2%">Summary of your previous attempts</div>
                <table class="table table-striped border" style="margin-top: 1%;text-align: center;">
                    <tr>                        
                        <th>Attempt</th> 
                        <th>State</th>
                        <th>Marks/10 </th>
                        <th>Grade/10 </th>
                        <th>Review</th>
                    </tr>
                    <c:set var="qr" value="${listqr}"></c:set>
                    <c:if test="${qr.size()>=1}">
                        <c:forEach begin="0" end="${qr.size()-1}" var="i" >
                            <tr>
                                <td>${i+1}</td>
                                <td>Finished: ${qr.get(i).qstart}</td>
                                <td><fmt:formatNumber value="${(numOfQuestion* qr.get(i).qgrade)/10}" pattern="##" ></fmt:formatNumber></td>
                                <td>${qr.get(i).qgrade}</td>
                                <td><a href="review?qid=${qr.get(i).qid}&qrid=${qr.get(i).qrid}">Review</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
                <c:if test="${qr.size()!=0}">
                    <div class="highestgrade" style="font-size: larger;">Highest grade: ${highestGrade}/10</div>
                    <div style="font-size: larger;">Quiz pass (mark>=5): ${count}</div>
                    <div style="font-size: larger;">Quiz fail (mark<5): ${qr.size() - count}</div>
                </c:if>
                <div class="bt d-flex justify-content-center" >
                    <input onclick="reAttempt()" type="button" value="RE-ATTEMPT QUIZ">

                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
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
                        function reAttempt() {
                            window.location = "quizdetail?qid=${qr.get(0).qid}";
                        }
    </script>
</html>
