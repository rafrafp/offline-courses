package view.utilities;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.transferObjects.StringListener;

public class Toolbar extends JPanel implements ActionListener {
	private static final long serialVersionUID = -3063771811551380932L;
	private JButton ClassButtons[];
	private String classtype;

	private StringListener textListener;

	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());

		ClassButtons = new JButton[10];

		for (int i = 0; i < 10; i++) {
			ClassButtons[i] = new JButton(String.valueOf(i + 1));
			ClassButtons[i].addActionListener(this);

		}

		setLayout(new FlowLayout(FlowLayout.LEFT));
		for (int i = 0; i < 10; i++) {
			add(ClassButtons[i]);
		}

	}

	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();

		if (clicked == ClassButtons[0]) {
			if (textListener != null) {
				textListener.textEmitted("1", classtype);
			}
		}

		else if (clicked == ClassButtons[1]) {
			if (textListener != null) {
				textListener.textEmitted("2", classtype);
			}
		} else if (clicked == ClassButtons[2]) {
			if (textListener != null) {
				textListener.textEmitted("3", classtype);
			}
		} else if (clicked == ClassButtons[3]) {
			if (textListener != null) {
				textListener.textEmitted("4", classtype);
			}
		} else if (clicked == ClassButtons[4]) {
			if (textListener != null) {
				textListener.textEmitted("5", classtype);
			}
		} else if (clicked == ClassButtons[5]) {
			if (textListener != null) {
				textListener.textEmitted("6", classtype);
			}
		} else if (clicked == ClassButtons[6]) {
			if (textListener != null) {
				textListener.textEmitted("7", classtype);
			}
		} else if (clicked == ClassButtons[7]) {
			if (textListener != null) {
				textListener.textEmitted("8", classtype);
			}
		} else if (clicked == ClassButtons[8]) {
			if (textListener != null) {
				textListener.textEmitted("9", classtype);
			}
		} else if (clicked == ClassButtons[9]) {
			if (textListener != null) {
				textListener.textEmitted("10", classtype);
			}
		}

	}

	public void setClasstype(String type) {
		classtype = type;
	}

	public String getClasstype() {
		return classtype;
	}

}
