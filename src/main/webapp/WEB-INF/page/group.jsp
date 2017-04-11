<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-3.3.7-dist/css/bootstrap.css"/>
		<script type="text/javascript" src="${ctx }/static/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="${ctx}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
		<script type="text/javascript" src="${ctx }/static/form.js"></script>
		<style type="text/css">
			.col-cont{padding-top: 10px;}
		</style>
		<script type="text/javascript">
			$(function(){
				
			});
		</script>
	</head>
	<body>
		<div style="height:10px;"></div>
		<div class="container">
			<div class="col-sm-12">
				<ul class="nav nav-tabs" id="tabs">
					<li class="active"><a class="tab-group" href="#group" data-toggle="tab">group</a></li>
				</ul>
			</div>
			<div class="tab-content">
				<div class="tab-pane active tab-group">
					<div class="row">
						<div class="col-sm-3 col-cont">
							<form id="form-group" class="form-horizontal" action="${ctx }/anime/save/group" method="post">
								<input type="hidden" name="g.id" />
								<div class="form-group">
									<label class="col-sm-3 control-label" for="g_name">name</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="g_name" name="g.name" value="${g.name }" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="g_pinyinPref">pinyinPref</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="g_pinyinPref" name="g.pinyinPref" value="${g.pinyinPref }" />
									</div>
								</div>
								<button type="button" class="btn btn-default" onclick="formSave(this)">save</button>
							</form>
						</div>
						<div class="col-sm-9 col-cont">
							<table id="table-group" class="table">
								<tr>
									<td>id</td>
									<td>name</td>
									<td>pinyinPref</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="row">
						<nav aria-label="...">
							<ul id="pager-group" class="pager">
								<li><a href="#">Previous</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">Next</a></li>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>