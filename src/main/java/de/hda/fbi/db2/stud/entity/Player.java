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
@Table (name = "Player")
@NamedQueries({
    @NamedQuery(name ="player.findByName",
                query = "SELECT m FROM Player m WHERE m.playerName=:playername")})

public class Player {


  @Id
  private String playerName;
  @OneToMany(mappedBy = "player",cascade = CascadeType.PERSIST)
  private ArrayList<Game> playedGames;

  public Player(String playerName){

   this.playerName = playerName;
   playedGames = new ArrayList<>();

  }


  public Player() {

  }
}
