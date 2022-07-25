<%-- 
    Document   : my_course
    Created on : Jun 11, 2022, 3:15:27 PM
    Author     : May Tinh Ha Anh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Course</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="../css/my_course.css">
        <style type="text/css">
            .container {
                /*    background-color: rgba(0, 0, 255, 0.1);
                    height: 100px;*/
                margin-top: 100px;
            }

            .course-detail {
                box-shadow: rgba(31, 31, 31, 0.12) 0px 1px 6px, rgba(31, 31, 31, 0.12) 0px 1px 4px;
                background-color: white;
                padding-bottom: 10px;
                /*max-height: 340px;*/
                min-height: 350px;
            }

            .course-title{
                margin: 10px 10px;
                box-sizing: border-box;
            }

            .course-detail img{
                width: 100%;
                height: auto;
                object-fit: cover;
            }

            .course-content > div{
                cursor: pointer;
                opacity: 1;
                transition: 0.4s all;
                transform: translateY(10px);
                /*transform: rotate(0deg);*/
                /*transform: rotate(0deg);*/

            }

            .course-content > div:hover{
                opacity: 0.7;
                /*transform: translateX(50px);*/
                /*transform: rotate(360deg);*/
                transform: translateX(0px);
            }

            .ul li a:active {
                background-color: blue;
            }
            .user_banner {
                background-color: #EBF3FF;
                margin-top: 70px;
            }
            .user_banner a {
                color: black;
                text-decoration: none;
            }
        </style>
    </head>
    <body style="background-color: white;">
        <jsp:include page="header_my_course.jsp"></jsp:include>
            <section>
                <nav class="navbar navbar-expand-lg navbar-light py-3 user_banner">
                    <ul class="navbar-nav"> 
                        <li class="nav-item mr-3">
                            <a class="a" href="home" title="Home Page">Home</a>
                        </li>
                        <li class="nav-item active">
                            <a class="a" href="mycourse" style="font-weight: bold" title="Course registration">My Courses</a>
                        </li>
                    </ul>
                </nav>
            </section>
        <div class="container">
                <div class="my-course-title">
                    <h3>My course</h3>
                </div>
                <div class="course-content row">
                <c:forEach var="mc" items="${requestScope.listMyCourse}">
                    <div class="col-lg-3 col-md-6 col-12 my-3 d-flex">
                        <a href="coursedetail?cid=${mc.cid}" style="color: black; text-decoration: none">
                            <div class="course-detail">
                                <img class="img-fluid"
                                     src="${mc.cimg}"
                                     alt="">
                                <div class="course-title">
                                    <div><b>Course name: ${mc.cname}</b></div>
                                    <div>${mc.ctitle}</div>
                                </div>
                                <hr style="height:1px; border-width:0; color:black; background-color:black; margin-left: 10px; margin-right: 10px;">
                                <div class="status" style="margin-left: 10px; margin-bottom: 10px;">
                                    Start course / In progress
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div class="paging-block d-flex justify-content-center" style="margin-top: 3%;">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:set var="page" value="${requestScope.page}"></c:set>
                        <c:if test="${requestScope.num != 0}">
                            <c:if test="${search != null}">
                                <li class="page-item ${page==1?"disabled":""}"><a class="page-link" href="searchmycourse?search=${search}&page=${page+1}">Previous</a></li>
                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                    <li class="page-item ${i==page?"active":""}"><a class="page-link" href="searchmycourse?search=${search}&page=${i}">${i}</a></li>
                                    </c:forEach>
                                <li class="page-item ${page==num?"disabled":""}"><a class="page-link" href="searchmycourse?search=${search}&page=${page+1}">Next</a></li>
                                </c:if>
                                <c:if test="${search == null}">
                                <li class="page-item ${page==1?"disabled":""}"><a class="page-link" href="mycourse?page=${page-1}">Previous</a></li>
                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                    <li class="page-item  ${i==page?"active":""}"><a class="page-link" href="mycourse?page=${i}">${i}</a></li>
                                    </c:forEach>
                                <li class="page-item ${page==num?"disabled":""}"><a class="page-link" href="mycourse?page=${page+1}">Next</a></li>
                                </c:if>
                            </c:if>
                    </ul>
                </nav>
            </div>
            <c:if test="${requestScope.num == 0}">
                <div style="text-align: left; font-size: 20px;">
                    Not found <b>${search}</b> course!
                </div>
            </c:if>  
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
</html>