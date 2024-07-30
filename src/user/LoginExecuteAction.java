package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションを取得
        HttpSession session = req.getSession();

        // リクエストパラメータからIDとパスワードを取得
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        try {
            // DAOを使用してIDとパスワードでユーザーを取得
            TeacherDAO dao = new TeacherDAO();
            Teacher user = dao.get(id, password);

            if (user == null) {
                // ユーザーが見つからない場合はエラーメッセージを設定し、ログインページに戻す
                req.setAttribute("loginError", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
                req.getRequestDispatcher("/user/login.jsp").forward(req, res);
            } else {
                // ユーザーが見つかった場合は、セッションにユーザーを保存し、メニュー画面に遷移
                session.setAttribute("user", user);
                req.getRequestDispatcher("/menu/menu.jsp").forward(req, res);
            }
        } catch (Exception e) {
            // エラー発生時の処理
            e.printStackTrace();
            req.setAttribute("errorMessage", "システムエラーが発生しました。もう一度お試しください。");
            req.getRequestDispatcher("/error.jsp").forward(req, res);
        }
    }
}
