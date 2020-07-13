package view.screens;

import controller.LoginController;
import controller.SignUpController;

public interface SignUpdialog {

	SignUpController getSignUpController();

	void setSignUpController(SignUpController signUpController);

	LoginController getLoginController();

	void setLoginController(LoginController loginController);

	void displayStatus();

	boolean validateInput();

	void clearAllTextFields();

}