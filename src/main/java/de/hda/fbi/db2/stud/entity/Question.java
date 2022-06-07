package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table (name = "Question")
public class Question {

  @Id
  private Integer id;
  @Column
  private String text;

  @ManyToOne
  private Category category;

  @ElementCollection
  @CollectionTable(name = "Answers")
  private ArrayList<Answer> myAnswerList;

  /**
   * Constructor of the class Question.
   *
   * @param id       unique id for every question-object
   * @param text     string which is holding the question of the object
   * @param category reference to the matching category
   */

  public Question(Integer id, String text, Category category) {
    this.id = id;
    this.text = text;
    this.category = category;
    this.myAnswerList = null;

  }

  public Question() {

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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question question = (Question) o;
    return Objects.equals(id, question.id) && Objects.equals(text, question.text)
        && Objects.equals(category, question.category) && Objects.equals(
        myAnswerList, question.myAnswerList);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getID());
  }

}
