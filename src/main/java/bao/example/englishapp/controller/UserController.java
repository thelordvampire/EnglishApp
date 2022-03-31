/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import bao.example.englishapp.process.SceneProcess;

/**
 *
 * @author Bao
 */
public class UserController implements Initializable {
    
    @FXML
    private void toReviewCollection(ActionEvent event) {
        SceneProcess.newReviewCollection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
