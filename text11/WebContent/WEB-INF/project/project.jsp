<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Insert title here</title>

<style>
#pro {
	width: 800px;
	margin: 20px auto;
}

#pro .select {
	background: #337ab7
}

#pro .select1 {
	background: red;
}

td {
	width: 30px;
	height: 20px；
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	$()
		.ready(
 					function() {
						var selectId = -1;

 		 			$("#showadd").click(function() {
						location.href = "pro?type=showadd";

						})
// 						$("#showupdate")
// 								.click(
// 										function() {
// 											if (selectId > -1) {
// 												location.href = "pro?type=showupdate&&id="
// 														+ selectId;
// 											} else {
// 												alert("请选中");
// 											}

// 										})

						$(".abc").click(function() {

							$(this).toggleClass("select");
							selectId = $(this).data("id");
						})
				
						
						$("#update")
								.click(
										function() {
											var length = ($("#pro .select").length);
											var ids = "";
											if (length > 0) {
												$("#pro .select")
														.each(
																function(index,
																		element) {
																	ids += $(
																			this)
																			.data("id")
																			+ ",";

																})
												ids = ids.substring(0,ids.length - 1);

												location.href = "pro?type=showupdate&&ids="+ids;

											} else {
												alert("plese select");
											}

										})
						
						
						
						
						
						
						
						
						
						
						
						
						$("#deletebatch")
								.click(
										function() {
											var length = ($("#pro .select").length);
											var ids = "";
											if (length > 0) {
												$("#pro .select")
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

												location.href = "pro?type=deletebatch&&ids="
														+ ids;

											} else {
												alert("plese select");
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
											var length = ($("#pro .select1").length);
											var array = new Array();
										
											if (length > 0) {
												
												$("#pro .select1")
														.each(
																function(index,
															element) {
																	var id =$(this)
																			.data("id");
																	    
																	var name = $(
																			this)
																			.find("[name=name1]")
																			.val();

																	 var pro={
																			 id:id,
																			 name:name
																	 }
																	array
																			.push(pro);

																})
															

											var pros= JSON.stringify(array);
												pros=pros.replace(/{/g,"%7b");
												pros=pros.replace(/}/g,"%7d");
												location.href="pro?type=updatabatch3&&pros="+pros;  
											} else {
												alert("请选中");
											}

										})
										
					 
							if(${p.ye}<=1){
					 			$("#pre").addClass("disabled");
								$("#pre").find("a").attr("oncilk","return false")
								
						}
							 
				 	
							if(${p.ye}>=${p.maxYe}){
								$("#next").addClass("disabled");
								$("#next").find("a").attr("oncilk","return false")
								
							}
							
							
							
									
     
					})
</script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<div id="pro">
<div>
   <form class="form-inline" role="form" action="pro">
    <label >项目:</label>
    <input type="text" class="form-control" name="searchName" value="${c.name }" placeholder="请输入名称">
    <input type="submit"   class="form-control"   value='搜索'/>
</form>
<br/>
<br/>
</div>
		<table class="table table-bordered table-striped table-hover">
			<tr>
			<thead>
				<th>id</th>
				<th>项目</th>
				
				
				
			</thead>
			</tr>
			<c:forEach items="${list}" var="pro">
			<tr data-id=${pro.id} class="abc">
			         <td name="id">${pro.id}</td>
				<td name="name">${pro.name}</td>
				
			</tr>
		 </c:forEach>
		</table>
		
		<ul class="pagination">
	<li id="pre"><a href="pro?ye=${p.ye-1}&searchName=${c.name }">上一页</a></li>
     <c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
	<li <c:if test="${p.ye==status.index}"> class="active" </c:if>><a
	 href="pro?ye=${status.index}&searchName=${c.name }"> ${status.index}</a></li>
	</c:forEach>
	<li id="next"><a href="pro?ye=${p.ye+1}&searchName=${c.name }">下一页</a></li>
</ul>
		
		
	 	<div>
		<button id="showadd" type="button" class="btn btn-primary">添加</button>
		<button id="update" type="button" class="btn btn-primary">修改</button>
		<button id="deletebatch" type="button" class="btn btn-primary">删除</button>
		


</div>
	</div>









</body>
</html>