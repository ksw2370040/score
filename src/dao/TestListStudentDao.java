package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
    // 基本となるSQLクエリ
    private String baseSql = "SELECT SUB.NAME as subject_name, T.SUBJECT_CD as subject_cd, T.NO as no, " +
                             "T.POINT as point " +
                             "FROM TEST T " +
                             "JOIN SUBJECT SUB ON T.SUBJECT_CD = SUB.CD " +
                             "WHERE T.SCHOOL_CD = ? AND T.STUDENT_NO LIKE ?";

    /**
     * 結果セットから TestListStudent のリストを作成します。
     * @param rSet 結果セット
     * @return TestListStudent のリスト
     * @throws Exception データベース関連の例外
     */
    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                TestListStudent TLS = new TestListStudent();
                String subjectName = rSet.getString("subject_name");
                String subjectCd = rSet.getString("subject_cd");
                int num = rSet.getInt("no");
                int point = rSet.getInt("point");
                TLS.setSubjectName(subjectName);
                TLS.setSubjectCd(subjectCd);
                TLS.setNum(num);
                TLS.setPoint(point);
                list.add(TLS);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 学生に基づいてテスト情報をフィルタリングします。
     * @param student 学生オブジェクト
     * @return TestListStudent のリスト
     * @throws Exception データベース関連の例外
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, student.getSchool().getCd());
            statement.setString(2, "%" + student.getNo() + "%");
            rSet = statement.executeQuery();
            list = postFilter(rSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return list;
    }
}
