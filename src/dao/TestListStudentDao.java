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

    /*
     * ResultSetからデータを抽出し、TestListStudentオブジェクトのリストを作成します。
     *
     * @param rSet SQLクエリの結果セット
     * @return TestListStudentのリスト
     * @throws Exception SQL処理中に発生した例外をスローします。
     */
    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>(); // TestListStudentのリストを初期化
        try {
            while (rSet.next()) { // 結果セットからデータを取得
                TestListStudent TLS = new TestListStudent(); // 新しいTestListStudentオブジェクトを作成
                String subjectName = rSet.getString("subject_name"); // 科目名を取得
                String subjectCd = rSet.getString("subject_cd"); // 科目コードを取得
                int num = rSet.getInt("no"); // テスト番号を取得
                int point = rSet.getInt("point"); // 得点を取得
                TLS.setSubjectName(subjectName); // 科目名を設定
                TLS.setSubjectCd(subjectCd); // 科目コードを設定
                TLS.setNum(num); // テスト番号を設定
                TLS.setPoint(point); // 得点を設定
                list.add(TLS); // リストに追加
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace(); // エラーを表示
        }
        return list; // TestListStudentのリストを返す
    }

    /*
     * 学生に基づいてテスト情報をフィルタリングします。
     *
     * @param student フィルタリングに使用する学生オブジェクト
     * @return フィルタリングされたTestListStudentのリスト
     * @throws Exception SQL処理中に発生した例外をスローします。
     */
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>(); // TestListStudentのリストを初期化
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null; // PreparedStatementを初期化
        ResultSet rSet = null; // ResultSetを初期化
        try {
            statement = connection.prepareStatement(baseSql); // SQLクエリを準備
            statement.setString(1, student.getSchool().getCd()); // 学校コードを設定
            statement.setString(2, "%" + student.getNo() + "%"); // 学生番号をLIKE条件で設定
            rSet = statement.executeQuery(); // クエリを実行
            list = postFilter(rSet); // 結果セットからリストを作成
        } catch (SQLException e) {
            e.printStackTrace(); // エラーを表示
            throw e; // 例外を再スロー
        } finally {
            // リソースをクリーンアップ
            if (rSet != null) {
                try {
                    rSet.close(); // ResultSetを閉じる
                } catch (SQLException sqle) {
                    sqle.printStackTrace(); // エラーを表示
                }
            }
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    sqle.printStackTrace(); // エラーを表示
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // 接続を閉じる
                } catch (SQLException sqle) {
                    sqle.printStackTrace(); // エラーを表示
                }
            }
        }
        return list; // フィルタリングされたTestListStudentのリストを返す
    }
}
