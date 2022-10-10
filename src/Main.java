import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;

public class Main {
    public static  void main(String[] args) throws SQLException, ClassNotFoundException {
        Teachers teachers = new Teachers();
        Students students = new Students();
        Courses courses = new Courses();
        Groups groups = new Groups();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Выберите таблицу с которой будете работать\n" +
                    "1) Преподаватели\n" +
                    "2) Курсы\n" +
                    "3) Группы\n" +
                    "4) Студенты\n" +
                    "0) Выход из программы");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    do {
                        System.out.println("Выберите действие\n" +
                                "1) Добавить преподавателя\n" +
                                "2) Удалить преподавателя\n" +
                                "3) Редактировать преподавателя\n" +
                                "4) Добавить курс преподавателю\n" +
                                "5) Удалить курс у преподавателя\n" +
                                "6) Показать всех преподавателей\n" +
                                "7) Показать всех преподавателей и их курсы\n" +
                                "0) Выйти в главное меню");

                        choice = scanner.nextInt();
                        switch (choice) {
                            case 1:
                                //добавление нового преподавателя в массив
                                System.out.print("Введите ФИО преподавателя: ");
                                scanner.nextLine();
                                String fio = scanner.nextLine();
                                System.out.print("Введите год рождения преподавателя: ");
                                int yearOfBirth = scanner.nextInt();
                                teachers.addTeacher(fio, yearOfBirth);
                                break;
                            case 2:
                                //удаление преподавателя из массива
                                if (!teachers.getTeachers().isEmpty()) {
                                    System.out.print("Какого преподавателя вы хотите удалить (по id): ");
                                    teachers.removeTeacher(teachers.findTeacherById(scanner.nextInt()));
                                } else {
                                    System.out.println("В массиве нет преподавателей");
                                }
                                break;
                            case 3:
                                //изменение одного из преподавателй
                                if (!teachers.getTeachers().isEmpty()) {
                                    System.out.print("Какого преподавателя вы хотите изменить (по id): ");
                                    Teacher updatedTeacher = teachers.findTeacherById(scanner.nextInt());

                                    System.out.println("Если вы не хотите менять поле, оставьте его пустым");

                                    System.out.print("Изменить ФИО преподавателя: ");
                                    scanner.nextLine();                         //блокирует \n
                                    String updatedFio = scanner.nextLine();

                                    System.out.print("Изменить возраст преподавателя: ");
                                    String updatedYearOfBirth = scanner.nextLine();

                                    teachers.updateTeacher(updatedTeacher, updatedFio, updatedYearOfBirth);
                                } else {
                                    System.out.println("В массиве нет преподавателей");
                                }
                                break;
                            case 4:
                                //добавление курса преподавателю
                                if (!teachers.getTeachers().isEmpty()) {
                                    System.out.println("Какому преподавателю вы хотите дать курс (по id)");
                                    Teacher teacher = teachers.findTeacherById(scanner.nextInt());                      //Ввод преподавателя
                                    System.out.println("Какой курс вы хотите дать преподавателю (по id)");
                                    Course course = courses.findCourseById(scanner.nextInt());                         //Ввод курса

                                    teacher.addCourseToTeacher(course);                                        //Добавление курса преподавателю
                                } else {
                                    System.out.print("Преподавателей нет в массиве");
                                }
                                break;
                            case 5:
                                //удаление курса у преподавателя
                                if (!teachers.getTeachers().isEmpty()) {
                                    System.out.println("У какого преподавателя вы хотите удалить курс (по id)");
                                    Teacher teacher = teachers.findTeacherById(scanner.nextInt());                      //Ввод преподавателя

                                    if (!teacher.getCourses().isEmpty()) {
                                        System.out.println("Какой курс вы хотите удалить у преподавателя (по id)");
                                        Course course = courses.findCourseById(scanner.nextInt());                     //Ввод курса
                                        teacher.removeCourseToTeacher(course);                                 //Удаление курса у преподавателя
                                    } else {
                                        System.out.println("У этого преподавателя нет курсов");
                                    }
                                } else {
                                    System.out.print("Преподавателей нет в массиве");
                                }
                                break;
                            case 6:
                                //вывод всех преподавателей
                                teachers.showTeachers();
                                break;
                            case 7:
                                //вывод всех преподавателей и их курсы
                                teachers.showTeachersWithCourses();
                                break;
                        }

                        System.out.println();
                    } while (choice != 0);
                    choice = -1;
                    break;
                case 2:
                    do {
                        System.out.println("Выберите действие\n" +
                                "1) Добавить курс\n" +
                                "2) Удалить курс\n" +
                                "3) Редактировать курс\n" +
                                "4) Показать все курсы\n" +
                                "0) Выход в главное меню");

                        choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                //добавление нового курса в массив
                                System.out.print("Введите название курса: ");
                                scanner.nextLine();
                                String title = scanner.nextLine();
                                System.out.print("Введите количество часов курса: ");
                                int numberOfHours = scanner.nextInt();
                                courses.addCourse(title, numberOfHours);
                                break;
                            case 2:
                                //удаление курса из массива
                                if (!courses.getCourses().isEmpty()) {
                                    System.out.print("Какой курс вы хотите удалить (по id): ");
                                    courses.removeCourse(courses.findCourseById(scanner.nextInt()));
                                } else {
                                    System.out.println("В массиве нет курсов");
                                }
                                break;
                            case 3:
                                //изменение одного из курсов
                                if (!courses.getCourses().isEmpty()) {
                                    System.out.print("Какой курс вы хотите изменить (по id): ");
                                    Course updatedCourse = courses.findCourseById(scanner.nextInt());

                                    System.out.println("Если вы не хотите менять поле, отсавьте его пустым");

                                    System.out.print("Изменить название курса: ");
                                    scanner.nextLine();                         //блокирует \n
                                    String updatedTitle = scanner.nextLine();

                                    System.out.print("Изменить количество часов: ");
                                    String updatedNumberOfHours = scanner.nextLine();

                                    courses.updateCourse(updatedCourse, updatedTitle, updatedNumberOfHours);
                                } else {
                                    System.out.println("Курсов нет в массиве");
                                }
                                break;
                            case 4:
                                //вывод всех курсов
                                courses.showCourses();
                                break;
                        }

                        System.out.println();
                    } while (choice != 0);
                    choice = -1;
                    break;
                case 3:
                    break;
                case 4:
                    do {
                        System.out.println("Выберите действие\n" +
                                "1) Добавить студента\n" +
                                "2) Удалить студента\n" +
                                "3) Редактировать студента\n" +
                                "4) Добавить студента в группу\n" +
                                "5) Показать всех студентов\n" +
                                "0) Выйти в главное меню");

                        choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                //добавление студента в массив
                                System.out.print("Введите ФИО студента: ");
                                scanner.nextLine();
                                String fio = scanner.nextLine();
                                System.out.print("Введите год рождения студента: ");
                                int yearOfBirth = scanner.nextInt();
                                System.out.print("Введите курс студента: ");
                                int yearOfStudy = scanner.nextInt();

                                students.addStudent(fio, yearOfBirth, yearOfStudy);
                                break;
                            case 2:
                                //удаление студента из массива
                                if (!students.getStudents().isEmpty()) {
                                    System.out.print("Какого студента вы хотите удалить (по id): ");
                                    students.removeStudent(students.findStudentById(scanner.nextInt()));
                                } else {
                                    System.out.println("Студентов нет в массиве");
                                }
                                break;
                            case 3:
                                //изменение одного из студентов
                                if (!students.getStudents().isEmpty()) {
                                    System.out.print("Какого студента вы хотите изменить (по id): ");
                                    Student updatedStudent = students.findStudentById(scanner.nextInt());

                                    System.out.println("Если вы не хотите менять поле, отсавьте его пустым");

                                    System.out.print("Изменить ФИО студента: ");
                                    scanner.nextLine();                         //блокирует \n
                                    String updatedFio = scanner.nextLine();

                                    System.out.print("Изменить год рождения студента: ");
                                    String updatedYearOfBirth = scanner.nextLine();

                                    System.out.print("Изменить курс студента: ");
                                    String updatedYearOfStudy = scanner.nextLine();

                                    students.updateStudent(updatedStudent, updatedFio, updatedYearOfBirth, updatedYearOfStudy);
                                } else {
                                    System.out.println("Студентов нет в массиве");
                                }
                                break;
                            case 4:
                                //добавление студента в группу
                                if (!students.getStudents().isEmpty()) {
                                    System.out.print("Какого студента вы хотите добавить в группу (по id): ");
                                    Student student = students.findStudentById(scanner.nextInt());
                                    System.out.print("В какую группу вы хотите добавить студента (по id): ");
                                    Group group = groups.findGroupById(scanner.nextInt());

                                    student.addGroupToStudent(group);
                                } else {
                                    System.out.println("Студентов нет в массиве");
                                }
                                break;
                            case 5:
                                //Вывод всех студентов
                                students.showStudents();
                                break;
                        }

                        System.out.println();
                    } while (choice != 0);
                    choice = -1;
                    break;
            }

            System.out.println();
        } while (choice != 0);
    }
}
