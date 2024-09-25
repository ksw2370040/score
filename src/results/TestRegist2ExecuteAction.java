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

public class TestRegist2ExecuteAction extends Action {

    // このメソッドは、HTTPリクエストを処理し、テスト結果を保存する
    public void execute(
            HttpServletRequest req, HttpServletResponse res
    ) throws Exception {
        Util util = new Util(); // ユーティリティクラスのインスタンスを生成

        List<Test> tests = new ArrayList<>(); // テスト情報を格納するリスト
        Teacher teacher = util.getUser(req); // リクエストから教師情報を取得

        // リクエストからパラメータを取得
        String pointstr[] = req.getParameterValues("test_point"); // テストの点数
        String student_nos[] = req.getParameterValues("student_no"); // 学生番号
        String subject_cds[] = req.getParameterValues("subject_cd"); // 科目コード
        String class_nums[] = req.getParameterValues("class_num"); // クラス番号
        String nostr[] = req.getParameterValues("test_no"); // テスト番号
        String count = req.getParameter("count"); // テストの数

        TestDao dao = new TestDao(); // テストDAOのインスタンスを生成

        // 取得したテストの数だけループ
        for (int i = 0; i < Integer.parseInt(count); i++) {
            Student student = new Student(); // 学生オブジェクトを生成
            Subject subject = new Subject(); // 科目オブジェクトを生成
            Test test = new Test(); // テストオブジェクトを生成

            // 各パラメータを整数に変換し、対応するオブジェクトに設定
            int point = Integer.parseInt(pointstr[i]); // テストの点数を取得
            int no = Integer.parseInt(nostr[i]); // テスト番号を取得
            String studentNo = student_nos[i]; // 学生番号を取得
            String subjectCd = subject_cds[i]; // 科目コードを取得
            String classNum = class_nums[i]; // クラス番号を取得

            // 学生オブジェクトに情報を設定
            student.setNo(studentNo); // 学生番号を設定
            subject.setCd(subjectCd); // 科目コードを設定

            // テストオブジェクトに情報を設定
            test.setPoint(point); // テストの点数を設定
            test.setNo(no); // テスト番号を設定
            test.setStudent(student); // 学生オブジェクトを設定
            test.setSubject(subject); // 科目オブジェクトを設定
            test.setClassNum(classNum); // クラス番号を設定
            test.setSchool(teacher.getSchool()); // 学校名を教師から取得して設定

            tests.add(test); // テストリストに追加
        }

        dao.save(tests); // テスト情報をデータベースに保存

        // 保存完了後、結果追加完了ページにフォワード
        req.getRequestDispatcher("../results/results_add_complete.jsp").forward(req, res);
    }
}
