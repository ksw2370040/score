package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{
		HttpSession session = req.getSession();

		Teacher teacher =(Teacher) session.getAttribute("user");
		boolean isAttend = false;
		Integer entYear = Integer.parseInt(req.getParameter("ent_year"));
		String No = req.getParameter("no");
		String Name = req.getParameter("name");
		String classNum = req.getParameter("class_num");
		String isAttendStr=req.getParameter("isAttend");
		School school= teacher.getSchool();
		if (isAttendStr != null){
			isAttend = true;
			req.setAttribute("isAttend", isAttendStr);
		}


		Student student =new Student();
		student.setEntYear(entYear);
		student.setNo(No);
		student.setName(Name);
		student.setClassNum(classNum);
		student.setIsAttend(isAttend);
		student.setSchool(school);


		StudentDao dao = new StudentDao();
		dao.save(student);
		req.getRequestDispatcher("../student/student_update_complete.jsp").forward(req, res);



		}

}