<%-- 
    Document   : update_setting
    Created on : Jul 11, 2022, 3:13:02 PM
    Author     : Tran Thi Thanh Thuy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update setting</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style type="text/css">
            <%@include file="../css/add_new_setting.css"%>
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="add-container" style="margin-bottom: 2%;">
                <div class="add-path">
                    <h4><a href="settinglist">List of settings</a> > <a href="settingdetail?id=${id}">Setting detail</a> > Update setting</h4>
                </div>
                <div class="add-form d-flex justify-content-center" style="margin-top: 1%;">
                    <form action="updatesetting" method="post">
                        <c:if test="${ms != null}">
                        <div class="ms" style="color: green; margin-bottom: 2%; text-align: center;">
                            <b>${ms}</b>
                        </div>
                    </c:if>
                    <c:if test="${er != null}">
                        <div class="ms" style="color: red; margin-bottom: 2%; text-align: center;">
                            <b>${er}</b>
                        </div>
                    </c:if>
                    <table>
                        <c:set value="${type}" var="type"></c:set>
                        <c:set value="${value}" var="value"></c:set>
                        <c:set value="${order}" var="order"></c:set>
                        <c:set value="${status}" var="status"></c:set>
                        <c:set value="${des}" var="des"></c:set>
                        <tr>
                            <td><span>Setting ID</span></td>
                            <td>                 
                                <input type="text" readonly="" name="id" value="${id}">
                            </td>
                        </tr>
                        <tr>
                            <td><span>Setting Type</span></td>
                            <td>                 
                                <select name="type">
                                    <option value="0">Choose setting type</option>
                                    <c:forEach items="${listAllSettingType}" var="i">
                                        <option value="${i.settingTypeId}" ${type eq i.settingTypeId?"selected":""}>${i.settingTypeName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><span>Setting Value</span></td>
                            <td>                 
                                <input type="text" name="value" value="${value}">
                            </td>
                        </tr>
                        <tr>
                            <td><span>Setting Order</span></td>
                            <td>                 
                                <input type="text" name="order" value="${order}">
                            </td>
                        </tr>
                        <tr>
                            <td><span>Setting Status</span></td>
                            <td>                 
                                <select name="status">
                                    <option value="0">Choose setting status</option>
                                    <c:forEach items="${listAllSettingStatus}" var="i">
                                        <option value="${i.settingStatusId}" ${status eq i.settingStatusId?"selected":""}>${i.settingStatusName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <!--ckeditor-->
                                <textarea id="des" name="des">
                                    ${des}
                                </textarea>
                            </td>
                        </tr>
                    </table>
                    <div class="add-button d-flex justify-content-center" style="margin-top: 2%;">
                        <button type="button" class="btn btn-primary btn-sm" onclick="this.form.submit();">Update</button>
                    </div>
                </form>
            </div>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
    <script
        src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>
    <script src="ckeditor/ckeditor.js"></script>
    <script>
                            var des = CKEDITOR.replace('des');
    </script>
</html>
