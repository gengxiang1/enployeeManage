<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Insert title here</title>

<style>
#emp {
	width: 900px;
	margin: 20px auto;
}

#emp .select {
	background: #337ab7
}

#emp .select1 {
	background: red;
}

td {
	width: 30px;
	height: 20px;
}
td img{
width: 60px;
height: 30px;
}
#bigPic{
width: 300px;
height: 200px;
position:absolute;
display:none;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	$()
			.ready(
					function() {
						var selectId = -1;

						$("#showadd").click(function() {
							location.href = "emp?type=showadd";

						})
						
						$("#showadd2").click(function() {
							location.href = "emp?type=showadd2";

						})
						$("#showupdate")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "emp?type=showupdate&&id="
														+ selectId;
											} else {
												alert("请选中");
											}

										})

						$(".abc").click(function() {

							$(this).toggleClass("select");
							selectId = $(this).data("id");
						})
						$("#delete").click(
								function() {
									if (selectId > -1) {
										location.href = "emp?type=delete&&id="
												+ selectId;
									} else {
										alert("请选中");
									}
								})

						$("#deletebatch")
								.click(
										function() {
											var length = ($("#emp .select").length);
											var ids = "";
											if (length > 0) {
												$("#emp .select")
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

												location.href = "emp?type=deletebatch&&ids="
														+ ids;

											} else {
												alert("plese select");
											}

										})
						$("#updatebatch1")
								.click(
										function() {
											var length = ($("#emp .select").length);
											var ids = "";
											if (length > 0) {
												$("#emp .select")
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
												location.href = "emp?type=showupdatabatch1&&ids="
														+ ids;

											} else {
												alert("plese select");
											}

										})
						$("#updatebatch2")
								.click(
										function() {
											var length = ($("#emp .select").length);
											var ids = "";
											if (length > 0) {
												$("#emp .select")
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
												location.href = "emp?type=showupdatabatch2&&ids="
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
											var sex = $(this)
													.find("[name=sex]").text()
											var age = $(this)
													.find("[name=age]").text()

											$(this).addClass("select1");

											$(this)
													.children()
													.eq(0)
													.html(
															"<input type='text' name=name1  value="+name+" />")

											if (sex == "男") {
												$(this)
														.children()
														.eq(1)
														.html(
																"<select name='sex1'><option value='男' selected>男</option><option  value='女'>女</option></select>")
											} else {
												$(this)
														.children()
														.eq(1)
														.html(
																"<select name='sex1'><option value='男'>男</option><option  value='女' selected >女</option></select>")
											}

											$(this)
													.children()
													.eq(2)
													.html(
															"<input type='text' name=age1  value="+age+" />")

											/*	alert(!($(this).css("background")=="red"));
												if($(this).css("background")!="red"){
												
													
													
												$(this).addClass("select1");
												 var name=$(this).find("[name=name]").html()
											   var sex=$(this).find("[name=sex]").html()
												var age=$(this).find("[name=age]").html()
												alert(name)
												$(this).find("[name=name]").html("<input type='text' name=name1  value="+name+" />")
												$(this).find("[name=sex]").html(("<input type='text' name=sex1  value="+sex+" />"))
												$(this).find("[name=age]").html("<input type='text' name=age1   value="+age+" />")
											  
												}*/
										})

						$("#updatebatch3")
								.click(
										function() {
											var length = ($("#emp .select1").length);
											var array = new Array();
											if (length > 0) {
												$("#emp .select1")
														.each(
																function(index,
																		element) {
																	var id = $(
																			this)
																			.data(
																					"id");
																	var name = $(
																			this)
																			.find(
																					"[name=name1]")
																			.val();

																	var sex = $(
																			this)
																			.find(
																					"[name=sex1]")
																			.val();

																	var age = $(
																			this)
																			.find(
																					"[name=age1]")
																			.val();
																	var emp = {
																		id:id,
																		name:name,
																		sex:sex,
																		age:age
																	}
																	array
																			.push(emp);

																})

											var emps= JSON.stringify(array);
												emps=emps.replace(/{/g,"%7b");
												emps=emps.replace(/}/g,"%7d");
												location.href="emp?type=updatabatch3&&emps="+emps;  
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
							
			 				

							$("td img").hover(function(event){
								
								var img=$(this).attr("src");
								$("#bigPic").attr("src",img);
								$("#bigPic").show();
								$("#bigPic").css({left:event.pageX+10,top:event.pageY+10})
							},function(){
								
								$("#bigPic").hide();
								
							})	
									
     
					})
</script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<div id="emp">
<div>
   <form class="form-inline" role="form" action="emp">
    <input type="hidden" name="type" value="search"/>
    <label >名称:</label>
    <input type="text" class="form-control" name="searchName" value="${c.name }" placeholder="请输入名称">
    <label>性别:</label>
   <select class="form-control" name="searchSex"> 
    <option value=''>请选择性别</option>
   <option value='男'<c:if test="${c.sex=='男'}">selected</c:if>> 男 </option>
    <option value='女'<c:if test="${c.sex=='女'}">selected</c:if>> 女 </option>
    
   </select>
<label>年龄:</label>
  <input type="text" class="form-control" name="searchAge" placeholder="请输入年龄" value="${c.age!=-1?c.age:"" }"/>
  <label>部门:</label>
   <select class="form-control" name="searchDepName"> 
    <option value=''>请选择部门</option>
  <c:forEach items="${depList}" var="dep">
   <option value="${dep.name}" <c:if test="${dep.name eq c.dep.name}"> selected </c:if>> ${dep.name} </option>
    
  </c:forEach>
   </select>
  <input type="submit" value="搜索"/>
  
</form>
<br/>
<br/>
</div>


	
	
		<table class="table table-bordered table-striped table-hover">
			<tr>
			<thead>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>部门 </th>
			  <th>图片 </th>
			</thead>
			</tr>
			<c:forEach items="${list}" var="emp">
			<tr data-id=${emp.id} class="abc">
				<td name="name">${emp.name}</td>
				<td name="sex">${emp.sex}</td>
				<td name="age">${emp.age}</td>
			 	<td name="depName">${emp.dep.name}</td>
	      <td name="img"><c:if test="${ not empty emp.img }"><img src="/img/${emp.img}"/></c:if></td>
			</tr>
		 </c:forEach>
		</table>
		
		<ul class="pagination">
			<li><a href="emp?ye=1&searchName=${c.name }&searchSex=${c.sex }&searchAge=${c.age!=-1?c.age:""}&searchDepName=${c.dep.name }">首页</a></li>
		
	<li id="pre"><a href="emp?ye=${p.ye-1}&searchName=${c.name }&searchSex=${c.sex }&searchAge=${c.age!=-1?c.age:"" }&searchDepName=${c.dep.name  }">上一页</a></li>
     <c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
	<li <c:if test="${p.ye==status.index}"> class="active" </c:if>><a
	 href="emp?ye=${status.index}&searchName=${c.name }&searchSex=${c.sex }&searchAge=${c.age!=-1?c.age:"" }&searchDepName=${c.dep.name }"> ${status.index}</a></li>
	</c:forEach>
	<li id="next"><a href="emp?ye=${p.ye+1}&searchName=${c.name }&searchSex=${c.sex }&searchAge=${c.age!=-1?c.age:""}&searchDepName=${c.dep.name }">下一页</a></li>
	<li><a href="emp?ye=${p.maxYe}&searchName=${c.name }&searchSex=${c.sex }&searchAge=${c.age!=-1?c.age:""}&searchDepName=${c.dep.name }">尾页</a></li>
</ul>
		
		
		<div>
		<button id="showadd" type="button" class="btn btn-primary">添加</button>
		<button id="showadd2" type="button" class="btn btn-primary">添加2</button>
		<button id="showupdate" type="button" class="btn btn-primary">修改</button>
		<button id="delete" type="button" class="btn btn-primary">删除</button>
		<button id="deletebatch" type="button" class="btn btn-primary">批量删除</button>
		<button id="updatebatch1" type="button" class="btn btn-primary">批量修改1</button>
		<button id="updatebatch2" type="button" class="btn btn-primary">批量修改2</button>
		<button id="updatebatch3" type="button" class="btn btn-primary">原表修改</button>


</div>
<img id="bigPic"/>
	</div>









</body>
</html>