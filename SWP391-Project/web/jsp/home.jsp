<%-- 
    Document   : home
    Created on : May 28, 2022, 6:34:46 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Home</title>
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
        </style>

    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <c:if test="${sessionScope.user == null}">
            <section>
                <div class="container-fluid d-flex justify-content-around align-items-center banner" style="margin: 110px 0 80px 0">
                    <div class="row">
                        <div class="col-xs-12 col-md-8 mt-5">
                            <h1 class="ban_text">Learn without limits</h1>
                            <h4 class="my_text mb-5">
                                Start, switch, or advance your career with many courses from
                                out platform.
                            </h4>
                            <button class="my_button">
                                <a style="color: white;" href="register" title="Sign up">Join in Free</a>
                            </button>
                            <button class="my_button" style="background-color: #ffffff;">
                                <a style="color: #0056d2;" href="courselist" title="Discovery all courses">Try all Courses</a>
                            </button>
                        </div>
                        <div class="col-xs-12 col-md-4">
                            <img class="img-fluid" src="https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://images.ctfassets.net/wp1lcwdav1p1/6JVJEaAT8FWwiBfVl1vECY/e4c94d93384ea61e0ec347e1e0605d58/C_Learner_Ehab.png?auto=format%2Ccompress&dpr=1&w=459&h=497&q=40" alt="image banner"/>
                        </div>
                    </div>
                </div>
                <div class="d-flex flex-column justify-content-center align-items-center mb-5 advertise">
                    <div class="my_text" style="font-size: 30px; color: #0062E4">
                        Discovering our courses you can learn many things
                    </div>
                    <div
                        id="carouselExampleIndicators"
                        class="container-fluid carousel slide col-10"
                        data-ride="carousel"
                        >
                        <ol class="carousel-indicators">
                            <!--danh dau dang o slide -->
                            <c:set value="0" var="countslide"/>
                            <c:forEach items="${sliders}" var="sliders">
                                <li data-target="#carouselExampleIndicators" data-slide-to="${sliders.id}" class="${countslide==0?'active':''}"></li>
                                    <c:set value="${countslide+1}" var="countslide"/>
                                </c:forEach>
                        </ol>
                        <!--slide nao xuat hien dau tien-->
                        <div class="carousel-inner">
                            <c:set value="0" var="countslide"/>
                            <c:forEach items="${sliders}" var="sliders">
                                <div class="carousel-item ${countslide==0?'active':''}">
                                    <c:set value="${countslide+1}" var="countslide"/>
                                    <a href="${sliders.link}" title="Go to IT Course">
                                        <img class="d-block" src="./images/slider/${sliders.image}" style="width: 100%;height: 300px;" alt="image slider"/>
                                        <div class="carousel-caption" style="background-color: beige;color: black">
                                            ${sliders.title}
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                        <a
                            class="carousel-control-prev pre my-auto"
                            href="#carouselExampleIndicators"
                            role="button"
                            data-slide="prev"
                            style="width: 60px;height: 50px;"
                            >
                            <i style="font-size: 30px;" class="fa-solid fa-angle-left"></i>
                        </a>
                        <a
                            class="carousel-control-next next my-auto"
                            href="#carouselExampleIndicators"
                            role="button"
                            data-slide="next"
                            style="width: 60px;height: 50px"
                            >
                            <i style="font-size: 30px;" class="fa-solid fa-angle-right"></i>
                        </a>
                    </div>
                </div>
            </section>
        </c:if>
        <c:if test="${sessionScope.user != null && sessionScope.user.rid == 2}">
            <section>
                <nav class="navbar navbar-expand-lg navbar-light py-3 mb-5 user_banner">
                    <ul class="navbar-nav"> 
                        <li class="nav-item mr-3 active">
                            <a class="a" style="font-weight: bold" href="home" title="Home Page">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="a" href="mycourse" title="Course registration">My Courses</a>
                        </li>
                    </ul>
                </nav>
            </section>
        </c:if>
        <section class="container-fluid row mx-auto px-0" style="${sessionScope.user != null && sessionScope.user.rid != 2? "margin-top: 130px":""}">
            <div class="col-xs-12 col-sm-10 col-md-8">
                <div id="course" class="d-flex flex-column">
                    <div class="advertise">
                        <div class="my_text mb-1" style="color: #382D8B">
                            <!--<p>${requestScope.numberFr}</p>-->
                            100% Free courses (Top featured courses)
                        </div>
                        <h3>Start learning with free courses</h3>
                        <h6>Free courses from top subjects in our platform.</h6>
                    </div>
                    <hr class="mt-0" style="width: 100%">
                    <div class="course-content row">
                        <c:forEach items="${requestScope.listFreeCourse}" var="list">
                            <div class="d-flex col-lg-3 col-md-6 col-xs-12 my-3">
                                <div class="course-detail">
                                    <a style="text-decoration: none" href="coursedetail?cid=${list.cid}">
                                        <img class="img-fluid" src="${list.cimg}" alt="image about course" title="Thumbnail">
                                        <div class="course-title">
                                            <c:if test="${list.cprice == 0}"><span class="my_span">Free</span></c:if>
                                            <c:if test="${list.cprice != 0}"><span class="my_span">${list.cprice} $</span></c:if>
                                            <div class="div1" title="Title">Course title: ${list.ctitle}</div>
                                            <div class="div2" title="Tag Line">Tag Line: <c:forEach var="splt" items="${fn:split(list.cdesc,'|')}" begin="0" end="0">
                                                    "${splt}" 
                                                </c:forEach></div>
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
                    <div class="mt-5" style="text-align: center">
                        <button class="my_button" style="background-color: #ffffff; font-size: 12px;">
                            <a class="my_link" href="courselist?search=&subject=0&level=0&lecturer=0&price=free&public=0" title="Discovery all courses">Explore more Courses</a>
                        </button>
                    </div>
                    <div class="advertise mt-4">
                        <div class="my_text mb-1" style="color: #382D8B">Specialized courses (Top featured courses)</div>
                        <h3>Start learning with specialized courses</h3>
                        <h6>Specialized courses covering top topics as business, technology, and more.</h6>
                    </div>
                    <div class="course-content row">
                        <c:forEach items="${requestScope.listFeeCourse}" var="list">
                            <div class="d-flex col-lg-3 col-md-6 col-xs-12 my-3">
                                <div class="course-detail">
                                    <a style="text-decoration: none" href="coursedetail?cid=${list.cid}">
                                        <img class="img-fluid" src="${list.cimg}" alt="image about course" title="Thumbnail">
                                        <div class="course-title">
                                            <c:if test="${list.cprice == 0}"><span class="my_span">Free</span></c:if>
                                            <c:if test="${list.cprice != 0}"><span class="my_span">${list.cprice} $</span></c:if>
                                            <div class="div1" title="Title">Course title: ${list.ctitle}</div>
                                            <div class="div2" title="Tag Line">Tag Line: <c:forEach var="splt" items="${fn:split(list.cdesc,'|')}" begin="0" end="0">
                                                    "${splt}" 
                                                </c:forEach></div>
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
                    <div class="mt-5" style="text-align: center">
                        <button class="my_button" style="background-color: #ffffff; font-size: 12px;">
                            <a class="my_link" href="courselist?search=&subject=0&level=0&lecturer=0&price=45.0+and+90.0&public=0" title="Discovery all courses">Explore more Courses</a>
                        </button>
                    </div>
                    <div class="advertise mt-4">
                        <div class="my_text mb-1" style="color: #382D8B">Popular posts (Top featured posts)</div>
                        <h3>Hot posts</h3>
                        <h6>Posts in top topics as news, education, and more.</h6>
                    </div>
                    <div class="course-content row">
                        <c:forEach items="${requestScope.listEarlyPost}" var="list">
                            <div class="d-flex col-lg-3 col-md-6 col-xs-12 my-3">
                                <a style="text-decoration: none" href="blogdetail?id=${list.poid}">
                                    <div class="course-detail">
                                        <img class="img-fluid" style="max-height: 12vh" src="${list.poimg}" alt="image about post" title="Thumbnail">
                                        <div class="course-title">
                                            <div class="div3" title="Title">Post title: ${list.potitle}</div>
                                            <div class="div2" title="Updated Day">Post updated: ${list.podate}</div>
                                            <h6>
                                                <c:forEach items="${listBlog}" var="lb">
                                                    <c:if test="${list.bid==lb.bid}">Blog: ${lb.bname}</c:if>
                                                </c:forEach>
                                            </h6>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="mt-5" style="text-align: center">
                        <button class="my_button" style="background-color: #ffffff; font-size: 12px;">
                            <a class="my_link" href="bloglist" title="Discovery all posts">Explore more Posts</a>
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 col-sm-2 col-md-4">
                <div class="d-flex flex-column">
                    <div class="advertise">
                        <div class="my_text mb-1" style="color: #382D8B">Usual posts (Top ${requestScope.numberL} posts)</div>
                        <h3>Lasted posts</h3>
                        <h6>Posts in which has been updated lately.</h6>
                    </div>
                    <hr class="mt-0 w-100">
                    <c:forEach items="${requestScope.listLastestPost}" var="list" >
                        <div class="course-content">
                            <div class="course-title" style="width: 80%">
                                <a href="blogdetail?id=${list.poid}" style="text-decoration: none;">
                                    <img class="img-fluid" src="${list.poimg}" alt="image about post" title="Thumbnail">
                                    <div class="div1 my-2" title="Title">Post title: ${list.potitle}</div>
                                    <div class="div2 mb-0" title="Updated Day">Post updated: ${list.podate}</div>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <c:if test="${sessionScope.user == null}">
            <section class="d-flex justify-content-around my-5">
                <div class="row">
                    <div class="col-xs-12 col-md-8 mt-4">
                        <h1 class="ban_text mb-2" style="font-size: 30px">Take the next step toward your personal and<br/> professional goals with Coursere.</h1>
                        <h4 class="my_text mb-4">Join now to receive personalized recommendations from the full<br/> Coursere catalog.</h4>
                        <button class="my_button">
                            <a style="color: white;" href="register" title="Sign up">Join in Free</a>
                        </button>
                    </div>
                    <div class="col-xs-12 col-md-4">
                        <img class="img-fluid" src="https://d3njjcbhbojbot.cloudfront.net/api/utilities/v1/imageproxy/https://coursera_assets.s3.amazonaws.com/front-page-rebrand/secondary-consumer-cta/Image-Next-Step.png?auto=format%2Ccompress&dpr=1&w=471&h=330&q=40" alt="image banner" />
                    </div>
                </div>
            </section>
        </c:if>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>
