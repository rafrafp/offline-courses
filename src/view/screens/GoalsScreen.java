package view.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import controller.GoalsController;
import controller.MainScreenController;
import controller.MyMedalsController;
import controller.SelectedCategoryToSolveQuizController;
import controller.SolvedQuizController;
import view.utilities.PanelBar;

public class GoalsScreen extends JFrame {
	private static final long serialVersionUID = -205890556903173287L;
	
	Color c = new Color(171, 240, 250);
	private JPanel contentPane;
	private PanelBar panelBar;
	private JButton btnSolvedQuizzes;
	private JButton btnMyMedals;
	private MyMedalsController myMedalsController;
	private GoalsController goalsController;
	private MainScreenController mainScreenController;
	private SolvedQuizController solvedQuizController;
	private SelectCategoryToSolveQuizScreen selectCategoryToSolvedQuizzesScreen;
	SelectedCategoryToSolveQuizController selectedCategoryToSolveQuizController;

	public GoalsScreen() {
		setTitle("Choose Your Goal");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		setResizable(false);
		panelBar = new PanelBar();
		panelBar.getLblClickGoals().setFont(new Font("Tahoma", Font.BOLD, 14));
		panelBar.getLblClickQuizzes().setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBar.getLblClickCourses().setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane = new JPanel();
		contentPane.setBackground(c);
		contentPane.setLayout(null);
		contentPane.add(panelBar);
		setContentPane(contentPane);

		btnSolvedQuizzes = new JButton("Solved Quizzes");
		btnSolvedQuizzes.setBackground(UIManager.getColor("Button.background"));
		btnSolvedQuizzes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSolvedQuizzes.setBounds(244, 146, 242, 212);
		contentPane.add(btnSolvedQuizzes);

		btnMyMedals = new JButton("My Medals");
		btnMyMedals.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMyMedals.setBounds(643, 146, 242, 212);
		contentPane.add(btnMyMedals);

		btnMyMedals.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				myMedalsController.start();
			}
		});

		btnSolvedQuizzes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				selectCategoryToSolvedQuizzesScreen.setVisible(true);
				selectedCategoryToSolveQuizController.start();

			}
		});
		panelBar.getLblClickLogOut().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				goalsController.logOut();

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
				mainScreenController.coursesWasPressed();

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
				mainScreenController.quizzesWasPressed();

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
		;
	}

	public MyMedalsController getMyMedalsController() {
		return myMedalsController;
	}

	public void setMyMedalsController(MyMedalsController myMedalsController) {
		this.myMedalsController = myMedalsController;
	}

	public SelectCategoryToSolveQuizScreen getSelectCategoryToSolvedQuizzesScreen() {
		return selectCategoryToSolvedQuizzesScreen;
	}

	public void setSelectCategoryToSolvedQuizzesScreen(
			SelectCategoryToSolveQuizScreen selectCategoryToSolvedQuizzesScreen) {
		this.selectCategoryToSolvedQuizzesScreen = selectCategoryToSolvedQuizzesScreen;
	}

	public MainScreenController getMainScreenController() {
		return mainScreenController;
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

	public PanelBar getPanelBar() {
		return panelBar;
	}

	public void setPanelBar(PanelBar panelBar) {
		this.panelBar = panelBar;
	}

	public SelectedCategoryToSolveQuizController getSelectedCategoryToSolveQuizController() {
		return selectedCategoryToSolveQuizController;
	}

	public void setSelectedCategoryToSolveQuizController(
			SelectedCategoryToSolveQuizController selectedCategoryToSolveQuizController) {
		this.selectedCategoryToSolveQuizController = selectedCategoryToSolveQuizController;
	}

	public SolvedQuizController getSolvedQuizController() {
		return solvedQuizController;
	}

	public void setSolvedQuizController(SolvedQuizController solvedQuizController) {
		this.solvedQuizController = solvedQuizController;
	}

	public GoalsController getGoalsController() {
		return goalsController;
	}

	public void setGoalsController(GoalsController goalsController) {
		this.goalsController = goalsController;
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);

	}

}
