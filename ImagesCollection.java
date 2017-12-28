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
import java.util.Collections;
import java.util.List;

class ImagesCollection {

    private List<Image> listOfImagesForCurrentTab = new ArrayList<>();

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

        Collections.sort(listOfFilesInDir);

        return listOfFilesInDir;

    }

    public static int getCountOfFilesInDirectory(String directory) {

        return getFilesInDirectory(directory).size();

    }

    private void addFilesToImage(List<File> files) throws FileNotFoundException,IOException {

        BufferedImage bufferedImage = null;

        if(files.size() != 0) {

            int countOfFiles = 0;

            List<File> filesBuffer = files;

            Collections.sort(filesBuffer);

            listOfImagesForCurrentTab.clear();

            for (File file : filesBuffer) {

                bufferedImage = ImageIO.read(file);

                listOfImagesForCurrentTab.add(SwingFXUtils.toFXImage(bufferedImage,null));

                countOfFiles++;

            }

            System.out.println("addFilesToImage: countOfFiles = " + countOfFiles);

        }   else    {

            System.out.println("Collection of file is empty! (May be directory is empty?)");

        }

    }

    public List<Image> getListOfImagesForCurrentTab(String tabName) {

        List<File> fileList = getFilesInDirectory(tabName);

        String fileName;

        int fileCount = 0;

        for(File file: fileList)    {

            fileName = fileList.get(fileCount).getName();

            System.out.println("getListOfImagesForCurrentTab: File = " + fileName);

            fileCount++;

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
