package ru.vsuet.hei_project.domain;

public abstract class Person {
    private String fio;
    private int yearOfBirth;

    public Person(String fio, int yearOfBirth) {
        this.fio = fio;
        this.yearOfBirth = yearOfBirth;
    }
}
