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
    private Hashtable<String, LinkedList> hash;
    int counter;
    public int[][] array;

    public Cheaters(File file, int words, int threshold, int counter){
        this.file = file;
        this.numWords = words;
        this.threshold = threshold;
        this.counter = counter;
        Hashtable<String, LinkedList> phrases = new Hashtable<>();
        this.hash = phrases;
    }



    public void cheating(ArrayList<String> words){
        ArrayList<String> result = new ArrayList<String>();


        for (int i = 0; i < words.size() - numWords - 1; i++){
            String phrase = new String();
            for (int j = 0; j < numWords; j++){
                phrase += words.get(i+j).toLowerCase();
                if (j != numWords - 1) {
                    phrase += " ";
                }
            }
            result.add(phrase);
        }
        hashing(result);

    }

    public void hashing(ArrayList<String> phrases){

        for(int i = 0; i<phrases.size(); i++) {
            LinkedList<Integer> linkedList = new LinkedList<Integer>();

            if(hash.containsKey(phrases.get(i))){
                linkedList = hash.get(phrases.get(i));
                linkedList.add(counter);
                hash.replace(phrases.get(i), linkedList);
            }
            else {
                linkedList.add(counter);
                hash.put(phrases.get(i), linkedList);
            }
        }

    }

    public int[][] countHits(int n){
        int[][] arr = new int[n][n];
        for(String s: hash.keySet()){
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            linkedList = hash.get(s);
            for(int i = 0; i < linkedList.size(); i++){
                for(int j = 0; j<linkedList.size(); j++){
                    int x = linkedList.get(i);
                    int y = linkedList.get(j);
                    int hits = arr[x][y];
                    hits++;
                    arr[x][y] = hits;
                }
            }
        }
        this.array = arr;
        ArrayList<String> files = getFile();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(arr[i][j] > threshold) {
                    System.out.println("Files " + files.get(i) + " and " + files.get(j) + " cheated " + arr[i][j] + " times");
                }
            }

        }
        return arr;
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
