import java.util.ArrayList;

public class DataBase {
    private static DataBase instance;

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
        Teacher newTeacher = new Teacher(fio, yearOfBirth);
        if (teachers.isEmpty()) {
            newTeacher.setId(1);
        } else {
            newTeacher.setId(teachers.get(teachers.size() - 1).getId() + 1);
        }
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
        Student newStudent = new Student(fio, yearOfBirth, yearOfStudy);
        if (students.isEmpty()) {
            newStudent.setId(1);
        } else {
            newStudent.setId(students.get(students.size() - 1).getId() + 1);
        }
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
            System.out.println(student.getId() + "\t" + student.getFio() + "\t (" + student.getGroup().getId() + ")" + student.getGroup().getTitle());
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
        Course newCourse = new Course(title, numberOfHours);
        if (courses.isEmpty()) {
            newCourse.setId(1);
        } else {
            newCourse.setId(courses.get(courses.size() - 1).getId() + 1);
        }
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


    //Группы
    public void addGroup(String title) {
        Group newGroup = new Group(title);
        if (groups.isEmpty()) {
            newGroup.setId(1);
        } else {
            newGroup.setId(groups.get(groups.size() - 1).getId() + 1);
        }
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
            for (Course course : group.getCourses()) {
                System.out.println("\t" + course.getId() + "\t" + course.getTitle());
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


    public void setTeachers(ArrayList<Teacher> teachers) {this.teachers = teachers;}
    public void setStudents(ArrayList<Student> students) {this.students = students;}
    public void setCourses(ArrayList<Course> courses) {this.courses = courses;}
    public void setGroups(ArrayList<Group> groups) {this.groups = groups;}

    public ArrayList<Teacher> getTeachers() {return this.teachers;}
    public ArrayList<Student> getStudents() {return this.students;}
    public ArrayList<Course> getCourses() {return this.courses;}
    public ArrayList<Group> getGroups() {return this.groups;}
}
