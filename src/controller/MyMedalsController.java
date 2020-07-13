package controller;

import model.Constants;
import model.Constants.MedalsEnum;
import model.Session;
import model.dao.QuizDao;
import model.transferObjects.Student;
import view.screens.MyMedalsScreen;
import view.utilities.DisplayStudentDetails;

public class MyMedalsController extends Controller {
	private MyMedalsScreen myMedalsScreen = null;
	private QuizDao quizDao=null;
	private MainScreenController mainScreenController;
	
	@Override
	public void start() {
		Student student;
		try {
			student = studentDao.getStudent(Session.getInstance().getCurrentUser().getUserName());
			DisplayStudentDetails displayFromSession=new DisplayStudentDetails();
			displayFromSession.displaylblUser(student.getFirstName(), student.getLastName(), myMedalsScreen.getPanelBar().getLblHelloFirstAndLastName());
		} catch (Exception e) {
			myMedalsScreen.showError("Failed to display the use on the panel bar "+e.getMessage());
		}
		
		displayMedalsAndGrades();
		myMedalsScreen.setVisible(true);
	}

	private void displayMedalsAndGrades() {
		displayMedal(Constants.CoursesEnum.C);
		displayMedal(Constants.CoursesEnum.JAVA);
		displayMedal(Constants.CoursesEnum.DATA_STRUCTURES);
		displayMedal(Constants.CoursesEnum.SQL);
	}

	private void displayMedal(Constants.CoursesEnum course) {
		try {
			
			int grade=quizDao.getQuizGrade(course, Session.getInstance().getCurrentUser().getID());
			if(grade==-1) {
				myMedalsScreen.displayYourGrade("No Quiz Done .", course);
				
				
			}
			else {
				myMedalsScreen.displayYourGrade("Your grade is "+grade, course);

			}
			Constants.MedalsEnum medal=calcMedalForGrade(grade);
			myMedalsScreen.displayMedal(course,medal);
			
			
		} catch (Exception e) {
			myMedalsScreen.showError("Failed to get grade. "+e.getMessage());
		}
	}

	public MyMedalsScreen getMyMedalsScreen() {
		return myMedalsScreen;
	}

	public void setMyMedalsScreen(MyMedalsScreen myMedalsScreen) {
		this.myMedalsScreen = myMedalsScreen;
	}

	public QuizDao getQuizDao() {
		return quizDao;
	}

	public void setQuizDao(QuizDao quizDao) {
		this.quizDao = quizDao;
	}
	
	
	
	public MainScreenController getMainScreenController() {
		return mainScreenController;
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

	private Constants.MedalsEnum calcMedalForGrade(int grade){
		Constants.MedalsEnum result=MedalsEnum.NO_MEDAL;
		int maxMedalGrade=0;
		for(int medalGrade: Constants.gradeToMedal.keySet()) {
			if((grade >= medalGrade) && (medalGrade>maxMedalGrade)) {
				maxMedalGrade=medalGrade;
				result=Constants.gradeToMedal.get(medalGrade);
			}
		}
		return result;
	}

	public void logOut() {
		myMedalsScreen.dispose();
		myMedalsScreen.clearAllData();
		mainScreenController.logOut();
		
	}
}
