import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) {launch(args);}
    public void start(Stage stage1){
        stage1.setWidth(500);
        stage1.setHeight(500);
        stage1.setTitle("Laboratoire 7");
        Menu importer = new Menu("Importer");
        Menu exporter = new Menu("Exporter");
        MenuItem ligne = new MenuItem("Lignes");
        MenuItem barre = new MenuItem("Barres");
        MenuItem region = new MenuItem("Régions");
        MenuItem png = new MenuItem("png");
        MenuItem gif = new MenuItem("gif");
        importer.getItems().addAll(ligne,barre,region);
        exporter.getItems().addAll(png,gif);
        MenuBar menu = new MenuBar(importer,exporter);
        BorderPane root = new BorderPane();
        Chart[] chart = new Chart[1];
        root.setTop(menu);
        Scene scene1 = new Scene(root);
        stage1.setScene(scene1);
        stage1.show();
        ligne.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Veuiller sélectionner un fichier de données");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("fichier *.dat","*.dat")
            );
            File fichier = fc.showOpenDialog(stage1);
            chart[0] = Lignes(fichier);
            root.setCenter(chart[0]);
        });
        region.setOnAction(event ->{
            FileChooser fc = new FileChooser();
            fc.setTitle("Veuiller sélectionner un fichier de données");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("fichier *.dat","*.dat")
            );
            File fichier = fc.showOpenDialog(stage1);
            chart[0] = Régions(fichier);
            root.setCenter(chart[0]);
        });
        barre.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Veuiller sélectionner un fichier de données");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("fichier *.dat","*.dat")
            );
            File fichier = fc.showOpenDialog(stage1);
            chart[0] = Barres(fichier);
            root.setCenter(chart[0]);
        });
        png.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Veuiller sélectionner un fichier de données");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("fichier *.png","*.png")
            );
            File fichier = fc.showSaveDialog(stage1);
            saveAsPng(chart[0],fichier.getPath());
        });
        gif.setOnAction(event -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Veuiller sélectionner un fichier de données");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("fichier *.gif","*.gif")
            );
            File fichier = fc.showSaveDialog(stage1);
            saveAsGif(chart[0],fichier.getPath());

    });
    }
    public void saveAsPng(Chart chart, String path) {
        WritableImage image = chart.snapshot(new SnapshotParameters(), null);
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveAsGif(Chart chart, String path) {
        WritableImage image = chart.snapshot(new SnapshotParameters(), null);
        File file = new File(path);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "gif", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BarChart<String, Number> Barres(File f){
        String[] listeM = new String[8];
        String[] listeT = new String[8];
        try{
            BufferedReader entree = new BufferedReader(
                    new FileReader(f));
            String mois = entree.readLine();
            String température = entree.readLine();
            listeM = mois.split(", ");
            listeT = température.split(". ");
            for(int i=0;i<8;i++){
                System.out.println(listeM[i]+ ","+listeT[i]);
            }
        }catch(Exception exception1){
        }
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mois");
        yAxis.setLabel("Température");
        final BarChart<String,Number> root =
                new BarChart<String, Number>(xAxis,yAxis);
        root.setTitle("Barre");
        //adding the data
        XYChart.Series series = new XYChart.Series();
        series.setName("info 1");
        for(int i=0;i<8;i++) {
            series.getData().add(new XYChart.Data(listeM[i], Integer.parseInt(listeT[i])));
        }
        root.getData().addAll(series);
        return root;
    }
    public static AreaChart<String, Number> Régions(File f){
        String[] listeM = new String[8];
        String[] listeT = new String[8];
        try{
            BufferedReader entree = new BufferedReader(
                    new FileReader(f));
            String mois = entree.readLine();
            String température = entree.readLine();
            listeM = mois.split(", ");
            listeT = température.split(". ");
            for(int i=0;i<8;i++){
                System.out.println(listeM[i]+ ","+listeT[i]);
            }
        }catch(Exception exception1){
        }
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mois");
        yAxis.setLabel("Température");

        final AreaChart<String,Number> root =
                new AreaChart<String,Number>(xAxis,yAxis);
        root.setTitle("Régions");
        //adding the data
        XYChart.Series series = new XYChart.Series();
        series.setName("info 1");
        for(int i=0;i<8;i++){
            series.getData().add(new XYChart.Data(listeM[i], Integer.parseInt(listeT[i])));
        }
        root.getData().addAll(series);

        return root;
    }
    public static LineChart Lignes(File f){
        String[] listeM = new String[8];
        String[] listeT = new String[8];
        try{
            BufferedReader entree = new BufferedReader(
                    new FileReader(f));
            String mois = entree.readLine();
            String température = entree.readLine();
            listeM = mois.split(", ");
            listeT = température.split(". ");
            for(int i=0;i<8;i++){
                System.out.println(listeM[i]+ ","+listeT[i]);
            }
        }catch(Exception exception1){

        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mois");
        yAxis.setLabel("Températures");

        final LineChart<String,Number> root =
                new LineChart<String,Number>(xAxis,yAxis);
        root.setTitle("Lignes");

        XYChart.Series series = new XYChart.Series();
        series.setName("info 1");
        for(int i=0;i<8;i++){
            series.getData().add(new XYChart.Data(listeM[i],Integer.parseInt(listeT[i])));
        }
        root.getData().addAll(series);
        return root;
}
}