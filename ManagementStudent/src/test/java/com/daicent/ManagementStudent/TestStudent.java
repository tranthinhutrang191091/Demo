package com.daicent.ManagementStudent;

import java.util.ArrayList;

import com.daicent.dao.ClassDAO;
import com.daicent.dao.StudentDAO;
import com.daicent.model.Class;
import com.daicent.model.Student;

public class TestStudent {
	public static void main(String[] args) {
//		Student student = new Student(63, "Trang  vcxvxb ", 100);
//		StudentDAO.getInstance().insert(student);
//		Student student = new Student(62, "Trang ", 1);
//		StudentDAO.getInstance().update(student);
//		Student student = new Student();
//		student.setIdStudent(62);
//		StudentDAO.getInstance().delete(student);
		
		
//		ArrayList<Student> list= StudentDAO.getInstance().selectAll();
//		for(Student st:list) {
//			System.out.println(st.toString());
//		}
		
		
//		Student student = new Student();
//		student.setIdStudent(61);
//		Student student1= StudentDAO.getInstance().selectById(student);
//		System.out.println(student1.toString());
		
		
		String a = "nameStudent like concat('%','Trang', '%')";
		ArrayList<Student> listStudent = StudentDAO.getInstance().selectByCondition(a);
		for (Student st : listStudent) {
			System.out.println(st.toString());
		}
	}
}
