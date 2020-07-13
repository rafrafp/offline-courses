package view.screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import controller.SignUpController;
import view.utilities.HintPasswordField;
import view.utilities.HintTextField;
import view.utilities.InputValidator;
import view.utilities.RoundedBorder;

public class LoginScreenImpl extends JFrame implements LoginScreen {
	private static final long serialVersionUID = -1249405427005504922L;

	LoginController loginController = null;

	Font textBoxFont = new Font("Tahoma", Font.PLAIN, 22);
	Color c = new Color(171, 240, 250);

	JPanel panelLabelStatus;
	JPanel panelImage;
	JPanel panelTexts;
	JPanel panelButtons;
	JPanel panelMain;
	JLabel labelImage;
	JLabel labelSignUp;
	HintTextField hintTextUserName;
	HintPasswordField hintPasswordField;
	JButton buttonLogin;
	JLabel labelStatus;

	private SignUpController signUpController;

	public LoginScreenImpl() {

		// labelImage
		labelImage = new JLabel();
		ImageIcon image_icon = new ImageIcon(getClass().getResource("/view/images/lala.png"));
		Image image = image_icon.getImage();
		Image fixedImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		image_icon = new ImageIcon(fixedImage);
		labelImage.setIcon(image_icon);

		// panelImage
		panelImage = new JPanel();
		panelImage.add(labelImage);
		panelImage.setBackground(c);

		// JTextusername
		hintTextUserName = new HintTextField("user name");
		hintTextUserName.setPreferredSize(new Dimension(600, 40));
		hintTextUserName.setMaximumSize(new Dimension(600, 40));
		hintTextUserName.setFont(textBoxFont);

		// JtextPassword
		hintPasswordField = new HintPasswordField("Password");
		hintPasswordField.setPreferredSize(new Dimension(600, 40));
		hintPasswordField.setMaximumSize(new Dimension(600, 40));
		hintPasswordField.setFont(textBoxFont);

		// panelTexts
		panelTexts = new JPanel();
		panelTexts.setBackground(c);
		// panelTexts.setBorder(new EmptyBorder(250, 100, 20, 100));
		panelTexts.setLayout(new BoxLayout(panelTexts, BoxLayout.Y_AXIS));
		panelTexts.add(hintTextUserName);
		panelTexts.add(Box.createRigidArea(new Dimension(0, 40)));
		panelTexts.add(hintPasswordField);

		// buttonLogin
		buttonLogin = new JButton("Login");
		buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonLogin.setPreferredSize(new Dimension(150, 30));
		buttonLogin.setBorder(new RoundedBorder());
		buttonLogin.setMaximumSize(new Dimension(150, 30));

		// labelButton
		labelSignUp = new JLabel("no account? SIGN UP");
		labelSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// panelButtons
		panelButtons = new JPanel();
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		panelButtons.setBackground(c);
		panelButtons.add(labelSignUp);
		panelButtons.add(Box.createRigidArea(new Dimension(40, 0)));
		panelButtons.add(buttonLogin);

		// labelStatus
		labelStatus = new JLabel();

		// panelLabelStatus
		panelLabelStatus = new JPanel();
		panelLabelStatus.setBounds(0, 0, 50, 50);
		panelLabelStatus.setBackground(c);
		panelLabelStatus.add(labelStatus);

		// panelMain
		panelMain = new JPanel();
		panelMain.setBackground(c);
		panelMain.setBorder(new EmptyBorder(100, 100, 100, 100));
		panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
		panelMain.add(panelImage);
		panelMain.add(Box.createRigidArea(new Dimension(0, 20)));
		panelMain.add(panelTexts);
		panelMain.add(Box.createRigidArea(new Dimension(0, 20)));
		panelMain.add(panelButtons);
		panelMain.add(Box.createRigidArea(new Dimension(0, 20)));
		panelMain.add(panelLabelStatus);

		// jframe
		// getContentPane().add(panelTexts);
		// getContentPane().add(panelButtons);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(panelMain);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		labelSignUp.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				signUpController.start();

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

		buttonLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!validateInput()) {
					labelStatus.setText("please fill all the fields");
					return;
				}
				loginController.login(hintTextUserName.getText(), String.valueOf(hintPasswordField.getPassword()));

			}
		});

		setTitle("Please Login Here !");
		setSize(500, 600);
		hintTextUserName.setText("");
		hintPasswordField.setText("");
		setResizable(false);

	}

	@Override
	public LoginController getLoginController() {
		return loginController;
	}

	@Override
	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	@Override
	public void displayStatus(String status) {
		labelStatus.setText(status);

	}

	@Override
	public void close() {
		this.dispose();
	}

	@Override
	public SignUpController getSignUpController() {
		return signUpController;
	}

	@Override
	public void setSignUpController(SignUpController signUpController) {
		this.signUpController = signUpController;
	}

	@Override
	public boolean validateInput() {
		return InputValidator.validateHintField(hintTextUserName, "User Name")
				&& InputValidator.validatePasswordField(hintPasswordField, "Password");

	}

	@Override
	public void clearTextFields() {
		hintTextUserName.setText("");
		hintPasswordField.setText("");
		labelStatus.setText("");

	}

//	public SignUpController getSignUpController() {
//		return signUpController;
//	}
//
//
//
//	public void setSignUpController(SignUpController signUpController) {
//		this.signUpController = signUpController;
//	}

}
