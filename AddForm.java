package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddForm {

    @FXML
    public TextFlow addFormText;
    public ComboBox categories;
    public Button addButton;

    private File openedFile;

//    private Map<Integer, String> mapOfExitingTabNames = new HashMap<Integer, String>();

//    private void setCategory()  {
//
//
//
//    }

    private void setListOfCategories()  {

        int countOfTabs = Controller.listOfExistingTabNames.size();

        String categoryName;

        for(int counter = 0; counter < countOfTabs; counter++)  {

            categoryName = Controller.listOfExistingTabNames.get(counter);

//            mapOfExitingTabNames.put(counter, categoryName);

            if(!Objects.equals(categoryName, "Все")) {

                categories.getItems().add(categoryName);

            }

        }

    }

    public void chooseImageToAdd(ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Выбрать файл");

        Stage stage = new Stage();

        openedFile = fileChooser.showOpenDialog(stage);

        if(openedFile != null)    {

            System.out.println("Opened File: " + openedFile.getName());

        }

    }

    public void addImageToCategory(ActionEvent actionEvent) {

        Stage stage = (Stage) addButton.getScene().getWindow();

        try {

            String choosedCategory = categories.getSelectionModel().getSelectedItem().toString();

            String outputDirectoryName = "images/" + choosedCategory + "/";
            String outputDirectoryNameForAll = "images/Все/";

            File outDir = new File(outputDirectoryName);
            File outDirForAll = new File(outputDirectoryNameForAll);

            DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-HHmmss");

            Date date = new Date();

            File sourceFile = new File(outputDirectoryName + openedFile.getName());
            File resultFile = new File(outputDirectoryName + dateFormat.format(date) + openedFile.getName());

            File sourceFileForAll = new File(outputDirectoryNameForAll + openedFile.getName());
            File resultFileForAll = new File(outputDirectoryNameForAll + dateFormat.format(date) + openedFile.getName());

            if (!choosedCategory.equals("")) {

                if (openedFile != null) {

                    if(outDir != null) {

                        System.out.println("Copy To: " + outputDirectoryName);

                        FileUtils.copyFileToDirectory(openedFile, outDir);

                        FileUtils.moveFile(sourceFile, resultFile);

                    }

                    if(outDirForAll != null) {

                        System.out.println("Copy To All: " + outputDirectoryNameForAll);

//                        System.out.println(outDirForAll.getName() + " == " + openedFile.getName());
                        FileUtils.copyFileToDirectory(openedFile, outDirForAll);

                        FileUtils.moveFile(sourceFileForAll, resultFileForAll);

                    }

                }

                Integer categoryIndex = 0;

                for(Map.Entry entry: Controller.mapOfExitingTabNames.entrySet())    {

                    if(entry.getValue().equals(choosedCategory)) {

                        categoryIndex = (Integer) entry.getKey();

                    }

                }

                System.out.println("categoryIndex: " + categoryIndex);

//                Controller.showImagesFromNumber(choosedCategory,Controller.currentShowNumberForTab[categoryIndex]);
                Controller.showImagesFromNumber(choosedCategory,0);
                Controller.showImagesFromNumber("Все",0);

            }

        } catch(Exception e)    {

            System.out.println("Error: addImageToCategory");
            e.printStackTrace();

        }

        stage.close();

    }

    public void initialize()    {

        addFormText.getChildren().add(new Text("Добавить из компьютера в категорию"));

        setListOfCategories();

    }

}
