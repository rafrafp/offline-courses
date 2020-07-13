package controller;

import view.screens.LoginScreenImpl;
import view.screens.SignUpDialogImpl;
import view.screens.SignUpdialog;

public interface SignUpController {

	SignUpdialog getRegistrationScreen();

	void setSignUpDialog(SignUpDialogImpl signUpDialog);


	void start();

	void setEnable();

	void setLoginScreen(LoginScreenImpl loginScreen);

	int saveUser(String firstName, String lastName, String userName, String password);

}