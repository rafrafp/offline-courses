package view.screens;

import controller.LoginController;
import controller.SignUpController;

public interface LoginScreen {
	void setSignUpController(SignUpController signUpController);

	void setLoginController(LoginController loginController);

	LoginController getLoginController();

	void displayStatus(String status);

	void close();

	SignUpController getSignUpController();

	boolean validateInput();

	void clearTextFields();

	void setEnabled(boolean b);

	void setVisible(boolean b);

}