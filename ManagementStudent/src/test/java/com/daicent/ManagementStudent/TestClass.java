package com.daicent.ManagementStudent;

import java.util.ArrayList;

import com.daicent.dao.ClassDAO;
import com.daicent.dao.UnitDAO;
import com.daicent.model.Class;
import com.daicent.model.Unit;

public class TestClass {
	public static void main(String[] args) {
//		Class class0= new Class(76, "lớp 100", 26);
//		ClassDAO.getInstance().insert(class0);
//		Class class0= new Class(76, "lớp 100", 25);
//		ClassDAO.getInstance().insert(class0);
//		Class class0= new Class(76, "lớp 99", 25);
//		ClassDAO.getInstance().update(class0);
//		Class class0= new Class();
//		class0.setIdClass(76);
//		ClassDAO.getInstance().delete(class0);
		
//		ArrayList<Class> list= ClassDAO.getInstance().selectAll();
//		for(Class cl:list) {
//			System.out.println(cl.toString());
//		}
		
		
//		Class class0 = new Class();
//		class0.setIdClass(76);
//		Class class1= ClassDAO.getInstance().selectById(class0);
//		System.out.println(class1.toString());
		
		
		String a = "nameClass like concat('%','1', '%')";
		ArrayList<Class> listClass = ClassDAO.getInstance().selectByCondition(a);
		for (Class cl : listClass) {
			System.out.println(cl.toString());
		}
	}
	
}
