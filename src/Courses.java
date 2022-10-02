import java.util.ArrayList;
import java.util.Scanner;

public class Courses {
    private ArrayList<Course> courses = new ArrayList<Course>();

    private Scanner scanner = new Scanner(System.in);

    public void addCourse() {
        Course newCourse = new Course();
        if (courses.isEmpty()) {
            newCourse.setId(1);
        } else {
            newCourse.setId(courses.get(courses.size() - 1).getId() + 1);
        }
        courses.add(newCourse);
    }

    public void removeCourse() {
        System.out.print("Какой курс вы хотите удалить из БД (по id): ");
        courses.remove(findCourseById(scanner.nextInt()));
    }

    public void showCourses() {
        for (Course course : courses) {
            System.out.println(course.getId() + " " + course.getTitle());
        }
    }

    public Course findCourseById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public void setCourses(ArrayList<Course> courses) {this.courses = courses;}

    public ArrayList<Course> getCourses() {return this.courses;}
}
