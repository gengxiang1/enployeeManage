package dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;

public class RegisterDao extends BaseDao {
public boolean add(User user) {
	getDao();
	int rs=0;
	try {	String sql="insert into user (username,password,salt)values(?,?,?)";
	
		PreparedStatement pstat=conn.prepareStatement(sql);
		pstat.setString(1, user.getUserName());
		pstat.setString(2,user.getPassword());
		pstat.setString(3,user.getSalt());
	    rs=pstat.executeUpdate();
	    return rs>0;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		return false;
		
	}
	  	
}



}
