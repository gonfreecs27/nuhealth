
import java.io.FileWriter;
import java.io.IOException;

public class Student {

    // Student properties
    public String studentId;
    public String name;
    public String section;
    public double height;
    public double weight;
    public double bmi;

    public char gender;
    public double dbw;

    /**
     * Logs the student's BMI data into the "students_bmi.csv" file. This method
     * prepares the student's details (ID, name, section, weight, height, and
     * BMI), and writes them as a comma-separated entry in the CSV file. If an
     * IOException occurs during the writing process, it is caught and printed.
     */
    public void logBMI() {
        try {
            // Prepare the record to be written in the CSV file
            String[] record = {
                this.studentId,
                this.name,
                this.section,
                String.valueOf(this.weight),
                String.valueOf(this.height),
                String.valueOf(this.bmi)
            };

            String entry = String.join(", ", record) + "\n";

            FileWriter fw = new FileWriter("students_bmi.csv", true);
            fw.write(entry);
            fw.close();
        } catch (IOException err) {
            System.out.println(err);
        }
    }


    /**
     * Logs the student's Dry Body Weight (DBW) data into the "students_dbw.csv"
     * file. This method prepares the student's details (ID, name, section,
     * gender, height, and DBW), and writes them as a comma-separated entry in
     * the CSV file. If an IOException occurs during the writing process, it is
     * caught and printed.
     */
    public void logDBW() {
        try {
            // Prepare the record to be written in the CSV file
            String[] record = {
                this.studentId,
                this.name,
                this.section,
                String.valueOf(this.gender),
                String.valueOf(this.height),
                String.valueOf(this.dbw)
            };

            String entry = String.join(", ", record) + "\n";

            FileWriter fw = new FileWriter("students_dbw.csv", true);
            fw.write(entry);
            fw.close();
        } catch (IOException err) {
            System.out.println(err);
        }
    }
}
