<!DOCTYPE html>
<html lang="en">

<#--引入头文件-->
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
<#--引入边栏-->
    <#include "../common/nav.ftl">

<#--引入内容 ，把内容包裹住-->
    <div id="page-content-wrapper">
        <div class="container-fluid">  <#-- 流动布局-->
            <div class="row clearfix">
                <div class="col-md-12 column">
                <#--<table class="table table-hover table-condensed table-striped">-->
                    <table class="table table-bordered table-hover table-condensed">
                        <thead>
                        <tr>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品价格</th>
                            <th>商品数量</th>
                            <th>商品类别</th>
                            <th>商品图片</th>
                        <#--这里占了2列-->
                            <th colspan="3">
                                操作
                            </th>

                        </tr>
                        </thead>
                        <tbody>
                <#list productInfo.content as product>
                <tr>
                    <td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.productPrice}</td>
                    <td>${product.productNum}</td>
                    <td>${product.productType}</td>
                    <td><img src="${product.productImg}" height="100" width="100"></td>
                <#--删除一个商品-->
                    <td><a href="/ftl/admin/deleteProduct?productId=${product.productId}">删除</a> </td>
                <#--如果是在架状态-->
                    <#if product.getProductStatus()==0>
                    <td><a href="/ftl/admin/productoff?productId=${product.productId}">点击下架</a></td>
                    <#else>
                    <td><a href="/ftl/admin/productup?productId=${product.productId}">点击上架</a></td>
                    </#if>

                    <td><a href="/ftl/admin/index?productId=${product.productId}">修改</a> </td>

                </tr>
                </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页实现-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                <#if currentPage lte 1>
                <li class="disabled">
                    <a href="#">上一页</a>
                </li>
                <#else>
                <li>
                    <a href="/ftl/admin/list.action?currentpage=${currentPage-1}&size=4">上一页</a>
                </li>
                </#if>
                <#list 1..productInfo.totalPages as index>
                    <#if currentPage==index>
                    <li class="disabled">
                        <a href="#">${index}</a>
                    </li>
                    <#else>
                    <li>
                        <a href="/ftl/admin/list.action?currentpage=${index}&size=4">${index}</a>
                    </li>
                    </#if>
                </#list>
                <#if currentPage gte productInfo.getTotalPages()>
                <li class="disabled">
                    <a href="#">下一页</a>
                </li>
                <#else>
                <li>
                    <a href="/ftl/admin/list.action?currentpage=${currentPage+1}&size=4">下一页</a>
                </li>
                </#if>
                    </ul>
                </div>

            </div>
        </div>
    </div>
    </div>

<#--引入内容 ，把内容包裹住-->
<div id="page-content-wrapper">
    <div class="container-fluid">  <#-- 流动布局-->
        <div class="row clearfix">
            <div class="col-md-12 column">
                <#--<table class="table table-hover table-condensed table-striped">-->
                <table class="table table-bordered table-hover table-condensed">
                    <thead>
                    <tr>
                        <th>商品编号</th>
                        <th>商品名称</th>
                        <th>商品价格</th>
                        <th>商品数量</th>
                        <th>商品类别</th>
                        <th>商品图片</th>
                    <#--这里占了2列-->
                        <th colspan="3">
                            操作
                        </th>

                    </tr>
                    </thead>
                    <tbody>
                <#list productInfo.content as product>
                <tr>
                    <td>${product.productId}</td>
                    <td>${product.productName}</td>
                    <td>${product.productPrice}</td>
                    <td>${product.productNum}</td>
                    <td>${product.productType}</td>
                    <td><img src="${product.productImg}" height="100" width="100"></td>
                    <#--删除一个商品-->
                    <td><a href="/ftl/admin/deleteProduct?productId=${product.productId}">删除</a> </td>
                    <#--如果是在架状态-->
                    <#if product.getProductStatus()==0>
                    <td><a href="/ftl/admin/productoff?productId=${product.productId}">点击下架</a></td>
                    <#else>
                    <td><a href="/ftl/admin/productup?productId=${product.productId}">点击上架</a></td>
                    </#if>

                    <td><a href="/ftl/admin/index?productId=${product.productId}">修改</a> </td>

                </tr>
                </#list>
                    </tbody>
                </table>
            </div>

        <#--分页实现-->
            <div class="col-md-12 column">
                <ul class="pagination pull-right">
                <#if currentPage lte 1>
                <li class="disabled">
                    <a href="#">上一页</a>
                </li>
                <#else>
                <li>
                    <a href="/ftl/admin/list.action?currentpage=${currentPage-1}&size=4">上一页</a>
                </li>
                </#if>
                <#list 1..productInfo.totalPages as index>
                    <#if currentPage==index>
                    <li class="disabled">
                        <a href="#">${index}</a>
                    </li>
                    <#else>
                    <li>
                        <a href="/ftl/admin/list.action?currentpage=${index}&size=4">${index}</a>
                    </li>
                    </#if>
                </#list>
                <#if currentPage gte productInfo.getTotalPages()>
                <li class="disabled">
                    <a href="#">下一页</a>
                </li>
                <#else>
                <li>
                    <a href="/ftl/admin/list.action?currentpage=${currentPage+1}&size=4">下一页</a>
                </li>
                </#if>
                </ul>
            </div>

        </div>
    </div>
</div>
<#--
<#list productInfo.productInfoList as product></#list>
-->

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

        alert("收到学生的反馈，内容是："+event.data)
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