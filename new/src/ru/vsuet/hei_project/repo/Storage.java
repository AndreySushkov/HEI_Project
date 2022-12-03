package ru.vsuet.hei_project.repo;

import ru.vsuet.hei_project.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Storage instance;

    List<Teacher> teachers;

    private Storage() {
        this.teachers = new ArrayList<>();
        init();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void init() {
        Teacher teacher1 = new Teacher(1L, "Петрова");
        Teacher teacher2 = new Teacher(2L, "Иванов");

        teachers.add(teacher1);
        teachers.add(teacher2);
    }
}
