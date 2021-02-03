package interview.file.statistics.lib;

import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileNotFoundException;



interface IFileStatistics {
    public int lineCount();
    public char mostCommonLetter();
    public double averageNumberOfLettersPerWord();
    public int delimitedWordCount(String delimiter);
}

public class FileStatistics implements IFileStatistics {
    
    private File file;

    public FileStatistics(File file) {
        this.file = file;
    }
    
    public int lineCount() {
        int count = 0;
        try {
            Scanner myReader = new Scanner(this.file);

            while (myReader.hasNextLine()) {
                myReader.nextLine();
                count++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return count;
    }

    public char mostCommonLetter() {

        ArrayList<String> letters = new ArrayList<String>();
        HashMap<String,Integer> letters_count = new HashMap<String,Integer>();

        String[] words = getWords(" ");

        for(String word : words) {
            Collections.addAll(letters, word.split(""));
        }

        for(String letter : letters) {
            if(letter != " "){
                int value = letters_count.getOrDefault(letter, 0);
                letters_count.put(letter, value + 1);
            }
        }

        int max = 0;
        String letter = "-";
        for(String key : letters_count.keySet()) {
            if( letters_count.get(key) > max) {
                max = letters_count.get(key);
                letter = key;
            }
        }

        return letter.charAt(0);
    }

    public double averageNumberOfLettersPerWord() {
        double average;
        int sum = 0;
        String[] words = getWords(" ");
        
        for(String word: words) {
            sum += word.length();
        }

        average = sum / words.length;
        return average;
    }

    public int delimitedWordCount(String delimiter) {
        String[] words = getWords(delimiter);

        return words.length;
    }

    private String[] getWords(String delimiter) {
        ArrayList<String> totalWords = new ArrayList();

        try {
            Scanner myReader = new Scanner(this.file);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] words = line.split(delimiter);
                Collections.addAll(totalWords, words);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        String[] wordArr = new String[totalWords.size()];
        wordArr = totalWords.toArray(wordArr);
        return wordArr;
    }

}
