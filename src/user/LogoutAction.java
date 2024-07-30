package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 現在のセッションを取得
        HttpSession session = req.getSession();

        // セッションに「user」属性が存在する場合
        if (session.getAttribute("user") != null) {
            // ユーザー情報をセッションから削除
            session.removeAttribute("user");
            // ログアウト完了ページにフォワード
            req.getRequestDispatcher("../user/logout.jsp").forward(req, res);
        } else {
            // ユーザーがログインしていない場合はエラーページにフォワード
            req.getRequestDispatcher("../common/error.jsp").forward(req, res);
        }
    }
}
