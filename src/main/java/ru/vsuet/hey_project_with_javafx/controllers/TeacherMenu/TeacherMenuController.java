package ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

import java.util.Optional;

public class TeacherMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

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
            String path = "TeacherMenu/AddTeacherMenu.fxml";
            AddTeacherMenuController addTeacherMenuController = (AddTeacherMenuController) sceneLoader.loadScene(path);
            addTeacherMenuController.transferParameters(teacherService, courseService);

            addTeacherButton.getScene().getWindow().hide();
        });

        removeTeacherButton.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление преподавателя");
            alert.setHeaderText("Вы точно хотите удалить преподавателя?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK) {
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
            }
        });

        updateTeacherButton.setOnAction(actionEvent -> {
            Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();

            String path = "TeacherMenu/UpdateTeacherMenu.fxml";
            UpdateTeacherMenuController updateTeacherMenuController = (UpdateTeacherMenuController) sceneLoader.loadScene(path);
            updateTeacherMenuController.transferParameters(teacherService, courseService, selectedTeacher);

            updateTeacherButton.getScene().getWindow().hide();
        });

        updateCoursesButton.setOnAction(actionEvent -> {
            Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();

            String path = "TeacherMenu/UpdateTeacherCoursesMenu.fxml";
            UpdateTeacherCoursesMenuController updateTeacherCoursesMenuController = (UpdateTeacherCoursesMenuController) sceneLoader.loadScene(path);
            updateTeacherCoursesMenuController.transferParameters(courseService, teacherService, selectedTeacher);

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

        updateTeacherTable();
    }

    private void updateTeacherTable() {
        teacherTable.setItems(teachers);
    }
}
