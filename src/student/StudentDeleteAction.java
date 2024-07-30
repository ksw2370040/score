package student;

// 必要なクラスをインポート
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import tool.Action;

public class StudentDeleteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // リクエストパラメータから学生番号を取得
        String no = req.getParameter("no");

        // StudentDaoのインスタンスを作成
        StudentDao dao = new StudentDao();

        // 指定された学生番号に基づいて学生を削除
        dao.delete(no);

        // 学生一覧ページにフォワード
        req.getRequestDispatcher("StudentList.action").forward(req, res);
    }
}
