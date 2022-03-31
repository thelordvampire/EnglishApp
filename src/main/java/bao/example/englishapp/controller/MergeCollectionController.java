/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tiennb.btl.manager.AppManager2;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author Bao
 */
public class MergeCollectionController implements Initializable, ISendController {
    
    private DetailCollectionController detailcolController;
    
    @FXML
    private JFXComboBox cbListCol1;
    
    @FXML
    private JFXComboBox cbListCol2;
    
    @FXML
    private JFXTextField newColName;
    
    @Override
    public void setPreController(Initializable detailcolController)
    {
        if(detailcolController instanceof DetailCollectionController)
            this.detailcolController = (DetailCollectionController) detailcolController; 
    }
    
    @FXML
    private void mergeCollection(ActionEvent event) {
        try {
            if(cbListCol1.getSelectionModel().getSelectedItem()!=null &&
                cbListCol2.getSelectionModel().getSelectedItem()!=null &&
                newColName.getText()!=null)
            {
                AppManager2 manager = new AppManager2();
                manager.gopHaiColection(cbListCol1.getSelectionModel().getSelectedItem().toString(),
                    cbListCol2.getSelectionModel().getSelectedItem().toString(), newColName.getText());
                detailcolController.loadCollectionName();
                detailcolController.loadColWord();
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DetailCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadCollectionName()
    {
        try {
            AppManager2 manager = new AppManager2();
            ObservableList<String> list = FXCollections.observableArrayList();
            ArrayList nameColection = manager.getNameColection();
            list.addAll(nameColection);
            
            cbListCol1.setItems(list);
            cbListCol2.setItems(list);
        } catch (IOException ex) {
            Logger.getLogger(DetailCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        loadCollectionName();
        
    }
}