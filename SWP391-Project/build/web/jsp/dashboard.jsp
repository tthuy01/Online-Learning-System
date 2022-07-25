<%-- 
    Document   : dashboard
    Created on : Jun 26, 2022, 5:21:55 PM
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
            <%@include file="../css/dashboard.css"%>
        </style>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        <script type="text/javascript">
            google.charts.load("current", {packages: ["corechart"]});
            google.charts.setOnLoadCallback(drawChart);
            function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Subject Category', '$'],
            <c:set var="sum" value="0"/>
            <c:forEach items="${listSubCatePro}" var="scp">
                    ['${scp.subjectCateName}',${scp.subjectCateProfit}],
                <c:set var="sum" value="${sum+scp.subjectCateProfit}"/>
            </c:forEach>
                ]);
                var options = {
                    title: 'Total revenue: ${sum} $',
                    is3D: true,
                };
                var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
                chart.draw(data, options);
            }
        </script>
    </head>

    <body>
        <section id="wrapper-dashboard" class="row container-fluid mx-auto px-0">
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
                            <li style="background-color: #425164;">
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
                        <c:if test="${sessionScope.user.rid==6}">
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
                        <li>
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
                        </c:if>
                        <li>
                            <a href="sliderlist">
                                <i class="fa fa-dashboard"></i>
                                <span>Sliders List</span>
                            </a>
                        </li>
                        <c:if test="${sessionScope.user.rid==6}">
                            <li >
                                <a href="userlist">
                                    <i class="fa fa-dashboard"></i>
                                    <span>User List</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>

            </div>
            <div id="right" class="col-lg-10 col-md-8 col-12">
                <!--header navbar-->
                <jsp:include page="../jsp/header_admin.jsp" />
                <div class="row mt-3 mx-auto">
                    <div class="col-lg-3 col-md-6 col-12">
                        <div class="d-flex justify-content-start p-4" style="background-color: white;border-radius: 4px;">
                            <div class="mr-2">
                                <span style="font-size: 30px;background-color: #F05050;border-radius: 4px;"
                                      class="py-2 px-3">
                                    <i style="color: white;" class="fa fa-globe"></i>
                                </span>
                            </div>
                            <div style="font-size: 12px;margin-top: -6px;">
                                <span style="font-size: 24px;font-weight: 500;">${numSub}</span><br />
                                Total Categories
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-12">
                        <div class="d-flex justify-content-start p-4" style="background-color: white;border-radius: 4px;">
                            <div class="mr-2">
                                <span style="font-size: 30px;background-color: #7266ba;border-radius: 4px;"
                                      class="py-2 px-3">
                                    <i style="color: white;" class="fa fa-book"></i>
                                </span>
                            </div>
                            <div style="font-size: 12px;margin-top: -6px;">
                                <span style="font-size: 24px;font-weight: 500;">${numCourse}</span><br />
                                Total Subjects
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-12">
                        <div class="d-flex justify-content-start p-4" style="background-color: white;border-radius: 4px;">
                            <div class="mr-2">
                                <span style="font-size: 30px;background-color: #23b7e5;border-radius: 4px;"
                                      class="py-2 px-3">
                                    <i style="color: white;" class="fa fa-dollar"></i>
                                </span>
                            </div>
                            <div style="font-size: 12px;margin-top: -6px;">
                                <span style="font-size: 24px;font-weight: 500;">${totalProfit}</span><br />
                                Total Profit
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 col-12">
                        <div class="d-flex justify-content-start p-4" style="background-color: white;border-radius: 4px;">
                            <div class="mr-2">
                                <span style="font-size: 30px;background-color: #27C24C;border-radius: 4px;"
                                      class="py-2 px-3">
                                    <i style="color: white;" class="fa fa-paperclip"></i>
                                </span>
                            </div>
                            <div style="font-size: 12px;margin-top: -6px;">
                                <span style="font-size: 24px;font-weight: 500;">${numLess}</span><br />
                                Total Lessons
                            </div>
                        </div>
                    </div>
                </div>
                <div class="pt-4 mx-auto" style="text-align: center">                    
                    <form action="dashboard">
                        <input hidden id="side" name="side" type="text" value="${side}"/>
                        <input hidden type="text" name="statby" value="${statby}"/>
                        <input hidden type="text" name="viewby" value="${viewby}"/>
                        <b>From:</b> <input type="date" name="from" id="datePicker1"
                                            style="width: 200px; margin-right: 30px;" value="${from}">
                        <b>To:</b> <input type="date" name="to" id="datePicker2" style="width: 200px;margin-right: 20px;" value="${to}">
                        <input type="submit" value="Search">
                    </form>
                    <p id="last" class="mt-3" style="text-transform: uppercase;font-weight: 700">Statistics for the last 7 days</p>
                </div>
                <div class="row mt-4">
                    <div class="row container-fluid mx-auto">
                        <div class="col-lg-8 col-12">
                            <header
                                style="padding: 10px 15px;text-transform: uppercase;border-bottom: 1px solid #eee;background-color: #FAFAFA;" class="d-flex justify-content-between">
                                <b>the trend of order</b>
                                <div style="text-transform: none">
                                    Statistics by&nbsp;
                                    <select name="statby" id="mySelect" onchange="myChange()">
                                        <option ${statby=='day'?"selected":""} value="day">Day</option>
                                        <option ${statby=='month'?"selected":""} value="month">Month</option>
                                        <option ${statby=='year'?"selected":""} value="year">Year</option>
                                    </select>
                                </div>
                            </header>
                            <div style="background-color:white;overflow:hidden">
                                <canvas id="myChart" style="width:100%;max-width:992px;min-height: 500px"></canvas>
                            </div>
                        </div>
                        <div class="col-lg-4 col-12">
                            <div style="background-color:white;">
                                <header
                                    style="padding: 7px 15px;text-transform: uppercase;border-bottom: 1px solid #eee;background-color: #FAFAFA;" class="d-flex justify-content-between">
                                    <span><b>New Subjects</b> <span id="header3" style="text-transform: none;">(${listSub.size()} results)</span></span>
                                    <input type="text" style="width: 160px;margin-right: -15px" id="myInput3" onkeyup="myFunction3()" placeholder="Search for all..">
                                </header>
                                <div style="background-color:white;text-align: center;max-height: 500px;overflow-y: scroll;" class="border-bottom">
                                    <table id="myTable3" class="table table-striped table-bordered" style="text-align: center;">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Subject Name</th>
                                                <th scope="col">Public date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="count" value="0" />
                                            <c:forEach items="${listSub}" var="listSub">
                                                <c:set var="count" value="${count+1}" />
                                                <tr class="dataSub">
                                                    <th scope="row">${count}</th>
                                                    <td>${listSub.cname}</td>
                                                    <td>${listSub.cpublic}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="row container-fluid mx-auto">
                        <div class="col-lg-8 col-12">
                            <header 
                                style="padding: 7px 15px;text-transform: uppercase;border-bottom: 1px solid #eee;background-color: #FAFAFA;" class="d-flex justify-content-between">
                                <span><b>New Registrations</b> <span id="header1" style="text-transform: none;">(Showing ${listNewReg.size()} results)</span></span>
                                <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for all..">
                            </header>
                            <div style="background-color:white;text-align: center;height: 285px;overflow-y: scroll;" class="border-bottom">
                                <table id="myTable" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Full Name</th>
                                            <th scope="col">Course Name</th>
                                            <th scope="col">Registration Time</th>
                                            <th scope="col">Registration status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="count" value="0" />
                                        <c:forEach items="${listNewReg}" var="lnr">
                                            <c:set var="count" value="${count+1}" />
                                            <tr>
                                                <th scope="row">${count}</th>
                                                <td>${lnr.ufullname}</td>
                                                <td>${lnr.cname}</td>
                                                <td>${lnr.rtime}</td>
                                                <td style="color:${lnr.regStatus=='Registered'?'blue':'red'};font-weight: 700">${lnr.regStatus=='Registered'?'Success':lnr.regStatus}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-lg-4 col-12">
                            <div style="background-color:white;text-align: center">
                                <header
                                    style="padding: 10px 15px;text-transform: uppercase;border-bottom: 1px solid #eee;background-color: #FAFAFA;" class="d-flex justify-content-between">
                                    <b>Revenues by subject categories</b>
                                    <select name="viewby" id="viewby" style="margin-right: -15px" onchange="myChange()">
                                        <option ${viewby=='0'?"selected":""} value="0">Cate</option>
                                        <option ${viewby=='1'?"selected":""} value="1" >Sub-Cate</option>
                                    </select>
                                </header>
                                <div id="piechart_3d" style="height: 285px;max-width: 467px;overflow: hidden;"></div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="row container-fluid mx-auto">
                        <div class="col-lg-6 col-12">
                            <header
                                style="padding: 7px 15px;text-transform: uppercase;border-bottom: 1px solid #eee;background-color: #FAFAFA;" class="d-flex justify-content-between">
                                <span><b>Newly Registered</b> <span id="header4" style="text-transform: none">(Showing ${listUser.size()} results)</span></span>
                                <input type="text" id="myInput4" onkeyup="myFunction4()" placeholder="Search for all..">
                            </header>
                            <div style="background-color:white;text-align: center;height: 346px;overflow-y: scroll;">
                                <table id="myTable4" class="table table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Image</th>
                                            <th scope="col">Full Name</th>
                                            <th scope="col">Address</th>
                                            <th scope="col">Register Time</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="count" value="0" />
                                        <c:forEach items="${listUser}" var="lu">
                                            <c:set var="count" value="${count+1}" />
                                            <tr>
                                                <th scope="row">${count}</th>
                                                <td><img src="images/avatar/${lu.uimg}" class="img-fluid" style="width: 50px;border-radius:50% "/></td>
                                                <td>${lu.ufullname}</td>
                                                <td>${lu.uaddress}</td>
                                                <td>${lu.utime}</td>
                                            </tr>    
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-lg-6 col-12">
                            <div style="background-color:white;">
                                <header
                                    style="padding: 7px 15px;text-transform: uppercase;border-bottom: 1px solid #eee;background-color: #FAFAFA;" class="d-flex justify-content-between">
                                    <span><b>NEWLY BOUGHT</b> <span id="header2" style="text-transform: none"></span></span>
                                    <input type="text" id="myInput2" onkeyup="myFunction2()" placeholder="Search for all..">
                                </header>
                                <div style="background-color:white;text-align: center;height: 346px;overflow-y: scroll;" class="border-bottom">
                                    <table id="myTable2" class="table table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Image</th>
                                                <th scope="col">Full Name</th>
                                                <th scope="col">Course Name</th>
                                                <th scope="col">Package</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:set var="count" value="0" />
                                            <c:forEach items="${listNewReg}" var="lnr">
                                                <c:if test="${lnr.regStatus=='Registered'}">
                                                    <c:set var="count" value="${count+1}" />
                                                    <tr>
                                                        <th scope="row">${count}</th>
                                                        <td><img src="images/avatar/${lnr.uimg}" class="img-fluid" style="width: 50px;border-radius:50% "/></td>
                                                        <td>${lnr.ufullname}</td>
                                                        <td>${lnr.cname}</td>
                                                        <td style="font-weight: 700;color:<c:if test="${lnr.packname=='Free'}">green</c:if>
                                                            <c:if test="${lnr.packname=='Silver'}">#7c7c7c</c:if><c:if test="${lnr.packname=='Gold'}">#ff9900</c:if>
                                                            <c:if test="${lnr.packname=='Diamond'}">violet</c:if>">${lnr.packname}</td>
                                                        </tr>
                                                </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

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
    <script src="/js/dashboard.js"></script>
    <script type="text/javascript">
                                        document.getElementById("header2").innerHTML = "(Showing " + (document.getElementById("myTable2").getElementsByTagName("tr").length - 1) + " results)";
                                        function myFunction() {
                                            var input, filter, table, tr, tdx, i, txtValue, j, td;
                                            input = document.getElementById("myInput");
                                            filter = input.value.toUpperCase();
                                            table = document.getElementById("myTable");
                                            tr = table.getElementsByTagName("tr");
                                            var count = 0;
                                            for (i = 0; i < tr.length; i++) {
                                                tdx = tr[i].getElementsByTagName("td");
                                                for (j = 0; j < tdx.length; j++) {
                                                    td = tdx[j];
                                                    if (td) {
                                                        txtValue = td.textContent || td.innerText;
                                                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                                            tr[i].style.display = "";
                                                            count++
                                                            break;
                                                        } else {
                                                            tr[i].style.display = "none";
                                                        }
                                                    }
                                                }
                                            }
                                            document.getElementById("header1").innerHTML = "(Showing " + count + " results)";
                                        }

                                        function myFunction2() {
                                            var input, filter, table, tr, tdx, i, txtValue, j, td;
                                            input = document.getElementById("myInput2");
                                            filter = input.value.toUpperCase();
                                            table = document.getElementById("myTable2");
                                            tr = table.getElementsByTagName("tr");
                                            var count = 0;
                                            for (i = 0; i < tr.length; i++) {
                                                tdx = tr[i].getElementsByTagName("td");
                                                for (j = 0; j < tdx.length; j++) {
                                                    td = tdx[j];
                                                    if (td) {
                                                        txtValue = td.textContent || td.innerText;
                                                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                                            tr[i].style.display = "";
                                                            count++;
                                                            break;
                                                        } else {
                                                            tr[i].style.display = "none";
                                                        }
                                                    }
                                                }
                                            }
                                            document.getElementById("header2").innerHTML = "(Showing " + count + " results)";
                                        }

                                        function myFunction3() {
                                            var input, filter, table, tr, tdx, i, txtValue, j, td;
                                            input = document.getElementById("myInput3");
                                            filter = input.value.toUpperCase();
                                            table = document.getElementById("myTable3");
                                            tr = table.getElementsByTagName("tr");
                                            var count = 0;
                                            for (i = 0; i < tr.length; i++) {
                                                tdx = tr[i].getElementsByTagName("td");
                                                for (j = 0; j < tdx.length; j++) {
                                                    td = tdx[j];
                                                    if (td) {
                                                        txtValue = td.textContent || td.innerText;
                                                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                                            tr[i].style.display = "";
                                                            count++;
                                                            break;
                                                        } else {
                                                            tr[i].style.display = "none";
                                                        }
                                                    }
                                                }
                                            }
                                            document.getElementById("header3").innerHTML = "(" + count + " results)";
                                        }

                                        function myFunction4() {
                                            var input, filter, table, tr, tdx, i, txtValue, j, td;
                                            input = document.getElementById("myInput4");
                                            filter = input.value.toUpperCase();
                                            table = document.getElementById("myTable4");
                                            tr = table.getElementsByTagName("tr");
                                            var count = 0;
                                            for (i = 0; i < tr.length; i++) {
                                                tdx = tr[i].getElementsByTagName("td");
                                                for (j = 0; j < tdx.length; j++) {
                                                    td = tdx[j];
                                                    if (td) {
                                                        txtValue = td.textContent || td.innerText;
                                                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                                                            tr[i].style.display = "";
                                                            count++;
                                                            break;
                                                        } else {
                                                            tr[i].style.display = "none";
                                                        }
                                                    }
                                                }
                                            }
                                            document.getElementById("header4").innerHTML = "(Showing " + count + " results)";
                                        }

                                        var from = document.getElementById('datePicker1').valueAsDate;
                                        var to = document.getElementById('datePicker2').valueAsDate;
                                        const c = new Date();
                                        c.setUTCHours(0, 00, 00);
                                        var range = (to - from) / (24 * 60 * 60 * 1000);
                                        if (!(to == c.toString() && range == 6)) {
                                            document.getElementById('last').style.display = "none";
                                        }
        <c:if test="${side == 'hide'}">
                                        document.getElementById('left').style.display = "none";
                                        right.classList.replace("col-lg-10", "col-lg-12");
        </c:if>
    </script>
    <script type="text/javascript">
//        var xValues = [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000];
        var xValues = [<c:forEach items="${listdate}" var="listdate">'${listdate}',</c:forEach>];
        new Chart("myChart", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
                        label: "Waitting",
                        fillColor: "rgba(220,220,220,0.2)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        borderColor: "red",
                        pointHighlightStroke: "rgba(220,220,220,1)",
                        data: [<c:forEach items="${submitteds}" var="submitteds">${submitteds},</c:forEach>]
//                        data: [860, 1140, 1060, 1060, 1070, 1110, 1330, 2210, 7830, 2478]
                    }, {
                        label: "Success",
                        fillColor: "rgba(151,187,205,0.2)",
                        strokeColor: "rgba(151,187,205,1)",
                        pointColor: "rgba(151,187,205,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        borderColor: "blue",
                        pointHighlightStroke: "rgba(151,187,205,1)",
                        data: [<c:forEach items="${registereds}" var="registereds">${registereds},</c:forEach>]
//                        data: [300, 700, 2000, 5000, 6000, 4000, 2000, 1000, 200, 100]
                    }]
            },
            options: {
                legend: {display: true},
                title: {
                    display: true,
//                    text: "The trend of order counts"
                }
            }
        });
    </script>

    <script type="text/javascript">
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
        function myChange() {
            var x = document.getElementById("mySelect").value;
            var y = document.getElementById("viewby").value;
            window.location = "dashboard?side=" + document.getElementById('side').value + "&statby=" + x + "&viewby=" + y + "&from="
                    + document.getElementById('datePicker1').value + "&to=" + document.getElementById('datePicker2').value;
        }
    </script>
</html>
