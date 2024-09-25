package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {
    // 基本SQLクエリ: 指定された学校コードに基づいてクラス番号を取得するクエリ
    private String baseSql = "select class_num from class_num where school_cd=?";

    /*
     * ResultSetからクラス番号を抽出し、リストを作成するメソッド。
     * データベースから取得したクラス番号をListに格納して返します。
     *
     * @param rSet   SQL実行結果のResultSetオブジェクト
     * @param school クラス番号と関連付ける学校の情報
     * @return クラス番号のリスト
     */
    private List<String> postFilter(ResultSet rSet, School school) throws Exception {
        List<String> list = new ArrayList<>(); // クラス番号を格納するリスト
        try {
            // ResultSetから1行ずつデータを取り出す
            while (rSet.next()) {
                ClassNum classnum = new ClassNum(); // ClassNumオブジェクトを作成
                classnum.setClassNum(rSet.getString("class_num")); // クラス番号をセット
                classnum.setSchool(school); // 学校情報をセット
                list.add(classnum.getClassNum()); // クラス番号をリストに追加
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace(); // エラー発生時のスタックトレースを表示
        }
        return list; // クラス番号のリストを返す
    }

    /*
     * 学校コードとクラス番号でフィルタリングし、該当するクラス番号のリストを返すメソッド。
     *
     * @param school   フィルタリング対象となる学校の情報
     * @param classNum フィルタリング対象となるクラス番号
     * @return フィルタリングされたクラス番号のリスト
     */
    public List<String> filter(School school, ClassNum classNum) throws Exception {
        List<String> list = new ArrayList<>(); // 結果を格納するリスト
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        // クラス番号でさらにフィルタリングする条件を追加
        String condition = "class_num=?";
        String order = " order by class_num asc"; // 結果を昇順に並べる
        // SQL文を構築し、学校コードとクラス番号でフィルタリング
        statement = connection.prepareStatement(baseSql + " and " + condition + order);
        statement.setString(1, school.getCd()); // 学校コードをセット
        statement.setString(2, classNum.getClassNum()); // クラス番号をセット
        rSet = statement.executeQuery(); // SQLを実行して結果を取得
        list = postFilter(rSet, school); // 取得結果をリストに変換
        return list; // フィルタリング結果のリストを返す
    }

    /*
     * 学校コードだけでフィルタリングし、該当するクラス番号のリストを返すメソッド。
     *
     * @param school フィルタリング対象となる学校の情報
     * @return フィルタリングされたクラス番号のリスト
     */
    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>(); // 結果を格納するリスト
        Connection connection = getConnection(); // データベース接続を取得
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String order = " order by class_num asc"; // クラス番号を昇順で取得する
        // SQL文を構築し、学校コードでフィルタリング
        statement = connection.prepareStatement(baseSql + order);
        statement.setString(1, school.getCd()); // 学校コードをセット
        rSet = statement.executeQuery(); // SQLを実行して結果を取得
        list = postFilter(rSet, school); // 取得結果をリストに変換
        return list; // フィルタリング結果のリストを返す
    }
}
