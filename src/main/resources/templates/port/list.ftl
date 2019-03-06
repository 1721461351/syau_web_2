<html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
<#--引入边栏-->
    <#include "../common/nav.ftl">
</div>

<#--主要内容content-->
<div id="page-content-wrapper">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>id</th>
                        <th>温度</th>
                        <th>湿度</th>
                        <th>光照强度</th>
                        <th>更新时间</th>
                    </tr>
                    </thead>
                    <tbody>

                        <#list portDataList as port>
                        <tr>
                            <td>${port.portId}</td>
                            <td>${port.temperature}</td>
                            <td>${port.humidity}</td>
                            <td>${port.lightIntensity}</td>
                            <td>${port.createTime}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</div>


<script>

    var websocket = null;
    if ('websocket' in window) {
        // websocket = new websocket('ws://localhost:8888/syau_web_1/webSocket');
        // websocket = new websocket();
        websocket = new WebSocket('ws://localhost:8888/webSocket');


    } else {
        alert("该浏览器不支持websocket");
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('关闭连接');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息：' + event.data);
        //这里写触发的事件，比如播放音乐等等

        alert(event.data)
    }

    websocket.onerror = function (event) {
        console.log('连接发生错误');
    }
    websocket.onbeforeactivate = function (event) {
        console.log('释放资源');
    }
</script>
</body>
</html>