package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDao extends Dao {

    /**
     * 学校コードに基づいて学校情報を取得します。
     * @param cd 学校コード
     * @return 学校オブジェクト。学校が見つからなかった場合はnull
     * @throws Exception データベース接続やクエリ実行に関する例外
     */
    public School get(String cd) throws Exception {
        School school = new School(); // 新しい学校オブジェクトを作成
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        try {
            // 学校コードに基づいて学校情報を取得するSQLクエリを準備
            statement = connection.prepareStatement("select * from School where cd=?");
            statement.setString(1, cd); // プレースホルダーに学校コードを設定
            ResultSet rSet = statement.executeQuery(); // クエリを実行し、結果セットを取得
            if (rSet.next()) { // 結果セットにデータがある場合
                // 学校オブジェクトにデータを設定
                school.setCd(rSet.getString("cd"));
                school.setName(rSet.getString("name"));
            } else {
                // 学校が見つからない場合はnullを返す
                school = null;
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

        return school; // 学校オブジェクトまたはnullを返す
    }
}
