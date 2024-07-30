package student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{
			String no =  req.getParameter("no");
			StudentDao dao= new StudentDao();
			Student student =null;
			student=dao.get(no);
			req.setAttribute("student", student);

			HttpSession session = req.getSession();
			Teacher teacher =(Teacher) session.getAttribute("user");

			String classNum=req.getParameter("class_num");

			ClassNumDao cNumDao=new ClassNumDao();


			List<String> list = cNumDao.filter(teacher.getSchool());
			req.setAttribute("class_num", classNum);

			req.setAttribute("class_num_set", list);
			req.getRequestDispatcher("../student/student_update.jsp").forward(req, res);


	}
}