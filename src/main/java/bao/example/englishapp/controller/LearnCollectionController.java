/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import com.tiennb.btl.manager.AppManager2;
import com.tiennb.btl.model.Word;
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

/**
 *
 * @author Bao
 */
public class LearnCollectionController implements Initializable {
    
    @FXML
    private ImageView image;
    
    @FXML
    private Label lbTiengAnh, lbCachDoc, lbTiengViet;
    
    private int index=0;
    private ArrayList<Word> listLearnWord;
    private AppManager2 manager;
            
    @FXML
    private void loadPreWord(ActionEvent event) {
        if(listLearnWord!=null && index-1>=0 && index-1<listLearnWord.size())
            loadWord(--index);
    }
    
    @FXML
    private void loadNextWord(ActionEvent event) {
        if(listLearnWord!=null && index+1>=0 && index+1<listLearnWord.size())
            loadWord(++index);
    }
    
    @FXML
    private void save(ActionEvent event) {
        try {
            manager.writeFileToExcel();
        } catch (IOException ex) {
            Logger.getLogger(LearnCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadWord(int i)
    {
        Word word = listLearnWord.get(i);
        image.setImage(new Image(word.getImagePath()));
        lbTiengAnh.setText(word.getTa());
        lbCachDoc.setText(word.getCachDoc());
        lbTiengViet.setText(word.getTv());
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            manager = new AppManager2();
            listLearnWord = manager.getMLastestOldWordAndNNewWord();
//            listLearnWord.forEach(word->{
//                System.out.println(word.toString());
//            });
            loadWord(index);
            
        } catch (IOException ex) {
            Logger.getLogger(LearnCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
