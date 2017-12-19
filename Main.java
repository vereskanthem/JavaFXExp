package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//import java.awt.*;

public class Main extends Application {

//    TabPane tabsOnMainScreen;
//    Tab     activeTab;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("JavaFXExam");
        primaryStage.setScene(new Scene(root, 500, 275));

//        tabsOnMainScreen = new TabPane();
//        activeTab        = new Tab();
//
//        activeTab.setText("1");
//        activeTab.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));

        primaryStage.show();

    }
//
//    private AddTabsToRootWindow()   {
//
//
//
//    }

    public void CreateInterfaceForTab(int id)   {

        HBox currentTabBox = new HBox();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
