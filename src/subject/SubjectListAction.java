package subject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import tool.Util;

public class SubjectListAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        List<Subject> list = null; // 科目のリストを初期化

        // ユーザー情報を取得
        Util util = new Util();
        Teacher teacher = util.getUser(req);

        // SubjectDaoのインスタンスを作成
        SubjectDao subjectDao = new SubjectDao();

        // リクエストパラメータから科目名を取得
        String Name = req.getParameter("f1");

        // 科目名が指定されている場合は、その名前でフィルタリング
        if (Name != null) {
            list = subjectDao.filter(teacher.getSchool(), Name);
        } else {
            // 科目名が指定されていない場合は、全ての科目を取得
            list = subjectDao.filter(teacher.getSchool());
        }

        // 取得した科目のリストをリクエスト属性に設定
        req.setAttribute("subjects", list);

        // 科目リストの表示ページにフォワード
        req.getRequestDispatcher("../subject/subject_list.jsp").forward(req, res);
    }
}
