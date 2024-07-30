package student;

// 必要なクラスをインポート
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションから教師情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータから学生情報を取得
        boolean isAttend = false;
        Integer entYear = Integer.parseInt(req.getParameter("ent_year"));
        String No = req.getParameter("no");
        String Name = req.getParameter("name");
        String classNum = req.getParameter("class_num");
        String isAttendStr = req.getParameter("isAttend");
        School school = teacher.getSchool();

        // 在籍フラグのパラメータが存在する場合、trueに設定
        if (isAttendStr != null) {
            isAttend = true;
            req.setAttribute("isAttend", isAttendStr);
        }

        // 学生オブジェクトを作成し、リクエストパラメータで設定
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(No);
        student.setName(Name);
        student.setClassNum(classNum);
        student.setIsAttend(isAttend);
        student.setSchool(school);

        // StudentDaoのインスタンスを作成し、学生情報をデータベースに保存
        StudentDao dao = new StudentDao();
        dao.save(student);

        // 学生更新完了ページにフォワード
        req.getRequestDispatcher("../student/student_update_complete.jsp").forward(req, res);
    }
}
