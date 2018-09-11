package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDao extends BaseDao {
	
	public boolean search(String name,String password) {
		boolean flag=false;
		getDao();
//		Connection conn=null;
//		 try {
//		Class.forName("com.mysql.jdbc.Driver");
//		 
//		 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root","123");
//		  Statement stat=conn.createStatement();
		
		 
		
		try {
			 String sql="select * from user where username='"+name+"'and password='"+password+"'";
			rs = stat.executeQuery(sql);
			 if(rs.next()){
				 flag=true;
				
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(rs,stat,conn);
			
		}

		return flag;
		 
	}
	}
		
//		 }
//		 catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//		finally {
//				
//				close(rs,stat,conn);
//				
//			}
//		return flag;
//		
//	}
//	
//
//}
