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

import org.apache.jasper.tagplugins.jstl.core.Out;

import dao.DepartmentDao;
import entity.Department;
import entity.Project;
import net.sf.json.JSONArray;
import sun.print.PrinterJobWrapper;
import until.Constsnt;
import until.Pagination;

public class DepartmentServlet extends HttpServlet {


	public static final String path = "WEB-INF/dep";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String type = request.getParameter("type");
	

		if (type == null | "search".equals(type)) {
			search(request, response);
		} else if ("showadd".equals(type)) {
			showAdd(request, response);
		} else if ("add".equals(type)) {
			add(request, response);

		} else if ("deletebatch".equals(type)) {
			deletebatch(request, response);
		} else if ("updatabatch3".equals(type)) {
			updatabatch3(request, response);

		} else if ("showManage".equals(type)) {
			showManage(request, response);

		} else if ("manage1".equals(type)) {
			manage1(request, response);

		}else if ("manage2".equals(type)) {
			manage2(request, response);

		}else if ("showManage1".equals(type)) {
			showManage1(request, response);

		}else if ("showManage2".equals(type)) {
			showManage2(request, response);

		}else if ("manage3".equals(type)) {
			manage3(request, response);

		}else if ("manage4".equals(type)) {
			manage4(request, response);

		}else if ("showManage3".equals(type)) {
			showManage3(request, response);

		}else if ("showManage4".equals(type)) {
			showManage4(request, response);

		}

				}
	
	private void showManage4(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		DepartmentDao dao = new DepartmentDao();
				int id = Integer.parseInt(request.getParameter("id"));
				List<Project> proList = dao.search(id);
				List<Project> proListNo = dao.searchNo(id);
				Department dep = new Department();
				dep = dao.search1(id);

				String name = dep.getName();

				request.setAttribute("proList", proList);
				request.setAttribute("proListNo", proListNo);
				request.setAttribute("name", name);
				request.setAttribute("id", id);

				try {
					request.getRequestDispatcher(path + "/showmange4.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	private void showManage3(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		DepartmentDao dao = new DepartmentDao();
				int id = Integer.parseInt(request.getParameter("id"));
				List<Project> proList = dao.search(id);
				List<Project> proListNo = dao.searchNo(id);
				Department dep = new Department();
				dep = dao.search1(id);

				String name = dep.getName();

				request.setAttribute("proList", proList);
				request.setAttribute("proListNo", proListNo);
				request.setAttribute("name", name);
				request.setAttribute("id", id);

				try {
					request.getRequestDispatcher(path + "/showmange3.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	private void manage4(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			DepartmentDao dao = new DepartmentDao();
			PrintWriter out = response.getWriter();
          List <Integer>list=new ArrayList();
			int did = Integer.parseInt(request.getParameter("did"));
			if (!"".equals(request.getParameter("pid"))) {
				String pids = request.getParameter("pid");
				String []temp=pids.split(",");
				for(int i=0;i<temp.length;i++) {
					list.add(Integer.parseInt(temp[i]));
					
				}
				

				boolean flage = dao.addRelation(list, did);
				out.print(flage);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void showManage2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DepartmentDao dao = new DepartmentDao();
		int id = Integer.parseInt(request.getParameter("id"));
		List<Project> proList = dao.search(id);
		List<Project> proListNo = dao.searchNo(id);
		Department dep = new Department();
		dep = dao.search1(id);

		String name = dep.getName();

		request.setAttribute("proList", proList);
		request.setAttribute("proListNo", proListNo);
		request.setAttribute("name", name);
		request.setAttribute("id", id);

		try {
			request.getRequestDispatcher(path + "/showmange2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void showManage1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DepartmentDao dao = new DepartmentDao();
		int id = Integer.parseInt(request.getParameter("id"));
		List<Project> proList = dao.search(id);
		List<Project> proListNo = dao.searchNo(id);
		Department dep = new Department();
		dep = dao.search1(id);

		String name = dep.getName();

		request.setAttribute("proList", proList);
		request.setAttribute("proListNo", proListNo);
		request.setAttribute("name", name);
		request.setAttribute("id", id);

		try {
			request.getRequestDispatcher(path + "/showmange1.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void manage3(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			DepartmentDao dao = new DepartmentDao();
			PrintWriter out = response.getWriter();

			int did = Integer.parseInt(request.getParameter("did"));
		  if (!("".equals(request.getParameter("tid")))) {
				String tids=request.getParameter("tid");
				boolean flag = dao.deleteRelation(tids,did);
				out.print(flag);
			}

		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	private void manage2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			DepartmentDao dao = new DepartmentDao();
			PrintWriter out = response.getWriter();

			int did = Integer.parseInt(request.getParameter("did"));
			if (request.getParameter("tid") != null&!("".equals(request.getParameter("tid")))) {
				 int tid= Integer.parseInt(request.getParameter("tid"));
				boolean flag = dao.deleteRelation(tid,did);
				out.print(flag);
			}

		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	private void manage1(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao dao = new DepartmentDao();
			PrintWriter out = response.getWriter();

			int did = Integer.parseInt(request.getParameter("did"));
			if (request.getParameter("pid") != null & !"".equals(request.getParameter("pid"))) {
				int pid = Integer.parseInt(request.getParameter("pid"));

				boolean flage = dao.addRelation(pid, did);
				out.print(flage);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showManage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DepartmentDao dao = new DepartmentDao();
		int id = Integer.parseInt(request.getParameter("id"));
		List<Project> proList = dao.search(id);
		List<Project> proListNo = dao.searchNo(id);
		Department dep = new Department();
		dep = dao.search1(id);

		String name = dep.getName();

		request.setAttribute("proList", proList);
		request.setAttribute("proListNo", proListNo);
		request.setAttribute("name", name);
		request.setAttribute("id", id);

		try {
			request.getRequestDispatcher(path + "/showmanage.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		DepartmentDao dao = new DepartmentDao();
		Department condition = new Department();
		String name = request.getParameter("searchName");
		int num = -1;
		if (request.getParameter("searchNum") != null & !"".equals(request.getParameter("searchNum"))) {
			num = Integer.parseInt(request.getParameter("searchNum"));
		}

		condition.setName(name);
		condition.setNum(num);
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = dao.searchCount(condition);

		Pagination p = new Pagination(ye, count, Constsnt.EMP_NUM_IN_PAGE, Constsnt.EMP_NUM_OF_PAGE);
		List<Department> list = dao.search(condition, p.getBegin(), Constsnt.EMP_NUM_IN_PAGE);

		request.setAttribute("c", condition);
		request.setAttribute("p", p);
		request.setAttribute("list", list);

		try {
			request.getRequestDispatcher(path + "/department.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updatabatch3(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		DepartmentDao dao = new DepartmentDao();
		String deps = request.getParameter("deps");
		System.out.println(123);
		List<Department> list = new ArrayList();
		JSONArray jsonArray = JSONArray.fromObject(deps);

		list = (List<Department>) jsonArray.toCollection(jsonArray, Department.class);
		dao.update(list);

		try {
			response.sendRedirect("dep");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		DepartmentDao dao = new DepartmentDao();
		try {

			request.getRequestDispatcher(path + "/addview.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		DepartmentDao dao = new DepartmentDao();
		Department emp = new Department();
		String name = request.getParameter("name");
		emp.setName(name);
		dao.add(emp);
		try {
			response.sendRedirect("dep");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deletebatch(HttpServletRequest request, HttpServletResponse response) {
		DepartmentDao dao = new DepartmentDao();
		String ids = request.getParameter("ids");
		dao.delete(ids);
		try {
			response.sendRedirect("dep");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}