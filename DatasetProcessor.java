import java.io.*;
import java.util.*;

/*
Name: Rein Alexander A. Vocal
Machine Problems: MP12, MP13, MP14

Program Description:
This program reads a CSV dataset and allows the user to perform three operations:
1. Display dataset in formatted table (MP12)
2. Detect rows with missing values (MP13)
3. Count keyword occurrences in dataset (MP14)

The program asks the user for the dataset file path before processing.
*/

public class DatasetProcessor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Ask user for dataset path
        System.out.print("Enter CSV file path: ");
        String filePath = scanner.nextLine();

        // Menu selection
        System.out.println("\nSelect Machine Problem:");
        System.out.println("1 - Display Dataset Table (MP12)");
        System.out.println("2 - Detect Missing Values (MP13)");
        System.out.println("3 - Count Keyword Occurrences (MP14)");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice){

            case 1:
                displayTable(filePath);
                break;

            case 2:
                detectMissing(filePath);
                break;

            case 3:
                System.out.print("Enter keyword to search: ");
                String keyword = scanner.nextLine();
                countKeyword(filePath, keyword);
                break;

            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    /*
    MP12
    Displays dataset in formatted table
    */
    public static void displayTable(String path){

        try{

            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            System.out.println("\n===== DATASET TABLE =====");

            while((line = br.readLine()) != null){

                String[] columns = line.split(",");

                for(String column : columns){

                    System.out.printf("%-25s", column);

                }

                System.out.println();
            }

            br.close();

        }catch(Exception e){

            System.out.println("Error reading file: " + e.getMessage());

        }

    }

    /*
    MP13
    Detect rows with missing values
    */
    public static void detectMissing(String path){

        try{

            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            int row = 0;

            while((line = br.readLine()) != null){

                row++;

                String[] columns = line.split(",");

                for(String column : columns){

                    if(column.trim().isEmpty()){

                        System.out.println("Missing value found at row: " + row);
                        break;

                    }

                }

            }

            br.close();

        }catch(Exception e){

            System.out.println("Error reading file: " + e.getMessage());

        }

    }

    /*
    MP14
    Count keyword occurrences
    */
    public static void countKeyword(String path, String keyword){

        int count = 0;

        try{

            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;

            while((line = br.readLine()) != null){

                if(line.toLowerCase().contains(keyword.toLowerCase())){

                    count++;

                }

            }

            br.close();

            System.out.println("Keyword '" + keyword + "' found " + count + " times.");

        }catch(Exception e){

            System.out.println("Error reading file: " + e.getMessage());

        }

    }

}