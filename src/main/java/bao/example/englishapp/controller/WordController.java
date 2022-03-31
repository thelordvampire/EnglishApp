/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import bao.example.englishapp.jpa.JPAUtil;
import bao.example.englishapp.model.Word;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import bao.example.englishapp.process.SceneProcess;
import bao.example.englishapp.process.WordProcessImp;


/**
 *
 * @author Bao
 */
public class WordController implements Initializable {
    
    @FXML
    private TextField txtEWord;
    
    @FXML
    private TextField txtVWord;
    
    @FXML
    private Label lbListWord;
    
    @FXML
    private void toScene(ActionEvent event)  
    {
        new SceneProcess().toHomeScene((Stage)((javafx.scene.control.Button)event.getSource()).getScene().getWindow());
    }
    
    @FXML
    private void addWord(ActionEvent event) 
    {
        WordProcessImp wordProcess = new WordProcessImp();
//        wordProcess.add(new Word(txtEWord.getText(), txtVWord.getText()));
//        Session session = HibernateUtil.getSessionFactory().openSession();

//        session.beginTransaction();


//        session.save(word);
//        session.getTransaction().commit();
        
        
//        try
//        {
//            FileInputStream excelFile = new FileInputStream(new File("src/main/resources/data/data.xlsx"));
//            Workbook workbook = new XSSFWorkbook(excelFile);
//            Sheet sheet = workbook.getSheetAt(0);
//            int rowNum = 0;
//
//            Row row = sheet.createRow(rowNum++);
//            int colNum = 0;
//            Cell cell = row.createCell(colNum++);
//            cell.setCellValue((String) txtEWord.getText());
//            cell = row.createCell(colNum++);
//            cell.setCellValue((String) txtVWord.getText());
//            FileOutputStream outputStream = new FileOutputStream("src/main/resources/data/data.xlsx");
//            workbook.write(outputStream);
//            workbook.close();
//        
//        }
//        catch(FileNotFoundException ex)
//        {
//            System.out.println(ex.getMessage());
//        } catch (IOException ex) {
//            Logger.getLogger(WordController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    @FXML
    private void updateWord(ActionEvent event) 
    {
    }
    
    @FXML
    private void deleteWord(ActionEvent event) 
    {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        System.out.println("word controller");
//        System.out.println(location.getPath());
//        System.out.println(resources.getBaseBundleName());
        if (location.getPath().contains("WordScene") && !location.getPath().contains("AddWordScene")) {
            new Thread(() -> {
                WordProcessImp wordProcess = new WordProcessImp();
                List<Word> listWord = wordProcess.selectAll();
                StringBuilder str = new StringBuilder();
                listWord.forEach((word) -> {
                    str.append(word.toString()).append("\n");
                });

                Platform.runLater(() -> {
                    lbListWord.setText(str.toString());
                });
            }).start();
        }
    }
    
}
