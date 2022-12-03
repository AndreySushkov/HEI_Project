package ru.vsuet.hei_project;

import ru.vsuet.hei_project.domain.Course;
import ru.vsuet.hei_project.domain.Teacher;
import ru.vsuet.hei_project.repo.CourseRepository;
import ru.vsuet.hei_project.repo.DataBaseConnector;
import ru.vsuet.hei_project.repo.IRepository;
import ru.vsuet.hei_project.repo.TeacherRepository;
import ru.vsuet.hei_project.service.CourseService;
import ru.vsuet.hei_project.service.IService;
import ru.vsuet.hei_project.service.TeacherService;
import ru.vsuet.hei_project.view.Menu;

public class Main {
    public static void main(String[] args) {
        DataBaseConnector connector = new DataBaseConnector();

        IRepository<Teacher> teacherRepository = new TeacherRepository(connector);
        IService<Teacher> teacherService = new TeacherService(teacherRepository);

        IRepository<Course> courseRepository = new CourseRepository(connector);
        IService<Course> courseService = new CourseService(courseRepository);

        Menu menu = new Menu(teacherService, courseService);
        while (true) {
            menu.draw();
        }
    }
}
