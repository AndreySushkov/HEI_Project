package ru.vsuet.hei_project.domain;

public class Student {
    private Long id;
    private String fio;
    private int yearOfBirth;
    private Long group_id;

    public Student(Long id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    public Student(Long id, String fio, Long group_id) {
        this(id, fio);
        this.group_id = group_id;
    }

    public Long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public Long getGroup_id() {
        return group_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
