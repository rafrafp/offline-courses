package view.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.MainScreenController;
import controller.MyMedalsController;
import model.Constants;
import model.Constants.CoursesEnum;
import model.Constants.MedalsEnum;
import view.utilities.PanelBar;

public class MyMedalsScreen extends JFrame {
	private static final long serialVersionUID = -8985190281857482557L;
	Color c=new Color(171,240,250);
	private JPanel contentPane;
	private PanelBar panelBar;
	JLabel lblImageC;
	JLabel lblImageJava;
	JLabel lblImageDataStructres;
	JLabel lblImageSql;
	MyMedalsController medalsController;
	MainScreenController mainScreenController;
	private JLabel lblYourGradeC;
	private JLabel lblYourGradeJava;
	private JLabel lblyourGradeData;
	private JLabel lblYourGradeSQL;
	
	public MyMedalsScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setSize(1000, 800);
		setResizable(false);
		setContentPane(contentPane);
		panelBar =new PanelBar();
		contentPane.add(panelBar);
		contentPane.setBackground(c);
		panelBar.getLblClickCourses().setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBar.getLblClickQuizzes().setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelBar.getLblClickGoals().setFont(new Font("Tahoma", Font.BOLD, 14));
		JLabel lblC = new JLabel("C");
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		lblC.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblC.setBounds(223, 130, 159, 96);
		contentPane.add(lblC);

		JLabel lblJava = new JLabel("JAVA");
		lblJava.setHorizontalAlignment(SwingConstants.CENTER);
		lblJava.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblJava.setBounds(392, 130, 159, 96);
		contentPane.add(lblJava);

		JLabel lblSql = new JLabel("SQL");
		lblSql.setHorizontalAlignment(SwingConstants.CENTER);
		lblSql.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSql.setBounds(815, 134, 159, 92);
		contentPane.add(lblSql);

		JLabel lblDataStructures = new JLabel("DATA STRUCTURES");
		lblDataStructures.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDataStructures.setBounds(578, 130, 239, 96);
		contentPane.add(lblDataStructures);

		lblImageC = new JLabel("");
		lblImageC.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageC.setBounds(235, 332, 159, 96);
		contentPane.add(lblImageC);

		lblImageJava = new JLabel("");
		lblImageJava.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageJava.setBounds(404, 332, 159, 96);
		contentPane.add(lblImageJava);

		lblImageDataStructres = new JLabel("");
		lblImageDataStructres.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageDataStructres.setBounds(599, 332, 159, 96);
		contentPane.add(lblImageDataStructres);

		lblImageSql = new JLabel("");
		lblImageSql.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageSql.setBounds(815, 332, 159, 96);
		contentPane.add(lblImageSql);
		
		lblYourGradeC = new JLabel("New label");
		lblYourGradeC.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourGradeC.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYourGradeC.setBounds(210, 266, 205, 14);
		contentPane.add(lblYourGradeC);
		
		lblYourGradeJava = new JLabel("New label");
		lblYourGradeJava.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourGradeJava.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYourGradeJava.setBounds(392, 266, 167, 14);
		contentPane.add(lblYourGradeJava);
		
		lblyourGradeData = new JLabel("New label");
		lblyourGradeData.setHorizontalAlignment(SwingConstants.CENTER);
		lblyourGradeData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblyourGradeData.setBounds(569, 266, 247, 14);
		contentPane.add(lblyourGradeData);
		
		lblYourGradeSQL = new JLabel("New label");
		lblYourGradeSQL.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourGradeSQL.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblYourGradeSQL.setBounds(815, 266, 159, 14);
		contentPane.add(lblYourGradeSQL);
		
		panelBar.getLblClickLogOut().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				medalsController.logOut();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		panelBar.getLblClickCourses().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				mainScreenController.coursesWasPressed();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		panelBar.getLblClickQuizzes().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				mainScreenController.quizzesWasPressed();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		panelBar.getLblClickGoals().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
				mainScreenController.startGoalsScreen();
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "ERROR",JOptionPane.ERROR_MESSAGE);

	}

	public void displayMedal(Constants.CoursesEnum course, Constants.MedalsEnum medal) {
		JLabel lblMedalForCourse=getMedalImageLabel(course);
		ImageIcon imageMedalIcon=getImageIcon(medal);
		if(imageMedalIcon!=null) {
			lblMedalForCourse.setVisible(true);
			lblMedalForCourse.setIcon(imageMedalIcon);	
		}
		else {
			lblMedalForCourse.setVisible(false);
		}

	}

	private ImageIcon getImageIcon(MedalsEnum medal) {
		String imageFile="";
		ImageIcon image_icon=null;
		if(medal==MedalsEnum.GOLD) {
			imageFile="gold.png";

		}
		else if(medal==MedalsEnum.SILVER) {
			imageFile="silver.png";
		}
		else if(medal==MedalsEnum.BRONZE) {
			imageFile="bronze.png";
		}
		else {
			image_icon=null;
			return image_icon;
		}

		image_icon=new ImageIcon(getClass().getResource("/view/images/"+imageFile));
		Image image=image_icon.getImage();
		Image fixedImage=image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		image_icon=new ImageIcon(fixedImage);
		return image_icon;
		
		
	}


	private JLabel getMedalImageLabel(CoursesEnum course) {
		JLabel lblMedal=null;
		if(course==CoursesEnum.C) {
			lblMedal=lblImageC;
		}
		else if(course==CoursesEnum.JAVA) {
			lblMedal=lblImageJava;
		}
		else if(course==CoursesEnum.DATA_STRUCTURES) {
			lblMedal=lblImageDataStructres;
		}
		else if(course==CoursesEnum.SQL) {
			lblMedal=lblImageSql;
		}
		else {
			showError("Failed to display medal. invalid course");
		}

		return lblMedal;
	}
	
	public void displayYourGrade(String text,Constants.CoursesEnum course) {
		if(course==CoursesEnum.C) {
		lblYourGradeC.setText(text);
		}
		else if(course==CoursesEnum.JAVA) {
			lblYourGradeJava.setText(text);
			}
		else if(course==CoursesEnum.DATA_STRUCTURES) {
			lblyourGradeData.setText(text);
		}
		else if(course==CoursesEnum.SQL) {
		lblYourGradeSQL.setText(text);
		}
	}
	
	public MainScreenController getMainScreenController() {
		return mainScreenController;
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}

	public PanelBar getPanelBar() {
		return panelBar;
	}

	public void setPanelBar(PanelBar panelBar) {
		this.panelBar = panelBar;
	}

	public MyMedalsController getMedalsController() {
		return medalsController;
	}

	public void setMedalsController(MyMedalsController medalsController) {
		this.medalsController = medalsController;
	}

	public void clearAllData() {
		
		
	}
}
