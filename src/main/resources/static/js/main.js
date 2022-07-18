'use strict';
var usernameForm = document.querySelector('#usernameForm');
var pubsub;

function getData(callback)
{
    fetch("/api/v1/group-order/get-group-link")
        .then(response => response.json())
        .then(data => data.link)
        .then(callback)

}
function showData(link) {
     pubsub=link;
     console.log(pubsub);

}

var stompClient = null;
var username = null;



function connect(event) {
        getData(showData);
        var socket = new SockJS('/bill-sharing');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected, onError);
         event.preventDefault();
}
function onConnected() {
    stompClient.subscribe('/topic/public/'+pubsub, onMessageReceived);
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
const api_url="/app/delete-order-item/";
async function delData(id ) {
    stompClient.send(api_url+pubsub, {}, JSON.stringify(id));
    // document.getElementById("data").style.display = "none";
}






usernameForm.addEventListener('submit', connect, true)
