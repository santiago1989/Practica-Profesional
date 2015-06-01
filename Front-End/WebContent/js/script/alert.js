function showPopup(){
	var popupText = document.getElementById("popupDescription").value;
	var popupType = document.getElementById("popupType").value;
	if(popupText != ""){
        if(popupType == 'info'){
        	new Messi(popupText, {title: 'Modal Window', modal: true});
        }else if(popupType == 'confirm'){
        	Messi.ask('Esta seguro que desea eliminar?',
        			function(val){ alert(val);});
        }
	}
}