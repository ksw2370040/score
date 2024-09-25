package user;

import javax.servlet.http.HttpServletRequest; // HTTPリクエストを表すクラス
import javax.servlet.http.HttpServletResponse; // HTTPレスポンスを表すクラス
import javax.servlet.http.HttpSession; // セッションを扱うためのクラス

import bean.Teacher; // 教師を表すクラス
import dao.TeacherDAO; // 教師に関するデータ操作を行うDAOクラス
import tool.Action; // アクションの抽象クラス

public class LoginExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションを取得
        HttpSession session = req.getSession(); // 現在のセッションを取得または新規作成

        // リクエストパラメータからIDとパスワードを取得
        String id = req.getParameter("id"); // IDを取得
        String password = req.getParameter("password"); // パスワードを取得

        try {
            // DAOを使用してIDとパスワードでユーザーを取得
            TeacherDAO dao = new TeacherDAO(); // TeacherDAOのインスタンスを作成
            Teacher user = dao.get(id, password); // IDとパスワードを基にユーザー情報を取得

            if (user == null) {
                // ユーザーが見つからない場合はエラーメッセージを設定し、ログインページに戻す
                req.setAttribute("loginError", "ログインに失敗しました。IDまたはパスワードが正しくありません。"); // エラーメッセージをリクエスト属性に設定
                req.getRequestDispatcher("/user/login.jsp").forward(req, res); // ログインページにフォワード
            } else {
                // ユーザーが見つかった場合は、セッションにユーザーを保存し、メニュー画面に遷移
                session.setAttribute("user", user); // セッションにユーザー情報を保存
                req.getRequestDispatcher("../menu/menu.jsp").forward(req, res); // メニュー画面にフォワード
            }
        } catch (Exception e) {
            // エラー発生時の処理
            e.printStackTrace(); // スタックトレースを出力（デバッグ用）
            req.setAttribute("errorMessage", "システムエラーが発生しました。もう一度お試しください。"); // エラーメッセージを設定
            req.getRequestDispatcher("/error.jsp").forward(req, res); // エラーページにフォワード
        }
    }
}
