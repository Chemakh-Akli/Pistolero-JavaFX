package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Win {

    @FXML
    AnchorPane gameover;

    public void click(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Projet Interface Graphique");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Stage stage = (Stage) gameover.getScene().getWindow();
        stage.close();
    }
}
