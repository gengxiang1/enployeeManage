<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Insert title here</title>

<style>
#dep {
	width: 800px;
	margin: 20px auto;
}

#dep .select {
	background: #337ab7
}

#dep .select1 {
	background: red;
}

td {
	width: 30px;
	height: 20px；
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<script>
	$()
		.ready(
 					function() {
						var selectId = -1;
						var ids = "";
						
						
						
						function transid(){
							
							$("#dep .select")
									.each(
											function(index,
													element) {
												ids += $(
														this)
														.data(
																"id")
														+ ",";

											})
							ids = ids.substring(0,
									ids.length - 1);

							

						
						}
						
						
		

 						$("#showadd").click(function() {
						location.href = "dep?type=showadd";

						})

						$(".abc").click(function() {

							$(this).toggleClass("select");
							selectId = $(this).data("id");
						})
						
						$("#deletebatch")
								.click(
										function() {
											var length = ($("#dep .select").length);
											
											if (length <= 0) {
												alert("请选中");
											}else{
                                                      transid();
												location.href = "dep?type=deletebatch&&ids="
														+ ids;

											} 
										})
					

										
						$(".abc")
								.dblclick(
										function() {
											$(this).unbind("dblclick");
											$(this).unbind("click");
											var name = $(this).find(
													"[name=name]").text();
											var id= $(this).find(
											"[name=id]").text();
											$(this).addClass("select1");
											$(this)
											.children()
											.eq(0)
											.html(
										"<input type='text' name=id1  value="+id+" />")

											$(this)
													.children()
													.eq(1)
													.html(
												"<input type='text' name=name1  value="+name+" />")

										})

						$("#updatebatch")
								.click(
										function() {
											var length = ($("#dep .select1").length);
											var array = new Array();
										
											if (length > 0) {
												
												$("#dep .select1")
														.each(
																function(index,
															element) {
																	var id =$(this)
																			.data("id");
																	    
																	var name = $(
																			this)
																			.find("[name=name1]")
																			.val();

																	 var dep={
																			 id:id,
																			 name:name
																	 }
																	array
																			.push(dep);

																})
															

											var deps= JSON.stringify(array);
												deps=deps.replace(/{/g,"%7b");
												deps=deps.replace(/}/g,"%7d");
												location.href="dep?type=updatabatch3&&deps="+deps;  
											} else {
												alert("请选中");
											}

										})
										
					
							
							
							$("#manage").click(function(){
								
								var length = ($("#dep .select").length);
								
								if (length== 1) {
									var id=$("#dep .select").data("id");
									var name = $("#dep .select").find("[name=name]").text();
									location.href = "dep?type=showManage&&id="+ id;
									
								}else{
									alert("只选一个"); 

								} 
								
						})
								$("#manage1").click(function(){
								
								var length = ($("#dep .select").length);
								
								if (length== 1) {
									var id=$("#dep .select").data("id");
									var name = $("#dep .select").find("[name=name]").text();
									location.href = "dep?type=showManage1&&id="+ id;
									
								}else{
									alert("只选一个"); 

								} 
								
						})
							
								$("#manage3").click(function(){
								
								var length = ($("#dep .select").length);
								
								if (length== 1) {
									var id=$("#dep .select").data("id");
									var name = $("#dep .select").find("[name=name]").text();
									location.href = "dep?type=showManage3&&id="+ id;
									
								}else{
									alert("只选一个"); 

								} 
								
						})
						$("#manage2").click(function(){
							
							var length = ($("#dep .select").length);
							
							if (length== 1) {
								var id=$("#dep .select").data("id");
								
								 location.href = "dep?type=showManage2&&id="+id;
								
							}else{
								alert("只选一个"); 

							} 
							
					})
					
			   
			
 					$("#manage4").click(function(){
						
 						var length = ($("#dep .select").length);
						
						if (length== 1) {
 						var id=$("#dep .select").data("id");
 						var url="dep?type=showManage4&&id="+id;
 						$("#myModal").modal();
 						$("#modalpro").load(url);
 						
							
					}else{
 							alert("只选一个"); 
						} 
						
			})
					
				
				
				
 					 }); 				
									
     
					
</script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<div id="dep">
<div>
   <form class="form-inline" role="form" action="dep">
    <label >部门:</label>
    <input type="text" class="form-control" name="searchName" value="${c.name }" placeholder="请输入名称">
    <input type="text" class="form-control" name="searchNum" value='${c.num !=-1?c.num:""}'placeholder="请输入个数">
    <input type="submit"   class="form-control"   value='搜索'/>
</form>
<br/>
<br/>
</div>


	
	
		<table class="table table-bordered table-striped table-hover">
			<tr>
			<thead >
				<th>id</th>
				<th>部门</th>
				<th>个数</th>
				
				
			</thead>
			</tr>
			<c:forEach items="${list}" var="dep">
			<tr data-id=${dep.id} class="abc">
			         <td name="id">${dep.id}</td>
				<td name="name">${dep.name}</td>
				<td name="num" ><a href="emp?searchDepName=${dep.name}">${dep.num}</a></td>
				
			</tr>
		 </c:forEach>
		</table>
		
		<ul class="pagination">
	<li id="pre"><a href="dep?ye=${p.ye-1}&searchName=${c.name }&searchNum=${c.num !=-1?c.num:""}">上一页</a></li>
     <c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
	<li <c:if test="${p.ye==status.index}"> class="active" </c:if>><a
	 href="dep?ye=${status.index}&searchName=${c.name }&searchNum=${c.num !=-1?c.num:""}"> ${status.index}</a></li>
	</c:forEach>
	<li id="next"><a href="dep?ye=${p.ye+1}&searchName=${c.name }&searchNum=${c.num !=-1?c.num:""}">下一页</a></li>
</ul>
		
		
		<div>
		<button id="showadd" type="button" class="btn btn-primary">添加</button>
		<button id="updatebatch" type="button" class="btn btn-primary">双击修改</button>
		<button id="deletebatch" type="button" class="btn btn-primary">删除</button>
		<button id="manage" type="button" class="btn btn-primary">项目管理</button>
		<button id="manage1" type="button" class="btn btn-primary">项目管理1</button>
		<button id="manage2" type="button" class="btn btn-primary">项目管理2批量</button>
       <button id="manage3" type="button" class="btn btn-primary">项目管理拖动</button>
   <button id="manage4"  type="button" class="btn btn-primary btn-lg" data-toggle="modal"data- targe="#myModal">项目管理有彈窗</button>
</div>
	</div> 

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					模态框（Modal）标题
				</h4>
			</div>
			<div class="modal-body" id="modalpro">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->

</body>
</html>