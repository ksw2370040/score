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

import bean.Student; // 学生データを格納するためのクラス
import bean.Teacher; // 教師データを格納するためのクラス
import dao.ClassNumDao; // クラス番号に関するデータアクセスオブジェクト
import dao.StudentDao; // 学生に関するデータアクセスオブジェクト
import tool.Action; // 基本アクションクラス

public class StudentListAction extends Action {

    /*
     * HTTPリクエストに基づくアクションの実行
     *
     * @param req HTTPリクエスト (HttpServletRequest)
     * @param res HTTPレスポンス (HttpServletResponse)
     * @throws Exception リクエスト処理中に発生した例外をスローします。
     */
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // フィルタ条件として入学年度、クラス番号、在籍フラグをリクエストパラメータから取得
        String entYearStr = req.getParameter("f1"); // 入学年度
        String classNum = req.getParameter("f2"); // クラス番号
        String isAttendStr = req.getParameter("f3"); // 在籍フラグ

        // セッションから教師情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user"); // セッションから教師オブジェクトを取得

        int entYear = 0; // 入学年度を格納する変数
        boolean isAttend = false; // 在籍フラグを格納する変数
        List<Student> students = null; // 学生リストを格納する変数
        LocalDate todaysDate = LocalDate.now(); // 現在の日付を取得
        int year = todaysDate.getYear(); // 現在の年を取得
        StudentDao sDao = new StudentDao(); // 学生データを操作するためのDAOインスタンスを生成
        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号を操作するためのDAOインスタンスを生成
        Map<String, String> errors = new HashMap<>(); // エラーメッセージを格納するマップ

        // 教師の学校に基づいてクラス番号のリストを取得
        List<String> list = cNumDao.filter(teacher.getSchool()); // 教師の学校に基づいてクラス番号を取得

        // 入学年度のパラメータが存在する場合、整数に変換
        if (entYearStr != null) {
            entYear = Integer.parseInt(entYearStr); // 入学年度を整数に変換
        }

        // 在籍フラグのパラメータが存在する場合、trueに設定
        if (isAttendStr != null) {
            isAttend = true; // 在籍中の場合はtrueに設定
            req.setAttribute("f3", isAttendStr); // フィルタ条件をリクエスト属性に設定
        }

        // フィルタ条件に基づいて学生リストを取得
        if (entYear != 0 && !classNum.equals("0")) {
            // 入学年度とクラス番号が両方とも指定されている場合
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            // 入学年度が指定されているがクラス番号は指定されていない場合
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            // 入学年度もクラス番号も指定されていない場合
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            // 入学年度とクラス番号の条件が不完全な場合、エラーメッセージを設定
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            req.setAttribute("errors", errors); // エラーメッセージをリクエスト属性に設定

            // 在籍フラグに基づいて学生リストを取得
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        // 入学年度のセットを作成（現在の年から10年前まで）
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i); // 10年間の入学年度をリストに追加
        }

        // リクエスト属性にフィルタ条件、学生リスト、クラス番号セット、入学年度セットを設定
        req.setAttribute("f1", entYear); // 入学年度をリクエスト属性に設定
        req.setAttribute("f2", classNum); // クラス番号をリクエスト属性に設定
        req.setAttribute("students", students); // 学生リストをリクエスト属性に設定
        req.setAttribute("class_num_set", list); // クラス番号リストをリクエスト属性に設定
        req.setAttribute("ent_year_set", entYearSet); // 入学年度リストをリクエスト属性に設定

        // student_list.jspにフォワード
        req.getRequestDispatcher("../student/student_list.jsp").forward(req, res); // JSPページにリダイレクト
    }
}
