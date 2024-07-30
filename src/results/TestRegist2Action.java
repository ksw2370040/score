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

public class TestRegist2Action extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{
		Util util = new Util();
		Subject subject = new Subject();
		StudentDao stuDao = new StudentDao();
		SubjectDao subDao= new SubjectDao();
		List<Student> students= new ArrayList<>();
		TestDao tDao = new TestDao();
		List<Test> tests = new ArrayList<>();
			Teacher teacher =util.getUser(req);

			String entYearStr=req.getParameter("f1");
			String classNum=req.getParameter("f2");
			String subject_cd=req.getParameter("f3");
			String noStr=req.getParameter("f4");

			int entYear=0;
			int no=0;

			if (entYearStr != null){
				entYear = Integer.parseInt(entYearStr);
			}
			if (noStr != null){
				no = Integer.parseInt(noStr);
			}

			if (entYear != 0 && !classNum.equals("0") && !subject_cd.equals("0") && no != 0  ){
				subject = subDao.get(subject_cd, teacher.getSchool());
				students = stuDao.filter(teacher.getSchool(),entYear,classNum, true);
				tests = tDao.filter(entYear, classNum, subject, no, teacher.getSchool());
			}

			req.setAttribute("f1", entYear);
			req.setAttribute("f2", classNum);
			req.setAttribute("f3", subject_cd);
			req.setAttribute("f4", noStr);
			req.setAttribute("tests", tests);
			req.setAttribute("students", students);
			req.setAttribute("subject", subject);
			req.setAttribute("no", no);
			util.setClassNum(req);
			util.setEntYearSet(req);
			util.setSubject(req);
			util.setNumSet(req);
			req.getRequestDispatcher("../results/test_regist2.jsp").forward(req, res);
		}

}