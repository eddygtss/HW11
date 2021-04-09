import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

class Person implements Serializable {
    String name, DOB, email, phoneNumber;

    public Person(String name, String DOB, String email, String phoneNumber) {
        this.name = name;
        this.DOB = DOB;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int ans = 0;
        String name, DOB, email, phoneNumber;
        Person person;

        while (true) {
            System.out.print("""
                    ***********************************************************
                    *                        Main Menu                        *
                    *  1. Add information into a file                         *
                    *  2. Retrieve information from a file and display them.  *
                    *  3. Delete information.                                 *
                    *  4. Update information.                                 *
                    *  5. Exit.                                               *
                    ***********************************************************
                    Please select from the options above:\s""");
            try {
                ans = input.nextInt();
            } catch (Exception e) {
                System.out.println("Please make sure to enter an integer from 1-3");
            }

            input.nextLine();

            // We want to stop the while loop if the selected option is 3.
            if (ans == 5) {
                System.out.println();
                break;
            }

            // First code lines in try block gathers information for the Account class
            try {
                // Then we obtain information for either a Services or Supplies object
                if (ans == 1) {
                    System.out.print("Please enter a name: ");
                    name = input.nextLine();
                    System.out.print("Please enter DOB: ");
                    DOB = input.nextLine();
                    System.out.print("Please enter email: ");
                    email = input.nextLine();
                    System.out.print("Please enter phone number: ");
                    phoneNumber = input.nextLine();
                    person = new Person(name, DOB, email, phoneNumber);
                    writeToFile(person);
                    System.out.println("Person written to file successfully!");
                } else if (ans == 2) {

                    System.out.println("Supplies added successfully!");
                } else {
                    System.out.println("Please make sure to enter an integer from 1-3");
                }
            } catch (InputMismatchException | IOException e) {
                input.nextLine();
                System.out.println("ERROR: " + e.toString());
            }
            System.out.println("-----------------------------------------");
        }

    }

    public static void writeToFile(Person person) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Person.bin"));

        objectOutputStream.writeObject(person);
    }

    public static void readFile(){

    }
}