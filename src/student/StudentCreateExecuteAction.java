package student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action{
	public void execute(
			HttpServletRequest req,HttpServletResponse res
		)throws Exception{
		HttpSession session = req.getSession();
    	boolean exists = true;
    	List<Student> students = new ArrayList<>();
    	StudentDao studentDao = new StudentDao();


		Teacher teacher =(Teacher) session.getAttribute("user");
		Integer entYear = Integer.parseInt(req.getParameter("ent_year"));
		String No = req.getParameter("no");
		String Name = req.getParameter("name");
		String classNum = req.getParameter("class_num");
		boolean isAttend = true;
		School school= teacher.getSchool();

    	students = studentDao.filter(school, isAttend);


        exists = students.stream().anyMatch(student -> student.getNo().equals(No));

        if (exists) {
            session.setAttribute("error", "この学生番号は既に存在します。");
            // エラーメッセージを表示するためにリダイレクト
            req.getRequestDispatcher("../student/StudentCreate.action").forward(req, res);

        } else {

		Student student =new Student();
		student.setEntYear(entYear);
		student.setNo(No);
		student.setName(Name);
		student.setClassNum(classNum);
		student.setIsAttend(isAttend);
		student.setSchool(school);

		StudentDao dao = new StudentDao();
		dao.save(student);
		req.getRequestDispatcher("../student/student_add_complete.jsp").forward(req, res);
		}



		}

}