package subject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import tool.Util;

public class SubjectCreateAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{

			Util util=new Util();
			Teacher teacher =util.getUser(req);
			String cd = req.getParameter("cd");

			SubjectDao subjectDao= new SubjectDao();


			List<Subject> list = subjectDao.filter(teacher.getSchool(),cd);

			req.setAttribute("subjects", list);
			req.getRequestDispatcher("../subject/subject_add.jsp").forward(req, res);
		}

}

