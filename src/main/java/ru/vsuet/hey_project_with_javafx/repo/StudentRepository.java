package ru.vsuet.hey_project_with_javafx.repo;

import ru.vsuet.hey_project_with_javafx.domain.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IRepository<Student> {
    private final String SELECT_ALL = "select * from students;";
    private final String SELECT_SINGLE = "select * from students where id = ?;";
    private final String INSERT = "insert into students(fio, yearBirth, monthBirth, dayBirth, yearStudy, numberRecordBook) values (?, ?, ?, ?, ?, ?);";        //создание без групппы
    private final String DELETE = "delete from students where id = ?;";
    private final String UPDATE = "update students set fio = ?, yearBirth = ?, monthBirth = ?, dayBirth = ?, yearStudy = ?, numberRecordBook = ?, group_id = ? where id = ?;";

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
                Integer yearBirth = resultSet.getInt("yearBirth");
                Integer monthBirth = resultSet.getInt("monthBirth");
                Integer dayBirth = resultSet.getInt("dayBirth");
                Integer yearStudy = resultSet.getInt("yearStudy");
                Integer numberRecordBook = resultSet.getInt("numberRecordBook");
                Long group_id = resultSet.getLong("group_id");
                Student student = new Student(id, fio, yearBirth, monthBirth, dayBirth, yearStudy, numberRecordBook, group_id);

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
                Integer yearBirth = resultSet.getInt("yearBirth");
                Integer monthBirth = resultSet.getInt("monthBirth");
                Integer dayBirth = resultSet.getInt("dayBirth");
                Integer yearStudy = resultSet.getInt("yearStudy");
                Integer numberRecordBook = resultSet.getInt("numberRecordBook");
                Long group_id = resultSet.getLong("group_id");
                return new Student(id, fio, yearBirth, monthBirth, dayBirth, yearStudy, numberRecordBook, group_id);
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
            int yearBirth = source.getYearBirth();
            int monthBirth = source.getMonthBirth();
            int dayBirth = source.getDayBirth();
            int yearStudy = source.getYearStudy();
            int numberRecordBook = source.getNumberRecordBook();
            statement.setString(1, fio);
            statement.setInt(2, yearBirth);
            statement.setInt(3, monthBirth);
            statement.setInt(4, dayBirth);
            statement.setInt(5, yearStudy);
            statement.setInt(6, numberRecordBook);
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
            Integer yearBirth = source.getYearBirth();
            Integer monthBirth = source.getMonthBirth();
            Integer dayBirth = source.getDayBirth();
            Integer yearStudy = source.getYearStudy();
            Integer numberRecordBook = source.getNumberRecordBook();
            Long group_id = source.getGroup_id();
            statement.setString(1, fio);
            statement.setInt(2, yearBirth);
            statement.setInt(3, monthBirth);
            statement.setInt(4, dayBirth);
            statement.setInt(5, yearStudy);
            statement.setInt(6, numberRecordBook);
            statement.setObject(7, group_id);
            statement.setLong(8, id);
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
