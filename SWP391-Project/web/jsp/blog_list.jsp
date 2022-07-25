
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Coursere Blog</title>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />


    </head>

    <body>
        <jsp:include page="header.jsp"/>

        <nav class="body row container-fluid d-flex justify-content-center ">


            <c:set var="bid" value="${requestScope.bid}" />
            <c:set var="searchBlog" value="${requestScope.searchBlog}" />
            <c:set var="bln" value="${requestScope.blogname}" />



            <c:if test="${bid==0 && page == 1 || searchBlog ne ''}">
                <div style="margin-top: 120px;width: 100%;"></div>
            </c:if>
                <c:if test="${bid==0 && page != 1 && searchBlog eq ''}">
                <div style="margin-top: 80px;width: 100%;"></div>
            </c:if>
            <c:if test="${bid!=0}">
                <div style="margin-top: 60px;"></div>
            </c:if>
            <div class="lastpost w-100  col-11 d-flex" >
                <c:if test="${page == 1 && bid==0 && searchBlog eq ''}">
                    <div class=" ">
                        <h5>Top 3 Latest Posts</h5>
                    </div>
                </c:if>

                <c:if test="${ searchBlog ne ''}">
                    <div class=" " >
                        <h5>Showing results for "${searchBlog}"</h5>
                    </div>
                </c:if>
                <c:if test="${bid==0 && page == 1 || searchBlog ne ''}" >
                    <form class="form-inline  my-2 my-lg-0 ml-auto" method="get" action="bloglist" >
                        <input class="form-control mr-sm-2" type="search" name="searchBlog" value="${searchBlog}" placeholder="Search in Post" aria-label="Search">
                        <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                                class="fa-solid fa-magnifying-glass"></i> </button>
                    </form>
                </c:if>
            </div>
            <div class="body_top row justify-content-center col-12 " >
                <c:if test="${searchBlog eq '' && bid==0}">
                    <c:if test="${page == 1 && bid==0}">
                        <div class="row  justify-content-center mt-3 col-12">
                            <div class="col-12 col-md-12 col-lg-7 px-0 py-3 mx-2"  >
                                <c:forEach items="${requestScope.listTop3}" var="bl2" begin="0" end="0">
                                    <a href="blogdetail?id=${bl2.poid}" class="d-flex align-items-end">
                                        <div class="p-2 bg-info w-100" style="position: absolute; "><h6 class=" mb-0 text-white " >${bl2.potitle}</h6></div>
                                        <img src="${bl2.poimg}" class="w-100" style="max-height: 55vh;overflow: hidden;">
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="col-12 col-md-12 col-lg-3 px-0 d-flex align-items-end flex-column mx-2" >
                                <c:forEach items="${requestScope.listTop3}" var="bl2" begin="1" end="1">
                                    <div class="py-3"  >
                                        <a href="blogdetail?id=${bl2.poid}" class="d-flex align-items-end">
                                            <h6 class=" mb-0 p-2 bg-info w-100 text-white " style="position: absolute;">${bl2.potitle}</h6>
                                            <img src="${bl2.poimg}" class="w-100">
                                        </a> 
                                    </div>
                                </c:forEach>                       
                                <c:forEach items="${requestScope.listTop3}" var="bl2" begin="2" end="2">
                                    <div class="mt-auto py-3" >
                                        <a href="blogdetail?id=${bl2.poid}" class="d-flex align-items-end">
                                            <h6 class="mb-0 p-2 bg-info w-100 text-white" style="position: absolute;">${bl2.potitle}</h6>
                                            <img src="${bl2.poimg}" class="w-100">
                                        </a> 
                                    </div>
                                </c:forEach>                        
                            </div>
                        </div>
                    </c:if>
                    <div class=" lastpost w-100 mt-5 px-5 col-12" >
                        <div class="d-flex">
                            <h5>Latest Posts Other</h5>
                            <c:if test="${bid==0 && page != 1}" >
                                <form class="form-inline  my-2 my-lg-0 ml-auto" method="get" action="bloglist" >
                                    <input class="form-control mr-sm-2" type="search" name="searchBlog" value="${searchBlog}" placeholder="Search in Post" aria-label="Search">
                                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                                            class="fa-solid fa-magnifying-glass"></i> </button>
                                </form>
                            </c:if>
                        </div>
                        <hr style="height:1px; width: 100%;  color: blue; background-color:blue;">
                    </div>
                </c:if>

                <c:if test="${searchBlog eq '' && bid!=0}">
                    <div class=" mt-5 py-5 w-100" style="background-color: #EBF3FF;text-align: center;">
                        <h3 class="py-3">${bln.bname}</h3>                    
                        <h6>${bln.btitle}</h6>
                    </div>
                    <div class=" lastpost w-100 mt-5 px-5 col-12">
                        <div class="d-flex">
                            <h5>Learn more about ${bln.bname}</h5>
                            <c:if test="${bid!=0}">
                                <form class="form-inline  my-2 my-lg-0 ml-auto" method="get" action="bloglist">
                                    <input class="form-control mr-sm-2" type="search" name="searchBlog" value="${searchBlog}" placeholder="Search in Post" aria-label="Search">
                                    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                                            class="fa-solid fa-magnifying-glass"></i> </button>
                                </form>
                            </c:if>
                        </div>
                        <hr style="height:1px; width: 100%;  color: blue; background-color:blue;">
                    </div>
                </c:if>

            </div>


            <div class="body_foot col-11 ">
                <div class=" mt-4">
                    <c:set var="page" value="${requestScope.page}" />

                    <c:if test="${requestScope.num > 1}">
                        <nav aria-label="...">
                            <ul class="page pagination">
                                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                    <li class="page-item ${i==page?"active":""}">
                                        <a class="page-link" href="bloglist?page=${i}&bid=${bid}&searchBlog=${searchBlog}">${i} <span class="sr-only">(current)</span></a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </nav>
                    </c:if>

                    <div class="row">

                        <c:if test="${bid==0 && searchBlog eq '' && page == 1}">
                            <c:forEach items="${requestScope.data}" var="bl"  begin="3" >
                                <div class="col-12 col-md-6 col-lg-4 px-4 mt-4 bg-white pt-2 d-flex "  >
                                    <div class="border ">
                                        <a href="blogdetail?id=${bl.poid}" class="text-dark " style="text-decoration: none;">
                                            <div class="">
                                                <img src="${bl.poimg}" class="w-100" style="max-height: 31vh;overflow: hidden;">
                                            </div>
                                            <div class="p-3 " >
                                                <h5 >${bl.potitle}</h5>
                                            </div>
                                            <div class="pl-3 ">
                                                <h7 >${bl.podate}</h7>
                                            </div>
                                            <div class="px-3 pt-3 text-lowercase" style="height: 15.5vh; overflow: hidden;" >
                                                <h7>${bl.podesc}</h7>
                                            </div>
                                            <div class="px-3 pb-3 "><h7>... read more</h7></div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>

                        <c:if test="${bid!=0 || searchBlog ne '' || page !=1}">
                            <c:forEach items="${requestScope.data}" var="bl"  >
                                <div class="col-12 col-md-6 col-lg-4 px-4 mt-4 bg-white pt-2 d-flex "  >
                                    <div class="border ">
                                        <a href="blogdetail?id=${bl.poid}" class="text-dark " style="text-decoration: none;">
                                            <div class="">
                                                <img src="${bl.poimg}" class="w-100" style="max-height: 31vh;overflow: hidden;"> 
                                            </div>
                                            <div class="p-3 " >
                                                <h5 >${bl.potitle}</h5>
                                            </div>
                                            <div class="pl-3 ">
                                                <h7 >${bl.podate}</h7>
                                            </div>
                                            <div class="px-3 pt-3 " style="height: 15.5vh; overflow: hidden;" >
                                                <h7 >${bl.podesc}</h7>
                                            </div>
                                            <div class="px-3 pb-3 "><h7>... read more</h7></div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        
                        

                    </div>  
                    <c:if test="${data.size()==0}">
                        <h5 class="notice-list mt-5 text-center">
                            <span style="color: #e66465; font-weight: bold">Not found</span> the post list that you want.&nbsp;Please <span style="color: green; font-weight: bold">re-do.</span><br>
                            <div class="mt-4"></div>
                            <span class="list-em"><i>The list is empty!</i></span>
                        </h5>
                    </c:if>
                </div>
            </div>

        </nav>

        <jsp:include page="footer.jsp"/>

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