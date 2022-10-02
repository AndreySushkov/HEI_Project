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
        if (!courses.isEmpty()) {
            System.out.print("Какой курс вы хотите удалить из БД (по id): ");
            courses.remove(findCourseById(scanner.nextInt()));
        } else {
            System.out.println("Курсов нет в БД");
        }
    }

    public void updateCourse() {
        if (!courses.isEmpty()) {
            System.out.print("Какой курс вы хотите изменить (по id): ");
            Course course = findCourseById(scanner.nextInt());

            System.out.println("Если вы не хотите менять поле, отсавьте его пустым");

            System.out.print("Изменить название курса: ");
            scanner.nextLine();                         //блокирует \n
            String updatedTitle = scanner.nextLine();
            if (!updatedTitle.equals("")) {
                course.setTitle(updatedTitle);
            }

            System.out.print("Изменить количество часов: ");
            String updatedNumberOfHours = scanner.nextLine();
            if (!updatedNumberOfHours.equals("")) {
                course.setNumberOfHours(Integer.parseInt(updatedNumberOfHours));
            }
        } else {
            System.out.println("Курсов нет в БД");
        }
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
