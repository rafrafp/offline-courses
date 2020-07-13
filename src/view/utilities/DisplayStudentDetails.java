package view.utilities;

import javax.swing.JLabel;

public class DisplayStudentDetails {

	public void displaylblUser(String firstName,String lastName,JLabel lblToDisplayfromUser) {
	firstName=firstName.replace(firstName.substring(0,1), firstName.substring(0,1).toUpperCase());
	lastName=lastName.replace(lastName.substring(0,1), lastName.substring(0,1).toUpperCase());
	lblToDisplayfromUser.setText("Hello "+firstName+" "+lastName);
	
	}
}
