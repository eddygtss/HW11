import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int ans = 0;
        String name, DOB, email, phoneNumber;
        ArrayList<Person> personList = new ArrayList<>();
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

            // Try block handles all of the option selections.
            try {
                // We add information to a file by calling writeToFile method.
                switch (ans){
                    case 1 -> {
                        personList.clear();
                        System.out.print("Please enter a name: ");
                        name = input.nextLine();
                        System.out.print("Please enter DOB: ");
                        DOB = input.nextLine();
                        System.out.print("Please enter email: ");
                        email = input.nextLine();
                        System.out.print("Please enter phone number: ");
                        phoneNumber = input.nextLine();
                        person = new Person(name, DOB, email, phoneNumber);
                        personList.add(person);
                        writeToFile(personList,1);
                        System.out.println("Person written to file successfully!");
                    }
                    case 2 -> {
                        personList = readFile();

                        for (Person p:personList) {
                            System.out.println(p);
                        }
                    }
                    case 3 -> {
                        personList.clear();
                        personList = readFile();
                        int count = 1, selection;

                        if (personList.isEmpty()){
                            System.out.println("No objects available to delete!");
                            break;
                        }

                        for (Person p:personList) {
                            System.out.println("#" + count + ": " + p);
                            count++;
                        }
                        System.out.print("Which entry would you like to delete?: ");
                        selection = input.nextInt() - 1;
                        input.nextLine();

                        if (personList.size() == 1) {
                            personList.clear();
                        } else {
                            personList.remove(selection);
                        }

                        for (Person p:personList) {
                            System.out.println("#" + count + ": " + p);
                            count++;
                        }

                        writeToFile(personList, 3);
                    }
                    case 4 -> {
                        personList = readFile();
                        int count = 1, selection;

                        if (personList.isEmpty()){
                            System.out.println("No objects available to delete!");
                            break;
                        }

                        for (Person p:personList) {
                            System.out.println(count + " " + p);
                            count++;
                        }
                        System.out.print("Which entry would you like to update?: ");
                        selection = input.nextInt() - 1;
                        input.nextLine();

                        personList.remove(selection);

                        System.out.print("Please enter a name: ");
                        name = input.nextLine();
                        System.out.print("Please enter DOB: ");
                        DOB = input.nextLine();
                        System.out.print("Please enter email: ");
                        email = input.nextLine();
                        System.out.print("Please enter phone number: ");
                        phoneNumber = input.nextLine();
                        person = new Person(name, DOB, email, phoneNumber);

                        personList.add(selection, person);

                        writeToFile(personList,4);
                    }
                }
            } catch (InputMismatchException | IOException | ClassNotFoundException e) {
                input.nextLine();
                System.out.println("ERROR: " + e.toString());
            }
            System.out.println("-----------------------------------------");
        }

    }

    public static void writeToFile(ArrayList<Person> personList, int sel) throws IOException, ClassNotFoundException {
        String file = "Person.bin";
        File file1 = new File(file);
        ArrayList<Person> writePerson = new ArrayList<>();

        if (file1.exists()) {
            writePerson = readFile();
        }

        if (personList.isEmpty()) {
            writePerson.clear();
        }

        if (sel == 1){
            writePerson.add(personList.get(0));
        }
        else if (sel == 3 || sel == 4) {
            writePerson = personList;
        }

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);

        objectOutputStream.writeObject(writePerson);

    }

    public static ArrayList<Person> readFile() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("Person.bin"));

        return (ArrayList<Person>) objectInputStream.readObject();
    }
}