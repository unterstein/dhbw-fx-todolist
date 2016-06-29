package ba.java.todolist.serialization;

import ba.java.todolist.model.User;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class XmlEncodeSerializer implements Serializer {

  @Override
  public void save(Map<String, User> users) {
    try (XMLEncoder enc = new XMLEncoder(new FileOutputStream(new File(DATABASE_FILE)))) {
      enc.writeObject(users);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Map<String, User> load() {
    try (XMLDecoder dec = new XMLDecoder(new FileInputStream(new File(DATABASE_FILE)))) {
      return (Map<String, User>) dec.readObject();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
