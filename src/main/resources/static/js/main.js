
'use strict';
var usernameForm = document.querySelector('#usernameForm');
var login = document.querySelector('#loginForm');
var pubsub;
var linkpubsub;

function getData(callback)
{
    fetch("http://localhost:8080/api/v1/group-order/get-group-link")
        .then(response => response.json())
        .then(data => pubsub=data['data']['link'])
        .then(callback)

}
function showData(link) {
//     pubsub=link;
    console.log(pubsub);
//
}

var stompClient = null;
var username = null;



function connect(event) {
    linkpubsub =document.getElementById("pubsub").value;
    //getLink
    getData(showData);
    var socket = new SockJS('/bill-sharing');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
    event.preventDefault();
}
function onConnected() {

    if (linkpubsub==""){
        stompClient.subscribe('/topic/public/'+pubsub, onMessageReceived);
    }else {
        stompClient.subscribe('/topic/public/'+linkpubsub, onMessageReceived);
    }

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
    if (linkpubsub==""){
        stompClient.send(api_url+pubsub, {}, JSON.stringify(id));
        document.getElementById("data").style.display = "none";
    }else {
        stompClient.send(api_url+linkpubsub, {}, JSON.stringify(id));
        document.getElementById("data").style.display = "none";
    }

}




function login(event){
    var account =
        {
            username: "admin",
            password: "admin"
        }
    fetch("http://localhost:8080/api/v1/auth/login",{
        method:"POST",
        body:account
    })
        .then(response => response.json())
        .then(response => console.log(response))
    event.preventDefault();

}
login.addEventListener('submit',login,true)


usernameForm.addEventListener('submit', connect, true)

