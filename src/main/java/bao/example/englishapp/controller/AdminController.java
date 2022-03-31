/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import bao.example.englishapp.process.SceneProcess;

/**
 *
 * @author Bao
 */
public class AdminController implements Initializable {
        
    @FXML
    private void toDetailCollectionScene(ActionEvent event) {
        SceneProcess.toDetailCollectionScene((Stage)((javafx.scene.control.Button)event.getSource()).getScene().getWindow());
    }
    
    @FXML
    private void toAddWordScene(ActionEvent event) {
        SceneProcess.toAddWordScene((Stage)((javafx.scene.control.Button)event.getSource()).getScene().getWindow());
    }
    
    @FXML
    private void showAllWord(ActionEvent event) {
        SceneProcess.toShowAllWordScene((Stage)((javafx.scene.control.Button)event.getSource()).getScene().getWindow());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
