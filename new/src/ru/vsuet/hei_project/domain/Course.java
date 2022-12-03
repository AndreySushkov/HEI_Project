package ru.vsuet.hei_project.domain;

public class Course {
    private Long id;
    private String title;
    private Long teacher_id;

    public Course(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Course(Long id, String title, Long teacher_id) {
        this(id, title);
        this.teacher_id = teacher_id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
