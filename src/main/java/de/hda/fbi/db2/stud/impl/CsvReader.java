package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab01Data;
import de.hda.fbi.db2.controller.CsvDataReader;
import de.hda.fbi.db2.stud.entity.Answer;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Question;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

//READS THE CSV FILE
public class CsvReader extends Lab01Data {

  private List<Category> myCategories = new ArrayList<>();
  private List<Question> myQuestions = new ArrayList<>();


  @Override
  public List<Question> getQuestions() {
    return myQuestions;
  }

  @Override
  public List<Category> getCategories() {
    return myCategories;
  }


  @Override
  public void loadCsvFile(List<String[]> csvLines) {
    List<String> files = new ArrayList<>();
    try {
      files = CsvDataReader.getAvailableFiles();
    } catch (IOException | URISyntaxException e) {
      e.printStackTrace();
    }

    try {
      csvLines = CsvDataReader.read(files.get(0));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    String[] column;
    String categorie;
    boolean categoryExists;

    for (int i = 1; i < csvLines.size(); i++) {

      column = csvLines.get(i);
      categorie = column[7];
      categoryExists = false;
      // System.out.println(column[7]);

      if (myCategories.isEmpty()) {
        Category category = new Category(categorie);
        myCategories.add(category);
      }

      if (myCategories.size() > 0) {

        for (int k = 0; k < myCategories.size(); k++) {

          categoryExists = categorie.equals(myCategories.get(k).getName());
          if (!categoryExists) { //categorie.equals(myCategories.get(k).getText())
            categoryExists = false;
          } else {
            categoryExists = true;
            break;
          }

        }

      }
      if (!categoryExists) {
        Category category2 = new Category(categorie);
        myCategories.add(category2);
      }

    }

    //get all Answers and Questions from List and initialize new Objects

    for (int i = 1; i < csvLines.size(); i++) {
      ArrayList<Answer> myAnswers = new ArrayList<>();
      myAnswers.clear();
      column = csvLines.get(i);
      String frage = column[1];
      String category = column[7];
      int correctAnswer = Integer.parseInt(column[6]);
      for (int j = 0; j < 4; j++) {
        Answer myanswer = new Answer(column[2 + j]);

        if ((j + 1) == correctAnswer) {
          myanswer.setCorrectAnswer(true);
        }
        myAnswers.add(myanswer);

      }

      for (int j = 0; j < myCategories.size(); j++) {
        if (category.equals(myCategories.get(j).getName())) {

          Question question = new Question(Integer.parseInt(column[0]), frage, myCategories.get(j));
          question.setMyAnswerList(myAnswers);
          myQuestions.add(question);
          myCategories.get(j).getQuestionList().add(question);

        }
      }
    }

  }
}
