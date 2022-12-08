package ru.vsuet.hey_project_with_javafx.controllers.CourseMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.util.Optional;

public class CourseMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

    private IService<Course> courseService;

    private ObservableList<Course> courses;

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
            String path = "CourseMenu/AddCourseMenu.fxml";
            AddCourseMenuController addCourseMenuController = (AddCourseMenuController) sceneLoader.loadScene(path);
            addCourseMenuController.transferParameters(courseService);

            addCourseButton.getScene().getWindow().hide();
        });

        updateCourseButton.setOnAction(actionEvent -> {
            Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();

            String path = "CourseMenu/UpdateCourseMenu.fxml";
            UpdateCourseMenuController updateCourseMenuController = (UpdateCourseMenuController) sceneLoader.loadScene(path);
            updateCourseMenuController.transferParameters(courseService, selectedCourse);

            updateCourseButton.getScene().getWindow().hide();
        });

        removeCourseButton.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление курса");
            alert.setHeaderText("Вы точно хотите удалить курс?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
                Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
                courseService.removeById(selectedCourse.getId());
                courses.remove(selectedCourse);
                updateCourseTable();
            }
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
