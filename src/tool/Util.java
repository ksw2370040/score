package tool;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;

public class Util{
public Teacher getUser(HttpServletRequest req)
	throws Exception{
		HttpSession session = req.getSession();
		Teacher user =(Teacher) session.getAttribute("user");
		return user;
	}
public void setClassNum(HttpServletRequest req)
	throws Exception{
	Teacher teacher = getUser(req);
	ClassNumDao cNumDao=new ClassNumDao();
	List<String> list = cNumDao.filter(teacher.getSchool());
	req.setAttribute("class_num_set", list);
	}
public void setEntYearSet(HttpServletRequest req)
	throws Exception{
	LocalDate todaysDate=LocalDate.now();

	int year=todaysDate.getYear();
	List<Integer> entYearSet = new ArrayList<>();
	for (int i = year - 10; i < year +1; i++){
		entYearSet.add(i);
	}

	req.setAttribute("ent_year_set", entYearSet);
	}
public void setSubject(HttpServletRequest req)
	throws Exception{
	Teacher user = getUser(req);
	SubjectDao subjectDao= new SubjectDao();
	List<Subject> subjects = subjectDao.filter(user.getSchool());
	req.setAttribute("subjects", subjects);
	}
public void setNumSet(HttpServletRequest req)
	throws Exception{
	List<Integer> number = new ArrayList<>();
	for (int i = 1; i <= 10; i++){
	number.add(i);
	}
	req.setAttribute("number", number);
	}
}
