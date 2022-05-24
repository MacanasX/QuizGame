package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;


public class Question {

  private Integer id;
  private String text;
  private Category category;
  private ArrayList<Answer> myAnswerList;

  /** Constructor of the class Question.
   *
   *
   * @param id unique id for every question-object
   * @param text string which is holding the question of the object
   * @param category reference to the matching category
   *
   */

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




}
