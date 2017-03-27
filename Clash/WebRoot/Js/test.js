/**
 * 
 */
var websocket = null;

if('WebSocket' in window) {
	websocket = new WebSocket("ws://localhost:8080/Clash/clash");
}
else {
	alert('当前浏览器 Not support websocket');
}


var sendButton = document.getElementById("send");
var closeButton = document.getElementById("close");
var showDiv = document.getElementById("show");


websocket.onopen = function(){
	sendMessage("Success");
}
websocket.onmessage = function(event){
	sendMessage(event.data);
}
websocket.onerror = function(){
	sendMessage("Error");
}
websocket.onclose = function(){
	sendMessage("Close");
}
function sendMessage(massage){
	showDiv.innerHTML += massage+'<br/>';
}
sendButton.onclick = function(){
//	alert("1111");
	var mess = document.getElementById("message").value;
	//alert(mess);
	websocket.send(mess);
}
closeButton.onclick = function(){
	websocket.close();
}

window.onbeforeunload = function(){
	websocket.close();
}