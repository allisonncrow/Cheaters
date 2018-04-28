package assignment7;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;




public class Main extends Application{
    private static int[][] arr;
    private static int thresh;
    private static File file;
    public static void main(String args[]){
        if(args.length < 3){
            System.out.println("Not enough arguments! Usage: File numWords threshold");
            System.exit(0);
        }
        File fileOrig = new File(args[0]);
        int counter = 0;
        int numWords = 0;
        int threshold = 0;
        try {
            numWords = Integer.parseInt(args[1]);
            threshold = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter an integer");
        }
        Cheaters cheaters = new Cheaters(fileOrig, numWords, threshold, counter);
        for(File file : fileOrig.listFiles()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                ArrayList<String> words = new ArrayList<>();
                for (String x = in.readLine(); x != null; x = in.readLine()) {
                    String[] wordsInLine = x.split(" ");
                    for (int i = 0; i < wordsInLine.length; i++) {
                        words.add(wordsInLine[i]);
                    }

                }
                cheaters.cheating(words);
            }
                catch (IOException e) {
                System.out.println("Please enter a valid directory");
            }
            cheaters.counter++;
        }
        int[][] array = cheaters.countHits(fileOrig.listFiles().length);
        file = fileOrig;
        arr = array;
        thresh = threshold;
        launch(args);



    }


    @Override
    public void start(Stage primaryStage) {
        ArrayList<String> files = new ArrayList<>();
        files = getFile();
        GridPane grid = new GridPane();
        grid.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        for (int x = 0; x < arr.length + 1; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(arr.length);
            grid.getColumnConstraints().add(cc);
        }
        for (int x = 0; x <arr.length + 1; x++) {
            RowConstraints rr = new RowConstraints();
            rr.setPercentHeight(arr.length);
            grid.getRowConstraints().add(rr);
        }
        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid,1200,1000);
        for(int i = 1; i< arr.length + 1; i++){
            Label label = new Label(files.get(i-1));
            grid.add(label, 0, i);
        }
        for(int i = 1; i< arr.length + 1; i++){
            Label label = new Label(files.get(i-1));
            grid.add(label, i, 0);
        }




        for(int i = 0; i<arr.length; i++){
            for(int j = 0; j<i; j++){
                if(arr[i][j] > thresh){
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.RED);
                    rectangle.heightProperty().bind(scene.heightProperty().divide(arr.length + 1));
                    rectangle.widthProperty().bind(scene.widthProperty().divide(arr.length + 1));
                    grid.add(rectangle, i+1, j+1);
                    Label label  = new Label();
                    label.setText("" + arr[i][j]);
                    grid.add(label, i+ 1, j+ 1);
                    label.setTextFill(Color.BLACK);
                    label.setFont(new Font(20));
                }
            }
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public ArrayList<String> getFile(){
        ArrayList<String> array = new ArrayList<>();
        File fileOrig = file;
        for(File file : file.listFiles()){
            array.add(file.toString().substring(fileOrig.toString().length() + 1));
        }
        return array;
    }
        
    }
