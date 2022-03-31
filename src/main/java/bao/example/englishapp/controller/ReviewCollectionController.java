/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bao.example.englishapp.controller;

import bao.example.englishapp.out_library.ui.circular_progress.FunLevelGauge;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.tiennb.btl.manager.AppManager2;
import com.tiennb.btl.model.Question;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Bao
 */
public class ReviewCollectionController implements Initializable {
    
    @FXML
    private StackPane stackPaneClock;
    
    @FXML
    private JFXRadioButton resultA, resultB, resultC, resultD;
    
    @FXML
    private Label lbQuestion, lbNumberQuestion;
    
    @FXML
    private JFXButton btnStartReview;
    
    private ArrayList<Question> makeListQuestion;
    private int index=0;
    private String strResult="";
    private long lastTimerCall;
    private AnimationTimer timer;
    
    @FXML
    private void startReview(ActionEvent event) {
        if(makeListQuestion!=null)
        {
            if(btnStartReview.getText().equalsIgnoreCase("Bắt đầu"))
            {
                lbNumberQuestion.setText("1/5");
                btnStartReview.setText("Tiếp theo");
                Question question = makeListQuestion.get(index);
                resultA.setText(question.getDapanA());
                resultB.setText(question.getDapanB());
                resultC.setText(question.getDapanC());
                resultD.setText(question.getDapanD());
                lbQuestion.setText(question.getQuestion());
                lastTimerCall = System.currentTimeMillis();
                timer.start();
            }
            else if(btnStartReview.getText().equalsIgnoreCase("Tiếp theo"))
            {
                if(index<makeListQuestion.size())
                {
                    lbNumberQuestion.setText((index+1)+"/"+makeListQuestion.size());
                    if(checkResult(makeListQuestion.get(index)))
                    {
                        System.out.println("câu "+(index+1)+" đúng");
                        strResult+="câu "+(index+1)+" đúng\n";
                    }
                    else
                    {
                        System.out.println("câu "+(index+1)+" sai");
                        strResult+="câu "+(index+1)+" sai ";
                        System.out.println("đáp án: "+makeListQuestion.get(index).getResult()+".");
                        strResult+="đáp án: "+makeListQuestion.get(index).getResult()+".\n";
                    }
                    resetValue();
                    index++;
                    if(index<makeListQuestion.size())
                    {
                        Question question = makeListQuestion.get(index);
                        resultA.setText(question.getDapanA());
                        resultB.setText(question.getDapanB());
                        resultC.setText(question.getDapanC());
                        resultD.setText(question.getDapanD());
                        lbQuestion.setText(question.getQuestion());

                        lastTimerCall = System.currentTimeMillis();
                        timer.start();
                    }
                } else
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    //        alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Kết quả");
                    alert.setContentText(strResult);
                    Optional<ButtonType> result = alert.showAndWait();
                }
            }
        }
    }
    
    @FXML
    private ToggleGroup answer;
    
    private boolean checkResult(Question question)
    {
        JFXRadioButton selectedToggle =(JFXRadioButton) answer.getSelectedToggle();
        if(selectedToggle!=null)
            return selectedToggle.getText().equals(question.getResult());
        return false;
    }
    
    private void resetValue()
    {
        resultA.setSelected(false);
        resultB.setSelected(false);
        resultC.setSelected(false);
        resultD.setSelected(false);
        setAnswerStatus(AnswerStatus.ENABLE);
    }
    
    private enum AnswerStatus{
        ENABLE, DISABLE;
    }
    
    private void setAnswerStatus(AnswerStatus status)
    {
        boolean flag = true;
        if(status==AnswerStatus.ENABLE)
            flag = false;
        resultA.setDisable(flag);
        resultB.setDisable(flag);
        resultC.setDisable(flag);
        resultD.setDisable(flag);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lbNumberQuestion.setText("0/5");
        final FunLevelGauge gauge = new FunLevelGauge();
        gauge.setPrefSize(75, 75);
        gauge.setColor(Color.GREEN);
        stackPaneClock.getChildren().add(gauge);
        
        timer = new AnimationTimer() {
            @Override public void handle(long now) {
                now = System.currentTimeMillis();
                int d = (int) (now - lastTimerCall)/1000;
                if (d>=0 && d<=10  ) 
                    // d là số giây
                    gauge.setLevel(0.1*d);
                else
                    setAnswerStatus(AnswerStatus.DISABLE);
            }
        };
        
        try {
            AppManager2 manager = new AppManager2();
            ArrayList<Question> demoList = manager.makeListQuestion();
            makeListQuestion = new ArrayList<>();
            if(demoList.size()>5)
            {
//                makeListQuestion = (ArrayList<Question>) makeListQuestion.subList(0, 4);
                makeListQuestion.add(demoList.get(0));
                makeListQuestion.add(demoList.get(1));
                makeListQuestion.add(demoList.get(2));
                makeListQuestion.add(demoList.get(3));
                makeListQuestion.add(demoList.get(4));
            }
        } catch (IOException ex) {
            Logger.getLogger(ReviewCollectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
