package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import tool.Util;

public class SubjectUpdateExecuteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ユーザー情報を取得
        Util util = new Util();
        Teacher teacher = util.getUser(req);

        // リクエストパラメータから科目コードと科目名を取得
        String cd = req.getParameter("cd");
        String Name = req.getParameter("name");
        School school = teacher.getSchool();

        // 更新する科目の情報を設定
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(Name);
        subject.setSchool(school);

        // SubjectDaoのインスタンスを作成し、科目情報を保存
        SubjectDao dao = new SubjectDao();
        dao.save(subject);

        // 更新完了ページにフォワード
        req.getRequestDispatcher("../subject/subject_update_complete.jsp").forward(req, res);
    }
}
