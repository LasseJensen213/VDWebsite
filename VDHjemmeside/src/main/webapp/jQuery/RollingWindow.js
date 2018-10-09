var warningIndex = 0;				
var numberOfWarnings = 0;
var updateWarningsDisplayInterval;
var fetchNotificationsInterval;
var fetchWarningsInterval;



$(document).ready(function() {
	$.ajax({
		url: "http://localhost:8080/VDHjemmeside/rest/backend/notifications"
	}).then(function(data) {
		$(".notification").remove();
		var $animationDuration = data.length*5;		
		$("#animation").css("animation-duration",$animationDuration + "s"); 



		$.each(data, function(index, element) {
			var $title = $("<p/>").addClass("title");
			var $description = $("<p/>").addClass("description");
			var $timeOfLastUpdate = $("<p/>").addClass("timeOfLastUpdate");
			var $notification = $("<div/>").addClass("notification");
			$notification.attr("id",element.situationID);

			$title.text(element.situationExtensionDTO.textualDescriptionList["0"].locationText);
			$description.text(element.situationExtensionDTO.textualDescriptionList["0"].messageText);
			$timeOfLastUpdate.html("<p>" + element.situationExtensionDTO.textualDescriptionList["0"].locationText + "</p>");

			$notification.append($title, $description);

			//window.console&&console.log(element.situationExtensionDTO.textualDescriptionList["0"].locationText);
			$(".animation").append($notification[0]);

		});
	});
	clearInterval(fetchNotificationsInterval);
	fetchNotificationsInterval = setInterval(fetchNotifications, 60000);
});


var fetchNotifications = function() {
	$.ajax({
		url: "http://localhost:8080/VDHjemmeside/rest/backend/notifications"
	}).then(function(data) {handleNotificationData(data)});
}

function handleNotificationData(data) {
	$(".notification").remove();
	var $animationDuration = data.length*5;		
	$("#animation").css("animation-duration",$animationDuration + "s"); 



	$.each(data, function(index, element) {
		var $title = $("<p/>").addClass("title");
		var $description = $("<p/>").addClass("description");
		var $timeOfLastUpdate = $("<p/>").addClass("timeOfLastUpdate");
		var $notification = $("<div/>").addClass("notification");
		$notification.attr("id",element.situationID);

		$title.text(element.situationExtensionDTO.textualDescriptionList["0"].locationText);
		$description.text(element.situationExtensionDTO.textualDescriptionList["0"].messageText);
		$timeOfLastUpdate.html("<p>" + element.situationExtensionDTO.textualDescriptionList["0"].locationText + "</p>");

		$notification.append($title, $description);

		//window.console&&console.log(element.situationExtensionDTO.textualDescriptionList["0"].locationText);
		$(".animation").append($notification[0]);

	});
}


//$(document).ready(function() {
//setInterval(function() {
////alert("test");
//$("div[id='Trafikman2/r_Trafikman2/1148305_TIC-Trafikman2/1']").remove();
//}, 40000)


//});




//Inserts the special warnings. 
var currentWarnings = {};
$(document).ready(function() {
	$.ajax({
		url: "http://localhost:8080/VDHjemmeside/rest/backend/warnings"
	}).then(function(data) {
		handleWarningData(data);
	});
	clearInterval(fetchWarningsInterval);
	fetchWarningsInterval = setInterval(fetchWarnings,60000);
});

var fetchWarnings = function() {
	$.ajax({
		url: "http://localhost:8080/VDHjemmeside/rest/backend/warnings"
	}).then(function(data) {handleWarningData(data)});
}

function handleWarningData(data) {
	console.log("Handling new data");
	clearInterval(updateWarningsDisplayInterval);
	numberOfWarnings = 0;


	if (!$.trim(data)){ 
		$(".warningSection").hide();
	}
	else {
		$(".toBeRemoved").remove();
		$.each(data, function(index, element) {
			$(".toBeRemoved").remove();

			var $title = $("<p/>").addClass("warningTitle");
			var $description = $("<p/>").addClass("warningText");
			var $timeOfLastUpdate = $("<p/>").addClass("warningLastUpdated");
			var $warning = $("<div/>").addClass("toBeRemoved");

			$title.text(element.title);
			$description.text(element.description);
			$timeOfLastUpdate.html(element.expire);

			$warning.append($title, $description, $timeOfLastUpdate);

			//window.console&&console.log(element);
			currentWarnings[numberOfWarnings] = $warning;
			numberOfWarnings = numberOfWarnings + 1;
		});
		$("#numberOfWarningsText").text(numberOfWarnings);
		window.console&&console.log(currentWarnings);
		
		
		$("#numberOfWarningsText").text(warningIndex + 1 + "/" + numberOfWarnings);
		$(".warning").append(currentWarnings[warningIndex]);
		warningIndex++;
		updateWarningsDisplayInterval = setInterval(updateWarningsDisplay, 10000);
		
	}
}

var updateWarningsDisplay = function() {
	if(warningIndex >= numberOfWarnings) {
		warningIndex = 0;
	}
	$(".toBeRemoved").remove();
	$(".warning").append(currentWarnings[warningIndex]);
	$("#numberOfWarningsText").text(warningIndex + 1 + "/" + numberOfWarnings);
	warningIndex++;
}


