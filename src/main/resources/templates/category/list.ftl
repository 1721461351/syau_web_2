<!DOCTYPE html>
<html lang="en">

<#--引入头文件-->
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
<#--引入边栏-->
    <#include "../common/nav.ftl">
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
                        <th></th>
                        <th>类目id</th>
                        <th>名字</th>
                        <th>类别</th>
                        <#--<th>创建时间</th>-->
                        <#--<th>修改时间</th>-->
                    <#--这里占了2列-->
                        <th>
                            修改
                        </th>

                    </tr>
                    </thead>
                    <tbody>
                <#list category as type>
                <tr>
                    <th></th>
                    <td>${type.categoryId}</td>
                    <td>${type.categoryName}</td>
                    <td>${type.categoryType}</td>
                    <#--<td>${product.productNum}</td>-->
                    <#--<td>${product.productType}</td>-->
                <#--删除一个商品-->
                    <td><a href="/ftl/admin//deleteProduct?productId=${type.categoryId}">修改</a> </td>
                </tr>
                </#list>
                    </tbody>
                </table>
            </div>


        </div>
    </div>
</div>
<#--
<#list productInfo.productInfoList as product></#list>
-->
</body>
</html>