package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
    // 基本SQLクエリ: 学校コードに基づいて科目情報を選択する
    private String baseSql = "select * from subject where school_cd=?";

    /*
     * 科目コードと学校オブジェクトに基づいて科目情報を取得します。
     *
     * @param cd 科目コード (String)
     * @param school 学校情報 (School)
     * @return 該当する科目を含むSubjectオブジェクト。見つからない場合はnullを返します。
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public Subject get(String cd, School school) throws Exception {
        Subject subject = new Subject(); // 新しい科目オブジェクトを作成
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        try {
            // 科目情報を取得するSQLクエリを準備
            statement = connection.prepareStatement("select * from subject where cd=? and school_cd=?");
            statement.setString(1, cd); // 科目コードを設定
            statement.setString(2, school.getCd()); // 学校コードを設定
            rSet = statement.executeQuery(); // クエリを実行
            SchoolDao schoolDao = new SchoolDao(); // SchoolDaoインスタンスを作成
            if (rSet.next()) { // 結果セットにデータがある場合
                // 科目オブジェクトにデータを設定
                subject.setCd(rSet.getString("cd")); // 科目コードを設定
                subject.setName(rSet.getString("name")); // 科目名を設定
                subject.setSchool(schoolDao.get(rSet.getString("school_cd"))); // 学校情報も取得
            } else {
                subject = null; // 該当する科目がない場合はnullを返す
            }
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (rSet != null) try { rSet.close(); } catch (SQLException e) { throw e; }
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return subject; // 科目オブジェクトまたはnullを返す
    }

    /*
     * ResultSetから科目リストを作成します。
     *
     * @param rSet 結果セット (ResultSet)
     * @param school 学校情報 (School)
     * @return 科目オブジェクトのリスト
     * @throws Exception 例外が発生した場合はスロー
     */
    private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {
        List<Subject> list = new ArrayList<>(); // 科目リストを初期化
        try {
            while (rSet.next()) { // 結果セットからデータをループ処理
                Subject subject = new Subject(); // 新しい科目オブジェクトを作成
                subject.setCd(rSet.getString("cd")); // 科目コードを設定
                subject.setName(rSet.getString("name")); // 科目名を設定
                subject.setSchool(school); // 学校情報を設定
                list.add(subject); // 科目オブジェクトをリストに追加
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace(); // エラーをログに出力
        }
        return list; // 科目リストを返す
    }

    /*
     * 学校コードに基づいて科目リストを取得します。
     *
     * @param school 学校情報 (School)
     * @return 科目情報を含むリスト
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>(); // 科目リストを初期化
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String order = " order by cd asc"; // 科目コードで昇順に並べ替え
        try {
            // SQLクエリを準備
            statement = connection.prepareStatement(baseSql + order);
            statement.setString(1, school.getCd()); // 学校コードを設定
            rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            list = postFilter(rSet, school); // 結果セットからリストを作成
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (rSet != null) try { rSet.close(); } catch (SQLException e) { throw e; }
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return list; // 科目のリストを返す
    }

    /*
     * 学校コードと科目名に基づいて科目リストを取得します。
     *
     * @param school 学校情報 (School)
     * @param name 科目名 (String)
     * @return 科目情報を含むリスト
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public List<Subject> filter(School school, String name) throws Exception {
        List<Subject> list = new ArrayList<>(); // 科目リストを初期化
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String order = " order by cd asc"; // 科目コードで昇順に並べ替え
        String option = "and name like ?"; // 名前の条件
        try {
            // SQLクエリを準備
            statement = connection.prepareStatement(baseSql + " " + option + " " + order);
            statement.setString(1, school.getCd()); // 学校コードを設定
            statement.setString(2, "%" + name + "%"); // 部分一致検索用に科目名を設定
            rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            list = postFilter(rSet, school); // 結果セットからリストを作成
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (rSet != null) try { rSet.close(); } catch (SQLException e) { throw e; }
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return list; // 科目のリストを返す
    }

    /*
     * 科目オブジェクトを保存または更新します。
     *
     * @param subject 科目情報 (Subject)
     * @return 保存または更新が成功した場合はtrue、それ以外はfalseを返します。
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        int count = 0; // 更新された行数をカウント
        try {
            // 既存の科目を取得
            Subject old = get(subject.getCd(), subject.getSchool());
            if (old == null) { // 科目が存在しない場合
                // 新しい科目を挿入
                statement = connection.prepareStatement("insert into subject(school_cd, cd, name) values(?,?,?)");
                statement.setString(1, subject.getSchool().getCd()); // 学校コードを設定
                statement.setString(2, subject.getCd()); // 科目コードを設定
                statement.setString(3, subject.getName()); // 科目名を設定
            } else { // 科目が存在する場合
                // 既存の科目を更新
                statement = connection.prepareStatement("update subject set name=? where cd=?");
                statement.setString(1, subject.getName()); // 新しい科目名を設定
                statement.setString(2, subject.getCd()); // 科目コードを設定
            }
            count = statement.executeUpdate(); // SQL文を実行
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return count > 0; // 更新された行数が1以上の場合はtrueを返す
    }

    /*
     * 科目コードに基づいて科目を削除します。
     *
     * @param cd 科目コード (String)
     * @return 削除された科目を含むSubjectオブジェクト
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public Subject delete(String cd) throws Exception {
        Subject subject = new Subject(); // 削除する科目のオブジェクトを初期化
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        try {
            // 科目を削除するためのSQLクエリを準備
            statement = connection.prepareStatement("delete from subject where cd=?");
            statement.setString(1, cd); // 科目コードを設定
            statement.executeUpdate(); // SQL文を実行
        } catch (Exception e) {
            throw e; // 例外が発生した場合は再スロー
        } finally {
            // リソースをクリーンアップ
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return subject; // 削除した科目オブジェクトを返す
    }
}
