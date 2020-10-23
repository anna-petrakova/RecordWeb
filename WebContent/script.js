function myFunction(element) {
	var theme = element.dataset.theme;
	console.log("theme " + theme);
    var wr = $('.wrapper');
	wr.removeClass().addClass('wrapper ' + theme);
}

