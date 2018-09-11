package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import net.sf.json.JSONArray;
import until.Constsnt;
import until.Pagination;

public class EmployeeServlet extends HttpServlet {

	public static final String path = "WEB-INF/emp";
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
	
		String type = request.getParameter("type");
		 HttpSession session=request.getSession();
		if(session.getAttribute("username")==null) {
				try {
					response.sendRedirect("log");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
		if (type == null | "search".equals(type)) {
			search(request, response);
		} else if ("showadd".equals(type)) {
			showAdd(request, response);
		} else if ("showadd2".equals(type)) {
			showAdd2(request, response);
		} else if ("add".equals(type)) {
			add(request, response);

		} else if ("showupdate".equals(type)) {
			showUpdata(request, response);

		} else if ("update".equals(type)) {
			update(request, response);
		} else if ("delete".equals(type)) {
			delete(request, response);
		} else if ("deletebatch".equals(type)) {
			deletebatch(request, response);
		} else if ("showupdatabatch1".equals(type)) {
			showupdatabatch1(request, response);
		} else if ("updatabatch1".equals(type)) {
			updatabatch1(request, response);
		} else if ("showupdatabatch2".equals(type)) {

			showupdatabatch2(request, response);

		} else if ("updatabatch2".equals(type)) {
			updatabatch2(request, response);

		} else if ("updatabatch3".equals(type)) {
			updatabatch3(request, response);

		} else if ("search".equals(type)) {
			search(request, response);

		} else if ("upload".equals(type)) {
			upload(request, response);
		} else if ("add2".equals(type)) {
			add2(request, response);
		} else if ("deleteFile".equals(type)) {
			deleteFile(request, response);
		}
			}
	}

	private void deleteFile(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployeeDao dao = new EmployeeDao();
		String fileName = request.getParameter("fileName");
		File file = new File("f:/tu/" + fileName);

		boolean flag = false;
		// 判断是否为空 //有没有发现讨论基本一样
		if (file.exists()) {
			file.delete();
			flag = true;
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void upload(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String path = "f:/tu/";
		FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items;
		String photo = "";
		try {

			items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("photo")) {

					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					photo = uuid.toString() + houzhui;
					File savedFile = new File(path, photo);
					item.write(savedFile);
				}
			}
			PrintWriter out = response.getWriter();
			out.print(photo);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showAdd2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployeeDao dao = new EmployeeDao();
		List<Department> depList = dao.searchCount1();
		request.setAttribute("depList1", depList);

		try {

			request.getRequestDispatcher(path + "/addview2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add2(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDao dao = new EmployeeDao();
		Employee emp = new Employee();
		Department dep = new Department();
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		Integer depId = null;
		if (!"".equals(request.getParameter("depId"))) {
			depId = Integer.parseInt(request.getParameter("depId"));
		}
		int age = Integer.parseInt(request.getParameter("age"));
		String img = request.getParameter("pic");

		dep.setId(depId);
		emp.setDep(dep);
		emp.setAge(age);
		emp.setName(name);
		emp.setSex(sex);
		emp.setImg(img);
		dao.add(emp);
		try {
			response.sendRedirect("emp");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDao dao = new EmployeeDao();
		Employee emp = new Employee();
		Department dep = new Department();
		try {

			// String path = request.getServletContext().getRealPath("/") + "/pic";
			// System.out.println(path);

			String path = "f:/tu/";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("photo")) {
					String photo=null;
                if(!"".equals(item.getName())) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					photo = uuid.toString() + houzhui;
					File savedFile = new File(path, photo);
					item.write(savedFile);
					
					}
                emp.setImg(photo);

				} else if (item.getFieldName().equals("name")) {
					String name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					emp.setName(name);
				} else if (item.getFieldName().equals("sex")) {
					String sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					emp.setSex(sex);
				} else if (item.getFieldName().equals("age")) {
					int age=0;
		  if( !"".equals(item.getString())) {
					 age = Integer.parseInt(item.getString());}
					emp.setAge(age);
				} else if (item.getFieldName().equals("depId")) {
					Integer dpId = null;
					if (item.getString() != null & !"".equals(item.getString())) {
						dpId = Integer.parseInt(item.getString());
					}
					dep.setId(dpId);
					emp.setDep(dep);
				}

			}

			dao.add(emp);
			response.sendRedirect("emp");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployeeDao dao = new EmployeeDao();
		Employee condition = new Employee();
		String name = request.getParameter("searchName");
		String sex = request.getParameter("searchSex");
		String depName = request.getParameter("searchDepName");

		int age = -1;
		if (request.getParameter("searchAge") != null & !("".equals(request.getParameter("searchAge")))) {
			age = Integer.parseInt(request.getParameter("searchAge"));
		}

		Department dep = new Department();
		dep.setName(depName);
		condition.setDep(dep);
		condition.setName(name);
		condition.setAge(age);
		condition.setSex(sex);

		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = dao.searchCount(condition);

		List<Department> depList = dao.searchCount1();
		Pagination p = new Pagination(ye, count, Constsnt.EMP_NUM_IN_PAGE, Constsnt.EMP_NUM_OF_PAGE);

		List<Employee> list = dao.search(condition, p.getBegin(), Constsnt.EMP_NUM_IN_PAGE);

		request.setAttribute("depList", depList);
		request.setAttribute("c", condition);

		request.setAttribute("p", p);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher(path + "/employee.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updatabatch2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployeeDao dao = new EmployeeDao();
		String emps = request.getParameter("emps");
		String[] array = emps.split(";");

		List<Employee> list = new ArrayList<Employee>();
		for (int i = 0; i < array.length; i++) {
			Employee emp = new Employee();
			String[] temp = array[i].split(",");
			emp.setId(Integer.parseInt(temp[0]));
			emp.setName(temp[1]);
			emp.setSex(temp[2]);
			emp.setAge(Integer.parseInt(temp[3]));
			list.add(emp);
			dao.update(list);
		}

		try {
			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private void updatabatch3(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployeeDao dao = new EmployeeDao();
		String emps = request.getParameter("emps");

		List<Employee> list = new ArrayList();
		JSONArray jsonArray = JSONArray.fromObject(emps);
		list = (List<Employee>) jsonArray.toCollection(jsonArray, Employee.class);
		dao.update(list);

		try {
			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showupdatabatch2(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		EmployeeDao dao = new EmployeeDao();
		String ids = request.getParameter("ids");
		List<Employee> list = dao.search(ids);

		request.setAttribute("emps", list);

		try {
			request.getRequestDispatcher(path + "/showupdatabatch2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updatabatch1(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDao dao = new EmployeeDao();
		String ids = request.getParameter("ids");
		Employee emp = new Employee();
		String name = request.getParameter("name");

		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		emp.setAge(age);
		emp.setName(name);
		emp.setSex(sex);
		dao.update(emp, ids);

		try {
			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showupdatabatch1(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDao dao = new EmployeeDao();
		String ids = request.getParameter("ids");
		List<Employee> list = dao.search(ids);
		request.setAttribute("emp1", list.get(0));
		request.setAttribute("ids", ids);
		try {
			request.getRequestDispatcher(path + "/showupdatabatch1.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDao dao = new EmployeeDao();
		List<Department> depList = dao.searchCount1();
		request.setAttribute("depList1", depList);

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

	public void update(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDao dao = new EmployeeDao();
		Employee emp = new Employee();
		Department dep = new Department();
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		Integer depId = null;
		if (!"".equals(request.getParameter("depId"))) {
			depId = Integer.parseInt(request.getParameter("depId"));
		}
		int age = Integer.parseInt(request.getParameter("age"));
		dep.setId(depId);
		emp.setDep(dep);
		emp.setId(id);
		emp.setAge(age);
		emp.setName(name);
		emp.setSex(sex);
		dao.update(emp);
		try {
			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showUpdata(HttpServletRequest request, HttpServletResponse response) {
		try {
			EmployeeDao dao = new EmployeeDao();
			Integer id = Integer.parseInt(request.getParameter("id"));
			Employee emp = dao.search1(id);
			List<Department> depList = dao.searchCount1();
			request.setAttribute("depList", depList);

			request.setAttribute("emp", emp);

			request.getRequestDispatcher(path + "/update.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public void add(HttpServletRequest request, HttpServletResponse response) {
	//
	// Employee emp = new Employee();
	// Department dep=new Department();
	// String name = request.getParameter("name");
	// String sex = request.getParameter("sex");
	// Integer depId=null;
	// if(!"".equals(request.getParameter("depId"))) {
	// depId = Integer.parseInt(request.getParameter("depId"));}
	// int age = Integer.parseInt(request.getParameter("age"));
	// dep.setId(depId);
	// emp.setDep(dep);
	// emp.setAge(age);
	// emp.setName(name);
	// emp.setSex(sex);
	// dao.add(emp);
	// try {
	// response.sendRedirect("emp");
	// } catch (IOException e) {
	//
	// e.printStackTrace();
	// }
	//
	// }

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDao dao = new EmployeeDao();
		dao.delete(id);
		try {
			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	public void deletebatch(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		EmployeeDao dao = new EmployeeDao();
		dao.delete(ids);
		try {
			response.sendRedirect("emp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		  doGet(request, response);
	}

}