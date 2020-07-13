package model.transferObjects;

import java.util.ArrayList;
import java.util.List;

public class Question {
	
	private int ID;
	private String text;
	private int rightAnswerID;
	private List<Answer> answers=new ArrayList<Answer>();
	
	
	

	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getRightAnswerID() {
		return rightAnswerID;
	}
	public void setRightAnswerID(int rightAnswerID) {
		this.rightAnswerID = rightAnswerID;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	 
	public void AddAnswer(Answer answer) {
		
		this.answers.add(answer);
	}

	
	
}
