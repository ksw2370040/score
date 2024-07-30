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

    /**
     * 特定の学生、科目、学校、テスト番号に基づいてテスト情報を取得します。
     * @param student 学生オブジェクト
     * @param subject 科目オブジェクト
     * @param school 学校オブジェクト
     * @param no テスト番号
     * @return 該当するテストオブジェクト、存在しない場合はnull
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = new Test();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?");
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4, no);
            ResultSet rSet = statement.executeQuery();
            SchoolDao schoolDao = new SchoolDao();
            StudentDao studentDao = new StudentDao();
            SubjectDao subjectDao = new SubjectDao();

            if (rSet.next()) {
                test.setStudent(studentDao.get(rSet.getString("student_no")));
                test.setSubject(subjectDao.get(rSet.getString("subject_cd"), schoolDao.get(rSet.getString("school_cd"))));
                test.setSchool(schoolDao.get(rSet.getString("school_cd")));
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));
            } else {
                test = null;
            }
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return test;
    }

    /**
     * 結果セットからテストのリストを作成します。
     * @param rSet 結果セット
     * @param school 学校オブジェクト
     * @return テストのリスト
     * @throws Exception データベース関連の例外
     */
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                String studentNo = rSet.getString("student_no");
                String subjectCd = rSet.getString("subject_cd");

                StudentDao stuDao = new StudentDao();
                Student student = stuDao.get(studentNo);

                SubjectDao subDao = new SubjectDao();
                Subject subject = subDao.get(subjectCd, school);

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
            throw e;
        }
        return list;
    }

    /**
     * 条件に基づいてテストのリストをフィルタリングします。
     * @param entYear 入学年
     * @param classNum クラス番号
     * @param subject 科目オブジェクト
     * @param num テスト番号
     * @param school 学校オブジェクト
     * @return フィルタリングされたテストのリスト
     * @throws Exception データベース関連の例外
     */
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String condition = " AND a.ENT_YEAR =? AND a.CLASS_NUM = ? AND SUBJECT.CD = ?";

        try {
            statement = connection.prepareStatement(baseSql + condition);
            statement.setInt(1, num);
            statement.setInt(2, num);
            statement.setInt(3, num);
            statement.setString(4, school.getCd());
            statement.setInt(5, entYear);
            statement.setString(6, classNum);
            statement.setString(7, subject.getCd());
            rSet = statement.executeQuery();
            list = postFilter(rSet, school);
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return list;
    }

    /**
     * テストのリストを保存します。
     * @param list 保存するテストのリスト
     * @return 成功した場合はtrue、失敗した場合はfalse
     * @throws Exception データベース関連の例外
     */
    public boolean save(List<Test> list) throws Exception {
        Connection connection = getConnection();
        boolean result = false;
        try {
            for (Test test : list) {
                save(test, connection);
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

    /**
     * 単一のテストを保存します。
     * @param test 保存するテストオブジェクト
     * @param connection データベース接続
     * @return 成功した場合はtrue、失敗した場合はfalse
     * @throws Exception データベース関連の例外
     */
    private boolean save(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        int count = 0;
        Test old = null;
        try {
            old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());
            if (old == null) {
                statement = connection.prepareStatement("INSERT INTO test(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES(?,?,?,?,?,?)");
                statement.setString(1, test.getStudent().getNo());
                statement.setString(2, test.getSubject().getCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClassNum());
            } else {
                statement = connection.prepareStatement("UPDATE test SET POINT=? WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?");
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
        return count > 0;
    }

    /**
     * テストのリストを削除します。
     * @param list 削除するテストのリスト
     * @return 成功した場合はtrue、失敗した場合はfalse
     * @throws Exception データベース関連の例外
     */
    public boolean delete(List<Test> list) throws Exception {
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

    /**
     * 単一のテストを削除します。
     * @param test 削除するテストオブジェクト
     * @param connection データベース接続
     * @return 成功した場合はtrue、失敗した場合はfalse
     * @throws Exception データベース関連の例外
     */
    private boolean delete(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        int count = 0;
        try {
            statement = connection.prepareStatement("DELETE FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=? AND class_num=?");
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());
            statement.setString(5, test.getClassNum());
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
        return count > 0;
    }
}
