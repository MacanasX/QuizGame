package de.hda.fbi.db2.stud.entity;

import javax.persistence.Entity;


public class Answer {


  private String text;
  private Boolean isCorrectAnswer;


  public Boolean getCorrectAnswer() {
    return isCorrectAnswer;
  }

  public void setCorrectAnswer(Boolean correctAnswer) {
    isCorrectAnswer = correctAnswer;
  }


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Answer(String text) {
    this.text = text;
    this.isCorrectAnswer = false;
  }




}
