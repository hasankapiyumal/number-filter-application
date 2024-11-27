package com.filter.number;

import java.io.*;
import java.util.*;

public class NumberProcessor {

    public static void main(String[] args) {
        // Input and output file paths
        String inputFilePath = "C:\\Users\\User\\IdeaProjects\\number-filter-application\\src\\main\\resources\\Base I.txt"; // Replace with your actual input file path
        String outputFilePath = "C:\\Users\\User\\IdeaProjects\\number-filter-application\\src\\main\\resources\\Base I.csv"; // Replace with your desired output CSV file path

        try {
            // Reading the input file
            List<String> numbers = readFile(inputFilePath);

            // Processing and validating the numbers
            List<String> validNumbers = processAndValidateNumbers(numbers);

            // Saving valid numbers to a CSV file
            saveToCSV(outputFilePath, validNumbers);

            System.out.println("Operation successful. Processed numbers saved to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Method to read lines from a file
    private static List<String> readFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        }
        return lines;
    }

    // Method to process and validate numbers
    private static List<String> processAndValidateNumbers(List<String> numbers) {
        List<String> validNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            String original = numbers.get(i);

            // Remove first two characters
            String processed = original.length() > 2 ? original.substring(2) : original;

            // Remove trailing ".00" if present
            if (processed.endsWith(".00")) {
                processed = processed.substring(0, processed.length() - 3);
            }

            // Validate if the processed number has exactly 9 digits
            if (processed.length() == 9) {
                validNumbers.add(processed);
            } else {
                System.out.println("Mismatch at position " + (i + 1) + ": " + original);
            }
        }
        return validNumbers;
    }

    // Method to save valid numbers to a CSV file
    private static void saveToCSV(String filePath, List<String> numbers) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write header (optional)
//            writer.write("Number");
//            writer.newLine();

            // Write numbers
            for (String number : numbers) {
                writer.write(number);
                writer.newLine();
            }
        }
    }
}
