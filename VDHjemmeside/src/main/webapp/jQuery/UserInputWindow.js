var resturl = "VDHjemmeside/rest/backend"


	$(document).ready(function() {
		$("#normalState").click(function() {
			$("#stateButton").html("Normalt indsatsniveau");
			$("#stateButton").attr("val","NORMAL_STATE_LEVEL");
		});
	});
$(document).ready(function() {
	$("#raisedState").click(function() {
		$("#stateButton").html("Forh&oslash;jet indsatsniveau");
		$("#stateButton").attr("val","RAISED_STATE_LEVEL");

	});
});
$(document).ready(function() {
	$("#highestState").click(function() {
		var text = "H&oslash;jt indsatsniveau";

		$("#stateButton").html(text);
		$("#stateButton").attr("val","HIGH_STATE_LEVEL");

	});
});

$(document).ready(function() {
	$("#state").submit(function() {
		logStateLevel();

		//Prevent submit function from changing url
		return false;
	});
});



function logStateLevel() {
	var stateLevel = $("#stateButton").attr("val")
	var date =  $("#date").val();
	var time =  $("#time").val();

	$("#datetime").val("");

	//Check the input value in the datetime field. 
	if(date == "" || time == "") {
		//alert("Indtast venligst et gyldigt tidspunkt");
		return;

	}

	var obj = {"date": date, "time": time, "statelevel": stateLevel};
	var json = JSON.stringify(obj);
	console.log(json);



	$.ajax({
		type: "POST",
		url: "http://localhost:8080/VDHjemmeside/rest/backend/addStateLevel",
		data: json,
		contentType: "application/json; charset=utf-8",
		crossDomain: true,
		dataType: json,
		success: function (data, status, jqXHR) {

			alert(success);
		},

		error: function (jqXHR, status) {
			// error handler
			console.log(jqXHR);
			//alert('fail' + jqXHR.status);
		}
	});

}

$(document).ready(function() {
	$("#timeNow").click(function() {
		setTimeNow();
	});
});


$(document).ready(function(){
	setTimeNow();
});

$(document).ready(function(){
	$("#h1").click(function() {
		insertAtCursor($("#textArea"),"h1");
	});
	$("#h2").click(function() {
		insertAtCursor($("#textArea"),"h2");

	});
	$("#h3").click(function() {
		insertAtCursor($("#textArea"),"h3");

	});
	$("#h4").click(function() {
		insertAtCursor($("#textArea"),"h4");

	});
	$("#h5").click(function() {
		insertAtCursor($("#textArea"),"h5");

	});
	$("#h6").click(function() {
		insertAtCursor($("#textArea"),"h6");

	});
	$("#bold").click(function() {
		insertAtCursor($("#textArea"),"b");

	});
	$("#italic").click(function() {
		insertAtCursor($("#textArea"),"i");

	});
	$("#underlined").click(function() {
		insertAtCursor($("#textArea"),"u");

	});


});





$(document).ready(function(){
	$("#preview").click(function() {
		 console.log($("#textArea").val());
		 $(".textPreview").html("");
		 $(".textPreview").show();

		 console.log($("#textArea").val());
		 $(".textPreview").append("<pre>" + $("#textArea").val() + "</pre>");
	});

});










var datetime =  $("#datetime").val();

function setTimeNow() {
	console.log(convertUTCDateToLocalDate(new Date()));
	$('input[type=date]').val(convertUTCDateToLocalDate(new Date()).toJSON().slice(0,10));
	$('input[type=time]').val(convertUTCDateToLocalDate(new Date()).toJSON().slice(11,16));

}

function convertUTCDateToLocalDate(date) {
    var newDate = new Date(date.getTime()+date.getTimezoneOffset()*60*1000);

    var offset = date.getTimezoneOffset() / 60;
    var hours = date.getHours();

    newDate.setHours(hours - offset);

    return newDate;   
}



function insertAtCursor(element, newText) {
	var startTag = "<" + newText + ">";
	var endTag = "</" + newText + ">";
	
	
	var start = element.prop("selectionStart")
	var end = element.prop("selectionEnd")
	var text = element.val()
	var before = text.substring(0, start)
	var middle = text.substring(start,end);
	var after  = text.substring(end, text.length)

	element.val(before + startTag + middle + endTag+ after)
	element[0].selectionStart = element[0].selectionEnd = start + startTag.length + middle.length
	element.focus()
	
}




$(document).ready(function(){
	$("#saveText").click(function() {
		var userText =  "<pre>" + $("#textArea").val() + "</pre>"

		
		var obj = {"htmlText": userText};
		var json = JSON.stringify(obj);
		console.log(json);



		$.ajax({
			type: "POST",
			url: "http://localhost:8080/VDHjemmeside/rest/backend/saveUserText",
			data: json,
			contentType: "application/json; charset=utf-8",
			crossDomain: true,
			dataType: json,
			success: function (data, status, jqXHR) {

				alert(success);
			},

			error: function (jqXHR, status) {
				// error handler
				console.log(jqXHR);
				//alert('fail' + jqXHR.status);
			}
		});
	});

});




