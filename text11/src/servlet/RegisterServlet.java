package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegisterDao;
import entity.User;
import until.MD5;

public class RegisterServlet extends HttpServlet{
	RegisterDao dao=new RegisterDao();
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String type=request.getParameter("type");
		if(type==null) {
		  showRegister(request,response);
		}else if("reg".equals(type)) {
			
			register(request,response);
		}
		
		
	}
private void showRegister(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	try {
		request.getRequestDispatcher("WEB-INF/register/register.jsp").forward(request, response);
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
private void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	try {	
		boolean flag=true;
		
	String userName=request.getParameter("userName");
	
	String  password=request.getParameter("password1");
	Date date = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:");
	String salt = MD5.MD5(format.format(date));
	password=password+salt;
	
	User user=new User();
	user.setSalt(salt);
	user.setUserName(userName);
	
	user.setPassword(MD5.MD5(password));
	
	
	flag=dao.add(user);
     
	if(flag) {
		
	request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
		
	}
		
	else {
		 request.setAttribute("str", "ÕË»§ÒÑ´æÔÚ,×¢²áÊ§°Ü");
		
			request.getRequestDispatcher("WEB-INF/register/register.jsp").forward(request, response);

		
		
	}
	
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
public void doPost(HttpServletRequest request,HttpServletResponse response) {
		
		doGet(request,response);
		
	}

}
