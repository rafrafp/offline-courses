package model;

import java.util.HashMap;

public class Constants {
	//HashMap< CoursesEnum, int> 

	public enum CoursesEnum {

		C, JAVA, DATA_STRUCTURES, SQL;
	}

	public enum MedalsEnum {

		NO_MEDAL, BRONZE, SILVER, GOLD;
	}

	public static HashMap< Integer, MedalsEnum> gradeToMedal = new HashMap<Integer, MedalsEnum>();
	public static HashMap<CoursesEnum, String> courseToCourseName=new HashMap<CoursesEnum, String>();
	static {
		gradeToMedal.put(80,MedalsEnum.BRONZE);
		gradeToMedal.put(90,MedalsEnum.SILVER);
		gradeToMedal.put(100,MedalsEnum.GOLD);
		
		courseToCourseName.put(CoursesEnum.C, "C");
		courseToCourseName.put(CoursesEnum.JAVA, "JAVA");
		courseToCourseName.put(CoursesEnum.DATA_STRUCTURES, "DATA");
		courseToCourseName.put(CoursesEnum.SQL, "SQL");
		
	}
}


