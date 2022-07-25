<%-- 
    Document   : setting
    Created on : Jul 7, 2022, 3:20:59 PM
    Author     : Tran Thi Thanh Thuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Setting</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style type="text/css">
            <%@include file="../css/header.css"%>
            <%@include file="../css/setting.css"%>
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="setting-list-container">
                <div class="setting-title">
                    <h4 style="width: 80%; float: left;">List of settings</h4>
                    <div class="addButton" style="float: right;">
                        <button type="button" class="btn btn-primary" onclick="window.location = 'addnewsetting'">Add new setting</button>
                    </div>                    
                    <div class="clear"></div>
                </div>
                <div class="row" style="margin-top: 2%;">
                    <!--Sider-->
                    <div class="sider col-md-2">
                        <form class="searchAndFilter" id="searchAndFilter" action="settinglist" method="get">
                            <!--Search-->
                            <div class="search-block">
                                <div class="search-box d-flex">
                                    <input class="form-control mr-sm-2" style="width: 80%;" type="search" name="search" value="${search}" placeholder="Enter order or value " aria-label="Search">
                                <button class="btn btn-outline-primary my-2 my-sm-0" type="submit"><i
                                        class="fa-solid fa-magnifying-glass"></i> </button>
                            </div>
                        </div>
                        <!--Filter-->
                        <div class="filter" style="margin-top: 5%;">
                            <!--Sort-->
                            <div class="sort-filter">
                                <div class="title" style="float: left;">
                                    Sort <i class="fas fa-arrow-down"></i> by 
                                </div>
                                <div class="content-sort" style="float: left; margin-left: 11%;">
                                    <table>
                                        <tr>
                                            <td>
                                                <select name="sort" onchange="document.getElementById('searchAndFilter').submit();">
                                                    <option value="" ${sortBy eq "" ? "selected":""}>Default</option>
                                                    <option value="id" ${sortBy eq "id" ? "selected":""}>Setting ID</option>
                                                    <option value="type" ${sortBy eq "type" ? "selected":""}>Setting type</option>
                                                    <option value="value" ${sortBy eq "value" ? "selected":""}>Setting value</option>
                                                    <option value="order" ${sortBy eq "order" ? "selected":""}>Setting order</option>
                                                    <option value="status" ${sortBy eq "status" ? "selected":""}>Setting status</option>
                                                </select>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="clear"></div>
                            </div>

                            <!--Sub filter-->
                            <c:set var="lst" value="${requestScope.listSettingType}"></c:set>
                            <c:set var="lss" value="${requestScope.listSettingStatus}"></c:set>
                            <c:set var="num" value="${requestScope.num}"></c:set>
                            <c:set var="page" value="${requestScope.page}"></c:set>
                            <c:set var="search" value="${requestScope.search}"></c:set>
                            <c:set var="sortBy" value="${requestScope.sortBy}"></c:set>
                            <c:set var="typeId" value="${requestScope.typeId}"></c:set>
                            <c:set var="statusId" value="${requestScope.statusId}"></c:set>
                            <c:set var="ls" value="${requestScope.listSetting}"></c:set>
                            <c:set var="listSettingTypeBySettingList" value="${requestScope.listSettingTypeBySettingList}"></c:set>
                            <c:set var="listSettingStatusBySettingList" value="${requestScope.listSettingStatusBySettingList}"></c:set>
                            <c:set var="cTypeId" value="${requestScope.cTypeId}"></c:set>
                            <c:set var="cStatusId" value="${requestScope.cStatusId}"></c:set>

                                <!--Setting type-->
                                <div class="sub-filter">
                                    <div class="title">
                                        Setting type
                                    </div>
                                    <div class="content-filter">
                                        <table>
                                        <c:if test="${lst.size() > 0}">
                                            <c:forEach begin="0" end="${lst.size()-1}" var="i">
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" value="${lst.get(i).settingTypeId}" name="type" id="type"
                                                               onclick="this.form.submit()" ${cTypeId[i]?"checked":""}>
                                                        <span>${lst.get(i).settingTypeName}</span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                </div>
                            </div>

                            <!--Setting status-->
                            <div class="sub-filter">
                                <div class="title">
                                    Setting status
                                </div>
                                <div class="content-filter">
                                    <table>
                                        <c:if test="${lss.size()>0}">
                                            <c:forEach begin="0" end="${lss.size()-1}" var="i">
                                                <tr>
                                                    <td>
                                                        <input type="checkbox" value="${lss.get(i).settingStatusId}" name="status" id="status"
                                                               onclick="this.form.submit()" ${cStatusId[i]?"checked":""}>
                                                        <span>${lss.get(i).settingStatusName}</span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                </div>
                            </div>

                            <!--Clear filter-->
                            <div class="clear-filter d-flex justify-content-center" style="margin-top: 5%;">
                                <button type="button" class="btn btn-primary btn-sm" onclick="window.location = 'settinglist'">Clear filter</button>
                            </div>
                        </div>
                    </form>
                </div>
                <!--Content-->
                <div class="content col-md-10">
                    <c:if test="${size > 0}">
                        <c:if test="${ms != null}">
                            <div style="color: green; text-align: center; margin-bottom: 2%;">
                                <b>${ms}</b>
                            </div>
                        </c:if>
                        <c:if test="${er != null}">
                            <div style="color: red; text-align: center; margin-bottom: 2%;">
                                <b>${er}</b>
                            </div>
                        </c:if>
                        <table class="table table-striped border">
                            <tr>
                                <th>ID</th>
                                <th>Type</th>
                                <th>Value</th>
                                <th>Order</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                            <c:if test="${ls.size() > 0}">
                                <c:forEach begin="0" end="${ls.size()-1}" var="i">
                                    <tr>
                                        <td>${ls.get(i).settingId}</td>
                                        <td>${listSettingTypeBySettingList.get(i).settingTypeName}</td>
                                        <td>${ls.get(i).settingValue}</td>
                                        <td>${ls.get(i).settingOrder}</td>
                                        <td>${listSettingStatusBySettingList.get(i).settingStatusName} <i class="fa fa-exchange text-primary" onclick="doChange('${ls.get(i).settingId}', '${ls.get(i).settingStatusId}')" style="cursor: pointer; margin-left: 4%;"></i></td>
                                        <td><a href="settingdetail?id=${ls.get(i).settingId}" style="margin-right: 4%;">View detail</a>|<a href="#" style="margin-left: 4%; margin-right: 4%; display: none;">View</a>|<a href="updatesetting?id=${ls.get(i).settingId}" style="margin-left: 4%;">Edit</a></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>

                        <!--Paging-->
                        <div class="paging-block d-flex justify-content-center" style="margin-top: 4%; margin-bottom: 1%;">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination">
                                    <c:if test="${requestScope.num != 0}">
                                        <c:if test="${search != null}">
                                            <li ${page==1?"style='display:none;'":""} class="page-item ${page==1?"disabled":""}"><a class="page-link" href="settinglist?search=${search}&sort=${sortBy}<c:forEach items="${typeId}" var="ti">&type=${ti}</c:forEach><c:forEach items="${statusId}" var="si">&status=${si}</c:forEach>&page=${page-1}">Previous</a></li>
                                                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="settinglist?search=${search}&sort=${sortBy}<c:forEach items="${typeId}" var="ti">&type=${ti}</c:forEach><c:forEach items="${statusId}" var="si">&status=${si}</c:forEach>&page=${i}">${i}</a></li>
                                                </c:forEach>
                                            <li ${page==num?"style='display:none;'":""} class="page-item ${page==num?"disabled":""}"><a class="page-link" href="settinglist?search=${search}&sort=${sortBy}<c:forEach items="${typeId}" var="ti">&type=${ti}</c:forEach><c:forEach items="${statusId}" var="si">&status=${si}</c:forEach>&page=${page+1}">Next</a></li>
                                            </c:if>
                                            <c:if test="${search == null}">
                                            <li ${page==1?"style='display:none;'":""} class="page-item ${page==1?"disabled":""}"><a class="page-link" href="settinglist?search=${search}&sort=${sortBy}<c:forEach items="${typeId}" var="ti">&type=${ti}</c:forEach><c:forEach items="${statusId}" var="si">&status=${si}</c:forEach>&page=${page-1}">Previous</a></li>
                                                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                                <li class="page-item ${i==page?"active":""}"><a class="page-link" href="settinglist?search=${search}&sort=${sortBy}<c:forEach items="${typeId}" var="ti">&type=${ti}</c:forEach><c:forEach items="${statusId}" var="si">&status=${si}</c:forEach>&page=${i}">${i}</a></li>
                                                </c:forEach>
                                            <li ${page==num?"style='display:none;'":""} class="page-item ${page==num?"disabled":""}"><a class="page-link" href="settinglist?search=${search}&sort=${sortBy}<c:forEach items="${typeId}" var="ti">&type=${ti}</c:forEach><c:forEach items="${statusId}" var="si">&status=${si}</c:forEach>&page=${page+1}">Next</a></li>
                                            </c:if>
                                        </c:if>
                                </ul>
                            </nav>
                        </div>
                    </c:if>
                    <c:if test="${size == 0}">
                        <h4>Settings not found</h4>
                    </c:if>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
        </body>
        <script type="text/javascript">
            function doChange(settingId, settingStatusId) {
                if (confirm("Are you sure to change setting status of setting " + settingId + "?"))
                {
                    //        window.location = 'changesettingstatus?settingId=${ls.get(i).settingId}&settingStatusId=${ls.get(i).settingStatusId}';
                    window.location = 'changesettingstatus?settingId=' + settingId + '&settingStatusId=' + settingStatusId;
                }
            }
    </script>
</html>
