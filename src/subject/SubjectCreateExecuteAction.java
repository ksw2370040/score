package subject;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import tool.Util;

public class SubjectCreateExecuteAction extends Action {
    private SubjectDao subjectDao = new SubjectDao();
    private Util util = new Util();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	boolean exists = true;
    	List<Subject> subjects =new ArrayList<>();


        // ユーザー情報を取得
        Teacher teacher = util.getUser(req);
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
        School school = teacher.getSchool();

		subjects = subjectDao.filter(teacher.getSchool());
        // 既存の科目コードの確認
        exists = subjects.stream().anyMatch(subject -> subject.getCd().equals(cd));

        if (exists) {
            // エラーメッセージをセッションに保存
            HttpSession session = req.getSession();
            session.setAttribute("error", "この科目コードは既に存在します。");
            // エラーメッセージを表示するためにリダイレクト
            req.getRequestDispatcher("../subject/SubjectCreate.action").forward(req, res);

        } else {
            // 新しい科目の保存処理
            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(school);

            subjectDao.save(subject);

            // 成功メッセージを表示するためにリダイレクト
            req.getRequestDispatcher("../subject/subject_add_complete.jsp").forward(req, res);
        }
    }
}
