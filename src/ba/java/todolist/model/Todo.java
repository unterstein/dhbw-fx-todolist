package ba.java.todolist.model;

import ba.java.todolist.serialization.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

public class Todo implements Serializable {
  private String name;
  private String comment;
  private boolean done;
  private boolean prioritized;
  private LocalDate dueDate;

  public Todo() {
    // Default Constructor is needed or JAXB
  }

  public Todo(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public boolean isPrioritized() {
    return prioritized;
  }

  public void setPrioritized(boolean prioritized) {
    this.prioritized = prioritized;
  }

  // adapter needed for serialization
  @XmlJavaTypeAdapter(LocalDateAdapter.class)
  public LocalDate getDueDate() {
    return dueDate;
  }

  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }
}
