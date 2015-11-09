function loadMenu(){
	var menu = $('.rm-nav').rMenu({
		
		// Optional Settings
		
		/**
		 * Minimum width for expanded layout in pixels - String
		 * The media query in css file should be changed to match this
		 * Must be in pixels and include px units if not using Modernizr.
		 * @default '769px'
		 */
		minWidth: '960px',
	});
	$('.passChange').click(function(){popupDiv('passChange')});
}
function popupDiv(idDiv){
	$.blockUI({
	  message: $("#" + idDiv),

	  css: {
	    position: 'absolute',
	  }
	});
}
function cerrarDiv(){
	$.unblockUI();
}