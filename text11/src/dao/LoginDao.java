package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;




public class LoginDao extends BaseDao {
	
	public boolean search(User user) {
		boolean flag=false;
		try { 
			
			Class.forName("com.mysql.jdbc.Driver");
		
		
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","123");
			String sql="select * from user where username=? and password=?";
			PreparedStatement pstat=conn.prepareStatement(sql);
			pstat.setString(1,user.getUserName());
		    pstat.setString(2,user.getPassword());
	ResultSet rs=pstat.executeQuery();
     
		 if(rs.next()){
			 flag=true;
		 }
		
		 }
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
			close(rs,stat,null);
				
			}
		return flag;
		
	}

	public String searchSalt(String userName) {
	getDao();
	String salt="";
   String sql="select salt from user where username ='"+userName+"'";

try {
	ResultSet rs= stat.executeQuery(sql);
	if(rs.next()) {
		salt=rs.getString(1);
		
		
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


 return salt;

	}


	public boolean searchName(String userName) {
	getDao();
	boolean flag=false;
   String sql="select username from user where username ='"+userName+"'";

try {
	ResultSet rs= stat.executeQuery(sql);
	if(rs.next()) {
		flag=true;
		
		
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


return flag;

	}	

	

}
