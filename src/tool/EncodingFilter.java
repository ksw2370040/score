package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// このフィルタはすべてのリクエストに対して適用され、
// リクエストとレスポンスの文字エンコーディングを設定します。
// また、フィルタの前後でメッセージをコンソールに出力します。
@WebFilter(urlPatterns = {"/*"})
public class EncodingFilter implements Filter {

    // フィルタ処理のメインメソッド
    public void doFilter(
            ServletRequest req, ServletResponse res, FilterChain chain
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
    public void init(FilterConfig filterConfig) {}

    // フィルタの破棄処理（必要に応じて実装）
    public void destroy() {}
}
