package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import entity.Department;
import entity.Employee;

public class EmployeeDao extends BaseDao {


	
	public Employee search1(int id) {
		Employee emp=new Employee();
		Department dep=new Department();
		getDao();
			String sql = "select * from Employee where id="+id;
		 try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				 dep.setId(rs.getInt("e_id"));
			     emp.setDep(dep);
				emp.setId(Integer.parseInt(rs.getString("id")));
				emp.setAge(Integer.parseInt(rs.getString("age")));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stat,conn);
		}
		 return emp;
			
	}

	public List<Employee> searchByPage(int begin,int size) {
		List<Employee> list = new ArrayList();
		getDao();
			
		String sql = "select * from Employee limit  "+ begin+","+size;
		
		try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(Integer.parseInt(rs.getString("id")));
				emp.setAge(Integer.parseInt(rs.getString("age")));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stat,conn);
		}
		 return list;
			
	}

	public List<Employee> search() {
		List<Employee> list = new ArrayList();
		getDao();
			String sql = "select * from Employee";
		 try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(Integer.parseInt(rs.getString("id")));
				emp.setAge(Integer.parseInt(rs.getString("age")));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stat,conn);
		}
		 return list;
			
	}



	public int searchCount() {
		List<Employee> list = new ArrayList();
		getDao();
		int count=0;
			String sql = "select count(*) from Employee";
		 try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stat,conn);
		}
		
		 return count;
		
			
	}

	

	
	public void add(Employee emp) {
	
             getDao();
             String sql="insert into employee (name,sex,age,e_id,img) values(?,?,?,?,?)";
             PreparedStatement pasta=null;
             
         
		try {
			  pasta = conn.prepareStatement(sql);
			
			  pasta.setString(1, emp.getName());
	           pasta.setString(2, emp.getSex());
	           pasta.setInt(3, emp.getAge()); 
	           pasta.setObject(4,emp.getDep().getId());
	           pasta.setString(5, emp.getImg()); 
	           int rs =  pasta.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
			    pasta.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
           
		

    
     
     			

   
     	}

	public void update(Employee emp) {
		// TODO Auto-generated method stub
		getDao();
		int rs=0;
		PreparedStatement psata=null;
		int id=emp.getId();
		String name=emp.getName();
		String sex=emp.getSex();
		int age=emp.getAge();
		Integer depId=emp.getDep().getId();
	String sql = "update employee set name=?,sex=?,age=?,e_id=? where id=?";
	try {
		psata=conn.prepareStatement(sql);
		psata.setString(1, name);
		psata.setString(2,sex);
		psata.setInt(3, age);
		psata.setObject(4, depId);
		psata.setInt(5, id);
		
		
		rs=psata.executeUpdate();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		finally {
			close(null,stat,conn);
		}
		
			
		
	}

	public void delete(int id) {
		
		getDao();
		int rs=0;
			String sql = "delete from employee where id="+id;
		 try {
			
			rs = stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null,stat,conn);
		}
		
			
		
	}

	public void delete(String ids) {
		getDao();
		int rs=0;
			String sql = "delete from employee where id in ("+ids+")";
			
		 try {
			
			rs = stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null,stat,conn);
		}
		
	
		
	}

	public List<Employee>search(String ids) {
		// TODO Auto-generated method stub
		List<Employee>list=new ArrayList();
		getDao();
			String sql = "select * from Employee where id in ("+ids+")";
			
		
		 try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Employee emp=new Employee();
				
				emp.setId(Integer.parseInt(rs.getString("id")));
				emp.setAge(Integer.parseInt(rs.getString("age")));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				list.add(emp);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stat,conn);
		}
		 return list;
		
	}

	public void update(Employee emp, String ids) {
		
		// TODO Auto-generated method stub
		getDao();
		int rs=0;
		int id=emp.getId();
		String name=emp.getName();
		String sex=emp.getSex();
		int age=emp.getAge();
			String sql = "update employee set name='"+name+"',sex='"+sex +"',age="+age +" "
					+ " where id in("+ids+")";
			
		 try {
			
			rs = stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null,stat,conn);
		}
		
		
	}

	public void update(List<Employee> list) {
		 try {	getDao();
		int rs=0;
	   for(Employee emp:list) {
			String sql = "update employee set name='"+emp.getName()+"',sex='"+emp.getSex() +"',age="+emp.getAge() +" "
					+ " where id in("+emp.getId()+")";
			
		
			
			rs = stat.executeUpdate(sql);}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null,stat,conn);
		}
		
		
	   
		
	}

	public List<Employee> search(Employee condition,int begin,int size) {
		List<Employee> list = new ArrayList(); 
		try {
		
		
		getDao();
		
		String where="where 1=1";
		if(condition.getName()!=null&!("".equals(condition.getName()))) {
			where+="  and name='"+condition.getName()+"'";
		}
		if(condition.getSex()!=null&!("".equals(condition.getSex()))) {
			where+="  and sex='"+condition.getSex()+"'";
		}
		
		if(condition.getAge()!=-1) {
			where+="  and age="+condition.getAge();
		}
		if(condition.getDep().getName()!=null&!("".equals(condition.getDep().getName()))) {
			where+="  and d.name='"+condition.getDep().getName()+"'";
		}
		String sql = "select img , e.* ,d.name as dname  from Employee as e left join department as d on(e.e_id=d.id )  "+where+ " limit "+ begin+","+size;
	
		
	        
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Employee emp1 = new Employee();
				emp1.setId(Integer.parseInt(rs.getString("id")));
				emp1.setAge(Integer.parseInt(rs.getString("age")));
				emp1.setName(rs.getString("name"));
				emp1.setSex(rs.getString("sex"));
				emp1.setImg(rs.getString("img"));
				Department dep=new Department();
                
				dep.setName(rs.getString("dname"));
				emp1.setDep(dep);
				list.add(emp1);
				}
				
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
			close(rs,stat,conn);
		}
		 return list; 
			
		
	}

	public int searchCount(Employee condition) {
		List<Employee> list = new ArrayList(); 
		try {
		
		
		getDao();
		
		String where="where 1=1";
		if(condition.getName()!=null&!("".equals(condition.getName()))) {
			where+="  and name='"+condition.getName()+"'";
		}
		if(condition.getSex()!=null&!("".equals(condition.getSex()))) {
			where+="  and sex='"+condition.getSex()+"'";
		}
		
		if(condition.getAge()!=-1) {
			where+="  and age="+condition.getAge();
		}
		if(condition.getDep().getName()!=null&!("".equals(condition.getDep().getName()))) {
			where+="  and d.name='"+condition.getDep().getName()+"'";
		}
		String sql = "select img ,e.* ,d.id as did,d.name as dname from Employee as e left join department as d on(e.e_id=d.id )  "+where;
	
			
	   
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Employee emp1 = new Employee();
				emp1.setId(Integer.parseInt(rs.getString("id")));
				emp1.setAge(Integer.parseInt(rs.getString("age")));
				emp1.setName(rs.getString("name"));
				emp1.setSex(rs.getString("sex"));
				emp1.setImg(rs.getString("img"));
				Department dep=new Department();
				dep.setId(rs.getInt("did"));
				dep.setName(rs.getString("dname"));
				emp1.setDep(dep); 
				list.add(emp1);
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


		
		
		


			
			
}