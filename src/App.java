import java.io.File;
import interview.file.statistics.lib.*;

public class App {
    public static void main(String[] args) throws Exception {
        int lineCount;
        int whitespace;
        double average;
        char letter;

        String path = "hello.txt";
        
        File file = new File(path);

        FileStatistics fileStatistics = new FileStatistics(file);
        lineCount = fileStatistics.lineCount();
        whitespace = fileStatistics.delimitedWordCount(" ");
        average  = fileStatistics.averageNumberOfLettersPerWord();
        letter = fileStatistics.mostCommonLetter();

        String stats = String.format("line count: %d\n" + 
        "whitespace delimited word count: %d\n" + 
        "average number of letters per word: %.1f\n" + 
        "most common letter: %c\n", lineCount, whitespace, average, letter);

        System.out.println(stats);
    }
}
