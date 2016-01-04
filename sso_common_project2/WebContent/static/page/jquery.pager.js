(function($) {   
	$.fn.pager = function(options) {
		var opts = $.extend({}, $.fn.pager.defaults, options);  
		return this.each(function() {       // empty out the destination element and then render out the pager with the supplied options    
			if(options.buttonClickCallback ==''){
				 options.buttonClickCallback = PageClick;
			}
			$(this).empty().append(renderpager(parseInt(options.pagenumber), parseInt(options.pagecount),options.totalcount, options.buttonClickCallback));
			$('.pages li').mouseover(function() { document.body.style.cursor = "pointer"; }).mouseout(function() { document.body.style.cursor = "auto";});
		});
	};    // render and return the pager with the supplied options
	
	function renderpager(pagenumber, pagecount,totalcount, buttonClickCallback) {        // setup $pager to hold render     
		var $pager = $('<ul class="pages"></ul>');        // add in the previous and next buttons 
		$pager.append("<li class='thpoint'>"+pagenumber+"/"+pagecount+"&nbsp;</li>");
		
		$pager.append(renderButton('首页', pagenumber, pagecount, buttonClickCallback)).append(renderButton('上一页', pagenumber, pagecount, buttonClickCallback));
		$pager.append(renderButton('下一页', pagenumber, pagecount, buttonClickCallback)).append(renderButton('末页', pagenumber, pagecount, buttonClickCallback));
		$pager.append("<li class='thpoint'>&nbsp;共"+totalcount+"条&nbsp;</li>");
		return $pager;
}    // renders and returns a 'specialized' button, ie 'next', 'previous' etc. rather than a page number button

function renderButton(buttonLabel, pagenumber, pagecount, buttonClickCallback) {     
	var $Button;   
	var destPage = 1;        // work out destination page for required button type   
	switch (buttonLabel) {
		case "首页":
			destPage = 1;
			$Button= $('<li class="pgNext" id="firstPage"></li>')
			break;
		case "上一页":   
			destPage = pagenumber - 1;
			$Button= $('<li class="pgNext" id="previosPage"></li>')
			break;
		case "下一页":
			destPage = pagenumber + 1;
			$Button= $('<li class="pgNext" id="nextPage"></li>')
		break;
		case "末页":
			destPage = pagecount;     
			$Button= $('<li class="pgNext" id="lastPage"></li>')
		break;     
	}        // disable and 'grey' out buttons if not needed.       
	if (buttonLabel == "首页" || buttonLabel == "上一页") {     
		pagenumber <= 1 ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); });     
	}       
	else {     
		pagenumber >= pagecount ? $Button.addClass('pgEmpty') : $Button.click(function() { buttonClickCallback(destPage); }); 
	}     
	return $Button;  
 }    // pager defaults. hardly worth bothering with in this case but used as placeholder for expansion in the next version

 $.fn.pager.defaults = {   
	 pagenumber: 1,     
	 pagecount: 1};
 })(jQuery);