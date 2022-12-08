package ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu;

import java.io.IOException;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class TeacherMenuController {
    private IService<Teacher> teacherService;
    private IService<Course> courseService;

    private ObservableList<Teacher> teachers;
    private ObservableList<Course> courses;

    @FXML
    private Button addTeacherButton;

    @FXML
    private TableColumn<Course, Long> courseIdTableColumn;

    @FXML
    private TableColumn<Course, Integer> courseNumberHoursTableColumn;

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> courseTitleTableColumn;

    @FXML
    private Button removeTeacherButton;

    @FXML
    private TableColumn<Teacher, Integer> teacherDayBirthTableColumn;

    @FXML
    private TableColumn<Teacher, String> teacherFioTableColumn;

    @FXML
    private TableColumn<Teacher, Long> teacherIdTableColumn;

    @FXML
    private TableColumn<Teacher, Integer> teacherMonthBirthTableColumn;

    @FXML
    private TableView<Teacher> teacherTable;

    @FXML
    private TableColumn<Teacher, Integer> teacherYearBirthTableColumn;

    @FXML
    private Button updateCoursesButton;

    @FXML
    private Button updateTeacherButton;

    @FXML
    void initialize() {
        teacherIdTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Long>("id"));
        teacherFioTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, String>("fio"));
        teacherDayBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("dayBirth"));
        teacherMonthBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("monthBirth"));
        teacherYearBirthTableColumn.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("yearBirth"));

        courseIdTableColumn.setCellValueFactory(new PropertyValueFactory<Course, Long>("id"));
        courseTitleTableColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        courseNumberHoursTableColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("numberHours"));

        teacherTable.setOnMouseClicked(mouseEvent -> {
            Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
            Long teacherId = selectedTeacher.getId();

            courses = FXCollections.observableArrayList();
            for (Course course : courseService.getAll()) {
                if (teacherId == course.getTeacher_id()) {
                    courses.add(course);
                }
            }

            courseTable.setItems(courses);
        });

        addTeacherButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddTeacherMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            AddTeacherMenuController addTeacherMenuController = loader.getController();
            addTeacherMenuController.transferParameters(teacherService, courseService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            addTeacherButton.getScene().getWindow().hide();
        });

        removeTeacherButton.setOnAction(actionEvent -> {
            Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
            Long teacherId = selectedTeacher.getId();

            for(Course course : courseService.getAll()) {
                if (teacherId == course.getTeacher_id()) {
                    Course newCourse = new Course(course.getId(), course.getTitle(), course.getNumberHours(), null);
                    courseService.update(newCourse);
                }
            }

            teachers.remove(selectedTeacher);
            teacherService.removeById(selectedTeacher.getId());

            updateTeacherTable();
        });

        updateTeacherButton.setOnAction(actionEvent -> {
            Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UpdateTeacherMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            UpdateTeacherMenuController updateTeacherMenuController = loader.getController();
            updateTeacherMenuController.transferParameters(teacherService, courseService, selectedTeacher);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            updateTeacherButton.getScene().getWindow().hide();
        });

        updateCoursesButton.setOnAction(actionEvent -> {
            Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UpdateTeacherCoursesMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            UpdateTeacherCoursesMenuController updateTeacherCoursesMenuController = loader.getController();
            updateTeacherCoursesMenuController.transferParameters(courseService, teacherService, selectedTeacher);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            updateCoursesButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Teacher> teacherService, IService<Course> courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;

        start();
    }

    private void start() {
        teachers = FXCollections.observableArrayList(teacherService.getAll());

        teachers.addListener(new ListChangeListener<Teacher>() {
            @Override
            public void onChanged(Change<? extends Teacher> change) {
                updateTeacherTable();
            }
        });

        updateTeacherTable();
    }

    private void updateTeacherTable() {
        teacherTable.setItems(teachers);
    }
}
