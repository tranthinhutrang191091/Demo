package com.daicent.ManagementStudent;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import com.daicent.annotation.UserAnnotation;
import com.daicent.dao.UserReflection;
import com.daicent.model.User;

public class TestUser {
	public static void main(String[] args) {
//		User t= new User(4, "tranhtinhutrang10", "tranhtinhutrang1", 8);
//		UserReflection.getInstance().insert(t);
		
//		User t= new User(4, "tranhtinhutrang10", "abc", 8);
//		UserReflection.getInstance().update(t);
		
//		User t= new User(4, "tranhtinhutrang10", "abc", 8);
//		UserReflection.getInstance().delete(t);
		
		ArrayList<User> list= UserReflection.getInstance().selectAll();
		for(User u:list) {
			System.out.println(u.toString());
		}
		
		
//		Class user = User.class;
//		Annotation annotation = user.getAnnotation(UserAnnotation.class);
//		UserAnnotation userAnnotation= null;
//		if(annotation instanceof UserAnnotation) {
//			userAnnotation = (UserAnnotation) annotation;
//			System.out.println(userAnnotation.idUser()+" ads");
//			System.out.println(userAnnotation.userName()+" ads");
//			System.out.println(userAnnotation.password()+" ads");
//			System.out.println(userAnnotation.idStudent()+" ads");
//			
//		}
		

	}
}
