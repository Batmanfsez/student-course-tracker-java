import java.util.*;
import java.io.*;

public class HariDarshanGopu_Section501_Project4 {
    // Data storage collections
    static ArrayList<Student> students = new ArrayList<>();          // All student records
    static ArrayList<Student_Employee> studentEmployees = new ArrayList<>(); // Students with jobs
    static ArrayList<Course> courses = new ArrayList<>();            // All course offerings
    static Random random = new Random();                             // For generating student IDs

    /**
     * Main program entry point - handles system initialization and main menu
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Display welcome message
        System.out.println("                Welcome to Student and Course Management System!\n");
        System.out.println("This system will allow you to manage students and courses. Let's start with the number of students this system will have: ");
        
        // Get and validate initial student count
        try {
            int numStudents = Integer.parseInt(input.nextLine());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Invalid number. Exiting program.");
            System.exit(0);
        }

        // Main program loop
        while (true) {
            System.out.println("***Welcome to Student and Course Management System***\n");
            System.out.println("Press '1' for Student Management System (SMS)");
            System.out.println("Press '2' for Course Management System (CMS)");
            System.out.println("Press '0' to exit");
            System.out.println();

            String choice = input.nextLine();

            // Main menu selection
            switch (choice) {
                case "1":
                    studentManagementSystem(input);  // Enter student management
                    break;
                case "2":
                    courseManagementSystem(input);   // Enter course management
                    break;
                case "0":
                    System.out.println("\nGood Bye!!!");
                    System.exit(0);                 // Exit program
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

    /**
     * Student Management System (SMS) - handles all student-related operations
     * @param input Scanner object for user input
     */
    public static void studentManagementSystem(Scanner input) {
        while (true) {
            // Display SMS menu
            System.out.println("***Welcome to SMS***\n");
            System.out.println("Press '1' to add a student");
            System.out.println("Press '2' to deactivate a student");
            System.out.println("Press '3' to display all students");
            System.out.println("Press '4' to search for a student by ID");
            System.out.println("Press '5' to assign on-campus job");
            System.out.println("Press '6' to display all students with on-campus jobs");
            System.out.println("Press '0' to exit SMS");
            System.out.println();

            String choice = input.nextLine();

            // Process SMS menu selection
            switch (choice) {
                case "1":
                    addStudent(input);          // Add new student record
                    break;
                case "2":
                    deactivateStudent(input);   // Deactivate student
                    break;
                case "3":
                    displayAllStudents();       // Show all students
                    break;
                case "4":
                    searchStudent(input);       // Search by student ID
                    break;
                case "5":
                    assignOnCampusJob(input);   // Assign employment
                    break;
                case "6":
                    displayStudentsWithJobs();  // Show employed students
                    break;
                case "0":
                    return;                     // Return to main menu
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

    /**
     * Adds a new student to the system
     * @param input Scanner object for user input
     */
    private static void addStudent(Scanner input) {
        // Collect student information
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        
        System.out.print("Enter level of the student: ");
        String level = input.nextLine();
        
        // Generate random student ID (0-99)
        int studentID = random.nextInt(100);
        
        // Create and store new student
        Student newStudent = new Student(studentID, firstName, lastName, level);
        students.add(newStudent);
        
        // Display confirmation
        System.out.println("\n" + firstName + " " + lastName + 
                         " has been added as a " + level + 
                         " with ID " + studentID + "\n");
    }

    /**
     * Deactivates a student by setting their status to inactive
     * @param input Scanner object for user input
     */
    private static void deactivateStudent(Scanner input) {
        System.out.print("Enter the ID for the student you want to deactivate: ");
        int studentID = Integer.parseInt(input.nextLine());
        
        // Search for student by ID
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                student.setActive(false);
                System.out.println("\n" + student.getFirstName() + " " + 
                                 student.getLastName() + " has been deactivated\n");
                return;
            }
        }
        System.out.println("\nStudent not found.\n");
    }

    /**
     * Displays all students sorted alphabetically by first name
     * Also writes student report to StudentReport.txt
     */
    private static void displayAllStudents() {
        System.out.println();
        // Display sorted student list to console
        students.stream()
               .sorted(Comparator.comparing(Student::getFirstName))
               .forEach(student -> {
                   System.out.println(student.getFirstName() + " " + student.getLastName());
                   System.out.println("ID: " + student.getStudentID());
                   System.out.println("Level: " + student.getLevel());
                   System.out.println("Status: " + (student.isActive() ? "Active" : "Inactive") + "\n");
               });
        
        // Write student report to file
        try (PrintWriter writer = new PrintWriter(new FileWriter("StudentReport.txt"))) {
            students.stream()
                   .sorted(Comparator.comparing(Student::getFirstName))
                   .forEach(student -> {
                       writer.println(student.getFirstName() + " " + student.getLastName());
                       writer.println("ID: " + student.getStudentID());
                       writer.println("Level: " + student.getLevel());
                       writer.println("Status: " + (student.isActive() ? "Active" : "Inactive") + "\n");
                   });
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Searches for a student by ID and displays their information
     * @param input Scanner object for user input
     */
    private static void searchStudent(Scanner input) {
        System.out.print("Enter the student ID: ");
        int studentID = Integer.parseInt(input.nextLine());
        
        // Search for student
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                // Display student information
                System.out.println("\n" + student.getFirstName() + " " + student.getLastName());
                System.out.println("ID: " + student.getStudentID());
                System.out.println("Level: " + student.getLevel());
                System.out.println("Status: " + (student.isActive() ? "Active" : "Inactive") + "\n");
                return;
            }
        }
        System.out.println("\nStudent not found.\n");
    }

    /**
     * Assigns an on-campus job to a student
     * @param input Scanner object for user input
     */
    private static void assignOnCampusJob(Scanner input) {
        System.out.print("Enter student ID: ");
        int studentID = Integer.parseInt(input.nextLine());
        
        System.out.print("Enter job: ");
        String job = input.nextLine();
        
        System.out.print("Enter job type: ");
        String jobType = input.nextLine();
        
        // Find student and assign job
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                if (student instanceof Student_Employee) {
                    // Existing employee - update job
                    Student_Employee employee = (Student_Employee) student;
                    employee.setJob(job);
                    employee.setEmploymentType(jobType);
                    System.out.println("\n" + employee.getFirstName() + " " + employee.getLastName() + 
                                     " has been assigned " + jobType + "-" + job + " job\n");
                } else {
                    // Convert regular student to employee
                    Student_Employee newEmployee = new Student_Employee(
                        student.getStudentID(), 
                        student.getFirstName(), 
                        student.getLastName(), 
                        student.getLevel()
                    );
                    newEmployee.setActive(student.isActive());
                    newEmployee.setJob(job);
                    newEmployee.setEmploymentType(jobType);
                    
                    // Update collections
                    students.remove(student);
                    students.add(newEmployee);
                    studentEmployees.add(newEmployee);
                    
                    System.out.println("\n" + newEmployee.getFirstName() + " " + newEmployee.getLastName() + 
                                     " has been assigned " + jobType + "-" + job + " job\n");
                }
                return;
            }
        }
        System.out.println("\nStudent not found.\n");
    }

    /**
     * Displays all students with on-campus jobs
     */
    private static void displayStudentsWithJobs() {
        System.out.println();
        for (Student student : students) {
            if (student instanceof Student_Employee) {
                Student_Employee employee = (Student_Employee) student;
                if (employee.getJob() != null) {
                    System.out.println(employee.getFirstName() + " " + employee.getLastName());
                    System.out.println("ID - " + employee.getStudentID());
                    System.out.println(employee.getEmploymentType() + " " + employee.getJob() + "\n");
                }
            }
        }
    }

    /**
     * Course Management System (CMS) - handles all course-related operations
     * @param input Scanner object for user input
     */
    public static void courseManagementSystem(Scanner input) {
        while (true) {
            // Display CMS menu
            System.out.println("***Welcome to CMS***\n");
            System.out.println("Press '1' to add a new course");
            System.out.println("Press '2' to assign student a new course");
            System.out.println("Press '3' to display student with assigned courses");
            System.out.println("Press '0' to exit CMS");
            System.out.println();

            String choice = input.nextLine();

            // Process CMS menu selection
            switch (choice) {
                case "1":
                    addCourse(input);           // Add new course
                    break;
                case "2":
                    assignStudentToCourse(input); // Enroll student
                    break;
                case "3":
                    displayStudentCourses(input); // Show enrollments
                    break;
                case "0":
                    return;                     // Return to main menu
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

    /**
     * Adds a new course to the system
     * @param input Scanner object for user input
     */
    private static void addCourse(Scanner input) {
        System.out.print("Enter course ID: ");
        int courseID = Integer.parseInt(input.nextLine());
        
        System.out.print("Enter course name: ");
        String courseName = input.nextLine();
        
        // Create and store new course
        Course newCourse = new Course(courseID, courseName);
        courses.add(newCourse);
        
        // Write course to file
        try (PrintWriter writer = new PrintWriter(new FileWriter("Courses.txt", true))) {
            writer.println(courseID + "," + courseName);
            System.out.println("\nConfirmation: New course " + courseID + " has been added\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Assigns a student to a course
     * @param input Scanner object for user input
     */
    private static void assignStudentToCourse(Scanner input) {
        System.out.print("Enter student ID: ");
        int studentID = Integer.parseInt(input.nextLine());
        
        System.out.print("Enter course ID: ");
        int courseID = Integer.parseInt(input.nextLine());
        
        // Find student and course
        Student student = students.stream()
                               .filter(s -> s.getStudentID() == studentID)
                               .findFirst()
                               .orElse(null);
                               
        Course course = courses.stream()
                            .filter(c -> c.getCourseID() == courseID)
                            .findFirst()
                            .orElse(null);
        
        if (student != null && course != null) {
            // Enroll student and record assignment
            student.addCourse(course);
            
            try (PrintWriter writer = new PrintWriter(new FileWriter("CourseAssignment.txt", true))) {
                writer.println(studentID + "," + courseID);
                System.out.println("\nConfirmation: " + student.getFirstName() + " " + student.getLastName() + 
                                 " has been assigned course " + courseID + "\n");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("\nStudent or course not found.\n");
        }
    }

    /**
     * Displays courses assigned to a specific student
     * @param input Scanner object for user input
     */
    private static void displayStudentCourses(Scanner input) {
        System.out.print("Enter student ID: ");
        int studentID = Integer.parseInt(input.nextLine());
        
        // Find student
        Student student = students.stream()
                               .filter(s -> s.getStudentID() == studentID)
                               .findFirst()
                               .orElse(null);
        
        if (student != null) {
            // Display enrollment information
            System.out.println("\n" + student.getFirstName() + " " + student.getLastName());
            System.out.println("ID - " + student.getStudentID());
            System.out.print("Courses: ");
            
            if (student.getCourses().isEmpty()) {
                System.out.println("None\n");
            } else {
                student.getCourses().forEach(course -> System.out.print(course.getCourseID() + " "));
                System.out.println("\n");
            }
        } else {
            System.out.println("\nStudent not found.\n");
        }
    }
}