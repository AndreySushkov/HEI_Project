package ru.vsuet.hey_project_with_javafx.repo;

import ru.vsuet.hey_project_with_javafx.domain.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements IRepository<Course> {
    private final String SELECT_ALL = "select * from courses;";
    private final String SELECT_SINGLE = "select * from courses where id = ?;";
    private final String INSERT = "insert into courses(title, numberHours) values (?, ?);";
    private final String DELETE = "delete from courses where id = ?;";
    private final String UPDATE = "update courses set title = ?, numberHours = ?, teacher_id = ? where id = ?;";

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
                Integer numberHours = resultSet.getInt("numberHours");
                Long teacher_id = resultSet.getLong("teacher_id");
                Course course = new Course(id, title, numberHours, teacher_id);

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
                Integer numberHours = resultSet.getInt("numberHours");
                Long teacher_id = resultSet.getLong("teacher_id");
                return new Course(id, title, numberHours, teacher_id);
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
            Integer numberHours = source.getNumberHours();
            statement.setString(1, title);
            statement.setInt(2, numberHours);
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
            Integer numberHours = source.getNumberHours();
            Long teacher_id = source.getTeacher_id();
            statement.setString(1, title);
            statement.setInt(2, numberHours);
            statement.setObject(3, teacher_id);
            statement.setLong(4, id);
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
