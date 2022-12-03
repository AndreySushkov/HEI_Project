package ru.vsuet.hei_project.view;

import ru.vsuet.hei_project.domain.Course;
import ru.vsuet.hei_project.domain.Teacher;
import ru.vsuet.hei_project.service.IService;

import java.util.Scanner;

public class Menu {
    Scanner keyboard = new Scanner(System.in);

    private TeacherMenu teacherMenu;
    private CourseMenu courseMenu;

    private final IService<Teacher> teacherService;
    private final IService<Course> courseService;
    public Menu(IService<Teacher> teacherService, IService<Course> courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;

        teacherMenu = new TeacherMenu(teacherService, courseService);
        courseMenu = new CourseMenu(courseService);
    }

    public void draw() {
        System.out.println("---- МЕНЮ ----");
        System.out.println("---- 1 - преподаватели ----");
        System.out.println("---- 2 - курсы ----");
        System.out.println("---- 0 - выход в главное меню ----");

        execute();
    }

    private void execute() {
        int command = keyboard.nextInt();

        switch (command) {
            case 1 -> openTeacherMenu();
            case 2 -> openCourseMenu();
            case 0 -> System.exit(0);
            default -> throw new IllegalArgumentException("Данная команда не найдена");
        }
    }

    private void openTeacherMenu() {
        while (teacherMenu.draw()) {}
    }

    private void openCourseMenu() {
        while (courseMenu.draw()) {}
    }
}
