package model.dao;

import model.transferObjects.Student;

public interface StudentDao {

	void insert(Student student) throws Exception;

	int getUserId(String userName, String password) throws Exception;
	
	boolean isUserExist(String userName) throws Exception;
	
	Student getStudent(String userName) throws Exception;
	
	public void delete(String userName) throws Exception;

	
}