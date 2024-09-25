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

public class TestDao extends Dao {

    // 基本となるSQLクエリ
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

    /*
     * 特定の学生番号、科目、学校コード、テスト番号に基づいてテスト情報を取得します。
     *
     * @param student 学生オブジェクト
     * @param subject 科目オブジェクト
     * @param school 学校オブジェクト
     * @param no テスト番号
     * @return 該当するテストを含むTestオブジェクト。見つからない場合はnullを返します。
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = new Test();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // テスト情報を取得するSQLクエリを準備
            statement = connection.prepareStatement("SELECT * FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?");
            statement.setString(1, student.getNo()); // 学生番号を設定
            statement.setString(2, subject.getCd()); // 科目コードを設定
            statement.setString(3, school.getCd()); // 学校コードを設定
            statement.setInt(4, no); // テスト番号を設定
            ResultSet rSet = statement.executeQuery(); // クエリを実行

            SchoolDao schoolDao = new SchoolDao(); // SchoolDaoのインスタンスを作成
            StudentDao studentDao = new StudentDao(); // StudentDaoのインスタンスを作成
            SubjectDao subjectDao = new SubjectDao(); // SubjectDaoのインスタンスを作成

            if (rSet.next()) { // 結果セットにデータがある場合
                test.setStudent(studentDao.get(rSet.getString("student_no"))); // 学生情報を設定
                test.setSubject(subjectDao.get(rSet.getString("subject_cd"), schoolDao.get(rSet.getString("school_cd")))); // 科目情報を設定
                test.setSchool(schoolDao.get(rSet.getString("school_cd"))); // 学校情報を設定
                test.setNo(rSet.getInt("no")); // テスト番号を設定
                test.setPoint(rSet.getInt("point")); // 得点を設定
                test.setClassNum(rSet.getString("class_num")); // クラス番号を設定
            } else {
                test = null; // 該当するテストがない場合はnullを返す
            }
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return test; // テストオブジェクトまたはnullを返す
    }

    /*
     * ResultSetからデータを抽出し、リストを作成します。
     *
     * @param rSet 結果セット
     * @param school 学校オブジェクト
     * @return 抽出したテストのリスト
     * @throws Exception データベース関連の例外
     */
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>(); // テストリストを初期化
        try {
            while (rSet.next()) { // 結果セットからデータを取得
                String studentNo = rSet.getString("student_no");
                String subjectCd = rSet.getString("subject_cd");

                StudentDao stuDao = new StudentDao(); // StudentDaoのインスタンスを作成
                Student student = stuDao.get(studentNo); // 学生情報を取得

                SubjectDao subDao = new SubjectDao(); // SubjectDaoのインスタンスを作成
                Subject subject = subDao.get(subjectCd, school); // 科目情報を取得

                Test test = new Test(); // 新しいTestオブジェクトを作成
                test.setSchool(school); // 学校情報を設定
                test.setNo(rSet.getInt("no")); // テスト番号を設定
                test.setClassNum(rSet.getString("class_num")); // クラス番号を設定
                test.setPoint(rSet.getInt("point")); // 得点を設定
                test.setStudent(student); // 学生情報を設定
                test.setSubject(subject); // 科目情報を設定

                list.add(test); // リストにテストを追加
            }
        } catch (SQLException e) {
            e.printStackTrace(); // エラーを表示
            throw e; // 例外をスロー
        }
        return list; // テストリストを返す
    }

    /**
     * 条件に基づいてテストのリストをフィルタリングします。
     *
     * @param entYear 入学年
     * @param classNum クラス番号
     * @param subject 科目オブジェクト
     * @param num テスト番号
     * @param school 学校オブジェクト
     * @return フィルタリングされたテストのリスト
     * @throws Exception データベース関連の例外
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>(); // テストリストを初期化
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String condition = " AND a.ENT_YEAR =? AND a.CLASS_NUM = ? AND SUBJECT.CD = ?";

        try {
            statement = connection.prepareStatement(baseSql + condition); // SQLクエリを準備
            statement.setInt(1, num);
            statement.setInt(2, num);
            statement.setInt(3, num);
            statement.setString(4, school.getCd()); // 学校コードを設定
            statement.setInt(5, entYear); // 入学年を設定
            statement.setString(6, classNum); // クラス番号を設定
            statement.setString(7, subject.getCd()); // 科目コードを設定
            rSet = statement.executeQuery(); // クエリを実行
            list = postFilter(rSet, school); // 結果セットからリストを作成
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return list; // フィルタリングされたテストリストを返す
    }

    /**
     * テストのリストを保存します。
     *
     * @param list 保存するテストのリスト
     * @return 成功した場合はtrue、失敗した場合はfalse
     * @throws Exception データベース関連の例外
     */
    public boolean save(List<Test> list) throws Exception {
        Connection connection = getConnection(); // データベース接続を取得
        boolean result = false; // 成功フラグを初期化
        try {
            for (Test test : list) {
                save(test, connection); // 各テストを保存
            }
            result = true; // 保存が成功した場合はtrueに設定
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return result; // 保存結果を返す
    }

    /**
     * 単一のテストを保存します。
     *
     * @param test 保存するテストオブジェクト
     * @param connection データベース接続
     * @return 成功した場合はtrue、失敗した場合はfalse
     * @throws Exception データベース関連の例外
     */
    private boolean save(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        int count = 0;
        Test old = null; // 既存のテストを格納する変数
        try {
            // 既存のテスト情報を取得
            old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());
            if (old == null) {
                // 既存のテストがない場合は新規挿入
                statement = connection.prepareStatement("INSERT INTO test(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES(?,?,?,?,?,?)");
                statement.setString(1, test.getStudent().getNo());
                statement.setString(2, test.getSubject().getCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClassNum());
            } else {
                // 既存のテストがある場合は更新
                statement = connection.prepareStatement("UPDATE test SET POINT=? WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?");
                statement.setInt(1, test.getPoint());
                statement.setString(2, test.getStudent().getNo());
                statement.setString(3, test.getSubject().getCd());
                statement.setString(4, test.getSchool().getCd());
                statement.setInt(5, test.getNo());
            }
            count = statement.executeUpdate(); // クエリを実行
            System.out.println(statement); // 実行したステートメントを出力
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return count > 0; // 更新または挿入が成功した場合はtrueを返す
    }

    /**
     * テストのリストを削除します。
     *
     * @param list 削除するテストのリスト
     * @return 成功した場合はtrue、失敗した場合はfalse
     * @throws Exception データベース関連の例外
     */
    public boolean delete(List<Test> list) throws Exception {
        Connection connection = getConnection(); // データベース接続を取得
        boolean result = true; // 成功フラグを初期化
        try {
            for (Test test : list) {
                result = delete(test, connection); // 各テストを削除
            }
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return result; // 削除結果を返す
    }

    /**
     * 単一のテストを削除します。
     *
     * @param test 削除するテストオブジェクト
     * @param connection データベース接続
     * @return 成功した場合はtrue、失敗した場合はfalse
     * @throws Exception データベース関連の例外
     */
    private boolean delete(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        int count = 0;
        try {
            // テスト情報を削除するSQLクエリを準備
            statement = connection.prepareStatement("DELETE FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=? AND class_num=?");
            statement.setString(1, test.getStudent().getNo()); // 学生番号を設定
            statement.setString(2, test.getSubject().getCd()); // 科目コードを設定
            statement.setString(3, test.getSchool().getCd()); // 学校コードを設定
            statement.setInt(4, test.getNo()); // テスト番号を設定
            statement.setString(5, test.getClassNum()); // クラス番号を設定
            count = statement.executeUpdate(); // クエリを実行
            System.out.println(statement); // 実行したステートメントを出力
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return count > 0; // 削除が成功した場合はtrueを返す
    }
}
