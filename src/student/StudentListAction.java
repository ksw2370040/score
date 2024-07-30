package student;

// 必要なクラスをインポート
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // フィルタ条件として入学年度、クラス番号、在籍フラグをリクエストパラメータから取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String isAttendStr = req.getParameter("f3");

        // セッションから教師情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        int entYear = 0;
        boolean isAttend = false;
        List<Student> students = null;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear(); // 現在の年を取得
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        // 教師の学校に基づいてクラス番号のリストを取得
        List<String> list = cNumDao.filter(teacher.getSchool());

        // 入学年度のパラメータが存在する場合、整数に変換
        if (entYearStr != null) {
            entYear = Integer.parseInt(entYearStr);
        }

        // 在籍フラグのパラメータが存在する場合、trueに設定
        if (isAttendStr != null) {
            isAttend = true;
            req.setAttribute("f3", isAttendStr);
        }

        // フィルタ条件に基づいて学生リストを取得
        if (entYear != 0 && !classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            // 入学年度とクラス番号の条件が不完全な場合、エラーメッセージを設定
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            req.setAttribute("errors", errors);

            // 在籍フラグに基づいて学生リストを取得
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        // 入学年度のセットを作成（現在の年から10年前まで）
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }

        // リクエスト属性にフィルタ条件、学生リスト、クラス番号セット、入学年度セットを設定
        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("students", students);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        // student_list.jspにフォワード
        req.getRequestDispatcher("../student/student_list.jsp").forward(req, res);
    }
}
