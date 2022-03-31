/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import com.jfoenix.controls.JFXTextField;
import com.tiennb.btl.manager.AppManager2;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author Bao
 */
public class AddNewCollectionController implements Initializable, ISendController {
    
    private DetailCollectionController detailcolController;
    
    @FXML
    private JFXTextField txtCollectionName;
    
    @Override
    public void setPreController(Initializable detailcolController)
    {
        if(detailcolController instanceof DetailCollectionController)
            this.detailcolController = (DetailCollectionController) detailcolController; 
    }
    
    @FXML
    private void addNewCollection(ActionEvent event) {
        try {
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
            AppManager2 manager = new AppManager2();
            manager.addColection(txtCollectionName.getText());
            detailcolController.loadCollectionName();
        } catch (IOException ex) {
            Logger.getLogger(AddNewCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
