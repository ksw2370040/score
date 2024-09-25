package results;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.Action;
import tool.Util;

public class TestListAction extends Action {
    /*
     * テストリストの表示処理を実行します。
     *
     * @param req HTTPリクエスト
     * @param res HTTPレスポンス
     * @throws Exception 処理中に発生した例外をスローします。
     */
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Util util = new Util(); // ユーティリティクラスのインスタンスを生成
        Student student = new Student(); // 学生情報を保持するオブジェクト
        StudentDao stuDao = new StudentDao(); // 学生情報を操作するDAO
        Subject subject = new Subject(); // 科目情報を保持するオブジェクト
        SubjectDao subDao = new SubjectDao(); // 科目情報を操作するDAO

        Teacher teacher = util.getUser(req); // 現在のユーザー（教師）を取得

        // リクエストからパラメータを取得
        String entYearStr = req.getParameter("f1"); // 入学年度
        String classNum = req.getParameter("f2"); // クラス番号
        String subjectCd = req.getParameter("f3"); // 科目コード
        String studentNo = req.getParameter("f4"); // 学生番号

        int entYear = 0; // 入学年度を格納する変数
        List<TestListSubject> TLsubs = new ArrayList<>(); // 科目ごとのテスト情報リスト
        List<TestListStudent> TLstus = new ArrayList<>(); // 学生ごとのテスト情報リスト

        LocalDate todaysDate = LocalDate.now(); // 今日の日付を取得
        int year = todaysDate.getYear(); // 現在の年を取得
        TestListSubjectDao TLsubDao = new TestListSubjectDao(); // 科目ごとのテスト情報を操作するDAO
        TestListStudentDao TLstuDao = new TestListStudentDao(); // 学生ごとのテスト情報を操作するDAO
        ClassNumDao cNumDao = new ClassNumDao(); // クラス情報を操作するDAO

        List<String> list = cNumDao.filter(teacher.getSchool()); // クラス番号のリストを取得
        Map<String, String> errors = new HashMap<>(); // エラーメッセージを格納するマップ

        subject.setCd(subjectCd); // 科目コードを設定
        student.setNo(studentNo); // 学生番号を設定
        student.setSchool(teacher.getSchool()); // 学校情報を設定

        // 年度のパラメータが指定されている場合の処理
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr); // 入学年度を整数に変換
        }

        // 条件に基づくデータ取得
        if (entYear != 0 && !classNum.equals("0") && !subjectCd.equals("0")) {
            try {
                TLsubs = TLsubDao.filter(entYear, classNum, subject, teacher.getSchool()); // 科目ごとのテスト情報を取得
            } catch (Exception e) {
                e.printStackTrace(); // エラーハンドリング
            }
        } else if (studentNo != null && !studentNo.equals("0")) {
            try {
                TLstus = TLstuDao.filter(student); // 学生ごとのテスト情報を取得
                student = stuDao.get(studentNo); // 学生情報を取得
            } catch (Exception e) {
                e.printStackTrace(); // エラーハンドリング
            }
        } else {
            errors.put("f1", "入学年度とクラスと科目を選択してください。"); // エラーメッセージを設定
            req.setAttribute("errors", errors); // エラーメッセージをリクエストに設定
            req.setAttribute("nosearch", true); // 検索結果なしのフラグを設定
        }

        // 年度のリストを生成
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i); // 過去10年間の年度をリストに追加
        }

        List<Subject> subjectSet = subDao.filter(teacher.getSchool()); // 科目のリストを取得

        // リクエストに属性を設定してJSPに転送
        req.setAttribute("f1", entYear); // 入学年度をリクエストに設定
        req.setAttribute("f2", classNum); // クラス番号をリクエストに設定
        req.setAttribute("f3", subjectCd); // 科目コードをリクエストに設定
        req.setAttribute("f4", studentNo); // 学生番号をリクエストに設定
        req.setAttribute("TLsubs", TLsubs); // 科目ごとのテスト情報をリクエストに設定
        req.setAttribute("TLstus", TLstus); // 学生ごとのテスト情報をリクエストに設定
        req.setAttribute("stu", student); // 学生情報をリクエストに設定
        req.setAttribute("subs", subjectSet); // 科目リストをリクエストに設定
        req.setAttribute("class_num_set", list); // クラス番号リストをリクエストに設定
        req.setAttribute("ent_year_set", entYearSet); // 年度リストをリクエストに設定

        // JSPに転送
        req.getRequestDispatcher("../results/test_list2.jsp").forward(req, res);
    }
}
