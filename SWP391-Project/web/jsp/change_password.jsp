<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>Home</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
        integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <style>
        .form-container {
            width: 400px;
            height: 600px;
            position: relative;
            text-align: center;
            padding: 20px 0;
            margin: auto;
            /* box-shadow: 0 0 20px 0px rgba(0, 0, 0, 0.1); */
        }

        .form-container span {
            font-weight: bold;
            padding: 0 10px;
            color: #555;
            cursor: pointer;
            width: 200px;
            display: inline-block;
        }

        .form-container form {
            max-width: 400px;
            padding: 0 20px;
            position: absolute;
            top: 120px;
            transition: transform 1s;
        }

        #RegForm input {
            width: 100%;
            height: 50px;
            margin: 10px 0;
            padding: 0 10px;
            border: 1px solid #ccc;
        }


        #Indicator {
            width: 100px;
            border: none;
            background: blueviolet;
            height: 3px;
            margin-top: 8px;
        }
    </style>
</head>

<body style="background-color: #0056d2">
                <div class="modal-body">
                    <div class="account-page">
                        <div class="container">
                            <div class="row">
                                <div class="col-12">
                                    <div class="form-container" style="background-color: white">
                                        <div class="form-bt">
                                            <!-- <span onclick="login()">Login</span> -->
                                            <span>Change Password</span>
                                            
                                            <hr id="Indicator">
                                            <h7 style="color: red">${requestScope.error}</h7>
                                        </div>
                                        <form id="RegForm" style="text-align: left;" action="changepassword" method="post">
                                            OLD PASSWORD
                                            <input type="password" name="oldpass" placeholder="Enter your old password" required>
                                            NEW PASSWORD
                                            <input type="password" name="newpass" placeholder="Enter your new password" required>
                                            CONFIRM NEW PASSWORD
                                            <input type="password" name="cfpass" placeholder="Confirm new password" required>
                                            <input type="submit" value="Change now">
                                            <input type="button" onclick="Home()" value="Home">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script >
            function Home(){
                window.location = "home";
            }
        </script>
   
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
