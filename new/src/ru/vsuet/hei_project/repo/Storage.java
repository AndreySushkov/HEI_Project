package ru.vsuet.hei_project.repo;

import ru.vsuet.hei_project.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Storage instance;

    List<Teacher> teachers;

    private Storage() {
        this.teachers = new ArrayList<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
}
