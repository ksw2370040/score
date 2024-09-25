package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
    // 基本となるSQLクエリ
    private String baseSql = "SELECT DISTINCT " +
                              "STUDENT.ENT_YEAR as ent_year, " +
                              "STUDENT.NO AS student_no, " +
                              "STUDENT.NAME AS student_name, " +
                              "STUDENT.class_num AS class_num, " +
                              "TEST1.point as point1, " +
                              "TEST2.point as point2 " +
                              "FROM STUDENT " +
                              "LEFT JOIN TEST AS TEST1 ON TEST1.STUDENT_NO = STUDENT.NO AND TEST1.no = 1 " +
                              "LEFT JOIN TEST AS TEST2 ON TEST2.STUDENT_NO = STUDENT.NO AND TEST2.no = 2 " +
                              "LEFT JOIN SUBJECT ON SUBJECT.SCHOOL_CD = STUDENT.SCHOOL_CD " +
                              "WHERE STUDENT.SCHOOL_CD = ? " +
                              "AND STUDENT.ENT_YEAR = ? " +
                              "AND STUDENT.CLASS_NUM = ? " +
                              "AND SUBJECT.CD = ?";

    /*
     * ResultSetからデータを抽出し、TestListSubjectオブジェクトのリストを作成します。
     *
     * @param rSet SQLクエリの結果セット
     * @return TestListSubjectのリスト
     * @throws Exception SQL処理中に発生した例外をスローします。
     */
    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        List<TestListSubject> list = new ArrayList<>(); // TestListSubjectのリストを初期化
        try {
            while (rSet.next()) { // 結果セットからデータを取得
                TestListSubject TLS = new TestListSubject(); // 新しいTestListSubjectオブジェクトを作成
                Map<Integer, Integer> points = new HashMap<>(); // 点数を保持するマップを作成

                // 学生情報を取得
                String studentName = rSet.getString("student_name"); // 学生名を取得
                String studentNo = rSet.getString("student_no"); // 学生番号を取得
                String classNum = rSet.getString("class_num"); // クラス番号を取得
                int entYear = rSet.getInt("ent_year"); // 入学年を取得
                int point1 = rSet.getInt("point1"); // テスト1の点数を取得
                int point2 = rSet.getInt("point2"); // テスト2の点数を取得

                // 点数をマップに追加
                points.put(1, point1);
                points.put(2, point2);

                // TestListSubjectオブジェクトにデータを設定
                TLS.setStudentName(studentName);
                TLS.setStudentNo(studentNo);
                TLS.setClassNum(classNum);
                TLS.setEntYear(entYear);
                TLS.setPoints(points);

                list.add(TLS); // リストに追加
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace(); // エラーを表示
        }
        return list; // TestListSubjectのリストを返す
    }

    /*
     * 学校、年度、クラス、科目に基づいてテスト情報をフィルタリングします。
     *
     * @param entYear 入学年度
     * @param classNum クラス番号
     * @param subject 科目オブジェクト
     * @param school 学校オブジェクト
     * @return フィルタリングされたTestListSubjectのリスト
     * @throws Exception SQL処理中に発生した例外をスローします。
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>(); // TestListSubjectのリストを初期化
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null; // PreparedStatementを初期化
        ResultSet rSet = null; // ResultSetを初期化
        try {
            statement = connection.prepareStatement(baseSql); // SQLクエリを準備
            statement.setString(1, school.getCd()); // 学校コードを設定
            statement.setInt(2, entYear); // 入学年を設定
            statement.setString(3, classNum); // クラス番号を設定
            statement.setString(4, subject.getCd()); // 科目コードを設定
            rSet = statement.executeQuery(); // クエリを実行
            list = postFilter(rSet); // 結果セットからリストを作成
        } catch (Exception e) {
            throw e; // 例外を再スロー
        } finally {
            // リソースをクリーンアップ
            if (rSet != null) {
                try {
                    rSet.close(); // ResultSetを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // エラーが発生した場合は例外をスロー
                }
            }
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // エラーが発生した場合は例外をスロー
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // 接続を閉じる
                } catch (SQLException sqle) {
                    throw sqle; // エラーが発生した場合は例外をスロー
                }
            }
        }
        return list; // フィルタリングされたTestListSubjectのリストを返す
    }
}
