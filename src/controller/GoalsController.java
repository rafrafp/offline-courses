package controller;

import model.Session;
import model.transferObjects.Student;
import view.screens.GoalsScreen;
import view.utilities.DisplayStudentDetails;

public class GoalsController extends Controller {
	
	private GoalsScreen goalsScreen=null;
	private MainScreenController mainScreenController=null;
	
	public GoalsScreen getGoalsScreen() {
		return goalsScreen;
	}

	public void setGoalsScreen(GoalsScreen goalsScreen) {
		this.goalsScreen = goalsScreen;
	}

	@Override
	public void start() {
		Student student;
		try {
			student = studentDao.getStudent(Session.getInstance().getCurrentUser().getUserName());
			DisplayStudentDetails displayFromSession=new DisplayStudentDetails();
			displayFromSession.displaylblUser(student.getFirstName(), student.getLastName(), goalsScreen.getPanelBar().getLblHelloFirstAndLastName());
		} catch (Exception e) {
			goalsScreen.showMessage("Failed to display the use on the panel bar "+e.getMessage());
		}
		goalsScreen.setVisible(true);
		
	}
	
	

	public MainScreenController getMainScreenController() {
		return mainScreenController;
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

	public void logOut() {
		goalsScreen.dispose();
		mainScreenController.logOut();

	}
	

}
