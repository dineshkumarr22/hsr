import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Student {
    private final String name;
    private final String rollNumber;
    private final String department;
    private final String address;
    private final String phoneNumber;

    public Student(String name, String rollNumber, String department, String address, String phoneNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.department = department;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getDepartment() {
        return department;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return name + " (" + rollNumber + ") - " + department + ", " + address + ", " + phoneNumber;
    }
}

public class HostelManagement {
    private static final int TOTAL_ROOMS = 100;
    private static final Map<Integer, List<Student>> rooms = new HashMap<>();

    public static void main(String[] args) {
        initializeRooms();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nHostel Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Display Room List");
            System.out.println("3. Display Vacancy List");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayRoomList();
                    break;
                case 3:
                    displayVacancyList();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeRooms() {
        for (int i = 1; i <= TOTAL_ROOMS; i++) {
            rooms.put(i, new ArrayList<>());
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.next();
        System.out.print("Enter department: ");
        String department = scanner.next();
        System.out.print("Enter address: ");
        String address = scanner.next();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.next();
        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        if (rooms.containsKey(roomNumber)) {
            List<Student> students = rooms.get(roomNumber);
            if (students.size() < 4) {
                Student student = new Student(name, rollNumber, department, address, phoneNumber);
                students.add(student);
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Room is full.");
            }
        } else {
            System.out.println("Invalid room number.");
        }
    }

    private static void displayRoomList() {
        for (int roomNumber : rooms.keySet()) {
            List<Student> students = rooms.get(roomNumber);
            System.out.println("Room " + roomNumber + ":");
            if (students.isEmpty()) {
                System.out.println("  Empty");
            } else {
                for (Student student : students) {
                    System.out.println("  " + student);
                }
            }
        }
    }

    private static void displayVacancyList() {
        for (int roomNumber : rooms.keySet()) {
            List<Student> students = rooms.get(roomNumber);
            int vacancies = 4 - students.size();
            System.out.println("Room " + roomNumber + ": " + vacancies + " vacancy(ies)");
        }
    }
}
