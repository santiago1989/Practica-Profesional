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