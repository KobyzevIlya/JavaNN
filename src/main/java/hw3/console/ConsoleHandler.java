package hw3.console;

import java.util.Scanner;

/**
 * ConsoleHandler - a class for working with console input and output.
 */
public class ConsoleHandler {
    private ConsoleHandler() {}

    /**
     * Method for reading a line from the console.
     * 
     * @return Returns the string entered from the console.
     */
    public static String readLine() {
        Scanner scanner = new Scanner(System.in);
        String to_return = null;
        
        try {
            to_return = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

        return to_return;
    }

    /**
     * Method for outputting a string to the console.
     * 
     * @param line The string to be output to the console.
     */
    public static void writeLine(String line) {
        try {
            System.out.println(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

