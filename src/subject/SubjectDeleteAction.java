package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import tool.Util;

public class SubjectDeleteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ユーザー情報を取得
        Util util = new Util();
        Teacher user = util.getUser(req);

        // リクエストパラメータから科目コードを取得
        String subject_cd = req.getParameter("cd");

        // SubjectDaoのインスタンスを作成し、科目情報を取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subject_cd, user.getSchool());

        // 取得した科目情報をリクエスト属性に設定
        req.setAttribute("subject", subject);

        // 確認ページにフォワード
        req.getRequestDispatcher("../subject/SubjectDeleteConfirm.jsp").forward(req, res);
    }
}
