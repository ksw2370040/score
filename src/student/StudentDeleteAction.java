package student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import tool.Action;

public class StudentDeleteAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{
		String no =  req.getParameter("no");
		StudentDao dao= new StudentDao();
		dao.delete(no);
		req.getRequestDispatcher("StudentList.action").forward(req, res);

}}