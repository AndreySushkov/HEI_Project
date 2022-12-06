package ru.vsuet.hey_project_with_javafx.repo;

import java.util.List;

public interface IRepository<T> {
    List<T> list();
    T find(Long id);
    void save(T source);
    void update(T source);
    void remove(T target);
}
