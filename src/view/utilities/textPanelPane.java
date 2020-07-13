package view.utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import main.Main;

public class textPanelPane extends JPanel {
	private static final long serialVersionUID = 6325983645390737761L;
	
	Color c = new Color(171, 240, 250);
	private JTextPane textArea;
	private SimpleAttributeSet setTopic;
	private SimpleAttributeSet setName;
	private SimpleAttributeSet setHeadline;
	private SimpleAttributeSet setText;

	public textPanelPane() {
		textArea = new JTextPane();
		int fontSize = 16;

		setTopic = new SimpleAttributeSet();
		SetTextParams(setTopic, true, true, fontSize * 3, Color.red, c);

		setName = new SimpleAttributeSet();
		SetTextParams(setName, true, false, fontSize * 2, Color.BLACK, c);

		setHeadline = new SimpleAttributeSet();
		SetTextParams(setHeadline, false, false, fontSize * 2, Color.BLACK, c);

		setText = new SimpleAttributeSet();
		SetTextParams(setText, false, false, fontSize, Color.BLACK, c);

		setLayout(new BorderLayout());
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}

	// helper func
	public void SetTextParams(SimpleAttributeSet name, boolean bold, boolean italic, int fontSize, Color myFontColor,
			Color c) {
		StyleConstants.setBold(name, bold);
		StyleConstants.setItalic(name, italic);
		StyleConstants.setFontSize(name, fontSize);
		StyleConstants.setForeground(name, myFontColor);
		StyleConstants.setBackground(name, c);
		// we can add more or Default parms
	}

	public String getFilePath(String path, String Class) {
		String Filepath = path;

		if (Class == "C") {
			Filepath = path + "C.json";
		}

		else if (Class == "SQL") {
			Filepath = path + "SQL.json";
		}

		else if (Class == "JAVA") {
			Filepath = path + "JAVA.json";
		}

		else if (Class == "DATA") {
			Filepath = path + "DATA.json";
		}

		return Filepath;
	}

	public void setDefaultText(String text) {
		textArea.setBackground(c);
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 48));
		textArea.setForeground(Color.red);
		textArea.setText(text);

	}

	public void clearText() {
		textArea.setText("");
	}

	public void appendTextFromJSON(String classNum, String inputClass) {
		File FileOpen = null; // Default file

		// File directory = new File("./");
		// String path = directory.getAbsolutePath(); //get dir path for files and pics
		String path = Main.class.getClassLoader().getResource("").getPath();
		String fullPath = null;
		try {
			fullPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		fullPath = fullPath.substring(1);
		fullPath = fullPath.replace("bin", "src/jasons/");

		FileOpen = new File(getFilePath(fullPath, inputClass));

		try {
			FileInputStream newJFileInputStream = (new FileInputStream(FileOpen));
			JSONObject jo = new JSONObject(new JSONTokener(newJFileInputStream));

			String topic = (String) jo.get("topic");
			Document doc = textArea.getStyledDocument();
			try {
				doc.insertString(doc.getLength(), topic + "\n", setTopic);
			} catch (BadLocationException e) {
			}

			String currClass = String.valueOf(classNum);
			jo = (JSONObject) jo.get(currClass);
			jo = (JSONObject) jo.get("subTopic");

			String name = (String) jo.get("name");
			try {
				doc.insertString(doc.getLength(), name + "\n", setName);
			} catch (BadLocationException e) {
			}

			// getting paragraphs
			JSONArray ja = (JSONArray) jo.get("paragraphs");

			for (int i = 0; i < ja.length(); i++) {
				String headline = ja.getJSONObject(i).getString("headline");
				String text = ja.getJSONObject(i).getString("text");
				boolean IsimageNull = ja.getJSONObject(i).isNull("image");

				try {
					doc.insertString(doc.getLength(), headline + "\n\n", setHeadline);
					doc.insertString(doc.getLength(), text + "\n\n", setText);
				} catch (BadLocationException e) {
				}
				if (!IsimageNull) {
					String imageLocation = ja.getJSONObject(i).getString("image");
					ImageIcon image = new ImageIcon(fullPath + "pic/" + imageLocation);
					textArea.setCaretPosition(textArea.getDocument().getLength());
					textArea.insertIcon(image);
				}
				textArea.setBackground(c);
				textArea.setEditable(false);
				textArea.setCaretPosition(0);

			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}