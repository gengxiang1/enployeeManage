package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BaiduDao {
	
	
public List<String> search(String a){
	 List<String>list=new ArrayList();
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","123");
		Statement stat= conn.createStatement();

		 String sql="select * from content where content like '"+ a +"  %'";
		 ResultSet rs=stat.executeQuery(sql);
	
		 while(rs.next()) {
			 list.add(rs.getString("content"));
			 
		 }
		 rs.close();
		 stat.close();
		 conn.close();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	
	}


}
