import java.util.Scanner;

public class Main {
    public static Teachers teachers = new Teachers();
    public static Students students = new Students();
    public static Courses courses = new Courses();
    public static Groups groups = new Groups();

    public static  void main(String[] args) {
        Test();
        Menu();
    }

    public static void Menu() {
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
                                groups.addGroup(title);
                                break;
                            case 2:
                                //удаление группы из массива
                                if (!groups.getGroups().isEmpty()) {
                                    System.out.print("Какую группу вы хотите удалить (по id): ");
                                    groups.removeGroup(groups.findGroupById(scanner.nextInt()));
                                } else {
                                    System.out.println("В массиве нет групп");
                                }
                                break;
                            case 3:
                                //изменение одной из групп
                                if (!groups.getGroups().isEmpty()) {
                                    System.out.print("Какую группу вы хотите изменить (по id): ");
                                    Group updatedGroup = groups.findGroupById(scanner.nextInt());

                                    System.out.println("Если вы не хотите менять поле, отсавьте его пустым");

                                    System.out.print("Изменить название группы: ");
                                    scanner.nextLine();                         //блокирует \n
                                    String updatedTitle = scanner.nextLine();

                                    groups.updateGroup(updatedGroup, updatedTitle);
                                } else {
                                    System.out.println("Групп нет в массиве");
                                }
                                break;
                            case 4:
                                //добавление курса группе
                                if (!groups.getGroups().isEmpty()) {
                                    System.out.println("Какой группе вы хотите дать курс (по id)");
                                    Group group = groups.findGroupById(scanner.nextInt());                      //Ввод группы
                                    System.out.println("Какой курс вы хотите дать группе (по id)");
                                    Course course = courses.findCourseById(scanner.nextInt());                         //Ввод курса

                                    group.addCourseToGroup(course);                                        //Добавление курса группе
                                } else {
                                    System.out.print("Групп нет в массиве");
                                }
                                break;
                            case 5:
                                //удаление курса у группы
                                if (!groups.getGroups().isEmpty()) {
                                    System.out.println("У какой группы вы хотите удалить курс (по id)");
                                    Group group = groups.findGroupById(scanner.nextInt());                      //Ввод преподавателя

                                    if (!group.getCourses().isEmpty()) {
                                        System.out.println("Какой курс вы хотите удалить у группы (по id)");
                                        Course course = courses.findCourseById(scanner.nextInt());                     //Ввод курса
                                        group.removeCourseToGroup(course);                                 //Удаление курса у преподавателя
                                    } else {
                                        System.out.println("У этой группы нет курсов");
                                    }
                                } else {
                                    System.out.print("Групп нет в массиве");
                                }
                                break;
                            case 6:
                                //вывод всех групп
                                groups.showGroups();
                                break;
                            case 7:
                                //вывод всех групп и их курсы
                                groups.showGroupsWithCourses();
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

    //Функция, предназначенная для тестов
    public static void Test() {
        teachers.addTeacher("Павлова Ирина Васильевна", 1972);
        teachers.addTeacher("Петрова Елена Федоровна", 1978);
        teachers.addTeacher("Иванова Катерина Захаровна", 1995);

        courses.addCourse("Программирование", 12);
        courses.addCourse("Информатика", 10);
        courses.addCourse("Математика", 10);

        teachers.findTeacherById(1).addCourseToTeacher(courses.findCourseById(1));
        teachers.findTeacherById(1).addCourseToTeacher(courses.findCourseById(3));
        teachers.findTeacherById(2).addCourseToTeacher(courses.findCourseById(2));
        teachers.findTeacherById(3).addCourseToTeacher(courses.findCourseById(3));

        students.addStudent("Миляев Андрей Викторович", 2004, 2);
        students.addStudent("Хренов Михаил Васильевич", 2002, 4);
        students.addStudent("Романов Иван Васильевич", 2000, 5);
        students.addStudent("Добрынина Наталья Алексеевна", 2005, 1);
        students.addStudent("Писцова Татьяна Михайловна", 2005, 1);

        groups.addGroup("У-1");
        groups.addGroup("У-2");
        groups.addGroup("У-3");

        groups.findGroupById(1).addCourseToGroup(courses.findCourseById(1));
        groups.findGroupById(1).addCourseToGroup(courses.findCourseById(2));
        groups.findGroupById(2).addCourseToGroup(courses.findCourseById(2));
        groups.findGroupById(2).addCourseToGroup(courses.findCourseById(3));
        groups.findGroupById(3).addCourseToGroup(courses.findCourseById(1));
        groups.findGroupById(3).addCourseToGroup(courses.findCourseById(3));

        students.findStudentById(1).setGroup(groups.findGroupById(1));
        students.findStudentById(2).setGroup(groups.findGroupById(1));
        students.findStudentById(5).setGroup(groups.findGroupById(1));
        students.findStudentById(3).setGroup(groups.findGroupById(2));
        students.findStudentById(4).setGroup(groups.findGroupById(2));
    }
}
