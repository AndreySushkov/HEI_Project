package ru.vsuet.hei_project.repo;

import ru.vsuet.hei_project.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IRepository<Student> {
    private final String SELECT_ALL = "select * from students;";
    private final String SELECT_SINGLE = "select * from students where id = ?;";
    private final String INSERT = "insert into students(fio) values (?);";
    private final String DELETE = "delete from students where id = ?;";
    private final String UPDATE = "update students set fio = ?, group_id = ? where id = ?;";

    private Connection connection;

    public StudentRepository(DataBaseConnector connector) {
        this.connection = connector.getConnection();
    }

    @Override
    public List<Student> list() {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ) {
            ResultSet resultSet = statement.executeQuery();
            List<Student> list = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String fio = resultSet.getString("fio");
                Student student = new Student(id, fio);

                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error while method list call: " + e.getMessage());
        }
    }

    @Override
    public Student find(Long id) {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_SINGLE);
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String fio = resultSet.getString("fio");
                return new Student(id, fio);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error while method find call: " + e.getMessage());
        }
    }

    @Override
    public void save(Student source) {
        try (
                PreparedStatement statement = connection.prepareStatement(INSERT);
        ) {
            String fio = source.getFio();
            statement.setString(1, fio);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while method save call: " + e.getMessage());
        }
    }

    @Override
    public void update(Student source) {
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE);
        ) {
            long id = source.getId();
            String fio = source.getFio();
            Long group_id = source.getGroup_id();
            statement.setString(1, fio);
            statement.setObject(2, group_id);
            statement.setLong(3, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while method update call: " + e.getMessage());
        }
    }

    @Override
    public void remove(Student target) {
        try (
                PreparedStatement statement = connection.prepareStatement(DELETE);
        ) {
            long id = target.getId();
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while method remove call: " + e.getMessage());
        }
    }
}
