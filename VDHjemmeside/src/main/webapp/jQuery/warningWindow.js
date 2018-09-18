$(document).ready(function () {
        $('.menu').animate({
            scrollTop: '+=100'
        }, 100);
  
});




/*
function scrolldownList() {
	$('.menu').animate({
		scrollTop: '+=100'
	}, 100);
}
*/


var currentWarning

$(document).ready(function markFirstElement() {
	$currentWarning = $(".menu li").first();
	$currentWarning.addClass("selected");
	iterateThroughElements();
	

});



function iterateThroughElements() {
	setInterval(function() { 
		$currentWarning.removeClass("selected");
		//console.log($currentWarning.next().length);
		if( $currentWarning.next().length == 0) {
			$currentWarning = $(".menu li").first();
			$currentWarning.addClass("selected");
			return;

		} 
		
		$currentWarning = $currentWarning.next();
		$currentWarning.addClass("selected");
		}, 500);
}


