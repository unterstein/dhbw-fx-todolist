package ba.java.todolist.serialization;

import ba.java.todolist.model.Todo;
import ba.java.todolist.model.TodoList;
import ba.java.todolist.model.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OwnLineBasedSerializer implements Serializer {

  public static final String USER = "USER;";
  public static final String LIST = "LIST;";
  public static final String TODO = "TODO;";

  @Override
  public void save(Map<String, User> users) {
    try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(DATABASE_FILE))))) {
      for (User user : users.values()) {
        writer.println(USER + " " + user.getName() + ", " + user.getPassword());
        for (TodoList liste : user.getLists()) {
          writer.println(LIST + " " + liste.getName());
          for (Todo aufgabe : liste.getTodo()) {
            writer.println(TODO+ " " + aufgabe.toSerializeString());
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Map<String, User> load() {
    Map<String, User> result = new HashMap<>();
    String line = null;
    String[] split = null;
    User currentUser = null;
    TodoList currentList = null;
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(DATABASE_FILE)))) {
      while ((line = reader.readLine()) != null) {
        if (line.startsWith(USER)) {
          split = line.replace(USER + " ", "").split(",");
          currentUser = new User(split[0].trim(), split[1].trim());
          result.put(currentUser.getName(), currentUser);
        } else if (line.startsWith(LIST)) {
          if (currentUser == null) {
            throw new Exception(); // TODO
          }
          currentList = currentUser.addTodoList(line.replace(LIST + " ", "").trim());
        } else if (line.startsWith(TODO)) {
          if (currentUser == null || currentList == null) {
            throw new Exception(); // TODO
          }
          split = line.replace(TODO + " ", "").split(",");
          Todo todo = currentList.addTodo(split[0].trim());
          todo.setComment(split[1].trim());
          todo.setDone(Boolean.valueOf(split[2].trim()));
          todo.setPrioritized(Boolean.valueOf(split[3].trim()));
          String date = split[4].trim();
          if (date != null && !"null".equals(date)) {
            todo.setDueDate(LocalDate.parse(date));
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }
}
