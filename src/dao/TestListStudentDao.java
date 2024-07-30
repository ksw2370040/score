package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{
	private String baseSql = "SELECT SUB.NAME as subject_name,T.SUBJECT_CD as subject_cd,T.NO as no, "+
								"T.POINT as point  FROM TEST T "+
								"join SUBJECT  SUB on T.SUBJECT_CD =SUB.CD "+
								"Where T.SCHOOL_CD=? and T.STUDENT_NO like ?";
	private List<TestListStudent> postFilter(ResultSet rSet)throws Exception{
		List<TestListStudent> list = new ArrayList<>();
		try{
			while (rSet.next()) {
				TestListStudent TLS = new TestListStudent();
				String subject_name = rSet.getString("subject_name");
				String subject_cd = rSet.getString("subject_cd");
				int num = rSet.getInt("no");
				int point = rSet.getInt("point");
				TLS.setSubjectName(subject_name);;
				TLS.setSubjectCd(subject_cd);
				TLS.setNum(num);
				TLS.setPoint(point);
				list.add(TLS);
			}
		}catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}
	public List<TestListStudent> filter(Student student) throws Exception {
		List<TestListStudent> list =new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		statement = connection.prepareStatement(baseSql);
		statement.setString(1, student.getSchool().getCd());
		statement.setString(2, "%" + student.getNo() + "%");
		rSet = statement.executeQuery();
		list = postFilter(rSet);

		return list;
	}
}