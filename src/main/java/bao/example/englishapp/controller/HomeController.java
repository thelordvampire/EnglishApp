package bao.example.englishapp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import bao.example.englishapp.process.SceneProcess;

public class HomeController implements Initializable {

    @FXML
    private void toAdminScene(ActionEvent event)  
    {
        new Thread(()->{
            Platform.runLater(()->{
                SceneProcess.toDetailCollectionScene(
                    (Stage)((javafx.scene.control.Button)event.getSource()).getScene().getWindow());
            });
        }).start();
    }
    
    @FXML
    private void toLearnCollection(ActionEvent event)  
    {
//        SceneProcess.toUserScene((Stage)((javafx.scene.control.Button)event.getSource()).getScene().getWindow());
        SceneProcess.newLearnCollection();
    }
    
    @FXML
    private void toReviewCollection(ActionEvent event) {
        SceneProcess.newReviewCollection();
    }
    
    @FXML
    private void toExit(ActionEvent event)  
    {
        Platform.exit();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
