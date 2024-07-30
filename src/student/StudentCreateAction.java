package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{


			HttpSession session = req.getSession();
			Teacher teacher =(Teacher) session.getAttribute("user");

			String entYearStr=req.getParameter("ent_year");
			String classNum=req.getParameter("class_num");

			int entYear=0;
			LocalDate todaysDate=LocalDate.now();
			int year=todaysDate.getYear();
			ClassNumDao cNumDao=new ClassNumDao();


			List<String> list = cNumDao.filter(teacher.getSchool());

			if (entYearStr != null){
				entYear = Integer.parseInt(entYearStr);
			}


			List<Integer> entYearSet = new ArrayList<>();
			for (int i = year - 10; i < year +1; i++){
				entYearSet.add(i);
			}
			req.setAttribute("ent_year", entYear);
			req.setAttribute("class_num", classNum);

			req.setAttribute("class_num_set", list);
			req.setAttribute("ent_year_set", entYearSet);
			req.getRequestDispatcher("../student/student_add.jsp").forward(req, res);
		}

}