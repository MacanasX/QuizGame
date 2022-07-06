package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab04MassData;

import de.hda.fbi.db2.stud.entity.Category;

import de.hda.fbi.db2.stud.entity.Game;

import de.hda.fbi.db2.stud.entity.Player;


import de.hda.fbi.db2.stud.entity.Question;
import java.util.ArrayList;

import java.time.temporal.ChronoUnit;

import java.time.LocalTime;
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

    start  = LocalTime.now();



    em = lab02EntityManager.getEntityManager();
    List resultL = em
        .createQuery("select c from Category  c order by c.id").getResultList();

    System.out.println("SIZE" + resultL.size());
    em.getTransaction().begin();
    for (int i = 0; i < 100; i++) {
      Player p = (Player) lab03Game.getOrCreatePlayer(
          "player " + i); //(Player) this.lab03Game.getOrCreatePlayer(playerName);
      em.persist(p);

      for (int j = 0; j < 100; j++) {

        ArrayList<Question> q = (ArrayList<Question>) this.questionsToPlay(resultL, 15);

        Game currentGame = (Game) lab03Game.createGame(p, q);
        Date time = getTimeStamp();
        currentGame.setTimestampStart(time);
        currentGame.setTimestampEnd(time);
        lab03Game.playGame(currentGame);


        em.persist(currentGame);
        //

      }

      if (i % 10 == 0 && i > 0) {
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


      }

    }

    return myQuestions;
  }

  private Date getTimeStamp() {

    Calendar timestamp = Calendar.getInstance();
    timestamp.add(Calendar.DATE, -rand.nextInt(20));
    return timestamp.getTime();
  }


}


