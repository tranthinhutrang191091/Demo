package com.daicent.ManagementStudent;

import com.daicent.dao.StudentSubjectDAO;
import com.daicent.model.StudentSubject;

public class TestStudentSubject {
	public static void main(String[] args) {
		StudentSubject ss= new StudentSubject(62,21,4,8.5f);
		StudentSubjectDAO.getInstance().insert(ss);
		
		
//		Student_Subject ss = new Student_Subject();
//		ss.setIdStudent_Subject(61);
//		Student_Subject ss1= Student_SubjectDAO.getInstance().selectById(ss);
//		System.out.println(ss1.toString());
	}
}
