import java.util.ArrayList;
import java.util.Scanner;

public class Teacher {
    private int id;
    private String fio;
    private int age;
    private ArrayList<Course> courses = new ArrayList<Course>();

    public Teacher() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ФИО преподавателя: ");
        this.fio = scanner.nextLine();
        System.out.print("Введите возраст преподавателя: ");
        this.age = scanner.nextInt();
    }

    public void addCourseForTeacher(Course course) {courses.add(course);}

    public void removeCourseForTeacher(Course course) {
        courses.remove(course);
    }

    public void setId(int id) {this.id = id;}
    public void setFio(String fio) {this.fio = fio;}
    public void setAge(int age) {this.age = age;}
    public void setCourses(ArrayList<Course> courses) {this.courses = courses;}

    public int getId() {return this.id;}
    public String getFio() {return this.fio;}
    public int getAge() {return this.age;}
    public ArrayList<Course> getCourses() {return this.courses;}
}
