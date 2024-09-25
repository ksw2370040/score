package results;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;
import tool.Util;

public class TestDeleteExecuteAction extends Action {
    /*
     * テストの点数の削除処理を実行します。
     *
     * @param req HTTPリクエスト
     * @param res HTTPレスポンス
     * @throws Exception 処理中に発生した例外をスローします。
     */
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Util util = new Util(); // ユーティリティクラスのインスタンスを生成

        List<Test> tests = new ArrayList<>(); // Testオブジェクトのリストを初期化
        Teacher teacher = util.getUser(req); // 現在のユーザー（教師）を取得

        // リクエストからパラメータを取得
        String[] studentNos = req.getParameterValues("student_no"); // 学生番号の配列を取得
        String[] subjectCds = req.getParameterValues("subject_cd"); // 科目コードの配列を取得
        String[] classNums = req.getParameterValues("class_num"); // クラス番号の配列を取得
        String[] nostr = req.getParameterValues("test_no"); // テスト番号の配列を取得
        String countStr = req.getParameter("count"); // テストの件数を取得

        // TestDaoのインスタンスを生成
        TestDao dao = new TestDao();

        // テスト削除のためのTestオブジェクトを作成
        for (int i = 0; i < Integer.parseInt(countStr); i++) {
            Student student = new Student(); // 新しいStudentオブジェクトを作成
            Subject subject = new Subject(); // 新しいSubjectオブジェクトを作成
            Test test = new Test(); // 新しいTestオブジェクトを作成

            int no = Integer.parseInt(nostr[i]); // テスト番号を取得
            String studentNo = studentNos[i]; // 学生番号を取得
            String subjectCd = subjectCds[i]; // 科目コードを取得
            String classNum = classNums[i]; // クラス番号を取得

            student.setNo(studentNo); // 学生番号を設定
            subject.setCd(subjectCd); // 科目コードを設定

            test.setNo(no); // テスト番号を設定
            test.setStudent(student); // 学生情報を設定
            test.setSubject(subject); // 科目情報を設定
            test.setClassNum(classNum); // クラス番号を設定
            test.setSchool(teacher.getSchool()); // 学校情報を設定

            tests.add(test); // テスト情報をリストに追加
        }

        // TestDaoを使ってテストを削除
        dao.delete(tests);

        // 処理完了後、結果ページに転送
        req.getRequestDispatcher("../results/results_delete_complete.jsp").forward(req, res);
    }
}
