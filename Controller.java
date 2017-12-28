package sample;

import com.sun.glass.ui.SystemClipboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Controller {

    @FXML
    AnchorPane mainPane;

    @FXML
    Button nextButton;

    @FXML
    Button prevButton;

    @FXML
    Button addNewImage;

    @FXML
    Button showImageFromNumberTestButton;

    @FXML
    TabPane tabsOnMainScreen;

    public static List<String> listOfExistingTabNames = new ArrayList<String>(Arrays.asList("Ещё что-то","Природа","Птицы","Цветы","Море","Любимое","Все"));

    private static List<ImagesCollection> listOfImagesForCurrentTab = new ArrayList<ImagesCollection>();
    private static List<GridPane> listOfGridPanesForDifferentTabs   = new ArrayList<GridPane>();

    public static Map<Integer, String> mapOfExitingTabNames = new HashMap<Integer, String>();

    public static int[] currentShowNumberForTab = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

//    List<Tab>    listOfExistingTabs     = new ArrayList<Tab>();

    public void makeUniqCatalogsForEachTab(String tabName) throws IOException {

        String imagesPath = System.getProperty("user.dir") + "/images/" + tabName;

        Path pathToCatalogWithImages = Paths.get(imagesPath);

        if(Files.exists(pathToCatalogWithImages))   {

        }   else   {

            try {

                Files.createDirectory(pathToCatalogWithImages);

            } catch(IOException e)    {

                System.out.println("Error: makeUniqCatalogsForEachTab. Cannot create directory: " + pathToCatalogWithImages);

            }

            System.out.println("Create: " + pathToCatalogWithImages);

        }

    }

    private void makeTabsAndContent() {

        int paneCount;

        for(paneCount = 0; paneCount< listOfExistingTabNames.size(); paneCount++)  {

            listOfImagesForCurrentTab.add(new ImagesCollection());

            listOfGridPanesForDifferentTabs.add(new GridPane());

            mapOfExitingTabNames.put(paneCount, listOfExistingTabNames.get(paneCount));

        }

        setGridForAllTabs();

        paneCount = 0;

        for(String tabName: listOfExistingTabNames) {

            try {

                makeUniqCatalogsForEachTab(tabName);

            } catch(IOException e)   {

                e.printStackTrace();

            }

            showImagesFromNumber(tabName, 0);

            tabsOnMainScreen.getTabs().add(new Tab(tabName, listOfGridPanesForDifferentTabs.get(paneCount)));

            paneCount++;

        }

        paneCount = 0;

        for(Tab currentTab: tabsOnMainScreen.getTabs()) {

            currentTab.setId(listOfExistingTabNames.get(paneCount));

            paneCount++;

        }

//        tabsOnMainScreen.getTabs().get(0);
//        tabsOnMainScreen.getTabs().get(1).setContent(grid);

    }

    private void setGridForAllTabs()   {

        int count = 0;

        GridPane currentGrid;

        for(Integer tabNum: mapOfExitingTabNames.keySet())  {

            currentGrid = listOfGridPanesForDifferentTabs.get(tabNum);

            currentGrid.setGridLinesVisible(true);

//            currentGrid.setId(tabName + "Grid");

            currentGrid.getRowConstraints().add(new RowConstraints(200));

            currentGrid.getColumnConstraints().add(new ColumnConstraints(300)); // column N is 300 wide
            currentGrid.getColumnConstraints().add(new ColumnConstraints(300)); // column N is 300 wide
            currentGrid.getColumnConstraints().add(new ColumnConstraints(300)); // column N is 300 wide

            currentGrid.setPadding(new Insets(10, 10, 10, 40));
            currentGrid.setHgap(10);

            count++;

        }

        System.out.println("Count: " + count);

    }

    private static void showWindowWithImage(Image currentImage)  {

        ImageView currentImageView = new ImageView(currentImage);

        currentImageView.setFitWidth(300);
        currentImageView.setFitHeight(300);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(currentImageView);

//                Scene scene = new Scene(stackPane);
        Stage stage = new Stage();
        stage.setScene(new Scene(stackPane,300,300));
        stage.show();

    }

    public static void showImagesFromNumber(String tabName, int showFromNumber)    {

        int paneCount = 0;

        Image currentImage;

        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;

        ImageView nullImageView = new ImageView();

        nullImageView.setImage(null);

        for(Map.Entry currentMapEntry: mapOfExitingTabNames.entrySet())  {

            if(currentMapEntry.getValue() == tabName)  {

                paneCount = (int)currentMapEntry.getKey();

                System.out.println("Current Tab is " + tabName + " : " + paneCount);

            }

        }

        GridPane currentGrid = listOfGridPanesForDifferentTabs.get(paneCount);
        ImagesCollection imagesOnGrid = listOfImagesForCurrentTab.get(paneCount);

        int countOfImagesForTab = ImagesCollection.getCountOfFilesInDirectory(tabName);

        int iterationCount = 0;

        currentGrid.getChildren().clear();

        List<Image> listOfImages = imagesOnGrid.getListOfImagesForCurrentTab(tabName);

//        System.out.println("showImagesFromNumber: listOfImages.size() = " + listOfImages.size());

//        for(Image img: listOfImages)   {
//
//            showWindowWithImage(img);
//
//        }

        System.out.println("countOfImagesForTab = " + countOfImagesForTab);

        for(int imgCount = showFromNumber; imgCount < countOfImagesForTab; imgCount++) {

            System.out.println("showImagesFromNumber: iterationCount = " + iterationCount);
//
            if(iterationCount == 0)   {

                currentImage = listOfImages.get(0);

//                showWindowWithImage(currentImage);

//                currentGrid.setRowIndex(nullImageView, 0);
//                currentGrid.setColumnIndex(nullImageView, 0);

                imageView1 = new ImageView(currentImage);

                imageView1.setFitHeight(200);
                imageView1.setFitWidth(300);

                GridPane.setRowIndex(imageView1, 0);
                GridPane.setColumnIndex(imageView1, 0);

                currentGrid.getChildren().add(imageView1);

            }

            if(iterationCount == 1)   {

                currentImage = listOfImages.get(1);

//                showWindowWithImage(currentImage);

//                currentGrid.setRowIndex(nullImageView, 0);
//                currentGrid.setColumnIndex(nullImageView, 1);
//
//                currentGrid.getChildren().add(nullImageView);

//                currentGrid.getChildren().add(nullImageView);

                imageView2 = new ImageView(currentImage);

                imageView2.setFitHeight(200);
                imageView2.setFitWidth(300);

                GridPane.setRowIndex(imageView2, 0);
                GridPane.setColumnIndex(imageView2, 1);

                currentGrid.getChildren().add(imageView2);

            }

            if(iterationCount == 2)   {

                currentImage = listOfImages.get(imgCount);

//                showWindowWithImage(currentImage);

//                currentGrid.setRowIndex(nullImageView, 0);
//                currentGrid.setColumnIndex(nullImageView, 2);

                //currentGrid.getChildren().add(nullImageView);

                imageView3 = new ImageView(currentImage);

                imageView3.setFitHeight(200);
                imageView3.setFitWidth(300);

                GridPane.setRowIndex(imageView3, 0);
                GridPane.setColumnIndex(imageView3, 2);

                currentGrid.getChildren().add(imageView3);

            }

            iterationCount++;

        }

    }

    public void showImageFromNumberTestButton(ActionEvent actionEvent) {

        final String activeTabId = tabsOnMainScreen.getSelectionModel().getSelectedItem().getId();

        int activeTabNumber = getTabNumberById(activeTabId);

        showImagesFromNumber(activeTabId, 0);

    }

    private int getTabNumberById(String activeTabId) {

        int activeTabNumber = 0;

        for(Map.Entry tabEntry : mapOfExitingTabNames.entrySet())    {

            if(tabEntry.getValue() == activeTabId)  {

                activeTabNumber = (int) tabEntry.getKey();

            }

        }

        return activeTabNumber;

    }

    public void slideToPrevImage(ActionEvent actionEvent) {

        final String activeTabId = tabsOnMainScreen.getSelectionModel().getSelectedItem().getId();

        int activeTabNumber = getTabNumberById(activeTabId);

        for(Map.Entry tabEntry : mapOfExitingTabNames.entrySet())    {

            if(tabEntry.getValue() == activeTabId)  {

                activeTabNumber = (int) tabEntry.getKey();

            }

        }

        if(currentShowNumberForTab[activeTabNumber] > 0) {

            currentShowNumberForTab[activeTabNumber] -= 1;

            System.out.println("activeTabId = " + activeTabId + "; currentShowNumber: " + currentShowNumberForTab[activeTabNumber]);

            showImagesFromNumber(activeTabId, currentShowNumberForTab[activeTabNumber]);

        }

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//
//        thread.start();

    }

    public void slideToNextImage(ActionEvent actionEvent) {

        final String activeTabId = tabsOnMainScreen.getSelectionModel().getSelectedItem().getId();

        final int countOfFilesInDirectory = ImagesCollection.getCountOfFilesInDirectory(activeTabId);

        int activeTabNumber = getTabNumberById(activeTabId);

        if((countOfFilesInDirectory > 2)) {

            if((countOfFilesInDirectory - currentShowNumberForTab[activeTabNumber]) > 3)    {

                currentShowNumberForTab[activeTabNumber] += 1;

                if(currentShowNumberForTab[activeTabNumber] < (countOfFilesInDirectory - 1)) {

                    System.out.println("activeTabId = " + activeTabId + "; currentShowNumber: " + currentShowNumberForTab[activeTabNumber]);
                    System.out.println("countOfFilesInDirectory = " + countOfFilesInDirectory);

                    showImagesFromNumber(activeTabId, currentShowNumberForTab[activeTabNumber]);

                }

            }

        }

    }

    public void addNewImage(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("AddForm.fxml"));

        Stage stage = new Stage();

        stage.setTitle("Добавить изображение");
        stage.setScene(new Scene(root, 370, 210));
        stage.setResizable(false);
        stage.show();

//        System.out.println("ActionName: " + actionEvent.getEventType().getName());

    }

    public void initialize() {

        mainPane.setTopAnchor(tabsOnMainScreen,1.0);

        mainPane.setLeftAnchor(tabsOnMainScreen,0.0);
        mainPane.setRightAnchor(tabsOnMainScreen,0.0);

        mainPane.setTopAnchor(prevButton,150.0);
        mainPane.setLeftAnchor(prevButton,-30.0);

        mainPane.setTopAnchor(nextButton,150.0);
        mainPane.setRightAnchor(nextButton,-30.0);

        mainPane.setBottomAnchor(addNewImage,20.0);
        mainPane.setLeftAnchor(addNewImage,20.0);

        mainPane.setBottomAnchor(showImageFromNumberTestButton,20.0);
        mainPane.setLeftAnchor(showImageFromNumberTestButton,180.0);

        tabsOnMainScreen.setCenterShape(true);

//        mainPane.setLeftAnchor(nextButton,10.0);

        nextButton.setRotate(270);
        prevButton.setRotate(90);


//        mainPane.(prevButton,1.0);

        makeTabsAndContent();

    }

}
