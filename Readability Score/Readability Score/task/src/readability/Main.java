package readability;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        final int[] AGES = {6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24, 25};
        String string = new String(Files.readAllBytes(Path.of(args[0])));
        int words = string.split("\\s+").length;
        long characters = string.chars().filter(c -> c != ' ' && c != '\n').count();
        int sentences = string.split("[?.!]").length;
        int[] syllableCounts = countSyllables(string);
        int syllables = syllableCounts[0];
        int polysyllables = syllableCounts[1];
        double auto = 4.71 * characters / words + 0.5 * words / sentences - 21.43;
        double flesch = 0.39 * words / sentences + 11.8 * syllables / words - 15.59;
        double simple = 1.043 * Math.sqrt(polysyllables * 30.0 / sentences) + 3.1291;
        double coleman = 0.0588 * characters / words * 100 - 0.296 * sentences / words * 100 - 15.8;
        System.out.println("The text is:");
        System.out.printf("%s\n\n", string).println("Words: " + words);
        System.out.println("Sentences: " + sentences);
        System.out.println("Characters: " + characters);
        System.out.println("Syllables: " + syllables);
        System.out.println("Polysyllables: " + polysyllables);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String ans = new Scanner(System.in).nextLine();
        System.out.println();
        String[] results = {
            String.format("Automated Readability Index: %.2f (about %d year olds).", auto, AGES[(int) auto - 1]),
            String.format("Flesch–Kincaid readability tests: %.2f (about %d year olds).", flesch, AGES[(int) flesch - 1]),
            String.format("Simple Measure of Gobbledygook: %.2f (about %d year olds).", simple, AGES[(int) simple - 1]),
            String.format("Coleman–Liau index: %.2f (about %d year olds).", coleman, AGES[(int) coleman - 1])
        };
        double avg = (AGES[(int) auto - 1] + AGES[(int) flesch - 1] + AGES[(int) simple - 1] +
                AGES[(int) coleman - 1]) / 4.0;
        switch (ans) {
            case "ARI": System.out.println(results[0]); break;
            case "FK": System.out.println(results[1]); break;
            case "SMOG": System.out.println(results[2]); break;
            case "CL": System.out.println(results[3]); break;
            default: Arrays.stream(results).forEach(System.out::println);
                System.out.printf("\nThis text should be understood by %s year olds.\n", avg);
        }

    }

    private static int[] countSyllables(String string) {
        final String VOWELS = "aeiouy";
        int syllableCount = 0;
        int polysyllableCount = 0;
        String clean = string.trim().toLowerCase().replaceAll("[.,?!]|e\\b", "").replaceAll("[aeiouy]{1,3}", "a");
        for (String word : clean.split("\\s+")) {
            int word_count = 0;
            for (char c : word.toCharArray()) if (VOWELS.contains(String.valueOf(c))) word_count++;
            if (word_count == 0) word_count = 1;
            if (word_count > 2) polysyllableCount++;
            syllableCount += word_count;
        }
        return new int[]{syllableCount, polysyllableCount};
    }

}
