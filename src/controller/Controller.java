package controller;

import model.dao.StudentDao;

public abstract class Controller {
	
	protected StudentDao studentDao=null;
	
	public abstract void start();
	
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao=studentDao;
	}
	
	public StudentDao getStudentDao() {
		return studentDao;
	}
	
}
