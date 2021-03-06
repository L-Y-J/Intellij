package unit;

import bean.Students;
import bean.Subsidize;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StudentsService;
import service.SubsidizeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yongjie on 14-5-22.
 */
public class StudentsTest {

	private static StudentsService studentsService;
	private static SubsidizeService subsidizeService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext applicationcontext = new ClassPathXmlApplicationContext("beans.xml");
			studentsService = (StudentsService) applicationcontext.getBean("StudentsService");
			subsidizeService = (SubsidizeService) applicationcontext.getBean("SubsidizeService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTest(){
		Students students = new Students();
		students.setStudentName("测试学生1");
		studentsService.addStudent(students);
	}

	@Test
	public void getTest(){
		Students student = studentsService.getStudent(221);
		System.out.println(student.getStudentId());
	}

	@Test
	public void getAllTest(){
		List students = studentsService.getStudents();
		for (Object o : students){
			Students s = (Students) o;
			System.out.println(s.getStudentId());
		}
	}

	@Test
	public void updateTest(){
		Students student = studentsService.getStudent(221);
		student.setStudentName("测试学生");
		boolean a = studentsService.updateStudent(student);
		System.out.println(a);
	}

	@Test
	public void deleteTest(){
		Students student = studentsService.getStudent(222);
		boolean b = studentsService.deleteStudent(student);
		System.out.println(b);
	}

	@Test
	public void Students_Subidize_Test(){
		Students student = studentsService.getStudent(303);
		Subsidize subsidize = subsidizeService.getSubsidize(301);
		student.getSubsidize().add(subsidize);
//		student.getSubsidize().clear();
		studentsService.updateStudent(student);
	}

	@Test
	public void queryStudentBySchoolTest(){
		List list = studentsService.queryStudentBySchool("学校1");
		for (Object o : list){
			Students s = (Students) o;
			System.out.println(s.getStudentName());
		}
	}

	@Test
	public void queryStudentsByName(){
		List list = studentsService.queryStudentByName("测试学生");
		Set set = new HashSet(list);
		for (Object o : set){
			System.out.println(((Students)o).getStudentId());
		}
	}
}

