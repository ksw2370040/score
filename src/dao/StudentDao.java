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

    /*
     * 学生番号に基づいて学生情報を取得します。
     *
     * @param no 学生番号 (String)
     * @return 学生情報を含むStudentオブジェクト。見つからなかった場合はnullを返します。
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
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
                student.setNo(rSet.getString("no")); // 学生番号をセット
                student.setName(rSet.getString("name")); // 学生名をセット
                student.setEntYear(rSet.getInt("ent_year")); // 入学年をセット
                student.setClassNum(rSet.getString("class_num")); // クラス番号をセット
                student.setIsAttend(rSet.getBoolean("is_attend")); // 出席状況をセット
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

    /*
     * ResultSetからデータを抽出し、学生のリストを作成します。
     *
     * @param rSet ResultSet オブジェクト
     * @param school 学校情報 (School)
     * @return 学生のリスト (List<Student>)
     * @throws Exception データ抽出中に発生した例外をスローします。
     */
    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>(); // 学生オブジェクトのリストを初期化
        try {
            while (rSet.next()) { // 結果セットからデータを繰り返し取得
                Student student = new Student(); // 新しい学生オブジェクトを作成
                student.setNo(rSet.getString("no")); // 学生番号をセット
                student.setName(rSet.getString("name")); // 学生名をセット
                student.setEntYear(rSet.getInt("ent_year")); // 入学年をセット
                student.setClassNum(rSet.getString("class_num")); // クラス番号をセット
                student.setIsAttend(rSet.getBoolean("is_attend")); // 出席状況をセット
                student.setSchool(school); // 学校情報をセット
                list.add(student); // 学生オブジェクトをリストに追加
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace(); // エラーをログに出力
        }
        return list; // 学生のリストを返す
    }

    /*
     * 学校コード、入学年、クラス番号、出席状況に基づいて学生情報をフィルタリングし、学生のリストを返します。
     *
     * @param school 学校情報 (School)
     * @param entYear 入学年 (int)
     * @param classNum クラス番号 (String)
     * @param isAttend 出席状況 (boolean)
     * @return フィルタリングされた学生のリスト (List<Student>)
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>(); // 学生のリストを初期化
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;

        // 条件と順序を設定
        String condition = "and ent_year=? and class_num=?"; // 入学年とクラス番号の条件
        String order = " order by no asc"; // 学生番号で昇順に並べる
        String conditionIsAttend = ""; // 出席状況に関する条件の初期化
        if (isAttend) {
            conditionIsAttend = "and is_attend=true"; // 出席している場合の条件
        }

        try {
            // SQLクエリを準備
            statement = connection.prepareStatement(baseSql + " " + condition + " " + conditionIsAttend + " " + order);
            statement.setString(1, school.getCd()); // 学校コードをセット
            statement.setInt(2, entYear); // 入学年をセット
            statement.setString(3, classNum); // クラス番号をセット
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

    /*
     * 学校コード、入学年、出席状況に基づいて学生情報をフィルタリングし、学生のリストを返します。
     *
     * @param school 学校情報 (School)
     * @param entYear 入学年 (int)
     * @param isAttend 出席状況 (boolean)
     * @return フィルタリングされた学生のリスト (List<Student>)
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>(); // 学生のリストを初期化
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;

        // 条件と順序を設定
        String condition = "and ent_year=?"; // 入学年の条件
        String order = " order by no asc"; // 学生番号で昇順に並べる
        String conditionIsAttend = ""; // 出席状況に関する条件の初期化
        if (isAttend) {
            conditionIsAttend = "and is_attend=true"; // 出席している場合の条件
        }

        try {
            // SQLクエリを準備
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            statement.setString(1, school.getCd()); // 学校コードをセット
            statement.setInt(2, entYear); // 入学年をセット
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
        /*
         * 学校コードと出席状況に基づいて学生情報をフィルタリングし、学生のリストを返します。
         *
         * @param school 学校情報 (School)
         * @param isAttend 出席状況 (boolean)
         * @return フィルタリングされた学生のリスト (List<Student>)
         * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
         */
        public List<Student> filter(School school, boolean isAttend) throws Exception {
            List<Student> list = new ArrayList<>(); // 学生のリストを初期化
            Connection connection = getConnection(); // データベース接続を取得
            PreparedStatement statement = null;
            ResultSet rSet = null;

            // 順序を設定
            String order = " order by no asc"; // 学生番号で昇順に並べる
            String conditionIsAttend = ""; // 出席状況に関する条件の初期化
            if (isAttend) {
                conditionIsAttend = "and is_attend=true"; // 出席している場合の条件を追加
            }

            try {
                // SQLクエリを準備
                statement = connection.prepareStatement(baseSql + " " + conditionIsAttend + " " + order);
                statement.setString(1, school.getCd()); // 学校コードをセット
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

        /*
         * 学校コードに基づいて全学生情報を取得し、学生のリストを返します。
         *
         * @param school 学校情報 (School)
         * @return 学生のリスト (List<Student>)
         * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
         */
        public List<Student> filter(School school) throws Exception {
            List<Student> list = new ArrayList<>(); // 学生のリストを初期化
            Connection connection = getConnection(); // データベース接続を取得
            PreparedStatement statement = null;
            ResultSet rSet = null;

            // 順序を設定
            String order = " order by no asc"; // 学生番号で昇順に並べる
            try {
                // SQLクエリを準備
                statement = connection.prepareStatement(baseSql + " " + order);
                statement.setString(1, school.getCd()); // 学校コードをセット
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

        /*
         * 学生情報を保存します。学生番号が存在しない場合は新規追加、存在する場合は更新します。
         *
         * @param student 保存する学生情報 (Student)
         * @return 成功した場合はtrue、失敗した場合はfalseを返します。
         * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
         */
        public boolean save(Student student) throws Exception {
            Connection connection = getConnection(); // データベース接続を取得
            PreparedStatement statement = null;
            int count = 0; // 更新または追加した行数をカウント

            try {
                // 学生番号で既存の学生を取得
                Student old = get(student.getNo());
                if (old == null) {
                    // 学生が存在しない場合は新規追加
                    statement = connection.prepareStatement(
                            "insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?,?,?,?,?,?)");
                    statement.setString(1, student.getNo()); // 学生番号をセット
                    statement.setString(2, student.getName()); // 学生名をセット
                    statement.setInt(3, student.getEntYear()); // 入学年をセット
                    statement.setString(4, student.getClassNum()); // クラス番号をセット
                    statement.setBoolean(5, student.isAttend()); // 出席状況をセット
                    statement.setString(6, student.getSchool().getCd()); // 学校コードをセット
                } else {
                    // 学生が存在する場合は更新
                    statement = connection.prepareStatement("update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
                    statement.setString(1, student.getName()); // 学生名をセット
                    statement.setInt(2, student.getEntYear()); // 入学年をセット
                    statement.setString(3, student.getClassNum()); // クラス番号をセット
                    statement.setBoolean(4, student.isAttend()); // 出席状況をセット
                    statement.setString(5, student.getNo()); // 学生番号をセット
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

        /*
         * 学生番号に基づいて学生情報を削除します。
         *
         * @param no 学生番号 (String)
         * @return 削除した学生の情報を含むStudentオブジェクト。削除処理後に情報は含まれていない可能性があります。
         * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
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
