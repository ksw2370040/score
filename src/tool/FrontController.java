package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// URLパターンが "*.action" のリクエストを処理するサーブレット
@WebServlet(urlPatterns = {"*.action"})
public class FrontController extends HttpServlet {

    // GETリクエストを処理するメソッド
    @Override
    public void doGet(
            HttpServletRequest req, HttpServletResponse res
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
            e.printStackTrace();
            req.getRequestDispatcher("../common/error.jsp").forward(req, res);
        }
    }

    // POSTリクエストもGETリクエストとして処理
    @Override
    public void doPost(
            HttpServletRequest req, HttpServletResponse res
    ) throws ServletException, IOException {
        doGet(req, res);
    }
}
