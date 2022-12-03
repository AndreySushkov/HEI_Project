package ru.vsuet.hei_project.domain;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private Long id;
    private String fio;
    private int yearOfBirth;

    private List<Course> courses;

    public Teacher(Long id, String fio) {
        this.id = id;
        this.fio = fio;
        this.courses = new ArrayList<>();
    }

    public Teacher(Long id, String fio, List<Course> courses) {
        this(id, fio);
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", courses=" + courses +
                '}';
    }
}
