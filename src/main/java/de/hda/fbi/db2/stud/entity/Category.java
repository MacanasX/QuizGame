package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column (unique = true)
  private String name;

  @OneToMany(mappedBy = "category")
  private ArrayList<Question> questionList;

  /** Constructor of the class Category.
   *
   * @param id unique id for every question-category
   * @param text string which holds the category of the object
   */

  public Category(Integer id, String text) {
    this.id = id;
    this.name = text;
    questionList = new ArrayList<>();

  }

  public Category() {

  }


  public Integer getID() {
    return id;
  }

  public void setID(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<Question> getQuestionList() {
    return questionList;
  }

  public void setQuestionList(ArrayList<Question> questionList) {
    this.questionList = questionList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return Objects.equals(id, category.id) && Objects.equals(name, category.name)
        && Objects.equals(questionList, category.questionList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, questionList);
  }
}
