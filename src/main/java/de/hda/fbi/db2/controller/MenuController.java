package de.hda.fbi.db2.controller;

import de.hda.fbi.db2.api.Lab02EntityManager;
import de.hda.fbi.db2.api.Lab03Game;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Game;
import de.hda.fbi.db2.stud.entity.Player;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * MenuController Created by l.koehler on 05.08.2019.
 */
public class MenuController {

  private final Controller controller;
  private Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

  public MenuController(Controller controller) {
    this.controller = controller;
  }

  /**
   * shows the menu.
   */
  public void showMenu() {
    do {
      System.out.println("Choose your Destiny?");
      System.out.println("--------------------------------------");
      System.out.println("1: Re-read csv");
      System.out.println("2: Play test");
      System.out.println("3: Create mass data");
      System.out.println("4: Analyze data");
      System.out.println("0: Quit");
    } while (readInput());
  }

  private boolean readInput() {
    try {
      BufferedReader reader =
          new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
      String input = reader.readLine();
      if (input == null) {
        return true;
      }
      switch (input) {
        case "0":
          return false;
        case "1":
          readCsv();
          break;
        case "2":
          playTest();
          break;
        case "3":
          createMassData();
          break;
        case "4":
          analyzeData();
          break;
        default:
          System.out.println("Input Error");
          break;
      }

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  private void analyzeData() throws ParseException {
    String userInput;
    String userInputDateStart;
    String userInputDateEnd;
    Boolean exit = false;
    EntityManager em = controller.getLab02EntityManager().getEntityManager();
    while (!exit) {
      System.out.println("Data Analyze");
      System.out.println("1. Search for a Player in a specific timerange ");
      System.out.println("2. All Games for a specific player ");
      System.out.println("3. All players with their played games  ");
      System.out.println("4. Most popular Categories ");
      System.out.println("5. Back");

      System.out.println("Choice: ");
      userInput = sc.nextLine();

      switch (userInput) {

        case "1":
          System.out.println(
              "Please enter a valid dateformat eg: yyyy-MM-dd HH:mm:ss for the startdate");
          userInputDateStart = sc.nextLine();
          System.out.println("And an Enddate: ");
          userInputDateEnd = sc.nextLine();
          SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          Query query = em.createNamedQuery("Player.playerFromDateToDate")
              .setParameter("startDate", parser.parse(userInputDateStart));
          query.setParameter("endDate", parser.parse(userInputDateEnd));

          List result2 = query.getResultList();

          for (Iterator i = result2.iterator(); i.hasNext(); ) {
            // Object[] element = (Object[]) i.next();
            System.out.println("Player that played at the time: " + (String) i.next());
          }

          break;

        case "2":
          System.out.println("Enter a Player-Name: ");
          userInput = sc.nextLine();
          Query query2 = em.createNamedQuery("specificPlayer.allGames")
              .setParameter("playerName", userInput);

          List resultset3 = query2.getResultList();

          for (Iterator i = resultset3.iterator(); i.hasNext(); ) {
            Object[] element = (Object[]) i.next();

            System.out.println(
                "Player " + userInput + " played following Game: " + (long) element[0] + " -> "
                    + " Date: "
                    + (Date) element[1] + " -> "
                    + " Score " + (int) element[2] + " -> " + " Number of Questions for Game "
                    + (long) element[3]);
          }

          break;
        case "3":
          List resultL = em
              .createNamedQuery("Player.playedGames").getResultList();
          System.out.println(resultL.size());

          for (Iterator i = resultL.iterator(); i.hasNext(); ) {
            Object[] element = (Object[]) i.next();
            // Player player = (Player) element[0];
            System.out.println(
                "Player-Name: " + (String) element[0] + " Played Games:  " + (Long) element[1]);
          }
          break;
        case "4":
          Query query3 = em.createNamedQuery("Category.mostPlayed");
          int counter = 1;
          List resultset4 = query3.getResultList();

          for (Iterator i = resultset4.iterator(); i.hasNext(); ) {
            Object[] element = (Object[]) i.next();

            System.out.println(
                "Category " + counter + ": " + (String) element[0] + " Times played: "
                    + (long) element[1]);
            counter++;


          }
          break;
        case "5":
          em.close();
          exit = true;
          break;

        default:
          System.out.println("Input Error");


      }
    }

  }

  private void createMassData() {
    controller.createMassData();
  }

  private void playTest() {
    Lab03Game gameController = controller.getLab03Game();
    Object player = gameController.interactiveGetOrCreatePlayer();
    List<?> questions = gameController.interactiveGetQuestions();
    Object game = gameController.createGame(player, questions);
    gameController.interactivePlayGame(game);
    gameController.persistGame(game);
  }

  private void readCsv() {
    controller.readCsv();
  }
}
