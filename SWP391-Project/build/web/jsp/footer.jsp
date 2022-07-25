<%-- 
    Document   : footer
    Created on : May 27, 2022, 3:13:05 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <%@include file="../css/footer.css"%>
        </style>
    </head>

    <body>
        <footer class="pt-1 pb-4">
            <hr class="mb-5">
            <div class="row container mx-auto">
                <div class="col-lg-3 col-md-6 col-12 mb-3">
                    <h5 class="mb-3">COURSERE</h5>
                    <p class="pt-2" style="color: #373A3C;font-size: 14px;">Get the in-demand skills you need to grow
                        professionally. Choose
                        any SkillSet that
                        aligns with your learning goals.
                    </p>
                </div>
                <div class="col-lg-3 col-md-6 col-12 mb-3">
                    <h5 class="mb-3">SUBJECT</h5>
                    <ul style="list-style: none;padding-left: 0;">
                        <c:forEach items="${listSubject}" var="lists">
                            <li><a href="courselist?subject=${lists.sid}">${lists.sname}</a></li>
                            </c:forEach>
                    </ul>
                </div>
                <div class="col-lg-3 col-md-6 col-12 mb-3">
                    <h5 class="mb-3">BLOG</h5>
                    <ul style="list-style: none;padding-left: 0;">
                        <c:forEach items="${listBlog}" var="lb">
                            <li><a href="bloglist?bid=${lb.bid}">${lb.bname}</a></li>
                            </c:forEach>
                    </ul>
                </div>
                <div class="col-lg-3 col-md-6 col-12 mb-3">
                    <h5 class="mb-3">CONTACT</h5>
                    <ul class="row" style="list-style: none;padding-left: 0;">
                        <li class="col-4"><a href="#"><i class="fa-brands fa-facebook"></i></a></li>
                        <li class="col-4"><a href="#"><i class="fa-brands fa-linkedin"></i></a></li>
                        <li class="col-4"><a href="#"><i class="fa-brands fa-twitter"></i></a></li>
                        <li class="col-4"><a href="#"><i class="fa-brands fa-youtube"></i></a></li>
                        <li class="col-4"><a href="#"><i class="fa-brands fa-instagram-square"></i></a></li>
                    </ul>
                </div>
            </div>
        </footer>
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