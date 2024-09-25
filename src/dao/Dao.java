package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Dao {
    // DataSourceの静的インスタンス
    static DataSource ds;

    /*
     データベース接続を取得します。
     */
    public Connection getConnection() throws Exception {
        // DataSourceが未初期化の場合
        if (ds == null) {
            // JNDI初期コンテキストの取得
            InitialContext ic = new InitialContext();
            // JNDIからDataSourceをlookupして取得
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/JSE");
        }
        // DataSourceから接続を取得して返す
        return ds.getConnection();
    }
}
