package view.utilities;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InputValidator {
	
	
	
	
	public  static boolean validateHintField(JTextField textField,String hint) {
		if(textField.getText()==null ||
				textField.getText().equals(hint) ||
				textField.getText().trim().length()==0) {
			return false;
		}

		return true;
	}
	
	public  static boolean validatePasswordField(JPasswordField passwordField,String hint) {
		if(String.valueOf(passwordField.getPassword())==null ||
				String.valueOf(passwordField.getPassword()).equals(hint) ||
				String.valueOf(passwordField.getPassword()).trim().length()==0) {
			return false;
		}

		return true;
	}
}
