package com.daicent.ManagementStudent;

import java.util.ArrayList;

import com.daicent.dao.UserReflection;
import com.daicent.model.User;

public class TestUser {
	public static void main(String[] args) {
//		User t= new User(3, "tranhtinhutrang", "tranhtinhutrang", 5);
//		UserReflection.getInstance().insert(t);
		
		ArrayList<User> list= UserReflection.getInstance().selectAll();
		for(User u:list) {
			System.out.println(u.toString());
		}
		
		

	}
}
