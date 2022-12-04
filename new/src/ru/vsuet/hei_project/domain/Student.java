package ru.vsuet.hei_project.domain;

public class Student extends Person{
    private Long id;
    private Long group_id;
    int yearStudy;
    int numberRecordBook;

    public Student(Long id, String fio, int yearBirth, int monthBirth, int dayBirth, int yearStudy, int numberRecordBook) {
        super(fio, yearBirth, monthBirth, dayBirth);
        this.id = id;
        this.yearStudy = yearStudy;
        this.numberRecordBook = numberRecordBook;
    }

    public Student(Long id, String fio, int yearBirth, int monthBirth, int dayBirth, int yearStudy, int numberRecordBook, Long group_id) {
        this(id, fio, yearBirth, monthBirth, dayBirth, yearStudy, numberRecordBook);
        this.group_id = group_id;
    }

    public Long getId() {
        return id;
    }

    public Long getGroup_id() {
        return group_id;
    }

    public int getNumberRecordBook() {
        return numberRecordBook;
    }

    public int getYearStudy() {
        return yearStudy;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fio=" + getFio() +
                ", dateBirth=" + getDayBirth() + "." + getMonthBirth() + "." + getYearBirth() +
                ", yearStudy=" + yearStudy +
                ", numberRecordBook=" + numberRecordBook +
                '}';
    }
}
