package user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action{
	public void execute(
		HttpServletRequest req,HttpServletResponse res
	)throws Exception{
		HttpSession session=req.getSession();

		if(session.getAttribute("user")!=null){
			session.removeAttribute("user");
			req.getRequestDispatcher("../user/logout.jsp").forward(req, res);
			}
		req.getRequestDispatcher("../common/error.jsp").forward(req, res);

	}

}