package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountListener implements HttpSessionListener,ServletContextListener{

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		ServletContext application=event.getSession().getServletContext();
		int num=0;
		if(application.getAttribute("num")!=null) {
			num=(Integer)(application.getAttribute("num"));
		}
		
		num++;
		application.setAttribute("num",num);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
//	ServletContext application=event.getSession().getServletContext();
//	 if(num>0) {
//		 int num=(Integer)(application.getAttribute("num"));
//    
//	
//	 num--;}
//   
//    application.setAttribute("num",num);

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
