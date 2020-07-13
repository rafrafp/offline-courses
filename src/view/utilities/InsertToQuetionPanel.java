package view.utilities;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InsertToQuetionPanel {
	Color c = new Color(171, 240, 250);
	JPanel panelQuetion;
	JScrollPane scrollPaneQuetion;
	JTextArea textAreaQuetion;
	JRadioButton rdbAnswer1;
	JRadioButton rdbAnswer2;
	JRadioButton rdbAnswer3;
	JRadioButton rdbAnswer4;
	ButtonGroup btnGroupQuetion;
	JLabel lblQuetionHeadLine;

	public void insertToQuetionPanel(JPanel panelQuetion, JScrollPane scrollPaneQuetion, JTextArea textAreaQuetion,
			JLabel lblQuetionHeadLine, String headline, String textQuetion) {
		this.panelQuetion = panelQuetion;
		this.scrollPaneQuetion = scrollPaneQuetion;
		this.scrollPaneQuetion.setBorder(BorderFactory.createEmptyBorder());
		this.scrollPaneQuetion.setForeground(c);
		this.scrollPaneQuetion.setBounds(0, 0, 296, 176);
		this.scrollPaneQuetion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.scrollPaneQuetion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scrollPaneQuetion.getHorizontalScrollBar().setBackground(c);
		this.lblQuetionHeadLine = lblQuetionHeadLine;
		this.lblQuetionHeadLine.setText(headline);
		this.scrollPaneQuetion.setColumnHeaderView(this.lblQuetionHeadLine);
		this.scrollPaneQuetion.getColumnHeader().setBackground(c);
		this.textAreaQuetion = textAreaQuetion;
		this.textAreaQuetion.setText(textQuetion);
		this.textAreaQuetion.setEditable(false);
		this.scrollPaneQuetion.setViewportView(this.textAreaQuetion);
		this.panelQuetion.add(scrollPaneQuetion);
		this.panelQuetion.add(this.rdbAnswer1);
		this.panelQuetion.add(this.rdbAnswer2);
		this.panelQuetion.add(this.rdbAnswer3);
		this.panelQuetion.add(this.rdbAnswer4);

	}

	public void rdbQuetions(JPanel panelQuetion, ButtonGroup btnGroupQuetion, JRadioButton rdbAnswer1,
			JRadioButton rdbAnswer2, JRadioButton rdbAnswer3, JRadioButton rdbAnswer4, String answer1, String answer2,
			String answer3, String answer4) {

		this.btnGroupQuetion = btnGroupQuetion;
		this.panelQuetion = panelQuetion;
		this.rdbAnswer1 = rdbAnswer1;
		this.rdbAnswer1.setBounds(this.panelQuetion.getX(), 0, 559, 23);
		this.rdbAnswer1.setText(answer1);
		this.rdbAnswer2 = rdbAnswer2;
		this.rdbAnswer2.setBounds(this.panelQuetion.getX(), 50, 559, 23);
		this.rdbAnswer2.setText(answer2);
		this.rdbAnswer3 = rdbAnswer3;
		this.rdbAnswer3.setBounds(this.panelQuetion.getX(), 100, 559, 23);
		this.rdbAnswer3.setText(answer3);
		this.rdbAnswer4 = rdbAnswer4;
		this.rdbAnswer4.setBounds(this.panelQuetion.getX(), 150, 559, 23);
		this.rdbAnswer4.setText(answer4);
		this.btnGroupQuetion = btnGroupQuetion;
		this.btnGroupQuetion.add(this.rdbAnswer1);
		this.btnGroupQuetion.add(this.rdbAnswer2);
		this.btnGroupQuetion.add(this.rdbAnswer3);
		this.btnGroupQuetion.add(this.rdbAnswer4);
	}

}
