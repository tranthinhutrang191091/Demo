package com.daicent.ManagementStudent;

import java.util.ArrayList;

import com.daicent.dao.SubjectDAO;
import com.daicent.model.Subject;

public class TestSubject {
	public static void main(String[] args) {
		//Subject subject = new Subject(13,"Văn Học");
		//SubjectDAO.getInstance().insert(subject);
		
		//Subject subject = new Subject(13,"Văn Học 1");
		//SubjectDAO.getInstance().update(subject);
		
		//Subject subject = new Subject(13,"Văn Học 1");
		//SubjectDAO.getInstance().delete(subject);
		
//		ArrayList<Subject> list= SubjectDAO.getInstance().selectAll();
//		for(Subject sb:list) {
//			System.out.println(sb.toString());
//		}
		
//		Subject subject1= new Subject(13,"");
//		Subject subject = SubjectDAO.getInstance().selectById(subject1);
//		System.out.println(subject.toString());
		
		String a = "nameSubject like concat('%','Anh', '%')";
		ArrayList<Subject> listSubject = SubjectDAO.getInstance().selectByCondition(a);
		for (Subject sb : listSubject) {
			System.out.println(sb.toString());
		}
		
	}
	
}
