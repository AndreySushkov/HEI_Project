package ru.vsuet.hey_project_with_javafx.service;

import ru.vsuet.hey_project_with_javafx.domain.Student;
import ru.vsuet.hey_project_with_javafx.repo.IRepository;

import java.util.List;

public class StudentService implements IService<Student>{
    private IRepository<Student> repository;

    public StudentService(IRepository<Student> repository) {
        this.repository = repository;
    }

    @Override
    public Student getById(Long id) {
        return repository.find(id);
    }

    @Override
    public List<Student> getAll() {
        return repository.list();
    }

    @Override
    public void save(Student source) {
        repository.save(source);
    }

    @Override
    public void update(Student source) {
        repository.update(source);
    }

    @Override
    public void removeById(Long id) {
        Student target = getById(id);
        repository.remove(target);
    }
}
