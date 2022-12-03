package ru.vsuet.hei_project.view;

import ru.vsuet.hei_project.domain.Course;
import ru.vsuet.hei_project.service.IService;

import java.util.Scanner;

public class CourseMenu {
    Scanner keyboard = new Scanner(System.in);

    private IService<Course> courseService;

    public CourseMenu(IService<Course> courseService) {
        this.courseService = courseService;
    }

    // TODO: 03.12.2022 Переписать это недоразумение
    public boolean draw() {
        System.out.println("---- МЕНЮ ----");
        System.out.println("---- 1 - список курсов ----");
        System.out.println("---- 2 - курс по id ----");
        System.out.println("---- 3 - сохранить новый курс ----");
        System.out.println("---- 4 - удалить курс по id ----");
        System.out.println("---- 5 - обновить курс по id ----");
        System.out.println("---- 0 - выход ----");

        boolean exit = execute();
        return exit;
    }

    private boolean execute() {
        int command = keyboard.nextInt();

        switch (command) {
            case 1 -> printCourseList();
            case 2 -> printSingleCourse();
            case 3 -> saveCourse();
            case 4 -> removeCourse();
            case 5 -> updateCourse();
            case 0 -> {
                return false;
            }
            default -> throw new IllegalArgumentException("Данная команда не найдена");
        }
        return  true;
    }

    private void printCourseList() {
        System.out.println("----СПИСОК КУРСОВ ----");
        courseService.getAll().forEach(System.out::println);
        System.out.println();
    }

    private void printSingleCourse() {
        System.out.println("---- КУРС ----");
        System.out.print("Enter id: ");
        Long id = keyboard.nextLong();
        Course course = courseService.getById(id);

        System.out.println(course);
        System.out.println();
    }

    private void saveCourse() {
        System.out.println("---- СОХРАНЕНИЕ КУРСА ----");
        System.out.print("Enter title: ");
        keyboard.nextLine();                           //нужно, чтобы не скипалась следующая строчка
        String title = keyboard.nextLine();
        Course course = new Course(0L, title);     //id не важен
        courseService.save(course);

        System.out.println("Курс записан");
        System.out.println();
    }

    private void removeCourse() {
        System.out.println("---- УДАЛЕНИЕ КУРСА ----");
        System.out.print("Enter id: ");
        Long id = keyboard.nextLong();
        courseService.removeById(id);

        System.out.println("Курс удален");
        System.out.println();
    }

    private void updateCourse() {
        System.out.println("---- ОБНОВЛЕНИЕ КУРСА (В СВЕТЛОЕ БУДУЩЕЕ) ----");
        System.out.print("Enter id: ");
        Long id = keyboard.nextLong();
        Course course = courseService.getById(id);

        System.out.print("Enter title");
        keyboard.nextLine();
        String newTitle = keyboard.nextLine();
        if (newTitle == "") {
            newTitle = course.getTitle();
        }

        Course newCourse = new Course(id, newTitle);
        courseService.update(newCourse);

        System.out.println("Object is updated");
        System.out.println();
    }
}
