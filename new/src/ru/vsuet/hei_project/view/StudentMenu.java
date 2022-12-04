package ru.vsuet.hei_project.view;

import ru.vsuet.hei_project.domain.Student;
import ru.vsuet.hei_project.service.IService;

import java.util.Scanner;

public class StudentMenu {
    Scanner keyboard = new Scanner(System.in);

    private IService<Student> studentService;

    public StudentMenu(IService<Student> studentService) {
        this.studentService = studentService;
    }

    // TODO: 03.12.2022 Переписать это недоразумение
    public boolean draw() {
        System.out.println("---- МЕНЮ ----");
        System.out.println("---- 1 - список студентов ----");
        System.out.println("---- 2 - студент по id ----");
        System.out.println("---- 3 - сохранить нового студента ----");
        System.out.println("---- 4 - удалить студента по id ----");
        System.out.println("---- 5 - обновить студента по id ----");
        System.out.println("---- 0 - выход ----");

        boolean exit = execute();
        return exit;
    }

    private boolean execute() {
        int command = keyboard.nextInt();

        switch (command) {
            case 1 -> printStudentList();
            case 2 -> printSingleStudent();
            case 3 -> saveStudent();
            case 4 -> removeStudent();
            case 5 -> updateStudent();
            case 0 -> {
                return false;
            }
            default -> throw new IllegalArgumentException("Данная команда не найдена");
        }
        return  true;
    }

    private void printStudentList() {
        System.out.println("----СПИСОК СТУДЕНТОВ ----");
        studentService.getAll().forEach(System.out::println);
        System.out.println();
    }

    private void printSingleStudent() {
        System.out.println("---- СТУДЕНТ ----");
        System.out.print("Введите id: ");
        Long id = keyboard.nextLong();
        Student student = studentService.getById(id);

        System.out.println(student);
        System.out.println();
    }

    private void saveStudent() {
        System.out.println("---- СОХРАНЕНИЕ СТУДЕНТА ----");
        System.out.print("Введите ФИО: ");
        keyboard.nextLine();                           //нужно, чтобы не скипалась следующая строчка
        String fio = keyboard.nextLine();
        Student student = new Student(0L, fio);     //id не важен
        studentService.save(student);

        System.out.println("Студент записан");
        System.out.println();
    }

    private void removeStudent() {
        System.out.println("---- УДАЛЕНИЕ СТУДЕНТА ----");
        System.out.print("Введите id: ");
        Long id = keyboard.nextLong();
        studentService.removeById(id);

        System.out.println("Студент удален");
        System.out.println();
    }

    private void updateStudent() {
        System.out.println("---- ОБНОВЛЕНИЕ СТУДЕНТА ----");
        System.out.print("Введите id: ");
        Long id = keyboard.nextLong();
        Student student = studentService.getById(id);

        System.out.print("Введите ФИО: ");
        keyboard.nextLine();
        String newTitle = keyboard.nextLine();
        if (newTitle == "") {
            newTitle = student.getFio();
        }

        Student newStudent = new Student(id, newTitle);
        studentService.update(newStudent);

        System.out.println("Студент обновлен");
        System.out.println();
    }
}
