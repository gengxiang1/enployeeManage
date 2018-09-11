package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.smartcardio.ResponseAPDU;
import javax.xml.ws.Response;

import org.apache.catalina.connector.Request;
import org.apache.jasper.tagplugins.jstl.core.Out;

import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import dao.ScoreDao;
import dao.UserDao;
import entity.Score;
import entity.Department;
import entity.Employee;
import entity.Project;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import until.Constsnt;
import until.Grade;
import until.Pagination;

public class ScoreServelet extends HttpServlet {

	public static final String path = "WEB-INF/score/score.jsp";
	public static final String path1 = "WEB-INF/score/score2.jsp";

	

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
        
   
		String type = request.getParameter("type");
		

		if (type == null | "search".equals(type)) {
			search(request, response);

		} else if ("save".equals(type)) {

			save(request, response);

		} else if ("see".equals(type)) {

			searchSea(request, response);
		}
		 else if ("save1".equals(type)) {

				save1(request, response);
			}
				}
	

	private void save1(HttpServletRequest request, HttpServletResponse response) {
		ScoreDao dao = new ScoreDao();
		int id=Integer.parseInt(request.getParameter("id"));
		int eid=Integer.parseInt(request.getParameter("eid"));
		int rid=Integer.parseInt(request.getParameter("rid"));
		int value=Integer.parseInt(request.getParameter("value"));
		String grade=dao.searchGrade(value);
		Grade g = Grade.getGrade(grade);
		
		Score sc=new Score();
		sc.setGrade(g);
		sc.setValue(value);
		
		try {
			PrintWriter out = response.getWriter();
			boolean flag=false;
			if(id!=0) {
				sc.setId(id);
				flag=dao.update(id,value);
			}else {
				int id1=dao.add(eid,rid,value);
				sc.setId(id1);
				if(id1>0) {
					flag=true;
				}
			}
			if(flag) {
			JSON json=	JSONObject.fromObject(sc);
				out.print(json);
				
			}else {
				out.print(false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

	private void save(HttpServletRequest request, HttpServletResponse response) {
		try {
			ScoreDao dao = new ScoreDao();
			PrintWriter out = response.getWriter();
			String array = request.getParameter("array");
			boolean flag = true;
			boolean flag1 = true;
			String[] temp = array.split(";");
			for (int i = 0; i < temp.length; i++) {
				String[] temp1 = temp[i].split(",");
				if (Integer.parseInt(temp1[0]) == 0) {
					flag = dao.add((String) temp[i]);
				} else {
					dao.update((String) temp[i]);
					flag1 = dao.add((String) temp[i]);
				}

			}
			if (flag) {
				flag1 = true;
			}
			out.print(flag1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		basic(request, response);
		ScoreDao dao = new ScoreDao();
		try {
			
			request.getRequestDispatcher(path).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void basic(HttpServletRequest request, HttpServletResponse response) {
		ScoreDao dao = new ScoreDao();
		Score condition = new Score();
		String eName = request.getParameter("eName");
		String sex = request.getParameter("sex");
		String depName = request.getParameter("depName");
		String proName = request.getParameter("proName");
		String grade = request.getParameter("grade");
		Grade g = Grade.getGrade(grade);
		Integer value = -1;
		if (request.getParameter("value") != null & !("".equals(request.getParameter("value")))) {
			value = Integer.parseInt(request.getParameter("value"));
		}
		Employee emp = new Employee();
		emp.setName(eName);

		Department dep = new Department();
		dep.setName(depName);
		emp.setDep(dep);
		emp.setSex(sex);
		Project pro = new Project();
		pro.setName(proName);
		condition.setEmp(emp);
		condition.setGrade(g);
		condition.setValue(value);
		condition.setGrade(g);
		condition.setPro(pro);
		;

		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = dao.searchCount(condition);

		List<Department> depList = dao.searchCount1();
		List<Project> proList = dao.searchCount2();
		Pagination p = new Pagination(ye, count, Constsnt.EMP_NUM_IN_PAGE, Constsnt.EMP_NUM_OF_PAGE);

		List<Score> list = dao.search(condition, p.getBegin(), Constsnt.EMP_NUM_IN_PAGE);

		Grade[] temp = Grade.values();
		Grade[] grades = new Grade[temp.length - 1];
		for (int i = 0; i < grades.length; i++) {

			grades[i] = temp[i];
		}

		request.setAttribute("grades", grades);
		request.setAttribute("depList", depList);
		request.setAttribute("proList", proList);
		request.setAttribute("c", condition);
		request.setAttribute("p", p);
		request.setAttribute("list", list);

	}

	private void searchSea(HttpServletRequest request, HttpServletResponse response) {
		basic(request, response);
		ScoreDao dao = new ScoreDao();
		try {
			request.getRequestDispatcher(path1).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}