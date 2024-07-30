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
    /**
     * テスト削除処理を実行します。
     * @param req HTTPリクエスト
     * @param res HTTPレスポンス
     * @throws Exception 処理中に発生する可能性のある例外
     */
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Util util = new Util();

        List<Test> tests = new ArrayList<>();
        Teacher teacher = util.getUser(req);

        // リクエストからパラメータを取得
        String[] studentNos = req.getParameterValues("student_no");
        String[] subjectCds = req.getParameterValues("subject_cd");
        String[] classNums = req.getParameterValues("class_num");
        String[] nostr = req.getParameterValues("test_no");
        String countStr = req.getParameter("count");

        // TestDaoのインスタンスを生成
        TestDao dao = new TestDao();

        // テスト削除のためのTestオブジェクトを作成
        for (int i = 0; i < Integer.parseInt(countStr); i++) {
            Student student = new Student();
            Subject subject = new Subject();
            Test test = new Test();

            int no = Integer.parseInt(nostr[i]);
            String studentNo = studentNos[i];
            String subjectCd = subjectCds[i];
            String classNum = classNums[i];

            student.setNo(studentNo);
            subject.setCd(subjectCd);

            test.setNo(no);
            test.setStudent(student);
            test.setSubject(subject);
            test.setClassNum(classNum);
            test.setSchool(teacher.getSchool());

            tests.add(test);
        }

        // TestDaoを使ってテストを削除
        dao.delete(tests);

        // 処理完了後、結果ページに転送
        req.getRequestDispatcher("../results/results_delete_complete.jsp").forward(req, res);
    }
}
