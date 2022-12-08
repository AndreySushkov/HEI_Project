package ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu;

import java.io.IOException;
import java.util.List;
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
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class UpdateTeacherCoursesMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

    private IService<Teacher> teacherService;
    private IService<Course> courseService;

    private Teacher teacher;

    private ObservableList<Course> courses;
    private ObservableList<Course> availableCourses;

    @FXML
    private Button addCourseButton;

    @FXML
    private Button applyButton;

    @FXML
    private TableColumn<Course, Long> availableCourseIdTableColumn;

    @FXML
    private TableColumn<Course, Integer> availableCourseNumberHoursTableColumn;

    @FXML
    private TableColumn<Course, String> availableCourseTitleTableColumn;

    @FXML
    private TableView<Course> availableCourseTable;

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
    void initialize() {
        courseIdTableColumn.setCellValueFactory(new PropertyValueFactory<Course, Long>("id"));
        courseTitleTableColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        courseNumberHoursTableColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("numberHours"));

        availableCourseIdTableColumn.setCellValueFactory(new PropertyValueFactory<Course, Long>("id"));
        availableCourseTitleTableColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("title"));
        availableCourseNumberHoursTableColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("numberHours"));

        addCourseButton.setOnAction(actionEvent -> {
            Course selectedCourse = availableCourseTable.getSelectionModel().getSelectedItem();
            courses.add(selectedCourse);
            availableCourses.remove(selectedCourse);
            updateCoursesTable();
            updateAvailableCoursesTable();
        });

        removeCourseButton.setOnAction(actionEvent -> {
            Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
            courses.remove(selectedCourse);
            availableCourses.add(selectedCourse);
            updateCoursesTable();
            updateAvailableCoursesTable();
        });

        applyButton.setOnAction(actionEvent -> {
            for (Course course : courses) {
                if (course.getTeacher_id() == 0) {
                    Course newCourse = new Course(course.getId(), course.getTitle(), course.getNumberHours(), teacher.getId());
                    courseService.update(newCourse);
                }
            }
            for (Course course : availableCourses) {
                if (course.getTeacher_id() != 0) {
                    Course newCourse = new Course(course.getId(), course.getTitle(), course.getNumberHours(), null);
                    courseService.update(newCourse);
                }
            }

            String path = "TeacherMenu/TeacherMenu.fxml";
            TeacherMenuController teacherMenuController = (TeacherMenuController) sceneLoader.loadScene(path);
            teacherMenuController.transferParameters(teacherService, courseService);

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Course> courseService, IService<Teacher> teacherService, Teacher teacher) {
        this.courseService = courseService;
        this.teacherService = teacherService;

        this.teacher = teacher;

        start();
    }

    public void start() {
        List<Course> allCourses = courseService.getAll();
        availableCourses = FXCollections.observableArrayList();
        courses = FXCollections.observableArrayList();
        for (Course course : allCourses) {
            if (course.getTeacher_id() == 0) {
                availableCourses.add(course);
            }
            if (course.getTeacher_id() == teacher.getId()) {
                courses.add(course);
            }
        }

        updateAvailableCoursesTable();
        updateCoursesTable();
    }

    public void updateCoursesTable() {
        courseTable.setItems(courses);
    }

    public void updateAvailableCoursesTable() {
        availableCourseTable.setItems(availableCourses);
    }
}