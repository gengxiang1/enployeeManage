package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProjectDao;
import entity.Employee;
import entity.Project;
import net.sf.json.JSONArray;
import until.Constsnt;
import until.Pagination;

public class ProjectServlet extends HttpServlet {

	
	 public static final String  path= "WEB-INF/project";
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String type = request.getParameter("type");
		
		if (type == null |"search".equals(type)) {
			search(request, response);
		} else if ("showadd".equals(type)) {
		 showAdd(request, response);
		 } else if ("add".equals(type)) {
		 add(request, response);
		
		 } 
		else if ("deletebatch".equals(type)) {
		deletebatch(request, response);
		 } else if ("showupdate".equals(type)) {
		showupdate(request, response);
		}else if ("update".equals(type)) {
			update(request, response);
			}
		
				}
		
	
	private void update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ProjectDao dao = new ProjectDao();
		String pros = request.getParameter("pros");
		String[] array = pros.split(";");

		List<Project> list = new ArrayList();
		for (int i = 0; i < array.length; i++) {
			Project pro = new Project();
			String[] temp = array[i].split(",");
			pro.setId(Integer.parseInt(temp[0]));
			pro.setName(temp[1]);
			list.add(pro);
			dao.update(list);
		}

		try {
			response.sendRedirect("pro");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void showupdate(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ProjectDao dao = new ProjectDao();
		String ids = request.getParameter("ids");
		List<Project> list = dao.search(ids);

		request.setAttribute("pros", list);

		try {
			request.getRequestDispatcher(path+"/showupdatabatch2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	private void search(HttpServletRequest request, HttpServletResponse response) {
		Project condition = new Project();
		ProjectDao dao = new ProjectDao();
		String name = request.getParameter("searchName");
		condition.setName(name);

		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = dao.searchCount(condition);

		Pagination p = new Pagination(ye, count, Constsnt.EMP_NUM_IN_PAGE, Constsnt.EMP_NUM_OF_PAGE);
		List<Project> list = dao.search(condition, p.getBegin(), Constsnt.EMP_NUM_IN_PAGE);

		request.setAttribute("c", condition);
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher(path+"/project.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	 private void updatabatch3(HttpServletRequest request, HttpServletResponse
	 response) 
	 {
		 ProjectDao dao = new ProjectDao();
	 // TODO Auto-generated method stub
	 String pros = request.getParameter("pros");
	
	 List<Project> list = new ArrayList();
	 JSONArray jsonArray = JSONArray.fromObject(pros);

      list = (List<Project>) jsonArray.toCollection(jsonArray,Project.class);
	 dao.update(list);
	
	 try {
	 response.sendRedirect("pro");
	 } catch (IOException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	
	 }
	
	 public void showAdd(HttpServletRequest request, HttpServletResponse response){
		 ProjectDao dao = new ProjectDao();
	
	 try {
	
	 request.getRequestDispatcher(path+"/addview.jsp").forward(request,response);
	 } catch (ServletException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 } catch (IOException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	 }
	
	 public void add(HttpServletRequest request, HttpServletResponse response) {
		 ProjectDao dao = new ProjectDao();
	 Project pro = new Project();
	 String name = request.getParameter("name");
	 pro.setName(name);
	 dao.add(pro);
	 try {
	 response.sendRedirect("pro");
	 } catch (IOException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	
	 }

	 public void deletebatch(HttpServletRequest request, HttpServletResponse
	 response) {
		 ProjectDao dao = new ProjectDao();
	
	 String ids = request.getParameter("ids");
	 dao.delete(ids);
	 try {
	 response.sendRedirect("pro");
	 } catch (IOException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
	 }
	
	 }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}