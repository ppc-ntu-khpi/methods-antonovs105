//package test;

//import domain.Exercise;

import java.util.Scanner;

public class TestResult {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter text ('exit' to exit):");
            String text = scanner.nextLine();

            if (text.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Enter key: ");
            String key = scanner.nextLine();

            if (key.equalsIgnoreCase("exit")) {
                break;
            }

            String encodedText = Exercise.vigenereEncode(text, key);

            System.out.println("Input text: " + text);
            System.out.println("Encoded text: " + encodedText);
            System.out.println("----------------------");
        }

        System.out.println("Program finished");
        scanner.close();
    }
}