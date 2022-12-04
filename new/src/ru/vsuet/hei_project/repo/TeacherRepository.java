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
    private final String INSERT = "insert into teachers(fio, yearBirth, monthBirth, dayBirth) values (?, ?, ?, ?);";
    private final String DELETE = "delete from teachers where id = ?;";
    private final String UPDATE = "update teachers set fio = ?, yearBirth = ?, monthBirth = ?, dayBirth = ? where id = ?;";

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
                Integer yearBirth = resultSet.getInt("yearBirth");
                Integer monthBirth = resultSet.getInt("monthBirth");
                Integer dayBirth = resultSet.getInt("dayBirth");

                statement1.setLong(1, id);
                ResultSet resultSet1 = statement1.executeQuery();
                List<Course> courses = new ArrayList<>();
                while (resultSet1.next()) {
                    long cId = resultSet1.getLong("c.id");
                    String cTitle = resultSet1.getString("c.title");
                    courses.add(new Course(cId, cTitle));
                }

                list.add(new Teacher(id, fio, yearBirth, monthBirth, dayBirth, courses));
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
                Integer yearBirth = resultSet.getInt("yearBirth");
                Integer monthBirth = resultSet.getInt("monthBirth");
                Integer dayBirth = resultSet.getInt("dayBirth");
                return new Teacher(id, fio, yearBirth, monthBirth, dayBirth, courses);
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
            Integer yearBirth = source.getYearBirth();
            Integer monthBirth = source.getMonthBirth();
            Integer dayBirth = source.getDayBirth();
            statement.setString(1, fio);
            statement.setInt(2, yearBirth);
            statement.setInt(3, monthBirth);
            statement.setInt(4, dayBirth);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while method save call: " + e.getMessage());
        }
    }

    @Override
    public void update(Teacher source) {
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE);
                ) {
            long id = source.getId();
            String fio = source.getFio();
            Integer yearBirth = source.getYearBirth();
            Integer monthBirth = source.getMonthBirth();
            Integer dayBirth = source.getDayBirth();
            statement.setString(1, fio);
            statement.setInt(2, yearBirth);
            statement.setInt(3, monthBirth);
            statement.setInt(4, dayBirth);
            statement.setLong(5, id);
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
