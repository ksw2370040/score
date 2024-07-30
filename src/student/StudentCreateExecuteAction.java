package student;

// 必要なクラスをインポート
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションから教師情報を取得
        HttpSession session = req.getSession();
        boolean exists = true;
        List<Student> students = new ArrayList<>();
        StudentDao studentDao = new StudentDao();

        // セッションから教師オブジェクトを取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータから入学年度、学生番号、名前、クラス番号を取得
        Integer entYear = Integer.parseInt(req.getParameter("ent_year"));
        String No = req.getParameter("no");
        String Name = req.getParameter("name");
        String classNum = req.getParameter("class_num");
        boolean isAttend = true;
        School school = teacher.getSchool();

        // 学校に基づいて現在在籍中の学生リストを取得
        students = studentDao.filter(school, isAttend);

        // 入力された学生番号が既に存在するか確認
        exists = students.stream().anyMatch(student -> student.getNo().equals(No));

        if (exists) {
            // 学生番号が既に存在する場合、エラーメッセージをセッションに設定し、リダイレクト
            session.setAttribute("error", "この学生番号は既に存在します。");
            req.getRequestDispatcher("../student/StudentCreate.action").forward(req, res);
        } else {
            // 学生オブジェクトを作成し、リクエストパラメータで設定
            Student student = new Student();
            student.setEntYear(entYear);
            student.setNo(No);
            student.setName(Name);
            student.setClassNum(classNum);
            student.setIsAttend(isAttend);
            student.setSchool(school);

            // 学生オブジェクトをデータベースに保存
            StudentDao dao = new StudentDao();
            dao.save(student);

            // 学生追加完了ページにフォワード
            req.getRequestDispatcher("../student/student_add_complete.jsp").forward(req, res);
        }
    }
}
