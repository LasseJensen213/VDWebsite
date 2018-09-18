//var mouseInMenu = false;
//
//
//$(document).ready(function () {
//	var idleState = false;
//	var idleTimer = null;
//	var timeOutOngoing = false;
//	var timeOut = null;
//	$('*').bind('mousemove click mouseup mousedown keydown keypress keyup submit change mouseenter scroll resize dblclick', function () {
//		clearTimeout(idleTimer);
//		if (idleState == true) { 
//			if(!timeOutOngoing) {
//				$(".menuButton").show();
//				$(".text").css("width","90%");
//				openNav();
//			}
//
//		}
//		idleState = false;
//		idleTimer = setTimeout(function () { 
//			$(".menuButton").hide();
//			$(".text").css("width","100%");
//			window.console&&console.log(mouseInMenu);
//			if(!mouseInMenu) {
//				closeNav();
//			}
//
//			clearTimeout(timeOut);
//			timeOutOngoing = true;
//
//			timeOut = setTimeout(function() {
//				timeOutOngoing = false;
//				idleState = true;
//				clearTimeout(idleTimer);
//
//			},1000);
//			idleState = true; }, 2000);
//	});
//	$("body").trigger("mousemove");
//});	
//$(document).ready(function() {
//
//	$("#menuButton").click(function() {
//		this.classList.toggle("change");
//	});
//});
//
//
//
//
//function openNav() {
//	$(".sideNav").css("width","250px");
//	$(".container").css("marginLeft","250px");
//	$("body").css("backgroundColor","rgba(0,0,0,0.4)");
//	//document.getElementById("main").style.marginLeft = "250px";
//
//	//document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
//}
//
//function closeNav() {
//	$(".sideNav").css("width","0px");
//	$(".container").css("marginLeft","0px");
//	$("body").css("backgroundColor","white");
//
//	//document.body.style.backgroundColor = "white";
//}
//
//$(document).ready(function() {
//	$("#sideNav").mouseenter(function() {
//		mouseInMenu = true;
//	});
//});
//$(document).ready(function() {
//
//	$(".sideNav").mouseleave(function() {
//		mouseInMenu = false;
//	});
//});
//
//
//
//
