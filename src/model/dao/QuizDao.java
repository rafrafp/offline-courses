package model.dao;

import java.util.Date;
import java.util.List;

import model.Constants;
import model.transferObjects.Answer;
import model.transferObjects.Question;
import model.transferObjects.Quiz;

public interface QuizDao {

	final int NUM_OF_QUESTIONS=10;
	/*
	 * returns: generated Quiz
	 */


	void updateStudentChosenAnswerId(int quizId,int questionId,int studentChosenAnswerId) throws Exception ;

	void updateQuizAsFinished(int quizId, Date timeFinished, int grade) throws Exception;

	void fillQuizWithQuestionsAndTheirAnswers(Quiz quiz, String courseName, int numOfQuestionsInQuiz) throws Exception;

	int insertQuizWithItsQuestionsAndAnswers(Quiz quiz) throws Exception;

	int getStudentChosenAnswerId(int quizId,int questionId) throws Exception;

	int getQuizGrade(Constants.CoursesEnum course,int studentId) throws Exception;

	boolean didStudentDoQuiz(int courseId,int studentId) throws Exception;

	Quiz getQuiz(int courseId,int studentId) throws Exception;

	List<Question> getQuestionsOfQuiz(int quizId) throws Exception;
	
	public List<Answer> getOptionalAnswers(int questionId) throws Exception;

	void removeQuiz(int quizId) throws Exception;
}
