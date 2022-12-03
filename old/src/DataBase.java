import com.mysql.cj.jdbc.SuspendableXAConnection;

import java.util.ArrayList;
import java.sql.*;

public class DataBase {
    private static DataBase instance;
    private DBWorker worker = new DBWorker();
    private Statement statement;

    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Group> groups = new ArrayList<>();

    private DataBase() {}

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }


    //Преподаватели
    public void addTeacher(String fio, int yearOfBirth) {
        int id;
        if (teachers.isEmpty()) {
            id = 1;
        } else {
            id = teachers.get(teachers.size() - 1).getId() + 1;
        }
        Teacher newTeacher = new Teacher(id, fio, yearOfBirth);
        teachers.add(newTeacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public void updateTeacher(Teacher updatedTeacher, String updatedFio, String updatedYearOfBirth) {
        if (!updatedFio.equals("")) {
            updatedTeacher.setFio(updatedFio);
        }
        if (!updatedYearOfBirth.equals("")) {
            updatedTeacher.setYearOfBirth(Integer.parseInt(updatedYearOfBirth));
        }
    }

    public void showTeachers() {
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getId() + "\t" + teacher.getFio());
        }
    }

    public void showTeachersWithCourses() {
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getId() + "\t" + teacher.getFio());
            for (Course course : teacher.getCourses()) {
                System.out.println("\t" + course.getId() + "\t" + course.getTitle());
            }
        }
    }

    public void addCourseToTeacher(Teacher teacher, Course course) {
        teacher.getCourses().add(course);
    }

    public void removeCourseToTeacher(Teacher teacher, Course course) {
        teacher.getCourses().remove(course);
    }

    public Teacher findTeacherById(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == id) {
                return teacher;
            }
        }
        return null;
    }


    //Студенты
    public void addStudent(String fio, int yearOfBirth, int yearOfStudy) {
        int id;
        if (students.isEmpty()) {
            id = 1;
        } else {
            id = students.get(students.size() - 1).getId() + 1;
        }
        Student newStudent = new Student(id, fio, yearOfBirth, yearOfStudy);
        students.add(newStudent);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void updateStudent(Student updatedStudent, String updatedFio, String updatedYearOfBirth, String updatedYearOfStudy) {
        if (!updatedFio.equals("")) {
            updatedStudent.setFio(updatedFio);
        }
        if (!updatedYearOfBirth.equals("")) {
            updatedStudent.setYearOfBirth(Integer.parseInt(updatedYearOfBirth));
        }
        if (!updatedYearOfStudy.equals("")) {
            updatedStudent.setYearOfStudy(Integer.parseInt(updatedYearOfStudy));
        }
    }

    public void showStudents() {
        for (Student student : students) {
            System.out.println(student.getId() + "\t" + student.getFio());
        }
    }

    public void showStudentsWithGroups() {
        for (Student student : students) {
            System.out.print(student.getId() + "\t" + student.getFio());
            if (student.getGroup() != null) {
                System.out.print("\t (" + student.getGroup().getId() + ")" + student.getGroup().getTitle());
            }
            System.out.println();
        }
    }

    public void addGroupToStudent(Student student, Group group) {
        student.setGroup(group);
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }


    //Курсы
    public void addCourse(String title, int numberOfHours) {
        int id;
        if (courses.isEmpty()) {
            id = 0;
        } else {
            id = courses.get(courses.size() - 1).getId() + 1;
        }
        Course newCourse = new Course(id, title, numberOfHours);
        courses.add(newCourse);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public void updateCourse(Course updatedCourse, String updatedTitle, String updatedNumberOfHours) {
        if (!updatedTitle.equals("")) {
            updatedCourse.setTitle(updatedTitle);
        }
        if (!updatedNumberOfHours.equals("")) {
            updatedCourse.setNumberOfHours(Integer.parseInt(updatedNumberOfHours));
        }
    }

    public void showCourses() {
        for (Course course : courses) {
            System.out.println(course.getId() + "\t" + course.getTitle());
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

    //for load from DB
    public Course findCourseByTitle(String title) {
        for (Course course : courses) {
            if (course.getTitle().equals(title)) {
                return course;
            }
        }
        return null;
    }


    //Группы
    public void addGroup(String title) {
        int id;
        if (groups.isEmpty()) {
            id = 1;
        } else {
            id = groups.get(groups.size() - 1).getId() + 1;
        }
        Group newGroup = new Group(id, title);
        groups.add(newGroup);
    }

    public void removeGroup(Group group) {
        groups.remove(group);
    }

    public void updateGroup(Group updatedGroup, String updatedTitle) {
        if (!updatedTitle.equals("")) {
            updatedGroup.setTitle(updatedTitle);
        }
    }

    public void showGroups() {
        for (Group group : groups) {
            System.out.println(group.getId() + "\t" + group.getTitle());
        }
    }

    public void showGroupsWithCourses() {
        for (Group group : groups) {
            System.out.println(group.getId() + "\t" + group.getTitle());
            if (group.getCourses().size() != 0) {
                for (Course course : group.getCourses()) {
                    System.out.println("\t" + course.getId() + "\t" + course.getTitle());
                }
            }
        }
    }

    public void addCourseToGroup(Group group, Course course) {
        group.getCourses().add(course);
    }

    public void removeCourseToGroup(Group group, Course course) {
        group.getCourses().remove(course);
    }

    public Group findGroupById(int id) {
        for (Group group : groups) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    public Group findGroupByTitle(String title) {
        for (Group group : groups) {
            if (group.getTitle() == title) {
                return group;
            }
        }
        return null;
    }


    public void load() throws SQLException {
        statement = worker.getConnection().createStatement();

        //Курсы
        ResultSet resultSet = statement.executeQuery("select * from courses");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            int numberOfHours = resultSet.getInt("numberOfHours");
            courses.add(new Course(id, title, numberOfHours));
        }

        //Преподаватели
        resultSet = statement.executeQuery("select * from teachers;");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String fio = resultSet.getString("fio");
            int yearOfBirth = resultSet.getInt("yearOfBirth");

            Teacher newTeacher = new Teacher(id, fio, yearOfBirth);

            //загрузка курсов преподавателей
            if (resultSet.getString("courses") != null) {
                String[] coursesList = resultSet.getString("courses").split(", ");
                for (String courseTitle : coursesList) {
                    Course newCourse = findCourseByTitle(courseTitle);
                    addCourseToTeacher(newTeacher, newCourse);
                }
            }

            teachers.add(newTeacher);
        }

        //Группы
        resultSet = statement.executeQuery("select * from groups_");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");

            Group newGroup = new Group(id, title);

            //Загрузка курсов групп
            if (resultSet.getString("courses") != null) {
                String[] courseList = resultSet.getString("courses").split(", ");
                for (String courseTitle : courseList) {
                    Course newCourse = findCourseByTitle(courseTitle);
                    addCourseToGroup(newGroup, newCourse);
                }
            }

            groups.add(newGroup);
        }

        //Студенты
        resultSet = statement.executeQuery("select * from students");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String fio = resultSet.getString("fio");
            int yearOfBirth = resultSet.getInt("yearOfBirth");
            int yearOfStudy = resultSet.getInt("yearOfStudy");

            Student newStudent = new Student(id, fio, yearOfBirth, yearOfStudy);

            //загрузка групп студентов
            if (resultSet.getString("group_") != null) {
                String[] groupList = resultSet.getString("group_").split(", ");
                for (String groupTitle : groupList) {
                    Group newGroup = findGroupByTitle(groupTitle);
                    addGroupToStudent(newStudent, newGroup);
                }
            }

            students.add(newStudent);
        }
    }

    public void save() throws SQLException {
        statement = worker.getConnection().createStatement();
        statement.executeUpdate("delete from courses");
        statement.executeUpdate("delete from teachers");
        statement.executeUpdate("delete from groups_");
        statement.executeUpdate("delete from students");

        //Курсы
        PreparedStatement preparedStatement = worker.getConnection().prepareStatement("insert into courses(id, title, numberOfHours) values(?, ?, ?)");
        for (Course course : courses) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, course.getTitle());
            preparedStatement.setInt(3, course.getNumberOfHours());

            preparedStatement.execute();
        }

        //Преподаватели
        preparedStatement = worker.getConnection().prepareStatement("insert into teachers(id, fio, yearOfBirth, courses) values (?, ?, ?, ?)");
        for (Teacher teacher : teachers) {
            preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setString(2, teacher.getFio());
            preparedStatement.setInt(3, teacher.getYearOfBirth());

            //сохранение списка курсов
            String coursesList = null;
            if (!teacher.getCourses().isEmpty()) {
                coursesList = "";
                for (int i = 0; i < teacher.getCourses().size(); i++) {
                    coursesList += teacher.getCourses().get(i).getTitle();
                    if (i != teacher.getCourses().size() - 1) {
                        coursesList += ", ";
                    }
                }
            }
            preparedStatement.setString(4, coursesList);

            preparedStatement.execute();
        }

        //Группы
        preparedStatement = worker.getConnection().prepareStatement("insert into groups_(id, title, courses) values (?, ?, ?)");
        for (Group group : groups) {
            preparedStatement.setInt(1, group.getId());
            preparedStatement.setString(2, group.getTitle());

            //сохранение списка курсов
            String coursesList = null;
            if (!group.getCourses().isEmpty()) {
                coursesList = "";
                for (int i = 0; i < group.getCourses().size(); i++) {
                    coursesList += group.getCourses().get(i).getTitle();
                    if (i != group.getCourses().size() - 1) {
                        coursesList += ", ";
                    }
                }
            }
            preparedStatement.setString(3, coursesList);

            preparedStatement.execute();
        }

        //Студенты
        preparedStatement = worker.getConnection().prepareStatement("insert into students(id, fio, yearOfBirth, yearOfStudy, group_) values (?, ?, ?, ?, ?)");
        for (Student student : students) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getFio());
            preparedStatement.setInt(3, student.getYearOfBirth());
            preparedStatement.setInt(4, student.getYearOfStudy());

            //сохранение группы
            String group = null;
            if (student.getGroup() != null) {
                group = student.getGroup().getTitle();
            }
            preparedStatement.setString(5, group);

            preparedStatement.execute();
        }
    }

    public void setTeachers(ArrayList<Teacher> teachers) {this.teachers = teachers;}
    public void setStudents(ArrayList<Student> students) {this.students = students;}
    public void setCourses(ArrayList<Course> courses) {this.courses = courses;}
    public void setGroups(ArrayList<Group> groups) {this.groups = groups;}

    public ArrayList<Teacher> getTeachers() {return this.teachers;}
    public ArrayList<Student> getStudents() {return this.students;}
    public ArrayList<Course> getCourses() {return this.courses;}
    public ArrayList<Group> getGroups() {return this.groups;}
}
