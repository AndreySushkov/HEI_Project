package ru.vsuet.hei_project.view;

import ru.vsuet.hei_project.domain.Group;
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
        System.out.print("Введите год рождения: ");
        int yearBirth = keyboard.nextInt();
        System.out.print("Введите месяц рождения: ");
        int monthBirth = keyboard.nextInt();
        System.out.print("Введите день рождения: ");
        int dayBirth = keyboard.nextInt();
        System.out.print("Введите курс обучения: ");
        int yearStudy = keyboard.nextInt();
        System.out.print("Введите номер зачетной книжки: ");
        int numberRecordBook = keyboard.nextInt();
        Student student = new Student(0L, fio, yearBirth, monthBirth, dayBirth, yearStudy, numberRecordBook);     //id не важен
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

        System.out.println("Оставьте поле пустым если не хотите его менять");

        System.out.print("Введите ФИО: ");
        keyboard.nextLine();
        String newFio = keyboard.nextLine();
        if (newFio == "") {
            newFio = student.getFio();
        }
        System.out.print("Введите год рождения: ");
        String sNewYearBirth = keyboard.nextLine();
        int newYearBirth = student.getYearBirth();
        if (sNewYearBirth != "") {
            newYearBirth = Integer.parseInt(sNewYearBirth);
        }
        System.out.print("Введите месяц рождения: ");
        String sNewMonthBirth = keyboard.nextLine();
        int newMonthBirth = student.getMonthBirth();
        if (sNewMonthBirth != "") {
            newMonthBirth = Integer.parseInt(sNewMonthBirth);
        }
        System.out.print("Введите день рождения: ");
        String sNewDayBirth = keyboard.nextLine();
        int newDayBirth = student.getDayBirth();
        if (sNewDayBirth != "") {
            newDayBirth = Integer.parseInt(sNewDayBirth);
        }
        System.out.print("Введите курс обучения: ");
        String sNewYearStudy = keyboard.nextLine();
        int newYearStudy = student.getYearStudy();
        if (sNewYearStudy != "") {
            newYearStudy = Integer.parseInt(sNewYearStudy);
        }
        System.out.print("Введите номер зачетной книжки: ");
        String sNewNumberRecordBook = keyboard.nextLine();
        int newNumberRecordBook = student.getNumberRecordBook();
        if (sNewNumberRecordBook != "") {
            newNumberRecordBook = Integer.parseInt(sNewNumberRecordBook);
        }

        Student newStudent = new Student(id, newFio, newYearBirth, newMonthBirth, newDayBirth, newYearStudy, newNumberRecordBook);
        studentService.update(newStudent);

        System.out.println("Студент обновлен");
        System.out.println();
    }
}
