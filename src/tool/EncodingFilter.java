package tool;

import java.io.IOException; // 入出力関連の例外を扱うためのクラス

import javax.servlet.Filter; // フィルタを作成するためのインタフェース
import javax.servlet.FilterChain; // フィルタチェーンを管理するためのクラス
import javax.servlet.FilterConfig; // フィルタの初期化情報を取得するためのクラス
import javax.servlet.ServletException; // サーブレット関連の例外を扱うためのクラス
import javax.servlet.ServletRequest; // サーブレットリクエストを表すクラス
import javax.servlet.ServletResponse; // サーブレットレスポンスを表すクラス
import javax.servlet.annotation.WebFilter; // フィルタのアノテーションを使用するためのクラス

// このフィルタはすべてのリクエストに対して適用され、
// リクエストとレスポンスの文字エンコーディングを設定します。
// また、フィルタの前後でメッセージをコンソールに出力します。
@WebFilter(urlPatterns = {"/*"}) // すべてのURLパターンに対してフィルタを適用
public class EncodingFilter implements Filter {

    // フィルタ処理のメインメソッド
    @Override
    public void doFilter(
            ServletRequest req, // サーブレットリクエストオブジェクト
            ServletResponse res, // サーブレットレスポンスオブジェクト
            FilterChain chain // 次のフィルタまたはサーブレットを呼び出すためのチェーン
    ) throws IOException, ServletException {
        // リクエストの文字エンコーディングをUTF-8に設定
        req.setCharacterEncoding("UTF-8");

        // レスポンスのコンテンツタイプを設定
        res.setContentType("text/html;charset=UTF-8");

        // フィルタ処理の前にメッセージを出力
        System.out.println("フィルタの前処理");

        // 次のフィルタまたはサーブレットを呼び出す
        chain.doFilter(req, res);

        // フィルタ処理の後にメッセージを出力
        System.out.println("フィルタの後処理");
    }

    // フィルタの初期化処理（必要に応じて実装）
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初期化処理が必要な場合はここに実装
    }

    // フィルタの破棄処理（必要に応じて実装）
    @Override
    public void destroy() {
        // リソースの解放などが必要な場合はここに実装
    }
}
