package main;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		try {

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						startApplication();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void startApplication() {
		try {
			// Injections
			new Configuration();
		} catch (Exception e) {
			System.out.println("Failed to Start Application. " + e.getMessage());
		}
	}

}
