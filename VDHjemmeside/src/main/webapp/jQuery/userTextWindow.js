

$(document).ready(function() {
	$("#menuButton").click(function() {
		this.classList.toggle("change");
	});
});


$(document).ready(function () {
    var idleState = false;
    var idleTimer = null;
    $('*').bind('mousemove click mouseup mousedown keydown keypress keyup submit change mouseenter scroll resize dblclick', function () {
        clearTimeout(idleTimer);
        if (idleState == true) { 
            $(".menuButton").show();
        	$(".textArea").css("width","90%");

        }
        idleState = false;
        idleTimer = setTimeout(function () { 
        	$(".menuButton").hide();
        	$(".textArea").css("width","100%");
            idleState = true; }, 2000);
    });
    $("body").trigger("mousemove");
});	




