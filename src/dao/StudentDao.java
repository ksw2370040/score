package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {
    // 基本SQLクエリ: 学校コードに基づいて学生情報を選択する
    private String baseSql = "select * from student where school_cd=?";

    /**
     * 学生番号に基づいて学生情報を取得します。
     * @param no 学生番号
     * @return 学生オブジェクト。学生が見つからなかった場合はnull
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public Student get(String no) throws Exception {
        Student student = new Student(); // 新しい学生オブジェクトを作成
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        try {
            // 学生番号に基づいて学生情報を取得するSQLクエリを準備
            statement = connection.prepareStatement("select * from student where no=?");
            statement.setString(1, no); // プレースホルダーに学生番号を設定
            ResultSet rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            SchoolDao schoolDao = new SchoolDao(); // SchoolDaoインスタンスを作成
            if (rSet.next()) { // 結果セットにデータがある場合
                // 学生オブジェクトにデータを設定
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setIsAttend(rSet.getBoolean("is_attend"));
                student.setSchool(schoolDao.get(rSet.getString("school_cd"))); // 学校情報も取得
            } else {
                // 学生が見つからない場合はnullを返す
                student = null;
            }
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Connectionを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
        }
        return student; // 学生オブジェクトまたはnullを返す
    }

    /**
     * ResultSetからデータを抽出し、学生のリストを作成します。
     * @param rSet 結果セット
     * @param school 学校オブジェクト
     * @return 学生のリスト
     */
    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Student student = new Student();
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setIsAttend(rSet.getBoolean("is_attend"));
                student.setSchool(school);
                list.add(student);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace(); // エラーをログに出力
        }
        return list;
    }

    /**
     * 学校、入学年、クラス番号、出席状況に基づいて学生情報をフィルタリングし、学生のリストを返します。
     * @param school 学校オブジェクト
     * @param entYear 入学年
     * @param classNum クラス番号
     * @param isAttend 出席状況
     * @return 学生のリスト
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        // 条件と順序を設定
        String condition = "and ent_year=? and class_num=?";
        String order = " order by no asc";
        String conditionIsAttend = "";
        if (isAttend) {
            conditionIsAttend = "and is_attend=true";
        }
        try {
            // SQLクエリを準備
            statement = connection.prepareStatement(baseSql + " " + condition + " " + conditionIsAttend + " " + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            list = postFilter(rSet, school); // 結果セットからリストを作成
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Connectionを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
        }
        return list; // 学生のリストを返す
    }

    /**
     * 学校、入学年、出席状況に基づいて学生情報をフィルタリングし、学生のリストを返します。
     * @param school 学校オブジェクト
     * @param entYear 入学年
     * @param isAttend 出席状況
     * @return 学生のリスト
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        // 条件と順序を設定
        String condition = "and ent_year=?";
        String order = " order by no asc";
        String conditionIsAttend = "";
        if (isAttend) {
            conditionIsAttend = "and is_attend=true";
        }
        try {
            // SQLクエリを準備
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            list = postFilter(rSet, school); // 結果セットからリストを作成
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Connectionを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
        }
        return list; // 学生のリストを返す
    }

    /**
     * 学校と出席状況に基づいて学生情報をフィルタリングし、学生のリストを返します。
     * @param school 学校オブジェクト
     * @param isAttend 出席状況
     * @return 学生のリスト
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        // 順序を設定
        String order = " order by no asc";
        String conditionIsAttend = "";
        if (isAttend) {
            conditionIsAttend = "and is_attend=true";
        }
        try {
            // SQLクエリを準備
            statement = connection.prepareStatement(baseSql + " " + conditionIsAttend + " " + order);
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            list = postFilter(rSet, school); // 結果セットからリストを作成
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Connectionを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
        }
        return list; // 学生のリストを返す
    }

    /**
     * 学校に基づいて全学生情報を取得し、学生のリストを返します。
     * @param school 学校オブジェクト
     * @return 学生のリスト
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public List<Student> filter(School school) throws Exception {
        List<Student> list = new ArrayList<>();
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        // 順序を設定
        String order = " order by no asc";
        try {
            // SQLクエリを準備
            statement = connection.prepareStatement(baseSql + " " + order);
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            list = postFilter(rSet, school); // 結果セットからリストを作成
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Connectionを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
        }
        return list; // 学生のリストを返す
    }

    /**
     * 学生情報を保存します。学生番号が存在しない場合は新規追加、存在する場合は更新します。
     * @param student 学生オブジェクト
     * @return 成功した場合はtrue、それ以外はfalse
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public boolean save(Student student) throws Exception {
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        int count = 0;
        try {
            // 学生番号で既存の学生を取得
            Student old = get(student.getNo());
            if (old == null) {
                // 学生が存在しない場合は新規追加
                statement = connection.prepareStatement(
                        "insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?,?,?,?,?,?)");
                statement.setString(1, student.getNo());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getEntYear());
                statement.setString(4, student.getClassNum());
                statement.setBoolean(5, student.isAttend());
                statement.setString(6, student.getSchool().getCd());
            } else {
                // 学生が存在する場合は更新
                statement = connection.prepareStatement("update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
                statement.setString(1, student.getName());
                statement.setInt(2, student.getEntYear());
                statement.setString(3, student.getClassNum());
                statement.setBoolean(4, student.isAttend());
                statement.setString(5, student.getNo());
            }
            count = statement.executeUpdate(); // クエリを実行
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Connectionを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
        }
        return count > 0; // 更新または追加が成功した場合はtrue、それ以外はfalse
    }

    /**
     * 学生番号に基づいて学生情報を削除します。
     * @param no 学生番号
     * @return 削除された学生オブジェクト
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public Student delete(String no) throws Exception {
        Student student = new Student(); // 削除対象の学生オブジェクトを作成
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        try {
            // 学生番号に基づいて学生情報を削除するSQLクエリを準備
            statement = connection.prepareStatement("delete from student where no=?");
            statement.setString(1, no); // プレースホルダーに学生番号を設定
            statement.executeUpdate(); // クエリを実行
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Connectionを閉じる
                } catch (SQLException sqle) {
                    throw sqle; // 例外が発生した場合は再スロー
                }
            }
        }
        return student; // 削除した学生オブジェクトを返す
    }
}
