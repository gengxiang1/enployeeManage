package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import entity.Department;
import entity.Project;

public class DepartmentDao extends BaseDao {
//
//
//	
	public  Department search1(int id) {
		Department dep=new Department();
		getDao();
			String sql = "select * from department where id="+id;
		 try {
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				
				dep.setName(rs.getString("name"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stat,conn);
		}
		 return dep;
			
}
 public List<Department> search() {
		List<Department> list = new ArrayList();
		getDao();
			String sql = "select * from department";
		 try {
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(Integer.parseInt(rs.getString("id")));
				dep.setName(rs.getString("name"));
				dep.setNum(Integer.parseInt(rs.getString("num")));
				list.add(dep);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stat,conn);
		}
		 return list;}
			



//
//	
//
//	
	public void add(Department emp) {
	    getDao();

    try {
     String sql="insert into department (name) values"
     		+ "('"+emp.getName()+"')";
    
     int rs = stat.executeUpdate(sql);
     			

     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} finally {
     			close(null, stat, conn);
     		}

     	}

//	public void update(Department emp) {
//		// TODO Auto-generated method stub
//		getDao();
//		int rs=0;
//		int id=emp.getId();
//		String name=emp.getName();
//		String sex=emp.getSex();
//		int age=emp.getAge();
//			String sql = "update employee set name='"+name+"',sex='"+sex +"',age="+age +"  where id="+id;
//		 try {
//			
//			rs = stat.executeUpdate(sql);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			close(null,stat,conn);
//		}
//		
//			
//		
//	}
//


	public void delete(String ids) {
		getDao();
		int rs=0;
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String sql2= "delete from relation where d_id in ("+ids+")";
		
     try {
			
			rs = stat.executeUpdate(sql2);
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		String sql1 = "update employee set e_id=null where e_id in ("+ids+")";
		 try {
			rs = stat.executeUpdate(sql1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
			String sql = "delete from department where id in ("+ids+")";
			
		 try {
			
			rs = stat.executeUpdate(sql);
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null,stat,conn);
		}
		
	
		
	}


	public void update(List<Department> list) {
		 try {	
	 getDao();
		int rs=0;
	   for(Department dep:list) {
			String sql = "update department set name='"+dep.getName()+"'"
					+ " where id in("+dep.getId()+")";
			
		
			
			rs = stat.executeUpdate(sql);}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null,stat,conn);
		}
		
		
	   
		
	}

	public List<Department> search(Department condition,int begin,int size) {
		List<Department> list = new ArrayList(); 
		try {
		
		
		getDao();
		
		String where="where 1=1";
		if(condition.getName()!=null&!("".equals(condition.getName()))) {
			where+="  and name='"+condition.getName()+"'";
		}if(condition.getNum()!=-1) {
			where+="  and ifnull(num,0)="+condition.getNum();
		}
		
		String sql = "select * from Department  "+where+ " limit "+ begin+","+size;
	
			
	
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(Integer.parseInt(rs.getString("id")));
				dep.setName(rs.getString("name"));
				dep.setNum(rs.getInt("num"));
			
				list.add(dep);}
				
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
			close(rs,stat,conn);
		}
		 return list; 
			
		
	}

	public int  searchCount(Department condition) {
		List<Department> list = new ArrayList(); 
		try {
		
		
		getDao();
		
		String where="where 1=1";
		if(condition.getName()!=null&!("".equals(condition.getName()))) {
			where+="  and name='"+condition.getName()+"'";
		}if(condition.getNum()!=-1) {
			where+=" and ifnull(num,0)="+condition.getNum();
		}
		String sql = "select * from Department  "+where;
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(Integer.parseInt(rs.getString("id")));
				dep.setName(rs.getString("name"));
				dep.setNum(rs.getInt("num"));
				list.add(dep);
				}
				
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
			close(rs,stat,conn);
		}
		 return list.size(); 
			
		
	}




	public List<Project> search(int id) {
		List<Project> list = new ArrayList();
		getDao();
			String sql = " select * from project where id in (select pid from  D_prelation where did="+id+")";
		 try {
			 
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(Integer.parseInt(rs.getString("id")));
				pro.setName(rs.getString("name"));
			
				list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stat,conn);
		}
		 return list;
		 }
	
	
	
	public List<Project> searchNo(int id) {
		List<Project> list = new ArrayList();
		getDao();
			String sql = " select * from project where id  not in (select pid from  D_prelation where did="+id+")";
			
		 try {
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(Integer.parseInt(rs.getString("id")));
				pro.setName(rs.getString("name"));
			   list.add(pro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,stat,conn);
		}
		 return list;
		 }




	public  boolean addRelation(int pid, int did) {
		getDao();
		PreparedStatement pstat=null;
		int rs=0;
		String sql="insert into relation(id,d_id)values(?,?)";
		
		try {
			pstat=conn.prepareStatement(sql);
			pstat.setInt(1, pid);
			pstat.setInt(2, did);
			rs=pstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs>0;
	}


	
	public  boolean addRelation( List<Integer>list, int did) {
		getDao();
		int rs=0;
		try {
			conn.setAutoCommit(false);
		     for(Integer id:list ) {
			String sql="insert into relation (id,d_id) values("+id+","+did+")";
			
				rs=stat.executeUpdate(sql);
			}
		     conn.commit();
		}
		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		return rs>0;
	}


	


	public boolean deleteRelation(String ids,int did) {
		// TODO Auto-generated method stub
		getDao();
		int rs=0;
		String sql="delete  from relation where d_id="+did+" and id in ("+ids+")";
	
		try {
			rs=stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs>0;
	}
		
	
	public boolean deleteRelation(int tid ,int did) {
		// TODO Auto-generated method stub
		getDao();
		int rs=0;
		String sql="delete  from relation where id ="+tid+" and d_id="+did;

		try {
			rs=stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs>0;
	}
			
			
	}

		
		
