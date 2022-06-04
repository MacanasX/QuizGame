package de.hda.fbi.db2.stud.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Answer {

  @Column
  private String text;
  @Column
  private Boolean isCorrectAnswer;


  public Answer(String text) {
    this.text = text;
    this.isCorrectAnswer = false;
  }

  public Answer() {

  }

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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Answer answer = (Answer) o;
    return Objects.equals(text, answer.text) && Objects.equals(isCorrectAnswer,
        answer.isCorrectAnswer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, isCorrectAnswer);
  }
}
