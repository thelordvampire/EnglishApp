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

/**
 *
 * @author Bao
 */
public class WordCollectionController implements Initializable{
    
   
    
    @FXML
    private void addCollection(ActionEvent event) 
    {
        
//        ObjectMapper mapper = new ObjectMapper();
//        Word word = new Word();
//
//        try {
//            mapper.writeValue(new File("c:\\user.json"), word);
//        } catch (IOException ex) {
//            Logger.getLogger(WordController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    @FXML
    private void updateCollection(ActionEvent event) 
    {
    }
    
    @FXML
    private void deleteCollection(ActionEvent event) 
    {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}
