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
    private String baseSql = "select * from subject where school_cd=?";

    /**
     * 科目コードと学校オブジェクトに基づいて科目情報を取得します。
     * @param cd 科目コード
     * @param school 学校オブジェクト
     * @return 該当する科目オブジェクト、存在しない場合はnull
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public Subject get(String cd, School school) throws Exception {
        Subject subject = new Subject();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        try {
            // 科目情報を取得するSQLクエリを準備
            statement = connection.prepareStatement("select * from subject where cd=? and school_cd=?");
            statement.setString(1, cd);
            statement.setString(2, school.getCd());
            rSet = statement.executeQuery();
            SchoolDao schoolDao = new SchoolDao();
            if (rSet.next()) {
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
            } else {
                subject = null; // 該当する科目がない場合はnullを返す
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rSet != null) try { rSet.close(); } catch (SQLException e) { throw e; }
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return subject;
    }

    /**
     * ResultSetから科目リストを作成します。
     * @param rSet 結果セット
     * @param school 学校オブジェクト
     * @return 科目オブジェクトのリスト
     * @throws Exception
     */
    private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 学校オブジェクトに基づいて科目リストを取得します。
     * @param school 学校オブジェクト
     * @return 科目オブジェクトのリスト
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String order = " order by cd asc";
        try {
            statement = connection.prepareStatement(baseSql + order);
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            if (rSet != null) try { rSet.close(); } catch (SQLException e) { throw e; }
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return list;
    }

    /**
     * 学校オブジェクトと科目名に基づいて科目リストを取得します。
     * @param school 学校オブジェクト
     * @param name 科目名
     * @return 科目オブジェクトのリスト
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public List<Subject> filter(School school, String name) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String order = " order by cd asc";
        String option = "and name like ?";
        try {
            statement = connection.prepareStatement(baseSql + " " + option + " " + order);
            statement.setString(1, school.getCd());
            statement.setString(2, "%" + name + "%");
            rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            if (rSet != null) try { rSet.close(); } catch (SQLException e) { throw e; }
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return list;
    }

    /**
     * 科目オブジェクトを保存または更新します。
     * @param subject 保存または更新する科目オブジェクト
     * @return 操作が成功したかどうか
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;
        try {
            Subject old = get(subject.getCd(), subject.getSchool());
            if (old == null) {
                statement = connection.prepareStatement(
                        "insert into subject(school_cd, cd, name) values(?,?,?)");
                statement.setString(1, subject.getSchool().getCd());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getName());
            } else {
                statement = connection.prepareStatement("update subject set name=? where cd=?");
                statement.setString(1, subject.getName());
                statement.setString(2, subject.getCd());
            }
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return count > 0;
    }

    /**
     * 科目コードに基づいて科目を削除します。
     * @param cd 科目コード
     * @return 削除した科目オブジェクト
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public Subject delete(String cd) throws Exception {
        Subject subject = new Subject();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("delete from subject where cd=?");
            statement.setString(1, cd);
            statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
            if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
        }
        return subject;
    }
}
