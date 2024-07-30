package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDAO extends Dao {
	public Teacher get(String id,String password)
	throws Exception{
		Teacher teacher=null;
		Connection connection=getConnection();
		PreparedStatement statement = null;
		try{
		statement=connection.prepareStatement("select * from Teacher where id=? and password=?");
		statement.setString(1,id);
		statement.setString(2, password);
		ResultSet rSet = statement.executeQuery();
		SchoolDao schoolDao = new SchoolDao();

		if (rSet.next()){
			teacher=new Teacher();
			teacher.setId(rSet.getString("id"));
			teacher.setName(rSet.getString("name"));
			teacher.setPassword(rSet.getString("password"));
			teacher.setSchool(schoolDao.get(rSet.getString("school_cd")));
		}else{
			teacher = null;
		}
	}catch(Exception e){
		throw e;
	}finally{
		if(statement != null){
			try{
				statement.close();
			}catch  (SQLException sqle){
				throw sqle;
			}
		}
		if (connection != null){
			try{
				connection.close();
			}catch (SQLException sqle){
				throw sqle;
			}
		}
	}
	return teacher;

	}
}