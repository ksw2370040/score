package student;

// 必要なクラスをインポート
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {

    /*
     * HTTPリクエストに基づくアクションの実行
     *
     * @param req HTTPリクエスト (HttpServletRequest)
     * @param res HTTPレスポンス (HttpServletResponse)
     * @throws Exception リクエスト処理中に発生した例外をスローします。
     */
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションから教師情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user"); // セッションから教師オブジェクトを取得

        // リクエストパラメータから入学年度とクラス番号を取得
        String entYearStr = req.getParameter("ent_year"); // 入学年度の文字列を取得
        String classNum = req.getParameter("class_num"); // クラス番号の文字列を取得

        int entYear = 0; // 入学年度を整数で初期化
        LocalDate todaysDate = LocalDate.now(); // 現在の日付を取得
        int year = todaysDate.getYear(); // 現在の年を取得

        // ClassNumDaoのインスタンスを作成
        ClassNumDao cNumDao = new ClassNumDao();

        // 教師の学校に基づいてクラス番号のリストを取得
        List<String> list = cNumDao.filter(teacher.getSchool());

        // 入学年度が指定されている場合、文字列から整数に変換
        if (entYearStr != null) {
            entYear = Integer.parseInt(entYearStr); // 入学年度を整数に変換
        }

        // 入学年度のセットを作成（現在の年から10年前まで）
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i); // 入学年度のセットに年を追加
        }

        // リクエスト属性に入学年度、クラス番号、クラス番号セット、入学年度セットを設定
        req.setAttribute("ent_year", entYear); // 入学年度をリクエストに設定
        req.setAttribute("class_num", classNum); // クラス番号をリクエストに設定
        req.setAttribute("class_num_set", list); // クラス番号のリストをリクエストに設定
        req.setAttribute("ent_year_set", entYearSet); // 入学年度のセットをリクエストに設定

        // student_add.jspにフォワード
        req.getRequestDispatcher("../student/student_add.jsp").forward(req, res); // JSPページにリダイレクト
    }
}
