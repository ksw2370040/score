package subject;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import tool.Util;

public class SubjectListAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{
		List<Subject> list = null;
			Util util=new Util();
			Teacher teacher =util.getUser(req);
			SubjectDao subjectDao= new SubjectDao();
			String Name=req.getParameter("f1");
			if (Name!=null){
				list = subjectDao.filter(teacher.getSchool(),Name);
			}else{
				list = subjectDao.filter(teacher.getSchool());
			}

			req.setAttribute("subjects", list);
			req.getRequestDispatcher("../subject/subject_list.jsp").forward(req, res);
		}

}