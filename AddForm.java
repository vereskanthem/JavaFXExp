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

            if (!choosedCategory.equals("")) {

                if (openedFile != null) {

                    System.out.println("Copy To: " + outputDirectoryName);

                    if(outDir != null) {

                        FileUtils.copyFileToDirectory(openedFile, outDir);

                    }

                    if(outDirForAll != null) {

                        System.out.println(outDirForAll.getName() + " == " + openedFile.getName());
                        FileUtils.copyFileToDirectory(openedFile, outDirForAll);

                    }

                }

                Controller.showImagesFromNumber(choosedCategory,0);

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
