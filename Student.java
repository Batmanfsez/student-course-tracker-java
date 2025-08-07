import java.util.ArrayList;

/**
 * Represents a student with basic information
 * Implements StudentInterface to ensure required methods are available
 */
public class Student implements StudentInterface {
    private int studentID;
    private String firstName;
    private String lastName;
    private String level;
    private boolean active;
    private ArrayList<Course> courses;

    /**
     * Constructor for Student
     * @param studentID Unique identifier for the student
     * @param firstName Student's first name
     * @param lastName Student's last name
     * @param level Academic level (Freshman/Sophomore/Junior/Senior)
     */
    public Student(int studentID, String firstName, String lastName, String level) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.active = true;  // Students are active by default
        this.courses = new ArrayList<>();
    }

    // Interface method implementations with brief comments
    @Override public int getStudentID() { return studentID; }
    @Override public String getFirstName() { return firstName; }
    @Override public String getLastName() { return lastName; }
    @Override public String getLevel() { return level; }
    @Override public boolean isActive() { return active; }
    @Override public void setActive(boolean active) { this.active = active; }
    
    /**
     * Adds a course to the student's enrolled courses
     * @param course Course object to add
     */
    @Override public void addCourse(Course course) { courses.add(course); }
    
    /**
     * Gets list of enrolled courses
     * @return ArrayList of Course objects
     */
    @Override public ArrayList<Course> getCourses() { return courses; }
}