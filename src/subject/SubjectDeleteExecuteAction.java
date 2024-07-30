package subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{
		String cd =  req.getParameter("cd");
		SubjectDao dao= new SubjectDao();
		dao.delete(cd);
		req.getRequestDispatcher("SubjectList.action").forward(req, res);

}}