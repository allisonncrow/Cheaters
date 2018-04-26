package assignment7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

public class Cheaters {

    private int numWords;
    private int threshold;
    private File file;
    private Hashtable<String, Integer> hash;
    int counter;

    public Cheaters(File file, int words, int threshold, int counter){
        this.file = file;
        this.numWords = words;
        this.threshold = threshold;
        this.counter = counter;
    }

    public File getFile(){
        return this.file;
    }

    public void cheating(ArrayList<String> words){
        ArrayList<String> result = new ArrayList<String>();
        Hashtable<String, Integer> phrases = new Hashtable<>();
        this.hash = phrases;

        for (int i = 0; i < words.size() - numWords - 1; i++){
            String phrase = new String();
            for (int j = 0; j < numWords; j++){
                phrase += words.get(i+j).toLowerCase();
                if (i != numWords - 1) {
                    phrase += " ";
                }
            }
            result.add(phrase);
        }
        hashing(result);

    }

    public void hashing(ArrayList<String> phrases){
        for(int i = 0; i<phrases.size(); i++) {
            hash.put(phrases.get(i), counter);
        }
        for(String s : hash.keySet()){
            System.out.println(s + " ");
            System.out.print(hash.get(s));
        }
    }






}
