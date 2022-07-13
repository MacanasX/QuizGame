package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Player")
@NamedQueries({
    @NamedQuery(name = "player.findByName",
        query = "SELECT m FROM Player m WHERE m.playerName=:playername"),
    @NamedQuery(name = "Player.count",
        query = "select count (p) from Player p"),
    @NamedQuery(name = "Player.playerFromDateToDate",
        query = "select distinct g.player.playerName "
            + "from Game g where g.timestampStart >= :startDate and g.timestampEnd <= :endDate"),
    @NamedQuery(name = "Player.playedGames",
        query = "select p.playerName, count(p) as playcount from Game g join g.player p group by p "
            + "order by playcount desc")


})

public class Player {


  @Id
  private String playerName;


  @OneToMany(mappedBy = "player", cascade = CascadeType.PERSIST)
  private ArrayList<Game> playedGames;

  /**
   *Constructor for the Player-Class.
   *
   * @param playerName Name for the Player
   */
  public Player(String playerName) {

    this.playerName = playerName;
    playedGames = new ArrayList<>();

  }

  public String getPlayerName() {
    return playerName;
  }

  public ArrayList<Game> getPlayedGames() {
    return playedGames;
  }


  public Player() {

  }
}
