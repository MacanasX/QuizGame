package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab02EntityManager;
import de.hda.fbi.db2.api.Lab03Game;
import de.hda.fbi.db2.stud.entity.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.persistence.EntityManager;

public class GameManager extends Lab03Game {


  @Override
  public Object getOrCreatePlayer(String playerName) {

    EntityManager em = lab02EntityManager.getEntityManager();
    Player result = (Player) em.createNamedQuery("Player.findByName").setParameter("name",playerName).getSingleResult();

    em.close();
    if (result != null)
      return result;
    else {

      Player player = new Player(playerName);
      return player;
    }


  }

  @Override
  public Object interactiveGetOrCreatePlayer() {
    EntityManager em = lab02EntityManager.getEntityManager();
    Scanner sc = new Scanner(System.in);

    System.out.println("Please enter your playername: ");
    String username = sc.nextLine();

    Player result = (Player) em.createQuery(
        "select m from Player m where m.playerName= :username").getSingleResult();
    em.close();
    if (result != null) {
      System.out.println("Welcome Back " + username + "!");
      return result;
    } else {
      System.out.println("New Player got created!");
      return new Player(username);
    }

  }

  @Override
  public List<?> getQuestions(List<?> categories, int amountOfQuestionsForCategory) {

    List<Question> myQuestion = new ArrayList<>();

    for (int i = 0; i < categories.size(); i++) {
      Category category = (Category) categories.get(i);
      Random rand = new Random();
      int random;

      if (category.getQuestionList().size() > amountOfQuestionsForCategory) {
        for (int j = 0; j < amountOfQuestionsForCategory; j++) {

          random = rand.nextInt(((category.getQuestionList().size() - 1)));
          myQuestion.add(category.getQuestionList().get(random));

        }}
        else{
          for (int j = 0; j < category.getQuestionList().size(); j++) {
            myQuestion.add(category.getQuestionList().get(j));
          }
        }



    }
    return myQuestion;
  }


  @Override
  public List<?> interactiveGetQuestions() {

    Scanner sc = new Scanner(System.in, "UTF-8");
    List<Object> mycategories = new ArrayList<>();

    List resultL = lab02EntityManager.getEntityManager().createQuery("select c from Category c order by c.id").getResultList();
    List<Category> categories = new ArrayList<>();

    for(Iterator i = resultL.iterator();i.hasNext();){
      categories.add((Category) i.next());
    }

    for (int i = 0; i < categories.size(); i++){
      System.out.println(categories.get(i).getID()+" "+categories.get(i).getName());
    }

    boolean exists = false;
    System.out.println("Give the ID of category (-1 to stop)");
    int id = sc.nextInt();
    while (id==-1)
    {
      System.out.println("You have to choice 2 categories ");
      id = sc.nextInt();
    }
    while (id != -1) {

      for(int j = 0; j < categories.size(); j++){
        if(categories.get(j).getID() == id){
          exists = true;
          mycategories.add(categories.get(j));
          break;
        }
      }
      if(exists == false){
        System.out.println("Give the right ID");
      }
      System.out.println("Give the ID of category (-1 to stop)");
      id = sc.nextInt();
      exists = false;
      if(id==-1 && mycategories.size()<2){
        System.out.println("You need to choice one more category");
        id = sc.nextInt();
      }
    }
    System.out.println("Give the number of questions per category.");
    int count = sc.nextInt();
    return this.getQuestions(mycategories, count);
  }

  @Override
  public Object createGame(Object player, List<?> questions) {

  /*  Map<Question, Integer> myMap = new HashMap<>();

    for (int i = 0; i < questions.size(); i++){
      myMap.put((Question) questions.get(i),0);
    }*/
    return new Game((Player) player, (ArrayList<Question>) questions);

  }

  @Override
  public void playGame(Object game) {
    Game g = (Game) game;
    Date start = new Date();
    g.setTimestamp_start(start);
    int random;

    for(int i = 0 ; i < g.getPlayedQuestions().size() ; i++){

        Question currentQuestion = g.getPlayedQuestions().get(i);
         random = (int) (Math.random()*4)+1;
        Answer givenAnswer = currentQuestion.getMyAnswerList().get(random-1);

        if(givenAnswer.getCorrectAnswer()){

          g.getGivenAnswers().put(currentQuestion,true);
        }
        else{

          g.getGivenAnswers().put(currentQuestion,false);

        }

    }
    g.setTimestamp_end(new Date());

  }

  @Override
  public void interactivePlayGame(Object game) {

    Scanner sc = new Scanner(System.in, "UTF-8");
    Game g = (Game) game;
    Date start = new Date();
    g.setTimestamp_start(start);


    for(int i = 0 ; i < g.getPlayedQuestions().size() ; i++){

      Question currentQuestion = g.getPlayedQuestions().get(i);

      System.out.println("Question: " + currentQuestion.getText());

      for(int j = 0 ; j < currentQuestion.getMyAnswerList().size() ; j++){
        Answer currentAnswer = currentQuestion.getMyAnswerList().get(j);
        System.out.println("Answer " + j+1 +": " + currentAnswer.getText());

      }
      System.out.println("Whats your answer? : ");
      int answer = sc.nextInt();

      if(currentQuestion.getMyAnswerList().get(answer-1).getCorrectAnswer()){

        g.getGivenAnswers().put(currentQuestion,true);
        System.out.println("Correct Answer!");
      }
      else{

        g.getGivenAnswers().put(currentQuestion,false);
        System.out.println("Wrong Answer!");

      }

    }
    g.setTimestamp_end(new Date());


  }

  @Override
  public void persistGame(Object game) {

    EntityManager em = this.lab02EntityManager.getEntityManager();
    em.getTransaction().begin();

    Game g = (Game) game;
    em.persist(g);
    em.persist(g.getPlayer());
    em.getTransaction().commit();
    em.close();


  }
}
