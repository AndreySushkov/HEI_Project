package ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ru.vsuet.hey_project_with_javafx.controllers.Controller;
import ru.vsuet.hey_project_with_javafx.controllers.SceneLoader;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class UpdateTeacherMenuController extends Controller {
    private SceneLoader sceneLoader = new SceneLoader();

    private IService<Teacher> teacherService;
    private IService<Course> courseService;

    private Teacher teacher;

    @FXML
    private DatePicker dateBirthField;

    @FXML
    private TextField fioField;

    @FXML
    private Button applyButton;

    @FXML
    void initialize() {
        applyButton.setOnAction(actionEvent -> {
            String fio = fioField.getText();
            LocalDate date = dateBirthField.getValue();
            int yearBirth = date.getYear();
            int monthBirth = date.getMonthValue();
            int dayBirth = date.getDayOfMonth();

            Teacher newTeacher = new Teacher(teacher.getId(), fio, yearBirth, monthBirth, dayBirth);
            teacherService.update(newTeacher);

            String path = "TeacherMenu/TeacherMenu.fxml";
            TeacherMenuController teacherMenuController = (TeacherMenuController) sceneLoader.loadScene(path);
            teacherMenuController.transferParameters(teacherService, courseService);

            applyButton.getScene().getWindow().hide();
        });
    }

    public void transferParameters(IService<Teacher> teacherService, IService<Course> courseService, Teacher teacher) {
        this.teacherService = teacherService;
        this.courseService = courseService;

        this.teacher = teacher;

        start();
    }

    public void start() {
        fioField.setText(teacher.getFio());
        LocalDate date = LocalDate.of(teacher.getYearBirth(), teacher.getMonthBirth(), teacher.getDayBirth());
        dateBirthField.setValue(date);
    }
}