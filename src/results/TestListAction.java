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
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Util util = new Util();
        Student student = new Student();
        StudentDao stuDao = new StudentDao();
        Subject subject = new Subject();
        SubjectDao subDao = new SubjectDao();

        Teacher teacher = util.getUser(req);

        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subjectCd = req.getParameter("f3");
        String studentNo = req.getParameter("f4");

        int entYear = 0;
        List<TestListSubject> TLsubs = new ArrayList<>();
        List<TestListStudent> TLstus = new ArrayList<>();

        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        TestListSubjectDao TLsubDao = new TestListSubjectDao();
        TestListStudentDao TLstuDao = new TestListStudentDao();
        ClassNumDao cNumDao = new ClassNumDao();

        List<String> list = cNumDao.filter(teacher.getSchool());
		Map<String, String> errors=new HashMap<>();




        subject.setCd(subjectCd);

        student.setNo(studentNo);
        student.setSchool(teacher.getSchool());

            if (entYearStr != null && !entYearStr.isEmpty()) {
                entYear = Integer.parseInt(entYearStr);
            }

            if (entYear != 0 && !classNum.equals("0") && !subject.equals("0")) {
                try {
                    TLsubs = TLsubDao.filter(entYear, classNum, subject, teacher.getSchool());


                } catch (Exception e) {
                    e.printStackTrace();

                }
            }else if (studentNo != null && !studentNo.equals("0") ) {
                try {
                    TLstus = TLstuDao.filter(student);
                    student = stuDao.get(studentNo);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{

				errors.put("f1","入学年度とクラスと科目を選択してください。");
				req.setAttribute("errors", errors);
                req.setAttribute("nosearch", true);


            }




        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        List<Subject> subjectSet = new ArrayList<>();
        subjectSet = subDao.filter(teacher.getSchool());




        req.setAttribute("f1", entYear);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("f4", studentNo);
        req.setAttribute("TLsubs", TLsubs);
        req.setAttribute("TLstus",TLstus);
        req.setAttribute("stu", student);
        req.setAttribute("subs", subjectSet);
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        req.getRequestDispatcher("../results/test_list2.jsp").forward(req, res);
    }
}
