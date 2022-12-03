package ru.vsuet.hei_project.repo;

import ru.vsuet.hei_project.domain.Course;
import ru.vsuet.hei_project.domain.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements IRepository<Course> {
    private final String SELECT_ALL = "select * from courses;";
    private final String SELECT_SINGLE = "select * from courses where id = ?;";
    private final String INSERT = "insert into courses(title) values (?);";
    private final String DELETE = "delete from courses where id = ?;";
    private final String UPDATE = "update courses set title = ?, teacher_id = ? where id = ?;";

    private Connection connection;

    public CourseRepository(DataBaseConnector connector) {
        this.connection = connector.getConnection();
    }

    @Override
    public List<Course> list() {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
        ) {
            ResultSet resultSet = statement.executeQuery();
            List<Course> list = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                Course course = new Course(id, title);

                list.add(course);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error while method list call: " + e.getMessage());
        }
    }

    @Override
    public Course find(Long id) {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_SINGLE);
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                return new Course(id, title);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error while method find call: " + e.getMessage());
        }
    }

    @Override
    public void save(Course source) {
        try (
                PreparedStatement statement = connection.prepareStatement(INSERT);
        ) {
            String title = source.getTitle();
            statement.setString(1, title);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while method save call: " + e.getMessage());
        }
    }

    @Override
    public void update(Course source) {
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE);
        ) {
            long id = source.getId();
            String title = source.getTitle();
            Long teacher_id = source.getTeacher_id();
            statement.setString(1, title);
            statement.setObject(2, teacher_id);
            statement.setLong(3, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while method update call: " + e.getMessage());
        }
    }

    @Override
    public void remove(Course target) {
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
