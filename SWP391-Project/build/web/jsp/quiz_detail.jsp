<%-- 
    Document   : quiz_detail
    Created on : Jun 11, 2022, 3:50:28 PM
    Author     : May Tinh Ha Anh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quiz Detail</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="../css/quiz_detail.css">
        <style>
            body {
                width: 100%;
            }
            /*            .left {
                            float: left;
                            width: 22%;
                            height: 750px;
                            background-color: rgba(0,0,255,0.2);
                            padding: 5px;
                        }*/

            .right {
                /*float: right;*/
                width: 100%;
                /*height: 500px;*/
                /*background-color: green;*/
            }

            .container-quiz-block {
                padding: 2%;
                margin-left: 20%;
                margin-right: 20%;
                margin-bottom: 2%;
            }

            .brief-info span {
                font-weight: 500;
            }

            .exam-name, .total-of-questions {
                /*font-size: 20px;*/
                margin-right: 2%;
            }

            .separate {
                /*font-size: 24px;*/
                margin-right: 2%;
            }

            .quiz-des {
                margin-top: 5%;
                /*font-size: 20px;*/
            }

            .start-button {
                margin-top: 5%;
            }

            #navbarSupportedContent form input, #navbarSupportedContent form button{
                display: none;
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="quiz-detail-container" style="margin-top: 110px;">
                <div class="right">
                    <div class="container-quiz-block" style="background-color: rgba(0,0,255,0.1);">
                        <div class="test-name-block">
                            <h2 class="test-name">${quizInfo.qname}</h2>
                    </div>
                    <div class="path" style="<%--font-size: 24px;--%>">
                        <a href="home">Home</a>
                        <i class="fa-solid fa-angle-right mx-2"></i>
                        <a href="mycourse">My courses</a>
                        <i class="fa-solid fa-angle-right mx-2"></i>
                        <a href="section?cid=${quizInfo.cid}">${course.cname}</a>
                        <i class="fa-solid fa-angle-right mx-2"></i>
                        <a>${quizInfo.qname}</a>
                    </div>
                    <hr style="height:1px;border-width:0;color:black;background-color:black;">
                    <div class="brief-info">
                        <span class="exam-name">Progress Test</span>
                        <span class="separate">|</span>
                        <span class="total-of-questions">${numOfQuestion} Questions</span>
                    </div>
                    <div class="quiz-des">
                        <p>${quizInfo.qdesc}</p>
                    </div>
                    <div class="start-button">
                        <input class="btn btn-primary btn-sm" type="button" value="Start Test" onclick="StartTestFunction()" style="float: left; width: 10%;">
                        <input class="btn btn-primary btn-sm" type="button" value="Back" onclick="window.location = 'section?cid=${quizInfo.cid}'" style="float: right; width: 10%;">
                        <div style="clear: both;"></div>
                    </div>
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
                            function StartTestFunction() {
                                window.location = "quiz?cid=${quizInfo.cid}&qid=${quizInfo.qid}";
                            }
    </script>
</html>
