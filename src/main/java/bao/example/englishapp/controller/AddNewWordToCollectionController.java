/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.fxml.builder.JavaFXImageBuilder;
import com.tiennb.btl.manager.AppManager2;
import com.tiennb.btl.model.Word;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Bao
 */
public class AddNewWordToCollectionController implements Initializable, ISendController {
    
    @FXML
    private ImageView image;
    
    @FXML
    private JFXTextField txtTiengAnh;
    
    @FXML
    private JFXTextField txtCachDoc;
    
    @FXML
    private JFXTextField txtTiengViet;
    
    private DetailCollectionController detailcolController;
    private File imageFile;
    
    @Override
    public void setPreController(Initializable detailcolController)
    {
        if(detailcolController instanceof DetailCollectionController)
            this.detailcolController = (DetailCollectionController) detailcolController; 
    }
    
    @FXML
    private void showImageShow(ActionEvent event) {
        FileChooser fileShow = new FileChooser();
        fileShow.setTitle("Chọn ảnh");
        imageFile = fileShow.showOpenDialog(null);
        if(imageFile!= null) {
            image.setImage(new Image(imageFile.toURI().toString(), 100, 100, false, true));
        }
    }
    
    @FXML
    private void addWordToCollection(ActionEvent event) {
        try {
            String selectedColName = detailcolController.getSelectedColName();
            if(selectedColName!=null)
            {
                AppManager2 manager = new AppManager2();
                manager.saveFile(imageFile);
                Word word = new Word(txtTiengAnh.getText(), txtTiengViet.getText(), 
                        txtCachDoc.getText(), "/images/"+imageFile.getName());
                manager.addWordToColectionWord(word, selectedColName);
                
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
                detailcolController.loadColWord();
            }
        } catch (IOException ex) {
            Logger.getLogger(AddNewWordToCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
