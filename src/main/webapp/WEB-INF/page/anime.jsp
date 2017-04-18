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
			var pageUrl = ctx + "/anime/index";
			$(function(){
				$('tr').on('click', function(){
					
				});
			});
			function go(pageNo){
				pageGo(pageNo, pageUrl);
			};
			function pageGo(pageNo, pageUrl){
				var action = pageUrl + "/" + pageNo;
				$('#searchForm').attr('action', action);
				$('#searchForm').submit();
			};
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
			function edit(a, formId){
				var $tds = $(a).parent().siblings();
				$.each($tds, function(i, e){
					var tag = $(e).attr('for');
					if(tag!=undefined&&tag!=''){
						$('#'+formId+'_'+tag).val($(e).html());
					}
				});
			};
			function del(a){
				if(window.confirm("确定删除？")){
					var $tds = $(a).parent().siblings();
					var url = ctx + "/anime/del/" + $($tds[0]).html();
					window.location.href = url;
				}
			}
			var setting = {
				rules : {
					'a.name' : {
						required : true
					},
					'a.curr' : {
						required : true,
						number : true
					},
					'a.all' : {
						required : true,
						number : true
					},
					'a.season' : {
						required : true
					},
					'a.status' : {
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
					<li class="active"><a class="tab-anime" href="#anime" data-toggle="tab">anime</a></li>
				</ul>
			</div>
			<div class="tab-content">
				<div class="tab-pane active">
					<div class="row">
						<div class="col-sm-3 col-cont">
							<form class="form-horizontal" id="editForm" action="${ctx }/anime/save" method="post">
								<div class="form-group">
									<label class="col-sm-3 control-label" for="editForm_id">id</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="editForm_id" name="a.id" readonly="readonly"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="editForm_creator">creator</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="editForm_creator" name="a.creator" readonly="readonly"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="editForm_name">name</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="editForm_name" name="a.name" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="editForm_curr">curr</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="editForm_curr" name="a.curr" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="editForm_all">all</label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="editForm_all" name="a.all" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="editForm_season">season</label>
									<div class="col-sm-8">
										<select class="form-control" id="editForm_season" name="a.season">
											${selectSeason }
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label" for="editForm_status">status</label>
									<div class="col-sm-8">
										<select class="form-control" id="editForm_status" name="a.status">
											${selectStatusSave }
										</select>
									</div>
								</div>
								<button type="button" class="btn btn-default" onclick="buttonClick(this)">Save</button>
								<button type="button" class="btn btn-default" onclick="resetSaveForm(this)">Reset</button>
							</form>
							<div class="col-cont"></div>
							<form class="form-horizontal" id="uploadForm" action="${ctx }/anime/upload" method="post" enctype="multipart/form-data">
								<div class="form-group">
									<label class="col-sm-3 control-label" for="editForm_file">file</label>
									<div class="col-sm-8">
										<input type="file" class="file" id="editForm_file" name="uploadFile" />
									</div>
								</div>
								<button type="submit" class="btn btn-default">Upload</button>
								<button type="button" class="btn btn-default" onclick="resetSaveForm(this)">Reset</button>
							</form>
							<div class="col-cont">
								<a class="btn btn-default" href="${ctx }/anime/download">Download</a>
							</div>
						</div>
						<div class="col-sm-9 col-cont">
							<nav class="navbar navbar-default">
								<div class="collapse navbar-collapse">
									<form class="navbar-form navbar-left" id="searchForm" action="" method="post">
										<div class="form-group">
											<input type="text" class="form-control" name="s.LIKE_name" placeholder="name" value="${s.LIKE_name }"/>
										</div>
										<div class="form-group">
											<select class="form-control" name="s.EQ_status" value="${s.EQ_status }">
												${selectStatusSearch }
											</select>
										</div>
        								<button type="button" class="btn btn-default" onclick="go(1)">Search</button>
        								<button type="button" class="btn btn-default" onclick="resetSearchForm(this)">Reset</button>
									</form>
								</div>
							</nav>
							<table class="table">
								<tr>
									<td>id</td>
									<td>name</td>
									<td>curr</td>
									<td>all</td>
									<td>season</td>
									<td>status</td>
									<td>creator</td>
									<td>operation</td>
								</tr>
								<c:forEach items="${page.result }" var="item">
									<tr>
										<td for="id">${item.id }</td>
										<td for="name">${item.name }</td>
										<td for="curr">${item.curr }</td>
										<td for="all">${item.all }</td>
										<td for="season">${item.season }</td>
										<td for="status">${item.status }</td>
										<td for="creator">${item.creator }</td>
										<td>
											<a href="#" onclick="edit(this,'editForm')">修改</a>
											<a href="#" onclick="del(this)">删除</a>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${fn:length(page.result) != page.pageSize}">
									<c:forEach var="x" begin="0" end="${page.pageSize - fn:length(page.result) - 1}">
										<tr style="height: 37px;"><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
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