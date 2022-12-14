package de.hda.fbi.db2.stud.entity;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.sound.midi.Sequence;

@Entity
@Table(name = "Category")
@NamedQueries({
    @NamedQuery(name = "Category.mostPlayed",
        query = "select c.name, count(c) as counter "
            + "from Question q join Game g join g.playedQuestions pq join Category c "
            + "where q.category = c and pq.id = q.id "
            + "group by c.id "
            + "order by counter desc ")})

public class Category {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(unique = true)
  private String name;

  @OneToMany(mappedBy = "category")
  private ArrayList<Question> questionList;

  /**
   * Constructor of the class Category.
   *
   * @param text string which holds the category of the object
   */

  public Category(String text) {
    this.name = text;
    questionList = new ArrayList<>();

  }

  public Category() {

  }


  public long getID() {
    return id;
  }

  public void setID(long id) {
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
    return Objects.hashCode(getID());
  }
}
