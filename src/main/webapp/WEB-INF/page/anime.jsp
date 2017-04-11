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
			var ctx = "${ctx}";
			$(function(){
				
			});
			function go(pageNo){
				var url = ctx + "/anime/anime/" + pageNo;
				window.location.href = url;
			};
			function formSave(button){
				var $form = $(button).parent();
				if(myValidate($form, setting) == true){
					var url = $form.attr('action');
					var data = $form.serialize();
					$.ajax({
						url : url,
						type : 'post',
						data : data,
						dataType : 'json',
						success : function(res){
							console.debug(res);
						},
						error : function(msg){
							
						}
					});
				}
			};
		</script>
	</head>
	<body>
		<div style="height:10px;"></div>
		<div class="container">
			<div class="col-sm-12">
				<ul class="nav nav-tabs" id="tabs">
					<li class="active"><a class="tab-anime" href="#anime" data-toggle="tab">anime</a></li>
				</ul>
			</div>
			<div class="tab-content">
				<div class="tab-pane active">
					<div class="row">
						<div class="col-sm-3 col-cont">
							<form class="form-horizontal" action="${ctx }/anime/save/anime" method="post">
								<input type="hidden" name="a.id" />
								<div class="form-group">
									<label class="col-sm-3 control-label" for="a_name">name</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="a_name" name="a.name" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="a_group">group</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="a_group" name="a.group" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="a_curr">curr</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="a_curr" name="a.curr" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="a_all">all</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="a_all" name="a.all" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="a_season">season</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="a_season" name="a.season" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="a_status">status</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="a_status" name="a.status" />
									</div>
								</div>
								<button type="button" class="btn btn-default" onclick="formSave(this)">save</button>
							</form>
						</div>
						<div class="col-sm-9 col-cont">
							<table class="table">
								<tr>
									<td>id</td>
									<td>name</td>
									<td>group</td>
									<td>curr</td>
									<td>all</td>
									<td>season</td>
									<td>status</td>
								</tr>
								<c:forEach items="${page.result }" var="item">
									<tr>
										<td>${item.id }</td>
										<td>${item.name }</td>
										<td>${item.group }</td>
										<td>${item.curr }</td>
										<td>${item.all }</td>
										<td>${item.season }</td>
										<td>${item.status }</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<div class="row">
						<nav aria-label="...">
							<ul class="pager">
								${page.pager }
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>