package ru.vsuet.hei_project.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private Long id;
    private String title;

    private List<Student> students = new ArrayList<>();

    public Group(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Group(Long id, String title, List<Student> students) {
        this(id, title);
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", students=" + students +
                '}';
    }
}
