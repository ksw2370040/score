package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDao extends Dao {

    /*
     * 学校コードに基づいて学校情報を取得するメソッド。
     * 指定された学校コードに該当する学校の情報をデータベースから取得し、Schoolオブジェクトとして返します。
     *
     * @param cd 学校コード (String)
     * @return 学校情報を含むSchoolオブジェクト。見つからなかった場合はnullを返します。
     * @throws Exception データベース接続やクエリ実行中に発生した例外をスローします。
     */
    public School get(String cd) throws Exception {
        School school = new School(); // 学校情報を格納するSchoolオブジェクトを作成
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        try {
            // 学校コードに基づいて学校情報を取得するSQLクエリを準備
            statement = connection.prepareStatement("select * from School where cd=?");
            statement.setString(1, cd); // プレースホルダーに学校コードを設定
            ResultSet rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            if (rSet.next()) { // 結果セットにデータがある場合
                // 結果セットから学校情報を抽出し、Schoolオブジェクトに設定
                school.setCd(rSet.getString("cd")); // 学校コードをセット
                school.setName(rSet.getString("name")); // 学校名をセット
            } else {
                // 学校が見つからない場合はnullを返す
                school = null;
            }
        } catch (Exception e) {
            // クエリ実行中に発生した例外をスロー
            throw e;
        } finally {
            // リソースのクリーンアップを行う
            if (statement != null) {
                try {
                    statement.close(); // PreparedStatementを閉じる
                } catch (SQLException sqle) {
                    // PreparedStatementを閉じる際に発生した例外をスロー
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close(); // Connectionを閉じる
                } catch (SQLException sqle) {
                    // Connectionを閉じる際に発生した例外をスロー
                    throw sqle;
                }
            }
        }

        return school; // 学校オブジェクトまたはnullを返す
    }
}
