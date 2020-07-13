package view.screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.MainScreenController;
import view.utilities.StateOfMainScreen;

public class MainScreen extends JFrame implements ActionListener {
	private static final long serialVersionUID = 8973991235867304616L;

	Color c = new Color(171, 240, 250);
	Color c2 = new Color(169, 192, 237);
	Color c3 = new Color(240, 240, 240);

	private JPanel panelBar;
	private JPanel panelImage;
	private JLabel lblImage;
	JLabel lblHelloFirstAndLastName;
	private JLabel lblClickLogOut;
	private JLabel lblClickCourses;
	private JLabel lblClickQuizzes;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblClickGoals;
	private JSeparator separator_2;
	private JPanel contentPane;
	private MainScreenController mainScreenController = null;
	private JButton btnCChoise;
	private JButton btnJavaChoise;
	private JButton btnDataStructuresChoise;
	private JButton btnSqlChoise;
	StateOfMainScreen screenState;
	private JLabel lblCScore;
	private JLabel lblJavaGrade;
	private JLabel lblDataStructuresGrade;
	private JLabel lblSqlGrade;

	public MainScreen() {
		setTitle("Choose course !");
		// panelImage & lblImage
		ImageIcon image_icon = new ImageIcon(getClass().getResource("/view/images/lala.png"));
		Image image = image_icon.getImage();
		Image fixedImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		image_icon = new ImageIcon(fixedImage);
		lblImage = new JLabel();
		panelImage = new JPanel();
		panelImage.setSize(120, 120);
		lblImage.setIcon(image_icon);
		lblImage.setBounds(10, 11, 46, 14);
		lblImage.setSize(120, 120);
		panelImage.add(lblImage);
		panelImage.setBounds(10, 13, 143, 138);
		panelImage.setLayout(null);
		panelImage.setBackground(c2);

		// lblhellousername
		lblHelloFirstAndLastName = new JLabel("New label");
		lblHelloFirstAndLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloFirstAndLastName.setBounds(20, 160, 133, 14);

		// lblClickLogOut
		lblClickLogOut = new JLabel("Log Out");
		lblClickLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClickLogOut.setBounds(61, 185, 46, 14);

		// lblClickCourses
		lblClickCourses = new JLabel("Courses");
		lblClickCourses.putClientProperty("courses", lblClickCourses);
		lblClickCourses.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClickCourses.setBounds(61, 274, 70, 14);
		lblClickCourses.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// lblClickQuizzes
		lblClickQuizzes = new JLabel("Quizzes");
		lblClickQuizzes.putClientProperty("Quizzes", lblClickQuizzes);
		lblClickQuizzes.getClientProperty("Quizzes").getClass().getName();
		lblClickQuizzes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblClickQuizzes.setBounds(61, 311, 70, 14);
		lblClickQuizzes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// lblClickGoals
		lblClickGoals = new JLabel("Goals");
		lblClickGoals.setBounds(61, 349, 46, 14);
		lblClickGoals.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// separators
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 298, 143, 2);
		separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(10, 336, 143, 2);
		separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(10, 374, 143, 2);
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(151, 44, 296, 2);

		// panelBar
		panelBar = new JPanel();
		panelBar.setLayout(null);
		panelBar.setBounds(0, 0, 183, 1137);
		panelBar.add(lblHelloFirstAndLastName);
		panelBar.add(panelImage);
		panelBar.add(lblClickLogOut);
		panelBar.add(lblClickCourses);
		panelBar.add(lblClickQuizzes);
		panelBar.add(separator);
		panelBar.add(separator_1);
		panelBar.add(lblClickGoals);
		panelBar.add(separator_2);
		panelBar.setBackground(c2);

		btnCChoise = new JButton("C");
		btnCChoise.setBackground(UIManager.getColor("Button.background"));
		btnCChoise.putClientProperty("tag_course_id", "1");
		btnCChoise.putClientProperty("tag_course_name", "C");
		btnCChoise.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCChoise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCChoise.setBounds(273, 84, 213, 198);

		btnJavaChoise = new JButton("JAVA");
		btnJavaChoise.putClientProperty("tag_course_id", "2");
		btnJavaChoise.putClientProperty("tag_course_name", "JAVA");
		btnJavaChoise.setBackground(UIManager.getColor("Button.background"));
		btnJavaChoise.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnJavaChoise.setBounds(632, 84, 213, 198);

		btnDataStructuresChoise = new JButton("DATA STRUCTURES");
		btnDataStructuresChoise.putClientProperty("tag_course_id", "3");
		btnDataStructuresChoise.putClientProperty("tag_course_name", "DATA");
		btnDataStructuresChoise.setBackground(UIManager.getColor("Button.background"));
		btnDataStructuresChoise.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDataStructuresChoise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDataStructuresChoise.setBounds(273, 378, 213, 198);

		btnSqlChoise = new JButton("SQL");
		btnSqlChoise.putClientProperty("tag_course_id", "4");
		btnSqlChoise.putClientProperty("tag_course_name", "SQL");
		btnSqlChoise.setBackground(UIManager.getColor("Button.background"));
		btnSqlChoise.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSqlChoise.setBounds(632, 378, 213, 198);

		lblClickLogOut.addMouseListener(new MouseListener() {

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

		lblClickCourses.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				getCoursesOnScreen();
			}

			@Override
			public void mouseExited(MouseEvent e) {

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

		lblClickQuizzes.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				setTitle("Choose Course!");
				getQuizOnScreen();

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

		lblClickGoals.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				mainScreenController.startGoalsScreen();

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

		btnCChoise.addActionListener(this);
		btnJavaChoise.addActionListener(this);
		btnDataStructuresChoise.addActionListener(this);
		btnSqlChoise.addActionListener(this);

		// maincontent
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1000, 800);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(panelBar);
		contentPane.setBackground(c);
		contentPane.add(btnCChoise);
		contentPane.add(btnJavaChoise);
		contentPane.add(btnSqlChoise);
		contentPane.add(btnDataStructuresChoise);

		lblCScore = new JLabel("your grade is: ");
		lblCScore.setVisible(false);
		lblCScore.setBounds(288, 299, 213, 38);
		contentPane.add(lblCScore);

		lblJavaGrade = new JLabel("your grade is: ");
		lblJavaGrade.setVisible(false);
		lblJavaGrade.setBounds(632, 317, 213, 38);
		contentPane.add(lblJavaGrade);

		lblDataStructuresGrade = new JLabel("your grade is: ");
		lblDataStructuresGrade.setVisible(false);
		lblDataStructuresGrade.setBounds(288, 614, 213, 38);
		contentPane.add(lblDataStructuresGrade);

		lblSqlGrade = new JLabel("your grade is: ");
		lblSqlGrade.setVisible(false);
		lblSqlGrade.setBounds(632, 614, 213, 38);
		contentPane.add(lblSqlGrade);

	}

	public MainScreenController getMainScreenController() {
		return mainScreenController;
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

	public JLabel getLblHelloFirstAndLastName() {
		return lblHelloFirstAndLastName;
	}

	public void setLblHelloFirstAndLastName(JLabel lblHelloFirstAndLastName) {
		this.lblHelloFirstAndLastName = lblHelloFirstAndLastName;
	}

	public JLabel getLblClickCourses() {
		return lblClickCourses;
	}

	public void setLblClickCourses(JLabel lblClickCourses) {
		this.lblClickCourses = lblClickCourses;
	}

	public void getCoursesOnScreen() {
		setTitle("Choose course !");
		setScreenState(StateOfMainScreen.CHOOSE_LEARN_COURSE);
		lblClickCourses.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClickQuizzes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClickGoals.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnCChoise.setText("C");
		btnJavaChoise.setText("JAVA");
		btnDataStructuresChoise.setText("Data Structures");
		btnSqlChoise.setText("SQL");
	}

	public void getQuizOnScreen() {
		setScreenState(StateOfMainScreen.CHOOSE_QUIZ);
		// mainScreenController.checkIfTheQuizIsAlreadyDone();
		lblClickCourses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClickQuizzes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClickGoals.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCChoise.setText("C Quiz");
		btnJavaChoise.setText("JAVA Quiz");
		btnDataStructuresChoise.setText("Data Structures Quiz");
		btnSqlChoise.setText("SQL Quiz");
	}

	public void getGoalsOnScreen() {
//		lblClickCourses.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblClickQuizzes.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		lblClickGoals.setFont(new Font("Tahoma", Font.BOLD, 14));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		int courseId = Integer.valueOf((String) button.getClientProperty("tag_course_id"));
		String courseName = (String) button.getClientProperty("tag_course_name");
		if (getScreenState() == StateOfMainScreen.CHOOSE_QUIZ) {

			mainScreenController.goToQuiz(courseId, courseName);

		} else {
			mainScreenController.startCourse(courseName);
		}
		// mainScreenController.setCourseName(button.getText());
		// selectedCourseName=button.getText();
		// mainScreenController.startCourseOrQuiz(selectedCourseName);

	}

	public StateOfMainScreen getScreenState() {
		return screenState;
	}

	public void setScreenState(StateOfMainScreen screenState) {
		this.screenState = screenState;
	}

	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);

	}

}
