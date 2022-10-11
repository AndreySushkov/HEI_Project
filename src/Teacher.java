import java.util.ArrayList;

public class Teacher extends Person{
    private int id;
    private ArrayList<Course> courses = new ArrayList<>();

    public Teacher(String fio, int yearOfBirth) {
        super(fio, yearOfBirth);
    }

    public void addCourseToTeacher(Course course) {courses.add(course);}

    public void removeCourseToTeacher(Course course) {
        courses.remove(course);
    }

    public void setId(int id) {this.id = id;}
    public void setCourses(ArrayList<Course> courses) {this.courses = courses;}

    public int getId() {return this.id;}
    public ArrayList<Course> getCourses() {return this.courses;}
}
