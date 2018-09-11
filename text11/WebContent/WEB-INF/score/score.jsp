<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
#score {
	width: 900px;
	margin: 20px auto;
}



.select {
	background: red;
}
.save{
width:80px;
height:30px;
margin:20px auto;
text-align:center;
line-height:30px;
}

</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
$().ready(function(){
	
	
	$(".abc").find("[name='value1']").click(function(){
		  $(this).unbind("click");
	   if( $(this).prev().html()!=""){
		 $(this).toggleClass("select");
		 var  name=$(this).text();
		  $(this).html("<input type='text' name='value2' value="+name+">")
		  }
	})
	

	
$("#save").click(function(){
	if(	$(".select").length>0){
	 var val=new Array();
	 var array="";
	$(".select").each(function(index,element){
		 var id=$(this).data("id");
		 array+=id+",";
		 var eid=$(this).parent().find("[name='eName1']").data("id");
		 array+=eid+",";
		 var pid=$(this).parent().find("[name='pName1']").data("id");
		 array+=pid+",";
		 var value=-1;
		 if($(this).find("[name='value2']").val()!=""){
			 value=$(this).find("[name='value2']").val(); 
			 val.push(value);
		 }
		 
		 array+=value+";";
	 })
	array=array.substring(0,array.length-1);
	 $.ajax({
		 url:"score",
		type:"get",
		 data:{
				 type:"save",
				 array:array,
				
			},
			  dataType:"text",
			success:function(data){
				if(data=="true"){
					$(".select").each(function(index,element){
						var i=index;
						$(this).html(val[i]);
						$(this).next().html();
						$(this).removeClass();
					})
					
					
				}
				
			
				
				
			}
	 })
	 }
else{
	alert("点击修改保存");
}
	 
	})
	

	
})
     



</script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<div id="score">
<div>
   <form class="form-inline" role="form" action="score">
    <input type="hidden" name="type" value="search"/>
    <label >名称:</label>
    <input type="text" class="form-control" name="eName" value="${c.emp.name }" placeholder="请输入名称">
    <label>性别:</label>
   <select class="form-control" name="sex"> 
    <option value=''>请选择性别</option>
   <option value='男'<c:if test="${c.emp.sex=='男'}">selected</c:if>> 男 </option>
    <option value='女'<c:if test="${c.emp.sex=='女'}">selected</c:if>> 女 </option>
    
   </select>

  
  <label>部门:</label>
   <select class="form-control" name="depName"> 
    <option value=''>请选择部门</option>
  <c:forEach items="${depList}" var="dep">
   <option value="${dep.name}" <c:if test="${dep.name eq c.emp.dep.name}"> selected </c:if>> ${dep.name} </option>
    
  </c:forEach>
   </select>
   
   
   <label>项目:</label>
 <select class="form-control" name="proName"> 
    <option value=''>请选择项目</option>
  <c:forEach items="${proList}" var="pro">
   <option value="${pro.name}" <c:if test="${pro.name eq c.pro.name}">selected </c:if>> ${pro.name} </option>
    
  </c:forEach>
   </select>
   
   <br/>
   <br/>
    <label >分数:</label>
    <input type="text" class="form-control" name="value" value="${c.value!=-1?c.value:""}" placeholder="请输入成绩">
    <label >等级:</label>
     <select class="form-control" name="grade"> 
   <option value=''>请选择等級</option>
  <c:forEach items="${grades}" var="grade">
    <option value="${grade.getValue()}" <c:if test="${grade==c.grade}">selected </c:if>>${grade.getValue()} </option>
     
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
				<th>部门</th>
				<th>项目 </th>
				<th>成绩 </th>
				<th>等级 </th>
			</thead>
			</tr>
			<c:forEach items="${list}" var="sc">
			<tr data-id="${sc.id}" class="abc">
				<td name="eName1" data-id="${sc.emp.id}">${sc.emp.name}</td>
				<td name="sex1">${sc.emp.sex}</td>
				<td name="dName1">${sc.emp.dep.name}</td>
				<td name="pName1" data-id="${sc.pro.id}">${sc.pro.name}</td>
				<td name="value1" data-id="${sc.id}">${sc.value}</td>
				<td name="grade1">${sc.grade.getValue()}</td>
			</tr>
		 </c:forEach>
		</table>
		
		<ul class="pagination">
			<li><a href="score?ye=1&type=search&Name=${c.emp.name }&sex=${c.emp.sex }&depName=${c.emp.dep.name }&proName=${c.pro.name }&value=${c.value!=-1?c.value:""}&grade=${c.grade.getValue()}">首页</a></li>
		
	<li id="pre"><a href="score?ye=${p.ye-1}&type=search&eName=${c.emp.name }&sex=${c.emp.sex }&depName=${c.emp.dep.name }&proName=${c.pro.name }&value=${c.value!=-1?c.value:""}&grade=${c.grade.getValue()}">上一页</a></li>
     <c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
	<li <c:if test="${p.ye==status.index}"> class="active" </c:if>><a
	 href="score?&type=search&ye=${status.index}&eName=${c.emp.name }&sex=${c.emp.sex }&depName=${c.emp.dep.name }&proName=${c.pro.name }&value=${c.value!=-1?c.value:""}&grade=${c.grade.getValue()}"> ${status.index}</a></li>
	</c:forEach>
	<li id="next"><a href="score?&type=search&ye=${p.ye+1}&eName=${c.emp.name }&sex=${c.emp.sex }&depName=${c.emp.dep.name }&proName=${c.pro.name }&value=${c.value!=-1?c.value:""}&grade=${c.grade.getValue()}">下一页</a></li>
	<li><a href="score?&type=search&ye=${p.maxYe}&$Name=${c.emp.name }&sex=${c.emp.sex }&depName=${c.emp.dep.name }&proName=${c.pro.name }&value=${c.value!=-1?c.value:""}&grade=${c.grade.getValue()}">尾页</a></li>
</ul>
		
		
		<div class="save">
		<button id="save" type="button" class="btn btn-primary">保存</button>
	

</div>
	</div>









</body>
</html>