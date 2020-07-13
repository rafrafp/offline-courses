package controller;

import model.Session;
import model.dao.QuizDao;
import model.transferObjects.Student;
import view.screens.CourseScreen;
import view.screens.MainScreen;
import view.utilities.DisplayStudentDetails;

public class MainScreenController extends Controller {

	private MainScreen mainScreen = null;
	private LoginController loginController = null;
	private QuizController quizController = null;
	private String courseName;
	private GoalsController goalsController = null;
	private QuizDao quizDao = null;
	private CourseScreen courseScreen = null;

	public MainScreen getTestScreen() {
		return mainScreen;
	}

	public void setMainScreen(MainScreen testScreen) {
		this.mainScreen = testScreen;
	}

	public MainScreenController() {
	}

	@Override
	public void start() {

		try {
			mainScreen.setMainScreenController(this);
			Student student = studentDao.getStudent(Session.getInstance().getCurrentUser().getUserName());
			DisplayStudentDetails displayFromSession = new DisplayStudentDetails();
			displayFromSession.displaylblUser(student.getFirstName(), student.getLastName(),
					mainScreen.getLblHelloFirstAndLastName());
			mainScreen.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void logOut() {

		mainScreen.dispose();
		loginController.cleanTextFields();
		courseScreen.dispose();
		mainScreen.getCoursesOnScreen();
		Session.getInstance().setCurrentUser(null);
		loginController.setLoginScreenEnableAgainAndClearAllFields();
		loginController.start();

	}

	public void coursesWasPressed() {
		mainScreen.setVisible(true);
		mainScreen.getCoursesOnScreen();
	}

	public void quizzesWasPressed() {
		mainScreen.setVisible(true);
		mainScreen.getQuizOnScreen();
	}

	public void goalsWasPresses() {
		mainScreen.setVisible(true);
		mainScreen.getGoalsOnScreen();
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	public QuizDao getQuizDao() {
		return quizDao;
	}

	public void setQuizDao(QuizDao quizDao) {
		this.quizDao = quizDao;
	}

	public CourseScreen getCourseScreen() {
		return courseScreen;
	}

	public void setCourseScreen(CourseScreen courseScreen) {
		this.courseScreen = courseScreen;
	}

	public QuizController getQuizController() {
		return quizController;
	}

	public GoalsController getGoalsController() {
		return goalsController;
	}

	public void setGoalsController(GoalsController goalsController) {
		this.goalsController = goalsController;
	}

	public void setQuizController(QuizController quizController) {
		this.quizController = quizController;
	}

	public void startWithSelectedLblPanelBar(String selectedLblPanelBar) {
		if (selectedLblPanelBar.equals("Courses")) {
			mainScreen.getCoursesOnScreen();
			start();

		} else if (selectedLblPanelBar.equals("Quizzes")) {
			mainScreen.getQuizOnScreen();
			start();
		}

		else {
			mainScreen.dispose();
			goalsController.start();
		}

	}

	public void setCourseName(String courseName) {

		this.courseName = courseName;

	}

	public String getCourseName() {

		return courseName;
	}

	public void goToQuiz(int courseId, String courseName) {
		quizController.setCourseId(courseId);
		quizController.setCourseName(courseName);
		int studentId = Session.getInstance().getCurrentUser().getID();
		boolean studentDidQuiz = didStudentDoQuiz(courseId, studentId);
		if (!studentDidQuiz) {
			quizController.start();
			mainScreen.dispose();
		} else {
			mainScreen.displayMessage("You can't repeat the quiz for this course.");
		}

	}

	private boolean didStudentDoQuiz(int courseId, int studentId) {
		boolean result = false;

		try {
			result = quizDao.didStudentDoQuiz(courseId, studentId);
		} catch (Exception e) {
			mainScreen.displayMessage("Failed to check if student already did the quiz. " + e.getMessage());
		}
		return result;
	}

	public void startCourse(String selectedCourseName) {
		Student student = null;
		try {
			student = Session.getInstance().getCurrentUser();
		} catch (Exception e1) {

			e1.getMessage();
		}
		DisplayStudentDetails displayFromSession = new DisplayStudentDetails();
		displayFromSession.displaylblUser(student.getFirstName(), student.getLastName(),
				courseScreen.getPanelBar().getLblHelloFirstAndLastName());
		courseScreen.setCourseNameToToolBar(selectedCourseName);
		courseScreen.setCourseName(selectedCourseName);
		courseScreen.setVisible(true);
		courseScreen.setDefaultPage();
		mainScreen.dispose();

	}

	public void startGoalsScreen() {
		mainScreen.dispose();
		goalsController.start();

	}

}
