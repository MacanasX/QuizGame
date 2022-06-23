package de.hda.fbi.db2.api;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.List;

/**
 * API Class for lab03 Created by l.koehler on 05.08.2019.
 */
public abstract class Lab03Game {

  /**
   * Can be overridden to perform additional initialization tasks.
   */
  public void init() {
  }

  /**
   * You can use the data from lab01, this variable will be automatically set.
   */
  protected Lab01Data lab01Data;

  /**
   * You can use the EntityManager or other stuff from lab02, this variable will be automatically
   * set.
   */
  protected Lab02EntityManager lab02EntityManager;

  /**
   * Setter for Lab01Data. Do not touch this method.
   *
   * @param lab01Data lab01Data
   */
  public final void setLab01Data(Lab01Data lab01Data) {
    this.lab01Data = lab01Data;
  }

  /**
   * Setter for Lab02EntityManager. Do not touch this method.
   *
   * @param lab02EntityManager lab02EntityManager
   */
  @SuppressFBWarnings("EI_EXPOSE_REP2")
  public final void setLab02EntityManager(Lab02EntityManager lab02EntityManager) {
    this.lab02EntityManager = lab02EntityManager;
  }

  /**
   * Creates a new Player or retrieves it from the database.
   * <p>
   * This function shall try to retrieve the player with the given name playerName from the
   * database. If no such player exists, it shall be created as a Java Object. It is not necessary
   * to persist the Player yet.
   * </p>
   *
   * <p>This function is primarily used for testing. There exists a version with user interaction
   * which shall be used from the menu.
   * </p>
   *
   * @param playerName The name for the new Player.
   * @return Player object which was created or retrieved.
   * @see Lab03Game#interactiveGetOrCreatePlayer()
   */
  public abstract Object getOrCreatePlayer(String playerName);

  /**
   * Creates a new Player or retrieves it from the database (interactive version).
   *
   * <p>
   * This function shall ask the user for a player name, and then shall try to retrieve such a
   * player from the database. If no such player exists, it shall be created as a Java Object. It is
   * not necessary to persist the player yet.
   * </p>
   *
   * <p>This function is primarily used for user interaction. There exists a version used for
   * testing, {@link #getOrCreatePlayer(String)}.</p>
   *
   * @return Player object which was created or retrieved.
   * @see Lab03Game#getOrCreatePlayer(String)
   */
  public abstract Object interactiveGetOrCreatePlayer();

  /**
   * This function shall generate a random list of questions which are from the given categories.
   *
   * <p>Per category there shall be a certain amount of questions chosen. If a category hosts less
   * questions than that amount, then all of the questions shall be chosen. Questions shall be
   * randomly selected.
   * </p>
   *
   * <p>There is also an interactive version of this function which asks the user for categories
   * instead of randomly selecting them.</p>
   *
   * @param categories                   A list of categories to select questions from
   * @param amountOfQuestionsForCategory The amount of questions per category. If a category has
   *                                     less than this amount, then all questions of that category
   *                                     shall be selected.
   * @return A List of randomly chosen Questions from the given Categories.
   * @see Lab03Game#interactiveGetQuestions()
   */
  public abstract List<?> getQuestions(List<?> categories, int amountOfQuestionsForCategory);

  /**
   * This function shall generate a random list of questions after asking the user for categories.
   *
   * <p>In this function, ask the user for categories and ggory.
   * Then, randomly select questions from those categories. Choose as many questions per category as
   * were entered, as long as the category has that many questions. If there are fewer questions,
   * then select all of them.</p>
   *
   * @return A List of randomly chosen Questions from categories which the user entered.
   * @see Lab03Game#getQuestions(List, int)
   */
  public abstract List<?> interactiveGetQuestions();

  /**
   * This function creates a Game Object with the given player and questions, but without playing
   * the game yet.
   *
   * <p>It is important that you neither play the game yet nor persist the game! This is just meant
   * to create the game object.</p>
   *
   * @param player    The Player which shall play the game.
   * @param questions The Questions which shall be asked during the game.
   * @return A Game object which contains an unplayed game
   *     for the given player with the given questions.
   */
  public abstract Object createGame(Object player, List<?> questions);

  /**
   * This function simulates a game play without user interaction by randomly choosing answers.
   *
   * <p>There is also an interactive version of this function which shall be called from the menu.
   * </p>
   *
   * @param game The Game object which shall be played.
   * @see Lab03Game#interactivePlayGame(Object)
   */
  public abstract void playGame(Object game);

  /**
   * This function plays the given game with the user, that is by using user interaction.
   *
   * <p>This is the function that should be called from the menu. Here you can implement the
   * necessary user interaction for the playing of the game.</p>
   *
   * @param game The Game object which shall be played.
   * @see Lab03Game#playGame(Object)
   */
  public abstract void interactivePlayGame(Object game);

  /**
   * Persists a played game, including the player who played it.
   *
   * @param game The Game object to be persisted.
   */
  public abstract void persistGame(Object game);
}
