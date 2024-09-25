package tool;

import java.io.IOException; // 入出力関連の例外を扱うためのクラス

import javax.servlet.ServletException; // サーブレット関連の例外を扱うためのクラス
import javax.servlet.annotation.WebServlet; // サーブレットのアノテーションを使用するためのクラス
import javax.servlet.http.HttpServlet; // HTTPサーブレットの基本クラス
import javax.servlet.http.HttpServletRequest; // HTTPリクエストを表すクラス
import javax.servlet.http.HttpServletResponse; // HTTPレスポンスを表すクラス

// URLパターンが "*.action" のリクエストを処理するサーブレット
@WebServlet(urlPatterns = {"*.action"}) // "*.action"にマッチするリクエストを処理
public class FrontController extends HttpServlet {

    // GETリクエストを処理するメソッド
    @Override
    public void doGet(
            HttpServletRequest req, // HTTPリクエストオブジェクト
            HttpServletResponse res // HTTPレスポンスオブジェクト
    ) throws ServletException, IOException {
        try {
            // リクエストパスからアクションクラスの名前を生成
            String path = req.getServletPath().substring(1); // "/example.action" -> "example.action"
            String name = path.replace(".a", "A").replace("/", "."); // "example.action" -> "exampleA"

            // クラス名を出力（デバッグ用）
            System.out.println("★ servlet path -> " + req.getServletPath());
            System.out.println("★ class name -> " + name);

            // アクションクラスのインスタンスを生成
            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

            // アクションのexecuteメソッドを呼び出す
            action.execute(req, res);
        } catch (Exception e) {
            // 例外が発生した場合、エラーページにフォワード
            e.printStackTrace(); // エラーのスタックトレースを出力
            req.getRequestDispatcher("../common/error.jsp").forward(req, res); // エラーページにフォワード
        }
    }

    // POSTリクエストもGETリクエストとして処理
    @Override
    public void doPost(
            HttpServletRequest req, // HTTPリクエストオブジェクト
            HttpServletResponse res // HTTPレスポンスオブジェクト
    ) throws ServletException, IOException {
        doGet(req, res); // POSTリクエストをGETリクエストとして処理
    }
}
