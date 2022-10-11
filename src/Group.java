import java.util.ArrayList;

public class Group {
    private int id;
    private String title;
    private ArrayList<Course> courses = new ArrayList<>();

    public Group(String title) {
        this.title = title;
    }

    public void addCourseToGroup(Course course) {courses.add(course);}

    public void removeCourseToGroup(Course course) {courses.remove(course);}

    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setCourses(ArrayList<Course> courses) {this.courses = courses;}

    public int getId() {return this.id;}
    public String getTitle() {return this.title;}
    public ArrayList<Course> getCourses() {return this.courses;}
}
