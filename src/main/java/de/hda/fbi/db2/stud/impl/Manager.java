package de.hda.fbi.db2.stud.impl;

import de.hda.fbi.db2.api.Lab02EntityManager;
import de.hda.fbi.db2.stud.entity.Category;
import de.hda.fbi.db2.stud.entity.Question;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager extends Lab02EntityManager {

  private EntityManagerFactory emf;

  private EntityManager em;

  @Override
  public void init() {
    emf = Persistence.createEntityManagerFactory("default-postgresPU");
  }

  @Override
  public void destroy() {
    emf.close();
    em.close();
  }

  @Override
  public void persistData() {
    List<String[]> csvLines = null;
    CsvReader reader = new CsvReader();
    reader.loadCsvFile(csvLines);
    List<Category> categories = new ArrayList<>();
    List<Question> questions = new ArrayList<>();
    categories = reader.getCategories();
    questions = reader.getQuestions();
    EntityManager myEM = getEntityManager();


    myEM.getTransaction().begin();
    for (Category category : categories) {

      myEM.persist(category);

      for (Question question : questions) {

        myEM.persist(question);

      }


    }
    myEM.getTransaction().commit();




  }

  @Override
  public javax.persistence.EntityManager getEntityManager() {

    em = emf.createEntityManager();


    return em;
  }
}
