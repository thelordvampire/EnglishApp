/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.process;

import bao.example.englishapp.controller.DetailCollectionController;
import bao.example.englishapp.controller.ISendController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Bao
 */
public class SceneProcess {
            
    public static void newLearnCollection() {
        newScene("/fxml/LearnCollectionScene.fxml");
    }
                    
    public static void newReviewCollection() {
        newScene("/fxml/ReviewCollectionScene.fxml");
    }
    
    public static void newMergeCollection(DetailCollectionController detailColController) {
        newScene("/fxml/MergeCollectionScene.fxml", detailColController);
    }
            
    public static void newAddNewCollection(DetailCollectionController detailColController) {
        newScene("/fxml/AddCollectionScene.fxml", detailColController);
    }
            
    public static void newAddNewWordToCollection(DetailCollectionController detailColController) {
        newScene("/fxml/AddNewWordToCollectionScene.fxml", detailColController);
    }
    
    public static void newUpdateWordToCollection(DetailCollectionController detailColController) {
        newScene("/fxml/UpdateWordToCollectionScene.fxml", detailColController);
    }
    
    private static void newScene(String url)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneProcess.class.getResource(url));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));  
            stage.show();
        } 
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void newScene(String url, Initializable controller)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneProcess.class.getResource(url));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));  
            stage.show();
            
            ISendController a = (ISendController) fxmlLoader.getController();
            a.setPreController(controller);
        } 
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void toExit() {
        
    }
    
    public static void toDetailCollectionScene(Stage stage) {
        loadScene(stage, "/fxml/DetailCollectionScene.fxml");
        //khoong hieu tai sao doi ten lai khong chay duoc
    }
            
    public static void toUserScene(Stage stage) {
        loadScene(stage, "/fxml/UserScene.fxml");
    }
    
    public static void toAdminScene(Stage stage) {
        loadScene(stage, "/fxml/AdminScene.fxml");
    }
    
    public static void toHomeScene(Stage stage) {
        loadScene(stage, "/fxml/HomeScene.fxml");
    }
    
    public static void toAddWordScene(Stage stage) {
        loadScene(stage, "/fxml/AddWordScene.fxml");
    }
    
    public static void toShowAllWordScene(Stage stage) {
        loadScene(stage, "/fxml/WordScene.fxml");
    }
    
    private static void loadScene(Stage stage, String url)
    {
//        Platform.runLater(() -> {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(SceneProcess.class.getResource(url));
                Parent root = (Parent) fxmlLoader.load();
                stage.setScene(new Scene(root));  
            } 
            catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
//        });
    }
}
