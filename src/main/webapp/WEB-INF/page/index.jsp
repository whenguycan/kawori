<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/bootstrap-3.3.7-dist/css/bootstrap.css"/>
		<script type="text/javascript" src="${ctx }/static/jquery-1.9.1.js"></script>
	</head>
	<body>
		<div class="container" style="height:10px;"></div>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<div class="row">
						<form class="form" action="${ctx }/anime/save/anime" method="post">
							<input type="hidden" name="a.id" value="${a.id }" />
							<div class="form-group">
								<label for="a_name">name</label>
								<input type="text" class="form-control" id="a_name" name="a.name" value="${a.name }" />
							</div>
							<div class="form-group">
								<label for="a_group">group</label>
								<input type="text" class="form-control" id="a_group" name="a.group" value="${a.group }" />
							</div>
							<div class="form-group">
								<label for="a_curr">curr</label>
								<input type="text" class="form-control" id="a_curr" name="a.curr" value="${a.curr }" />
							</div>
							<div class="form-group">
								<label for="a_all">all</label>
								<input type="text" class="form-control" id="a_all" name="a.all" value="${a.all }" />
							</div>
							<div class="form-group">
								<label for="a_season">season</label>
								<input type="text" class="form-control" id="a_season" name="a.season" value="${a.season }" />
							</div>
							<div class="form-group">
								<label for="a_status">status</label>
								<input type="text" class="form-control" id="a_status" name="a.status" value="${a.status }" />
							</div>
							<button type="submit" class="btn btn-default">save</button>
						</form>
					</div>
					<div class="row">
						<form class="form" action="${ctx }/anime/save/group" method="post">
							<input type="hidden" name="g.id" value="${g.id }" />
							<div class="form-group">
								<label for="g_name">name</label>
								<input type="text" class="form-control" id="g_name" name="g.name" value="${g.name }" />
							</div>
							<div class="form-group">
								<label for="g_pinyinPref">pinyinPref</label>
								<input type="text" class="form-control" id="g_pinyinPref" name="g.pinyinPref" value="${g.pinyinPref }" />
							</div>
							<button type="submit" class="btn btn-default">save</button>
						</form>
					</div>
				</div>
				<div class="col-sm-3">
					<table class="table">
						<tr>
							<td>id</td>
							<td>name</td>
						</tr>
						<c:forEach items="${groups }" var="item">
							<tr>
								<td>${item.id }</td>
								<td>${item.name }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="col-sm-6">
					<table class="table">
						<tr>
							<td>id</td>
							<td>name</td>
						</tr>
						<c:forEach items="${animes }" var="item">
							<tr>
								<td>${item.id }</td>
								<td>${item.name }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>