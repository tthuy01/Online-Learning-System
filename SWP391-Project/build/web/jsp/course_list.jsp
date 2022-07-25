<%-- 
    Document   : course_list.jsp
    Created on : May 27, 2022, 3:09:08 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Course</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- JS for jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- CSS for searching -->
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <!-- JS for searching -->

        <script>
            // .js-example-basic-single declare this class into your select box
            $(document).ready(function () {
                $('.js-example-basic-single').select2();
            });
        </script>
        <style type="text/css">
            <%@include file="../css/course_list.css"%>
        </style>
    </head>

    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <section id="top">
            <c:if test="${search==null||search eq ''}">
                <div class="top-banner d-flex flex-column justify-content-around">
                    <div>
                        <a href="">COURSERE</a>
                        <i class="fa-solid fa-angle-right mx-2"></i>
                        <c:if test="${subject == 0||subject==null}">
                            <p style="display: inline;">List of courses</p>
                        </c:if>
                        <c:if test="${subject != 0&&subject!=null}">
                            <p style="display: inline;">${listSubject.get(subject-1).sname}</p>
                        </c:if>
                    </div>
                    <div>
                        <c:if test="${subject == 0||subject==null}">
                            <h2>List of courses</h2>
                        </c:if>
                        <c:if test="${subject != 0&&subject!=null}">
                            <h2>${listSubject.get(subject-1).sname}</h2>
                        </c:if>
                    </div>
                </div>
            </c:if>
            <c:if test="${search!=null&&search ne ''}">
                <div class="top-banner d-flex flex-column justify-content-around" style="height: 80px;padding-top: 30px;
                     background-color: #f5f7f8;color: #636363;">
                    <div>
                        <a href="" style="color:#0056d2;">COURSERE</a>
                        <i class="fa-solid fa-angle-right mx-2"></i>
                        <p style="display: inline;">Search</p>
                        <span class="mt-4" style="display: block;color: black;font-weight: 500;font-size: 20px">Showing ${size} total results for "${search}"</span>
                    </div>
                </div>
            </c:if>
            <div class="top-filter">
                <div class="search-filter">
                    <div class="main-filter d-flex flex-column">
                        <div class="filterby-section">
                            <h3 style="font-size: 14px;color: #373A3C;">Filter By</h3>
                        </div>
                        <form action="courselist" method="get" id="myForm">
                            <div class="filter-section d-flex justify-content-between">
                                <input type="hidden" name="search" value="${search}"/>
                                <select name="subject" id="subject" onchange="this.form.submit()" class="js-example-basic-single">
                                    <option ${subject=="0"?"selected":""} value="0">Subject</option>
                                    <c:forEach items="${listSubject}" var="s">
                                        <option ${subject==s.sid?"selected":""} value="${s.sid}">${s.sname}</option>
                                    </c:forEach>
                                </select>
                                <select name="level" id="level" onchange="this.form.submit()">
                                    <option ${level=="0"?"selected":""} value="0">Level</option>
                                    <c:forEach items="${listLevel}" var="l">
                                        <option ${level==l.lid?"selected":""} value="${l.lid}">${l.lname}</option>
                                    </c:forEach>
                                </select>
                                <select name="lecturer" id="lecturer" onchange="this.form.submit()" class="js-example-basic-single">
                                    <option ${lecturer=="0"?"selected":""} value="0">Lecturer</option>
                                    <c:forEach items="${listLec}" var="lecturer">
                                        <option ${requestScope.lecturer==lecturer.lid?"selected":""} value="${lecturer.lid}">${lecturer.lname}</option>
                                    </c:forEach>
                                </select>
                                <select name="price" id="price" onchange="this.form.submit()">
                                    <option ${price=="all"?"selected":""} value="all">Price</option>
                                    <option ${price=="free"?"selected":""} value="free">Free</option>
                                    <c:forEach begin="0" end="3" var="i">
                                        <c:set value="${(maxPrice/4)*i} and ${(maxPrice/4)*(i+1)}" var="p"></c:set>
                                        <option  ${price==p?"selected":""} value="${p}">${(maxPrice/4)*i} $ - ${(maxPrice/4)*(i+1)} $</option>
                                    </c:forEach>
                                </select>
                                <select name="public" id="public" onchange="this.form.submit()">
                                    <option ${public=="0"?"selected":""} value="0">Public Date</option>
                                    <option ${public=="desc"?"selected":""} value="desc">Newest</option>
                                    <option ${public=="asc"?"selected":""} value="asc">Oldest</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="d-flex">
                        <c:if test="${subject ne '0'&&subject!=null}">
                            <div class="filter-clear">
                                <p>Subject: ${listSubject.get(subject-1).sname}<i id="subjectId" class="fa-solid fa-xmark ml-3" style="cursor: pointer" onclick="clearFilter(this.id)"></i></p>
                            </div>
                        </c:if>
                        <c:if test="${level ne '0'&&level!=null}">
                            <div class="filter-clear">
                                <p>Level: ${listLevel.get(level-1).lname}<i id="levelId" class="fa-solid fa-xmark ml-3" style="cursor: pointer" onclick="clearFilter(this.id)"></i></p>
                            </div>
                        </c:if>
                        <c:if test="${lecturer ne '0'&&lecturer!=null}">
                            <div class="filter-clear">
                                <p>Lecturer: ${listLec.get(lecturer-1).lname}<i id="lecturerId" class="fa-solid fa-xmark ml-3" style="cursor: pointer" onclick="clearFilter(this.id)"></i></p>
                            </div>
                        </c:if>
                        <c:if test="${price!=null&&price ne 'all'}">
                            <div class="filter-clear">
                                <p>Price: ${price=="free"?"Free":""}<c:forEach begin="0" end="3" var="i">
                                        <c:set value="${(maxPrice/4)*i} and ${(maxPrice/4)*(i+1)}" var="p"></c:set>
                                        <c:if test="${price==p}">${(maxPrice/4)*i} $ to ${(maxPrice/4)*(i+1)} $</c:if>
                                    </c:forEach><i id="priceId" class="fa-solid fa-xmark ml-3" style="cursor: pointer" onclick="clearFilter(this.id)"></i></p>
                            </div>
                        </c:if>
                        <c:if test="${public!=null&&public ne '0'}">
                            <div class="filter-clear">
                                <p>Public Date: ${public=="desc"?"Newest":"Oldest"}<i id="publicId" class="fa-solid fa-xmark ml-3" style="cursor: pointer" onclick="clearFilter(this.id)"></i></p>
                            </div>
                        </c:if>
                        <div>
                            <p id="clear" ${subject==null||(subject=="0"&&level=="0"&&lecturer=="0"&&price=="all"&&public=="0")?"hidden":""} 
                               onclick="window.location = 'courselist'">Clear all</p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section id="course" class="row container-fluid">
                <div class="col-lg-3 col-md-6 col-12">
                    <form class="form-inline mb-4" method="get" action="courselist">
                        <input style="width: 250px" class="form-control mr-sm-2" type="search" name="search" value="${search}" placeholder="Enter course name or course id..." aria-label="Search">
                    <input hidden type="text" name="subject" value="${subject==null?0:subject}"/>
                    <input hidden type="text" name="level" value="${level==null?0:level}"/>
                    <input hidden type="text" name="lecturer" value="${lecturer==null?0:lecturer}"/>
                    <input hidden type="text" name="price" value="${price==null?'all':price}"/>
                    <input hidden type="text" name="public" value="${public==null?0:public}"/>
                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                            class="fa-solid fa-magnifying-glass"></i> </button>
                </form>
                <div class="course-header" style="margin-bottom: 23px">
                    <h4 style="">Featured courses</h4>
                </div>
                <ul style="list-style: none; margin-left: -50px">
                    <c:forEach items="${listF}" var="listF">
                        <li style="padding: 10px;border-top:1px solid #74a3e6;">
                            <a href="coursedetail?cid=${listF.cid}" class="d-flex" style="text-decoration: none">
                                <img style="width: 100px;height: 80px;margin-right: 20px" src="${listF.cimg}" class="img-fluid"/>
                                <div class="d-flex flex-column">
                                    <span>Course Name: ${listF.cname}</span>
                                    <c:if test="${listF.cprice == 0}">
                                        <span class="my_span">Free</span>
                                    </c:if>
                                    <c:if test="${listF.cprice != 0}">
                                        <span class="my_span"><del>${listF.cprice} $</del>&nbsp;&nbsp;${listF.cprice*80/100} $</span>
                                    </c:if>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-lg-9 col-md-6 col-12">
                <div class="course-header">
                    <h4 ${error!=null?"hidden":""}>List of courses</h4>
                </div>
                <div class="course-content row">
                    <c:forEach items="${requestScope.listCourse}" var="listcourse">
                        <div class="col-lg-3 col-md-6 col-12 my-3 d-flex">
                            <a href="coursedetail?cid=${listcourse.cid}" style="color: black; text-decoration: none">
                                <div class="course-detail">
                                    <img class="img-fluid"
                                         src="${listcourse.cimg}"
                                         alt="">
                                    <div class="course-title d-flex flex-column justify-content-around">
                                        <c:if test="${listcourse.cprice == 0}">
                                            <span class="my_span">Free</span>
                                        </c:if>
                                        <c:if test="${listcourse.cprice != 0}">
                                            <span class="my_span"><del>${listcourse.cprice} $</del>&nbsp;&nbsp;${listcourse.cprice*80/100} $</span>
                                        </c:if>
                                        <div class="div1" title="Title">Course title: ${listcourse.ctitle}</div>
                                        <div class="div2" title="Tag Line">Tag Line: <c:forEach var="splt" items="${fn:split(listcourse.cdesc,'|')}" begin="0" end="0">
                                                "${splt}" 
                                            </c:forEach></div>
                                        <h6>
                                            <c:forEach items="${listSubject}" var="s">
                                                <c:if test="${s.sid==listcourse.sid}">Subject: ${s.sname}</c:if>
                                            </c:forEach>
                                        </h6>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${error!=null}">
                    <h2 class="text-center" style="margin-bottom: 200px">${error}</h2>
                </c:if>
                <c:if test="${error==null}">
                    <nav class="d-flex justify-content-center mt-5" aria-label="...">
                        <ul class="pagination">
                            <c:if test="${subject==null}">
                                <li class="page-item ${index==1?"disabled":""}">
                                    <a class="page-link" href="courselist?<c:if test="${search!=null}">search=${search}&</c:if>index=${index-1}" tabindex="-1">Previous</a>
                                    </li>
                                <c:forEach begin="${1}" end="${endPage}" var="page">
                                    <li class="page-item ${index==page?"active":""}"><a class="page-link" href="courselist?<c:if test="${search!=null}">search=${search}&</c:if>index=${page}">${page}</a></li>
                                    </c:forEach>
                                <li class="page-item ${index==endPage?"disabled":""}">
                                    <a class="page-link" href="courselist?<c:if test="${search!=null}">search=${search}&</c:if>index=${index+1}">Next</a>
                                    </li>
                            </c:if>
                            <c:if test="${subject!=null}">
                                <li class="page-item ${index==1?"disabled":""}">
                                    <a class="page-link" href="courselist?<c:if test="${search!=null}">search=${search}&</c:if>subject=${subject}&level=${level}&lecturer=${lecturer}&price=${price}&public=${public}&index=${index-1}" tabindex="-1">Previous</a>
                                    </li>
                                <c:forEach begin="${1}" end="${endPage}" var="page">
                                    <li class="page-item ${index==page?"active":""}"><a class="page-link" href="courselist?<c:if test="${search!=null}">search=${search}&</c:if>subject=${subject}&level=${level}&lecturer=${lecturer}&price=${price}&public=${public}&index=${page}">${page}</a></li>
                                    </c:forEach>
                                <li class="page-item ${index==endPage?"disabled":""}">
                                    <a class="page-link" href="courselist?<c:if test="${search!=null}">search=${search}&</c:if>subject=${subject}&level=${level}&lecturer=${lecturer}&price=${price}&public=${public}&index=${index+1}">Next</a>
                                    </li>
                            </c:if>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </section>
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
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

    <script type="text/javascript">
                               function clearFilter(choiceId) {
                                   const choice = choiceId.split("I");
                                   if (choice[0] == "price") {
                                       document.getElementById(choice[0]).value = "all";
                                   } else {
                                       document.getElementById(choice[0]).value = "0";
                                   }
                                   document.getElementById("myForm").submit();
                               }
    </script>
</html>