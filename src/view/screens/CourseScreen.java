package view.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import controller.MainScreenController;
import model.dao.StudentDao;
import model.transferObjects.StringListener;
import view.utilities.PanelBar;
import view.utilities.Toolbar;
import view.utilities.textPanelPane;

public class CourseScreen extends JFrame {
	private static final long serialVersionUID = -3438840804921077892L;

	PanelBar panelBar;
	Color c = new Color(171, 240, 250);
	private Toolbar toolbar;
	private textPanelPane textPanelPane;
	private String courseName;
	private MainScreenController mainScreenController;
	private StudentDao studentDao;

	public CourseScreen() {
		setTitle("Choose lesson number");

		toolbar = new Toolbar();
		toolbar.setBounds(181, 0, 803, 37);
		textPanelPane = new textPanelPane();
		textPanelPane.setBounds(181, 37, 803, 724);
		textPanelPane.setBackground(c);
		panelBar = new PanelBar();
		panelBar.setBounds(0, 0, 183, 761);

		textPanelPane.setDefaultText("select class number");

		StringListener stringListener = new StringListener() {

			@Override
			public void textEmitted(String text, String classType) {
				textPanelPane.clearText();
				textPanelPane.appendTextFromJSON(text, classType);

			}
		};
		getContentPane().setLayout(null);
		getContentPane().add(panelBar);
		toolbar.setStringListener(stringListener);
		getContentPane().setBackground(c);
		getContentPane().add(toolbar);
		getContentPane().add(textPanelPane);
		setSize(1000, 800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelBar.getLblClickCourses().setFont(new Font("Tahoma", Font.BOLD, 14));
		panelBar.getLblClickQuizzes().setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBar.getLblClickGoals().setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBar.getLblClickLogOut().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				mainScreenController.logOut();

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

		panelBar.getLblClickCourses().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				mainScreenController.start();

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

		panelBar.getLblClickQuizzes().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				mainScreenController.startWithSelectedLblPanelBar("Quizzes");

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

		panelBar.getLblClickGoals().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				mainScreenController.startWithSelectedLblPanelBar("Goals");

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
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCourseNameToToolBar(String courseName) {
		toolbar.setClasstype(courseName);
	}

	public MainScreenController getMainScreenController() {
		return mainScreenController;
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public void setDefaultPage() {
		textPanelPane.setDefaultText("Welcome To " + courseName + " Course" + "\n" + "Choose Course Lesson Number");

	}

	public PanelBar getPanelBar() {
		return panelBar;
	}

	public void setPanelBar(PanelBar panelBar) {
		this.panelBar = panelBar;
	}

}
