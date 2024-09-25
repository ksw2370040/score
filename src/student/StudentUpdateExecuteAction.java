package student;

// 必要なクラスをインポート
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School; // 学校データを格納するためのクラス
import bean.Student; // 学生データを格納するためのクラス
import bean.Teacher; // 教師データを格納するためのクラス
import dao.StudentDao; // 学生に関するデータアクセスオブジェクト
import tool.Action; // 基本アクションクラス

public class StudentUpdateExecuteAction extends Action {

    // HTTPリクエストに基づくアクションの実行
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションから教師情報を取得
        HttpSession session = req.getSession(); // 現在のセッションを取得
        Teacher teacher = (Teacher) session.getAttribute("user"); // セッションから教師オブジェクトを取得

        // リクエストパラメータから学生情報を取得
        boolean isAttend = false; // 在籍フラグの初期化
        Integer entYear = Integer.parseInt(req.getParameter("ent_year")); // 入学年度をリクエストから取得
        String No = req.getParameter("no"); // 学生番号をリクエストから取得
        String Name = req.getParameter("name"); // 学生名をリクエストから取得
        String classNum = req.getParameter("class_num"); // クラス番号をリクエストから取得
        String isAttendStr = req.getParameter("isAttend"); // 在籍フラグの文字列をリクエストから取得
        School school = teacher.getSchool(); // 教師の学校情報を取得

        // 在籍フラグのパラメータが存在する場合、trueに設定
        if (isAttendStr != null) {
            isAttend = true; // 在籍フラグをtrueに設定
            req.setAttribute("isAttend", isAttendStr); // 在籍フラグをリクエスト属性に設定
        }

        // 学生オブジェクトを作成し、リクエストパラメータで設定
        Student student = new Student(); // 学生オブジェクトを生成
        student.setEntYear(entYear); // 入学年度を設定
        student.setNo(No); // 学生番号を設定
        student.setName(Name); // 学生名を設定
        student.setClassNum(classNum); // クラス番号を設定
        student.setIsAttend(isAttend); // 在籍フラグを設定
        student.setSchool(school); // 学校情報を設定

        // StudentDaoのインスタンスを作成し、学生情報をデータベースに保存
        StudentDao dao = new StudentDao(); // 学生データを操作するためのDAOインスタンスを生成
        dao.save(student); // 学生情報をデータベースに保存

        // 学生更新完了ページにフォワード
        req.getRequestDispatcher("../student/student_update_complete.jsp").forward(req, res); // JSPページにリダイレクト
    }
}
