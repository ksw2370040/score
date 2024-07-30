package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDAO extends Dao {

    /**
     * IDとパスワードに基づいて教師情報を取得します。
     * @param id 教師ID
     * @param password 教師パスワード
     * @return 該当する教師オブジェクト、存在しない場合はnull
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public Teacher get(String id, String password) throws Exception {
        Teacher teacher = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            // 教師情報を取得するSQLクエリを準備
            statement = connection.prepareStatement("select * from Teacher where id=? and password=?");
            statement.setString(1, id);
            statement.setString(2, password);
            rSet = statement.executeQuery();
            SchoolDao schoolDao = new SchoolDao();

            if (rSet.next()) {
                teacher = new Teacher();
                teacher.setId(rSet.getString("id"));
                teacher.setName(rSet.getString("name"));
                teacher.setPassword(rSet.getString("password"));
                teacher.setSchool(schoolDao.get(rSet.getString("school_cd")));
            } else {
                teacher = null; // 該当する教師がない場合はnullを返す
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rSet != null) try { rSet.close(); } catch (SQLException e) { throw e; }
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return teacher;
    }
}
