package ru.vsuet.hey_project_with_javafx.service;

import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.repo.IRepository;

import java.util.List;

public class TeacherService implements IService<Teacher> {
    private final IRepository<Teacher> repository;

    public TeacherService(IRepository<Teacher> repository) {
        this.repository = repository;
    }

    @Override
    public Teacher getById(Long id) {
        return repository.find(id);
    }

    @Override
    public List<Teacher> getAll() {
        return repository.list();
    }

    @Override
    public void save(Teacher source) {
        repository.save(source);
    }

    @Override
    public void update(Teacher source) {
        repository.update(source);
    }

    @Override
    public void removeById(Long id) {
        Teacher target = getById(id);
        repository.remove(target);
    }
}
