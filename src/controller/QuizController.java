package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Session;
import model.dao.QuizDao;
import model.transferObjects.Question;
import model.transferObjects.Quiz;
import model.transferObjects.Student;
import view.screens.OutOfQuizDialog;
import view.screens.QuizScreen;
import view.utilities.DisplayStudentDetails;

public class QuizController extends Controller {
	private SolvedQuizController solvedQuizController=null;
	private  MainScreenController mainScreenController =null;
	private  QuizScreen quizScreen=null;
	private  OutOfQuizDialog outOfQuizDialog=null;
	private QuizDao quizDao=null;
	private int courseId=-1;
	private String courseName=null;
	private int questionIndex=0;
	private Quiz quiz = null;
	private int[] chosenAnswerForEachQuestion=null;
	ArrayList<Quiz>quizzes = new ArrayList<Quiz>();


	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public QuizController() {
	}

	@Override
	public void start() {


		try {
			Student student=studentDao.getStudent(Session.getInstance().getCurrentUser().getUserName());
			DisplayStudentDetails displayFromSession=new DisplayStudentDetails();
			displayFromSession.displaylblUser(student.getFirstName(), student.getLastName(), quizScreen.getPanelBar().getLblHelloFirstAndLastName());
			quizScreen.showPanelBeforeQuiz();
			quizScreen.displayHeadLineName(courseName);
			quizScreen.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public QuizScreen getQuizScreen() {
		return quizScreen;
	}

	public void setQuizScreen(QuizScreen quizScreen) {
		this.quizScreen = quizScreen;
	}

	public void logOut() {
		quizScreen.dispose();
		mainScreenController.logOut();

	}

	public MainScreenController getMainScreenController() {
		return mainScreenController;
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}



	public  OutOfQuizDialog getOutOfQuizDialog() {
		return outOfQuizDialog;
	}

	public  void setOutOfQuizDialog(OutOfQuizDialog outOfQuizDialog) {
		this.outOfQuizDialog = outOfQuizDialog;
	}

	public void goToSelectedScreen(String selectedLblPanelBar) {
		quizScreen.dispose();
		outOfQuizDialog.dispose();
		mainScreenController.startWithSelectedLblPanelBar(selectedLblPanelBar);


	}

	public void generateQuiz() throws Exception {
		try {

			quiz=generateAndInsertQuizToDb(courseId,courseName,QuizDao.NUM_OF_QUESTIONS,Session.getInstance().getCurrentUser().getID());
			questionIndex=0;
			chosenAnswerForEachQuestion=new int[QuizDao.NUM_OF_QUESTIONS];

			for(int i=0;i<QuizDao.NUM_OF_QUESTIONS;i++) {
				chosenAnswerForEachQuestion[i]=-1;
			}

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void displayQuestionScreen(boolean isQuizStartedNow) throws Exception {
		try {

			
			quizScreen.displayQuizName(quiz.getName());
			Question question = quiz.getQuestions().get(questionIndex);
			quizScreen.displayQuestion(questionIndex+1+") " + question.getText());
			quizScreen.displayOptionalAnswer(1, question.getAnswers().get(0).getText());
			quizScreen.displayOptionalAnswer(2, question.getAnswers().get(1).getText());
			quizScreen.displayOptionalAnswer(3, question.getAnswers().get(2).getText());
			quizScreen.displayOptionalAnswer(4, question.getAnswers().get(3).getText());
			if(isQuizStartedNow) {
				quizScreen.enableBtnPrevQuestion(false);
			}
			if(chosenAnswerForEachQuestion[questionIndex]!=-1) {

				quizScreen.showChosenAnswer(chosenAnswerForEachQuestion[questionIndex]);
			}
			if(chosenAnswerForEachQuestion[questionIndex]==-1){
				quizScreen.resetChosenAnswers();
			}
			quizScreen.displayQuestionScreen();

		} catch (Exception e) {
			throw e;
		}
	}

	public QuizDao getQuizDao() {
		return quizDao;
	}
	public void setQuizDao(QuizDao quizDao) {
		this.quizDao = quizDao;
	}


	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void openDialog() {
		outOfQuizDialog.setVisible(true);


	}

	public void setChosenAnswerForQuestion(int answerIndex) {

		chosenAnswerForEachQuestion[questionIndex]=answerIndex;

	}

	public void buttonPrevQuestionClicked() throws Exception{

		if(questionIndex>0) {

			questionIndex--;
			displayQuestionScreen(false);
		}
		if(questionIndex<=0) {
			//btnPrevQuetion.setEnabled(false);
			quizScreen.enableBtnPrevQuestion(false);
		}

		if(questionIndex<QuizDao.NUM_OF_QUESTIONS) {
			quizScreen.enableBtnNextQuestion(true);
		}





	}

	public void buttonNextQuestionClicked()throws Exception{
		if(questionIndex<QuizDao.NUM_OF_QUESTIONS-1) {
			questionIndex++;
			displayQuestionScreen(false);
		}
		if(questionIndex>=QuizDao.NUM_OF_QUESTIONS-1) {
			quizScreen.enableBtnNextQuestion(false);
		}

		if(questionIndex>0) {
			quizScreen.enableBtnPrevQuestion(true);
		}



	}

	public void submitQuiz() throws Exception {

		try {
			
			int quizId=quiz.getID();
			for(int i=0;i<QuizDao.NUM_OF_QUESTIONS;i++) {

				int questionId=quiz.getQuestions().get(i).getID();
				int studentChosenAnswerId=chosenAnswerForEachQuestion[i];
				quizDao.updateStudentChosenAnswerId(quizId, questionId, studentChosenAnswerId);
			}

			int grade=calcGrade();

//			SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss ");
			Date timeFinished = new Date(System.currentTimeMillis()); 
			quiz.setEndTime(timeFinished);
			quiz.setGrade(grade);
			quizDao.updateQuizAsFinished(quizId,timeFinished,grade);
			
			quizScreen.dispose();
			
			
			solvedQuizController.setQuiz(quiz);
			solvedQuizController.setCourseId(courseId);
			solvedQuizController.start();
			
			
			
		}
		catch(Exception e){
			throw new Exception("submit quiz failed. " + e.getMessage());
		}

	}

	private int calcGrade() {
		int grade=0;
		for(int i=0;i<QuizDao.NUM_OF_QUESTIONS;i++) {
			int rightAnswerId=quiz.getQuestions().get(i).getRightAnswerID();
			int chosenAnswerId=chosenAnswerForEachQuestion[i];
			grade+=(rightAnswerId==chosenAnswerId)?100/QuizDao.NUM_OF_QUESTIONS:0;
		}

		return grade;
	}
	
	

	
	public Quiz generateAndInsertQuizToDb(int courseId,String courseName,int numOfQuestions,int studentId) throws Exception {
		Quiz quiz=new Quiz();
		quiz.setCourseId(courseId);
		quiz.setCourseName(courseName);
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss ");
		Date currentDateTime = new Date(System.currentTimeMillis()); 
		String dateTimeStr=formatter.format(currentDateTime);  
		String quizName="<html>"+courseName  +" Quiz"+"<br/"+"Started At "+dateTimeStr+"<html/>";
		quiz.setName(quizName);
		quiz.setStartTime(currentDateTime);
		quiz.setStudentId(studentId);
		quizDao.fillQuizWithQuestionsAndTheirAnswers(quiz,courseName,QuizDao.NUM_OF_QUESTIONS);

		int quizId=quizDao.insertQuizWithItsQuestionsAndAnswers(quiz);
		quiz.setID(quizId);

		return quiz;
	}

	
	
	public ArrayList<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(ArrayList<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	public SolvedQuizController getSolvedQuizController() {
		return solvedQuizController;
	}

	public void setSolvedQuizController(SolvedQuizController solvedQuizController) {
		this.solvedQuizController = solvedQuizController;
	}

	public void removeQuizFromDb() {
		try {
			quizDao.removeQuiz(quiz.getID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
