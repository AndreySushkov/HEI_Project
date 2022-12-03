package ru.vsuet.hei_project.repo;

import ru.vsuet.hei_project.domain.Teacher;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTeacherRepository implements IRepository<Teacher> {
    private final List<Teacher> teachers;

    public InMemoryTeacherRepository() {
        this.teachers = Storage.getInstance().teachers;
    }

    @Override
    public List<Teacher> list() {
        return new ArrayList<>(teachers);
    }

    @Override
    public Teacher find(Long id) {
        return teachers.stream()
                .filter(teacher -> id.equals(teacher.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Teacher source) {
        teachers.add(source);
    }

    @Override
    public void update(Teacher source) {

    }

    @Override
    public void remove(Teacher target) {
        teachers.removeIf(teacher -> teacher.getId().equals(target.getId()));
    }
}
