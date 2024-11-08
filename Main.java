
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printHeader("");

        System.out.println("Press [Enter] to continue...");
        scanner.nextLine();

        boolean running = true;
        while (running) {
            printMenu();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showBMIScreen();
                    break;
                case 2:
                    showDBWScreen();
                    break;
                case 3:
                    logout();
                    running = false;
                    break;
                default:
                    try {
                        System.out.println("Invalid choice. Please try again.");

                        // We'll use the Thread object to pause before allowing
                        // the user to select another option
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
            }
        }

        scanner.close();
    }

    /**
     * Prints the header of the page, including the system title, a separator,
     * and an optional page title. Clears the screen, displays a fixed header,
     * and if provided, prints the given page title.
     *
     * @param pageTitle The title of the current page, which will be printed
     * below the system title. If null or empty, only the system title and
     * separator lines are printed.
     */
    private static void printHeader(String pageTitle) {
        clearScreen();
        System.out.println("=====================================================================================================================");
        System.out.println("                                          STUDENT HEALTH AND FITNESS TRACKER                                         ");
        System.out.println("=====================================================================================================================");
        System.out.println();
        System.out.println("The Student Health and Fitness Tracker is a system designed to monitor and manage the health metrics of students. It");
        System.out.println("users to calculate and track vital health data such as Body Mass Index (BMI) and Dry Body Weight (DBW), providing");
        System.out.println("valuable insights into their physical well-being. The system records student information, including weight, height,");
        System.out.println("and section, and logs health data into CSV files for easy tracking and reporting.");
        System.out.println("This system is intended for Nationalian's use only");
        System.out.println();

        if (pageTitle != null && !pageTitle.isEmpty()) {
            System.out.println(pageTitle);
            System.out.println();
        }
    }


    /**
     * Prints the main menu of the system, including options for BMI Calculator,
     * DBW Calculator, and Exit. The header is displayed with the title "Main
     * Menu", and the available choices are listed for the user.
     *
     * This method does not take any parameters or return any values.
     */
    private static void printMenu() {
        printHeader("Main Menu:");
        System.out.println("1. BMI Calculator");
        System.out.println("2. DBW Calculator");
        System.out.println("3. Exit");
        System.out.println();
        System.out.print("Enter your choice: ");
    }


    /*
     * This method clears the console screen using ANSI escape codes.
     * The escape sequence "\033[H\033[2J" is used to reset the cursor position to the top-left of the terminal
     * and clear the screen. This is commonly used in Unix-like systems (Linux, macOS) to provide a clean output screen.
     * The `System.out.flush()` ensures that the command is executed immediately by flushing the output buffer.
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    /**
     * This method displays the BMI calculation screen, where the user can enter
     * their details (Student ID, Name, Section, Weight, and Height) and the
     * program will calculate and display their BMI. Afterward, the user is
     * prompted to press Enter to go back to the main menu.
     */
    private static void showBMIScreen() {
        Student student = new Student();

        Scanner scanner = new Scanner(System.in);

        printHeader("Calculate BMI");
        System.out.println("This feature calculates the Body Mass Index (BMI) of the student based on the provided weight and height, then");
        System.out.println("record all entries in the students_bmi.csv file.");
        System.out.println();

        // Ask for Student ID
        System.out.print("Student ID: ");
        student.studentId = scanner.nextLine();
        System.out.println();

        // Ask for Student Name
        System.out.print("Student Name: ");
        student.name = scanner.nextLine();
        System.out.println();

        // Ask for Student's section
        System.out.print("Section: ");
        student.section = scanner.nextLine();
        System.out.println();

        System.out.print("Enter your weight in kilograms: ");

        // Use the readDouble method to ensure that the input from
        // the user will be of type double
        student.weight = readDouble(scanner);

        System.out.println();
        System.out.print("Enter your height in meters: ");

        student.height = readDouble(scanner);

        // Calculate the BMI using the calculateBMI method
        student.bmi = calculateBMI(student.weight, student.height);

        System.out.println();
        System.out.printf("The student's BMI is: %.2f", student.bmi);

        // Log the student's bmi records into file
        // by calling the logBMI method of Student class
        student.logBMI();

        // Consume the left over new line character after reading the previous input
        scanner.nextLine();

        System.out.println();
        System.out.println();
        System.out.println("Press [Enter] to go back to main menu...");
        scanner.nextLine();
    }


    /**
     * Displays the screen for calculating Dry Body Weight (DBW) of a student.
     * This method prompts the user for their personal information (ID, name,
     * section, height, and gender), calculates the student's DBW, and logs the
     * data into a CSV file.
     *
     * The DBW calculation is based on gender and height, and the resulting
     * value is stored in a file for future reference. If an exception occurs
     * during input or calculation, it is caught and displayed.
     */
    private static void showDBWScreen() {
        Scanner scanner = new Scanner(System.in);

        try {
            Student student = new Student();

            printHeader("Calculate DBW");
            System.out.println("This feature calculates the Dry Body Weight (DBW) of the student based on the provided gender and height, then");
            System.out.println("record all entries in the students_dbw.csv file.");
            System.out.println();

            // Ask for Student ID
            System.out.print("Student ID: ");
            student.studentId = scanner.nextLine();
            System.out.println();

            // Ask for Student Name
            System.out.print("Student Name: ");
            student.name = scanner.nextLine();
            System.out.println();

            // Ask for Student's section
            System.out.print("Section: ");
            student.section = scanner.nextLine();
            System.out.println();

            System.out.print("Enter your height in centimeters: ");

            // Use the readDouble method to ensure that the input from
            // the user will be of type double
            student.height = readDouble(scanner);

            System.out.println();
            System.out.print("Enter your gender (M/F): ");
            student.gender = scanner.next().charAt(0);

            // Calculate the BMI using the calculateBMI method
            student.dbw = calculateDBW(student.gender, student.height);

            System.out.println();
            System.out.printf("The student's DBW is: %.2f", student.dbw);

            // Log the student's dbw records into file
            // by calling the logDBW method of Student class
            student.logDBW();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            // Consume the left over new line character after reading the previous input
            scanner.nextLine();

            System.out.println();
            System.out.println();
            System.out.println("Press [Enter] to go back to main menu...");
            scanner.nextLine();
        }
    }


    /**
     * Logs the user out of the system by displaying an exit message.
     * This method is used when the user chooses to exit the application.
     */
    private static void logout() {
        // Display a message indicating that the user is exiting the system
        System.out.println("Exiting the system. Goodbye!");
    }

    /**
     * Calculates the Body Mass Index (BMI) based on the provided weight and
     * height.
     *
     * BMI is a value derived from an individual's weight and height, used to
     * assess whether they are underweight, normal weight, overweight, or obese.
     *
     * The formula for BMI is: BMI = weight (kg) / height (m)^2
     *
     * A BMI in the range of: - 18.5 to 24.9 is considered normal weight. -
     * Below 18.5 is underweight. - 25 to 29.9 is overweight. - 30 and above is
     * considered obese.
     *
     * @param weight The weight of the person in kilograms (kg).
     * @param height The height of the person in meters (m).
     * @return The calculated BMI value.
     */
    private static double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }


    /**
     * Calculates the Dry Body Weight (DBW) based on the gender and height of the student.
     * The DBW is calculated using the following formula:
     * - For males: DBW = 50 + 2.3 * (height in inches - 60)
     * - For females: DBW = 45.5 + 2.3 * (height in inches - 60)
     * The height is provided in centimeters, which is converted to inches before applying the formula.
     * 
     * @param gender The gender of the student ('M' for male, 'F' for female).
     * @param heightInCm The height of the student in centimeters.
     * @return The calculated DBW (Dry Body Weight) in kilograms.
     * @throws IllegalArgumentException If the height is less than 60 inches or the gender is invalid.
     */
    private static double calculateDBW(char gender, double heightInCm) {
        // Convert height first from cm to in
        // 1 inch = 2.54 cm
        double heightInInches = heightInCm / 2.54;
        double dbw = 0;

        if (heightInInches >= 60) {
            if (gender == 'M' || gender == 'm') {
                dbw = 50 + 2.3 * (heightInInches - 60);
            } else if (gender == 'F' || gender == 'f') {
                dbw = 45.5 + 2.3 * (heightInInches - 60);
            } else {
                throw new IllegalArgumentException("Invalid gender input. Please enter 'M' or 'F' only.");
            }
        } else {
            throw new IllegalArgumentException("Height must be at least 60 inches.");
        }
        return dbw;
    }


    /**
     * Reads and returns a valid double input from the user.
     *
     * This function ensures that the input provided by the user is a valid
     * double value. If the user enters an invalid input (e.g., a non-numeric
     * value), it will repeatedly prompt them to enter a valid number. The
     * invalid input is discarded, and the user is asked to try again. Once a
     * valid double is entered, the function returns that value.
     *
     * @param scanner The Scanner object used to read user input from the
     * console.
     * @return A valid double value entered by the user.
     */
    public static double readDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a number.");

            // Clear the invalid input
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
