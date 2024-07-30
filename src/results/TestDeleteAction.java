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
    public void execute(
    			HttpServletRequest req, HttpServletResponse res
    		) throws Exception {
		Util util=new Util();

        List<Test> tests = new ArrayList<>();
        Teacher teacher = util.getUser(req);

        String student_nos[] = req.getParameterValues("student_no");
        String subject_cds[] = req.getParameterValues("subject_cd");
        String class_nums[] = req.getParameterValues("class_num");
        String nostr[] = req.getParameterValues("test_no");
        String count = req.getParameter("count");


        for (int i = 0; i < Integer.parseInt(count); i++) {

        	Student student = new Student();
            Subject subject = new Subject();
        	Test test = new Test();

            int no = Integer.parseInt(nostr[i]);
            String studentNo = student_nos[i];
            String subjectCd = subject_cds[i];
            String classNum = class_nums[i];

            student.setNo(studentNo);
            subject.setCd(subjectCd);

            test.setNo(no);
            test.setStudent(student);
            test.setSubject(subject);
            test.setClassNum(classNum);
            test.setSchool(teacher.getSchool());

            tests.add(test);
        }
        req.setAttribute("tests", tests);

        req.getRequestDispatcher("../results/regist_delete.jsp").forward(req, res);
    }
}
