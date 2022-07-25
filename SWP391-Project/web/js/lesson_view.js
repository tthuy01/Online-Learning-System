

let navLinks = document.querySelector(".navbar_content");
let menuOpenBtn = document.querySelector(".navbar .bx-menu");
let menuCloseBtn = document.querySelector(".navbar_content .bx-x");
menuOpenBtn.onclick = function() {
navLinks.style.left = "0";
}
menuCloseBtn.onclick = function() {
navLinks.style.left = "-100%";
}

var dd_menu_a = document.querySelectorAll(".sub_lesson");



dd_menu_a.forEach(function(dd_menu_item){

		dd_menu_item.addEventListener("click", function(){
			
			dd_menu_item.classList.toggle("active");
		})
})

