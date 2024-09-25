package tool;

import javax.servlet.http.HttpServletRequest; // HTTPリクエストオブジェクトを扱うためのクラス
import javax.servlet.http.HttpServletResponse; // HTTPレスポンスオブジェクトを扱うためのクラス

// Actionクラスは、サーブレットアクションの抽象クラスです。
// このクラスを継承することで、各アクションの具体的な処理を実装することができます。
public abstract class Action {

    // executeメソッドは、サーブレットアクションを実行するための抽象メソッドです。
    // サブクラスで具体的な処理を実装する必要があります。
    //
    // @param request - HTTPリクエストオブジェクト
    // @param response - HTTPレスポンスオブジェクト
    // @throws Exception - 処理中に発生した例外をスローします
    public abstract void execute(
        HttpServletRequest request, // クライアントからのHTTPリクエストを表すオブジェクト
        HttpServletResponse response // サーバーからクライアントへのHTTPレスポンスを表すオブジェクト
    ) throws Exception; // メソッド実行中に発生した例外を通知するための宣言
}
