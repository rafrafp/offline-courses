package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Constants;
import model.Constants.MedalsEnum;
import model.Session;
import model.dao.QuizDao;
import model.transferObjects.Answer;
import model.transferObjects.Question;
import model.transferObjects.Quiz;
import model.transferObjects.Student;
import view.screens.OutOfQuizDialog;
import view.screens.SelectCategoryToSolveQuizScreen;
import view.screens.SolvedQuizScreen;
import view.utilities.DisplayStudentDetails;
import view.utilities.StateForSolvedQuiz;

public class SolvedQuizController extends Controller {

	private MainScreenController mainScreenController =null;
	private SolvedQuizScreen solvedQuizScreen=null;
	private OutOfQuizDialog outOfQuizDialog=null;
	private QuizDao quizDao=null;
	private int courseId=-1;
	private String courseName=null;
	int questionIndex=0;
	private Quiz quiz = null;
	private int[] chosenAnswerForEachQuestion=null;
	private StateForSolvedQuiz statmentFromWhereSolvedCame;
	private SelectCategoryToSolveQuizScreen selectCategoryToSolvedQuizzesScreen;
	private QuizController quizController=null;
	ArrayList<Quiz>quizzes;
	public SolvedQuizController() {
	}

	@Override
	public void start() {

		questionIndex=0;
		Student student=Session.getInstance().getCurrentUser();
		student=Session.getInstance().getCurrentUser();
		DisplayStudentDetails displayStudentDetails=new DisplayStudentDetails();
		displayStudentDetails.displaylblUser(student.getFirstName(), student.getLastName(), solvedQuizScreen.getPanelbar().getLblHelloFirstAndLastName());	
		
		if(didStudentDoQuiz(courseId, student.getID())) {
		try {
			displayMedal();
			displayQuestionScreen(true);
			selectCategoryToSolvedQuizzesScreen.dispose();


		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}




	public ArrayList<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(ArrayList<Quiz> quizzes) {
		this.quizzes = quizzes;
	}

	private void displayMedal() {
		try {

			int grade=quiz.getGrade();
			Constants.MedalsEnum medal=calcMedalForGrade(grade);
			solvedQuizScreen.displayMedalAndTheText(medal);
			

		} catch (Exception e) {
			solvedQuizScreen.showError("Failed to get grade. "+e.getMessage());
		}
	}




	public QuizController getQuizController() {
		return quizController;
	}

	public void setQuizController(QuizController quizController) {
		this.quizController = quizController;
	}

	public SolvedQuizScreen getSolvedQuizScreen() {
		return solvedQuizScreen;
	}

	public void setSolvedQuizScreen(SolvedQuizScreen solvedQuizScreen) {
		this.solvedQuizScreen = solvedQuizScreen;
	}

	public void logOut() {
		solvedQuizScreen.dispose();
		selectCategoryToSolvedQuizzesScreen.dispose();
		mainScreenController.logOut();

	}

	public MainScreenController getMainScreenController() {
		return mainScreenController;
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}


	public void goToSelectedScreen(String selectedLblPanelBar) {
		solvedQuizScreen.dispose();
		selectCategoryToSolvedQuizzesScreen.dispose();
		mainScreenController.startWithSelectedLblPanelBar(selectedLblPanelBar);


	}

	//	public void generateQuiz() throws Exception {
	//		try {
	//
	//			quiz=generateAndInsertQuizToDb(courseId,courseName,5,Session.getInstance().getCurrentUser().getID());
	//
	//			chosenAnswerForEachQuestion=new int[QuizDao.NUM_OF_QUESTIONS];
	//
	//			for(int i=0;i<QuizDao.NUM_OF_QUESTIONS;i++) {
	//				chosenAnswerForEachQuestion[i]=-1;
	//			}
	//
	//		} catch (Exception e) {
	//			throw e;
	//		}
	//	}

	public void displayQuestionScreen(boolean isQuizStartedNow) throws Exception {
		try {
			if(isQuizStartedNow) {
				questionIndex=0;
				solvedQuizScreen.hidetheLastRightOrWronglblImage();
				solvedQuizScreen.enableBtnPrevQuestion(false);
				solvedQuizScreen.enableBtnNextQuestion(true);
			}
			solvedQuizScreen.disableAllOptionalAnsers();
			SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss ");
			String dateTimeStr=formatter.format(quiz.getStartTime());  
			String DateEndStr=formatter.format(quiz.getEndTime());
			String quizName="<html>"+quiz.getCourseName()  +" Quiz"+"<br/"+"Started At "+dateTimeStr + "<br/"+"Ended at "+DateEndStr+"<html/>";
			solvedQuizScreen.displayQuizName(quizName);
			int sumOfRightAnswer=Math.round((float)quiz.getGrade()/100*QuizDao.NUM_OF_QUESTIONS);
			solvedQuizScreen.displayQuizSummary(quiz.getGrade(),QuizDao.NUM_OF_QUESTIONS,sumOfRightAnswer);
			Question question = quiz.getQuestions().get(questionIndex);
			solvedQuizScreen.displayQuestion(questionIndex+1+") " + question.getText());
			solvedQuizScreen.displayOptionalAnswer(1, question.getAnswers().get(0).getText());
			solvedQuizScreen.displayOptionalAnswer(2, question.getAnswers().get(1).getText());
			solvedQuizScreen.displayOptionalAnswer(3, question.getAnswers().get(2).getText());
			solvedQuizScreen.displayOptionalAnswer(4, question.getAnswers().get(3).getText());


			int questionId=question.getID();
			int studentChosenAnswer=quizDao.getStudentChosenAnswerId(quiz.getID(), questionId);
			if(studentChosenAnswer!=-1) {
				solvedQuizScreen.displayDidNotAnswerQuestion("");
				solvedQuizScreen.showChosenAnswer(studentChosenAnswer);
				solvedQuizScreen.showRightAnswer(question.getRightAnswerID());
				if(studentChosenAnswer!=question.getRightAnswerID()) {
					solvedQuizScreen.showifItsWrongAnswer(question.getRightAnswerID());
					solvedQuizScreen.showXnextToWrongChosenAnswer(studentChosenAnswer);
				}
			}
			if(studentChosenAnswer==-1){
				solvedQuizScreen.resetChosenAnswers();
				solvedQuizScreen.showifItsWrongAnswer(question.getRightAnswerID());
				solvedQuizScreen.displayDidNotAnswerQuestion("You did not answer this question");
			}
			//solvedQuizScreen.displayQuestionScreen();

			//		Quiz quiz=quizDao.generateAndInsertQuizToDb(mainScreenController.getCourseName());
			//		quizScreen.displayQuizName(mainScreenController.getCourseName());
			//		quizScreen.displayQuestionsAndTheirAnswers(quiz.getQuestions());
			solvedQuizScreen.setVisible(true);
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



	public SelectCategoryToSolveQuizScreen getSelectCategoryToSolvedQuizzesScreen() {
		return selectCategoryToSolvedQuizzesScreen;
	}

	public void setSelectCategoryToSolvedQuizzesScreen(
			SelectCategoryToSolveQuizScreen selectCategoryToSolvedQuizzesScreen) {
		this.selectCategoryToSolvedQuizzesScreen = selectCategoryToSolvedQuizzesScreen;
	}

	public void openDialog() {
		outOfQuizDialog.setVisible(true);


	}

	public void setChosenAnswerForQuestion(int answerIndex) {

		chosenAnswerForEachQuestion[questionIndex]=answerIndex;

	}

	public void buttonPrevQuestionClicked() throws Exception{

		if(questionIndex>0) {
			solvedQuizScreen.hidetheLastRightOrWronglblImage();
			questionIndex--;
			displayQuestionScreen(false);

		}
		if(questionIndex<=0) {
			//btnPrevQuetion.setEnabled(false);
			solvedQuizScreen.enableBtnPrevQuestion(false);
		}

		if(questionIndex<QuizDao.NUM_OF_QUESTIONS-1) {
			solvedQuizScreen.enableBtnNextQuestion(true);
		}





	}

	public void buttonNextQuestionClicked()throws Exception{
		if(questionIndex<QuizDao.NUM_OF_QUESTIONS-1) {
			solvedQuizScreen.hidetheLastRightOrWronglblImage();
			questionIndex++;

			displayQuestionScreen(false);
		}
		if(questionIndex>=QuizDao.NUM_OF_QUESTIONS-1) {
			solvedQuizScreen.enableBtnNextQuestion(false);
		}

		if(questionIndex>0) {
			solvedQuizScreen.enableBtnPrevQuestion(true);
		}



	}

	//	public void submitQuiz() throws Exception {
	//
	//		try {
	//			int quizId=quiz.getID();
	//			for(int i=0;i<QuizDao.NUM_OF_QUESTIONS;i++) {
	//
	//				int questionId=quiz.getQuestions().get(i).getID();
	//				int studentChosenAnswerId=chosenAnswerForEachQuestion[i];
	//				quizDao.updateStudentChosenAnswerId(quizId, questionId, studentChosenAnswerId);
	//			}
	//
	//			int grade=calcGrade();
	//			Date timeFinished=new Date();
	//			quizDao.updateQuizAsFinished(quizId,timeFinished,grade);
	//
	//			return ;
	//		}
	//		catch(Exception e){
	//			throw new Exception("submit quiz failed. " + e.getMessage());
	//		}
	//
	//	}

	//	private int calcGrade() {
	//		int grade=0;
	//		for(int i=0;i<QuizDao.NUM_OF_QUESTIONS;i++) {
	//			int rightAnswerId=quiz.getQuestions().get(i).getRightAnswerID();
	//			int chosenAnswerId=chosenAnswerForEachQuestion[i];
	//			grade+=(rightAnswerId==chosenAnswerId)?100/QuizDao.NUM_OF_QUESTIONS:0;
	//		}
	//
	//		return grade;
	//	}
	//	
	//	public Quiz generateAndInsertQuizToDb(int courseId,String courseName,int numOfQuestions,int studentId) throws Exception {
	//		Quiz quiz=new Quiz();
	//		quiz.setCourseId(courseId);
	//		quiz.setCourseName(courseName);
	//		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss ");
	//		Date currentDateTime = new Date(System.currentTimeMillis()); 
	//		String dateTimeStr=formatter.format(currentDateTime);  
	//		String quizName="<html>"+courseName  +" Quiz"+"<br/"+"Started At "+quiz.getEndTime() + "<br/"+"Ended at "+dateTimeStr+"<html/>";
	//		quiz.setStartTime(currentDateTime);
	//		quiz.setStudentId(studentId);
	//		quizDao.fillQuizWithQuestionsAndTheirAnswers(quiz,courseName,QuizDao.NUM_OF_QUESTIONS);
	//
	//		int quizId=quizDao.insertQuizWithItsQuestionsAndAnswers(quiz);
	//		quiz.setID(quizId);
	//
	//		return quiz;
	//	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
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

	public StateForSolvedQuiz getStatmentFromWhereSolvedCame() {
		return statmentFromWhereSolvedCame;
	}

	public void setStatmentFromWhereSolvedCame(StateForSolvedQuiz statmentFromWhereSolvedCame) {
		this.statmentFromWhereSolvedCame = statmentFromWhereSolvedCame;
	}

	
	private boolean didStudentDoQuiz(int courseId,int studentId) {
		boolean result = false;
		
		try {
			result=quizDao.didStudentDoQuiz(courseId, studentId);
		} catch (Exception e) {
			selectCategoryToSolvedQuizzesScreen.displayMessage("Failed to check if student already did the quiz. "+e.getMessage());
		}
		return result;
	}

	public void loadQuizForCourse(int courseId) {
		Student student=Session.getInstance().getCurrentUser();
		boolean didStudentDoQuiz=didStudentDoQuiz(courseId, student.getID());
		if(didStudentDoQuiz) {
		try {
			this.quiz=quizDao.getQuiz(courseId, student.getID());
		} catch (Exception e) {
			solvedQuizScreen.showError("Failed to get quiz. "+e.getMessage());
			return;
		}
		List<Question> questions=null;
		try {
			questions=quizDao.getQuestionsOfQuiz(quiz.getID());
		} catch (Exception e) {
			solvedQuizScreen.showError("Failed to get questions. "+e.getMessage());
			return;
		}
		List<Answer>answers=null;
		try {
			for(Question question:questions) {
				answers=quizDao.getOptionalAnswers(question.getID());
				for(Answer answer: answers) {
					question.AddAnswer(answer);
				}
			}
		} catch (Exception e) {
			solvedQuizScreen.showError("Failed to get optional answers. "+e.getMessage() );
			return;
		}
		this.quiz.setQuestions(questions);
		}
		
		else {
			selectCategoryToSolvedQuizzesScreen.displayMessage("You didn't do test in this course yet.");
			
			
		}
	}

	public void goBackToSelectedSolvedScreen() {
		selectCategoryToSolvedQuizzesScreen.setVisible(true);
		
	}

}
