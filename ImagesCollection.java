package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImagesCollection {

    private List<Image> listOfImagesForCurrentTab = new ArrayList<>();

    private void makeUniqCatalogsForEachTab(String tabName) throws IOException {

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

    private boolean getDirExistOrNot(String directory)  {

        String imagesPath = System.getProperty("user.dir") + "/images/" + directory;

        Path pathToCatalogWithImages = Paths.get(imagesPath);

        if(Files.exists(pathToCatalogWithImages)) {

            System.out.println("Exists: " + pathToCatalogWithImages);

            return true;

        }   else    {

            System.out.println("NotExists: " + pathToCatalogWithImages);

            return false;

        }

    }

    private static List<File> getFilesInDirectory(String directory) {

        String imagesPath = System.getProperty("user.dir") + "/images/" + directory;

        Path pathToCatalogWithImages = Paths.get(imagesPath);

        List<File> listOfFilesInDir = new ArrayList<File>();

        for(final File file: pathToCatalogWithImages.toFile().listFiles())  {

            listOfFilesInDir.add(file);

//            System.out.println("File in " + directory + ": " + file.toURI().toString());

        }

        return listOfFilesInDir;

    }

    public static int getCountOfFilesInDirectory(String directory) {

        return getFilesInDirectory(directory).size();

    }

    private void addFilesToImage(List<File> files) throws FileNotFoundException,IOException {

        if(files.size() != 0) {

            int countOfFiles = 0;

            for (File file : files) {

                System.out.println(file.toString());

                BufferedImage bufferedImage = ImageIO.read(file);

                listOfImagesForCurrentTab.add(SwingFXUtils.toFXImage(bufferedImage,null));

                countOfFiles++;

            }

        }   else    {

            System.out.println("Collection of file is empty! (May be directory is empty?)");

        }

    }

    public List<Image> getListOfImagesForCurrentTab(String tabName) {

        try {

            makeUniqCatalogsForEachTab(tabName);

        } catch(IOException e)   {

            e.printStackTrace();

        }

        if(getDirExistOrNot(tabName))    {

            try {

                addFilesToImage(getFilesInDirectory(tabName));

            } catch(Exception e)    {

                e.printStackTrace();

            }

        }

        return listOfImagesForCurrentTab;

    }

}
