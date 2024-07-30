package subject;

// 必要なクラスをインポート
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import tool.Util;

public class SubjectCreateAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ユーティリティクラスを使用して教師情報を取得
        Util util = new Util();
        Teacher teacher = util.getUser(req);

        // リクエストパラメータから科目コードを取得
        String cd = req.getParameter("cd");

        // SubjectDaoのインスタンスを作成
        SubjectDao subjectDao = new SubjectDao();

        // 教師の学校と科目コードに基づいて科目リストを取得
        List<Subject> list = subjectDao.filter(teacher.getSchool(), cd);

        // 取得した科目リストをリクエスト属性に設定
        req.setAttribute("subjects", list);

        // subject_add.jspにフォワード
        req.getRequestDispatcher("../subject/subject_add.jsp").forward(req, res);
    }
}
