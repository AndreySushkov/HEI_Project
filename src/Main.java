import java.util.Scanner;

public class Main {
    public static  void main(String[] args) {
        Teachers teachers = new Teachers();
        Courses courses = new Courses();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1) Добавить преподавателя\n" +
                    "2) Удалить преподавателя\n" +
                    "3) Редактировать преподавателя\n" +
                    "4) Добавить курс в БД\n" +
                    "5) Удалить курс из БД\n" +
                    "6) Редактировать курс в БД\n" +
                    "7) Показать всех преподавателей\n" +
                    "8) Показать все курсы\n" +
                    "9) Показать всех преподавателей и их курсы\n" +
                    "10) Добавить курс преподавателю\n" +
                    "11) Удалить курс у преподавателя\n" +
                    "0) Выход из программы");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    teachers.addTeacher();
                    break;
                case 2:
                    teachers.removeTeacher();
                    break;
                case 4:
                    courses.addCourse();
                    break;
                case 5:
                    courses.removeCourse();
                    break;
                case 7:
                    teachers.showTeachers();
                    break;
                case 8:
                    courses.showCourses();
                    break;
                case 9:
                    teachers.showTeachersWithCourses();
                    break;
                case 10:
                    System.out.println("Какому преподавателю вы хотите дать курс (по id)");
                    Teacher teacher = teachers.findTeacherById(scanner.nextInt());
                    System.out.println("Какой курс вы хотите дать преподавателю (по id)");
                    Course course = courses.findCourseById(scanner.nextInt());
                    teacher.addCourseForTeacher(course);
                    break;
                case 11:
                    System.out.println("У какого реподавателя вы хотите удалить курс (по id)");
                    teacher = teachers.findTeacherById(scanner.nextInt());
                    System.out.println("Какой курс вы хотите удалить у преподавателя (по id)");
                    course = courses.findCourseById(scanner.nextInt());
                    teacher.removeCourseForTeacher(course);
                    break;
            }

            System.out.println();
        } while (choice != 0);
    }
}
