package student;

// 必要なクラスをインポート
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // リクエストパラメータから学生番号を取得
        String no = req.getParameter("no");

        // StudentDaoのインスタンスを作成し、指定された学生番号に基づいて学生情報を取得
        StudentDao dao = new StudentDao();
        Student student = dao.get(no);

        // 学生オブジェクトをリクエスト属性に設定
        req.setAttribute("student", student);

        // セッションから教師情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータからクラス番号を取得
        String classNum = req.getParameter("class_num");

        // ClassNumDaoのインスタンスを作成し、教師の学校に基づいてクラス番号のリストを取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> list = cNumDao.filter(teacher.getSchool());

        // クラス番号とクラス番号セットをリクエスト属性に設定
        req.setAttribute("class_num", classNum);
        req.setAttribute("class_num_set", list);

        // student_update.jspにフォワード
        req.getRequestDispatcher("../student/student_update.jsp").forward(req, res);
    }
}
