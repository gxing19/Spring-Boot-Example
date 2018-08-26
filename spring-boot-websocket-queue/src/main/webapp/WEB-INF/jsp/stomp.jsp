<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<title>STOMP</title>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<script type="text/javascript" src="https://cdn.staticfile.org/sockjs-client/1.1.5/sockjs.min.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/stomp.js/2.3.3/stomp.min.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>

<body>
<div>
    <button id="connect" onclick="connect()">连接</button>
    <button id="disConnect" disabled="disabled" onclick="disConnect()">断开连接</button>
</div>
<div id="conversationDiv">
    <label>消息</label>
    <input type="text" name="msg" id="msgId" value=""/>
    <button id="sendMsg" onclick="sendMsg()">发送</button>
    <p id="response"></p>
</div>
</body>

<script type="text/javascript">

    var stompClient = null;

    function setConnected(connected) {
        document.getElementById("connect").disabled = connected;
        document.getElementById("disConnect").disabled = !connected;
        document.getElementById("conversationDiv").style.visibility = connected ? 'visible' : 'hidden';
        $("#response").html();
    }

    function connect() {
        var socket = new SockJS("http://localhost/websocket");
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log("connected>>>>:" + frame);//connected>>>>:CONNECTED

            stompClient.subscribe("/topic/getRespMsg", function (response) {
                var msg = JSON.parse(response.body).resultMsg;
                showResponse(msg);
            })
        });
    }

    function disConnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("DisConnect-------------------------")

    }


    function sendMsg() {
        var msg = $("#msgId").val();
        //以'/app'开头被路由到 @MessageMapping 注解的路径
        stompClient.send("/app/hello", {}, JSON.stringify({"msg": msg}));
    }

    function showResponse(msg) {
        $("#response").html(msg);
    }


</script>
</html>