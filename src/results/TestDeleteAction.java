package results;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import tool.Action;
import tool.Util;

public class TestDeleteAction extends Action {

    /**
     * HTTPリクエストからテスト情報を取得し、削除リクエストをJSPに転送します。
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

        int count = Integer.parseInt(countStr);

        for (int i = 0; i < count; i++) {
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

        // テスト情報をリクエストスコープにセット
        req.setAttribute("tests", tests);

        // JSPに転送
        req.getRequestDispatcher("../results/regist_delete.jsp").forward(req, res);
    }
}
