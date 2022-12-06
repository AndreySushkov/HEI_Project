package ru.vsuet.hey_project_with_javafx.domain;

public abstract class Person {
    private String fio;
    private int yearBirth;
    private int monthBirth;
    private int dayBirth;

    public Person(String fio, int yearBirth, int monthBirth, int dayBirth) {
        this.fio = fio;
        this.yearBirth = yearBirth;
        this.monthBirth = monthBirth;
        this.dayBirth = dayBirth;
    }

    public String getFio() {
        return fio;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public int getMonthBirth() {
        return monthBirth;
    }

    public int getDayBirth() {
        return dayBirth;
    }
}
