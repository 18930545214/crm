<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
				<shiro:authenticated>
					<shiro:principal property="username"></shiro:principal>
				</shiro:authenticated>
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<li class="treeview">
				<shiro:hasRole name="admin">
				<a href="#"> <i class="fa fa-cogs"></i>
					<span>系统管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				</shiro:hasRole>
				<ul class="treeview-menu">
					<li id="system-setting">
						<a
						href="${pageContext.request.contextPath}/pages/user-list.jsp"> <i
							class="fa fa-circle-o"></i> 用户管理
					</a>
					</li>
					<li class="system-setting"><a
						href="${pageContext.request.contextPath}/pages/role-list.jsp"> <i
							class="fa fa-circle-o"></i> 角色管理
					</a></li>
					<li class="system-setting"><a
						href="${pageContext.request.contextPath}/pages/permission-list.jsp">
							<i class="fa fa-circle-o"></i> 资源权限管理
					</a></li>
					<li class="system-setting">
						<a
						href="${pageContext.request.contextPath}/syslog/queryAll"> <i
							class="fa fa-circle-o"></i> 访问日志
					</a>
					</li>
				</ul>

			</li>

			<li class="treeview">
				<shiro:hasRole name="admin">
				<a href="#"> <i class="fa fa-cube"></i>
					<span>基础数据</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				</shiro:hasRole>
				<ul class="treeview-menu">

					<li class="system-setting"><a
						href="${pageContext.request.contextPath}/pages/product-list.jsp">
							<i class="fa fa-circle-o"></i> 产品管理
					</a></li>
					<!-- 当前用户是否拥有该角色，有就显示相关信息 -->

					<li class="system-setting">
						<shiro:hasRole name="admin">
							<%--<a href="admin.jsp">Admin Page</a><br><br>--%>

						<a href="${pageContext.request.contextPath}/pages/orders-list.jsp"> <i
							class="fa fa-circle-o"></i> 订单管理
					</a>
						</shiro:hasRole></li>

				</ul></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>