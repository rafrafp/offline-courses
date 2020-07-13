package controller;

import model.Session;
import model.transferObjects.Student;
import view.screens.SelectCategoryToSolveQuizScreen;
import view.utilities.DisplayStudentDetails;

public class SelectedCategoryToSolveQuizController extends Controller {
	SelectCategoryToSolveQuizScreen selectCategoryScreen=null;
	
	
	@Override
	public void start() {
		Student student=Session.getInstance().getCurrentUser();
		student=Session.getInstance().getCurrentUser();
		DisplayStudentDetails displayStudentDetails=new DisplayStudentDetails();
		displayStudentDetails.displaylblUser(student.getFirstName(), student.getLastName(), selectCategoryScreen.getPanelBar().getLblHelloFirstAndLastName());	
	}


	public SelectCategoryToSolveQuizScreen getSelectCategoryScreen() {
		return selectCategoryScreen;
	}


	public void setSelectCategoryScreen(SelectCategoryToSolveQuizScreen selectCategoryScreen) {
		this.selectCategoryScreen = selectCategoryScreen;
	}
	
}
