package ru.vsuet.hei_project.service;

import ru.vsuet.hei_project.domain.Group;
import ru.vsuet.hei_project.repo.IRepository;

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
