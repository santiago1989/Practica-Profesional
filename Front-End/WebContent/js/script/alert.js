function showPopup(){
	var popupText = document.getElementById("popupDescription").value;
	var popupType = document.getElementById("popupType").value;
	if(popupText != ""){
        if(popupType == 'info'){
        	new Messi(popupText, {title: 'Informaci&oacute;n', modal: true});
        }else if(popupType == 'confirm'){
        	Messi.ask(popupText,function(val){ alert(val);});
        }else{
        	new Messi(popupText, {title: 'Error',titleClass: "error", modal: true});
        }
	}
}
function showAlert(text,urlToCall){
//	var dialog = new Messi(text, {
//		  title: 'Buttons',
//		  buttons: [{id: 0, label: 'Yes', val: 'Y'}, {id: 1, label: 'No', val: 'N'}],
//		  callback: function(val) { alert('Your selection: ' + val);this.hide(); }
//	});
	Messi.ask(text, 
		function(val) {if(val == 'Y'){window.location.href = urlToCall;}});
}