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
				
			});
			function resetSearchForm(button){
				var $form = $(button).parent();
				$form.find('input').val('');
				$form.find('select').val('');
			};
			function resetSaveForm(button){
				var $form = $(button).parent();
				$form.find('input').val('');
				$form.find('select').val('');
				$form.find('input').parent().parent().removeClass('has-error');
				$form.find('select').parent().parent().removeClass('has-error');
			};
			var setting = {
				rules : {
					'u.username' : {
						required : true
					},
					'u.password' : {
						required : true
					}
				}
			};
		</script>
	</head>
	<body>
		<div style="height:10px;"></div>
		<div class="container">
			<div class="col-sm-12">
				<ul class="nav nav-tabs" id="tabs">
					<li class="active"><a class="tab-login" href="#login" data-toggle="tab">login</a></li>
				</ul>
			</div>
			<div class="tab-content">
				<div class="tab-pane active">
					<div class="row">
						<div class="col-sm-3 col-cont">
							<form class="form-horizontal" id="loginForm" action="${ctx }/login" method="post">
								<div class="form-group">
									<label class="col-sm-3 control-label" for="loginForm_name">username</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="loginForm_username" name="u.username" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="loginForm_password">password</label>
									<div class="col-sm-8">
										<input type="password" class="form-control" id="loginForm_password" name="u.password" />
									</div>
								</div>
								<button type="submit" class="btn btn-default">Login</button>
								<button type="button" class="btn btn-default" onclick="resetSaveForm(this)">Reset</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>