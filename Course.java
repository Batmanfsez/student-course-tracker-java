/**
 * Represents a course with ID and name
 * Implements CourseInterface for required methods
 */
public class Course implements CourseInterface {
    private int courseID;    // Numeric course identifier (e.g., 3311)
    private String name;     // Course name (e.g., "Java 101")

    /**
     * Constructor for Course
     * @param courseID Unique course identifier
     * @param name Course name
     */
    public Course(int courseID, String name) {
        this.courseID = courseID;
        this.name = name;
    }

    // Interface implementations
    @Override public int getCourseID() { return courseID; }
    @Override public String getName() { return name; }
}