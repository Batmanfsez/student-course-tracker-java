import java.util.ArrayList;

/**
 * Interface defining required methods for Student classes
 * Ensures consistency across all student implementations
 */
public interface StudentInterface {
    int getStudentID();
    String getFirstName();
    String getLastName();
    String getLevel();
    boolean isActive();
    void setActive(boolean active);
    void addCourse(Course course);
    ArrayList<Course> getCourses();
}