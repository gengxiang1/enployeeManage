package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class ProjectDao extends BaseDao {

 public List<Project> search() {
		List<Project> list = new ArrayList();
		getDao();
			String sql = "select * from project";
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
		 return list;}
			


 
 
 
 public List<Project> search(String ids) {
		List<Project> list = new ArrayList();
		getDao();
			String sql = "select * from project where id in ("+ids+")";
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
		 return list;}
			




//
//	
//
//	
	public void add(Project pro) {
	    getDao();

    try {
     String sql="insert into project (name) values"
     		+ "('"+pro.getName()+"')";
    
     int rs = stat.executeUpdate(sql);
     			

     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} finally {
     			close(null, stat, conn);
     		}

     	}

//	public void update(Project emp) {
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
		String sql1 = "delete from relation where id in ("+ids+")";
		 try {
				
				rs = stat.executeUpdate(sql1);
				;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql = "delete from project where id in ("+ids+")";
			
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


	public void update(List<Project> list) {
		 try {	
	 getDao();
		int rs=0;
	   for(Project pro:list) {
			String sql = "update project set name='"+pro.getName()+"'"
					+ " where id in("+pro.getId()+")";
			
		
			
			rs = stat.executeUpdate(sql);}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(null,stat,conn);
		}
		
		
	   
		
	}

	public List<Project> search(Project condition,int begin,int size) {
		List<Project> list = new ArrayList(); 
		try {
		
		
		getDao();
		
		String where="where 1=1";
		if(condition.getName()!=null&!("".equals(condition.getName()))) {
			where+="  and name='"+condition.getName()+"'";
		}
		
		String sql = "select * from Project  "+where+ " limit "+ begin+","+size;
	
			
	
			rs = stat.executeQuery(sql);
			
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(Integer.parseInt(rs.getString("id")));
				pro.setName(rs.getString("name"));
				
				list.add(pro);}
				
		
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
			close(rs,stat,conn);
		}
		 return list; 
			
		
	}

	public int  searchCount(Project condition) {
		List<Project> list = new ArrayList(); 
		try {
		
		
		getDao();
		
		String where="where 1=1";
		if(condition.getName()!=null&!("".equals(condition.getName()))) {
			where+="  and name='"+condition.getName()+"'";
		}
		
		String sql = "select * from Project  "+where;
	
			
	
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
		 return list.size(); 
			
		
	}

		
			
}