package main;

import java.sql.Connection;

import controller.GoalsController;
import controller.LoginController;
import controller.MainScreenController;
import controller.MyMedalsController;
import controller.NavigationFactory;
import controller.NavigationFactoryImpl;
import controller.QuizController;
import controller.SelectedCategoryToSolveQuizController;
import controller.SignUpControllerImpl;
import controller.SolvedQuizController;
import model.dao.StudentDao;
import model.daoImpl.DbHandler;
import model.daoImpl.QuizDaoImplForSqlite;
import model.daoImpl.StudentDaoImplForSqlite;
import view.screens.CourseScreen;
import view.screens.GoalsScreen;
import view.screens.LoginScreenImpl;
import view.screens.MainScreen;
import view.screens.MyMedalsScreen;
import view.screens.OutOfQuizDialog;
import view.screens.QuizScreen;
import view.screens.SelectCategoryToSolveQuizScreen;
import view.screens.SignUpDialogImpl;
import view.screens.SolvedQuizScreen;

public class Configuration {

	public Configuration() {
		NavigationFactory navigationFactory = new NavigationFactoryImpl();
		Connection connection = null;
		try {
			connection = DbHandler.connectToDb("offline_courses.db");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// inject dependency to the object
		StudentDaoImplForSqlite studentDaoImplForSqlite = new StudentDaoImplForSqlite();
		studentDaoImplForSqlite.setConnection(connection);
		QuizDaoImplForSqlite quizDaoImplForSqlite = new QuizDaoImplForSqlite();
		quizDaoImplForSqlite.setConnection(connection);

		StudentDao studentDao = studentDaoImplForSqlite;

		SignUpControllerImpl signUpController = new SignUpControllerImpl();
		LoginController loginController = new LoginController();
		LoginScreenImpl loginScreen = new LoginScreenImpl();
		loginScreen.setLoginController(loginController);
		loginScreen.setSignUpController(signUpController);
		SignUpDialogImpl signUpDialog = new SignUpDialogImpl();

		signUpController.setLoginScreen(loginScreen);
		signUpController.setSignUpDialog(signUpDialog);
		signUpController.setStudentDao(studentDao);
		signUpController.setNavigationFactory(navigationFactory);
		signUpDialog.setSignUpController(signUpController);

		loginController.setStudentDao(studentDao);
		loginController.setLoginScreen(loginScreen);
		loginController.setSignUpDialog(signUpDialog);

		QuizController quizController = new QuizController();
		GoalsController goalsController = new GoalsController();
		GoalsScreen goalsScreen = new GoalsScreen();
		goalsController.setGoalsScreen(goalsScreen);
		MyMedalsController myMedalsController = new MyMedalsController();
		goalsScreen.setMyMedalsController(myMedalsController);
		MyMedalsScreen myMedalsScreen = new MyMedalsScreen();
		myMedalsController.setMyMedalsScreen(myMedalsScreen);
		myMedalsController.setQuizDao(quizDaoImplForSqlite);
		MainScreen mainScreen = new MainScreen();
		MainScreenController mainScreenController = new MainScreenController();
		mainScreen.setMainScreenController(mainScreenController);
		mainScreenController.setMainScreen(mainScreen);
		mainScreenController.setGoalsController(goalsController);
		mainScreenController.setLoginController(loginController);
		mainScreenController.setStudentDao(studentDao);
		mainScreenController.setQuizController(quizController);
		loginController.setNavigationFactory(navigationFactory);
		loginController.start();

		navigationFactory.addController("After Successfull Login", mainScreenController);

		QuizScreen quizScreen = new QuizScreen();
		quizController.setStudentDao(studentDao);
		quizController.setQuizScreen(quizScreen);
		quizController.setQuizDao(quizDaoImplForSqlite);
		quizController.setMainScreenController(mainScreenController);
		mainScreenController.setQuizController(quizController);
		OutOfQuizDialog outOfQuizDialog = new OutOfQuizDialog();
		quizController.setOutOfQuizDialog(outOfQuizDialog);
		outOfQuizDialog.setQuizController(quizController);
		outOfQuizDialog.setQuizScreen(quizScreen);
		quizScreen.setQuizController(quizController);

		SolvedQuizController solvedQuizController = new SolvedQuizController();
		SolvedQuizScreen solvedQuizScreen = new SolvedQuizScreen();
		solvedQuizController.setSolvedQuizScreen(solvedQuizScreen);
		solvedQuizScreen.setSolvedQuizController(solvedQuizController);
		quizController.setSolvedQuizController(solvedQuizController);
		solvedQuizScreen.setSolvedQuizController(solvedQuizController);
		solvedQuizController.setQuizDao(quizDaoImplForSqlite);
		solvedQuizController.setMainScreenController(mainScreenController);
		mainScreenController.setQuizDao(quizDaoImplForSqlite);
		goalsController.setStudentDao(studentDao);
		myMedalsController.setStudentDao(studentDao);
		goalsController.setMainScreenController(mainScreenController);
		goalsScreen.setGoalsController(goalsController);
		goalsScreen.setMainScreenController(mainScreenController);
		goalsScreen.setSolvedQuizController(solvedQuizController);
		SelectCategoryToSolveQuizScreen selectCategoryToSolvedQuizzesScreen = new SelectCategoryToSolveQuizScreen();
		selectCategoryToSolvedQuizzesScreen.setSolvedQuizController(solvedQuizController);
		solvedQuizController.setSelectCategoryToSolvedQuizzesScreen(selectCategoryToSolvedQuizzesScreen);
		goalsScreen.setSelectCategoryToSolvedQuizzesScreen(selectCategoryToSolvedQuizzesScreen);
		solvedQuizController.setQuizController(quizController);
		myMedalsScreen.setMedalsController(myMedalsController);
		myMedalsController.setMainScreenController(mainScreenController);
		myMedalsScreen.setMainScreenController(mainScreenController);
		SelectedCategoryToSolveQuizController selectedCategoryController = new SelectedCategoryToSolveQuizController();
		selectedCategoryController.setSelectCategoryScreen(selectCategoryToSolvedQuizzesScreen);
		goalsScreen.setSelectedCategoryToSolveQuizController(selectedCategoryController);
		CourseScreen courseScreen = new CourseScreen();
		mainScreenController.setCourseScreen(courseScreen);
		courseScreen.setMainScreenController(mainScreenController);
		courseScreen.setStudentDao(studentDaoImplForSqlite);
	}
}
