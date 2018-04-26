package assignment7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        if(args.length < 3){
            System.out.println("Not enough arguments! Usage: File numWords threshold");
            System.exit(0);
        }
        File fileOrig = new File(args[0]);
        int counter = 0;
        for(File file : fileOrig.listFiles()) {
            int numWords = 0;
            int threshold = 0;
            try {
                numWords = Integer.parseInt(args[1]);
                threshold = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer");
            }


            Cheaters cheaters = new Cheaters(file, numWords, threshold, counter);

            try {
                file = cheaters.getFile();
                Scanner sc = new Scanner(cheaters.getFile());
                ArrayList<String> words = new ArrayList<>();
                while (sc.hasNextLine()) {
                    String nextLine = sc.nextLine();
                    String[] wordsInLine = nextLine.split(" ");
                    for (int i = 0; i < wordsInLine.length; i++) {
                        words.add(wordsInLine[i]);
                    }
                }
                cheaters.cheating(words);


            } catch (FileNotFoundException e) {
                System.out.println("Please enter a valid directory");
            }
            counter++;
        }


    }
}
