/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import com.jfoenix.controls.JFXTextField;
import com.tiennb.btl.manager.AppManager2;
import com.tiennb.btl.model.Word;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import bao.example.englishapp.process.EditToUpdateDataProcess;
import bao.example.englishapp.process.SceneProcess;

/**
 *
 * @author Bao
 */
public class EditCollectionController implements Initializable, ISendController {
    
    @FXML
    private Label lbTiengAnh;
    
    @FXML
    private AnchorPane paneDetailCollectionItem;
    
    private DetailCollectionController detailcolController;
    
    @Override
    public void setPreController(Initializable preController)
    {
        if(preController instanceof DetailCollectionController)
            this.detailcolController = (DetailCollectionController) preController; 
    }
    
    @FXML
    private void changeColorMouseEntered(MouseEvent event) {
        paneDetailCollectionItem.setStyle("-fx-background-color: red;");
    }
    
    @FXML
    private void changeColorMouseExited(MouseEvent event) {
        paneDetailCollectionItem.setStyle("-fx-background-color: white;");
    }
    
    @FXML
    private void toEditWordToColletion(MouseEvent event) {
        EditToUpdateDataProcess.setUpdateWord(getUpdateWord());
        SceneProcess.newUpdateWordToCollection(detailcolController);
    }
    
    public String getSelectedColName()
    {
        return (detailcolController!=null)? detailcolController.getSelectedColName(): null;
    }
    
    private Word getUpdateWord()
    {
        if(lbTiengAnh!=null)
        {
            try {
                AppManager2 manager = new AppManager2();
                ArrayList<Word> listWord = manager.getAllWordFromColection(detailcolController.getSelectedColName());
                for (Word word : listWord) 
                    if(word.getTa().equals(lbTiengAnh.getText()))
                        return word;
            } catch (IOException ex) {
                Logger.getLogger(UpdateWordCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    
    
    @FXML
    private void toDeleteWordToColletion(MouseEvent event) {
        String selectedColName = detailcolController.getSelectedColName();
        if(selectedColName!=null && lbTiengAnh!=null)
        {
            try {
                AppManager2 manager = new AppManager2();
                manager.deleteWord(lbTiengAnh.getText(), selectedColName);
                detailcolController.loadColWord();
            } catch (IOException ex) {
                Logger.getLogger(DetailCollectionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
