var mouseInMenu = false;
var updateTimerInterval;
var dateTime;



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
			//window.console&&console.log(mouseInMenu);
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

		//console.log(data);
		$(".userText").append(data.htmlText);

	});
});


$(document).ready(function() {
	$.ajax({
		url: "http://localhost:8080/VDHjemmeside/rest/backend/getCurrentState"
	}).then(function(data) {
		//console.log(data)

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
		dateTime = data.dateTime;
		updateTimerInterval = setInterval(updateTimer, 1000);


	});
});

var updateTimer = function() {
	var d = new Date(dateTime);


	var timeEllapsed = +new Date().getTime() - d;

	var days = Math.floor(timeEllapsed / (1000 * 60 * 60 * 24));
	var hours = Math.floor((timeEllapsed % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
	var minutes = Math.floor((timeEllapsed % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((timeEllapsed % (1000 * 60)) / 1000);


	var dateString = "Tid brugt i tilstand: ";
	if(days != 0) {
		dateString += days + " dage, ";
	}
	if(hours != 0) {
		dateString += hours + " timer, ";
	}
	if(minutes != 0) {
		dateString += minutes +" minutter, "
	}
	dateString += seconds + " sekunder.";
	

	$("#timeSpentInState").html(dateString);
}




