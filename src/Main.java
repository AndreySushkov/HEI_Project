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
                    "7) Добавить курс преподавателю\n" +
                    "8) Удалить курс у преподавателя\n" +
                    "9) Показать всех преподавателей\n" +
                    "10) Показать все курсы\n" +
                    "11) Показать всех преподавателей и их курсы\n" +
                    "0) Выход из программы");
            choice = scanner.nextInt();

            Teacher teacher;
            Course course;
            switch (choice) {
                case 1:
                    teachers.addTeacher();
                    break;
                case 2:
                    teachers.removeTeacher();
                    break;
                case 3:
                    teachers.updateTeacher();
                    break;
                case 4:
                    courses.addCourse();
                    break;
                case 5:
                    courses.removeCourse();
                    break;
                case 6:
                    courses.updateCourse();
                    break;
                case 7:
                    if (!teachers.getTeachers().isEmpty()) {
                        System.out.println("Какому преподавателю вы хотите дать курс (по id)");
                        teacher = teachers.findTeacherById(scanner.nextInt());                      //Ввод преподавателя
                        if (teacher == null) {
                            System.out.println("Такого преподавателя нет");
                            break;
                        }
                        System.out.println("Какой курс вы хотите дать преподавателю (по id)");
                        course = courses.findCourseById(scanner.nextInt());                         //Ввод курса
                        if (course == null) {
                            System.out.println("Такого курса нет");
                            break;
                        }
                        teacher.addCourseForTeacher(course);                                        //Добавление курса преподавателю
                    } else {
                        System.out.print("Преподавателей нет в БД (что тоже крайне странно)");
                    }
                    break;
                case 8:
                    if (!teachers.getTeachers().isEmpty()) {
                        System.out.println("У какого преподавателя вы хотите удалить курс (по id)");
                        teacher = teachers.findTeacherById(scanner.nextInt());                      //Ввод преподавателя
                        if (teacher == null) {
                            System.out.println("Такого преподавателя нет");
                            break;
                        }
                        if (!teacher.getCourses().isEmpty()) {
                            System.out.println("Какой курс вы хотите удалить у преподавателя (по id)");
                            course = courses.findCourseById(scanner.nextInt());                     //Ввод курса
                            if (course == null) {
                                System.out.println("Такого курса нет");
                                break;
                            }
                            teacher.removeCourseForTeacher(course);                                 //Удаление курса у преподавателя
                        } else {
                            System.out.println("У этого преподавателя нет курсов (что странно)");
                        }
                    } else {
                        System.out.print("Преподавателей нет в БД (что тоже крайне странно)");
                    }
                    break;
                case 9:
                    teachers.showTeachers();
                    break;
                case 10:
                    courses.showCourses();
                    break;
                case 11:
                    teachers.showTeachersWithCourses();
                    break;
            }

            System.out.println();
        } while (choice != 0);
    }
}
