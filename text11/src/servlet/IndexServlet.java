package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class IndexServlet extends HttpServlet {
public void doGet(HttpServletRequest request,HttpServletResponse response) {
	
	 
	String type=request.getParameter("type");
 
	if(type==null) {
		show(request,response);
		
	}
	
	
	}
		
private void log(HttpServletRequest request, HttpServletResponse response) {
	// TODO Auto-generated method stub
try {
	request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
} catch (ServletException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
}

private void show(HttpServletRequest request, HttpServletResponse response) {
	// TODO Auto-generated method stub
	try {
		request.setCharacterEncoding("utf-8");
		
		request.getRequestDispatcher("WEB-INF/index/index.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}





public void doPost(HttpServletRequest request,HttpServletResponse response) {
		
		
		
	}
	
}
