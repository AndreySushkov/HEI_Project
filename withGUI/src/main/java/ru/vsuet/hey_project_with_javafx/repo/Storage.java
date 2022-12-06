package ru.vsuet.hey_project_with_javafx.repo;

import ru.vsuet.hey_project_with_javafx.domain.Teacher;

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
