import java.util.ArrayList;
import java.util.Scanner;

public class Teachers {
    private ArrayList<Teacher> teachers = new ArrayList<Teacher>();

    private Scanner scanner = new Scanner(System.in);

    public void addTeacher() {
        Teacher newTeacher = new Teacher();
        if (teachers.isEmpty()) {
            newTeacher.setId(1);
        } else {
            newTeacher.setId(teachers.get(teachers.size() - 1).getId() + 1);
        }
        teachers.add(newTeacher);
    }

    public void removeTeacher() {
        System.out.print("Какого преподавателя вы хотите удалить (по id): ");
        teachers.remove(findTeacherById(scanner.nextInt()));
    }

    public void showTeachers() {
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getId() + " " + teacher.getFio());
        }
    }

    public void showTeachersWithCourses() {
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getId() + " " + teacher.getFio());
            for (Course course : teacher.getCourses()) {
                System.out.println("\t" + course.getId() + " " + course.getTitle());
            }
        }
    }

    public Teacher findTeacherById(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {this.teachers = teachers;}

    public ArrayList<Teacher> getTeachers() {return this.teachers;}
}
