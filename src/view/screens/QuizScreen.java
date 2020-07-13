package view.screens;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controller.QuizController;
import view.utilities.PanelBar;
import view.utilities.RoundBorder;
import view.utilities.StateFromWhereTheOutOfQuizDialog;

public class QuizScreen extends JFrame implements MouseListener {
	private static final long serialVersionUID = -3714024570665285744L;
	private PanelBar panelBar;
	private QuizController quizController;
	public String selectedLblPanelBar;
	Color c = new Color(171, 240, 250);
	Color c2 = new Color(169, 192, 237);
	private JButton btnSubmit;
	private JButton btnStartQuiz;
	JPanel panelCardsContainer;
	JPanel panelCardBeforeQuiz;
	JPanel panelCardQuestions;
	JRadioButton rdbOptionalAnswer1;
	JRadioButton rdbOptionalAnswer2;
	JRadioButton rdbOptionalAnswer3;
	JRadioButton rdbOptionalAnswer4;
	JButton btnNextQuetion;
	JButton btnPrevQuetion;
	JButton btnSubmitQuiz;
	JTextArea textareaQuestionText;
	JLabel lblHeadLine;
	private JTextArea txtrYouCanStart;
	private JLabel lblQuizName;
	private CardLayout cardLayout = null;
	private StateFromWhereTheOutOfQuizDialog stateFromWhereTheOutOfQuizDialog;
	ButtonGroup rdbGroup;

	public QuizScreen() {

		panelCardQuestions = new JPanel();
		panelCardQuestions.setLayout(null);
		panelCardQuestions.setBackground(c);

		panelBar = new PanelBar();
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		setResizable(false);
		getContentPane().setLayout(null);

		getContentPane().add(panelBar);
		// panelCardQuestions.add(textareaQuestionText);

		;
		rdbOptionalAnswer1 = new JRadioButton("New radio button");
		rdbOptionalAnswer1.setBackground(c);
		rdbOptionalAnswer1.putClientProperty("tag_button_index", 1);
		rdbOptionalAnswer1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbOptionalAnswer1.setBounds(37, 496, 724, 23);
		panelCardQuestions.add(rdbOptionalAnswer1);

		rdbOptionalAnswer2 = new JRadioButton("New radio button");
		rdbOptionalAnswer2.setBackground(c);
		rdbOptionalAnswer2.putClientProperty("tag_button_index", 2);
		rdbOptionalAnswer2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbOptionalAnswer2.setBounds(37, 540, 724, 23);
		panelCardQuestions.add(rdbOptionalAnswer2);

		rdbOptionalAnswer3 = new JRadioButton("New radio button");
		rdbOptionalAnswer3.setBackground(c);
		rdbOptionalAnswer3.putClientProperty("tag_button_index", 3);
		rdbOptionalAnswer3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbOptionalAnswer3.setBounds(37, 590, 724, 23);
		panelCardQuestions.add(rdbOptionalAnswer3);

		rdbOptionalAnswer4 = new JRadioButton("New radio button");
		rdbOptionalAnswer4.setBackground(c);
		rdbOptionalAnswer4.putClientProperty("tag_button_index", 4);
		rdbOptionalAnswer4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbOptionalAnswer4.setBounds(37, 637, 730, 23);
		panelCardQuestions.add(rdbOptionalAnswer4);

		rdbGroup = new ButtonGroup();
		rdbGroup.add(rdbOptionalAnswer1);
		rdbGroup.add(rdbOptionalAnswer2);
		rdbGroup.add(rdbOptionalAnswer3);
		rdbGroup.add(rdbOptionalAnswer4);

		btnNextQuetion = new JButton("Next");
		btnNextQuetion.setBounds(360, 724, 89, 23);
		btnNextQuetion.setBorder(new RoundBorder());
		panelCardQuestions.add(btnNextQuetion);

		btnPrevQuetion = new JButton("Prev");
		btnPrevQuetion.setBounds(242, 724, 89, 23);
		btnPrevQuetion.setBorder(new RoundBorder());
		panelCardQuestions.add(btnPrevQuetion);

		btnSubmit = new JButton("Submit Quiz");
		btnSubmit.setBounds(532, 724, 110, 23);
		btnSubmit.setBorder(new RoundBorder());
		panelCardQuestions.add(btnSubmit);
		panelCardBeforeQuiz = new JPanel();
		panelCardBeforeQuiz.setBounds(188, 11, 500, 500);
		panelCardBeforeQuiz.setLayout(null);
		btnStartQuiz = new JButton();
		btnStartQuiz.setBorder(new RoundBorder());
		btnStartQuiz.setText("Start Quiz");
		btnStartQuiz.setBounds(225, 517, 238, 40);
		panelCardBeforeQuiz.add(btnStartQuiz);

		panelCardsContainer = new JPanel(new CardLayout());
		panelCardsContainer.setBounds(185, 0, 809, 771);
		panelCardBeforeQuiz.setBackground(c);
		panelCardsContainer.setBackground(c);

		getContentPane().add(panelCardsContainer);
		panelCardsContainer.add(panelCardBeforeQuiz, "panelCardBefpreQuiz");

		txtrYouCanStart = new JTextArea();
		txtrYouCanStart.setEditable(false);
		txtrYouCanStart.setBackground(c);
		txtrYouCanStart.setFont(new Font("Courier New", Font.BOLD, 20));
		txtrYouCanStart.setText("You can start a new quiz now. \r\nQuiz start time will be recorded.\r\n\r\n");
		txtrYouCanStart.setBounds(25, 249, 774, 85);
		panelCardBeforeQuiz.add(txtrYouCanStart);

		lblHeadLine = new JLabel("New label");
		lblHeadLine.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeadLine.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHeadLine.setBounds(54, 41, 607, 71);
		panelCardBeforeQuiz.add(lblHeadLine);

		JTextArea txtrWarningYouWill = new JTextArea();
		txtrWarningYouWill.setBackground(c);
		txtrWarningYouWill.setText(
				"Warning: \r\nyou will have only one opportunity to do the quiz for this course. \r\nAfter you submit the quiz there is no way to do another quiz.\r\n");
		txtrWarningYouWill.setForeground(Color.RED);
		txtrWarningYouWill.setFont(new Font("Courier New", Font.PLAIN, 20));
		txtrWarningYouWill.setBounds(10, 356, 799, 96);
		panelCardBeforeQuiz.add(txtrWarningYouWill);
		panelCardsContainer.add(panelCardQuestions, "panelCardQuestions");

		lblQuizName = new JLabel("quiz name");
		lblQuizName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuizName.setBounds(224, 32, 364, 53);
		panelCardQuestions.add(lblQuizName);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 310, 724, 142);
		panelCardQuestions.add(scrollPane);
		scrollPane.setBackground(c);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		// textareaQuestionText.set
		textareaQuestionText = new JTextArea();
		scrollPane.setViewportView(textareaQuestionText);
		textareaQuestionText.setCaretPosition(0);
		textareaQuestionText.setBackground(c);
		textareaQuestionText.setFont(new Font("Courier New", Font.PLAIN, 20));
		textareaQuestionText.setEditable(false);

		showPanelBeforeQuiz();
		// CardLayout cardLayout=(CardLayout)(panelCardsContainer.getLayout());
		// cardLayout.show(panelCardsContainer, "panelCardBeforequiz");

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				if (panelCardQuestions.isShowing()) {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					setStateFromWhereTheOutOfQuizDialog(StateFromWhereTheOutOfQuizDialog.EXIT_STATMENT);
					quizController.openDialog();
				}

			}

		});

		btnStartQuiz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnNextQuetion.setEnabled(true);

				try {
					quizController.generateQuiz();
					quizController.displayQuestionScreen(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}

			}
		});

		btnNextQuetion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					quizController.buttonNextQuestionClicked();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Failed to display next question. " + e1.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		btnPrevQuetion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					quizController.buttonPrevQuestionClicked();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Failed to display prev question. " + e1.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		ActionListener radiobtnActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JRadioButton radioButton = (JRadioButton) actionEvent.getSource();
				int answerIndex = Integer.valueOf((Integer) radioButton.getClientProperty("tag_button_index"));
				// chosenAnswers[questionIndex]=answerIndex;
				quizController.setChosenAnswerForQuestion(answerIndex);
			}
		};

		rdbOptionalAnswer1.addActionListener(radiobtnActionListener);
		rdbOptionalAnswer2.addActionListener(radiobtnActionListener);
		rdbOptionalAnswer3.addActionListener(radiobtnActionListener);
		rdbOptionalAnswer4.addActionListener(radiobtnActionListener);

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					quizController.submitQuiz();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Failed to submit quiz. " + e1.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		panelBar.getLblClickLogOut().addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

				// only if the quizz is started we will open the dialog
				if (panelCardQuestions.isShowing()) {
					setStateFromWhereTheOutOfQuizDialog(StateFromWhereTheOutOfQuizDialog.LOG_OUT_STATMENT);
					quizController.openDialog();
				} else {
					quizController.logOut();
				}

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

		panelBar.getLblClickCourses().addMouseListener(this);
		panelBar.getLblClickQuizzes().addMouseListener(this);
		panelBar.getLblClickGoals().addMouseListener(this);

	}

	public QuizController getQuizController() {
		return quizController;
	}

	public void setQuizController(QuizController quizController) {
		this.quizController = quizController;
	}

	public PanelBar getPanelBar() {
		return panelBar;
	}

	public JPanel getPanelCardBeforeQuiz() {
		return panelCardBeforeQuiz;
	}

	public void setPanelCardBeforeQuiz(JPanel panelCardBeforeQuiz) {
		this.panelCardBeforeQuiz = panelCardBeforeQuiz;
	}

	public void setPanelBar(PanelBar panelBar) {
		this.panelBar = panelBar;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// only if it's in the middle of the test the dialog will open up
		if (panelCardQuestions.isShowing()) {
			JLabel label = (JLabel) e.getSource();
			setStateFromWhereTheOutOfQuizDialog(StateFromWhereTheOutOfQuizDialog.ALL_OTHER_STATMENT);
			selectedLblPanelBar = label.getText();
			quizController.openDialog();
		} else {
			JLabel label = (JLabel) e.getSource();
			setStateFromWhereTheOutOfQuizDialog(StateFromWhereTheOutOfQuizDialog.ALL_OTHER_STATMENT);
			selectedLblPanelBar = label.getText();
			quizController.goToSelectedScreen(selectedLblPanelBar);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void displayQuizName(String name) {
		lblQuizName.setText(name);

	}

	public void displayHeadLineName(String name) {
		lblHeadLine.setText(name + " Quiz");

	}

	public void displayQuestion(String text) {

		StringBuilder str = new StringBuilder(text);

		// }
		for (int i = str.length() / 2; i < str.length(); i++) {
			@SuppressWarnings("deprecation")
			Character cha = new Character(str.charAt(i));

			if (cha.equals(' ')) {
				str.insert(i, '\n');
				break;
			}

		}
		textareaQuestionText.setText(text);

	}

	public void enableBtnPrevQuestion(boolean isEnabled) {
		btnPrevQuetion.setEnabled(isEnabled);
	}

	public void enableBtnNextQuestion(boolean isEnabled) {
		btnNextQuetion.setEnabled(isEnabled);
	}

	public void displayOptionalAnswer(int answerIndex, String text) {
		switch (answerIndex) {
		case 1:
			rdbOptionalAnswer1.setText(text);
			break;
		case 2:
			rdbOptionalAnswer2.setText(text);
			break;
		case 3:
			rdbOptionalAnswer3.setText(text);
			break;
		case 4:
			rdbOptionalAnswer4.setText(text);
			break;

		default:
			JOptionPane.showMessageDialog(null, "invalid answer number: " + answerIndex, "ERROR",
					JOptionPane.ERROR_MESSAGE);
			break;
		}

	}

	public void displayQuestionScreen() {

		cardLayout = (CardLayout) (panelCardsContainer.getLayout());
		cardLayout.show(panelCardsContainer, "panelCardQuestions");
		textareaQuestionText.setCaretPosition(0);
	}

	public void showPanelBeforeQuiz() {
		panelCardBeforeQuiz.setVisible(true);
		panelCardQuestions.setVisible(false);
		cardLayout = (CardLayout) (panelCardsContainer.getLayout());
		cardLayout.show(panelCardsContainer, "panelCardBeforequiz");
	}

	public StateFromWhereTheOutOfQuizDialog getStateFromWhereTheOutOfQuizDialog() {
		return stateFromWhereTheOutOfQuizDialog;
	}

	public void setStateFromWhereTheOutOfQuizDialog(StateFromWhereTheOutOfQuizDialog stateFromWhereTheOutOfQuizDialog) {
		this.stateFromWhereTheOutOfQuizDialog = stateFromWhereTheOutOfQuizDialog;
	}

	public void showChosenAnswer(int i) {
		switch (i) {
		case 1:
			rdbOptionalAnswer1.setSelected(true);
			break;
		case 2:
			rdbOptionalAnswer2.setSelected(true);
			break;
		case 3:
			rdbOptionalAnswer3.setSelected(true);
			break;
		case 4:
			rdbOptionalAnswer4.setSelected(true);
			break;

		}

	}

	public void resetChosenAnswers() {
		rdbGroup.clearSelection();

	}
}
