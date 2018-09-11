<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
#main{
width:900px;
margin:20px auto;

}
.emp{
width:400px;
float:left;
margin:10px 50px 10px 0px;
border:1px dashed #ccc;

}
#saveBtn{
clear:both;
margin:10px auto;
text-align:center;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$().ready(function(){
	var emps="";
	$("#save").click(function(){
		$(".emp").each(function(index,element){
			var id=$(this).find("[name=id]").val();
			var name=$(this).find("[name=name]").val();
			var sex=$(this).find("[name=sex]:checked").val();
			var age=$(this).find("[name=age]").val();
		   emps+=(id+","+name+","+sex+","+age+";");
		})
	   emps=emps.substring(0,emps.length-1);
		location.href="emp?type=updatabatch2&&emps="+emps;
	})
	
	
})

</script>
</head>
<body>

<div id="main">
<c:forEach  items="${emps}" var="emp">
 <form  action="emp" class="form-horizontal emp" role="form" mothod="post">
<input type="hidden" name="id" value="${emp.id }" /> 
  <input type="hidden" name="type" value="updatabatch1"/>
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label">名字</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="name" value="${emp.name }">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">性别</label>
    <div class="col-sm-10">
      <input type="radio" name="sex" value="男"<c:if test="${emp.sex=='男'}">checked</c:if>/>男
            <input type="radio" name="sex" value="女"<c:if test="${emp.sex=='女'}">checked</c:if>/>女
    </div>
  </div>
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label">年龄</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="age" value="${emp.age }">
    </div>
  </div>
</form>

</c:forEach>
<div id="saveBtn">
<button id="save" type="button" class="btn btn-primary">保存</button>
</div>

</div>



</body>
</html>