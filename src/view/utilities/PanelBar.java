package view.utilities;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class PanelBar extends JPanel {
	private static final long serialVersionUID = -216308211887660158L;
	private JLabel lblHelloFirstAndLastName;
	private JLabel lblClickLogOut;
	private JLabel lblClickCourses;
	private JLabel lblClickQuizzes;
	private JLabel lblClickGoals;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JPanel panelImage;
	Color c2=new Color(169,192,237);
	private JLabel lblImage;

	public PanelBar() {
		//panelImage & lblImage
		ImageIcon image_icon=new ImageIcon(getClass().getResource("/view/images/lala.png"));
		Image image=image_icon.getImage();
		Image fixedImage=image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		image_icon=new ImageIcon(fixedImage);
		lblImage = new JLabel();
		panelImage = new JPanel();
		panelImage.setSize(120,120);
		lblImage.setIcon(image_icon);
		lblImage.setBounds(10, 11, 46, 14);
		lblImage.setSize(120,120);
		panelImage.add(lblImage);
		panelImage.setBounds(10, 13, 143, 138);
		panelImage.setLayout(null);
		panelImage.setBackground(c2);

		//lblhellousername
		lblHelloFirstAndLastName = new JLabel("New label");
		lblHelloFirstAndLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelloFirstAndLastName.setBounds(20, 160, 133, 14);

		//lblClickLogOut
		lblClickLogOut = new JLabel("Log Out");
		lblClickLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblClickLogOut.setBounds(61, 185, 46, 14);

		//lblClickCourses
		lblClickCourses = new JLabel("Courses");
		lblClickCourses.setBounds(61, 274, 70, 14);
		lblClickCourses.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		//lblClickQuizzes
		lblClickQuizzes = new JLabel("Quizzes");
		lblClickQuizzes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClickQuizzes.setBounds(61, 311, 70, 14);
		lblClickQuizzes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		//lblClickGoals
		lblClickGoals = new JLabel("Goals");
		lblClickGoals.setBounds(61, 349, 46, 14);
		lblClickGoals.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		//separators
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 298, 143, 2);
		separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(10, 336, 143, 2);
		separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(10, 374, 143, 2);


		//panelBar
		setLayout(null);
		setBounds(0, 0, 183, 1137);
		add(lblHelloFirstAndLastName);
		add(panelImage);
		add(lblClickLogOut);
		add(lblClickCourses);
		add(lblClickQuizzes);
		add(separator);
		add(separator_1);
		add(lblClickGoals);
		add(separator_2);
		setBackground(c2);


	}

	public JLabel getLblHelloFirstAndLastName() {
		return lblHelloFirstAndLastName;
	}

	public void setLblHelloFirstAndLastName(JLabel lblHelloFirstAndLastName) {
		this.lblHelloFirstAndLastName = lblHelloFirstAndLastName;
	}

	public JLabel getLblClickLogOut() {
		return lblClickLogOut;
	}

	public void setLblClickLogOut(JLabel lblClickLogOut) {
		this.lblClickLogOut = lblClickLogOut;
	}

	public JLabel getLblClickCourses() {
		return lblClickCourses;
	}

	public void setLblClickCourses(JLabel lblClickCourses) {
		this.lblClickCourses = lblClickCourses;
	}

	public JLabel getLblClickQuizzes() {
		return lblClickQuizzes;
	}

	public void setLblClickQuizzes(JLabel lblClickQuizzes) {
		this.lblClickQuizzes = lblClickQuizzes;
	}

	public JLabel getLblClickGoals() {
		return lblClickGoals;
	}

	public void setLblClickGoals(JLabel lblClickGoals) {
		this.lblClickGoals = lblClickGoals;
	}
	
	

}
