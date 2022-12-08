package ru.vsuet.hey_project_with_javafx.controllers.TeacherMenu;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.vsuet.hey_project_with_javafx.domain.Course;
import ru.vsuet.hey_project_with_javafx.domain.Teacher;
import ru.vsuet.hey_project_with_javafx.service.IService;

public class UpdateTeacherMenuController {
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

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TeacherMenu.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            TeacherMenuController teacherMenuController = loader.getController();
            teacherMenuController.transferParameters(teacherService, courseService);

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

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