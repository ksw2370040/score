package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import tool.Util;

public class SubjectUpdateExecuteAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{
		Util util = new Util();
		Teacher teacher = util.getUser(req);
		String cd = req.getParameter("cd");
		String Name = req.getParameter("name");
		School school= teacher.getSchool();


		Subject subject =new Subject();
		subject.setCd(cd);
		subject.setName(Name);
		subject.setSchool(school);


		SubjectDao dao = new SubjectDao();
		dao.save(subject);
		req.getRequestDispatcher("../subject/subject_update_complete.jsp").forward(req, res);



		}

}