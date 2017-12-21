package sample;

import com.sun.glass.ui.SystemClipboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    @FXML
    AnchorPane mainPane;

    @FXML
    TabPane tabsOnMainScreen;

    List<String> listOfExistingTabNames = new ArrayList<String>(Arrays.asList("Природа","Птицы","Цветы","Море","Любимое","Все"));
//    List<Tab>    listOfExistingTabs     = new ArrayList<Tab>();
    @FXML
    public void makeTabsAndContent() {

//        List<HBox> listOfHboxForDifferentTabs            = new ArrayList<HBox>();
        List<GridPane> listOfGridPanesForDifferentTabs   = new ArrayList<GridPane>();
        List<ImagesCollection> listOfImagesForCurrentTab = new ArrayList<ImagesCollection>();

//        List<Button> prevButtonForTab = new ArrayList<>();
//        List<Button> nextButtonForTab = new ArrayList<>();

        for(int paneCount = 0; paneCount< listOfExistingTabNames.size(); paneCount++)  {

            listOfImagesForCurrentTab.add(new ImagesCollection());

            listOfGridPanesForDifferentTabs.add(new GridPane());

        }

        int paneCount = 0;

        for(String tabName: listOfExistingTabNames) {

            GridPane currentGrid = listOfGridPanesForDifferentTabs.get(paneCount);
            ImagesCollection imagesOnGrid = listOfImagesForCurrentTab.get(paneCount);

            currentGrid.setId(tabName + "Grid");

            int countOfImagesForTab = ImagesCollection.getCountOfFilesInDirectory(tabName);

            currentGrid.setGridLinesVisible(true);

            for(int imgCount = 0; imgCount < countOfImagesForTab; imgCount++) {

                Image currentImage = imagesOnGrid.getListOfImageViewersForCurrentTab(tabName).get(imgCount);

                ImageView currentView = new ImageView(currentImage);

                currentView.setFitHeight(100);
                currentView.setFitWidth(100);

                currentGrid.getChildren().add(currentView);

                currentGrid.getColumnConstraints().add(new ColumnConstraints(200)); // column N is 100 wide

                currentGrid.setRowIndex(currentView, 0);
                currentGrid.setColumnIndex(currentView, imgCount);

            }

            tabsOnMainScreen.getTabs().add(new Tab(tabName, listOfGridPanesForDifferentTabs.get(paneCount)));

            paneCount++;

        }



//        listOfGridPanesForDifferentTabs.

//        GridPane grid = new GridPane();

//        grid.getChildren().addAll(new Text("1"));
//
//        tabsOnMainScreen.getTabs().get(0);
//        tabsOnMainScreen.getTabs().get(1).setContent(grid);

    }

    public void slideToPrevImage(ActionEvent actionEvent) {
    }

    public void slideToNextImage(ActionEvent actionEvent) {
    }

    public void initialize() {

        mainPane.setTopAnchor(tabsOnMainScreen,1.0);
        mainPane.setLeftAnchor(tabsOnMainScreen,0.0);
        mainPane.setRightAnchor(tabsOnMainScreen,0.0);

        makeTabsAndContent();

    }

}
