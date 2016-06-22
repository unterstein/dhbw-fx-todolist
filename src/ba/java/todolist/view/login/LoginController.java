package ba.java.todolist.view.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

  @FXML
  private Button button;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    button.setOnAction(e -> {
      Stage stage = (Stage) button.getScene().getWindow();
      try {
        Parent root = FXMLLoader.load(getClass().getResource("listView.fxml"));
        stage.setScene(new Scene(root, 300, 275));
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    });
  }
}
