import java.util.Scanner;

import java.sql.*;

public class Main {
    final static DataBase dataBase = DataBase.getInstance();

    public static  void main(String[] args) throws SQLException {
        //Test();
        dataBase.load();
        Menu();
    }

    private static void Menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Выберите таблицу с которой будете работать\n" +
                    "1) Преподаватели\n" +
                    "2) Курсы\n" +
                    "3) Группы\n" +
                    "4) Студенты\n" +
                    "5) Сохранить\n" +
                    "0) Выход из программы");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //таблица преподавателей
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
                                dataBase.addTeacher(fio, yearOfBirth);
                                break;
                            case 2:
                                //удаление преподавателя из массива
                                if (!dataBase.getTeachers().isEmpty()) {
                                    System.out.print("Какого преподавателя вы хотите удалить (по id): ");
                                    dataBase.removeTeacher(dataBase.findTeacherById(scanner.nextInt()));
                                } else {
                                    System.out.println("В массиве нет преподавателей");
                                }
                                break;
                            case 3:
                                //изменение одного из преподавателй
                                if (!dataBase.getTeachers().isEmpty()) {
                                    System.out.print("Какого преподавателя вы хотите изменить (по id): ");
                                    Teacher updatedTeacher = dataBase.findTeacherById(scanner.nextInt());

                                    System.out.println("Если вы не хотите менять поле, оставьте его пустым");

                                    System.out.print("Изменить ФИО преподавателя: ");
                                    scanner.nextLine();                         //блокирует \n
                                    String updatedFio = scanner.nextLine();

                                    System.out.print("Изменить возраст преподавателя: ");
                                    String updatedYearOfBirth = scanner.nextLine();

                                    dataBase.updateTeacher(updatedTeacher, updatedFio, updatedYearOfBirth);
                                } else {
                                    System.out.println("В массиве нет преподавателей");
                                }
                                break;
                            case 4:
                                //добавление курса преподавателю
                                if (!dataBase.getTeachers().isEmpty()) {
                                    System.out.println("Какому преподавателю вы хотите дать курс (по id)");
                                    Teacher teacher = dataBase.findTeacherById(scanner.nextInt());                      //Ввод преподавателя
                                    System.out.println("Какой курс вы хотите дать преподавателю (по id)");
                                    Course course = dataBase.findCourseById(scanner.nextInt());                         //Ввод курса

                                    dataBase.addCourseToTeacher(teacher, course);                                       //Добавление курса преподавателю
                                } else {
                                    System.out.print("Преподавателей нет в массиве");
                                }
                                break;
                            case 5:
                                //удаление курса у преподавателя
                                if (!dataBase.getTeachers().isEmpty()) {
                                    System.out.println("У какого преподавателя вы хотите удалить курс (по id)");
                                    Teacher teacher = dataBase.findTeacherById(scanner.nextInt());                      //Ввод преподавателя

                                    if (!teacher.getCourses().isEmpty()) {
                                        System.out.println("Какой курс вы хотите удалить у преподавателя (по id)");
                                        Course course = dataBase.findCourseById(scanner.nextInt());                     //Ввод курса
                                        dataBase.removeCourseToTeacher(teacher, course);                                //Удаление курса у преподавателя
                                    } else {
                                        System.out.println("У этого преподавателя нет курсов");
                                    }
                                } else {
                                    System.out.print("Преподавателей нет в массиве");
                                }
                                break;
                            case 6:
                                //вывод всех преподавателей
                                dataBase.showTeachers();
                                break;
                            case 7:
                                //вывод всех преподавателей и их курсы
                                dataBase.showTeachersWithCourses();
                                break;
                        }

                        System.out.println();
                    } while (choice != 0);
                    choice = -1;
                    break;
                case 2:
                    //таблица курсов
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
                                dataBase.addCourse(title, numberOfHours);
                                break;
                            case 2:
                                //удаление курса из массива
                                if (!dataBase.getCourses().isEmpty()) {
                                    System.out.print("Какой курс вы хотите удалить (по id): ");
                                    dataBase.removeCourse(dataBase.findCourseById(scanner.nextInt()));
                                } else {
                                    System.out.println("В массиве нет курсов");
                                }
                                break;
                            case 3:
                                //изменение одного из курсов
                                if (!dataBase.getCourses().isEmpty()) {
                                    System.out.print("Какой курс вы хотите изменить (по id): ");
                                    Course updatedCourse = dataBase.findCourseById(scanner.nextInt());

                                    System.out.println("Если вы не хотите менять поле, отсавьте его пустым");

                                    System.out.print("Изменить название курса: ");
                                    scanner.nextLine();                         //блокирует \n
                                    String updatedTitle = scanner.nextLine();

                                    System.out.print("Изменить количество часов: ");
                                    String updatedNumberOfHours = scanner.nextLine();

                                    dataBase.updateCourse(updatedCourse, updatedTitle, updatedNumberOfHours);
                                } else {
                                    System.out.println("Курсов нет в массиве");
                                }
                                break;
                            case 4:
                                //вывод всех курсов
                                dataBase.showCourses();
                                break;
                        }

                        System.out.println();
                    } while (choice != 0);
                    choice = -1;
                    break;
                case 3:
                    //таблица групп
                    do {
                        System.out.println("Выберите действие:\n" +
                                "1) Добавить группу\n" +
                                "2) Удалить группу\n" +
                                "3) Редактировать группу\n" +
                                "4) Добавить курс в группу\n" +
                                "5) Удалить курс из группы\n" +
                                "6) Показать все группы\n" +
                                "7) показать все группы и их курсы\n" +
                                "0) Выйти в главное меню");

                        choice = scanner.nextInt();

                        switch (choice) {
                            case 1:
                                //добавление новой группы в массив
                                System.out.print("Введите название группы: ");
                                scanner.nextLine();
                                String title = scanner.nextLine();
                                dataBase.addGroup(title);
                                break;
                            case 2:
                                //удаление группы из массива
                                if (!dataBase.getGroups().isEmpty()) {
                                    System.out.print("Какую группу вы хотите удалить (по id): ");
                                    dataBase.removeGroup(dataBase.findGroupById(scanner.nextInt()));
                                } else {
                                    System.out.println("В массиве нет групп");
                                }
                                break;
                            case 3:
                                //изменение одной из групп
                                if (!dataBase.getGroups().isEmpty()) {
                                    System.out.print("Какую группу вы хотите изменить (по id): ");
                                    Group updatedGroup = dataBase.findGroupById(scanner.nextInt());

                                    System.out.println("Если вы не хотите менять поле, отсавьте его пустым");

                                    System.out.print("Изменить название группы: ");
                                    scanner.nextLine();                         //блокирует \n
                                    String updatedTitle = scanner.nextLine();

                                    dataBase.updateGroup(updatedGroup, updatedTitle);
                                } else {
                                    System.out.println("Групп нет в массиве");
                                }
                                break;
                            case 4:
                                //добавление курса группе
                                if (!dataBase.getGroups().isEmpty()) {
                                    System.out.println("Какой группе вы хотите дать курс (по id)");
                                    Group group = dataBase.findGroupById(scanner.nextInt());                      //Ввод группы
                                    System.out.println("Какой курс вы хотите дать группе (по id)");
                                    Course course = dataBase.findCourseById(scanner.nextInt());                         //Ввод курса

                                    dataBase.addCourseToGroup(group, course);                                     //Добавление курса группе
                                } else {
                                    System.out.print("Групп нет в массиве");
                                }
                                break;
                            case 5:
                                //удаление курса у группы
                                if (!dataBase.getGroups().isEmpty()) {
                                    System.out.println("У какой группы вы хотите удалить курс (по id)");
                                    Group group = dataBase.findGroupById(scanner.nextInt());                      //Ввод преподавателя

                                    if (!group.getCourses().isEmpty()) {
                                        System.out.println("Какой курс вы хотите удалить у группы (по id)");
                                        Course course = dataBase.findCourseById(scanner.nextInt());                     //Ввод курса
                                        dataBase.removeCourseToGroup(group, course);                                 //Удаление курса у преподавателя
                                    } else {
                                        System.out.println("У этой группы нет курсов");
                                    }
                                } else {
                                    System.out.print("Групп нет в массиве");
                                }
                                break;
                            case 6:
                                //вывод всех групп
                                dataBase.showGroups();
                                break;
                            case 7:
                                //вывод всех групп и их курсы
                                dataBase.showGroupsWithCourses();
                                break;
                        }

                        System.out.println();
                    } while (choice != 0);
                    choice = -1;
                    break;
                case 4:
                    //таблица студентов
                    do {
                        System.out.println("Выберите действие\n" +
                                "1) Добавить студента\n" +
                                "2) Удалить студента\n" +
                                "3) Редактировать студента\n" +
                                "4) Добавить студента в группу\n" +
                                "5) Показать всех студентов\n" +
                                "6) Показать всех студентов и их группы\n" +
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

                                dataBase.addStudent(fio, yearOfBirth, yearOfStudy);
                                break;
                            case 2:
                                //удаление студента из массива
                                if (!dataBase.getStudents().isEmpty()) {
                                    System.out.print("Какого студента вы хотите удалить (по id): ");
                                    dataBase.removeStudent(dataBase.findStudentById(scanner.nextInt()));
                                } else {
                                    System.out.println("Студентов нет в массиве");
                                }
                                break;
                            case 3:
                                //изменение одного из студентов
                                if (!dataBase.getStudents().isEmpty()) {
                                    System.out.print("Какого студента вы хотите изменить (по id): ");
                                    Student updatedStudent = dataBase.findStudentById(scanner.nextInt());

                                    System.out.println("Если вы не хотите менять поле, отсавьте его пустым");

                                    System.out.print("Изменить ФИО студента: ");
                                    scanner.nextLine();                         //блокирует \n
                                    String updatedFio = scanner.nextLine();

                                    System.out.print("Изменить год рождения студента: ");
                                    String updatedYearOfBirth = scanner.nextLine();

                                    System.out.print("Изменить курс студента: ");
                                    String updatedYearOfStudy = scanner.nextLine();

                                    dataBase.updateStudent(updatedStudent, updatedFio, updatedYearOfBirth, updatedYearOfStudy);
                                } else {
                                    System.out.println("Студентов нет в массиве");
                                }
                                break;
                            case 4:
                                //добавление студента в группу
                                if (!dataBase.getStudents().isEmpty()) {
                                    System.out.print("Какого студента вы хотите добавить в группу (по id): ");
                                    Student student = dataBase.findStudentById(scanner.nextInt());
                                    System.out.print("В какую группу вы хотите добавить студента (по id): ");
                                    Group group = dataBase.findGroupById(scanner.nextInt());

                                    dataBase.addGroupToStudent(student, group);
                                } else {
                                    System.out.println("Студентов нет в массиве");
                                }
                                break;
                            case 5:
                                //Вывод всех студентов
                                dataBase.showStudents();
                                break;
                            case 6:
                                //Вывод всех студентов и их группы
                                dataBase.showStudentsWithGroups();
                                break;
                        }

                        System.out.println();
                    } while (choice != 0);
                    choice = -1;
                    break;
                case 5:
                    try {
                        dataBase.save();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            System.out.println();
        } while (choice != 0);
        try {
            dataBase.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Функция, предназначенная для тестов
    public static void Test() {
        dataBase.addTeacher("Павлова Ирина Васильевна", 1972);
        dataBase.addTeacher("Петрова Елена Федоровна", 1978);
        dataBase.addTeacher("Иванова Катерина Захаровна", 1995);

        dataBase.addCourse("Программирование", 12);
        dataBase.addCourse("Информатика", 10);
        dataBase.addCourse("Математика", 10);

        dataBase.addCourseToTeacher(dataBase.findTeacherById(1), dataBase.findCourseById(1));
        dataBase.addCourseToTeacher(dataBase.findTeacherById(1), dataBase.findCourseById(3));
        dataBase.addCourseToTeacher(dataBase.findTeacherById(2), dataBase.findCourseById(2));
        dataBase.addCourseToTeacher(dataBase.findTeacherById(3), dataBase.findCourseById(3));

        dataBase.addStudent("Панаев Андрей Викторович", 2004, 2);
        dataBase.addStudent("Скабичевский Михаил Васильевич", 2002, 4);
        dataBase.addStudent("Романов Иван Васильевич", 2000, 5);
        dataBase.addStudent("Добрынина Наталья Алексеевна", 2005, 1);
        dataBase.addStudent("Писцова Татьяна Михайловна", 2005, 1);

        dataBase.addGroup("У-1");
        dataBase.addGroup("У-2");
        dataBase.addGroup("У-3");

        dataBase.addCourseToGroup(dataBase.findGroupById(1), dataBase.findCourseById(1));
        dataBase.addCourseToGroup(dataBase.findGroupById(1), dataBase.findCourseById(2));
        dataBase.addCourseToGroup(dataBase.findGroupById(2), dataBase.findCourseById(2));
        dataBase.addCourseToGroup(dataBase.findGroupById(2), dataBase.findCourseById(3));
        dataBase.addCourseToGroup(dataBase.findGroupById(3), dataBase.findCourseById(1));
        dataBase.addCourseToGroup(dataBase.findGroupById(3), dataBase.findCourseById(3));

        dataBase.addGroupToStudent(dataBase.findStudentById(1), dataBase.findGroupById(1));
        dataBase.addGroupToStudent(dataBase.findStudentById(2), dataBase.findGroupById(1));
        dataBase.addGroupToStudent(dataBase.findStudentById(5), dataBase.findGroupById(1));
        dataBase.addGroupToStudent(dataBase.findStudentById(3), dataBase.findGroupById(2));
        dataBase.addGroupToStudent(dataBase.findStudentById(4), dataBase.findGroupById(2));
    }
}
