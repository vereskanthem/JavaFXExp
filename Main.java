package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

//        Image i  = new Image("https://static.pexels.com/photos/67517/pexels-photo-67517.jpeg");
//        ImageView iv = new ImageView();
//
//        iv.setImage(i);
//        iv.setFitWidth(100);
//        iv.setFitHeight(100);

//        root.getChildrenUnmodifiable().addAll(iv);

        primaryStage.setTitle("JavaFXExam");
        primaryStage.setScene(new Scene(root, 700, 400));

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
