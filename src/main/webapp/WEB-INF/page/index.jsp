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
							<input type="text" name="a.name" value="${a.name }" />
							<input type="text" name="a.group" value="${a.group }" />
							<input type="text" name="a.curr" value="${a.curr }" />
							<input type="text" name="a.all" value="${a.all }" />
							<input type="text" name="a.season" value="${a.season }" />
							<input type="text" name="a.status" value="${a.status }" />
							<button type="submit">save</button>
						</form>
					</div>
					<div class="row">
						<form class="form" action="${ctx }/anime/save/group" method="post">
							<input type="hidden" name="g.id" value="${g.id }" />
							<input type="hidden" name="g.pinyinPref" value="${g.pinyinPref }" />
							<input type="text" name="g.name" value="${g.name }" />
							<button type="submit">save</button>
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