package ru.vsuet.hey_project_with_javafx.service;

import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.repo.IRepository;

import java.util.List;

public class GroupService implements IService<Group> {
    private final IRepository<Group> repository;

    public GroupService(IRepository<Group> repository) {
        this.repository = repository;
    }

    @Override
    public Group getById(Long id) {
        return repository.find(id);
    }

    @Override
    public List<Group> getAll() {
        return repository.list();
    }

    @Override
    public void save(Group source) {
        repository.save(source);
    }

    @Override
    public void update(Group source) {
        repository.update(source);
    }

    @Override
    public void removeById(Long id) {
        Group target = getById(id);
        repository.remove(target);
    }
}
