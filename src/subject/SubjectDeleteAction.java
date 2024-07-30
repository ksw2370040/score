package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;
import tool.Util;

public class SubjectDeleteAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{
			Util util = new Util();
			Teacher user = util.getUser(req);
			String subject_cd= req.getParameter("cd");
			SubjectDao subjectDao= new SubjectDao();
			Subject subject = subjectDao.get(subject_cd,user.getSchool());
			req.setAttribute("subject", subject);
			req.getRequestDispatcher("../subject/SubjectDeleteConfirm.jsp").forward(req, res);


	}
}