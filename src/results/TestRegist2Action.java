package results;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;
import tool.Util;

public class TestRegist2Action extends Action {
    /*
     * テスト登録処理のためのクラス。
     *
     * @param req HTTPリクエスト
     * @param res HTTPレスポンス
     * @throws Exception 処理中に発生した例外をスローします。
     */
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Util util = new Util(); // ユーティリティクラスのインスタンスを生成
        Subject subject = new Subject(); // 科目情報を保持するオブジェクト
        StudentDao stuDao = new StudentDao(); // 学生情報を操作するDAO
        SubjectDao subDao = new SubjectDao(); // 科目情報を操作するDAO
        List<Student> students = new ArrayList<>(); // 学生リスト
        TestDao tDao = new TestDao(); // テスト情報を操作するDAO
        List<Test> tests = new ArrayList<>(); // テストリスト
        Teacher teacher = util.getUser(req); // 現在のユーザー（教師）を取得

        // リクエストからパラメータを取得
        String entYearStr = req.getParameter("f1"); // 入学年度
        String classNum = req.getParameter("f2"); // クラス番号
        String subject_cd = req.getParameter("f3"); // 科目コード
        String noStr = req.getParameter("f4"); // テスト番号

        int entYear = 0; // 入学年度を格納する変数
        int no = 0; // テスト番号を格納する変数

        // 入学年度とテスト番号をパース
        if (entYearStr != null) {
            entYear = Integer.parseInt(entYearStr); // 入学年度を整数に変換
        }
        if (noStr != null) {
            no = Integer.parseInt(noStr); // テスト番号を整数に変換
        }

        // 条件に基づいてデータを取得
        if (entYear != 0 && !classNum.equals("0") && !subject_cd.equals("0") && no != 0) {
            subject = subDao.get(subject_cd, teacher.getSchool()); // 科目情報を取得
            students = stuDao.filter(teacher.getSchool(), entYear, classNum, true); // 学生情報を取得
            tests = tDao.filter(entYear, classNum, subject, no, teacher.getSchool()); // テスト情報を取得
        }

        // リクエスト属性の設定
        req.setAttribute("f1", entYear); // 入学年度をリクエストに設定
        req.setAttribute("f2", classNum); // クラス番号をリクエストに設定
        req.setAttribute("f3", subject_cd); // 科目コードをリクエストに設定
        req.setAttribute("f4", noStr); // テスト番号をリクエストに設定
        req.setAttribute("tests", tests); // テスト情報をリクエストに設定
        req.setAttribute("students", students); // 学生情報をリクエストに設定
        req.setAttribute("subject", subject); // 科目情報をリクエストに設定
        req.setAttribute("no", no); // テスト番号をリクエストに設定
        util.setClassNum(req); // クラス番号のリストをリクエストに設定
        util.setEntYearSet(req); // 入学年度のリストをリクエストに設定
        util.setSubject(req); // 科目のリストをリクエストに設定
        util.setNumSet(req); // テスト番号のリストをリクエストに設定

        // JSP への転送
        req.getRequestDispatcher("../results/test_regist2.jsp").forward(req, res);
    }
}
