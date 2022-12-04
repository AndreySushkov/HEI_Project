package ru.vsuet.hei_project.service;

import ru.vsuet.hei_project.domain.Course;
import ru.vsuet.hei_project.domain.Student;
import ru.vsuet.hei_project.repo.IRepository;

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
