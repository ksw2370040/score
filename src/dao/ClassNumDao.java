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
    // 基本SQLクエリ: 学校コードでクラス番号を選択する
    private String baseSql = "select class_num from class_num where school_cd=?";

    /**
     * ResultSetからデータを抽出し、クラス番号のリストを作成します。
     * @param rSet 結果セット
     * @param school 学校オブジェクト
     * @return クラス番号のリスト
     */
    private List<String> postFilter(ResultSet rSet, School school) throws Exception {
        List<String> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                ClassNum classnum = new ClassNum();
                classnum.setClassNum(rSet.getString("class_num"));
                classnum.setSchool(school);
                list.add(classnum.getClassNum());
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 学校とクラス番号に基づいてフィルタリングし、クラス番号のリストを返します。
     * @param school 学校オブジェクト
     * @param classNum クラス番号オブジェクト
     * @return クラス番号のリスト
     */
    public List<String> filter(School school, ClassNum classNum) throws Exception {
        List<String> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        // クラス番号でフィルタリングする条件を追加
        String condition = "class_num=?";
        String order = " order by class_num asc";
        statement = connection.prepareStatement(baseSql + " and " + condition + order);
        statement.setString(1, school.getCd());
        statement.setString(2, classNum.getClassNum());
        rSet = statement.executeQuery();
        list = postFilter(rSet, school);
        return list;
    }

    /**
     * 学校に基づいてフィルタリングし、クラス番号のリストを返します。
     * @param school 学校オブジェクト
     * @return クラス番号のリスト
     */
    public List<String> filter(School school) throws Exception {
        List<String> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String order = " order by class_num asc";
        statement = connection.prepareStatement(baseSql + order);
        statement.setString(1, school.getCd());
        rSet = statement.executeQuery();
        list = postFilter(rSet, school);
        return list;
    }
}
