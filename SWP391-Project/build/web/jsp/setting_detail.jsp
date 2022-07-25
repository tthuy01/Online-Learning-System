<%-- 
    Document   : setting_detail
    Created on : Jul 13, 2022, 2:11:06 PM
    Author     : Tran Thi Thanh Thuy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Setting detail</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style type="text/css">
            <%@include file="../css/setting_detail.css"%>
            <%@include file="../css/setting.css"%>
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
            <div class="setting-detail-container">
                <div class="add-container" style="margin-bottom: 2%;">
                    <div class="setting-detail-title">
                        <div class="add-path" style="width: 60%; float: left;">
                            <h4><a href="settinglist">List of settings</a> > <a>Setting detail</a></h4>
                        </div>
                        <div class="action" style="width: 35%; float: right;">
                            <button type="button" class="btn btn-primary btn-sm" onclick="window.location = 'updatesetting?id=${id}'" style="float: right; margin-left: 5%;">Update</button>
                            <button type="button" class="btn btn-primary btn-sm" onclick="window.location = 'addnewsetting'" style="float: right;">Add new</button>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="add-form d-flex justify-content-center" style="margin-top: 1%;">
                        <table>
                            <tr>
                                <td><span>Setting ID</span></td>
                                <td>                 
                                    <input type="text" readonly="" name="id" value="${id}">
                            </td>
                        </tr>
                        <tr>
                            <td><span>Setting Type</span></td>
                            <td>                 
                                <input type="text" readonly="" name="type" value="${type}"> 
                            </td>
                        </tr>
                        <tr>
                            <td><span>Setting Value</span></td>
                            <td>                 
                                <input type="text" name="value" value="${value}" readonly="">
                            </td>
                        </tr>
                        <tr>
                            <td><span>Setting Order</span></td>
                            <td>                 
                                <input type="text" name="order" value="${order}" readonly="">
                            </td>
                        </tr>
                        <tr>
                            <td><span>Setting Status</span></td>
                            <td>                 
                                <input type="text" name="status" value="${status}" readonly="">
                            </td>
                        </tr>
                        <tr>
                            <td>Description</td>
                            <td>
                                <!--ckeditor-->
                                <textarea id="des" name="des" readonly="">
                                    ${des}
                                </textarea>
                            </td>
                        </tr>
                    </table>
                </div>
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
