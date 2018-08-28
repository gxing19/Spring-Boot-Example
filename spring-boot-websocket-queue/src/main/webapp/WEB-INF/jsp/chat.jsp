<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>聊天页面</title>
    <script src="/js/sockjs.min.js"></script>
    <script src="/js/stomp.js"></script>
    <script src="/js/jquery-3.1.1.js"></script>
</head>
<body>
<p>
    聊天室
</p>
<p>
    <input type="button" id="disConnect" name="disConnect" onclick="disConnect()" value="断开连接">
</p>

<form id="chatForm">
    <textarea rows="4" cols="60" id="chatMsgText" name="text"></textarea>
    <input type="submit"/>
</form>

<div id="output"></div>

</body>
<script th:inline="javascript">
    $('#chatForm').submit(function (e) {
        e.preventDefault();
        // var text = $('#chatForm').find('textarea[name="text"]').val();
        var text = $("#chatMsgText").val();
        console.log("msg content:" + text);
        sendSpittle(text);
    });

    // 连接endpoint为"/endpointChat"的节点
    var sock = new SockJS("/endpointChat");
    var stomp = Stomp.over(sock);

    //连接WebSocket服务端
    stomp.connect('guest', 'guest', function (frame) {
        // 订阅/user/queue/notifications发送的消息, 多了一个/user，并且这个user是必须的，
        // 因为SimpMessagingTemplate发送消息默认会在路径最前面添加前缀,默认的前缀就是 /user
        stomp.subscribe("/user/queue/notifications", handleNotification);
    });

    function handleNotification(message) {
        $('#output').append("<b>收到了:" + message.body + "</b><br/>")
    }

    function sendSpittle(text) {
        // 表示向后端路径发送消息请求,
        // /im 是后端配置的路由前缀, 会被路由到@Controller类下 @MessageMapping 注解的控制器,
        // /chat 是映射到控制器的路径
        stomp.send("/im/chat", {}, text);
    }

    function disConnect() {
        sock.close();
    }

</script>
</html>