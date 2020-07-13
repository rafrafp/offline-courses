package controller;

import model.Session;
import model.transferObjects.Student;
import view.screens.LoginScreenImpl;
import view.screens.SignUpDialogImpl;
import view.screens.SignUpdialog;

public class SignUpControllerImpl extends Controller implements SignUpController {
	private SignUpDialogImpl signUpDialog = null;
	private LoginScreenImpl loginScreen = null;
	private NavigationFactory navigationFactory = null;

	public SignUpControllerImpl() {
	}

	@Override
	public SignUpdialog getRegistrationScreen() {
		return signUpDialog;
	}

	@Override
	public void setSignUpDialog(SignUpDialogImpl signUpDialog) {
		this.signUpDialog = signUpDialog;
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
			setEnable();
			signUpDialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setEnable() {
		boolean isEnabled = loginScreen.isEnabled();
		loginScreen.setEnabled(!isEnabled);
	}

	@Override
	public void setLoginScreen(LoginScreenImpl loginScreen) {
		this.loginScreen = loginScreen;
	}

	@Override
	public int saveUser(String firstName, String lastName, String userName, String password) {
		int studentId = -1;

		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setUserName(userName);
		student.setPassword(password);

		try {

			this.studentDao.insert(student);

			studentId = studentDao.getUserId(userName, password);
			student.setID(studentId);
			Session.getInstance().setCurrentUser(student);

			Controller controller = navigationFactory.getController("After Successfull Login");
			controller.setStudentDao(studentDao);
			controller.start();
			loginScreen.dispose();
			signUpDialog.dispose();

		} catch (Exception e) {

			try {
				if (studentDao.isUserExist(userName)) {
					signUpDialog.displayStatus();
					return -1;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();

		}

		return studentId;
	}

}
