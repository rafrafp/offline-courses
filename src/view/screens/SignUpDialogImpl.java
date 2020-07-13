package view.screens;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.LoginController;
import controller.SignUpController;
import view.utilities.HintPasswordField;
import view.utilities.HintTextField;
import view.utilities.InputValidator;
import view.utilities.RoundBorder;

public class SignUpDialogImpl extends JDialog implements SignUpdialog {

	private static final long serialVersionUID = 5598904883546779577L;
	SignUpController signUpController = null;
	LoginController loginController = null;
	private HintTextField hintTextfirstName;
	private HintTextField hintLasttName;
	private HintTextField hintuserName;
	private HintPasswordField hintPasswordField;
	private JButton btnSend;
	Color c = new Color(171, 240, 250);
	private JPanel panelImage;
	private JLabel labelImage;
	private JLabel lblStatus;
	private JLabel lblSignUp;

	public SignUpDialogImpl() {

		setModalityType(ModalityType.APPLICATION_MODAL);

		// lableImage
		labelImage = new JLabel();
		ImageIcon image_icon = new ImageIcon(getClass().getResource("/view/images/lala.png"));
		Image image = image_icon.getImage();
		Image fixedImage = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		image_icon = new ImageIcon(fixedImage);
		labelImage.setIcon(image_icon);

		// panelImage
		panelImage = new JPanel();
		panelImage.setBackground(c);
		panelImage.add(labelImage);
		panelImage.setBounds(77, 0, 219, 145);

		// lblSignUp
		lblSignUp = new JLabel("Sign Up");
		lblSignUp.setBounds(166, 149, 46, 14);

		// hintTextFirstName
		hintTextfirstName = new HintTextField("First Name");
		hintTextfirstName.setBounds(136, 171, 112, 20);

		// hintTextLastName
		hintLasttName = new HintTextField("Last Name");
		hintLasttName.setBounds(136, 202, 112, 20);

		// hintTextUserName
		hintuserName = new HintTextField("User Name");
		hintuserName.setBounds(136, 233, 112, 20);

		// HintPasswordField
		hintPasswordField = new HintPasswordField("Password");
		hintPasswordField.setBounds(136, 264, 112, 20);

		// btnSend
		btnSend = new JButton("Send");
		btnSend.setBounds(146, 297, 89, 23);
		btnSend.setBorder(new RoundBorder());
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!validateInput()) {
					lblStatus.setBounds(0, 331, 405, 14);
					lblStatus.setText("Please fill all the input fields");
					return;
				}
				signUpController.saveUser(hintTextfirstName.getText(), hintLasttName.getText(), hintuserName.getText(),
						String.valueOf(hintPasswordField.getPassword()));

			}
		});

		// lblStatus
		lblStatus = new JLabel();
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(-10, 331, 405, 14);

		// JDialog
		getContentPane().setLayout(null);
		getContentPane().setBackground(c);
		getContentPane().add(panelImage);
		getContentPane().add(lblSignUp);
		getContentPane().add(hintTextfirstName);
		getContentPane().add(hintLasttName);
		getContentPane().add(hintuserName);
		getContentPane().add(hintPasswordField);
		getContentPane().add(btnSend);
		getContentPane().add(lblStatus);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pack();
		setSize(400, 400);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				signUpController.setEnable();

			}
		});

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
	public LoginController getLoginController() {
		return loginController;
	}

	@Override
	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}

	@Override
	public void displayStatus() {
		lblStatus.setBounds(-10, 331, 405, 14);
		lblStatus.setText("User Name Exist");

	}

	@Override
	public boolean validateInput() {
		return InputValidator.validateHintField(hintTextfirstName, "First Name")
				&& InputValidator.validateHintField(hintLasttName, "Last Name")
				&& InputValidator.validateHintField(hintuserName, "User Name")
				&& InputValidator.validatePasswordField(hintPasswordField, "Password");

	}

	@Override
	public void clearAllTextFields() {
		hintTextfirstName.setText("");
		hintLasttName.setText("");
		hintuserName.setText("");
		hintPasswordField.setText("");
		lblStatus.setText("");
	}

}
