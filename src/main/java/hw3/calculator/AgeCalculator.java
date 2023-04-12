package hw3.calculator;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import hw3.console.ConsoleHandler;

/**
 * This class represents an age calculator which calculates the age of a person based on their birth date.
 */
public class AgeCalculator {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;

    /**
     * Constructs an instance of the AgeCalculator class.
     */
    public AgeCalculator() {}

    /**
     * Prompts the user to enter their data in the specified format and calculates their age based on their birth date.
     */
    public void analyze() {
        ConsoleHandler.writeLine("Enter data in the format \"First_Name Last_Name Patronymic DD.MM.YYYYY\":");
        String data = ConsoleHandler.readLine();
        splitUserData(data);

        String name_characters = name.charAt(0) + ". " + patronymic.charAt(0) + ".";
        Period age = Period.between(birthDate, LocalDate.now());

        ConsoleHandler.writeLine(surname + " " + name_characters + " " + age.getYears() + " " + getEnding(age.getYears()));
    }

    /**
     * Splits the user's data into name, surname, patronymic, and birth date.
     *
     * @param data a string containing the user's data in the specified format.
     */
    private void splitUserData(String data) {
        try {
            String[] parts = data.split(" ");
            name = parts[1];
            surname = parts[0];
            patronymic = parts[2];
            birthDate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            ConsoleHandler.writeLine("Invalid input. Please enter data in the format \"First_Name Last_Name Patronymic DD.MM.YYYY\".");
            splitUserData(ConsoleHandler.readLine());
        }
    }

    /**
     * Returns the appropriate ending for the specified number of years.
     *
     * @param years the number of years.
     * @return the appropriate ending for the specified number of years.
     */
    private String getEnding(int years) {
        if (years == 1) {
            return "год";
        } else if (years >= 2 && years <= 4) {
            return "года";
        } else if (years >= 5 && years <= 20) {
            return "лет";
        } else if (years % 10 == 1) {
            return "год";
        } else if (years % 10 >= 2 && years % 10 <= 4) {
            return "года";
        } else {
            return "лет";
        }
    }
}
