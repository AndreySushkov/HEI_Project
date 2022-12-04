package ru.vsuet.hei_project.view;

import ru.vsuet.hei_project.domain.Course;
import ru.vsuet.hei_project.domain.Teacher;
import ru.vsuet.hei_project.service.IService;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Scanner;

public class TeacherMenu {
    Scanner keyboard = new Scanner(System.in);

    private IService<Teacher> teacherService;
    private IService<Course> courseService;

    public TeacherMenu(IService<Teacher> teacherService, IService<Course> courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    // TODO: 03.12.2022 Переписать это недоразумение
    public boolean draw() {
        System.out.println("---- МЕНЮ ----");
        System.out.println("---- 1 - список преподавателей ----");
        System.out.println("---- 2 - преподаватель по id ----");
        System.out.println("---- 3 - сохранить нового преподавателя ----");
        System.out.println("---- 4 - удалить преподавателя по id ----");
        System.out.println("---- 5 - обновить преподавателя по id ----");
        System.out.println("---- 6 - добавить преподавателю курс ----");
        System.out.println("---- 7 - удалить у преподавателя курс ----");
        System.out.println("---- 0 - выход ----");

        boolean exit = execute();
        return exit;
    }

    private boolean execute() {
        int command = keyboard.nextInt();

        switch (command) {
            case 1 -> printTeacherList();
            case 2 -> printSingleTeacher();
            case 3 -> saveTeacher();
            case 4 -> removeTeacher();
            case 5 -> updateTeacher();
            case 6 -> addCourseToTeacher();
            case 7 -> removeCourseFromTeacher();
            case 0 -> {
                return false;
            }
            default -> throw new IllegalArgumentException("Данная команда не найдена");
        }
        return  true;
    }

    private void printTeacherList() {
        System.out.println("----СПИСОК ПРЕПОДАВАТЕЛЕЙ ----");
        teacherService.getAll().forEach(System.out::println);
        System.out.println();
    }

    private void printSingleTeacher() {
        System.out.println("---- ПРЕПОДАВАТЕЛЬ ----");
        System.out.print("Введите id: ");
        Long id = keyboard.nextLong();
        Teacher teacher = teacherService.getById(id);

        System.out.println(teacher);
        System.out.println();
    }

    private void saveTeacher() {
        System.out.println("---- СОХРАНЕНИЕ ПРЕПОДАВАТЕЛЯ ----");
        System.out.print("Введите ФИО: ");
        keyboard.nextLine();                           //нужно, чтобы не скипалась следующая строчка
        String fio = keyboard.nextLine();
        Teacher teacher = new Teacher(0L, fio);     //id не важен
        teacherService.save(teacher);

        System.out.println("Преподаватель записан");
        System.out.println();
    }

    private void removeTeacher() {
        System.out.println("---- УДАЛЕНИЕ ПРЕПОДАВАТЕЛЯ ----");
        System.out.print("Введите id: ");
        Long id = keyboard.nextLong();
        teacherService.removeById(id);

        System.out.println("Преподаватель удален");
        System.out.println();
    }

    private void updateTeacher() {
        System.out.println("---- ОБНОВЛЕНИЕ ПРЕПОДАВАТЕЛЯ ----");
        System.out.print("Введите id: ");
        Long id = keyboard.nextLong();
        Teacher teacher = teacherService.getById(id);

        System.out.print("Введите ФИО: ");
        keyboard.nextLine();
        String newFio = keyboard.nextLine();
        if (newFio == "") {
            newFio = teacher.getFio();
        }

        Teacher newTeacher = new Teacher(id, newFio);
        teacherService.update(newTeacher);

        System.out.println("Преподаватель обновлен");
        System.out.println();
    }

    private void addCourseToTeacher() {
        System.out.println("---- ДОБАВЛЕНИЕ КУРСА ПРЕПОДАВАТЕЛЮ ----");
        System.out.print("Введите id переподавателя: ");
        long tId = keyboard.nextLong();
        System.out.print("Введите id курса: ");
        long cId = keyboard.nextLong();

        Teacher oldTeacher = teacherService.getById(tId);
        Course oldCourse = courseService.getById(cId);

        List<Course> oldCourses = oldTeacher.getCourses();
        oldCourses.add(oldCourse);

        Teacher newTeacher = new Teacher(tId, oldTeacher.getFio(), oldCourses);
        Course newCourse = new Course(cId, oldCourse.getTitle(), tId);

        teacherService.update(newTeacher);
        courseService.update(newCourse);

        System.out.println("Курс преподавателю был добавлен");
        System.out.println();
    }

    private void removeCourseFromTeacher() {
        System.out.println("---- УДАЛЕНИЕ КУРСА У ПРЕПОДАВАТЕЛЯ ----");
        System.out.print("Введите id преподавателя: ");
        long tId = keyboard.nextLong();
        System.out.print("Введите id курса: ");
        long cId = keyboard.nextLong();

        Teacher oldTeacher = teacherService.getById(tId);
        Course oldCourse = courseService.getById(cId);

        List<Course> oldCourses = oldTeacher.getCourses();
        oldCourses.remove(oldCourse);

        Teacher newTeacher = new Teacher(oldTeacher.getId(), oldTeacher.getFio(), oldCourses);
        Course newCourse = new Course(cId, oldCourse.getTitle(), null);

        teacherService.update(newTeacher);
        courseService.update(newCourse);

        System.out.println("Курс у преподавателя был удален");
        System.out.println();
    }
}
