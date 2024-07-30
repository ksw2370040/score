package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // リクエストパラメータから科目コードを取得
        String cd = req.getParameter("cd");

        // SubjectDaoのインスタンスを作成し、指定された科目コードを削除
        SubjectDao dao = new SubjectDao();
        dao.delete(cd);

        // 削除後、科目リストページにフォワード
        req.getRequestDispatcher("SubjectList.action").forward(req, res);
    }
}
