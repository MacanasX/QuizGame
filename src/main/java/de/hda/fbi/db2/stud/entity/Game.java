package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Game.count",
        query = "SELECT COUNT(g) FROM Game g"),
    @NamedQuery(name = "PlayedQuestions.count",
        query = "select count (g.givenAnswers) from Game g "),

    @NamedQuery(name = "specificPlayer.allGames",
        query = "select g.id, g.timestampStart, g.score , count (g.id)  "
            + " from Game g, g.playedQuestions gp"
            + " where g.player.playerName=:playerName"
            + " group by g.id"
              + " order by g.id desc")
})



public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column
  private int score;
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestampStart;
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestampEnd;
  /*@ManyToMany
  private ArrayList<Category> playedCategories;
  */

  @ManyToMany
  private ArrayList<Question> playedQuestions;
  @ElementCollection
  private Map<Question, Boolean> givenAnswers;
  @ManyToOne
  private Player player;

  /**
   * Constructor for the Game-Class.
   *
   * @param player current Player for the Game
   * @param playedQuestions questions that get played
   */
  public Game(Player player, ArrayList<Question> playedQuestions) {
    this.player = player;
    this.givenAnswers = new HashMap<>();
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

  public Date getTimestampStart() {
    return timestampStart;
  }

  public void setTimestampStart(Date timestampStart) {
    this.timestampStart = timestampStart;
  }

  public Date getTimestampEnd() {
    return timestampEnd;
  }

  public void setTimestampEnd(Date timestampEnd) {
    this.timestampEnd = timestampEnd;
  }

  public ArrayList<Question> getPlayedQuestions() {
    return playedQuestions;
  }

  public void setPlayedQuestions(ArrayList<Question> playedQuestions) {
    this.playedQuestions = playedQuestions;
  }

  public Map<Question, Boolean> getGivenAnswers() {
    return givenAnswers;
  }

  public void setGivenAnswers(
      Map<Question, Boolean> givenAnswers) {
    this.givenAnswers = givenAnswers;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
