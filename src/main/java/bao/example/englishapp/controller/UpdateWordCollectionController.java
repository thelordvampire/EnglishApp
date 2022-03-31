/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import com.jfoenix.controls.JFXTextField;
import com.tiennb.btl.manager.AppManager2;
import com.tiennb.btl.model.Word;
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
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import bao.example.englishapp.process.EditToUpdateDataProcess;

/**
 *
 * @author Bao
 */
public class UpdateWordCollectionController implements Initializable, ISendController {
    
    @FXML
    private ImageView image;
    
    @FXML
    private JFXTextField txtTiengAnh;
    
    @FXML
    private JFXTextField txtCachDoc;
    
    @FXML
    private JFXTextField txtTiengViet;
    
    private File imageFile;
    private DetailCollectionController detailColController;
    private Word updateWord;
    private String strTiengAnh;
    
    @Override
    public void setPreController(Initializable preController) {
        if(preController instanceof DetailCollectionController)
            this.detailColController = (DetailCollectionController) preController;
    }
    
    @FXML
    private void showImageShow(ActionEvent event) {
        FileChooser fileShow = new FileChooser();
        fileShow.setTitle("Chọn ảnh");
        imageFile = fileShow.showOpenDialog(null);
        image.setImage(new Image(imageFile.toURI().toString(), 100, 100, false, true));
    }
    
    @FXML
    private void editWordToColletion(ActionEvent event) {
        try {
            System.out.println("vao day 1");
            if(updateWord!=null && strTiengAnh!=null)
            {
                System.out.println("vao day 2");
                AppManager2 manager = new AppManager2();
                if(imageFile!=null)
                    manager.saveFile(imageFile);
                updateWord.setTa(txtTiengAnh.getText());
                updateWord.setTv(txtTiengViet.getText());
                updateWord.setCachDoc(txtCachDoc.getText());
                if(imageFile!=null)
                    updateWord.setImagePath("/images/"+imageFile.getName());
                manager.updateWord(strTiengAnh, updateWord, detailColController.getSelectedColName());
                
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
                detailColController.loadColWord();
            }
        } catch (IOException ex) {
            Logger.getLogger(AddNewWordToCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateWord = EditToUpdateDataProcess.getUpdateWord();
        if(updateWord!=null)
        {
            txtTiengAnh.setText(updateWord.getTa());
            txtTiengViet.setText(updateWord.getTv());
            txtCachDoc.setText(updateWord.getCachDoc());
            image.setImage(new Image(updateWord.getImagePath()));
            strTiengAnh = updateWord.getTa();
        }
    }
}
