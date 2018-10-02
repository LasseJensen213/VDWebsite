var mouseInMenu = false;


$(document).ready(function () {
	var idleState = false;
	var idleTimer = null;
	var timeOutOngoing = false;
	var timeOut = null;
	$('*').bind('mousemove click mouseup mousedown keydown keypress keyup submit change mouseenter scroll resize dblclick', function () {
		clearTimeout(idleTimer);
		if (idleState == true) { 
			if(!timeOutOngoing) {
				$(".menuButton").show();
				$(".text").css("width","90%");
				openNav();
			}

		}
		idleState = false;
		idleTimer = setTimeout(function () { 
			$(".menuButton").hide();
			$(".text").css("width","100%");
			window.console&&console.log(mouseInMenu);
			if(!mouseInMenu) {
				closeNav();
			}

			clearTimeout(timeOut);
			timeOutOngoing = true;

			timeOut = setTimeout(function() {
				timeOutOngoing = false;
				idleState = true;
				clearTimeout(idleTimer);

			},1000);
			idleState = true; }, 2000);
	});
	$("body").trigger("mousemove");
});	
$(document).ready(function() {

	$("#menuButton").click(function() {
		this.classList.toggle("change");
	});
});




function openNav() {
	$(".sideNav").css("width","250px");
	$(".container").css("marginLeft","250px");
	$("body").css("backgroundColor","rgba(0,0,0,0.4)");
	//document.getElementById("main").style.marginLeft = "250px";

	//document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
	$(".sideNav").css("width","0px");
	$(".container").css("marginLeft","0px");
	$("body").css("backgroundColor","white");

	//document.body.style.backgroundColor = "white";
}

$(document).ready(function() {
	$("#sideNav").mouseenter(function() {
		mouseInMenu = true;
	});
});
$(document).ready(function() {

	$(".sideNav").mouseleave(function() {
		mouseInMenu = false;
	});
});


$(document).ready(function() {
	$.ajax({
		url: "http://localhost:8080/VDHjemmeside/rest/backend/getUserText"
	}).then(function(data) {
		$(".userText").html("");

		console.log(data);
		$(".userText").append(data.htmlText);
	
	});
});


$(document).ready(function() {
	$.ajax({
		url: "http://localhost:8080/VDHjemmeside/rest/backend/getCurrentState"
	}).then(function(data) {
		console.log(data)
		
		var date = data.dateTime.substring(0,10);
		date = date.replace("-","/");
		date = date.replace("-","/");

		var time = data.dateTime.substring(11,16);
		if(data.statelevel == "NULL_LEVEL") {
			$("#circle").attr("src","./pictures/greyCircle.png");
			$("#timeStamp").text("Intet indsatsniveau siden " + date + " - " + time);


		}else if(data.statelevel == "NORMAL_STATE_LEVEL") {
			$("#circle").attr("src","./pictures/greenCircle.png");
			$("#timeStamp").html("Normalt indsatsniveau siden " + date + " - " + time);


		}else if(data.statelevel == "RAISED_STATE_LEVEL") {
			$("#circle").attr("src","./pictures/yellowCircle.png");
			$("#timeStamp").html("Forh&oslash;jet indsatsniveau siden " + date + " - " + time);


		}else if(data.statelevel == "HIGH_STATE_LEVEL") {
			$("#circle").attr("src","./pictures/redCircle.png");
			$("#timeStamp").html("H&oslash;jt indsatsniveau siden " + date + " - " + time);


		}
		
		var year = data.dateTime.substring(0,4);
		var month = data.dateTime.substring(5,7);
		var day = data.dateTime.substring(9,10);
		var hour = data.dateTime.substring(11,13);
		var minute = data.dateTime.substring(14,16);

		var d = new Date(data.dateTime);
		console.log("year: " + year);
		console.log("month: " + month);
		console.log("day: " + day);
		console.log("hour: " + hour);
		console.log("minute: " + minute);
		
		
		
		console.log("d: " + d);

		console.log("now: " + +new Date());

		var timeEllapsed = +new Date().getTime() - d;

		console.log("Time ellapsed: " + timeEllapsed);
		
		$("#timeSpentInState").html("asfd");
		
	
	});
	
	
	 
	 
	 
	// 


});




