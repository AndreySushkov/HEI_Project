package ru.vsuet.hei_project.view;

import ru.vsuet.hei_project.domain.Group;
import ru.vsuet.hei_project.domain.Student;
import ru.vsuet.hei_project.service.IService;

import java.util.List;
import java.util.Scanner;

public class GroupMenu {
    Scanner keyboard = new Scanner(System.in);

    private IService<Group> groupService;
    private IService<Student> studentService;

    public GroupMenu(IService<Group> groupService, IService<Student> studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    // TODO: 03.12.2022 Переписать это недоразумение
    public boolean draw() {
        System.out.println("---- МЕНЮ ----");
        System.out.println("---- 1 - список групп ----");
        System.out.println("---- 2 - группа по id ----");
        System.out.println("---- 3 - сохранить новую группу ----");
        System.out.println("---- 4 - удалить группу по id ----");
        System.out.println("---- 5 - обновить группу по id ----");
        System.out.println("---- 6 - добавить в группу студента ----");
        System.out.println("---- 7 - удалить из группы студента ----");
        System.out.println("---- 0 - выход ----");

        boolean exit = execute();
        return exit;
    }

    private boolean execute() {
        int command = keyboard.nextInt();

        switch (command) {
            case 1 -> printGroupList();
            case 2 -> printSingleGroup();
            case 3 -> saveGroup();
            case 4 -> removeGroup();
            case 5 -> updateGroup();
            case 6 -> addStudentToGroup();
            case 7 -> removeStudentFromGroup();
            case 0 -> {
                return false;
            }
            default -> throw new IllegalArgumentException("Данная команда не найдена");
        }
        return  true;
    }

    private void printGroupList() {
        System.out.println("----СПИСОК ГРУПП ----");
        groupService.getAll().forEach(System.out::println);
        System.out.println();
    }

    private void printSingleGroup() {
        System.out.println("---- ГРУППА ----");
        System.out.print("Введите id: ");
        Long id = keyboard.nextLong();
        Group group = groupService.getById(id);

        System.out.println(group);
        System.out.println();
    }

    private void saveGroup() {
        System.out.println("---- СОХРАНЕНИЕ ГРУППЫ ----");
        System.out.print("Введите наименование: ");
        keyboard.nextLine();                           //нужно, чтобы не скипалась следующая строчка
        String title = keyboard.nextLine();
        Group group = new Group(0L, title);     //id не важен
        groupService.save(group);

        System.out.println("Группа записана");
        System.out.println();
    }

    private void removeGroup() {
        System.out.println("---- УДАЛЕНИЕ ГРУППЫ ----");
        System.out.print("Введите id: ");
        Long id = keyboard.nextLong();
        groupService.removeById(id);

        System.out.println("Группа удалена");
        System.out.println();
    }

    private void updateGroup() {
        System.out.println("---- ОБНОВЛЕНИЕ ГРУППЫ ----");
        System.out.print("Введите id: ");
        Long id = keyboard.nextLong();
        Group group = groupService.getById(id);

        System.out.print("Введите наименование: ");
        keyboard.nextLine();
        String newTitle = keyboard.nextLine();
        if (newTitle == "") {
            newTitle = group.getTitle();
        }

        Group newGroup = new Group(id, newTitle);
        groupService.update(newGroup);

        System.out.println("Группа обновлена");
        System.out.println();
    }

    private void addStudentToGroup() {
        System.out.println("---- ДОБАВЛЕНИЕ СТУДЕНТА В ГРУППУ ----");
        System.out.print("Введите id группы: ");
        long gId = keyboard.nextLong();
        System.out.print("Введите id студента: ");
        long sId = keyboard.nextLong();

        Group oldGroup = groupService.getById(gId);
        Student oldStudent = studentService.getById(sId);

        List<Student> oldStudents = oldGroup.getStudents();
        oldStudents.add(oldStudent);

        Group newGroup = new Group(gId, oldGroup.getTitle(), oldStudents);
        Student newStudent = new Student(sId, oldStudent.getFio(), gId);

        groupService.update(newGroup);
        studentService.update(newStudent);

        System.out.println("Студент в группу был добавлен");
        System.out.println();
    }

    private void removeStudentFromGroup() {
        System.out.println("---- УДАЛЕНИЕ СТУДЕНТА ИЗ ГРУППЫ ----");
        System.out.print("Введите id группы: ");
        long gId = keyboard.nextLong();
        System.out.print("Введите id студента: ");
        long sId = keyboard.nextLong();

        Group oldGroup = groupService.getById(gId);
        Student oldStudent = studentService.getById(sId);

        List<Student> oldStudents = oldGroup.getStudents();
        oldStudents.remove(oldStudent);

        Group newGroup = new Group(oldGroup.getId(), oldGroup.getTitle(), oldStudents);
        Student newStudent = new Student(sId, oldStudent.getFio(), null);

        groupService.update(newGroup);
        studentService.update(newStudent);

        System.out.println("Студент из группы был удален");
        System.out.println();
    }
}
