package student;

// 必要なクラスをインポート
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student; // 学生データを格納するためのクラス
import bean.Teacher; // 教師データを格納するためのクラス
import dao.ClassNumDao; // クラス番号に関するデータアクセスオブジェクト
import dao.StudentDao; // 学生に関するデータアクセスオブジェクト
import tool.Action; // 基本アクションクラス

public class StudentUpdateAction extends Action {

    /*
     * HTTPリクエストに基づくアクションの実行
     *
     * @param req HTTPリクエスト (HttpServletRequest)
     * @param res HTTPレスポンス (HttpServletResponse)
     * @throws Exception リクエスト処理中に発生した例外をスローします。
     */
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // リクエストパラメータから学生番号を取得
        String no = req.getParameter("no"); // 学生番号をリクエストから取得

        // StudentDaoのインスタンスを作成し、指定された学生番号に基づいて学生情報を取得
        StudentDao dao = new StudentDao(); // 学生データを操作するためのDAOインスタンスを生成
        Student student = dao.get(no); // 学生番号を使用して学生情報を取得

        // 学生オブジェクトをリクエスト属性に設定
        req.setAttribute("student", student); // 取得した学生情報をリクエスト属性に設定

        // セッションから教師情報を取得
        HttpSession session = req.getSession(); // 現在のセッションを取得
        Teacher teacher = (Teacher) session.getAttribute("user"); // セッションから教師オブジェクトを取得

        // リクエストパラメータからクラス番号を取得
        String classNum = req.getParameter("class_num"); // クラス番号をリクエストから取得

        // ClassNumDaoのインスタンスを作成し、教師の学校に基づいてクラス番号のリストを取得
        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号を操作するためのDAOインスタンスを生成
        List<String> list = cNumDao.filter(teacher.getSchool()); // 教師の学校に基づいてクラス番号を取得

        // クラス番号とクラス番号セットをリクエスト属性に設定
        req.setAttribute("class_num", classNum); // 現在のクラス番号をリクエスト属性に設定
        req.setAttribute("class_num_set", list); // クラス番号リストをリクエスト属性に設定

        // student_update.jspにフォワード
        req.getRequestDispatcher("../student/student_update.jsp").forward(req, res); // JSPページにリダイレクト
    }
}
