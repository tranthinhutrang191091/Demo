package com.daicent.ManagementStudent;

import com.daicent.dao.Student_SubjectDAO;
import com.daicent.model.Student_Subject;

public class TestStudent_Subject {
	public static void main(String[] args) {
		Student_Subject ss= new Student_Subject(62,21,4,8.5f);
		Student_SubjectDAO.getInstance().insert(ss);
		
		
//		Student_Subject ss = new Student_Subject();
//		ss.setIdStudent_Subject(61);
//		Student_Subject ss1= Student_SubjectDAO.getInstance().selectById(ss);
//		System.out.println(ss1.toString());
	}
}
