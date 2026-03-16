// Import needed classes
import java.io.File;              // For file handling
import java.io.FileNotFoundException; // For error handling if file not found
import java.util.Scanner;         // For user input and reading file
import java.util.HashMap;         // For storing totals
import java.util.Map;             // For looping through results

public class CriticScoreAnalyzer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Scanner for user input
        File file = null;                       // File object to hold dataset

        // Loop until user gives a valid file path
        while (true) {
            System.out.print("Enter dataset file path: ");
            String path = input.nextLine();     // Read user input
            file = new File(path);              // Create File object

            // Check if file exists and is a CSV
            if (file.exists() && file.isFile() && path.endsWith(".csv")) {
                System.out.println("✅ File found. Processing...");
                break; // Exit loop if valid
            } else {
                System.out.println("❌ Invalid file path. Please try again.");
            }
        }

        // Process the dataset
        processDataset(file);

        input.close(); // Close scanner
    }

    // Function to read and analyze dataset
    public static void processDataset(File file) {
        try {
            Scanner fileScanner = new Scanner(file); // Scanner to read file
            String header = fileScanner.nextLine();  // Skip header line

            // Maps to store total scores and counts per console
            Map<String, Double> totalScores = new HashMap<>();
            Map<String, Integer> counts = new HashMap<>();

            // Read each line of the CSV
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] row = line.split(","); // Split by comma

                // Column 2 = console, Column 6 = critic_score
                if (row.length >= 7) {
                    String console = row[2];
                    try {
                        double score = Double.parseDouble(row[6]);

                        // Add score to totals
                        totalScores.put(console, totalScores.getOrDefault(console, 0.0) + score);
                        counts.put(console, counts.getOrDefault(console, 0) + 1);
                    } catch (NumberFormatException e) {
                        // Skip rows where critic_score is not a number
                    }
                }
            }

            fileScanner.close();

            // Print results
            System.out.println("\n📊 Average Critic Score per Console:");
            String bestConsole = null;
            double bestAvg = -Double.MAX_VALUE;

            for (String console : totalScores.keySet()) {
                double avg = totalScores.get(console) / counts.get(console);
                System.out.printf("%s: %.2f\n", console, avg);

                if (avg > bestAvg) {
                    bestAvg = avg;
                    bestConsole = console;
                }
            }

            // Print best console
            System.out.printf("\n🏆 Best Console: %s with average score %.2f\n", bestConsole, bestAvg);

        } catch (FileNotFoundException e) {
            System.out.println("❌ Error: File not found.");
        }
    }
}
