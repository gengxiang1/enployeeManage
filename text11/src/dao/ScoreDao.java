package dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.text.AttributeSet.ParagraphAttribute;

import com.mysql.jdbc.PreparedStatement;

import entity.Department;
import entity.Project;
import entity.Score;
import until.Grade;
import entity.Employee;

public class ScoreDao extends BaseDao{
	
	
	
	public List<Score> search(Score sc,int begin,int size) {
		List<Score> list = new ArrayList(); 
		try {
		getDao();
		
		String where = " having 1=1" + " ";
		
		
		if (sc.getEmp().getName()!=null&! "".equals(sc.getEmp().getName()))
			where += " " + "and ename='" + sc.getEmp().getName() + "'";
		if (sc.getEmp().getDep().getName()!=null&!"".equals(sc.getEmp().getDep().getName()))
			where += " " + "and dname='" + sc.getEmp().getDep().getName() + "'";
		if (sc.getPro().getName()!=null&!"".equals(sc.getPro().getName())){
			where += " " + " and pname ='" + sc.getPro().getName() + "'";
		}
		if (sc.getValue() != -1) {
			where += " " + "and value=" + sc.getValue();
		}
		if (sc.getGrade()!=null&sc.getGrade().getValue()!=null) {
			where += " " + "and grade ='" + sc.getGrade().getValue() + "'";
		}if(sc.getEmp().getSex()!=null&!"".equals(sc.getEmp().getSex())) {
			where+="  and sex = '"+sc.getEmp().getSex()+"'";
			
		}

		 String sql = "select *from sumview" + where+"  limit "+begin+","+size;
		
		
	   
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				
				Score sc1 = new Score();

				sc1.setId(rs.getInt("sid"));
			   
                Grade g=Grade.getGrade(rs.getString("grade")); 
				sc1.setGrade(g);

				sc1.setValue((Integer)rs.getObject("value"));
				Employee emp = new Employee();
				emp.setSex(rs.getString("sex"));
				emp.setId(rs.getInt("eid"));
				emp.setName(rs.getString("ename"));
				Department dep = new Department();
				dep.setName(rs.getString("dname"));
		         dep.setId(rs.getInt("did"));
				emp.setDep(dep);
				sc1.setEmp(emp);

				Project pro = new Project();
			 pro.setId(rs.getInt("pid"));
				pro.setName(rs.getString("pname"));
				sc1.setPro(pro);

				list.add(sc1);

				
				
				}
				
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
			close(rs,stat,conn);
		}
		 return list; 
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public int searchCount(Score sc) {
		List<Score> list = new ArrayList(); 
		try {
		getDao();
		
		String where = " where 1=1" + " ";
		
		if (sc.getEmp().getName()!=null&! "".equals(sc.getEmp().getName()))
			where += " " + "and ename='" + sc.getEmp().getName() + "'";
		if (sc.getEmp().getDep().getName()!=null&!"".equals(sc.getEmp().getDep().getName()))
			where += " " + "and dname='" + sc.getEmp().getDep().getName() + "'";
		if (sc.getPro().getName()!=null&!"".equals(sc.getPro().getName())){
			where += " " + " and pname ='" + sc.getPro().getName() + "'";
		}
		if (sc.getValue() !=-1) {
			where += " " + "and value=" + sc.getValue();
		}
		if (sc.getGrade()!=null&sc.getGrade().getValue()!=null) {
			where += " " + "and grade ='" + sc.getGrade().getValue()  + "'";
		}if(sc.getEmp().getSex()!=null&!"".equals(sc.getEmp().getSex())) {
			where+="  and sex = '"+sc.getEmp().getSex()+"'";
			
		}

		 String sql = "select *from sumview" + where;
		
		
	   
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Score sc1 = new Score();

				sc1.setId(rs.getInt("sid"));

				Grade g=Grade.getGrade(rs.getString("grade")); 
			     sc1.setGrade(g);

				sc1.setValue((Integer)rs.getObject("value"));
				Employee emp = new Employee();
				emp.setSex(rs.getString("sex"));
				
				emp.setId(rs.getInt("eid"));
				emp.setName(rs.getString("ename"));
				Department dep = new Department();
				dep.setName(rs.getString("dname"));
		         dep.setId(rs.getInt("did"));
				emp.setDep(dep);
				sc1.setEmp(emp);

				Project pro = new Project();
			 pro.setId(rs.getInt("pid"));
				pro.setName(rs.getString("pname"));
				sc1.setPro(pro);

				list.add(sc1);

				
				
				}
				
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
			close(rs,stat,conn);
		}
		 return list.size(); 
			
		
	}

	public List<Department> searchCount1() {
		List<Department>depList = new ArrayList(); 
		try {
		getDao();
		String sql = "select * from department";
		rs = stat.executeQuery(sql);
			while (rs.next()) {
				
				Department dep=new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				depList.add(dep);
			
				}
				
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
			close(rs,stat,conn);
		}
		
		 return depList; 
			
		
	}
public boolean add(String temp1) {
		String [] temp=temp1.split(",");
		int id=Integer.parseInt(temp[0]);
		int eid=Integer.parseInt(temp[1]);
		int rid=Integer.parseInt(temp[2]);
		int value=Integer.parseInt(temp[3]);
		
		
		getDao();
		String sql = "insert into score  (e_id,R_id,value) values" + "(" + eid + ","
				+ rid + "," + value + ")";
int rs=0;
		try {
			 rs = stat.executeUpdate(sql);
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, stat, conn);
		}
		return rs>0;

	}


	public boolean update(String temp1) {
		String [] temp=temp1.split(",");
		int id=Integer.parseInt(temp[0]);
		
		int value=Integer.parseInt(temp[3]);
   int rs=0; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123");

			String sql = "update score set value=" + value + " " + "where id=" + id;

			stat = conn.createStatement();
			 rs = stat.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}

		return rs>0;
	}



	public List<Project> searchCount2() {
		// TODO Auto-generated method stub
		List<Project>proList = new ArrayList(); 
		try {
		getDao();
		String sql = "select * from project";
		rs = stat.executeQuery(sql);
		
			while (rs.next()) {
				Project pro=new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				proList.add(pro);
				
				}
				
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
			close(rs,stat,conn);
		}
		
		 return proList; 
			
	}











	public boolean update(int id, int value) {
		// TODO Auto-generated method stub
		int rs=0; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123");

			String sql = "update score set value=" + value + " " + "where id=" + id;

			stat = conn.createStatement();
			 rs = stat.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (SQLException e) {

			e.printStackTrace();
		}
return rs>0;
	
	}











	public int  add(int eid, int rid, int value) {
		// TODO Auto-generated method stub
		int id=0;
		try {	getDao();
		String sql = "insert into score  (e_id,R_id,value) values" + "(" + eid + ","
				+ rid + "," + value + ")";
       
		
			 int rs = stat.executeUpdate(sql);
			
	   String sql1="select last_insert_id()";
	    ResultSet rs1 = stat.executeQuery(sql1);
	    while(rs1.next()) {
	    	id=rs1.getInt(1);
	    }
	   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, stat, conn);
		}
		return id;
	}











	public String searchGrade( int value) {
		// TODO Auto-generated method stub
		String grade=null;
		if(value>=90) {
			grade="优秀";
		}else if(value>=80) {
			grade="良好";
		}else if(value>=70) {
			grade="一般";
		}else if(value>=60) {
			grade="及格";
		}else {
			grade="不及格";
		}
	

		return grade;












}
	}
