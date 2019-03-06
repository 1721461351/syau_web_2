<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                管理系统
            </a>
        </li>
        <li>
            <a href="#"><i class="fa fa-fw fa-list-alt"></i> #</a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">商品操作</li>
                <li><a href="/ftl/admin/list.action">商品列表</a></li>
                <li><a href="/ftl/admin/index">新增商品</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">类目操作</li>
                <li><a href="/ftl/admin/category/list">类目列表</a></li>
                <li><a href="">新增类目</a></li>
            </ul>
        </li>

        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 串口 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">串口数据操作</li>
                <li><a href="/ftl/admin/port/list">数据列表</a></li>
                <#--<li><a href="">新增类目</a></li>-->
            </ul>
        </li>

        <#--添加公告的内容-->
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 反馈 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">公告操作操作</li>
                <li><a href="/admin/publishList.action">反馈信息列表</a></li>
            <li><a href="/ftl/admin/publish">发布公告</a></li>
            </ul>
        </li>

        <li>
            <a href="/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>