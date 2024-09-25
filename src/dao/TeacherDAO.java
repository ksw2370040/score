package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Teacher;

public class TeacherDAO extends Dao {

    /*
     * IDとパスワードに基づいて教師情報を取得します。
     *
     * @param id 教師のID (String)
     * @param password 教師のパスワード (String)
     * @return 該当する教師を含むTeacherオブジェクト。見つからない場合はnullを返します。
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public Teacher get(String id, String password) throws Exception {
        Teacher teacher = null; // 教師オブジェクトを初期化
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            // 教師情報を取得するSQLクエリを準備
            statement = connection.prepareStatement("select * from Teacher where id=? and password=?");
            statement.setString(1, id); // 教師のIDを設定
            statement.setString(2, password); // 教師のパスワードを設定
            rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            SchoolDao schoolDao = new SchoolDao(); // SchoolDaoインスタンスを作成

            if (rSet.next()) { // 結果セットにデータがある場合
                teacher = new Teacher(); // 新しいTeacherオブジェクトを作成
                teacher.setId(rSet.getString("id")); // 教師のIDを設定
                teacher.setName(rSet.getString("name")); // 教師の名前を設定
                teacher.setPassword(rSet.getString("password")); // 教師のパスワードを設定
                teacher.setSchool(schoolDao.get(rSet.getString("school_cd"))); // 学校情報を取得して設定
            } else {
                teacher = null; // 該当する教師がない場合はnullを返す
            }
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (rSet != null) try { rSet.close(); } catch (SQLException e) { throw e; }
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return teacher; // 教師オブジェクトまたはnullを返す
    }
}
