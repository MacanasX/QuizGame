package de.hda.fbi.db2.stud.impl;


import de.hda.fbi.db2.api.Lab04MassData;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Game;
import de.hda.fbi.db2.stud.entity.Player;
import de.hda.fbi.db2.stud.entity.Question;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;

public class MassData extends Lab04MassData {

  private EntityManager em = null;
  private Random rand = new Random();
  private LocalTime start;
  private LocalTime end;

  @Override
  public void createMassData() {

    start = LocalTime.now();

    em = lab02EntityManager.getEntityManager();
    List resultL = em
        .createQuery("select c from Category  c order by c.id").getResultList();

    System.out.println("SIZE" + resultL.size());
    em.getTransaction().begin();
    for (int i = 0; i < 2000; i++) {
      Player p = (Player) lab03Game.getOrCreatePlayer(
          generateRandomPlayer());
      em.persist(p);

      for (int j = 0; j < 100; j++) {

        ArrayList<Question> q = (ArrayList<Question>) this.questionsToPlay(resultL,
            rand.nextInt(11) + 10);
        // System.out.println("fragen: " + q.size());
        Game currentGame = (Game) lab03Game.createGame(p, q);

        lab03Game.playGame(currentGame);
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = getTimeStamp();

        currentGame.setTimestampStart(time);
        currentGame.setTimestampEnd(time);

        em.persist(currentGame);
        //

      }

      if ((i % 10 == 0) && (i > 0)) {
        em.flush();
        em.clear();
      }

    }
    em.flush();
    em.clear();
    em.getTransaction().commit();

    end = LocalTime.now();
    long between = ChronoUnit.MILLIS.between(start, end);
    int minutes = (int) ((between / 1000) / 60);
    int seconds = (int) ((between / 1000) % 60);
    System.out.println("The duration is: " + minutes + " minutes and " + seconds + " seconds");

    long playerCount = (long) em.createNamedQuery("Player.count").getSingleResult();
    long gameCount = (long) em.createNamedQuery("Game.count").getSingleResult();

    System.out.println("Number of Players:" + playerCount);
    System.out.println("Number of played games: " + gameCount);
    System.out.println("Played questions : " + (long) em.createNamedQuery("PlayedQuestions.count")
        .getSingleResult());
    em.close();

  }


  private List<Question> questionsToPlay(List<Category> categories, int maxQuestions) {
    ArrayList<Question> myQuestions = new ArrayList<>();

    for (int i = 0; i < maxQuestions; i++) {

      Category currentCategory = categories.get(rand.nextInt(categories.size()));

      for (int j = 0; j < currentCategory.getQuestionList().size(); j++) {
        Question currenQuestion = currentCategory.getQuestionList()
            .get(rand.nextInt(currentCategory.getQuestionList().size()));

        if (!myQuestions.contains(currenQuestion)) {
          myQuestions.add(currenQuestion);
          break;
        }
        if (j == currentCategory.getQuestionList().size() - 1) {
          i--;
        }


      }

    }

    return myQuestions;
  }

  private Date getTimeStamp() {

    Calendar timestamp = Calendar.getInstance();
    timestamp.add(Calendar.DATE, rand.nextInt(14));
    System.out.println(timestamp.getTime());
    return timestamp.getTime();
  }

  /**
   * Generates Random Player Name for the Game.
   * @return RandomPlayername
   */
  public String generateRandomPlayer() {
    String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
        + "lmnopqrstuvwxyz";

    StringBuilder sb = new StringBuilder(12);
    for (int i = 0; i < 12; i++) {
      sb.append(chars.charAt(rand.nextInt(chars.length())));
    }
    return sb.toString();
  }
}




