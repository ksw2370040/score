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
    public void execute(
    			HttpServletRequest req, HttpServletResponse res
    		) throws Exception {
		Util util=new Util();

        List<Test> tests = new ArrayList<>();
        Teacher teacher = util.getUser(req);

        String pointstr[] = req.getParameterValues("test_point");
        String student_nos[] = req.getParameterValues("student_no");
        String subject_cds[] = req.getParameterValues("subject_cd");
        String class_nums[] = req.getParameterValues("class_num");
        String nostr[] = req.getParameterValues("test_no");
        String count = req.getParameter("count");

        TestDao dao = new TestDao();

        for (int i = 0; i < Integer.parseInt(count); i++) {

        	Student student = new Student();
            Subject subject = new Subject();
        	Test test = new Test();

            int point = Integer.parseInt(pointstr[i]);
            int no = Integer.parseInt(nostr[i]);
            String studentNo = student_nos[i];
            String subjectCd = subject_cds[i];
            String classNum = class_nums[i];

            student.setNo(studentNo);
            subject.setCd(subjectCd);

            test.setPoint(point);
            test.setNo(no);
            test.setStudent(student);
            test.setSubject(subject);
            test.setClassNum(classNum);
            test.setSchool(teacher.getSchool());

            tests.add(test);
        }
        dao.save(tests);

        req.getRequestDispatcher("../results/results_add_complete.jsp").forward(req, res);
    }
}
