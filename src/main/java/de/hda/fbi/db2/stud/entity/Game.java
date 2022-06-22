package de.hda.fbi.db2.stud.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id ;
  @Column
  private int score;
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp_start;
  @Temporal(TemporalType.TIMESTAMP)
  private Date  timestamp_end;
  @ManyToMany
  private ArrayList<Category> playedCategories;
  @ManyToMany
  private ArrayList<Question> playedQuestions;
  @ElementCollection
  private HashMap<Question,Boolean> givenAnswers;
  @ManyToOne
  private Player player;

  public Game(Player player , ArrayList<Question> playedQuestions){
    this.player = player;
    this.playedQuestions = playedQuestions;

  }

  public Game() {

  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public Date getTimestamp_start() {
    return timestamp_start;
  }

  public void setTimestamp_start(Date timestamp_start) {
    this.timestamp_start = timestamp_start;
  }

  public Date getTimestamp_end() {
    return timestamp_end;
  }

  public void setTimestamp_end(Date timestamp_end) {
    this.timestamp_end = timestamp_end;
  }

  public ArrayList<Category> getPlayedCategories() {
    return playedCategories;
  }

  public void setPlayedCategories(
      ArrayList<Category> playedCategories) {
    this.playedCategories = playedCategories;
  }

  public ArrayList<Question> getPlayedQuestions() {
    return playedQuestions;
  }

  public void setPlayedQuestions(ArrayList<Question> playedQuestions) {
    this.playedQuestions = playedQuestions;
  }

  public HashMap<Question, Boolean> getGivenAnswers() {
    return givenAnswers;
  }

  public void setGivenAnswers(
      HashMap<Question, Boolean> givenAnswers) {
    this.givenAnswers = givenAnswers;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
