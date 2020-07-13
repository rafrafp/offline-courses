package model;

import model.transferObjects.Student;

// Singleton
public final class Session {

	private static volatile Session instance = null;

	private Student currentUser;

	// disable default ctor
	private Session() {

	}

	public static Session getInstance() {

		if (instance == null) {
			instance = new Session();
		}

		return instance;

	}

	public Student getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Student currentUser) {
		this.currentUser = currentUser;
	}

}
