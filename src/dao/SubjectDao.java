package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao{
	private String baseSql = "select * from subject where school_cd=?";
	public Subject get(String cd,School school) throws Exception{
		Subject subject = new Subject();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try{
			statement =connection.prepareStatement("select * from subject where cd=? and school_cd=?");
			statement.setString(1, cd);
			statement.setString(2,school.getCd());
			ResultSet rSet = statement.executeQuery();
			SchoolDao schoolDao = new SchoolDao();
			if (rSet.next()){
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
			}else{
				subject = null;
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
		return subject;

	}

	private List<Subject> postFilter(ResultSet rSet, School school)throws Exception{
		List<Subject> list = new ArrayList<>();
		try{
			while (rSet.next()) {
				Subject subject = new Subject();
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				subject.setSchool(school);
				list.add(subject);
			}
		}catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}
	public List<Subject> filter(School school) throws Exception {
		List<Subject> list =new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String order = " order by cd asc";
		statement = connection.prepareStatement(baseSql +order);
		statement.setString(1, school.getCd());
		rSet = statement.executeQuery();
		list = postFilter(rSet,school);

		return list;
	}
	public List<Subject> filter(School school,String name) throws Exception {
		List<Subject> list =new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String order = " order by cd asc";
		String option ="and name like ?";
		statement = connection.prepareStatement(baseSql +" "+option+" "+order);
		statement.setString(1, school.getCd());
		statement.setString(2, "%" + name + "%");

		rSet = statement.executeQuery();
		list = postFilter(rSet,school);

		return list;
	}
	public boolean save(Subject subject) throws Exception{
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		try{
			Subject old = get(subject.getCd(),subject.getSchool());
			if (old == null){
				statement = connection.prepareStatement(
						"insert into subject(school_cd, cd, name) values(?,?,?)");
				statement.setString(1, subject.getSchool().getCd());
				statement.setString(2, subject.getCd());
				statement.setString(3, subject.getName());
			}else{
				statement = connection.prepareStatement("update subject set name=? where cd=?");
				statement.setString(1, subject.getName());
				statement.setString(2,subject.getCd());
			}
			count = statement.executeUpdate();
		}catch (Exception e){
			throw e;
		}finally{
			if (statement != null){
				try{
					statement.close();
				}catch (SQLException sqle){
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
		if(count > 0){
			return true;
		}else{
			return false ;
	}
	}
	public Subject delete(String cd) throws Exception{
		Subject subject = new Subject();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try{
			statement =connection.prepareStatement("delete from subject where cd=?");
			statement.setString(1, cd);
			statement.executeUpdate();
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
		return subject;

	}
	}