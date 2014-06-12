<%@ page language="java" pageEncoding="UTF-8"%>
<style type="text/css">
.menu {
	z-index: 10002;
	width: 150px;
	display: none;
	position: absolute;
	right: 5px;
	top: 26px;
	-moz-border-radius: 15px;
	-khtml-border-radius: 15px;
	-webkit-border-radius: 15px;
	border-radius: 15px;
	-moz-box-shadow: 3px 3px 3px rgba(0, 0, 0, .1);
	-webkit-box-shadow: 3px 3px 3px rgba(0, 0, 0, .1);
	box-shadow: 3px 3px 3px rgba(0, 0, 0, .1);
	background: #F3F3F3;
	border: 1px solid #C9C9C9;
	border-top: 0;
}

.menu ul {
	list-style: none;
	margin: 0px;
	margin-top: 10px;
	margin-bottom: 10px;
}

.menu ul li {
	margin: 5px 0 5px 0;
	padding-left: 10px;
	padding-right: 10px;
	font-size: 14px;
	font-family: 微软雅黑;
	line-height: 18px;
	height: 18px;
}

.menu ul li.menuDiv {
	border-bottom: 2px solid #C9C9C9;
	margin: 0 0 2px 0;
	height: 2px;
	line-height: 2px;
}

.menu ul li a,.menu ul li a:visited {
	display: block;
	color: black;
	padding: 0px 0px 0px 20px;
	text-decoration: none;
	background: url('static/images/menu/menu_page_process.png') no-repeat;
}

.menu ul li a.home,.menu ul li a.home:visited {
	background: url('static/images/menu/menu_home.png') no-repeat;
}

.menu ul li a.exit,.menu ul li a.exit:visited {
	background: url('static/images/menu/menu_exit.png') no-repeat;
}

.menu ul li a:hover,.menu ul li a:hover.exit,.menu ul li a.home:hover {
	color: rgb(173, 173, 173);
	background: url('static/images/menu/menu_next.png') no-repeat;
}
</style>

<script type="text/javascript">
	$(function() {
		$(".menu_index").on("mouseover", function() {
			if ($(".menu").is(":animated")) { //判断元素是否正处于动画状态
				$(".menu").stop(true, true);
			}
			$(".menu").slideDown(800);
		});
		$(".menu_index").on("mouseleave", function() {
			$(".menu").slideUp(800);
		});
	});
</script>

<div class="menu_index" style="display: none;">
	<div class="index"></div>
	页面索引
	<div class="menu" style="display: block; display: none;">
		<ul>
			<li><a href="web" target="_blank">首页</a></li>
			<li class="menuDiv"></li>
			<li><a href="column">栏目定义</a></li>
			<li class="menuDiv"></li>
			<li><a href="dict/list">数据字典</a></li>
			<li><a href="user/list">用户管理</a></li>
			<li class="menuDiv"></li>
			<li><a class="exit" href="logout">退出</a></li>
		</ul>
	</div>
</div>