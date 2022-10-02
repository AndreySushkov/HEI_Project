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
        if (!teachers.isEmpty()) {
            System.out.print("Какого преподавателя вы хотите удалить (по id): ");
            teachers.remove(findTeacherById(scanner.nextInt()));
        } else {
            System.out.println("Преподавателей нет в БД");
        }
    }

    public void updateTeacher() {
        if (!teachers.isEmpty()) {
            System.out.print("Какого преподавателя вы хотите изменить (по id): ");
            Teacher teacher = findTeacherById(scanner.nextInt());

            System.out.println("Если вы не хотите менять поле, отсавьте его пустым");

            System.out.print("Изменить ФИО: ");
            scanner.nextLine();                         //блокирует \n
            String updatedFio = scanner.nextLine();
            if (!updatedFio.equals("")) {
                teacher.setFio(updatedFio);
            }

            System.out.print("Изменить возраст: ");
            String updatedAge = scanner.nextLine();
            if (!updatedAge.equals("")) {
                teacher.setAge(Integer.parseInt(updatedAge));
            }
        } else {
            System.out.println("Преподавателей нет в БД");
        }
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
