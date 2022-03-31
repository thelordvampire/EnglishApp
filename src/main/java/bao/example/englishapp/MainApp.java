package bao.example.englishapp;

import static bao.example.englishapp.value.AppConstant.APP_TITLE;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/HomeScene.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle(APP_TITLE);
        stage.setScene(scene);
//        stage.setOnCloseRequest((WindowEvent we) -> {
//            JPAUtil.shutdown();//dong ket noi toi database
//        });
        stage.show();
        
        //tao ket noi toi database
//        JPAUtil.createManager();
        
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
