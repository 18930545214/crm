<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>产品列表</title>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-2.2.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/layer/layer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.pagination.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
    <script>
        var isFirst=true;
        $(function () {
            // 显示第一页的5条数据
            show(1, 3);
        });
        function updateStatus(status) {
            var id = isCheckbox();
            if(id==="id"){
                    layer.alert('你还没有选中哦', {icon: 7});
            }else {
                $.post("${pageContext.request.contextPath}/product/update",
                    {"_method":"put",
                        "id":id,
                        "productStatus":status},
                    function (data) {
                        if (data.info==="success"){
                            layer.msg('操作成功', {icon: 6});
                            show(1,3);
                            $("#selall").iCheck("uncheck");
                        }else {
                            layer.msg('权限不足', {icon: 5});
                        }
                    });
            }
        }
        function deleteById(){
            var id = isCheckbox();
            if(id==="id"){
                layer.alert('你还没有选中哦', {icon: 7});
            }else {
                layer.confirm('你确定要删除吗？', {
                    btn: ['确定','取消'] //按钮
                }, function(){
                    $.post("${pageContext.request.contextPath}/product/delete",
                        {"_method":"delete",
                            "id":id},
                        function (data) {
                            if (data.info==="success"){
                                layer.msg('删除成功', {icon: 6});
                                show(1,3);
                                $("#selall").iCheck("uncheck");
                            }else {
                                layer.msg('权限不足', {icon: 5});
                            }
                        });
                }, function(){
                    layer.msg('你已取消');
                });
            }
        }
        function queryByName(name) {
            if (name===""){
                layer.msg('没有搜到内容', {icon: 5});
            }else {
                $.get("${pageContext.request.contextPath}/product/queryByName/",{"name":name}, function (data) {
                    if (data===""){
                        layer.alert('权限不足', {icon: 2});
                    }else {
                        //此代码要使用jquery进行拼接
                        var $tbody = $("#productList");
                        $tbody.empty();// 清空元素中的内容
                        // 循环创建tr,td元素
                        $("#pull_left").empty();
                        $("#Pagination").empty();
                        for (var i = 0; i < data.length; i++) {
                            var pid = i+1;
                            var pro = data[i];//取出消息
                            var $tr = $("<tr></tr>");
                            var $td1 = $("<td><input name='ids' type='checkbox' value='"+pro.id+"' onclick='setCheckBok()'></td>");
                            var $td2 = $("<td>"+pid+"</td>");
                            var $td3 = $("<td>"+pro.productNum+"</td>");
                            var $td4 = $("<td>"+pro.productName+"</td>");
                            var $td5 = $("<td>"+pro.cityName+"</td>");
                            var $td6 = $("<td>"+pro.departureTime+"</td>");
                            var $td7 = $("<td class='text-center'>"+pro.productPrice+"</td>");
                            var $td8 = $("<td class='text-center'>"+pro.productDesc+"</td>");
                            var $td9 = $("<td class='text-center'>"+(pro.productStatus>0?'开启':'关闭')+"</td>");
                            var $td10 = $("<td class='text-center'><button type='button' class='btn bg-olive btn-xs' onclick=\"location.href='${pageContext.request.contextPath}/pages/orders-list.jsp?"+ pro.id +"'\">订单</button></td>");
                            $tr.append($td1).append($td2).append($td3).append($td4).append($td5).append($td6).append($td7).append($td8).append($td9).append($td10);
                            $tbody.append($tr);
                        }
                    }
                })

            }
        }
        function show(pageNo, pageSize) {
        //发送ajax请求
            $.get("${pageContext.request.contextPath}/product/queryAll/" + pageNo + "/" + pageSize, function (data) {
                //此代码要使用jquery进行拼接
                var $tbody = $("#productList");
                $tbody.empty();// 清空元素中的内容
                // 循环创建tr,td元素
                for (var i = 0; i < data.list.length; i++) {
                    var pid = i+1;
                    var pro = data.list[i];//取出消息
                    var $tr = $("<tr></tr>");
                    var $td1 = $("<td><input name='ids' type='checkbox' value='"+pro.id+"' onclick='setCheckBok()'></td>");
                    var $td2 = $("<td>"+pid+"</td>");
                    var $td3 = $("<td>"+pro.productNum+"</td>");
                    var $td4 = $("<td>"+pro.productName+"</td>");
                    var $td5 = $("<td>"+pro.cityName+"</td>");
                    var $td6 = $("<td>"+pro.departureTime+"</td>");
                    var $td7 = $("<td class='text-center'>"+pro.productPrice+"</td>");
                    var $td8 = $("<td class='text-center'>"+pro.productDesc+"</td>");
                    var $td9 = $("<td class='text-center'>"+(pro.productStatus>0?'开启':'关闭')+"</td>");
                    var $td10 = $("<td class='text-center'><button type='button' class='btn bg-olive btn-xs' onclick=\"location.href='${pageContext.request.contextPath}/pages/orders-list.jsp?"+ pro.id +"'\">订单</button></td>");
                    $tr.append($td1).append($td2).append($td3).append($td4).append($td5).append($td6).append($td7).append($td8).append($td9).append($td10);
                    $tbody.append($tr);
                }
                /*设置总页数和总记录数*/
                $("#pageTotals").html(data.pages);
                $("#totolRecords").html(data.total);
                /*创建下拉菜单*/
                $("#pageSize").empty();
                for(var i=1;i<=5;i++) {
                    var $option;
                    if(i==data.pageSize) {
                        $option = $("<option selected='selected'>" + i + "</option>");
                    }else{
                        $option = $("<option>" + i + "</option>");
                    }
                    $("#pageSize").append($option);
                }
                if(isFirst){
                    initPagination(data.total,pageSize);//初始化分頁插件
                    isFirst=false;
                }
            })

        }
    </script>

</head>

<body class="hold-transition skin-purple sidebar-mini">
<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="header.jsp"></jsp:include>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="aside.jsp"></jsp:include>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <!-- @@master = admin-layout.html-->
    <!-- @@block = content -->

    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                数据管理
                <small>数据列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">数据管理</a></li>
                <li class="active">数据列表</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content">

            <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="新建" onclick="location.href='${pageContext.request.contextPath}/pages/product-add.jsp'"><i
                                            class="fa fa-file-o"></i> 新建
                                    </button>
                                    <button type="button" class="btn btn-default" title="删除" onclick="deleteById()"><i class="fa fa-trash-o"></i> 删除
                                    </button>
                                    <button type="button" class="btn btn-default" title="开启" onclick="updateStatus(1)"><i class="fa fa-check"></i>
                                        开启
                                    </button>
                                    <button type="button" class="btn btn-default" title="屏蔽" onclick="updateStatus(0)"><i class="fa fa-ban"></i>
                                        屏蔽
                                    </button>
                                    <button type="button" class="btn btn-default" title="刷新" onclick="refurbish()"><i
                                            class="fa fa-refresh"></i> 刷新
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="box-tools pull-right">
                            <div class="has-feedback">
                                <input type="text" class="form-control input-sm" placeholder="搜索" onblur="queryByName(this.value,1,3)">
                                <span class="glyphicon glyphicon-search form-control-feedback"></span>
                            </div>
                        </div>
                        <!--工具栏/-->

                        <!--数据列表-->
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="" style="padding-right:0px;">
                                    <input id="selall" type="checkbox" class="icheckbox_square-blue" onclick="checkbox()">
                                </th>
                                <th class="sorting_asc">ID</th>
                                <th class="sorting_desc">编号</th>
                                <th class="sorting_asc sorting_asc_disabled">产品名称</th>
                                <th class="sorting_desc sorting_desc_disabled">出发城市</th>
                                <th class="sorting">出发时间</th>
                                <th class="text-center sorting">产品价格</th>
                                <th class="text-center sorting">产品描述</th>
                                <th class="text-center sorting">状态</th>
                                <th class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody id="productList">
                           
                            <%--<tr>
                                <td><input name="ids" type="checkbox"></td>
                                <td>2</td>
                                <td>hwua-001</td>
                                <td>上海5日游</td>
                                <td>上海</td>
                                <td>2018-03-10 19:00:00</td>
                                <td class="text-center">850</td>
                                <td class="text-center">关闭</td>
                                <td class="text-center">${product.productStatusStr}</td>
                                <td class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs">订单</button>
                                    <button type="button" class="btn bg-olive btn-xs">详情</button>
                                    <button type="button" class="btn bg-olive btn-xs">编辑</button>
                                </td>
                            </tr>--%>

                            </tbody>
                        </table>
                        <!--数据列表/-->



                    </div>
                    <!-- 数据表格 /-->


                </div>
                <!-- /.box-body -->

                <!-- .box-footer-->
                <div class="box-footer">
                    <div class="pull-left" id="pull_left">
                        <div class="form-group form-inline">
                            总共<span id="pageTotals">2</span> 页，共<span id="totolRecords">14</span> 条数据。 每页
                            <select class="form-control" id="pageSize" onchange="show(1,this.value);isFirst=true">
                            </select> 条
                        </div>
                    </div>
                    <div class="box-tools pull-right" id="Pagination">
                        <ul class="pagination">
                            <%--<li><a href="#" aria-label="Previous">首页</a></li>
                            <li><a href="#">上一页</a></li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#">下一页</a></li>
                            <li><a href="#" aria-label="Next">尾页</a></li>--%>
                        </ul>
                    </div>
                </div>
                <!-- /.box-footer-->


            </div>

        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- @@close -->
    <!-- 内容区域 /-->

    <!-- 底部导航 -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.8
        </div>
        <strong>Copyright &copy; 2014-2017 <a href="http://www.hwua.com">上海海文信息技术有限公司</a>.</strong> All rights reserved.
    </footer>
    <!-- 底部导航 /-->

</div>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<script src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
<script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
<script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
    $(document).ready(function () {
        // 选择框
        $(".select2").select2();

        // WYSIHTML5编辑器
        $(".textarea").wysihtml5({
            locale: 'zh-CN'
        });
    });
    // 设置激活菜单
    function setSidebarActive(tagUri) {
        var liObj = $("#" + tagUri);
        if (liObj.length > 0) {
            liObj.parent().parent().addClass("active");
            liObj.addClass("active");
        }
    }
$(document).ready(function () {

        // 激活导航位置
        setSidebarActive("admin-datalist");
    });


</script>
</body>

</html>
