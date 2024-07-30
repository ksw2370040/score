package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao{
	private String baseSql =
			"SELECT a.no AS student_no, " +
		               "subject.cd AS subject_cd, " +
		               "a.school_cd AS school_cd, " +
		               "CASE " +
		               "    WHEN test.no <> ? THEN ? " +
		               "    ELSE test.no " +
		               "END AS no, " +
		               "CASE " +
		               "    WHEN test.no <> ? THEN 0 " +
		               "    ELSE test.point " +
		               "END AS point, " +
		               "a.class_num AS class_num " +
		               "FROM STUDENT AS a " +
		               "LEFT JOIN TEST ON a.NO = TEST.STUDENT_NO " +
		               "LEFT JOIN SUBJECT ON a.SCHOOL_CD = SUBJECT.SCHOOL_CD " +
		               "WHERE a.SCHOOL_CD = ? ";

     	public Test get(Student student,Subject subject,School school,int no) throws Exception{
     		Test test = new Test();
     		Connection connection = getConnection();
     		PreparedStatement statement = null;
     		try{
     			statement =connection.prepareStatement("select * from test where student_no=? and subject_cd=? and school_cd=? and no=?");
     			statement.setString(1, student.getNo());
     			statement.setString(2, subject.getCd());
     			statement.setString(3, school.getCd());
     			statement.setInt(4, no);
     			ResultSet rSet = statement.executeQuery();
     			SchoolDao schoolDao = new SchoolDao();
     			StudentDao studentDao = new StudentDao();
     			SubjectDao subjectDao = new SubjectDao();
     			if (rSet.next()){
     				test.setStudent(studentDao.get(rSet.getString("student_no")));
     				test.setSubject(subjectDao.get(rSet.getString("subject_cd"),schoolDao.get(rSet.getString("school_cd"))));
     				test.setSchool(schoolDao.get(rSet.getString("school_cd")));
     				test.setNo(rSet.getInt("no"));
     				test.setPoint(rSet.getInt("point"));
     				test.setClassNum(rSet.getString("class_num"));
     			}else{
     				test = null;
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
     		return test;

     	}

     	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
     	    List<Test> list = new ArrayList<>();
     	    try {
     	        while (rSet.next()) {
     	            String studentNo = rSet.getString("student_no");  // Assuming "student_no" is the correct column name
     	            String subjectCd = rSet.getString("subject_cd");  // Assuming "subject_cd" is the correct column name

     	            StudentDao stuDao = new StudentDao();
     	            Student student = stuDao.get(studentNo);  // Use the actual student number retrieved from ResultSet

     	            SubjectDao subDao = new SubjectDao();
     	            Subject subject = subDao.get(subjectCd, school);  // Use the actual subject code and school

     	            Test test = new Test();
     	            test.setSchool(school);
     	            test.setNo(rSet.getInt("no"));
     	            test.setClassNum(rSet.getString("class_num"));
     	            test.setPoint(rSet.getInt("point"));
     	            test.setStudent(student);
     	            test.setSubject(subject);

     	            list.add(test);
     	        }
     	    } catch (SQLException e) {
     	        e.printStackTrace();
     	        throw e;  // Rethrow SQLException for handling at higher level if needed
     	    }
     	    return list;
     	}

     	public List<Test> filter(int entYear,String classNum ,Subject subject, int num,School school)throws Exception{
     		List<Test> list =new ArrayList<>();
     		Connection connection = getConnection();
     		PreparedStatement statement = null;
     		ResultSet rSet = null;
             String condition = " AND a.ENT_YEAR =? AND a.CLASS_NUM = ? AND SUBJECT.CD = ?";

     		try{
     			statement = connection.prepareStatement(baseSql +" "+ condition );
     			statement.setInt(1, num);
     			statement.setInt(2, num);
     			statement.setInt(3, num);
     			statement.setString(4, school.getCd());
     			statement.setInt(5, entYear);
     			statement.setString(6, classNum);
     			statement .setString(7, subject.getCd());
     			rSet = statement.executeQuery();
     			list = postFilter(rSet, school);
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
     		return list;
     	}

     	public boolean save(List<Test> list) throws Exception {
     	    Connection connection = getConnection();
            boolean result = false;
     		try {
    	    for (Test tests : list) {
    	        save(tests, connection);
    	    }
            	    result = true;
            	} catch (Exception e) {
            	    throw e;
             } finally {
                 if (connection != null) {
                     try {
                         connection.close();
                     } catch (SQLException sqle) {
                         throw sqle;
                     }
                 }
             }
             return result;
         }

         private boolean save(Test test, Connection connection) throws Exception {
        	 connection = getConnection();
             PreparedStatement statement = null;
             int count = 0;
             Test old = null;
             try {
                 old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());
                 if (old == null) {
                     statement = connection.prepareStatement("insert into test(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) values(?,?,?,?,?,?)");
                     statement.setString(1, test.getStudent().getNo());
                     statement.setString(2, test.getSubject().getCd());
                     statement.setString(3, test.getSchool().getCd());
                     statement.setInt(4, test.getNo());
                     statement.setInt(5, test.getPoint());
                     statement.setString(6, test.getClassNum());
                 } else {
                     statement = connection.prepareStatement("update test set POINT=? where STUDENT_NO=? and SUBJECT_CD=? and SCHOOL_CD=? and NO=?");
                     statement.setInt(1, test.getPoint());
                     statement.setString(2, test.getStudent().getNo());
                     statement.setString(3, test.getSubject().getCd());
                     statement.setString(4, test.getSchool().getCd());
                     statement.setInt(5, test.getNo());
                 }
                 count = statement.executeUpdate();
                 System.out.println(statement);
             } catch (Exception e) {
                 throw e;
             } finally {
                 if (statement != null) {
                     try {
                         statement.close();
                     } catch (SQLException sqle) {
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
     	public boolean delete(List<Test> list)throws Exception{
     	    Connection connection = getConnection();
     	    boolean result = true;
     	    try {
     	        for (Test test : list) {
     	            result = delete(test, connection);
     	        }
     	    } catch (Exception e) {
     	        throw e;
     	    } finally {
     	        if (connection != null) {
     	            try {
     	                connection.close();
     	            } catch (SQLException sqle) {
     	                throw sqle;
     	            }
     	        }
     	     }
     	    return result;

     	}
     	private boolean delete(Test test, Connection connection) throws Exception{
     		connection = getConnection();
     		PreparedStatement statement = null;
     		int count = 0;

     		try{
     			statement =connection.prepareStatement("delete from test where student_no=? and subject_cd=? and school_cd=? and no=? and class_num = ?");
     			statement.setString(1, test.getStudent().getNo());
     			statement.setString(2, test.getSubject().getCd());
     			statement.setString(3, test.getSchool().getCd());
     			statement.setInt(4, test.getNo());
     			statement.setString(5, test.getClassNum());
     			statement.executeUpdate();
                count = statement.executeUpdate();
                System.out.println(statement);
            } catch (Exception e) {
                throw e;
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException sqle) {
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
     }
