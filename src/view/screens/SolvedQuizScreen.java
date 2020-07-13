package view.screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.SolvedQuizController;
import model.Constants.MedalsEnum;
import view.utilities.PanelBar;

public class SolvedQuizScreen extends JFrame {
	private static final long serialVersionUID = 8498417711569564488L;
	
	Color c = new Color(171, 240, 250);
	Color c2 = new Color(169, 192, 237);
	private JPanel contentPane;
	private PanelBar panelbar;
	private JLabel lblQuizTitle;
	private JTextArea textareaQuizSummary;
	private JTextArea textareaQuestion;
	private JRadioButton rdbOptionalAnswer1;
	private JRadioButton rdbOptionalAnswer2;
	private JRadioButton rdbOptionalAnswer3;
	private JRadioButton rdbOptionalAnswer4;
	private JButton btnNext;
	public String selectedLblPanelBar;
	private JButton btnPrev;
	private JLabel lblImageVorX1;
	private JLabel lblImageVorX2;
	private JLabel lblImageVorX3;
	private JLabel lblImageVorX4;
	private SolvedQuizController solvedQuizController = null;
	private ButtonGroup rdbGroup;
	private JLabel lblYouEarnedAMedal;
	private JLabel lblImageMedal;
	JLabel lblDidNotAnswer;
	private JLabel lblImageGoBackToSeectedSolvedQuizScreen;
	private JScrollPane scrollPane_1;

	public SolvedQuizScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		setResizable(false);
		panelbar = new PanelBar();
		contentPane = new JPanel();
		contentPane.setBackground(c);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(panelbar);

		lblQuizTitle = new JLabel("New label");
		lblQuizTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuizTitle.setBounds(332, 11, 642, 118);
		contentPane.add(lblQuizTitle);
		panelbar.getLblClickCourses().setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelbar.getLblClickQuizzes().setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelbar.getLblClickGoals().setFont(new Font("Tahoma", Font.BOLD, 14));
		textareaQuizSummary = new JTextArea();
		contentPane.add(textareaQuizSummary);
		textareaQuizSummary.setBackground(c);
		textareaQuizSummary.setEditable(false);
		textareaQuizSummary.setFont(new Font("Courier New", Font.BOLD, 20));
		textareaQuizSummary.setBounds(247, 143, 562, 80);
		// contentPane.add(textareaQuizSummary);

		rdbOptionalAnswer1 = new JRadioButton("New radio button");
		rdbOptionalAnswer1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbOptionalAnswer1.setBounds(284, 551, 562, 23);
		rdbOptionalAnswer1.setBackground(c);
		contentPane.add(rdbOptionalAnswer1);

		rdbOptionalAnswer2 = new JRadioButton("New radio button");
		rdbOptionalAnswer2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbOptionalAnswer2.setBounds(284, 591, 562, 23);
		rdbOptionalAnswer2.setBackground(c);
		contentPane.add(rdbOptionalAnswer2);

		rdbOptionalAnswer3 = new JRadioButton("New radio button");
		rdbOptionalAnswer3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbOptionalAnswer3.setBounds(284, 633, 562, 23);
		rdbOptionalAnswer3.setBackground(c);
		contentPane.add(rdbOptionalAnswer3);

		rdbOptionalAnswer4 = new JRadioButton("New radio button");
		rdbOptionalAnswer4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbOptionalAnswer4.setBounds(284, 671, 562, 23);
		rdbOptionalAnswer4.setBackground(c);
		contentPane.add(rdbOptionalAnswer4);

		rdbGroup = new ButtonGroup();
		rdbGroup.add(rdbOptionalAnswer1);
		rdbGroup.add(rdbOptionalAnswer2);
		rdbGroup.add(rdbOptionalAnswer3);
		rdbGroup.add(rdbOptionalAnswer4);

		btnNext = new JButton("Next");
		btnNext.setBounds(556, 727, 89, 23);
		contentPane.add(btnNext);

		btnPrev = new JButton("Prev");
		btnPrev.setBounds(412, 727, 89, 23);
		contentPane.add(btnPrev);

		lblImageVorX1 = new JLabel("");
		lblImageVorX1.setBounds(232, 540, 46, 50);
		contentPane.add(lblImageVorX1);

		lblImageVorX2 = new JLabel("");
		lblImageVorX2.setBounds(232, 575, 46, 35);
		contentPane.add(lblImageVorX2);

		lblImageVorX3 = new JLabel("");
		lblImageVorX3.setBounds(232, 621, 46, 40);
		contentPane.add(lblImageVorX3);

		lblImageVorX4 = new JLabel("");
		lblImageVorX4.setBounds(232, 653, 46, 50);
		contentPane.add(lblImageVorX4);

		lblYouEarnedAMedal = new JLabel("");
		lblYouEarnedAMedal.setBounds(302, 234, 203, 50);
		contentPane.add(lblYouEarnedAMedal);

		lblImageMedal = new JLabel("");
		lblImageMedal.setBounds(694, 234, 132, 90);
		contentPane.add(lblImageMedal);

		lblDidNotAnswer = new JLabel("");
		lblDidNotAnswer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDidNotAnswer.setBounds(193, 521, 491, 23);
		contentPane.add(lblDidNotAnswer);
		lblImageGoBackToSeectedSolvedQuizScreen = new JLabel("");
		lblImageGoBackToSeectedSolvedQuizScreen.setBounds(193, 27, 85, 61);
		lblImageGoBackToSeectedSolvedQuizScreen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setIconTolblImageGoBack();
		contentPane.add(lblImageGoBackToSeectedSolvedQuizScreen);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(207, 365, 767, 126);

		textareaQuestion = new JTextArea();
		scrollPane_1.setViewportView(textareaQuestion);
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		textareaQuestion.setCaretPosition(0);
		textareaQuestion.setBackground(c);
		textareaQuestion.setEditable(false);
		textareaQuestion.setFont(new Font("Courier New", Font.PLAIN, 20));
		contentPane.add(scrollPane_1);
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					solvedQuizController.buttonNextQuestionClicked();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Failed to display next question. " + e1.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		lblImageGoBackToSeectedSolvedQuizScreen.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

				dispose();
				solvedQuizController.goBackToSelectedSolvedScreen();

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

		btnPrev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					solvedQuizController.buttonPrevQuestionClicked();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Failed to display next question. " + e1.getMessage(), "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		panelbar.getLblClickLogOut().addMouseListener(new MouseListener() {

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

		panelbar.getLblClickCourses().addMouseListener(mouseListener);
		panelbar.getLblClickQuizzes().addMouseListener(mouseListener);
		panelbar.getLblClickGoals().addMouseListener(mouseListener);

	}

	private void setIconTolblImageGoBack() {
		ImageIcon image_icon = new ImageIcon(getClass().getResource("/view/images/goback.png"));
		Image image = image_icon.getImage();
		Image fixedImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		image_icon = new ImageIcon(fixedImage);
		lblImageGoBackToSeectedSolvedQuizScreen.setIcon(image_icon);

	}

	public void displayQuizName(String name) {
		lblQuizTitle.setText(name);

	}

	public void setSolvedQuizController(SolvedQuizController solvedQuizController) {
		this.solvedQuizController = solvedQuizController;

	}

	public PanelBar getPanelbar() {
		return panelbar;
	}

	public void setPanelbar(PanelBar panelbar) {
		this.panelbar = panelbar;
	}

	public void displayQuestion(String text) {
		textareaQuestion.setCaretPosition(0);
		textareaQuestion.setText(text);

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

	public void enableBtnPrevQuestion(boolean isEnabled) {
		btnPrev.setEnabled(isEnabled);

	}

	public void enableBtnNextQuestion(boolean isEnabled) {
		btnNext.setEnabled(isEnabled);

	}

	public void showifItsWrongAnswer(int i) {
		JLabel lblImage = null;
		switch (i) {
		case 1:
			lblImage = lblImageVorX1;
			lblImageVorX1.setVisible(true);
			break;
		case 2:
			lblImage = lblImageVorX2;
			lblImageVorX2.setVisible(true);
			break;
		case 3:
			lblImage = lblImageVorX3;
			lblImageVorX3.setVisible(true);
			break;
		case 4:
			lblImage = lblImageVorX4;
			lblImageVorX4.setVisible(true);
			break;
		}

		ImageIcon image_icon = new ImageIcon(getClass().getResource("/view/images/right_answer.png"));
		Image image = image_icon.getImage();
		Image fixedImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		image_icon = new ImageIcon(fixedImage);
		lblImage.setIcon(image_icon);

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

	public void showRightAnswer(int rightAnswerID) {
		JLabel lblImage = null;
		switch (rightAnswerID) {
		case 1:
			lblImage = lblImageVorX1;
			lblImageVorX1.setVisible(true);
			break;
		case 2:
			lblImage = lblImageVorX2;
			lblImageVorX2.setVisible(true);
			break;
		case 3:
			lblImage = lblImageVorX3;
			lblImageVorX3.setVisible(true);
			break;
		case 4:
			lblImage = lblImageVorX4;
			lblImageVorX4.setVisible(true);
			break;
		}

		ImageIcon image_icon = new ImageIcon(getClass().getResource("/view/images/right_answer.png"));
		Image image = image_icon.getImage();
		Image fixedImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		image_icon = new ImageIcon(fixedImage);
		lblImage.setIcon(image_icon);

	}

	public void resetChosenAnswers() {
		rdbGroup.clearSelection();

	}

	public SolvedQuizController getSolvedQuizController() {
		return solvedQuizController;
	}

	public void disableAllOptionalAnsers() {
		rdbOptionalAnswer1.setEnabled(false);
		rdbOptionalAnswer2.setEnabled(false);
		rdbOptionalAnswer3.setEnabled(false);
		rdbOptionalAnswer4.setEnabled(false);
	}

	public void displayQuizSummary(int grade, int numOfQuestions, int numOfQuestionAnswerdRight) {

		String summary = "Your grade is: " + grade + "\nYou got " + numOfQuestionAnswerdRight + " of " + numOfQuestions
				+ " answerd right!";
		textareaQuizSummary.setText(summary);

	}

	public void hidetheLastRightOrWronglblImage() {

		lblImageVorX1.setVisible(false);

		lblImageVorX2.setVisible(false);

		lblImageVorX3.setVisible(false);

		lblImageVorX4.setVisible(false);

	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "ERROR", JOptionPane.ERROR_MESSAGE);

	}

	public void displayMedalAndTheText(MedalsEnum medal) {
		String medalKind = null;
		ImageIcon imageMedalIcon = getImageIcon(medal);
		if (imageMedalIcon != null) {
			if (medal == MedalsEnum.GOLD) {
				medalKind = "Gold";
				lblYouEarnedAMedal.setText("Youv'e Earned a " + medalKind + " Medal!");
				lblImageMedal.setIcon(imageMedalIcon);
				lblImageMedal.setVisible(true);
			} else if (medal == MedalsEnum.SILVER) {
				medalKind = "Silver";
				lblYouEarnedAMedal.setText("Youv'e Earned a " + medalKind + " Medal!");
				lblImageMedal.setIcon(imageMedalIcon);
				lblImageMedal.setVisible(true);
			} else if (medal == MedalsEnum.BRONZE) {
				medalKind = "Bronze";
				lblYouEarnedAMedal.setText("Youv'e Earned a " + medalKind + " Medal!");
				lblImageMedal.setIcon(imageMedalIcon);
				lblImageMedal.setVisible(true);
			}

			else {
				lblYouEarnedAMedal.setText("");
				lblImageMedal.setText("");
			}

		} else {
			lblYouEarnedAMedal.setText("");
			lblImageMedal.setVisible(false);

		}

	}

	private ImageIcon getImageIcon(MedalsEnum medal) {
		String imageFile = "";
		if (medal == MedalsEnum.GOLD) {
			imageFile = "gold.png";

		} else if (medal == MedalsEnum.SILVER) {
			imageFile = "silver.png";
		} else if (medal == MedalsEnum.BRONZE) {
			imageFile = "bronze.png";
		} else {
			return null;
		}

		ImageIcon image_icon = new ImageIcon(getClass().getResource("/view/images/" + imageFile));
		Image image = image_icon.getImage();
		Image fixedImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		image_icon = new ImageIcon(fixedImage);
		return image_icon;

	}

	public void showXnextToWrongChosenAnswer(int studentChosenAnswer) {
		JLabel lblImage = null;
		switch (studentChosenAnswer) {
		case 1:
			lblImage = lblImageVorX1;
			lblImageVorX1.setVisible(true);
			break;
		case 2:
			lblImage = lblImageVorX2;
			lblImageVorX2.setVisible(true);
			break;
		case 3:
			lblImage = lblImageVorX3;
			lblImageVorX3.setVisible(true);
			break;
		case 4:
			lblImage = lblImageVorX4;
			lblImageVorX4.setVisible(true);
			break;
		}

		ImageIcon image_icon = new ImageIcon(getClass().getResource("/view/images/wrong_answer.png"));
		Image image = image_icon.getImage();
		Image fixedImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		image_icon = new ImageIcon(fixedImage);
		lblImage.setIcon(image_icon);

	}

	public void displayDidNotAnswerQuestion(String text) {

		lblDidNotAnswer.setText(text);

	}
}
