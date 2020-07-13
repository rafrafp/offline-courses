package view.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.SolvedQuizController;
import view.utilities.PanelBar;

public class SelectCategoryToSolveQuizScreen extends JFrame {
	private static final long serialVersionUID = 6350272443067656673L;
	Color c = new Color(171, 240, 250);
	private JPanel contentPane;
	PanelBar panelBar;
	JButton btnCChoice;
	JButton btnJava;
	JButton btnDataStructures;
	JButton btnSql;
	SolvedQuizController solvedQuizController;
	public String selectedLblPanelBar;

	public SelectCategoryToSolveQuizScreen() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setSize(1000, 800);
		setResizable(false);
		panelBar = new PanelBar();
		contentPane.add(panelBar);
		contentPane.setBackground(c);
		setContentPane(contentPane);
		panelBar.getLblClickGoals().setFont(new Font("Tahoma", Font.BOLD, 14));
		panelBar.getLblClickQuizzes().setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBar.getLblClickCourses().setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnCChoice = new JButton("C");
		btnCChoice.putClientProperty("tag_course_id", "1");
		btnCChoice.putClientProperty("tag_course_name", "C");
		btnCChoice.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnCChoice.setBounds(272, 111, 239, 168);
		contentPane.add(btnCChoice);

		btnJava = new JButton("JAVA");
		btnJava.putClientProperty("tag_course_id", "2");
		btnJava.putClientProperty("tag_course_name", "JAVA");
		btnJava.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnJava.setBounds(602, 111, 239, 168);
		contentPane.add(btnJava);

		btnDataStructures = new JButton("DATA STRUCTURES");
		btnDataStructures.putClientProperty("tag_course_id", "3");
		btnDataStructures.putClientProperty("tag_course_name", "DATA");
		btnDataStructures.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDataStructures.setBounds(272, 404, 239, 168);
		contentPane.add(btnDataStructures);

		btnSql = new JButton("SQL");
		btnSql.putClientProperty("tag_course_id", "4");
		btnSql.putClientProperty("tag_course_name", "SQL");
		btnSql.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSql.setBounds(602, 404, 239, 168);
		contentPane.add(btnSql);
		panelBar.getLblClickLogOut().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				solvedQuizController.logOut();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		MouseListener mouseListener = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				JLabel label = (JLabel) e.getSource();
				selectedLblPanelBar = label.getText();

				selectedLblPanelBar = label.getText();
				solvedQuizController.goToSelectedScreen(selectedLblPanelBar);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		};
		panelBar.getLblClickCourses().addMouseListener(mouseListener);
		panelBar.getLblClickQuizzes().addMouseListener(mouseListener);
		panelBar.getLblClickGoals().addMouseListener(mouseListener);

		btnCChoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				int courseId = Integer.valueOf((String) button.getClientProperty("tag_course_id"));

				solvedQuizController.loadQuizForCourse(courseId);
				solvedQuizController.setCourseId(courseId);

				// solvedQuizController.setStatmentFromWhereSolvedCame(StateForSolvedQuiz.GOALS);

				// dispose();
				solvedQuizController.start();

			}
		});

		btnJava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				int courseId = Integer.valueOf((String) button.getClientProperty("tag_course_id"));

				solvedQuizController.loadQuizForCourse(courseId);
				solvedQuizController.setCourseId(courseId);

				// solvedQuizController.setStatmentFromWhereSolvedCame(StateForSolvedQuiz.GOALS);

				// dispose();
				solvedQuizController.start();

			}
		});

		btnDataStructures.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				int courseId = Integer.valueOf((String) button.getClientProperty("tag_course_id"));

				solvedQuizController.loadQuizForCourse(courseId);
				solvedQuizController.setCourseId(courseId);

				// solvedQuizController.setStatmentFromWhereSolvedCame(StateForSolvedQuiz.GOALS);

				// dispose();
				solvedQuizController.start();

			}
		});

		btnSql.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				int courseId = Integer.valueOf((String) button.getClientProperty("tag_course_id"));

				solvedQuizController.loadQuizForCourse(courseId);
				solvedQuizController.setCourseId(courseId);

				// solvedQuizController.setStatmentFromWhereSolvedCame(StateForSolvedQuiz.GOALS);

				// dispose();
				solvedQuizController.start();

			}
		});
	}

	public SolvedQuizController getSolvedQuizController() {
		return solvedQuizController;
	}

	public void setSolvedQuizController(SolvedQuizController solvedQuizController) {
		this.solvedQuizController = solvedQuizController;
	}

	public PanelBar getPanelBar() {
		return panelBar;
	}

	public void setPanelBar(PanelBar panelBar) {
		this.panelBar = panelBar;
	}

	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);

	}

}
