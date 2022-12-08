package ru.vsuet.hey_project_with_javafx.controllers.CourseMenu;

import javafx.collections.FXCollections;
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
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.io.IOException;

public class CourseMenuController {
    IService<Course> courseService;

    ObservableList<Course> courses;

    @FXML
    private Button addCourseButton;

    @FXML
    private TableColumn<Course, Long> courseIdTableColumn;

    @FXML
    private TableColumn<Course, Integer> courseNumberHoursTableColumn;

    @FXML
    private TableView<Course> courseTable;

    @FXML
    private TableColumn<Course, String> courseTitleTableColumn;

    @FXML
    private Button removeCourseButton;

    @FXML
    private Button updateCourseButton;

    @FXML
    void initialize() {
        courseIdTableColumn.setCellValueFactory(new PropertyValueFactory<Course, Long>("id"));
        courseTitleTableColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        courseNumberHoursTableColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("numberHours"));

        addCourseButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddCourseMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            AddCourseMenuController addCourseMenuController = loader.getController();
            addCourseMenuController.transferParameters(courseService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            addCourseButton.getScene().getWindow().hide();
        });

        updateCourseButton.setOnAction(actionEvent -> {
            Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("UpdateCourseMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            UpdateCourseMenuController updateCourseMenuController = loader.getController();
            updateCourseMenuController.transferParameters(courseService, selectedCourse);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            updateCourseButton.getScene().getWindow().hide();
        });

        removeCourseButton.setOnAction(actionEvent -> {
            Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
            courseService.removeById(selectedCourse.getId());
            courses.remove(selectedCourse);
            updateCourseTable();
        });
    }

    public void transferParameters(IService<Course> courseService) {
        this.courseService = courseService;

        start();
    }

    private void start() {
        courses = FXCollections.observableArrayList(courseService.getAll());

        updateCourseTable();
    }

    public void updateCourseTable() {
        courseTable.setItems(courses);
    }
}
