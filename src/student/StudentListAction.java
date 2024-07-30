package student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{

			String entYearStr=req.getParameter("f1");
			String classNum=req.getParameter("f2");
			String isAttendStr=req.getParameter("f3");

			HttpSession session = req.getSession();
			Teacher teacher =(Teacher) session.getAttribute("user");

			int entYear=0;
			boolean isAttend=false;
			List<Student> students=null;
			LocalDate todaysDate=LocalDate.now();
			int year=todaysDate.getYear();
			StudentDao sDao=new StudentDao();
			ClassNumDao cNumDao=new ClassNumDao();
			Map<String, String> errors=new HashMap<>();



			List<String> list = cNumDao.filter(teacher.getSchool());

			if (entYearStr != null){
				entYear = Integer.parseInt(entYearStr);
			}
			if (isAttendStr != null){
				isAttend = true;
				req.setAttribute("f3", isAttendStr);
			}



			if (entYear != 0 && !classNum.equals("0")){
				students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
			} else if (entYear != 0 && classNum.equals("0")){
				students = sDao.filter(teacher.getSchool(), entYear, isAttend);
			}else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")){
				students = sDao.filter(teacher.getSchool(), isAttend);
			}else{
				errors.put("f1","クラスを指定する場合は入学年度も指定してください");
				req.setAttribute("errors", errors);

				students = sDao.filter(teacher.getSchool(),isAttend);
			}

			if (entYearStr != null){
				entYear = Integer.parseInt(entYearStr);
			}

			List<Integer> entYearSet = new ArrayList<>();
			for (int i = year - 10; i < year +1; i++){
				entYearSet.add(i);
			}
			req.setAttribute("f1", entYear);
			req.setAttribute("f2", classNum);
			req.setAttribute("students", students);
			req.setAttribute("class_num_set", list);
			req.setAttribute("ent_year_set", entYearSet);
			req.getRequestDispatcher("../student/student_list.jsp").forward(req, res);
		}
}