package controller;

import javax.swing.JOptionPane;

import model.Session;
import model.transferObjects.Student;
import view.screens.LoginScreen;
import view.screens.SignUpdialog;

public class LoginController extends Controller {
	// private static LoginController thisObj=null;
	private LoginScreen loginScreen = null;
	private SignUpdialog signUpDialog = null;
	private NavigationFactory navigationFactory = null;

	public LoginController() {
		// thisObj=this;
	}

	public LoginScreen getLoginScreen() {
		return loginScreen;
	}

	public void setLoginScreen(LoginScreen loginScreen) {
		this.loginScreen = loginScreen;
	}

	public NavigationFactory getNavigationFactory() {
		return navigationFactory;
	}

	public void setNavigationFactory(NavigationFactory navigationFactory) {
		this.navigationFactory = navigationFactory;
	}

	@Override
	public void start() {
		try {

			loginScreen.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void login(String userName, String password) {
		try {
			Student student = studentDao.getStudent(userName);
			if (student == null) {
				loginScreen.displayStatus("Invalid username or password");
			} else if (student.getPassword().equals(password)) {
				Session.getInstance().setCurrentUser(student);
				loginScreen.close();
				Controller controller = navigationFactory.getController("After Successfull Login");

				controller.start();

			} else {

				loginScreen.displayStatus("invalid username or password");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public SignUpdialog getSignUpScreen() {
		return signUpDialog;
	}

	public void setSignUpDialog(SignUpdialog signUpDialog) {
		this.signUpDialog = signUpDialog;
	}

	public void cleanTextFields() {
		loginScreen.clearTextFields();
	}

	public void setLoginScreenEnableAgainAndClearAllFields() {
		loginScreen.setEnabled(true);
		loginScreen.clearTextFields();
		signUpDialog.clearAllTextFields();

	}

}
