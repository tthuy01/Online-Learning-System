// for register screen
// var LoginForm = document.getElementById("LoginForm");
// var RegForm = document.getElementById("RegForm");
// var Indicator = document.getElementById("Indicator");

// function register() {
//     RegForm.style.transform = "translateX(0px)";
//     LoginForm.style.transform = "translateX(0px)";
//     Indicator.style.transform = "translateX(50px)";
// }
// function login() {
//     RegForm.style.transform = "translateX(400px)";
//     LoginForm.style.transform = "translateX(400px)";
//     Indicator.style.transform = "translateX(-50px)";
// }


// for login screen
var LoginForm = document.getElementById("LoginForm");
var RegForm = document.getElementById("RegForm");
var Indicator = document.getElementById("Indicator");

function register() {
    RegForm.style.transform = "translateX(-400px)";
    LoginForm.style.transform = "translateX(-400px)";
    Indicator.style.transform = "translateX(50px)";
}
function login() {
    RegForm.style.transform = "translateX(0px)";
    LoginForm.style.transform = "translateX(0px)";
    Indicator.style.transform = "translateX(-50px)";
}
