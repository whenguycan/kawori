<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="script.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			.col-cont{padding-top: 10px;}
		</style>
		<script type="text/javascript">
			var ctx = "${ctx}";
			$(function(){
				initSelect();
			});
			var pageUrl = "${ctx}/anime/group";
			function go(pageNo){
				pageGo(pageNo, pageUrl);
			};
			var setting = {
				rules : {
					'g.name' : {
						required : true
					}
				}
			};
			function buttonClick(button){
				formSubmit($(button), setting, function(data){
					if(data.status == false){
						alert(data.msg);
					}else{
						go(1, pageUrl);
					}
				});
			};
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
							<form class="form-horizontal" action="${ctx }/anime/save/group" method="post">
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
								<button type="button" class="btn btn-default" onclick="buttonClick(this)">save</button>
							</form>
						</div>
						<div class="col-sm-9 col-cont">
							<table class="table">
								<tr>
									<td>id</td>
									<td>name</td>
									<td>pinyinPref</td>
								</tr>
								<c:forEach items="${page.result }" var="item">
									<tr>
										<td>${item.id }</td>
										<td>${item.name }</td>
										<td>${item.pinyinPref }</td>
									</tr>
								</c:forEach>
								<c:if test="${fn:length(page.result) != page.pageSize}">
									<c:forEach var="x" begin="0" end="${page.pageSize - fn:length(page.result) - 1}">
										<tr style="height: 37px;"><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
									</c:forEach>
								</c:if>
							</table>
							<nav aria-label="...">
								<ul class="pagination">
									${page.pagination }
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>