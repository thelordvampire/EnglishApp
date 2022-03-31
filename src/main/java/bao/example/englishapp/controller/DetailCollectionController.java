/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import com.jfoenix.controls.JFXComboBox;
import com.tiennb.btl.manager.AppManager2;
import com.tiennb.btl.model.Word;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import bao.example.englishapp.process.FileProcess;
import bao.example.englishapp.process.SceneProcess;

/**
 *
 * @author Bao
 */
public class DetailCollectionController implements Initializable {
    
    @FXML
    private GridPane gridPane;
    
    @FXML
    private VBox boxAddWord;
    
    @FXML
    private JFXComboBox comboListCollection;
    
    @FXML
    private void toAlertDeleteCollection(ActionEvent event) {
        Object item = comboListCollection.getSelectionModel().getSelectedItem();
        if(item!=null)
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
    //        alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Bạn muốn xóa chủ đề: "+item.toString());
    //        alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    AppManager2 manager = new AppManager2();
                    manager.deleteColection(item.toString());
                    loadCollectionName();
                } catch (IOException ex) {
                    Logger.getLogger(DetailCollectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
//        else
//        {
//            // thông báo chưa chọn chủ đề
//        }
    }
    
    @FXML
    private void toHomeScene(MouseEvent event) {
        SceneProcess.toHomeScene((Stage)((ImageView)event.getSource()).getScene().getWindow());
    }
            
    @FXML
    private void toMergeCollection(ActionEvent event) {
        SceneProcess.newMergeCollection(this);
    }
    
    @FXML
    private void toAddNewWordToCollection(ActionEvent event) {
        SceneProcess.newAddNewWordToCollection(this);
    }
    
    @FXML
    private void toAddNewCollection(ActionEvent event) {
        SceneProcess.newAddNewCollection(this);
    }
    
    @FXML
    private void loadColWord(ActionEvent event) {
        try {
            AppManager2 manager = new AppManager2();
            refreshListWordDetailCollectionScene(manager.getAllWordFromColection(comboListCollection.
                    getSelectionModel().getSelectedItem().toString()));
        } catch (IOException ex) {
            Logger.getLogger(DetailCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadColWord() {
        try {
            if(comboListCollection.getSelectionModel().getSelectedItem()!=null)
            {
                AppManager2 manager = new AppManager2();
                refreshListWordDetailCollectionScene(manager.getAllWordFromColection(comboListCollection.
                        getSelectionModel().getSelectedItem().toString()));
            }
        } catch (IOException ex) {
            Logger.getLogger(DetailCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void refreshListWordDetailCollectionScene(ArrayList<Word> listWord)
    {
        clearListWordDetailCollectionScene();
        listWord.forEach((word) -> {loadNewWord(word);});
        addBoxAddWord();
    }
    
    private void clearListWordDetailCollectionScene()
    {
        gridPane.getChildren().clear();
    }
    
    private void addBoxAddWord()
    {
        int n = gridPane.getChildren().size();
        int rowIndex = n/4;
        int colIndex = n%4;
        gridPane.setPrefHeight((rowIndex+1)*400+100);
        gridPane.add(boxAddWord,colIndex,rowIndex);
    }
    
    private void loadNewWord(Word word)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/DetailCollectionItem.fxml"));
            AnchorPane nodeItem =(AnchorPane)  fxmlLoader.load();
            
            
            ISendController controller = (ISendController) fxmlLoader.getController();
            controller.setPreController(this);

            ((ImageView) nodeItem.getChildren().get(0)).setImage(
                    new Image((new File("src/main/resources"+word.getImagePath())).toURI().toString()));
            ((Label) nodeItem.getChildren().get(1)).setText(word.getTa());
            ((Label) nodeItem.getChildren().get(2)).setText(word.getCachDoc());
            ((Label) nodeItem.getChildren().get(3)).setText(word.getTv());

            int n = gridPane.getChildren().size();
            int rowIndex = n/4;
            int colIndex = n%4;
            gridPane.add(nodeItem,colIndex,rowIndex);
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
            comboListCollection.setItems(list);
        } catch (IOException ex) {
            Logger.getLogger(DetailCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getSelectedColName()
    {
        if(comboListCollection.getSelectionModel().getSelectedItem()!=null)
           return comboListCollection.getSelectionModel().getSelectedItem().toString();
        return null;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCollectionName();
//        if (location.getPath().contains("DetailCollectionScene")) {
            
//        }
        
//        System.out.println("detail collection");
//try{
//        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/fxml/DetailCollectionItem.fxml"));
//        Node nodeItem = SceneProcess.loadScene();
//        gridDetailCollection.add(newLoadedPane,0,0);
//        gridDetailCollection.add(nodeItem,0,0);
//        gridDetailCollection.add(nodeItem,1,0);
//    }catch(IOException e)
//    {
//        System.out.println(e.getMessage());
//    }
            
    }
    
}
