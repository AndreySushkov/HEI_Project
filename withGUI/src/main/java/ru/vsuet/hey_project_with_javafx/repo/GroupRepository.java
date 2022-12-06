package ru.vsuet.hey_project_with_javafx.repo;

import ru.vsuet.hey_project_with_javafx.domain.Group;
import ru.vsuet.hey_project_with_javafx.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements IRepository<Group> {
    private final String SELECT_ALL = "select * from groups_;";
    private final String SELECT_SINGLE = "select * from groups_ where id = ?;";
    private final String SELECT_SINGLE_STUDENTS = "select * from groups_ g join students s on g.id = s.group_id where g.id = ?;";
    private final String INSERT = "insert into groups_(title) values (?);";
    private final String DELETE = "delete from groups_ where id = ?;";
    private final String UPDATE = "update groups_ set title = ? where id = ?;";

    private final Connection connection;

    public GroupRepository(DataBaseConnector connector) {
        this.connection = connector.getConnection();
    }

    @Override
    public List<Group> list() {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
                PreparedStatement statement1 = connection.prepareStatement(SELECT_SINGLE_STUDENTS);
        ) {
            ResultSet resultSet = statement.executeQuery();
            List<Group> list = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");

                statement1.setLong(1, id);
                ResultSet resultSet1 = statement1.executeQuery();
                List<Student> students = new ArrayList<>();
                while (resultSet1.next()) {
                    long sId = resultSet1.getLong("s.id");
                    String sFio = resultSet1.getString("s.fio");
                    Integer sYearBirth = resultSet1.getInt("s.yearBirth");
                    Integer sMonthBirth = resultSet1.getInt("s.monthBirth");
                    Integer sDayBirth = resultSet1.getInt("s.dayBirth");
                    Integer sYearStudy = resultSet1.getInt("s.yearStudy");
                    Integer sNumberRecordBook = resultSet1.getInt("s.numberRecordBook");
                    students.add(new Student(sId, sFio, sYearBirth, sMonthBirth, sDayBirth, sYearStudy, sNumberRecordBook));
                }

                list.add(new Group(id, title, students));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Error while method list call: " + e.getMessage());
        }
    }

    @Override
    public Group find(Long id) {
        try (
                PreparedStatement statement = connection.prepareStatement(SELECT_SINGLE_STUDENTS);
                PreparedStatement statement1 = connection.prepareStatement(SELECT_SINGLE);
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                long sId = resultSet.getLong("s.id");
                String sTitle = resultSet.getString("s.fio");
                Integer sYearBirth = resultSet.getInt("s.yearBirth");
                Integer sMonthBirth = resultSet.getInt("s.monthBirth");
                Integer sDayBirth = resultSet.getInt("s.dayBirth");
                Integer sYearStudy = resultSet.getInt("s.yearStudy");
                Integer sNumberRecordBook = resultSet.getInt("s.numberRecordBook");
                students.add(new Student(sId, sTitle, sYearBirth, sMonthBirth, sDayBirth, sYearStudy, sNumberRecordBook));
            }

            statement1.setLong(1, id);
            resultSet = statement1.executeQuery();
            while (resultSet.next()) {
                String fio = resultSet.getString("title");
                return new Group(id, fio, students);
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Error while method find call: " + e.getMessage());
        }
    }

    @Override
    public void save(Group source) {
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
    public void update(Group source) {
        try (
                PreparedStatement statement = connection.prepareStatement(UPDATE);
        ) {
            long id = source.getId();
            String title = source.getTitle();
            statement.setString(1, title);
            statement.setLong(2, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while method update call: " + e.getMessage());
        }
    }

    @Override
    public void remove(Group target) {
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
