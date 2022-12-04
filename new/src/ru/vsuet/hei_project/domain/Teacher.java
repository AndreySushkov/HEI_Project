package ru.vsuet.hei_project.domain;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{
    private Long id;

    private List<Course> courses;

    public Teacher(Long id, String fio, int yearBirth, int monthBirth, int dayBirth) {
        super(fio, yearBirth, monthBirth, dayBirth);
        this.id = id;
        this.courses = new ArrayList<>();
    }

    public Teacher(Long id, String fio, int yearBirth, int monthBirth, int dayBirth, List<Course> courses) {
        this(id, fio, yearBirth, monthBirth, dayBirth);
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", fio='" + getFio() + '\'' +
                ", dateBirth=" + getDayBirth() + "." + getMonthBirth() + "." + getYearBirth() +
                ", courses=" + courses +
                '}';
    }
}
