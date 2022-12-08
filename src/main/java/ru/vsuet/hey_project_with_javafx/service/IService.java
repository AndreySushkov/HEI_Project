package ru.vsuet.hey_project_with_javafx.service;

import java.util.List;

public interface IService<T> {
    T getById(Long id);
    List<T> getAll();
    void save(T source);
    void update(T source);
    void removeById(Long id);
}
