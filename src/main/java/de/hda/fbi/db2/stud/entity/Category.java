package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;


public class Category {

  private Integer id;
  private String text;
  private ArrayList<Question> questionList;

  public Category(Integer id, String text) {
    this.id = id;
    this.text = text;
    questionList = new ArrayList<>();

  }


  public Integer getID() {
    return id;
  }

  public void setID(Integer id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public ArrayList<Question> getQuestionList() {
    return questionList;
  }

  public void setQuestionList(ArrayList<Question> questionList) {
    this.questionList = questionList;
  }

  public void printData() {

    System.out.println("Meine ID: " + this.id + " , Mein Text: " + this.text);
    for (int i = 0; i < this.questionList.size(); i++) {
      questionList.get(i).printData();

    }

  }


}
