package de.hda.fbi.db2.api;

import java.net.URISyntaxException;
import java.util.List;

/**
 * API Class for lab01 Created by l.koehler on 05.08.2019.
 */
public abstract class Lab01Data {

  /**
   * Can be overridden to perform additional initialization tasks.
   */
  public void init() {
  }

  /**
   * Return all questions.
   *
   * @return questions
   */
  public abstract List<?> getQuestions();

  /**
   * Return all categories.
   *
   * @return categories
   */
  public abstract List<?> getCategories();

  /**
   * Save the CSV data in appropriate objects.
   *
   * @param csvLines CSV lines, each line is a String array consisting of the columns of the line.
   *                 The first line consists of the headers of the CSV columns.
   */
  public abstract void loadCsvFile(List<String[]> csvLines) throws URISyntaxException;
}
