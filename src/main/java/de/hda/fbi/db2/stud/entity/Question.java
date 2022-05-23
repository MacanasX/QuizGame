package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;


public class Question {

  private Integer id;
  private String text;
  private Category category;
  private ArrayList<Answer> myAnswerList;

  public Question(Integer id, String text, Category category) {
    this.id = id;
    this.text = text;
    this.category = category;
    this.myAnswerList = null;

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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public ArrayList<Answer> getMyAnswerList() {
    return myAnswerList;
  }

  public void setMyAnswerList(ArrayList<Answer> myAnswerList) {
    this.myAnswerList = myAnswerList;
  }

  public void printData() {

    System.out.println(
        "Meine ID: " + this.id + " , Mein Text: " + this.text + " , Meine Kategorie: "
            + this.category);
    for (int i = 0; i < this.myAnswerList.size(); i++) {
      myAnswerList.get(i).printData();

    }

  }


}
