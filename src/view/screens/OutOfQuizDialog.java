package view.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;

import controller.QuizController;
import view.utilities.StateFromWhereTheOutOfQuizDialog;

public class OutOfQuizDialog extends JDialog {
	private static final long serialVersionUID = -8757103398232805065L;
	Color c2 = new Color(169, 192, 237);
	JButton btnOk;
	QuizController quizController = null;
	QuizScreen quizScreen = null;

	public OutOfQuizDialog() {
		setBounds(100, 100, 524, 242);
		getContentPane().setBackground(c2);
		getContentPane().setLayout(null);

		btnOk = new JButton("YES");
		btnOk.setBounds(201, 142, 89, 23);
		getContentPane().add(btnOk);

		JTextArea txtrAreYouSure = new JTextArea();
		txtrAreYouSure.setBackground(c2);
		txtrAreYouSure.setEditable(false);
		txtrAreYouSure.setFont(new Font("Courier New", Font.BOLD, 15));
		txtrAreYouSure.setText(
				"Are you sure you want to get out of  the quiz? \r\nYou will lose all your chosen answer\r\nand you will have to start a new quizz");
		txtrAreYouSure.setBounds(10, 11, 488, 110);
		getContentPane().add(txtrAreYouSure);
		setModal(true);
		setLocationRelativeTo(quizScreen);

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (quizScreen
						.getStateFromWhereTheOutOfQuizDialog() == StateFromWhereTheOutOfQuizDialog.ALL_OTHER_STATMENT) {
					quizController.removeQuizFromDb();
					quizController.goToSelectedScreen(quizScreen.selectedLblPanelBar);
				} else if (quizScreen
						.getStateFromWhereTheOutOfQuizDialog() == StateFromWhereTheOutOfQuizDialog.LOG_OUT_STATMENT) {
					quizController.removeQuizFromDb();
					quizController.logOut();
				}

				else if (quizScreen
						.getStateFromWhereTheOutOfQuizDialog() == StateFromWhereTheOutOfQuizDialog.EXIT_STATMENT) {
					quizController.removeQuizFromDb();
					quizScreen.dispose();
				}

				dispose();
			}
		});
	}

	public QuizController getQuizController() {
		return quizController;
	}

	public void setQuizController(QuizController quizController) {
		this.quizController = quizController;
	}

	public QuizScreen getQuizScreen() {
		return quizScreen;
	}

	public void setQuizScreen(QuizScreen quizScreen) {
		this.quizScreen = quizScreen;
	}

}
