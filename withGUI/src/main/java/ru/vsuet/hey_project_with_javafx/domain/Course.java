package ru.vsuet.hey_project_with_javafx.domain;

public class Course {
    private Long id;
    private String title;
    private int numberHours;
    private Long teacher_id;

    public Course(Long id, String title, int numberHours) {
        this.id = id;
        this.title = title;
        this.numberHours = numberHours;
    }

    public Course(Long id, String title, int numberHours, Long teacher_id) {
        this(id, title, numberHours);
        this.teacher_id = teacher_id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberHours() {
        return numberHours;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", numberHours=" + numberHours +
                ", teacher_id=" + teacher_id +
                '}';
    }
}
