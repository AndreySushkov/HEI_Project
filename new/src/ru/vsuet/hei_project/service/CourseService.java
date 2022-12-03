package ru.vsuet.hei_project.service;

import ru.vsuet.hei_project.domain.Course;
import ru.vsuet.hei_project.repo.IRepository;

import java.util.List;

public class CourseService implements IService<Course> {
    private final IRepository<Course> repository;

    public CourseService(IRepository<Course> repository) {
        this.repository = repository;
    }

    @Override
    public Course getById(Long id) {
        return repository.find(id);
    }

    @Override
    public List<Course> getAll() {
        return repository.list();
    }

    @Override
    public void save(Course source) {
        repository.save(source);
    }

    @Override
    public void update(Course source) {
        repository.update(source);
    }

    @Override
    public void removeById(Long id) {
        Course target = getById(id);
        repository.remove(target);
    }
}
