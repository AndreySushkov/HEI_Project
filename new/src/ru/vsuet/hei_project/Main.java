package ru.vsuet.hei_project;

import ru.vsuet.hei_project.domain.Course;
import ru.vsuet.hei_project.domain.Group;
import ru.vsuet.hei_project.domain.Student;
import ru.vsuet.hei_project.domain.Teacher;
import ru.vsuet.hei_project.repo.*;
import ru.vsuet.hei_project.service.*;
import ru.vsuet.hei_project.view.Menu;

public class Main {
    public static void main(String[] args) {
        DataBaseConnector connector = new DataBaseConnector();

        IRepository<Teacher> teacherRepository = new TeacherRepository(connector);
        IService<Teacher> teacherService = new TeacherService(teacherRepository);

        IRepository<Course> courseRepository = new CourseRepository(connector);
        IService<Course> courseService = new CourseService(courseRepository);

        IRepository<Student> studentRepository = new StudentRepository(connector);
        IService<Student> studentService = new StudentService(studentRepository);

        IRepository<Group> groupRepository = new GroupRepository(connector);
        IService<Group> groupService = new GroupService(groupRepository);

        Menu menu = new Menu(teacherService, courseService, studentService, groupService);
        while (true) {
            menu.draw();
        }
    }
}
