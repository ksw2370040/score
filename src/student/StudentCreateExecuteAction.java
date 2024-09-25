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

    /*
     * HTTPリクエストに基づくアクションの実行
     *
     * @param req HTTPリクエスト (HttpServletRequest)
     * @param res HTTPレスポンス (HttpServletResponse)
     * @throws Exception リクエスト処理中に発生した例外をスローします。
     */
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションから教師情報を取得
        HttpSession session = req.getSession(); // セッションを取得
        boolean exists = true; // 学生番号の存在フラグ
        List<Student> students = new ArrayList<>(); // 在籍中の学生リスト
        StudentDao studentDao = new StudentDao(); // 学生DAOのインスタンスを作成

        // セッションから教師オブジェクトを取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータから入学年度、学生番号、名前、クラス番号を取得
        Integer entYear = Integer.parseInt(req.getParameter("ent_year")); // 入学年度を取得
        String No = req.getParameter("no"); // 学生番号を取得
        String Name = req.getParameter("name"); // 名前を取得
        String classNum = req.getParameter("class_num"); // クラス番号を取得
        boolean isAttend = true; // 在籍状況をtrueに設定（在籍中）
        School school = teacher.getSchool(); // 教師の学校情報を取得

        // 学校に基づいて現在在籍中の学生リストを取得
        students = studentDao.filter(school, isAttend); // 在籍中の学生をフィルタリング

        // 入力された学生番号が既に存在するか確認
        exists = students.stream().anyMatch(student -> student.getNo().equals(No)); // 学生番号の存在をチェック

        if (exists) {
            // 学生番号が既に存在する場合、エラーメッセージをセッションに設定し、リダイレクト
            session.setAttribute("error", "この学生番号は既に存在します。"); // エラーメッセージを設定
            req.getRequestDispatcher("../student/StudentCreate.action").forward(req, res); // 再フォワード
        } else {
            // 学生オブジェクトを作成し、リクエストパラメータで設定
            Student student = new Student(); // 学生オブジェクトを生成
            student.setEntYear(entYear); // 入学年度を設定
            student.setNo(No); // 学生番号を設定
            student.setName(Name); // 名前を設定
            student.setClassNum(classNum); // クラス番号を設定
            student.setIsAttend(isAttend); // 在籍状況を設定
            student.setSchool(school); // 学校情報を設定

            // 学生オブジェクトをデータベースに保存
            StudentDao dao = new StudentDao(); // 新しいStudentDaoインスタンスを作成
            dao.save(student); // 学生情報をデータベースに保存

            // 学生追加完了ページにフォワード
            req.getRequestDispatcher("../student/student_add_complete.jsp").forward(req, res); // 完了ページにフォワード
        }
    }
}
