package Drivers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import DictionaryDS.DictionaryAsAVLTree;
import Exceptions.WordAlreadyExistsException;
import Exceptions.WordNotFoundException;

public class driver {
    public static void main(String[] args) {
        // * init a scanner to read from the console
        Scanner myScanner = new Scanner(System.in);
        System.out.print("Enter filename>\n");
        String fileName = myScanner.nextLine();
        File myFile = new File(fileName);
        try {
            DictionaryAsAVLTree myDictionary = new DictionaryAsAVLTree(myFile);
            System.out.println("Dictionary loaded successfully.");
            System.out.print("check word>\n");
            String word = myScanner.nextLine();
            String result1 = myDictionary.findWord(word) ? "Word found" : "Word not found";
            System.out.println(result1);
            System.out.print("add new word>\n");
            word = myScanner.nextLine();
            try {
                myDictionary.addWord(word);
                System.out.println("word added successfully.");
            } catch (WordAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
            System.out.print("remove word>\n");
            word = myScanner.nextLine();
            try {
                myDictionary.deleteWord(word);
            } catch (WordNotFoundException e) {
                System.out.println(e.getMessage());
            }
            System.out.print("search for similar words>\n");
            word = myScanner.nextLine();
            String[] similerWords = myDictionary.findSimilar(word);
            System.out.println(Arrays.toString(similerWords));
            System.out.println();
            System.out.println("Save Updated Dictionary (Y/N)> Y");
            if (myScanner.nextLine().equals("Y")) {
                System.out.print("Enter filename>\n ");
                myFile = new File(myScanner.nextLine());
                try {
                    myDictionary.writeFile(myFile);
                    System.out.println("Dictionary saved successfully.");
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            }
            myScanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
