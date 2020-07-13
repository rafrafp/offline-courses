package model.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import model.Constants;
import model.Constants.CoursesEnum;
import model.dao.QuizDao;
import model.transferObjects.Answer;
import model.transferObjects.Question;
import model.transferObjects.Quiz;

public class QuizDaoImplForSqlite implements QuizDao {

	private Connection connection = null;
	private PreparedStatement statment = null;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public int insertQuizWithItsQuestionsAndAnswers(Quiz quiz) throws Exception {

		insertQuizToDb(quiz);
		int quizId = getGreatestQuizIdFromDb();
		insertQuizQuestionsToDb(quizId, quiz);

		return quizId;
	}

	private void insertQuizQuestionsToDb(int quizId, Quiz quiz) throws Exception {

		try {
			for (Question question : quiz.getQuestions()) {

				String sql = "INSERT INTO quizzes_to_questions (fk_quiz_id, fk_quetion_id, student_chosen_answer_id) "
						+ "VALUES (?,?,?)";
				statment = connection.prepareStatement(sql);
				statment.setInt(1, quizId);
				statment.setInt(2, question.getID());
				statment.setInt(3, -1);
				statment.executeUpdate();
			}
		} catch (SQLException e) {
			throw new Exception("Failed to insert student to db. " + e.getMessage());
		} finally {

			if (statment != null) {
				statment.close();
			}
		}

	}

	public void updateStudentChosenAnswerId(int quizId, int questionId, int studentChosenAnswerId) throws Exception {

		try {

			String sql = "UPDATE quizzes_to_questions SET student_chosen_answer_id=? "
					+ "WHERE fk_quiz_id=? AND fk_quetion_id=?";
			statment = connection.prepareStatement(sql);
			statment.setInt(1, studentChosenAnswerId);
			statment.setInt(2, quizId);
			statment.setInt(3, questionId);
			statment.executeUpdate();

		} catch (SQLException e) {
			throw new Exception("Failed to Update student chosen answer in db. " + e.getMessage());
		} finally {

			if (statment != null) {
				statment.close();
			}
		}

	}

	private void insertQuizToDb(Quiz quiz) throws Exception {

		try {
			String sql = "INSERT INTO generated_quizzes(name, start_quiz_time, end_quiz_time,grade,fk_student_id,fk_lesson_category_id) "
					+ "VALUES (?,?,?,?,?,?)";
			statment = connection.prepareStatement(sql);
			statment.setString(1, quiz.getName());
			if (quiz.getStartTime() != null) {
				java.sql.Date sqlStartDate = new java.sql.Date(quiz.getStartTime().getTime());
				statment.setDate(2, sqlStartDate);
			} else {
				statment.setDate(2, null);
			}

			if (quiz.getEndTime() != null) {
				java.sql.Date sqlEndDate = new java.sql.Date(quiz.getEndTime().getTime());
				statment.setDate(3, sqlEndDate);
			} else {
				statment.setDate(3, null);
			}
			statment.setInt(4, quiz.getGrade());
			statment.setInt(5, quiz.getStudentId());
			statment.setInt(6, quiz.getCourseId());

			statment.executeUpdate();

		} catch (SQLException e) {
			throw new Exception("Failed to insert student to db. " + e.getMessage());
		} finally {

			if (statment != null) {
				statment.close();
			}
		}

	}

	private int getGreatestQuizIdFromDb() throws Exception {
		ResultSet resultSet = null;
		try {
			int result = 0;
			String sql = "SELECT MAX(id) as max_id FROM generated_quizzes";

			statment = connection.prepareStatement(sql);
			resultSet = statment.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt("max_id");
			}

			return result;

		} catch (Exception e) {
			throw new Exception("Failed to fill quiz from db. " + e.getMessage());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statment != null) {
				statment.close();
			}
		}

	}

	public int getStudentChosenAnswerId(int quizId, int questionId) throws Exception {
		ResultSet resultSet = null;
		try {
			int result = 0;
			String sql = "SELECT student_chosen_answer_id FROM quizzes_to_questions "
					+ "WHERE fk_quiz_id=? AND fk_quetion_id=?";

			statment = connection.prepareStatement(sql);
			statment.setInt(1, quizId);
			statment.setInt(2, questionId);
			resultSet = statment.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt("student_chosen_answer_id");
			}

			return result;

		} catch (Exception e) {
			throw new Exception("Failed to get student chosen answer id from db. " + e.getMessage());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statment != null) {
				statment.close();
			}
		}

	}

	public void fillQuizWithQuestionsAndTheirAnswers(Quiz quiz, String courseName, int numOfQuestionsInQuiz)
			throws Exception {
		ResultSet resultSet = null;
		try {

			String sql = "SELECT questions_pool.id AS question_id,questions_pool.text AS question_text,questions_pool.fk_right_answer_id,answers.id AS answer_id,answers.text AS answer_text "
					+ "FROM questions_pool  INNER JOIN lessons_categories "
					+ "ON questions_pool.fk_category_id=lessons_categories.id " + "INNER JOIN answers "
					+ "ON answers.fk_question_id=questions_pool.id " + "WHERE lessons_categories.name=?";

			statment = connection.prepareStatement(sql);
			statment.setString(1, courseName);
			resultSet = statment.executeQuery();
			int countQuestionsFromPool = 0;
			ArrayList<Question> questions = new ArrayList<Question>();
			int lastQuestionId = -1;
			Question question = null;
			boolean questionsExist = false;
			while (resultSet.next()) {
				questionsExist = true;
				if (lastQuestionId != resultSet.getInt("question_id")) {
					if (lastQuestionId != -1) {
						questions.add(question);
					}
					question = new Question();
					countQuestionsFromPool++;
					question.setID(resultSet.getInt("question_id"));
					question.setText(resultSet.getString("question_text"));
					question.setRightAnswerID(resultSet.getInt("fk_right_answer_id"));

				}

				Answer answer = new Answer();
				answer.setId(resultSet.getInt("answer_id"));
				answer.setText(resultSet.getString("answer_text"));
				question.AddAnswer(answer);

				lastQuestionId = resultSet.getInt("question_id");
			}

			if (questionsExist) {
				questions.add(question);
			}

			int numOfQuestionsToRemove = countQuestionsFromPool - numOfQuestionsInQuiz;
			removeQuestionsAtRandom(questions, numOfQuestionsToRemove);

			quiz.setQuestions(questions);

		} catch (Exception e) {
			throw new Exception("Failed to fill quiz from db. " + e.getMessage());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statment != null) {
				statment.close();
			}
		}

	}

	private void removeQuestionsAtRandom(ArrayList<Question> questions, int numOfQuestionsToRemove) {
		Random rand = new Random();
		int removedQuestions = 0;

		while (removedQuestions < numOfQuestionsToRemove) {
			int randomIndex = rand.nextInt(questions.size() - 1);
			Collections.shuffle(questions, new Random(questions.size() - 1));
			questions.remove(randomIndex);
			removedQuestions++;

		}

	}

	// private static Connection connectToDb() throws Exception{
	// try {
	// String path=Main.class.getClassLoader().getResource("").getPath();
	// String fullPath=URLDecoder.decode(path,"UTF-8");
	// fullPath=fullPath.substring(1);
	// String dbPath="jdbc:sqlite:"+fullPath+"offline_courses.db";
	// DbHandler dbHandler=new DbHandler();
	// dbHandler.setConnectionString(dbPath);
	// return dbHandler.connect();
	// }
	//
	// catch(Exception e){
	// throw new Exception("ERROR: Failed to connect to DB. "+e.getMessage());
	// }
	// }

	@Override
	public void updateQuizAsFinished(int quizId, Date timeFinished, int grade) throws Exception {
		try {

			String sql = "UPDATE generated_quizzes SET end_quiz_time=?, grade=? " + "WHERE id=?";
			statment = connection.prepareStatement(sql);

			java.sql.Date sqlFinishedDate = new java.sql.Date(timeFinished.getTime());
			statment.setDate(1, sqlFinishedDate);
			statment.setInt(2, grade);
			statment.setInt(3, quizId);
			statment.executeUpdate();

		} catch (SQLException e) {
			throw new Exception("Failed to Update finished quiz in db. " + e.getMessage());
		} finally {

			if (statment != null) {
				statment.close();
			}
		}

	}

	@Override
	public int getQuizGrade(CoursesEnum course, int studentId) throws Exception {
		int result = -1;
		ResultSet resultSet = null;

		String courseName = Constants.courseToCourseName.get(course);
		try {
			String sql = "SELECT generated_quizzes.grade as grade "
					+ "FROM generated_quizzes  INNER JOIN  lessons_categories "
					+ "ON generated_quizzes.fk_lesson_category_id=lessons_categories.id "
					+ "WHERE lessons_categories.name=? AND generated_quizzes.fk_student_id=?";

			statment = connection.prepareStatement(sql);
			statment.setString(1, courseName);
			statment.setInt(2, studentId);
			resultSet = statment.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt("grade");
			} else {
				result = -1;
			}

			return result;

		} catch (Exception e) {
			throw new Exception("Failed to get quiz grade from db. " + e.getMessage());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statment != null) {
				statment.close();
			}

		}

	}

	@Override
	public boolean didStudentDoQuiz(int courseId, int studentId) throws Exception {
		boolean result = false;
		ResultSet resultSet = null;

		try {
			String sql = "SELECT id " + "FROM generated_quizzes " + "WHERE fk_lesson_category_id=? AND fk_student_id=?";

			statment = connection.prepareStatement(sql);
			statment.setInt(1, courseId);
			statment.setInt(2, studentId);
			resultSet = statment.executeQuery();
			if (resultSet.next()) {
				result = true;
			}

			return result;

		} catch (Exception e) {
			throw new Exception("Failed to check if quiz exists in db. " + e.getMessage());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statment != null) {
				statment.close();
			}

		}

	}

	@Override
	public Quiz getQuiz(int courseId, int studentId) throws Exception {

		Quiz quiz = null;
		ResultSet resultSet = null;

		try {
			String sql = "SELECT generated_quizzes.id AS id," + "generated_quizzes.name AS name,"
					+ "generated_quizzes.start_quiz_time AS start_quiz_time,"
					+ "generated_quizzes.end_quiz_time AS end_quiz_time," + "generated_quizzes.grade AS grade,"
					+ "generated_quizzes.fk_student_id AS fk_student_id,"
					+ "generated_quizzes.fk_lesson_category_id AS fk_lesson_category_id, "
					+ "lessons_categories.name AS course_name "
					+ "FROM generated_quizzes INNER JOIN lessons_categories "
					+ "ON generated_quizzes.fk_lesson_category_id=lessons_categories.id "
					+ "WHERE fk_student_id=? AND fk_lesson_category_id=?";

			statment = connection.prepareStatement(sql);
			statment.setInt(1, studentId);
			statment.setInt(2, courseId);
			resultSet = statment.executeQuery();
			if (resultSet.next()) {
				quiz = new Quiz();
				quiz.setID(resultSet.getInt("id"));
				quiz.setName(resultSet.getString("name"));
				java.sql.Date sqlStartDate = resultSet.getDate("start_quiz_time");
				if (sqlStartDate != null) {
					Date startQuizTime = new Date(sqlStartDate.getTime());
					quiz.setStartTime(startQuizTime);

				} else {
					quiz.setStartTime(null);
				}
				java.sql.Date sqlEndTime = resultSet.getDate("end_quiz_time");
				if (sqlEndTime != null) {
					Date endQuizTime = new Date(sqlEndTime.getTime());
					quiz.setEndTime(endQuizTime);

				} else {
					quiz.setEndTime(null);
				}

				quiz.setGrade(resultSet.getInt("grade"));
				quiz.setStudentId(resultSet.getInt("fk_student_id"));
				quiz.setCourseName(resultSet.getString("course_name"));
			}

			return quiz;

		} catch (Exception e) {
			throw new Exception("Failed to get quiz from db. " + e.getMessage());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statment != null) {
				statment.close();
			}

		}

	}

	@Override
	public List<Question> getQuestionsOfQuiz(int quizId) throws Exception {

		ArrayList<Question> questions = new ArrayList<Question>();
		ResultSet resultSet = null;

		try {
			String sql = "SELECT questions_pool.id AS id, " + "questions_pool.text AS text, "
					+ "questions_pool.fk_right_answer_id AS right_answer_id "
					+ "FROM quizzes_to_questions INNER JOIN questions_pool "
					+ "ON quizzes_to_questions.fk_quetion_id=questions_pool.id "
					+ "WHERE quizzes_to_questions.fk_quiz_id=?";

			statment = connection.prepareStatement(sql);
			statment.setInt(1, quizId);
			resultSet = statment.executeQuery();
			while (resultSet.next()) {
				Question question = new Question();
				question.setID(resultSet.getInt("id"));
				question.setText(resultSet.getString("text"));
				question.setRightAnswerID(resultSet.getInt("right_answer_id"));
				questions.add(question);
			}

			return questions;

		} catch (Exception e) {
			throw new Exception("Failed to get questions from db for quizid=" + quizId + ". " + e.getMessage());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statment != null) {
				statment.close();
			}

		}
	}

	@Override
	public List<Answer> getOptionalAnswers(int questionId) throws Exception {

		ArrayList<Answer> answers = new ArrayList<Answer>();
		ResultSet resultSet = null;

		try {
			String sql = "SELECT id,text " + "FROM answers " + "WHERE fk_question_id=?";

			statment = connection.prepareStatement(sql);
			statment.setInt(1, questionId);
			resultSet = statment.executeQuery();
			while (resultSet.next()) {
				Answer answer = new Answer();
				answer.setId(resultSet.getInt("id"));
				answer.setText(resultSet.getString("text"));
				answers.add(answer);
			}

			return answers;

		} catch (Exception e) {
			throw new Exception("Failed to get answers from db for questionId=" + questionId + ". " + e.getMessage());
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statment != null) {
				statment.close();
			}

		}
	}

	@Override
	public void removeQuiz(int quizId) throws Exception {
		ResultSet resultSet = null;
		try {
			String sql = "DELETE FROM generated_quizzes WHERE id=?";

			statment = connection.prepareStatement(sql);
			statment.setInt(1, quizId);
			statment.executeUpdate();
			// resultSet=statment.executeQuery();
		} catch (Exception e) {
			throw new Exception("Failed to delete quiz from generated-quizzes. " + e.getMessage());
		}

		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statment != null) {
				statment.close();
			}

		}

	}

}