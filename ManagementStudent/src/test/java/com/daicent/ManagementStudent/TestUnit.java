package com.daicent.ManagementStudent;

import java.util.ArrayList;

import com.daicent.dao.SubjectDAO;
import com.daicent.dao.UnitDAO;
import com.daicent.model.Subject;
import com.daicent.model.Unit;

public class TestUnit {
	public static void main(String[] args) {
//		Unit u = new Unit(26,"Khối 1", 6);
//		UnitDAO.getInstance().insert(u);
		
//		Unit u = new Unit(26,"Khối 7", 6);
//		UnitDAO.getInstance().update(u);
		
//		Unit u = new Unit();
//		u.setIdUnit(26);
//		UnitDAO.getInstance().delete(u);
		
//		ArrayList<Unit> list= UnitDAO.getInstance().selectAll();
//		for(Unit u:list) {
//			System.out.println(u.toString());
//		}
		
//		Unit u = new Unit();
//		u.setIdUnit(26);
//		Unit u2 =UnitDAO.getInstance().selectById(u);
//		System.out.println(u2.toString());
		
		String a = "nameUnit like concat('%','1', '%')";
		ArrayList<Unit> listUnit = UnitDAO.getInstance().selectByCondition(a);
		for (Unit u : listUnit) {
			System.out.println(u.toString());
		}
	}
}
