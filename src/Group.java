import java.util.Scanner;

public class Group {
    private int id;
    private String title;
    private Course[] courses;

    public Group(String title) {
        this.title = title;
    }

    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setCourses(Course[] courses) {this.courses = courses;}

    public int getId() {return this.id;}
    public String getTitle() {return this.title;}
    public Course[] getCourses() {return this.courses;}
}
