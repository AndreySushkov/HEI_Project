package ru.vsuet.hei_project.repo;

import ru.vsuet.hei_project.domain.Course;
import ru.vsuet.hei_project.domain.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository implements IRepository<Teacher> {
    private final String SELECT_ALL = "select * from teachers;";
    private final String SELECT_SINGLE = "select * from teachers where id = ?;";
    private final String SELECT_SINGLE_COURSES = "select * from teachers t join courses c on t.id = c.teacher_id where t.id = ?;";
    private final String SELECT_COURSES = "select * from courses;";
    private final String INSERT = "insert into teachers(fio) values (?);";
    private final String UPDATE_COURSE = "update courses set teacher_id = ? where id = ?";
    private final String DELETE = "delete from teachers where id = ?;";
    private final String UPDATE = "update teachers set fio = ? where id = ?;";

    private final Connection connection;

    public TeacherRepository(DataBaseConnector connector) {
        this.connection = connector.getConnection();
    }

    @Override
    public List<Teacher> list() {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                PreparedStatement statement1 = connection.prepareStatement(SELECT_SINGLE_COURSES);
                ) {
            ResultSet resultSet = statement.executeQuery();
            List<Teacher> list = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String fio = resultSet.getString("fio");

                statement1.setLong(1, id);
                ResultSet resultSet1 = statement1.executeQuery();
                List<Course> courses = new ArrayList<>();
                while (resultSet1.next()) {
                    long cId = resultSet1.getLong("c.id");
                    String cTitle = resultSet1.getString("c.title");
                    courses.add(new Course(cId, cTitle));
                }

                list.add(new Teacher(id, fio, courses));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error while method list call: " + e.getMessage());
        }
    }

    @Override
    public Teacher find(Long id) {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_SINGLE_COURSES);
                PreparedStatement statement1 = connection.prepareStatement(SELECT_SINGLE);
                ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                long cId = resultSet.getLong("c.id");
                String cTitle = resultSet.getString("c.title");
                courses.add(new Course(cId, cTitle));
            }

            statement1.setLong(1, id);
            resultSet = statement1.executeQuery();
            while (resultSet.next()) {
                String fio = resultSet.getString("fio");
                return new Teacher(id, fio, courses);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error while method find call: " + e.getMessage());
        }
    }

    @Override
    public void save(Teacher source) {
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
    public void update(Teacher source) {
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE);
                PreparedStatement statement1 = connection.prepareStatement(SELECT_COURSES);
                PreparedStatement statement2 = connection.prepareStatement(UPDATE_COURSE);
                ) {
            long id = source.getId();
            String fio = source.getFio();
            statement.setString(1, fio);
            statement.setLong(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while method update call: " + e.getMessage());
        }
    }

    @Override
    public void remove(Teacher target) {
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
