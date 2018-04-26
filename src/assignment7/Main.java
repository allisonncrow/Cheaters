package assignment7;

import java.io.*;
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
        cheaters.countHits(fileOrig.listFiles().length);


    }
}
