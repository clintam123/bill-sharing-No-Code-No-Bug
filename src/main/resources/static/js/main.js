'use strict';
var usernameForm = document.querySelector('#usernameForm');


var stompClient = null;
var username = null;



function connect(event) {
        var socket = new SockJS('/bill-sharing');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
         event.preventDefault();
}
function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);
}

function onError(error) {
    console.log("Lỗi rồi")
}
function onMessageReceived(payload){
    console.log(payload.body)
    const message = JSON.parse(payload.body);
    if (message !=null){
        document.getElementById("data").style.display="none";
    }
    // console.log(message.content)
}
const api_url="/app/delete-order-item"
async function delData(id ) {
    stompClient.send(api_url, {}, JSON.stringify(id));
    // document.getElementById("data").style.display = "none";
}





usernameForm.addEventListener('submit', connect, true)
