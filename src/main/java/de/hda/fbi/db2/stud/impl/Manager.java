package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab02EntityManager;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Question;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Manager extends Lab02EntityManager {

  private EntityManagerFactory emf;

  @Override
  public void init() {
    emf = Persistence.createEntityManagerFactory("docker-local-postgresPU"); //fbi-postgresPU
  }

  @Override
  public void destroy() {
    emf.close();

  }

  @Override
  public void persistData() {
    System.out.println("AUFGERUFEN!");
    List<String[]> csvLines = null;
    CsvReader reader = new CsvReader();
    reader.loadCsvFile(csvLines);
    List<Category> categories = reader.getCategories();
    List<Question> questions = reader.getQuestions();

    EntityManager myEM = getEntityManager();
    myEM.getTransaction().begin();
    for (Category category : categories) {

      myEM.persist(category);

    }
    for (Question question : questions) {

      myEM.persist(question);


    }

    myEM.getTransaction().commit();

    myEM.close();
  }

  @Override
  public EntityManager getEntityManager() {

    return emf.createEntityManager();
  }
}
