package a;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import dao.BaiduDao;

public class BaiduServlet extends HttpServlet {

public void doGet(HttpServletRequest request,HttpServletResponse response  ) {
	
	String type=request.getParameter("type");
	if(type==null) {
		show(request,response);
	}if("show".equals(type)) {
		show1(request,response);
	}
   	
		
	}

private void show1(HttpServletRequest request, HttpServletResponse response) {
	// TODO Auto-generated method stub
	try {
		 String content=request.getParameter("content");
		PrintWriter out=response.getWriter();
		BaiduDao dao=new BaiduDao();
		List<String>list=new ArrayList();
	    list=dao.search(content);
	    String str="";
	for(int i=0;i<list.size();i++) {
		str+=list.get(i)+"<br/>";
	}
	out.println(str);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void show(HttpServletRequest request, HttpServletResponse response) {

	
	try {
		request.getRequestDispatcher("WEB-INF/baidu/baidu.jsp").forward(request, response);
	} catch (ServletException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void doPost(HttpServletRequest request,HttpServletResponse response  ) {
		
		
	doGet(request, response);	
	
	
}
}
