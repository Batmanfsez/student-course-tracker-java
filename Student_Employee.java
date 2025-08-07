/**
 * Represents a student employee (subclass of Student)
 * Adds employment-specific information (job type and position)
 */
public class Student_Employee extends Student {
    private String employmentType;  // full-time or part-time
    private String job;             // TA or RA

    /**
     * Constructor for Student_Employee
     * @param studentID Unique identifier
     * @param firstName First name
     * @param lastName Last name
     * @param level Academic level
     */
    public Student_Employee(int studentID, String firstName, String lastName, String level) {
        super(studentID, firstName, lastName, level);
    }

    // Getters and setters with brief comments
    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
}