<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My registration</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style type="text/css">
            <%@include file="../css/registration_list.css"%>
        </style>
        <script type="text/javascript">
            function doDelete(rid)
            {
//                if (rstatusId !== 1)
//                {
                if (confirm("Are you sure to delete registration with id = " + rid + "?"))
                {
                    window.location = "deleteregistration?rid=" + rid;
                }
//                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="header_my_registration.jsp"></jsp:include>
            <div class="registration-container" style="margin-top: 110px;">
                <div class="title-registration">
                    <h3 style="width: 100%;">My registrations</h3>
                </div>
                <div class="row" style="margin-top: 2%;">
                    <!--Sider-->
                    <div class="sider col-md-2">
                        <form class="searchAndFilter" id="searchAndFilter" action="myregistration" method="get">
                            <div class="search-block">
                                <!--                            <div class="title">
                                                                Search by Subject or Email
                                                            </div>-->
                                <div class="search-box d-flex">
                                    <input class="form-control mr-sm-2" style="width: 80%;" type="search" name="search" value="${search}" placeholder="Enter Subject or Course" aria-label="Search">
                                <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                                        class="fa-solid fa-magnifying-glass"></i> </button>
                            </div>
                        </div>

                        <div class="filter" style="margin-top: 5%;">
                            <c:set var="lsc" value="${listSubjectCategory}"></c:set>
                            <c:set var="ls" value="${listSubject}"></c:set>
                            <c:set var="lpp" value="${listPricePackage}"></c:set>
                            <c:set var="mp" value="${maxPrice}"></c:set>
                            <c:set var="lrs" value="${listRegistrationStatus}"></c:set>

                            <c:set var="cSubCate" value="${cSubCate}"></c:set>
                            <c:set var="cSubject" value="${cSubject}"></c:set>
                            <c:set var="cPricePackage" value="${cPricePackage}"></c:set>
                            <c:set var="cRegistrationStatus" value="${cRegistrationStatus}"></c:set>
                            <c:set var="cTotalCost" value="${cTotalCost}"></c:set>

                            <c:set var="sortBy" value="${sortBy}"></c:set>

                                <div class="sort-filter">
                                    <div class="title" style="float: left;">
                                        Sort <i class="fas fa-arrow-down"></i> by 
                                    </div>
                                    <div class="content-sort" style="float: left; margin-left: 6%;">
                                        <table>
                                            <tr>
                                                <td>
                                                    <select name="sort" onchange="document.getElementById('searchAndFilter').submit();">
                                                        <option value="" ${sortBy eq "" ? "selected":""}>Default</option>
                                                    <option value="id" ${sortBy eq "id" ? "selected":""}>ID</option>
                                                    <option value="registrationTime" ${sortBy eq "registrationTime" ? "selected":""}>Registration Time</option>
                                                    <option value="course" ${sortBy eq "course" ? "selected":""}>Course</option>
                                                    <option value="subject" ${sortBy eq "subject" ? "selected":""}>Subject</option>
                                                    <option value="package" ${sortBy eq "package" ? "selected":""}>Package</option>
                                                    <option value="totalCost" ${sortBy eq 'totalCost' ? "selected":""}>Total cost</option>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>

                            <div class="clear"></div>

                            <div class="sub-filter">
                                <div class="title">
                                    Subject Category
                                </div>
                                <div class="content-filter">
                                    <table>
                                        <c:if test="${lsc.size() > 0}">
                                            <c:forEach begin="0" end="${lsc.size()-1}" var="i">
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" value="${lsc.get(i).subjectCateId}" name="subCate" id="subjectCaterogy"
                                                               onclick="this.form.submit()" ${cSubCate[i]?"checked":""}>
                                                        <span>${lsc.get(i).subjectCateName}</span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                </div>
                            </div>

                            <div class="sub-filter">
                                <div class="title">
                                    Subject
                                </div>
                                <div class="content-filter">
                                    <table>
                                        <c:if test="${ls.size() > 0}">
                                            <c:forEach begin="0" end="${ls.size() - 1}" var="i">
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" value="${ls.get(i).sid}" name="subject" id="subject"
                                                               onclick="this.form.submit()" ${cSubject[i]?"checked":""}>
                                                        <span>${ls.get(i).sname}</span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                </div>
                            </div>

                            <div class="sub-filter">
                                <div class="title">
                                    Package
                                </div>
                                <div class="content-filter">
                                    <table>
                                        <c:if test="${lpp.size() > 0}">
                                            <c:forEach begin="0" end="${lpp.size()-1}" var="i">
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" value="${lpp.get(i).packid}" name="package" id="package"
                                                               onclick="this.form.submit()" ${cPricePackage[i]?"checked":""}>
                                                        <span>${lpp.get(i).packname}</span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                </div>
                            </div>

                            <div class="sub-filter">
                                <div class="title">
                                    Total cost
                                </div>
                                <div class="content-filter">
                                    <table>
                                        <c:forEach begin="0" end="4" var="i">
                                            <tr>
                                                <td>
                                                    <input type="checkbox" value="${Math.ceil((mp/5)*i)} and ${Math.ceil((mp/5)*(i+1))}" name="totalCost" id="totalCost"
                                                           onclick="this.form.submit()" ${cTotalCost[i]?"checked":""}>
                                                    <span>${Math.ceil((mp/5)*i)}$ - ${Math.ceil((mp/5)*(i+1))}$</span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </div>

                            <div class="sub-filter">
                                <div class="title">
                                    Status
                                </div>
                                <div class="content-filter">
                                    <table>
                                        <c:if test="${lrs.size() > 0}">
                                            <c:forEach begin="0" end="${lrs.size() - 1}" var="i">
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" value="${lrs.get(i).rstatusId}" name="status" id="status"
                                                               onclick="this.form.submit()" ${cRegistrationStatus[i]?"checked":""}>
                                                        <span>${lrs.get(i).rstatusName}</span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                </div>
                            </div>
                            
                            <div class="clear-filter d-flex justify-content-center" style="margin-top: 5%;">
                                <button type="button" class="btn btn-primary btn-sm" onclick="window.location = 'myregistration'">Clear filter</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!--Content-->
                <div class="content col-md-10">
                    <c:set var="lr" value="${listRegistration}"></c:set>
                    <c:set var="lcn" value="${listCourseName}"></c:set>
                    <c:set var="lsn" value="${listSubjectName}"></c:set>
                    <c:set var="lpn" value="${listPackageName}"></c:set>
                    <c:set var="ltp" value="${listTotalPrice}"></c:set>
                    <c:set var="lvt" value="${listValidTo}"></c:set>
                    <c:set var="lu" value="${listUser}"></c:set>
                        <table class="table table-striped">
                            <tr>
                                <th>ID</th>
                                <th>Course</th>
                                <th>Subject</th>
                                <th>Registration time</th>
                                <th>Package</th>
                                <th>Total cost</th>
                                <th>Status</th>
                                <th>Valid from</th>
                                <th>Valid to</th>
                                <th>Action</th>
                            </tr>
                        <c:if test="${lr.size() > 0}">
                            <c:forEach begin="0" end="${lr.size()-1}" var="i">
                                <tr>
                                    <td>${lr.get(i).rid}</td>
                                    <td>${lcn.get(i)}</td>
                                    <td>${lsn.get(i)}</td>
                                    <td>${lr.get(i).rtime}</td>
                                    <td>${lpn.get(i)}</td>
                                    <td>${ltp.get(i)}</td>
                                    <td>
                                        <c:if test="${lr.get(i).rstatusId == 0}">
                                            Submitted
                                        </c:if>
                                        <c:if test="${lr.get(i).rstatusId == 1}">
                                            Registered
                                        </c:if>
                                    </td>
                                    <td>${lr.get(i).rtime}</td>
                                    <td>${lvt.get(i)}</td>
                                    <td>
                                        <c:if test="${lr.get(i).rstatusId == 0}">
                                            <c:if test="${lpn.get(i) ne 'Free'}">
                                                <a href="coursedetail?cid=${lr.get(i).cid}&eidtRegistration=1">Edit</a><!--Gui yeu cau Edit-->
                                            </c:if>
                                            <!--<a href="coursedetail?cid=${lr.get(i).cid}">Edit</a>-->
                                            <a href="#" onclick="doDelete('${lr.get(i).rid}')">Cancel</a>
                                        </c:if>
                                        <c:if test="${lr.get(i).rstatusId == 1}">

                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>

                    <c:if test="${requestScope.num == 0}">
                        <div style="text-align: left; font-size: 20px;">
                            Not found the registration!
                            <c:if test="${requestScope.ms != null}">
                                <span style="color: red;">(${requestScope.ms})</span>
                            </c:if>
                        </div>
                    </c:if> 

                    <!--Paging-->
                    <div class="paging-block d-flex justify-content-center" style="margin-top: 5%;">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <c:set var="page" value="${requestScope.page}"></c:set>
                                <c:set var="subCates" value="${subCates}"></c:set>
                                <c:set var="subjects" value="${subjects}"></c:set>
                                <c:set var="packs" value="${packs}"></c:set>
                                <c:set var="statuses" value="${statuses}"></c:set>
                                <c:set var="costs" value="${costs}"></c:set>
                                <c:if test="${requestScope.num != 0}">
                                    <c:if test="${search != null}">
                                        <li class="page-item ${page==1?"disabled":""}"><a class="page-link" href="myregistration?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&page=${page-1}">Previous</a></li>
                                            <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                            <li class="page-item ${i==page?"active":""}"><a class="page-link" href="myregistration?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&page=${i}">${i}</a></li>
                                            </c:forEach>
                                        <li class="page-item ${page==num?"disabled":""}"><a class="page-link" href="myregistration?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&page=${page+1}">Next</a></li>
                                        </c:if>
                                        <c:if test="${search == null}">
                                        <li class="page-item ${page==1?"disabled":""}"><a class="page-link" href="myregistration?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&page=${page-1}">Previous</a></li>
                                            <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                            <li class="page-item ${i==page?"active":""}"><a class="page-link" href="myregistration?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&page=${i}">${i}</a></li>
                                            </c:forEach>
                                        <li class="page-item ${page==num?"disabled":""}"><a class="page-link" href="myregistration?search=${search}<c:forEach items="${subCates}" var="scs">&subCate=${scs}</c:forEach><c:forEach items="${subjects}" var="sjs">&subject=${sjs}</c:forEach><c:forEach items="${packs}" var="pas">&package=${pas}</c:forEach><c:forEach items="${costs}" var="cos">&totalCost=${cos}</c:forEach><c:forEach items="${statuses}" var="sts">&status=${sts}</c:forEach>&sort=${sortBy}&page=${page+1}">Next</a></li>
                                        </c:if>
                                    </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
