<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet"/>
<style>
#main{
width:400px;
margin:20px auto;

}
#photos img{
width:100px;
height:100px;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$().ready(function(){
	$("#upload").click(function(){
		var formData=new FormData();
		formData.append("photo",$("[name='photo']")[0].files[0]);
// 		for(var i=0;i<$("[name='photo']")[0].files.length;i++){
// 			formData.append("photo",$("[name='photo']")[0].files[i]);
// 		}
//  传多个文件
		$.ajax({
			url:"emp?type=upload",
			type:"post",
			data:formData,
			cache:false,
		processData:false,
		contentType:false,
			dataType:"text",
			success:function(data){
				var str="<img src='/pic/"+data+"'/>";
				str+="<input type='hidden' name='pic' value='"+data+"'/>";
				$("#photos").append(str);
			}
			
		})
$(document).on("click","#photos img",function(){
			var img=$(this);
			var imgName=$(this).next().val();
	$.ajax({
				url:"emp",
				type:"post",
				data:{
					type:"deleteFile",
					fileName:imgName
					},
				dataType:"text",
			success:function(){
				if(data="true"){
				img.next().remove();
				img.remove();
				}
			}
				
				
			})
			
		})
		
		
	})
	
	
})
</script>

</head>

<body>
<div id="main">
 <form  action="emp?type=add2" class="form-horizontal" role="form" method="post">
  
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label">名字</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="name" placeholder="请输入名字">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-sm-2 control-label">性别</label>
    <div class="col-sm-10">
      <input type="radio" name="sex" value="男" checked/>男
          <input type="radio" name="sex" value="女"/>女
    </div>
  </div>
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label">年龄</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="age" placeholder="请输入名字">
    </div>
  </div>
   <div class="form-group">
  <label for="lastname" class="col-sm-2 control-label">部门</label>
  <div class="col-sm-10">
  <select name="depId" class="form-control">  
   <option value=''>请选择部门</option>
  <c:forEach items="${depList1}" var="dep">
   <option value="${dep.id}">${dep.name}</option>
   </c:forEach>
   </select>
 </div>
 </div>
 
  <div class="form-group">
    <label for="firstname" class="col-sm-2 control-label">头像</label>
    <div class="col-sm-6">
      <input type="file" class="form-control" name="photo"/>
    </div>
    <div class="col-sm-3">
      <input type="button" id="upload" class="form-control btn btn-primary" value='上传' />
<!-- multiple 传多个文件 -->
    </div>
  </div>
<div id="photos"></div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-primary">保存</button>
    </div>
  </div>
</form>
</div>
</body>
</html>